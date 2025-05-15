package net.postoronnim.mobsoftheunderground.entity.geodite.ai;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Dynamic;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.brain.*;
import net.minecraft.entity.ai.brain.sensor.Sensor;
import net.minecraft.entity.ai.brain.sensor.SensorType;
import net.minecraft.entity.ai.brain.task.*;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;
import net.minecraft.util.math.random.Random;
import net.postoronnim.mobsoftheunderground.entity.geodite.custom.GeoditeEntity;

import java.util.Optional;

public class GeoditeBrain {
    private static final float WALK_SPEED = 0.4f;
    private static final float ATTACK_WALK_SPEED = 0.7f;
    private static final ImmutableList<SensorType<? extends Sensor<? super GeoditeEntity>>> SENSORS = ImmutableList.of(
            SensorType.NEAREST_LIVING_ENTITIES, SensorType.HURT_BY, SensorType.NEAREST_PLAYERS
    );
    private static final ImmutableList<MemoryModuleType<?>> MEMORY_MODULES = ImmutableList.of(
            //NearestLivingEntitiesSensor outputs
            MemoryModuleType.MOBS,
            MemoryModuleType.VISIBLE_MOBS,

            //HurtBySensor outputs
            MemoryModuleType.HURT_BY,
            MemoryModuleType.HURT_BY_ENTITY,

            //NearestPlayersSensor outputs
            MemoryModuleType.NEAREST_PLAYERS,
            MemoryModuleType.NEAREST_VISIBLE_PLAYER,
            MemoryModuleType.NEAREST_VISIBLE_TARGETABLE_PLAYER,

            MemoryModuleType.PATH,
            MemoryModuleType.CANT_REACH_WALK_TARGET_SINCE,
            MemoryModuleType.WALK_TARGET,
            MemoryModuleType.LOOK_TARGET,
            MemoryModuleType.ATTACK_TARGET,
            MemoryModuleType.ATTACK_COOLING_DOWN
            );

    public static void initialize(GeoditeEntity ironOreGolem, Random random) {
    }

    public static Brain.Profile<GeoditeEntity> createProfile() {
        return Brain.createProfile(MEMORY_MODULES, SENSORS);
    }

    public static Brain<?> create(GeoditeEntity ironOreGolem, Dynamic<?> dynamic) {
        Brain.Profile<GeoditeEntity> profile = Brain.createProfile(MEMORY_MODULES, SENSORS);
        Brain<GeoditeEntity> brain = profile.deserialize(dynamic);
        addCoreActivities(brain);
        addIdleActivities(brain);
        addFightActivities(brain, ironOreGolem);
        brain.setCoreActivities(ImmutableSet.of(Activity.CORE));
        brain.setDefaultActivity(Activity.IDLE);
        brain.resetPossibleActivities();
        return brain;
    }

    private static void addCoreActivities(Brain<GeoditeEntity> brain) {
        brain.setTaskList(
                Activity.CORE,
                0,
                ImmutableList.of(
                        new LookAroundTask(45, 90),
                        new MoveToTargetTask()
                )
        );
    }

    private static void addIdleActivities(Brain<GeoditeEntity> brain) {
        brain.setTaskList(
                Activity.IDLE,
                ImmutableList.of(
                        Pair.of(2, new RandomTask<>(
                                ImmutableMap.of(MemoryModuleType.WALK_TARGET, MemoryModuleState.VALUE_ABSENT),
                                ImmutableList.of(
                                        Pair.of(StrollTask.create(WALK_SPEED), 1),
                                        Pair.of(new WaitTask(20, 100), 1)
                                ))
                        ),
                        Pair.of(3, UpdateAttackTargetTask.create(GeoditeBrain::getPreferredTarget))
                )
        );
    }

    private static void addFightActivities(Brain<GeoditeEntity> brain, GeoditeEntity entity) {
        brain.setTaskList(
                Activity.FIGHT,
                10,
                ImmutableList.of(
                        RangedApproachTask.create(ATTACK_WALK_SPEED),
                        DelayedAttackTask.create(50),
                        ForgetAttackTargetTask.create()
                ),
                MemoryModuleType.ATTACK_TARGET
        );
    }

    private static Optional<? extends LivingEntity> getPreferredTarget(GeoditeEntity entity) {
        Optional<PlayerEntity> optional = entity.getBrain().getOptionalRegisteredMemory(MemoryModuleType.NEAREST_VISIBLE_TARGETABLE_PLAYER);

        return optional;
    }

    public static void updateActivities(GeoditeEntity entity) {
        Brain<?> brain = entity.getBrain();
        brain.resetPossibleActivities(ImmutableList.of(Activity.FIGHT, Activity.IDLE));
    }

    public static class DelayedAttackTask {
        public static SingleTickTask<GeoditeEntity> create(int cooldown) {
            return TaskTriggerer.task(
                    context -> context.group(
                                    context.queryMemoryOptional(MemoryModuleType.LOOK_TARGET),
                                    context.queryMemoryValue(MemoryModuleType.ATTACK_TARGET),
                                    context.queryMemoryAbsent(MemoryModuleType.ATTACK_COOLING_DOWN),
                                    context.queryMemoryValue(MemoryModuleType.VISIBLE_MOBS)
                            )
                            .apply(
                                    context,
                                    (lookTarget, attackTarget, attackCoolingDown, visibleMobs) -> (world, entity, time) -> {
                                        LivingEntity livingEntity = context.getValue(attackTarget);

                                        if (!entity.isInAttackRange(livingEntity) ||
                                                !context.<LivingTargetCache>getValue(visibleMobs).contains(livingEntity)) {
                                            return false;
                                        }

                                        lookTarget.remember(new EntityLookTarget(livingEntity, true));
                                        entity.startDelayedAttack();

                                        attackCoolingDown.remember(true, cooldown);
                                        return true;
                                    }
                            )
            );
        }
    }
}
