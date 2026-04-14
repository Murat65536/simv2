package murat.simv2;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;

import com.mojang.brigadier.Command;
import java.util.ArrayList;
import java.util.List;
import murat.simv2.simulation.RuntimeSlicedClientPlayerEntity;
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
    private static RuntimeSlicedClientPlayerEntity simulator;

    private PathPredictionController() {
    }

    public static void register() {
        ClientTickEvents.END_CLIENT_TICK.register(PathPredictionController::onEndClientTick);
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) ->
            dispatcher.register(literal("pathpredict")
                .then(literal("on").executes(context -> setEnabled(context.getSource(), true)))
                .then(literal("off").executes(context -> setEnabled(context.getSource(), false)))
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
            RuntimeSlicedClientPlayerEntity sim = ensureSimulator(client, realPlayer);
            sim.syncFrom(realPlayer);
            PathRenderer.setPath(predictPath(sim, PREDICTION_TICKS));
        } catch (RuntimeException ex) {
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

    private static RuntimeSlicedClientPlayerEntity ensureSimulator(MinecraftClient client, ClientPlayerEntity realPlayer) {
        if (simulator == null || !simulator.isBoundTo(realPlayer) || simulator.getWorld() != realPlayer.getWorld()) {
            simulator = new RuntimeSlicedClientPlayerEntity(client, realPlayer);
        }
        return simulator;
    }

    private static List<Vec3d> predictPath(RuntimeSlicedClientPlayerEntity sim, int ticks) {
        List<Vec3d> positions = new ArrayList<>(ticks + 1);
        positions.add(sim.getPos().add(0.0, 0.05, 0.0));
        for (int i = 0; i < ticks; i++) {
            sim.tickMovementInput();
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
        source.sendFeedback(Text.literal(enabled
            ? "Path prediction enabled"
            : "Path prediction disabled"));
        return Command.SINGLE_SUCCESS;
    }
}
