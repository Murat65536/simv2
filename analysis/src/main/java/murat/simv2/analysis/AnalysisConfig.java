package murat.simv2.analysis;

import java.util.List;
import java.util.Set;

public final class AnalysisConfig {

    private AnalysisConfig() {
    }

    public static final String ENTITY_INTERNAL = "Lnet/minecraft/entity/Entity";

    public static final String SEED_FIELD_NAME = "pos";

    public static final EntryMethod ENTRY_METHOD = new EntryMethod(
        "Lnet/minecraft/client/network/ClientPlayerEntity",
        "tickMovement",
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
        "net/minecraft/client/data/.*"
    );

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
        "net/minecraft/block/.*",
        "net/minecraft/block/entity/.*",
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
