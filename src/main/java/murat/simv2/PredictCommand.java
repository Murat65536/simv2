package murat.simv2;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import murat.simv2.simulation.MovementSimulator;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.Vec3d;

import java.util.List;

public class PredictCommand {

    private static final MovementSimulator simulator = new MovementSimulator();
    private static int activeTicks = 0;

    public static void register() {
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) ->
            dispatcher.register(
                ClientCommandManager.literal("predict")
                    .executes(ctx -> {
                        return stop(ctx.getSource().getPlayer());
                    })
                    .then(ClientCommandManager.argument("ticks", IntegerArgumentType.integer(0, 200))
                        .executes(ctx -> {
                            int ticks = IntegerArgumentType.getInteger(ctx, "ticks");
                            if (ticks == 0) return stop(ctx.getSource().getPlayer());
                            return start(ticks, ctx.getSource().getPlayer());
                        })
                    )
            )
        );

        ClientTickEvents.END_CLIENT_TICK.register(PredictCommand::onTick);
    }

    private static int start(int ticks, ClientPlayerEntity player) {
        activeTicks = ticks;
        player.sendMessage(Text.literal("Predicting " + ticks + " ticks"), true);
        return 1;
    }

    private static int stop(ClientPlayerEntity player) {
        activeTicks = 0;
        PathRenderer.clearPath();
        player.sendMessage(Text.literal("Prediction stopped"), true);
        return 1;
    }

    private static void onTick(MinecraftClient client) {
        if (activeTicks <= 0) return;

        ClientPlayerEntity player = client.player;
        if (player == null || client.world == null) return;

        if (!simulator.isInitialized()) {
            simulator.init();
        }

        List<Vec3d> path = simulator.predict(
            player.input.playerInput,
            player.getYaw(),
            player.getPitch(),
            activeTicks
        );

        PathRenderer.setPath(path);
    }
}
