package murat.simv2.simulation.mirror.net.minecraft.world.biome;

// Generated mirror stub for simulation closure.
public class Biome extends java.lang.Object {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static com.mojang.serialization.Codec<murat.simv2.simulation.mirror.net.minecraft.world.biome.Biome> CODEC;
    public static java.lang.Object FOLIAGE_NOISE;
    public static java.lang.Object FROZEN_OCEAN_NOISE;
    public static int MAX_TEMPERATURE_CACHE_SIZE;
    public static com.mojang.serialization.Codec<murat.simv2.simulation.mirror.net.minecraft.world.biome.Biome> NETWORK_CODEC;
    public static com.mojang.serialization.Codec<murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry<murat.simv2.simulation.mirror.net.minecraft.world.biome.Biome>> REGISTRY_CODEC;
    public static com.mojang.serialization.Codec<murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntryList<murat.simv2.simulation.mirror.net.minecraft.world.biome.Biome>> REGISTRY_ENTRY_LIST_CODEC;
    public static java.lang.Object TEMPERATURE_NOISE;
    public java.lang.Object effects;
    public java.lang.Object generationSettings;
    public java.lang.Object spawnSettings;
    public java.lang.ThreadLocal<it.unimi.dsi.fastutil.longs.Long2FloatLinkedOpenHashMap> temperatureCache;
    public murat.simv2.simulation.mirror.net.minecraft.world.biome.Biome.Weather weather;

    public Biome(murat.simv2.simulation.mirror.net.minecraft.world.biome.Biome.Weather p0, java.lang.Object p1, java.lang.Object p2, java.lang.Object p3) {
    }

    public boolean canSetIce(murat.simv2.simulation.mirror.net.minecraft.world.WorldView p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1) {
        return false;
    }

    public boolean canSetIce(murat.simv2.simulation.mirror.net.minecraft.world.WorldView p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1, boolean p2) {
        return false;
    }

    public boolean canSetSnow(murat.simv2.simulation.mirror.net.minecraft.world.WorldView p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1) {
        return false;
    }

    public float computeTemperature(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, int p1) {
        return 0.0F;
    }

    public boolean doesNotSnow(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, int p1) {
        return false;
    }

    public java.util.Optional getAdditionsSound() {
        return null;
    }

    public int getDefaultDryFoliageColor() {
        return 0;
    }

    public int getDefaultFoliageColor() {
        return 0;
    }

    public int getDefaultGrassColor() {
        return 0;
    }

    public int getDryFoliageColor() {
        return 0;
    }

    public java.lang.Object getEffects() {
        return null;
    }

    public int getFogColor() {
        return 0;
    }

    public int getFoliageColor() {
        return 0;
    }

    public java.lang.Object getGenerationSettings() {
        return null;
    }

    public int getGrassColorAt(double p0, double p1) {
        return 0;
    }

    public int getGrassColor() {
        return 0;
    }

    public java.util.Optional getLoopSound() {
        return null;
    }

    public java.util.Optional getMoodSound() {
        return null;
    }

    public float getMusicVolume() {
        return 0.0F;
    }

    public java.util.Optional getMusic() {
        return null;
    }

    public java.util.Optional getParticleConfig() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.world.biome.Biome.Precipitation getPrecipitation(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, int p1) {
        return null;
    }

    public int getSkyColor() {
        return 0;
    }

    public java.lang.Object getSpawnSettings() {
        return null;
    }

    public float getTemperature() {
        return 0.0F;
    }

    public float getTemperature(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, int p1) {
        return 0.0F;
    }

    public int getWaterColor() {
        return 0;
    }

    public int getWaterFogColor() {
        return 0;
    }

    public boolean hasPrecipitation() {
        return false;
    }

    public boolean isCold(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, int p1) {
        return false;
    }

    public boolean shouldGenerateLowerFrozenOceanSurface(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, int p1) {
        return false;
    }

    public Biome() {
    }

    public static class Builder extends java.lang.Object {
        public java.lang.Float downfall;
        public java.lang.Object generationSettings;
        public boolean precipitation;
        public java.lang.Object spawnSettings;
        public java.lang.Object specialEffects;
        public java.lang.Float temperature;
        public murat.simv2.simulation.mirror.net.minecraft.world.biome.Biome.TemperatureModifier temperatureModifier;

        public Builder() {
        }

