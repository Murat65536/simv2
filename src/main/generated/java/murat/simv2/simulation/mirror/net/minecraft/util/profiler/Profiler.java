package murat.simv2.simulation.mirror.net.minecraft.util.profiler;

// Generated mirror stub for simulation closure.
public interface Profiler {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static final java.lang.String ROOT_NAME = null;

    public void addZoneText(java.lang.String p0);

    public void addZoneValue(long p0);

    public void endTick();

    public void markSampleType(murat.simv2.simulation.mirror.net.minecraft.util.profiler.SampleType p0);

    public void pop();

    public void push(java.lang.String p0);

    public void push(java.util.function.Supplier p0);

    public murat.simv2.simulation.mirror.net.minecraft.util.profiler.ScopedProfiler scoped(java.lang.String p0);

    public murat.simv2.simulation.mirror.net.minecraft.util.profiler.ScopedProfiler scoped(java.util.function.Supplier p0);

    public void setZoneColor(int p0);

    public void startTick();

    public void swap(java.lang.String p0);

    public void swap(java.util.function.Supplier p0);

    public static murat.simv2.simulation.mirror.net.minecraft.util.profiler.Profiler union(murat.simv2.simulation.mirror.net.minecraft.util.profiler.Profiler p0, murat.simv2.simulation.mirror.net.minecraft.util.profiler.Profiler p1) {
        return null;
    }

    public void visit(java.lang.String p0);

    public void visit(java.lang.String p0, int p1);

    public void visit(java.util.function.Supplier p0);

    public void visit(java.util.function.Supplier p0, int p1);

    public static class UnionProfiler extends java.lang.Object implements murat.simv2.simulation.mirror.net.minecraft.util.profiler.Profiler {
        public murat.simv2.simulation.mirror.net.minecraft.util.profiler.Profiler first;
        public murat.simv2.simulation.mirror.net.minecraft.util.profiler.Profiler second;

        public UnionProfiler(murat.simv2.simulation.mirror.net.minecraft.util.profiler.Profiler p0, murat.simv2.simulation.mirror.net.minecraft.util.profiler.Profiler p1) {
        }

        public void addZoneText(java.lang.String p0) {
        }

        public void addZoneValue(long p0) {
        }

        public void endTick() {
        }

        public void markSampleType(murat.simv2.simulation.mirror.net.minecraft.util.profiler.SampleType p0) {
        }

        public void pop() {
        }

        public void push(java.lang.String p0) {
        }

        public void push(java.util.function.Supplier p0) {
        }

        public murat.simv2.simulation.mirror.net.minecraft.util.profiler.ScopedProfiler scoped(java.lang.String p0) {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.util.profiler.ScopedProfiler scoped(java.util.function.Supplier p0) {
            return null;
        }

        public void setZoneColor(int p0) {
        }

        public void startTick() {
        }

        public void swap(java.lang.String p0) {
        }

        public void swap(java.util.function.Supplier p0) {
        }

        public static murat.simv2.simulation.mirror.net.minecraft.util.profiler.Profiler union(murat.simv2.simulation.mirror.net.minecraft.util.profiler.Profiler p0, murat.simv2.simulation.mirror.net.minecraft.util.profiler.Profiler p1) {
            return null;
        }

        public void visit(java.lang.String p0) {
        }

        public void visit(java.lang.String p0, int p1) {
        }

        public void visit(java.util.function.Supplier p0) {
        }

        public void visit(java.util.function.Supplier p0, int p1) {
        }

        public UnionProfiler() {
        }

    }

    // END GENERATED MIRROR NESTED STUBS
}
