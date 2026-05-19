package murat.simv2.mixin.boundary;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Escape-root boundary gate: the real {@code ClientWorld} effect overrides.
 *
 * <p>The prediction clone shares the real {@code ClientWorld}. Every particle /
 * sound / world-event / block-write / entity-add the clone's movement tick can
 * reach funnels through these {@code ClientWorld} methods regardless of call
 * depth, so cancelling them at HEAD while {@link murat.simv2.predict.Prediction}
 * is active isolates the prediction by construction — no per-leaf denylist.
 * <em>Reads</em> (block/fluid/collision/light) are deliberately untouched so the
 * predicted physics stays exact. The guard is thread-scoped, so the real game on
 * other threads is never affected.
 */
@Mixin(targets = "net/minecraft/client/world/ClientWorld")
public abstract class BoundaryClientWorldMixin {

    @Inject(method = "addParticleClient(Lnet/minecraft/particle/ParticleEffect;DDDDDD)V", at = @At("HEAD"), cancellable = true, require = 1)
    private void simv2$g0(CallbackInfo ci) {
        if (murat.simv2.predict.Prediction.isActive()) { ci.cancel(); }
    }

    @Inject(method = "addParticleClient(Lnet/minecraft/particle/ParticleEffect;ZZDDDDDD)V", at = @At("HEAD"), cancellable = true, require = 1)
    private void simv2$g1(CallbackInfo ci) {
        if (murat.simv2.predict.Prediction.isActive()) { ci.cancel(); }
    }

    @Inject(method = "addImportantParticleClient(Lnet/minecraft/particle/ParticleEffect;DDDDDD)V", at = @At("HEAD"), cancellable = true, require = 1)
    private void simv2$g2(CallbackInfo ci) {
        if (murat.simv2.predict.Prediction.isActive()) { ci.cancel(); }
    }

    @Inject(method = "addImportantParticleClient(Lnet/minecraft/particle/ParticleEffect;ZDDDDDD)V", at = @At("HEAD"), cancellable = true, require = 1)
    private void simv2$g3(CallbackInfo ci) {
        if (murat.simv2.predict.Prediction.isActive()) { ci.cancel(); }
    }

    @Inject(method = "addBlockBreakParticles(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;)V", at = @At("HEAD"), cancellable = true, require = 1)
    private void simv2$g4(CallbackInfo ci) {
        if (murat.simv2.predict.Prediction.isActive()) { ci.cancel(); }
    }

    @Inject(method = "addFireworkParticle(DDDDDDLjava/util/List;)V", at = @At("HEAD"), cancellable = true, require = 1)
    private void simv2$g5(CallbackInfo ci) {
        if (murat.simv2.predict.Prediction.isActive()) { ci.cancel(); }
    }

    @Inject(method = "playSound(Lnet/minecraft/entity/Entity;DDDLnet/minecraft/registry/entry/RegistryEntry;Lnet/minecraft/sound/SoundCategory;FFJ)V", at = @At("HEAD"), cancellable = true, require = 1)
    private void simv2$g6(CallbackInfo ci) {
        if (murat.simv2.predict.Prediction.isActive()) { ci.cancel(); }
    }

    @Inject(method = "playSoundFromEntity(Lnet/minecraft/entity/Entity;Lnet/minecraft/entity/Entity;Lnet/minecraft/registry/entry/RegistryEntry;Lnet/minecraft/sound/SoundCategory;FFJ)V", at = @At("HEAD"), cancellable = true, require = 1)
    private void simv2$g7(CallbackInfo ci) {
        if (murat.simv2.predict.Prediction.isActive()) { ci.cancel(); }
    }

    @Inject(method = "playSoundFromEntityClient(Lnet/minecraft/entity/Entity;Lnet/minecraft/sound/SoundEvent;Lnet/minecraft/sound/SoundCategory;FF)V", at = @At("HEAD"), cancellable = true, require = 1)
    private void simv2$g8(CallbackInfo ci) {
        if (murat.simv2.predict.Prediction.isActive()) { ci.cancel(); }
    }

    @Inject(method = "playSoundClient(Lnet/minecraft/sound/SoundEvent;Lnet/minecraft/sound/SoundCategory;FF)V", at = @At("HEAD"), cancellable = true, require = 1)
    private void simv2$g9(CallbackInfo ci) {
        if (murat.simv2.predict.Prediction.isActive()) { ci.cancel(); }
    }

    @Inject(method = "playSoundClient(DDDLnet/minecraft/sound/SoundEvent;Lnet/minecraft/sound/SoundCategory;FFZ)V", at = @At("HEAD"), cancellable = true, require = 1)
    private void simv2$g10(CallbackInfo ci) {
        if (murat.simv2.predict.Prediction.isActive()) { ci.cancel(); }
    }

    @Inject(method = "syncWorldEvent(Lnet/minecraft/entity/Entity;ILnet/minecraft/util/math/BlockPos;I)V", at = @At("HEAD"), cancellable = true, require = 1)
    private void simv2$g11(CallbackInfo ci) {
        if (murat.simv2.predict.Prediction.isActive()) { ci.cancel(); }
    }

    @Inject(method = "syncGlobalEvent(ILnet/minecraft/util/math/BlockPos;I)V", at = @At("HEAD"), cancellable = true, require = 1)
    private void simv2$g12(CallbackInfo ci) {
        if (murat.simv2.predict.Prediction.isActive()) { ci.cancel(); }
    }

    @Inject(method = "setBlockBreakingInfo(ILnet/minecraft/util/math/BlockPos;I)V", at = @At("HEAD"), cancellable = true, require = 1)
    private void simv2$g13(CallbackInfo ci) {
        if (murat.simv2.predict.Prediction.isActive()) { ci.cancel(); }
    }

    @Inject(method = "addEntity(Lnet/minecraft/entity/Entity;)V", at = @At("HEAD"), cancellable = true, require = 1)
    private void simv2$g14(CallbackInfo ci) {
        if (murat.simv2.predict.Prediction.isActive()) { ci.cancel(); }
    }

    @Inject(method = "emitGameEvent(Lnet/minecraft/registry/entry/RegistryEntry;Lnet/minecraft/util/math/Vec3d;Lnet/minecraft/world/event/GameEvent$Emitter;)V", at = @At("HEAD"), cancellable = true, require = 1)
    private void simv2$g15(CallbackInfo ci) {
        if (murat.simv2.predict.Prediction.isActive()) { ci.cancel(); }
    }

    @Inject(method = "setBlockState(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;II)Z", at = @At("HEAD"), cancellable = true, require = 1)
    private void simv2$g16(CallbackInfoReturnable<Boolean> cir) {
        if (murat.simv2.predict.Prediction.isActive()) { cir.setReturnValue(false); }
    }

    @Inject(method = "getCrammedEntities(Lnet/minecraft/entity/Entity;Lnet/minecraft/util/math/Box;)Ljava/util/List;", at = @At("HEAD"), cancellable = true, require = 1)
    private void simv2$g17(CallbackInfoReturnable<java.util.List> cir) {
        if (murat.simv2.predict.Prediction.isActive()) { cir.setReturnValue(java.util.List.of()); }
    }
}
