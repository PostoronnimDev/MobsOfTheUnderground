package net.postoronnim.mobsoftheunderground.entity.shardling.client;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.postoronnim.mobsoftheunderground.MobsOfTheUnderground;
import net.postoronnim.mobsoftheunderground.entity.geodite.client.GeoditeModel;
import net.postoronnim.mobsoftheunderground.entity.geodite.custom.GeoditeEntity;
import net.postoronnim.mobsoftheunderground.entity.shardling.custom.ShardlingEntity;

public class ShardlingRenderer extends MobEntityRenderer<ShardlingEntity, ShardlingModel<ShardlingEntity>> {
    public ShardlingRenderer(EntityRendererFactory.Context context) {
        super(context, new ShardlingModel<>(context.getPart(ShardlingModel.SHARDLING)), 0.3f);
    }

    @Override
    public Identifier getTexture(ShardlingEntity entity) {
        return Identifier.of(MobsOfTheUnderground.MOD_ID,  "textures/entity/shardling/shardling.png");
    }

    @Override
    public void render(ShardlingEntity livingEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        super.render(livingEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
}
