package murat.simv2.sim;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.junit.jupiter.api.Test;

/**
 * Validates the {@link Rollout} driver — the mass-rollout product: a multi-tick loop that matches
 * stepping by hand, never mutates the seed, refuses out-of-scope seeds, stops honestly at the edge
 * of the captured region, and fans out deterministically in parallel.
 */
class RolloutTest {

    private static WorldSnapshot floor(AABB bounds) {
        WorldSnapshot.Builder b = new WorldSnapshot.Builder()
            .addBlocker(new AABB(-64, -1, -64, 64, 0, 64)); // floor, top face at y=0
        if (bounds != null) {
            b.bounds(bounds);
        }
        return b.build();
    }

    private static SimPlayerState grounded() {
        SimPlayerState s = new SimPlayerState();
        s.pos = new Vec3(0.5, 0.0, 0.5);
        return s;
    }

    private static long[] bits(Vec3 v) {
        return new long[]{
            Double.doubleToRawLongBits(v.x()),
            Double.doubleToRawLongBits(v.y()),
            Double.doubleToRawLongBits(v.z())};
    }

    @Test
    void runMatchesManualStepping() {
        WorldSnapshot w = floor(null);
        SimPlayerState seed = grounded();
        Rollout.InputPolicy forward = Rollout.constant(PlayerInput.walkForward());

        Rollout.Result r = Rollout.run(seed, w, forward, 100);

        SimPlayerState manual = seed.copy();
        for (int t = 0; t < 100; t++) {
            MovementSim.step(manual, PlayerInput.walkForward(), w);
        }
        assertArrayEquals(bits(manual.pos), bits(r.finalState().pos), "final pos");
        assertArrayEquals(bits(manual.velocity), bits(r.finalState().velocity), "final velocity");
        assertEquals(100, r.ticksRun());
        assertFalse(r.escapedCapture());
        assertEquals(100, r.trajectory().size());
        // seed must be untouched
        assertArrayEquals(bits(new Vec3(0.5, 0.0, 0.5)), bits(seed.pos), "seed not mutated");
    }

    @Test
    void escapeFlaggedWhenLeavingCapturedRegion() {
        AABB bounds = new AABB(-10, -10, -10, 10, 10, 10);
        WorldSnapshot w = floor(bounds);
        SimPlayerState seed = grounded();
        seed.movementSpeedBase = 0.13;

        Rollout.Result r = Rollout.run(seed, w, Rollout.constant(PlayerInput.walkForward()), 1000);

        assertTrue(r.escapedCapture(), "walking out of a ±10 region should flag escape");
        assertTrue(r.ticksRun() > 0 && r.ticksRun() < 1000, "ran=" + r.ticksRun());
    }

    @Test
    void outOfScopeSeedIsRefused() {
        WorldSnapshot w = floor(null);
        SimPlayerState seed = grounded();
        seed.inScope = false;
        Rollout.InputPolicy p = Rollout.constant(PlayerInput.NONE);
        assertThrows(IllegalArgumentException.class, () -> Rollout.run(seed, w, p, 10));
        assertThrows(IllegalArgumentException.class, () -> Rollout.runMany(List.of(p), seed, w, 10));
    }

    @Test
    void runManyParallelMatchesSequential() {
        WorldSnapshot w = floor(null);
        SimPlayerState seed = grounded();
        List<Rollout.InputPolicy> policies = new ArrayList<>();
        Random rnd = new Random(5L);
        for (int i = 0; i < 250; i++) {
            PlayerInput in = new PlayerInput(rnd.nextBoolean(), rnd.nextBoolean(), rnd.nextBoolean(),
                rnd.nextBoolean(), rnd.nextBoolean(), false, rnd.nextBoolean(), false);
            policies.add(Rollout.constant(in));
        }

        List<Rollout.Result> many = Rollout.runMany(policies, seed, w, 80);
        assertEquals(policies.size(), many.size());
        for (int i = 0; i < policies.size(); i++) {
            Rollout.Result seq = Rollout.run(seed, w, policies.get(i), 80);
            assertArrayEquals(bits(seq.finalState().pos), bits(many.get(i).finalState().pos), "pos i=" + i);
            assertArrayEquals(bits(seq.finalState().velocity), bits(many.get(i).finalState().velocity), "vel i=" + i);
        }
    }

