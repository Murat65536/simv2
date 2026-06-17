package murat.simv2.sim;

import murat.simv2.sim.gen.GeneratedMovement;

/**
 * Standalone one-tick player movement, ported from LivingEntity.travel / travelMidAir /
 * applyMovementInput / Entity.move / Entity.updateVelocity (Minecraft 1.21.5), operating on a
 * {@link SimPlayerState} and a {@link WorldSnapshot}. No Minecraft runtime involvement, so it
 * can be run hundreds of thousands of times in parallel.
 *
 * <p>SCOPE: the on-ground / in-air ("midair") case only — normal walk / run / jump / fall.
 * Water/lava ({@code travelInFluid}), elytra ({@code travelGliding}), ladders/climbing,
 * levitation and slow-falling are intentionally NOT handled yet; {@link #supports} reports
 * whether a state is in scope so the harness can skip (never silently mis-simulate) the rest.
 *
 * <p>DETERMINISM: yaw rotation goes through {@link MathHelperPort} (a bit-exact port of
 * MathHelper's 65536-entry float sine table on a float angle), NOT java.lang.Math.sin/cos —
 * java.lang.Math diverges from the table by up to ~1e-4, which compounds over long rollouts and
 * was the source of the ~1e-6-per-tick off-axis residual. This makes off-axis movement bit-exact.
 */
public final class MovementSim {
    private MovementSim() {
    }

    public static final double GRAVITY = 0.08;
    // Air drag and ground friction are FLOAT in Minecraft: LivingEntity.travelMidAir computes
    // `g = slipperiness * 0.91F` and the vertical drag `* 0.98F` in float, then widens to double at
    // the multiply. getMovementSpeed(F) likewise computes the whole speed in float. Keeping these
    // float (not double) is load-bearing for bit-exactness against the generated physics — e.g.
    // (double)0.98F = 0.9800000190734863, not 0.98.
    public static final float AIR_DRAG_Y = 0.98f;
    public static final float FRICTION_BASE = 0.91f;
    public static final float GROUND_SPEED_FACTOR = 0.21600002f;
    // PlayerEntity.getOffGroundSpeed(): air-control speed is sprint-dependent (the +30% sprint
    // boost applies in the air too).
    public static final float OFF_GROUND_SPEED = 0.02f;
    public static final float OFF_GROUND_SPRINT_SPEED = 0.025999999f;
    public static final double SPRINT_JUMP_BOOST = 0.2;

    private static final double HORIZONTAL_COLLISION_EPS = 1.0E-5;

    /**
     * Advance one tick of the standalone rollout. {@code movementInput} is (strafe, 0, forward) in
     * [-1,1] (MC's travel input, pre-rotation); {@code jumpHeld} mirrors the jump key.
     *
     * <p>The physics is the GENERATED code ({@link GeneratedMovement}): the jump kick and the
     * travel integration both run the transpiler output, so the rollout never depends on the
     * hand-port. Only the input gating (when to jump) and the {@code moveSelf} collision delegate
     * remain hand-written. The hand-port {@link #jump}/{@link #travelMidAir} survive as the
     * bit-exact oracles the generated code is differentially tested against.
     */
    public static void step(SimPlayerState s, Vec3 movementInput, boolean jumpHeld, WorldSnapshot world) {
        // tickMovement: jump is applied before travel, when held and grounded.
        s.jumping = jumpHeld;
        if (jumpHeld && s.onGround) {
            GeneratedMovement.jump(s);
        }
        GeneratedMovement.travelMidAir(s, movementInput, world);
    }

    /**
     * The on-land portion of LivingEntity.travel (== travelMidAir): assumes any pre-travel jump
     * is already reflected in {@code s.velocity}. The in-game validator anchors here, because the
     * real player's velocity at {@code travel}'s entry has already had jump/deadzone/input-tick
     * applied — so this is the exact slice of physics we ported and want to check.
     */
    public static void travelMidAir(SimPlayerState s, Vec3 movementInput, WorldSnapshot world) {
        // MC: f = onGround ? blockSlipperiness : 1.0F; g = f * 0.91F (float).
        float slipperiness = s.onGround ? slipperinessBelow(s, world) : 1.0f;
        float horizontalDrag = slipperiness * FRICTION_BASE;
        // getMovementSpeed(slipperiness): the whole speed is computed in float (movementSpeed is a
        // float attribute snapshot); off-ground speed is sprint-dependent.
        float speed = s.onGround
            ? s.movementSpeed * (GROUND_SPEED_FACTOR / (slipperiness * slipperiness * slipperiness))
            : (s.sprinting ? OFF_GROUND_SPRINT_SPEED : OFF_GROUND_SPEED);

        // applyMovementInput: updateVelocity (add rotated input) then move(SELF, velocity).
        // speed (float) widens to double at the call — exactly where MC widens it too.
        s.velocity = s.velocity.add(movementInputToVelocity(movementInput, speed, s.yaw));
        moveSelf(s, world);

        Vec3 postMove = s.velocity;            // move() may have zeroed horizontal components
        double newY = postMove.y() - GRAVITY;  // no levitation / slow-falling in scope
        // x/z *= (double)g ; y *= (double)0.98F  (float drags widened at the multiply, as MC does).
        s.velocity = new Vec3(postMove.x() * horizontalDrag, newY * AIR_DRAG_Y, postMove.z() * horizontalDrag);
    }

