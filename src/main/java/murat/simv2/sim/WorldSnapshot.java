package murat.simv2.sim;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Immutable, Minecraft-free snapshot of the local world the movement sim collides against.
 *
 * <p>This is the heart of the standalone design: collision shapes are captured ONCE from
 * Minecraft (every nearby block's collision boxes flattened to world-space AABBs, plus the
 * per-block slipperiness used for ground friction) and thereafter the sim never touches the
 * live world. That makes rollouts side-effect-free, thread-safe and fast enough to run
 * hundreds of thousands of times.
 *
 * <p>{@link #collisions(AABB)} is currently a linear scan — fine for capture-and-validate.
 * For mass rollouts this is the place to add a spatial index (uniform grid over block
 * coords); the API does not change.
 */
public final class WorldSnapshot {
    /** Vanilla default block friction (DEFAULT_FRICTION). */
    public static final double DEFAULT_SLIPPERINESS = 0.6;

    private final List<AABB> blockers;
    private final Map<Long, Double> slipperinessByBlock;
    private final double defaultSlipperiness;

    public WorldSnapshot(List<AABB> blockers, Map<Long, Double> slipperinessByBlock,
                         double defaultSlipperiness) {
        this.blockers = List.copyOf(blockers);
        this.slipperinessByBlock = Map.copyOf(slipperinessByBlock);
        this.defaultSlipperiness = defaultSlipperiness;
    }

    /** Block collision boxes overlapping the query box (== World.getBlockCollisions). */
    public List<AABB> collisions(AABB query) {
        List<AABB> hits = new ArrayList<>();
        for (AABB b : blockers) {
            if (b.intersects(query)) {
                hits.add(b);
            }
        }
        return hits;
    }

    /**
     * Slipperiness of the block at the given block coordinates (the velocity-affecting block).
     * Falls back to the snapshot default outside the captured region.
     */
    public double slipperinessAt(int blockX, int blockY, int blockZ) {
        Double s = slipperinessByBlock.get(packBlock(blockX, blockY, blockZ));
        return s != null ? s : defaultSlipperiness;
    }

    public int blockerCount() {
        return blockers.size();
    }

    public static long packBlock(int x, int y, int z) {
        // BlockPos.asLong layout is irrelevant here; any stable packing works.
        return ((long) (x & 0x3FFFFFF) << 38) | ((long) (z & 0x3FFFFFF) << 12) | (y & 0xFFF);
    }

    /** Builder used by the capture boundary (and tests) to assemble a snapshot. */
    public static final class Builder {
        private final List<AABB> blockers = new ArrayList<>();
        private final Map<Long, Double> slipperiness = new HashMap<>();
        private double defaultSlipperiness = DEFAULT_SLIPPERINESS;

        public Builder addBlocker(AABB box) {
            blockers.add(box);
            return this;
        }

        public Builder slipperiness(int x, int y, int z, double value) {
            slipperiness.put(packBlock(x, y, z), value);
            return this;
        }

        public Builder defaultSlipperiness(double value) {
            this.defaultSlipperiness = value;
            return this;
        }

        public WorldSnapshot build() {
            return new WorldSnapshot(blockers, slipperiness, defaultSlipperiness);
        }
    }
}
