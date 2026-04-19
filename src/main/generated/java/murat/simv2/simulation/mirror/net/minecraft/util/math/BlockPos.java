package murat.simv2.simulation.mirror.net.minecraft.util.math;

// Generated mirror stub for simulation closure.
public class BlockPos extends murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3i {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static long BITS_X;
    public static long BITS_Y;
    public static long BITS_Z;
    public static int BIT_SHIFT_X;
    public static int BIT_SHIFT_Z;
    public static com.mojang.serialization.Codec<murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos> CODEC;
    public static org.slf4j.Logger LOGGER;
    public static int MAX_XZ;
    public static murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos ORIGIN;
    public static murat.simv2.simulation.mirror.net.minecraft.network.codec.PacketCodec<io.netty.buffer.ByteBuf, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos> PACKET_CODEC;
    public static int SIZE_BITS_XZ;
    public static int SIZE_BITS_Y;
    public static murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3i ZERO;
    public static int field_33083;

    public BlockPos(int p0, int p1, int p2) {
    }

    public BlockPos(murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3i p0) {
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos add(int p0, int p1, int p2) {
        return null;
    }

    public static long add(long p0, int p1, int p2, int p3) {
        return 0L;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos add(murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3i p0) {
        return null;
    }

    public long asLong() {
        return 0L;
    }

    public static long asLong(int p0, int p1, int p2) {
        return 0L;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d clampToWithin(murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p0) {
        return null;
    }

    public int compareTo(murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3i p0) {
        return 0;
    }

    public static com.mojang.serialization.Codec createOffsetCodec(int p0) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos crossProduct(murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3i p0) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos down() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos down(int p0) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos east() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos east(int p0) {
        return null;
    }

    public boolean equals(java.lang.Object p0) {
        return false;
    }

    public static java.util.Optional findClosest(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, int p1, int p2, java.util.function.Predicate p3) {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos fromLong(long p0) {
        return null;
    }

    public int getChebyshevDistance(murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3i p0) {
        return 0;
    }

    public int getComponentAlongAxis(murat.simv2.simulation.mirror.net.minecraft.util.math.Direction.Axis p0) {
        return 0;
    }

    public int getManhattanDistance(murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3i p0) {
        return 0;
    }

    public double getSquaredDistanceFromCenter(double p0, double p1, double p2) {
        return 0.0D;
    }

    public double getSquaredDistance(double p0, double p1, double p2) {
        return 0.0D;
    }

    public double getSquaredDistance(murat.simv2.simulation.mirror.net.minecraft.util.math.Position p0) {
        return 0.0D;
    }

    public double getSquaredDistance(murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3i p0) {
        return 0.0D;
    }

    public int getX() {
        return 0;
    }

    public int getY() {
        return 0;
    }

    public int getZ() {
        return 0;
    }

    public int hashCode() {
        return 0;
    }

    public boolean isWithinDistance(murat.simv2.simulation.mirror.net.minecraft.util.math.Position p0, double p1) {
        return false;
    }

    public boolean isWithinDistance(murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3i p0, double p1) {
        return false;
    }

    public static java.lang.Iterable iterateInSquare(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, int p1, murat.simv2.simulation.mirror.net.minecraft.util.math.Direction p2, murat.simv2.simulation.mirror.net.minecraft.util.math.Direction p3) {
        return null;
    }

    public static java.lang.Iterable iterateOutwards(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, int p1, int p2, int p3) {
        return null;
    }

    public static java.lang.Iterable iterateRandomly(murat.simv2.simulation.mirror.net.minecraft.util.math.random.Random p0, int p1, int p2, int p3, int p4, int p5, int p6, int p7) {
        return null;
    }

    public static java.lang.Iterable iterateRandomly(murat.simv2.simulation.mirror.net.minecraft.util.math.random.Random p0, int p1, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p2, int p3) {
        return null;
    }

    public static int iterateRecursively(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, int p1, int p2, java.util.function.BiConsumer p3, java.util.function.Function p4) {
        return 0;
    }

    public static java.lang.Iterable iterate(int p0, int p1, int p2, int p3, int p4, int p5) {
        return null;
    }

    public static java.lang.Iterable iterate(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1) {
        return null;
    }

    public static java.lang.Iterable iterate(murat.simv2.simulation.mirror.net.minecraft.util.math.Box p0) {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos max(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1) {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos min(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos multiply(int p0) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos.Mutable mutableCopy() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos north() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos north(int p0) {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos ofFloored(double p0, double p1, double p2) {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos ofFloored(murat.simv2.simulation.mirror.net.minecraft.util.math.Position p0) {
        return null;
    }

    public static long offset(long p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Direction p1) {
        return 0L;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos offset(murat.simv2.simulation.mirror.net.minecraft.util.math.Direction p0) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos offset(murat.simv2.simulation.mirror.net.minecraft.util.math.Direction p0, int p1) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos offset(murat.simv2.simulation.mirror.net.minecraft.util.math.Direction.Axis p0, int p1) {
        return null;
    }

    public static long removeChunkSectionLocalY(long p0) {
        return 0L;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos rotate(murat.simv2.simulation.mirror.net.minecraft.util.BlockRotation p0) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3i setX(int p0) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3i setY(int p0) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3i setZ(int p0) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos south() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos south(int p0) {
        return null;
    }

    public static java.util.stream.Stream streamOutwards(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, int p1, int p2, int p3) {
        return null;
    }

    public static java.util.stream.Stream streamSouthEastSquare(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0) {
        return null;
    }

    public static java.util.stream.Stream stream(int p0, int p1, int p2, int p3, int p4, int p5) {
        return null;
    }

    public static java.util.stream.Stream stream(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockBox p0) {
        return null;
    }

    public static java.util.stream.Stream stream(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1) {
        return null;
    }

    public static java.util.stream.Stream stream(murat.simv2.simulation.mirror.net.minecraft.util.math.Box p0) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos subtract(murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3i p0) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d toBottomCenterPos() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d toCenterPos() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos toImmutable() {
        return null;
    }

    public java.lang.String toShortString() {
        return null;
    }

    public java.lang.String toString() {
        return null;
    }

    public static int unpackLongX(long p0) {
        return 0;
    }

    public static int unpackLongY(long p0) {
        return 0;
    }

    public static int unpackLongZ(long p0) {
        return 0;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos up() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos up(int p0) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos west() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos west(int p0) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos withY(int p0) {
        return null;
    }

    public BlockPos() {
    }

    public static class IterationState {
        public static murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos.IterationState ACCEPT;
        public static murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos.IterationState SKIP;
        public static murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos.IterationState STOP;
        public static murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos.IterationState[] field_55168;

        public IterationState(java.lang.String p0, int p1) {
        }

        public int ordinal() {
            return 0;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos.IterationState valueOf(java.lang.String p0) {
            return null;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos.IterationState[] values() {
            return null;
        }

        public IterationState() {
        }

    }

    public static class Mutable extends murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos {
        public static com.mojang.serialization.Codec<murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos> CODEC;
        public static int MAX_XZ;
        public static murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos ORIGIN;
        public static murat.simv2.simulation.mirror.net.minecraft.network.codec.PacketCodec<io.netty.buffer.ByteBuf, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos> PACKET_CODEC;
        public static int SIZE_BITS_XZ;
        public static int SIZE_BITS_Y;
        public static murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3i ZERO;

        public Mutable() {
        }

        public Mutable(double p0, double p1, double p2) {
        }

        public Mutable(int p0, int p1, int p2) {
        }

        public murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos add(int p0, int p1, int p2) {
            return null;
        }

        public static long add(long p0, int p1, int p2, int p3) {
            return 0L;
        }

        public murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos add(murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3i p0) {
            return null;
        }

        public long asLong() {
            return 0L;
        }

        public static long asLong(int p0, int p1, int p2) {
            return 0L;
        }

        public murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d clampToWithin(murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p0) {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos.Mutable clamp(murat.simv2.simulation.mirror.net.minecraft.util.math.Direction.Axis p0, int p1, int p2) {
            return null;
        }

        public int compareTo(murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3i p0) {
            return 0;
        }

        public static com.mojang.serialization.Codec createOffsetCodec(int p0) {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos crossProduct(murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3i p0) {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos down() {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos down(int p0) {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos east() {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos east(int p0) {
            return null;
        }

        public boolean equals(java.lang.Object p0) {
            return false;
        }

        public static java.util.Optional findClosest(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, int p1, int p2, java.util.function.Predicate p3) {
            return null;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos fromLong(long p0) {
            return null;
        }

        public int getChebyshevDistance(murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3i p0) {
            return 0;
        }

        public int getComponentAlongAxis(murat.simv2.simulation.mirror.net.minecraft.util.math.Direction.Axis p0) {
            return 0;
        }

        public int getManhattanDistance(murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3i p0) {
            return 0;
        }

        public double getSquaredDistanceFromCenter(double p0, double p1, double p2) {
            return 0.0D;
        }

        public double getSquaredDistance(double p0, double p1, double p2) {
            return 0.0D;
        }

        public double getSquaredDistance(murat.simv2.simulation.mirror.net.minecraft.util.math.Position p0) {
            return 0.0D;
        }

        public double getSquaredDistance(murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3i p0) {
            return 0.0D;
        }

        public int getX() {
            return 0;
        }

        public int getY() {
            return 0;
        }

        public int getZ() {
            return 0;
        }

        public int hashCode() {
            return 0;
        }

        public boolean isWithinDistance(murat.simv2.simulation.mirror.net.minecraft.util.math.Position p0, double p1) {
            return false;
        }

        public boolean isWithinDistance(murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3i p0, double p1) {
            return false;
        }

        public static java.lang.Iterable iterateInSquare(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, int p1, murat.simv2.simulation.mirror.net.minecraft.util.math.Direction p2, murat.simv2.simulation.mirror.net.minecraft.util.math.Direction p3) {
            return null;
        }

        public static java.lang.Iterable iterateOutwards(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, int p1, int p2, int p3) {
            return null;
        }

        public static java.lang.Iterable iterateRandomly(murat.simv2.simulation.mirror.net.minecraft.util.math.random.Random p0, int p1, int p2, int p3, int p4, int p5, int p6, int p7) {
            return null;
        }

        public static java.lang.Iterable iterateRandomly(murat.simv2.simulation.mirror.net.minecraft.util.math.random.Random p0, int p1, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p2, int p3) {
            return null;
        }

        public static int iterateRecursively(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, int p1, int p2, java.util.function.BiConsumer p3, java.util.function.Function p4) {
            return 0;
        }

        public static java.lang.Iterable iterate(int p0, int p1, int p2, int p3, int p4, int p5) {
            return null;
        }

        public static java.lang.Iterable iterate(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1) {
            return null;
        }

        public static java.lang.Iterable iterate(murat.simv2.simulation.mirror.net.minecraft.util.math.Box p0) {
            return null;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos max(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1) {
            return null;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos min(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1) {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos.Mutable move(int p0, int p1, int p2) {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos.Mutable move(murat.simv2.simulation.mirror.net.minecraft.util.math.Direction p0) {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos.Mutable move(murat.simv2.simulation.mirror.net.minecraft.util.math.Direction p0, int p1) {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos.Mutable move(murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3i p0) {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos multiply(int p0) {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos.Mutable mutableCopy() {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos north() {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos north(int p0) {
            return null;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos ofFloored(double p0, double p1, double p2) {
            return null;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos ofFloored(murat.simv2.simulation.mirror.net.minecraft.util.math.Position p0) {
            return null;
        }

        public static long offset(long p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Direction p1) {
            return 0L;
        }

        public murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos offset(murat.simv2.simulation.mirror.net.minecraft.util.math.Direction p0) {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos offset(murat.simv2.simulation.mirror.net.minecraft.util.math.Direction p0, int p1) {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos offset(murat.simv2.simulation.mirror.net.minecraft.util.math.Direction.Axis p0, int p1) {
            return null;
        }

        public static long removeChunkSectionLocalY(long p0) {
            return 0L;
        }

        public murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos rotate(murat.simv2.simulation.mirror.net.minecraft.util.BlockRotation p0) {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos.Mutable setX(int p0) {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos.Mutable setY(int p0) {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos.Mutable setZ(int p0) {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos.Mutable set(double p0, double p1, double p2) {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos.Mutable set(int p0, int p1, int p2) {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos.Mutable set(long p0) {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos.Mutable set(murat.simv2.simulation.mirror.net.minecraft.util.math.AxisCycleDirection p0, int p1, int p2, int p3) {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos.Mutable set(murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3i p0) {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos.Mutable set(murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3i p0, int p1, int p2, int p3) {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos.Mutable set(murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3i p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Direction p1) {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos.Mutable set(murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3i p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3i p1) {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos south() {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos south(int p0) {
            return null;
        }

        public static java.util.stream.Stream streamOutwards(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, int p1, int p2, int p3) {
            return null;
        }

        public static java.util.stream.Stream streamSouthEastSquare(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0) {
            return null;
        }

        public static java.util.stream.Stream stream(int p0, int p1, int p2, int p3, int p4, int p5) {
            return null;
        }

        public static java.util.stream.Stream stream(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockBox p0) {
            return null;
        }

        public static java.util.stream.Stream stream(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1) {
            return null;
        }

        public static java.util.stream.Stream stream(murat.simv2.simulation.mirror.net.minecraft.util.math.Box p0) {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos subtract(murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3i p0) {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d toBottomCenterPos() {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d toCenterPos() {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos toImmutable() {
            return null;
        }

        public java.lang.String toShortString() {
            return null;
        }

        public java.lang.String toString() {
            return null;
        }

        public static int unpackLongX(long p0) {
            return 0;
        }

        public static int unpackLongY(long p0) {
            return 0;
        }

        public static int unpackLongZ(long p0) {
            return 0;
        }

        public murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos up() {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos up(int p0) {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos west() {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos west(int p0) {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos withY(int p0) {
            return null;
        }

    }

    // END GENERATED MIRROR NESTED STUBS
}
