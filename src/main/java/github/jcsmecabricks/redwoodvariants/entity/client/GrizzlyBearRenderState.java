package github.jcsmecabricks.redwoodvariants.entity.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.world.entity.AnimationState;

@Environment(EnvType.CLIENT)
public class GrizzlyBearRenderState extends LivingEntityRenderState {
    public final AnimationState idlingAnimationState = new AnimationState();
    public float warningAnimationProgress;
    public final AnimationState sittingTransitionAnimationState = new AnimationState();
    public final AnimationState sittingAnimationState = new AnimationState();
    public final AnimationState standingTransitionAnimationState = new AnimationState();
    public final AnimationState attackingAnimationState = new AnimationState();
}
