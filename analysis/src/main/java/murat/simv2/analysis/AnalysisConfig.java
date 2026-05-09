package murat.simv2.analysis;

import java.util.List;
import java.util.Set;

/**
 * Static configuration for the movement-field analysis.
 * <p>
 * The analysis answers a single question: <em>what code on the client player
 * affects {@code Entity.pos} during a single tick?</em> Everything else — the
 * field manifest, the mirror closure, the generated sync code — is derived
 * mechanically from the WALA backward slice of that question.
 */
public final class AnalysisConfig {

    private AnalysisConfig() {
    }

    public static final String ENTITY_INTERNAL = "Lnet/minecraft/entity/Entity";

    /** Internal name of the seed field — the only thing we slice backward from. */
    public static final String SEED_FIELD_NAME = "pos";

    /** Entry point method (class, method, descriptor) for the call graph. */
    public static final EntryMethod ENTRY_METHOD = new EntryMethod(
        "Lnet/minecraft/client/network/ClientPlayerEntity",
        "tickMovement",
        "()V"
    );

    /**
     * WALA scope exclusions. We deliberately keep the exclusion list focused
     * on packages that only exist for rendering / data / network and never
     * influence movement. We do NOT exclude {@code java.*} — pointer analysis
     * requires it.
     */
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
        "net/minecraft/world/storage/.*"
    );

    /**
     * Heap-flow exclusions for SDG construction. These types stay in the
     * call graph but the slicer will not track per-instance heap data flow
     * through them. This keeps the IFDS solver tractable on JDK string and
     * collection internals and on a few enormous {@code <clinit>}s in MC.
     */
    public static final List<String> SLICER_HEAP_EXCLUSIONS = List.of(
        "java/lang/String",
        "java/lang/AbstractStringBuilder",
        "java/lang/StringBuilder",
        "java/lang/StringBuffer",
        "java/lang/StringUTF16",
        "java/lang/StringLatin1",
        "java/lang/StringConcatHelper",
        "java/lang/Throwable",
        "java/lang/Exception",
        "java/lang/RuntimeException",
        "java/lang/NullPointerException",
        "java/lang/NumberFormatException",
        "java/lang/Class",
        "java/lang/Module.*",
        "java/lang/Thread",
        "java/lang/SecurityManager",
        "java/lang/Enum",
        "java/lang/Integer",
        "java/time/.*",
        "java/util/.*",
        "java/util/regex/.*",
        "java/util/concurrent/atomic/.*",
        "java/util/function/.*",
        "java/util/stream/.*",
        "net/minecraft/component/.*",
        "net/minecraft/state/.*",
        "net/minecraft/entity/damage/.*",
        "net/minecraft/entity/attribute/.*",
        "net/minecraft/entity/effect/StatusEffects",
        "net/minecraft/nbt/.*",
        "net/minecraft/registry/.*",
        "net/minecraft/world/event/GameEvent",
        "net/minecraft/world/Difficulty",
        "net/minecraft/world/GameMode",
        "net/minecraft/world/GameRules.*",
        "net/minecraft/world/chunk/.*",
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
