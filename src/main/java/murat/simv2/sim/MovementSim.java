package murat.simv2.sim;

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
    public static final double AIR_DRAG_Y = 0.98;
    public static final double FRICTION_BASE = 0.91;
    public static final double GROUND_SPEED_FACTOR = 0.21600002;
    // PlayerEntity.getOffGroundSpeed(): air-control speed is sprint-dependent (the +30% sprint
    // boost applies in the air too). Float literals promoted to double to match MC's arithmetic.
    public static final double OFF_GROUND_SPEED = 0.02F;
    public static final double OFF_GROUND_SPRINT_SPEED = 0.025999999F;
    public static final double JUMP_VELOCITY = 0.42;
    public static final double SPRINT_JUMP_BOOST = 0.2;

    private static final double HORIZONTAL_COLLISION_EPS = 1.0E-5;

    /**
     * Advance one tick. {@code movementInput} is (strafe, 0, forward) in [-1,1] (MC's travel
     * input, pre-rotation); {@code jumpHeld} mirrors the jump key.
     */
    public static void step(SimPlayerState s, Vec3 movementInput, boolean jumpHeld, WorldSnapshot world) {
        // tickMovement: jump is applied before travel, when held and grounded.
        s.jumping = jumpHeld;
        if (jumpHeld && s.onGround) {
            jump(s);
        }
        travelMidAir(s, movementInput, world);
    }

    /**
     * The on-land portion of LivingEntity.travel (== travelMidAir): assumes any pre-travel jump
     * is already reflected in {@code s.velocity}. The in-game validator anchors here, because the
     * real player's velocity at {@code travel}'s entry has already had jump/deadzone/input-tick
     * applied — so this is the exact slice of physics we ported and want to check.
     */
    public static void travelMidAir(SimPlayerState s, Vec3 movementInput, WorldSnapshot world) {
        double slipperiness = s.onGround ? slipperinessBelow(s, world) : 1.0;
        double horizontalDrag = slipperiness * FRICTION_BASE;
        double speed = s.onGround
            ? s.movementSpeed * (GROUND_SPEED_FACTOR / (slipperiness * slipperiness * slipperiness))
            : (s.sprinting ? OFF_GROUND_SPRINT_SPEED : OFF_GROUND_SPEED);

        // applyMovementInput: updateVelocity (add rotated input) then move(SELF, velocity)
        s.velocity = s.velocity.add(movementInputToVelocity(movementInput, speed, s.yaw));
        moveSelf(s, world);

        Vec3 postMove = s.velocity;          // move() may have zeroed horizontal components
        double newY = postMove.y() - GRAVITY; // no levitation / slow-falling in scope
        s.velocity = new Vec3(postMove.x() * horizontalDrag, newY * AIR_DRAG_Y, postMove.z() * horizontalDrag);
    }

    /** Entity.move(MovementType.SELF, velocity): collide, advance position, update flags. */
    private static void moveSelf(SimPlayerState s, WorldSnapshot world) {
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

    private static void jump(SimPlayerState s) {
        Vec3 v = s.velocity;
        s.velocity = new Vec3(v.x(), Math.max(JUMP_VELOCITY, v.y()), v.z());
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

    private static double slipperinessBelow(SimPlayerState s, WorldSnapshot world) {
        int bx = (int) Math.floor(s.pos.x());
        int by = (int) Math.floor(s.boundingBox().minY() - 0.5000001);
        int bz = (int) Math.floor(s.pos.z());
        return world.slipperinessAt(bx, by, bz);
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
