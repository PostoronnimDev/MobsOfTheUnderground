package net.postoronnim.mobsoftheunderground.entity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.postoronnim.mobsoftheunderground.MobsOfTheUnderground;
import net.postoronnim.mobsoftheunderground.entity.amethyst_infected.custom.AmethystInfectedEntity;
import net.postoronnim.mobsoftheunderground.entity.geodite.custom.GeoditeEntity;
import net.postoronnim.mobsoftheunderground.entity.shardling.custom.ShardlingEntity;

public class ModEntities {


    public static final EntityType<GeoditeEntity> GEODITE = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(MobsOfTheUnderground.MOD_ID, "geodite"),
            EntityType.Builder.create(GeoditeEntity::new, SpawnGroup.MONSTER)
                    .dimensions(1.8f, 2.5f).build());

    public static final EntityType<ShardlingEntity> SHARDLING = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(MobsOfTheUnderground.MOD_ID, "shardling"),
            EntityType.Builder.create(ShardlingEntity::new, SpawnGroup.MONSTER)
                    .dimensions(0.5f, 0.5f).build());

    public static final EntityType<AmethystInfectedEntity> AMETHYST_INFECTED = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(MobsOfTheUnderground.MOD_ID, "amethyst_infected"),
            EntityType.Builder.create(AmethystInfectedEntity::new, SpawnGroup.MONSTER)
                    .dimensions(0.8f, 1.8f).build());


    public static void registerModEntities() {
        MobsOfTheUnderground.LOGGER.info("Register Mod Entities for " + MobsOfTheUnderground.MOD_ID);
        FabricDefaultAttributeRegistry.register(GEODITE, GeoditeEntity.createAttributes());
        FabricDefaultAttributeRegistry.register(SHARDLING, ShardlingEntity.createAttributes());
        FabricDefaultAttributeRegistry.register(AMETHYST_INFECTED, AmethystInfectedEntity.createAttributes());
    }
}
