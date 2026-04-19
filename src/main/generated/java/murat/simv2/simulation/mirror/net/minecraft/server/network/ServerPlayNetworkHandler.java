package murat.simv2.simulation.mirror.net.minecraft.server.network;

// Generated mirror stub for simulation closure.
public class ServerPlayNetworkHandler {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static murat.simv2.simulation.mirror.net.minecraft.text.Text CHAT_VALIDATION_FAILED_TEXT;
    public static int DEFAULT_SEQUENCE;
    public static murat.simv2.simulation.mirror.net.minecraft.text.Text INVALID_COMMAND_SIGNATURE_TEXT;
    public static int KEEP_ALIVE_INTERVAL;
    public static org.slf4j.Logger LOGGER;
    public static int MAX_PENDING_ACKNOWLEDGMENTS;
    public static murat.simv2.simulation.mirror.net.minecraft.text.Text UNEXPECTED_QUERY_RESPONSE_TEXT;
    public java.lang.Object acknowledgmentValidator;
    public java.lang.Object chunkDataSender;
    public murat.simv2.simulation.mirror.net.minecraft.network.ClientConnection connection;
    public murat.simv2.simulation.mirror.net.minecraft.util.Cooldown creativeItemDropCooldown;
    public static int field_49027;
    public static int field_49778;
    public boolean floating;
    public int floatingTicks;
    public int globalChatMessageIndex;
    public int lastTeleportCheckTicks;
    public int lastTickMovePacketsCount;
    public double lastTickRiddenX;
    public double lastTickRiddenY;
    public double lastTickRiddenZ;
    public double lastTickX;
    public double lastTickY;
    public double lastTickZ;
    public java.lang.Object messageChainTaskQueue;
    public murat.simv2.simulation.mirror.net.minecraft.util.Cooldown messageCooldown;
    public java.lang.Object messageUnpacker;
    public int movePacketsCount;
    public boolean movedThisTick;
    public murat.simv2.simulation.mirror.net.minecraft.server.network.ServerPlayerEntity player;
    public boolean requestedReconfiguration;
    public int requestedTeleportId;
    public murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d requestedTeleportPos;
    public int sequence;
    public murat.simv2.simulation.mirror.net.minecraft.server.MinecraftServer server;
    public murat.simv2.simulation.mirror.net.minecraft.network.encryption.PublicPlayerSession session;
    public java.lang.Object signatureStorage;
    public int ticks;
    public murat.simv2.simulation.mirror.net.minecraft.entity.Entity topmostRiddenEntity;
    public double updatedRiddenX;
    public double updatedRiddenY;
    public double updatedRiddenZ;
    public double updatedX;
    public double updatedY;
    public double updatedZ;
    public boolean vehicleFloating;
    public int vehicleFloatingTicks;

    public ServerPlayNetworkHandler(murat.simv2.simulation.mirror.net.minecraft.server.MinecraftServer p0, murat.simv2.simulation.mirror.net.minecraft.network.ClientConnection p1, murat.simv2.simulation.mirror.net.minecraft.server.network.ServerPlayerEntity p2, java.lang.Object p3) {
    }

    public boolean accepts(murat.simv2.simulation.mirror.net.minecraft.network.packet.Packet p0) {
        return false;
    }

    public void addBook(java.lang.Object p0, java.util.List p1, int p2) {
    }

    public void addCustomCrashReportInfo(murat.simv2.simulation.mirror.net.minecraft.util.crash.CrashReport p0, murat.simv2.simulation.mirror.net.minecraft.util.crash.CrashReportSection p1) {
    }

    public void baseTick() {
    }

    public static boolean canPlace(murat.simv2.simulation.mirror.net.minecraft.server.network.ServerPlayerEntity p0, murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p1) {
        return false;
    }

    public void checkForSpam() {
    }

    public static double clampHorizontal(double p0) {
        return 0.0D;
    }

    public static double clampVertical(double p0) {
        return 0.0D;
    }

    public void cleanUp() {
    }

    public java.util.Map collectArgumentMessages(java.lang.Object p0, java.lang.Object p1, java.lang.Object p2) {
        return null;
    }

    public static void copyBlockDataToStack(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0, murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p1, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p2, murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p3) {
    }

    public java.lang.Object createClientData(murat.simv2.simulation.mirror.net.minecraft.network.packet.c2s.common.SyncedClientOptions p0) {
        return null;
    }

    public java.lang.Object createDisconnectionInfo(murat.simv2.simulation.mirror.net.minecraft.text.Text p0, java.lang.Throwable p1) {
        return null;
    }

    public static java.lang.Object createInvalidCommandSignatureException(java.lang.String p0, java.util.List p1, java.util.List p2) {
        return null;
    }

