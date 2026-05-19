package murat.simv2.analysis;

import com.ibm.wala.classLoader.CallSiteReference;
import com.ibm.wala.classLoader.IClass;
import com.ibm.wala.classLoader.IMethod;
import com.ibm.wala.ipa.callgraph.CGNode;
import com.ibm.wala.ipa.callgraph.CallGraph;
import com.ibm.wala.ipa.cha.IClassHierarchy;
import com.ibm.wala.ssa.IR;
import com.ibm.wala.ssa.SSAAbstractInvokeInstruction;
import com.ibm.wala.ssa.SSAInstruction;
import com.ibm.wala.ssa.SSAPutInstruction;
import com.ibm.wala.types.ClassLoaderReference;
import com.ibm.wala.types.MethodReference;
import com.ibm.wala.types.Selector;
import com.ibm.wala.types.TypeReference;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Derives which reachable callees are <em>escaping effects</em> — calls that,
 * run on the prediction clone, would mutate or perform I/O on an object the
 * clone <b>shares with the real game</b>. This replaces the hand-curated
 * "what is a sink" judgment with a forward, summary-based analysis over the
 * call graph WALA already built (no slice, no points-to, no IFDS).
 *
 * <p>Policy input = the clone's sharing boundary ({@link AnalysisConfig#ESCAPE_ROOTS}):
 * the real {@code World}/{@code MinecraftClient}/network-handler/connection are
 * always the shared instances (even {@code this.getWorld()} returns the shared
 * world), and any {@code Entity} that is not the entry {@code this} may be the
 * real player. A value of such a type is <em>escaping</em>.
 *
 * <p>A method is <em>effectful-escaping</em> if it directly mutates an escaping
 * object ({@code putfield} on an escape-root-declared field — including its own
 * {@code this} for the shared singletons — or {@code putstatic}, or an invoke
 * into a terminal effect package: sound/particle/render), or it invokes an
 * effectful method <em>through an escaping receiver</em>. The last clause's
 * {@code this}-Entity carve-out is what stops the clone's own movement
 * ({@code this.move}/{@code this.travel}, all {@code this}-owned) from being
 * marked effectful — only its genuinely-escaping sub-calls
 * ({@code this.playSound}→{@code world.playSound}, …) are.
 *
 * <p>Honest limitation: a purely intraprocedural escape test cannot see that a
 * callee mutates a non-{@code this} {@code Entity} passed down through several
 * frames (e.g. {@code pushAwayFrom}→{@code addVelocity} on the real player) —
 * those remain in the curated {@link AnalysisConfig#MIRROR_SINKS} supplement,
 * and {@code WalaPipelineRunner} emits a {@code DERIVED}/{@code CURATED} diff so
 * the boundary is explicit.
 */
final class SinkEffectAnalysis {

    private final IClassHierarchy cha;
    private final IClass world;
    private final IClass minecraftClient;
    private final IClass netHandler;
    private final IClass connection;
    private final IClass entity;

    /** Methods proven to perform an escaping effect when invoked. */
    private final Set<IMethod> effectful = new HashSet<>();

    /** {@code name+descriptor} → declaring classes of effectful methods, for
     *  virtual-dispatch-aware callee resolution (a call declares the abstract
     *  {@code World.playSound}; the effectful one is {@code ClientWorld}'s
     *  override). */
    private final java.util.Map<String, List<IClass>> effectfulBySelector =
        new java.util.HashMap<>();

    /** Methods whose <em>own</em> body performs the escaping effect via an
     *  escaping <em>put</em> (putstatic / write into a shared-singleton or
     *  non-{@code this}-entity field) — the leaves we gate callee-side. A
     *  method that merely <em>invokes</em> a terminal sound/particle/render
     *  sink is deliberately NOT here: that sink is gated independently at its
     *  own call-site (the {@code inEffectPackage(calleeOwner)} branch of
     *  {@link #gateOwners}), so gating the caller too would needlessly
     *  HEAD-cancel a transitive parent and freeze clone-owned state evolution
     *  (e.g. {@code ClientPlayerEntity.updateWaterSubmersionState()Z}, which
     *  computes clone buoyancy and merely also plays an enter/exit sound). */
    private final Set<IMethod> directEffect = new HashSet<>();

    /** {@code name+descriptor} → declaring classes of {@link #directEffect}
     *  methods (concrete: they have IR), for dispatch-aware owner mapping. */
    private final java.util.Map<String, List<IClass>> directBySelector =
        new java.util.HashMap<>();

    /** {@code name+descriptor} → declaring classes of <em>every</em> CG-reached
     *  method that has a body (concrete: it has IR). Used by {@link
     *  #gateOwners} to map a call's declared owner (which may be an abstract
     *  base — {@code World.playSound} — or a subtype that merely inherits the
     *  body — {@code ClientPlayNetworkHandler.sendPacket} whose body is
     *  {@code ClientCommonNetworkHandler.sendPacket}) to the concrete class
     *  whose body actually runs, so the generated {@code @Mixin(targets=…)}
     *  binds. CG-reached (not all-CHA), so it stays precise and version-true. */
    private final java.util.Map<String, List<IClass>> reachableBySelector =
        new java.util.HashMap<>();

    SinkEffectAnalysis(IClassHierarchy cha, CallGraph cg) {
        this.cha = cha;
        this.world = lookup(AnalysisConfig.ESCAPE_ROOT_WORLD);
        this.minecraftClient = lookup(AnalysisConfig.ESCAPE_ROOT_MINECRAFT_CLIENT);
        this.netHandler = lookup(AnalysisConfig.ESCAPE_ROOT_NET_HANDLER);
        this.connection = lookup(AnalysisConfig.ESCAPE_ROOT_CONNECTION);
        this.entity = lookup(AnalysisConfig.ESCAPE_ROOT_ENTITY);
        compute(cg);
    }

    /**
     * True if a call to {@code (owner,name,desc)} is an escaping effect:
     * its resolved target is effectful-escaping, or its owner is an
     * unresolvable shared-singleton / terminal-effect-package method
     * (abstract {@code World}/{@code MinecraftClient} API, sound/particle).
     */
    boolean isEffectfulCallee(String ownerInternal, String name, String desc) {
        if (inEffectPackage(ownerInternal)) {
            return true;
        }
        IClass owner = lookup("L" + ownerInternal);
        // Virtual-dispatch-aware: the call may declare an abstract/base method
        // (e.g. World.playSound) whose effectful implementation lives in a
        // subtype (ClientWorld.playSound). Match any effectful method with the
        // same selector whose declaring class is related to the call owner.
        List<IClass> impls = effectfulBySelector.get(name + desc);
        if (impls != null && owner != null) {
            for (IClass dc : impls) {
                if (cha.isAssignableFrom(owner, dc)
                    || cha.isAssignableFrom(dc, owner)) {
                    return true;
                }
            }
        }
        IMethod m = resolve(ownerInternal, name, desc);
        if (m != null && effectful.contains(m)) {
            return true;
        }
        // Unresolvable (abstract / excluded body): a call onto a shared
        // singleton type is an effect we cannot rule out.
        return m == null && owner != null && isNonEntityEscapeRoot(owner);
    }

    // --- fixpoint -------------------------------------------------------

    private record Edge(CGNode caller, CGNode callee, boolean escapingReceiver) {
    }

    private void compute(CallGraph cg) {
        List<Edge> edges = new ArrayList<>();
        for (CGNode node : cg) {
            IR ir = node.getIR();
            if (ir == null) {
                continue;
            }
            IMethod cm = node.getMethod();
            reachableBySelector
                .computeIfAbsent(
                    cm.getName().toString() + cm.getDescriptor().toString(),
                    k -> new ArrayList<>())
                .add(cm.getDeclaringClass());
            boolean callerStatic = cm.isStatic();
            if (directlyEffectful(ir, callerStatic, cm.isClinit())) {
                effectful.add(node.getMethod());
                directEffect.add(node.getMethod());
            }
            for (var it = node.iterateCallSites(); it.hasNext(); ) {
                CallSiteReference site = it.next();
                SSAAbstractInvokeInstruction[] calls = ir.getCalls(site);
                if (calls == null || calls.length == 0) {
                    continue;
                }
                boolean escRecv = escapingReceiver(calls[0], site, callerStatic);
                for (CGNode tgt : cg.getPossibleTargets(node, site)) {
                    edges.add(new Edge(node, tgt, escRecv));
                }
            }
        }
        // Monotone propagation: a method is effectful if it invokes an
        // effectful method through an escaping receiver. Iterate to fixpoint.
        boolean changed = true;
        while (changed) {
            changed = false;
            for (Edge e : edges) {
                if (e.escapingReceiver()
                    && effectful.contains(e.callee().getMethod())
                    && effectful.add(e.caller().getMethod())) {
                    changed = true;
                }
            }
        }
        for (IMethod m : effectful) {
            effectfulBySelector
                .computeIfAbsent(
                    m.getName().toString() + m.getDescriptor().toString(),
                    k -> new ArrayList<>())
                .add(m.getDeclaringClass());
        }
        for (IMethod m : directEffect) {
            directBySelector
                .computeIfAbsent(
                    m.getName().toString() + m.getDescriptor().toString(),
                    k -> new ArrayList<>())
                .add(m.getDeclaringClass());
        }
    }

    // --- callee-side gate-target resolution -----------------------------

    /**
     * True iff {@code (owner,name,desc)} resolves to a {@code static} method.
     * Mixin requires a callback's static-ness to match its target's, so the
     * emitter needs this to generate a {@code static} handler for a static
     * effect leaf (e.g. a static factory that performs I/O); a mismatch fails
     * mixin APPLY. Resolved against the gate owner (static-ness is invariant
     * under overriding, so the concrete owner agrees with the declarer).
     */
    boolean isStaticMethod(String ownerInternal, String name, String desc) {
        IMethod m = resolve(ownerInternal, name, desc);
        return m != null && m.isStatic();
    }

    /**
     * True iff the callee resolves to a compiler-generated synthetic or
     * bridge method. These are never a movement effect (e.g. an enum's
     * synthetic {@code values()}/{@code $values()} accessor —
     * {@code SoundInstance$AttenuationType.method_36927}); gating one made
     * it return {@code null} on the prediction thread and NPE'd the clone
     * tick, which {@code MovementPredictor}'s blanket catch turned into a
     * silent self-disable. Excluded from the gate set entirely.
     */
    boolean isSyntheticOrBridge(String ownerInternal, String name, String desc) {
        IMethod m = resolve(ownerInternal, name, desc);
        return m != null && (m.isSynthetic() || m.isBridge());
    }

    /** Effect-package owner (terminal sound/particle/render leaf). */
    boolean inEffectPackagePublic(String ownerInternal) {
        return inEffectPackage(ownerInternal);
    }

    /**
     * True iff {@code (owner,name,desc)} resolves to a method whose own body
     * performs the escaping effect — an effect-package leaf, or a
     * {@link #directEffect} method (dispatch-aware via {@link
     * #directBySelector}). Transitive-only escaping parents are excluded.
     */
    boolean isDirectlyEffectful(String ownerInternal, String name, String desc) {
        if (inEffectPackage(ownerInternal)) {
            return true;
        }
        IClass owner = lookup("L" + ownerInternal);
        List<IClass> impls = directBySelector.get(name + desc);
        if (impls != null && owner != null) {
            for (IClass dc : impls) {
                if (cha.isAssignableFrom(owner, dc)
                    || cha.isAssignableFrom(dc, owner)) {
                    return true;
                }
            }
        }
        IMethod m = resolve(ownerInternal, name, desc);
        return m != null && directEffect.contains(m);
    }

    /**
     * The concrete internal owner name(s) to gate callee-side for a call to
     * {@code (calleeOwner,name,desc)}, or empty if it must not be gated
     * (a transitive-only parent that is not curated). Effect-package leaves
     * gate by their own owner; directly-effectful / curated leaves resolve to
     * the concrete declaring class(es) (dispatch-aware), falling back to the
     * bytecode owner.
     */
    List<String> gateOwners(
        String calleeOwner, String name, String desc, boolean curated) {
        java.util.LinkedHashSet<String> r = new java.util.LinkedHashSet<>();
        if (inEffectPackage(calleeOwner)) {
            // Terminal sound/particle/render leaf. Emit it only if a mixin
            // can bind to it; an interface effect owner (e.g. the
            // SoundInstance data interface — not an I/O frame; the real leaf
            // is the concrete SoundManager.play, gated elsewhere) would crash
            // mixin PREPARE with a target-type mismatch, so resolve to its
            // bindable concrete impl(s) instead, or nothing.
            IClass eo = lookup("L" + calleeOwner);
            if (eo != null && bindable(eo)) {
                r.add(calleeOwner);
            } else {
                chaBodyOwners(calleeOwner, name, desc, r);
            }
            return new ArrayList<>(r);
        }
        boolean direct = isDirectlyEffectful(calleeOwner, name, desc);
        if (!direct && !curated) {
            return List.of(); // transitive-only escaping parent — do not gate
        }
        IClass owner = lookup("L" + calleeOwner);
        addRelated(r, (direct ? directBySelector : effectfulBySelector)
            .get(name + desc), owner);
        if (r.isEmpty()) {
            // CG-derived maps are empty for an escaping leaf whose receiver
            // has no in-scope allocation (the shared World / network handler:
            // ClientWorld.playSound / ClientCommonNetworkHandler.sendPacket
            // are never CG nodes — precisely why they escape). Resolve the
            // class whose body actually runs by CHA instead (deterministic,
            // points-to-free, version-portable) so @Mixin(targets=…) binds.
            addRelated(r, reachableBySelector.get(name + desc), owner);
        }
        if (r.isEmpty()) {
            chaBodyOwners(calleeOwner, name, desc, r);
        }
        // NO last-resort "emit the declared bytecode owner": that emitted a
        // @Mixin gate against an abstract / interface-declared owner (e.g.
        // World.spawnEntity / .syncWorldEvent / the abstract playSound
        // overload — body only in the excluded ServerWorld) whose method the
        // mixin remapper cannot resolve on that target ("Cannot remap … does
        // not exist in net/minecraft/world/World"); under fail-loud require
        // that is a launch crash, and under the old require=0 it silently
        // no-op'd — the original leak. chaBodyOwners' first branch already
        // emits owners that have a concrete body there (World.playSoundClient
        // / .addParticleClient / the concrete playSound overloads ARE
        // concrete in World), and the subtype sweep maps abstract leaves to
        // their concrete client declarer (ClientWorld). A leaf that resolves
        // to neither is server-only — the client clone never runs it — so
        // emitting nothing is correct, not a coverage gap.
        return new ArrayList<>(r);
    }

    /**
     * CHA-resolves the <em>bindable</em> class(es) whose body runs for a call
     * to {@code (calleeOwner,name,desc)}. A class is bindable only if a
     * {@code @Mixin(targets=…)/@Inject} can attach to it: it must be concrete
     * (Mixin cannot inject a body-less method), non-interface, and within the
     * prediction's execution scope (not in {@link AnalysisConfig#WALA_EXCLUSIONS}
     * — the clone runs client+common code; {@code net/minecraft/server/…} is
     * out of scope by that same policy and thread-scoped to a no-op anyway).
     *
     * <p>If the declared owner resolves to a bindable concrete body, emit its
     * real declarer (an inherited body maps up:
     * {@code ClientPlayNetworkHandler.sendPacket} →
     * {@code ClientCommonNetworkHandler}). Otherwise the call is through an
     * abstract base / interface API ({@code World.playSound},
     * {@code ModifiableWorld.spawnEntity}); emit every bindable concrete
     * subtype's declarer ({@code ClientWorld}; {@code ServerWorld} is dropped
     * as excluded). Bounded — only the few abstract shared-singleton APIs
     * reach the enumeration.
     */
    private void chaBodyOwners(
        String calleeOwner, String name, String desc, java.util.Set<String> r) {
        IMethod rm = resolve(calleeOwner, name, desc);
        if (rm != null && !rm.isAbstract() && bindable(rm.getDeclaringClass())) {
            r.add(stripL(rm.getDeclaringClass().getName().toString()));
            return;
        }
        TypeReference ownerRef = TypeReference.findOrCreate(
            ClassLoaderReference.Application, "L" + calleeOwner);
        if (cha.lookupClass(ownerRef) == null) {
            return;
        }
        Selector sel = Selector.make(name + desc);
        for (IClass sc : cha.computeSubClasses(ownerRef)) {
            if (sc.isInterface() || !bindable(sc)) {
                continue;
            }
            // Find the class that *declares* a concrete body for this
            // selector — NOT cha.resolveMethod(sc, sel), which returns the
            // method sc dispatches to and so reports the inherited *abstract*
            // base (World) for the very subtype whose override is the real
            // leaf (ClientWorld.addParticleClient / .playSoundClient). An
            // abstract class is still a valid @Mixin target when it declares
            // a concrete body, so sc itself is not filtered on abstractness.
            for (IMethod dm : sc.getDeclaredMethods()) {
                if (dm.getSelector().equals(sel)
                    && !dm.isAbstract()
                    && bindable(dm.getDeclaringClass())) {
                    r.add(stripL(dm.getDeclaringClass().getName().toString()));
                }
            }
        }
    }

    /** Compiled {@link AnalysisConfig#WALA_EXCLUSIONS} (out-of-scope owners). */
    private static final java.util.regex.Pattern[] EXCLUDED =
        java.util.Arrays.stream(AnalysisConfig.WALA_EXCLUSIONS)
            .map(java.util.regex.Pattern::compile)
            .toArray(java.util.regex.Pattern[]::new);

    /**
     * True iff a {@code @Mixin(targets=c)/@Inject} can bind to {@code c}: it
     * exists, is a class (not an interface — injecting interface methods is
     * not supported here), and is in the prediction's execution scope (not in
     * {@link AnalysisConfig#WALA_EXCLUSIONS}). An <em>abstract</em> class is
     * still bindable: Mixin injects into a <em>concrete method body</em>, which
     * an abstract class may declare ({@code World.setBlockState} is a concrete
     * body in abstract {@code World}). The method-has-a-body requirement is
     * enforced separately at the call sites via {@code !IMethod.isAbstract()}.
     */
    private boolean bindable(IClass c) {
        if (c == null || c.isInterface()) {
            return false;
        }
        String slash = stripL(c.getName().toString());
        for (java.util.regex.Pattern p : EXCLUDED) {
            if (p.matcher(slash).matches()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Adds each class in {@code impls} whose type is in {@code owner}'s
     * hierarchy (sub- or super-type, either direction — the body may live on
     * an inherited super or a concrete override) to {@code r}, as an internal
     * slash name. {@code owner == null} (unresolvable) accepts all.
     */
    private void addRelated(
        java.util.Set<String> r, List<IClass> impls, IClass owner) {
        if (impls == null) {
            return;
        }
        for (IClass dc : impls) {
            if (owner == null
                || cha.isAssignableFrom(owner, dc)
                || cha.isAssignableFrom(dc, owner)) {
                r.add(stripL(dc.getName().toString()));
            }
        }
    }

    /**
     * True iff {@code ir}'s own body performs an escaping <em>put</em>. An
     * invoke into a terminal effect package is intentionally <em>not</em>
     * counted: that callee is the leaf and is gated at its own call-site by
     * {@link #gateOwners}'s {@code inEffectPackage(calleeOwner)} branch.
     * Counting the caller too would mark every in-scope caller of a
     * sound/particle/render method as a "direct" leaf and HEAD-cancel those
     * transitive parents, freezing clone-owned state (the
     * {@code updateWaterSubmersionState} buoyancy regression).
     */
    private boolean directlyEffectful(
        IR ir, boolean callerStatic, boolean inClinit) {
        for (SSAInstruction s : ir.getInstructions()) {
            if (s instanceof SSAPutInstruction put
                && escapingPut(put, callerStatic, inClinit)) {
                return true;
            }
        }
        return false;
    }

    private boolean escapingPut(
        SSAPutInstruction put, boolean callerStatic, boolean inClinit) {
        if (put.isStatic()) {
            // Class initialization is not a movement effect: a {@code
            // <clinit>}'s putstatic (including the compiler-synthetic enum
            // {@code $VALUES} array) runs once at class load, never on the
            // prediction thread. Counting it made enum/value-holder methods
            // (e.g. SoundInstance$AttenuationType's synthetic values()
            // accessor) "effectful", which then HEAD-cancelled to null and
            // NPE'd the clone tick.
            if (inClinit) {
                return false;
            }
            return true; // genuine global-state write
        }
        IClass declared = lookup(
            put.getDeclaredField().getDeclaringClass().getName().toString());
        if (declared == null) {
            return false;
        }
        if (isNonEntityEscapeRoot(declared)) {
            return true; // the clone shares these even via their own `this`
        }
        if (entity != null && cha.isAssignableFrom(entity, declared)) {
            // another entity's field — escaping unless it is the clone's
            // own `this` (value number 1 in an instance method).
            return callerStatic || put.getRef() != 1;
        }
        return false;
    }

    private boolean escapingReceiver(
        SSAAbstractInvokeInstruction inv, CallSiteReference site, boolean callerStatic) {
        IClass owner = lookup(
            inv.getDeclaredTarget().getDeclaringClass().getName().toString());
        if (owner == null) {
            return false;
        }
        if (isNonEntityEscapeRoot(owner)) {
            return true; // shared singleton receiver
        }
        if (entity != null && cha.isAssignableFrom(entity, owner)) {
            if (site.isStatic()) {
                return false;
            }
            // non-`this` Entity receiver may be the real player.
            return callerStatic || inv.getReceiver() != 1;
        }
        return false;
    }

    // --- type helpers ---------------------------------------------------

    private boolean isNonEntityEscapeRoot(IClass c) {
        return assignable(world, c) || assignable(minecraftClient, c)
            || assignable(netHandler, c) || assignable(connection, c);
    }

    private boolean assignable(IClass root, IClass c) {
        return root != null && c != null && cha.isAssignableFrom(root, c);
    }

    private static boolean inEffectPackage(String ownerInternal) {
        for (String p : AnalysisConfig.EFFECT_PACKAGES) {
            if (ownerInternal.startsWith(p)) {
                return true;
            }
        }
        return false;
    }

    private IClass lookup(String typeName) {
        String l = typeName.startsWith("L") ? typeName : "L" + typeName;
        return cha.lookupClass(
            TypeReference.findOrCreate(ClassLoaderReference.Application, l));
    }

    private IMethod resolve(String ownerInternal, String name, String desc) {
        try {
            TypeReference owner = TypeReference.findOrCreate(
                ClassLoaderReference.Application, "L" + ownerInternal);
            return cha.resolveMethod(
                MethodReference.findOrCreate(owner, Selector.make(name + desc)));
        } catch (Throwable t) {
            return null;
        }
    }

    private static String stripL(String typeName) {
        String s = typeName.startsWith("L") ? typeName.substring(1) : typeName;
        return s.endsWith(";") ? s.substring(0, s.length() - 1) : s;
    }
}
