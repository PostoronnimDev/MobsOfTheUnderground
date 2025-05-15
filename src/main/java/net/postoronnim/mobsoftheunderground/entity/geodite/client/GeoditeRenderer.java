package net.postoronnim.mobsoftheunderground.entity.geodite.client;

import net.minecraft.client.render.*;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.postoronnim.mobsoftheunderground.MobsOfTheUnderground;
import net.postoronnim.mobsoftheunderground.util.feature.GlowLayer;
import net.postoronnim.mobsoftheunderground.entity.geodite.custom.GeoditeEntity;

public class GeoditeRenderer extends MobEntityRenderer<GeoditeEntity, GeoditeModel<GeoditeEntity>> {
    private static final Identifier EMISSIVE_TEXTURE = Identifier.of(MobsOfTheUnderground.MOD_ID, "textures/entity/geodite/geodite_emissive.png");

    public GeoditeRenderer(EntityRendererFactory.Context context) {
        super(context, new GeoditeModel<>(context.getPart(GeoditeModel.GEODITE)), 1f);

        this.addFeature(new GlowLayer<>(this, EMISSIVE_TEXTURE));
    }

    @Override
    public Identifier getTexture(GeoditeEntity entity) {
        return Identifier.of(MobsOfTheUnderground.MOD_ID,  "textures/entity/geodite/geodite.png");
    }

    @Override
    public void render(GeoditeEntity livingEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        super.render(livingEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
}
