package murat.simv2.simulation.mirror.net.minecraft.client.network;

// Generated mirror stub for simulation closure.
public class ClientPlayNetworkHandler {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static int ACKNOWLEDGMENT_BATCH_SIZE;
    public static murat.simv2.simulation.mirror.net.minecraft.text.Text BAD_CHAT_INDEX_TEXT;
    public static murat.simv2.simulation.mirror.net.minecraft.text.Text INVALID_PACKET_TEXT;
    public static org.slf4j.Logger LOGGER;
    public static murat.simv2.simulation.mirror.net.minecraft.text.Text RECONFIGURING_TEXT;
    public static murat.simv2.simulation.mirror.net.minecraft.text.Text UNSECURE_SERVER_TOAST_TEXT;
    public static murat.simv2.simulation.mirror.net.minecraft.text.Text UNSECURE_SERVER_TOAST_TITLE;
    public java.lang.Object advancementHandler;
    public java.lang.String brand;
    public murat.simv2.simulation.mirror.net.minecraft.recipe.BrewingRecipeRegistry brewingRecipeRegistry;
    public java.util.List<java.lang.ref.WeakReference<java.lang.Object>> cachedData;
    public java.lang.Object chunkBatchSizeCalculator;
    public int chunkLoadDistance;
    public murat.simv2.simulation.mirror.net.minecraft.client.MinecraftClient client;
    public murat.simv2.simulation.mirror.net.minecraft.registry.DynamicRegistryManager.Immutable combinedDynamicRegistries;
    public com.mojang.brigadier.CommandDispatcher<java.lang.Object> commandDispatcher;
    public java.lang.Object commandSource;
    public java.lang.Object componentHasher;
    public murat.simv2.simulation.mirror.net.minecraft.network.ClientConnection connection;
    public java.util.Map<java.lang.String, java.lang.String> customReportDetails;
    public java.lang.Object dataQueryHandler;
    public java.lang.Object debugSampleSubscriber;
    public boolean displayedUnsecureChatWarning;
    public murat.simv2.simulation.mirror.net.minecraft.resource.featuretoggle.FeatureSet enabledFeatures;
    public static int field_54852;
    public murat.simv2.simulation.mirror.net.minecraft.item.FuelRegistry fuelRegistry;
    public int globalChatMessageIndex;
    public java.lang.Object lastSeenMessagesCollector;
    public java.util.Set<murat.simv2.simulation.mirror.net.minecraft.client.network.PlayerListEntry> listedPlayerListEntries;
    public java.lang.Object messagePacker;
    public java.lang.Object pingMeasurer;
    public java.util.Map<java.util.UUID, murat.simv2.simulation.mirror.net.minecraft.client.network.PlayerListEntry> playerListEntries;
    public murat.simv2.simulation.mirror.net.minecraft.client.gui.screen.Screen postDisconnectScreen;
    public com.mojang.authlib.GameProfile profile;
    public java.util.concurrent.CompletableFuture<java.util.Optional<java.lang.Object>> profileKeyPairFuture;
    public murat.simv2.simulation.mirror.net.minecraft.util.math.random.Random random;
    public java.lang.Object recipeManager;
    public java.util.OptionalInt removedPlayerVehicleId;
    public murat.simv2.simulation.mirror.net.minecraft.scoreboard.Scoreboard scoreboard;
    public java.lang.Object searchManager;
    public boolean secureChatEnforced;
    public java.util.Map<murat.simv2.simulation.mirror.net.minecraft.util.Identifier, byte[]> serverCookies;
    public murat.simv2.simulation.mirror.net.minecraft.client.network.ServerInfo serverInfo;
    public java.lang.Object serverLinks;
    public java.lang.Object session;
    public java.util.UUID sessionId;
    public java.lang.Object signatureStorage;
    public int simulationDistance;
    public murat.simv2.simulation.mirror.net.minecraft.network.packet.c2s.common.SyncedClientOptions syncedOptions;
    public boolean transferring;
    public murat.simv2.simulation.mirror.net.minecraft.client.world.ClientWorld world;
    public boolean worldCleared;
    public java.util.Set<murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey<murat.simv2.simulation.mirror.net.minecraft.world.World>> worldKeys;
    public java.lang.Object worldLoadingState;
    public murat.simv2.simulation.mirror.net.minecraft.client.world.ClientWorld.Properties worldProperties;
    public java.lang.Object worldSession;

