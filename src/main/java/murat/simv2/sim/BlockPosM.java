package murat.simv2.sim;

/**
 * Transpiler value type standing in for {@code net.minecraft.util.math.BlockPos.Mutable} — a reusable
 * mutable integer block coordinate. The generated fluid scan allocates one of these once and re-{@code
 * set}s it per cell (mirroring Minecraft, which reuses a single {@code Mutable} to avoid per-cell
 * allocation), then reads the world at its coordinates. Like {@link Vec3}/{@link AABB} it carries no
 * world access — it is pure data the generated code threads through {@link FluidView}.
 */
public final class BlockPosM {

    public int x;
    public int y;
    public int z;

    /** Set all three coordinates and return {@code this} (matches {@code Mutable.set(int,int,int)}). */
    public BlockPosM set(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
        return this;
    }
}
