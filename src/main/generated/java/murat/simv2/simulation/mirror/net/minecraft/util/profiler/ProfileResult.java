package murat.simv2.simulation.mirror.net.minecraft.util.profiler;

// Generated mirror stub for simulation closure.
public interface ProfileResult {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static final char SPLITTER_CHAR = '\0';

    public int getEndTick();

    public long getEndTime();

    public static java.lang.String getHumanReadableName(java.lang.String p0) {
        return null;
    }

    public java.lang.String getRootTimings();

    public int getStartTick();

    public long getStartTime();

    public int getTickSpan();

    public long getTimeSpan();

    public java.util.List getTimings(java.lang.String p0);

    public boolean save(java.nio.file.Path p0);

    // END GENERATED MIRROR NESTED STUBS
}