    @Test
    void tapJumpResetsCooldownOnRelease() {
        // Releasing the jump key must reset the cooldown (vanilla), so a tap re-jumps immediately
        // even if a held-jump cooldown was still running.
        WorldSnapshot w = floor(null);
        SimPlayerState s = grounded();
        for (int i = 0; i < 6; i++) {
            MovementSim.step(s, Vec3.ZERO, false, w);
        }
        s.jumpingCooldown = 5;
        MovementSim.step(s, Vec3.ZERO, false, w); // jump released -> cooldown reset to 0
        assertEquals(0, s.jumpingCooldown, "releasing jump resets the cooldown");
        assertTrue(s.onGround);
        MovementSim.step(s, Vec3.ZERO, true, w); // grounded + cooldown 0 -> jumps now, not late
        assertTrue(s.velocity.y() > 0.3, "tap re-jumps immediately, vy=" + s.velocity.y());
    }

    @Test
    void escapeDetectedForHighSpeedSeedNearEdge() {
        // Regression for the under-bounded margin: a high-movementSpeed seed near the boundary must
        // flag escape and NOT advance into the uncaptured region (where it would read missing blocks).
        AABB bounds = new AABB(-20, -20, -20, 14, 20, 20);
        WorldSnapshot w = new WorldSnapshot.Builder()
            .addBlocker(new AABB(-64, -1, -64, 64, 0, 64)).bounds(bounds).build();
        SimPlayerState seed = grounded();
        seed.pos = new Vec3(9.5, 0.0, 0.5);
        seed.velocity = new Vec3(3.1, 0, 0);
        seed.sprinting = true;
        seed.movementSpeedBase = 1.5; // base; sprint re-applies +30% in-step (Speed effect / attribute)

        Rollout.Result r = Rollout.run(seed, w, Rollout.constant(PlayerInput.sprintForward()), 50);

        assertTrue(r.escapedCapture(), "high-speed seed near the edge must flag escape");
        assertTrue(r.finalState().pos.x() < 14.0,
            "must not advance past captured maxX=14, x=" + r.finalState().pos.x());
    }

    @Test
    void sneakingDoesNotWalkOffLedge() {
        // floor ends at z=3; a non-sneaking walker falls off, a sneaking one is clamped at the ledge.
        WorldSnapshot w = new WorldSnapshot.Builder().addBlocker(new AABB(-64, -1, -64, 64, 0, 3)).build();

        SimPlayerState walk = new SimPlayerState();
        walk.pos = new Vec3(0.5, 0, 2.0);
        for (int i = 0; i < 3; i++) {
            MovementSim.step(walk, PlayerInput.NONE, w);
        }
        for (int t = 0; t < 100; t++) {
            MovementSim.step(walk, PlayerInput.walkForward(), w);
        }
        assertTrue(walk.pos.y() < 0.0, "non-sneaking walks off the ledge and falls, y=" + walk.pos.y());

        SimPlayerState sneak = new SimPlayerState();
        sneak.pos = new Vec3(0.5, 0, 2.0);
        for (int i = 0; i < 3; i++) {
            MovementSim.step(sneak, PlayerInput.NONE, w);
        }
        for (int t = 0; t < 100; t++) {
            MovementSim.step(sneak, PlayerInput.sneakForward(), w);
        }
        assertEquals(0.0, sneak.pos.y(), 1.0e-9, "sneaking stays on the ledge (does not fall)");
        assertTrue(sneak.onGround, "sneaking stays grounded at the ledge");
        assertTrue(sneak.pos.z() > 2.5, "sneaking still advanced toward the ledge, z=" + sneak.pos.z());
    }

    @Test
    void sprintStopsOnHeadOnWall() {
        // Sprinting straight into a wall (collidedSoftly=false) must stop the sprint, per
        // shouldStopSprinting's `horizontalCollision && !collidedSoftly`.
        WorldSnapshot w = new WorldSnapshot.Builder()
            .addBlocker(new AABB(-64, -1, -64, 64, 0, 64))  // floor
            .addBlocker(new AABB(-64, 0, 3, 64, 4, 64))     // wall: z >= 3
            .build();
        SimPlayerState s = new SimPlayerState();
        s.pos = new Vec3(0.5, 0, 0.5);
        s.yaw = 0.0f; // forward -> +z, straight into the wall
        s.movementSpeedBase = 0.13;
        for (int i = 0; i < 3; i++) {
            MovementSim.step(s, PlayerInput.NONE, w);
        }
        boolean sprintedAtSomePoint = false;
        for (int t = 0; t < 60; t++) {
            MovementSim.step(s, PlayerInput.sprintForward(), w);
            sprintedAtSomePoint |= s.sprinting;
        }
        assertTrue(sprintedAtSomePoint, "started sprinting toward the wall");
        assertTrue(s.horizontalCollision, "pinned head-on against the wall");
        assertFalse(s.sprinting, "a head-on wall (not a slide) stops sprinting");
    }

