package murat.simv2.simulation.mirror.net.minecraft.world.entity;

// Generated mirror stub for simulation closure.
public interface EntityLookup<T> {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public void forEachIntersects(murat.simv2.simulation.mirror.net.minecraft.util.TypeFilter p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Box p1, murat.simv2.simulation.mirror.net.minecraft.util.function.LazyIterationConsumer p2);

    public void forEachIntersects(murat.simv2.simulation.mirror.net.minecraft.util.math.Box p0, java.util.function.Consumer p1);

    public void forEach(murat.simv2.simulation.mirror.net.minecraft.util.TypeFilter p0, murat.simv2.simulation.mirror.net.minecraft.util.function.LazyIterationConsumer p1);

    public murat.simv2.simulation.mirror.net.minecraft.world.entity.EntityLike get(int p0);

    public murat.simv2.simulation.mirror.net.minecraft.world.entity.EntityLike get(java.util.UUID p0);

    public java.lang.Iterable iterate();

    // END GENERATED MIRROR NESTED STUBS
}
