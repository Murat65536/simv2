package murat.simv2.simulation.mirror.net.minecraft.server.command;

// Generated mirror stub for simulation closure.
public class ServerCommandSource extends java.lang.Object {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static com.mojang.brigadier.exceptions.SimpleCommandExceptionType REQUIRES_ENTITY_EXCEPTION;
    public static com.mojang.brigadier.exceptions.SimpleCommandExceptionType REQUIRES_PLAYER_EXCEPTION;
    public murat.simv2.simulation.mirror.net.minecraft.text.Text displayName;
    public murat.simv2.simulation.mirror.net.minecraft.entity.Entity entity;
    public murat.simv2.simulation.mirror.net.minecraft.command.argument.EntityAnchorArgumentType.EntityAnchor entityAnchor;
    public int level;
    public java.lang.Object messageChainTaskQueue;
    public java.lang.String name;
    public murat.simv2.simulation.mirror.net.minecraft.server.command.CommandOutput output;
    public murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d position;
    public java.lang.Object returnValueConsumer;
    public murat.simv2.simulation.mirror.net.minecraft.util.math.Vec2f rotation;
    public murat.simv2.simulation.mirror.net.minecraft.server.MinecraftServer server;
    public java.lang.Object signedArguments;
    public boolean silent;
    public murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld world;

    public ServerCommandSource(murat.simv2.simulation.mirror.net.minecraft.server.command.CommandOutput p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p1, murat.simv2.simulation.mirror.net.minecraft.util.math.Vec2f p2, murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p3, int p4, java.lang.String p5, murat.simv2.simulation.mirror.net.minecraft.text.Text p6, murat.simv2.simulation.mirror.net.minecraft.server.MinecraftServer p7, murat.simv2.simulation.mirror.net.minecraft.entity.Entity p8) {
    }

    public ServerCommandSource(murat.simv2.simulation.mirror.net.minecraft.server.command.CommandOutput p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p1, murat.simv2.simulation.mirror.net.minecraft.util.math.Vec2f p2, murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p3, int p4, java.lang.String p5, murat.simv2.simulation.mirror.net.minecraft.text.Text p6, murat.simv2.simulation.mirror.net.minecraft.server.MinecraftServer p7, murat.simv2.simulation.mirror.net.minecraft.entity.Entity p8, boolean p9, java.lang.Object p10, murat.simv2.simulation.mirror.net.minecraft.command.argument.EntityAnchorArgumentType.EntityAnchor p11, java.lang.Object p12, java.lang.Object p13) {
    }

    public static com.mojang.brigadier.ResultConsumer asResultConsumer() {
        return null;
    }

    public static void forEachMatching(java.lang.Iterable p0, java.lang.String p1, java.lang.String p2, java.util.function.Function p3, java.util.function.Consumer p4) {
    }

    public static void forEachMatching(java.lang.Iterable p0, java.lang.String p1, java.util.function.Function p2, java.util.function.Consumer p3) {
    }

    public java.util.Collection getBlockPositionSuggestions() {
        return null;
    }

    public java.util.Collection getChatSuggestions() {
        return null;
    }

    public java.util.concurrent.CompletableFuture getCompletions(com.mojang.brigadier.context.CommandContext p0) {
        return null;
    }

    public com.mojang.brigadier.CommandDispatcher getDispatcher() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.text.Text getDisplayName() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.resource.featuretoggle.FeatureSet getEnabledFeatures() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.command.argument.EntityAnchorArgumentType.EntityAnchor getEntityAnchor() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.entity.Entity getEntityOrThrow() {
        return null;
    }

    public java.util.Collection getEntitySuggestions() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.entity.Entity getEntity() {
        return null;
    }

    public java.lang.Object getMessageChainTaskQueue() {
        return null;
    }

    public java.lang.String getName() {
        return null;
    }

    public java.util.Collection getPlayerNames() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.server.network.ServerPlayerEntity getPlayerOrThrow() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.server.network.ServerPlayerEntity getPlayer() {
        return null;
    }

    public java.util.Collection getPositionSuggestions() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d getPosition() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.registry.DynamicRegistryManager getRegistryManager() {
        return null;
    }

    public java.lang.Object getReturnValueConsumer() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.Vec2f getRotation() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.server.MinecraftServer getServer() {
        return null;
    }

    public java.lang.Object getSignedArguments() {
        return null;
    }

    public java.util.stream.Stream getSoundIds() {
        return null;
    }

    public java.util.Collection getTeamNames() {
        return null;
    }

    public java.util.Set getWorldKeys() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld getWorld() {
        return null;
    }

    public void handleException(com.mojang.brigadier.exceptions.CommandExceptionType p0, com.mojang.brigadier.Message p1, boolean p2, java.lang.Object p3) {
    }

    public void handleException(com.mojang.brigadier.exceptions.CommandSyntaxException p0, boolean p1, java.lang.Object p2) {
    }

    public boolean hasPermissionLevel(int p0) {
        return false;
    }

    public boolean isExecutedByPlayer() {
        return false;
    }

    public boolean isSilent() {
        return false;
    }

    public java.util.concurrent.CompletableFuture listIdSuggestions(murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey p0, java.lang.Object p1, com.mojang.brigadier.suggestion.SuggestionsBuilder p2, com.mojang.brigadier.context.CommandContext p3) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.server.command.ServerCommandSource mergeReturnValueConsumers(java.lang.Object p0, java.util.function.BinaryOperator p1) {
        return null;
    }

