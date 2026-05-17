package murat.simv2.analysis;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * {@link SinkRules} is the single source of truth for which reachable callees
 * are side effects that must be gated by a Mixin while a prediction runs. Each
 * denylist category is exercised here so a regression in
 * {@link AnalysisConfig#MIRROR_SINKS} fails the build, and the negative cases
 * pin that movement reads (collision/blockstate/position) pass through.
 */
class SinkRulesTest {

    @Test
    void networkSendsAreSinks() {
        assertTrue(SinkRules.isSink(
            "net/minecraft/client/network/ClientPlayNetworkHandler", "sendPacket"));
        assertTrue(SinkRules.isSink(
            "net/minecraft/client/network/ClientCommonNetworkHandler", "send"));
        // sendAbilitiesUpdate is denylisted for any owner.
        assertTrue(SinkRules.isSink(
            "net/minecraft/client/network/ClientPlayerEntity", "sendAbilitiesUpdate"));
        // "send*" only counts on a network handler owner.
        assertFalse(SinkRules.isSink(
            "net/minecraft/world/World", "sendPacket"));
    }

    @Test
    void soundParticleEventCallbacksAreSinks() {
        assertTrue(SinkRules.isSink("net/minecraft/world/World", "playSound"));
        assertTrue(SinkRules.isSink("net/minecraft/world/World", "playSoundFromEntity"));
        assertTrue(SinkRules.isSink("net/minecraft/client/world/ClientWorld", "addParticle"));
        assertTrue(SinkRules.isSink("net/minecraft/client/world/ClientWorld", "addParticleClient"));
        assertTrue(SinkRules.isSink("net/minecraft/world/World", "addBlockBreakParticles"));
        assertTrue(SinkRules.isSink("net/minecraft/entity/Entity", "spawnSprintingParticles"));
        assertTrue(SinkRules.isSink("net/minecraft/world/World", "emitGameEvent"));
        assertTrue(SinkRules.isSink("net/minecraft/world/World", "syncWorldEvent"));
        assertTrue(SinkRules.isSink("net/minecraft/world/World", "playLevelEvent"));
        assertTrue(SinkRules.isSink("net/minecraft/block/Block", "onEntityCollision"));
        assertTrue(SinkRules.isSink("net/minecraft/block/AbstractBlock", "onSteppedOn"));
        assertTrue(SinkRules.isSink("net/minecraft/block/AbstractBlock", "onLandedUpon"));
        assertTrue(SinkRules.isSink("net/minecraft/block/Block", "onEntityLand"));
    }

    @Test
    void clientSingletonMutatorsAreSinks() {
        assertTrue(SinkRules.isSink(
            "net/minecraft/client/tutorial/TutorialManager", "onMovement"));
        assertTrue(SinkRules.isSink(
            "net/minecraft/client/MinecraftClient", "setScreen"));
        // onMovement / setScreen are only sinks on those specific owners.
        assertFalse(SinkRules.isSink("net/minecraft/entity/Entity", "onMovement"));
        assertFalse(SinkRules.isSink("net/minecraft/entity/Entity", "setScreen"));
    }

    @Test
    void crossEntityPushIsSink() {
        // A co-located clone must not shove the real player.
        assertTrue(SinkRules.isSink("net/minecraft/entity/LivingEntity", "tickCramming"));
        assertTrue(SinkRules.isSink("net/minecraft/entity/Entity", "pushAwayFrom"));
    }

    @Test
    void worldAndEntityWritesAreSinks() {
        // With the tick() entry the clone must never litter the real world.
        assertTrue(SinkRules.isSink("net/minecraft/world/World", "spawnEntity"));
        assertTrue(SinkRules.isSink("net/minecraft/entity/Entity", "dropStack"));
        assertTrue(SinkRules.isSink("net/minecraft/entity/player/PlayerEntity", "dropItem"));
        assertTrue(SinkRules.isSink("net/minecraft/entity/LivingEntity", "dropInventory"));
        assertTrue(SinkRules.isSink("net/minecraft/world/World", "setBlockState"));
        assertTrue(SinkRules.isSink("net/minecraft/world/World", "breakBlock"));
        assertTrue(SinkRules.isSink("net/minecraft/world/World", "removeBlock"));
    }

    @Test
    void movementReadsPassThrough() {
        assertFalse(SinkRules.isSink("net/minecraft/world/World", "getBlockState"));
        assertFalse(SinkRules.isSink("net/minecraft/entity/Entity", "getPos"));
        assertFalse(SinkRules.isSink("net/minecraft/entity/Entity", "move"));
        assertFalse(SinkRules.isSink("net/minecraft/world/World", "getEntityCollisions"));
        assertFalse(SinkRules.isSink("net/minecraft/entity/Entity", "setVelocity"));
    }
}
