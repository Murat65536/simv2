package murat.simv2.simulation.mirror.net.minecraft.util.shape;

// Generated mirror stub for simulation closure.
public class ArrayVoxelShape extends murat.simv2.simulation.mirror.net.minecraft.util.shape.VoxelShape {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public murat.simv2.simulation.mirror.net.minecraft.util.shape.VoxelSet voxels;
    public it.unimi.dsi.fastutil.doubles.DoubleList xPoints;
    public it.unimi.dsi.fastutil.doubles.DoubleList yPoints;
    public it.unimi.dsi.fastutil.doubles.DoubleList zPoints;

    public ArrayVoxelShape(murat.simv2.simulation.mirror.net.minecraft.util.shape.VoxelSet p0, double[] p1, double[] p2, double[] p3) {
    }

    public ArrayVoxelShape(murat.simv2.simulation.mirror.net.minecraft.util.shape.VoxelSet p0, it.unimi.dsi.fastutil.doubles.DoubleList p1, it.unimi.dsi.fastutil.doubles.DoubleList p2, it.unimi.dsi.fastutil.doubles.DoubleList p3) {
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.shape.VoxelShape asCuboid() {
        return null;
    }

    public double calculateMaxDistance(murat.simv2.simulation.mirror.net.minecraft.util.math.AxisCycleDirection p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Box p1, double p2) {
        return 0.0D;
    }

    public double calculateMaxDistance(murat.simv2.simulation.mirror.net.minecraft.util.math.Direction.Axis p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Box p1, double p2) {
        return 0.0D;
    }

    public boolean equals(java.lang.Object p0) {
        return false;
    }

    public void forEachBox(murat.simv2.simulation.mirror.net.minecraft.util.shape.VoxelShapes.BoxConsumer p0) {
    }

    public void forEachEdge(murat.simv2.simulation.mirror.net.minecraft.util.shape.VoxelShapes.BoxConsumer p0) {
    }

    public java.util.List getBoundingBoxes() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.Box getBoundingBox() {
        return null;
    }

    public java.util.Optional getClosestPointTo(murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p0) {
        return null;
    }

    public int getCoordIndex(murat.simv2.simulation.mirror.net.minecraft.util.math.Direction.Axis p0, double p1) {
        return 0;
    }

    public double getEndingCoord(murat.simv2.simulation.mirror.net.minecraft.util.math.Direction.Axis p0, double p1, double p2) {
        return 0.0D;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.shape.VoxelShape getFace(murat.simv2.simulation.mirror.net.minecraft.util.math.Direction p0) {
        return null;
    }

    public double getMax(murat.simv2.simulation.mirror.net.minecraft.util.math.Direction.Axis p0) {
        return 0.0D;
    }

    public double getMin(murat.simv2.simulation.mirror.net.minecraft.util.math.Direction.Axis p0) {
        return 0.0D;
    }

    public it.unimi.dsi.fastutil.doubles.DoubleList getPointPositions(murat.simv2.simulation.mirror.net.minecraft.util.math.Direction.Axis p0) {
        return null;
    }

    public double getPointPosition(murat.simv2.simulation.mirror.net.minecraft.util.math.Direction.Axis p0, int p1) {
        return 0.0D;
    }

    public double getStartingCoord(murat.simv2.simulation.mirror.net.minecraft.util.math.Direction.Axis p0, double p1, double p2) {
        return 0.0D;
    }

    public boolean isCube() {
        return false;
    }

    public boolean isEmpty() {
        return false;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.shape.VoxelShape offset(double p0, double p1, double p2) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.shape.VoxelShape offset(murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p0) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.shape.VoxelShape offset(murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3i p0) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.hit.BlockHitResult raycast(murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p1, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p2) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.shape.VoxelShape simplify() {
        return null;
    }

    public java.lang.String toString() {
        return null;
    }

    public ArrayVoxelShape() {
    }

    // END GENERATED MIRROR NESTED STUBS
}
