package murat.simv2.analysis;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class MovementFieldAnalyzer {
    private MovementFieldAnalyzer() {
    }

    public static void main(String[] args) throws Exception {
        AnalysisRunConfig config = AnalysisRunConfig.parse(args);
        if (config.mode() == AnalysisMode.SPOON_ONLY) {
            runSpoonOnly(config);
            System.out.println("\n=== Spoon phase complete ===");
            return;
        }
        if (config.mode() == AnalysisMode.ALL) {
            requireSourcesJarForSpoon(config, AnalysisMode.ALL);
        }

        WalaPipelineResult walaResult = new WalaPipelineRunner().run(config);
        if (config.mode() == AnalysisMode.WALA_ONLY) {
            System.out.println("\nWALA-only mode selected — skipping Spoon phase.");
            System.out.println("\n=== Analysis complete ===");
            return;
        }
        if (!walaResult.isSpoonReady()) {
            throw new IllegalStateException(
                "ALL mode requires Spoon prerequisites, but WALA did not produce usable slice lines. "
                    + walaResult.spoonReadinessDetail()
                    + " Action: rerun with inputs where 0-1-Container-CFA pointer analysis succeeds, "
                    + "or run with mode=wala for WALA-only output.");
        }
        runSpoonPhase(config, walaResult.classifiedFields(), walaResult.sliceLines(), walaResult.mirrorClosure());
        System.out.println("\n=== Analysis complete ===");
    }

    private static void runSpoonOnly(AnalysisRunConfig config) throws Exception {
        SpoonArtifacts artifacts = AnalysisArtifacts.loadForSpoon(config.outputDir());
        runSpoonPhase(config, artifacts.fields(), artifacts.sliceLines(), artifacts.mirrorClosure());
    }

    private static void runSpoonPhase(AnalysisRunConfig config,
                                      List<FieldResult> classified,
                                      Map<String, Map<String, Set<Integer>>> sliceLines,
                                      MirrorClosure mirrorClosure) throws Exception {
        Path sourcesJarPath = requireSourcesJarForSpoon(config, config.mode());
        Path slicedOutputDir = config.outputDir().resolve("java/murat/simv2/simulation/sliced");

        System.out.println("\nRunning Spoon source pruning...");
        SpoonSlicePruner pruner = new SpoonSlicePruner(
            sourcesJarPath,
            Path.of(config.minecraftJar()),
            config.extraSpoonClasspath(),
            sliceLines
        );
        pruner.pruneAndWrite(slicedOutputDir);

        try {
            MirrorClassEmitter mirrorEmitter = new MirrorClassEmitter(
                config.outputDir(),
                Path.of(config.minecraftJar())
            );
            mirrorEmitter.emit(mirrorClosure);
        } finally {
            deleteDirectoryIfExists(slicedOutputDir);
        }
    }

    private static Path requireSourcesJarForSpoon(AnalysisRunConfig config, AnalysisMode mode) {
        String sourcesJar = config.sourcesJar();
        String modeLabel = switch (mode) {
            case ALL -> "ALL mode";
            case SPOON_ONLY -> "SPOON_ONLY mode";
            case WALA_ONLY -> "Spoon phase";
        };
        if (sourcesJar.isBlank()) {
            throw new IllegalStateException(modeLabel
                + " requires a sources JAR. Pass -PsourcesJar=<path-to-sources.jar>.");
        }
        Path sourcesJarPath = Path.of(sourcesJar);
        if (!Files.exists(sourcesJarPath)) {
            throw new IllegalStateException(modeLabel + " requires an existing sources JAR, but it was not found: "
                + sourcesJarPath + ". Pass -PsourcesJar=<path-to-sources.jar>.");
        }
        return sourcesJarPath;
    }

    private static void deleteDirectoryIfExists(Path dir) throws Exception {
        if (dir == null || !Files.exists(dir)) {
            return;
        }
        try (var walk = Files.walk(dir)) {
            walk.sorted(Comparator.reverseOrder())
                .forEach(path -> {
                    try {
                        Files.deleteIfExists(path);
                    } catch (Exception ex) {
                        throw new RuntimeException("Failed to delete path: " + path, ex);
                    }
                });
        } catch (RuntimeException ex) {
            if (ex.getCause() instanceof Exception cause) {
                throw cause;
            }
            throw ex;
        }
    }

}
