package net.postoronnim.mobsoftheunderground.entity.geodite.custom;

import com.mojang.serialization.Dynamic;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockState;
import net.minecraft.client.render.entity.model.ZombieEntityModel;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.brain.Brain;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.profiler.Profilers;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import net.postoronnim.mobsoftheunderground.entity.ModEntities;
import net.postoronnim.mobsoftheunderground.entity.geodite.DelayedAttacker;
import net.postoronnim.mobsoftheunderground.entity.geodite.ai.GeoditeBrain;
import net.postoronnim.mobsoftheunderground.entity.shardling.custom.ShardlingEntity;
import net.postoronnim.mobsoftheunderground.util.ShardlingSpawner;
import org.jetbrains.annotations.Nullable;

public class GeoditeEntity extends HostileEntity implements DelayedAttacker {
    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;
    public final AnimationState attackAnimationState = new AnimationState();

    private int attackHitTimeout = 0;
    public static final TrackedData<Boolean> DELAYING_ATTACK = DataTracker.registerData(GeoditeEntity.class, TrackedDataHandlerRegistry.BOOLEAN);

    @Override
    public void startDelayedAttack() {
        this.attackHitTimeout = 25;
        this.dataTracker.set(DELAYING_ATTACK, true);
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
        super.initDataTracker(builder);
        builder.add(DELAYING_ATTACK, false);
    }

    @Override
    public void onTrackedDataSet(TrackedData<?> data) {
        if (DELAYING_ATTACK.equals(data) && this.getWorld().isClient()) {
            boolean delayingAttack = this.dataTracker.get(DELAYING_ATTACK);
            if (delayingAttack) {
                this.attackAnimationState.start(this.age); // Start sitting animation
            }
        }
        super.onTrackedDataSet(data);
    }

    public GeoditeEntity(EntityType<GeoditeEntity> type, World world) {
        super(type, world);
    }

    @Override
    public float getSoundPitch() {
        return 0.4f;
    }

    public static DefaultAttributeContainer.Builder createAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.MAX_HEALTH, 150)
                .add(EntityAttributes.MOVEMENT_SPEED, 0.4)
                .add(EntityAttributes.ATTACK_DAMAGE, 12)
                .add(EntityAttributes.KNOCKBACK_RESISTANCE, 10)
                .add(EntityAttributes.ARMOR, 8);
    }

    @Override
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData) {
        GeoditeBrain.initialize(this, world.getRandom());
        return super.initialize(world, difficulty, spawnReason, entityData);
    }

    @Override
    protected Brain<?> deserializeBrain(Dynamic<?> dynamic) {
        return GeoditeBrain.create(this, dynamic);
    }

    @Override
    protected void mobTick(ServerWorld serverWorld) {
        Profilers.get().push("geoditeBrain");
        Brain<?> brain = this.getBrain();
        ((Brain<GeoditeEntity>)brain).tick((ServerWorld)this.getWorld(), this);
        GeoditeBrain.updateActivities(this);
        Profilers.get().pop();
        super.mobTick(serverWorld);
    }

    @Override
    public void tick() {
        super.tick();

        if (this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = 80;
            if (this.getWorld().isClient()){
                this.idleAnimationState.start(this.age);
            }
        } else {
            this.idleAnimationTimeout--;
        }


        if(this.dataTracker.get(DELAYING_ATTACK)) {
            if (this.attackHitTimeout <= 0) {
                LivingEntity target = brain.getOptionalMemory(MemoryModuleType.ATTACK_TARGET).isPresent()
                        ? brain.getOptionalMemory(MemoryModuleType.ATTACK_TARGET).get() : null;
                this.playSound(SoundEvents.ENTITY_WARDEN_ATTACK_IMPACT, 10.0F, this.getSoundPitch());
                this.playSound(SoundEvents.BLOCK_AMETHYST_BLOCK_HIT, 10f, this.getSoundPitch());
                if (target != null && this.getWorld() instanceof ServerWorld serverWorld) {
                    if(this.isInAttackRange(target)) {
                        this.tryAttack(serverWorld, target);
                    }
                }
                this.dataTracker.set(DELAYING_ATTACK, false);
            } else {
                this.attackAnimationState.startIfNotRunning(this.age);
                this.attackHitTimeout--;
            }
        }
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_IRON_GOLEM_DEATH;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.ENTITY_IRON_GOLEM_HURT;
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {
        this.playSound(SoundEvents.BLOCK_DEEPSLATE_HIT, 4f, this.getSoundPitch());
    }

    @Override
    protected void applyDamage(ServerWorld serverWorld, DamageSource source, float amount) {
        if (amount > 8f) {
            if(serverWorld.getRandom().nextFloat() > 0.5f) {
                ShardlingSpawner.spawnParticleExplosion(this, new Vec3d(this.getX(), this.getY() + 1, this.getZ()), 20);
            }
        }
        super.applyDamage(serverWorld, source, amount);
    }

    @Override
    public boolean tryAttack(ServerWorld serverWorld, Entity target) {
        serverWorld.sendEntityStatus(this, EntityStatuses.PLAY_ATTACK_SOUND);
        return super.tryAttack(serverWorld, target);
    }

    @Override
    public void onDeath(DamageSource damageSource) {
        ShardlingSpawner.spawnShardlings(this, this.getPos(), 3);

        super.onDeath(damageSource);
    }
}
