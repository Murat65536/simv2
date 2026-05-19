package murat.simv2.predict;

import murat.simv2.SimV2;
import net.minecraft.util.math.Vec3d;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

/**
 * Predicts the real player's future positions by ticking a clone of the
 * real player. The clone is <em>built once</em> with the real constructor
 * and reused: before each prediction every declared field is reset to its
 * pristine post-construction snapshot and the game's own
 * {@code Entity.copyFrom} re-seeds the live state, making it field-identical
 * to a fresh build without the per-tick allocation. A dimension/world swap
 * rebuilds it. Predictions are additionally decimated — while the held input
 * and look angle are unchanged the previous path is reused (translated to
 * the player's current position), so the heavy path runs only on real input
 * change or after a few ticks.
 *
 * <p>The clone is created via {@code ClientPlayerEntity}'s own public
 * constructor, so it is a fully valid, independent entity that owns its own
 * {@code DataTracker}/abilities/attributes/{@code limbAnimator} (the ctor
 * makes them). It is then seeded from the real player with the game's own
 * cross-entity state transfer, {@code Entity.copyFrom} (the method portal /
 * dimension travel uses: {@code writeNbt(real)} → {@code readNbt(clone)}).
 * That reads the real player (no mutation) and writes values into the clone's
 * <em>own</em> sub-objects, so the real player cannot be perturbed and the
 * predictor stays version-portable — the only Minecraft names it hardcodes are
 * {@code copyFrom}/{@code tickMovement}/{@code getPos} (the most stable in the
 * game) plus the {@code Input}/{@code Vec3d} types.
 *
 * <p>By default {@code copyFrom} is used only as a fallback: the clone is
 * seeded by a faster <em>targeted</em> copy of just the physics state — flat
 * value fields, the {@code DataTracker} (positionally), {@code
 * AttributeContainer.setFrom}, and the flat {@code PlayerAbilities}/{@code
 * HungerManager} — skipping the whole-player NBT round-trip (inventory,
 * components, …). It is identified by type, not field name, so it stays
 * version-portable. The deferred 1-tick fidelity probe validates it every
 * recompute and, on any divergence, the predictor <em>auto-reverts to {@code
 * copyFrom} for the session</em> (also forceable with {@code
 * -Dsimv2.predict.nbtSeed=true}): the fast path can never silently corrupt
 * the prediction.
 *
 * <p>The one sub-object the ctor leaves null and {@code copyFrom} does not
 * restore (client-only transient, not in NBT) is {@code input}; it is copied
 * field-by-field from a single small {@code net.minecraft.*} object (no JDK
 * wall). The clone also inherits the ctor default for {@code autoJumpEnabled}
 * (a transient client boolean {@code copyFrom} skips, normally re-synced only
 * by {@code sendMovementPackets} after the tick); it is seeded from the live
 * player so the prediction honours the real auto-jump setting from tick 1.
 * The clone shares the real {@code ClientWorld}/network handler; every
 * side effect that would reach them is cancelled at the escape-root boundary
 * (the {@code ClientWorld}/{@code World}/network/sound/{@code MinecraftClient}
 * write methods) by the hand-written {@code murat.simv2.mixin.boundary} Mixins,
 * each gated on {@link Prediction#isActive()} so only the predicting thread is
 * affected. Reads pass through untouched, so predicted physics stays exact.
 *
 * <p>Any resolution mismatch fails <em>loud at init</em> (disabled + logged)
 * — never silent corruption. Fully defensive: never throws into the client
 * tick loop.
 */
public final class MovementPredictor {

    public static final MovementPredictor INSTANCE = new MovementPredictor();

    /** Predicted ticks per frame (≈ {@value}/20 s of look-ahead). */
    private static final int HORIZON = 60;

    /**
     * Real-player NBT snapshot/diff backstop. Off by default: it serialised
     * the entire real player to NBT <em>twice</em> every client tick (before
     * and after the K-tick loop) on the render thread — the dominant
     * per-frame cost. Enable with {@code -Dsimv2.predict.debug=true} when
     * hunting a state leak; the boundary gates are the primary defense.
     */
    private static final boolean DEBUG =
        Boolean.getBoolean("simv2.predict.debug");

    private boolean initialized;
    private boolean enabled;
    private boolean warned;
    private int diag;

    private Object unsafe;
    private Method allocateInstance;

    private Class<?> playerClass;
    private Constructor<?> ctor;
    private List<Field> playerFields;     // net.minecraft-declared, for ctor-arg scan
    private Field inputField;

    private Method mCopyFrom;  // Entity.copyFrom(Entity) — the game's own transfer
    private Method mTick;      // the entry we run forward (full entity tick)
    private Method mGetPos;    // the result we read

    // Bound MethodHandles for the K-tick hot loop: tick()+getPos() run
    // HORIZON×/frame. Method.invoke does an access check + arg boxing per
    // call; an unreflected handle is ~direct-call fast and removes 120+
    // reflective dispatches per client tick.
    private MethodHandle hTick;
    private MethodHandle hGetPos;

    // Marks the clone "loaded" so ClientPlayerEntity.tick()'s
    // `if (this.isLoaded())` guard lets super.tick() (all movement) run every
    // horizon tick. setLoaded(boolean) preferred; field fallback otherwise.
    private Method mSetLoaded;
    private Field loadedField;   // boolean: the load gate
    private Field loadCountField; // int: tick-down counter (set to 0)

    // Client-only transient state copyFrom's NBT round-trip does not carry:
    // ClientPlayerEntity's ctor defaults autoJumpEnabled = true and only
    // sendMovementPackets() (which runs *after* the tick's movement) syncs
    // it to the real option. A fresh clone would therefore auto-jump on its
    // first predicted tick whatever the real setting. Seeded from the live
    // player each predict (resolved here once).
    private Field autoJumpField; // boolean: autoJumpEnabled

