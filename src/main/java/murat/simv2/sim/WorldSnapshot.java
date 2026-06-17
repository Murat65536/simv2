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
    /**
     * Vanilla default block friction (DEFAULT_FRICTION). FLOAT, not double: Minecraft's
     * {@code Block.getSlipperiness()} is a float and the friction arithmetic
     * ({@code g = slipperiness * 0.91F}) is done in float before widening — keeping this float is
     * load-bearing for bit-exactness against the generated physics.
     */
    public static final float DEFAULT_SLIPPERINESS = 0.6f;

    private final List<AABB> blockers;
    private final Map<Long, Float> slipperinessByBlock;
    private final float defaultSlipperiness;

    public WorldSnapshot(List<AABB> blockers, Map<Long, Float> slipperinessByBlock,
                         float defaultSlipperiness) {
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
     * Falls back to the snapshot default outside the captured region. FLOAT (see field doc).
     */
    public float slipperinessAt(int blockX, int blockY, int blockZ) {
        Float s = slipperinessByBlock.get(packBlock(blockX, blockY, blockZ));
        return s != null ? s : defaultSlipperiness;
    }

    /**
     * Fused velocity-affecting-block slipperiness for a player state — the standalone equivalent of
     * MC's {@code getWorld().getBlockState(getVelocityAffectingPos()).getBlock().getSlipperiness()}.
     * The transpiler routes that whole chain to this single call (the intermediate BlockPos/
     * BlockState/Block objects are dead). Returns FLOAT so {@code g = slip * 0.91F} stays in float.
     */
    public static float slipperinessAt(SimPlayerState s, WorldSnapshot world) {
        int bx = (int) Math.floor(s.pos.x());
        int by = (int) Math.floor(s.boundingBox().minY() - 0.5000001);
        int bz = (int) Math.floor(s.pos.z());
        return world.slipperinessAt(bx, by, bz);
    }

    // --- Dead-branch delegate stubs --------------------------------------------------------------
    // These satisfy the transpiler's coverage of the levitation / client-void / climbing branches
    // of travelMidAir/applyClimbingSpeed. Those branches are constant-folded dead at the source
    // level (getStatusEffect -> null, isClient -> false, isClimbing -> false), so these are NEVER
    // executed at runtime; they exist only so the emitted code (which is not dead-code-eliminated)
    // compiles. Loosely typed (Object) because the chain locals degrade to Object in the transpiler.

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
        private final Map<Long, Float> slipperiness = new HashMap<>();
        private float defaultSlipperiness = DEFAULT_SLIPPERINESS;

        public Builder addBlocker(AABB box) {
            blockers.add(box);
            return this;
        }

        public Builder slipperiness(int x, int y, int z, float value) {
            slipperiness.put(packBlock(x, y, z), value);
            return this;
        }

        public Builder defaultSlipperiness(float value) {
            this.defaultSlipperiness = value;
            return this;
        }

        public WorldSnapshot build() {
            return new WorldSnapshot(blockers, slipperiness, defaultSlipperiness);
        }
    }
}
