package murat.simv2.sim;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import murat.simv2.sim.gen.GeneratedMovement;
import org.junit.jupiter.api.Test;

/**
 * Bit-exact differential gate for the transpiled fluid family. Asserts the GENERATED
 * {@link GeneratedMovement#updateWaterState}/{@link GeneratedMovement#isInLava} reproduce the
 * VALIDATED hand-port (lifted verbatim below as the golden oracle) across a grid of player states
 * and worlds — same velocity push, fluid depths, touching/lava flags, and fall-distance reset, to
 * the last bit (compared on {@code doubleToLongBits}). This is the gate that let the hand-port
 * {@code Environment} logic be retired: the generated code is the production path, and this test
 * pins it to the reference it replaced so an MC-version re-run of the pipeline cannot silently drift.
 *
 * <p>The reference here is TEST code (an oracle), not production movement logic — exactly the
 * golden-vector role the zero-hardcode plan reserves for hand-written physics.
 */
class FluidGenerationDiffTest {

    @Test
    void generatedFluidMatchesReferenceAcrossGrid() {
        List<WorldSnapshot> worlds = worlds();
        int cases = 0;
        for (double[] pos : positions()) {
            for (double[] vel : velocities()) {
                for (boolean flying : new boolean[] {false, true}) {
                    for (boolean firstUpdate : new boolean[] {false, true}) {
                        for (WorldSnapshot world : worlds) {
                            SimPlayerState base = state(pos, vel, flying, firstUpdate);

                            SimPlayerState ref = base.copy();
                            boolean refTouching = refUpdateWaterState(ref, world);
                            boolean refLava = refIsInLava(ref);

                            SimPlayerState gen = base.copy();
                            boolean genTouching = GeneratedMovement.updateWaterState(gen, world);
                            boolean genLava = GeneratedMovement.isInLava(gen);

                            String w = "case[pos=" + pos[0] + "," + pos[1] + "," + pos[2]
                                + " vel=" + vel[0] + "," + vel[1] + "," + vel[2]
                                + " flying=" + flying + " firstUpdate=" + firstUpdate + "]";
                            assertEquals(refTouching, genTouching, w + " touching(return)");
                            assertEquals(refLava, genLava, w + " isInLava");
                            assertEquals(ref.touchingWater, gen.touchingWater, w + " touchingWater");
                            assertBits(ref.fluidHeightWater, gen.fluidHeightWater, w + " fluidHeightWater");
                            assertBits(ref.fluidHeightLava, gen.fluidHeightLava, w + " fluidHeightLava");
                            assertBits(ref.fallDistance, gen.fallDistance, w + " fallDistance");
                            assertBits(ref.velocity.x(), gen.velocity.x(), w + " velocity.x");
                            assertBits(ref.velocity.y(), gen.velocity.y(), w + " velocity.y");
                            assertBits(ref.velocity.z(), gen.velocity.z(), w + " velocity.z");
                            cases++;
                        }
                    }
                }
            }
        }
        // Sanity: the grid is actually large (not silently empty).
        org.junit.jupiter.api.Assertions.assertTrue(cases >= 500, "grid too small: " + cases);
    }

    private static void assertBits(double expected, double actual, String msg) {
        assertEquals(Double.doubleToLongBits(expected), Double.doubleToLongBits(actual),
            msg + " (expected=" + expected + " actual=" + actual + ")");
    }

    private static SimPlayerState state(double[] pos, double[] vel, boolean flying, boolean firstUpdate) {
        SimPlayerState s = new SimPlayerState();
        s.pos = new Vec3(pos[0], pos[1], pos[2]);
        s.velocity = new Vec3(vel[0], vel[1], vel[2]);
        s.flying = flying;
        s.firstUpdate = firstUpdate;
        s.fallDistance = 5.0; // non-zero so the water-touch reset is observable
        return s;
    }

    private static List<double[]> positions() {
        return List.of(
            new double[] {0.5, 64.0, 0.5},   // centred, scans one column (x=0)
            new double[] {0.9, 64.0, 0.9},   // straddles two columns in x and z
            new double[] {0.5, 64.4, 0.5},   // raised feet -> partial bottom cell
            new double[] {0.0, 64.0, 0.0},   // on a cell boundary
            new double[] {0.5, 63.7, 0.5});  // feet below the integer line
    }

    private static List<double[]> velocities() {
        return List.of(
            new double[] {0.0, 0.0, 0.0},       // still -> the min-flow nudge branch can fire
            new double[] {0.1, 0.0, 0.0},       // above the still-velocity epsilon in x
            new double[] {0.0, 0.0, 0.1},       // above epsilon in z
            new double[] {0.001, -0.2, 0.001},  // below epsilon both -> min-flow branch
            new double[] {-0.4, 0.3, 0.25});    // larger, both signs
    }

