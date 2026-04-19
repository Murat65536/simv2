package murat.simv2.simulation.mirror.net.minecraft.text;

// Generated mirror stub for simulation closure.
public interface StringVisitable {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static final murat.simv2.simulation.mirror.net.minecraft.text.StringVisitable EMPTY = null;
    public static final java.util.Optional<murat.simv2.simulation.mirror.net.minecraft.util.Unit> TERMINATE_VISIT = null;

    public static murat.simv2.simulation.mirror.net.minecraft.text.StringVisitable concat(java.util.List p0) {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.text.StringVisitable concat(murat.simv2.simulation.mirror.net.minecraft.text.StringVisitable[] p0) {
        return null;
    }

    public java.lang.String getString();

    public static murat.simv2.simulation.mirror.net.minecraft.text.StringVisitable plain(java.lang.String p0) {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.text.StringVisitable styled(java.lang.String p0, murat.simv2.simulation.mirror.net.minecraft.text.Style p1) {
        return null;
    }

    public java.util.Optional visit(murat.simv2.simulation.mirror.net.minecraft.text.StringVisitable.StyledVisitor p0, murat.simv2.simulation.mirror.net.minecraft.text.Style p1);

    public java.util.Optional visit(murat.simv2.simulation.mirror.net.minecraft.text.StringVisitable.Visitor p0);

    public static interface StyledVisitor<T> {
        public java.util.Optional accept(murat.simv2.simulation.mirror.net.minecraft.text.Style p0, java.lang.String p1);

    }

    public static interface Visitor<T> {
        public java.util.Optional accept(java.lang.String p0);

    }

    // END GENERATED MIRROR NESTED STUBS
}
