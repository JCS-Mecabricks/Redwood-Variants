package github.jcsmecabricks.redwoodvariants.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import github.jcsmecabricks.redwoodvariants.RedwoodVariants;
import net.minecraft.client.animation.KeyframeAnimation;
import net.minecraft.client.model.*;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.MeshTransformer;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.Identifier;
import java.util.Set;

public class GrizzlyBearModel extends EntityModel<GrizzlyBearRenderState> {
    private static final MeshTransformer BABY_TRANSFORMER = new BabyModelTransform(true, 16.0F, 4.0F, 2.25F, 2.0F, 24.0F, Set.of("head"));
    public static final ModelLayerLocation GRIZZLY_BEAR = new ModelLayerLocation(Identifier.fromNamespaceAndPath(RedwoodVariants.MOD_ID, "grizzly_bear"), "main");
    private final KeyframeAnimation walkingAnimation;
    private final KeyframeAnimation attackAnimation;
    private final KeyframeAnimation sittingTransitionAnimation;
    private final KeyframeAnimation sittingAnimation;
    private final KeyframeAnimation standingAnimation;
    private final KeyframeAnimation idlingAnimation;
    private final ModelPart root;
    private final ModelPart grizzly_bear;
    private final ModelPart body;
    private final ModelPart neck;
    private final ModelPart head;
    private final ModelPart mouth;
    private final ModelPart right_leg;
    private final ModelPart right_front;
    private final ModelPart right_back;
    private final ModelPart left_leg;
    private final ModelPart left_back;
    private final ModelPart left_front;
    public GrizzlyBearModel(ModelPart root) {
        super(root);
        this.attackAnimation = GrizzlyBearAnimations.ATTACK.bake(root);
        this.walkingAnimation = GrizzlyBearAnimations.WALK.bake(root);
        this.sittingTransitionAnimation = GrizzlyBearAnimations.SIT_TRANSITION.bake(root);
        this.sittingAnimation = GrizzlyBearAnimations.SIT.bake(root);
        this.standingAnimation = GrizzlyBearAnimations.STAND.bake(root);
        this.idlingAnimation = GrizzlyBearAnimations.IDLE.bake(root);
        this.root = root.getChild("root");
        this.grizzly_bear = this.root.getChild("grizzly_bear");
        this.body = this.grizzly_bear.getChild("body");
        this.neck = this.grizzly_bear.getChild("neck");
        this.head = this.neck.getChild("head");
        this.mouth = this.head.getChild("mouth");
        this.right_leg = this.grizzly_bear.getChild("right_leg");
        this.right_front = this.right_leg.getChild("right_front");
        this.right_back = this.right_leg.getChild("right_back");
        this.left_leg = this.grizzly_bear.getChild("left_leg");
        this.left_back = this.left_leg.getChild("left_back");
        this.left_front = this.left_leg.getChild("left_front");
    }
    public static LayerDefinition getTexturedModelData() {
        MeshDefinition modelData = new MeshDefinition();
        PartDefinition modelPartData = modelData.getRoot();
        PartDefinition root = modelPartData.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition grizzly_bear = root.addOrReplaceChild("grizzly_bear", CubeListBuilder.create(), PartPose.offset(1.0F, 0.0F, 5.0F));

        PartDefinition body = grizzly_bear.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-8.0F, -19.0F, -7.0F, 14.0F, 11.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition neck = grizzly_bear.addOrReplaceChild("neck", CubeListBuilder.create().texOffs(0, 25).addBox(-7.0F, -9.0F, -6.0F, 12.0F, 10.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -9.0F, -13.0F));

        PartDefinition head = neck.addOrReplaceChild("head", CubeListBuilder.create().texOffs(47, 24).addBox(-6.5F, -7.0F, -5.0F, 7.0F, 7.0F, 7.0F, new CubeDeformation(0.0F))
                .texOffs(56, 6).addBox(-7.5F, -7.0F, -4.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(56, 12).addBox(-0.5F, -8.0F, -4.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(56, 10).addBox(0.5F, -7.0F, -4.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(56, 8).addBox(-7.5F, -8.0F, -4.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(56, 14).addBox(0.5F, -8.0F, -4.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(56, 16).addBox(-6.5F, -8.0F, -4.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, 0.0F, -7.0F));

        PartDefinition mouth = head.addOrReplaceChild("mouth", CubeListBuilder.create().texOffs(56, 0).addBox(-3.5F, -12.0F, -28.0F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, 9.0F, 20.0F));

        PartDefinition right_leg = grizzly_bear.addOrReplaceChild("right_leg", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition right_front = right_leg.addOrReplaceChild("right_front", CubeListBuilder.create().texOffs(48, 38).addBox(-6.5F, -8.0F, -16.0F, 4.0F, 8.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition right_back = right_leg.addOrReplaceChild("right_back", CubeListBuilder.create().texOffs(0, 47).addBox(-7.5F, -8.0F, -2.0F, 4.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition left_leg = grizzly_bear.addOrReplaceChild("left_leg", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition left_back = left_leg.addOrReplaceChild("left_back", CubeListBuilder.create().texOffs(24, 47).addBox(1.5F, -8.0F, -2.0F, 4.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition left_front = left_leg.addOrReplaceChild("left_front", CubeListBuilder.create().texOffs(48, 52).addBox(0.5F, -8.0F, -16.0F, 4.0F, 8.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
        return LayerDefinition.create(modelData, 128, 128).apply(BABY_TRANSFORMER).apply(MeshTransformer.scaling(1.2F));
    }

    @Override
    public void setupAnim(GrizzlyBearRenderState grizzlyBearRenderState) {
        super.setupAnim(grizzlyBearRenderState);
        this.getPart().getAllParts().forEach(ModelPart::resetPose);
        this.setHeadAngles(grizzlyBearRenderState, grizzlyBearRenderState.yRot, grizzlyBearRenderState.xRot);
        this.walkingAnimation.applyWalk(grizzlyBearRenderState.walkAnimationPos, grizzlyBearRenderState.walkAnimationSpeed, 2.0F, 2.5F);
        this.sittingTransitionAnimation.apply(grizzlyBearRenderState.sittingTransitionAnimationState, grizzlyBearRenderState.ageInTicks);
        this.sittingAnimation.apply(grizzlyBearRenderState.sittingAnimationState, grizzlyBearRenderState.ageInTicks);
        this.standingAnimation.apply(grizzlyBearRenderState.standingTransitionAnimationState, grizzlyBearRenderState.ageInTicks);
        this.idlingAnimation.apply(grizzlyBearRenderState.idlingAnimationState, grizzlyBearRenderState.ageInTicks);
        this.attackAnimation.apply(grizzlyBearRenderState.attackingAnimationState, grizzlyBearRenderState.ageInTicks);
    }

    private void setHeadAngles(GrizzlyBearRenderState grizzlyBearRenderState, float headYaw, float headPitch) {
        this.head.yRot = headYaw * 0.017453292F;
        this.head.xRot = headPitch * 0.017453292F;
    }

    public ModelPart getPart() {
        return root;
    }

    public void render(PoseStack matrices, VertexConsumer vertexConsumer, int light, int color, float red, float green, float blue, float alpha) {
        root.render(matrices, vertexConsumer, light, color);
    }
}
