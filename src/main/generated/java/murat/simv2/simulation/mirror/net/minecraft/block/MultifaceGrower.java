package murat.simv2.simulation.mirror.net.minecraft.block;

// Generated mirror stub for simulation closure.
public class MultifaceGrower extends java.lang.Object {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static murat.simv2.simulation.mirror.net.minecraft.block.MultifaceGrower.GrowType[] GROW_TYPES;
    public murat.simv2.simulation.mirror.net.minecraft.block.MultifaceGrower.GrowChecker growChecker;

    public MultifaceGrower(murat.simv2.simulation.mirror.net.minecraft.block.MultifaceBlock p0) {
    }

    public MultifaceGrower(murat.simv2.simulation.mirror.net.minecraft.block.MultifaceGrower.GrowChecker p0) {
    }

    public boolean canGrow(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0, murat.simv2.simulation.mirror.net.minecraft.world.BlockView p1, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p2, murat.simv2.simulation.mirror.net.minecraft.util.math.Direction p3) {
        return false;
    }

    public java.util.Optional getGrowPos(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0, murat.simv2.simulation.mirror.net.minecraft.world.BlockView p1, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p2, murat.simv2.simulation.mirror.net.minecraft.util.math.Direction p3, murat.simv2.simulation.mirror.net.minecraft.util.math.Direction p4, murat.simv2.simulation.mirror.net.minecraft.block.MultifaceGrower.GrowPosPredicate p5) {
        return null;
    }

    public long grow(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0, murat.simv2.simulation.mirror.net.minecraft.world.WorldAccess p1, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p2, boolean p3) {
        return 0L;
    }

    public long grow(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0, murat.simv2.simulation.mirror.net.minecraft.world.WorldAccess p1, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p2, murat.simv2.simulation.mirror.net.minecraft.util.math.Direction p3, boolean p4) {
        return 0L;
    }

    public java.util.Optional grow(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0, murat.simv2.simulation.mirror.net.minecraft.world.WorldAccess p1, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p2, murat.simv2.simulation.mirror.net.minecraft.util.math.Direction p3, murat.simv2.simulation.mirror.net.minecraft.util.math.Direction p4, boolean p5) {
        return null;
    }

    public java.util.Optional grow(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0, murat.simv2.simulation.mirror.net.minecraft.world.WorldAccess p1, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p2, murat.simv2.simulation.mirror.net.minecraft.util.math.Direction p3, murat.simv2.simulation.mirror.net.minecraft.util.math.random.Random p4, boolean p5) {
        return null;
    }

    public java.util.Optional grow(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0, murat.simv2.simulation.mirror.net.minecraft.world.WorldAccess p1, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p2, murat.simv2.simulation.mirror.net.minecraft.util.math.random.Random p3) {
        return null;
    }

    public java.util.Optional place(murat.simv2.simulation.mirror.net.minecraft.world.WorldAccess p0, murat.simv2.simulation.mirror.net.minecraft.block.MultifaceGrower.GrowPos p1, boolean p2) {
        return null;
    }

    public MultifaceGrower() {
    }

    public static interface GrowChecker {
        public boolean canGrow(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0);

        public boolean canGrow(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Direction p1);

        public boolean canGrow(murat.simv2.simulation.mirror.net.minecraft.world.BlockView p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1, murat.simv2.simulation.mirror.net.minecraft.block.MultifaceGrower.GrowPos p2);

        public murat.simv2.simulation.mirror.net.minecraft.block.MultifaceGrower.GrowType[] getGrowTypes();

        public murat.simv2.simulation.mirror.net.minecraft.block.BlockState getStateWithDirection(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0, murat.simv2.simulation.mirror.net.minecraft.world.BlockView p1, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p2, murat.simv2.simulation.mirror.net.minecraft.util.math.Direction p3);

        public boolean hasDirection(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Direction p1);

