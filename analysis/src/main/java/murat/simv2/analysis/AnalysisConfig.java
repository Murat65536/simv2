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

    public static final String ENTITY_INTERNAL = "Lnet/minecraft/entity/Entity";

    /** Internal name of the seed field — the only thing we slice backward from. */
    public static final String SEED_FIELD_NAME = "pos";

    /** Entry point method (class, method, descriptor) for the call graph. */
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
}
