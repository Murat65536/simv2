package murat.simv2.analysis;

import java.nio.file.Files;

public final class MovementFieldAnalyzer {
    private MovementFieldAnalyzer() {
    }

    public static void main(String[] args) throws Exception {
        AnalysisRunConfig config = AnalysisRunConfig.parse(args);
        Files.createDirectories(config.outputDir());

        runWala(config);

        System.out.println("\n=== Analysis complete ===");
    }

    private static void runWala(AnalysisRunConfig config) throws Exception {
        new WalaPipelineRunner().run(config);
    }
}
