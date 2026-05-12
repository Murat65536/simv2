package murat.simv2.analysis;

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

    public static final String TARGET_PACKAGE_INTERNAL = "net/minecraft/";
    public static final String TARGET_PACKAGE_DOT = "net.minecraft.";
    public static final String TARGET_PACKAGE_INTERNAL_L = "Lnet/minecraft/";

    public static final String ENTITY_INTERNAL = "Lnet/minecraft/entity/Entity";

    /** Internal name of the seed field — the only thing we slice backward from. */
    public static final String SEED_FIELD_NAME = "pos";

    /**
     * Entry point method (class, method, descriptor) for the call graph.
     * <p>{@code tickMovement} (not {@code tick}) keeps the call graph scoped to
     * the movement subsystem — input sampling, physics, collision, the
     * {@code Entity.move} chain — without dragging rendering, sound, GUI, and
     * network-send into the slice.
     */
    public static final EntryMethod ENTRY_METHOD = new EntryMethod(
        "Lnet/minecraft/client/network/ClientPlayerEntity",
        "tickMovement",
        "()V"
    );

    public record EntryMethod(String classInternal, String name, String descriptor) {
        public String selector() {
            return name + descriptor;
        }
    }

    public static final String[] WALA_EXCLUSIONS = {
        "java\\/awt\\/.*",
        "javax\\/swing\\/.*",
        "sun\\/awt\\/.*",
        "sun\\/swing\\/.*",
        "com\\/sun\\/.*",
        "sun\\/.*",
        "org\\/lwjgl\\/.*",
        "java\\/nio\\/.*",
        "java\\/net\\/.*",
        "java\\/text\\/.*",
        "java\\/sql\\/.*",
        "java\\/rmi\\/.*",
        "java\\/security\\/.*",
        "java\\/io\\/.*",
        "java\\/util\\/.*",
        "java\\/util\\/concurrent\\/.*",
        "javax\\/.*",
        "it\\/unimi\\/dsi\\/fastutil\\/.*",
        "com\\/mojang\\/datafixers\\/.*",
        "com\\/mojang\\/serialization\\/.*",
        "com\\/mojang\\/brigadier\\/.*",
        "com\\/mojang\\/blaze3d\\/.*"
    };

    public static final String[] SLICER_HEAP_EXCLUSIONS = {
        "java\\/.*",
        "javax\\/.*",
        "sun\\/.*",
        "com\\/sun\\/.*",
        "org\\/lwjgl\\/.*"
    };
}
