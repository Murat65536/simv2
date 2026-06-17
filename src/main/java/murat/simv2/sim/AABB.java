package murat.simv2.sim;

/**
 * Axis-aligned bounding box (world coordinates). Mirrors the subset of
 * net.minecraft.util.math.Box the movement sim needs, as pure doubles.
 */
public record AABB(double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {

    public AABB offset(double dx, double dy, double dz) {
        return new AABB(minX + dx, minY + dy, minZ + dz, maxX + dx, maxY + dy, maxZ + dz);
    }

    /** Expand the box along a movement vector (Box.stretch). */
    public AABB stretch(double dx, double dy, double dz) {
        double x0 = minX, x1 = maxX, y0 = minY, y1 = maxY, z0 = minZ, z1 = maxZ;
        if (dx < 0.0) x0 += dx; else x1 += dx;
        if (dy < 0.0) y0 += dy; else y1 += dy;
        if (dz < 0.0) z0 += dz; else z1 += dz;
        return new AABB(x0, y0, z0, x1, y1, z1);
    }

    public boolean intersects(AABB o) {
        return minX < o.maxX && maxX > o.minX
            && minY < o.maxY && maxY > o.minY
            && minZ < o.maxZ && maxZ > o.minZ;
    }

    /** Standing-player-style box centred on (x,z) at foot height y. */
    public static AABB ofEntity(Vec3 pos, double width, double height) {
        double hw = width / 2.0;
        return new AABB(pos.x() - hw, pos.y(), pos.z() - hw,
            pos.x() + hw, pos.y() + height, pos.z() + hw);
    }
}
