package murat.simv2.simulation.sliced;

import java.util.UUID;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.GameMode;
import org.jetbrains.annotations.Nullable;
import static net.minecraft.client.network.AbstractClientPlayerEntity.*;
import static net.minecraft.client.network.ClientPlayerEntity.*;
import static net.minecraft.entity.Entity.*;
import static net.minecraft.entity.LivingEntity.*;
import static net.minecraft.entity.player.PlayerEntity.*;

// Sliced from net.minecraft.client.network.AbstractClientPlayerEntity
// Movement-relevant statements only (WALA backward slice + Spoon AST pruning)
// Generated — do not edit

public abstract class SlicedAbstractClientPlayerEntity extends SlicedPlayerEntity {

  @Nullable private PlayerListEntry playerListEntry;
  protected UUID uuid = MathHelper.randomUuid(this.random);

  @Nullable
  public GameMode getGameMode() {
    PlayerListEntry playerListEntry = this.getPlayerListEntry();
    return playerListEntry != null ? playerListEntry.getGameMode() : null;
  }

  @Nullable
  protected PlayerListEntry getPlayerListEntry() {
    if (this.playerListEntry == null) {
      this.playerListEntry =
          MinecraftClient.getInstance().getNetworkHandler().getPlayerListEntry(this.getUuid());
    }
    return this.playerListEntry;
  }

  public UUID getUuid() {
    return this.uuid;
  }
}
