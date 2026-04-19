package murat.simv2.simulation.mirror.net.minecraft.entity.data;

// Generated mirror stub for simulation closure.
public interface TrackedDataHandler<T> {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public murat.simv2.simulation.mirror.net.minecraft.network.codec.PacketCodec codec();

    public java.lang.Object copy(java.lang.Object p0);

    public murat.simv2.simulation.mirror.net.minecraft.entity.data.TrackedData create(int p0);

    public static murat.simv2.simulation.mirror.net.minecraft.entity.data.TrackedDataHandler create(murat.simv2.simulation.mirror.net.minecraft.network.codec.PacketCodec p0) {
        return null;
    }

    public static interface ImmutableHandler<T> extends murat.simv2.simulation.mirror.net.minecraft.entity.data.TrackedDataHandler {
        public murat.simv2.simulation.mirror.net.minecraft.network.codec.PacketCodec codec();

        public java.lang.Object copy(java.lang.Object p0);

        public murat.simv2.simulation.mirror.net.minecraft.entity.data.TrackedData create(int p0);

        public static murat.simv2.simulation.mirror.net.minecraft.entity.data.TrackedDataHandler create(murat.simv2.simulation.mirror.net.minecraft.network.codec.PacketCodec p0) {
            return null;
        }

    }

    // END GENERATED MIRROR NESTED STUBS
}
