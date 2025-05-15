package net.postoronnim.mobsoftheunderground;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.registry.FabricBrewingRecipeRegistryBuilder;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.postoronnim.mobsoftheunderground.effect.ModEffects;
import net.postoronnim.mobsoftheunderground.entity.ModEntities;
import net.postoronnim.mobsoftheunderground.entity.geodite.custom.GeoditeEntity;
import net.postoronnim.mobsoftheunderground.item.ModItemGroups;
import net.postoronnim.mobsoftheunderground.item.ModItems;
import net.postoronnim.mobsoftheunderground.particle.ModParticles;
import net.postoronnim.mobsoftheunderground.potion.ModPotions;
import net.postoronnim.mobsoftheunderground.world.gen.ModWorldGeneration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MobsOfTheUnderground implements ModInitializer {
	public static final String MOD_ID = "mobsoftheunderground";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Initializing " + MOD_ID);
		ModItems.registerModItems();
		ModItemGroups.registerItemGroups();
		ModEntities.registerModEntities();
		ModWorldGeneration.generate();
		ModEffects.registerEffects();
		ModPotions.registerPotions();
		ModParticles.registerParticles();

		FabricBrewingRecipeRegistryBuilder.BUILD.register(builder -> {
			builder.registerPotionRecipe(Potions.AWKWARD, ModItems.AMETHYST_INFECTED_FLESH, ModPotions.AMETHYST_INFECTION_POTION);
		});
	}
}