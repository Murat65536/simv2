package murat.simv2.analysis;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Locale;

/**
 * Parsed CLI configuration: where the inputs live, where artifacts go, and
 * the analysis tuning flags.
 *
 * <p>CLI: {@code <mcJar> <outputDir> [sourcesJar|-]}.
 * The third argument is the Minecraft sources JAR or a literal {@code "-"}
 * to indicate "no sources jar".
 *
 * <p>Tuning flags arrive as system properties (plumbed from Gradle
 * {@code -P} properties by {@code analysis/build.gradle}):
 * <ul>
 *   <li>{@code analysis.phaseA} — {@code zerocfa} (default) or {@code cha}:
 *       which cheap call graph drives the Phase A scope pruning.</li>
 *   <li>{@code analysis.skipClinit} — skip static-initializer modeling in
 *       Phase B. Big cost cut but UNSOUND for registry-object dispatch
 *       (e.g. friction via {@code block.getVelocityMultiplier()}); off by
 *       default, validate the output if you turn it on.</li>
 *   <li>{@code analysis.maxCgNodes} — fail fast if the Phase B call graph
 *       exceeds this many nodes ({@code 0} = unlimited).</li>
 * </ul>
 */
public record AnalysisRunConfig(
    Path minecraftJar,
    Path outputDir,
    Path sourcesJar,
    PhaseAMode phaseAMode,
    boolean skipClinit,
    long maxCgNodes
) {
    /** Which cheap call graph drives the Phase A reachability pre-pass. */
    public enum PhaseAMode {
        /** 0-CFA propagation call graph — type-based instance keys, tightest closure. */
        ZEROCFA,
        /** Class-hierarchy-only call graph — no points-to at all, cheapest but coarsest. */
        CHA
    }

    public static AnalysisRunConfig parse(String[] args) {
        if (args.length < 2) {
            throw new IllegalArgumentException(
                "Usage: MovementFieldAnalyzer <mcJar> <outputDir> [sourcesJar|-]");
        }
        Path mcJar = Path.of(args[0]);
        Path outputDir = Path.of(args[1]);
        Path sourcesJar = (args.length >= 3 && !args[2].equals("-") && !args[2].isBlank()) ? Path.of(args[2]) : null;

        if (!Files.exists(mcJar)) {
            throw new IllegalStateException("Minecraft jar not found: " + mcJar);
        }

        return new AnalysisRunConfig(
            mcJar,
            outputDir,
            sourcesJar,
            parsePhaseAMode(System.getProperty("analysis.phaseA", "zerocfa")),
            Boolean.parseBoolean(System.getProperty("analysis.skipClinit", "false")),
            parseMaxCgNodes(System.getProperty("analysis.maxCgNodes", "0")));
    }

    private static PhaseAMode parsePhaseAMode(String value) {
        String normalized = value.trim().toLowerCase(Locale.ROOT);
        return switch (normalized) {
            case "", "zerocfa", "0cfa" -> PhaseAMode.ZEROCFA;
            case "cha" -> PhaseAMode.CHA;
            default -> throw new IllegalArgumentException(
                "Unknown analysis.phaseA value: '" + value + "' (expected zerocfa or cha)");
        };
    }

    private static long parseMaxCgNodes(String value) {
        long parsed = Long.parseLong(value.trim());
        if (parsed < 0) {
            throw new IllegalArgumentException("analysis.maxCgNodes must be >= 0, got " + parsed);
        }
        return parsed;
    }
}
