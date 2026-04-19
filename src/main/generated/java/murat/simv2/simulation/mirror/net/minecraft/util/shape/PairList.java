package murat.simv2.simulation.mirror.net.minecraft.util.shape;

// Generated mirror stub for simulation closure.
public interface PairList {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public boolean forEachPair(murat.simv2.simulation.mirror.net.minecraft.util.shape.PairList.Consumer p0);

    public it.unimi.dsi.fastutil.doubles.DoubleList getPairs();

    public int size();

    public static interface Consumer {
        public boolean merge(int p0, int p1, int p2);

    }

    // END GENERATED MIRROR NESTED STUBS
}
