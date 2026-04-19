package murat.simv2.simulation.mirror.net.minecraft.network.encryption;

// Generated mirror stub for simulation closure.
public interface SignatureVerifier {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static final org.slf4j.Logger LOGGER = null;
    public static final murat.simv2.simulation.mirror.net.minecraft.network.encryption.SignatureVerifier NOOP = null;

    public static murat.simv2.simulation.mirror.net.minecraft.network.encryption.SignatureVerifier create(com.mojang.authlib.yggdrasil.ServicesKeySet p0, com.mojang.authlib.yggdrasil.ServicesKeyType p1) {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.network.encryption.SignatureVerifier create(java.security.PublicKey p0, java.lang.String p1) {
        return null;
    }

    public boolean validate(byte[] p0, byte[] p1);

    public boolean validate(java.lang.Object p0, byte[] p1);

    public static boolean verify(java.lang.Object p0, byte[] p1, java.security.Signature p2) {
        return false;
    }

    // END GENERATED MIRROR NESTED STUBS
}
