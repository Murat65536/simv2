package murat.simv2.harness;

import murat.simv2.sim.AABB;
import murat.simv2.sim.SimPlayerState;
import murat.simv2.sim.Vec3;
import murat.simv2.sim.WorldSnapshot;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.World;

/**
 * The world half of the Minecraft &harr; sim boundary: captures the live {@link World} into the
 * MC-free {@link WorldSnapshot} the standalone core collides against. (The player-state half of
 * the bridge is GENERATED from the movement-field manifest — see
 * {@link PlayerStateCapture}.) Once captured, the sim never touches MC again.
 */
public final class Captures {
    private Captures() {
    }

    public static Vec3 toVec3(Vec3d v) {
        return new Vec3(v.x, v.y, v.z);
    }

    /**
     * MOVEMENT_SPEED attribute value with the sprint modifier removed. Captured as the sim's base
     * speed; the rollout re-applies the +30% sprint boost ({@code ×(1+(double)0.3f)}) in-step keyed
     * off its own sprint state, so a sim-toggled sprint updates the on-ground speed faithfully.
     * The sprint boost is a single MULTIPLY_TOTAL factor applied multiplicatively, so dividing the
     * full (double) value by {@code (1+(double)0.3f)} when sprinting recovers the base exactly.
     */
    public static double movementSpeedBase(net.minecraft.entity.player.PlayerEntity player) {
        double full = player.getAttributeValue(net.minecraft.entity.attribute.EntityAttributes.MOVEMENT_SPEED);
        return player.isSprinting() ? full / (1.0 + (double) 0.3f) : full;
    }

    /**
     * Single-tick capture (used by the in-game validator): a region just large enough to contain
     * this tick's movement (bounded by velocity, with a generous vertical margin for fast falls).
     */
    public static WorldSnapshot captureWorld(World world, SimPlayerState s) {
        AABB box = s.boundingBox();
        Vec3 v = s.velocity;
        double marginX = Math.abs(v.x()) + 1.0;
        double marginY = Math.max(Math.abs(v.y()) + 1.0, 4.0);
        double marginZ = Math.abs(v.z()) + 1.0;
        return captureRegion(world, new AABB(
            box.minX() - marginX, box.minY() - marginY, box.minZ() - marginZ,
            box.maxX() + marginX, box.maxY() + marginY, box.maxZ() + marginZ));
    }

    /**
     * Big-region capture for MASS ROLLOUTS: capture a generous box around the seed ONCE, then run
     * many hundred-tick rollouts against the single frozen snapshot. Terrain is static, so one
     * capture stays valid for the whole rollout; the only constraint is spatial coverage, which the
     * snapshot's captured-bounds + the rollout's escape check enforce honestly. Sizes the box by
     * {@code radiusXZ} (horizontal reach) and {@code radiusY} (fall/jump reach) around the seed.
     */
    public static WorldSnapshot captureWorld(World world, SimPlayerState seed, int radiusXZ, int radiusY) {
        AABB box = seed.boundingBox();
        return captureRegion(world, new AABB(
            box.minX() - radiusXZ, box.minY() - radiusY, box.minZ() - radiusXZ,
            box.maxX() + radiusXZ, box.maxY() + radiusY, box.maxZ() + radiusXZ));
    }

    /**
     * Capture all block collision boxes + slipperiness in {@code region}, flattening every block's
     * VoxelShape into world-space AABBs, and record the region as the snapshot's captured bounds.
     */
    public static WorldSnapshot captureRegion(World world, AABB region) {
        int minX = (int) Math.floor(region.minX());
        int maxX = (int) Math.floor(region.maxX());
        int minY = (int) Math.floor(region.minY());
        int maxY = (int) Math.floor(region.maxY());
        int minZ = (int) Math.floor(region.minZ());
        int maxZ = (int) Math.floor(region.maxZ());

        WorldSnapshot.Builder b = new WorldSnapshot.Builder().bounds(region);
        BlockPos.Mutable pos = new BlockPos.Mutable();
        for (int x = minX; x <= maxX; x++) {
            for (int y = minY; y <= maxY; y++) {
                for (int z = minZ; z <= maxZ; z++) {
                    pos.set(x, y, z);
                    BlockState state = world.getBlockState(pos);
                    VoxelShape shape = state.getCollisionShape(world, pos);
                    if (!shape.isEmpty()) {
                        for (Box bb : shape.getBoundingBoxes()) {
                            b.addBlocker(new AABB(
                                bb.minX + x, bb.minY + y, bb.minZ + z,
                                bb.maxX + x, bb.maxY + y, bb.maxZ + z));
                        }
                    }
                    float slip = state.getBlock().getSlipperiness();
                    if (slip != WorldSnapshot.DEFAULT_SLIPPERINESS) {
                        b.slipperiness(x, y, z, slip);
                    }
                    // Horizontal velocity multiplier (soul sand / honey = 0.4F); only the few that
                    // deviate are stored. The Soul-Speed MOVEMENT_EFFICIENCY lerp is gated out of
                    // scope, so the raw block value is the in-scope value (see WorldSnapshot).
                    float velMult = state.getBlock().getVelocityMultiplier();
                    if (velMult != WorldSnapshot.DEFAULT_VELOCITY_MULTIPLIER) {
                        b.velocityMultiplier(x, y, z, velMult);
                    }
                }
            }
        }
        return b.build();
    }
}
