package murat.simv2.analysis;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.function.LongSupplier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PrintingProgressMonitorTest {

    @Test
    void workedWithoutBeginTaskPrintsImmediateProgress() throws Exception {
        FakeClock clock = new FakeClock();
        String output = captureStdout(() -> {
            PrintingProgressMonitor monitor = new PrintingProgressMonitor(clock, 10_000L);
            monitor.worked(1);
        });

        assertTrue(output.contains("[progress] call graph construction: 1 units (0.0s)"));
    }

    @Test
    void workedUsesTimeHeartbeatForRateLimiting() throws Exception {
        FakeClock clock = new FakeClock();
        String output = captureStdout(() -> {
            PrintingProgressMonitor monitor = new PrintingProgressMonitor(clock, 10_000L);
            monitor.worked(1);
            monitor.worked(1);
            clock.advanceMillis(9_999);
            monitor.worked(1);
            clock.advanceMillis(1);
            monitor.worked(1);
        });

        int progressLineCount = countLinesContaining(output, "[progress]");
        assertEquals(2, progressLineCount);
        assertTrue(output.contains("4 units (10.0s)"));
    }

    private static String captureStdout(ThrowingRunnable action) throws Exception {
        PrintStream originalOut = System.out;
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        try (PrintStream capture = new PrintStream(buffer, true, StandardCharsets.UTF_8)) {
            System.setOut(capture);
            action.run();
            capture.flush();
            return buffer.toString(StandardCharsets.UTF_8);
        } finally {
            System.setOut(originalOut);
        }
    }

    private static int countLinesContaining(String text, String token) {
        String[] lines = text.split("\\R");
        int count = 0;
        for (String line : lines) {
            if (line.contains(token)) {
                count++;
            }
        }
        return count;
    }

    @FunctionalInterface
    private interface ThrowingRunnable {
        void run() throws Exception;
    }

    private static final class FakeClock implements LongSupplier {
        private long nowNanos = 0L;

        @Override
        public long getAsLong() {
            return nowNanos;
        }

        void advanceMillis(long millis) {
            nowNanos += millis * 1_000_000L;
        }
    }
}
