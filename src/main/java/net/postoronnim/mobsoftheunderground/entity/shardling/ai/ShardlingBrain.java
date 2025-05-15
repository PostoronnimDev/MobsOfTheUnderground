package net.postoronnim.mobsoftheunderground.entity.shardling.ai;

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
import net.minecraft.entity.mob.PiglinBrain;
import net.minecraft.entity.mob.WardenBrain;
import net.minecraft.entity.mob.WardenEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.random.Random;
import net.postoronnim.mobsoftheunderground.effect.ModEffects;
import net.postoronnim.mobsoftheunderground.entity.amethyst_infected.custom.AmethystInfectedEntity;
import net.postoronnim.mobsoftheunderground.entity.geodite.custom.GeoditeEntity;
import net.postoronnim.mobsoftheunderground.entity.shardling.custom.ShardlingEntity;

import java.util.Optional;
import java.util.function.Predicate;

public class ShardlingBrain {
    private static final float WALK_SPEED = 0.6f;
    private static final float ATTACK_WALK_SPEED = 0.7f;
    private static final ImmutableList<SensorType<? extends Sensor<? super ShardlingEntity>>> SENSORS = ImmutableList.of(
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

    public static void initialize(ShardlingEntity ironOreGolem, Random random) {
    }

    public static Brain.Profile<ShardlingEntity> createProfile() {
        return Brain.createProfile(MEMORY_MODULES, SENSORS);
    }

    public static Brain<?> create(ShardlingEntity ironOreGolem, Dynamic<?> dynamic) {
        Brain.Profile<ShardlingEntity> profile = Brain.createProfile(MEMORY_MODULES, SENSORS);
        Brain<ShardlingEntity> brain = profile.deserialize(dynamic);
        addCoreActivities(brain);
        addIdleActivities(brain);
        addFightActivities(brain, ironOreGolem);
        brain.setCoreActivities(ImmutableSet.of(Activity.CORE));
        brain.setDefaultActivity(Activity.IDLE);
        brain.resetPossibleActivities();
        return brain;
    }

    private static void addCoreActivities(Brain<ShardlingEntity> brain) {
        brain.setTaskList(
                Activity.CORE,
                0,
                ImmutableList.of(
                        new LookAroundTask(45, 90),
                        new MoveToTargetTask()
                )
        );
    }

    private static void addIdleActivities(Brain<ShardlingEntity> brain) {
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
                        Pair.of(3, UpdateAttackTargetTask.create(ShardlingBrain::getPreferredTarget))
                )
        );
    }

    private static void addFightActivities(Brain<ShardlingEntity> brain, ShardlingEntity entity) {
        brain.setTaskList(
                Activity.FIGHT,
                10,
                ImmutableList.of(
                        RangedApproachTask.create(ATTACK_WALK_SPEED),
                        MeleeAttackTask.create(30),
                        UpdateAttackTargetTask.create(ShardlingBrain::getPreferredTarget),
                        ForgetAttackTargetTask.create()
                ),
                MemoryModuleType.ATTACK_TARGET
        );
    }

    private static Optional<? extends LivingEntity> getPreferredTarget(ShardlingEntity entity) {

        Optional<LivingEntity> optional = Optional.empty();

        if(entity.getBrain().getOptionalRegisteredMemory(MemoryModuleType.VISIBLE_MOBS).isPresent()) {
            var targets = entity.getBrain().getOptionalRegisteredMemory(MemoryModuleType.VISIBLE_MOBS).get().iterate(livingEntity
                    -> (livingEntity.getStatusEffect(ModEffects.AMETHYST_INFECTION) == null
                    && livingEntity.getStatusEffect(ModEffects.AMETHYSTIZATION) == null
                    && livingEntity.getStatusEffect(ModEffects.FATAL_AMETHYSTIZATION) == null
                    && !(livingEntity instanceof ShardlingEntity)
                    && !(livingEntity instanceof GeoditeEntity)
                    && !(livingEntity instanceof AmethystInfectedEntity)));

            LivingEntity nearestTarget = null;

            for (var target : targets) {
                if (nearestTarget == null) {
                    nearestTarget = target;
                } else {
                    if (entity.distanceTo(target) < entity.distanceTo(nearestTarget)) {
                        nearestTarget = target;
                    }
                }
            }

            if(nearestTarget != null) {
                optional =  Optional.of(nearestTarget);
            }
        }

        return optional;
    }

    public static void updateActivities(ShardlingEntity entity) {
        Brain<?> brain = entity.getBrain();
        brain.resetPossibleActivities(ImmutableList.of(Activity.FIGHT, Activity.IDLE));
    }

}
