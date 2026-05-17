package murat.simv2.predict;

import murat.simv2.SimV2;
import net.minecraft.client.MinecraftClient;
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
 * makes them) — <b>zero reflective field-graph copying</b>, so none of the
 * JDK-encapsulation / nested-mutable problems can occur. Movement state is
 * seeded real→clone through a dozen permanent public accessors
 * ({@code setPosition/setVelocity/setYaw/setPose/setSprinting/…}); the one
 * sub-object the ctor leaves null, {@code input}, is the single small flat
 * {@code net.minecraft.*} object copied field-by-field (no JDK wall, no
 * hardcoded field names). The real player object never has
 * {@code tickMovement()} called on it, so it cannot be perturbed.
 *
 * <p>The clone shares the real {@code ClientWorld}/network handler (a fake
 * world is impractical); every side effect that would reach them is suppressed
 * by the WALA-generated {@code Prediction.ACTIVE} gate Mixins.
 *
 * <p>Version-portability: ctor resolved by being the widest public
 * constructor; args matched by type; accessors resolved by (permanent) name.
 * Any mismatch fails <em>loud at init</em> (disabled + logged) — never silent
 * corruption. Fully defensive: never throws into the client tick loop.
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
    private Method getWorld;

    private Method mTickMovement, mGetPos, mGetVelocity, mSetVelocity,
        mGetYaw, mSetYaw, mGetPitch, mSetPitch, mIsOnGround, mSetOnGround,
        mIsSprinting, mSetSprinting, mIsSneaking, mSetSneaking,
        mIsSwimming, mSetSwimming, mGetPose, mSetPose, mSetPosition,
        mGetAbilities;
    private Method mSetHeadYaw, mGetHeadYaw, mSetBodyYaw, mGetBodyYaw; // optional

    private MovementPredictor() {
    }

    /**
     * Builds a fresh clone of {@code realPlayer}, seeds it, steps it
     * {@link #HORIZON} ticks, and returns the predicted positions. Returns an
     * empty list (and disables itself) on any failure — never throws.
     *
     * @param realPlayer the live {@code ClientPlayerEntity} (opaque here).
     */
    public List<Vec3d> predict(Object realPlayer) {
        if (realPlayer == null) {
            return List.of();
        }
        try {
            if (!initialized) {
                init(realPlayer);
            }
            if (!enabled) {
                return List.of();
            }

            Object input = inputField.get(realPlayer);
            if (input == null) {
                return List.of(); // not ready — ticking would share real input
            }

            Object clone = ctor.newInstance(ctorArgs(realPlayer));

            // Seed movement state via permanent public API.
            Vec3d pos = (Vec3d) mGetPos.invoke(realPlayer);
            mSetPosition.invoke(clone, pos.x, pos.y, pos.z);
            mSetVelocity.invoke(clone, mGetVelocity.invoke(realPlayer));
            mSetYaw.invoke(clone, mGetYaw.invoke(realPlayer));
            mSetPitch.invoke(clone, mGetPitch.invoke(realPlayer));
            mSetOnGround.invoke(clone, mIsOnGround.invoke(realPlayer));
            mSetSprinting.invoke(clone, mIsSprinting.invoke(realPlayer));
            mSetSneaking.invoke(clone, mIsSneaking.invoke(realPlayer));
            mSetSwimming.invoke(clone, mIsSwimming.invoke(realPlayer));
            mSetPose.invoke(clone, mGetPose.invoke(realPlayer));
            if (mSetHeadYaw != null && mGetHeadYaw != null) {
                mSetHeadYaw.invoke(clone, mGetHeadYaw.invoke(realPlayer));
            }
            if (mSetBodyYaw != null && mGetBodyYaw != null) {
                mSetBodyYaw.invoke(clone, mGetBodyYaw.invoke(realPlayer));
            }
            // Abilities (flying/speed) — clone owns its instance; copy values.
            copyFields(mGetAbilities.invoke(realPlayer), mGetAbilities.invoke(clone));

            // The one ctor-null sub-object: a private copy of input.
            Object privInput = allocate(input.getClass());
            copyFields(input, privInput);
            inputField.set(clone, privInput);

            List<Vec3d> path = new ArrayList<>(HORIZON);
            Prediction.ACTIVE = true;
            try {
                for (int i = 0; i < HORIZON; i++) {
                    mTickMovement.invoke(clone);
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

    private void init(Object realPlayer) throws Exception {
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

        getWorld = playerClass.getMethod("getWorld");
        mTickMovement = playerClass.getMethod("tickMovement");
        mGetPos = playerClass.getMethod("getPos");
        mGetVelocity = playerClass.getMethod("getVelocity");
        mGetYaw = playerClass.getMethod("getYaw");
        mGetPitch = playerClass.getMethod("getPitch");
        mIsOnGround = playerClass.getMethod("isOnGround");
        mIsSprinting = playerClass.getMethod("isSprinting");
        mIsSneaking = playerClass.getMethod("isSneaking");
        mIsSwimming = playerClass.getMethod("isSwimming");
        mGetPose = playerClass.getMethod("getPose");
        mGetAbilities = playerClass.getMethod("getAbilities");

        mSetPosition = playerClass.getMethod("setPosition",
            double.class, double.class, double.class);
        mSetVelocity = playerClass.getMethod("setVelocity", mGetVelocity.getReturnType());
        mSetYaw = playerClass.getMethod("setYaw", float.class);
        mSetPitch = playerClass.getMethod("setPitch", float.class);
        mSetOnGround = playerClass.getMethod("setOnGround", boolean.class);
        mSetSprinting = playerClass.getMethod("setSprinting", boolean.class);
        mSetSneaking = playerClass.getMethod("setSneaking", boolean.class);
        mSetSwimming = playerClass.getMethod("setSwimming", boolean.class);
        mSetPose = playerClass.getMethod("setPose", mGetPose.getReturnType());

        mSetHeadYaw = optional("setHeadYaw", float.class);
        mGetHeadYaw = optional("getHeadYaw");
        mSetBodyYaw = optional("setBodyYaw", float.class);
        mGetBodyYaw = optional("getBodyYaw");

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
        ctorArgs(realPlayer);

        enabled = true;
        SimV2.LOGGER.info(
            "[simv2] movement predictor ready: ctor/{} args, horizon {}",
            ctor.getParameterCount(), HORIZON);
    }

    /**
     * Resolves the constructor arguments from the real player by type:
     * the {@link MinecraftClient} singleton, the player's world, the trailing
     * booleans as {@code false}, and any other reference arg by scanning the
     * player's own {@code net.minecraft.*} fields for an assignable value
     * (network handler / stat handler / recipe book — no hardcoded names).
     */
    private Object[] ctorArgs(Object realPlayer) throws Exception {
        MinecraftClient client = MinecraftClient.getInstance();
        Object world = getWorld.invoke(realPlayer);
        Class<?>[] pts = ctor.getParameterTypes();
        Object[] args = new Object[pts.length];
        for (int i = 0; i < pts.length; i++) {
            Class<?> pt = pts[i];
            if (pt == boolean.class) {
                args[i] = Boolean.FALSE;
            } else if (pt.isInstance(client)) {
                args[i] = client;
            } else if (world != null && pt.isInstance(world)) {
                args[i] = world;
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

    private Method optional(String name, Class<?>... params) {
        try {
            return playerClass.getMethod(name, params);
        } catch (NoSuchMethodException e) {
            return null;
        }
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
     * (same class). Only ever called on small flat {@code net.minecraft.*}
     * objects ({@code PlayerAbilities}, {@code Input}) — the unnamed module,
     * so {@code setAccessible} works and there is no JDK wall.
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
