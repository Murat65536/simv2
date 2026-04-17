package murat.simv2;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;

import com.mojang.brigadier.Command;
import java.util.ArrayList;
import java.util.List;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.Vec3d;

public final class PathPredictionController {
    private static final int PREDICTION_TICKS = 20;

    private static boolean enabled;
    private static murat.simv2.simulation.mirror.net.minecraft.client.network.ClientPlayerEntity simulator;
    private static ClientPlayerEntity boundRealPlayer;
    private static String lastFailureClass;
    private static String lastFailureMessage;

    private PathPredictionController() {
    }

    public static void register() {
        ClientTickEvents.END_CLIENT_TICK.register(PathPredictionController::onEndClientTick);
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) ->
            dispatcher.register(literal("pathpredict")
                .then(literal("on").executes(context -> setEnabled(context.getSource(), true)))
                .then(literal("off").executes(context -> setEnabled(context.getSource(), false)))
                .then(literal("status").executes(context -> reportStatus(context.getSource())))
            )
        );
    }

    private static void onEndClientTick(MinecraftClient client) {
        if (!enabled) {
            return;
        }

        ClientPlayerEntity realPlayer = client.player;
        if (realPlayer == null || client.world == null || client.getNetworkHandler() == null) {
            simulator = null;
            boundRealPlayer = null;
            PathRenderer.clearPath();
            return;
        }

        try {
            murat.simv2.simulation.mirror.net.minecraft.client.network.ClientPlayerEntity sim = ensureSimulator(realPlayer);
            syncSimulator(sim, realPlayer);
            PathRenderer.setPath(predictPath(sim, PREDICTION_TICKS));
        } catch (RuntimeException ex) {
            recordFailure(ex);
            enabled = false;
            simulator = null;
            boundRealPlayer = null;
            PathRenderer.clearPath();
            client.execute(() -> {
                if (client.player != null) {
                    client.player.sendMessage(Text.literal("Path prediction disabled: " + ex.getClass().getSimpleName()), false);
                }
            });
            SimV2.LOGGER.error("Path prediction failed and was disabled", ex);
        }
    }

    private static murat.simv2.simulation.mirror.net.minecraft.client.network.ClientPlayerEntity ensureSimulator(ClientPlayerEntity realPlayer) {
        if (simulator == null || boundRealPlayer != realPlayer) {
            simulator = createSimulator(realPlayer);
            boundRealPlayer = realPlayer;
        }
        return simulator;
    }

    private static murat.simv2.simulation.mirror.net.minecraft.client.network.ClientPlayerEntity createSimulator(ClientPlayerEntity realPlayer) {
        return new murat.simv2.simulation.mirror.net.minecraft.client.network.ClientPlayerEntity() {
            @Override
            protected void initDataTracker(murat.simv2.simulation.mirror.net.minecraft.entity.data.DataTracker.Builder builder) {
            }

            @Override
            public boolean damage(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld world,
                                  murat.simv2.simulation.mirror.net.minecraft.entity.damage.DamageSource source,
                                  float amount) {
                return false;
            }

            @Override
            public void emitGameEvent(murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry<murat.simv2.simulation.mirror.net.minecraft.world.event.GameEvent> event) {
            }

            @Override
            public murat.simv2.simulation.mirror.net.minecraft.world.GameMode getGameMode() {
                return (boundRealPlayer != null && boundRealPlayer.isSpectator())
                    ? murat.simv2.simulation.mirror.net.minecraft.world.GameMode.SPECTATOR
                    : murat.simv2.simulation.mirror.net.minecraft.world.GameMode.SURVIVAL;
            }

            @Override
            public void setVelocity(double x, double y, double z) {
                this.velocity = new murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d(x, y, z);
            }
        };
    }

    private static void syncSimulator(murat.simv2.simulation.mirror.net.minecraft.client.network.ClientPlayerEntity sim,
                                      ClientPlayerEntity realPlayer) {
        invokeGeneratedSync(realPlayer, sim);
        sim.getDataTracker().set(murat.simv2.simulation.mirror.net.minecraft.entity.Entity.FLAGS, encodeEntityFlags(realPlayer));
        sim.getDataTracker().set(murat.simv2.simulation.mirror.net.minecraft.entity.Entity.POSE, toMirrorEntityPose(realPlayer.getPose()));
        sim.getDataTracker().set(murat.simv2.simulation.mirror.net.minecraft.entity.Entity.FROZEN_TICKS, realPlayer.getFrozenTicks());
        sim.getDataTracker().set(
            murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity.SLEEPING_POSITION,
            realPlayer.getSleepingPosition().map(PathPredictionController::toMirrorBlockPos)
        );
        sim.getDataTracker().set(murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity.HEALTH, realPlayer.getHealth());
        sim.getDataTracker().set(murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity.LIVING_FLAGS, encodeLivingFlags(realPlayer));
    }

    private static void invokeGeneratedSync(ClientPlayerEntity realPlayer,
                                            murat.simv2.simulation.mirror.net.minecraft.client.network.ClientPlayerEntity sim) {
        for (var method : murat.simv2.simulation.GeneratedSync.class.getDeclaredMethods()) {
            if (!method.getName().equals("sync") || method.getParameterCount() != 2) {
                continue;
            }
            Class<?>[] parameterTypes = method.getParameterTypes();
            if (!parameterTypes[0].isAssignableFrom(realPlayer.getClass())) {
                continue;
            }
            if (!parameterTypes[1].isAssignableFrom(sim.getClass())) {
                continue;
            }
            try {
                method.invoke(null, realPlayer, sim);
                return;
            } catch (ReflectiveOperationException ex) {
                throw new IllegalStateException("Failed to invoke GeneratedSync.sync", ex);
            }
        }
        throw new IllegalStateException("No compatible GeneratedSync.sync overload found");
    }

    private static List<Vec3d> predictPath(murat.simv2.simulation.mirror.net.minecraft.client.network.ClientPlayerEntity sim, int ticks) {
        int safeTicks = Math.max(0, ticks);
        List<Vec3d> positions = new ArrayList<>(safeTicks + 1);
        positions.add(toVanillaVec(sim.getPos()).add(0.0, 0.05, 0.0));
        for (int i = 0; i < safeTicks; i++) {
            // tickMovement already applies movement input once per simulated tick.
            sim.tickMovement();
            positions.add(toVanillaVec(sim.getPos()).add(0.0, 0.05, 0.0));
        }
        return positions;
    }

    private static Vec3d toVanillaVec(murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d value) {
        return new Vec3d(value.x, value.y, value.z);
    }

    private static murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos toMirrorBlockPos(net.minecraft.util.math.BlockPos value) {
        return new murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos(value.getX(), value.getY(), value.getZ());
    }

    private static murat.simv2.simulation.mirror.net.minecraft.entity.EntityPose toMirrorEntityPose(net.minecraft.entity.EntityPose value) {
        return murat.simv2.simulation.mirror.net.minecraft.entity.EntityPose.valueOf(value.name());
    }

    private static byte encodeEntityFlags(ClientPlayerEntity realPlayer) {
        byte flags = 0;
        if (realPlayer.isSneaking()) {
            flags |= (byte) (1 << murat.simv2.simulation.mirror.net.minecraft.entity.Entity.SNEAKING_FLAG_INDEX);
        }
        if (realPlayer.isSprinting()) {
            flags |= (byte) (1 << murat.simv2.simulation.mirror.net.minecraft.entity.Entity.SPRINTING_FLAG_INDEX);
        }
        if (realPlayer.isSwimming()) {
            flags |= (byte) (1 << murat.simv2.simulation.mirror.net.minecraft.entity.Entity.SWIMMING_FLAG_INDEX);
        }
        if (realPlayer.isGliding()) {
            flags |= (byte) (1 << murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity.GLIDING_FLAG_INDEX);
        }
        return flags;
    }

    private static byte encodeLivingFlags(ClientPlayerEntity realPlayer) {
        byte flags = 0;
        if (realPlayer.isUsingItem()) {
            flags |= 1;
        }
        if (realPlayer.isUsingRiptide()) {
            flags |= 4;
        }
        return flags;
    }

    private static int setEnabled(FabricClientCommandSource source, boolean newValue) {
        enabled = newValue;
        if (!enabled) {
            simulator = null;
            boundRealPlayer = null;
            PathRenderer.clearPath();
        }
        clearFailure();
        source.sendFeedback(Text.literal(enabled
            ? "Path prediction enabled"
            : "Path prediction disabled"));
        return Command.SINGLE_SUCCESS;
    }

    private static int reportStatus(FabricClientCommandSource source) {
        source.sendFeedback(Text.literal("Path prediction status: enabled=" + enabled
            + ", simulatorPresent=" + (simulator != null)
            + ", lastFailure=" + formatLastFailure()));
        return Command.SINGLE_SUCCESS;
    }

    private static void recordFailure(RuntimeException ex) {
        lastFailureClass = ex.getClass().getSimpleName();
        lastFailureMessage = ex.getMessage();
    }

    private static void clearFailure() {
        lastFailureClass = null;
        lastFailureMessage = null;
    }

    private static String formatLastFailure() {
        if (lastFailureClass == null) {
            return "none";
        }
        if (lastFailureMessage == null || lastFailureMessage.isBlank()) {
            return lastFailureClass;
        }
        return lastFailureClass + ": " + lastFailureMessage;
    }
}
