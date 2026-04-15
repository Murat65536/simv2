package murat.simv2;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;

import com.mojang.brigadier.Command;
import java.util.ArrayList;
import java.util.List;
import murat.simv2.simulation.RuntimeMirroredClientPlayerEntity;
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
    private static RuntimeMirroredClientPlayerEntity simulator;
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
            PathRenderer.clearPath();
            return;
        }

        try {
            RuntimeMirroredClientPlayerEntity sim = ensureSimulator(client, realPlayer);
            sim.syncFrom(realPlayer);
            PathRenderer.setPath(predictPath(sim, PREDICTION_TICKS));
        } catch (RuntimeException ex) {
            recordFailure(ex);
            enabled = false;
            simulator = null;
            PathRenderer.clearPath();
            client.execute(() -> {
                if (client.player != null) {
                    client.player.sendMessage(Text.literal("Path prediction disabled: " + ex.getClass().getSimpleName()), false);
                }
            });
            SimV2.LOGGER.error("Path prediction failed and was disabled", ex);
        }
    }

    private static RuntimeMirroredClientPlayerEntity ensureSimulator(MinecraftClient client, ClientPlayerEntity realPlayer) {
        if (simulator == null || !simulator.isBoundTo(realPlayer) || simulator.getWorld() != realPlayer.getWorld()) {
            simulator = new RuntimeMirroredClientPlayerEntity(client, realPlayer);
        }
        return simulator;
    }

    private static List<Vec3d> predictPath(RuntimeMirroredClientPlayerEntity sim, int ticks) {
        int safeTicks = Math.max(0, ticks);
        List<Vec3d> positions = new ArrayList<>(safeTicks + 1);
        positions.add(sim.getPos().add(0.0, 0.05, 0.0));
        for (int i = 0; i < safeTicks; i++) {
            // tickMovement already applies movement input once per simulated tick.
            sim.tickMovement();
            positions.add(sim.getPos().add(0.0, 0.05, 0.0));
        }
        return positions;
    }

    private static int setEnabled(FabricClientCommandSource source, boolean newValue) {
        enabled = newValue;
        if (!enabled) {
            simulator = null;
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
