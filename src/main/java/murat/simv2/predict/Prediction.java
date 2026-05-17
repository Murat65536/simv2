package murat.simv2.predict;

/**
 * The single flag that tells the sink-gating Mixins a movement prediction is
 * running.
 *
 * <p>It is a plain {@code static boolean}: prediction runs synchronously on the
 * client thread inside {@link MovementPredictor#predict}, the only place that
 * sets it, so there is no concurrency to guard. When it is {@code false} (every
 * real game tick) the gating Mixins are a single branch-predicted load — zero
 * measurable cost. When {@code true}, every side-effecting call enumerated in
 * {@code sink-callsites.txt} is suppressed, so ticking the disposable player
 * clone cannot emit packets/sounds/particles/game-events or open screens.
 */
public final class Prediction {

    /** True only inside the per-tick look-ahead loop. */
    public static boolean ACTIVE;

    private Prediction() {
    }
}