    private static List<WorldSnapshot> worlds() {
        List<WorldSnapshot> out = new ArrayList<>();
        out.add(new WorldSnapshot.Builder().build()); // no fluid
        // Deep water over the 2x2x2 cell footprint at all candidate positions.
        out.add(fill(new WorldSnapshot.Builder(), false, 1.0f, 0.0, 0.0).build());
        out.add(fill(new WorldSnapshot.Builder(), false, 0.5f, 0.0, 0.0).build());
        out.add(fill(new WorldSnapshot.Builder(), false, 0.3f, 0.0, 0.0).build()); // depth < 0.4 scale
        out.add(fill(new WorldSnapshot.Builder(), false, 0.1f, 0.0, 0.0).build());
        // Flowing water (various directions) -> exercises the averaged, depth-scaled push.
        out.add(fill(new WorldSnapshot.Builder(), false, 1.0f, 0.14, 0.0).build());
        out.add(fill(new WorldSnapshot.Builder(), false, 0.3f, 0.0, -0.2).build());
        out.add(fill(new WorldSnapshot.Builder(), false, 0.6f, 0.05, 0.05).build());
        // Lava, overworld and ultrawarm (different push speed).
        out.add(fill(new WorldSnapshot.Builder(), true, 1.0f, 0.0, 0.0).build());
        out.add(fill(new WorldSnapshot.Builder().ultrawarm(true), true, 1.0f, 0.0, 0.0).build());
        out.add(fill(new WorldSnapshot.Builder(), true, 0.3f, 0.0, 0.0).build());
        // Mixed: water at the feet column, lava in a neighbour column (multi-cell, two tags).
        WorldSnapshot.Builder mix = new WorldSnapshot.Builder();
        for (int y = 62; y <= 66; y++) {
            mix.water(0, y, 0, 1.0f, 0.1, 0.0);
            mix.lava(1, y, 1, 1.0f);
        }
        out.add(mix.build());
        // Shallow patch only at the lower cell (touching but not over the eye).
        WorldSnapshot.Builder patch = new WorldSnapshot.Builder();
        for (int y = 62; y <= 64; y++) {
            patch.water(0, y, 0, 0.5f);
            patch.water(1, y, 1, 0.5f);
        }
        out.add(patch.build());
        return out;
    }

    /** Fill a 2x3x2 block of fluid spanning every cell the test positions can scan (x,z in {0,1}, y 62..66). */
    private static WorldSnapshot.Builder fill(WorldSnapshot.Builder b, boolean lava, float height,
                                              double flowX, double flowZ) {
        for (int x = 0; x <= 1; x++) {
            for (int z = 0; z <= 1; z++) {
                for (int y = 62; y <= 66; y++) {
                    if (lava) {
                        b.lava(x, y, z, height);
                    } else {
                        b.water(x, y, z, height, flowX, flowZ);
                    }
                }
            }
        }
        return b;
    }

    // ===== Golden oracle: the validated hand-port, verbatim from the retired Environment logic. =====
    // MC 1.21.5 Entity.updateWaterState -> checkWaterState -> updateMovementInFluid (+ isInLava). Kept
    // here as TEST-only reference physics so the generated production path is pinned to it bit-for-bit.

    private static final double WATER_PUSH = 0.014;
    private static final double LAVA_PUSH_OVERWORLD = 0.0023333333333333335;
    private static final double LAVA_PUSH_ULTRAWARM = 0.007;

    private static boolean refUpdateWaterState(SimPlayerState s, SimWorld world) {
        s.fluidHeightWater = 0.0;
        s.fluidHeightLava = 0.0;
        boolean touchingWater = refCheckWaterState(s, world);
        double lavaPush = world.isUltrawarm() ? LAVA_PUSH_ULTRAWARM : LAVA_PUSH_OVERWORLD;
        boolean lava = refUpdateMovementInFluid(s, world, SimWorld.FluidTag.LAVA, lavaPush);
        return touchingWater || lava;
    }

    private static boolean refCheckWaterState(SimPlayerState s, SimWorld world) {
        if (refUpdateMovementInFluid(s, world, SimWorld.FluidTag.WATER, WATER_PUSH)) {
            s.fallDistance = 0.0;
            s.touchingWater = true;
        } else {
            s.touchingWater = false;
        }
        return s.touchingWater;
    }

    private static boolean refUpdateMovementInFluid(SimPlayerState s, SimWorld world,
                                                    SimWorld.FluidTag tag, double speed) {
        AABB box = s.boundingBox().contract(0.001);
        int i = (int) Math.floor(box.minX());
        int j = (int) Math.ceil(box.maxX());
        int k = (int) Math.floor(box.minY());
        int l = (int) Math.ceil(box.maxY());
        int m = (int) Math.floor(box.minZ());
        int n = (int) Math.ceil(box.maxZ());
        double d = 0.0;
        boolean pushed = !s.flying;
        boolean touching = false;
        Vec3 flowSum = Vec3.ZERO;
        int count = 0;
        for (int p = i; p < j; p++) {
            for (int q = k; q < l; q++) {
                for (int r = m; r < n; r++) {
                    if (!world.isFluidInTag(p, q, r, tag)) {
                        continue;
                    }
                    double e = (double) ((float) q + world.fluidHeightAt(p, q, r));
                    if (e >= box.minY()) {
                        touching = true;
                        d = Math.max(e - box.minY(), d);
                        if (pushed) {
                            Vec3 flow = world.fluidFlowAt(p, q, r);
                            if (d < 0.4) {
                                flow = flow.scale(d);
                            }
                            flowSum = flowSum.add(flow);
                            count++;
                        }
                    }
                }
            }
        }
        if (flowSum.length() > 0.0) {
            if (count > 0) {
                flowSum = flowSum.scale(1.0 / (double) count);
            }
            flowSum = flowSum.scale(speed);
            Vec3 v = s.velocity;
            if (Math.abs(v.x()) < 0.003 && Math.abs(v.z()) < 0.003
                && flowSum.length() < 0.0045000000000000005) {
                flowSum = flowSum.normalize().scale(0.0045000000000000005);
            }
            s.velocity = s.velocity.add(flowSum);
        }
        if (tag == SimWorld.FluidTag.WATER) {
            s.fluidHeightWater = d;
        } else {
            s.fluidHeightLava = d;
        }
        return touching;
    }

    private static boolean refIsInLava(SimPlayerState s) {
        return !s.firstUpdate && s.fluidHeightLava > 0.0;
    }
}
