package murat.simv2.simulation.mirror.net.minecraft.world.dimension;

// Generated mirror stub for simulation closure.
public class DimensionType {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static com.mojang.serialization.Codec<murat.simv2.simulation.mirror.net.minecraft.world.dimension.DimensionType> CODEC;
    public static int MAX_COLUMN_HEIGHT;
    public static int MAX_HEIGHT;
    public static int MIN_HEIGHT;
    public static float[] MOON_SIZES;
    public static murat.simv2.simulation.mirror.net.minecraft.network.codec.PacketCodec<java.lang.Object, murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry<murat.simv2.simulation.mirror.net.minecraft.world.dimension.DimensionType>> PACKET_CODEC;
    public static com.mojang.serialization.Codec<murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry<murat.simv2.simulation.mirror.net.minecraft.world.dimension.DimensionType>> REGISTRY_CODEC;
    public static int SIZE_BITS_Y;
    public float ambientLight;
    public boolean bedWorks;
    public double coordinateScale;
    public murat.simv2.simulation.mirror.net.minecraft.util.Identifier effects;
    public static int field_31440;
    public static int field_33411;
    public static int field_35478;
    public static int field_35479;
    public java.util.OptionalLong fixedTime;
    public boolean hasCeiling;
    public boolean hasSkyLight;
    public int height;
    public murat.simv2.simulation.mirror.net.minecraft.registry.tag.TagKey<murat.simv2.simulation.mirror.net.minecraft.block.Block> infiniburn;
    public int logicalHeight;
    public int minY;
    public murat.simv2.simulation.mirror.net.minecraft.world.dimension.DimensionType.MonsterSettings monsterSettings;
    public boolean natural;
    public boolean respawnAnchorWorks;
    public boolean ultrawarm;

    public DimensionType(java.util.OptionalLong p0, boolean p1, boolean p2, boolean p3, boolean p4, double p5, boolean p6, boolean p7, int p8, int p9, int p10, murat.simv2.simulation.mirror.net.minecraft.registry.tag.TagKey p11, murat.simv2.simulation.mirror.net.minecraft.util.Identifier p12, float p13, murat.simv2.simulation.mirror.net.minecraft.world.dimension.DimensionType.MonsterSettings p14) {
    }

    public float ambientLight() {
        return 0.0F;
    }

    public boolean bedWorks() {
        return false;
    }

    public double coordinateScale() {
        return 0.0D;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.Identifier effects() {
        return null;
    }

    public boolean equals(java.lang.Object p0) {
        return false;
    }

    public java.util.OptionalLong fixedTime() {
        return null;
    }

    public static double getCoordinateScaleFactor(murat.simv2.simulation.mirror.net.minecraft.world.dimension.DimensionType p0, murat.simv2.simulation.mirror.net.minecraft.world.dimension.DimensionType p1) {
        return 0.0D;
    }

    public int getMoonPhase(long p0) {
        return 0;
    }

    public static java.nio.file.Path getSaveDirectory(murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey p0, java.nio.file.Path p1) {
        return null;
    }

    public float getSkyAngle(long p0) {
        return 0.0F;
    }

    public boolean hasCeiling() {
        return false;
    }

    public boolean hasFixedTime() {
        return false;
    }

    public boolean hasRaids() {
        return false;
    }

    public boolean hasSkyLight() {
        return false;
    }

    public int hashCode() {
        return 0;
    }

    public int height() {
        return 0;
    }

    public murat.simv2.simulation.mirror.net.minecraft.registry.tag.TagKey infiniburn() {
        return null;
    }

    public int logicalHeight() {
        return 0;
    }

    public int minY() {
        return 0;
    }

    public murat.simv2.simulation.mirror.net.minecraft.world.dimension.DimensionType.MonsterSettings monsterSettings() {
        return null;
    }

    public int monsterSpawnBlockLightLimit() {
        return 0;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.intprovider.IntProvider monsterSpawnLightTest() {
        return null;
    }

    public boolean natural() {
        return false;
    }

    public boolean piglinSafe() {
        return false;
    }

    public boolean respawnAnchorWorks() {
        return false;
    }

    public java.lang.String toString() {
        return null;
    }

    public boolean ultrawarm() {
        return false;
    }

    public static com.mojang.serialization.DataResult worldFromDimensionNbt(com.mojang.serialization.Dynamic p0) {
        return null;
    }

    public DimensionType() {
    }

    public static class MonsterSettings {
        public static com.mojang.serialization.MapCodec<murat.simv2.simulation.mirror.net.minecraft.world.dimension.DimensionType.MonsterSettings> CODEC;
        public boolean hasRaids;
        public int monsterSpawnBlockLightLimit;
        public murat.simv2.simulation.mirror.net.minecraft.util.math.intprovider.IntProvider monsterSpawnLightTest;
        public boolean piglinSafe;

        public MonsterSettings(boolean p0, boolean p1, murat.simv2.simulation.mirror.net.minecraft.util.math.intprovider.IntProvider p2, int p3) {
        }

        public boolean equals(java.lang.Object p0) {
            return false;
        }

        public boolean hasRaids() {
            return false;
        }

        public int hashCode() {
            return 0;
        }

        public int monsterSpawnBlockLightLimit() {
            return 0;
        }

        public murat.simv2.simulation.mirror.net.minecraft.util.math.intprovider.IntProvider monsterSpawnLightTest() {
            return null;
        }

        public boolean piglinSafe() {
            return false;
        }

        public java.lang.String toString() {
            return null;
        }

        public MonsterSettings() {
        }

    }

    // END GENERATED MIRROR NESTED STUBS
}