    // Best-effort real-player NBT snapshot — the version-robust catch-all that
    // detects any direct state impact the choke probes did not explain.
    private Method mWriteNbt;
    private Constructor<?> nbtCtor;

    // Per-ctor-arg resolution plan, built once on the first call: each slot
    // is ARG_CLIENT (use the passed client), ARG_FALSE (the constant false),
    // or the player Field to read live. Removes the O(params ×
    // playerFields) reflective scan from the per-tick path; still correct
    // across a dimension/world swap because the Fields are re-read from the
    // current realPlayer every tick.
    private Object[] argPlan;
    private static final Object ARG_CLIENT = new Object();
    private static final Object ARG_FALSE = new Object();

    // --- B: clone reuse ------------------------------------------------
    // The clone is built once and reused every predict instead of
    // reconstructed per client tick (the ctor allocates a whole
    // ClientPlayerEntity — DataTracker/attributes/abilities/inventory — and
    // 20 such throwaways/s was the dominant alloc + GC source). Correctness
    // is preserved by restoring every declared instance field to the
    // pristine post-ctor snapshot before each reuse, then running copyFrom:
    // field-for-field identical to a fresh build, minus the allocation.
    // The clone's collision world is fixed at construction, so a
    // dimension/world swap (realPlayer.getWorld() identity changes) forces
    // a rebuild.
    private Object reusableClone;
    private Object[] cloneSnapshot;     // pristine post-ctor field values
    private Method mGetWorld;           // Entity.getWorld() — swap guard

    // --- incremental "extend" prediction ------------------------------
    // predict() runs every client tick; held input changes slowly. A full
    // recompute ticks the clone HORIZON times. Instead, in the steady
    // regime, the clone is LEFT at its horizon-end state and the path is
    // kept as a HORIZON-deep FIFO ring of absolute predictions: each tick
    // we drop the now-past front, tick the retained clone ONCE more, and
    // append that new endpoint — 1 tick of work instead of HORIZON, with
    // no periodic forced recompute. The clone reads the real (shared)
    // world, so terrain ahead is handled correctly (more accurate than
    // translating a frozen shape). The fidelity probe validates the ring
    // front against reality every tick; a >FIDELITY_EPS miss (input the
    // guards didn't catch, accumulated drift, terrain change) forces a
    // re-anchoring recompute. Because the clone is ticked forward through
    // real physics (not a translated frozen shape) the extend is
    // phase-correct airborne and through turns too: the look angle is
    // synced onto the clone each extend, so jumping / falling / looking
    // around while moving all keep extending. Recompute only on a held-
    // input change, an external position jump, a probe miss, a world
    // swap, or a skipped frame.
    private static final double JUMP_SQ = 1.0;       // >1 block = external
    private static final float ANG_EPS = 0.5F;       // degrees (degrade path)
    private Vec3d[] ring;              // HORIZON absolute predictions, FIFO
    private int ringStart;            // index of the front (oldest) entry
    private boolean ringValid;        // clone+ring usable for an extend
    private boolean lastArmRecompute; // armed predictedNext: recompute vs extend
    private boolean driftLogged;      // one-shot incremental-drift notice
    private double[] lastInputSig;
    private float lastYaw;
    private float lastPitch;
    private int advances;             // extends since last recompute (diag)
    private Method mGetYaw;             // best-effort look-angle accessors
    private Method mGetPitch;
    private Method mSetYaw;             // sync clone look onto each extend
    private Method mSetPitch;
    private boolean angleSyncable;      // both get+set look accessors found

    // --- B verification: deferred 1-tick fidelity probe (DEBUG) -------
    // The one B failure mode with no other automated check: a reused
    // clone seeded unfaithfully (silently wrong path). Each recompute
    // ticks the clone exactly once; that position predicts where the real
    // player will be one client tick later. predict() runs once per
    // END_CLIENT_TICK, so the *next* call's actual real position is
    // exactly that — compared here. Divergence > FIDELITY_EPS with no
    // external position jump (teleport/knockback) between seed and sample
    // => the clone seed is incomplete. DEBUG-gated; zero cost otherwise.
    private static final double FIDELITY_EPS = 1.0E-3;  // 1 mm
    private Vec3d predictedNext;   // clone pos 1 tick out, from last recompute
    private Vec3d fidelityBase;    // seeded real pos that produced it
    private long fidelityTick;     // predictTick value that armed it
    private long predictTick;      // ++ once per predict() past the input gate

    // --- targeted seed (replaces copyFrom's whole-player NBT) ---------
    // Identified by stable TYPE simple-name, never field name. If any
    // required piece is unresolved, or -Dsimv2.predict.nbtSeed=true, or
    // the fidelity probe ever trips, fastSeed stays/goes off and the
    // pristine-restore + copyFrom fallback is used (correct, just slower).
    private static final boolean FORCE_NBT =
        Boolean.getBoolean("simv2.predict.nbtSeed");
    private boolean fastSeed;
    private volatile boolean fastSeedTripped;
    private boolean revertLogged;
    private Field dtField;          // the DataTracker-typed player field
    private Field dtEntriesField;   // DataTracker's Entry[] array
    private Method mEntryGet;       // DataTracker$Entry.get()
    private Method mEntrySet;       // DataTracker$Entry.set(T)
    private Field attrField;        // the AttributeContainer-typed field
    private Method mAttrSetFrom;    // AttributeContainer.setFrom(same)
    private Field abilField;        // PlayerAbilities-typed field (flat)
    private Field hungerField;      // HungerManager-typed field (flat)
    private Method mSetPosition;    // setPosition(DDD): re-derive the box

    private MovementPredictor() {
    }

