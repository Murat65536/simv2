package murat.simv2.analysis;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * M2 — the routing table. For every call the transpiler ({@link SimGenerator}) encounters, this
 * decides HOW it is handled. It is the one place curated human judgement lives about the
 * physics/world boundary, and it is enforced as a coverage invariant: a call target that is
 * reached during transpilation but not classified here makes generation FAIL (never a silent drop),
 * so a future Minecraft movement mechanic cannot slip through unhandled.
 *
 * <p>Templates use {@code $k} = the k-th SSA use of the call (for instance calls {@code $0} is the
 * receiver, which maps to the {@link murat.simv2.sim.SimPlayerState} {@code s}).
 */
final class Routing {

    enum Cat {
        /** Transpile the callee (recurse) — it is movement control logic. */
        PHYSICS,
        /** Map to a deterministic math/vector primitive (Vec3 / MathHelperPort). */
        MATH,
        /** Read a player-state field from SimPlayerState. */
        STATE_READ,
        /** Write a player-state field on SimPlayerState (void call). */
        STATE_WRITE,
        /** Delegate to the validated standalone world/collision layer. */
        WORLD,
        /** Drop — not movement (sounds, particles, fall damage, events, profiler). */
        PRUNE
    }

    record Route(Cat cat, String template) {
    }

    private static final Map<String, Route> ROUTES = new LinkedHashMap<>();
    private static final Map<String, Route> FIELD_ROUTES = new LinkedHashMap<>();

    private static void r(String target, Cat cat, String template) {
        ROUTES.put(target, new Route(cat, template));
    }

    /** Field access route, keyed "Lclass.fieldName". $0 is the ref (for getstatic there is none). */
    private static void f(String key, Cat cat, String template) {
        FIELD_ROUTES.put(key, new Route(cat, template));
    }

    static {
        // --- MATH: vector / scalar primitives ---
        r("Lnet/minecraft/util/math/Vec3d#lengthSquared()D", Cat.MATH, "$0.lengthSquared()");
        r("Lnet/minecraft/util/math/Vec3d#normalize()Lnet/minecraft/util/math/Vec3d;", Cat.MATH, "$0.normalize()");
        r("Lnet/minecraft/util/math/Vec3d#multiply(D)Lnet/minecraft/util/math/Vec3d;", Cat.MATH, "$0.scale($1)");
        r("Lnet/minecraft/util/math/Vec3d#add(Lnet/minecraft/util/math/Vec3d;)Lnet/minecraft/util/math/Vec3d;", Cat.MATH, "$0.add($1)");
        r("Lnet/minecraft/util/math/MathHelper#sin(F)F", Cat.MATH, "murat.simv2.sim.MathHelperPort.sin($0)");
        r("Lnet/minecraft/util/math/MathHelper#cos(F)F", Cat.MATH, "murat.simv2.sim.MathHelperPort.cos($0)");

        // --- STATE: player getters/setters <-> SimPlayerState fields ---
        r("Lnet/minecraft/entity/Entity#getYaw()F", Cat.STATE_READ, "s.yaw");
        r("Lnet/minecraft/entity/Entity#getVelocity()Lnet/minecraft/util/math/Vec3d;", Cat.STATE_READ, "s.velocity");
        r("Lnet/minecraft/entity/Entity#setVelocity(Lnet/minecraft/util/math/Vec3d;)V", Cat.STATE_WRITE, "s.velocity = $1");

        r("Lnet/minecraft/entity/player/PlayerEntity#isSprinting()Z", Cat.STATE_READ, "s.sprinting");
        r("Lnet/minecraft/entity/player/PlayerAbilities#getFlySpeed()F", Cat.STATE_READ, "s.flySpeed");

        // --- PRUNE: not movement; value-returning prunes carry a substitute constant ---
        r("Lnet/minecraft/entity/player/PlayerEntity#hasVehicle()Z", Cat.PRUNE, "false");

        // --- PHYSICS: transpile (recurse). Template unused. ---
        r("Lnet/minecraft/entity/Entity#movementInputToVelocity(Lnet/minecraft/util/math/Vec3d;FF)Lnet/minecraft/util/math/Vec3d;", Cat.PHYSICS, "");
        r("Lnet/minecraft/entity/Entity#updateVelocity(FLnet/minecraft/util/math/Vec3d;)V", Cat.PHYSICS, "");
        r("Lnet/minecraft/entity/player/PlayerEntity#getOffGroundSpeed()F", Cat.PHYSICS, "");

        // --- FIELD access (getfield/getstatic) ---
        f("Lnet/minecraft/util/math/Vec3d.x", Cat.MATH, "$0.x()");
        f("Lnet/minecraft/util/math/Vec3d.y", Cat.MATH, "$0.y()");
        f("Lnet/minecraft/util/math/Vec3d.z", Cat.MATH, "$0.z()");
        f("Lnet/minecraft/util/math/Vec3d.ZERO", Cat.MATH, "Vec3.ZERO");
        // this.abilities.<x>: abilities stands in for the player state; flying maps to s.flying.
        f("Lnet/minecraft/entity/player/PlayerEntity.abilities", Cat.STATE_READ, "s");
        f("Lnet/minecraft/entity/player/PlayerAbilities.flying", Cat.STATE_READ, "s.flying");
    }

    static Route lookup(String target) {
        return ROUTES.get(target);
    }

    static Route fieldLookup(String declaringClass, String fieldName) {
        return FIELD_ROUTES.get(declaringClass + "." + fieldName);
    }

    private Routing() {
    }
}
