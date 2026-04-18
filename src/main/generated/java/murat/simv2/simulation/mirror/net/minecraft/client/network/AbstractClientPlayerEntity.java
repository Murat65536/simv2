package murat.simv2.simulation.mirror.net.minecraft.client.network;

import murat.simv2.simulation.mirror.net.minecraft.client.MinecraftClient;
import murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity;
import murat.simv2.simulation.mirror.net.minecraft.world.GameMode;
import org.jetbrains.annotations.Nullable;

// Mirrored from net.minecraft.client.network.AbstractClientPlayerEntity
// Movement-relevant statements only (WALA backward slice + Spoon AST pruning)
// Generated - do not edit
public abstract class AbstractClientPlayerEntity extends PlayerEntity {
    @Nullable
    public murat.simv2.simulation.mirror.net.minecraft.client.network.PlayerListEntry playerListEntry;

    @Nullable
    public murat.simv2.simulation.mirror.net.minecraft.world.GameMode getGameMode() {
        murat.simv2.simulation.mirror.net.minecraft.client.network.PlayerListEntry playerListEntry = this.getPlayerListEntry();
        return playerListEntry != null ? playerListEntry.getGameMode() : null;
    }

    @Nullable
    protected murat.simv2.simulation.mirror.net.minecraft.client.network.PlayerListEntry getPlayerListEntry() {
        if (this.playerListEntry == null) {
            this.playerListEntry = murat.simv2.simulation.mirror.net.minecraft.client.MinecraftClient.getInstance().getNetworkHandler().getPlayerListEntry(this.getUuid());
        }
        return this.playerListEntry;
    }

    public AbstractClientPlayerEntity() {
    }

}

