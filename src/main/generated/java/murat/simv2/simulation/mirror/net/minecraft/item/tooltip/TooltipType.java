package murat.simv2.simulation.mirror.net.minecraft.item.tooltip;

// Generated mirror stub for simulation closure.
public interface TooltipType {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static final murat.simv2.simulation.mirror.net.minecraft.item.tooltip.TooltipType.Default ADVANCED = null;
    public static final murat.simv2.simulation.mirror.net.minecraft.item.tooltip.TooltipType.Default BASIC = null;

    public boolean isAdvanced();

    public boolean isCreative();

    public static class Default implements murat.simv2.simulation.mirror.net.minecraft.item.tooltip.TooltipType {
        public boolean advanced;
        public boolean creative;

        public Default(boolean p0, boolean p1) {
        }

        public boolean advanced() {
            return false;
        }

        public boolean creative() {
            return false;
        }

        public boolean equals(java.lang.Object p0) {
            return false;
        }

        public int hashCode() {
            return 0;
        }

        public boolean isAdvanced() {
            return false;
        }

        public boolean isCreative() {
            return false;
        }

        public java.lang.String toString() {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.item.tooltip.TooltipType.Default withCreative() {
            return null;
        }

        public Default() {
        }

    }

    // END GENERATED MIRROR NESTED STUBS
}
