package net.postoronnim.mobsoftheunderground.entity.amethyst_infected.client;

import net.minecraft.client.model.*;
import net.minecraft.client.render.LightmapTextureManager;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.postoronnim.mobsoftheunderground.MobsOfTheUnderground;
import net.postoronnim.mobsoftheunderground.entity.amethyst_infected.custom.AmethystInfectedEntity;

public class AmethystInfectedModel extends EntityModel<AmethystInfectedRendererState> {
    public static final EntityModelLayer AMETHYST_INFECTED = new EntityModelLayer(Identifier.of(MobsOfTheUnderground.MOD_ID, "amethyst_infected"), "main");

    private final ModelPart root;
    private final ModelPart head;
    private final ModelPart headMain;
    private final ModelPart headCrystals;
    private final ModelPart body;
    private final ModelPart rightArm;
    private final ModelPart rightArmMain;
    private final ModelPart rightArmCrystals;
    private final ModelPart leftArm;
    private final ModelPart leftArmMain;
    private final ModelPart leftArmCrystals;
    private final ModelPart rightLeg;
    private final ModelPart leftLeg;
    public AmethystInfectedModel(ModelPart root) {
        super(root);
        this.root = root.getChild("root");
        this.head = this.root.getChild("head");
        this.headMain = this.head.getChild("headMain");
        this.headCrystals = this.head.getChild("headCrystals");
        this.body = this.root.getChild("body");
        this.rightArm = this.root.getChild("rightArm");
        this.rightArmMain = this.rightArm.getChild("rightArmMain");
        this.rightArmCrystals = this.rightArm.getChild("rightArmCrystals");
        this.leftArm = this.root.getChild("leftArm");
        this.leftArmMain = this.leftArm.getChild("leftArmMain");
        this.leftArmCrystals = this.leftArm.getChild("leftArmCrystals");
        this.rightLeg = this.root.getChild("rightLeg");
        this.leftLeg = this.root.getChild("leftLeg");
    }
    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData root = modelPartData.addChild("root", ModelPartBuilder.create(), ModelTransform.origin(0.0F, 24.0F, 0.0F));

        ModelPartData head = root.addChild("head", ModelPartBuilder.create(), ModelTransform.origin(0.0F, -24.0F, 0.0F));

