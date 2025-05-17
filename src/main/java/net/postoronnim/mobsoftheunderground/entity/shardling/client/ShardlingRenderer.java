package net.postoronnim.mobsoftheunderground.entity.shardling.client;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.postoronnim.mobsoftheunderground.MobsOfTheUnderground;
import net.postoronnim.mobsoftheunderground.entity.geodite.client.GeoditeModel;
import net.postoronnim.mobsoftheunderground.entity.geodite.client.GeoditeRendererState;
import net.postoronnim.mobsoftheunderground.entity.geodite.custom.GeoditeEntity;
import net.postoronnim.mobsoftheunderground.entity.shardling.custom.ShardlingEntity;
import net.postoronnim.mobsoftheunderground.util.feature.GlowLayer;

public class ShardlingRenderer extends MobEntityRenderer<ShardlingEntity, ShardlingRendererState, ShardlingModel> {
    private final String PATH = "textures/entity/shardling/shardling.png";
    private final String EMISSIVE = "textures/entity/shardling/shardling_emissive.png";

    public ShardlingRenderer(EntityRendererFactory.Context context) {
        super(context, new ShardlingModel(context.getPart(ShardlingModel.SHARDLING)), 0.3f);
        this.addFeature(new GlowLayer<>(this, Identifier.of(MobsOfTheUnderground.MOD_ID, EMISSIVE)));
    }

    @Override
    public ShardlingRendererState createRenderState() {
        return new ShardlingRendererState();
    }

    @Override
    public void updateRenderState(ShardlingEntity entity, ShardlingRendererState rendererState, float f) {
        super.updateRenderState(entity, rendererState, f);
        rendererState.attackAnimationState = entity.attackAnimationState;
        rendererState.idleAnimationState = entity.idleAnimationState;
    }

    @Override
    public Identifier getTexture(ShardlingRendererState state) {
        return Identifier.of(MobsOfTheUnderground.MOD_ID,  PATH);
    }
}
