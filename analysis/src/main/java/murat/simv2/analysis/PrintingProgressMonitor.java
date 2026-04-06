package murat.simv2.analysis;

import com.ibm.wala.util.MonitorUtil.IProgressMonitor;

public class PrintingProgressMonitor implements IProgressMonitor {

    private String taskName = "";
    private int totalWork = 0;
    private int worked = 0;
    private boolean cancelled = false;

    @Override
    public void beginTask(String task, int totalWork) {
        this.taskName = task != null ? task : "";
        this.totalWork = totalWork;
        this.worked = 0;
        if (!taskName.isEmpty()) {
            System.out.println("  [progress] " + taskName + (totalWork > 0 ? " (" + totalWork + " units)" : ""));
        }
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
        if (totalWork > 0 && worked % 500 == 0) {
            System.out.println("  [progress] " + taskName + ": " + worked + "/" + totalWork
                + " (" + (worked * 100 / totalWork) + "%)");
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
        System.out.println("  [done] " + taskName);
    }
}
