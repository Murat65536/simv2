package murat.simv2.mixin;

import murat.simv2.harness.MovementValidator;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Anchors the movement validator at the entry of {@link LivingEntity#travel} for the local
 * client player. HEAD is the precise phase where the real velocity has had jump / input-tick /
 * deadzone applied but movement integration has not — matching {@code MovementSim.travelMidAir}.
 */
@Mixin(LivingEntity.class)
public class LivingEntityMixin {

    @Inject(method = "travel(Lnet/minecraft/util/math/Vec3d;)V", at = @At("HEAD"))
    private void simv2$travelBegin(Vec3d movementInput, CallbackInfo ci) {
        if ((Object) this instanceof ClientPlayerEntity player
            && MinecraftClient.getInstance().player == player) {
            MovementValidator.beginTravel(player, movementInput);
        }
    }

    @Inject(method = "travel(Lnet/minecraft/util/math/Vec3d;)V", at = @At("RETURN"))
    private void simv2$travelEnd(Vec3d movementInput, CallbackInfo ci) {
        if ((Object) this instanceof ClientPlayerEntity player
            && MinecraftClient.getInstance().player == player) {
            MovementValidator.endTravel(player);
        }
    }
}
