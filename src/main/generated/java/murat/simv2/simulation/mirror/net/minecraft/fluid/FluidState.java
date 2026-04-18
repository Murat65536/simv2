package murat.simv2.simulation.mirror.net.minecraft.fluid;

// Generated mirror stub for simulation closure.
public class FluidState {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static com.mojang.serialization.Codec CODEC;
    public static java.lang.String NAME;
    public static java.lang.String PROPERTIES;
    public com.mojang.serialization.MapCodec codec;
    public static int field_31727;
    public static int field_31728;
    public java.lang.Object owner;

    public FluidState(murat.simv2.simulation.mirror.net.minecraft.fluid.Fluid p0, it.unimi.dsi.fastutil.objects.Reference2ObjectArrayMap p1, com.mojang.serialization.MapCodec p2) {
    }

    public boolean canBeReplacedWith(murat.simv2.simulation.mirror.net.minecraft.world.BlockView p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1, murat.simv2.simulation.mirror.net.minecraft.fluid.Fluid p2, murat.simv2.simulation.mirror.net.minecraft.util.math.Direction p3) {
        return false;
    }

    public boolean canFlowTo(murat.simv2.simulation.mirror.net.minecraft.world.BlockView p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1) {
        return false;
    }

    public boolean contains(java.lang.Object p0) {
        return false;
    }

    public static com.mojang.serialization.Codec createCodec(com.mojang.serialization.Codec p0, java.util.function.Function p1) {
        return null;
    }

    public void createWithMap(java.util.Map p0) {
    }

    public java.lang.Object cycle(java.lang.Object p0) {
        return null;
    }

    public boolean equals(java.lang.Object p0) {
        return false;
    }

    public float getBlastResistance() {
        return 0.0F;
    }

    public murat.simv2.simulation.mirror.net.minecraft.block.BlockState getBlockState() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.Box getCollisionBox(murat.simv2.simulation.mirror.net.minecraft.world.BlockView p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1) {
        return null;
    }

    public java.util.Map getEntries() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.fluid.Fluid getFluid() {
        return null;
    }

    public float getHeight() {
        return 0.0F;
    }

    public double getHeight(java.lang.Object p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1) {
        return 0.0D;
    }

    public int getLevel() {
        return 0;
    }

    public static java.lang.Object getNext(java.util.List p0, java.lang.Object p1) {
        return null;
    }

    public java.util.Optional getOrEmpty(java.lang.Object p0) {
        return null;
    }

    public java.lang.Object getParticle() {
        return null;
    }

    public java.util.Collection getProperties() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry getRegistryEntry() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.shape.VoxelShape getShape(murat.simv2.simulation.mirror.net.minecraft.world.BlockView p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d getVelocity(java.lang.Object p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1) {
        return null;
    }

    public java.lang.Comparable get(java.lang.Object p0) {
        return null;
    }

    public java.lang.Comparable get(java.lang.Object p0, java.lang.Comparable p1) {
        return null;
    }

    public boolean hasRandomTicks() {
        return false;
    }

    public int hashCode() {
        return 0;
    }

    public boolean isEmpty() {
        return false;
    }

    public boolean isEqualAndStill(murat.simv2.simulation.mirror.net.minecraft.fluid.Fluid p0) {
        return false;
    }

    public boolean isIn(java.lang.Object p0) {
        return false;
    }

    public boolean isIn(murat.simv2.simulation.mirror.net.minecraft.registry.tag.TagKey p0) {
        return false;
    }

    public boolean isOf(murat.simv2.simulation.mirror.net.minecraft.fluid.Fluid p0) {
        return false;
    }

    public boolean isStill() {
        return false;
    }

    public void onEntityCollision(murat.simv2.simulation.mirror.net.minecraft.world.World p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1, murat.simv2.simulation.mirror.net.minecraft.entity.Entity p2, murat.simv2.simulation.mirror.net.minecraft.entity.EntityCollisionHandler p3) {
    }

    public void onRandomTick(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1, murat.simv2.simulation.mirror.net.minecraft.util.math.random.Random p2) {
    }

    public void onScheduledTick(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1, murat.simv2.simulation.mirror.net.minecraft.block.BlockState p2) {
    }

    public void randomDisplayTick(murat.simv2.simulation.mirror.net.minecraft.world.World p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1, murat.simv2.simulation.mirror.net.minecraft.util.math.random.Random p2) {
    }

    public java.util.stream.Stream streamTags() {
        return null;
    }

    public java.lang.String toString() {
        return null;
    }

    public java.lang.Object withIfExists(java.lang.Object p0, java.lang.Comparable p1) {
        return null;
    }

    public java.lang.Object with(java.lang.Object p0, java.lang.Comparable p1) {
        return null;
    }

    public FluidState() {
    }

    // END GENERATED MIRROR NESTED STUBS
}
