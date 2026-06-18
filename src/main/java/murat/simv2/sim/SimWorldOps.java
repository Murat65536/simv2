package murat.simv2.sim;

/**
 * Fused, player-state-relative world lookups built on the {@link SimWorld} primitives. These compute
 * the block coordinate(s) a Minecraft read would resolve to (e.g. the velocity-affecting block under
 * the feet) and delegate to the raw per-coordinate reads, so any {@link SimWorld} backend supports
 * them for free. The transpiler routes the corresponding Minecraft call chains here (templates
 * reference {@code world}, which keeps the generator's world-threading detector happy).
 */
public final class SimWorldOps {

    private SimWorldOps() {
    }

    /**
     * Fused velocity-affecting-block slipperiness — MC's {@code getWorld().getBlockState(
     * getVelocityAffectingPos()).getBlock().getSlipperiness()}. Uses MC's {@code 0.500001F}
     * Y-offset (widened) so it matches {@code getPosWithYOffset}.
     */
    public static float slipperinessAt(SimPlayerState s, SimWorld world) {
        int bx = (int) Math.floor(s.pos.x());
        int by = (int) Math.floor(s.boundingBox().minY() - (double) 0.500001f);
        int bz = (int) Math.floor(s.pos.z());
        return world.slipperinessAt(bx, by, bz);
    }

    /**
     * Fused horizontal velocity multiplier — MC's {@code Entity.getVelocityMultiplier}: the feet-cell
     * block (PRIMARY); if 1.0, the velocity-affecting (supporting) block below. Assumes
     * {@code MOVEMENT_EFFICIENCY == 0} (Soul Speed gated out of scope).
     *
     * <p>KNOWN APPROXIMATION (shared with {@link #slipperinessAt}): the fall-through reads the centre
     * column {@code floor(pos.x), floor(pos.z)} rather than MC's {@code supportingBlockPos}; a bounded
     * boundary-straddle gap inherent to the centre-column lookup.
     */
    public static float velocityMultiplierAt(SimPlayerState s, SimWorld world) {
        int px = (int) Math.floor(s.pos.x());
        int py = (int) Math.floor(s.pos.y());
        int pz = (int) Math.floor(s.pos.z());
        float f = world.velocityMultiplierAt(px, py, pz);
        if ((double) f != 1.0) {
            return f;
        }
        int vy = (int) Math.floor(s.boundingBox().minY() - (double) 0.500001f);
        return world.velocityMultiplierAt(px, vy, pz);
    }

    /**
     * LivingEntity.isClimbing: the block at the feet cell is climbable (ladder/vine/scaffolding), OR
     * it is an open trapdoor with a matching-facing ladder directly below (canEnterTrapdoor). Spectator
     * folded false. Pure predicate (MC's {@code climbingPos} write is dropped). Coords = floored pos,
     * read at the call's pos (pre-move in applyClimbingSpeed; post-move in applyMovementInput).
     */
    public static boolean isClimbing(SimPlayerState s, SimWorld world) {
        int bx = (int) Math.floor(s.pos.x());
        int by = (int) Math.floor(s.pos.y());
        int bz = (int) Math.floor(s.pos.z());
        if (world.isClimbableAt(bx, by, bz)) {
            return true;
        }
        if (world.isOpenTrapdoorAt(bx, by, bz)) {
            return world.isLadderAt(bx, by - 1, bz)
                && world.ladderFacingAt(bx, by - 1, bz) == world.trapdoorFacingAt(bx, by, bz);
        }
        return false;
    }

    /** Whether the player's feet-cell block is scaffolding (applyClimbingSpeed's climb-hold exception). */
    public static boolean isScaffoldingAt(SimPlayerState s, SimWorld world) {
        return world.isScaffoldingAt(
            (int) Math.floor(s.pos.x()), (int) Math.floor(s.pos.y()), (int) Math.floor(s.pos.z()));
    }

    // --- Dead-branch delegate stubs --------------------------------------------------------------
    // Satisfy the transpiler's coverage of the levitation / client-void / climbing branches that are
    // constant-folded dead (getStatusEffect -> null, isClient -> false, isClimbing -> false). NEVER
    // executed at runtime; present only so the (non-DCE'd) generated code compiles. Loosely typed
    // because the chain locals degrade to Object in the transpiler.

    public static Object velocityAffectingPos(SimPlayerState s) {
        return null;
    }

    public static Object blockStateAt(Object world, Object pos) {
        return null;
    }

    public static int bottomY() {
        return -64;
    }

    public static Object blockStateAtPos(SimPlayerState s) {
        return null;
    }

    public static boolean isOf(Object state, Object block) {
        return false;
    }
}
