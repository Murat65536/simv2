package murat.simv2.analysis;

import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Entry point for the analysis CLI.
 * <p>
 * Three modes:
 * <ul>
 *   <li>{@code wala} — run the WALA backward slice from {@code Entity.pos}
 *       writes; emit the slice JSON, the mirror closure, the field manifest,
 *       the access widener, and {@code GeneratedSync.java}.</li>
 *   <li>{@code spoon} — read the WALA artifacts and build the mirror classes
 *       in {@code murat.simv2.simulation.mirror.net.minecraft.*}.</li>
 *   <li>{@code all} — both, in order.</li>
 * </ul>
 */
public final class MovementFieldAnalyzer {
    private MovementFieldAnalyzer() {
    }

    public static void main(String[] args) throws Exception {
        AnalysisRunConfig config = AnalysisRunConfig.parse(args);
        Files.createDirectories(config.outputDir());

        switch (config.mode()) {
            case WALA -> runWala(config);
            case SPOON -> runSpoon(config);
            case ALL -> {
                runWala(config);
                runSpoon(config);
            }
        }
        System.out.println("\n=== Analysis complete (" + config.mode() + ") ===");
    }

    private static void runWala(AnalysisRunConfig config) throws Exception {
        new WalaPipelineRunner().run(config);
    }

    private static void runSpoon(AnalysisRunConfig config) throws Exception {
        Path sourcesJar = config.sourcesJar();
        if (sourcesJar == null) {
            throw new IllegalStateException("Spoon phase requires -PsourcesJar=<path>.");
        }
        if (!Files.exists(sourcesJar)) {
            throw new IllegalStateException("Sources jar not found: " + sourcesJar);
        }
        AnalysisArtifacts.requireWalaArtifacts(config.outputDir());
        new MirrorBuilder().run(config);
    }
}
