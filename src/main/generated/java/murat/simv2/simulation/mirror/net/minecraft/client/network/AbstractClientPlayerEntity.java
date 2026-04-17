package murat.simv2.simulation.mirror.net.minecraft.client.network;

import murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.world.GameMode;
import org.jetbrains.annotations.Nullable;

// Mirrored from net.minecraft.client.network.AbstractClientPlayerEntity
// Movement-relevant statements only (WALA backward slice + Spoon AST pruning)
// Generated - do not edit
public abstract class AbstractClientPlayerEntity extends PlayerEntity {
    @Nullable
    public PlayerListEntry playerListEntry;

    @Nullable
    public GameMode getGameMode() {
        PlayerListEntry playerListEntry = this.getPlayerListEntry();
        return playerListEntry != null ? playerListEntry.getGameMode() : null;
    }

    @Nullable
    protected PlayerListEntry getPlayerListEntry() {
        if (this.playerListEntry == null) {
            this.playerListEntry = MinecraftClient.getInstance().getNetworkHandler().getPlayerListEntry(this.getUuid());
        }
        return this.playerListEntry;
    }

    public AbstractClientPlayerEntity() {
    }

}

