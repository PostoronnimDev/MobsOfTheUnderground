package net.postoronnim.mobsoftheunderground.particle;

import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.postoronnim.mobsoftheunderground.MobsOfTheUnderground;
import net.postoronnim.mobsoftheunderground.MobsOfTheUndergroundClient;

public class ModParticles {

    public static final SimpleParticleType AMETHYST_PARTICLE =
            registerParticle("amethyst_particle", FabricParticleTypes.simple());

    private static SimpleParticleType registerParticle(String name, SimpleParticleType particleType) {
        return Registry.register(Registries.PARTICLE_TYPE, Identifier.of(MobsOfTheUnderground.MOD_ID, name), particleType);
    }

    public static void registerParticles() {
        MobsOfTheUnderground.LOGGER.info("Registering Mod Particles for " + MobsOfTheUnderground.MOD_ID);
    }
}
