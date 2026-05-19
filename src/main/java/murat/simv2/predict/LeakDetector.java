package murat.simv2.predict;

import murat.simv2.SimV2;

import java.util.HashSet;
import java.util.Set;

/**
 * Detects effects that escape a prediction to the real game.
 *
 * <p>Static analysis cannot enumerate the escaping-effect set
 * (demonstrated — it bottoms out in code WALA can't see). So instead we
 * <em>observe the effect boundary at runtime</em>: tiny Mixins at the
 * universal effect chokes ({@code SoundManager.play},
 * {@code ParticleManager.add*}, {@code ClientConnection.send},
 * {@code ClientWorld.addEntity}, {@code World.setBlockState}) call
 * {@link #record} while {@link Prediction#ACTIVE}. Because every sound /
 * particle / packet / spawn / block-write funnels through these regardless of
 * the call path, this catches leaks the caller-side generated gates miss
 * <em>and</em> automatically catches new ones after a Minecraft update — the
 * chokes are version-stable; a new leaking path still funnels through them and
 * its stack trace is logged.
 *
 * <p>Each distinct {@code (kind, call-site)} is logged once (the predicted K
 * ticks would otherwise repeat it 60×/frame); the logged stack pinpoints the
 * exact ungated path so it can be gated.
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
