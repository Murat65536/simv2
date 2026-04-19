package murat.simv2.simulation.mirror.net.minecraft.client.network;

// Generated mirror stub for simulation closure.
public class SocialInteractionsManager extends java.lang.Object {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public boolean blockListLoaded;
    public java.util.concurrent.CompletableFuture<?> blockListLoader;
    public murat.simv2.simulation.mirror.net.minecraft.client.MinecraftClient client;
    public java.util.Set<java.util.UUID> hiddenPlayers;
    public java.util.Map<java.lang.String, java.util.UUID> playerNameByUuid;
    public com.mojang.authlib.minecraft.UserApiService userApiService;

    public SocialInteractionsManager(murat.simv2.simulation.mirror.net.minecraft.client.MinecraftClient p0, com.mojang.authlib.minecraft.UserApiService p1) {
    }

    public java.util.Set getHiddenPlayers() {
        return null;
    }

    public java.util.UUID getUuid(java.lang.String p0) {
        return null;
    }

    public void hidePlayer(java.util.UUID p0) {
    }

    public boolean isPlayerBlocked(java.util.UUID p0) {
        return false;
    }

    public boolean isPlayerHidden(java.util.UUID p0) {
        return false;
    }

    public boolean isPlayerMuted(java.util.UUID p0) {
        return false;
    }

    public void loadBlockList() {
    }

    public void setPlayerOffline(java.util.UUID p0) {
    }

    public void setPlayerOnline(murat.simv2.simulation.mirror.net.minecraft.client.network.PlayerListEntry p0) {
    }

    public void showPlayer(java.util.UUID p0) {
    }

    public void unloadBlockList() {
    }

    public SocialInteractionsManager() {
    }

    // END GENERATED MIRROR NESTED STUBS
}
