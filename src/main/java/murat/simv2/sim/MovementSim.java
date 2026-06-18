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
    /** tickMovement's jump gate: after a jump, no re-jump for this many ticks (vanilla). */
    public static final int JUMP_COOLDOWN = 10;
    // Sprint on-ground speed boost: SPRINTING_SPEED_BOOST = 0.3F, ADD_MULTIPLIED_TOTAL -> value
    // *= (1 + 0.3F). NOTE 0.3F as a float widens to (double)0.3f = 0.30000001192092896, so the
    // multiplier is 1.3000000119209290, NOT 1.3 — keep it exact.
    public static final double SPRINT_SPEED_MULTIPLIER = 1.0 + (double) 0.3f;
    private static final float INPUT_SCALE = 0.98f;     // applyMovementSpeedFactors base
    private static final float USING_ITEM_FACTOR = 0.2f; // using-item slowdown

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
    public static void step(SimPlayerState s, Vec3 movementInput, boolean jumpHeld, SimWorld world) {
        // Record this tick's travel input (sidewaysSpeed, forwardSpeed) so moveSelf can compute
        // collidedSoftly (the desired direction vs the collision-adjusted movement).
        s.inputX = movementInput.x();
        s.inputZ = movementInput.z();
        // tickMovement: decrement the jump cooldown, then jump before travel — only when held,
        // grounded AND off cooldown (vanilla gates a held jump to once per JUMP_COOLDOWN ticks).
        if (s.jumpingCooldown > 0) {
            s.jumpingCooldown--;
        }
        s.jumping = jumpHeld;
        if (jumpHeld) {
            if (s.onGround && s.jumpingCooldown == 0) {
                GeneratedMovement.jump(s);
                s.jumpingCooldown = JUMP_COOLDOWN;
            }
        } else {
            // Vanilla tickMovement resets the cooldown to 0 whenever jump is NOT held, so a tap
            // (release between presses) re-jumps immediately — the cooldown only paces a HELD jump.
            s.jumpingCooldown = 0;
        }
        GeneratedMovement.travelMidAir(s, movementInput, world);
    }

    /**
     * Advance one tick from RAW player intent (the full input model). Mirrors the client's
     * tickMovement order: update sprint (start/stop) → recompute the on-ground move speed for the
     * new sprint state → set the sneak flag (gates the sneak-edge clamp in {@link #moveSelf}) →
     * convert the buttons to a travel-space input via {@link #toTravelInput} → run the low-level
     * {@link #step(SimPlayerState, Vec3, boolean, SimWorld)} (jump gate + travel).
     */
    public static void step(SimPlayerState s, PlayerInput in, SimWorld world) {
        updateSprint(s, in);
        // setSprinting adds/removes the MOVEMENT_SPEED sprint modifier; reflect that on the field the
        // physics reads, so a sim-toggled sprint changes on-ground speed (off-ground uses the constants).
        s.movementSpeed = (float) (s.movementSpeedBase * (s.sprinting ? SPRINT_SPEED_MULTIPLIER : 1.0));
        s.sneaking = in.sneak();
        Vec3 travelInput = toTravelInput(in, s);
        step(s, travelInput, in.jump(), world);
    }

    /**
     * canStartSprinting then shouldStopSprinting, reduced to the in-scope (non-fluid, non-vehicle)
     * case. Mirrors ClientPlayerEntity.tickMovement's TWO independent sequential gates (NOT if/else):
     * a sprint can start and then immediately stop in the same tick (e.g. starting against a wall).
     */
    private static void updateSprint(SimPlayerState s, PlayerInput in) {
        boolean hasForward = in.forward() && !in.back(); // Input.hasForwardMovement(): forward, not back
        // canStartSprinting + the sprint key (double-tap not modeled): forward + key + fed + !item + !sneak.
        if (!s.sprinting && hasForward && in.sprint() && s.canSprint && !in.usingItem() && !in.sneak()) {
            s.sprinting = true;
        }
        // shouldStopSprinting: no forward, OR a HARD wall hit (horizontalCollision && !collidedSoftly —
        // a shallow-angle wall-slide keeps sprinting), OR can no longer sprint. Runs unconditionally.
        if (s.sprinting && (!hasForward || (s.horizontalCollision && !s.collidedSoftly) || !s.canSprint)) {
            s.sprinting = false;
        }
    }

    /**
     * Buttons → travel-space input (sidewaysSpeed, 0, forwardSpeed), bit-exact with the client:
     * KeyboardInput.getMovementInput + applyMovementSpeedFactors (×0.98, ×0.2 item, ×SNEAKING_SPEED)
     * + applyDirectionalMovementSpeedFactors (normalize, then ×min(len·dirMult, 1)). All float.
     */
    static Vec3 toTravelInput(PlayerInput in, SimPlayerState s) {
        float sideways = movementMultiplier(in.left(), in.right());
        float forward = movementMultiplier(in.forward(), in.back());
        if (sideways * sideways + forward * forward == 0.0f) {
            return new Vec3(sideways, 0.0, forward); // == (0,0): applyMovementSpeedFactors returns input
        }
        float x = sideways * INPUT_SCALE;
        float y = forward * INPUT_SCALE;
        if (in.usingItem()) {
            x *= USING_ITEM_FACTOR;
            y *= USING_ITEM_FACTOR;
        }
        if (in.sneak()) { // shouldSlowDown() ~ sneak intent (in scope)
            x *= s.sneakingSpeed;
            y *= s.sneakingSpeed;
        }
        // applyDirectionalMovementSpeedFactors
        float f = (float) Math.sqrt((double) (x * x + y * y)); // Vec2f.length() = MathHelper.sqrt
        if (f <= 0.0f) {
            return new Vec3(x, 0.0, y);
        }
        float nx = x * (1.0f / f);
        float ny = y * (1.0f / f);
        float af = Math.abs(nx);
        float ag = Math.abs(ny);
        float ratio = ag > af ? af / ag : ag / af; // getDirectionalMovementSpeedMultiplier
        float dirMult = (float) Math.sqrt((double) (1.0f + ratio * ratio));
        float h = Math.min(f * dirMult, 1.0f);
        return new Vec3(nx * h, 0.0, ny * h);
    }

    /** KeyboardInput.getMovementMultiplier: both-or-neither → 0, else +1 for positive, -1 for negative. */
    private static float movementMultiplier(boolean positive, boolean negative) {
        if (positive == negative) {
            return 0.0f;
        }
        return positive ? 1.0f : -1.0f;
    }

    /**
     * The on-land portion of LivingEntity.travel (== travelMidAir): assumes any pre-travel jump
     * is already reflected in {@code s.velocity}. The in-game validator anchors here, because the
     * real player's velocity at {@code travel}'s entry has already had jump/deadzone/input-tick
     * applied — so this is the exact slice of physics we ported and want to check.
     */
    public static void travelMidAir(SimPlayerState s, Vec3 movementInput, SimWorld world) {
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
    public static void moveSelf(SimPlayerState s, SimWorld world) {
        AABB box = s.boundingBox();
        // PlayerEntity.adjustMovementForSneaking (GENERATED — the full gate incl. the airborne
        // fallDistance branch lives inside it; pass MovementType.SELF as null, which the type==SELF
        // fold accepts). Returns the movement unchanged when not sneaking/clipping.
        Vec3 movement = GeneratedMovement.adjustMovementForSneaking(s, s.velocity, null, world);
        Vec3 adjusted = Collision.adjustForCollisions(movement, box, world, s.stepHeight, s.onGround);
        s.pos = s.pos.add(adjusted);

        boolean clippedX = !approximatelyEquals(movement.x(), adjusted.x());
        boolean clippedZ = !approximatelyEquals(movement.z(), adjusted.z());
        s.horizontalCollision = clippedX || clippedZ;
        s.verticalCollision = movement.y() != adjusted.y();
        s.onGround = s.verticalCollision && movement.y() < 0.0;
        // Entity.move sets collidedSoftly = hasCollidedSoftly(adjusted) whenever horizontalCollision;
        // a shallow-angle wall-slide keeps sprint alive (consumed by updateSprint next tick).
        s.collidedSoftly = s.horizontalCollision && hasCollidedSoftly(s, adjusted);

        if (s.horizontalCollision) {
            s.velocity = new Vec3(clippedX ? 0.0 : s.velocity.x(), s.velocity.y(),
                clippedZ ? 0.0 : s.velocity.z());
        }

        // Entity.move tail: setVelocity(getVelocity().multiply((double)m, 1.0, (double)m)) — scales
        // X/Z by the block's horizontal velocity multiplier (soul sand / honey = 0.4F), Y unchanged.
        // The float is widened at the multiply (== (double)0.4f = 0.4000000059604645, not double 0.4).
        // Runs here, before travelMidAir's gravity/friction, matching vanilla's move-then-travel order.
        // NOTE: the soul-sand/honey case via velocityMultiplier is modeled; blocks using the SEPARATE
        // pre-collision movementMultiplier mechanic (cobweb, sweet-berry, powder-snow, bubble column)
        // are NOT modeled and not block-type-detectable from the snapshot — a rollout seeded in/over
        // them would diverge (a known gap; would need per-block movementMultiplier capture to gate).
        float vm = SimWorldOps.velocityMultiplierAt(s, world);
        if (vm != 1.0f) { // no-op fast path; *1.0 is exact, so identical to skipping
            Vec3 v = s.velocity;
            s.velocity = new Vec3(v.x() * (double) vm, v.y(), v.z() * (double) vm);
        }
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
    /**
     * ClientPlayerEntity.hasCollidedSoftly: true when the angle between the desired travel direction
     * (the input rotated by yaw) and the collision-adjusted movement is &lt; ~8° — a wall-slide, which
     * keeps sprint alive. Uses the recorded {@code s.inputX/inputZ} as sidewaysSpeed/forwardSpeed.
     */
    private static boolean hasCollidedSoftly(SimPlayerState s, Vec3 adjusted) {
        float f = s.yaw * (float) (Math.PI / 180.0);
        double d = (double) MathHelperPort.sin(f);
        double e = (double) MathHelperPort.cos(f);
        double g = s.inputX * e - s.inputZ * d;
        double h = s.inputZ * e + s.inputX * d;
        double i = g * g + h * h;
        double j = adjusted.x() * adjusted.x() + adjusted.z() * adjusted.z();
        if (!(i < 1.0E-5f) && !(j < 1.0E-5f)) {
            double k = g * adjusted.x() + h * adjusted.z();
            double l = Math.acos(k / Math.sqrt(i * j));
            return l < 0.13962634f;
        }
        return false;
    }

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

    private static float slipperinessBelow(SimPlayerState s, SimWorld world) {
        // Identical to the transpiler's fused slipperiness chain target, so generated == hand-port.
        return SimWorldOps.slipperinessAt(s, world);
    }

    private static boolean approximatelyEquals(double a, double b) {
        return Math.abs(b - a) < HORIZONTAL_COLLISION_EPS;
    }

    /**
     * Whether this state is within the ported scope (on-land, non-flying). The harness skips the
     * rest. Flying (creative/spectator) uses a different gravity/drag path and is excluded.
     */
    public static boolean supports(boolean touchingWater, boolean inLava, boolean gliding,
                                   boolean hasVehicle, boolean flying) {
        // Climbing (ladders/vines/scaffolding) is now in scope — handled per-tick via the live
        // isClimbing world read — so it no longer gates. Fluid/elytra/vehicle/flying still do.
        return !touchingWater && !inLava && !gliding && !hasVehicle && !flying;
    }
}
