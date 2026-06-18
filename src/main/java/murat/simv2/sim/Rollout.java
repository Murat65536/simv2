package murat.simv2.sim;

import java.util.ArrayList;
import java.util.List;

/**
 * Drives the standalone movement sim forward over many ticks, and fans rollouts out in parallel —
 * the actual "simulate the player hundreds of times from the current position" product.
 *
 * <p>A rollout NEVER touches the real player or world: it runs on a private {@link SimPlayerState}
 * copy of the seed against a frozen {@link WorldSnapshot}. The snapshot is captured ONCE (a big
 * region around the seed — see Captures.captureWorld(world, seed, radius...)) and shared read-only,
 * so {@link #runMany} parallelises safely: {@code copy()} is independent per rollout, the snapshot
 * (incl. its grid index) is immutable, and the generated physics + collision are pure.
 *
 * <p>HONESTY GUARANTEES:
 * <ul>
 *   <li>Out-of-scope seeds (water/lava/elytra/climb/vehicle/flying) are REFUSED, not silently
 *       simulated with on-land physics — see {@link SimPlayerState#inScope}.
 *   <li>If a rollout would leave the captured region (where block data is complete), it STOPS and
 *       flags {@link Result#escapedCapture()} rather than colliding against missing blocks. Capture
 *       a larger region (bigger radius) if your rollouts escape too early.
 * </ul>
 *
 * <p>KNOWN SEED-FIDELITY GAP: {@code LivingEntity.jumpingCooldown} is private with no accessor, so
 * the seed's {@code jumpingCooldown} defaults to 0 (see {@link SimPlayerState}). A rollout seeded
 * from a player who jumped within the last ~10 ticks may therefore fire one extra jump in its first
 * few ticks; it self-corrects after the first sim jump. Add a mixin accessor + capture if exact
 * seed cooldown ever matters.
 */
public final class Rollout {
    private Rollout() {
    }

    /** Supplies the raw {@link PlayerInput} for each tick (may read the live in-rollout state). */
    @FunctionalInterface
    public interface InputPolicy {
        PlayerInput at(int tick, SimPlayerState state);
    }

    /**
     * One rollout's outcome. {@code trajectory} is the position after each simulated tick (empty for
     * {@link #runMany}, which records only the final state to stay memory-light at scale).
     * {@code ticksRun} ≤ the requested ticks; {@code escapedCapture} is true iff it stopped early
     * because it reached the edge of the captured region.
     */
    public record Result(SimPlayerState finalState, List<Vec3> trajectory, int ticksRun,
                         boolean escapedCapture) {
    }

    /** A policy that issues the same raw input every tick (e.g. hold forward, or stand still). */
    public static InputPolicy constant(PlayerInput input) {
        return (tick, state) -> input;
    }

    /**
     * Run a single rollout from {@code seed} for up to {@code ticks} ticks, recording the trajectory.
     * The seed is copied; the caller's state is never mutated.
     *
     * @throws IllegalArgumentException if the seed is out of the ported physics scope.
     */
    public static Result run(SimPlayerState seed, SimWorld world, InputPolicy inputs, int ticks) {
        return runInternal(seed, world, inputs, ticks, true);
    }

    /**
     * Fan out many rollouts (typically with different input policies) in parallel against the one
     * frozen snapshot, returning each final state. Out-of-scope seeds throw before any work starts.
     */
    public static List<Result> runMany(List<InputPolicy> policies, SimPlayerState seed,
                                       SimWorld world, int ticks) {
        requireInScope(seed);
        return policies.parallelStream()
            .map(p -> runInternal(seed, world, p, ticks, false))
            .toList();
    }

    private static Result runInternal(SimPlayerState seed, SimWorld world, InputPolicy inputs,
                                      int ticks, boolean recordTrajectory) {
        requireInScope(seed);
        SimPlayerState s = seed.copy();
        List<Vec3> trajectory = recordTrajectory ? new ArrayList<>(ticks) : List.of();
        int t = 0;
        for (; t < ticks; t++) {
            // Stop honestly before a tick whose collision query could leave the captured region. The
            // margin is a guaranteed UPPER BOUND on this tick's displacement — it accounts for the
            // input the step is about to ADD to the velocity (and a possible jump + step-up probe),
            // not just the current velocity — so a passing covers() proves the query has complete data.
            if (!world.covers(expand(s.boundingBox(), maxTickDisplacement(s, world)))) {
                break;
            }
            PlayerInput in = inputs.at(t, s);
            MovementSim.step(s, in, world);
            if (recordTrajectory) {
                trajectory.add(s.pos);
            }
        }
        return new Result(s, trajectory, t, t < ticks);
    }

    /**
     * A guaranteed upper bound on how far the player box can extend in one tick (per axis), so the
     * escape check brackets the real collision query (which runs on the POST-input velocity). The
     * input adds at most {@code speed} to a component (input magnitude ≤ 1, scaled by speed); a jump
     * adds ≤ 0.42 (Y) + 0.2 (sprint-jump horizontal); the step-up probe stretches Y by stepHeight.
     * Generous on purpose — over-estimating only stops a rollout slightly early, never reads missing
     * blocks. The 4.0 floor covers ordinary play (terminal fall ≈ 3.92/tick).
     */
    private static double maxTickDisplacement(SimPlayerState s, SimWorld world) {
        float slip = s.onGround ? SimWorldOps.slipperinessAt(s, world) : 1.0f;
        // The high-level step may raise movementSpeed to base*sprintMult this tick (sprint start), so
        // bound the on-ground input add by that, not just the current field value.
        double fieldBound = Math.max((double) s.movementSpeed,
            s.movementSpeedBase * MovementSim.SPRINT_SPEED_MULTIPLIER);
        double inputAdd = s.onGround
            ? fieldBound * (0.21600002 / ((double) slip * slip * slip))
            : Math.max(MovementSim.OFF_GROUND_SPRINT_SPEED, s.flySpeed * 2.0);
        double kick = 0.42 + 0.2 + Math.max(0.0, s.stepHeight); // jump Y + sprint-jump horiz + step probe
        return Math.max(4.0, maxAbs(s.velocity) + inputAdd + kick + 1.0E-6);
    }

    private static void requireInScope(SimPlayerState seed) {
        if (!seed.inScope) {
            throw new IllegalArgumentException(
                "seed state is outside the ported movement scope (water/lava/elytra/climb/vehicle/"
                    + "flying) — refusing to simulate it with on-land physics. See SimPlayerState.inScope.");
        }
    }

    private static double maxAbs(Vec3 v) {
        return Math.max(Math.abs(v.x()), Math.max(Math.abs(v.y()), Math.abs(v.z())));
    }

    private static AABB expand(AABB box, double m) {
        return new AABB(box.minX() - m, box.minY() - m, box.minZ() - m,
            box.maxX() + m, box.maxY() + m, box.maxZ() + m);
    }
}
