package murat.simv2.simulation.mirror.net.minecraft.client;

// Generated mirror stub for simulation closure.
public class QuickPlayLogger extends java.lang.Object {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static com.google.gson.Gson GSON;
    public static org.slf4j.Logger LOGGER;
    public static murat.simv2.simulation.mirror.net.minecraft.client.QuickPlayLogger NOOP;
    public java.nio.file.Path path;
    public murat.simv2.simulation.mirror.net.minecraft.client.QuickPlayLogger.QuickPlayWorld world;

    public QuickPlayLogger(java.lang.String p0) {
    }

    public static murat.simv2.simulation.mirror.net.minecraft.client.QuickPlayLogger create(java.lang.String p0) {
        return null;
    }

    public void save(murat.simv2.simulation.mirror.net.minecraft.client.MinecraftClient p0) {
    }

    public void setWorld(murat.simv2.simulation.mirror.net.minecraft.client.QuickPlayLogger.WorldType p0, java.lang.String p1, java.lang.String p2) {
    }

    public QuickPlayLogger() {
    }

    public static class Log {
        public static com.mojang.serialization.Codec<murat.simv2.simulation.mirror.net.minecraft.client.QuickPlayLogger.Log> CODEC;
        public murat.simv2.simulation.mirror.net.minecraft.world.GameMode gameMode;
        public java.time.Instant lastPlayedTime;
        public murat.simv2.simulation.mirror.net.minecraft.client.QuickPlayLogger.QuickPlayWorld quickPlayWorld;

        public Log(murat.simv2.simulation.mirror.net.minecraft.client.QuickPlayLogger.QuickPlayWorld p0, java.time.Instant p1, murat.simv2.simulation.mirror.net.minecraft.world.GameMode p2) {
        }

        public boolean equals(java.lang.Object p0) {
            return false;
        }

        public murat.simv2.simulation.mirror.net.minecraft.world.GameMode gameMode() {
            return null;
        }

        public int hashCode() {
            return 0;
        }

        public java.time.Instant lastPlayedTime() {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.client.QuickPlayLogger.QuickPlayWorld quickPlayWorld() {
            return null;
        }

        public java.lang.String toString() {
            return null;
        }

        public Log() {
        }

    }

    public static class QuickPlayWorld {
        public static com.mojang.serialization.MapCodec<murat.simv2.simulation.mirror.net.minecraft.client.QuickPlayLogger.QuickPlayWorld> CODEC;
        public java.lang.String id;
        public java.lang.String name;
        public murat.simv2.simulation.mirror.net.minecraft.client.QuickPlayLogger.WorldType type;

        public QuickPlayWorld(murat.simv2.simulation.mirror.net.minecraft.client.QuickPlayLogger.WorldType p0, java.lang.String p1, java.lang.String p2) {
        }

        public boolean equals(java.lang.Object p0) {
            return false;
        }

        public int hashCode() {
            return 0;
        }

        public java.lang.String id() {
            return null;
        }

        public java.lang.String name() {
            return null;
        }

        public java.lang.String toString() {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.client.QuickPlayLogger.WorldType type() {
            return null;
        }

        public QuickPlayWorld() {
        }

    }

    public static class WorldType implements murat.simv2.simulation.mirror.net.minecraft.util.StringIdentifiable {
        public static com.mojang.serialization.Codec<murat.simv2.simulation.mirror.net.minecraft.client.QuickPlayLogger.WorldType> CODEC;
        public static murat.simv2.simulation.mirror.net.minecraft.client.QuickPlayLogger.WorldType MULTIPLAYER;
        public static murat.simv2.simulation.mirror.net.minecraft.client.QuickPlayLogger.WorldType REALMS;
        public static murat.simv2.simulation.mirror.net.minecraft.client.QuickPlayLogger.WorldType SINGLEPLAYER;
        public static murat.simv2.simulation.mirror.net.minecraft.client.QuickPlayLogger.WorldType[] field_44573;
        public java.lang.String id;

        public WorldType(java.lang.String p0, int p1, java.lang.String p2) {
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

        public static murat.simv2.simulation.mirror.net.minecraft.client.QuickPlayLogger.WorldType valueOf(java.lang.String p0) {
            return null;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.client.QuickPlayLogger.WorldType[] values() {
            return null;
        }

        public WorldType() {
        }

    }

    // END GENERATED MIRROR NESTED STUBS
}
