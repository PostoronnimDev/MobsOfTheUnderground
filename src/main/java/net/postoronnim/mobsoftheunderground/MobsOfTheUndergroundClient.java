package net.postoronnim.mobsoftheunderground;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.postoronnim.mobsoftheunderground.entity.ModEntities;
import net.postoronnim.mobsoftheunderground.entity.amethyst_infected.client.AmethystInfectedModel;
import net.postoronnim.mobsoftheunderground.entity.amethyst_infected.client.AmethystInfectedRenderer;
import net.postoronnim.mobsoftheunderground.entity.geodite.client.GeoditeModel;
import net.postoronnim.mobsoftheunderground.entity.geodite.client.GeoditeRenderer;
import net.postoronnim.mobsoftheunderground.entity.shardling.client.ShardlingModel;
import net.postoronnim.mobsoftheunderground.entity.shardling.client.ShardlingRenderer;
import net.postoronnim.mobsoftheunderground.particle.AmethystParticle;
import net.postoronnim.mobsoftheunderground.particle.ModParticles;
import net.postoronnim.mobsoftheunderground.util.renderer.EmissiveRenderer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MobsOfTheUndergroundClient implements ClientModInitializer {
    public static final String MOD_ID = "mobsoftheunderground";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitializeClient() {

        EntityModelLayerRegistry.registerModelLayer(GeoditeModel.GEODITE, GeoditeModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.GEODITE, GeoditeRenderer::new);

        EntityModelLayerRegistry.registerModelLayer(AmethystInfectedModel.AMETHYST_INFECTED, AmethystInfectedModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.AMETHYST_INFECTED, AmethystInfectedRenderer::new);

        EntityModelLayerRegistry.registerModelLayer(ShardlingModel.SHARDLING, ShardlingModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.SHARDLING, ShardlingRenderer::new);

        ParticleFactoryRegistry.getInstance().register(ModParticles.AMETHYST_PARTICLE, AmethystParticle.Factory::new);
    }
}
