package net.postoronnim.mobsoftheunderground.entity.shardling.custom;

import com.mojang.serialization.Dynamic;
import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.brain.Brain;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import net.postoronnim.mobsoftheunderground.effect.ModEffects;
import net.postoronnim.mobsoftheunderground.entity.shardling.ai.ShardlingBrain;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShardlingEntity extends HostileEntity {
    private static final Logger log = LoggerFactory.getLogger(ShardlingEntity.class);
    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;
    public final AnimationState attackAnimationState = new AnimationState();

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
        super.initDataTracker(builder);
    }

    public ShardlingEntity(EntityType<ShardlingEntity> type, World world) {
        super(type, world);
    }

    public static DefaultAttributeContainer.Builder createAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 10)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.5)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 2);
    }

    @Override
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData) {
        ShardlingBrain.initialize(this, world.getRandom());
        return super.initialize(world, difficulty, spawnReason, entityData);
    }

    @Override
    protected Brain.Profile<ShardlingEntity> createBrainProfile() {
        return ShardlingBrain.createProfile();
    }

    @Override
    protected Brain<?> deserializeBrain(Dynamic<?> dynamic) {
        return ShardlingBrain.create(this, dynamic);
    }

    @Override
    protected void mobTick() {
        this.getWorld().getProfiler().push("shardlingBrain");
        Brain<?> brain = this.getBrain();
        ((Brain<ShardlingEntity>)brain).tick((ServerWorld)this.getWorld(), this);
        ShardlingBrain.updateActivities(this);
        this.getWorld().getProfiler().pop();
        super.mobTick();
    }

    @Override
    public void tick() {
        super.tick();

        if (this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = 40;
            if (this.getWorld().isClient()){
                this.idleAnimationState.start(this.age);
            }
        } else {
            this.idleAnimationTimeout--;
        }
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.BLOCK_AMETHYST_BLOCK_BREAK;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.BLOCK_AMETHYST_BLOCK_HIT;
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {
        this.playSound(SoundEvents.BLOCK_DEEPSLATE_HIT, 0.3f, 2f);
        if (this.getWorld().getRandom().nextFloat() > 0.5f) {
            this.playSound(SoundEvents.BLOCK_AMETHYST_BLOCK_STEP, 0.2f, 1f);
        }
    }

    @Override
    public void handleStatus(byte status) {
        if (status == EntityStatuses.PLAY_ATTACK_SOUND) {
            this.attackAnimationState.start(this.age);
        } else {
            super.handleStatus(status);
        }
    }

    @Override
    public boolean tryAttack(Entity target) {
        this.getWorld().sendEntityStatus(this, EntityStatuses.PLAY_ATTACK_SOUND);
        this.playSound(SoundEvents.BLOCK_AMETHYST_BLOCK_HIT, 1.0f, this.getSoundPitch());
        if(target instanceof LivingEntity livingEntity) {
            if(this.getWorld().getRandom().nextFloat() > 0.66) {
                if(livingEntity.getStatusEffect(ModEffects.AMETHYST_INFECTION) == null
                        && livingEntity.getStatusEffect(ModEffects.AMETHYSTIZATION) == null
                        && livingEntity.getStatusEffect(ModEffects.FATAL_AMETHYSTIZATION) == null) {
                    livingEntity.setStatusEffect(new StatusEffectInstance(ModEffects.AMETHYST_INFECTION, 6000, 0, false, true, true), this);
                    this.kill();
                }
            }
        }
        return super.tryAttack(target);
    }
}
