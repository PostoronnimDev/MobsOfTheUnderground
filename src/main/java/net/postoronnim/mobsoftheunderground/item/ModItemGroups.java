package net.postoronnim.mobsoftheunderground.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.postoronnim.mobsoftheunderground.MobsOfTheUnderground;
import net.postoronnim.mobsoftheunderground.MobsOfTheUndergroundClient;
import net.postoronnim.mobsoftheunderground.potion.ModPotions;

public class ModItemGroups {
    public static final ItemGroup MOBSOFTHEUNDERGROUND_ITEMS_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(MobsOfTheUnderground.MOD_ID, "mobsoftheunderground_items_group"),
            FabricItemGroup.builder().icon(() -> new ItemStack(ModItems.GEODITE_SPAWN_EGG))
                    .displayName(Text.translatable("itemgroup.mobsoftheunderground.mobsoftheunderground_items_group"))
                    .entries((displayContext, entries) -> {

                        //spawn eggs
                        entries.add(ModItems.GEODITE_SPAWN_EGG);
                        entries.add(ModItems.AMETHYST_INFECTED_SPAWN_EGG);
                        entries.add(ModItems.SHARDLING_SPAWN_EGG);
                        entries.add(ModItems.LIVING_AMETHYST);
                        entries.add(ModItems.AMETHYST_INFECTED_FLESH);
                    }).build());

    public static void registerItemGroups() {
        MobsOfTheUnderground.LOGGER.info("Registering Item Groups for " + MobsOfTheUnderground.MOD_ID);
    }
}
