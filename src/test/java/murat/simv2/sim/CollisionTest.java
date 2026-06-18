package murat.simv2.sim;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Scenario coverage for the hand-written swept-AABB {@link Collision} (the least-covered,
 * version-sensitive movement code). Each test exercises a distinct collision behavior with a
 * hand-computed expected result. {@link #slabStepUp}/{@link #stairStepUpPrunesHighFace} are
 * DIFFERENTIAL: they fail against the (former) collectStepHeights parameter-swap bug — a walking
 * player stuck at a slab — and {@link #fenceTooTallNoStep} is the control proving the boundary is
 * step-height (0.6), not total failure.
 */
class CollisionTest {

    private static final double TOL = 1.0e-9;

    private static AABB playerBox(double x, double y, double z) {
        return AABB.ofEntity(new Vec3(x, y, z), SimPlayerState.WIDTH, SimPlayerState.HEIGHT);
    }

    private static WorldSnapshot world(AABB... blockers) {
        WorldSnapshot.Builder b = new WorldSnapshot.Builder();
        for (AABB a : blockers) {
            b.addBlocker(a);
        }
        return b.build();
    }

    @Test
    void fullBlockWallStops() {
        Vec3 r = Collision.adjustForCollisions(new Vec3(0.5, 0, 0), playerBox(0.6, 0, 0),
            world(new AABB(1, 0, -1, 2, 2, 2)), 0.6, true);
        assertEquals(0.1, r.x(), TOL, "clipped to the wall face");
        assertEquals(0.0, r.y(), 0.0);
        assertEquals(0.0, r.z(), 0.0);
    }

    @Test
    void slabStepUp() { // DIFFERENTIAL — catches the collectStepHeights param-swap bug
        Vec3 r = Collision.adjustForCollisions(new Vec3(0.2, 0, 0), playerBox(0.7, 0, 0),
            world(new AABB(1, 0, -1, 2, 0.5, 2)), 0.6, true);
        assertEquals(0.2, r.x(), TOL, "steps onto the 0.5 slab and keeps its horizontal movement");
        assertEquals(0.5, r.y(), TOL, "rises to the slab top");
    }

    @Test
    void stairStepUpPrunesHighFace() { // DIFFERENTIAL + the 1.0 face (> stepHeight) must be pruned
        WorldSnapshot w = world(new AABB(1, 0, -1, 2, 0.5, 2), new AABB(1.5, 0.5, -1, 2, 1.0, 2));
        Vec3 r = Collision.adjustForCollisions(new Vec3(0.2, 0, 0), playerBox(0.7, 0, 0), w, 0.6, true);
        assertEquals(0.2, r.x(), TOL);
        assertEquals(0.5, r.y(), TOL);
    }

    @Test
    void fenceTooTallNoStep() { // CONTROL — 1.5 > stepHeight, so no step (buggy and fixed agree)
        Vec3 r = Collision.adjustForCollisions(new Vec3(0.2, 0, 0), playerBox(0.7, 0, 0),
            world(new AABB(1, 0, -1, 2, 1.5, 2)), 0.6, true);
        assertEquals(0.0, r.x(), TOL, "blocked, no step over a 1.5-tall fence");
        assertEquals(0.0, r.y(), TOL);
    }

    @Test
    void diagonalCornerSqueeze() { // getAxisCheckOrder: |x|>=|z| so X clips first, then Z
        WorldSnapshot w = world(new AABB(1, 0, -2, 2, 2, 2), new AABB(-2, 0, 1, 2, 2, 2));
        Vec3 r = Collision.adjustForCollisions(new Vec3(0.5, 0, 0.4), playerBox(0.6, 0, 0.6), w, 0.6, true);
        assertEquals(0.1, r.x(), TOL);
        assertEquals(0.1, r.z(), TOL);
    }

    @Test
    void ceilingBonk() {
        Vec3 r = Collision.adjustForCollisions(new Vec3(0, 0.5, 0), playerBox(0, 0, 0),
            world(new AABB(-1, 2, -1, 1, 3, 1)), 0.6, false);
        assertEquals(0.2, r.y(), TOL, "clipped to the ceiling");
        assertEquals(0.0, r.x(), 0.0);
        assertEquals(0.0, r.z(), 0.0);
    }

    @Test
    void descendOntoLedge() {
        Vec3 r = Collision.adjustForCollisions(new Vec3(0, -0.2, 0), playerBox(1.5, 0.6, 0.5),
            world(new AABB(1, 0, 0, 2, 0.5, 1)), 0.6, false);
        assertEquals(-0.1, r.y(), TOL, "lands on the ledge top, not through it");
    }

    @Test
    void fastHorizontalNoTunnelling() {
        Vec3 r = Collision.adjustForCollisions(new Vec3(10, 0, 0), playerBox(0, 0, 0),
            world(new AABB(5, 0, -1, 6, 2, 1)), 0.6, true);
        assertEquals(4.7, r.x(), TOL, "swept AABB stops at the wall, no tunnelling");
    }

    @Test
    void ceilingSetsVerticalCollisionFlag() { // flags are only observable via moveSelf
        SimPlayerState s = new SimPlayerState();
        s.pos = new Vec3(0, 0, 0);
        s.velocity = new Vec3(0, 0.5, 0);
        s.onGround = false;
        MovementSim.moveSelf(s, world(new AABB(-1, 2, -1, 1, 3, 1)));
        assertTrue(s.verticalCollision, "ceiling -> verticalCollision");
        assertFalse(s.onGround, "an upward clip does not ground the player");
    }

    @Test
    void walkOffEdgeFallsCleanlyWithoutFalseWall() {
        WorldSnapshot w = world(new AABB(-64, -1, -64, 64, 0, 3)); // floor ends at z=3
        SimPlayerState s = new SimPlayerState();
        s.pos = new Vec3(0.5, 0, 0.5);
        s.yaw = 0.0f;
        for (int i = 0; i < 5; i++) {
            MovementSim.step(s, Vec3.ZERO, false, w); // settle onto the floor
        }
        assertTrue(s.onGround && s.pos.y() == 0.0, "grounded on the floor before walking");

        boolean everHorizontal = false;
        boolean groundedMidWalk = false;
        for (int t = 0; t < 40; t++) {
            MovementSim.step(s, new Vec3(0, 0, 1), false, w);
            everHorizontal |= s.horizontalCollision;
            if (t == 2) {
                groundedMidWalk = s.onGround;
            }
        }
        assertTrue(groundedMidWalk, "still grounded while over the floor");
        assertFalse(everHorizontal, "walking off an edge is never a horizontal collision");
        assertFalse(s.onGround, "airborne after walking off the edge");
        assertTrue(s.pos.y() < 0.0, "falling past the edge, y=" + s.pos.y());
    }
}
