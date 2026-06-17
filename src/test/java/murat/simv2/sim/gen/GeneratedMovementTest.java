package murat.simv2.sim.gen;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Random;
import murat.simv2.sim.MathHelperPort;
import murat.simv2.sim.SimPlayerState;
import murat.simv2.sim.Vec3;
import org.junit.jupiter.api.Test;

/**
 * GO/NO-GO gate for the Option-C transpiler (Track 2). Asserts that the standalone Java
 * {@link GeneratedMovement#movementInputToVelocity} emitted by SimGenerator from the captured SSA
 * IR matches a faithful hand mirror of Minecraft's {@code Entity.movementInputToVelocity}
 * BIT-FOR-BIT across a large random sweep. If the transpiler mistranslated SSA, types, the phi,
 * the branches, the MATH routing, or the constants, the bits diverge and this fails.
 */
class GeneratedMovementTest {

    /** Exact mirror of Entity.movementInputToVelocity (1.21.5), in our value types. */
    private static Vec3 reference(Vec3 input, float speed, float yaw) {
        double d = input.lengthSquared();
        if (d < 1.0E-7) {
            return Vec3.ZERO;
        }
        Vec3 v = (d > 1.0 ? input.normalize() : input).scale((double) speed);
        float f = MathHelperPort.sin(yaw * (float) (Math.PI / 180.0));
        float g = MathHelperPort.cos(yaw * (float) (Math.PI / 180.0));
        return new Vec3(
            v.x() * (double) g - v.z() * (double) f,
            v.y(),
            v.z() * (double) g + v.x() * (double) f);
    }

    private static void assertBitEqual(Vec3 expected, Vec3 actual, String ctx) {
        assertEquals(Double.doubleToRawLongBits(expected.x()), Double.doubleToRawLongBits(actual.x()), "x " + ctx);
        assertEquals(Double.doubleToRawLongBits(expected.y()), Double.doubleToRawLongBits(actual.y()), "y " + ctx);
        assertEquals(Double.doubleToRawLongBits(expected.z()), Double.doubleToRawLongBits(actual.z()), "z " + ctx);
    }

    @Test
    void transpiledMatchesReferenceBitExact() {
        Random rnd = new Random(20240617L);
        for (int i = 0; i < 300_000; i++) {
            // mix scales so we hit the <1e-7 (ZERO), <=1 (no normalize) and >1 (normalize) branches
            double scale = switch (i % 3) {
                case 0 -> 1.0E-4;   // tiny -> often returns ZERO
                case 1 -> 0.9;      // <= 1 -> no normalize
                default -> 3.0;     // > 1 -> normalize
            };
            Vec3 input = new Vec3(
                (rnd.nextDouble() * 2 - 1) * scale,
                (rnd.nextDouble() * 2 - 1) * scale,
                (rnd.nextDouble() * 2 - 1) * scale);
            float speed = (float) (rnd.nextDouble() * 0.3);
            float yaw = (float) (rnd.nextDouble() * 1440.0 - 720.0);

            Vec3 expected = reference(input, speed, yaw);
            Vec3 actual = GeneratedMovement.movementInputToVelocity(input, speed, yaw);
            assertBitEqual(expected, actual, "i=" + i + " in=" + input + " spd=" + speed + " yaw=" + yaw);
        }
    }

    @Test
    void updateVelocityMatchesReferenceBitExact() {
        Random rnd = new Random(99L);
        for (int i = 0; i < 200_000; i++) {
            Vec3 startVel = new Vec3(rnd.nextGaussian(), rnd.nextGaussian(), rnd.nextGaussian());
            float yaw = (float) (rnd.nextDouble() * 1440.0 - 720.0);
            float speed = (float) (rnd.nextDouble() * 0.3);
            double scale = switch (i % 3) {
                case 0 -> 1.0E-4;
                case 1 -> 0.9;
                default -> 3.0;
            };
            Vec3 input = new Vec3(
                (rnd.nextDouble() * 2 - 1) * scale,
                (rnd.nextDouble() * 2 - 1) * scale,
                (rnd.nextDouble() * 2 - 1) * scale);

            // reference: this.setVelocity(this.getVelocity().add(movementInputToVelocity(input, speed, yaw)))
            Vec3 expected = startVel.add(reference(input, speed, yaw));

            SimPlayerState s = new SimPlayerState();
            s.velocity = startVel;
            s.yaw = yaw;
            GeneratedMovement.updateVelocity(s, speed, input);

            assertBitEqual(expected, s.velocity, "updateVelocity i=" + i);
        }
    }

    /** Exact mirror of PlayerEntity.getOffGroundSpeed (hasVehicle() pruned to false). */
    private static float refOffGroundSpeed(boolean flying, boolean sprinting, float flySpeed) {
        if (flying) {
            return sprinting ? flySpeed * 2.0F : flySpeed;
        }
        return sprinting ? 0.025999999F : 0.02F;
    }

    @Test
    void offGroundSpeedMatchesReferenceBitExact() {
        Random rnd = new Random(7L);
        for (int i = 0; i < 100_000; i++) {
            boolean flying = (i & 1) == 0;
            boolean sprinting = (i & 2) == 0;
            float flySpeed = (float) (rnd.nextDouble() * 0.2);
            SimPlayerState s = new SimPlayerState();
            s.flying = flying;
            s.sprinting = sprinting;
            s.flySpeed = flySpeed;

            float expected = refOffGroundSpeed(flying, sprinting, flySpeed);
            float actual = GeneratedMovement.getOffGroundSpeed(s);
            assertEquals(Float.floatToRawIntBits(expected), Float.floatToRawIntBits(actual),
                "offGroundSpeed flying=" + flying + " sprint=" + sprinting + " fly=" + flySpeed);
        }
    }

    @Test
    void edgeCases() {
        // zero input -> ZERO
        assertBitEqual(Vec3.ZERO,
            GeneratedMovement.movementInputToVelocity(Vec3.ZERO, 0.1f, 33.3f), "zero");
        // exact axis yaws (table == Math here) and unit-ish inputs
        for (float yaw : new float[]{0f, 90f, 180f, 270f, -90f, 45f}) {
            Vec3 in = new Vec3(0, 0, 0.98);
            assertBitEqual(reference(in, 0.13f, yaw),
                GeneratedMovement.movementInputToVelocity(in, 0.13f, yaw), "axis yaw=" + yaw);
        }
    }
}
