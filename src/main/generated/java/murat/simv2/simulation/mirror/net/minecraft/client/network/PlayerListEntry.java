package murat.simv2.simulation.mirror.net.minecraft.client.network;

// Generated mirror stub for simulation closure.
public class PlayerListEntry extends java.lang.Object {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public murat.simv2.simulation.mirror.net.minecraft.text.Text displayName;
    public murat.simv2.simulation.mirror.net.minecraft.world.GameMode gameMode;
    public int latency;
    public int listOrder;
    public murat.simv2.simulation.mirror.net.minecraft.network.message.MessageVerifier messageVerifier;
    public com.mojang.authlib.GameProfile profile;
    public murat.simv2.simulation.mirror.net.minecraft.network.encryption.PublicPlayerSession session;
    public boolean showHat;
    public java.util.function.Supplier<murat.simv2.simulation.mirror.net.minecraft.client.util.SkinTextures> texturesSupplier;

    public PlayerListEntry(com.mojang.authlib.GameProfile p0, boolean p1) {
    }

    public murat.simv2.simulation.mirror.net.minecraft.text.Text getDisplayName() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.world.GameMode getGameMode() {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.network.message.MessageVerifier getInitialVerifier(boolean p0) {
        return null;
    }

    public int getLatency() {
        return 0;
    }

    public int getListOrder() {
        return 0;
    }

    public murat.simv2.simulation.mirror.net.minecraft.network.message.MessageVerifier getMessageVerifier() {
        return null;
    }

    public com.mojang.authlib.GameProfile getProfile() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.scoreboard.Team getScoreboardTeam() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.network.encryption.PublicPlayerSession getSession() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.client.util.SkinTextures getSkinTextures() {
        return null;
    }

    public boolean hasPublicKey() {
        return false;
    }

    public void resetSession(boolean p0) {
    }

    public void setDisplayName(murat.simv2.simulation.mirror.net.minecraft.text.Text p0) {
    }

    public void setGameMode(murat.simv2.simulation.mirror.net.minecraft.world.GameMode p0) {
    }

    public void setLatency(int p0) {
    }

    public void setListOrder(int p0) {
    }

    public void setSession(murat.simv2.simulation.mirror.net.minecraft.network.encryption.PublicPlayerSession p0) {
    }

    public void setShowHat(boolean p0) {
    }

    public boolean shouldShowHat() {
        return false;
    }

    public static java.util.function.Supplier texturesSupplier(com.mojang.authlib.GameProfile p0) {
        return null;
    }

    public PlayerListEntry() {
    }

    // END GENERATED MIRROR NESTED STUBS
}
