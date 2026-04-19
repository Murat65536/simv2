package murat.simv2.simulation.mirror.net.minecraft.block;

// Generated mirror stub for simulation closure.
public interface Portal {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public murat.simv2.simulation.mirror.net.minecraft.world.TeleportTarget createTeleportTarget(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0, murat.simv2.simulation.mirror.net.minecraft.entity.Entity p1, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p2);

    public int getPortalDelay(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0, murat.simv2.simulation.mirror.net.minecraft.entity.Entity p1);

    public murat.simv2.simulation.mirror.net.minecraft.block.Portal.Effect getPortalEffect();

    public static class Effect {
        public static murat.simv2.simulation.mirror.net.minecraft.block.Portal.Effect CONFUSION;
        public static murat.simv2.simulation.mirror.net.minecraft.block.Portal.Effect NONE;
        public static murat.simv2.simulation.mirror.net.minecraft.block.Portal.Effect[] field_52063;

        public Effect(java.lang.String p0, int p1) {
        }

        public int ordinal() {
            return 0;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.block.Portal.Effect valueOf(java.lang.String p0) {
            return null;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.block.Portal.Effect[] values() {
            return null;
        }

        public Effect() {
        }

    }

    // END GENERATED MIRROR NESTED STUBS
}
