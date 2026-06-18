package murat.simv2.sim;

/**
 * Runtime helpers the generated movement code emits against. Reproduces JVM bytecode semantics
 * that have no direct Java operator — currently the {@code dcmpg}/{@code dcmpl} (and float)
 * comparison opcodes, whose NaN tie-breaking differs (cmpg yields +1 on NaN, cmpl yields -1).
 */
public final class SimRuntime {
    private SimRuntime() {
    }

    /** JVM dcmpg/fcmpg: a<b -> -1, a==b -> 0, a>b -> 1, NaN -> 1. */
    public static int cmpg(double a, double b) {
        if (a < b) return -1;
        if (a > b) return 1;
        if (a == b) return 0;
        return 1; // NaN
    }

    /** JVM dcmpl/fcmpl: a<b -> -1, a==b -> 0, a>b -> 1, NaN -> -1. */
    public static int cmpl(double a, double b) {
        if (a < b) return -1;
        if (a > b) return 1;
        if (a == b) return 0;
        return -1; // NaN
    }

    // --- Fluid-height map (Entity.fluidHeight: Object2DoubleMap<TagKey>) --------------------------
    // Minecraft keys per-tick fluid depth by fluid tag in an Object2DoubleMap; the sim models exactly
    // the two tags in scope as the SimPlayerState.fluidHeightWater/Lava slots. These reproduce the
    // map's clear()/put(tag,d)/getDouble(tag) the generated fluid scan emits, dispatching on the tag.

    /** {@code fluidHeight.clear()} — reset both per-tag depths to 0 at the start of updateWaterState. */
    public static void clearFluidHeight(SimPlayerState s) {
        s.fluidHeightWater = 0.0;
        s.fluidHeightLava = 0.0;
    }

    /** {@code fluidHeight.put(tag, height)} — record this tick's depth for the tag's slot. */
    public static void putFluidHeight(SimPlayerState s, SimWorld.FluidTag tag, double height) {
        if (tag == SimWorld.FluidTag.WATER) {
            s.fluidHeightWater = height;
        } else {
            s.fluidHeightLava = height;
        }
    }

    /** {@code fluidHeight.getDouble(tag)} — read the recorded depth for the tag's slot (0 if none). */
    public static double getFluidHeight(SimPlayerState s, SimWorld.FluidTag tag) {
        return tag == SimWorld.FluidTag.WATER ? s.fluidHeightWater : s.fluidHeightLava;
    }
}