    /**
     * Builds a fresh clone of {@code realPlayer}, seeds it via
     * {@code copyFrom}, steps it {@link #HORIZON} ticks, and returns the
     * predicted positions. Returns an empty list (and disables itself) on any
     * failure — never throws.
     *
     * @param client     the {@code MinecraftClient} (a constructor arg; opaque).
     * @param realPlayer the live {@code ClientPlayerEntity} (opaque here).
     */
    public List<Vec3d> predict(Object client, Object realPlayer) {
        if (client == null || realPlayer == null) {
            return List.of();
        }
        try {
            if (!initialized) {
                init(client, realPlayer);
            }
            if (!enabled) {
                return List.of();
            }

            Object input = inputField.get(realPlayer);
            if (input == null) {
                return List.of(); // not ready — ticking would share real input
            }

            Vec3d curPos = posOf(realPlayer);
            double[] sig = inputSignature(input);

            // Fidelity / drift probe — always runs (one Vec3d compare,
            // negligible) because it guards three things at once: the fast
            // seed (interlock), incremental extend (drift), and DEBUG
            // validation. predictTick counts client ticks past the input
            // gate; the previous frame armed predictedNext (the ring front
            // = its prediction for *this* tick) at fidelityTick. If this
            // is the very next client tick and the real player took no
            // external jump, its actual position now must equal that
            // prediction within FIDELITY_EPS. A miss forces a re-anchoring
            // recompute; if the missed prediction came from a recompute it
            // is a genuine seed-fidelity failure (revert seed to NBT for
            // the session), if from an extend it is incremental drift
            // (re-anchor only — not a seed bug).
            predictTick++;
            if (predictedNext != null && curPos != null
                && fidelityBase != null
                && predictTick == fidelityTick + 1
                && curPos.squaredDistanceTo(fidelityBase) < JUMP_SQ) {
                double d = curPos.distanceTo(predictedNext);
                if (d > FIDELITY_EPS) {
                    ringValid = false;          // re-anchor via recompute
                    if (lastArmRecompute) {
                        fastSeedTripped = true;
                        if (!revertLogged) {
                            revertLogged = true;
                            SimV2.LOGGER.warn(
                                "[simv2] fidelity probe tripped ({} blocks"
                                    + " > {}) — reverting clone seed to NBT"
                                    + " copyFrom for this session",
                                String.format("%.6f", d), FIDELITY_EPS);
                        }
                        LeakDetector.recordFidelity(d,
                            "actual " + fmt(curPos) + " vs predicted "
                                + fmt(predictedNext) + ", 1 tick from seed "
                                + fmt(fidelityBase));
                    } else if (!driftLogged) {
                        driftLogged = true;
                        SimV2.LOGGER.info(
                            "[simv2] incremental drift {} blocks > {} —"
                                + " re-anchoring (recompute)",
                            String.format("%.6f", d), FIDELITY_EPS);
                    }
                }
            }

            // A world swap invalidates the retained clone (its collision
            // world is ctor-bound) and the ring: rebuild from scratch.
            if (reusableClone != null && mGetWorld != null
                && worldOf(reusableClone) != worldOf(realPlayer)) {
                reusableClone = null;
                cloneSnapshot = null;
                ringValid = false;
            }

            // Incremental extend: advance the retained clone ONE tick and
            // rotate the ring instead of a full HORIZON recompute. The
            // clone is ticked through real physics so this is phase-
            // correct airborne and the live look angle is synced onto it,
            // so jumping / falling / turning while moving keep extending.
            // drift = (actual - predicted-for-now) is the probe-bounded
            // 1-tick error; adding it re-glues the path origin to the real
            // player without staleness. No clone is rebuilt or re-seeded —
            // only ticked once. Recompute only on a held-input change, an
            // external position jump, a probe miss, a swap, or a gap.
            if (ringValid && reusableClone != null && curPos != null
                && predictedNext != null
                && predictTick == fidelityTick + 1
                && sigEquals(sig, lastInputSig)
                && (angleSyncable || angleUnchanged(realPlayer))
                && curPos.squaredDistanceTo(predictedNext) < JUMP_SQ) {
                Vec3d drift = curPos.subtract(predictedNext);
                LeakDetector.beginWindow();
                Prediction.begin();
                try {
                    markLoaded(reusableClone);
                    Object ci = inputField.get(reusableClone);
                    if (ci != null) {
                        copyFields(input, ci); // replay current held input
                    }
                    if (autoJumpField != null) {
                        autoJumpField.setBoolean(reusableClone,
                            autoJumpField.getBoolean(realPlayer));
                    }
                    if (angleSyncable) {
                        // Sync the live look onto the clone so the extend
                        // walks the current direction (turn => path bends,
                        // not a recompute). Movement reads entity yaw.
                        mSetYaw.invoke(reusableClone,
                            ((Float) mGetYaw.invoke(realPlayer)).floatValue());
                        mSetPitch.invoke(reusableClone,
                            ((Float) mGetPitch.invoke(realPlayer))
                                .floatValue());
                    }
                    hTick.invoke(reusableClone);
                    Object p = hGetPos.invoke(reusableClone);
                    if (p instanceof Vec3d np) {
                        ring[ringStart] = np;          // oldest -> newest
                        ringStart = (ringStart + 1) % HORIZON;
                        predictedNext = ring[ringStart];
                        lastArmRecompute = false;
                        fidelityBase = curPos;
                        fidelityTick = predictTick;
                        advances++;
                        return ringList(drift);
                    }
                    ringValid = false; // bad tick -> recompute below
                } finally {
                    Prediction.end();
                }
            }

            // Gate the ENTIRE clone lifecycle, not just the K-tick loop.
            // ctor + copyFrom(readNbt(clone)) + markLoaded already run effect
            // leaves on the SHARED real world/network handler — equip sounds
            // (LivingEntity.onEquipStack), game events, attribute-sync
            // packets — when the clone is seeded. Before, these ran *before*
            // Prediction.begin(), so the gates saw isActive()==false and the
            // effects leaked into the real game every client tick (the
            // reported step/equip noises). begin() is thread-scoped, so
            // suppressing them here cannot touch the real game; clone-owned
            // state (position/motion/abilities written into the clone's own
            // fields by readNbt) does not pass through the gated leaves and
            // is unaffected.
            LeakDetector.beginWindow();
            Prediction.begin();
            try {
                long tA = System.nanoTime();
                Object clone = reusableClone;
                if (clone == null) {
                    clone = ctor.newInstance(ctorArgs(client, realPlayer));
                    // Reuse only when the swap guard is available;
                    // otherwise rebuild every tick (correct, just no win).
                    if (mGetWorld != null) {
                        cloneSnapshot = snapshotClone(clone);
                        reusableClone = clone;
                    }
                }
                long tCtor = System.nanoTime();

                // Seed the clone from the live player. Fast path: targeted
                // copy of just the physics state (flat fields + DataTracker
                // + attributes + abilities/hunger), no whole-player NBT.
                // Fallback (probe tripped / forced / unavailable): restore
                // the pristine snapshot then the game's own Entity.copyFrom
                // (writeNbt(real) -> readNbt(clone)). Reads real; no mutate.
                seed(clone, realPlayer);
                long tCopy = System.nanoTime();

                // Seed the transient client boolean copyFrom's NBT skips:
                // without this the fresh clone keeps the ctor default
                // autoJumpEnabled = true and auto-jumps on tick 1 into any
                // adjacent block regardless of the real option.
                if (autoJumpField != null) {
                    autoJumpField.setBoolean(
                        clone, autoJumpField.getBoolean(realPlayer));
                }

                // input is client-only transient (not in NBT) and ctor-null —
                // the one sub-object we must give the clone its own copy of.
                Object privInput = allocate(input.getClass());
                copyFields(input, privInput);
                inputField.set(clone, privInput);

                // Without this the clone's tick() skips super.tick() (all
                // movement) for the whole horizon — pure clone mutation.
                markLoaded(clone);

                String nbtBefore = DEBUG ? snapshotNbt(realPlayer) : null;

                Object startObj = hGetPos.invoke(clone);
                Vec3d start = startObj instanceof Vec3d sv ? sv : null;

                long tLoop0 = System.nanoTime();
                List<Vec3d> path = new ArrayList<>(HORIZON);
                for (int i = 0; i < HORIZON; i++) {
                    hTick.invoke(clone);
                    Object p = hGetPos.invoke(clone);
                    if (p instanceof Vec3d v) {
                        path.add(v);
                    } else {
                        break;
                    }
                }
                long tLoop1 = System.nanoTime();

                // Throttled diagnostic (~every 40 ticks ≈ 2 s): clone advance,
                // replayed input, and the per-phase cost breakdown (µs) so the
                // dominant cost is visible — ctor alloc (incl. now-cached
                // ctorArgs) vs. copyFrom NBT round-trip vs. the irreducible
                // 60-tick physics loop. Decides whether reuse/targeted-copy/
                // memoize is worth it.
                if (diag++ % 40 == 0 && start != null && !path.isEmpty()) {
                    Vec3d end = path.get(path.size() - 1);
                    SimV2.LOGGER.info(
                        "[simv2] predict: {} ticks, disp {} blocks (start {} ->"
                            + " end {}); input {}; cost ctor {}µs seed {}µs"
                            + " loop {}µs; {} extends since last recompute",
                        path.size(),
                        String.format("%.3f", end.distanceTo(start)),
                        fmt(start), fmt(end), dumpInput(privInput),
                        (tCtor - tA) / 1000, (tCopy - tCtor) / 1000,
                        (tLoop1 - tLoop0) / 1000, advances);
                }

                // Catch-all (DEBUG only): the real player must be
                // byte-identical across the prediction. Any difference is an
                // impact the gates missed. Off in normal play — this
                // serialised the whole player to NBT a second time per frame.
                if (DEBUG) {
                    String nbtAfter = snapshotNbt(realPlayer);
                    if (nbtBefore != null && nbtAfter != null
                        && !nbtBefore.equals(nbtAfter)) {
                        LeakDetector.recordPlayerStateChange(
                            "NBT len " + nbtBefore.length() + " -> "
                                + nbtAfter.length() + "; "
                                + firstDiff(nbtBefore, nbtAfter));
                    }
                }

                // Arm incremental + the probe from this fresh recompute.
                // The clone was seeded at the real player's current pos so
                // path positions are real-anchored (returned as-is). The
                // clone is LEFT at its horizon-end state: the next steady
                // frame extends it by one tick instead of recomputing.
                // Incremental is only enabled if a full horizon was
                // produced (a short path => recompute next, safe).
                int n = Math.min(path.size(), HORIZON);
                if (ring == null) {
                    ring = new Vec3d[HORIZON];
                }
                for (int i = 0; i < n; i++) {
                    ring[i] = path.get(i);
                }
                ringStart = 0;
                ringValid = (n == HORIZON);
                lastInputSig = sig;
                if (mGetYaw != null && mGetPitch != null) {
                    try {
                        lastYaw = (Float) mGetYaw.invoke(realPlayer);
                        lastPitch = (Float) mGetPitch.invoke(realPlayer);
                    } catch (Throwable ignored) {
                        // angle term simply weakened until next recompute
                    }
                }
                advances = 0;

                // path.get(0) is the clone after one tick = the prediction
                // of the real player's position next client tick. The next
                // predict() call (one END_CLIENT_TICK later) checks it; a
                // recompute arm means a miss is a seed-fidelity failure.
                if (start != null && !path.isEmpty()) {
                    predictedNext = path.get(0);
                    fidelityBase = start;
                    fidelityTick = predictTick;
                    lastArmRecompute = true;
                }
                return path;
            } finally {
                Prediction.end();
            }
        } catch (Throwable t) {
            enabled = false;
            if (!warned) {
                warned = true;
                SimV2.LOGGER.warn("[simv2] movement predictor disabled: {}", t, t);
            }
            return List.of();
        }
    }

