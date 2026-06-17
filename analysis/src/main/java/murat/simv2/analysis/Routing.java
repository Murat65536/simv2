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
    /** {@code instanceof} type tests -> the constant they fold to for the simulated player entity. */
    private static final Map<String, String> TYPE_TESTS = new LinkedHashMap<>();

    private static void r(String target, Cat cat, String template) {
        ROUTES.put(target, new Route(cat, template));
    }

    /** Field access route, keyed "Lclass.fieldName". $0 is the ref (for getstatic there is none). */
    private static void f(String key, Cat cat, String template) {
        FIELD_ROUTES.put(key, new Route(cat, template));
    }

    /** Register an {@code instanceof <internalType>} fold (the sim entity is the local player). */
    private static void tt(String internalType, String literal) {
        TYPE_TESTS.put(internalType, literal);
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

        // ===== travelMidAir closure: gravity + speed leaves (no WORLD threading) =====
        // PHYSICS (transpile/recurse)
        r("Lnet/minecraft/entity/LivingEntity#getMovementSpeed(F)F", Cat.PHYSICS, "");
        r("Lnet/minecraft/entity/LivingEntity#getEffectiveGravity()D", Cat.PHYSICS, "");
        r("Lnet/minecraft/entity/Entity#getFinalGravity()D", Cat.PHYSICS, "");
        // getOffGroundSpeed is called on the LivingEntity static type but the reached override is
        // PlayerEntity's; SimGenerator resolves the IR record by selector (virtual dispatch).
        r("Lnet/minecraft/entity/LivingEntity#getOffGroundSpeed()F", Cat.PHYSICS, "");
        // STATE_READ
        r("Lnet/minecraft/entity/LivingEntity#isOnGround()Z", Cat.STATE_READ, "s.onGround");
        r("Lnet/minecraft/entity/LivingEntity#getMovementSpeed()F", Cat.STATE_READ, "s.movementSpeed");
        r("Lnet/minecraft/entity/LivingEntity#getVelocity()Lnet/minecraft/util/math/Vec3d;", Cat.STATE_READ, "s.velocity");
        // MATH
        r("Ljava/lang/Math#min(DD)D", Cat.MATH, "java.lang.Math.min($0, $1)");
        // PRUNE (value substitutes for out-of-scope: vanilla gravity, no status effects)
        r("Lnet/minecraft/entity/Entity#getGravity()D", Cat.PRUNE, "0.08");
        r("Lnet/minecraft/entity/Entity#hasNoGravity()Z", Cat.PRUNE, "false");
        r("Lnet/minecraft/entity/LivingEntity#hasStatusEffect(Lnet/minecraft/registry/entry/RegistryEntry;)Z", Cat.PRUNE, "false");
        f("Lnet/minecraft/entity/effect/StatusEffects.SLOW_FALLING", Cat.PRUNE, "null");

        // ===== travelMidAir closure: full one-tick physics (collision DELEGATED to MovementSim) =====
        // PHYSICS (transpile/recurse). Template unused. updateVelocity / getVelocity / setVelocity(Vec3d)
        // dispatched on the LivingEntity receiver resolve to the existing Entity# rows by selector.
        r("Lnet/minecraft/entity/LivingEntity#travelMidAir(Lnet/minecraft/util/math/Vec3d;)V", Cat.PHYSICS, "");
        r("Lnet/minecraft/entity/LivingEntity#applyMovementInput(Lnet/minecraft/util/math/Vec3d;F)Lnet/minecraft/util/math/Vec3d;", Cat.PHYSICS, "");
        r("Lnet/minecraft/entity/LivingEntity#applyClimbingSpeed(Lnet/minecraft/util/math/Vec3d;)Lnet/minecraft/util/math/Vec3d;", Cat.PHYSICS, "");

        // STATE: extra reads/writes reached by the closure
        r("Lnet/minecraft/entity/LivingEntity#getY()D", Cat.STATE_READ, "s.pos.y()");
        // 3-double velocity write (distinct descriptor from the existing setVelocity(Vec3d) row).
        r("Lnet/minecraft/entity/LivingEntity#setVelocity(DDD)V", Cat.STATE_WRITE, "s.velocity = new Vec3($1, $2, $3)");

        // WORLD: read-only world snapshot, or the move() collision boundary. The slipperiness chain
        // (getVelocityAffectingPos -> getWorld -> getBlockState -> getBlock -> getSlipperiness) folds
        // into the single fused WorldSnapshot.slipperinessAt(s, world); the intermediate objects are
        // dead. Entity.move(SELF, velocity) DELEGATES to the validated hand-port collision helper.
        r("Lnet/minecraft/entity/LivingEntity#getVelocityAffectingPos()Lnet/minecraft/util/math/BlockPos;", Cat.WORLD, "murat.simv2.sim.WorldSnapshot.velocityAffectingPos(s)");
        r("Lnet/minecraft/entity/LivingEntity#getWorld()Lnet/minecraft/world/World;", Cat.WORLD, "world");
        r("Lnet/minecraft/world/World#getBlockState(Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/block/BlockState;", Cat.WORLD, "murat.simv2.sim.WorldSnapshot.blockStateAt($0, $1)");
        r("Lnet/minecraft/block/BlockState#getBlock()Lnet/minecraft/block/Block;", Cat.WORLD, "$0");
        r("Lnet/minecraft/block/Block#getSlipperiness()F", Cat.WORLD, "murat.simv2.sim.WorldSnapshot.slipperinessAt(s, world)");
        r("Lnet/minecraft/world/World#getBottomY()I", Cat.WORLD, "murat.simv2.sim.WorldSnapshot.bottomY()");
        r("Lnet/minecraft/entity/LivingEntity#move(Lnet/minecraft/entity/MovementType;Lnet/minecraft/util/math/Vec3d;)V", Cat.WORLD, "murat.simv2.sim.MovementSim.moveSelf(s, world)");
        // applyClimbingSpeed dead-branch world reads — classified for coverage; eliminated by isClimbing=false.
        r("Lnet/minecraft/entity/LivingEntity#getBlockStateAtPos()Lnet/minecraft/block/BlockState;", Cat.WORLD, "murat.simv2.sim.WorldSnapshot.blockStateAtPos(s)");
        r("Lnet/minecraft/block/BlockState#isOf(Lnet/minecraft/block/Block;)Z", Cat.WORLD, "murat.simv2.sim.WorldSnapshot.isOf($0, $1)");

        // PRUNE: not movement. Value-returning prunes carry a substitute constant that makes the
        // out-of-scope branches dead (no levitation/slow-falling, never no-drag, never climbing,
        // server-authoritative so chunks always loaded).
        r("Lnet/minecraft/entity/LivingEntity#getStatusEffect(Lnet/minecraft/registry/entry/RegistryEntry;)Lnet/minecraft/entity/effect/StatusEffectInstance;", Cat.PRUNE, "null");
        r("Lnet/minecraft/entity/effect/StatusEffectInstance#getAmplifier()I", Cat.PRUNE, "0");
        r("Lnet/minecraft/world/World#isChunkLoaded(Lnet/minecraft/util/math/BlockPos;)Z", Cat.PRUNE, "true");
        r("Lnet/minecraft/entity/LivingEntity#hasNoDrag()Z", Cat.PRUNE, "false");
        r("Lnet/minecraft/entity/LivingEntity#isClimbing()Z", Cat.PRUNE, "false");
        r("Lnet/minecraft/entity/LivingEntity#isHoldingOntoLadder()Z", Cat.PRUNE, "false");
        r("Lnet/minecraft/block/PowderSnowBlock#canWalkOnPowderSnow(Lnet/minecraft/entity/Entity;)Z", Cat.PRUNE, "false");
        // Void side-effect (resets fallDistance, not movement). Drop.
        r("Lnet/minecraft/entity/LivingEntity#onLanding()V", Cat.PRUNE, "");

        // MATH: deterministic primitives
        r("Ljava/lang/Math#max(DD)D", Cat.MATH, "java.lang.Math.max($0, $1)");
        r("Lnet/minecraft/util/math/MathHelper#clamp(DDD)D", Cat.MATH, "murat.simv2.sim.MathHelperPort.clamp($0, $1, $2)");

        // FIELD access reached by the travelMidAir closure.
        // Registry/block keys feed only already-pruned consumers; MovementType/SELF feeds only the
        // WORLD-delegated move(); isClient is false (server-authoritative). All benign substitutes.
        f("Lnet/minecraft/entity/effect/StatusEffects.LEVITATION", Cat.PRUNE, "null");
        f("Lnet/minecraft/block/Blocks.SCAFFOLDING", Cat.PRUNE, "null");
        f("Lnet/minecraft/entity/MovementType.SELF", Cat.PRUNE, "null");
        f("Lnet/minecraft/world/World.isClient", Cat.PRUNE, "false");
        // applyMovementInput final-branch reads (manifest REF — read-only, invariant-safe).
        f("Lnet/minecraft/entity/LivingEntity.horizontalCollision", Cat.STATE_READ, "s.horizontalCollision");
        f("Lnet/minecraft/entity/LivingEntity.jumping", Cat.STATE_READ, "s.jumping");
        f("Lnet/minecraft/entity/LivingEntity.wasInPowderSnow", Cat.PRUNE, "false");

        // --- instanceof type tests (the simulated entity IS the local player) ---
        tt("Lnet/minecraft/entity/player/PlayerEntity", "true");  // sim entity is a player
        tt("Lnet/minecraft/entity/Flutterer", "false");           // not a bee/allay -> normal air drag (0.98F)

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
        Route exact = ROUTES.get(target);
        if (exact != null) {
            return exact;
        }
        // Virtual dispatch: a call's declared owner can differ from where we routed the selector
        // (e.g. LivingEntity#getFinalGravity vs the routed Entity#getFinalGravity). Fall back to a
        // selector (name+descriptor) match; ambiguous distinct routings -> null (coverage gap).
        String sel = selectorOf(target);
        Route match = null;
        for (Map.Entry<String, Route> e : ROUTES.entrySet()) {
            if (sel.equals(selectorOf(e.getKey()))) {
                if (match != null && !match.equals(e.getValue())) {
                    return null;
                }
                match = e.getValue();
            }
        }
        return match;
    }

    private static String selectorOf(String key) {
        return key.substring(key.indexOf('#') + 1);
    }

    static Route fieldLookup(String declaringClass, String fieldName) {
        return FIELD_ROUTES.get(declaringClass + "." + fieldName);
    }

    /** Constant an {@code instanceof <internalType>} folds to, or null (coverage gap). */
    static String typeTest(String internalType) {
        return TYPE_TESTS.get(internalType);
    }

    private Routing() {
    }
}
