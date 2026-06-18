package murat.simv2.sim;

/**
 * Transpiler value type standing in for a {@code net.minecraft.fluid.FluidState} resolved at a block
 * position. Minecraft's {@code world.getFluidState(pos)} returns a {@code FluidState} the movement
 * code then queries with {@code isIn(tag)} / {@code getHeight(world,pos)} / {@code getVelocity(world,pos)};
 * here those collapse to the corresponding {@link SimWorld} per-coordinate reads, with the coordinates
 * snapshotted at resolution time. That snapshot is exact: Minecraft passes the SAME (un-mutated within
 * the iteration) {@code BlockPos} back into {@code getHeight}/{@code getVelocity}, so binding the
 * coordinates once and dropping the redundant {@code world}/{@code pos} arguments preserves semantics.
 */
public final class FluidView {

    private final SimWorld world;
    private final int x;
    private final int y;
    private final int z;

    public FluidView(SimWorld world, BlockPosM pos) {
        this(world, pos.x, pos.y, pos.z);
    }

    public FluidView(SimWorld world, int x, int y, int z) {
        this.world = world;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /** {@code FluidState.isIn(tag)} — the fluid here belongs to the given tag (water/lava). */
    public boolean isIn(SimWorld.FluidTag tag) {
        return world.isFluidInTag(x, y, z, tag);
    }

    /** {@code FluidState.getHeight(world,pos)} — up-resolved fluid height (FLOAT). */
    public float getHeight() {
        return world.fluidHeightAt(x, y, z);
    }

    /** {@code FluidState.getVelocity(world,pos)} — fluid flow vector (ZERO if still/none). */
    public Vec3 getVelocity() {
        return world.fluidFlowAt(x, y, z);
    }
}
