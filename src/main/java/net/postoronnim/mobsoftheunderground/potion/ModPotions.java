package net.postoronnim.mobsoftheunderground.potion;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.potion.Potion;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.postoronnim.mobsoftheunderground.MobsOfTheUnderground;
import net.postoronnim.mobsoftheunderground.MobsOfTheUndergroundClient;
import net.postoronnim.mobsoftheunderground.effect.ModEffects;

import javax.swing.*;

public class ModPotions {

    public static final RegistryEntry<Potion> AMETHYST_INFECTION_POTION = registerPotion("amethyst_infection_potion",
            new Potion(new StatusEffectInstance(ModEffects.AMETHYST_INFECTION, 6000, 0)));

    private static RegistryEntry<Potion> registerPotion(String name, Potion potion) {
        return Registry.registerReference(Registries.POTION, Identifier.of(MobsOfTheUnderground.MOD_ID, name), potion);
    }

    public static void registerPotions() {
        MobsOfTheUnderground.LOGGER.info("Registering Mod Potions for " + MobsOfTheUnderground.MOD_ID);
    }
}
