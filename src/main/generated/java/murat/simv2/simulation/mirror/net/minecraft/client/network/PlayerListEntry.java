package murat.simv2.simulation.mirror.net.minecraft.client.network;

// Generated mirror stub for simulation closure.
public class PlayerListEntry {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public murat.simv2.simulation.mirror.net.minecraft.text.Text displayName;
    public murat.simv2.simulation.mirror.net.minecraft.world.GameMode gameMode;
    public int latency;
    public int listOrder;
    public java.lang.Object messageVerifier;
    public com.mojang.authlib.GameProfile profile;
    public java.lang.Object session;
    public boolean showHat;
    public java.util.function.Supplier texturesSupplier;

    public PlayerListEntry(com.mojang.authlib.GameProfile p0, boolean p1) {
    }

    public murat.simv2.simulation.mirror.net.minecraft.text.Text getDisplayName() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.world.GameMode getGameMode() {
        return null;
    }

    public static java.lang.Object getInitialVerifier(boolean p0) {
        return null;
    }

    public int getLatency() {
        return 0;
    }

    public int getListOrder() {
        return 0;
    }

    public java.lang.Object getMessageVerifier() {
        return null;
    }

    public com.mojang.authlib.GameProfile getProfile() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.scoreboard.Team getScoreboardTeam() {
        return null;
    }

    public java.lang.Object getSession() {
        return null;
    }

    public java.lang.Object getSkinTextures() {
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

    public void setSession(java.lang.Object p0) {
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
