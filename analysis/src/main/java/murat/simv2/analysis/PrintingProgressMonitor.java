package murat.simv2.analysis;

import com.ibm.wala.util.MonitorUtil.IProgressMonitor;

import java.util.Locale;
import java.util.function.LongSupplier;

public class PrintingProgressMonitor implements IProgressMonitor {

    private static final String DEFAULT_TASK_NAME = "call graph construction";
    private static final long DEFAULT_HEARTBEAT_MILLIS = 10_000L;

    private final LongSupplier nanoClock;
    private final long heartbeatNanos;

    private String taskName = DEFAULT_TASK_NAME;
    private int totalWork = 0;
    private int worked = 0;
    private int lastWorkedSnapshot = 0;
    private boolean cancelled = false;
    private long taskStartNanos = -1L;
    private long lastPrintedNanos = -1L;
    private boolean donePrinted = false;

    public PrintingProgressMonitor() {
        this(System::nanoTime, DEFAULT_HEARTBEAT_MILLIS);
    }

    PrintingProgressMonitor(LongSupplier nanoClock, long heartbeatMillis) {
        if (heartbeatMillis <= 0) {
            throw new IllegalArgumentException("heartbeatMillis must be > 0");
        }
        this.nanoClock = nanoClock;
        this.heartbeatNanos = heartbeatMillis * 1_000_000L;
    }

    @Override
    public void beginTask(String task, int totalWork) {
        this.taskName = normalizeTaskName(task);
        this.totalWork = totalWork;
        this.worked = 0;
        this.lastWorkedSnapshot = 0;
        this.donePrinted = false;
        long now = nanoClock.getAsLong();
        this.taskStartNanos = now;
        this.lastPrintedNanos = now;
        System.out.println("  [progress] Started " + taskName
            + (totalWork > 0 ? " (" + totalWork + " units)" : ""));
    }

    @Override
    public void subTask(String subTask) {
        if (subTask != null && !subTask.isEmpty()) {
            System.out.println("  [subtask] " + subTask);
        }
    }

    @Override
    public void worked(int units) {
        worked += units;
        long now = nanoClock.getAsLong();
        ensureTaskStarted(now);

        if (lastPrintedNanos < 0 || (now - lastPrintedNanos) >= heartbeatNanos) {
            printProgress(now);
        }
    }

    @Override
    public void cancel() {
        this.cancelled = true;
    }

    @Override
    public boolean isCanceled() {
        return cancelled;
    }

    @Override
    public String getCancelMessage() {
        return "Cancelled";
    }

    @Override
    public void done() {
        if (donePrinted) {
            return;
        }
        donePrinted = true;
        long now = nanoClock.getAsLong();
        ensureTaskStarted(now);
        if (worked != lastWorkedSnapshot) {
            printProgress(now);
        }
        double elapsedSeconds = Math.max(0L, now - taskStartNanos) / 1_000_000_000.0;
        System.out.println(String.format(Locale.ROOT,
            "  [done] %s in %.1fs", taskName, elapsedSeconds));
    }

    private void ensureTaskStarted(long nowNanos) {
        if (taskStartNanos >= 0) {
            return;
        }
        taskStartNanos = nowNanos;
        lastPrintedNanos = -1L;
    }

    private void printProgress(long nowNanos) {
        double elapsedSeconds = Math.max(0L, nowNanos - taskStartNanos) / 1_000_000_000.0;
        if (totalWork > 0) {
            int percent = (int) Math.min(100L, (worked * 100L) / totalWork);
            System.out.println(String.format(Locale.ROOT,
                "  [progress] %s: %d/%d (%d%%, %.1fs)",
                taskName, worked, totalWork, percent, elapsedSeconds));
        } else {
            System.out.println(String.format(Locale.ROOT,
                "  [progress] %s: %d units (%.1fs)",
                taskName, worked, elapsedSeconds));
        }
        lastPrintedNanos = nowNanos;
        lastWorkedSnapshot = worked;
    }

    private static String normalizeTaskName(String task) {
        if (task == null) {
            return DEFAULT_TASK_NAME;
        }
        String trimmed = task.trim();
        return trimmed.isEmpty() ? DEFAULT_TASK_NAME : trimmed;
    }
}
