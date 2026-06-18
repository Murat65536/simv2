package murat.simv2.sim;

/**
 * Minimal immutable double vector for the standalone movement sim.
 *
 * <p>Deliberately NOT net.minecraft.util.math.Vec3d: the sim core must run hundreds of
 * thousands of times off the render thread with no Minecraft coupling. MC's Vec3d is only
 * touched at the capture/harness boundary, where it is converted to/from this type.
 */
public record Vec3(double x, double y, double z) {
    public static final Vec3 ZERO = new Vec3(0.0, 0.0, 0.0);

    public Vec3 add(Vec3 o) {
        return new Vec3(x + o.x, y + o.y, z + o.z);
    }

    public Vec3 add(double dx, double dy, double dz) {
        return new Vec3(x + dx, y + dy, z + dz);
    }

    public Vec3 mul(double sx, double sy, double sz) {
        return new Vec3(x * sx, y * sy, z * sz);
    }

    public Vec3 scale(double s) {
        return new Vec3(x * s, y * s, z * s);
    }

    public Vec3 withX(double v) {
        return new Vec3(v, y, z);
    }

    public Vec3 withY(double v) {
        return new Vec3(x, v, z);
    }

    public Vec3 withZ(double v) {
        return new Vec3(x, y, v);
    }

    public double length() {
        return Math.sqrt(x * x + y * y + z * z);
    }

    public double lengthSquared() {
        return x * x + y * y + z * z;
    }

    public double horizontalLengthSquared() {
        return x * x + z * z;
    }

    /** Matches Vec3d.normalize(): returns ZERO below the 1e-7 length threshold. */
    public Vec3 normalize() {
        double len = Math.sqrt(x * x + y * y + z * z);
        return len < 1.0E-7 ? ZERO : new Vec3(x / len, y / len, z / len);
    }
}
