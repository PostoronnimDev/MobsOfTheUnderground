package net.postoronnim.mobsoftheunderground.item;

import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.postoronnim.mobsoftheunderground.MobsOfTheUnderground;
import net.postoronnim.mobsoftheunderground.entity.ModEntities;
import net.postoronnim.mobsoftheunderground.item.custom.LivingAmethyst;

public class ModItems {

    public static final Item GEODITE_SPAWN_EGG = registerItem("geodite_spawn_egg",
            new SpawnEggItem(ModEntities.GEODITE,
                    new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(MobsOfTheUnderground.MOD_ID, "geodite_spawn_egg")))));

    public static final Item SHARDLING_SPAWN_EGG = registerItem("shardling_spawn_egg",
            new SpawnEggItem(ModEntities.SHARDLING,
                    new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(MobsOfTheUnderground.MOD_ID, "shardling_spawn_egg")))));

    public static final Item AMETHYST_INFECTED_SPAWN_EGG = registerItem("amethyst_infected_spawn_egg",
            new SpawnEggItem(ModEntities.AMETHYST_INFECTED,
                    new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(MobsOfTheUnderground.MOD_ID, "amethyst_infected_spawn_egg")))));

    public static final Item LIVING_AMETHYST = registerItem("living_amethyst",
            new LivingAmethyst(new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(MobsOfTheUnderground.MOD_ID, "living_amethyst")))));

    public static final Item AMETHYST_INFECTED_FLESH = registerItem("amethyst_infected_flesh",
            new Item(new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(MobsOfTheUnderground.MOD_ID, "amethyst_infected_flesh")))));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(MobsOfTheUnderground.MOD_ID, name), item);
    }

    public static void registerModItems() {
        MobsOfTheUnderground.LOGGER.info("Registering Mod Items for " + MobsOfTheUnderground.MOD_ID);
    }
}
