package murat.simv2.predict;

import murat.simv2.SimV2;

import java.util.HashSet;
import java.util.Set;

/**
 * Independent backstop that verifies a prediction left no trace on the real
 * game, and that the reused clone tracks reality.
 *
 * <p>Isolation itself is done by the {@code murat.simv2.mixin.boundary} Mixins,
 * which cancel every effect at the escape-root boundary
 * ({@code ClientWorld}/{@code World}/network/sound/{@code MinecraftClient})
 * while {@link Prediction#isActive()}. Those gates fire on essentially every
 * predicted tick <em>by design</em> — that is the suppression working, not a
 * leak — so they are deliberately <em>not</em> wired into this class.
 *
 * <p>This class is the version-robust catch-all instead, with two live
 * observers, each called from {@link MovementPredictor} and logged once
 * (dedup) with detail so the gap can be closed:
 *
 * <ul>
 *   <li>{@link #recordPlayerStateChange} — the real-player NBT byte-diff
 *   backstop (DEBUG only): any difference across a prediction window means an
 *   effect reached the real player through a path the boundary missed.</li>
 *   <li>{@link #recordFidelity} — the deferred 1-tick probe: the reused clone
 *   diverged from the real player after one identical tick, i.e. an
 *   unfaithful targeted seed or accumulated incremental-extend drift.</li>
 * </ul>
 */
public final class LeakDetector {

    /** Distinct sites already reported this session (dedup). */
    private static final Set<String> SEEN = new HashSet<>();

    private LeakDetector() {
    }

    /** Reports a real-player state change the boundary gates didn't prevent. */
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

    /**
     * Reports that the reused prediction clone diverged from the real
     * player after one identical tick — an unfaithful targeted seed or
     * accumulated incremental-extend drift (the clone-reuse failure mode,
     * the one with no other automated check). Logged once per session
     * (dedup) with the divergence magnitude and a concrete example.
     */
    public static void recordFidelity(double diff, String detail) {
        synchronized (SEEN) {
            if (!SEEN.add("fidelity")) {
                return;
            }
        }
        SimV2.LOGGER.warn(
            "[simv2] PREDICTION FIDELITY — reused clone diverged {} blocks "
                + "after 1 identical tick (clone seed incomplete): {}",
            String.format("%.6f", diff), detail);
    }
}
