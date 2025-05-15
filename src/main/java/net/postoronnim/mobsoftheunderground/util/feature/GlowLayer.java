package net.postoronnim.mobsoftheunderground.util.feature;

import net.minecraft.client.render.*;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Identifier;

public class GlowLayer<E extends LivingEntity, M extends SinglePartEntityModel<E>> extends FeatureRenderer<E, M> {
    private final Identifier GLOW_TEXTURE;

    public GlowLayer(FeatureRendererContext<E, M> context, Identifier texture) {
        super(context);
        GLOW_TEXTURE = texture;
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light,
                       E entity, float limbAngle, float limbDistance,
                       float tickDelta, float animationProgress, float headYaw, float headPitch) {

        int customLight = LightmapTextureManager.pack(7,7);

        VertexConsumer vertexConsumer = vertexConsumers.getBuffer(RenderLayer.getEntityCutoutNoCull(GLOW_TEXTURE));

        getContextModel().render(matrices, vertexConsumer, customLight, OverlayTexture.DEFAULT_UV, 0xFFFFFFFF); // full color, full alpha
    }
}