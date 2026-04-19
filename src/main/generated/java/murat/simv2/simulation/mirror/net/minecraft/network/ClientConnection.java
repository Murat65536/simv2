package murat.simv2.simulation.mirror.net.minecraft.network;

// Generated mirror stub for simulation closure.
public class ClientConnection extends io.netty.channel.SimpleChannelInboundHandler {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static java.lang.Object C2S_HANDSHAKE_STATE;
    public static java.util.function.Supplier<io.netty.channel.nio.NioEventLoopGroup> CLIENT_IO_GROUP;
    public static float CURRENT_PACKET_COUNTER_WEIGHT;
    public static java.util.function.Supplier<io.netty.channel.epoll.EpollEventLoopGroup> EPOLL_CLIENT_IO_GROUP;
    public static java.util.function.Supplier<io.netty.channel.DefaultEventLoopGroup> LOCAL_CLIENT_IO_GROUP;
    public static org.slf4j.Logger LOGGER;
    public static org.slf4j.Marker NETWORK_MARKER;
    public static org.slf4j.Marker NETWORK_PACKETS_MARKER;
    public static org.slf4j.Marker PACKET_RECEIVED_MARKER;
    public static org.slf4j.Marker PACKET_SENT_MARKER;
    public java.net.SocketAddress address;
    public float averagePacketsReceived;
    public float averagePacketsSent;
    public io.netty.channel.Channel channel;
    public boolean disconnected;
    public java.lang.Object disconnectionInfo;
    public boolean duringLogin;
    public boolean encrypted;
    public boolean errored;
    public java.lang.Object packetListener;
    public java.lang.Object packetSizeLogger;
    public int packetsReceivedCounter;
    public int packetsSentCounter;
    public java.lang.Object pendingDisconnectionInfo;
    public java.lang.Object prePlayStateListener;
    public java.util.Queue<java.util.function.Consumer<murat.simv2.simulation.mirror.net.minecraft.network.ClientConnection>> queuedTasks;
    public java.lang.Object side;
    public int ticks;

    public ClientConnection(java.lang.Object p0) {
    }

    public void addFlowControlHandler(io.netty.channel.ChannelPipeline p0) {
    }

    public static void addHandlers(io.netty.channel.ChannelPipeline p0, java.lang.Object p1, boolean p2, java.lang.Object p3) {
    }

    public static void addLocalValidator(io.netty.channel.ChannelPipeline p0, java.lang.Object p1) {
    }

    public void channelActive(io.netty.channel.ChannelHandlerContext p0) {
    }

    public void channelInactive(io.netty.channel.ChannelHandlerContext p0) {
    }

    public void channelRead0(io.netty.channel.ChannelHandlerContext p0, murat.simv2.simulation.mirror.net.minecraft.network.packet.Packet p1) {
    }

    public static murat.simv2.simulation.mirror.net.minecraft.network.ClientConnection connectLocal(java.net.SocketAddress p0) {
        return null;
    }

    public void connect(java.lang.String p0, int p1, java.lang.Object p2) {
    }

    public void connect(java.lang.String p0, int p1, java.lang.Object p2, java.lang.Object p3, java.lang.Object p4, boolean p5) {
    }

    public void connect(java.lang.String p0, int p1, java.lang.Object p2, java.lang.Object p3, java.lang.Object p4, java.lang.Object p5) {
    }

    public static murat.simv2.simulation.mirror.net.minecraft.network.ClientConnection connect(java.net.InetSocketAddress p0, boolean p1, java.lang.Object p2) {
        return null;
    }

    public static io.netty.channel.ChannelFuture connect(java.net.InetSocketAddress p0, boolean p1, murat.simv2.simulation.mirror.net.minecraft.network.ClientConnection p2) {
        return null;
    }

    public void disconnect(java.lang.Object p0) {
    }

    public void disconnect(murat.simv2.simulation.mirror.net.minecraft.text.Text p0) {
    }

    public void exceptionCaught(io.netty.channel.ChannelHandlerContext p0, java.lang.Throwable p1) {
    }

    public void flushInternal() {
    }

    public void flush() {
    }

    public java.lang.String getAddressAsString(boolean p0) {
        return null;
    }

    public java.net.SocketAddress getAddress() {
        return null;
    }

    public float getAveragePacketsReceived() {
        return 0.0F;
    }

    public float getAveragePacketsSent() {
        return 0.0F;
    }

    public java.lang.Object getDisconnectionInfo() {
        return null;
    }

    public static java.lang.String getInboundHandlerName(boolean p0) {
        return null;
    }

    public java.lang.Object getOppositeSide() {
        return null;
    }

    public static java.lang.String getOutboundHandlerName(boolean p0) {
        return null;
    }

    public java.lang.Object getPacketListener() {
        return null;
    }

    public static io.netty.channel.ChannelOutboundHandler getPrepender(boolean p0) {
        return null;
    }

    public java.lang.Object getSide() {
        return null;
    }

    public static io.netty.channel.ChannelInboundHandler getSplitter(java.lang.Object p0, boolean p1) {
        return null;
    }

    public void handleDisconnection() {
    }

    public static void handlePacket(murat.simv2.simulation.mirror.net.minecraft.network.packet.Packet p0, java.lang.Object p1) {
    }

    public void handleQueuedTasks() {
    }

    public boolean isChannelAbsent() {
        return false;
    }

    public boolean isEncrypted() {
        return false;
    }

    public boolean isLocal() {
        return false;
    }

    public boolean isOpen() {
        return false;
    }

    public void resetPacketSizeLog(java.lang.Object p0) {
    }

    public void sendImmediately(murat.simv2.simulation.mirror.net.minecraft.network.packet.Packet p0, java.lang.Object p1, boolean p2) {
    }

    public void sendInternal(murat.simv2.simulation.mirror.net.minecraft.network.packet.Packet p0, java.lang.Object p1, boolean p2) {
    }

    public void send(murat.simv2.simulation.mirror.net.minecraft.network.packet.Packet p0) {
    }

    public void send(murat.simv2.simulation.mirror.net.minecraft.network.packet.Packet p0, java.lang.Object p1) {
    }

    public void send(murat.simv2.simulation.mirror.net.minecraft.network.packet.Packet p0, java.lang.Object p1, boolean p2) {
    }

    public void setCompressionThreshold(int p0, boolean p1) {
    }

    public void setInitialPacketListener(java.lang.Object p0) {
    }

    public void setPacketListener(java.lang.Object p0, java.lang.Object p1) {
    }

    public void setupEncryption(javax.crypto.Cipher p0, javax.crypto.Cipher p1) {
    }

    public void submit(java.util.function.Consumer p0) {
    }

    public static void syncUninterruptibly(io.netty.channel.ChannelFuture p0) {
    }

    public void tick() {
    }

    public void transitionInbound(java.lang.Object p0, java.lang.Object p1) {
    }

    public void transitionOutbound(java.lang.Object p0) {
    }

    public void tryDisableAutoRead() {
    }

    public void updateStats() {
    }

    public ClientConnection() {
    }

    // END GENERATED MIRROR NESTED STUBS
}
