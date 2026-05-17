package murat.simv2.predict;

import murat.simv2.SimV2;
import net.minecraft.util.math.Vec3d;

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
 * wall). The clone shares the real {@code ClientWorld}/network handler; every
 * side effect that would reach them is suppressed by the WALA-generated
 * {@code Prediction.ACTIVE} gate Mixins.
 *
 * <p>Any resolution mismatch fails <em>loud at init</em> (disabled + logged)
 * — never silent corruption. Fully defensive: never throws into the client
 * tick loop.
 */
public final class MovementPredictor {

    public static final MovementPredictor INSTANCE = new MovementPredictor();

    /** Predicted ticks per frame (≈ {@value}/20 s of look-ahead). */
    private static final int HORIZON = 60;

    private boolean initialized;
    private boolean enabled;
    private boolean warned;

    private Object unsafe;
    private Method allocateInstance;

    private Class<?> playerClass;
    private Constructor<?> ctor;
    private List<Field> playerFields;     // net.minecraft-declared, for ctor-arg scan
    private Field inputField;

    private Method mCopyFrom;  // Entity.copyFrom(Entity) — the game's own transfer
    private Method mTick;      // the entry we run forward (full entity tick)
    private Method mGetPos;    // the result we read

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

            Object clone = ctor.newInstance(ctorArgs(client, realPlayer));

            // The game's own cross-entity state transfer (writeNbt(real) ->
            // readNbt(clone) + portal fields): position, motion, rotation,
            // fall distance, on-ground, abilities — into the clone's *own*
            // structures. Reads the real player; does not mutate it.
            mCopyFrom.invoke(clone, realPlayer);

            // input is client-only transient (not in NBT) and ctor-null — the
            // one sub-object we must give the clone its own private copy of.
            Object privInput = allocate(input.getClass());
            copyFields(input, privInput);
            inputField.set(clone, privInput);

            List<Vec3d> path = new ArrayList<>(HORIZON);
            Prediction.ACTIVE = true;
            try {
                for (int i = 0; i < HORIZON; i++) {
                    mTick.invoke(clone);
                    Object p = mGetPos.invoke(clone);
                    if (p instanceof Vec3d v) {
                        path.add(v);
                    } else {
                        break;
                    }
                }
            } finally {
                Prediction.ACTIVE = false;
            }
            return path;
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
     * other reference arg by scanning the player's own {@code net.minecraft.*}
     * fields for an assignable value (world / network handler / stat handler /
     * recipe book — no hardcoded names).
     */
    private Object[] ctorArgs(Object client, Object realPlayer) throws Exception {
        Class<?>[] pts = ctor.getParameterTypes();
        Object[] args = new Object[pts.length];
        for (int i = 0; i < pts.length; i++) {
            Class<?> pt = pts[i];
            if (pt == boolean.class) {
                args[i] = Boolean.FALSE;
            } else if (pt.isInstance(client)) {
                args[i] = client;
            } else {
                args[i] = scanField(realPlayer, pt);
                if (args[i] == null) {
                    disable("cannot resolve ctor arg #" + i + " : " + pt.getName());
                    throw new IllegalStateException("unresolved ctor arg " + pt);
                }
            }
        }
        return args;
    }

    private Object scanField(Object realPlayer, Class<?> pt) throws Exception {
        for (Field f : playerFields) {
            if (pt.isAssignableFrom(f.getType())) {
                Object v = f.get(realPlayer);
                if (v != null) {
                    return v;
                }
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
