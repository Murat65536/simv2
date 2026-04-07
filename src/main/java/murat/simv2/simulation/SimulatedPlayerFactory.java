package murat.simv2.simulation;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.world.ClientWorld;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

/**
 * Creates {@link SimulatedPlayer} instances via Objenesis (constructor bypass)
 * followed by manual field initialisation in {@link SimulatedPlayer#bootstrap}.
 * <p>
 * This avoids the heavy Entity → ClientPlayerEntity constructor chain while
 * still producing a real subclass that passes all {@code instanceof} checks.
 */
public final class SimulatedPlayerFactory {

    private static final Objenesis OBJENESIS = new ObjenesisStd(true);

    private SimulatedPlayerFactory() {}

    public static SimulatedPlayer create(MinecraftClient client, ClientWorld world, ClientPlayNetworkHandler networkHandler) {
        SimulatedPlayer player = OBJENESIS.newInstance(SimulatedPlayer.class);
        player.bootstrap(client, world, networkHandler);
        return player;
    }
}
