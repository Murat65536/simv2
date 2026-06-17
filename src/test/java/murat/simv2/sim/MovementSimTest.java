package murat.simv2.sim;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Validates the standalone (Minecraft-free) movement core against known vanilla physics
 * constants. These run without the game, so they guard the sim's position-level fidelity in CI.
 *
 * <p>Bit-exact agreement with a live player over long rollouts is validated separately by the
 * in-game anchored harness (and gated by the MathHelper.sin determinism caveat in MovementSim).
 */
class MovementSimTest {

    private static WorldSnapshot floor(boolean withWall) {
        WorldSnapshot.Builder b = new WorldSnapshot.Builder()
            .addBlocker(new AABB(-64, -1, -64, 64, 0, 64)); // floor, top face at y=0
        if (withWall) {
            b.addBlocker(new AABB(-64, 0, 2, 64, 4, 3));     // wall at z in [2,3]
        }
        return b.build();
    }

    @Test
    void fallSettlesOnFloorAndGrounds() {
        SimPlayerState s = new SimPlayerState();
        s.pos = new Vec3(0.5, 5.0, 0.5);
        WorldSnapshot w = floor(false);
        for (int i = 0; i < 200; i++) MovementSim.step(s, Vec3.ZERO, false, w);
        assertEquals(0.0, s.pos.y(), 1e-9, "feet rest on floor top");
        assertTrue(s.onGround, "onGround after settling");
    }

    @Test
    void freeFallReachesAnalyticTerminalVelocity() {
        SimPlayerState s = new SimPlayerState();
        WorldSnapshot empty = new WorldSnapshot.Builder().build();
        for (int i = 0; i < 5000; i++) MovementSim.step(s, Vec3.ZERO, false, empty);
        double terminal = -MovementSim.GRAVITY * MovementSim.AIR_DRAG_Y / (1.0 - MovementSim.AIR_DRAG_Y);
        assertEquals(terminal, s.velocity.y(), 1e-9, "terminal velocity -3.92");
    }

    @Test
    void walkingStopsAtWall() {
        SimPlayerState s = new SimPlayerState();
        s.pos = new Vec3(0.5, 0.0, 0.5);
        s.yaw = 0.0f; // forward input -> +Z
        WorldSnapshot w = floor(true);
        for (int i = 0; i < 200; i++) MovementSim.step(s, new Vec3(0, 0, 1), false, w);
        assertEquals(2.0 - SimPlayerState.WIDTH / 2.0, s.pos.z(), 1e-6, "stops a half-width from wall");
        assertTrue(s.horizontalCollision, "horizontal collision flagged");
        assertTrue(s.onGround, "still grounded while walking");
    }

    @Test
    void jumpReachesVanillaApexThenLands() {
        SimPlayerState s = new SimPlayerState();
        s.pos = new Vec3(0.5, 0.0, 0.5);
        WorldSnapshot w = floor(false);
        for (int i = 0; i < 5; i++) MovementSim.step(s, Vec3.ZERO, false, w); // settle -> onGround
        assertTrue(s.onGround, "grounded before jump");

        double peak = s.pos.y();
        for (int i = 0; i < 40; i++) {
            MovementSim.step(s, Vec3.ZERO, i == 0, w); // hold jump for one tick
            peak = Math.max(peak, s.pos.y());
        }
        // Canonical vanilla standing-jump apex is ~1.2522 blocks.
        assertEquals(1.2522, peak, 1e-3, "vanilla jump apex");
        assertTrue(s.onGround && Math.abs(s.pos.y()) < 1e-9, "lands back on the floor");
    }
}