    // --- one-time setup -------------------------------------------------

    private void init(Object client, Object realPlayer) throws Exception {
        initialized = true;
        Class<?> unsafeClass = Class.forName("sun.misc.Unsafe");
        Field theUnsafe = unsafeClass.getDeclaredField("theUnsafe");
        theUnsafe.setAccessible(true);
        unsafe = theUnsafe.get(null);
        allocateInstance = unsafeClass.getMethod("allocateInstance", Class.class);

        playerClass = realPlayer.getClass();
        playerFields = collectFields(playerClass);

        // Widest public constructor = the real ClientPlayerEntity ctor.
        Constructor<?> widest = null;
        for (Constructor<?> c : playerClass.getDeclaredConstructors()) {
            if (Modifier.isPublic(c.getModifiers())
                && (widest == null
                    || c.getParameterCount() > widest.getParameterCount())) {
                widest = c;
            }
        }
        if (widest == null) {
            disable("no public ClientPlayerEntity constructor");
            return;
        }
        widest.setAccessible(true);
        ctor = widest;

        // Entity.copyFrom(Entity): the public 1-arg method whose parameter a
        // ClientPlayerEntity is assignable to (no Entity class-name string).
        for (Method m : playerClass.getMethods()) {
            if (m.getName().equals("copyFrom")
                && m.getParameterCount() == 1
                && m.getParameterTypes()[0].isAssignableFrom(playerClass)) {
                mCopyFrom = m;
                break;
            }
        }
        if (mCopyFrom == null) {
            disable("Entity.copyFrom(Entity) not found");
            return;
        }
        mTick = playerClass.getMethod("tick");
        mGetPos = playerClass.getMethod("getPos");
        MethodHandles.Lookup lk = MethodHandles.lookup();
        hTick = lk.unreflect(mTick);
        hGetPos = lk.unreflect(mGetPos);

        // The clone must be "loaded" or ClientPlayerEntity.tick()'s
        // `if (this.isLoaded())` skips super.tick() (= all movement) for the
        // whole horizon. Prefer the public setLoaded(boolean); fall back to
        // the private boolean gate field (+ its int countdown) so a rename
        // does not silently freeze prediction.
        for (Method m : playerClass.getMethods()) {
            if (m.getName().equals("setLoaded")
                && m.getParameterCount() == 1
                && m.getParameterTypes()[0] == boolean.class) {
                mSetLoaded = m;
                break;
            }
        }
        if (mSetLoaded == null) {
            for (Field f : playerFields) {
                String n = f.getName().toLowerCase();
                if (loadedField == null && f.getType() == boolean.class
                    && n.contains("load")) {
                    f.setAccessible(true);
                    loadedField = f;
                } else if (loadCountField == null && f.getType() == int.class
                    && n.contains("load")) {
                    f.setAccessible(true);
                    loadCountField = f;
                }
            }
        }
        if (mSetLoaded == null && loadedField == null) {
            disable("cannot mark clone loaded (no setLoaded / load field)");
            return;
        }

        // The lone boolean field whose name carries "autojump" is
        // ClientPlayerEntity.autoJumpEnabled. Best-effort: if a remap hides
        // the name the clone still predicts, just with the ctor-default
        // auto-jump (logged, not fatal).
        for (Field f : playerFields) {
            if (f.getType() == boolean.class
                && f.getName().toLowerCase().contains("autojump")) {
                f.setAccessible(true);
                autoJumpField = f;
                break;
            }
        }
        if (autoJumpField == null) {
            SimV2.LOGGER.info(
                "[simv2] autoJumpEnabled field not found; clone uses ctor"
                    + " default (prediction may auto-jump)");
        }

        // Stable Entity accessors for reuse / incremental extend (same
        // stability class as getPos/copyFrom). Each best-effort: getWorld
        // absent -> no clone reuse (rebuild per tick, still correct).
        // get+setYaw/Pitch all present -> the look angle is synced onto
        // the clone each extend so turning keeps extending; otherwise it
        // degrades to recompute-on-look-change (still correct).
        mGetWorld = tryMethod(playerClass, "getWorld");
        mGetYaw = tryMethod(playerClass, "getYaw");
        mGetPitch = tryMethod(playerClass, "getPitch");
        mSetYaw = tryMethod1(playerClass, "setYaw", float.class);
        mSetPitch = tryMethod1(playerClass, "setPitch", float.class);
        angleSyncable = mGetYaw != null && mGetPitch != null
            && mSetYaw != null && mSetPitch != null;
        SimV2.LOGGER.info(
            "[simv2] incremental extend: {}",
            angleSyncable
                ? "look synced onto clone (turns/jumps/falls extend)"
                : "look gated (recompute on look-change > " + ANG_EPS
                    + "deg; airborne still extends)");

        // Targeted-seed wiring (best-effort, type-name based). Any gap =>
        // fastSeed disabled, copyFrom fallback used. Never fatal.
        try {
            dtField = fieldByTypeSimpleName("DataTracker");
            if (dtField != null) {
                for (Field f : dtField.getType().getDeclaredFields()) {
                    if (f.getType().isArray()
                        && !Modifier.isStatic(f.getModifiers())) {
                        f.setAccessible(true);
                        dtEntriesField = f;
                        break;
                    }
                }
                if (dtEntriesField != null) {
                    Class<?> entry =
                        dtEntriesField.getType().getComponentType();
                    mEntryGet = entry.getMethod("get");
                    for (Method m : entry.getMethods()) {
                        if (m.getName().equals("set")
                            && m.getParameterCount() == 1) {
                            mEntrySet = m;
                            break;
                        }
                    }
                }
            }
            attrField = fieldByTypeSimpleName("AttributeContainer");
            if (attrField != null) {
                Class<?> ac = attrField.getType();
                mAttrSetFrom = ac.getMethod("setFrom", ac);
            }
            abilField = fieldByTypeSimpleName("PlayerAbilities");
            hungerField = fieldByTypeSimpleName("HungerManager");
            mSetPosition = playerClass.getMethod(
                "setPosition", double.class, double.class, double.class);
        } catch (Throwable ignored) {
            // leave whatever resolved; the conjunction below gates it
        }
        fastSeed = !FORCE_NBT && dtField != null && dtEntriesField != null
            && mEntryGet != null && mEntrySet != null && attrField != null
            && mAttrSetFrom != null && abilField != null
            && hungerField != null && mSetPosition != null;
        SimV2.LOGGER.info(
            "[simv2] clone seed: {}",
            fastSeed ? "targeted (copyFrom = probe-guarded fallback)"
                : FORCE_NBT ? "NBT copyFrom (forced)"
                    : "NBT copyFrom (targeted unavailable)");
        if (mGetWorld == null) {
            SimV2.LOGGER.info(
                "[simv2] Entity.getWorld() not found; clone rebuilt each"
                    + " tick (no reuse, still correct)");
        }

        // Best-effort NBT snapshot wiring (writeNbt + NbtCompound are as
        // stable as copyFrom). If absent, the choke probes still detect.
        try {
            Class<?> nbt = Class.forName(
                "net.minecraft.nbt.NbtCompound", false, playerClass.getClassLoader());
            nbtCtor = nbt.getDeclaredConstructor();
            nbtCtor.setAccessible(true);
            for (Method m : playerClass.getMethods()) {
                if (m.getName().equals("writeNbt")
                    && m.getParameterCount() == 1
                    && m.getParameterTypes()[0].isAssignableFrom(nbt)) {
                    mWriteNbt = m;
                    break;
                }
            }
        } catch (Throwable ignored) {
            mWriteNbt = null;
        }

        inputField = null;
        Class<?> inputType = tryLoad("net.minecraft.client.input.Input");
        for (Field f : playerFields) {
            if (inputType != null && inputType.isAssignableFrom(f.getType())) {
                f.setAccessible(true);
                inputField = f;
                break;
            }
        }
        if (inputField == null) {
            disable("ClientPlayerEntity.input field not found");
            return;
        }

        // Validate ctor args resolve now (fail loud here, not mid-tick).
        ctorArgs(client, realPlayer);

        enabled = true;
        SimV2.LOGGER.info(
            "[simv2] movement predictor ready: ctor/{} args, horizon {}",
            ctor.getParameterCount(), HORIZON);
    }

