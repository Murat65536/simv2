package murat.simv2.analysis;

import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Parsed CLI configuration: where the inputs live, where artifacts go.
 *
 * <p>CLI: {@code <mcJar> <outputDir> [sourcesJar|-]}.
 * The third argument is the Minecraft sources JAR or a literal {@code "-"}
 * to indicate "no sources jar".
 */
public record AnalysisRunConfig(
    Path minecraftJar,
    Path outputDir,
    Path sourcesJar
) {
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

        return new AnalysisRunConfig(mcJar, outputDir, sourcesJar);
    }
}
