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
     * <p>{@code tick()} (the full entity tick), not {@code tickMovement()}:
     * elytra ({@code LivingEntity.tickFallFlying}), water/swim state
     * ({@code Entity.baseTick}/{@code updateSwimming}) and other movement
     * modes live in {@code tick()} and its callees, not in
     * {@code tickMovement()} alone. The broader reachable set means more
     * side-effecting call-sites — that is the point of generating the gates
     * from the analysis: the suppression set scales with the entry.
     * Rendering/sound/GUI are still pruned by {@link #WALA_EXCLUSIONS}.
     */
    public static final EntryMethod ENTRY_METHOD = new EntryMethod(
        "Lnet/minecraft/client/network/ClientPlayerEntity",
        "tick",
        "()V"
    );

    public record EntryMethod(String classInternal, String name, String descriptor) {
        public String selector() {
            return name + descriptor;
        }
    }

    /**
     * WALA analysis-scope exclusions. Rendering/GUI/data/server cannot
     * affect the real player and are excluded for tractability. Sound and
     * particle are deliberately kept in scope (their effect leaves are
     * callee-side gate targets); they bottom out quickly into still-excluded
     * LWJGL/concurrency, so the call graph stays bounded.
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
        // sound + particle are intentionally NOT excluded: they must stay
        // in scope so their effect leaves (SoundManager.play/playNextTick,
        // ParticleManager.add*) resolve to concrete owners and become
        // callee-side gate targets (closes the deferred-enqueue hole).
        "net\\/minecraft\\/client\\/texture\\/.*",
        "net\\/minecraft\\/client\\/font\\/.*",
        "net\\/minecraft\\/client\\/model\\/.*",
        "net\\/minecraft\\/data\\/.*",
        "net\\/minecraft\\/datafixer\\/.*",
        "net\\/minecraft\\/server\\/.*"
    };

    /**
     * The prediction clone's <em>sharing boundary</em>: the only objects it
     * does not exclusively own. {@link SinkEffectAnalysis} treats a value of
     * one of these types as <em>escaping</em> and derives, from the call
     * graph, every reachable call that mutates / performs I/O on such a value
     * — that derived set is what the gate Mixins suppress. This ~5-type policy
     * replaces the per-method judgment; it is intrinsic to how the clone is
     * built and changes far less across versions than method names.
     *
     * <p>{@code WORLD}/{@code MINECRAFT_CLIENT}/{@code NET_HANDLER}/
     * {@code CONNECTION} are <em>always</em> the shared real instances (even
     * {@code this.getWorld()} is the shared world). {@code ENTITY} is escaping
     * only when it is not the entry {@code this} (it may be the real player).
     */
    public static final String ESCAPE_ROOT_WORLD = "Lnet/minecraft/world/World";
    public static final String ESCAPE_ROOT_MINECRAFT_CLIENT =
        "Lnet/minecraft/client/MinecraftClient";
    public static final String ESCAPE_ROOT_NET_HANDLER =
        "Lnet/minecraft/client/network/ClientCommonNetworkHandler";
    public static final String ESCAPE_ROOT_CONNECTION =
        "Lnet/minecraft/network/ClientConnection";
    public static final String ESCAPE_ROOT_ENTITY = "Lnet/minecraft/entity/Entity";

    /**
     * Terminal-effect packages: a call whose declared owner is here is an
     * I/O sink we cannot (and need not) see into — they are excluded from the
     * analysis scope, so the last in-scope frame calling into them is the
     * effect boundary.
     */
    public static final String[] EFFECT_PACKAGES = {
        "net/minecraft/client/sound/",
        "net/minecraft/client/particle/",
        "net/minecraft/client/render/",
    };

    /**
     * <b>Optional manual supplement</b> to the {@link SinkEffectAnalysis}-
     * derived sink set, unioned with it. The intraprocedural escape analysis
     * cannot see effects that flow through several frames onto a non-{@code
     * this} entity (e.g. {@code pushAwayFrom}→{@code addVelocity} on the real
     * player); those stay listed here until the analysis (or a dynamic check)
     * subsumes them. {@code WalaPipelineRunner} emits a {@code DERIVED}/
     * {@code CURATED} diff so this residue is explicit and shrinkable.
     *
     * <p>Each rule is {@code {ownerRegex, nameRegex}} ({@code ownerRegex} may
     * be {@code null} = any owner), matched against the callee's internal
     * owner and method name.
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
        // World / entity creation, drops and block writes. With the full
        // tick() entry the clone can reach death / void / fire paths; since it
        // shares the real ClientWorld, a clone that "died" mid-prediction
        // would otherwise spawn item entities / write blocks into the *real*
        // world. None of these have any role in projecting held-key movement,
        // so gating every reachable one keeps zero-impact intact.
        {null, "spawnEntity"},
        {null, "dropStack"},
        {null, "dropItem"},
        {null, "dropInventory"},
        {null, "setBlockState"},
        {null, "breakBlock"},
        {null, "removeBlock"},
    };
}
