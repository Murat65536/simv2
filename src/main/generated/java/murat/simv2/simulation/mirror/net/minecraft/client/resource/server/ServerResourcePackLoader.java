package murat.simv2.simulation.mirror.net.minecraft.client.resource.server;

// Generated mirror stub for simulation closure.
public class ServerResourcePackLoader extends java.lang.Object {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static java.lang.Object DEBUG_PACK_STATE_CHANGE_CALLBACK;
    public static org.slf4j.Logger LOGGER;
    public static java.lang.Object NOOP_PROVIDER;
    public static java.lang.Object POSITION;
    public static murat.simv2.simulation.mirror.net.minecraft.text.Text SERVER_NAME_TEXT;
    public static java.util.regex.Pattern SHA1_PATTERN;
    public murat.simv2.simulation.mirror.net.minecraft.client.MinecraftClient client;
    public java.lang.Object downloader;
    public java.lang.Object manager;
    public int packIndex;
    public java.lang.Object packProvider;
    public java.lang.Object packSource;
    public java.lang.Object packStateChangeCallback;
    public java.lang.Object reloadContext;

    public ServerResourcePackLoader(murat.simv2.simulation.mirror.net.minecraft.client.MinecraftClient p0, java.nio.file.Path p1, murat.simv2.simulation.mirror.net.minecraft.client.RunArgs.Network p2) {
    }

    public void acceptAll() {
    }

    public void addResourcePack(java.util.UUID p0, java.net.URL p1, java.lang.String p2) {
    }

    public void addResourcePack(java.util.UUID p0, java.nio.file.Path p1) {
    }

    public void clear() {
    }

    public void close() {
    }

    public java.lang.Object createDownloadQueuer(java.lang.Object p0, java.util.concurrent.Executor p1, murat.simv2.simulation.mirror.net.minecraft.client.session.Session p2, java.net.Proxy p3) {
        return null;
    }

    public java.lang.Object createListener(int p0) {
        return null;
    }

    public java.lang.Runnable createPackChangeCallback(java.util.concurrent.Executor p0) {
        return null;
    }

    public void declineAll() {
    }

    public java.util.concurrent.CompletableFuture getPackLoadFuture(java.util.UUID p0) {
        return null;
    }

    public static java.lang.Object getPackProvider(java.util.List p0) {
        return null;
    }

    public java.lang.Object getPassthroughPackProvider() {
        return null;
    }

    public java.lang.Object getReloadScheduler() {
        return null;
    }

    public static java.lang.Object getStateChangeCallback(murat.simv2.simulation.mirror.net.minecraft.network.ClientConnection p0) {
        return null;
    }

    public void initWorldPack() {
    }

    public void init(murat.simv2.simulation.mirror.net.minecraft.network.ClientConnection p0, java.lang.Object p1) {
    }

    public void onForcedReloadFailure() {
    }

    public void onReloadFailure() {
    }

    public void onReloadSuccess() {
    }

    public void reload(java.lang.Object p0) {
    }

    public void removeAll() {
    }

    public void remove(java.util.UUID p0) {
    }

    public static com.google.common.hash.HashCode toHashCode(java.lang.String p0) {
        return null;
    }

    public java.util.List toProfiles(java.util.List p0) {
        return null;
    }

    public ServerResourcePackLoader() {
    }

    // END GENERATED MIRROR NESTED STUBS
}
