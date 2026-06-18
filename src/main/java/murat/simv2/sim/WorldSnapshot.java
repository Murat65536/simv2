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
 * <p>{@link #collisions(AABB)} is backed by a uniform grid index over block coordinates, so a
 * big-region capture (needed for long rollouts) stays cheap: a query only scans the blockers in
 * the cells the box overlaps, not every blocker. The grid is built once and never mutated, so the
 * whole snapshot is safe to share read-only across parallel rollouts.
 */
public final class WorldSnapshot implements SimWorld {
    /**
     * Vanilla default block friction (DEFAULT_FRICTION). FLOAT, not double: Minecraft's
     * {@code Block.getSlipperiness()} is a float and the friction arithmetic
     * ({@code g = slipperiness * 0.91F}) is done in float before widening — keeping this float is
     * load-bearing for bit-exactness against the generated physics.
     */
    public static final float DEFAULT_SLIPPERINESS = 0.6f;

    /** Vanilla default horizontal velocity multiplier (Block.velocityMultiplier; soul sand/honey = 0.4F). */
    public static final float DEFAULT_VELOCITY_MULTIPLIER = 1.0f;

    private final List<AABB> blockers;
    private final Map<Long, List<AABB>> grid; // block cell -> blockers overlapping it (read-only)
    private final Map<Long, Float> slipperinessByBlock;
    private final float defaultSlipperiness;
    private final Map<Long, Float> velocityMultiplierByBlock;
    private final float defaultVelocityMultiplier;
    // Climbing (Phase 1): packed-cell membership/facing. Ladders & scaffolding are also climbable.
    private final java.util.Set<Long> climbable;
    private final Map<Long, Integer> ladderFacing;   // ladder cell -> Direction ordinal
    private final Map<Long, Integer> trapdoorFacing;  // OPEN trapdoor cell -> Direction ordinal
    private final java.util.Set<Long> scaffolding;
    // Fluids (Phase 2): per-cell fluid tag (1=water, 2=lava), up-resolved height, flow velocity.
    private final Map<Long, Integer> fluidTag;
    private final Map<Long, Float> fluidHeight;
    private final Map<Long, Vec3> fluidFlow;
    private final boolean ultrawarm;
    /** The fully-captured region. A query box outside it has incomplete data (see {@link #covers}). */
    private final AABB capturedBounds;

    public WorldSnapshot(List<AABB> blockers, Map<Long, Float> slipperinessByBlock,
                         float defaultSlipperiness) {
        this(builderFrom(blockers, slipperinessByBlock, defaultSlipperiness,
            Map.of(), DEFAULT_VELOCITY_MULTIPLIER, null));
    }

    public WorldSnapshot(List<AABB> blockers, Map<Long, Float> slipperinessByBlock,
                         float defaultSlipperiness, AABB capturedBounds) {
        this(builderFrom(blockers, slipperinessByBlock, defaultSlipperiness,
            Map.of(), DEFAULT_VELOCITY_MULTIPLIER, capturedBounds));
    }

    public WorldSnapshot(List<AABB> blockers, Map<Long, Float> slipperinessByBlock,
                         float defaultSlipperiness, Map<Long, Float> velocityMultiplierByBlock,
                         float defaultVelocityMultiplier, AABB capturedBounds) {
        this(builderFrom(blockers, slipperinessByBlock, defaultSlipperiness,
            velocityMultiplierByBlock, defaultVelocityMultiplier, capturedBounds));
    }

    // Single field-init site. All world-data dimensions (collision/slipperiness/velMult/climbing/
    // fluid) flow through the Builder, so adding a dimension never grows a constructor signature.
    private WorldSnapshot(Builder b) {
        this.blockers = List.copyOf(b.blockers);
        this.slipperinessByBlock = Map.copyOf(b.slipperiness);
        this.defaultSlipperiness = b.defaultSlipperiness;
        this.velocityMultiplierByBlock = Map.copyOf(b.velocityMultiplier);
        this.defaultVelocityMultiplier = b.defaultVelocityMultiplier;
        this.climbable = java.util.Set.copyOf(b.climbable);
        this.ladderFacing = Map.copyOf(b.ladderFacing);
        this.trapdoorFacing = Map.copyOf(b.trapdoorFacing);
        this.scaffolding = java.util.Set.copyOf(b.scaffolding);
        this.fluidTag = Map.copyOf(b.fluidTag);
        this.fluidHeight = Map.copyOf(b.fluidHeight);
        this.fluidFlow = Map.copyOf(b.fluidFlow);
        this.ultrawarm = b.ultrawarm;
        this.capturedBounds = b.capturedBounds;
        this.grid = buildGrid(this.blockers);
    }

    private static Builder builderFrom(List<AABB> blockers, Map<Long, Float> slipperinessByBlock,
                                       float defaultSlipperiness, Map<Long, Float> velocityMultiplierByBlock,
                                       float defaultVelocityMultiplier, AABB capturedBounds) {
        Builder b = new Builder();
        b.blockers.addAll(blockers);
        b.slipperiness.putAll(slipperinessByBlock);
        b.defaultSlipperiness = defaultSlipperiness;
        b.velocityMultiplier.putAll(velocityMultiplierByBlock);
        b.defaultVelocityMultiplier = defaultVelocityMultiplier;
        b.capturedBounds = capturedBounds;
        return b;
    }

    private static Map<Long, List<AABB>> buildGrid(List<AABB> blockers) {
        Map<Long, List<AABB>> g = new HashMap<>();
        for (AABB b : blockers) {
            // Upper cell uses nextDown(max): the max face is exclusive (matches AABB.intersects's
            // strict <), and unlike a fixed epsilon it never inverts the range for a thin box, so a
            // sub-epsilon-width blocker is still bucketed (and thus never missed vs the linear scan).
            int x0 = (int) Math.floor(b.minX());
            int x1 = (int) Math.floor(Math.nextDown(b.maxX()));
            int y0 = (int) Math.floor(b.minY());
            int y1 = (int) Math.floor(Math.nextDown(b.maxY()));
            int z0 = (int) Math.floor(b.minZ());
            int z1 = (int) Math.floor(Math.nextDown(b.maxZ()));
            for (int x = x0; x <= x1; x++) {
                for (int y = y0; y <= y1; y++) {
                    for (int z = z0; z <= z1; z++) {
                        g.computeIfAbsent(packBlock(x, y, z), k -> new ArrayList<>()).add(b);
                    }
                }
            }
        }
        return g;
    }

    /**
     * Block collision boxes overlapping the query box (== World.getBlockCollisions). Grid-indexed:
     * scans only the cells the box overlaps. May return a blocker more than once when it spans
     * several queried cells — harmless, because {@link Collision} clips with min/max (idempotent)
     * and collects step heights into a set (so duplicates and ordering never change the result).
     */
    @Override
    public List<AABB> collisions(AABB query) {
        List<AABB> hits = new ArrayList<>();
        int x0 = (int) Math.floor(query.minX());
        int x1 = (int) Math.floor(query.maxX());
        int y0 = (int) Math.floor(query.minY());
        int y1 = (int) Math.floor(query.maxY());
        int z0 = (int) Math.floor(query.minZ());
        int z1 = (int) Math.floor(query.maxZ());
        for (int x = x0; x <= x1; x++) {
            for (int y = y0; y <= y1; y++) {
                for (int z = z0; z <= z1; z++) {
                    List<AABB> cell = grid.get(packBlock(x, y, z));
                    if (cell == null) {
                        continue;
                    }
                    for (AABB b : cell) {
                        if (b.intersects(query)) {
                            hits.add(b);
                        }
                    }
                }
            }
        }
        return hits;
    }

    /**
     * Slipperiness of the block at the given block coordinates (the velocity-affecting block).
     * Falls back to the snapshot default outside the captured region. FLOAT (see field doc).
     */
    @Override
    public float slipperinessAt(int blockX, int blockY, int blockZ) {
        Float s = slipperinessByBlock.get(packBlock(blockX, blockY, blockZ));
        return s != null ? s : defaultSlipperiness;
    }

    /** Horizontal velocity multiplier of the block at the given coords (soul sand/honey = 0.4F). */
    @Override
    public float velocityMultiplierAt(int blockX, int blockY, int blockZ) {
        Float v = velocityMultiplierByBlock.get(packBlock(blockX, blockY, blockZ));
        return v != null ? v : defaultVelocityMultiplier;
    }

    /**
     * Whether {@code query} lies entirely within the captured region (i.e. collision data for it is
     * complete). A snapshot with no captured bounds (a synthetic test world) covers everything. The
     * rollout driver uses this to stop honestly instead of silently colliding against missing blocks.
     */
    @Override
    public boolean covers(AABB query) {
        return capturedBounds == null
            || (query.minX() >= capturedBounds.minX() && query.maxX() <= capturedBounds.maxX()
            && query.minY() >= capturedBounds.minY() && query.maxY() <= capturedBounds.maxY()
            && query.minZ() >= capturedBounds.minZ() && query.maxZ() <= capturedBounds.maxZ());
    }

    @Override
    public boolean isClimbableAt(int x, int y, int z) {
        return climbable.contains(packBlock(x, y, z));
    }

    @Override
    public boolean isLadderAt(int x, int y, int z) {
        return ladderFacing.containsKey(packBlock(x, y, z));
    }

    @Override
    public int ladderFacingAt(int x, int y, int z) {
        return ladderFacing.getOrDefault(packBlock(x, y, z), -1);
    }

    @Override
    public boolean isOpenTrapdoorAt(int x, int y, int z) {
        return trapdoorFacing.containsKey(packBlock(x, y, z));
    }

    @Override
    public int trapdoorFacingAt(int x, int y, int z) {
        return trapdoorFacing.getOrDefault(packBlock(x, y, z), -1);
    }

    @Override
    public boolean isScaffoldingAt(int x, int y, int z) {
        return scaffolding.contains(packBlock(x, y, z));
    }

    @Override
    public boolean isFluidInTag(int x, int y, int z, FluidTag tag) {
        Integer t = fluidTag.get(packBlock(x, y, z));
        return t != null && t == (tag == FluidTag.WATER ? 1 : 2);
    }

    @Override
    public float fluidHeightAt(int x, int y, int z) {
        Float h = fluidHeight.get(packBlock(x, y, z));
        return h != null ? h : 0.0f;
    }

    @Override
    public Vec3 fluidFlowAt(int x, int y, int z) {
        Vec3 v = fluidFlow.get(packBlock(x, y, z));
        return v != null ? v : Vec3.ZERO;
    }

    @Override
    public boolean isUltrawarm() {
        return ultrawarm;
    }

    public AABB capturedBounds() {
        return capturedBounds;
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
        private final Map<Long, Float> velocityMultiplier = new HashMap<>();
        private float defaultVelocityMultiplier = DEFAULT_VELOCITY_MULTIPLIER;
        private final java.util.Set<Long> climbable = new java.util.HashSet<>();
        private final Map<Long, Integer> ladderFacing = new HashMap<>();
        private final Map<Long, Integer> trapdoorFacing = new HashMap<>();
        private final java.util.Set<Long> scaffolding = new java.util.HashSet<>();
        private final Map<Long, Integer> fluidTag = new HashMap<>();
        private final Map<Long, Float> fluidHeight = new HashMap<>();
        private final Map<Long, Vec3> fluidFlow = new HashMap<>();
        private boolean ultrawarm = false;
        private AABB capturedBounds; // null = unbounded (synthetic test worlds)

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

        public Builder velocityMultiplier(int x, int y, int z, float value) {
            velocityMultiplier.put(packBlock(x, y, z), value);
            return this;
        }

        public Builder defaultVelocityMultiplier(float value) {
            this.defaultVelocityMultiplier = value;
            return this;
        }

        /** Mark a climbable cell (ladder/vine/scaffolding — anything in BlockTags.CLIMBABLE). */
        public Builder climbable(int x, int y, int z) {
            climbable.add(packBlock(x, y, z));
            return this;
        }

        /** Mark a ladder cell with its facing ordinal (also climbable). */
        public Builder ladder(int x, int y, int z, int facingOrdinal) {
            long k = packBlock(x, y, z);
            ladderFacing.put(k, facingOrdinal);
            climbable.add(k);
            return this;
        }

        /** Mark an OPEN trapdoor cell with its facing ordinal (climbing-through-trapdoor). */
        public Builder openTrapdoor(int x, int y, int z, int facingOrdinal) {
            trapdoorFacing.put(packBlock(x, y, z), facingOrdinal);
            return this;
        }

        /** Mark a scaffolding cell (also climbable; the climb-hold exception). */
        public Builder scaffolding(int x, int y, int z) {
            long k = packBlock(x, y, z);
            scaffolding.add(k);
            climbable.add(k);
            return this;
        }

        /** Mark a still-water cell with its up-resolved height (0..1+). */
        public Builder water(int x, int y, int z, float height) {
            return water(x, y, z, height, 0.0, 0.0);
        }

        /** Mark a (possibly flowing) water cell with height and horizontal flow velocity. */
        public Builder water(int x, int y, int z, float height, double flowX, double flowZ) {
            long k = packBlock(x, y, z);
            fluidTag.put(k, 1);
            fluidHeight.put(k, height);
            if (flowX != 0.0 || flowZ != 0.0) {
                fluidFlow.put(k, new Vec3(flowX, 0.0, flowZ));
            }
            return this;
        }

        /** Mark a lava cell with its up-resolved height. */
        public Builder lava(int x, int y, int z, float height) {
            long k = packBlock(x, y, z);
            fluidTag.put(k, 2);
            fluidHeight.put(k, height);
            return this;
        }

        public Builder ultrawarm(boolean value) {
            this.ultrawarm = value;
            return this;
        }

        /** The region that was fully scanned for blocks; enables {@link WorldSnapshot#covers}. */
        public Builder bounds(AABB region) {
            this.capturedBounds = region;
            return this;
        }

        public WorldSnapshot build() {
            return new WorldSnapshot(this);
        }
    }
}
