package murat.simv2.simulation.mirror.net.minecraft.server;

// Generated mirror stub for simulation closure.
public class ServerMetadata {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static com.mojang.serialization.Codec<murat.simv2.simulation.mirror.net.minecraft.server.ServerMetadata> CODEC;
    public murat.simv2.simulation.mirror.net.minecraft.text.Text description;
    public java.util.Optional<murat.simv2.simulation.mirror.net.minecraft.server.ServerMetadata.Favicon> favicon;
    public java.util.Optional<murat.simv2.simulation.mirror.net.minecraft.server.ServerMetadata.Players> players;
    public boolean secureChatEnforced;
    public java.util.Optional<murat.simv2.simulation.mirror.net.minecraft.server.ServerMetadata.Version> version;

    public ServerMetadata(murat.simv2.simulation.mirror.net.minecraft.text.Text p0, java.util.Optional p1, java.util.Optional p2, java.util.Optional p3, boolean p4) {
    }

    public murat.simv2.simulation.mirror.net.minecraft.text.Text description() {
        return null;
    }

    public boolean equals(java.lang.Object p0) {
        return false;
    }

    public java.util.Optional favicon() {
        return null;
    }

    public int hashCode() {
        return 0;
    }

    public java.util.Optional players() {
        return null;
    }

    public boolean secureChatEnforced() {
        return false;
    }

    public java.lang.String toString() {
        return null;
    }

    public java.util.Optional version() {
        return null;
    }

    public ServerMetadata() {
    }

    public static class Favicon {
        public static com.mojang.serialization.Codec<murat.simv2.simulation.mirror.net.minecraft.server.ServerMetadata.Favicon> CODEC;
        public static java.lang.String DATA_URI_PREFIX;
        public byte[] iconBytes;

        public Favicon(byte[] p0) {
        }

        public boolean equals(java.lang.Object p0) {
            return false;
        }

        public int hashCode() {
            return 0;
        }

        public byte[] iconBytes() {
            return null;
        }

        public java.lang.String toString() {
            return null;
        }

        public Favicon() {
        }

    }

    public static class Players {
        public static com.mojang.serialization.Codec<murat.simv2.simulation.mirror.net.minecraft.server.ServerMetadata.Players> CODEC;
        public static com.mojang.serialization.Codec<com.mojang.authlib.GameProfile> GAME_PROFILE_CODEC;
        public int max;
        public int online;
        public java.util.List<com.mojang.authlib.GameProfile> sample;

        public Players(int p0, int p1, java.util.List p2) {
        }

        public boolean equals(java.lang.Object p0) {
            return false;
        }

        public int hashCode() {
            return 0;
        }

        public int max() {
            return 0;
        }

        public int online() {
            return 0;
        }

        public java.util.List sample() {
            return null;
        }

        public java.lang.String toString() {
            return null;
        }

        public Players() {
        }

    }

    public static class Version {
        public static com.mojang.serialization.Codec<murat.simv2.simulation.mirror.net.minecraft.server.ServerMetadata.Version> CODEC;
        public java.lang.String gameVersion;
        public int protocolVersion;

        public Version(java.lang.String p0, int p1) {
        }

        public static murat.simv2.simulation.mirror.net.minecraft.server.ServerMetadata.Version create() {
            return null;
        }

        public boolean equals(java.lang.Object p0) {
            return false;
        }

        public java.lang.String gameVersion() {
            return null;
        }

        public int hashCode() {
            return 0;
        }

        public int protocolVersion() {
            return 0;
        }

        public java.lang.String toString() {
            return null;
        }

        public Version() {
        }

    }

    // END GENERATED MIRROR NESTED STUBS
}
