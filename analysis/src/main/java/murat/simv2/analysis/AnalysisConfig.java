package murat.simv2.analysis;

import java.util.List;
import java.util.Set;

public final class AnalysisConfig {

    private AnalysisConfig() {
    }

    public static final String ENTITY_INTERNAL = "Lnet/minecraft/entity/Entity";

    public static final String SEED_FIELD_NAME = "pos";

    // The slice is seeded on calls that mutate an entity's position/velocity, but
    // only when the receiver provably belongs to this (the movement) hierarchy.
    // Entity.pos itself is written in one shared place (Entity.setPos / Entity.<init>)
    // whose receiver aliases the player AND any XP orb / dropped item positioned
    // during the tick — so the write site cannot tell them apart. The mutator CALL
    // sites can: the player moves via move/travel* on a LivingEntity receiver, while
    // orbs/items are positioned in their constructors on orb/item receivers (which
    // are Entity but NOT LivingEntity). Seeding only LivingEntity-receiver mutations
    // separates the two.
    public static final String MOVEMENT_RECEIVER_INTERNAL = "Lnet/minecraft/entity/LivingEntity";

    public static final Set<String> POSITION_MUTATORS = Set.of(
        "setPosition", "setPos", "setPosRaw", "updatePosition",
        "setVelocity", "addVelocity", "updateVelocity");

    // The per-tick entry. tick() (not the narrower tickMovement()) is used because it
    // covers ALL per-tick movement — super.tick() reaches Entity.tick/LivingEntity.tick
    // (fall, fluid push, riding, portals, collision-shape math), which tickMovement
    // alone misses. Overridable via -PanalysisEntrySelector / -PanalysisEntryClass.
    public static final EntryMethod ENTRY_METHOD = new EntryMethod(
        "Lnet/minecraft/client/network/ClientPlayerEntity",
        "tick",
        "()V"
    );

    public static final List<String> REQUIRED_PRIMARY_CLASSES = List.of(
        "net.minecraft.entity.Entity",
        "net.minecraft.entity.LivingEntity",
        "net.minecraft.entity.player.PlayerEntity",
        "net.minecraft.client.network.AbstractClientPlayerEntity",
        "net.minecraft.client.network.ClientPlayerEntity"
    );

    public static final List<String> WALA_EXCLUSIONS = List.of(
        "net/minecraft/client/render/.*",
        "net/minecraft/client/gui/.*",
        "net/minecraft/client/font/.*",
        "net/minecraft/client/model/.*",
        "net/minecraft/client/color/.*",
        "net/minecraft/client/texture/.*",
        "net/minecraft/client/gl/.*",
        "net/minecraft/client/particle/.*",
        "net/minecraft/client/sound/.*",
        "net/minecraft/client/realms/.*",
        "net/minecraft/client/splash/.*",
        "net/minecraft/client/toast/.*",
        "net/minecraft/client/tutorial/.*",
        "net/minecraft/client/resource/.*",
        "net/minecraft/client/search/.*",
        "net/minecraft/client/option/.*",
        "net/minecraft/server/.*",
        "net/minecraft/dedicated/.*",
        "net/minecraft/network/.*",
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
        "net/minecraft/registry/.*",
        "net/minecraft/component/.*",
        "net/minecraft/predicate/.*",
        "net/minecraft/village/.*",
        "net/minecraft/structure/.*",
        "net/minecraft/screen/.*",
        "net/minecraft/inventory/.*",
        "net/minecraft/enchantment/.*",
        "net/minecraft/item/tooltip/.*",
        "net/minecraft/world/chunk/.*",
        "net/minecraft/world/biome/.*",
        "net/minecraft/world/gen/.*",
        "net/minecraft/world/dimension/.*",
        "net/minecraft/world/level/storage/.*",
        "net/minecraft/world/storage/.*",

        "net/minecraft/entity/ai/.*",
        "net/minecraft/util/profiler/.*",
        "net/minecraft/test/.*",
        "net/minecraft/client/data/.*",
        // Crash reporting: tick() wraps its body in try/catch(CrashReport ...). The
        // catch path is never movement. None of it can carry velocity->position flow.
        "net/minecraft/util/crash/.*",
        "net/minecraft/util/thread/.*",
        // Non-movement leaves pulled in purely by StringIdentifiable interface
        // dispatch: a single StringIdentifiable.asString()/codec call resolves under
        // CHA to ~every enum implementer, dragging these unrelated enums (and their
        // name/id fields) into the slice. We exclude only the ones with no movement
        // role — GameMode (flight/spectator noclip), attributes, DataTracker and
        // equipment are movement-coupled and deliberately kept.
        "net/minecraft/util/Formatting",
        "net/minecraft/util/Arm",
        "net/minecraft/entity/SpawnGroup",
        "net/minecraft/entity/passive/.*"
    );

    // Heap locations excluded when an SDG carries heap dependences. The NO_HEAP
    // slice has no heap edges, so these are inert today; they confine the heap scope
    // only if a heap-aware SDG is ever built. Retained as the curated "cannot carry
    // velocity->position physics" set for any future heap-aware SDG construction.
    public static final List<String> SLICER_HEAP_EXCLUSIONS = List.of(
        // --- The entire JDK. Its shared heap (collections, java/lang/invoke
        // MethodHandles, IO, NIO, reflection, ref) carries no movement physics,
        // so exclude it wholesale from heap dataflow. (Vec3d/BlockPos and Entity
        // fields live under net/minecraft, so they are unaffected.)
        "java/.*",
        "javax/.*",
        "jdk/.*",
        "sun/.*",
        "com/sun/.*",
        // --- Minecraft subsystems that cannot carry velocity->position flow.
        "net/minecraft/block/.*",
        "net/minecraft/component/.*",
        "net/minecraft/state/.*",
        "net/minecraft/entity/damage/.*",
        "net/minecraft/entity/attribute/.*",
        "net/minecraft/entity/effect/StatusEffects",
        "net/minecraft/fluid/.*",
        "net/minecraft/item/.*",
        "net/minecraft/nbt/.*",
        "net/minecraft/registry/.*",
        "net/minecraft/world/.*",
        "net/minecraft/client/world/.*",
        "net/minecraft/server/world/.*",
        "net/minecraft/util/shape/.*",
        "net/minecraft/util/Identifier",
        "net/minecraft/util/Formatting",
        "net/minecraft/util/DyeColor",
        "net/minecraft/util/Rarity"
    );

    public record EntryMethod(String classInternal, String name, String descriptor) {
        public String selector() {
            return name + descriptor;
        }
    }
}
