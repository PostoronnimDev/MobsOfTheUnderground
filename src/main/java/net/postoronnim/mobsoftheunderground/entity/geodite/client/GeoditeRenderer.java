package net.postoronnim.mobsoftheunderground.entity.geodite.client;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;
import net.postoronnim.mobsoftheunderground.MobsOfTheUnderground;
import net.postoronnim.mobsoftheunderground.util.feature.GlowLayer;
import net.postoronnim.mobsoftheunderground.entity.geodite.custom.GeoditeEntity;

public class GeoditeRenderer extends MobEntityRenderer<GeoditeEntity, GeoditeRendererState, GeoditeModel> {
    private final String PATH = "textures/entity/geodite/geodite.png";
    private final String EMISSIVE = "textures/entity/geodite/geodite_emissive.png";

    public GeoditeRenderer(EntityRendererFactory.Context context) {
        super(context, new GeoditeModel(context.getPart(GeoditeModel.GEODITE)), 1f);
        this.addFeature(new GlowLayer<>(this, Identifier.of(MobsOfTheUnderground.MOD_ID, EMISSIVE)));
    }

    @Override
    public GeoditeRendererState createRenderState() {
        return new GeoditeRendererState();
    }

    @Override
    public void updateRenderState(GeoditeEntity entity, GeoditeRendererState rendererState, float f) {
        super.updateRenderState(entity, rendererState, f);
        rendererState.attackAnimationState = entity.attackAnimationState;
        rendererState.idleAnimationState = entity.idleAnimationState;
    }

    @Override
    public Identifier getTexture(GeoditeRendererState state) {
        return Identifier.of(MobsOfTheUnderground.MOD_ID, PATH);
    }
}
