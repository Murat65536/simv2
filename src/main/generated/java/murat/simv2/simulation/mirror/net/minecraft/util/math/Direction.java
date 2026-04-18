package murat.simv2.simulation.mirror.net.minecraft.util.math;

// Generated mirror stub for simulation closure.
public class Direction {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static murat.simv2.simulation.mirror.net.minecraft.util.math.Direction[] ALL;
    public static java.lang.Object CODEC;
    public static murat.simv2.simulation.mirror.net.minecraft.util.math.Direction DOWN;
    public static murat.simv2.simulation.mirror.net.minecraft.util.math.Direction EAST;
    public static murat.simv2.simulation.mirror.net.minecraft.util.math.Direction[] HORIZONTAL;
    public static com.mojang.serialization.Codec HORIZONTAL_QUARTER_TURNS_CODEC;
    public static com.mojang.serialization.Codec INDEX_CODEC;
    public static java.util.function.IntFunction INDEX_TO_VALUE_FUNCTION;
    public static murat.simv2.simulation.mirror.net.minecraft.util.math.Direction NORTH;
    public static java.lang.Object PACKET_CODEC;
    public static murat.simv2.simulation.mirror.net.minecraft.util.math.Direction SOUTH;
    public static murat.simv2.simulation.mirror.net.minecraft.util.math.Direction UP;
    public static murat.simv2.simulation.mirror.net.minecraft.util.math.Direction[] VALUES;
    public static com.mojang.serialization.Codec VERTICAL_CODEC;
    public static murat.simv2.simulation.mirror.net.minecraft.util.math.Direction WEST;
    public murat.simv2.simulation.mirror.net.minecraft.util.math.Direction.Axis axis;
    public murat.simv2.simulation.mirror.net.minecraft.util.math.Direction.AxisDirection direction;
    public murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d doubleVector;
    public static murat.simv2.simulation.mirror.net.minecraft.util.math.Direction[] field_11037;
    public org.joml.Vector3fc floatVector;
    public int horizontalQuarterTurns;
    public java.lang.String id;
    public int index;
    public int oppositeIndex;
    public murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3i vec3i;

    public Direction(java.lang.String p0, int p1, int p2, int p3, int p4, java.lang.String p5, murat.simv2.simulation.mirror.net.minecraft.util.math.Direction.AxisDirection p6, murat.simv2.simulation.mirror.net.minecraft.util.math.Direction.Axis p7, murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3i p8) {
    }

