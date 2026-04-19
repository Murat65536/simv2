package murat.simv2.simulation.mirror.net.minecraft.client.font;

// Generated mirror stub for simulation closure.
public class FontManager extends java.lang.Object {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static java.lang.Object FINDER;
    public static java.lang.String FONTS_JSON;
    public static com.google.gson.Gson GSON;
    public static org.slf4j.Logger LOGGER;
    public static murat.simv2.simulation.mirror.net.minecraft.util.Identifier MISSING_STORAGE_ID;
    public java.lang.Object currentStorage;
    public java.util.Map<murat.simv2.simulation.mirror.net.minecraft.util.Identifier, java.lang.Object> fontStorages;
    public java.util.List<java.lang.Object> fonts;
    public java.lang.Object missingStorage;
    public murat.simv2.simulation.mirror.net.minecraft.client.texture.TextureManager textureManager;

    public FontManager(murat.simv2.simulation.mirror.net.minecraft.client.texture.TextureManager p0) {
    }

    public void close() {
    }

    public murat.simv2.simulation.mirror.net.minecraft.client.font.TextRenderer createAdvanceValidatingTextRenderer() {
        return null;
    }

    public static java.lang.Object createEmptyFont() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.client.font.TextRenderer createTextRenderer() {
        return null;
    }

    public static java.util.Set getActiveFilters(murat.simv2.simulation.mirror.net.minecraft.client.option.GameOptions p0) {
        return null;
    }

    public java.lang.String getName() {
        return null;
    }

    public java.util.Map getRequiredFontProviders(java.util.List p0) {
        return null;
    }

    public java.lang.Object getStorageInternal(murat.simv2.simulation.mirror.net.minecraft.util.Identifier p0) {
        return null;
    }

    public java.lang.Object getStorage(murat.simv2.simulation.mirror.net.minecraft.util.Identifier p0) {
        return null;
    }

    public void insertFont(java.util.List p0, java.lang.Object p1) {
    }

    public static java.util.List loadFontProviders(java.util.List p0, murat.simv2.simulation.mirror.net.minecraft.util.Identifier p1) {
        return null;
    }

    public java.util.concurrent.CompletableFuture loadIndex(murat.simv2.simulation.mirror.net.minecraft.resource.ResourceManager p0, java.util.concurrent.Executor p1) {
        return null;
    }

    public java.util.concurrent.CompletableFuture load(murat.simv2.simulation.mirror.net.minecraft.client.font.FontManager.FontKey p0, java.lang.Object p1, murat.simv2.simulation.mirror.net.minecraft.resource.ResourceManager p2, java.util.concurrent.Executor p3) {
        return null;
    }

    public java.util.concurrent.CompletableFuture reload(java.lang.Object p0, murat.simv2.simulation.mirror.net.minecraft.resource.ResourceManager p1, java.util.concurrent.Executor p2, java.util.concurrent.Executor p3) {
        return null;
    }

    public void reload(murat.simv2.simulation.mirror.net.minecraft.client.font.FontManager.ProviderIndex p0, murat.simv2.simulation.mirror.net.minecraft.util.profiler.Profiler p1) {
    }

    public void setActiveFilters(murat.simv2.simulation.mirror.net.minecraft.client.option.GameOptions p0) {
    }

    public FontManager() {
    }

    public static class Builder {
        public java.lang.Object filter;
        public murat.simv2.simulation.mirror.net.minecraft.client.font.FontManager.FontKey id;
        public com.mojang.datafixers.util.Either<java.util.concurrent.CompletableFuture<java.util.Optional<java.lang.Object>>, murat.simv2.simulation.mirror.net.minecraft.util.Identifier> result;

        public Builder(murat.simv2.simulation.mirror.net.minecraft.client.font.FontManager.FontKey p0, java.lang.Object p1, com.mojang.datafixers.util.Either p2) {
        }

        public java.lang.Object applyFilter(java.lang.Object p0) {
            return null;
        }

        public java.util.Optional build(java.util.function.Function p0) {
            return null;
        }

        public boolean equals(java.lang.Object p0) {
            return false;
        }

