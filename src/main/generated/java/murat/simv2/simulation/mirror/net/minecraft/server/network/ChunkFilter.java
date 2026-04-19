package murat.simv2.simulation.mirror.net.minecraft.server.network;

// Generated mirror stub for simulation closure.
public interface ChunkFilter {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static final murat.simv2.simulation.mirror.net.minecraft.server.network.ChunkFilter IGNORE_ALL = null;

    public static murat.simv2.simulation.mirror.net.minecraft.server.network.ChunkFilter cylindrical(murat.simv2.simulation.mirror.net.minecraft.util.math.ChunkPos p0, int p1) {
        return null;
    }

    public static void forEachChangedChunk(murat.simv2.simulation.mirror.net.minecraft.server.network.ChunkFilter p0, murat.simv2.simulation.mirror.net.minecraft.server.network.ChunkFilter p1, java.util.function.Consumer p2, java.util.function.Consumer p3) {
    }

    public void forEach(java.util.function.Consumer p0);

    public boolean isWithinDistanceExcludingEdge(int p0, int p1);

    public static boolean isWithinDistanceExcludingEdge(int p0, int p1, int p2, int p3, int p4) {
        return false;
    }

    public boolean isWithinDistance(int p0, int p1);

    public boolean isWithinDistance(int p0, int p1, boolean p2);

    public static boolean isWithinDistance(int p0, int p1, int p2, int p3, int p4, boolean p5) {
        return false;
    }

    public boolean isWithinDistance(murat.simv2.simulation.mirror.net.minecraft.util.math.ChunkPos p0);

    public static class Cylindrical implements murat.simv2.simulation.mirror.net.minecraft.server.network.ChunkFilter {
        public murat.simv2.simulation.mirror.net.minecraft.util.math.ChunkPos center;
        public int viewDistance;

        public Cylindrical(murat.simv2.simulation.mirror.net.minecraft.util.math.ChunkPos p0, int p1) {
        }

        public murat.simv2.simulation.mirror.net.minecraft.util.math.ChunkPos center() {
            return null;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.server.network.ChunkFilter cylindrical(murat.simv2.simulation.mirror.net.minecraft.util.math.ChunkPos p0, int p1) {
            return null;
        }

        public boolean equals(java.lang.Object p0) {
            return false;
        }

        public static void forEachChangedChunk(murat.simv2.simulation.mirror.net.minecraft.server.network.ChunkFilter p0, murat.simv2.simulation.mirror.net.minecraft.server.network.ChunkFilter p1, java.util.function.Consumer p2, java.util.function.Consumer p3) {
        }

        public void forEach(java.util.function.Consumer p0) {
        }

        public int getBottom() {
            return 0;
        }

        public int getLeft() {
            return 0;
        }

        public int getRight() {
            return 0;
        }

        public int getTop() {
            return 0;
        }

        public int hashCode() {
            return 0;
        }

        public boolean isWithinDistanceExcludingEdge(int p0, int p1) {
            return false;
        }

        public static boolean isWithinDistanceExcludingEdge(int p0, int p1, int p2, int p3, int p4) {
            return false;
        }

        public boolean isWithinDistance(int p0, int p1) {
            return false;
        }

        public boolean isWithinDistance(int p0, int p1, boolean p2) {
            return false;
        }

        public static boolean isWithinDistance(int p0, int p1, int p2, int p3, int p4, boolean p5) {
            return false;
        }

        public boolean isWithinDistance(murat.simv2.simulation.mirror.net.minecraft.util.math.ChunkPos p0) {
            return false;
        }

        public boolean overlaps(murat.simv2.simulation.mirror.net.minecraft.server.network.ChunkFilter.Cylindrical p0) {
            return false;
        }

        public java.lang.String toString() {
            return null;
        }

        public int viewDistance() {
            return 0;
        }

        public Cylindrical() {
        }

    }

    // END GENERATED MIRROR NESTED STUBS
}
