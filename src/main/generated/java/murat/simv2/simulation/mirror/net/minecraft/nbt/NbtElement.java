package murat.simv2.simulation.mirror.net.minecraft.nbt;

// Generated mirror stub for simulation closure.
public interface NbtElement {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static final byte BYTE_ARRAY_TYPE = (byte) 0;
    public static final byte BYTE_TYPE = (byte) 0;
    public static final byte COMPOUND_TYPE = (byte) 0;
    public static final byte DOUBLE_TYPE = (byte) 0;
    public static final byte END_TYPE = (byte) 0;
    public static final byte FLOAT_TYPE = (byte) 0;
    public static final byte INT_ARRAY_TYPE = (byte) 0;
    public static final byte INT_TYPE = (byte) 0;
    public static final byte LIST_TYPE = (byte) 0;
    public static final byte LONG_ARRAY_TYPE = (byte) 0;
    public static final byte LONG_TYPE = (byte) 0;
    public static final int MAX_DEPTH = 0;
    public static final byte SHORT_TYPE = (byte) 0;
    public static final byte STRING_TYPE = (byte) 0;
    public static final int field_33246 = 0;
    public static final int field_33247 = 0;
    public static final int field_33248 = 0;
    public static final int field_33249 = 0;

    public void accept(murat.simv2.simulation.mirror.net.minecraft.nbt.scanner.NbtScanner p0);

    public void accept(murat.simv2.simulation.mirror.net.minecraft.nbt.visitor.NbtElementVisitor p0);

    public java.util.Optional asBoolean();

    public java.util.Optional asByteArray();

    public java.util.Optional asByte();

    public java.util.Optional asCompound();

    public java.util.Optional asDouble();

    public java.util.Optional asFloat();

    public java.util.Optional asIntArray();

    public java.util.Optional asInt();

    public java.util.Optional asLongArray();

    public java.util.Optional asLong();

    public java.util.Optional asNbtList();

    public java.util.Optional asNumber();

    public java.util.Optional asShort();

    public java.util.Optional asString();

    public murat.simv2.simulation.mirror.net.minecraft.nbt.NbtElement copy();

    public murat.simv2.simulation.mirror.net.minecraft.nbt.scanner.NbtScanner.Result doAccept(murat.simv2.simulation.mirror.net.minecraft.nbt.scanner.NbtScanner p0);

    public murat.simv2.simulation.mirror.net.minecraft.nbt.NbtType getNbtType();

    public int getSizeInBytes();

    public byte getType();

    public java.lang.String toString();

    public void write(java.io.DataOutput p0);

    // END GENERATED MIRROR NESTED STUBS
}
