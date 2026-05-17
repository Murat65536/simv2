package murat.simv2.analysis;

/**
 * Static configuration for the sink-gate analysis.
 *
 * <p>The analysis builds a call graph rooted at {@link #ENTRY_METHOD} and, from
 * it, enumerates every reachable side-effecting call-site (the
 * {@link #MIRROR_SINKS} denylist). {@link SinkMixinEmitter} turns that set into
 * the Mixins that suppress those effects while a movement prediction runs, so
 * the gate set re-derives mechanically for whatever Minecraft jar the analysis
 * is pointed at.
 */
public final class AnalysisConfig {

    private AnalysisConfig() {
    }

    public static final String TARGET_PACKAGE_INTERNAL_L = "Lnet/minecraft/";

    /**
     * Entry point method (class, method, descriptor) for the call graph.
     * <p>{@code tickMovement} (not {@code tick}) keeps the call graph scoped to
     * the movement subsystem — input sampling, physics, collision, the
     * {@code Entity.move} chain — without dragging rendering, sound, GUI, and
     * network-send into it.
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

    /**
     * WALA analysis-scope exclusions. {@code tickMovement} cannot reach
     * rendering/sound/GUI/data, so excluding those packages keeps the call
     * graph scoped to movement and tractable.
     */
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

    /**
     * Side-effecting callees that must be suppressed while a movement
     * prediction runs. {@link WalaPipelineRunner} reports every reachable
     * call-site whose callee matches one of these rules; the generated gate
     * Mixins skip those calls when {@code Prediction.ACTIVE}.
     *
     * <p>Each rule is {@code {ownerRegex, nameRegex}} ({@code ownerRegex} may
     * be {@code null} = any owner), matched against the callee's internal
     * owner name and method name. They are unambiguous <em>effects</em>;
     * movement <em>reads</em> ({@code getBlockState}, {@code getCollisions},
     * {@code raycast}, …) match none of these and pass through.
     */
    public static final String[][] MIRROR_SINKS = {
        // Network: ClientPlay/Common network handler sends.
        {".*[Nn]etworkHandler", "send.*"},
        {null, "sendAbilitiesUpdate"},
        // Sound (leaf sinks; the higher-level playStepSound/playSwimSound
        // funnel here, so catching the leaf catches them too).
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
