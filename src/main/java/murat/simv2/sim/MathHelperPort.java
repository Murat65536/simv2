package murat.simv2.sim;

/**
 * Bit-exact port of the parts of {@code net.minecraft.util.math.MathHelper} the movement physics
 * uses. Minecraft does not call {@link java.lang.Math#sin}/{@code cos}; it indexes a 65536-entry
 * FLOAT sine table on a FLOAT angle. Reproducing that table exactly is required for the simulated
 * trajectory to match the real one bit-for-bit over long rollouts (java.lang.Math.sin diverges by
 * up to ~1e-4 from the table, which compounds).
 *
 * <p>Verbatim from MathHelper (1.21.5): the table fill, the {@code 10430.378f} index scale, the
 * {@code +16384} quarter-turn offset for cosine, and the {@code & 0xFFFF} wrap.
 */
public final class MathHelperPort {
    private MathHelperPort() {
    }

    private static final float[] SINE_TABLE = new float[65536];

    static {
        for (int i = 0; i < 65536; i++) {
            SINE_TABLE[i] = (float) Math.sin((double) i * Math.PI * 2.0 / 65536.0);
        }
    }

    public static float sin(float value) {
        return SINE_TABLE[(int) (value * 10430.378F) & 65535];
    }

    public static float cos(float value) {
        return SINE_TABLE[(int) (value * 10430.378F + 16384.0F) & 65535];
    }

    /** MathHelper.clamp(double,double,double) — verbatim. Reached only in a dead climbing branch. */
    public static double clamp(double value, double min, double max) {
        return value < min ? min : (value > max ? max : value);
    }
}
