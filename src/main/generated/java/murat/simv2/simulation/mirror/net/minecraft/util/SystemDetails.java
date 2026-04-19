package murat.simv2.simulation.mirror.net.minecraft.util;

// Generated mirror stub for simulation closure.
public class SystemDetails extends java.lang.Object {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static long GIGA;
    public static java.lang.String JAVA_VERSION;
    public static java.lang.String JVM_VERSION;
    public static org.slf4j.Logger LOGGER;
    public static long MEBI;
    public static java.lang.String OPERATING_SYSTEM;
    public java.util.Map<java.lang.String, java.lang.String> sections;

    public SystemDetails() {
    }

    public void addGlobalMemoryGroup(oshi.hardware.GlobalMemory p0) {
    }

    public void addGraphicsCardGroup(java.util.List p0) {
    }

    public void addHardwareGroup(oshi.SystemInfo p0) {
    }

    public void addPhysicalMemoryGroup(java.util.List p0) {
    }

    public void addProcessorGroup(oshi.hardware.CentralProcessor p0) {
    }

    public void addSection(java.lang.String p0, java.lang.String p1) {
    }

    public void addSection(java.lang.String p0, java.util.function.Supplier p1) {
    }

    public void addStorageGroup() {
    }

    public void addStorageSection(java.lang.String p0) {
    }

    public void addStorageSection(java.lang.String p0, java.util.function.Supplier p1) {
    }

    public void addVirtualMemoryGroup(oshi.hardware.VirtualMemory p0) {
    }

    public java.lang.String collect() {
        return null;
    }

    public static float toMebibytes(long p0) {
        return 0.0F;
    }

    public void tryAddGroup(java.lang.String p0, java.lang.Runnable p1) {
    }

    public void writeTo(java.lang.StringBuilder p0) {
    }

    // END GENERATED MIRROR NESTED STUBS
}
