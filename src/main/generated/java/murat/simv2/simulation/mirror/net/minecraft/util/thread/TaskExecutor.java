package murat.simv2.simulation.mirror.net.minecraft.util.thread;

// Generated mirror stub for simulation closure.
public interface TaskExecutor<R> {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public void close();

    public java.lang.Runnable createTask(java.lang.Runnable p0);

    public java.util.concurrent.CompletableFuture executeAsync(java.util.function.Consumer p0);

    public java.lang.String getName();

    public static murat.simv2.simulation.mirror.net.minecraft.util.thread.TaskExecutor of(java.lang.String p0, java.util.concurrent.Executor p1) {
        return null;
    }

    public void send(java.lang.Runnable p0);

    // END GENERATED MIRROR NESTED STUBS
}
