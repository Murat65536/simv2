package murat.simv2.simulation.mirror.net.minecraft.nbt;

// Generated mirror stub for simulation closure.
public interface NbtType<T> {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public void accept(java.io.DataInput p0, murat.simv2.simulation.mirror.net.minecraft.nbt.scanner.NbtScanner p1, murat.simv2.simulation.mirror.net.minecraft.nbt.NbtSizeTracker p2);

    public static murat.simv2.simulation.mirror.net.minecraft.nbt.NbtType createInvalid(int p0) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.nbt.scanner.NbtScanner.Result doAccept(java.io.DataInput p0, murat.simv2.simulation.mirror.net.minecraft.nbt.scanner.NbtScanner p1, murat.simv2.simulation.mirror.net.minecraft.nbt.NbtSizeTracker p2);

    public java.lang.String getCommandFeedbackName();

    public java.lang.String getCrashReportName();

    public murat.simv2.simulation.mirror.net.minecraft.nbt.NbtElement read(java.io.DataInput p0, murat.simv2.simulation.mirror.net.minecraft.nbt.NbtSizeTracker p1);

    public void skip(java.io.DataInput p0, int p1, murat.simv2.simulation.mirror.net.minecraft.nbt.NbtSizeTracker p2);

    public void skip(java.io.DataInput p0, murat.simv2.simulation.mirror.net.minecraft.nbt.NbtSizeTracker p1);

    public static interface OfFixedSize<T> extends murat.simv2.simulation.mirror.net.minecraft.nbt.NbtType {
        public void accept(java.io.DataInput p0, murat.simv2.simulation.mirror.net.minecraft.nbt.scanner.NbtScanner p1, murat.simv2.simulation.mirror.net.minecraft.nbt.NbtSizeTracker p2);

        public static murat.simv2.simulation.mirror.net.minecraft.nbt.NbtType createInvalid(int p0) {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.nbt.scanner.NbtScanner.Result doAccept(java.io.DataInput p0, murat.simv2.simulation.mirror.net.minecraft.nbt.scanner.NbtScanner p1, murat.simv2.simulation.mirror.net.minecraft.nbt.NbtSizeTracker p2);

        public java.lang.String getCommandFeedbackName();

        public java.lang.String getCrashReportName();

        public int getSizeInBytes();

        public murat.simv2.simulation.mirror.net.minecraft.nbt.NbtElement read(java.io.DataInput p0, murat.simv2.simulation.mirror.net.minecraft.nbt.NbtSizeTracker p1);

        public void skip(java.io.DataInput p0, int p1, murat.simv2.simulation.mirror.net.minecraft.nbt.NbtSizeTracker p2);

        public void skip(java.io.DataInput p0, murat.simv2.simulation.mirror.net.minecraft.nbt.NbtSizeTracker p1);

    }

    public static interface OfVariableSize<T> extends murat.simv2.simulation.mirror.net.minecraft.nbt.NbtType {
        public void accept(java.io.DataInput p0, murat.simv2.simulation.mirror.net.minecraft.nbt.scanner.NbtScanner p1, murat.simv2.simulation.mirror.net.minecraft.nbt.NbtSizeTracker p2);

        public static murat.simv2.simulation.mirror.net.minecraft.nbt.NbtType createInvalid(int p0) {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.nbt.scanner.NbtScanner.Result doAccept(java.io.DataInput p0, murat.simv2.simulation.mirror.net.minecraft.nbt.scanner.NbtScanner p1, murat.simv2.simulation.mirror.net.minecraft.nbt.NbtSizeTracker p2);

        public java.lang.String getCommandFeedbackName();

        public java.lang.String getCrashReportName();

        public murat.simv2.simulation.mirror.net.minecraft.nbt.NbtElement read(java.io.DataInput p0, murat.simv2.simulation.mirror.net.minecraft.nbt.NbtSizeTracker p1);

        public void skip(java.io.DataInput p0, int p1, murat.simv2.simulation.mirror.net.minecraft.nbt.NbtSizeTracker p2);

        public void skip(java.io.DataInput p0, murat.simv2.simulation.mirror.net.minecraft.nbt.NbtSizeTracker p1);

    }

    // END GENERATED MIRROR NESTED STUBS
}
