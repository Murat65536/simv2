package murat.simv2.simulation.mirror.net.minecraft.util.crash;

// Generated mirror stub for simulation closure.
public class CrashReport {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static java.time.format.DateTimeFormatter DATE_TIME_FORMATTER;
    public static org.slf4j.Logger LOGGER;
    public java.lang.Throwable cause;
    public java.nio.file.Path file;
    public boolean hasStackTrace;
    public java.lang.String message;
    public java.util.List otherSections;
    public java.lang.StackTraceElement[] stackTrace;
    public java.lang.Object systemDetailsSection;

    public CrashReport(java.lang.String p0, java.lang.Throwable p1) {
    }

    public void addDetails(java.lang.StringBuilder p0) {
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.crash.CrashReportSection addElement(java.lang.String p0) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.crash.CrashReportSection addElement(java.lang.String p0, int p1) {
        return null;
    }

    public java.lang.String asString(java.lang.Object p0) {
        return null;
    }

    public java.lang.String asString(java.lang.Object p0, java.util.List p1) {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.util.crash.CrashReport create(java.lang.Throwable p0, java.lang.String p1) {
        return null;
    }

    public java.lang.String getCauseAsString() {
        return null;
    }

    public java.lang.Throwable getCause() {
        return null;
    }

    public java.nio.file.Path getFile() {
        return null;
    }

    public java.lang.String getMessage() {
        return null;
    }

    public java.lang.String getStackTrace() {
        return null;
    }

    public java.lang.Object getSystemDetailsSection() {
        return null;
    }

    public static void initCrashReport() {
    }

    public boolean writeToFile(java.nio.file.Path p0, java.lang.Object p1) {
        return false;
    }

    public boolean writeToFile(java.nio.file.Path p0, java.lang.Object p1, java.util.List p2) {
        return false;
    }

    public CrashReport() {
    }

    // END GENERATED MIRROR NESTED STUBS
}
