package murat.simv2.simulation.mirror.net.minecraft.util.math.random;

// Generated mirror stub for simulation closure.
public class RandomSequencesState {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static java.lang.Object STATE_TYPE;
    public boolean includeSequenceId;
    public boolean includeWorldSeed;
    public int salt;
    public long seed;
    public java.util.Map<murat.simv2.simulation.mirror.net.minecraft.util.Identifier, java.lang.Object> sequences;

    public RandomSequencesState(long p0) {
    }

    public RandomSequencesState(long p0, int p1, boolean p2, boolean p3, java.util.Map p4) {
    }

    public static com.mojang.serialization.Codec createCodec(long p0) {
        return null;
    }

    public java.lang.Object createSequence(murat.simv2.simulation.mirror.net.minecraft.util.Identifier p0) {
        return null;
    }

    public java.lang.Object createSequence(murat.simv2.simulation.mirror.net.minecraft.util.Identifier p0, int p1, boolean p2, boolean p3) {
        return null;
    }

    public void forEachSequence(java.util.function.BiConsumer p0) {
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.random.Random getOrCreate(murat.simv2.simulation.mirror.net.minecraft.util.Identifier p0) {
        return null;
    }

    public boolean isDirty() {
        return false;
    }

    public void markDirty() {
    }

    public int resetAll() {
        return 0;
    }

    public void reset(murat.simv2.simulation.mirror.net.minecraft.util.Identifier p0) {
    }

    public void reset(murat.simv2.simulation.mirror.net.minecraft.util.Identifier p0, int p1, boolean p2, boolean p3) {
    }

    public void setDefaultParameters(int p0, boolean p1, boolean p2) {
    }

    public void setDirty(boolean p0) {
    }

    public RandomSequencesState() {
    }

    public static class WrappedRandom extends java.lang.Object implements murat.simv2.simulation.mirror.net.minecraft.util.math.random.Random {
        public murat.simv2.simulation.mirror.net.minecraft.util.math.random.RandomSequencesState field_44865;
        public murat.simv2.simulation.mirror.net.minecraft.util.math.random.Random random;

        public WrappedRandom(murat.simv2.simulation.mirror.net.minecraft.util.math.random.RandomSequencesState p0, murat.simv2.simulation.mirror.net.minecraft.util.math.random.Random p1) {
        }

        public static murat.simv2.simulation.mirror.net.minecraft.util.math.random.Random createLocal() {
            return null;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.util.math.random.Random createThreadSafe() {
            return null;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.util.math.random.Random create() {
            return null;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.util.math.random.Random create(long p0) {
            return null;
        }

        public boolean equals(java.lang.Object p0) {
            return false;
        }

        public int nextBetweenExclusive(int p0, int p1) {
            return 0;
        }

        public int nextBetween(int p0, int p1) {
            return 0;
        }

        public boolean nextBoolean() {
            return false;
        }

        public double nextDouble() {
            return 0.0D;
        }

        public float nextFloat() {
            return 0.0F;
        }

        public double nextGaussian() {
            return 0.0D;
        }

        public int nextInt() {
            return 0;
        }

        public int nextInt(int p0) {
            return 0;
        }

        public long nextLong() {
            return 0L;
        }

        public murat.simv2.simulation.mirror.net.minecraft.util.math.random.RandomSplitter nextSplitter() {
            return null;
        }

        public double nextTriangular(double p0, double p1) {
            return 0.0D;
        }

        public float nextTriangular(float p0, float p1) {
            return 0.0F;
        }

        public void setSeed(long p0) {
        }

        public void skip(int p0) {
        }

        public murat.simv2.simulation.mirror.net.minecraft.util.math.random.Random split() {
            return null;
        }

        public WrappedRandom() {
        }

    }

    // END GENERATED MIRROR NESTED STUBS
}
