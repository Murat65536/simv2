package murat.simv2.simulation.mirror.net.minecraft.fluid;

// Generated mirror stub for simulation closure.
public class Fluid extends java.lang.Object {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static murat.simv2.simulation.mirror.net.minecraft.util.collection.IdList<murat.simv2.simulation.mirror.net.minecraft.fluid.FluidState> STATE_IDS;
    public murat.simv2.simulation.mirror.net.minecraft.fluid.FluidState defaultState;
    public murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry.Reference<murat.simv2.simulation.mirror.net.minecraft.fluid.Fluid> registryEntry;
    public murat.simv2.simulation.mirror.net.minecraft.state.StateManager<murat.simv2.simulation.mirror.net.minecraft.fluid.Fluid, murat.simv2.simulation.mirror.net.minecraft.fluid.FluidState> stateManager;

    public Fluid() {
    }

    public void appendProperties(murat.simv2.simulation.mirror.net.minecraft.state.StateManager.Builder p0) {
    }

    public boolean canBeReplacedWith(murat.simv2.simulation.mirror.net.minecraft.fluid.FluidState p0, murat.simv2.simulation.mirror.net.minecraft.world.BlockView p1, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p2, murat.simv2.simulation.mirror.net.minecraft.fluid.Fluid p3, murat.simv2.simulation.mirror.net.minecraft.util.math.Direction p4) {
        return false;
    }

    public float getBlastResistance() {
        return 0.0F;
    }

    public java.util.Optional getBucketFillSound() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.item.Item getBucketItem() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.Box getCollisionBox(murat.simv2.simulation.mirror.net.minecraft.fluid.FluidState p0, murat.simv2.simulation.mirror.net.minecraft.world.BlockView p1, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p2) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.fluid.FluidState getDefaultState() {
        return null;
    }

    public float getHeight(murat.simv2.simulation.mirror.net.minecraft.fluid.FluidState p0) {
        return 0.0F;
    }

    public float getHeight(murat.simv2.simulation.mirror.net.minecraft.fluid.FluidState p0, murat.simv2.simulation.mirror.net.minecraft.world.BlockView p1, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p2) {
        return 0.0F;
    }

    public int getLevel(murat.simv2.simulation.mirror.net.minecraft.fluid.FluidState p0) {
        return 0;
    }

    public murat.simv2.simulation.mirror.net.minecraft.particle.ParticleEffect getParticle() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry.Reference getRegistryEntry() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.shape.VoxelShape getShape(murat.simv2.simulation.mirror.net.minecraft.fluid.FluidState p0, murat.simv2.simulation.mirror.net.minecraft.world.BlockView p1, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p2) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.state.StateManager getStateManager() {
        return null;
    }

    public int getTickRate(murat.simv2.simulation.mirror.net.minecraft.world.WorldView p0) {
        return 0;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d getVelocity(murat.simv2.simulation.mirror.net.minecraft.world.BlockView p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1, murat.simv2.simulation.mirror.net.minecraft.fluid.FluidState p2) {
        return null;
    }

    public boolean hasRandomTicks() {
        return false;
    }

    public boolean isEmpty() {
        return false;
    }

    public boolean isIn(murat.simv2.simulation.mirror.net.minecraft.registry.tag.TagKey p0) {
        return false;
    }

    public boolean isStill(murat.simv2.simulation.mirror.net.minecraft.fluid.FluidState p0) {
        return false;
    }

    public boolean matchesType(murat.simv2.simulation.mirror.net.minecraft.fluid.Fluid p0) {
        return false;
    }

    public void onEntityCollision(murat.simv2.simulation.mirror.net.minecraft.world.World p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1, murat.simv2.simulation.mirror.net.minecraft.entity.Entity p2, murat.simv2.simulation.mirror.net.minecraft.entity.EntityCollisionHandler p3) {
    }

    public void onRandomTick(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1, murat.simv2.simulation.mirror.net.minecraft.fluid.FluidState p2, murat.simv2.simulation.mirror.net.minecraft.util.math.random.Random p3) {
    }

    public void onScheduledTick(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1, murat.simv2.simulation.mirror.net.minecraft.block.BlockState p2, murat.simv2.simulation.mirror.net.minecraft.fluid.FluidState p3) {
    }

    public void randomDisplayTick(murat.simv2.simulation.mirror.net.minecraft.world.World p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1, murat.simv2.simulation.mirror.net.minecraft.fluid.FluidState p2, murat.simv2.simulation.mirror.net.minecraft.util.math.random.Random p3) {
    }

    public void setDefaultState(murat.simv2.simulation.mirror.net.minecraft.fluid.FluidState p0) {
    }

    public murat.simv2.simulation.mirror.net.minecraft.block.BlockState toBlockState(murat.simv2.simulation.mirror.net.minecraft.fluid.FluidState p0) {
        return null;
    }

    // END GENERATED MIRROR NESTED STUBS
}