    /**
     * Resolves the constructor arguments from the real player by type: the
     * passed {@code client}, the trailing booleans as {@code false}, and every
     * other reference arg by reading the player's own {@code net.minecraft.*}
     * fields (world / network handler / stat handler / recipe book — no
     * hardcoded names). The type→field resolution is the expensive part
     * (O(params × playerFields)); it is computed once into {@link #argPlan}
     * and thereafter only the live values are read, so this is cheap on the
     * per-tick path. Re-reading the current {@code realPlayer} each call keeps
     * it correct if a dimension swap recreated the player/world.
     */
    private Object[] ctorArgs(Object client, Object realPlayer) throws Exception {
        Object[] plan = argPlan;
        if (plan == null) {
            plan = buildArgPlan(client, realPlayer);
            argPlan = plan;
        }
        Object[] args = new Object[plan.length];
        for (int i = 0; i < plan.length; i++) {
            Object slot = plan[i];
            if (slot == ARG_CLIENT) {
                args[i] = client;
            } else if (slot == ARG_FALSE) {
                args[i] = Boolean.FALSE;
            } else {
                Object v = ((Field) slot).get(realPlayer);
                if (v == null) {
                    // Resolved at build time but null now (player/world
                    // recreated by a dimension swap). Rebuild once against
                    // the current player; resolveField only picks non-null
                    // fields, so the rebuilt plan reads non-null this tick.
                    argPlan = null;
                    return ctorArgs(client, realPlayer);
                }
                args[i] = v;
            }
        }
        return args;
    }

