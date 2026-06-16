package murat.simv2.analysis;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Locale;

public record AnalysisRunConfig(
    Path minecraftJar,
    Path outputDir,
    Path sourcesJar,
    PhaseAMode phaseAMode,
    boolean skipClinit,
    long maxCgNodes
) {

    public enum PhaseAMode {

        ZEROCFA,

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
