package murat.simv2.simulation.mirror.net.minecraft.block;

// Generated mirror stub for simulation closure.
public interface Fertilizable {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public boolean canGrow(murat.simv2.simulation.mirror.net.minecraft.world.World p0, murat.simv2.simulation.mirror.net.minecraft.util.math.random.Random p1, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p2, murat.simv2.simulation.mirror.net.minecraft.block.BlockState p3);

    public static boolean canSpread(murat.simv2.simulation.mirror.net.minecraft.world.WorldView p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1, murat.simv2.simulation.mirror.net.minecraft.block.BlockState p2) {
        return false;
    }

    public static java.util.Optional findPosToSpreadTo(java.util.List p0, murat.simv2.simulation.mirror.net.minecraft.world.WorldView p1, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p2, murat.simv2.simulation.mirror.net.minecraft.block.BlockState p3) {
        return null;
    }

    public static java.util.Optional findPosToSpreadTo(murat.simv2.simulation.mirror.net.minecraft.world.World p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1, murat.simv2.simulation.mirror.net.minecraft.block.BlockState p2) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.block.Fertilizable.FertilizableType getFertilizableType();

    public murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos getFertilizeParticlePos(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0);

    public void grow(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0, murat.simv2.simulation.mirror.net.minecraft.util.math.random.Random p1, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p2, murat.simv2.simulation.mirror.net.minecraft.block.BlockState p3);

    public boolean isFertilizable(murat.simv2.simulation.mirror.net.minecraft.world.WorldView p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1, murat.simv2.simulation.mirror.net.minecraft.block.BlockState p2);

    public static class FertilizableType {
        public static murat.simv2.simulation.mirror.net.minecraft.block.Fertilizable.FertilizableType GROWER;
        public static murat.simv2.simulation.mirror.net.minecraft.block.Fertilizable.FertilizableType NEIGHBOR_SPREADER;
        public static murat.simv2.simulation.mirror.net.minecraft.block.Fertilizable.FertilizableType[] field_47836;

        public FertilizableType(java.lang.String p0, int p1) {
        }

        public int ordinal() {
            return 0;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.block.Fertilizable.FertilizableType valueOf(java.lang.String p0) {
            return null;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.block.Fertilizable.FertilizableType[] values() {
            return null;
        }

        public FertilizableType() {
        }

    }

    // END GENERATED MIRROR NESTED STUBS
}
