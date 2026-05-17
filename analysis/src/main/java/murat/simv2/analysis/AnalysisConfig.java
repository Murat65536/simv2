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
        "com\\/mojang\\/blaze3d\\/.*",
        // tickMovement cannot reach rendering/sound/GUI/data. Excluding
        // these drops their CG nodes (and heap) from the SDG.
        "net\\/minecraft\\/client\\/render\\/.*",
        "net\\/minecraft\\/client\\/gui\\/.*",
        "net\\/minecraft\\/client\\/sound\\/.*",
        "net\\/minecraft\\/client\\/particle\\/.*",
        "net\\/minecraft\\/client\\/texture\\/.*",
        "net\\/minecraft\\/client\\/font\\/.*",
        "net\\/minecraft\\/client\\/model\\/.*",
        "net\\/minecraft\\/data\\/.*",
        "net\\/minecraft\\/datafixer\\/.*",
        "net\\/minecraft\\/server\\/.*"
    };

    public static final String[] SLICER_HEAP_EXCLUSIONS = {
        "java\\/.*",
        "javax\\/.*",
        "sun\\/.*",
        "com\\/sun\\/.*",
        "org\\/lwjgl\\/.*",
        // Highest-leverage Tier-1 lever: cut heap-dependence edges for
        // movement-irrelevant subsystems without changing DataDependenceOptions.
        "com\\/mojang\\/.*",
        "io\\/netty\\/.*",
        "net\\/minecraft\\/client\\/render\\/.*",
        "net\\/minecraft\\/client\\/gui\\/.*",
        "net\\/minecraft\\/client\\/sound\\/.*",
        "net\\/minecraft\\/client\\/particle\\/.*",
        "net\\/minecraft\\/client\\/texture\\/.*",
        "net\\/minecraft\\/client\\/font\\/.*",
        "net\\/minecraft\\/client\\/model\\/.*",
        "net\\/minecraft\\/datafixer\\/.*"
    };

    /**
     * Sink call-sites neutralized in the mirror's child-first class copies
     * (and <em>only</em> those copies — the real game's classes are emitted
     * verbatim, so this can never affect live gameplay). A movement tick run
     * on the mirror must not leave the mirror's object graph: no packets, no
     * sounds, no particles, no game events, no block-collision callbacks, no
     * client-singleton mutation. Each matched {@code invoke*} is rewritten to
     * "pop args/receiver, push type-default" — net stack effect preserved, so
     * existing StackMapTables stay valid.
     *
     * <p>Each rule is {@code {ownerRegex, nameRegex}} ({@code ownerRegex} may
     * be {@code null} = any owner), matched against the callee's internal
     * owner name and method name. Chosen to be unambiguous *effects* that
     * never feed {@code Entity.pos} (collision/blockstate <em>reads</em> —
     * {@code getBlockState}, {@code getCollisions}, {@code raycast}, … — match
     * none of these and pass through). Tuned via the in-client loop; every
     * neutralized site is audited in {@code simv2-mirror/neutralized.txt}.
     */
    public static final String[][] MIRROR_SINKS = {
        // Network: ClientPlay/Common network handler sends.
        {".*[Nn]etworkHandler", "send.*"},
        {null, "sendAbilitiesUpdate"},
        // Sound (leaf sinks; higher-level playStepSound/playSwimSound funnel
        // here and are themselves child-first bodies, so the leaf is caught).
        {null, "playSound.*"},
        {null, "playSoundFromEntity"},
        // Particles.
        {null, "addParticle.*"},
        {null, "addEmitter"},
        {null, "addBlockBreakParticles"},
        {null, "spawnSprintingParticles"},
        // Game / world events.
        {null, "emitGameEvent"},
        {null, "syncWorldEvent"},
        {null, "playLevelEvent"},
        // Block-collision callbacks reached from Entity.tickBlockCollisions /
        // move / fall — these mutate the real world (trample, tripwire, sculk).
        {null, "onEntityCollision"},
        {null, "onSteppedOn"},
        {null, "onLandedUpon"},
        {null, "onEntityLand"},
        // Client singleton mutators reached via the shared MinecraftClient.
        {".*TutorialManager", "onMovement"},
        {".*MinecraftClient", "setScreen"},
        // Cross-entity state writes. The prediction clone is seeded to the
        // real player's position, so it is co-located with the real player,
        // which is in the shared world's entity list. tickCramming() queries
        // those entities and pushAwayFrom() calls addVelocity() on the *other*
        // entity — i.e. the clone would shove the real player every predicted
        // tick. Gating these stops the clone perturbing anything but itself.
        {null, "tickCramming"},
        {null, "pushAwayFrom"},
    };
}
