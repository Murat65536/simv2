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

    public static final String TARGET_PACKAGE_INTERNAL = "testproject/";
    public static final String TARGET_PACKAGE_DOT = "testproject.";
    public static final String TARGET_PACKAGE_INTERNAL_L = "Ltestproject/";

    public static final String ENTITY_INTERNAL = "Ltestproject/TargetEntity";

    /** Internal name of the seed field — the only thing we slice backward from. */
    public static final String SEED_FIELD_NAME = "targetVar";

    /** Entry point method (class, method, descriptor) for the call graph. */
    public static final EntryMethod ENTRY_METHOD = new EntryMethod(
        "Ltestproject/MainEntry",
        "entry",
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
        "javax\\/.*"
    };

    public static final String[] SLICER_HEAP_EXCLUSIONS = {
        "java\\/.*",
        "javax\\/.*",
        "sun\\/.*",
        "com\\/sun\\/.*",
        "org\\/lwjgl\\/.*"
    };
}
