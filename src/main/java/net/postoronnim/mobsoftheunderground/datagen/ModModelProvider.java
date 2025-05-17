package net.postoronnim.mobsoftheunderground.datagen;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.BlockStateModelGenerator;
import net.minecraft.client.data.ItemModelGenerator;
import net.minecraft.client.data.Model;
import net.minecraft.client.data.Models;
import net.minecraft.util.Identifier;
import net.postoronnim.mobsoftheunderground.item.ModItems;

import java.util.Optional;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {

        itemModelGenerator.register(ModItems.GEODITE_SPAWN_EGG, Models.GENERATED);
        itemModelGenerator.register(ModItems.SHARDLING_SPAWN_EGG, Models.GENERATED);
        itemModelGenerator.register(ModItems.AMETHYST_INFECTED_SPAWN_EGG, Models.GENERATED);

        itemModelGenerator.register(ModItems.LIVING_AMETHYST, Models.GENERATED);
        itemModelGenerator.register(ModItems.AMETHYST_INFECTED_FLESH, Models.GENERATED);
    }
}
