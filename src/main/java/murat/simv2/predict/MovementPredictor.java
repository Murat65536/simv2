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
 * Predicts the real player's future positions by ticking a <em>disposable
 * clone built with the real constructor</em>.
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
                Object clone = ctor.newInstance(ctorArgs(client, realPlayer));
                long tCtor = System.nanoTime();

                // The game's own cross-entity state transfer (writeNbt(real)
                // -> readNbt(clone) + portal fields): position, motion,
                // rotation, fall distance, on-ground, abilities — into the
                // clone's *own* structures. Reads the real player; no mutate.
                mCopyFrom.invoke(clone, realPlayer);
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
                            + " end {}); input {}; cost ctor {}µs copyFrom {}µs"
                            + " loop {}µs",
                        path.size(),
                        String.format("%.3f", end.distanceTo(start)),
                        fmt(start), fmt(end), dumpInput(privInput),
                        (tCtor - tA) / 1000, (tCopy - tCtor) / 1000,
                        (tLoop1 - tLoop0) / 1000);
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
                return path;
            } finally {
                Prediction.end();
            }
        } catch (Throwable t) {
            enabled = false;
            if (!warned) {
                warned = true;
                SimV2.LOGGER.warn("[simv2] movement predictor disabled: {}",
                    String.valueOf(t), t);
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
                    if (sb.length() > 0) {
                        sb.append(' ');
                    }
                    sb.append(f.getName()).append('=').append(f.get(in));
                } catch (Throwable ignored) {
                    // best-effort diagnostic only
                }
            }
        }
        return sb.length() == 0 ? "<none>" : sb.toString();
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
