package murat.simv2.simulation.mirror.net.minecraft.util.function;

// Generated mirror stub for simulation closure.
public interface LazyIterationConsumer<T> {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public murat.simv2.simulation.mirror.net.minecraft.util.function.LazyIterationConsumer.NextIteration accept(java.lang.Object p0);

    public static murat.simv2.simulation.mirror.net.minecraft.util.function.LazyIterationConsumer forConsumer(java.util.function.Consumer p0) {
        return null;
    }

    public static class NextIteration {
        public static murat.simv2.simulation.mirror.net.minecraft.util.function.LazyIterationConsumer.NextIteration ABORT;
        public static murat.simv2.simulation.mirror.net.minecraft.util.function.LazyIterationConsumer.NextIteration CONTINUE;
        public static murat.simv2.simulation.mirror.net.minecraft.util.function.LazyIterationConsumer.NextIteration[] field_41285;

        public NextIteration(java.lang.String p0, int p1) {
        }

        public int ordinal() {
            return 0;
        }

        public boolean shouldAbort() {
            return false;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.util.function.LazyIterationConsumer.NextIteration valueOf(java.lang.String p0) {
            return null;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.util.function.LazyIterationConsumer.NextIteration[] values() {
            return null;
        }

        public NextIteration() {
        }

    }

    // END GENERATED MIRROR NESTED STUBS
}
