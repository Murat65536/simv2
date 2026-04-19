package murat.simv2.simulation.mirror.net.minecraft.network.message;

// Generated mirror stub for simulation closure.
public interface MessageVerifier {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static final org.slf4j.Logger LOGGER = null;
    public static final murat.simv2.simulation.mirror.net.minecraft.network.message.MessageVerifier NO_SIGNATURE = null;
    public static final murat.simv2.simulation.mirror.net.minecraft.network.message.MessageVerifier UNVERIFIED = null;

    public java.lang.Object ensureVerified(java.lang.Object p0);

    public static class Impl extends java.lang.Object implements murat.simv2.simulation.mirror.net.minecraft.network.message.MessageVerifier {
        public java.util.function.BooleanSupplier expirationChecker;
        public boolean lastMessageVerified;
        public java.lang.Object lastVerifiedMessage;
        public murat.simv2.simulation.mirror.net.minecraft.network.encryption.SignatureVerifier signatureVerifier;

        public Impl(murat.simv2.simulation.mirror.net.minecraft.network.encryption.SignatureVerifier p0, java.util.function.BooleanSupplier p1) {
        }

        public java.lang.Object ensureVerified(java.lang.Object p0) {
            return null;
        }

        public boolean verifyPrecedingSignature(java.lang.Object p0) {
            return false;
        }

        public boolean verify(java.lang.Object p0) {
            return false;
        }

        public Impl() {
        }

    }

    // END GENERATED MIRROR NESTED STUBS
}
