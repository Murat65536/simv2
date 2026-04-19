package murat.simv2.simulation.mirror.net.minecraft.network.packet;

// Generated mirror stub for simulation closure.
public interface Packet<T> {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public void apply(java.lang.Object p0);

    public static murat.simv2.simulation.mirror.net.minecraft.network.codec.PacketCodec createCodec(java.lang.Object p0, java.lang.Object p1) {
        return null;
    }

    public java.lang.Object getPacketType();

    public boolean isWritingErrorSkippable();

    public boolean transitionsNetworkState();

    // END GENERATED MIRROR NESTED STUBS
}
