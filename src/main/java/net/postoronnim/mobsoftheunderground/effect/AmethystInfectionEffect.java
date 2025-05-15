package net.postoronnim.mobsoftheunderground.effect;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.*;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageSources;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import net.postoronnim.mobsoftheunderground.MobsOfTheUnderground;
import net.postoronnim.mobsoftheunderground.MobsOfTheUndergroundClient;
import net.postoronnim.mobsoftheunderground.entity.ModEntities;
import net.postoronnim.mobsoftheunderground.entity.amethyst_infected.custom.AmethystInfectedEntity;
import net.postoronnim.mobsoftheunderground.entity.geodite.custom.GeoditeEntity;
import net.postoronnim.mobsoftheunderground.entity.shardling.custom.ShardlingEntity;
import net.postoronnim.mobsoftheunderground.util.ShardlingSpawner;

import java.util.Map;

public class AmethystInfectionEffect extends StatusEffect {

    private static final Identifier AMETHYST_INFECTION_ARMOR_MODIFIER = Identifier.of(MobsOfTheUnderground.MOD_ID, "amethyst_infection_armor_modifier");

    public AmethystInfectionEffect(StatusEffectCategory category, int color, ParticleEffect particleEffect) {
        super(category, color, particleEffect);
        this.addAttributeModifier(EntityAttributes.GENERIC_ARMOR, AMETHYST_INFECTION_ARMOR_MODIFIER, 4f, EntityAttributeModifier.Operation.ADD_VALUE);
        this.applySound(SoundEvents.BLOCK_AMETHYST_CLUSTER_BREAK);
    }

    @Override
    public void onApplied(LivingEntity entity, int amplifier) {
        if(entity.getStatusEffect(ModEffects.AMETHYSTIZATION) != null
                || entity.getStatusEffect(ModEffects.FATAL_AMETHYSTIZATION) != null
                || (entity instanceof ShardlingEntity)
                || (entity instanceof GeoditeEntity)
                || (entity instanceof AmethystInfectedEntity)) {
            entity.removeStatusEffect(ModEffects.AMETHYST_INFECTION);
        }
    }

    public boolean applyUpdateEffect(LivingEntity entity, int amplifier) {

        if (entity.getStatusEffect(ModEffects.AMETHYST_INFECTION).getDuration() <= 1) {
            entity.addStatusEffect(new StatusEffectInstance(ModEffects.AMETHYSTIZATION,
                    6000, 0, false, true, true));
            entity.removeStatusEffect(ModEffects.AMETHYST_INFECTION);;
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
//        ShardlingSpawner.spawnShardlings(entity.getWorld(), entity.getPos(), 1);
    }
}
