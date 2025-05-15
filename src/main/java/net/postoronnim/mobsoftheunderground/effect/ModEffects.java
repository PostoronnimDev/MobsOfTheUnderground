package net.postoronnim.mobsoftheunderground.effect;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.postoronnim.mobsoftheunderground.MobsOfTheUnderground;
import net.postoronnim.mobsoftheunderground.particle.ModParticles;

public class ModEffects {

    public static final RegistryEntry<StatusEffect> AMETHYST_INFECTION = registerStatusEffect("amethyst_infection",
            new AmethystInfectionEffect(StatusEffectCategory.NEUTRAL, 0xA672D1, ModParticles.AMETHYST_PARTICLE));
    public static final RegistryEntry<StatusEffect> AMETHYSTIZATION = registerStatusEffect("amethystization",
            new AmethystizationEffect(StatusEffectCategory.BENEFICIAL, 0xA672D1, ModParticles.AMETHYST_PARTICLE));
    public static final RegistryEntry<StatusEffect> FATAL_AMETHYSTIZATION = registerStatusEffect("fatal_amethystization",
            new FatalAmethystizationEffect(StatusEffectCategory.HARMFUL, 0xA672D1, ModParticles.AMETHYST_PARTICLE));

    private static RegistryEntry<StatusEffect> registerStatusEffect(String name, StatusEffect statusEffect) {
        return Registry.registerReference(Registries.STATUS_EFFECT, Identifier.of(MobsOfTheUnderground.MOD_ID, name), statusEffect);
    }

    public static void registerEffects() {
        MobsOfTheUnderground.LOGGER.info("Registering Mod Effects for " + MobsOfTheUnderground.MOD_ID);
    }
}
