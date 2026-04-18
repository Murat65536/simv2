package murat.simv2.simulation.mirror.net.minecraft.util.math;

// Generated mirror stub for simulation closure.
public class DirectionTransformation {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static murat.simv2.simulation.mirror.net.minecraft.util.math.Direction.Axis[] AXES;
    public static murat.simv2.simulation.mirror.net.minecraft.util.math.DirectionTransformation[][] BY_ROTATIONS;
    public static murat.simv2.simulation.mirror.net.minecraft.util.math.DirectionTransformation[][] COMBINATIONS;
    public static murat.simv2.simulation.mirror.net.minecraft.util.math.DirectionTransformation IDENTITY;
    public static murat.simv2.simulation.mirror.net.minecraft.util.math.DirectionTransformation[] INVERSES;
    public static murat.simv2.simulation.mirror.net.minecraft.util.math.DirectionTransformation INVERSION;
    public static murat.simv2.simulation.mirror.net.minecraft.util.math.DirectionTransformation INVERT_X;
    public static murat.simv2.simulation.mirror.net.minecraft.util.math.DirectionTransformation INVERT_Y;
    public static murat.simv2.simulation.mirror.net.minecraft.util.math.DirectionTransformation INVERT_Z;
    public static murat.simv2.simulation.mirror.net.minecraft.util.math.DirectionTransformation ROT_120_NNN;
    public static murat.simv2.simulation.mirror.net.minecraft.util.math.DirectionTransformation ROT_120_NNP;
    public static murat.simv2.simulation.mirror.net.minecraft.util.math.DirectionTransformation ROT_120_NPN;
    public static murat.simv2.simulation.mirror.net.minecraft.util.math.DirectionTransformation ROT_120_NPP;
    public static murat.simv2.simulation.mirror.net.minecraft.util.math.DirectionTransformation ROT_120_PNN;
    public static murat.simv2.simulation.mirror.net.minecraft.util.math.DirectionTransformation ROT_120_PNP;
    public static murat.simv2.simulation.mirror.net.minecraft.util.math.DirectionTransformation ROT_120_PPN;
    public static murat.simv2.simulation.mirror.net.minecraft.util.math.DirectionTransformation ROT_120_PPP;
    public static murat.simv2.simulation.mirror.net.minecraft.util.math.DirectionTransformation ROT_180_EDGE_XY_NEG;
    public static murat.simv2.simulation.mirror.net.minecraft.util.math.DirectionTransformation ROT_180_EDGE_XY_POS;
    public static murat.simv2.simulation.mirror.net.minecraft.util.math.DirectionTransformation ROT_180_EDGE_XZ_NEG;
    public static murat.simv2.simulation.mirror.net.minecraft.util.math.DirectionTransformation ROT_180_EDGE_XZ_POS;
    public static murat.simv2.simulation.mirror.net.minecraft.util.math.DirectionTransformation ROT_180_EDGE_YZ_NEG;
    public static murat.simv2.simulation.mirror.net.minecraft.util.math.DirectionTransformation ROT_180_EDGE_YZ_POS;
    public static murat.simv2.simulation.mirror.net.minecraft.util.math.DirectionTransformation ROT_180_FACE_XY;
    public static murat.simv2.simulation.mirror.net.minecraft.util.math.DirectionTransformation ROT_180_FACE_XZ;
    public static murat.simv2.simulation.mirror.net.minecraft.util.math.DirectionTransformation ROT_180_FACE_YZ;
    public static murat.simv2.simulation.mirror.net.minecraft.util.math.DirectionTransformation ROT_60_REF_NNN;
    public static murat.simv2.simulation.mirror.net.minecraft.util.math.DirectionTransformation ROT_60_REF_NNP;
    public static murat.simv2.simulation.mirror.net.minecraft.util.math.DirectionTransformation ROT_60_REF_NPN;
    public static murat.simv2.simulation.mirror.net.minecraft.util.math.DirectionTransformation ROT_60_REF_NPP;
    public static murat.simv2.simulation.mirror.net.minecraft.util.math.DirectionTransformation ROT_60_REF_PNN;
    public static murat.simv2.simulation.mirror.net.minecraft.util.math.DirectionTransformation ROT_60_REF_PNP;
    public static murat.simv2.simulation.mirror.net.minecraft.util.math.DirectionTransformation ROT_60_REF_PPN;
    public static murat.simv2.simulation.mirror.net.minecraft.util.math.DirectionTransformation ROT_60_REF_PPP;
    public static murat.simv2.simulation.mirror.net.minecraft.util.math.DirectionTransformation ROT_90_REF_X_NEG;
    public static murat.simv2.simulation.mirror.net.minecraft.util.math.DirectionTransformation ROT_90_REF_X_POS;
    public static murat.simv2.simulation.mirror.net.minecraft.util.math.DirectionTransformation ROT_90_REF_Y_NEG;
    public static murat.simv2.simulation.mirror.net.minecraft.util.math.DirectionTransformation ROT_90_REF_Y_POS;
    public static murat.simv2.simulation.mirror.net.minecraft.util.math.DirectionTransformation ROT_90_REF_Z_NEG;
    public static murat.simv2.simulation.mirror.net.minecraft.util.math.DirectionTransformation ROT_90_REF_Z_POS;
    public static murat.simv2.simulation.mirror.net.minecraft.util.math.DirectionTransformation ROT_90_X_NEG;
    public static murat.simv2.simulation.mirror.net.minecraft.util.math.DirectionTransformation ROT_90_X_POS;
    public static murat.simv2.simulation.mirror.net.minecraft.util.math.DirectionTransformation ROT_90_Y_NEG;
    public static murat.simv2.simulation.mirror.net.minecraft.util.math.DirectionTransformation ROT_90_Y_POS;
    public static murat.simv2.simulation.mirror.net.minecraft.util.math.DirectionTransformation ROT_90_Z_NEG;
    public static murat.simv2.simulation.mirror.net.minecraft.util.math.DirectionTransformation ROT_90_Z_POS;
    public static murat.simv2.simulation.mirror.net.minecraft.util.math.DirectionTransformation SWAP_NEG_XY;
    public static murat.simv2.simulation.mirror.net.minecraft.util.math.DirectionTransformation SWAP_NEG_XZ;
    public static murat.simv2.simulation.mirror.net.minecraft.util.math.DirectionTransformation SWAP_NEG_YZ;
    public static murat.simv2.simulation.mirror.net.minecraft.util.math.DirectionTransformation SWAP_XY;
    public static murat.simv2.simulation.mirror.net.minecraft.util.math.DirectionTransformation SWAP_XZ;
    public static murat.simv2.simulation.mirror.net.minecraft.util.math.DirectionTransformation SWAP_YZ;
    public murat.simv2.simulation.mirror.net.minecraft.util.math.AxisTransformation axisTransformation;
    public static murat.simv2.simulation.mirror.net.minecraft.util.math.DirectionTransformation[] field_23298;
    public boolean flipX;
    public boolean flipY;
    public boolean flipZ;
    public java.util.Map mappings;
    public org.joml.Matrix3fc matrix;
    public java.lang.String name;

