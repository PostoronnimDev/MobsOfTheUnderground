package net.postoronnim.mobsoftheunderground.util.feature;

import net.minecraft.client.render.*;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Identifier;

public class GlowLayer<S extends LivingEntityRenderState, M extends EntityModel<S>> extends FeatureRenderer<S, M> {
    private final Identifier GLOW_TEXTURE;

    public GlowLayer(FeatureRendererContext<S, M> context, Identifier texture) {
        super(context);
        GLOW_TEXTURE = texture;
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, S state, float limbAngle, float limbDistance) {
        int customLight = LightmapTextureManager.pack(7,7);

        VertexConsumer vertexConsumer = vertexConsumers.getBuffer(RenderLayer.getEntityCutoutNoCull(GLOW_TEXTURE));

        getContextModel().render(matrices, vertexConsumer, customLight, OverlayTexture.DEFAULT_UV, 0xFFFFFFFF); // full color, full alpha
    }
}