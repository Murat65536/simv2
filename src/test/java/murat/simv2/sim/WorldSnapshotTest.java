package murat.simv2.sim;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import org.junit.jupiter.api.Test;

/**
 * The grid-indexed {@link WorldSnapshot#collisions} must return exactly the blockers a brute-force
 * linear scan would (the index is a perf optimization, not a behavior change), and {@link
 * WorldSnapshot#covers} must reflect the captured bounds so the rollout driver can stop honestly.
 */
class WorldSnapshotTest {

    @Test
    void gridMatchesBruteForceLinearScan() {
        Random rnd = new Random(31L);
        for (int world = 0; world < 50; world++) {
            List<AABB> blockers = new ArrayList<>();
            WorldSnapshot.Builder b = new WorldSnapshot.Builder();
            int n = 20 + rnd.nextInt(60);
            for (int i = 0; i < n; i++) {
                int x = rnd.nextInt(20) - 10;
                int y = rnd.nextInt(8) - 2;
                int z = rnd.nextInt(20) - 10;
                double h = rnd.nextBoolean() ? 1.0 : 0.5; // full block or slab
                AABB blk = new AABB(x, y, z, x + 1, y + h, z + 1);
                blockers.add(blk);
                b.addBlocker(blk);
            }
            WorldSnapshot snap = b.build();
            for (int q = 0; q < 200; q++) {
                double cx = rnd.nextDouble() * 24 - 12;
                double cy = rnd.nextDouble() * 12 - 4;
                double cz = rnd.nextDouble() * 24 - 12;
                AABB query = new AABB(cx, cy, cz,
                    cx + 0.2 + rnd.nextDouble() * 3,
                    cy + 0.2 + rnd.nextDouble() * 3,
                    cz + 0.2 + rnd.nextDouble() * 3);
                Set<AABB> brute = new HashSet<>();
                for (AABB blk : blockers) {
                    if (blk.intersects(query)) {
                        brute.add(blk);
                    }
                }
                Set<AABB> grid = new HashSet<>(snap.collisions(query));
                assertEquals(brute, grid, "world=" + world + " q=" + q + " query=" + query);
            }
        }
    }

    @Test
    void gridFindsThinAndExactBoundaryBlockers() {
        // Regression for the sub-epsilon bucketing bug: a thin (< 1e-7) blocker and exact-integer-
        // face blockers must still be indexed and returned, matching the linear scan / intersects().
        AABB thin = new AABB(3.0, 0, 0, 3.00000005, 1, 1); // X width 5e-8
        AABB unit = new AABB(5.0, 0, 0, 6.0, 1, 1);         // exact integer faces
        AABB slab = new AABB(0.0, 0, 0, 1.0, 0.5, 1.0);
        WorldSnapshot snap = new WorldSnapshot.Builder()
            .addBlocker(thin).addBlocker(unit).addBlocker(slab).build();

        assertTrue(snap.collisions(new AABB(2.5, 0.5, 0.5, 3.5000001, 0.6, 0.6)).contains(thin),
            "thin sub-epsilon blocker must be found");
        assertTrue(snap.collisions(new AABB(5.5, 0.5, 0.5, 5.6, 0.6, 0.6)).contains(unit),
            "unit blocker found");
        assertTrue(snap.collisions(new AABB(0.5, 0.2, 0.5, 0.6, 0.3, 0.6)).contains(slab),
            "slab found");
        assertFalse(snap.collisions(new AABB(6.0, 0.5, 0.5, 6.5, 0.6, 0.6)).contains(unit),
            "exclusive max face: a box starting at x=6.0 does not collide the [5,6] block");
    }

    @Test
    void coversReflectsCapturedBounds() {
        AABB region = new AABB(-10, -10, -10, 10, 10, 10);
        WorldSnapshot bounded = new WorldSnapshot.Builder().bounds(region).build();
        assertTrue(bounded.covers(new AABB(-1, -1, -1, 1, 1, 1)), "inside is covered");
        assertFalse(bounded.covers(new AABB(5, 5, 5, 11, 6, 6)), "poking past maxX is not covered");
        assertFalse(bounded.covers(new AABB(-11, 0, 0, -9, 1, 1)), "poking past minX is not covered");

        WorldSnapshot unbounded = new WorldSnapshot.Builder().build();
        assertTrue(unbounded.covers(new AABB(1e6, 1e6, 1e6, 1e6 + 1, 1e6 + 1, 1e6 + 1)),
            "no captured bounds -> covers everything (synthetic test world)");
    }
}