        public murat.simv2.simulation.mirror.net.minecraft.world.biome.Biome build() {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.world.biome.Biome.Builder downfall(float p0) {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.world.biome.Biome.Builder effects(java.lang.Object p0) {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.world.biome.Biome.Builder generationSettings(java.lang.Object p0) {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.world.biome.Biome.Builder precipitation(boolean p0) {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.world.biome.Biome.Builder spawnSettings(java.lang.Object p0) {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.world.biome.Biome.Builder temperatureModifier(murat.simv2.simulation.mirror.net.minecraft.world.biome.Biome.TemperatureModifier p0) {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.world.biome.Biome.Builder temperature(float p0) {
            return null;
        }

        public java.lang.String toString() {
            return null;
        }

    }

    public static class Precipitation implements murat.simv2.simulation.mirror.net.minecraft.util.StringIdentifiable {
        public static com.mojang.serialization.Codec<murat.simv2.simulation.mirror.net.minecraft.world.biome.Biome.Precipitation> CODEC;
        public static murat.simv2.simulation.mirror.net.minecraft.world.biome.Biome.Precipitation NONE;
        public static murat.simv2.simulation.mirror.net.minecraft.world.biome.Biome.Precipitation RAIN;
        public static murat.simv2.simulation.mirror.net.minecraft.world.biome.Biome.Precipitation SNOW;
        public static murat.simv2.simulation.mirror.net.minecraft.world.biome.Biome.Precipitation[] field_9386;
        public java.lang.String name;

        public Precipitation(java.lang.String p0, int p1, java.lang.String p2) {
        }

        public java.lang.String asString() {
            return null;
        }

        public static com.mojang.serialization.Codec createBasicCodec(java.util.function.Supplier p0) {
            return null;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.util.StringIdentifiable.EnumCodec createCodec(java.util.function.Supplier p0) {
            return null;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.util.StringIdentifiable.EnumCodec createCodec(java.util.function.Supplier p0, java.util.function.Function p1) {
            return null;
        }

        public static java.util.function.Function createMapper(murat.simv2.simulation.mirror.net.minecraft.util.StringIdentifiable[] p0, java.util.function.Function p1) {
            return null;
        }

        public int ordinal() {
            return 0;
        }

        public static com.mojang.serialization.Keyable toKeyable(murat.simv2.simulation.mirror.net.minecraft.util.StringIdentifiable[] p0) {
            return null;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.world.biome.Biome.Precipitation valueOf(java.lang.String p0) {
            return null;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.world.biome.Biome.Precipitation[] values() {
            return null;
        }

        public Precipitation() {
        }

    }

    public static class TemperatureModifier implements murat.simv2.simulation.mirror.net.minecraft.util.StringIdentifiable {
        public static com.mojang.serialization.Codec<murat.simv2.simulation.mirror.net.minecraft.world.biome.Biome.TemperatureModifier> CODEC;
        public static murat.simv2.simulation.mirror.net.minecraft.world.biome.Biome.TemperatureModifier FROZEN;
        public static murat.simv2.simulation.mirror.net.minecraft.world.biome.Biome.TemperatureModifier NONE;
        public static murat.simv2.simulation.mirror.net.minecraft.world.biome.Biome.TemperatureModifier[] field_26412;
        public java.lang.String name;

        public TemperatureModifier(java.lang.String p0, int p1, java.lang.String p2) {
        }

        public java.lang.String asString() {
            return null;
        }

        public static com.mojang.serialization.Codec createBasicCodec(java.util.function.Supplier p0) {
            return null;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.util.StringIdentifiable.EnumCodec createCodec(java.util.function.Supplier p0) {
            return null;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.util.StringIdentifiable.EnumCodec createCodec(java.util.function.Supplier p0, java.util.function.Function p1) {
            return null;
        }

        public static java.util.function.Function createMapper(murat.simv2.simulation.mirror.net.minecraft.util.StringIdentifiable[] p0, java.util.function.Function p1) {
            return null;
        }

        public float getModifiedTemperature(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, float p1) {
            return 0.0F;
        }

        public java.lang.String getName() {
            return null;
        }

        public int ordinal() {
            return 0;
        }

        public static com.mojang.serialization.Keyable toKeyable(murat.simv2.simulation.mirror.net.minecraft.util.StringIdentifiable[] p0) {
            return null;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.world.biome.Biome.TemperatureModifier valueOf(java.lang.String p0) {
            return null;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.world.biome.Biome.TemperatureModifier[] values() {
            return null;
        }

        public TemperatureModifier() {
        }

    }

    public static class Weather {
        public static com.mojang.serialization.MapCodec<murat.simv2.simulation.mirror.net.minecraft.world.biome.Biome.Weather> CODEC;
        public float downfall;
        public boolean hasPrecipitation;
        public float temperature;
        public murat.simv2.simulation.mirror.net.minecraft.world.biome.Biome.TemperatureModifier temperatureModifier;

        public Weather(boolean p0, float p1, murat.simv2.simulation.mirror.net.minecraft.world.biome.Biome.TemperatureModifier p2, float p3) {
        }

        public float downfall() {
            return 0.0F;
        }

        public boolean equals(java.lang.Object p0) {
            return false;
        }

        public boolean hasPrecipitation() {
            return false;
        }

        public int hashCode() {
            return 0;
        }

        public murat.simv2.simulation.mirror.net.minecraft.world.biome.Biome.TemperatureModifier temperatureModifier() {
            return null;
        }

        public float temperature() {
            return 0.0F;
        }

        public java.lang.String toString() {
            return null;
        }

        public Weather() {
        }

    }

    // END GENERATED MIRROR NESTED STUBS
}
