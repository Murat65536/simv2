package murat.simv2.predict;

/**
 * Tells the sink-gating / choke Mixins that a movement prediction is running
 * <em>on the calling thread</em>.
 *
 * <p>Activation is <strong>thread-scoped</strong>, not process-global. The
 * prediction runs synchronously, start to finish, on one thread inside
 * {@link MovementPredictor#predict} — but the integrated server (its own
 * <em>Server thread</em>) and netty (its own <em>IO thread</em>) tick
 * concurrently. A process-global flag would make the chokes cancel the real
 * server's block ticks and real outbound packets — maximal impact, the exact
 * opposite of the goal. So {@link #isActive()} is true only on the thread that
 * called {@link #begin()} and only until it calls {@link #end()}; every other
 * thread always sees {@code false} and runs the real game untouched.
 *
 * <p>{@code activeThread} is a single {@code volatile} reference. On a real
 * game tick {@link #isActive()} is one volatile load + an identity compare —
 * no allocation, no {@code ThreadLocal} map lookup, zero measurable cost.
 */
public final class Prediction {

    /** The thread currently inside the look-ahead loop, or {@code null}. */
    private static volatile Thread activeThread;

    private Prediction() {
    }

    /** Marks the current thread as predicting. Pair with {@link #end()}. */
    public static void begin() {
        activeThread = Thread.currentThread();
    }

    /** Clears prediction state. Safe to call unconditionally in a finally. */
    public static void end() {
        activeThread = null;
    }

    /**
     * True iff the <em>calling</em> thread is the one currently predicting.
     * Every gate/choke Mixin guards on this, so effects on the server/netty
     * threads are never suppressed.
     */
    public static boolean isActive() {
        return Thread.currentThread() == activeThread;
    }
}