    public void disableFlush() {
    }

    public void disconnect(java.lang.Object p0) {
    }

    public void disconnect(murat.simv2.simulation.mirror.net.minecraft.text.Text p0) {
    }

    public void enableFlush() {
    }

    public void executeCommand(java.lang.String p0) {
    }

    public void fillCrashReport(murat.simv2.simulation.mirror.net.minecraft.util.crash.CrashReport p0) {
    }

    public java.util.concurrent.CompletableFuture filterTexts(java.util.List p0) {
        return null;
    }

    public java.util.concurrent.CompletableFuture filterText(java.lang.Object p0, java.util.function.BiFunction p1) {
        return null;
    }

    public java.util.concurrent.CompletableFuture filterText(java.lang.String p0) {
        return null;
    }

    public java.net.SocketAddress getConnectionAddress() {
        return null;
    }

    public com.mojang.authlib.GameProfile getDebugProfile() {
        return null;
    }

    public int getLatency() {
        return 0;
    }

    public int getMaxAllowedFloatingTicks(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0) {
        return 0;
    }

    public java.lang.Object getPhase() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.server.network.ServerPlayerEntity getPlayer() {
        return null;
    }

    public com.mojang.authlib.GameProfile getProfile() {
        return null;
    }

    public java.lang.Object getSide() {
        return null;
    }

    public java.lang.Object getSignedMessage(java.lang.Object p0, java.lang.Object p1) {
        return null;
    }

    public void handleCommandExecution(java.lang.Object p0, java.lang.Object p1) {
    }

    public void handleDecoratedMessage(java.lang.Object p0) {
    }

    public void handleMessageChainException(java.lang.Object p0) {
    }

    public void handleMovement(murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p0) {
    }

    public boolean handlePendingTeleport() {
        return false;
    }

    public static boolean hasIllegalCharacter(java.lang.String p0) {
        return false;
    }

    public boolean isConnectionOpen() {
        return false;
    }

    public boolean isEntityOnAir(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0) {
        return false;
    }

    public boolean isHost() {
        return false;
    }

    public boolean isInCreativeMode() {
        return false;
    }

    public static boolean isMovementInvalid(double p0, double p1, double p2, float p3, float p4) {
        return false;
    }

    public boolean isPlayerNotCollidingWithBlocks(murat.simv2.simulation.mirror.net.minecraft.world.WorldView p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Box p1, double p2, double p3, double p4) {
        return false;
    }

    public void onAcknowledgeChunks(java.lang.Object p0) {
    }

    public void onAcknowledgeReconfiguration(java.lang.Object p0) {
    }

    public void onAdvancementTab(java.lang.Object p0) {
    }

    public void onBoatPaddleState(java.lang.Object p0) {
    }

    public void onBookUpdate(java.lang.Object p0) {
    }

    public void onBundleItemSelected(java.lang.Object p0) {
    }

    public void onButtonClick(java.lang.Object p0) {
    }

    public void onChatCommandSigned(java.lang.Object p0) {
    }

    public void onChatMessage(java.lang.Object p0) {
    }

    public void onClickSlot(java.lang.Object p0) {
    }

    public void onClientCommand(java.lang.Object p0) {
    }

    public void onClientOptions(java.lang.Object p0) {
    }

    public void onClientStatus(java.lang.Object p0) {
    }

    public void onClientTickEnd(java.lang.Object p0) {
    }

    public void onCloseHandledScreen(java.lang.Object p0) {
    }

    public void onCommandExecution(java.lang.Object p0) {
    }

    public void onCookieResponse(java.lang.Object p0) {
    }

    public void onCraftRequest(java.lang.Object p0) {
    }

    public void onCreativeInventoryAction(java.lang.Object p0) {
    }

    public void onCustomPayload(java.lang.Object p0) {
    }

    public void onDebugSampleSubscription(java.lang.Object p0) {
    }

    public void onDisconnected(java.lang.Object p0) {
    }

    public void onHandSwing(java.lang.Object p0) {
    }

    public void onJigsawGenerating(java.lang.Object p0) {
    }

    public void onKeepAlive(java.lang.Object p0) {
    }

    public void onMessageAcknowledgment(java.lang.Object p0) {
    }

    public void onPacketException(murat.simv2.simulation.mirror.net.minecraft.network.packet.Packet p0, java.lang.Exception p1) {
    }

    public void onPickItemFromBlock(java.lang.Object p0) {
    }

    public void onPickItemFromEntity(java.lang.Object p0) {
    }

    public void onPickItem(murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p0) {
    }

    public void onPlayerAction(java.lang.Object p0) {
    }