        public boolean place(murat.simv2.simulation.mirror.net.minecraft.world.WorldAccess p0, murat.simv2.simulation.mirror.net.minecraft.block.MultifaceGrower.GrowPos p1, murat.simv2.simulation.mirror.net.minecraft.block.BlockState p2, boolean p3);

    }

    public static class GrowPos {
        public murat.simv2.simulation.mirror.net.minecraft.util.math.Direction face;
        public murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos pos;

        public GrowPos(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Direction p1) {
        }

        public boolean equals(java.lang.Object p0) {
            return false;
        }

        public murat.simv2.simulation.mirror.net.minecraft.util.math.Direction face() {
            return null;
        }

        public int hashCode() {
            return 0;
        }

        public murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos pos() {
            return null;
        }

        public java.lang.String toString() {
            return null;
        }

        public GrowPos() {
        }

    }

    public static interface GrowPosPredicate {
        public boolean test(murat.simv2.simulation.mirror.net.minecraft.world.BlockView p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1, murat.simv2.simulation.mirror.net.minecraft.block.MultifaceGrower.GrowPos p2);

    }

    public static class GrowType {
        public static murat.simv2.simulation.mirror.net.minecraft.block.MultifaceGrower.GrowType SAME_PLANE;
        public static murat.simv2.simulation.mirror.net.minecraft.block.MultifaceGrower.GrowType SAME_POSITION;
        public static murat.simv2.simulation.mirror.net.minecraft.block.MultifaceGrower.GrowType WRAP_AROUND;
        public static murat.simv2.simulation.mirror.net.minecraft.block.MultifaceGrower.GrowType[] field_37601;

        public GrowType(java.lang.String p0, int p1) {
        }

        public murat.simv2.simulation.mirror.net.minecraft.block.MultifaceGrower.GrowPos getGrowPos(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Direction p1, murat.simv2.simulation.mirror.net.minecraft.util.math.Direction p2) {
            return null;
        }

        public int ordinal() {
            return 0;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.block.MultifaceGrower.GrowType valueOf(java.lang.String p0) {
            return null;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.block.MultifaceGrower.GrowType[] values() {
            return null;
        }

        public GrowType() {
        }

    }

    public static class LichenGrowChecker extends java.lang.Object implements murat.simv2.simulation.mirror.net.minecraft.block.MultifaceGrower.GrowChecker {
        public murat.simv2.simulation.mirror.net.minecraft.block.MultifaceBlock lichen;

        public LichenGrowChecker(murat.simv2.simulation.mirror.net.minecraft.block.MultifaceBlock p0) {
        }

        public boolean canGrow(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0) {
            return false;
        }

        public boolean canGrow(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Direction p1) {
            return false;
        }

        public boolean canGrow(murat.simv2.simulation.mirror.net.minecraft.world.BlockView p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1, murat.simv2.simulation.mirror.net.minecraft.block.MultifaceGrower.GrowPos p2) {
            return false;
        }

        public boolean canGrow(murat.simv2.simulation.mirror.net.minecraft.world.BlockView p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p2, murat.simv2.simulation.mirror.net.minecraft.util.math.Direction p3, murat.simv2.simulation.mirror.net.minecraft.block.BlockState p4) {
            return false;
        }

        public murat.simv2.simulation.mirror.net.minecraft.block.MultifaceGrower.GrowType[] getGrowTypes() {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.block.BlockState getStateWithDirection(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0, murat.simv2.simulation.mirror.net.minecraft.world.BlockView p1, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p2, murat.simv2.simulation.mirror.net.minecraft.util.math.Direction p3) {
            return null;
        }

        public boolean hasDirection(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Direction p1) {
            return false;
        }

        public boolean place(murat.simv2.simulation.mirror.net.minecraft.world.WorldAccess p0, murat.simv2.simulation.mirror.net.minecraft.block.MultifaceGrower.GrowPos p1, murat.simv2.simulation.mirror.net.minecraft.block.BlockState p2, boolean p3) {
            return false;
        }

        public LichenGrowChecker() {
        }

    }

    // END GENERATED MIRROR NESTED STUBS
}
