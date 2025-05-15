package net.postoronnim.mobsoftheunderground.world.gen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.SpawnLocationTypes;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.world.Heightmap;
import net.postoronnim.mobsoftheunderground.entity.ModEntities;

public class ModEntitySpawns {

    public static void addSpawns() {
        BiomeModifications.addSpawn(BiomeSelectors.foundInOverworld(), SpawnGroup.MONSTER, ModEntities.GEODITE, 20, 1, 1);
        BiomeModifications.addSpawn(BiomeSelectors.foundInOverworld(), SpawnGroup.MONSTER, ModEntities.AMETHYST_INFECTED, 30, 1, 3);


        SpawnRestriction.register(ModEntities.GEODITE, SpawnLocationTypes.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
                (type, world, spawnReason, pos, random)
                        -> pos.getY() < 0 && world.getLightLevel(pos) < 8);

        SpawnRestriction.register(ModEntities.AMETHYST_INFECTED, SpawnLocationTypes.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
                (type, world, spawnReason, pos, random)
                        -> pos.getY() < 30 && world.getLightLevel(pos) < 8);
    }
}