    private Object[] buildArgPlan(Object client, Object realPlayer) throws Exception {
        Class<?>[] pts = ctor.getParameterTypes();
        Object[] plan = new Object[pts.length];
        for (int i = 0; i < pts.length; i++) {
            Class<?> pt = pts[i];
            if (pt == boolean.class) {
                plan[i] = ARG_FALSE;
            } else if (pt.isInstance(client)) {
                plan[i] = ARG_CLIENT;
            } else {
                Field f = resolveField(realPlayer, pt);
                if (f == null) {
                    disable("cannot resolve ctor arg #" + i + " : " + pt.getName());
                    throw new IllegalStateException("unresolved ctor arg " + pt);
                }
                plan[i] = f;
            }
        }
        return plan;
    }

    private Field resolveField(Object realPlayer, Class<?> pt) throws Exception {
        for (Field f : playerFields) {
            if (pt.isAssignableFrom(f.getType()) && f.get(realPlayer) != null) {
                return f;
            }
        }
        return null;
    }

    /**
     * Marks the clone loaded so {@code ClientPlayerEntity.tick()} runs the
     * full movement path every horizon tick. Resolved in {@link #init}; one of
     * the two mechanisms is guaranteed non-null there or the predictor is
     * already disabled.
     */
    private void markLoaded(Object clone) throws Exception {
        if (mSetLoaded != null) {
            mSetLoaded.invoke(clone, true);
            return;
        }
        loadedField.set(clone, true);
        if (loadCountField != null) {
            loadCountField.set(clone, 0);
        }
    }

    private static Method tryMethod(Class<?> c, String name) {
        try {
            return c.getMethod(name);
        } catch (Throwable t) {
            return null;
        }
    }

    private static Method tryMethod1(Class<?> c, String name, Class<?> p) {
        try {
            return c.getMethod(name, p);
        } catch (Throwable t) {
            return null;
        }
    }

