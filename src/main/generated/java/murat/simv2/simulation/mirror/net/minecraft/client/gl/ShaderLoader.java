package murat.simv2.simulation.mirror.net.minecraft.client.gl;

// Generated mirror stub for simulation closure.
public class ShaderLoader {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static java.lang.String INCLUDE_PATH;
    public static org.slf4j.Logger LOGGER;
    public static java.lang.Object POST_EFFECT_FINDER;
    public static java.lang.String SHADERS_PATH;
    public murat.simv2.simulation.mirror.net.minecraft.client.gl.ShaderLoader.Cache cache;
    public static int field_53936;
    public java.util.function.Consumer<java.lang.Exception> onError;
    public murat.simv2.simulation.mirror.net.minecraft.client.texture.TextureManager textureManager;

    public ShaderLoader(murat.simv2.simulation.mirror.net.minecraft.client.texture.TextureManager p0, java.util.function.Consumer p1) {
    }

    public void apply(java.lang.Object p0, murat.simv2.simulation.mirror.net.minecraft.resource.ResourceManager p1, murat.simv2.simulation.mirror.net.minecraft.util.profiler.Profiler p2) {
    }

    public void apply(murat.simv2.simulation.mirror.net.minecraft.client.gl.ShaderLoader.Definitions p0, murat.simv2.simulation.mirror.net.minecraft.resource.ResourceManager p1, murat.simv2.simulation.mirror.net.minecraft.util.profiler.Profiler p2) {
    }

    public void close() {
    }

    public static java.lang.Object createImportProcessor(java.util.Map p0, murat.simv2.simulation.mirror.net.minecraft.util.Identifier p1) {
        return null;
    }

    public java.lang.String getName() {
        return null;
    }

    public java.lang.String getSource(murat.simv2.simulation.mirror.net.minecraft.util.Identifier p0, com.mojang.blaze3d.shaders.ShaderType p1) {
        return null;
    }

    public void handleError(java.lang.Exception p0) {
    }

    public static boolean isShaderSource(murat.simv2.simulation.mirror.net.minecraft.util.Identifier p0) {
        return false;
    }

    public static void loadPostEffect(murat.simv2.simulation.mirror.net.minecraft.util.Identifier p0, java.lang.Object p1, com.google.common.collect.ImmutableMap.Builder p2) {
    }

    public java.lang.Object loadPostEffect(murat.simv2.simulation.mirror.net.minecraft.util.Identifier p0, java.util.Set p1) {
        return null;
    }

    public static void loadShaderSource(murat.simv2.simulation.mirror.net.minecraft.util.Identifier p0, java.lang.Object p1, com.mojang.blaze3d.shaders.ShaderType p2, java.util.Map p3, com.google.common.collect.ImmutableMap.Builder p4) {
    }

    public java.lang.Object prepare(murat.simv2.simulation.mirror.net.minecraft.resource.ResourceManager p0, murat.simv2.simulation.mirror.net.minecraft.util.profiler.Profiler p1) {
        return null;
    }

    public java.util.concurrent.CompletableFuture reload(java.lang.Object p0, murat.simv2.simulation.mirror.net.minecraft.resource.ResourceManager p1, java.util.concurrent.Executor p2, java.util.concurrent.Executor p3) {
        return null;
    }

    public ShaderLoader() {
    }

    public static class Cache extends java.lang.Object {
        public murat.simv2.simulation.mirror.net.minecraft.client.gl.ShaderLoader.Definitions definitions;
        public boolean errorHandled;
        public murat.simv2.simulation.mirror.net.minecraft.client.gl.ShaderLoader field_54021;
        public java.util.Map<murat.simv2.simulation.mirror.net.minecraft.util.Identifier, java.util.Optional<java.lang.Object>> postEffectProcessors;

        public Cache(murat.simv2.simulation.mirror.net.minecraft.client.gl.ShaderLoader p0, murat.simv2.simulation.mirror.net.minecraft.client.gl.ShaderLoader.Definitions p1) {
        }

        public void close() {
        }

        public java.lang.Object getOrLoadProcessor(murat.simv2.simulation.mirror.net.minecraft.util.Identifier p0, java.util.Set p1) {
            return null;
        }

        public java.lang.String getSource(murat.simv2.simulation.mirror.net.minecraft.util.Identifier p0, com.mojang.blaze3d.shaders.ShaderType p1) {
            return null;
        }

        public java.lang.Object loadProcessor(murat.simv2.simulation.mirror.net.minecraft.util.Identifier p0, java.util.Set p1) {
            return null;
        }

        public Cache() {
        }

    }

    public static class Definitions {
        public static murat.simv2.simulation.mirror.net.minecraft.client.gl.ShaderLoader.Definitions EMPTY;
        public java.util.Map<murat.simv2.simulation.mirror.net.minecraft.util.Identifier, java.lang.Object> postChains;
        public java.util.Map<murat.simv2.simulation.mirror.net.minecraft.client.gl.ShaderLoader.ShaderSourceKey, java.lang.String> shaderSources;

        public Definitions(java.util.Map p0, java.util.Map p1) {
        }

        public boolean equals(java.lang.Object p0) {
            return false;
        }

        public int hashCode() {
            return 0;
        }

        public java.util.Map postChains() {
            return null;
        }

        public java.util.Map shaderSources() {
            return null;
        }

        public java.lang.String toString() {
            return null;
        }

        public Definitions() {
        }

    }

    public static class LoadException extends java.lang.Exception {
        public LoadException(java.lang.String p0) {
        }

        public LoadException() {
        }

    }

    public static class ShaderSourceKey {
        public murat.simv2.simulation.mirror.net.minecraft.util.Identifier id;
        public com.mojang.blaze3d.shaders.ShaderType type;

        public ShaderSourceKey(murat.simv2.simulation.mirror.net.minecraft.util.Identifier p0, com.mojang.blaze3d.shaders.ShaderType p1) {
        }

        public boolean equals(java.lang.Object p0) {
            return false;
        }

        public int hashCode() {
            return 0;
        }

        public murat.simv2.simulation.mirror.net.minecraft.util.Identifier id() {
            return null;
        }

        public java.lang.String toString() {
            return null;
        }

        public com.mojang.blaze3d.shaders.ShaderType type() {
            return null;
        }

        public ShaderSourceKey() {
        }

    }

    // END GENERATED MIRROR NESTED STUBS
}
