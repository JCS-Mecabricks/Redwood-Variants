package github.jcsmecabricks.redwoodvariants.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import github.jcsmecabricks.redwoodvariants.RedwoodVariants;
import github.jcsmecabricks.redwoodvariants.entity.custom.GrizzlyBearEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;

public class GrizzlyBearRenderer extends MobRenderer<GrizzlyBearEntity, GrizzlyBearRenderState, GrizzlyBearModel> {
    private static final Identifier TEXTURE = Identifier.fromNamespaceAndPath(RedwoodVariants.MOD_ID, "textures/entity/grizzly/grizzly_bear.png");

    public GrizzlyBearRenderer(EntityRendererProvider.Context context) {
        super(context, new GrizzlyBearModel(context.bakeLayer(GrizzlyBearModel.GRIZZLY_BEAR)), 0.9F);
    }

    @Override
    public GrizzlyBearRenderState createRenderState() {
        return new GrizzlyBearRenderState();
    }

    @Override
    public void extractRenderState(GrizzlyBearEntity entity, GrizzlyBearRenderState state, float partialTicks) {
        super.extractRenderState(entity, state, partialTicks);
        state.idlingAnimationState.copyFrom(entity.idleAnimationState);
        state.sittingTransitionAnimationState.copyFrom(entity.sittingTransitionAnimationState);
        state.sittingAnimationState.copyFrom(entity.sittingAnimationState);
        state.standingTransitionAnimationState.copyFrom(entity.standingTransitionAnimationState);
        state.attackingAnimationState.copyFrom(entity.attackAnimationState);
        state.isBaby = entity.isBaby();
    }

    @Override
    public Identifier getTextureLocation(GrizzlyBearRenderState state) {
        return TEXTURE;
    }

    @Override
    protected void scale(GrizzlyBearRenderState state, PoseStack matrices) {
        float scale = state.isBaby ? 1.1F : 2.1F;
        matrices.scale(scale, scale, scale);
    }
}
