package net.postoronnim.mobsoftheunderground.entity.shardling.client;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.postoronnim.mobsoftheunderground.MobsOfTheUnderground;
import net.postoronnim.mobsoftheunderground.entity.geodite.client.GeoditeAnimations;
import net.postoronnim.mobsoftheunderground.entity.geodite.client.GeoditeRendererState;
import net.postoronnim.mobsoftheunderground.entity.geodite.custom.GeoditeEntity;
import net.postoronnim.mobsoftheunderground.entity.shardling.custom.ShardlingEntity;

public class ShardlingModel extends EntityModel<ShardlingRendererState> {
    public static final EntityModelLayer SHARDLING = new EntityModelLayer(Identifier.of(MobsOfTheUnderground.MOD_ID, "shardling"), "main");
    private final ModelPart root;
    private final ModelPart body;
    private final ModelPart bodyCrystals;
    private final ModelPart leftFrontLeg;
    private final ModelPart rightFrontLeg;
    private final ModelPart leftBackLeg;
    private final ModelPart rightBackLeg;
    public ShardlingModel(ModelPart root) {
        super(root);
        this.root = root.getChild("root");
        this.body = this.root.getChild("body");
        this.bodyCrystals = this.body.getChild("bodyCrystals");
        this.leftFrontLeg = this.root.getChild("leftFrontLeg");
        this.rightFrontLeg = this.root.getChild("rightFrontLeg");
        this.leftBackLeg = this.root.getChild("leftBackLeg");
        this.rightBackLeg = this.root.getChild("rightBackLeg");
    }
    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData root = modelPartData.addChild("root", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 21.0F, 0.0F));

        ModelPartData body = root.addChild("body", ModelPartBuilder.create().uv(0, 12).cuboid(-2.0F, -3.0F, -3.0F, 4.0F, 1.0F, 6.0F, new Dilation(0.0F))
                .uv(0, 0).cuboid(-3.0F, -2.0F, -5.0F, 6.0F, 2.0F, 10.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData bodyCrystals = body.addChild("bodyCrystals", ModelPartBuilder.create().uv(8, 19).cuboid(0.0F, -5.0F, 0.0F, 2.0F, 5.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(-1.0F, -2.0F, -1.0F));

        ModelPartData cube_r1 = bodyCrystals.addChild("cube_r1", ModelPartBuilder.create().uv(0, 19).cuboid(-1.0F, -4.0F, -1.0F, 2.0F, 4.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.4363F));

        ModelPartData cube_r2 = bodyCrystals.addChild("cube_r2", ModelPartBuilder.create().uv(0, 19).cuboid(-1.0F, -4.0F, -1.0F, 2.0F, 4.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, 0.0F, -1.0F, 0.4363F, 0.0F, 0.0F));

        ModelPartData cube_r3 = bodyCrystals.addChild("cube_r3", ModelPartBuilder.create().uv(0, 19).cuboid(-1.0F, -4.0F, -1.0F, 2.0F, 4.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, 0.0F, 3.0F, -0.4363F, 0.0F, 0.0F));

        ModelPartData cube_r4 = bodyCrystals.addChild("cube_r4", ModelPartBuilder.create().uv(0, 19).cuboid(-1.0F, -4.0F, -1.0F, 2.0F, 4.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(2.0F, 0.0F, 2.0F, 0.0F, 0.0F, 0.4363F));

        ModelPartData leftFrontLeg = root.addChild("leftFrontLeg", ModelPartBuilder.create().uv(20, 12).cuboid(-1.0F, -1.0F, -1.0F, 2.0F, 5.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(4.0F, -1.0F, -4.0F));

        ModelPartData rightFrontLeg = root.addChild("rightFrontLeg", ModelPartBuilder.create().uv(20, 12).cuboid(-1.0F, -1.0F, -1.0F, 2.0F, 5.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(-4.0F, -1.0F, -4.0F));

        ModelPartData leftBackLeg = root.addChild("leftBackLeg", ModelPartBuilder.create().uv(20, 12).cuboid(-1.0F, -1.0F, -1.0F, 2.0F, 5.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(4.0F, -1.0F, 4.0F));

        ModelPartData rightBackLeg = root.addChild("rightBackLeg", ModelPartBuilder.create().uv(20, 12).cuboid(-1.0F, -1.0F, -1.0F, 2.0F, 5.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(-4.0F, -1.0F, 4.0F));
        return TexturedModelData.of(modelData, 32, 32);
    }

    @Override
    public void setAngles(ShardlingRendererState state) {
        this.getRootPart().traverse().forEach(ModelPart::resetTransform);

        this.animateWalking(ShardlingAnimations.WALK, state.limbFrequency, state.limbAmplitudeMultiplier, 2f, 2.5f);
        this.animate(state.idleAnimationState, ShardlingAnimations.IDLE, state.age, 1f);
        this.animate(state.attackAnimationState, ShardlingAnimations.ATTACK, state.age, 1f);
    }
}