    public ClientPlayNetworkHandler(murat.simv2.simulation.mirror.net.minecraft.client.MinecraftClient p0, murat.simv2.simulation.mirror.net.minecraft.network.ClientConnection p1, java.lang.Object p2) {
    }

    public boolean accepts(murat.simv2.simulation.mirror.net.minecraft.network.packet.Packet p0) {
        return false;
    }

    public void acknowledge(java.lang.Object p0, boolean p1) {
    }

    public void addCustomCrashReportInfo(murat.simv2.simulation.mirror.net.minecraft.util.crash.CrashReport p0, murat.simv2.simulation.mirror.net.minecraft.util.crash.CrashReportSection p1) {
    }

    public void clearCachedData() {
    }

    public void clearWorld() {
    }

    public murat.simv2.simulation.mirror.net.minecraft.client.gui.screen.Screen createDisconnectedScreen(java.lang.Object p0) {
        return null;
    }

    public java.lang.Object createDisconnectionInfo(murat.simv2.simulation.mirror.net.minecraft.text.Text p0, java.lang.Throwable p1) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.entity.Entity createEntity(murat.simv2.simulation.mirror.net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket p0) {
        return null;
    }

    public void fetchProfileKey() {
    }

    public void fillCrashReport(murat.simv2.simulation.mirror.net.minecraft.util.crash.CrashReport p0) {
    }

    public static murat.simv2.simulation.mirror.net.minecraft.item.ItemStack getActiveDeathProtector(murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity p0) {
        return null;
    }

    public java.lang.Object getAdvancementHandler() {
        return null;
    }

    public java.lang.String getBrand() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.recipe.BrewingRecipeRegistry getBrewingRecipeRegistry() {
        return null;
    }

    public com.mojang.brigadier.CommandDispatcher getCommandDispatcher() {
        return null;
    }

    public java.lang.Object getCommandSource() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.network.ClientConnection getConnection() {
        return null;
    }

    public java.lang.Object getDataQueryHandler() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.resource.featuretoggle.FeatureSet getEnabledFeatures() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.item.FuelRegistry getFuelRegistry() {
        return null;
    }

    public java.util.Collection getListedPlayerListEntries() {
        return null;
    }

    public java.lang.Object getPhase() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.client.network.PlayerListEntry getPlayerListEntry(java.lang.String p0) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.client.network.PlayerListEntry getPlayerListEntry(java.util.UUID p0) {
        return null;
    }

    public java.util.Collection getPlayerList() {
        return null;
    }

    public java.util.Collection getPlayerUuids() {
        return null;
    }

