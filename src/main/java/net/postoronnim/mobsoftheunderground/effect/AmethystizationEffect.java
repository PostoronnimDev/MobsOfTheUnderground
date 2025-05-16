package net.postoronnim.mobsoftheunderground.effect;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.postoronnim.mobsoftheunderground.MobsOfTheUnderground;
import net.postoronnim.mobsoftheunderground.util.ShardlingSpawner;

public class AmethystizationEffect extends StatusEffect {
    private static final Identifier AMETHYSTIZATION_ARMOR_MODIFIER = Identifier.of(MobsOfTheUnderground.MOD_ID, "amethystization_armor_modifier");
    private static final Identifier AMETHYSTIZATION_SPEED_MODIFIER = Identifier.of(MobsOfTheUnderground.MOD_ID, "amethystization_speed_modifier");


    public AmethystizationEffect(StatusEffectCategory category, int color, ParticleEffect particleEffect) {
        super(category, color, particleEffect);
        this.applySound(SoundEvents.BLOCK_AMETHYST_CLUSTER_BREAK);
        this.addAttributeModifier(EntityAttributes.GENERIC_ARMOR, AMETHYSTIZATION_ARMOR_MODIFIER, 6f, EntityAttributeModifier.Operation.ADD_VALUE);
        this.addAttributeModifier(EntityAttributes.GENERIC_MOVEMENT_SPEED, AMETHYSTIZATION_SPEED_MODIFIER, -0.2f, EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE);
    }

    public boolean applyUpdateEffect(LivingEntity entity, int amplifier) {

        if (entity.getStatusEffect(ModEffects.AMETHYSTIZATION).getDuration() <= 1) {
            entity.addStatusEffect(new StatusEffectInstance(ModEffects.FATAL_AMETHYSTIZATION,
                    6000, 0, false, true, true));
            entity.removeStatusEffect(ModEffects.AMETHYSTIZATION);
            return false;
        }

        return true;
    }


    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

    @Override
    public void onEntityRemoval(LivingEntity entity, int amplifier, Entity.RemovalReason reason) {
        ShardlingSpawner.spawnShardlings(entity, entity.getPos(), 2);
    }
}
