package murat.simv2.simulation.mirror.net.minecraft.util.thread;

// Generated mirror stub for simulation closure.
public class ThreadExecutor<R> extends java.lang.Object implements murat.simv2.simulation.mirror.net.minecraft.util.thread.SampleableExecutor, murat.simv2.simulation.mirror.net.minecraft.util.thread.TaskExecutor {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static org.slf4j.Logger LOGGER;
    public int executionsInProgress;
    public static long field_52421;
    public java.lang.String name;
    public java.util.Queue<java.lang.Object> tasks;

    public ThreadExecutor(java.lang.String p0) {
    }

    public boolean canExecute(java.lang.Runnable p0) {
        return false;
    }

    public void cancelTasks() {
    }

    public void close() {
    }

    public java.util.List createSamplers() {
        return null;
    }

    public java.lang.Runnable createTask(java.lang.Runnable p0) {
        return null;
    }

    public java.util.concurrent.CompletableFuture executeAsync(java.util.function.Consumer p0) {
        return null;
    }

    public void executeSync(java.lang.Runnable p0) {
    }

    public void executeTask(java.lang.Runnable p0) {
    }

    public void execute(java.lang.Runnable p0) {
    }

    public java.lang.String getName() {
        return null;
    }

    public int getTaskCount() {
        return 0;
    }

    public java.lang.Thread getThread() {
        return null;
    }

    public static boolean isMemoryError(java.lang.Throwable p0) {
        return false;
    }

    public boolean isOnThread() {
        return false;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.util.thread.TaskExecutor of(java.lang.String p0, java.util.concurrent.Executor p1) {
        return null;
    }

    public void runTasks() {
    }

    public void runTasks(java.util.function.BooleanSupplier p0) {
    }

    public boolean runTask() {
        return false;
    }

    public void send(java.lang.Runnable p0) {
    }

    public boolean shouldExecuteAsync() {
        return false;
    }

    public void submitAndJoin(java.lang.Runnable p0) {
    }

    public java.util.concurrent.CompletableFuture submitAsync(java.lang.Runnable p0) {
        return null;
    }

    public java.util.concurrent.CompletableFuture submit(java.lang.Runnable p0) {
        return null;
    }

    public java.util.concurrent.CompletableFuture submit(java.util.function.Supplier p0) {
        return null;
    }

    public void waitForTasks() {
    }

    public ThreadExecutor() {
    }

    // END GENERATED MIRROR NESTED STUBS
}
