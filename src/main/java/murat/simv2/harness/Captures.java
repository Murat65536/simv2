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
     * Capture all block collision boxes + slipperiness in a region around the player large enough
     * to contain this tick's movement (bounded by velocity, with a generous vertical margin for
     * fast falls). Flattens every block's VoxelShape into world-space AABBs.
     */
    public static WorldSnapshot captureWorld(World world, SimPlayerState s) {
        AABB box = s.boundingBox();
        Vec3 v = s.velocity;
        double marginX = Math.abs(v.x()) + 1.0;
        double marginY = Math.max(Math.abs(v.y()) + 1.0, 4.0);
        double marginZ = Math.abs(v.z()) + 1.0;

        int minX = (int) Math.floor(box.minX() - marginX);
        int maxX = (int) Math.floor(box.maxX() + marginX);
        int minY = (int) Math.floor(box.minY() - marginY);
        int maxY = (int) Math.floor(box.maxY() + marginY);
        int minZ = (int) Math.floor(box.minZ() - marginZ);
        int maxZ = (int) Math.floor(box.maxZ() + marginZ);

        WorldSnapshot.Builder b = new WorldSnapshot.Builder();
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
                }
            }
        }
        return b.build();
    }
}
