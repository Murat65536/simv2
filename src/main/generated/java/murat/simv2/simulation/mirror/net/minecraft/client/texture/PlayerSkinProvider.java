package murat.simv2.simulation.mirror.net.minecraft.client.texture;

// Generated mirror stub for simulation closure.
public class PlayerSkinProvider extends java.lang.Object {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static org.slf4j.Logger LOGGER;
    public com.google.common.cache.LoadingCache<murat.simv2.simulation.mirror.net.minecraft.client.texture.PlayerSkinProvider.Key, java.util.concurrent.CompletableFuture<java.util.Optional<murat.simv2.simulation.mirror.net.minecraft.client.util.SkinTextures>>> cache;
    public murat.simv2.simulation.mirror.net.minecraft.client.texture.PlayerSkinProvider.FileCache capeCache;
    public murat.simv2.simulation.mirror.net.minecraft.client.texture.PlayerSkinProvider.FileCache elytraCache;
    public com.mojang.authlib.minecraft.MinecraftSessionService sessionService;
    public murat.simv2.simulation.mirror.net.minecraft.client.texture.PlayerSkinProvider.FileCache skinCache;

    public PlayerSkinProvider(java.nio.file.Path p0, com.mojang.authlib.minecraft.MinecraftSessionService p1, java.util.concurrent.Executor p2) {
    }

    public java.util.concurrent.CompletableFuture fetchSkinTextures(com.mojang.authlib.GameProfile p0) {
        return null;
    }

    public java.util.concurrent.CompletableFuture fetchSkinTextures(java.util.UUID p0, com.mojang.authlib.minecraft.MinecraftProfileTextures p1) {
        return null;
    }

    public java.util.function.Supplier getSkinTexturesSupplier(com.mojang.authlib.GameProfile p0) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.client.util.SkinTextures getSkinTextures(com.mojang.authlib.GameProfile p0) {
        return null;
    }

    public PlayerSkinProvider() {
    }

    public static class FileCache extends java.lang.Object {
        public java.nio.file.Path directory;
        public java.util.Map<java.lang.String, java.util.concurrent.CompletableFuture<murat.simv2.simulation.mirror.net.minecraft.util.Identifier>> hashToTexture;
        public com.mojang.authlib.minecraft.MinecraftProfileTexture.Type type;

        public FileCache(java.nio.file.Path p0, com.mojang.authlib.minecraft.MinecraftProfileTexture.Type p1) {
        }

        public murat.simv2.simulation.mirror.net.minecraft.util.Identifier getTexturePath(java.lang.String p0) {
            return null;
        }

        public java.util.concurrent.CompletableFuture get(com.mojang.authlib.minecraft.MinecraftProfileTexture p0) {
            return null;
        }

        public java.util.concurrent.CompletableFuture store(com.mojang.authlib.minecraft.MinecraftProfileTexture p0) {
            return null;
        }

        public FileCache() {
        }

    }

    public static class Key {
        public com.mojang.authlib.properties.Property packedTextures;
        public java.util.UUID profileId;

        public Key(java.util.UUID p0, com.mojang.authlib.properties.Property p1) {
        }

        public boolean equals(java.lang.Object p0) {
            return false;
        }

        public int hashCode() {
            return 0;
        }

        public com.mojang.authlib.properties.Property packedTextures() {
            return null;
        }

        public java.util.UUID profileId() {
            return null;
        }

        public java.lang.String toString() {
            return null;
        }

        public Key() {
        }

    }

    // END GENERATED MIRROR NESTED STUBS
}
