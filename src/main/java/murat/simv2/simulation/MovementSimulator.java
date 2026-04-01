package murat.simv2.simulation;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.util.PlayerInput;
import net.minecraft.util.math.Vec3d;

import java.util.ArrayList;
import java.util.List;

public class MovementSimulator {

    private SimulatedPlayer simPlayer;

    public void init() {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null || client.world == null) {
            throw new IllegalStateException("Cannot init MovementSimulator: player or world is null");
        }
        this.simPlayer = new SimulatedPlayer(client, client.world, client.player.networkHandler);
    }

    public boolean isInitialized() {
        return this.simPlayer != null;
    }

    public SimulatedPlayer getSimulatedPlayer() {
        return this.simPlayer;
    }

    /**
     * Sync simulation state from the real player, then predict N ticks
     * forward with constant input.
     *
     * @return list of positions after each tick (size == ticks)
     */
    public List<Vec3d> predict(PlayerInput input, float yaw, float pitch, int ticks) {
        ClientPlayerEntity real = MinecraftClient.getInstance().player;
        if (real == null) return List.of();

        simPlayer.syncFrom(real);
        simPlayer.setInput(input);
        simPlayer.setYaw(yaw);
        simPlayer.setPitch(pitch);

        List<Vec3d> path = new ArrayList<>(ticks);
        for (int i = 0; i < ticks; i++) {
            simPlayer.simulateTick();
            path.add(simPlayer.getPos());
        }
        return path;
    }

    /**
     * Predict with per-tick input changes.
     * Each frame specifies input, yaw, and pitch for that tick.
     *
     * @return list of positions after each tick (size == frames.size())
     */
    public List<Vec3d> predictSequence(List<InputFrame> frames) {
        ClientPlayerEntity real = MinecraftClient.getInstance().player;
        if (real == null) return List.of();

        simPlayer.syncFrom(real);

        List<Vec3d> path = new ArrayList<>(frames.size());
        for (InputFrame frame : frames) {
            simPlayer.setInput(frame.input());
            simPlayer.setYaw(frame.yaw());
            simPlayer.setPitch(frame.pitch());
            simPlayer.simulateTick();
            path.add(simPlayer.getPos());
        }
        return path;
    }

    /**
     * Run a single simulation tick with the given input.
     * Does NOT reset state -- call reset() first if you want a fresh start.
     */
    public void step(PlayerInput input, float yaw, float pitch) {
        simPlayer.setInput(input);
        simPlayer.setYaw(yaw);
        simPlayer.setPitch(pitch);
        simPlayer.simulateTick();
    }

    /**
     * Reset simulation state from the real player.
     */
    public void reset() {
        ClientPlayerEntity real = MinecraftClient.getInstance().player;
        if (real != null) {
            simPlayer.syncFrom(real);
        }
    }

    public record InputFrame(PlayerInput input, float yaw, float pitch) {}
}
