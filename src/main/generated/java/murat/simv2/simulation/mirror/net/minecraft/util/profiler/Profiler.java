package murat.simv2.simulation.mirror.net.minecraft.util.profiler;

// Generated mirror stub for simulation closure.
public interface Profiler {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static final java.lang.String ROOT_NAME = null;

    public default void addZoneText(java.lang.String p0) {
    }

    public default void addZoneValue(long p0) {
    }

    public default void endTick() {
    }

    public default void markSampleType(java.lang.Object p0) {
    }

    public default void pop() {
    }

    public default void push(java.lang.String p0) {
    }

    public default void push(java.util.function.Supplier p0) {
    }

    public default java.lang.Object scoped(java.lang.String p0) {
        return null;
    }

    public default java.lang.Object scoped(java.util.function.Supplier p0) {
        return null;
    }

    public default void setZoneColor(int p0) {
    }

    public default void startTick() {
    }

    public default void swap(java.lang.String p0) {
    }

    public default void swap(java.util.function.Supplier p0) {
    }

    public static murat.simv2.simulation.mirror.net.minecraft.util.profiler.Profiler union(murat.simv2.simulation.mirror.net.minecraft.util.profiler.Profiler p0, murat.simv2.simulation.mirror.net.minecraft.util.profiler.Profiler p1) {
        return null;
    }

    public default void visit(java.lang.String p0) {
    }

    public default void visit(java.lang.String p0, int p1) {
    }

    public default void visit(java.util.function.Supplier p0) {
    }

    public default void visit(java.util.function.Supplier p0, int p1) {
    }

    public static class UnionProfiler implements murat.simv2.simulation.mirror.net.minecraft.util.profiler.Profiler {
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

        public void markSampleType(java.lang.Object p0) {
        }

        public void pop() {
        }

        public void push(java.lang.String p0) {
        }

        public void push(java.util.function.Supplier p0) {
        }

        public java.lang.Object scoped(java.lang.String p0) {
            return null;
        }

        public java.lang.Object scoped(java.util.function.Supplier p0) {
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
