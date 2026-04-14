package murat.simv2.analysis;

import java.nio.file.Files;
import java.nio.file.Path;

record AnalysisRunConfig(
    String minecraftJar,
    Path outputDir,
    String sourcesJar,
    String extraSpoonClasspath,
    AnalysisMode mode
) {
    static AnalysisRunConfig parse(String[] args) throws Exception {
        if (args.length < 3) {
            System.err.println(
                "Usage: MovementFieldAnalyzer <minecraft-jar> <output-dir> <sources-jar> "
                    + "[extra-spoon-classpath] [mode: all|wala|spoon]");
            System.exit(1);
        }
        String minecraftJar = args[0];
        Path outputDir = Path.of(args[1]);
        String sourcesJar = args[2];
        String extraSpoonClasspath = args.length >= 4 ? args[3] : "";
        AnalysisMode mode = AnalysisMode.from(args.length >= 5 ? args[4] : "all");
        Files.createDirectories(outputDir);
        return new AnalysisRunConfig(
            minecraftJar,
            outputDir,
            sourcesJar,
            extraSpoonClasspath,
            mode
        );
    }
}