    /** {@code entity.getPos()} as a {@link Vec3d}, or {@code null}. */
    private Vec3d posOf(Object entity) {
        try {
            return mGetPos.invoke(entity) instanceof Vec3d v ? v : null;
        } catch (Throwable t) {
            return null;
        }
    }

    /** {@code entity.getWorld()} identity for the swap guard. */
    private Object worldOf(Object entity) {
        try {
            return mGetWorld.invoke(entity);
        } catch (Throwable t) {
            return null;
        }
    }

    /** True if look angle is within {@link #ANG_EPS} of the cached compute. */
    private boolean angleUnchanged(Object realPlayer) {
        if (mGetYaw == null || mGetPitch == null) {
            return true; // accessor absent -> bounded by sig + cache age
        }
        try {
            float yaw = (Float) mGetYaw.invoke(realPlayer);
            float pitch = (Float) mGetPitch.invoke(realPlayer);
            return Math.abs(yaw - lastYaw) <= ANG_EPS
                && Math.abs(pitch - lastPitch) <= ANG_EPS;
        } catch (Throwable t) {
            return false; // can't tell -> recompute (safe)
        }
    }

    /**
     * The HORIZON-deep ring in FIFO order (front first), each translated
     * by {@code drift} so the path origin stays glued to the real player.
     */
    private List<Vec3d> ringList(Vec3d drift) {
        List<Vec3d> out = new ArrayList<>(HORIZON);
        for (int i = 0; i < HORIZON; i++) {
            Vec3d v = ring[(ringStart + i) % HORIZON];
            if (v == null) {
                break;
            }
            out.add(v.add(drift));
        }
        return out;
    }

    /**
     * Every primitive {@code float}/{@code double}/{@code boolean} of the
     * {@code Input}, in stable hierarchy/declaration order (mirrors {@link
     * #dumpInput}). No name strings — version-portable. An unequal array
     * means the held input changed; equal means the path is still valid.
     */
    private static double[] inputSignature(Object in) {
        List<Double> vals = new ArrayList<>();
        for (Class<?> k = in.getClass(); k != null && k != Object.class;
             k = k.getSuperclass()) {
            for (Field f : k.getDeclaredFields()) {
                if (Modifier.isStatic(f.getModifiers())) {
                    continue;
                }
                Class<?> t = f.getType();
                try {
                    if (t == boolean.class) {
                        f.setAccessible(true);
                        vals.add(f.getBoolean(in) ? 1.0 : 0.0);
                    } else if (t == float.class) {
                        f.setAccessible(true);
                        vals.add((double) f.getFloat(in));
                    } else if (t == double.class) {
                        f.setAccessible(true);
                        vals.add(f.getDouble(in));
                    }
                } catch (Throwable ignored) {
                    // skipped field only weakens the cache, never wrong
                }
            }
        }
        double[] a = new double[vals.size()];
        for (int i = 0; i < a.length; i++) {
            a[i] = vals.get(i);
        }
        return a;
    }

    private static boolean sigEquals(double[] a, double[] b) {
        if (a == null || b == null || a.length != b.length) {
            return false;
        }
        for (int i = 0; i < a.length; i++) {
            if (a[i] != b[i]) {
                return false;
            }
        }
        return true;
    }

    /** Pristine post-ctor value of every field in {@link #playerFields}. */
    private Object[] snapshotClone(Object clone) {
        Object[] snap = new Object[playerFields.size()];
        for (int i = 0; i < snap.length; i++) {
            try {
                snap[i] = playerFields.get(i).get(clone);
            } catch (Throwable ignored) {
                snap[i] = null;
            }
        }
        return snap;
    }

    /**
     * Resets every declared field to its pristine post-ctor value so a
     * reused clone is field-identical to a freshly built one before
     * {@code copyFrom} re-seeds the live player state. Transient timers
     * (jump/sprint cooldown, swing) and the removed flag are covered
     * generically — no per-field name list. A field the ctor set once and
     * movement never reassigns (often {@code final}) may reject the set;
     * leaving it is correct (it still holds that ctor value).
     */
    private void restoreClone(Object clone, Object[] snap) {
        List<Field> fs = playerFields;
        for (int i = 0; i < fs.size(); i++) {
            try {
                fs.get(i).set(clone, snap[i]);
            } catch (Throwable ignored) {
                // unsettable final the ctor fixed once — already correct
            }
        }
    }

    /**
     * Seeds the clone from the live player. Fast path = targeted physics
     * copy (no whole-player NBT). Fallback = pristine restore + the game's
     * own {@code copyFrom}. Once the fidelity probe trips this switches
     * permanently to the fallback, so the fast path can never silently
     * corrupt the prediction. {@code autoJumpEnabled} is (re)seeded by the
     * caller for both paths.
     */
    private void seed(Object clone, Object real) throws Exception {
        if (fastSeed && !fastSeedTripped) {
            fastSeed(clone, real);
            return;
        }
        if (cloneSnapshot != null) {
            restoreClone(clone, cloneSnapshot);
        }
        mCopyFrom.invoke(clone, real);
    }

