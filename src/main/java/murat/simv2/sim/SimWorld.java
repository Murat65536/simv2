package murat.simv2.sim;

import java.util.List;

/**
 * The world-access seam the standalone physics reads through. Decoupling the physics from a concrete
 * world lets the SAME movement code run against either a frozen {@link WorldSnapshot} (simv2's
 * mass-rollout / tests) or a live adapter over a real Minecraft {@code WorldView} (the Tungsten
 * pathfinder, which reads per-tick on worker threads). Implementations must be safe to read
 * concurrently for the duration of a rollout (a {@code WorldSnapshot} is immutable; a live adapter
 * reads read-only inside its prediction gate).
 *
 * <p>Only the raw per-coordinate / per-box reads live here; the fused, player-state-relative lookups
 * (e.g. the velocity-affecting block's slipperiness) are static helpers in {@link SimWorldOps} that
 * compute the coordinates and call back into this interface — so a new world backend implements just
 * these primitives. Fluid/climbing reads are added here as the corresponding scope phases land.
 */
public interface SimWorld {

    /** Block collision boxes overlapping the query box (== World.getBlockCollisions / isSpaceEmpty). */
    List<AABB> collisions(AABB query);

    /** Slipperiness (friction) of the block at these coords; FLOAT (g = slip * 0.91F). */
    float slipperinessAt(int blockX, int blockY, int blockZ);

    /** Horizontal velocity multiplier of the block at these coords (soul sand/honey = 0.4F). */
    float velocityMultiplierAt(int blockX, int blockY, int blockZ);

    /** Whether the query box lies entirely within fully-known world data (frozen snapshots only). */
    boolean covers(AABB query);

    // --- Climbing (Phase 1): ladders / vines / scaffolding / climbable-through-trapdoor ------------

    /** Block at these coords is in {@code BlockTags.CLIMBABLE} (ladder, vine, scaffolding, ...). */
    boolean isClimbableAt(int x, int y, int z);

    /** Block at these coords is a {@code LadderBlock}. */
    boolean isLadderAt(int x, int y, int z);

    /** Horizontal facing ordinal ({@code Direction.ordinal()}) of a ladder here, or -1 if not a ladder. */
    int ladderFacingAt(int x, int y, int z);

    /** Block at these coords is a {@code TrapdoorBlock} with {@code OPEN == true}. */
    boolean isOpenTrapdoorAt(int x, int y, int z);

    /** Horizontal facing ordinal of a trapdoor here, or -1 if not a trapdoor. */
    int trapdoorFacingAt(int x, int y, int z);

    /** Block at these coords is {@code Blocks.SCAFFOLDING} (the climb-hold exception). */
    boolean isScaffoldingAt(int x, int y, int z);

    // --- Fluids (Phase 2): water / lava detection, height, flow ----------------------------------

    enum FluidTag { WATER, LAVA }

    /** The fluid at these coords is in {@code tag} (FluidState.isIn(FluidTags.WATER/LAVA)). */
    boolean isFluidInTag(int x, int y, int z, FluidTag tag);

    /** Up-resolved fluid height at these coords (FluidState.getHeight(world,pos)); FLOAT, 0 if no fluid. */
    float fluidHeightAt(int x, int y, int z);

    /** Fluid flow velocity at these coords (FluidState.getVelocity(world,pos)); ZERO if still/none. */
    Vec3 fluidFlowAt(int x, int y, int z);

    /** Dimension is ultrawarm (Nether) — selects the faster LAVA push speed. */
    boolean isUltrawarm();
}
