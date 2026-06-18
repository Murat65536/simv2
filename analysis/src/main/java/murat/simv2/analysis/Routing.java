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
        r("Lnet/minecraft/entity/LivingEntity#getVelocityAffectingPos()Lnet/minecraft/util/math/BlockPos;", Cat.WORLD, "murat.simv2.sim.SimWorldOps.velocityAffectingPos(s)");
        r("Lnet/minecraft/entity/LivingEntity#getWorld()Lnet/minecraft/world/World;", Cat.WORLD, "world");
        r("Lnet/minecraft/world/World#getBlockState(Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/block/BlockState;", Cat.WORLD, "murat.simv2.sim.SimWorldOps.blockStateAt($0, $1)");
        r("Lnet/minecraft/block/BlockState#getBlock()Lnet/minecraft/block/Block;", Cat.WORLD, "$0");
        r("Lnet/minecraft/block/Block#getSlipperiness()F", Cat.WORLD, "murat.simv2.sim.SimWorldOps.slipperinessAt(s, world)");
        r("Lnet/minecraft/world/World#getBottomY()I", Cat.WORLD, "murat.simv2.sim.SimWorldOps.bottomY()");
        r("Lnet/minecraft/entity/LivingEntity#move(Lnet/minecraft/entity/MovementType;Lnet/minecraft/util/math/Vec3d;)V", Cat.WORLD, "murat.simv2.sim.MovementSim.moveSelf(s, world)");
        // applyClimbingSpeed dead-branch world reads — classified for coverage; eliminated by isClimbing=false.
        r("Lnet/minecraft/entity/LivingEntity#getBlockStateAtPos()Lnet/minecraft/block/BlockState;", Cat.WORLD, "murat.simv2.sim.SimWorldOps.blockStateAtPos(s)");
        // The ONLY isOf in the closure is applyClimbingSpeed's `getBlockStateAtPos().isOf(SCAFFOLDING)`,
        // so route it directly to the scaffolding world read (args ignored). Revisit if a second isOf appears.
        r("Lnet/minecraft/block/BlockState#isOf(Lnet/minecraft/block/Block;)Z", Cat.WORLD, "murat.simv2.sim.SimWorldOps.isScaffoldingAt(s, world)");

        // PRUNE: not movement. Value-returning prunes carry a substitute constant that makes the
        // out-of-scope branches dead (no levitation/slow-falling, never no-drag, never climbing,
        // server-authoritative so chunks always loaded).
        r("Lnet/minecraft/entity/LivingEntity#getStatusEffect(Lnet/minecraft/registry/entry/RegistryEntry;)Lnet/minecraft/entity/effect/StatusEffectInstance;", Cat.PRUNE, "null");
        r("Lnet/minecraft/entity/effect/StatusEffectInstance#getAmplifier()I", Cat.PRUNE, "0");
        r("Lnet/minecraft/world/World#isChunkLoaded(Lnet/minecraft/util/math/BlockPos;)Z", Cat.PRUNE, "true");
        r("Lnet/minecraft/entity/LivingEntity#hasNoDrag()Z", Cat.PRUNE, "false");
        // Phase 1: climbing is now in scope. isClimbing reads the live/snapshot world (ladder/vine/
        // scaffolding at the feet cell, or an open trapdoor over a matching ladder); isHoldingOntoLadder
        // is just sneaking. This lights up the (already-transpiled) applyClimbingSpeed clamp + the
        // applyMovementInput climb-up branch.
        r("Lnet/minecraft/entity/LivingEntity#isClimbing()Z", Cat.WORLD, "murat.simv2.sim.SimWorldOps.isClimbing(s, world)");
        r("Lnet/minecraft/entity/LivingEntity#isHoldingOntoLadder()Z", Cat.STATE_READ, "s.sneaking");
        r("Lnet/minecraft/block/PowderSnowBlock#canWalkOnPowderSnow(Lnet/minecraft/entity/Entity;)Z", Cat.PRUNE, "false");
        // Entity.onLanding(): resets fallDistance to 0. Now that fallDistance is a sim-managed state
        // field (checkWaterState calls this on every water-touch tick; applyClimbingSpeed calls it
        // when climbing), model the reset faithfully rather than dropping it.
        r("Lnet/minecraft/entity/LivingEntity#onLanding()V", Cat.STATE_WRITE, "s.fallDistance = 0.0");

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

        // ===== jump() closure (pre-travel velocity kick) =====
        // PHYSICS (transpile/recurse). jump() reads no WORLD-template call -> no world param.
        r("Lnet/minecraft/entity/LivingEntity#jump()V", Cat.PHYSICS, "");
        r("Lnet/minecraft/entity/LivingEntity#getJumpVelocity()F", Cat.PHYSICS, "");
        r("Lnet/minecraft/entity/LivingEntity#getJumpVelocity(F)F", Cat.PHYSICS, "");
        r("Lnet/minecraft/entity/LivingEntity#getJumpBoostVelocityModifier()F", Cat.PHYSICS, "");
        // addVelocityInternal(v): velocity = getVelocity().add(v) -> setVelocity. All callees already routed.
        r("Lnet/minecraft/entity/Entity#addVelocityInternal(Lnet/minecraft/util/math/Vec3d;)V", Cat.PHYSICS, "");
        // Block honey/slime jump multiplier — OUT OF SCOPE. PRUNE to float 1.0F so its world/block-
        // reading body is never transpiled (keeps jump() world-free). Keyed on the LivingEntity call site.
        r("Lnet/minecraft/entity/LivingEntity#getJumpVelocityMultiplier()F", Cat.PRUNE, "1.0F");
        // JUMP_STRENGTH attribute read -> the captured snapshot field. Returns D (double); the (float)
        // narrowing lives in the caller getJumpVelocity(F), mirroring getMovementSpeed. SAFE: keyed on the
        // exact target, and within the transpiled closure getAttributeValue is only ever reached with
        // JUMP_STRENGTH (gravity is PRUNEd; step-height/move-speed callers are not in the closure). If a
        // future closure adds another getAttributeValue caller, rework this BEFORE adding it.
        r("Lnet/minecraft/entity/LivingEntity#getAttributeValue(Lnet/minecraft/registry/entry/RegistryEntry;)D", Cat.STATE_READ, "s.jumpStrength");
        // getstatic attribute/effect keys feed only the value-route / already-PRUNEd consumers. Benign null.
        f("Lnet/minecraft/entity/attribute/EntityAttributes.JUMP_STRENGTH", Cat.PRUNE, "null");
        f("Lnet/minecraft/entity/effect/StatusEffects.JUMP_BOOST", Cat.PRUNE, "null");
        // velocityDirty: network-resync bookkeeping flag written by jump() (a putfield), NOT movement.
        // PRUNE with empty template -> the putfield handler drops the write.
        f("Lnet/minecraft/entity/LivingEntity.velocityDirty", Cat.PRUNE, "");

        // ===== sneak-edge clamp (Phase 2 of zero-hardcode): generate PlayerEntity.adjustMovementForSneaking =====
        // PHYSICS (transpile/recurse) — all in the IR, no new transpiler ops.
        r("Lnet/minecraft/entity/player/PlayerEntity#adjustMovementForSneaking(Lnet/minecraft/util/math/Vec3d;Lnet/minecraft/entity/MovementType;)Lnet/minecraft/util/math/Vec3d;", Cat.PHYSICS, "");
        r("Lnet/minecraft/entity/player/PlayerEntity#isSpaceAroundPlayerEmpty(DDD)Z", Cat.PHYSICS, "");
        r("Lnet/minecraft/entity/player/PlayerEntity#method_30263(F)Z", Cat.PHYSICS, "");
        // STATE / MATH leaves
        r("Lnet/minecraft/entity/player/PlayerEntity#clipAtLedge()Z", Cat.STATE_READ, "s.sneaking");
        r("Lnet/minecraft/entity/player/PlayerEntity#getStepHeight()F", Cat.STATE_READ, "(float) s.stepHeight");
        r("Lnet/minecraft/entity/Entity#getBoundingBox()Lnet/minecraft/util/math/Box;", Cat.STATE_READ, "s.boundingBox()");
        r("Ljava/lang/Math#abs(D)D", Cat.MATH, "java.lang.Math.abs($0)");
        r("Ljava/lang/Math#signum(D)D", Cat.MATH, "java.lang.Math.signum($0)");
        // WORLD: the empty-space probe -> a block-collision query (drops entity collisions + worldborder).
        r("Lnet/minecraft/world/World#isSpaceEmpty(Lnet/minecraft/entity/Entity;Lnet/minecraft/util/math/Box;)Z", Cat.WORLD, "world.collisions($2).isEmpty()");
        // Box.<init>(6 doubles) is handled inline by SimGenerator (javaType Box->AABB; new AABB(...)).
        // Box face fields -> AABB accessors.
        f("Lnet/minecraft/util/math/Box.minX", Cat.MATH, "$0.minX()");
        f("Lnet/minecraft/util/math/Box.minY", Cat.MATH, "$0.minY()");
        f("Lnet/minecraft/util/math/Box.minZ", Cat.MATH, "$0.minZ()");
        f("Lnet/minecraft/util/math/Box.maxX", Cat.MATH, "$0.maxX()");
        f("Lnet/minecraft/util/math/Box.maxY", Cat.MATH, "$0.maxY()");
        f("Lnet/minecraft/util/math/Box.maxZ", Cat.MATH, "$0.maxZ()");
        // MovementType.SELF already routed (PRUNE null); PLAYER too. The (type==SELF||type==PLAYER)
        // check folds true since both -> null and we only ever call it for SELF movement.
        f("Lnet/minecraft/entity/MovementType.PLAYER", Cat.PRUNE, "null");
        // method_30263's airborne branch reads fallDistance (a captured/sim-managed state field).
        f("Lnet/minecraft/entity/player/PlayerEntity.fallDistance", Cat.STATE_READ, "s.fallDistance");

        // ===== fluid detection + flow push (zero-hardcode P1): generate Entity.updateWaterState =====
        // Retires the velocity-bearing core of the Environment.java hand-port. Closure:
        // updateWaterState -> {checkWaterState, updateMovementInFluid} -> {isPushedByFluids}.
        // PHYSICS (transpile/recurse).
        r("Lnet/minecraft/entity/Entity#updateWaterState()Z", Cat.PHYSICS, "");
        r("Lnet/minecraft/entity/Entity#checkWaterState()V", Cat.PHYSICS, "");
        r("Lnet/minecraft/entity/Entity#updateMovementInFluid(Lnet/minecraft/registry/tag/TagKey;D)Z", Cat.PHYSICS, "");
        r("Lnet/minecraft/entity/Entity#isInLava()Z", Cat.PHYSICS, "");
        // isPushedByFluids resolves (by selector) to the PlayerEntity override: !this.abilities.flying.
        r("Lnet/minecraft/entity/Entity#isPushedByFluids()Z", Cat.PHYSICS, "");

        // WORLD: the per-cell fluid resolve + the ultrawarm (Nether faster-lava) dimension read.
        // getFluidState(pos) -> a coordinate-bound FluidView (uses the real `world` param, not the
        // dead getWorld() chain local — mirrors the slipperiness fusion). getDimension() folds to the
        // world; ultrawarm() reads the dimension flag.
        r("Lnet/minecraft/world/World#getFluidState(Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/fluid/FluidState;", Cat.WORLD, "new murat.simv2.sim.FluidView(world, $1)");
        r("Lnet/minecraft/world/World#getDimension()Lnet/minecraft/world/dimension/DimensionType;", Cat.WORLD, "world");
        r("Lnet/minecraft/world/dimension/DimensionType#ultrawarm()Z", Cat.WORLD, "world.isUltrawarm()");

        // FluidView value-type methods (the FluidState chain): isIn(tag) / getHeight / getVelocity.
        // getHeight/getVelocity drop the (world,pos) args — the coords are bound in the FluidView.
        r("Lnet/minecraft/fluid/FluidState#isIn(Lnet/minecraft/registry/tag/TagKey;)Z", Cat.MATH, "$0.isIn($1)");
        r("Lnet/minecraft/fluid/FluidState#getHeight(Lnet/minecraft/world/BlockView;Lnet/minecraft/util/math/BlockPos;)F", Cat.MATH, "$0.getHeight()");
        r("Lnet/minecraft/fluid/FluidState#getVelocity(Lnet/minecraft/world/BlockView;Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/util/math/Vec3d;", Cat.MATH, "$0.getVelocity()");
        // BlockPos.Mutable cursor: set(x,y,z) mutates in place and returns this (BlockPosM).
        r("Lnet/minecraft/util/math/BlockPos$Mutable#set(III)Lnet/minecraft/util/math/BlockPos$Mutable;", Cat.MATH, "$0.set($1, $2, $3)");
        // AABB / Vec3 / MathHelper math used by the cell scan.
        r("Lnet/minecraft/util/math/Box#contract(D)Lnet/minecraft/util/math/Box;", Cat.MATH, "$0.contract($1)");
        r("Lnet/minecraft/util/math/Vec3d#length()D", Cat.MATH, "$0.length()");
        r("Lnet/minecraft/util/math/MathHelper#floor(D)I", Cat.MATH, "murat.simv2.sim.MathHelperPort.floor($0)");
        r("Lnet/minecraft/util/math/MathHelper#ceil(D)I", Cat.MATH, "murat.simv2.sim.MathHelperPort.ceil($0)");

        // STATE: the fluidHeight map (Object2DoubleMap<TagKey>) -> the per-tag SimPlayerState slots,
        // dispatched on the tag by SimRuntime. The map ref ($0 = getfield fluidHeight = `s`) is dead;
        // the helpers take `s` directly. touchingWater is a plain state read.
        r("Lit/unimi/dsi/fastutil/objects/Object2DoubleMap#clear()V", Cat.STATE_WRITE, "murat.simv2.sim.SimRuntime.clearFluidHeight(s)");
        r("Lit/unimi/dsi/fastutil/objects/Object2DoubleMap#put(Ljava/lang/Object;D)D", Cat.STATE_WRITE, "murat.simv2.sim.SimRuntime.putFluidHeight(s, $1, $2)");
        r("Lit/unimi/dsi/fastutil/objects/Object2DoubleMap#getDouble(Ljava/lang/Object;)D", Cat.STATE_READ, "murat.simv2.sim.SimRuntime.getFluidHeight(s, $1)");
        r("Lnet/minecraft/entity/Entity#isTouchingWater()Z", Cat.STATE_READ, "s.touchingWater");

        // PRUNE: out-of-scope / dead. Region always loaded (server-authoritative); no vehicle (so the
        // boat-vehicle branch in checkWaterState is dead — its checkcast passes through and the
        // isSubmergedInWater() call is never reached); onSwimmingStart is sound/particles.
        r("Lnet/minecraft/entity/Entity#isRegionUnloaded()Z", Cat.PRUNE, "false");
        r("Lnet/minecraft/entity/Entity#getVehicle()Lnet/minecraft/entity/Entity;", Cat.PRUNE, "null");
        r("Lnet/minecraft/entity/Entity#onSwimmingStart()V", Cat.PRUNE, "");
        r("Lnet/minecraft/entity/vehicle/AbstractBoatEntity#isSubmergedInWater()Z", Cat.PRUNE, "false");

        // FIELD access reached by the fluid closure. fluidHeight (the map) stands in for state -> `s`
        // (dead local; the map helpers reference `s` directly). touchingWater/firstUpdate are plain
        // state fields (the touchingWater write goes through the lvalue-template putfield handler).
        f("Lnet/minecraft/entity/Entity.fluidHeight", Cat.STATE_READ, "s");
        f("Lnet/minecraft/entity/Entity.touchingWater", Cat.STATE_READ, "s.touchingWater");
        f("Lnet/minecraft/entity/Entity.firstUpdate", Cat.STATE_READ, "s.firstUpdate");
        // FluidTags keys -> the SimWorld.FluidTag enum the scan dispatches on.
        f("Lnet/minecraft/registry/tag/FluidTags.WATER", Cat.MATH, "murat.simv2.sim.SimWorld.FluidTag.WATER");
        f("Lnet/minecraft/registry/tag/FluidTags.LAVA", Cat.MATH, "murat.simv2.sim.SimWorld.FluidTag.LAVA");

        // --- instanceof type tests (the simulated entity IS the local player) ---
        tt("Lnet/minecraft/entity/player/PlayerEntity", "true");  // sim entity is a player
        tt("Lnet/minecraft/entity/Flutterer", "false");           // not a bee/allay -> normal air drag (0.98F)
        tt("Lnet/minecraft/entity/vehicle/AbstractBoatEntity", "false"); // sim player is never a boat (vehicle branch dead)

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
