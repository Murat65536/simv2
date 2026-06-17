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
}
