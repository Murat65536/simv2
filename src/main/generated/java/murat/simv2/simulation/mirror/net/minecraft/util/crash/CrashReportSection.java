package murat.simv2.simulation.mirror.net.minecraft.util.crash;

// Generated mirror stub for simulation closure.
public class CrashReportSection extends java.lang.Object {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public java.util.List<murat.simv2.simulation.mirror.net.minecraft.util.crash.CrashReportSection.Element> elements;
    public java.lang.StackTraceElement[] stackTrace;
    public java.lang.String title;

    public CrashReportSection(java.lang.String p0) {
    }

    public static void addBlockInfo(murat.simv2.simulation.mirror.net.minecraft.util.crash.CrashReportSection p0, murat.simv2.simulation.mirror.net.minecraft.world.HeightLimitView p1, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p2, murat.simv2.simulation.mirror.net.minecraft.block.BlockState p3) {
    }

    public static murat.simv2.simulation.mirror.net.minecraft.util.crash.CrashReportSection addBlockLocation(murat.simv2.simulation.mirror.net.minecraft.util.crash.CrashReportSection p0, murat.simv2.simulation.mirror.net.minecraft.world.HeightLimitView p1, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p2) {
        return null;
    }

    public void addStackTrace(java.lang.StringBuilder p0) {
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.crash.CrashReportSection add(java.lang.String p0, java.lang.Object p1) {
        return null;
    }

    public void add(java.lang.String p0, java.lang.Throwable p1) {
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.crash.CrashReportSection add(java.lang.String p0, murat.simv2.simulation.mirror.net.minecraft.util.crash.CrashCallable p1) {
        return null;
    }

    public static java.lang.String createPositionString(murat.simv2.simulation.mirror.net.minecraft.world.HeightLimitView p0, double p1, double p2, double p3) {
        return null;
    }

    public static java.lang.String createPositionString(murat.simv2.simulation.mirror.net.minecraft.world.HeightLimitView p0, int p1, int p2, int p3) {
        return null;
    }

    public static java.lang.String createPositionString(murat.simv2.simulation.mirror.net.minecraft.world.HeightLimitView p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1) {
        return null;
    }

    public java.lang.StackTraceElement[] getStackTrace() {
        return null;
    }

    public int initStackTrace(int p0) {
        return 0;
    }

    public boolean shouldGenerateStackTrace(java.lang.StackTraceElement p0, java.lang.StackTraceElement p1) {
        return false;
    }

    public void trimStackTraceEnd(int p0) {
    }

    public CrashReportSection() {
    }

    public static class Element extends java.lang.Object {
        public java.lang.String detail;
        public java.lang.String name;

        public Element(java.lang.String p0, java.lang.Object p1) {
        }

        public java.lang.String getDetail() {
            return null;
        }

        public java.lang.String getName() {
            return null;
        }

        public Element() {
        }

    }

    // END GENERATED MIRROR NESTED STUBS
}
