package github.jcsmecabricks.redwoodvariants.entity.client;

import github.jcsmecabricks.redwoodvariants.RedwoodVariants;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.animation.Animation;
import net.minecraft.client.render.entity.animation.CamelAnimations;
import net.minecraft.client.render.entity.model.BabyModelTransformer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.ModelTransformer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

import java.util.Set;

public class GrizzlyBearModel extends EntityModel<GrizzlyBearRenderState> {
    private static final ModelTransformer BABY_TRANSFORMER = new BabyModelTransformer(true, 16.0F, 4.0F, 2.25F, 2.0F, 24.0F, Set.of("head"));
    public static final EntityModelLayer GRIZZLY_BEAR = new EntityModelLayer(Identifier.of(RedwoodVariants.MOD_ID, "grizzly_bear"), "main");
    private final Animation walkingAnimation;
    private final Animation attackAnimation;
    private final Animation sittingTransitionAnimation;
    private final Animation sittingAnimation;
    private final Animation standingAnimation;
    private final Animation idlingAnimation;
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
        this.attackAnimation = GrizzlyBearAnimations.ATTACK.createAnimation(root);
        this.walkingAnimation = GrizzlyBearAnimations.WALK.createAnimation(root);
        this.sittingTransitionAnimation = GrizzlyBearAnimations.SIT_TRANSITION.createAnimation(root);
        this.sittingAnimation = GrizzlyBearAnimations.SIT.createAnimation(root);
        this.standingAnimation = GrizzlyBearAnimations.STAND.createAnimation(root);
        this.idlingAnimation = GrizzlyBearAnimations.IDLE.createAnimation(root);
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
    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData root = modelPartData.addChild("root", ModelPartBuilder.create(), ModelTransform.origin(0.0F, 24.0F, 0.0F));

        ModelPartData grizzly_bear = root.addChild("grizzly_bear", ModelPartBuilder.create(), ModelTransform.origin(1.0F, 0.0F, 5.0F));

        ModelPartData body = grizzly_bear.addChild("body", ModelPartBuilder.create().uv(0, 0).cuboid(-8.0F, -19.0F, -7.0F, 14.0F, 11.0F, 14.0F, new Dilation(0.0F)), ModelTransform.origin(0.0F, 0.0F, 0.0F));

        ModelPartData neck = grizzly_bear.addChild("neck", ModelPartBuilder.create().uv(0, 25).cuboid(-7.0F, -9.0F, -6.0F, 12.0F, 10.0F, 12.0F, new Dilation(0.0F)), ModelTransform.origin(0.0F, -9.0F, -13.0F));

        ModelPartData head = neck.addChild("head", ModelPartBuilder.create().uv(47, 24).cuboid(-6.5F, -7.0F, -5.0F, 7.0F, 7.0F, 7.0F, new Dilation(0.0F))
                .uv(56, 6).cuboid(-7.5F, -7.0F, -4.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(56, 12).cuboid(-0.5F, -8.0F, -4.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(56, 10).cuboid(0.5F, -7.0F, -4.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(56, 8).cuboid(-7.5F, -8.0F, -4.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(56, 14).cuboid(0.5F, -8.0F, -4.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(56, 16).cuboid(-6.5F, -8.0F, -4.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.origin(2.0F, 0.0F, -7.0F));

        ModelPartData mouth = head.addChild("mouth", ModelPartBuilder.create().uv(56, 0).cuboid(-3.5F, -12.0F, -28.0F, 5.0F, 3.0F, 3.0F, new Dilation(0.0F)), ModelTransform.origin(-2.0F, 9.0F, 20.0F));

        ModelPartData right_leg = grizzly_bear.addChild("right_leg", ModelPartBuilder.create(), ModelTransform.origin(0.0F, 0.0F, 0.0F));

        ModelPartData right_front = right_leg.addChild("right_front", ModelPartBuilder.create().uv(48, 38).cuboid(-6.5F, -8.0F, -16.0F, 4.0F, 8.0F, 6.0F, new Dilation(0.0F)), ModelTransform.origin(0.0F, 0.0F, 0.0F));

        ModelPartData right_back = right_leg.addChild("right_back", ModelPartBuilder.create().uv(0, 47).cuboid(-7.5F, -8.0F, -2.0F, 4.0F, 8.0F, 8.0F, new Dilation(0.0F)), ModelTransform.origin(0.0F, 0.0F, 0.0F));

        ModelPartData left_leg = grizzly_bear.addChild("left_leg", ModelPartBuilder.create(), ModelTransform.origin(0.0F, 0.0F, 0.0F));

        ModelPartData left_back = left_leg.addChild("left_back", ModelPartBuilder.create().uv(24, 47).cuboid(1.5F, -8.0F, -2.0F, 4.0F, 8.0F, 8.0F, new Dilation(0.0F)), ModelTransform.origin(0.0F, 0.0F, 0.0F));

        ModelPartData left_front = left_leg.addChild("left_front", ModelPartBuilder.create().uv(48, 52).cuboid(0.5F, -8.0F, -16.0F, 4.0F, 8.0F, 6.0F, new Dilation(0.0F)), ModelTransform.origin(0.0F, 0.0F, 0.0F));
        return TexturedModelData.of(modelData, 128, 128).transform(BABY_TRANSFORMER).transform(ModelTransformer.scaling(1.2F));
    }

    @Override
    public void setAngles(GrizzlyBearRenderState grizzlyBearRenderState) {
        super.setAngles(grizzlyBearRenderState);
        this.getPart().traverse().forEach(ModelPart::resetTransform);
        this.setHeadAngles(grizzlyBearRenderState, grizzlyBearRenderState.relativeHeadYaw, grizzlyBearRenderState.pitch);
        this.walkingAnimation.applyWalking(grizzlyBearRenderState.limbSwingAnimationProgress, grizzlyBearRenderState.limbSwingAmplitude, 2.0F, 2.5F);
        this.sittingTransitionAnimation.apply(grizzlyBearRenderState.sittingTransitionAnimationState, grizzlyBearRenderState.age);
        this.sittingAnimation.apply(grizzlyBearRenderState.sittingAnimationState, grizzlyBearRenderState.age);
        this.standingAnimation.apply(grizzlyBearRenderState.standingTransitionAnimationState, grizzlyBearRenderState.age);
        this.idlingAnimation.apply(grizzlyBearRenderState.idlingAnimationState, grizzlyBearRenderState.age);
        this.attackAnimation.apply(grizzlyBearRenderState.attackingAnimationState, grizzlyBearRenderState.age);
    }

    private void setHeadAngles(GrizzlyBearRenderState grizzlyBearRenderState, float headYaw, float headPitch) {
        this.head.yaw = headYaw * 0.017453292F;
        this.head.pitch = headPitch * 0.017453292F;
    }

    public ModelPart getPart() {
        return root;
    }

    public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int color, float red, float green, float blue, float alpha) {
        root.render(matrices, vertexConsumer, light, color);
    }
}
