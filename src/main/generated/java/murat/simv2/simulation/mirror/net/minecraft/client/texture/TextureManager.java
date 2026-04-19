package murat.simv2.simulation.mirror.net.minecraft.client.texture;

// Generated mirror stub for simulation closure.
public class TextureManager extends java.lang.Object {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static org.slf4j.Logger LOGGER;
    public static murat.simv2.simulation.mirror.net.minecraft.util.Identifier MISSING_IDENTIFIER;
    public murat.simv2.simulation.mirror.net.minecraft.resource.ResourceManager resourceContainer;
    public java.util.Map<murat.simv2.simulation.mirror.net.minecraft.util.Identifier, java.lang.Object> textures;
    public java.util.Set<java.lang.Object> tickListeners;

    public TextureManager(murat.simv2.simulation.mirror.net.minecraft.resource.ResourceManager p0) {
    }

    public void closeTexture(murat.simv2.simulation.mirror.net.minecraft.util.Identifier p0, java.lang.Object p1) {
    }

    public void close() {
    }

    public void destroyTexture(murat.simv2.simulation.mirror.net.minecraft.util.Identifier p0) {
    }

    public void dumpDynamicTextures(java.nio.file.Path p0) {
    }

    public java.lang.String getName() {
        return null;
    }

    public java.lang.Object getTexture(murat.simv2.simulation.mirror.net.minecraft.util.Identifier p0) {
        return null;
    }

    public static java.lang.Object loadTexture(murat.simv2.simulation.mirror.net.minecraft.resource.ResourceManager p0, murat.simv2.simulation.mirror.net.minecraft.util.Identifier p1, java.lang.Object p2) {
        return null;
    }

    public java.lang.Object loadTexture(murat.simv2.simulation.mirror.net.minecraft.util.Identifier p0, java.lang.Object p1) {
        return null;
    }

    public void registerTexture(murat.simv2.simulation.mirror.net.minecraft.util.Identifier p0) {
    }

    public void registerTexture(murat.simv2.simulation.mirror.net.minecraft.util.Identifier p0, java.lang.Object p1) {
    }

    public static murat.simv2.simulation.mirror.net.minecraft.client.texture.TextureManager.ReloadedTexture reloadTexture(murat.simv2.simulation.mirror.net.minecraft.resource.ResourceManager p0, murat.simv2.simulation.mirror.net.minecraft.util.Identifier p1, java.lang.Object p2, java.util.concurrent.Executor p3) {
        return null;
    }

    public java.util.concurrent.CompletableFuture reload(java.lang.Object p0, murat.simv2.simulation.mirror.net.minecraft.resource.ResourceManager p1, java.util.concurrent.Executor p2, java.util.concurrent.Executor p3) {
        return null;
    }

    public void tick() {
    }

    public TextureManager() {
    }

    public static class ReloadedTexture {
        public java.util.concurrent.CompletableFuture<java.lang.Object> newContents;
        public java.lang.Object texture;

        public ReloadedTexture(java.lang.Object p0, java.util.concurrent.CompletableFuture p1) {
        }

        public boolean equals(java.lang.Object p0) {
            return false;
        }

        public int hashCode() {
            return 0;
        }

        public java.util.concurrent.CompletableFuture newContents() {
            return null;
        }

        public java.lang.Object texture() {
            return null;
        }

        public java.lang.String toString() {
            return null;
        }

        public ReloadedTexture() {
        }

    }

    // END GENERATED MIRROR NESTED STUBS
}
