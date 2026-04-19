package murat.simv2.simulation.mirror.net.minecraft.entity.data;

// Generated mirror stub for simulation closure.
public class DataTracker extends java.lang.Object {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static murat.simv2.simulation.mirror.net.minecraft.util.collection.Class2IntMap CLASS_TO_LAST_ID;
    public static org.slf4j.Logger LOGGER;
    public static int MAX_DATA_VALUE_ID;
    public boolean dirty;
    public murat.simv2.simulation.mirror.net.minecraft.entity.data.DataTracker.Entry<?>[] entries;
    public murat.simv2.simulation.mirror.net.minecraft.entity.data.DataTracked trackedEntity;

    public DataTracker(murat.simv2.simulation.mirror.net.minecraft.entity.data.DataTracked p0, murat.simv2.simulation.mirror.net.minecraft.entity.data.DataTracker.Entry[] p1) {
    }

    public void copyToFrom(murat.simv2.simulation.mirror.net.minecraft.entity.data.DataTracker.Entry p0, murat.simv2.simulation.mirror.net.minecraft.entity.data.DataTracker.SerializedEntry p1) {
    }

    public java.util.List getChangedEntries() {
        return null;
    }

    public java.util.List getDirtyEntries() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.entity.data.DataTracker.Entry getEntry(murat.simv2.simulation.mirror.net.minecraft.entity.data.TrackedData p0) {
        return null;
    }

    public <T> murat.simv2.simulation.mirror.net.minecraft.entity.data.TrackedData<T> getData(murat.simv2.simulation.mirror.net.minecraft.entity.data.TrackedData<T> p0) {
        return null;
    }

    public <T> T get(murat.simv2.simulation.mirror.net.minecraft.entity.data.TrackedData<T> p0) {
        return null;
    }

    public boolean isDirty() {
        return false;
    }

    public static <T> murat.simv2.simulation.mirror.net.minecraft.entity.data.TrackedData<T> registerData(java.lang.Class p0, murat.simv2.simulation.mirror.net.minecraft.entity.data.TrackedDataHandler<T> p1) {
        return null;
    }

    public <T> void set(murat.simv2.simulation.mirror.net.minecraft.entity.data.TrackedData<T> p0, T p1) {
    }

    public <T> void set(murat.simv2.simulation.mirror.net.minecraft.entity.data.TrackedData<T> p0, T p1, boolean p2) {
    }

    public void writeUpdatedEntries(java.util.List p0) {
    }

    public DataTracker() {
    }

    public static class Builder extends java.lang.Object {
        public murat.simv2.simulation.mirror.net.minecraft.entity.data.DataTracked entity;
        public murat.simv2.simulation.mirror.net.minecraft.entity.data.DataTracker.Entry<?>[] entries;

        public Builder(java.lang.Object p0) {
        }

        public <T> murat.simv2.simulation.mirror.net.minecraft.entity.data.DataTracker.Builder add(murat.simv2.simulation.mirror.net.minecraft.entity.data.TrackedData<T> p0, T p1) {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.entity.data.DataTracker build() {
            return null;
        }

        public Builder() {
        }

    }

    public static class Entry<T> extends java.lang.Object {
        public murat.simv2.simulation.mirror.net.minecraft.entity.data.TrackedData<java.lang.Object> data;
        public boolean dirty;
        public java.lang.Object initialValue;
        public java.lang.Object value;

        public Entry(murat.simv2.simulation.mirror.net.minecraft.entity.data.TrackedData p0, java.lang.Object p1) {
        }

        public murat.simv2.simulation.mirror.net.minecraft.entity.data.TrackedData getData() {
            return null;
        }

        public java.lang.Object get() {
            return null;
        }

        public boolean isDirty() {
            return false;
        }

        public boolean isUnchanged() {
            return false;
        }

        public void setDirty(boolean p0) {
        }

        public void set(java.lang.Object p0) {
        }

        public murat.simv2.simulation.mirror.net.minecraft.entity.data.DataTracker.SerializedEntry toSerialized() {
            return null;
        }

        public Entry() {
        }

    }

    public static class SerializedEntry<T> {
        public murat.simv2.simulation.mirror.net.minecraft.entity.data.TrackedDataHandler<java.lang.Object> handler;
        public int id;
        public java.lang.Object value;

        public SerializedEntry(int p0, murat.simv2.simulation.mirror.net.minecraft.entity.data.TrackedDataHandler p1, java.lang.Object p2) {
        }

        public boolean equals(java.lang.Object p0) {
            return false;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.entity.data.DataTracker.SerializedEntry fromBuf(java.lang.Object p0, int p1) {
            return null;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.entity.data.DataTracker.SerializedEntry fromBuf(java.lang.Object p0, int p1, murat.simv2.simulation.mirror.net.minecraft.entity.data.TrackedDataHandler p2) {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.entity.data.TrackedDataHandler handler() {
            return null;
        }

        public int hashCode() {
            return 0;
        }

        public int id() {
            return 0;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.entity.data.DataTracker.SerializedEntry of(murat.simv2.simulation.mirror.net.minecraft.entity.data.TrackedData p0, java.lang.Object p1) {
            return null;
        }

        public java.lang.String toString() {
            return null;
        }

        public java.lang.Object value() {
            return null;
        }

        public void write(java.lang.Object p0) {
        }

        public SerializedEntry() {
        }

    }

    // END GENERATED MIRROR NESTED STUBS
}
