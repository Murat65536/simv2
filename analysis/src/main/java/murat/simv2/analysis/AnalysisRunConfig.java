package murat.simv2.analysis;

import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Parsed CLI configuration: where the inputs live, where artifacts go,
 * and which mode to run.
 *
 * <p>CLI: {@code <mcJar> <outputDir> <sourcesJar|-> [mode]}.
 * The third argument is the Minecraft sources JAR or a literal {@code "-"}
 * to indicate "no sources jar" (legal only for {@link AnalysisMode#WALA}).
 */
public record AnalysisRunConfig(
    Path minecraftJar,
    Path outputDir,
    Path sourcesJar,
    AnalysisMode mode
) {
    public static AnalysisRunConfig parse(String[] args) {
        if (args.length < 3) {
            throw new IllegalArgumentException(
                "Usage: MovementFieldAnalyzer <mcJar> <outputDir> <sourcesJar|-> [mode: all|wala|spoon]");
        }
        Path mcJar = Path.of(args[0]);
        Path outputDir = Path.of(args[1]);
        Path sourcesJar = args[2].equals("-") || args[2].isBlank() ? null : Path.of(args[2]);

        AnalysisMode mode = AnalysisMode.ALL;
        if (args.length >= 4 && !args[3].isBlank()) {
            mode = AnalysisMode.parse(args[3]);
        }

        if (!Files.exists(mcJar)) {
            throw new IllegalStateException("Minecraft jar not found: " + mcJar);
        }
        if ((mode == AnalysisMode.SPOON || mode == AnalysisMode.ALL) && sourcesJar == null) {
            throw new IllegalArgumentException(
                "Mode '" + mode + "' requires a sources jar. Pass -PsourcesJar=<path>.");
        }

        return new AnalysisRunConfig(mcJar, outputDir, sourcesJar, mode);
    }
}