        ModelPartData headMain = head.addChild("headMain", ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.0F)), ModelTransform.origin(0.0F, 0.0F, 0.0F));

        ModelPartData headCrystals = head.addChild("headCrystals", ModelPartBuilder.create().uv(8, 51).cuboid(0.0F, -5.0F, 0.0F, 2.0F, 5.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, -6.0F, -1.0F, 0.0F, 0.0F, 0.6981F));

        ModelPartData cube_r1 = headCrystals.addChild("cube_r1", ModelPartBuilder.create().uv(0, 51).cuboid(-1.0F, -4.0F, -1.0F, 2.0F, 4.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.4363F));

        ModelPartData cube_r2 = headCrystals.addChild("cube_r2", ModelPartBuilder.create().uv(0, 51).cuboid(-1.0F, -4.0F, -1.0F, 2.0F, 4.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, 0.0F, -1.0F, 0.4363F, 0.0F, 0.0F));

        ModelPartData cube_r3 = headCrystals.addChild("cube_r3", ModelPartBuilder.create().uv(0, 51).cuboid(-1.0F, -4.0F, -1.0F, 2.0F, 4.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, 0.0F, 3.0F, -0.4363F, 0.0F, 0.0F));

        ModelPartData cube_r4 = headCrystals.addChild("cube_r4", ModelPartBuilder.create().uv(0, 51).cuboid(-1.0F, -4.0F, -1.0F, 2.0F, 4.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(2.0F, 0.0F, 2.0F, 0.0F, 0.0F, 0.4363F));

        ModelPartData body = root.addChild("body", ModelPartBuilder.create().uv(16, 16).cuboid(-4.0F, -6.0F, -2.0F, 8.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.origin(0.0F, -18.0F, 0.0F));

        ModelPartData rightArm = root.addChild("rightArm", ModelPartBuilder.create(), ModelTransform.origin(-5.0F, -22.0F, 0.0F));

        ModelPartData rightArmMain = rightArm.addChild("rightArmMain", ModelPartBuilder.create(), ModelTransform.origin(-1.0F, 0.0F, 0.0F));

        ModelPartData RightArm_r1 = rightArmMain.addChild("RightArm_r1", ModelPartBuilder.create().uv(40, 16).cuboid(-2.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -1.5708F, 0.0F, 0.0F));

        ModelPartData rightArmCrystals = rightArm.addChild("rightArmCrystals", ModelPartBuilder.create().uv(8, 51).cuboid(0.0F, -5.0F, 0.0F, 2.0F, 5.0F, 2.0F, new Dilation(0.0F)), ModelTransform.origin(-2.0F, -1.0F, -2.0F));

        ModelPartData cube_r5 = rightArmCrystals.addChild("cube_r5", ModelPartBuilder.create().uv(0, 51).cuboid(-1.0F, -4.0F, -1.0F, 2.0F, 4.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.4363F));

        ModelPartData cube_r6 = rightArmCrystals.addChild("cube_r6", ModelPartBuilder.create().uv(0, 51).cuboid(-1.0F, -4.0F, -1.0F, 2.0F, 4.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, 0.0F, -1.0F, 0.4363F, 0.0F, 0.0F));

        ModelPartData cube_r7 = rightArmCrystals.addChild("cube_r7", ModelPartBuilder.create().uv(0, 51).cuboid(-1.0F, -4.0F, -1.0F, 2.0F, 4.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, 0.0F, 3.0F, -0.4363F, 0.0F, 0.0F));

        ModelPartData leftArm = root.addChild("leftArm", ModelPartBuilder.create(), ModelTransform.origin(5.0F, -22.0F, 0.0F));

        ModelPartData leftArmMain = leftArm.addChild("leftArmMain", ModelPartBuilder.create(), ModelTransform.origin(1.0F, 0.0F, 0.0F));

        ModelPartData LeftArm_r1 = leftArmMain.addChild("LeftArm_r1", ModelPartBuilder.create().uv(40, 16).mirrored().cuboid(-2.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(0.0F, 0.0F, 0.0F, -1.5708F, 0.0F, 0.0F));

        ModelPartData leftArmCrystals = leftArm.addChild("leftArmCrystals", ModelPartBuilder.create().uv(8, 51).cuboid(0.0F, -5.0F, 0.0F, 2.0F, 5.0F, 2.0F, new Dilation(0.0F)), ModelTransform.origin(0.0F, -1.0F, -2.0F));

        ModelPartData cube_r8 = leftArmCrystals.addChild("cube_r8", ModelPartBuilder.create().uv(0, 51).cuboid(-1.0F, -4.0F, -1.0F, 2.0F, 4.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, 0.0F, -1.0F, 0.4363F, 0.0F, 0.0F));

        ModelPartData cube_r9 = leftArmCrystals.addChild("cube_r9", ModelPartBuilder.create().uv(0, 51).cuboid(-1.0F, -4.0F, -1.0F, 2.0F, 4.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, 0.0F, 3.0F, -0.4363F, 0.0F, 0.0F));

        ModelPartData cube_r10 = leftArmCrystals.addChild("cube_r10", ModelPartBuilder.create().uv(0, 51).cuboid(-1.0F, -4.0F, -1.0F, 2.0F, 4.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(2.0F, 0.0F, 2.0F, 0.0F, 0.0F, 0.4363F));

        ModelPartData rightLeg = root.addChild("rightLeg", ModelPartBuilder.create().uv(0, 16).cuboid(-2.1F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.origin(-1.9F, -12.0F, 0.0F));

        ModelPartData leftLeg = root.addChild("leftLeg", ModelPartBuilder.create().uv(0, 16).mirrored().cuboid(-1.9F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.origin(1.9F, -12.0F, 0.0F));
        return TexturedModelData.of(modelData, 64, 64);
    }

    @Override
    public void setAngles(AmethystInfectedRendererState state) {
        this.getRootPart().traverse().forEach(ModelPart::resetTransform);

        this.animateWalking(AmethystInfectedAnimations.WALK, state.limbSwingAnimationProgress, state.limbSwingAmplitude, 8f, 10f);
        this.animate(state.idleAnimationState, AmethystInfectedAnimations.IDLE, state.age, 1f);
        this.animate(state.attackAnimationState, AmethystInfectedAnimations.ATTACK, state.age, 1f);
    }
}
