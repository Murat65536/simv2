package murat.simv2.simulation.mirror.net.minecraft.util;

// Generated mirror stub for simulation closure.
public interface ActionResult {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static final murat.simv2.simulation.mirror.net.minecraft.util.ActionResult.Success CONSUME = null;
    public static final murat.simv2.simulation.mirror.net.minecraft.util.ActionResult.Fail FAIL = null;
    public static final murat.simv2.simulation.mirror.net.minecraft.util.ActionResult.Pass PASS = null;
    public static final murat.simv2.simulation.mirror.net.minecraft.util.ActionResult.PassToDefaultBlockAction PASS_TO_DEFAULT_BLOCK_ACTION = null;
    public static final murat.simv2.simulation.mirror.net.minecraft.util.ActionResult.Success SUCCESS = null;
    public static final murat.simv2.simulation.mirror.net.minecraft.util.ActionResult.Success SUCCESS_SERVER = null;

    public boolean isAccepted();

    public static class Fail implements murat.simv2.simulation.mirror.net.minecraft.util.ActionResult {
        public Fail() {
        }

        public boolean equals(java.lang.Object p0) {
            return false;
        }

        public int hashCode() {
            return 0;
        }

        public boolean isAccepted() {
            return false;
        }

        public java.lang.String toString() {
            return null;
        }

    }

    public static class ItemContext {
        public static murat.simv2.simulation.mirror.net.minecraft.util.ActionResult.ItemContext KEEP_HAND_STACK;
        public static murat.simv2.simulation.mirror.net.minecraft.util.ActionResult.ItemContext KEEP_HAND_STACK_NO_INCREMENT_STAT;
        public boolean incrementStat;
        public murat.simv2.simulation.mirror.net.minecraft.item.ItemStack newHandStack;

        public ItemContext(boolean p0, murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p1) {
        }

        public boolean equals(java.lang.Object p0) {
            return false;
        }

        public int hashCode() {
            return 0;
        }

        public boolean incrementStat() {
            return false;
        }

        public murat.simv2.simulation.mirror.net.minecraft.item.ItemStack newHandStack() {
            return null;
        }

        public java.lang.String toString() {
            return null;
        }

        public ItemContext() {
        }

    }

    public static class Pass implements murat.simv2.simulation.mirror.net.minecraft.util.ActionResult {
        public Pass() {
        }

        public boolean equals(java.lang.Object p0) {
            return false;
        }

        public int hashCode() {
            return 0;
        }

        public boolean isAccepted() {
            return false;
        }

        public java.lang.String toString() {
            return null;
        }

    }

    public static class PassToDefaultBlockAction implements murat.simv2.simulation.mirror.net.minecraft.util.ActionResult {
        public PassToDefaultBlockAction() {
        }

        public boolean equals(java.lang.Object p0) {
            return false;
        }

        public int hashCode() {
            return 0;
        }

        public boolean isAccepted() {
            return false;
        }

        public java.lang.String toString() {
            return null;
        }

    }

    public static class Success implements murat.simv2.simulation.mirror.net.minecraft.util.ActionResult {
        public murat.simv2.simulation.mirror.net.minecraft.util.ActionResult.ItemContext itemContext;
        public murat.simv2.simulation.mirror.net.minecraft.util.ActionResult.SwingSource swingSource;

        public Success(murat.simv2.simulation.mirror.net.minecraft.util.ActionResult.SwingSource p0, murat.simv2.simulation.mirror.net.minecraft.util.ActionResult.ItemContext p1) {
        }

        public boolean equals(java.lang.Object p0) {
            return false;
        }

        public murat.simv2.simulation.mirror.net.minecraft.item.ItemStack getNewHandStack() {
            return null;
        }

        public int hashCode() {
            return 0;
        }

        public boolean isAccepted() {
            return false;
        }

        public murat.simv2.simulation.mirror.net.minecraft.util.ActionResult.ItemContext itemContext() {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.util.ActionResult.Success noIncrementStat() {
            return null;
        }

        public boolean shouldIncrementStat() {
            return false;
        }

        public murat.simv2.simulation.mirror.net.minecraft.util.ActionResult.SwingSource swingSource() {
            return null;
        }

        public java.lang.String toString() {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.util.ActionResult.Success withNewHandStack(murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p0) {
            return null;
        }

        public Success() {
        }

    }

    public static class SwingSource {
        public static murat.simv2.simulation.mirror.net.minecraft.util.ActionResult.SwingSource CLIENT;
        public static murat.simv2.simulation.mirror.net.minecraft.util.ActionResult.SwingSource NONE;
        public static murat.simv2.simulation.mirror.net.minecraft.util.ActionResult.SwingSource SERVER;
        public static murat.simv2.simulation.mirror.net.minecraft.util.ActionResult.SwingSource[] field_52429;

        public SwingSource(java.lang.String p0, int p1) {
        }

        public int ordinal() {
            return 0;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.util.ActionResult.SwingSource valueOf(java.lang.String p0) {
            return null;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.util.ActionResult.SwingSource[] values() {
            return null;
        }

        public SwingSource() {
        }

    }

    // END GENERATED MIRROR NESTED STUBS
}
