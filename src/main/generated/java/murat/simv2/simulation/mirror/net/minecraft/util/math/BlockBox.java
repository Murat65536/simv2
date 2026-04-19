package murat.simv2.simulation.mirror.net.minecraft.util.math;

// Generated mirror stub for simulation closure.
public class BlockBox extends java.lang.Object {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static com.mojang.serialization.Codec<murat.simv2.simulation.mirror.net.minecraft.util.math.BlockBox> CODEC;
    public static org.slf4j.Logger LOGGER;
    public int maxX;
    public int maxY;
    public int maxZ;
    public int minX;
    public int minY;
    public int minZ;

    public BlockBox(int p0, int p1, int p2, int p3, int p4, int p5) {
    }

    public BlockBox(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0) {
    }

    public boolean contains(int p0, int p1, int p2) {
        return false;
    }

    public boolean contains(murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3i p0) {
        return false;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.util.math.BlockBox create(murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3i p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3i p1) {
        return null;
    }

    public static java.util.Optional encompassPositions(java.lang.Iterable p0) {
        return null;
    }

    public static java.util.Optional encompass(java.lang.Iterable p0) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.BlockBox encompass(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockBox p0) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.BlockBox encompass(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0) {
        return null;
    }

    public boolean equals(java.lang.Object p0) {
        return false;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.BlockBox expand(int p0) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.BlockBox expand(int p0, int p1, int p2) {
        return null;
    }

    public void forEachVertex(java.util.function.Consumer p0) {
    }

    public int getBlockCountX() {
        return 0;
    }

    public int getBlockCountY() {
        return 0;
    }

    public int getBlockCountZ() {
        return 0;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos getCenter() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3i getDimensions() {
        return null;
    }

    public int getMaxX() {
        return 0;
    }

    public int getMaxY() {
        return 0;
    }

    public int getMaxZ() {
        return 0;
    }

    public int getMinX() {
        return 0;
    }

    public int getMinY() {
        return 0;
    }

    public int getMinZ() {
        return 0;
    }

    public int hashCode() {
        return 0;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.util.math.BlockBox infinite() {
        return null;
    }

    public boolean intersectsXZ(int p0, int p1, int p2, int p3) {
        return false;
    }

    public boolean intersects(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockBox p0) {
        return false;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.BlockBox move(int p0, int p1, int p2) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.BlockBox move(murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3i p0) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.BlockBox offset(int p0, int p1, int p2) {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.util.math.BlockBox rotated(int p0, int p1, int p2, int p3, int p4, int p5, int p6, int p7, int p8, murat.simv2.simulation.mirror.net.minecraft.util.math.Direction p9) {
        return null;
    }

    public java.util.stream.Stream streamChunkPos() {
        return null;
    }

    public java.lang.String toString() {
        return null;
    }

    public BlockBox() {
    }

    // END GENERATED MIRROR NESTED STUBS
}