    public void onPlayerInput(java.lang.Object p0) {
    }

    public void onPlayerInteractBlock(java.lang.Object p0) {
    }

    public void onPlayerInteractEntity(java.lang.Object p0) {
    }

    public void onPlayerInteractItem(java.lang.Object p0) {
    }

    public void onPlayerLoaded(java.lang.Object p0) {
    }

    public void onPlayerMove(java.lang.Object p0) {
    }

    public void onPlayerSession(java.lang.Object p0) {
    }

    public void onPong(java.lang.Object p0) {
    }

    public void onQueryBlockNbt(java.lang.Object p0) {
    }

    public void onQueryEntityNbt(java.lang.Object p0) {
    }

    public void onQueryPing(java.lang.Object p0) {
    }

    public void onRecipeBookData(java.lang.Object p0) {
    }

    public void onRecipeCategoryOptions(java.lang.Object p0) {
    }

    public void onRenameItem(java.lang.Object p0) {
    }

    public void onRequestCommandCompletions(java.lang.Object p0) {
    }

    public void onResourcePackStatus(java.lang.Object p0) {
    }

    public void onSelectMerchantTrade(java.lang.Object p0) {
    }

    public void onSetTestBlock(java.lang.Object p0) {
    }

    public void onSignUpdate(java.lang.Object p0, java.util.List p1) {
    }

    public void onSlotChangedState(java.lang.Object p0) {
    }

    public void onSpectatorTeleport(java.lang.Object p0) {
    }

    public void onTeleportConfirm(java.lang.Object p0) {
    }

    public void onTestInstanceBlockAction(java.lang.Object p0) {
    }

    public void onUpdateBeacon(java.lang.Object p0) {
    }

    public void onUpdateCommandBlockMinecart(java.lang.Object p0) {
    }

    public void onUpdateCommandBlock(java.lang.Object p0) {
    }

    public void onUpdateDifficultyLock(java.lang.Object p0) {
    }

    public void onUpdateDifficulty(java.lang.Object p0) {
    }

    public void onUpdateJigsaw(java.lang.Object p0) {
    }

    public void onUpdatePlayerAbilities(java.lang.Object p0) {
    }

    public void onUpdateSelectedSlot(java.lang.Object p0) {
    }

    public void onUpdateSign(java.lang.Object p0) {
    }

    public void onUpdateStructureBlock(java.lang.Object p0) {
    }

    public void onVehicleMove(java.lang.Object p0) {
    }

    public com.mojang.brigadier.ParseResults parse(java.lang.String p0) {
        return null;
    }

    public void reconfigure() {
    }

    public void requestTeleport(double p0, double p1, double p2, float p3, float p4) {
    }

    public void requestTeleport(murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerPosition p0, java.util.Set p1) {
    }

    public void sendChatMessage(java.lang.Object p0, murat.simv2.simulation.mirror.net.minecraft.network.message.MessageType.Parameters p1) {
    }

    public void sendPacket(murat.simv2.simulation.mirror.net.minecraft.network.packet.Packet p0) {
    }

    public void sendProfilelessChatMessage(murat.simv2.simulation.mirror.net.minecraft.text.Text p0, murat.simv2.simulation.mirror.net.minecraft.network.message.MessageType.Parameters p1) {
    }

    public void send(murat.simv2.simulation.mirror.net.minecraft.network.packet.Packet p0, java.lang.Object p1) {
    }

    public void setSession(murat.simv2.simulation.mirror.net.minecraft.network.encryption.PublicPlayerSession p0) {
    }

    public boolean shouldCheckMovement(boolean p0) {
        return false;
    }

    public void syncWithPlayerPosition() {
    }

    public void tick() {
    }

    public java.lang.Object toRawFilteredPair(java.lang.Object p0) {
        return null;
    }

    public java.util.Map toUnsignedSignatures(java.util.List p0) {
        return null;
    }

    public void updateBookContent(java.util.List p0, int p1) {
    }

    public void updateSequence(int p0) {
    }

    public java.util.Optional validateAcknowledgment(java.lang.Object p0) {
        return null;
    }

    public void validateMessage(java.lang.String p0, java.lang.Runnable p1) {
    }

    public ServerPlayNetworkHandler() {
    }

    public static interface Interaction {
        public murat.simv2.simulation.mirror.net.minecraft.util.ActionResult run(murat.simv2.simulation.mirror.net.minecraft.server.network.ServerPlayerEntity p0, murat.simv2.simulation.mirror.net.minecraft.entity.Entity p1, murat.simv2.simulation.mirror.net.minecraft.util.Hand p2);

    }

    // END GENERATED MIRROR NESTED STUBS
}
