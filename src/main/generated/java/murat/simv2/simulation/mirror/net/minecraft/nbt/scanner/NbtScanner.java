package murat.simv2.simulation.mirror.net.minecraft.nbt.scanner;

// Generated mirror stub for simulation closure.
public interface NbtScanner {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public murat.simv2.simulation.mirror.net.minecraft.nbt.scanner.NbtScanner.Result endNested();

    public murat.simv2.simulation.mirror.net.minecraft.nbt.scanner.NbtScanner.NestedResult startListItem(murat.simv2.simulation.mirror.net.minecraft.nbt.NbtType p0, int p1);

    public murat.simv2.simulation.mirror.net.minecraft.nbt.scanner.NbtScanner.NestedResult startSubNbt(murat.simv2.simulation.mirror.net.minecraft.nbt.NbtType p0, java.lang.String p1);

    public murat.simv2.simulation.mirror.net.minecraft.nbt.scanner.NbtScanner.Result start(murat.simv2.simulation.mirror.net.minecraft.nbt.NbtType p0);

    public murat.simv2.simulation.mirror.net.minecraft.nbt.scanner.NbtScanner.Result visitByteArray(byte[] p0);

    public murat.simv2.simulation.mirror.net.minecraft.nbt.scanner.NbtScanner.Result visitByte(byte p0);

    public murat.simv2.simulation.mirror.net.minecraft.nbt.scanner.NbtScanner.Result visitDouble(double p0);

    public murat.simv2.simulation.mirror.net.minecraft.nbt.scanner.NbtScanner.Result visitEnd();

    public murat.simv2.simulation.mirror.net.minecraft.nbt.scanner.NbtScanner.Result visitFloat(float p0);

    public murat.simv2.simulation.mirror.net.minecraft.nbt.scanner.NbtScanner.Result visitIntArray(int[] p0);

    public murat.simv2.simulation.mirror.net.minecraft.nbt.scanner.NbtScanner.Result visitInt(int p0);

    public murat.simv2.simulation.mirror.net.minecraft.nbt.scanner.NbtScanner.Result visitListMeta(murat.simv2.simulation.mirror.net.minecraft.nbt.NbtType p0, int p1);

    public murat.simv2.simulation.mirror.net.minecraft.nbt.scanner.NbtScanner.Result visitLongArray(long[] p0);

    public murat.simv2.simulation.mirror.net.minecraft.nbt.scanner.NbtScanner.Result visitLong(long p0);

    public murat.simv2.simulation.mirror.net.minecraft.nbt.scanner.NbtScanner.Result visitShort(short p0);

    public murat.simv2.simulation.mirror.net.minecraft.nbt.scanner.NbtScanner.Result visitString(java.lang.String p0);

    public murat.simv2.simulation.mirror.net.minecraft.nbt.scanner.NbtScanner.NestedResult visitSubNbtType(murat.simv2.simulation.mirror.net.minecraft.nbt.NbtType p0);

    public static class NestedResult {
        public static murat.simv2.simulation.mirror.net.minecraft.nbt.scanner.NbtScanner.NestedResult BREAK;
        public static murat.simv2.simulation.mirror.net.minecraft.nbt.scanner.NbtScanner.NestedResult ENTER;
        public static murat.simv2.simulation.mirror.net.minecraft.nbt.scanner.NbtScanner.NestedResult HALT;
        public static murat.simv2.simulation.mirror.net.minecraft.nbt.scanner.NbtScanner.NestedResult SKIP;
        public static murat.simv2.simulation.mirror.net.minecraft.nbt.scanner.NbtScanner.NestedResult[] field_36252;

        public NestedResult(java.lang.String p0, int p1) {
        }

        public int ordinal() {
            return 0;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.nbt.scanner.NbtScanner.NestedResult valueOf(java.lang.String p0) {
            return null;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.nbt.scanner.NbtScanner.NestedResult[] values() {
            return null;
        }

        public NestedResult() {
        }

    }

    public static class Result {
        public static murat.simv2.simulation.mirror.net.minecraft.nbt.scanner.NbtScanner.Result BREAK;
        public static murat.simv2.simulation.mirror.net.minecraft.nbt.scanner.NbtScanner.Result CONTINUE;
        public static murat.simv2.simulation.mirror.net.minecraft.nbt.scanner.NbtScanner.Result HALT;
        public static murat.simv2.simulation.mirror.net.minecraft.nbt.scanner.NbtScanner.Result[] field_36256;

        public Result(java.lang.String p0, int p1) {
        }

        public int ordinal() {
            return 0;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.nbt.scanner.NbtScanner.Result valueOf(java.lang.String p0) {
            return null;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.nbt.scanner.NbtScanner.Result[] values() {
            return null;
        }

        public Result() {
        }

    }

    // END GENERATED MIRROR NESTED STUBS
}