    public void sendChatMessage(murat.simv2.simulation.mirror.net.minecraft.network.message.SentMessage p0, boolean p1, murat.simv2.simulation.mirror.net.minecraft.network.message.MessageType.Parameters p2) {
    }

    public void sendError(murat.simv2.simulation.mirror.net.minecraft.text.Text p0) {
    }

    public void sendFeedback(java.util.function.Supplier p0, boolean p1) {
    }

    public void sendMessage(murat.simv2.simulation.mirror.net.minecraft.text.Text p0) {
    }

    public void sendToOps(murat.simv2.simulation.mirror.net.minecraft.text.Text p0) {
    }

    public boolean shouldFilterText(murat.simv2.simulation.mirror.net.minecraft.server.network.ServerPlayerEntity p0) {
        return false;
    }

    public static boolean shouldSuggest(java.lang.String p0, java.lang.String p1) {
        return false;
    }

    public static java.util.concurrent.CompletableFuture suggestColumnPositions(java.lang.String p0, java.util.Collection p1, com.mojang.brigadier.suggestion.SuggestionsBuilder p2, java.util.function.Predicate p3) {
        return null;
    }

    public static java.util.concurrent.CompletableFuture suggestFromIdentifier(java.lang.Iterable p0, com.mojang.brigadier.suggestion.SuggestionsBuilder p1, java.util.function.Function p2, java.util.function.Function p3) {
        return null;
    }

    public static java.util.concurrent.CompletableFuture suggestFromIdentifier(java.util.stream.Stream p0, com.mojang.brigadier.suggestion.SuggestionsBuilder p1, java.util.function.Function p2, java.util.function.Function p3) {
        return null;
    }

    public static java.util.concurrent.CompletableFuture suggestIdentifiers(java.lang.Iterable p0, com.mojang.brigadier.suggestion.SuggestionsBuilder p1) {
        return null;
    }

    public static java.util.concurrent.CompletableFuture suggestIdentifiers(java.lang.Iterable p0, com.mojang.brigadier.suggestion.SuggestionsBuilder p1, java.lang.String p2) {
        return null;
    }

    public static java.util.concurrent.CompletableFuture suggestIdentifiers(java.util.stream.Stream p0, com.mojang.brigadier.suggestion.SuggestionsBuilder p1) {
        return null;
    }

    public static java.util.concurrent.CompletableFuture suggestIdentifiers(java.util.stream.Stream p0, com.mojang.brigadier.suggestion.SuggestionsBuilder p1, java.lang.String p2) {
        return null;
    }

    public void suggestIdentifiers(murat.simv2.simulation.mirror.net.minecraft.registry.Registry p0, java.lang.Object p1, com.mojang.brigadier.suggestion.SuggestionsBuilder p2) {
    }

    public static java.util.concurrent.CompletableFuture suggestMatching(java.lang.Iterable p0, com.mojang.brigadier.suggestion.SuggestionsBuilder p1) {
        return null;
    }

    public static java.util.concurrent.CompletableFuture suggestMatching(java.lang.Iterable p0, com.mojang.brigadier.suggestion.SuggestionsBuilder p1, java.util.function.Function p2, java.util.function.Function p3) {
        return null;
    }

    public static java.util.concurrent.CompletableFuture suggestMatching(java.lang.String[] p0, com.mojang.brigadier.suggestion.SuggestionsBuilder p1) {
        return null;
    }

    public static java.util.concurrent.CompletableFuture suggestMatching(java.util.stream.Stream p0, com.mojang.brigadier.suggestion.SuggestionsBuilder p1) {
        return null;
    }

    public static java.util.concurrent.CompletableFuture suggestPositions(java.lang.String p0, java.util.Collection p1, com.mojang.brigadier.suggestion.SuggestionsBuilder p2, java.util.function.Predicate p3) {
        return null;
    }

    public java.lang.Object withDummyReturnValueConsumer() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.server.command.ServerCommandSource withEntityAnchor(murat.simv2.simulation.mirror.net.minecraft.command.argument.EntityAnchorArgumentType.EntityAnchor p0) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.server.command.ServerCommandSource withEntity(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.server.command.ServerCommandSource withLevel(int p0) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.server.command.ServerCommandSource withLookingAt(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, murat.simv2.simulation.mirror.net.minecraft.command.argument.EntityAnchorArgumentType.EntityAnchor p1) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.server.command.ServerCommandSource withLookingAt(murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p0) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.server.command.ServerCommandSource withMaxLevel(int p0) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.server.command.ServerCommandSource withOutput(murat.simv2.simulation.mirror.net.minecraft.server.command.CommandOutput p0) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.server.command.ServerCommandSource withPosition(murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p0) {
        return null;
    }

    public java.lang.Object withReturnValueConsumer(java.lang.Object p0) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.server.command.ServerCommandSource withRotation(murat.simv2.simulation.mirror.net.minecraft.util.math.Vec2f p0) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.server.command.ServerCommandSource withSignedArguments(java.lang.Object p0, java.lang.Object p1) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.server.command.ServerCommandSource withSilent() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.server.command.ServerCommandSource withWorld(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0) {
        return null;
    }

    public ServerCommandSource() {
    }

    // END GENERATED MIRROR NESTED STUBS
}
