package murat.simv2.mixin.boundary;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Escape-root boundary gate: the tutorial manager.
 *
 * <p>{@code ClientPlayerEntity.tickMovement} feeds the held input into
 * {@code TutorialManager.onMovement}, which accumulates real tutorial progress
 * state. Cancelling it at HEAD while predicting keeps the simulated input from
 * advancing the real tutorial. Thread-scoped — real movement still drives it.
 */
@Mixin(targets = "net/minecraft/client/tutorial/TutorialManager")
public abstract class BoundaryTutorialManagerMixin {

    @Inject(method = "onMovement(Lnet/minecraft/client/input/Input;)V", at = @At("HEAD"), cancellable = true, require = 1)
    private void simv2$g0(CallbackInfo ci) {
        if (murat.simv2.predict.Prediction.isActive()) { ci.cancel(); }
    }
}
