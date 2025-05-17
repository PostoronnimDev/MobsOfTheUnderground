package net.postoronnim.mobsoftheunderground.entity.amethyst_infected.client;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;
import net.postoronnim.mobsoftheunderground.MobsOfTheUnderground;
import net.postoronnim.mobsoftheunderground.entity.amethyst_infected.custom.AmethystInfectedEntity;
import net.postoronnim.mobsoftheunderground.util.feature.GlowLayer;

public class AmethystInfectedRenderer extends MobEntityRenderer<AmethystInfectedEntity, AmethystInfectedRendererState, AmethystInfectedModel> {
    private final String PATH = "textures/entity/amethyst_infected/amethyst_infected.png";
    private final String EMISSIVE = "textures/entity/amethyst_infected/amethyst_infected_emissive.png";

    public AmethystInfectedRenderer(EntityRendererFactory.Context context) {
        super(context, new AmethystInfectedModel(context.getPart(AmethystInfectedModel.AMETHYST_INFECTED)), 1f);
        this.addFeature(new GlowLayer<>(this, Identifier.of(MobsOfTheUnderground.MOD_ID, this.EMISSIVE)));
    }

    @Override
    public AmethystInfectedRendererState createRenderState() {
        return new AmethystInfectedRendererState();
    }

    @Override
    public void updateRenderState(AmethystInfectedEntity entity, AmethystInfectedRendererState rendererState, float f) {
        super.updateRenderState(entity, rendererState, f);
        rendererState.attackAnimationState = entity.attackAnimationState;
        rendererState.idleAnimationState = entity.idleAnimationState;
    }

    @Override
    public Identifier getTexture(AmethystInfectedRendererState state) {
        return Identifier.of(MobsOfTheUnderground.MOD_ID, PATH);
    }
}
