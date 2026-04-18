package murat.simv2.simulation.mirror.net.minecraft.util.shape;

// Generated mirror stub for simulation closure.
public class VoxelSet {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static murat.simv2.simulation.mirror.net.minecraft.util.math.Direction.Axis[] AXES;
    public int sizeX;
    public int sizeY;
    public int sizeZ;

    public VoxelSet(int p0, int p1, int p2) {
    }

    public boolean contains(int p0, int p1, int p2) {
        return false;
    }

    public boolean contains(java.lang.Object p0, int p1, int p2, int p3) {
        return false;
    }

    public void forEachBox(murat.simv2.simulation.mirror.net.minecraft.util.shape.VoxelSet.PositionBiConsumer p0, boolean p1) {
    }

    public void forEachDirection(murat.simv2.simulation.mirror.net.minecraft.util.shape.VoxelSet.PositionConsumer p0) {
    }

    public void forEachDirection(murat.simv2.simulation.mirror.net.minecraft.util.shape.VoxelSet.PositionConsumer p0, java.lang.Object p1) {
    }

    public void forEachEdge(murat.simv2.simulation.mirror.net.minecraft.util.shape.VoxelSet.PositionBiConsumer p0, boolean p1) {
    }

    public void forEachEdge(murat.simv2.simulation.mirror.net.minecraft.util.shape.VoxelSet.PositionBiConsumer p0, java.lang.Object p1, boolean p2) {
    }

    public int getEndingAxisCoord(murat.simv2.simulation.mirror.net.minecraft.util.math.Direction.Axis p0, int p1, int p2) {
        return 0;
    }

    public int getMax(murat.simv2.simulation.mirror.net.minecraft.util.math.Direction.Axis p0) {
        return 0;
    }

    public int getMin(murat.simv2.simulation.mirror.net.minecraft.util.math.Direction.Axis p0) {
        return 0;
    }

    public int getSize(murat.simv2.simulation.mirror.net.minecraft.util.math.Direction.Axis p0) {
        return 0;
    }

    public int getStartingAxisCoord(murat.simv2.simulation.mirror.net.minecraft.util.math.Direction.Axis p0, int p1, int p2) {
        return 0;
    }

    public int getXSize() {
        return 0;
    }

    public int getYSize() {
        return 0;
    }

    public int getZSize() {
        return 0;
    }

    public boolean inBoundsAndContains(int p0, int p1, int p2) {
        return false;
    }

    public boolean inBoundsAndContains(java.lang.Object p0, int p1, int p2, int p3) {
        return false;
    }

    public boolean isEmpty() {
        return false;
    }

    public void set(int p0, int p1, int p2) {
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.shape.VoxelSet transform(murat.simv2.simulation.mirror.net.minecraft.util.math.DirectionTransformation p0) {
        return null;
    }

    public VoxelSet() {
    }

    public static interface PositionBiConsumer {
        public default void consume(int p0, int p1, int p2, int p3, int p4, int p5) {
        }

    }

    public static interface PositionConsumer {
        public default void consume(murat.simv2.simulation.mirror.net.minecraft.util.math.Direction p0, int p1, int p2, int p3) {
        }

    }

    // END GENERATED MIRROR NESTED STUBS
}
