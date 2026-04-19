package murat.simv2.simulation.mirror.net.minecraft.client.network;

// Generated mirror stub for simulation closure.
public class ServerInfo extends java.lang.Object {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static org.slf4j.Logger LOGGER;
    public static int MAX_FAVICON_SIZE;
    public java.lang.String address;
    public byte[] favicon;
    public murat.simv2.simulation.mirror.net.minecraft.text.Text label;
    public java.lang.String name;
    public long ping;
    public murat.simv2.simulation.mirror.net.minecraft.text.Text playerCountLabel;
    public java.util.List<murat.simv2.simulation.mirror.net.minecraft.text.Text> playerListSummary;
    public murat.simv2.simulation.mirror.net.minecraft.server.ServerMetadata.Players players;
    public int protocolVersion;
    public murat.simv2.simulation.mirror.net.minecraft.client.network.ServerInfo.ResourcePackPolicy resourcePackPolicy;
    public murat.simv2.simulation.mirror.net.minecraft.client.network.ServerInfo.ServerType serverType;
    public murat.simv2.simulation.mirror.net.minecraft.client.network.ServerInfo.Status status;
    public murat.simv2.simulation.mirror.net.minecraft.text.Text version;

    public ServerInfo(java.lang.String p0, java.lang.String p1, murat.simv2.simulation.mirror.net.minecraft.client.network.ServerInfo.ServerType p2) {
    }

    public void copyFrom(murat.simv2.simulation.mirror.net.minecraft.client.network.ServerInfo p0) {
    }

    public void copyWithSettingsFrom(murat.simv2.simulation.mirror.net.minecraft.client.network.ServerInfo p0) {
    }

    public static murat.simv2.simulation.mirror.net.minecraft.client.network.ServerInfo fromNbt(murat.simv2.simulation.mirror.net.minecraft.nbt.NbtCompound p0) {
        return null;
    }

    public byte[] getFavicon() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.client.network.ServerInfo.ResourcePackPolicy getResourcePackPolicy() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.client.network.ServerInfo.ServerType getServerType() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.client.network.ServerInfo.Status getStatus() {
        return null;
    }

    public boolean isLocal() {
        return false;
    }

    public boolean isRealm() {
        return false;
    }

    public void setFavicon(byte[] p0) {
    }

    public void setResourcePackPolicy(murat.simv2.simulation.mirror.net.minecraft.client.network.ServerInfo.ResourcePackPolicy p0) {
    }

    public void setStatus(murat.simv2.simulation.mirror.net.minecraft.client.network.ServerInfo.Status p0) {
    }

    public murat.simv2.simulation.mirror.net.minecraft.nbt.NbtCompound toNbt() {
        return null;
    }

    public static byte[] validateFavicon(byte[] p0) {
        return null;
    }

    public ServerInfo() {
    }

    public static class ResourcePackPolicy {
        public static com.mojang.serialization.MapCodec<murat.simv2.simulation.mirror.net.minecraft.client.network.ServerInfo.ResourcePackPolicy> CODEC;
        public static murat.simv2.simulation.mirror.net.minecraft.client.network.ServerInfo.ResourcePackPolicy DISABLED;
        public static murat.simv2.simulation.mirror.net.minecraft.client.network.ServerInfo.ResourcePackPolicy ENABLED;
        public static murat.simv2.simulation.mirror.net.minecraft.client.network.ServerInfo.ResourcePackPolicy PROMPT;
        public static murat.simv2.simulation.mirror.net.minecraft.client.network.ServerInfo.ResourcePackPolicy[] RESOURCE_PACK_POLICIES;
        public murat.simv2.simulation.mirror.net.minecraft.text.Text name;

        public ResourcePackPolicy(java.lang.String p0, int p1, java.lang.String p2) {
        }

        public murat.simv2.simulation.mirror.net.minecraft.text.Text getName() {
            return null;
        }

        public int ordinal() {
            return 0;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.client.network.ServerInfo.ResourcePackPolicy valueOf(java.lang.String p0) {
            return null;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.client.network.ServerInfo.ResourcePackPolicy[] values() {
            return null;
        }

        public ResourcePackPolicy() {
        }

    }

    public static class ServerType {
        public static murat.simv2.simulation.mirror.net.minecraft.client.network.ServerInfo.ServerType LAN;
        public static murat.simv2.simulation.mirror.net.minecraft.client.network.ServerInfo.ServerType OTHER;
        public static murat.simv2.simulation.mirror.net.minecraft.client.network.ServerInfo.ServerType REALM;
        public static murat.simv2.simulation.mirror.net.minecraft.client.network.ServerInfo.ServerType[] field_45612;

        public ServerType(java.lang.String p0, int p1) {
        }

        public int ordinal() {
            return 0;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.client.network.ServerInfo.ServerType valueOf(java.lang.String p0) {
            return null;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.client.network.ServerInfo.ServerType[] values() {
            return null;
        }

        public ServerType() {
        }

    }

    public static class Status {
        public static murat.simv2.simulation.mirror.net.minecraft.client.network.ServerInfo.Status INCOMPATIBLE;
        public static murat.simv2.simulation.mirror.net.minecraft.client.network.ServerInfo.Status INITIAL;
        public static murat.simv2.simulation.mirror.net.minecraft.client.network.ServerInfo.Status PINGING;
        public static murat.simv2.simulation.mirror.net.minecraft.client.network.ServerInfo.Status SUCCESSFUL;
        public static murat.simv2.simulation.mirror.net.minecraft.client.network.ServerInfo.Status UNREACHABLE;
        public static murat.simv2.simulation.mirror.net.minecraft.client.network.ServerInfo.Status[] field_47885;

        public Status(java.lang.String p0, int p1) {
        }

        public int ordinal() {
            return 0;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.client.network.ServerInfo.Status valueOf(java.lang.String p0) {
            return null;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.client.network.ServerInfo.Status[] values() {
            return null;
        }

        public Status() {
        }

    }

    // END GENERATED MIRROR NESTED STUBS
}
