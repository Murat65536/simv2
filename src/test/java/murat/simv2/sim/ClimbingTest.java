package murat.simv2.sim;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import murat.simv2.sim.gen.GeneratedMovement;
import org.junit.jupiter.api.Test;

/**
 * Phase 1 — climbing. Validates the now-live {@code isClimbing} world read and the (already
 * transpiled) {@link GeneratedMovement#applyClimbingSpeed} clamp: ±0.15 horizontal, y≥-0.15 slow
 * descent, and the sneak-hold-on-ladder exception (which scaffolding does NOT use). Matches MC's
 * LivingEntity.isClimbing / canEnterTrapdoor / applyClimbingSpeed.
 */
class ClimbingTest {

    private static final double TOL = 1.0e-6;

    private static SimPlayerState at(double x, double y, double z) {
        SimPlayerState s = new SimPlayerState();
        s.pos = new Vec3(x, y, z);
        return s;
    }

    @Test
    void isClimbingDetectsLadder() {
        WorldSnapshot w = new WorldSnapshot.Builder().ladder(0, 1, 0, 2).build();
        assertTrue(SimWorldOps.isClimbing(at(0.5, 1.0, 0.5), w), "standing on a ladder cell");
        assertFalse(SimWorldOps.isClimbing(at(0.5, 1.0, 0.5), new WorldSnapshot.Builder().build()),
            "empty world is not climbing");
        assertFalse(SimWorldOps.isClimbing(at(5.5, 1.0, 0.5), w), "away from the ladder");
    }

    @Test
    void isClimbingThroughOpenTrapdoorOverMatchingLadder() {
        WorldSnapshot match = new WorldSnapshot.Builder().openTrapdoor(0, 2, 0, 2).ladder(0, 1, 0, 2).build();
        assertTrue(SimWorldOps.isClimbing(at(0.5, 2.0, 0.5), match),
            "open trapdoor over a same-facing ladder is climbable");
        WorldSnapshot mismatch = new WorldSnapshot.Builder().openTrapdoor(0, 2, 0, 2).ladder(0, 1, 0, 3).build();
        assertFalse(SimWorldOps.isClimbing(at(0.5, 2.0, 0.5), mismatch),
            "trapdoor facing must match the ladder below");
    }

    @Test
    void applyClimbingSpeedClampsVelocity() {
        WorldSnapshot w = new WorldSnapshot.Builder().ladder(0, 1, 0, 2).build();
        Vec3 r = GeneratedMovement.applyClimbingSpeed(at(0.5, 1.0, 0.5), new Vec3(0.5, -0.5, 0.3), w);
        assertEquals(0.15, r.x(), TOL, "x clamped to 0.15");
        assertEquals(-0.15, r.y(), TOL, "y clamped to -0.15 (slow descent)");
        assertEquals(0.15, r.z(), TOL, "z clamped to 0.15");
    }

    @Test
    void notClimbingLeavesVelocityUnchanged() {
        WorldSnapshot w = new WorldSnapshot.Builder().build(); // no climbable block
        Vec3 in = new Vec3(0.5, -0.5, 0.3);
        Vec3 r = GeneratedMovement.applyClimbingSpeed(at(0.5, 1.0, 0.5), in, w);
        assertEquals(in.x(), r.x(), 0.0);
        assertEquals(in.y(), r.y(), 0.0);
        assertEquals(in.z(), r.z(), 0.0);
    }

    @Test
    void sneakingOnLadderHoldsPosition() {
        WorldSnapshot w = new WorldSnapshot.Builder().ladder(0, 1, 0, 2).build();
        SimPlayerState sneak = at(0.5, 1.0, 0.5);
        sneak.sneaking = true;
        Vec3 held = GeneratedMovement.applyClimbingSpeed(sneak, new Vec3(0, -0.5, 0), w);
        assertEquals(0.0, held.y(), TOL, "sneaking on a ladder holds (no descent)");

        Vec3 descend = GeneratedMovement.applyClimbingSpeed(at(0.5, 1.0, 0.5), new Vec3(0, -0.5, 0), w);
        assertEquals(-0.15, descend.y(), TOL, "not sneaking -> slow descent");
    }

    @Test
    void scaffoldingDoesNotUseTheLadderHold() {
        WorldSnapshot w = new WorldSnapshot.Builder().scaffolding(0, 1, 0).build();
        SimPlayerState sneak = at(0.5, 1.0, 0.5);
        sneak.sneaking = true;
        Vec3 r = GeneratedMovement.applyClimbingSpeed(sneak, new Vec3(0, -0.5, 0), w);
        assertEquals(-0.15, r.y(), TOL, "scaffolding is climbable but does not use the sneak-hold");
    }
}
