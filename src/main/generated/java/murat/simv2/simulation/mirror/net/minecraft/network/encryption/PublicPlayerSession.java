package murat.simv2.simulation.mirror.net.minecraft.network.encryption;

// Generated mirror stub for simulation closure.
public class PublicPlayerSession {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public java.lang.Object publicKeyData;
    public java.util.UUID sessionId;

    public PublicPlayerSession(java.util.UUID p0, java.lang.Object p1) {
    }

    public java.lang.Object createUnpacker(java.util.UUID p0) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.network.message.MessageVerifier createVerifier(java.time.Duration p0) {
        return null;
    }

    public boolean equals(java.lang.Object p0) {
        return false;
    }

    public int hashCode() {
        return 0;
    }

    public boolean isKeyExpired() {
        return false;
    }

    public java.lang.Object publicKeyData() {
        return null;
    }

    public java.util.UUID sessionId() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.network.encryption.PublicPlayerSession.Serialized toSerialized() {
        return null;
    }

    public java.lang.String toString() {
        return null;
    }

    public PublicPlayerSession() {
    }

    public static class Serialized {
        public java.lang.Object publicKeyData;
        public java.util.UUID sessionId;

        public Serialized(java.util.UUID p0, java.lang.Object p1) {
        }

        public boolean equals(java.lang.Object p0) {
            return false;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.network.encryption.PublicPlayerSession.Serialized fromBuf(java.lang.Object p0) {
            return null;
        }

        public int hashCode() {
            return 0;
        }

        public java.lang.Object publicKeyData() {
            return null;
        }

        public java.util.UUID sessionId() {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.network.encryption.PublicPlayerSession toSession(com.mojang.authlib.GameProfile p0, murat.simv2.simulation.mirror.net.minecraft.network.encryption.SignatureVerifier p1) {
            return null;
        }

        public java.lang.String toString() {
            return null;
        }

        public static void write(java.lang.Object p0, murat.simv2.simulation.mirror.net.minecraft.network.encryption.PublicPlayerSession.Serialized p1) {
        }

        public Serialized() {
        }

    }

    // END GENERATED MIRROR NESTED STUBS
}
