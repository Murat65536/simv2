package murat.simv2.sim.gen;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Random;
import murat.simv2.sim.AABB;
import murat.simv2.sim.MathHelperPort;
import murat.simv2.sim.MovementSim;
import murat.simv2.sim.SimPlayerState;
import murat.simv2.sim.Vec3;
import murat.simv2.sim.WorldSnapshot;
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

    /** Exact mirror of LivingEntity.getMovementSpeed(float) (player). */
    private static float refMovementSpeed(SimPlayerState s, float slip) {
        return s.onGround
            ? s.movementSpeed * (0.21600002F / (slip * slip * slip))
            : refOffGroundSpeed(s.flying, s.sprinting, s.flySpeed);
    }

    @Test
    void movementSpeedMatchesReferenceBitExact() {
        Random rnd = new Random(123L);
        for (int i = 0; i < 200_000; i++) {
            SimPlayerState s = new SimPlayerState();
            s.onGround = (i & 1) == 0;
            s.sprinting = (i & 2) == 0;
            s.flying = (i & 4) == 0;
            s.movementSpeed = (float) (rnd.nextDouble() * 0.4);
            s.flySpeed = (float) (rnd.nextDouble() * 0.2);
            float slip = (float) (0.4 + rnd.nextDouble() * 0.6); // 0.4..1.0 (ice..normal)

            float expected = refMovementSpeed(s, slip);
            float actual = GeneratedMovement.getMovementSpeed(s, slip);
            assertEquals(Float.floatToRawIntBits(expected), Float.floatToRawIntBits(actual),
                "movementSpeed i=" + i + " onGround=" + s.onGround + " slip=" + slip);
        }
    }

    @Test
    void gravityIsVanilla() {
        // On-land scope: no slow-falling, no NoGravity -> getFinalGravity()/getEffectiveGravity() == 0.08.
        Random rnd = new Random(5L);
        for (int i = 0; i < 1000; i++) {
            SimPlayerState s = new SimPlayerState();
            s.velocity = new Vec3(rnd.nextGaussian(), rnd.nextGaussian(), rnd.nextGaussian());
            assertEquals(Double.doubleToRawLongBits(0.08),
                Double.doubleToRawLongBits(GeneratedMovement.getEffectiveGravity(s)), "effGrav i=" + i);
            assertEquals(Double.doubleToRawLongBits(0.08),
                Double.doubleToRawLongBits(GeneratedMovement.getFinalGravity(s)), "finalGrav i=" + i);
        }
    }

    /**
     * The whole-tick GO/NO-GO: the transpiled {@link GeneratedMovement#travelMidAir} (the top of the
     * closure — slipperiness chain, applyMovementInput, gravity, drag, the move() collision delegate)
     * must match the validated hand-port {@link MovementSim#travelMidAir} BIT-FOR-BIT across a random
     * sweep of in-scope states (grounded/airborne, sprint, varied slipperiness) and worlds (empty +
     * collision floor). Both delegate collision to the same {@code MovementSim.moveSelf}, so this
     * isolates the transpiled physics arithmetic + world wiring.
     */
    @Test
    void travelMidAirMatchesHandPortBitExact() {
        Random rnd = new Random(424242L);
        for (int i = 0; i < 300_000; i++) {
            SimPlayerState a = new SimPlayerState();
            a.pos = new Vec3(rnd.nextGaussian() * 4, 64 + rnd.nextDouble() * 4, rnd.nextGaussian() * 4);
            a.velocity = new Vec3(rnd.nextGaussian() * 0.3, rnd.nextGaussian() * 0.4 - 0.1, rnd.nextGaussian() * 0.3);
            a.yaw = (float) (rnd.nextDouble() * 1440.0 - 720.0);
            a.onGround = (i & 1) == 0;
            a.sprinting = (i & 2) == 0;
            a.movementSpeed = (float) (0.05 + rnd.nextDouble() * 0.3);
            a.flying = false; // travelMidAir scope is non-flying
            SimPlayerState b = a.copy();

            Vec3 input = new Vec3(rnd.nextDouble() * 2 - 1, 0, rnd.nextDouble() * 2 - 1);
            WorldSnapshot world = randomWorld(rnd, a);

            MovementSim.travelMidAir(a, input, world);
            GeneratedMovement.travelMidAir(b, input, world);

            String ctx = "i=" + i;
            assertBitEqual(a.velocity, b.velocity, "vel " + ctx);
            assertBitEqual(a.pos, b.pos, "pos " + ctx);
            assertEquals(a.onGround, b.onGround, "onGround " + ctx);
            assertEquals(a.horizontalCollision, b.horizontalCollision, "hColl " + ctx);
            assertEquals(a.verticalCollision, b.verticalCollision, "vColl " + ctx);
        }
    }

    /** Empty world (default friction) half the time; a 5x5 collision floor + custom slipperiness otherwise. */
    private static WorldSnapshot randomWorld(Random rnd, SimPlayerState s) {
        WorldSnapshot.Builder b = new WorldSnapshot.Builder();
        float[] slips = {0.6f, 0.98f, 0.8f, 0.5f, 1.0f}; // normal, ice, packed ice, slime, max
        int bx = (int) Math.floor(s.pos.x());
        int by = (int) Math.floor(s.boundingBox().minY() - 0.5000001);
        int bz = (int) Math.floor(s.pos.z());
        float slip = slips[rnd.nextInt(slips.length)];
        if (slip != WorldSnapshot.DEFAULT_SLIPPERINESS) {
            b.slipperiness(bx, by, bz, slip);
        }
        if (rnd.nextBoolean()) {
            int fy = (int) Math.floor(s.boundingBox().minY()) - 1;
            for (int dx = -2; dx <= 2; dx++) {
                for (int dz = -2; dz <= 2; dz++) {
                    int x = bx + dx;
                    int z = bz + dz;
                    b.addBlocker(new AABB(x, fy, z, x + 1, fy + 1, z + 1));
                }
            }
        }
        return b.build();
    }

    /**
     * The pre-travel jump kick: transpiled {@link GeneratedMovement#jump} must match the hand-port
     * {@link MovementSim#jump} BIT-FOR-BIT across random jump strengths (incl. attribute modifiers),
     * sprint state and yaw — covering the float-narrowed base velocity and the sin/cos sprint boost.
     */
    @Test
    void jumpMatchesHandPortBitExact() {
        Random rnd = new Random(7777L);
        for (int i = 0; i < 300_000; i++) {
            SimPlayerState a = new SimPlayerState();
            a.velocity = new Vec3(rnd.nextGaussian() * 0.3, rnd.nextGaussian() * 0.5, rnd.nextGaussian() * 0.3);
            a.yaw = (float) (rnd.nextDouble() * 1440.0 - 720.0);
            a.sprinting = (i & 1) == 0;
            a.jumpStrength = 0.30 + rnd.nextDouble() * 0.3; // vanilla 0.42 +/- attribute modifiers
            SimPlayerState b = a.copy();

            MovementSim.jump(a);
            GeneratedMovement.jump(b);

            assertBitEqual(a.velocity, b.velocity, "jump i=" + i + " sprint=" + a.sprinting + " yaw=" + a.yaw);
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
