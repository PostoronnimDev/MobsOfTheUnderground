package net.postoronnim.mobsoftheunderground.item;

import net.minecraft.item.EnchantedBookItem;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.postoronnim.mobsoftheunderground.MobsOfTheUnderground;
import net.postoronnim.mobsoftheunderground.entity.ModEntities;
import net.postoronnim.mobsoftheunderground.item.custom.LivingAmethyst;

public class ModItems {

    public static final Item GEODITE_SPAWN_EGG = registerItem("geodite_spawn_egg",
            new SpawnEggItem(ModEntities.GEODITE, 0x3a3b48, 0xa678f1, new Item.Settings()));

    public static final Item SHARDLING_SPAWN_EGG = registerItem("shardling_spawn_egg",
            new SpawnEggItem(ModEntities.SHARDLING, 0x797979, 0xfecbe6, new Item.Settings()));

    public static final Item AMETHYST_INFECTED_SPAWN_EGG = registerItem("amethyst_infected_spawn_egg",
            new SpawnEggItem(ModEntities.AMETHYST_INFECTED, 0x60692d, 0xcfa0f3, new Item.Settings()));

    public static final Item LIVING_AMETHYST = registerItem("living_amethyst",
            new LivingAmethyst(new Item.Settings()));

    public static final Item AMETHYST_INFECTED_FLESH = registerItem("amethyst_infected_flesh",
            new Item(new Item.Settings()));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(MobsOfTheUnderground.MOD_ID, name), item);
    }

    public static void registerModItems() {
        MobsOfTheUnderground.LOGGER.info("Registering Mod Items for " + MobsOfTheUnderground.MOD_ID);
    }
}
