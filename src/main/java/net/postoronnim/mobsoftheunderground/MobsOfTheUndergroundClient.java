package net.postoronnim.mobsoftheunderground;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.postoronnim.mobsoftheunderground.entity.ModEntities;
import net.postoronnim.mobsoftheunderground.entity.amethyst_infected.client.AmethystInfectedModel;
import net.postoronnim.mobsoftheunderground.entity.geodite.client.GeoditeModel;
import net.postoronnim.mobsoftheunderground.entity.shardling.client.ShardlingModel;
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
        EntityRendererRegistry.register(ModEntities.GEODITE, (context) ->
                new EmissiveRenderer<>(context,
                        new GeoditeModel<>(context.getPart(GeoditeModel.GEODITE)),
                        0.5f, "textures/entity/geodite/geodite.png",
                        "textures/entity/geodite/geodite_emissive.png"));

        EntityModelLayerRegistry.registerModelLayer(AmethystInfectedModel.AMETHYST_INFECTED, AmethystInfectedModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.AMETHYST_INFECTED, (context) ->
                new EmissiveRenderer<>(context,
                new AmethystInfectedModel<>(context.getPart(AmethystInfectedModel.AMETHYST_INFECTED)),
                0.5f, "textures/entity/amethyst_infected/amethyst_infected.png",
                        "textures/entity/amethyst_infected/amethyst_infected_emissive.png"));

        EntityModelLayerRegistry.registerModelLayer(ShardlingModel.SHARDLING, ShardlingModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.SHARDLING, (context) ->
                new EmissiveRenderer<>(context,
                        new ShardlingModel<>(context.getPart(ShardlingModel.SHARDLING)),
                        0.5f, "textures/entity/shardling/shardling.png",
                        "textures/entity/shardling/shardling_emissive.png"));

        ParticleFactoryRegistry.getInstance().register(ModParticles.AMETHYST_PARTICLE, AmethystParticle.Factory::new);
    }
}