    /**
     * Targeted physics seed, identified by type (never field name): every
     * flat value field, the {@code DataTracker} positionally, {@code
     * AttributeContainer.setFrom}, the flat {@code PlayerAbilities}/{@code
     * HungerManager}, then {@code setPosition} to re-derive the bounding
     * box from the copied pos+pose. Overwrites all of it from the real
     * player, so a reused clone keeps nothing from a prior run — without
     * the whole-player NBT round-trip {@code copyFrom} pays.
     */
    private void fastSeed(Object clone, Object real) throws Exception {
        // 1. Flat value fields (primitive / Vec3d / enum / BlockPos).
        //    Immutable types share refs safely; the clone's tick replaces
        //    them. Per-field guarded: a rejected set just trips the probe.
        for (Field f : playerFields) {
            Class<?> t = f.getType();
            if (t.isPrimitive() || t == Vec3d.class || t.isEnum()
                || "BlockPos".equals(t.getSimpleName())) {
                try {
                    f.set(clone, f.get(real));
                } catch (Throwable ignored) {
                    // unsettable -> fidelity probe is the safety net
                }
            }
        }
        // 2. DataTracker: positional. clone & real are the same concrete
        //    class => identical entry layout, so index i matches. This
        //    overwrites every clone entry (no prior-run value survives).
        Object rt = dtField.get(real);
        Object ct = dtField.get(clone);
        Object[] re = (Object[]) dtEntriesField.get(rt);
        Object[] ce = (Object[]) dtEntriesField.get(ct);
        int n = Math.min(re.length, ce.length);
        for (int i = 0; i < n; i++) {
            if (re[i] != null && ce[i] != null) {
                mEntrySet.invoke(ce[i], mEntryGet.invoke(re[i]));
            }
        }
        // 3. Attributes: movement speed + modifiers (effects, equipment).
        mAttrSetFrom.invoke(attrField.get(clone), attrField.get(real));
        // 4/5. Flat client objects copyFrom's NBT would otherwise carry.
        copyFields(abilField.get(real), abilField.get(clone));
        copyFields(hungerField.get(real), hungerField.get(clone));
        // 6. pos was copied as a raw field; re-derive the bounding box and
        //    chunk/block caches from it now pose is set — what readNbt's
        //    setPos would have done. Without this tick-1 collision uses a
        //    stale box and the prediction is wrong at obstacles.
        Object p = mGetPos.invoke(clone);
        if (p instanceof Vec3d v) {
            mSetPosition.invoke(clone, v.x, v.y, v.z);
        }
    }

    /** First {@link #playerFields} entry whose TYPE simple-name matches. */
    private Field fieldByTypeSimpleName(String simple) {
        for (Field f : playerFields) {
            if (f.getType().getSimpleName().equals(simple)) {
                f.setAccessible(true);
                return f;
            }
        }
        return null;
    }

    private Object allocate(Class<?> c) throws Exception {
        return allocateInstance.invoke(unsafe, c);
    }

    private static Class<?> tryLoad(String name) {
        try {
            return Class.forName(name);
        } catch (Throwable t) {
            return null;
        }
    }

    private void disable(String why) {
        enabled = false;
        SimV2.LOGGER.warn("[simv2] movement predictor disabled: {}", why);
    }

    /** Real player's NBT as a string, or {@code null} if wiring unavailable. */
    private String snapshotNbt(Object realPlayer) {
        if (mWriteNbt == null || nbtCtor == null) {
            return null;
        }
        try {
            Object nbt = nbtCtor.newInstance();
            mWriteNbt.invoke(realPlayer, nbt);
            return String.valueOf(nbt);
        } catch (Throwable t) {
            return null;
        }
    }

    private static String fmt(Vec3d v) {
        return String.format("(%.2f,%.2f,%.2f)", v.x, v.y, v.z);
    }

    /**
     * Dumps every primitive {@code float}/{@code double}/{@code boolean} field
     * of the clone's copied {@code Input} (e.g. {@code movementForward},
     * {@code jumping}, {@code sprinting}) — version-portable, no name strings.
     * If these are all zero/false the clone replays "no keys held".
     */
    private static String dumpInput(Object in) {
        if (in == null) {
            return "<null>";
        }
        StringBuilder sb = new StringBuilder();
        for (Class<?> k = in.getClass(); k != null && k != Object.class;
             k = k.getSuperclass()) {
            for (Field f : k.getDeclaredFields()) {
                if (Modifier.isStatic(f.getModifiers())) {
                    continue;
                }
                Class<?> t = f.getType();
                if (t != float.class && t != double.class
                    && t != boolean.class) {
                    continue;
                }
                try {
                    f.setAccessible(true);
                    if (!sb.isEmpty()) {
                        sb.append(' ');
                    }
                    sb.append(f.getName()).append('=').append(f.get(in));
                } catch (Throwable ignored) {
                    // best-effort diagnostic only
                }
            }
        }
        return sb.isEmpty() ? "<none>" : sb.toString();
    }

    /** A short hint at where two NBT strings first diverge. */
    private static String firstDiff(String a, String b) {
        int n = Math.min(a.length(), b.length());
        int i = 0;
        while (i < n && a.charAt(i) == b.charAt(i)) {
            i++;
        }
        int from = Math.max(0, i - 20);
        int to = Math.min(a.length(), i + 40);
        return "near: ..." + a.substring(from, to).replace('\n', ' ') + "...";
    }

    /**
     * Copies every non-static instance field from {@code src} to {@code dst}
     * (same class). Only ever called on the small flat {@code net.minecraft.*}
     * {@code input} object — the unnamed module, so {@code setAccessible}
     * works and there is no JDK wall.
     */
    private static void copyFields(Object src, Object dst) throws Exception {
        if (src == null || dst == null) {
            return;
        }
        for (Class<?> k = src.getClass(); k != null && k != Object.class;
             k = k.getSuperclass()) {
            for (Field f : k.getDeclaredFields()) {
                if (Modifier.isStatic(f.getModifiers())) {
                    continue;
                }
                f.setAccessible(true);
                f.set(dst, f.get(src));
            }
        }
    }

    /** Non-static instance fields declared on {@code c} or a supertype. */
    private static List<Field> collectFields(Class<?> c) {
        List<Field> out = new ArrayList<>();
        for (Class<?> k = c; k != null && k != Object.class; k = k.getSuperclass()) {
            for (Field f : k.getDeclaredFields()) {
                if (Modifier.isStatic(f.getModifiers())) {
                    continue;
                }
                try {
                    f.setAccessible(true);
                } catch (RuntimeException e) {
                    continue;
                }
                out.add(f);
            }
        }
        return out;
    }
}
