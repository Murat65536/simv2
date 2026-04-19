package murat.simv2.simulation.mirror.net.minecraft.world.block;

// Generated mirror stub for simulation closure.
public class WireOrientation extends java.lang.Object {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static murat.simv2.simulation.mirror.net.minecraft.network.codec.PacketCodec<io.netty.buffer.ByteBuf, murat.simv2.simulation.mirror.net.minecraft.world.block.WireOrientation> PACKET_CODEC;
    public static murat.simv2.simulation.mirror.net.minecraft.world.block.WireOrientation[] VALUES;
    public java.util.List<murat.simv2.simulation.mirror.net.minecraft.util.math.Direction> directionsByPriority;
    public murat.simv2.simulation.mirror.net.minecraft.util.math.Direction front;
    public java.util.List<murat.simv2.simulation.mirror.net.minecraft.util.math.Direction> horizontalDirections;
    public int ordinal;
    public murat.simv2.simulation.mirror.net.minecraft.util.math.Direction right;
    public java.util.Map<murat.simv2.simulation.mirror.net.minecraft.util.math.Direction, murat.simv2.simulation.mirror.net.minecraft.world.block.WireOrientation> siblingsByFront;
    public java.util.Map<murat.simv2.simulation.mirror.net.minecraft.world.block.WireOrientation.SideBias, murat.simv2.simulation.mirror.net.minecraft.world.block.WireOrientation> siblingsBySideBias;
    public java.util.Map<murat.simv2.simulation.mirror.net.minecraft.util.math.Direction, murat.simv2.simulation.mirror.net.minecraft.world.block.WireOrientation> siblingsByUp;
    public murat.simv2.simulation.mirror.net.minecraft.world.block.WireOrientation.SideBias sideBias;
    public murat.simv2.simulation.mirror.net.minecraft.util.math.Direction up;
    public java.util.List<murat.simv2.simulation.mirror.net.minecraft.util.math.Direction> verticalDirections;

    public WireOrientation(murat.simv2.simulation.mirror.net.minecraft.util.math.Direction p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Direction p1, murat.simv2.simulation.mirror.net.minecraft.world.block.WireOrientation.SideBias p2) {
    }

    public static murat.simv2.simulation.mirror.net.minecraft.world.block.WireOrientation fromOrdinal(int p0) {
        return null;
    }

    public java.util.List getDirectionsByPriority() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.Direction getFront() {
        return null;
    }

    public java.util.List getHorizontalDirections() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.Direction getRight() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.world.block.WireOrientation.SideBias getSideBias() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.Direction getUp() {
        return null;
    }

    public java.util.List getVerticalDirections() {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.world.block.WireOrientation initializeValuesArray(murat.simv2.simulation.mirror.net.minecraft.world.block.WireOrientation p0, murat.simv2.simulation.mirror.net.minecraft.world.block.WireOrientation[] p1) {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.world.block.WireOrientation of(murat.simv2.simulation.mirror.net.minecraft.util.math.Direction p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Direction p1, murat.simv2.simulation.mirror.net.minecraft.world.block.WireOrientation.SideBias p2) {
        return null;
    }

    public static int ordinalFromComponents(murat.simv2.simulation.mirror.net.minecraft.util.math.Direction p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Direction p1, murat.simv2.simulation.mirror.net.minecraft.world.block.WireOrientation.SideBias p2) {
        return 0;
    }

    public int ordinal() {
        return 0;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.world.block.WireOrientation random(murat.simv2.simulation.mirror.net.minecraft.util.math.random.Random p0) {
        return null;
    }

    public java.lang.String toString() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.world.block.WireOrientation withFrontAndSideBias(murat.simv2.simulation.mirror.net.minecraft.util.math.Direction p0) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.world.block.WireOrientation withFrontIfNotUp(murat.simv2.simulation.mirror.net.minecraft.util.math.Direction p0) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.world.block.WireOrientation withFront(murat.simv2.simulation.mirror.net.minecraft.util.math.Direction p0) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.world.block.WireOrientation withOppositeSideBias() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.world.block.WireOrientation withSideBias(murat.simv2.simulation.mirror.net.minecraft.world.block.WireOrientation.SideBias p0) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.world.block.WireOrientation withUp(murat.simv2.simulation.mirror.net.minecraft.util.math.Direction p0) {
        return null;
    }

    public WireOrientation() {
    }

    public static class SideBias {
        public static murat.simv2.simulation.mirror.net.minecraft.world.block.WireOrientation.SideBias LEFT;
        public static murat.simv2.simulation.mirror.net.minecraft.world.block.WireOrientation.SideBias RIGHT;
        public static murat.simv2.simulation.mirror.net.minecraft.world.block.WireOrientation.SideBias[] field_52684;
        public java.lang.String name;

        public SideBias(java.lang.String p0, int p1, java.lang.String p2) {
        }

        public murat.simv2.simulation.mirror.net.minecraft.world.block.WireOrientation.SideBias opposite() {
            return null;
        }

        public int ordinal() {
            return 0;
        }

        public java.lang.String toString() {
            return null;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.world.block.WireOrientation.SideBias valueOf(java.lang.String p0) {
            return null;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.world.block.WireOrientation.SideBias[] values() {
            return null;
        }

        public SideBias() {
        }

    }

    // END GENERATED MIRROR NESTED STUBS
}