    public com.mojang.authlib.GameProfile getProfile() {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.text.Text getPrompt(murat.simv2.simulation.mirror.net.minecraft.text.Text p0, murat.simv2.simulation.mirror.net.minecraft.text.Text p1) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.recipe.RecipeManager getRecipeManager() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.registry.DynamicRegistryManager.Immutable getRegistryManager() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.scoreboard.Scoreboard getScoreboard() {
        return null;
    }

    public java.lang.Object getSearchManager() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.client.network.ServerInfo getServerInfo() {
        return null;
    }

    public java.lang.Object getServerLinks() {
        return null;
    }

    public java.util.UUID getSessionId() {
        return null;
    }

    public java.lang.Object getSide() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.client.gui.screen.DownloadingTerrainScreen.WorldEntryReason getWorldEntryReason(boolean p0, murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey p1, murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey p2) {
        return null;
    }

    public java.util.Set getWorldKeys() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.client.world.ClientWorld getWorld() {
        return null;
    }

    public void handlePlayerListAction(java.lang.Object p0, java.lang.Object p1, murat.simv2.simulation.mirror.net.minecraft.client.network.PlayerListEntry p2) {
    }

    public boolean hasFeature(murat.simv2.simulation.mirror.net.minecraft.resource.featuretoggle.FeatureSet p0) {
        return false;
    }

    public boolean isConnectionOpen() {
        return false;
    }

    public boolean isSecureChatEnforced() {
        return false;
    }

    public void loadChunk(int p0, int p1, java.lang.Object p2) {
    }

    public java.lang.Object method_68823() {
        return null;
    }

    public void onAdvancements(java.lang.Object p0) {
    }

    public void onBlockBreakingProgress(java.lang.Object p0) {
    }

    public void onBlockEntityUpdate(java.lang.Object p0) {
    }

    public void onBlockEvent(java.lang.Object p0) {
    }

    public void onBlockUpdate(java.lang.Object p0) {
    }

    public void onBossBar(java.lang.Object p0) {
    }

    public void onBundle(java.lang.Object p0) {
    }

    public void onChatMessage(java.lang.Object p0) {
    }

    public void onChatSuggestions(java.lang.Object p0) {
    }

    public void onChunkBiomeData(java.lang.Object p0) {
    }

    public void onChunkData(java.lang.Object p0) {
    }

    public void onChunkDeltaUpdate(java.lang.Object p0) {
    }

    public void onChunkLoadDistance(java.lang.Object p0) {
    }

    public void onChunkRenderDistanceCenter(java.lang.Object p0) {
    }

    public void onChunkSent(java.lang.Object p0) {
    }

    public void onCloseScreen(java.lang.Object p0) {
    }

    public void onCommandSuggestions(java.lang.Object p0) {
    }

    public void onCommandTree(java.lang.Object p0) {
    }

    public void onCookieRequest(java.lang.Object p0) {
    }

    public void onCooldownUpdate(java.lang.Object p0) {
    }

    public void onCraftFailedResponse(java.lang.Object p0) {
    }

    public void onCustomPayload(java.lang.Object p0) {
    }

    public void onCustomReportDetails(java.lang.Object p0) {
    }

    public void onDamageTilt(java.lang.Object p0) {
    }

    public void onDeathMessage(java.lang.Object p0) {
    }

    public void onDebugSample(java.lang.Object p0) {
    }

    public void onDifficulty(java.lang.Object p0) {
    }

    public void onDisconnected(java.lang.Object p0) {
    }

    public void onDisconnect(java.lang.Object p0) {
    }

    public void onEndCombat(java.lang.Object p0) {
    }

    public void onEnterCombat(java.lang.Object p0) {
    }

    public void onEnterReconfiguration(java.lang.Object p0) {
    }

    public void onEntitiesDestroy(java.lang.Object p0) {
    }

    public void onEntityAnimation(java.lang.Object p0) {
    }

    public void onEntityAttach(java.lang.Object p0) {
    }

    public void onEntityAttributes(java.lang.Object p0) {
    }

    public void onEntityDamage(java.lang.Object p0) {
    }

    public void onEntityEquipmentUpdate(java.lang.Object p0) {
    }

    public void onEntityPassengersSet(java.lang.Object p0) {
    }

    public void onEntityPositionSync(java.lang.Object p0) {
    }

    public void onEntityPosition(java.lang.Object p0) {
    }

    public void onEntitySetHeadYaw(java.lang.Object p0) {
    }

    public void onEntitySpawn(murat.simv2.simulation.mirror.net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket p0) {
    }

    public void onEntityStatusEffect(java.lang.Object p0) {
    }

    public void onEntityStatus(java.lang.Object p0) {
    }

    public void onEntityTrackerUpdate(java.lang.Object p0) {
    }

    public void onEntityVelocityUpdate(java.lang.Object p0) {
    }

    public void onEntity(java.lang.Object p0) {
    }

    public void onExperienceBarUpdate(java.lang.Object p0) {
    }

    public void onExplosion(java.lang.Object p0) {
    }

    public void onGameJoin(java.lang.Object p0) {
    }

    public void onGameMessage(java.lang.Object p0) {
    }

    public void onGameStateChange(java.lang.Object p0) {
    }

    public void onHealthUpdate(java.lang.Object p0) {
    }

    public void onInventory(java.lang.Object p0) {
    }

    public void onItemPickupAnimation(java.lang.Object p0) {
    }

    public void onKeepAlive(java.lang.Object p0) {
    }

    public void onLightUpdate(java.lang.Object p0) {
    }

    public void onLookAt(java.lang.Object p0) {
    }

    public void onMapUpdate(java.lang.Object p0) {
    }

    public void onMoveMinecartAlongTrack(java.lang.Object p0) {
    }

    public void onNbtQueryResponse(java.lang.Object p0) {
    }

    public void onOpenHorseScreen(java.lang.Object p0) {
    }

    public void onOpenScreen(java.lang.Object p0) {
    }

    public void onOpenWrittenBook(java.lang.Object p0) {
    }

    public void onOverlayMessage(java.lang.Object p0) {
    }

    public void onPacketException(murat.simv2.simulation.mirror.net.minecraft.network.packet.Packet p0, java.lang.Exception p1) {
    }

    public void onParticle(java.lang.Object p0) {
    }

    public void onPingResult(java.lang.Object p0) {
    }

    public void onPing(java.lang.Object p0) {
    }

    public void onPlaySoundFromEntity(java.lang.Object p0) {
    }

    public void onPlaySound(java.lang.Object p0) {
    }

    public void onPlayerAbilities(java.lang.Object p0) {
    }

    public void onPlayerActionResponse(java.lang.Object p0) {
    }

    public void onPlayerListHeader(java.lang.Object p0) {
    }

    public void onPlayerList(java.lang.Object p0) {
    }

    public void onPlayerPositionLook(java.lang.Object p0) {
    }

    public void onPlayerRemove(java.lang.Object p0) {
    }

    public void onPlayerRespawn(java.lang.Object p0) {
    }

    public void onPlayerRotation(java.lang.Object p0) {
    }

    public void onPlayerSpawnPosition(java.lang.Object p0) {
    }

    public void onProfilelessChatMessage(java.lang.Object p0) {
    }

    public void onProjectilePower(java.lang.Object p0) {
    }

    public void onRecipeBookAdd(java.lang.Object p0) {
    }

    public void onRecipeBookRemove(java.lang.Object p0) {
    }

    public void onRecipeBookSettings(java.lang.Object p0) {
    }

    public void onRemoveEntityStatusEffect(java.lang.Object p0) {
    }

    public void onRemoveMessage(java.lang.Object p0) {
    }

    public void onResourcePackRemove(java.lang.Object p0) {
    }

    public void onResourcePackSend(java.lang.Object p0) {
    }

    public void onScoreboardDisplay(java.lang.Object p0) {
    }

    public void onScoreboardObjectiveUpdate(java.lang.Object p0) {
    }

    public void onScoreboardScoreReset(java.lang.Object p0) {
    }

    public void onScoreboardScoreUpdate(java.lang.Object p0) {
    }

    public void onScreenHandlerPropertyUpdate(java.lang.Object p0) {
    }

    public void onScreenHandlerSlotUpdate(java.lang.Object p0) {
    }

    public void onSelectAdvancementTab(java.lang.Object p0) {
    }

    public void onServerLinks(java.lang.Object p0) {
    }

    public void onServerMetadata(java.lang.Object p0) {
    }

    public void onServerTransfer(java.lang.Object p0) {
    }

    public void onSetCameraEntity(java.lang.Object p0) {
    }

    public void onSetCursorItem(java.lang.Object p0) {
    }

    public void onSetPlayerInventory(java.lang.Object p0) {
    }

    public void onSetTradeOffers(java.lang.Object p0) {
    }

    public void onSignEditorOpen(java.lang.Object p0) {
    }

    public void onSimulationDistance(java.lang.Object p0) {
    }

    public void onStartChunkSend(java.lang.Object p0) {
    }

    public void onStatistics(java.lang.Object p0) {
    }

    public void onStopSound(java.lang.Object p0) {
    }

    public void onStoreCookie(java.lang.Object p0) {
    }

    public void onSubtitle(java.lang.Object p0) {
    }

    public void onSynchronizeRecipes(java.lang.Object p0) {
    }

    public void onSynchronizeTags(java.lang.Object p0) {
    }

    public void onTeam(java.lang.Object p0) {
    }

    public void onTestInstanceBlockStatus(java.lang.Object p0) {
    }

    public void onTickStep(java.lang.Object p0) {
    }

    public void onTitleClear(java.lang.Object p0) {
    }

    public void onTitleFade(java.lang.Object p0) {
    }

    public void onTitle(java.lang.Object p0) {
    }

    public void onUnloadChunk(java.lang.Object p0) {
    }

    public void onUpdateSelectedSlot(java.lang.Object p0) {
    }

    public void onUpdateTickRate(java.lang.Object p0) {
    }

    public void onVehicleMove(java.lang.Object p0) {
    }

    public void onWorldBorderCenterChanged(java.lang.Object p0) {
    }

    public void onWorldBorderInitialize(java.lang.Object p0) {
    }

    public void onWorldBorderInterpolateSize(java.lang.Object p0) {
    }

    public void onWorldBorderSizeChanged(java.lang.Object p0) {
    }

    public void onWorldBorderWarningBlocksChanged(java.lang.Object p0) {
    }

    public void onWorldBorderWarningTimeChanged(java.lang.Object p0) {
    }

    public void onWorldEvent(java.lang.Object p0) {
    }

    public void onWorldTimeUpdate(java.lang.Object p0) {
    }

    public com.mojang.brigadier.ParseResults parse(java.lang.String p0) {
        return null;
    }

    public void playSpawnSound(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0) {
    }

    public void readLightData(int p0, int p1, java.lang.Object p2, boolean p3) {
    }

    public void refreshRecipeBook(murat.simv2.simulation.mirror.net.minecraft.client.recipebook.ClientRecipeBook p0) {
    }

    public void refreshSearchManager() {
    }

    public void registerForCleaning(java.lang.Object p0) {
    }

    public void scheduleRenderChunk(murat.simv2.simulation.mirror.net.minecraft.world.chunk.WorldChunk p0, int p1, int p2) {
    }

    public void sendAcknowledgment() {
    }

    public void sendChatCommand(java.lang.String p0) {
    }

    public void sendChatMessage(java.lang.String p0) {
    }

    public boolean sendCommand(java.lang.String p0) {
        return false;
    }

    public void sendPacket(murat.simv2.simulation.mirror.net.minecraft.network.packet.Packet p0) {
    }

    public void sendQueuedPackets() {
    }

    public static boolean setPosition(murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerPosition p0, java.util.Set p1, murat.simv2.simulation.mirror.net.minecraft.entity.Entity p2, boolean p3) {
        return false;
    }

    public void setPublicSession(java.lang.Object p0, murat.simv2.simulation.mirror.net.minecraft.client.network.PlayerListEntry p1) {
    }

    public murat.simv2.simulation.mirror.net.minecraft.registry.Registry.PendingTagLoad startTagReload(murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey p0, java.lang.Object p1) {
        return null;
    }

    public void startWorldLoading(murat.simv2.simulation.mirror.net.minecraft.client.network.ClientPlayerEntity p0, murat.simv2.simulation.mirror.net.minecraft.client.world.ClientWorld p1, murat.simv2.simulation.mirror.net.minecraft.client.gui.screen.DownloadingTerrainScreen.WorldEntryReason p2) {
    }

    public void syncOptions(murat.simv2.simulation.mirror.net.minecraft.network.packet.c2s.common.SyncedClientOptions p0) {
    }

    public void tick() {
    }

    public void unloadChunk(java.lang.Object p0) {
    }

    public void unloadWorld() {
    }

    public void updateKeyPair(java.lang.Object p0) {
    }

    public void updateLighting(int p0, int p1, murat.simv2.simulation.mirror.net.minecraft.world.chunk.light.LightingProvider p2, murat.simv2.simulation.mirror.net.minecraft.world.LightType p3, java.util.BitSet p4, java.util.BitSet p5, java.util.Iterator p6, boolean p7) {
    }

    public void warnOnUnknownPayload(java.lang.Object p0) {
    }

    public ClientPlayNetworkHandler() {
    }

    // END GENERATED MIRROR NESTED STUBS
}
