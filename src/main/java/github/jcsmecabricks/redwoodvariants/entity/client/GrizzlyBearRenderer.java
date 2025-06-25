package github.jcsmecabricks.redwoodvariants.entity.client;

import github.jcsmecabricks.redwoodvariants.RedwoodVariants;
import github.jcsmecabricks.redwoodvariants.entity.custom.GrizzlyBearEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class GrizzlyBearRenderer extends MobEntityRenderer<GrizzlyBearEntity, GrizzlyBearRenderState, GrizzlyBearModel> {
    private static final Identifier TEXTURE = Identifier.of(RedwoodVariants.MOD_ID, "textures/entity/grizzly/grizzly_bear.png");

    public GrizzlyBearRenderer(EntityRendererFactory.Context context) {
        super(context, new GrizzlyBearModel(context.getPart(GrizzlyBearModel.GRIZZLY_BEAR)), 0.9F);
    }

    @Override
    public GrizzlyBearRenderState createRenderState() {
        return new GrizzlyBearRenderState();
    }

    @Override
    public void updateRenderState(GrizzlyBearEntity entity, GrizzlyBearRenderState state, float tickDelta) {
        super.updateRenderState(entity, state, tickDelta);
        state.idlingAnimationState.copyFrom(entity.idleAnimationState);
        state.sittingTransitionAnimationState.copyFrom(entity.sittingTransitionAnimationState);
        state.sittingAnimationState.copyFrom(entity.sittingAnimationState);
        state.standingTransitionAnimationState.copyFrom(entity.standingTransitionAnimationState);
        state.attackingAnimationState.copyFrom(entity.attackAnimationState);
        state.baby = entity.isBaby();
    }

    @Override
    protected void scale(GrizzlyBearRenderState state, MatrixStack matrices) {
        float scale = state.baby ? 1.1F : 2.1F;
        matrices.scale(scale, scale, scale);
    }

    @Override
    public Identifier getTexture(GrizzlyBearRenderState state) {
        return TEXTURE;
    }
}
