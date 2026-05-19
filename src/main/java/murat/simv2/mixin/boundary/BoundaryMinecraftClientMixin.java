package murat.simv2.mixin.boundary;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Escape-root boundary gate: the {@code MinecraftClient} singleton.
 *
 * <p>The one shared mutable root the movement code can reach <em>not</em>
 * through a held reference is the {@code MinecraftClient} static singleton. The
 * only such mutator reachable from a movement tick is {@code setScreen}
 * (e.g. via {@code tickNausea}/{@code closeScreen}); cancelling it at HEAD while
 * predicting stops the prediction from yanking the real screen. Thread-scoped.
 */
@Mixin(targets = "net/minecraft/client/MinecraftClient")
public abstract class BoundaryMinecraftClientMixin {

    @Inject(method = "setScreen(Lnet/minecraft/client/gui/screen/Screen;)V", at = @At("HEAD"), cancellable = true, require = 1)
    private void simv2$g0(CallbackInfo ci) {
        if (murat.simv2.predict.Prediction.isActive()) { ci.cancel(); }
    }
}
