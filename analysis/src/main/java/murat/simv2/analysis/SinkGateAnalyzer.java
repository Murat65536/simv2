package murat.simv2.analysis;

import java.nio.file.Files;

/**
 * Entry point for the sink-gate analysis CLI.
 *
 * <p>Builds a call graph from {@code ClientPlayerEntity#tickMovement()} and
 * emits {@code chain.txt}, {@code sink-callsites.txt}, and the generated
 * gate Mixins ({@code sim-v2.gen.mixins.json} + {@code mixin/gen/*}). Run it
 * (the {@code :analysis:runWala} Gradle task) whenever the Minecraft version
 * changes to re-derive the gates.
 */
public final class SinkGateAnalyzer {
    private SinkGateAnalyzer() {
    }

    public static void main(String[] args) throws Exception {
        AnalysisRunConfig config = AnalysisRunConfig.parse(args);
        Files.createDirectories(config.outputDir());

        new WalaPipelineRunner().run(config);

        System.out.println("\n=== Analysis complete ===");
    }
}
