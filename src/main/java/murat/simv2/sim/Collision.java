package murat.simv2.sim;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

/**
 * Standalone swept-AABB collision, ported from Entity.adjustMovementForCollisions /
 * VoxelShapes.calculateMaxOffset (1.21.5). Operates purely on {@link AABB} blockers from a
 * {@link WorldSnapshot}; no Minecraft runtime involvement.
 *
 * <p>Block collision shapes (stairs, slabs, fences) are represented as their constituent
 * AABBs in the snapshot, so per-axis clipping over those boxes reproduces MC's behaviour.
 * Axis order matches MC exactly: Y first, then X/Z ordered by the larger horizontal component.
 */
public final class Collision {
    private Collision() {
    }

    private static final double EPS = 1.0E-7;

    /**
     * Resolve {@code movement} of {@code box} against the world, including step-up, exactly as
     * Entity.adjustMovementForCollisions(Vec3d) does.
     */
    public static Vec3 adjustForCollisions(Vec3 movement, AABB box, SimWorld world,
                                           double stepHeight, boolean onGround) {
        List<AABB> blockers = world.collisions(box.stretch(movement.x(), movement.y(), movement.z()));
        Vec3 vec = movement.lengthSquared() == 0.0 ? movement : collide(movement, box, blockers);

        boolean clippedX = movement.x() != vec.x();
        boolean clippedY = movement.y() != vec.y();
        boolean clippedZ = movement.z() != vec.z();
        boolean descendingClip = clippedY && movement.y() < 0.0;

        if (stepHeight > 0.0 && (descendingClip || onGround) && (clippedX || clippedZ)) {
            AABB base = descendingClip ? box.offset(0.0, vec.y(), 0.0) : box;
            AABB probe = base.stretch(movement.x(), stepHeight, movement.z());
            if (!descendingClip) {
                probe = probe.stretch(0.0, -1.0E-5, 0.0);
            }
            List<AABB> stepBlockers = world.collisions(probe);
            float verticalClip = (float) vec.y();
            for (double candidate : collectStepHeights(base, stepBlockers, stepHeight, verticalClip)) {
                Vec3 stepped = collide(new Vec3(movement.x(), candidate, movement.z()), base, stepBlockers);
                if (stepped.horizontalLengthSquared() > vec.horizontalLengthSquared()) {
                    double drop = box.minY() - base.minY();
                    return new Vec3(stepped.x(), stepped.y() - drop, stepped.z());
                }
            }
        }
        return vec;
    }

    /** Per-axis clipping (Y, then X/Z by magnitude), accumulating the box position as MC does. */
    private static Vec3 collide(Vec3 movement, AABB box, List<AABB> blockers) {
        if (blockers.isEmpty()) {
            return movement;
        }
        double dx = movement.x();
        double dy = movement.y();
        double dz = movement.z();
        AABB cur = box;

        if (dy != 0.0) {
            for (AABB b : blockers) dy = clipY(cur, b, dy);
            cur = cur.offset(0.0, dy, 0.0);
        }
        boolean xFirst = Math.abs(movement.x()) >= Math.abs(movement.z());
        if (xFirst) {
            if (dx != 0.0) { for (AABB b : blockers) dx = clipX(cur, b, dx); cur = cur.offset(dx, 0.0, 0.0); }
            if (dz != 0.0) { for (AABB b : blockers) dz = clipZ(cur, b, dz); }
        } else {
            if (dz != 0.0) { for (AABB b : blockers) dz = clipZ(cur, b, dz); cur = cur.offset(0.0, 0.0, dz); }
            if (dx != 0.0) { for (AABB b : blockers) dx = clipX(cur, b, dx); }
        }
        return new Vec3(dx, dy, dz);
    }

    private static double clipX(AABB box, AABB b, double d) {
        if (box.maxY() > b.minY() + EPS && box.minY() < b.maxY() - EPS
            && box.maxZ() > b.minZ() + EPS && box.minZ() < b.maxZ() - EPS) {
            if (d > 0.0 && box.maxX() <= b.minX() + EPS) {
                d = Math.min(b.minX() - box.maxX(), d);
            } else if (d < 0.0 && box.minX() >= b.maxX() - EPS) {
                d = Math.max(b.maxX() - box.minX(), d);
            }
        }
        return d;
    }

    private static double clipY(AABB box, AABB b, double d) {
        if (box.maxX() > b.minX() + EPS && box.minX() < b.maxX() - EPS
            && box.maxZ() > b.minZ() + EPS && box.minZ() < b.maxZ() - EPS) {
            if (d > 0.0 && box.maxY() <= b.minY() + EPS) {
                d = Math.min(b.minY() - box.maxY(), d);
            } else if (d < 0.0 && box.minY() >= b.maxY() - EPS) {
                d = Math.max(b.maxY() - box.minY(), d);
            }
        }
        return d;
    }

    private static double clipZ(AABB box, AABB b, double d) {
        if (box.maxX() > b.minX() + EPS && box.minX() < b.maxX() - EPS
            && box.maxY() > b.minY() + EPS && box.minY() < b.maxY() - EPS) {
            if (d > 0.0 && box.maxZ() <= b.minZ() + EPS) {
                d = Math.min(b.minZ() - box.maxZ(), d);
            } else if (d < 0.0 && box.minZ() >= b.maxZ() - EPS) {
                d = Math.max(b.maxZ() - box.minZ(), d);
            }
        }
        return d;
    }

    /**
     * Candidate step-up heights: distinct upward block faces in {@code (0, stepHeight]}, EXCLUDING
     * the already-achieved vertical clip. Mirrors MC's {@code Entity.collectStepHeights(box, shapes,
     * f, stepHeight)} called as {@code collectStepHeights(box, list, getStepHeight(), verticalClip)} —
     * i.e. it skips {@code g == verticalClip} and breaks once {@code g > getStepHeight()}.
     *
     * <p>(The hand-port previously had these two swapped — excluding {@code g == stepHeight} and
     * breaking at {@code g > verticalClip} — which, on a flat grounded walk where verticalClip≈0,
     * pruned every positive face and stopped the player dead at a slab/stair instead of stepping up.)
     */
    private static double[] collectStepHeights(AABB base, List<AABB> blockers, double stepHeight,
                                               float verticalClip) {
        TreeSet<Float> heights = new TreeSet<>();
        for (AABB b : blockers) {
            for (double face : new double[]{b.minY(), b.maxY()}) {
                float g = (float) (face - base.minY());
                if (!(g < 0.0F) && g != verticalClip) {
                    if (g > (float) stepHeight) {
                        break;
                    }
                    heights.add(g);
                }
            }
        }
        List<Double> out = new ArrayList<>(heights.size());
        for (Float f : heights) out.add((double) f);
        double[] arr = new double[out.size()];
        for (int i = 0; i < arr.length; i++) arr[i] = out.get(i);
        return arr;
    }
}
