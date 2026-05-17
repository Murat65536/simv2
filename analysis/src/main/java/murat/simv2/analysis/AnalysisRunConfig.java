package murat.simv2.analysis;

import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Parsed CLI configuration: the Minecraft merged jar to analyse and where the
 * artifacts go.
 *
 * <p>CLI: {@code <mcJar> <outputDir>}. Any further arguments are ignored
 * (the Gradle task may pass extras).
 */
public record AnalysisRunConfig(
    Path minecraftJar,
    Path outputDir
) {
    public static AnalysisRunConfig parse(String[] args) {
        if (args.length < 2) {
            throw new IllegalArgumentException(
                "Usage: SinkGateAnalyzer <mcJar> <outputDir>");
        }
        Path mcJar = Path.of(args[0]);
        Path outputDir = Path.of(args[1]);
        if (!Files.exists(mcJar)) {
            throw new IllegalStateException("Minecraft jar not found: " + mcJar);
        }
        return new AnalysisRunConfig(mcJar, outputDir);
    }
}
