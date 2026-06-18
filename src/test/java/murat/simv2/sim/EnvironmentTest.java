package murat.simv2.sim;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Phase 2 — the per-tick environment-state backbone ({@link Environment#update}). Verifies fluid
 * detection (water/lava touching + depth), {@code isInLava}, the fall-distance reset on water-touch,
 * the one-tick-lagged submersion, the swimming flag, and the fluid-flow push — all read through
 * {@link SimWorld}. (The dispatcher that consumes these flags + the swimming-pose bbox land in the
 * water-physics phase.)
 */
class EnvironmentTest {

    /** Player feet at (0.5, 64, 0.5); standing box scans cells (0,64,0) and (0,65,0). */
    private static SimPlayerState player() {
        SimPlayerState s = new SimPlayerState();
        s.pos = new Vec3(0.5, 64.0, 0.5);
        return s;
    }

    @Test
    void detectsWaterAndResetsFallDistance() {
        WorldSnapshot w = new WorldSnapshot.Builder().water(0, 64, 0, 1.0f).water(0, 65, 0, 1.0f).build();
        SimPlayerState s = player();
        s.fallDistance = 5.0;
        Environment.update(s, w);
        assertTrue(s.touchingWater, "in water -> touchingWater");
        assertTrue(s.fluidHeightWater > 0.0, "water depth recorded");
        assertEquals(0.0, s.fallDistance, 0.0, "water-touch resets fallDistance");
        assertFalse(Environment.isInLava(s), "not in lava");
    }

    @Test
    void detectsLava() {
        WorldSnapshot w = new WorldSnapshot.Builder().lava(0, 64, 0, 1.0f).lava(0, 65, 0, 1.0f).build();
        SimPlayerState s = player();
        Environment.update(s, w);
        assertTrue(Environment.isInLava(s), "in lava");
        assertTrue(s.fluidHeightLava > 0.0, "lava depth recorded");
        assertFalse(s.touchingWater, "lava is not water");
    }

    @Test
    void noFluidLeavesFlagsClear() {
        WorldSnapshot w = new WorldSnapshot.Builder().build();
        SimPlayerState s = player();
        Environment.update(s, w);
        assertFalse(s.touchingWater);
        assertFalse(Environment.isInLava(s));
        assertEquals(0.0, s.fluidHeightWater, 0.0);
    }

    @Test
    void submersionLagsOneTick() {
        // deep water: full at the feet cell AND the eye cell (eyeY = 64 + 1.62 = 65.62 -> cell 65)
        WorldSnapshot w = new WorldSnapshot.Builder().water(0, 64, 0, 1.0f).water(0, 65, 0, 1.0f).build();
        SimPlayerState s = player();
        Environment.update(s, w);
        assertFalse(s.submergedInWater, "first tick: submergedInWater still lagged-false");
        assertTrue(s.submergedFluidWater, "but this-tick eye probe sees submersion");
        Environment.update(s, w);
        assertTrue(s.submergedInWater, "second tick: the lagged flag catches up");
    }

    @Test
    void swimmingRequiresSprintAndSubmersion() {
        WorldSnapshot w = new WorldSnapshot.Builder().water(0, 64, 0, 1.0f).water(0, 65, 0, 1.0f).build();
        SimPlayerState s = player();
        s.sprinting = true;
        Environment.update(s, w); // submergedInWater not yet latched
        Environment.update(s, w); // now submerged + sprinting + water at feet -> swimming
        assertTrue(s.swimming, "sprint + submerged in water -> swimming");

        SimPlayerState walker = player(); // sprint off
        Environment.update(walker, w);
        Environment.update(walker, w);
        assertFalse(walker.swimming, "no sprint -> not swimming");
    }

    @Test
    void flowingWaterPushesVelocity() {
        WorldSnapshot w = new WorldSnapshot.Builder()
            .water(0, 64, 0, 1.0f, 0.1, 0.0) // flow in +x
            .water(0, 65, 0, 1.0f)
            .build();
        SimPlayerState s = player();
        Environment.update(s, w);
        assertTrue(s.velocity.x() > 0.0, "flowing water pushes +x, vx=" + s.velocity.x());
    }
}
