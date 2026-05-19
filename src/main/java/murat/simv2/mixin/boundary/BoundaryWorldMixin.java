package murat.simv2.mixin.boundary;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Escape-root boundary gate: the {@code World} public effect API.
 *
 * <p>Defense-in-depth alongside {@link BoundaryClientWorldMixin}: the concrete
 * {@code World} sound/particle/block-write entry points the clone calls are
 * cancelled here at HEAD before they delegate down to the
 * {@code ClientWorld} override. {@code World}'s {@code abstract}
 * {@code playSound}/{@code playSoundFromEntity} {@code RegistryEntry} overloads
 * are intentionally <em>not</em> targeted (no method body to inject into — their
 * concrete implementations live on {@code ClientWorld} and are gated there).
 * {@code getOtherEntities} is emptied during prediction so the clone never
 * obtains a real entity it could mutate (e.g. {@code pushAwayFrom}); reads stay
 * exact otherwise.
 */
@Mixin(targets = "net/minecraft/world/World")
public abstract class BoundaryWorldMixin {

    @Inject(method = "addParticleClient(Lnet/minecraft/particle/ParticleEffect;DDDDDD)V", at = @At("HEAD"), cancellable = true, require = 1)
    private void simv2$g0(CallbackInfo ci) {
        if (murat.simv2.predict.Prediction.isActive()) { ci.cancel(); }
    }

    @Inject(method = "playSoundClient(DDDLnet/minecraft/sound/SoundEvent;Lnet/minecraft/sound/SoundCategory;FFZ)V", at = @At("HEAD"), cancellable = true, require = 1)
    private void simv2$g1(CallbackInfo ci) {
        if (murat.simv2.predict.Prediction.isActive()) { ci.cancel(); }
    }

    @Inject(method = "playSoundFromEntity(Lnet/minecraft/entity/Entity;Lnet/minecraft/entity/Entity;Lnet/minecraft/sound/SoundEvent;Lnet/minecraft/sound/SoundCategory;FF)V", at = @At("HEAD"), cancellable = true, require = 1)
    private void simv2$g2(CallbackInfo ci) {
        if (murat.simv2.predict.Prediction.isActive()) { ci.cancel(); }
    }

    @Inject(method = "playSound(Lnet/minecraft/entity/Entity;DDDLnet/minecraft/sound/SoundEvent;Lnet/minecraft/sound/SoundCategory;)V", at = @At("HEAD"), cancellable = true, require = 1)
    private void simv2$g3(CallbackInfo ci) {
        if (murat.simv2.predict.Prediction.isActive()) { ci.cancel(); }
    }

    @Inject(method = "playSound(Lnet/minecraft/entity/Entity;DDDLnet/minecraft/sound/SoundEvent;Lnet/minecraft/sound/SoundCategory;FF)V", at = @At("HEAD"), cancellable = true, require = 1)
    private void simv2$g4(CallbackInfo ci) {
        if (murat.simv2.predict.Prediction.isActive()) { ci.cancel(); }
    }

    @Inject(method = "playSound(Lnet/minecraft/entity/Entity;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/sound/SoundEvent;Lnet/minecraft/sound/SoundCategory;FF)V", at = @At("HEAD"), cancellable = true, require = 1)
    private void simv2$g5(CallbackInfo ci) {
        if (murat.simv2.predict.Prediction.isActive()) { ci.cancel(); }
    }

    @Inject(method = "playSound(Lnet/minecraft/entity/Entity;DDDLnet/minecraft/sound/SoundEvent;Lnet/minecraft/sound/SoundCategory;FFJ)V", at = @At("HEAD"), cancellable = true, require = 1)
    private void simv2$g6(CallbackInfo ci) {
        if (murat.simv2.predict.Prediction.isActive()) { ci.cancel(); }
    }

    @Inject(method = "setBlockState(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;I)Z", at = @At("HEAD"), cancellable = true, require = 1)
    private void simv2$g7(CallbackInfoReturnable<Boolean> cir) {
        if (murat.simv2.predict.Prediction.isActive()) { cir.setReturnValue(false); }
    }

    @Inject(method = "getOtherEntities(Lnet/minecraft/entity/Entity;Lnet/minecraft/util/math/Box;Ljava/util/function/Predicate;)Ljava/util/List;", at = @At("HEAD"), cancellable = true, require = 1)
    private void simv2$g8(CallbackInfoReturnable<java.util.List> cir) {
        if (murat.simv2.predict.Prediction.isActive()) { cir.setReturnValue(java.util.List.of()); }
    }
}
