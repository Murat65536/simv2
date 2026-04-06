package murat.simv2.analysis;

import com.ibm.wala.types.TypeReference;

import java.util.List;
import java.util.Set;

public final class AnalysisConfig {

    private AnalysisConfig() {}

    public static final Set<String> TARGET_CLASSES = Set.of(
        "Lnet/minecraft/entity/Entity",
        "Lnet/minecraft/entity/LivingEntity",
        "Lnet/minecraft/entity/player/PlayerEntity",
        "Lnet/minecraft/client/network/AbstractClientPlayerEntity",
        "Lnet/minecraft/client/network/ClientPlayerEntity"
    );

    public static final String ENTITY_CLASS = "Lnet/minecraft/entity/Entity";
    public static final String LIVING_ENTITY_CLASS = "Lnet/minecraft/entity/LivingEntity";
    public static final String CLIENT_PLAYER_CLASS = "Lnet/minecraft/client/network/ClientPlayerEntity";

    public static final List<EntryMethod> ENTRY_METHODS = List.of(
        new EntryMethod(LIVING_ENTITY_CLASS, "tickMovement", "()V")
    );

    public static final Set<String> SEED_FIELDS = Set.of(
        "pos", "velocity", "boundingBox", "onGround", "fallDistance"
    );

    public static final Set<String> SEED_METHODS = Set.of(
        "setPosition", "setVelocity", "setPos", "move"
    );

    // Fields that WALA discovers as reachable but must not be synced.
    // These are identity fields, shared references, or internal bookkeeping
    // that would corrupt entity state if copied between instances.
    public static final Set<String> EXCLUDED_FIELDS = Set.of(
        // Identity — copying these makes the game treat sim as the real player
        "uuid", "uuidString", "id", "type", "gameProfile",
        // Shared references — must not alias between entities
        "dataTracker", "world", "random", "changeListener",
        "collisionHandler", "currentlyCheckedCollisions", "queuedCollisionChecks",
        "collidedBlockPositions", "brain", "tickables", "passengerList",
        "lastEquipmentStacks", "itemCooldownManager", "portalManager",
        "playerScreenHandler", "currentScreenHandler",
        // Internal bookkeeping recomputed each tick
        "removalReason", "firstUpdate", "age",
        // Damage/combat state — not relevant to movement prediction
        "damageTracker", "lastDamageSource", "lastDamageTaken", "lastDamageTime",
        "attackerReference", "attacking", "attackingPlayer",
        "lastAttackTime", "lastAttackedTime", "hurtTime", "maxHurtTime",
        "playerHitTimer", "dead", "deathTime", "experienceDroppingDisabled",
        // Render interpolation — only relevant on the rendering side
        "lastRenderX", "lastRenderY", "lastRenderZ",
        "lastRenderPitch", "lastRenderYaw", "renderPitch", "renderYaw",
        "headTrackingIncrements", "interpolator", "serverHeadYaw",
        "limbAnimator", "capeX", "capeY", "capeZ",
        "lastCapeX", "lastCapeY", "lastCapeZ",
        "distanceMoved", "lastDistanceMoved", "lastVelocity",
        // Non-movement player state
        "experienceLevel", "experienceProgress", "experiencePickUpDelay",
        "totalExperience", "enchantingTableSeed", "enderChestInventory",
        "shoulderEntityAddedTime", "damageTiltYaw",
        "sleepTimer", "lastDeathPos", "playerListEntry",
        "despawnCounter", "riptideAttackDamage", "riptideStack",
        "selectedItem", "remainingLoadTicks",
        "currentExplosionImpactPos", "currentExplosionResetGraceTime",
        "ignoreFallDamageFromCurrentExplosion", "explodedBy",
        // Visual-only
        "hasVisualFire", "lastChimeAge", "lastChimeIntensity",
        "distanceTraveled", "nextStepSoundDistance",
        "stuckArrowTimer", "stuckStingerTimer",
        "handSwingProgress", "lastHandSwingProgress", "handSwingTicks", "handSwinging",
        "preferredHand",
        // Network sync state — not relevant for local prediction
        "lastXClient", "lastYClient", "lastZClient",
        "lastYawClient", "lastPitchClient",
        "lastSneaking", "lastSprinting", "lastOnGround", "lastHorizontalCollision",
        "lastPlayerInput", "ticksSinceLastPositionPacketSent",
        "itemDropCooldown",
        // Client state that should be read but not copied
        "client", "networkHandler"
    );

    public static final List<String> EXCLUSIONS = List.of(
        // Rendering
        "net/minecraft/client/render/.*",
        "net/minecraft/client/gui/.*",
        "net/minecraft/client/font/.*",
        "net/minecraft/client/texture/.*",
        "net/minecraft/client/gl/.*",
        "net/minecraft/client/particle/.*",
        "net/minecraft/client/sound/.*",
        "net/minecraft/client/realms/.*",
        "net/minecraft/client/toast/.*",
        "net/minecraft/client/tutorial/.*",
        "net/minecraft/client/resource/.*",
        "net/minecraft/client/search/.*",
        "net/minecraft/client/option/.*",
        // Server
        "net/minecraft/server/.*",
        "net/minecraft/dedicated/.*",
        // Networking
        "net/minecraft/network/.*",
        // Data / non-movement systems
        "net/minecraft/data/.*",
        "net/minecraft/advancement/.*",
        "net/minecraft/recipe/.*",
        "net/minecraft/loot/.*",
        "net/minecraft/command/.*",
        "net/minecraft/scoreboard/.*",
        "net/minecraft/text/.*",
        "net/minecraft/nbt/.*",
        "net/minecraft/datafixer/.*",
        "net/minecraft/stat/.*",
        "net/minecraft/village/.*",
        "net/minecraft/structure/.*",
        "net/minecraft/screen/.*",
        "net/minecraft/inventory/.*",
        "net/minecraft/enchantment/.*",
        "net/minecraft/component/.*",
        "net/minecraft/registry/.*",
        "net/minecraft/resource/.*",
        "net/minecraft/predicate/.*",
        "net/minecraft/particle/.*",
        "net/minecraft/sound/.*",
        // Java stdlib — aggressive exclusions to shrink CG/SDG
        "java/awt/.*",
        "javax/.*",
        "sun/.*",
        "com/sun/.*",
        "jdk/.*",
        "java/lang/invoke/.*",
        "java/lang/reflect/.*",
        "java/lang/ref/.*",
        "java/io/.*",
        "java/nio/.*",
        "java/net/.*",
        "java/security/.*",
        "java/text/.*",
        "java/time/.*",
        "java/util/concurrent/.*",
        "java/util/logging/.*",
        "java/util/regex/.*",
        "java/util/stream/.*",
        "java/util/zip/.*",
        "java/util/jar/.*",
        // Third-party libs pulled in by MC
        "com/google/.*",
        "com/mojang/.*",
        "org/apache/.*",
        "org/slf4j/.*",
        "io/netty/.*",
        "it/unimi/dsi/fastutil/.*"
    );

    // Methods to extract via backward slicing + Spoon pruning
    public static final Set<String> TARGET_METHODS = Set.of(
        "tickMovement"
    );

    // Dot-notation class names for Spoon
    public static final List<String> TARGET_CLASSES_DOT = List.of(
        "net.minecraft.entity.Entity",
        "net.minecraft.entity.LivingEntity",
        "net.minecraft.entity.player.PlayerEntity",
        "net.minecraft.client.network.AbstractClientPlayerEntity",
        "net.minecraft.client.network.ClientPlayerEntity"
    );

    public record EntryMethod(String className, String methodName, String descriptor) {}
}