    /** Entity.move(MovementType.SELF, velocity): collide, advance position, update flags. */
    public static void moveSelf(SimPlayerState s, WorldSnapshot world) {
        Vec3 movement = s.velocity;
        AABB box = s.boundingBox();
        Vec3 adjusted = Collision.adjustForCollisions(movement, box, world, s.stepHeight, s.onGround);
        s.pos = s.pos.add(adjusted);

        boolean clippedX = !approximatelyEquals(movement.x(), adjusted.x());
        boolean clippedZ = !approximatelyEquals(movement.z(), adjusted.z());
        s.horizontalCollision = clippedX || clippedZ;
        s.verticalCollision = movement.y() != adjusted.y();
        s.onGround = s.verticalCollision && movement.y() < 0.0;

        if (s.horizontalCollision) {
            s.velocity = new Vec3(clippedX ? 0.0 : s.velocity.x(), s.velocity.y(),
                clippedZ ? 0.0 : s.velocity.z());
        }
        // getVelocityMultiplier() (soul sand, honey) defaults to 1.0 and is omitted for now.
    }

    /**
     * Oracle hand-port of LivingEntity.jump(). The production path uses {@link GeneratedMovement#jump};
     * this is kept public so the bit-exact differential test can diff against it.
     *
     * <p>Jump velocity is {@code getJumpVelocity() = (float)JUMP_STRENGTH * multiplier(1) + boost(0)},
     * computed as a FLOAT then widened for {@code Math.max} — so the base is {@code (double)(float)
     * jumpStrength}, NOT the double literal 0.42 ((float)0.42 = 0.41999998688697815). Real MC narrows
     * to float; matching that is what keeps this a valid oracle.
     */
    public static void jump(SimPlayerState s) {
        float jumpVelocity = (float) s.jumpStrength; // *1.0F multiplier + 0.0F boost are identities in scope
        Vec3 v = s.velocity;
        s.velocity = new Vec3(v.x(), Math.max((double) jumpVelocity, v.y()), v.z());
        if (s.sprinting) {
            // Sprint-jump boost — MC uses MathHelper.sin/cos here too (float table).
            float g = s.yaw * (float) (Math.PI / 180.0);
            s.velocity = s.velocity.add(
                -MathHelperPort.sin(g) * SPRINT_JUMP_BOOST, 0.0, MathHelperPort.cos(g) * SPRINT_JUMP_BOOST);
        }
    }

    private static Vec3 movementInputToVelocity(Vec3 input, double speed, float yaw) {
        double lenSq = input.lengthSquared();
        if (lenSq < 1.0E-7) {
            return Vec3.ZERO;
        }
        Vec3 scaled = (lenSq > 1.0 ? input.normalize() : input).scale(speed);
        // Bit-exact match for MC: rotate by yaw via the float sine table on a float angle, then
        // promote to double for the mix (== MC's `vec3d.x * (double)g - vec3d.z * (double)f`).
        float angle = yaw * (float) (Math.PI / 180.0);
        float sin = MathHelperPort.sin(angle);
        float cos = MathHelperPort.cos(angle);
        return new Vec3(
            scaled.x() * cos - scaled.z() * sin,
            scaled.y(),
            scaled.z() * cos + scaled.x() * sin);
    }

    private static float slipperinessBelow(SimPlayerState s, WorldSnapshot world) {
        // Identical to the transpiler's fused slipperiness chain target, so generated == hand-port.
        return WorldSnapshot.slipperinessAt(s, world);
    }

    private static boolean approximatelyEquals(double a, double b) {
        return Math.abs(b - a) < HORIZONTAL_COLLISION_EPS;
    }

    /**
     * Whether this state is within the ported scope (on-land, non-flying). The harness skips the
     * rest. Flying (creative/spectator) uses a different gravity/drag path and is excluded.
     */
    public static boolean supports(boolean touchingWater, boolean inLava, boolean gliding,
                                   boolean climbing, boolean hasVehicle, boolean flying) {
        return !touchingWater && !inLava && !gliding && !climbing && !hasVehicle && !flying;
    }
}