    public DirectionTransformation(java.lang.String p0, int p1, java.lang.String p2, murat.simv2.simulation.mirror.net.minecraft.util.math.AxisTransformation p3, boolean p4, boolean p5, boolean p6) {
    }

    public java.lang.String asString() {
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

    public static murat.simv2.simulation.mirror.net.minecraft.util.math.DirectionTransformation fromRotations(java.lang.Object p0, java.lang.Object p1) {
        return null;
    }

    public it.unimi.dsi.fastutil.booleans.BooleanList getAxisFlips() {
        return null;
    }

    public org.joml.Matrix3fc getMatrix() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.DirectionTransformation inverse() {
        return null;
    }

    public java.lang.Object mapJigsawOrientation(java.lang.Object p0) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.Direction map(murat.simv2.simulation.mirror.net.minecraft.util.math.Direction p0) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.Direction.Axis map(murat.simv2.simulation.mirror.net.minecraft.util.math.Direction.Axis p0) {
        return null;
    }

    public int ordinal() {
        return 0;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.DirectionTransformation prepend(murat.simv2.simulation.mirror.net.minecraft.util.math.DirectionTransformation p0) {
        return null;
    }

    public boolean shouldFlipDirection(murat.simv2.simulation.mirror.net.minecraft.util.math.Direction.Axis p0) {
        return false;
    }

    public static com.mojang.serialization.Keyable toKeyable(java.lang.Object[] p0) {
        return null;
    }

    public java.lang.String toString() {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.util.math.DirectionTransformation valueOf(java.lang.String p0) {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.util.math.DirectionTransformation[] values() {
        return null;
    }

    public DirectionTransformation() {
    }

    // END GENERATED MIRROR NESTED STUBS
}
