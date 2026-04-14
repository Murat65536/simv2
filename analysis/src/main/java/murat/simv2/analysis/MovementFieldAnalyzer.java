package murat.simv2.analysis;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ibm.wala.ipa.callgraph.*;
import com.ibm.wala.ipa.callgraph.cha.CHACallGraph;
import com.ibm.wala.ipa.callgraph.impl.DefaultEntrypoint;
import com.ibm.wala.ipa.callgraph.impl.Util;
import com.ibm.wala.ipa.callgraph.propagation.InstanceKey;
import com.ibm.wala.ipa.callgraph.propagation.PointerAnalysis;
import com.ibm.wala.ipa.cha.ClassHierarchyFactory;
import com.ibm.wala.ipa.cha.IClassHierarchy;
import com.ibm.wala.classLoader.IClass;
import com.ibm.wala.classLoader.IMethod;
import com.ibm.wala.types.ClassLoaderReference;
import com.ibm.wala.types.MethodReference;
import com.ibm.wala.types.Selector;
import com.ibm.wala.types.TypeReference;
import com.ibm.wala.core.util.config.AnalysisScopeReader;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class MovementFieldAnalyzer {

    public static void main(String[] args) throws Exception {
        if (args.length < 3) {
            System.err.println("Usage: MovementFieldAnalyzer <minecraft-jar> <output-dir> <sources-jar> [extra-spoon-classpath] [mode: all|wala|spoon]");
            System.exit(1);
        }

        String minecraftJar = args[0];
        Path outputDir = Path.of(args[1]);
        String sourcesJar = args[2];
        String extraSpoonClasspath = args.length >= 4 ? args[3] : "";
        PipelineMode mode = PipelineMode.from(args.length >= 5 ? args[4] : "all");
        Files.createDirectories(outputDir);

        if (mode == PipelineMode.SPOON_ONLY) {
            runSpoonOnly(minecraftJar, outputDir, sourcesJar, extraSpoonClasspath);
            System.out.println("\n=== Spoon phase complete ===");
            return;
        }

        System.out.println("=== WALA Movement Field Analysis ===");
        System.out.println("Minecraft JAR: " + minecraftJar);
        System.out.println("Output dir: " + outputDir);

        // Step 1: Write exclusions file
        File exclusionsFile = writeExclusions();

        // Step 2: Create analysis scope
        System.out.println("\nBuilding analysis scope...");
        AnalysisScope scope = AnalysisScopeReader.instance.makeJavaBinaryAnalysisScope(
            minecraftJar, exclusionsFile);

        System.out.println("Application loader modules: " +
            scope.getModules(ClassLoaderReference.Application));
        System.out.println("Primordial loader modules: " +
            scope.getModules(ClassLoaderReference.Primordial).size() + " modules");

        // Step 3: Build class hierarchy
        System.out.println("Building class hierarchy...");
        IClassHierarchy cha = ClassHierarchyFactory.make(scope);
        System.out.println("Class hierarchy: " + cha.getNumberOfClasses() + " classes");

        for (String tc : AnalysisConfig.TARGET_CLASSES) {
            TypeReference tr = TypeReference.findOrCreate(ClassLoaderReference.Application, tc);
            IClass c = cha.lookupClass(tr);
            System.out.println("  " + tc + " -> " + (c != null ? c.getAllMethods().size() + " methods" : "NOT FOUND"));
        }

        // Step 4: Create entry points on ClientPlayerEntity (concrete class)
        System.out.println("Setting up entry points...");
        Set<Entrypoint> entrypoints = createEntrypoints(cha);
        if (entrypoints.isEmpty()) {
            System.err.println("ERROR: No entry points found.");
            System.exit(1);
        }
        System.out.println("Entry points: " + entrypoints.size());

        // Step 5: Try 0-CFA first, fall back to CHA
        CallGraph cg;
        PointerAnalysis<InstanceKey> pa = null;

        System.out.println("Attempting 0-1-Container-CFA call graph...");
        try {
            AnalysisOptions options = new AnalysisOptions(scope, entrypoints);
            options.setReflectionOptions(AnalysisOptions.ReflectionOptions.NONE);

            AnalysisCache cache = new AnalysisCacheImpl();
            CallGraphBuilder<InstanceKey> builder = Util.makeZeroOneContainerCFABuilder(
                options, cache, cha, scope);

            long cgStart = System.currentTimeMillis();
            cg = builder.makeCallGraph(options, new PrintingProgressMonitor());
            pa = builder.getPointerAnalysis();
            long cgTime = System.currentTimeMillis() - cgStart;
            System.out.println("0-1-Container-CFA call graph: " + cg.getNumberOfNodes() + " nodes in " + (cgTime / 1000) + "s");

            // If CFA produced a trivial graph, fall back
            if (cg.getNumberOfNodes() <= 5) {
                System.out.println("0-1-Container-CFA produced trivial graph, falling back to CHA...");
                throw new RuntimeException("trivial graph");
            }
        } catch (Exception e) {
            System.out.println("0-1-Container-CFA failed (" + e.getMessage() + "), using CHA call graph...");
            CHACallGraph chaCg = new CHACallGraph(cha);
            chaCg.init(entrypoints);
            cg = chaCg;
            pa = null;
            System.out.println("CHA call graph: " + cg.getNumberOfNodes() + " nodes");
        }

        // Step 6: Field analysis
        System.out.println("\nRunning field analysis...");
        SliceAnalysis sliceAnalysis = new SliceAnalysis(cg, pa, cha);
        Set<SliceAnalysis.FieldInfo> slicedFields = sliceAnalysis.analyze();
        System.out.println("Discovered fields: " + slicedFields.size());

        for (SliceAnalysis.FieldInfo f : slicedFields) {
            System.out.println("  " + (f.isWrite() ? "W" : "R") + " " + f.declaringClass() + "." + f.fieldName());
        }

        // Step 7: Mod/Ref classification
        System.out.println("\nClassifying fields...");
        ModRefClassifier classifier = new ModRefClassifier(cg, pa);
        List<FieldResult> classified = classifier.classify(slicedFields);

        for (FieldResult r : classified) {
            System.out.println("  " + r.category() + " " + r.declaringClass() + "." + r.fieldName());
        }

        // Step 8: Code generation
        System.out.println("\nGenerating code...");
        SyncCodeGenerator generator = new SyncCodeGenerator(cha, outputDir);
        generator.generate(classified);

        // Step 9: Backward slice for line-level analysis
        Map<String, Map<String, Set<Integer>>> sliceLines = null;
        if (pa != null) {
            System.out.println("\n=== Phase 2: Backward Slice + Spoon Pruning ===");
            BackwardSliceExporter sliceExporter = new BackwardSliceExporter(cg, pa, cha);
            sliceLines = sliceExporter.computeSliceLines();
            sliceExporter.exportToJson(sliceLines, outputDir.resolve("movement-slice.json"));
        } else {
            System.out.println("\nSkipping backward slice (no pointer analysis with CHA call graph).");
        }

        if (mode == PipelineMode.WALA_ONLY) {
            System.out.println("\nWALA-only mode selected — skipping Spoon phase.");
            System.out.println("\n=== Analysis complete ===");
            exclusionsFile.delete();
            return;
        }

        if (sliceLines != null) {
            runSpoonPhase(minecraftJar, outputDir, sourcesJar, extraSpoonClasspath, classified, sliceLines);
        } else {
            System.out.println("Skipping Spoon phase because backward slice data is unavailable.");
        }

        System.out.println("\n=== Analysis complete ===");
        exclusionsFile.delete();
    }

    private static void runSpoonOnly(String minecraftJar,
                                     Path outputDir,
                                     String sourcesJar,
                                     String extraSpoonClasspath) throws Exception {
        List<FieldResult> fields = loadFieldManifest(outputDir.resolve("movement-fields.txt"));
        Map<String, Map<String, Set<Integer>>> sliceLines = loadSliceLines(outputDir.resolve("movement-slice.json"));
        runSpoonPhase(minecraftJar, outputDir, sourcesJar, extraSpoonClasspath, fields, sliceLines);
    }

    private static void runSpoonPhase(String minecraftJar,
                                      Path outputDir,
                                      String sourcesJar,
                                      String extraSpoonClasspath,
                                      List<FieldResult> classified,
                                      Map<String, Map<String, Set<Integer>>> sliceLines) throws Exception {
        if (sourcesJar.isEmpty() || !Files.exists(Path.of(sourcesJar))) {
            throw new IllegalStateException("Spoon phase requires a valid sources JAR. Pass -PsourcesJar=<path-to-sources.jar>.");
        }
        System.out.println("\nRunning Spoon source pruning...");
        SpoonSlicePruner pruner = new SpoonSlicePruner(
            Path.of(sourcesJar),
            Path.of(minecraftJar),
            extraSpoonClasspath,
            sliceLines
        );
        pruner.pruneAndWrite(outputDir.resolve("java/murat/simv2/simulation/sliced"));

        RuntimeSimClassGenerator runtimeGenerator = new RuntimeSimClassGenerator(outputDir);
        runtimeGenerator.generate(classified);
    }

    private static List<FieldResult> loadFieldManifest(Path manifestPath) throws IOException {
        if (!Files.exists(manifestPath)) {
            throw new IllegalStateException("Missing movement field manifest: " + manifestPath
                + ". Run WALA phase first.");
        }
        List<FieldResult> fields = new ArrayList<>();
        for (String line : Files.readAllLines(manifestPath)) {
            String trimmed = line.trim();
            if (trimmed.isEmpty() || trimmed.startsWith("#")) {
                continue;
            }
            String[] parts = trimmed.split("\\s+");
            if (parts.length < 4) {
                throw new IllegalStateException("Invalid movement field manifest line: " + line);
            }
            fields.add(new FieldResult(
                parts[1],
                parts[2],
                parts[3],
                FieldResult.FieldCategory.valueOf(parts[0])
            ));
        }
        if (fields.isEmpty()) {
            throw new IllegalStateException("Movement field manifest is empty: " + manifestPath);
        }
        return fields;
    }

    private static Map<String, Map<String, Set<Integer>>> loadSliceLines(Path slicePath) throws IOException {
        if (!Files.exists(slicePath)) {
            throw new IllegalStateException("Missing movement slice JSON: " + slicePath
                + ". Run WALA phase first.");
        }
        Type mapType = new TypeToken<Map<String, Map<String, Set<Integer>>>>() { }.getType();
        String json = Files.readString(slicePath);
        Map<String, Map<String, Set<Integer>>> sliceLines = new Gson().fromJson(json, mapType);
        if (sliceLines == null || sliceLines.isEmpty()) {
            throw new IllegalStateException("Movement slice JSON is empty: " + slicePath);
        }
        return sliceLines;
    }

    private enum PipelineMode {
        ALL,
        WALA_ONLY,
        SPOON_ONLY;

        private static PipelineMode from(String raw) {
            if (raw == null || raw.isBlank()) {
                return ALL;
            }
            return switch (raw.trim().toLowerCase(Locale.ROOT)) {
                case "all" -> ALL;
                case "wala" -> WALA_ONLY;
                case "spoon" -> SPOON_ONLY;
                default -> throw new IllegalArgumentException("Unknown mode '" + raw + "'. Use one of: all, wala, spoon.");
            };
        }
    }

    private static File writeExclusions() throws IOException {
        File file = File.createTempFile("wala-exclusions", ".txt");
        file.deleteOnExit();
        Files.writeString(file.toPath(), String.join("\n", AnalysisConfig.EXCLUSIONS) + "\n");
        return file;
    }

    private static Set<Entrypoint> createEntrypoints(IClassHierarchy cha) {
        Set<Entrypoint> entrypoints = new HashSet<>();

        // Resolve all entry methods against ClientPlayerEntity (concrete) so that
        // 0-CFA can allocate a receiver instance. The methods themselves may be
        // declared on Entity or LivingEntity but are inherited by ClientPlayerEntity.
        TypeReference concreteType = TypeReference.findOrCreate(
            ClassLoaderReference.Application, AnalysisConfig.CLIENT_PLAYER_CLASS);
        IClass concreteClass = cha.lookupClass(concreteType);
        if (concreteClass == null) {
            System.err.println("ERROR: ClientPlayerEntity not found in class hierarchy");
            return entrypoints;
        }

        for (AnalysisConfig.EntryMethod em : AnalysisConfig.ENTRY_METHODS) {
            // Create MethodReference on the concrete type (ClientPlayerEntity) so that
            // DefaultEntrypoint allocates a ClientPlayerEntity receiver, not the
            // abstract declaring class. WALA's 0-CFA silently drops entry points
            // when it can't instantiate the receiver type.
            MethodReference concreteMethodRef = MethodReference.findOrCreate(
                concreteType, Selector.make(em.methodName() + em.descriptor()));

            // Verify the method exists somewhere in the hierarchy
            IMethod resolved = cha.resolveMethod(concreteMethodRef);
            if (resolved == null) {
                System.err.println("WARNING: Method not found: " + em.className() + "." + em.methodName());
                continue;
            }

            // Pass the MethodReference (not IMethod) — this forces DefaultEntrypoint
            // to use ClientPlayerEntity as the receiver allocation type
            entrypoints.add(new DefaultEntrypoint(concreteMethodRef, cha));
            System.out.println("  Entry: ClientPlayerEntity." + em.methodName() + em.descriptor()
                + " (resolves to " + resolved.getDeclaringClass().getName() + ")");
        }

        return entrypoints;
    }
}
