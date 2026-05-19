package murat.simv2.mixin;

import murat.simv2.predict.Prediction;
import net.minecraft.client.network.ClientPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Makes the prediction clone count as the controlled (camera) entity while a
 * prediction is running.
 *
 * <p>{@code ClientPlayerEntity.tickMovementInput()} only applies the player's
 * input ({@code this.input} → {@code forwardSpeed}/{@code sidewaysSpeed}/
 * {@code jumping}) inside {@code if (this.isCamera())}; otherwise it runs the
 * no-input branch. {@code isCamera()} is {@code client.getCameraEntity() ==
 * this}, which is false for the clone (the real player is the camera), so
 * without this the clone never applies the held input and the prediction
 * decays to "if you released all keys".
 *
 * <p>Forcing {@code isCamera()} true only while {@link Prediction#isActive()}
 * (i.e. only during the synchronous prediction, which only ever ticks the
 * clone) makes the clone reuse MC's own player-controlled movement path — no
 * duplicated movement math — so the prediction is "where you'd be if you kept
 * holding the same keys". When not predicting it is one branch-predicted load,
 * zero behaviour change for the real player.
 */
@Mixin(ClientPlayerEntity.class)
public abstract class PredictionCameraMixin {

    @Inject(method = "isCamera", at = @At("HEAD"), cancellable = true)
    private void simv2$predictAsCamera(CallbackInfoReturnable<Boolean> cir) {
        if (Prediction.isActive()) {
            cir.setReturnValue(true);
        }
    }
}
