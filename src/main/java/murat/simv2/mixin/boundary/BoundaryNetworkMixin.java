package murat.simv2.mixin.boundary;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Escape-root boundary gate: the network handler.
 *
 * <p>The clone shares the real {@code ClientPlayNetworkHandler}. Every outbound
 * packet — direct {@code networkHandler.sendPacket(...)} from the clone and
 * {@code ClientWorld.sendPacket} alike — funnels through
 * {@code ClientCommonNetworkHandler.sendPacket(Packet)}, so cancelling it at
 * HEAD while predicting means the simulation has nowhere to send. Thread-scoped:
 * the real game's outbound traffic on other threads is untouched.
 */
@Mixin(targets = "net/minecraft/client/network/ClientCommonNetworkHandler")
public abstract class BoundaryNetworkMixin {

    @Inject(method = "sendPacket(Lnet/minecraft/network/packet/Packet;)V", at = @At("HEAD"), cancellable = true, require = 1)
    private void simv2$g0(CallbackInfo ci) {
        if (murat.simv2.predict.Prediction.isActive()) { ci.cancel(); }
    }
}
