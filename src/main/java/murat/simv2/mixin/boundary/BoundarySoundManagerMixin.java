package murat.simv2.mixin.boundary;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Escape-root boundary gate: the sound engine.
 *
 * <p>A few movement sounds reach the engine without going through the world —
 * e.g. elytra and portal sounds via {@code client.getSoundManager().play(...)}.
 * Cancelling {@code SoundManager.play} (immediate and delayed) at HEAD while
 * predicting closes that bypass. Thread-scoped — real audio is untouched.
 */
@Mixin(targets = "net/minecraft/client/sound/SoundManager")
public abstract class BoundarySoundManagerMixin {

    @Inject(method = "play(Lnet/minecraft/client/sound/SoundInstance;)V", at = @At("HEAD"), cancellable = true, require = 1)
    private void simv2$g0(CallbackInfo ci) {
        if (murat.simv2.predict.Prediction.isActive()) { ci.cancel(); }
    }

    @Inject(method = "play(Lnet/minecraft/client/sound/SoundInstance;I)V", at = @At("HEAD"), cancellable = true, require = 1)
    private void simv2$g1(CallbackInfo ci) {
        if (murat.simv2.predict.Prediction.isActive()) { ci.cancel(); }
    }
}
