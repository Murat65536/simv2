package murat.simv2.simulation.mirror.net.minecraft.util.math;

// Generated mirror stub for simulation closure.
public class Box extends java.lang.Object {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static double EPSILON;
    public double maxX;
    public double maxY;
    public double maxZ;
    public double minX;
    public double minY;
    public double minZ;

    public Box(double p0, double p1, double p2, double p3, double p4, double p5) {
    }

    public Box(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0) {
    }

    public Box(murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p1) {
    }

    public boolean collides(murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p0, java.util.List p1) {
        return false;
    }

    public boolean contains(double p0, double p1, double p2) {
        return false;
    }

    public boolean contains(murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p0) {
        return false;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.Box contract(double p0) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.Box contract(double p0, double p1, double p2) {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.util.math.Box enclosing(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1) {
        return null;
    }

    public boolean equals(java.lang.Object p0) {
        return false;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.Box expand(double p0) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.Box expand(double p0, double p1, double p2) {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.util.math.Box from(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockBox p0) {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.util.math.Box from(murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p0) {
        return null;
    }

    public double getAverageSideLength() {
        return 0.0D;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d getCenter() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d getHorizontalCenter() {
        return null;
    }

    public double getLengthX() {
        return 0.0D;
    }

    public double getLengthY() {
        return 0.0D;
    }

    public double getLengthZ() {
        return 0.0D;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d getMaxPos() {
        return null;
    }

    public double getMax(murat.simv2.simulation.mirror.net.minecraft.util.math.Direction.Axis p0) {
        return 0.0D;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d getMinPos() {
        return null;
    }

    public double getMin(murat.simv2.simulation.mirror.net.minecraft.util.math.Direction.Axis p0) {
        return 0.0D;
    }

    public int hashCode() {
        return 0;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.Box intersection(murat.simv2.simulation.mirror.net.minecraft.util.math.Box p0) {
        return null;
    }

    public boolean intersects(double p0, double p1, double p2, double p3, double p4, double p5) {
        return false;
    }

    public boolean intersects(murat.simv2.simulation.mirror.net.minecraft.util.math.Box p0) {
        return false;
    }

    public boolean intersects(murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p1) {
        return false;
    }

    public boolean isNaN() {
        return false;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.Box offset(double p0, double p1, double p2) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.Box offset(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.Box offset(murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p0) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.Box offset(org.joml.Vector3f p0) {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.util.math.Box of(murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p0, double p1, double p2, double p3) {
        return null;
    }

    public static java.util.Optional raycast(double p0, double p1, double p2, double p3, double p4, double p5, murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p6, murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p7) {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.util.hit.BlockHitResult raycast(java.lang.Iterable p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p1, murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p2, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p3) {
        return null;
    }

    public java.util.Optional raycast(murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p1) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.Box shrink(double p0, double p1, double p2) {
        return null;
    }

    public double squaredMagnitude(murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p0) {
        return 0.0D;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.Box stretch(double p0, double p1, double p2) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.Box stretch(murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p0) {
        return null;
    }

    public java.lang.String toString() {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.util.math.Direction traceCollisionSide(double p0, double p1, double p2, double p3, double p4, double p5, murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p6, double[] p7, murat.simv2.simulation.mirror.net.minecraft.util.math.Direction p8, double p9, double p10, double p11) {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.util.math.Direction traceCollisionSide(double[] p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Direction p1, double p2, double p3, double p4, double p5, double p6, double p7, double p8, double p9, murat.simv2.simulation.mirror.net.minecraft.util.math.Direction p10, double p11, double p12, double p13) {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.util.math.Direction traceCollisionSide(murat.simv2.simulation.mirror.net.minecraft.util.math.Box p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p1, double[] p2, murat.simv2.simulation.mirror.net.minecraft.util.math.Direction p3, double p4, double p5, double p6) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.Box union(murat.simv2.simulation.mirror.net.minecraft.util.math.Box p0) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.Box withMaxX(double p0) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.Box withMaxY(double p0) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.Box withMaxZ(double p0) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.Box withMinX(double p0) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.Box withMinY(double p0) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.Box withMinZ(double p0) {
        return null;
    }

    public Box() {
    }

    public static class Builder extends java.lang.Object {
        public float maxX;
        public float maxY;
        public float maxZ;
        public float minX;
        public float minY;
        public float minZ;

        public Builder() {
        }

        public murat.simv2.simulation.mirror.net.minecraft.util.math.Box build() {
            return null;
        }

        public void encompass(org.joml.Vector3fc p0) {
        }

    }

    // END GENERATED MIRROR NESTED STUBS
}