        public java.lang.Object filter() {
            return null;
        }

        public int hashCode() {
            return 0;
        }

        public murat.simv2.simulation.mirror.net.minecraft.client.font.FontManager.FontKey id() {
            return null;
        }

        public com.mojang.datafixers.util.Either result() {
            return null;
        }

        public java.lang.String toString() {
            return null;
        }

        public Builder() {
        }

    }

    public static class FontEntry {
        public java.util.List<murat.simv2.simulation.mirror.net.minecraft.client.font.FontManager.Builder> builders;
        public java.util.Set<murat.simv2.simulation.mirror.net.minecraft.util.Identifier> dependencies;
        public murat.simv2.simulation.mirror.net.minecraft.util.Identifier fontId;

        public FontEntry(murat.simv2.simulation.mirror.net.minecraft.util.Identifier p0) {
        }

        public FontEntry(murat.simv2.simulation.mirror.net.minecraft.util.Identifier p0, java.util.List p1, java.util.Set p2) {
        }

        public void addBuilder(murat.simv2.simulation.mirror.net.minecraft.client.font.FontManager.FontKey p0, java.lang.Object p1, java.util.concurrent.CompletableFuture p2) {
        }

        public void addReferenceBuilder(murat.simv2.simulation.mirror.net.minecraft.client.font.FontManager.FontKey p0, java.lang.Object p1, java.lang.Object p2) {
        }

        public java.util.List builders() {
            return null;
        }

        public java.util.Set dependencies() {
            return null;
        }

        public boolean equals(java.lang.Object p0) {
            return false;
        }

        public murat.simv2.simulation.mirror.net.minecraft.util.Identifier fontId() {
            return null;
        }

        public void forDependencies(java.util.function.Consumer p0) {
        }

        public void forOptionalDependencies(java.util.function.Consumer p0) {
        }

        public java.util.stream.Stream getImmediateProviders() {
            return null;
        }

        public java.util.Optional getRequiredFontProviders(java.util.function.Function p0) {
            return null;
        }

        public int hashCode() {
            return 0;
        }

        public java.lang.String toString() {
            return null;
        }

        public FontEntry() {
        }

    }

    public static class FontKey {
        public murat.simv2.simulation.mirror.net.minecraft.util.Identifier fontId;
        public int index;
        public java.lang.String pack;

        public FontKey(murat.simv2.simulation.mirror.net.minecraft.util.Identifier p0, java.lang.String p1, int p2) {
        }

        public boolean equals(java.lang.Object p0) {
            return false;
        }

        public murat.simv2.simulation.mirror.net.minecraft.util.Identifier fontId() {
            return null;
        }

        public int hashCode() {
            return 0;
        }

        public int index() {
            return 0;
        }

        public java.lang.String pack() {
            return null;
        }

        public java.lang.String toString() {
            return null;
        }

        public FontKey() {
        }

    }

    public static class ProviderIndex {
        public java.util.List<java.lang.Object> allProviders;
        public java.util.Map<murat.simv2.simulation.mirror.net.minecraft.util.Identifier, java.util.List<java.lang.Object>> fontSets;

        public ProviderIndex(java.util.Map p0, java.util.List p1) {
        }

        public java.util.List allProviders() {
            return null;
        }

        public boolean equals(java.lang.Object p0) {
            return false;
        }

        public java.util.Map fontSets() {
            return null;
        }

        public int hashCode() {
            return 0;
        }

        public java.lang.String toString() {
            return null;
        }

        public ProviderIndex() {
        }

    }

    public static class Providers {
        public static com.mojang.serialization.Codec<murat.simv2.simulation.mirror.net.minecraft.client.font.FontManager.Providers> CODEC;
        public java.util.List<java.lang.Object> providers;

        public Providers(java.util.List p0) {
        }

        public boolean equals(java.lang.Object p0) {
            return false;
        }

        public int hashCode() {
            return 0;
        }

        public java.util.List providers() {
            return null;
        }

        public java.lang.String toString() {
            return null;
        }

        public Providers() {
        }

    }

    // END GENERATED MIRROR NESTED STUBS
}
