package murat.simv2;

import net.fabricmc.api.ClientModInitializer;

public class SimV2Client implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        PathRenderer.register();
        SimV2.LOGGER.info("[movement-sim] client init: movement validator armed (LivingEntityMixin)");
    }
}
