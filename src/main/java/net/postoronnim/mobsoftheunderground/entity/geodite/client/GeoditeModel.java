package net.postoronnim.mobsoftheunderground.entity.geodite.client;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.postoronnim.mobsoftheunderground.MobsOfTheUnderground;
import net.postoronnim.mobsoftheunderground.entity.geodite.custom.GeoditeEntity;

public class GeoditeModel<T extends GeoditeEntity> extends SinglePartEntityModel<T> {
    public static final EntityModelLayer GEODITE = new EntityModelLayer(Identifier.of(MobsOfTheUnderground.MOD_ID, "geodite"), "main");

    private final ModelPart root;
    private final ModelPart body;
    private final ModelPart core;
    private final ModelPart bodyCrystals;
    private final ModelPart rightArm;
    private final ModelPart rightArmCrystals;
    private final ModelPart leftArm;
    private final ModelPart leftArmCrystals;
    private final ModelPart rightLeg;
    private final ModelPart leftLeg;

    public GeoditeModel(ModelPart root) {
        this.root = root.getChild("Root");
        this.body = this.root.getChild("Body");
        this.core = this.body.getChild("Core");
        this.bodyCrystals = this.body.getChild("BodyCrystals");
        this.rightArm = this.root.getChild("RightArm");
        this.rightArmCrystals = this.rightArm.getChild("RightArmCrystals");
        this.leftArm = this.root.getChild("LeftArm");
        this.leftArmCrystals = this.leftArm.getChild("LeftArmCrystals");
        this.rightLeg = this.root.getChild("RightLeg");
        this.leftLeg = this.root.getChild("LeftLeg");
    }
    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData Root = modelPartData.addChild("Root", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 3.0F, 0.0F));

        ModelPartData Body = Root.addChild("Body", ModelPartBuilder.create().uv(60, 34).cuboid(-10.0F, 4.0F, -3.0F, 20.0F, 8.0F, 11.0F, new Dilation(0.0F))
                .uv(36, 87).cuboid(-4.0F, -4.0F, -5.0F, 8.0F, 8.0F, 13.0F, new Dilation(0.0F))
                .uv(36, 62).cuboid(-4.0F, -13.0F, -8.0F, 8.0F, 9.0F, 16.0F, new Dilation(0.0F))
                .uv(0, 0).cuboid(4.0F, -15.0F, -9.0F, 9.0F, 19.0F, 17.0F, new Dilation(0.0F))
                .uv(52, 0).cuboid(-12.0F, -14.0F, -8.0F, 8.0F, 18.0F, 16.0F, new Dilation(0.0F))
                .uv(0, 36).cuboid(-5.0F, -16.0F, -5.0F, 16.0F, 12.0F, 14.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData Core = Body.addChild("Core", ModelPartBuilder.create().uv(103, 66).cuboid(-10.0F, -14.0F, -6.0F, 4.0F, 4.0F, 1.0F, new Dilation(0.0F))
                .uv(114, 64).cuboid(-11.0F, -15.0F, -5.0F, 6.0F, 6.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(8.0F, 12.0F, -1.0F));

        ModelPartData BodyCrystals = Body.addChild("BodyCrystals", ModelPartBuilder.create().uv(105, 113).cuboid(-7.0F, -18.0F, -2.0F, 5.0F, 10.0F, 5.0F, new Dilation(0.0F)), ModelTransform.pivot(8.0F, -7.0F, 2.0F));

        ModelPartData cube_r1 = BodyCrystals.addChild("cube_r1", ModelPartBuilder.create().uv(116, 96).cuboid(-1.5F, -5.0F, -2.5F, 3.0F, 10.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(0.5F, -2.0F, 8.5F, -0.6429F, 0.1321F, 0.1741F));

        ModelPartData cube_r2 = BodyCrystals.addChild("cube_r2", ModelPartBuilder.create().uv(116, 96).cuboid(-0.5F, -5.0F, -2.5F, 3.0F, 10.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(-9.5F, -2.0F, 7.5F, -0.4742F, -0.1188F, -0.1309F));

        ModelPartData cube_r3 = BodyCrystals.addChild("cube_r3", ModelPartBuilder.create().uv(99, 96).cuboid(-1.5F, -6.0F, -2.5F, 4.0F, 11.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(-4.5F, -5.0F, 7.5F, -0.6545F, 0.0F, 0.0F));

        ModelPartData cube_r4 = BodyCrystals.addChild("cube_r4", ModelPartBuilder.create().uv(99, 96).cuboid(-1.5F, -7.0F, -2.5F, 4.0F, 8.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(-0.5F, -8.0F, 4.5F, -0.3463F, 0.0447F, 0.1231F));

        ModelPartData cube_r5 = BodyCrystals.addChild("cube_r5", ModelPartBuilder.create().uv(99, 96).cuboid(-1.5F, -4.0F, -2.5F, 4.0F, 5.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(-8.5F, -8.0F, 4.5F, -0.3442F, -0.0594F, -0.1642F));

        ModelPartData cube_r6 = BodyCrystals.addChild("cube_r6", ModelPartBuilder.create().uv(99, 96).cuboid(-2.0F, -1.0F, -2.0F, 4.0F, 6.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(3.0F, -13.0F, -1.0F, 0.0F, 0.0F, 0.3054F));

        ModelPartData cube_r7 = BodyCrystals.addChild("cube_r7", ModelPartBuilder.create().uv(99, 96).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 5.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(-11.0F, -13.0F, -1.0F, 0.0F, 0.0F, -0.2182F));

        ModelPartData cube_r8 = BodyCrystals.addChild("cube_r8", ModelPartBuilder.create().uv(116, 96).cuboid(-2.0F, 0.0F, -2.0F, 3.0F, 5.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(-7.0F, -12.0F, -5.0F, 0.1304F, 0.0114F, -0.0865F));

        ModelPartData cube_r9 = BodyCrystals.addChild("cube_r9", ModelPartBuilder.create().uv(116, 96).cuboid(-2.0F, -1.0F, -2.0F, 3.0F, 6.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(-1.0F, -12.0F, -5.0F, 0.1298F, -0.017F, 0.1298F));

        ModelPartData RightArm = Root.addChild("RightArm", ModelPartBuilder.create().uv(0, 62).cuboid(-4.0F, -6.5F, -5.0F, 8.0F, 33.0F, 10.0F, new Dilation(0.0F))
                .uv(104, 54).cuboid(-3.0F, -7.5F, -3.0F, 6.0F, 1.0F, 6.0F, new Dilation(0.0F)), ModelTransform.pivot(-16.0F, -5.5F, -5.0F));

        ModelPartData RightArmCrystals = RightArm.addChild("RightArmCrystals", ModelPartBuilder.create().uv(116, 96).cuboid(2.5F, -6.0F, -1.5F, 3.0F, 9.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(-3.5F, -9.5F, 0.5F));

        ModelPartData cube_r10 = RightArmCrystals.addChild("cube_r10", ModelPartBuilder.create().uv(116, 96).cuboid(-1.5F, -2.0F, -1.5F, 3.0F, 6.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.3054F));

        ModelPartData cube_r11 = RightArmCrystals.addChild("cube_r11", ModelPartBuilder.create().uv(116, 96).cuboid(-1.5F, -3.0F, -1.5F, 3.0F, 6.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(3.0F, 0.0F, -4.0F, 0.3491F, 0.0F, 0.0F));

        ModelPartData cube_r12 = RightArmCrystals.addChild("cube_r12", ModelPartBuilder.create().uv(116, 96).cuboid(-1.5F, -3.0F, -1.5F, 3.0F, 6.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(5.0F, 0.0F, 3.0F, -0.3491F, 0.0F, 0.0F));

        ModelPartData LeftArm = Root.addChild("LeftArm", ModelPartBuilder.create().uv(0, 62).mirrored().cuboid(-4.0F, -6.5F, -5.0F, 8.0F, 33.0F, 10.0F, new Dilation(0.0F)).mirrored(false)
                .uv(104, 54).mirrored().cuboid(-3.0F, -7.5F, -3.0F, 6.0F, 1.0F, 6.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(17.0F, -5.5F, -5.0F));

        ModelPartData LeftArmCrystals = LeftArm.addChild("LeftArmCrystals", ModelPartBuilder.create().uv(99, 96).cuboid(-1.5F, -8.0F, -1.5F, 4.0F, 10.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(-1.5F, -9.5F, 0.5F));

        ModelPartData cube_r13 = LeftArmCrystals.addChild("cube_r13", ModelPartBuilder.create().uv(116, 96).cuboid(-1.5F, -3.0F, -1.5F, 3.0F, 6.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(3.0F, 0.0F, 3.0F, -0.3491F, 0.0F, 0.0F));

        ModelPartData cube_r14 = LeftArmCrystals.addChild("cube_r14", ModelPartBuilder.create().uv(116, 96).cuboid(-1.5F, -3.0F, -1.5F, 3.0F, 6.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, 0.0F, -4.0F, 0.3491F, 0.0F, 0.0F));

        ModelPartData cube_r15 = LeftArmCrystals.addChild("cube_r15", ModelPartBuilder.create().uv(116, 96).cuboid(-1.5F, -2.0F, -1.5F, 3.0F, 5.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(5.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.3491F));

        ModelPartData RightLeg = Root.addChild("RightLeg", ModelPartBuilder.create().uv(0, 105).cuboid(-4.0F, -0.5F, -3.5F, 8.0F, 9.0F, 8.0F, new Dilation(0.0F)), ModelTransform.pivot(-6.0F, 12.5F, 0.5F));

        ModelPartData LeftLeg = Root.addChild("LeftLeg", ModelPartBuilder.create().uv(0, 105).mirrored().cuboid(-4.0F, -0.5F, -4.0F, 8.0F, 9.0F, 8.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(6.0F, 12.5F, 1.0F));
        return TexturedModelData.of(modelData, 128, 128);
    }

    @Override
    public void setAngles(T entity, float limbAngle, float limbDistance, float ageInTicks, float netHeadYaw, float headPitch) {
        this.getPart().traverse().forEach(ModelPart::resetTransform);

        this.animateMovement(GeoditeAnimations.WALK, limbAngle, limbDistance, 2f, 2.5f);
        this.updateAnimation(entity.idleAnimationState, GeoditeAnimations.IDLE, ageInTicks, 1f);
        this.updateAnimation(entity.attackAnimationState, GeoditeAnimations.ATTACK, ageInTicks, 1f);
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, int color) {
        root.render(matrices, vertexConsumer, light, overlay, color);
    }

    @Override
    public ModelPart getPart() {
        return root;
    }
}
