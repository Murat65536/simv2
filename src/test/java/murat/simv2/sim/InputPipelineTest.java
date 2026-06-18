package murat.simv2.sim;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Validates the raw-intent → travel-space input conversion ({@link MovementSim#toTravelInput}) against
 * Minecraft's client pipeline: button → Vec2f, the ×0.98 base, using-item (×0.2) and sneak
 * (×SNEAKING_SPEED) slowdowns, and the directional normalization that caps the magnitude at 1.
 * Travel input is (sidewaysSpeed, 0, forwardSpeed); forward maps to +z.
 */
class InputPipelineTest {

    private static final double TOL = 1.0e-5;

    private static SimPlayerState state() {
        return new SimPlayerState(); // sneakingSpeed defaults to 0.3
    }

    @Test
    void noInputIsZero() {
        Vec3 r = MovementSim.toTravelInput(PlayerInput.NONE, state());
        assertEquals(0.0, r.x(), 0.0);
        assertEquals(0.0, r.z(), 0.0);
    }

    @Test
    void forwardAppliesThe098Factor() {
        Vec3 r = MovementSim.toTravelInput(PlayerInput.walkForward(), state());
        assertEquals(0.0, r.x(), TOL);
        assertEquals(0.98, r.z(), TOL);
    }

    @Test
    void diagonalNormalizesToUnit() {
        PlayerInput forwardLeft = new PlayerInput(true, false, true, false, false, false, false, false);
        Vec3 r = MovementSim.toTravelInput(forwardLeft, state());
        assertEquals(0.70710677, Math.abs(r.x()), TOL);
        assertEquals(0.70710677, r.z(), TOL);
        assertEquals(1.0, Math.hypot(r.x(), r.z()), 1.0e-4, "diagonal is clamped to unit length");
    }

    @Test
    void sneakScalesBySneakingSpeed() {
        PlayerInput in = new PlayerInput(true, false, false, false, false, true, false, false);
        Vec3 r = MovementSim.toTravelInput(in, state()); // sneakingSpeed = 0.3
        assertEquals(0.98 * 0.3, r.z(), TOL);
    }

    @Test
    void usingItemScalesByPointTwo() {
        PlayerInput in = new PlayerInput(true, false, false, false, false, false, false, true);
        Vec3 r = MovementSim.toTravelInput(in, state());
        assertEquals(0.98 * 0.2, r.z(), TOL);
    }

    @Test
    void outputNeverExceedsUnitLength() {
        // every directional button combo; the directional normalization caps |input| at 1.
        for (int m = 0; m < 16; m++) {
            PlayerInput in = new PlayerInput((m & 1) != 0, (m & 2) != 0, (m & 4) != 0, (m & 8) != 0,
                false, false, false, false);
            Vec3 r = MovementSim.toTravelInput(in, state());
            double len = Math.hypot(r.x(), r.z());
            assertTrue(len <= 1.0 + 1.0e-6, "combo " + m + " length " + len + " exceeds 1");
        }
    }
}
