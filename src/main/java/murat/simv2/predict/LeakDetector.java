package murat.simv2.predict;

import murat.simv2.SimV2;

import java.util.HashSet;
import java.util.Set;

/**
 * Independent backstop that verifies a prediction left no trace on the real
 * game.
 *
 * <p>Isolation itself is done by the {@code murat.simv2.mixin.boundary} Mixins,
 * which cancel every effect at the escape-root boundary
 * ({@code ClientWorld}/{@code World}/network/sound/{@code MinecraftClient})
 * while {@link Prediction#isActive()}. Those gates fire on essentially every
 * predicted tick <em>by design</em> — that is the suppression working, not a
 * leak — so they are deliberately <em>not</em> wired into this detector.
 *
 * <p>This class is the version-robust catch-all instead: {@link
 * #recordPlayerStateChange} is called from {@link MovementPredictor} when the
 * real player's NBT is not byte-identical across a prediction window. Any such
 * diff means an effect reached the real player through a path the boundary
 * missed; it is logged once (dedup) with detail so the gap can be closed. The
 * ad-hoc {@link #record} probe remains available for pinpointing a specific
 * leaking call path during debugging.
 */
public final class LeakDetector {

    /** Distinct leak sites already reported this session (dedup). */
    private static final Set<String> SEEN = new HashSet<>();

    /** Per-window cap so a pathological leak can't spam / stall a tick. */
    private static final int MAX_PER_WINDOW = 32;
    private static int windowCount;

    private LeakDetector() {
    }

    /** Called by {@link MovementPredictor} before the K-tick loop. */
    static void beginWindow() {
        windowCount = 0;
    }

    /**
     * Invoked from a choke Mixin when an effect fires during a prediction.
     * Logs the kind + the call path the first time that exact site is seen.
     */
    public static void record(String kind) {
        if (!Prediction.isActive() || windowCount++ >= MAX_PER_WINDOW) {
            return;
        }
        StackTraceElement[] st = new Throwable().getStackTrace();
        String key = kind + '@' + siteKey(st);
        synchronized (SEEN) {
            if (!SEEN.add(key)) {
                return;
            }
        }
        SimV2.LOGGER.warn(
            "[simv2] PREDICTION LEAK ({}) reached the real game — gate this path:\n{}",
            kind, trace(st));
    }

    /** Reports a real-player state change the choke probes didn't explain. */
    static void recordPlayerStateChange(String detail) {
        synchronized (SEEN) {
            if (!SEEN.add("player-state:" + detail)) {
                return;
            }
        }
        SimV2.LOGGER.warn(
            "[simv2] PREDICTION LEAK (player-state) — the real player changed "
                + "across a prediction: {}", detail);
    }

    /** First few non-simv2/non-JDK frames identify the leaking call path. */
    private static String siteKey(StackTraceElement[] st) {
        StringBuilder b = new StringBuilder();
        int n = 0;
        for (StackTraceElement e : st) {
            String c = e.getClassName();
            if (c.startsWith("murat.simv2.") || c.startsWith("java.")
                || c.startsWith("org.spongepowered.")) {
                continue;
            }
            b.append(c).append('.').append(e.getMethodName()).append(';');
            if (++n == 4) {
                break;
            }
        }
        return b.toString();
    }

    private static String trace(StackTraceElement[] st) {
        StringBuilder b = new StringBuilder();
        int n = 0;
        for (StackTraceElement e : st) {
            String c = e.getClassName();
            if (c.startsWith("murat.simv2.") || c.startsWith("org.spongepowered.")) {
                continue;
            }
            b.append("    at ").append(e).append('\n');
            if (++n == 14) {
                break;
            }
        }
        return b.toString();
    }
}
