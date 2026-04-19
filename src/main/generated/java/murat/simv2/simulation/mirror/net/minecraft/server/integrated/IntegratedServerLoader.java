package murat.simv2.simulation.mirror.net.minecraft.server.integrated;

// Generated mirror stub for simulation closure.
public class IntegratedServerLoader extends java.lang.Object {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static org.slf4j.Logger LOGGER;
    public static java.util.UUID WORLD_PACK_ID;
    public murat.simv2.simulation.mirror.net.minecraft.client.MinecraftClient client;
    public murat.simv2.simulation.mirror.net.minecraft.world.level.storage.LevelStorage storage;

    public IntegratedServerLoader(murat.simv2.simulation.mirror.net.minecraft.client.MinecraftClient p0, murat.simv2.simulation.mirror.net.minecraft.world.level.storage.LevelStorage p1) {
    }

    public java.util.concurrent.CompletableFuture applyWorldPack(murat.simv2.simulation.mirror.net.minecraft.client.resource.server.ServerResourcePackLoader p0, murat.simv2.simulation.mirror.net.minecraft.world.level.storage.LevelStorage.Session p1) {
        return null;
    }

    public void checkBackupAndStart(murat.simv2.simulation.mirror.net.minecraft.world.level.storage.LevelStorage.Session p0, murat.simv2.simulation.mirror.net.minecraft.server.SaveLoader p1, murat.simv2.simulation.mirror.net.minecraft.resource.ResourcePackManager p2, java.lang.Runnable p3) {
    }

    public void createAndStart(java.lang.String p0, java.lang.Object p1, java.lang.Object p2, java.util.function.Function p3, murat.simv2.simulation.mirror.net.minecraft.client.gui.screen.Screen p4) {
    }

    public murat.simv2.simulation.mirror.net.minecraft.world.level.storage.LevelStorage.Session createSession(java.lang.String p0) {
        return null;
    }

    public com.mojang.datafixers.util.Pair loadForRecreation(murat.simv2.simulation.mirror.net.minecraft.world.level.storage.LevelStorage.Session p0) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.server.SaveLoader load(com.mojang.serialization.Dynamic p0, boolean p1, murat.simv2.simulation.mirror.net.minecraft.resource.ResourcePackManager p2) {
        return null;
    }

    public java.lang.Object load(java.lang.Object p0, java.lang.Object p1, java.lang.Object p2) {
        return null;
    }

    public void showBackupPromptScreen(murat.simv2.simulation.mirror.net.minecraft.world.level.storage.LevelStorage.Session p0, boolean p1, java.lang.Runnable p2, java.lang.Runnable p3) {
    }

    public java.util.concurrent.CompletableFuture showPackLoadFailureScreen() {
        return null;
    }

    public void startNewWorld(murat.simv2.simulation.mirror.net.minecraft.world.level.storage.LevelStorage.Session p0, java.lang.Object p1, java.lang.Object p2, java.lang.Object p3) {
    }

    public void start(java.lang.String p0, java.lang.Runnable p1) {
    }

    public void start(murat.simv2.simulation.mirror.net.minecraft.world.level.storage.LevelStorage.Session p0, com.mojang.serialization.Dynamic p1, boolean p2, java.lang.Runnable p3) {
    }

    public void start(murat.simv2.simulation.mirror.net.minecraft.world.level.storage.LevelStorage.Session p0, java.lang.Object p1, com.mojang.serialization.Dynamic p2, java.lang.Runnable p3) {
    }

    public void start(murat.simv2.simulation.mirror.net.minecraft.world.level.storage.LevelStorage.Session p0, java.lang.Runnable p1) {
    }

    public void start(murat.simv2.simulation.mirror.net.minecraft.world.level.storage.LevelStorage.Session p0, murat.simv2.simulation.mirror.net.minecraft.server.SaveLoader p1, murat.simv2.simulation.mirror.net.minecraft.client.resource.server.ServerResourcePackLoader p2, murat.simv2.simulation.mirror.net.minecraft.resource.ResourcePackManager p3, java.lang.Runnable p4) {
    }

    public void start(murat.simv2.simulation.mirror.net.minecraft.world.level.storage.LevelStorage.Session p0, murat.simv2.simulation.mirror.net.minecraft.server.SaveLoader p1, murat.simv2.simulation.mirror.net.minecraft.resource.ResourcePackManager p2) {
    }

    public void start(murat.simv2.simulation.mirror.net.minecraft.world.level.storage.LevelStorage.Session p0, murat.simv2.simulation.mirror.net.minecraft.server.SaveLoader p1, murat.simv2.simulation.mirror.net.minecraft.resource.ResourcePackManager p2, java.lang.Runnable p3) {
    }

    public static void tryLoad(murat.simv2.simulation.mirror.net.minecraft.client.MinecraftClient p0, java.lang.Object p1, com.mojang.serialization.Lifecycle p2, java.lang.Runnable p3, boolean p4) {
    }

    public IntegratedServerLoader() {
    }

    public static class CurrentSettings {
        public murat.simv2.simulation.mirror.net.minecraft.registry.Registry<murat.simv2.simulation.mirror.net.minecraft.world.dimension.DimensionOptions> existingDimensionRegistry;
        public java.lang.Object levelInfo;
        public java.lang.Object options;

        public CurrentSettings(java.lang.Object p0, java.lang.Object p1, murat.simv2.simulation.mirror.net.minecraft.registry.Registry p2) {
        }

        public boolean equals(java.lang.Object p0) {
            return false;
        }

        public murat.simv2.simulation.mirror.net.minecraft.registry.Registry existingDimensionRegistry() {
            return null;
        }

        public int hashCode() {
            return 0;
        }

        public java.lang.Object levelInfo() {
            return null;
        }

        public java.lang.Object options() {
            return null;
        }

        public java.lang.String toString() {
            return null;
        }

        public CurrentSettings() {
        }

    }

    // END GENERATED MIRROR NESTED STUBS
}
