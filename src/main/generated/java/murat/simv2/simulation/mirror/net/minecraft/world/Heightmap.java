package murat.simv2.simulation.mirror.net.minecraft.world;

// Generated mirror stub for simulation closure.
public class Heightmap extends java.lang.Object {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static org.slf4j.Logger LOGGER;
    public static java.util.function.Predicate<murat.simv2.simulation.mirror.net.minecraft.block.BlockState> NOT_AIR;
    public static java.util.function.Predicate<murat.simv2.simulation.mirror.net.minecraft.block.BlockState> SUFFOCATES;
    public java.util.function.Predicate<murat.simv2.simulation.mirror.net.minecraft.block.BlockState> blockPredicate;
    public murat.simv2.simulation.mirror.net.minecraft.world.chunk.Chunk chunk;
    public java.lang.Object storage;

    public Heightmap(murat.simv2.simulation.mirror.net.minecraft.world.chunk.Chunk p0, murat.simv2.simulation.mirror.net.minecraft.world.Heightmap.Type p1) {
    }

    public long[] asLongArray() {
        return null;
    }

    public int getOneLower(int p0, int p1) {
        return 0;
    }

    public int get(int p0) {
        return 0;
    }

    public int get(int p0, int p1) {
        return 0;
    }

    public static void populateHeightmaps(murat.simv2.simulation.mirror.net.minecraft.world.chunk.Chunk p0, java.util.Set p1) {
    }

    public void setTo(murat.simv2.simulation.mirror.net.minecraft.world.chunk.Chunk p0, murat.simv2.simulation.mirror.net.minecraft.world.Heightmap.Type p1, long[] p2) {
    }

    public void set(int p0, int p1, int p2) {
    }

    public static int toIndex(int p0, int p1) {
        return 0;
    }

    public boolean trackUpdate(int p0, int p1, int p2, murat.simv2.simulation.mirror.net.minecraft.block.BlockState p3) {
        return false;
    }

    public Heightmap() {
    }

    public static class Purpose {
        public static murat.simv2.simulation.mirror.net.minecraft.world.Heightmap.Purpose CLIENT;
        public static murat.simv2.simulation.mirror.net.minecraft.world.Heightmap.Purpose LIVE_WORLD;
        public static murat.simv2.simulation.mirror.net.minecraft.world.Heightmap.Purpose WORLDGEN;
        public static murat.simv2.simulation.mirror.net.minecraft.world.Heightmap.Purpose[] field_13208;

        public Purpose(java.lang.String p0, int p1) {
        }

        public int ordinal() {
            return 0;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.world.Heightmap.Purpose valueOf(java.lang.String p0) {
            return null;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.world.Heightmap.Purpose[] values() {
            return null;
        }

        public Purpose() {
        }

    }

    public static class Type implements murat.simv2.simulation.mirror.net.minecraft.util.StringIdentifiable {
        public static com.mojang.serialization.Codec<murat.simv2.simulation.mirror.net.minecraft.world.Heightmap.Type> CODEC;
        public static java.util.function.IntFunction<murat.simv2.simulation.mirror.net.minecraft.world.Heightmap.Type> INDEX_MAPPER;
        public static murat.simv2.simulation.mirror.net.minecraft.world.Heightmap.Type MOTION_BLOCKING;
        public static murat.simv2.simulation.mirror.net.minecraft.world.Heightmap.Type MOTION_BLOCKING_NO_LEAVES;
        public static murat.simv2.simulation.mirror.net.minecraft.world.Heightmap.Type OCEAN_FLOOR;
        public static murat.simv2.simulation.mirror.net.minecraft.world.Heightmap.Type OCEAN_FLOOR_WG;
        public static murat.simv2.simulation.mirror.net.minecraft.network.codec.PacketCodec<io.netty.buffer.ByteBuf, murat.simv2.simulation.mirror.net.minecraft.world.Heightmap.Type> PACKET_CODEC;
        public static murat.simv2.simulation.mirror.net.minecraft.world.Heightmap.Type WORLD_SURFACE;
        public static murat.simv2.simulation.mirror.net.minecraft.world.Heightmap.Type WORLD_SURFACE_WG;
        public java.util.function.Predicate<murat.simv2.simulation.mirror.net.minecraft.block.BlockState> blockPredicate;
        public static murat.simv2.simulation.mirror.net.minecraft.world.Heightmap.Type[] field_13199;
        public java.lang.String id;
        public int index;
        public murat.simv2.simulation.mirror.net.minecraft.world.Heightmap.Purpose purpose;

        public Type(java.lang.String p0, int p1, int p2, java.lang.String p3, murat.simv2.simulation.mirror.net.minecraft.world.Heightmap.Purpose p4, java.util.function.Predicate p5) {
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

        public java.util.function.Predicate getBlockPredicate() {
            return null;
        }

        public java.lang.String getId() {
            return null;
        }

        public boolean isStoredServerSide() {
            return false;
        }

        public int ordinal() {
            return 0;
        }

        public boolean shouldSendToClient() {
            return false;
        }

        public static com.mojang.serialization.Keyable toKeyable(murat.simv2.simulation.mirror.net.minecraft.util.StringIdentifiable[] p0) {
            return null;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.world.Heightmap.Type valueOf(java.lang.String p0) {
            return null;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.world.Heightmap.Type[] values() {
            return null;
        }

        public Type() {
        }

    }

    // END GENERATED MIRROR NESTED STUBS
}
