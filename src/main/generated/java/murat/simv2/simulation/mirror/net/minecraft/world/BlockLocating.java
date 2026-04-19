package murat.simv2.simulation.mirror.net.minecraft.world;

// Generated mirror stub for simulation closure.
public class BlockLocating extends java.lang.Object {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public BlockLocating() {
    }

    public static java.util.Optional findColumnEnd(murat.simv2.simulation.mirror.net.minecraft.world.BlockView p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1, murat.simv2.simulation.mirror.net.minecraft.block.Block p2, murat.simv2.simulation.mirror.net.minecraft.util.math.Direction p3, murat.simv2.simulation.mirror.net.minecraft.block.Block p4) {
        return null;
    }

    public static com.mojang.datafixers.util.Pair findLargestRectangle(int[] p0) {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.world.BlockLocating.Rectangle getLargestRectangle(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Direction.Axis p1, int p2, murat.simv2.simulation.mirror.net.minecraft.util.math.Direction.Axis p3, int p4, java.util.function.Predicate p5) {
        return null;
    }

    public static int moveWhile(java.util.function.Predicate p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos.Mutable p1, murat.simv2.simulation.mirror.net.minecraft.util.math.Direction p2, int p3) {
        return 0;
    }

    public static class IntBounds extends java.lang.Object {
        public int max;
        public int min;

        public IntBounds(int p0, int p1) {
        }

        public java.lang.String toString() {
            return null;
        }

        public IntBounds() {
        }

    }

    public static class Rectangle extends java.lang.Object {
        public int height;
        public murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos lowerLeft;
        public int width;

        public Rectangle(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, int p1, int p2) {
        }

        public Rectangle() {
        }

    }

    // END GENERATED MIRROR NESTED STUBS
}
