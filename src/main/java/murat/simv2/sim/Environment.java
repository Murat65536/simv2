package murat.simv2.sim;

import murat.simv2.sim.gen.GeneratedMovement;

/**
 * The per-tick environment-state backbone: the slice of {@code Entity.baseTick} that runs BEFORE
 * {@code travel} and maintains the fluid/swim flags the travel dispatcher branches on. Works against
 * both a frozen {@link WorldSnapshot} and a live adapter via the {@link SimWorld} seam.
 *
 * <p>The velocity-bearing fluid core ({@code updateWaterState → checkWaterState → updateMovementInFluid}
 * + {@code isInLava}) is GENERATED from the WALA IR (see {@link GeneratedMovement}); this class just
 * orchestrates it and adds the two flag-maintenance steps that fall OUTSIDE the velocity→position
 * dataflow slice the IR captures — {@code updateSubmergedInWaterState} and {@code updateSwimming} write
 * only {@code submergedInWater}/{@code swimming} (no velocity), so they are not in the captured IR yet
 * and remain hand-ported here until the WALA closure is extended to seed on them too.
 *
 * <p>SCOPE NOTE (Phase 2): the fluid scan uses the STANDING bounding box. The swimming pose (0.6×0.6,
 * eye 0.4) is not modeled yet — it is added in the water-physics phase that consumes {@code swimming}.
 * Until the dispatcher consumes these flags (Phase 3), {@code update} is exercised by unit tests only.
 */
public final class Environment {
    private Environment() {
    }

    /** Advance the player's environment state one tick (run before travel). */
    public static void update(SimPlayerState s, SimWorld world) {
        // Generated: fluid detection + depth + the averaged, depth-scaled flow push (resets
        // fallDistance on water-touch). firstUpdate is still as-of-last-tick here (MC clears it at the
        // end of baseTick), so checkWaterState's first-tick guard reads correctly.
        GeneratedMovement.updateWaterState(s, world);
        updateSubmergedInWaterState(s, world);
        updateSwimming(s, world);
        // (Entity.baseTick also does `if (isInLava()) fallDistance *= 0.5` — folded into the fluid
        //  phase alongside the fall-distance accrual in moveSelf, which isn't wired here yet.)
        s.firstUpdate = false;
    }

    /** True iff the player is in lava this tick (Entity.isInLava — generated). */
    public static boolean isInLava(SimPlayerState s) {
        return GeneratedMovement.isInLava(s);
    }

    /**
     * Entity.updateSubmergedInWaterState: {@code submergedInWater} reads the PREVIOUS tick's eye-probe
     * (one-tick lag, as in MC), then this tick's probe at the eye cell is recomputed.
     */
    private static void updateSubmergedInWaterState(SimPlayerState s, SimWorld world) {
        s.submergedInWater = s.submergedFluidWater;
        double eyeY = s.pos.y() + s.standingEyeHeight;
        int bx = (int) Math.floor(s.pos.x());
        int by = (int) Math.floor(eyeY);
        int bz = (int) Math.floor(s.pos.z());
        boolean water = world.isFluidInTag(bx, by, bz, SimWorld.FluidTag.WATER);
        double top = (double) ((float) by + world.fluidHeightAt(bx, by, bz));
        s.submergedFluidWater = water && top > eyeY;
    }

    /** Entity.updateSwimming (+ PlayerEntity override: flying => not swimming). */
    private static void updateSwimming(SimPlayerState s, SimWorld world) {
        if (s.flying) {
            s.swimming = false;
            return;
        }
        if (s.swimming) {
            s.swimming = s.sprinting && s.touchingWater; // && !hasVehicle (no vehicle in scope)
        } else {
            int bx = (int) Math.floor(s.pos.x());
            int by = (int) Math.floor(s.pos.y());
            int bz = (int) Math.floor(s.pos.z());
            s.swimming = s.sprinting && s.submergedInWater
                && world.isFluidInTag(bx, by, bz, SimWorld.FluidTag.WATER);
        }
    }
}
