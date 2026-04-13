package murat.simv2.simulation.sliced;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.world.GameMode;
import org.jetbrains.annotations.Nullable;
// Sliced from net.minecraft.client.network.AbstractClientPlayerEntity
// Movement-relevant statements only (WALA backward slice + Spoon AST pruning)
// Generated - do not edit
public abstract class SlicedAbstractClientPlayerEntity extends SlicedPlayerEntity {
    @Nullable
    private PlayerListEntry playerListEntry;

    /**
     */
    @Nullable
    public GameMode getGameMode() {
        PlayerListEntry playerListEntry = this.getPlayerListEntry();
        return playerListEntry != null ? playerListEntry.getGameMode() : null;
    }

    /**
     */
    @Nullable
    protected PlayerListEntry getPlayerListEntry() {
        if (this.playerListEntry == null) {
            this.playerListEntry = MinecraftClient.getInstance().getNetworkHandler().getPlayerListEntry(this.getUuid());
        }
        return this.playerListEntry;
    }
}