    public java.lang.String asString() {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.util.math.Direction byId(java.lang.String p0) {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.util.math.Direction byIndex(int p0) {
        return null;
    }

    public static com.mojang.serialization.Codec createBasicCodec(java.util.function.Supplier p0) {
        return null;
    }

    public static java.lang.Object createCodec(java.util.function.Supplier p0) {
        return null;
    }

    public static java.lang.Object createCodec(java.util.function.Supplier p0, java.util.function.Function p1) {
        return null;
    }

    public static java.util.function.Function createMapper(java.lang.Object[] p0, java.util.function.Function p1) {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.util.math.Direction fromHorizontalDegrees(double p0) {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.util.math.Direction fromHorizontalQuarterTurns(int p0) {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.util.math.Direction fromVector(int p0, int p1, int p2, murat.simv2.simulation.mirror.net.minecraft.util.math.Direction p3) {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.util.math.Direction fromVector(murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3i p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Direction p1) {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.util.math.Direction from(murat.simv2.simulation.mirror.net.minecraft.util.math.Direction.Axis p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Direction.AxisDirection p1) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.Direction.Axis getAxis() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.Direction.AxisDirection getDirection() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d getDoubleVector() {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.util.math.Direction[] getEntityFacingOrder(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0) {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.util.math.Direction getFacing(double p0, double p1, double p2) {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.util.math.Direction getFacing(float p0, float p1, float p2) {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.util.math.Direction getFacing(murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p0) {
        return null;
    }

    public org.joml.Vector3fc getFloatVector() {
        return null;
    }

    public static float getHorizontalDegreesOrThrow(murat.simv2.simulation.mirror.net.minecraft.util.math.Direction p0) {
        return 0.0F;
    }

    public int getHorizontalQuarterTurns() {
        return 0;
    }

    public java.lang.String getId() {
        return null;
    }

    public int getIndex() {
        return 0;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.util.math.Direction getLookDirectionForAxis(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Direction.Axis p1) {
        return null;
    }

    public int getOffsetX() {
        return 0;
    }

    public int getOffsetY() {
        return 0;
    }

    public int getOffsetZ() {
        return 0;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.Direction getOpposite() {
        return null;
    }

    public float getPositiveHorizontalDegrees() {
        return 0.0F;
    }

    public org.joml.Quaternionf getRotationQuaternion() {
        return null;
    }

    public org.joml.Vector3f getUnitVector() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3i getVector() {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.util.math.Direction get(murat.simv2.simulation.mirror.net.minecraft.util.math.Direction.AxisDirection p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Direction.Axis p1) {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.util.math.Direction[] listClosest(murat.simv2.simulation.mirror.net.minecraft.util.math.Direction p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Direction p1, murat.simv2.simulation.mirror.net.minecraft.util.math.Direction p2) {
        return null;
    }

    public int ordinal() {
        return 0;
    }

    public boolean pointsTo(float p0) {
        return false;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.util.math.Direction random(murat.simv2.simulation.mirror.net.minecraft.util.math.random.Random p0) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.Direction rotateClockwise(murat.simv2.simulation.mirror.net.minecraft.util.math.Direction.Axis p0) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.Direction rotateCounterclockwise(murat.simv2.simulation.mirror.net.minecraft.util.math.Direction.Axis p0) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.Direction rotateXClockwise() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.Direction rotateXCounterclockwise() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.Direction rotateYClockwise() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.Direction rotateYCounterclockwise() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.Direction rotateZClockwise() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.Direction rotateZCounterclockwise() {
        return null;
    }

    public static java.util.Collection shuffle(murat.simv2.simulation.mirror.net.minecraft.util.math.random.Random p0) {
        return null;
    }

    public static java.util.stream.Stream stream() {
        return null;
    }

    public static com.mojang.serialization.Keyable toKeyable(java.lang.Object[] p0) {
        return null;
    }

    public java.lang.String toString() {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.util.math.Direction transform(org.joml.Matrix4fc p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Direction p1) {
        return null;
    }

    public static com.mojang.serialization.DataResult validateVertical(murat.simv2.simulation.mirror.net.minecraft.util.math.Direction p0) {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.util.math.Direction valueOf(java.lang.String p0) {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.util.math.Direction[] values() {
        return null;
    }

    public Direction() {
    }

    public static class Axis {
        public static java.lang.Object CODEC;
        public static murat.simv2.simulation.mirror.net.minecraft.util.math.Direction.Axis[] VALUES;
        public static murat.simv2.simulation.mirror.net.minecraft.util.math.Direction.Axis X;
        public static murat.simv2.simulation.mirror.net.minecraft.util.math.Direction.Axis Y;
        public static murat.simv2.simulation.mirror.net.minecraft.util.math.Direction.Axis Z;
        public static murat.simv2.simulation.mirror.net.minecraft.util.math.Direction.Axis[] field_11049;
        public java.lang.String id;

        public Axis(java.lang.String p0, int p1, java.lang.String p2) {
        }

        public java.lang.String asString() {
            return null;
        }

        public boolean choose(boolean p0, boolean p1, boolean p2) {
            return false;
        }

        public double choose(double p0, double p1, double p2) {
            return 0.0D;
        }

        public int choose(int p0, int p1, int p2) {
            return 0;
        }

        public static com.mojang.serialization.Codec createBasicCodec(java.util.function.Supplier p0) {
            return null;
        }

        public static java.lang.Object createCodec(java.util.function.Supplier p0) {
            return null;
        }

        public static java.lang.Object createCodec(java.util.function.Supplier p0, java.util.function.Function p1) {
            return null;
        }

        public static java.util.function.Function createMapper(java.lang.Object[] p0, java.util.function.Function p1) {
            return null;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.util.math.Direction.Axis fromId(java.lang.String p0) {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.util.math.Direction[] getDirections() {
            return null;
        }

        public java.lang.String getId() {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.util.math.Direction getNegativeDirection() {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.util.math.Direction getPositiveDirection() {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.util.math.Direction.Type getType() {
            return null;
        }

        public boolean isHorizontal() {
            return false;
        }

        public boolean isVertical() {
            return false;
        }

        public int ordinal() {
            return 0;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.util.math.Direction.Axis pickRandomAxis(murat.simv2.simulation.mirror.net.minecraft.util.math.random.Random p0) {
            return null;
        }

        public boolean test(murat.simv2.simulation.mirror.net.minecraft.util.math.Direction p0) {
            return false;
        }

        public static com.mojang.serialization.Keyable toKeyable(java.lang.Object[] p0) {
            return null;
        }

        public java.lang.String toString() {
            return null;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.util.math.Direction.Axis valueOf(java.lang.String p0) {
            return null;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.util.math.Direction.Axis[] values() {
            return null;
        }

        public Axis() {
        }

    }

    public static class AxisDirection {
        public static murat.simv2.simulation.mirror.net.minecraft.util.math.Direction.AxisDirection NEGATIVE;
        public static murat.simv2.simulation.mirror.net.minecraft.util.math.Direction.AxisDirection POSITIVE;
        public java.lang.String description;
        public static murat.simv2.simulation.mirror.net.minecraft.util.math.Direction.AxisDirection[] field_11058;
        public int offset;

        public AxisDirection(java.lang.String p0, int p1, int p2, java.lang.String p3) {
        }

        public java.lang.String getDescription() {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.util.math.Direction.AxisDirection getOpposite() {
            return null;
        }

        public int offset() {
            return 0;
        }

        public int ordinal() {
            return 0;
        }

        public java.lang.String toString() {
            return null;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.util.math.Direction.AxisDirection valueOf(java.lang.String p0) {
            return null;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.util.math.Direction.AxisDirection[] values() {
            return null;
        }

        public AxisDirection() {
        }

    }

    public static class Type {
        public static murat.simv2.simulation.mirror.net.minecraft.util.math.Direction.Type HORIZONTAL;
        public static murat.simv2.simulation.mirror.net.minecraft.util.math.Direction.Type VERTICAL;
        public murat.simv2.simulation.mirror.net.minecraft.util.math.Direction.Axis[] axisArray;
        public murat.simv2.simulation.mirror.net.minecraft.util.math.Direction[] facingArray;
        public static murat.simv2.simulation.mirror.net.minecraft.util.math.Direction.Type[] field_11063;

        public Type(java.lang.String p0, int p1, murat.simv2.simulation.mirror.net.minecraft.util.math.Direction[] p2, murat.simv2.simulation.mirror.net.minecraft.util.math.Direction.Axis[] p3) {
        }

        public int getFacingCount() {
            return 0;
        }

        public java.util.List getShuffled(murat.simv2.simulation.mirror.net.minecraft.util.math.random.Random p0) {
            return null;
        }

        public java.util.Iterator iterator() {
            return null;
        }

        public int ordinal() {
            return 0;
        }

        public murat.simv2.simulation.mirror.net.minecraft.util.math.Direction.Axis randomAxis(murat.simv2.simulation.mirror.net.minecraft.util.math.random.Random p0) {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.util.math.Direction random(murat.simv2.simulation.mirror.net.minecraft.util.math.random.Random p0) {
            return null;
        }

        public java.util.stream.Stream stream() {
            return null;
        }

        public boolean test(murat.simv2.simulation.mirror.net.minecraft.util.math.Direction p0) {
            return false;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.util.math.Direction.Type valueOf(java.lang.String p0) {
            return null;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.util.math.Direction.Type[] values() {
            return null;
        }

        public Type() {
        }

    }

    // END GENERATED MIRROR NESTED STUBS
}
