package murat.simv2;

import murat.simv2.predict.MovementPredictor;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.util.math.Vec3d;

import java.util.List;

public class SimV2Client implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        PathRenderer.register();

        // Each client tick: clone the live player, run the clone forward with
        // side effects gated off, and hand the predicted positions to the
        // renderer. The real player is never ticked, so the game is never
        // impacted; MovementPredictor lazy-inits and disables itself on any
        // failure — it never throws here.
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player == null) {
                PathRenderer.clearPath();
                return;
            }
            List<Vec3d> predicted = MovementPredictor.INSTANCE.predict(client.player);
            if (predicted.isEmpty()) {
                PathRenderer.clearPath();
            } else {
                PathRenderer.setPath(predicted);
            }
        });
    }
}