    @Test
    void wallSlideSetsCollidedSoftly() {
        // A shallow-angle slide along a wall sets collidedSoftly (so sprint survives); a head-on hit
        // does not. (Differential for the dropped `&& !collidedSoftly` term.)
        WorldSnapshot w = new WorldSnapshot.Builder().addBlocker(new AABB(1, -64, -64, 2, 64, 64)).build();

        SimPlayerState slide = new SimPlayerState();
        slide.pos = new Vec3(0.7, 5, 0.5);
        slide.velocity = new Vec3(0.1, 0, 1.0); // mostly +z (along wall), slight +x into it
        slide.inputX = 0.1;
        slide.inputZ = 1.0;
        MovementSim.moveSelf(slide, w);
        assertTrue(slide.horizontalCollision, "x clipped by the wall");
        assertTrue(slide.collidedSoftly, "shallow-angle slide -> collidedSoftly");

        SimPlayerState headOn = new SimPlayerState();
        headOn.pos = new Vec3(0.7, 5, 0.5);
        headOn.velocity = new Vec3(1.0, 0, 0.0); // straight into the wall
        headOn.inputX = 1.0;
        headOn.inputZ = 0.0;
        MovementSim.moveSelf(headOn, w);
        assertTrue(headOn.horizontalCollision);
        assertFalse(headOn.collidedSoftly, "head-on -> not collidedSoftly");
    }

    @Test
    void sprintStartedAgainstHeadOnWallIsCancelledSameTick() {
        // MC runs canStartSprinting THEN shouldStopSprinting as two passes, so pressing sprint while
        // pinned head-on against a wall starts then immediately stops -> net NOT sprinting.
        // (Differential for the else-if; the old if/else left it sprinting.)
        WorldSnapshot w = new WorldSnapshot.Builder()
            .addBlocker(new AABB(-64, -1, -64, 64, 0, 64))
            .addBlocker(new AABB(-64, 0, 3, 64, 4, 64)) // wall z >= 3
            .build();
        SimPlayerState s = new SimPlayerState();
        s.pos = new Vec3(0.5, 0, 0.5);
        s.yaw = 0.0f;
        s.movementSpeedBase = 0.13;
        for (int i = 0; i < 3; i++) {
            MovementSim.step(s, PlayerInput.NONE, w);
        }
        for (int t = 0; t < 40; t++) {
            MovementSim.step(s, PlayerInput.walkForward(), w); // walk (no sprint) fully into the wall
        }
        assertTrue(s.horizontalCollision, "pinned head-on against the wall");
        assertFalse(s.collidedSoftly, "fully pinned head-on is not a soft collision");
        assertFalse(s.sprinting);

        MovementSim.step(s, PlayerInput.sprintForward(), w);
        assertFalse(s.sprinting, "sprint started against a head-on wall is cancelled the same tick");
    }

    @Test
    void sprintStartStopAndSpeedRecompute() {
        WorldSnapshot w = floor(null);
        SimPlayerState s = grounded(); // movementSpeedBase defaults to 0.1
        for (int i = 0; i < 5; i++) {
            MovementSim.step(s, PlayerInput.NONE, w); // settle, not sprinting
        }
        assertTrue(s.onGround);
        assertFalse(s.sprinting);

        MovementSim.step(s, PlayerInput.sprintForward(), w);
        assertTrue(s.sprinting, "forward + sprint key starts sprinting");
        assertEquals((float) (s.movementSpeedBase * MovementSim.SPRINT_SPEED_MULTIPLIER),
            s.movementSpeed, 1.0e-6f, "on-ground speed gets the +30% sprint boost");

        MovementSim.step(s, PlayerInput.NONE, w);
        assertFalse(s.sprinting, "no forward input stops sprinting");
        assertEquals((float) s.movementSpeedBase, s.movementSpeed, 1.0e-6f, "speed back to base");
    }

    @Test
    void jumpCooldownGatesHeldJump() {
        WorldSnapshot w = floor(null);
        SimPlayerState s = grounded();
        for (int i = 0; i < 6; i++) {
            MovementSim.step(s, Vec3.ZERO, false, w); // settle -> steady grounded
        }
        assertTrue(s.onGround, "grounded after settling");

        s.jumpingCooldown = 2;
        MovementSim.step(s, Vec3.ZERO, true, w); // 2 -> 1: cooldown blocks the jump
        assertTrue(s.velocity.y() < 0.1, "no jump while on cooldown, vy=" + s.velocity.y());
        assertEquals(1, s.jumpingCooldown);
        assertTrue(s.onGround, "still grounded");

        MovementSim.step(s, Vec3.ZERO, true, w); // 1 -> 0: jump fires
        assertTrue(s.velocity.y() > 0.3, "jumps when cooldown reaches 0, vy=" + s.velocity.y());
        assertEquals(MovementSim.JUMP_COOLDOWN, s.jumpingCooldown, "cooldown re-armed after jump");
    }
}
