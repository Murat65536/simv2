package murat.simv2.analysis;

import com.ibm.wala.classLoader.IClass;
import com.ibm.wala.classLoader.IMethod;
import com.ibm.wala.core.util.config.AnalysisScopeReader;
import com.ibm.wala.ipa.callgraph.AnalysisCache;
import com.ibm.wala.ipa.callgraph.AnalysisCacheImpl;
import com.ibm.wala.ipa.callgraph.AnalysisOptions;
import com.ibm.wala.ipa.callgraph.AnalysisScope;
import com.ibm.wala.ipa.callgraph.CallGraph;
import com.ibm.wala.ipa.callgraph.CallGraphBuilder;
import com.ibm.wala.ipa.callgraph.Entrypoint;
import com.ibm.wala.ipa.callgraph.cha.CHACallGraph;
import com.ibm.wala.ipa.callgraph.impl.DefaultEntrypoint;
import com.ibm.wala.ipa.callgraph.impl.Util;
import com.ibm.wala.ipa.callgraph.propagation.InstanceKey;
import com.ibm.wala.ipa.callgraph.propagation.PointerAnalysis;
import com.ibm.wala.ipa.cha.ClassHierarchyFactory;
import com.ibm.wala.ipa.cha.IClassHierarchy;
import com.ibm.wala.types.ClassLoaderReference;
import com.ibm.wala.types.MethodReference;
import com.ibm.wala.types.Selector;
import com.ibm.wala.types.TypeReference;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

final class WalaPipelineRunner {
    WalaPipelineResult run(AnalysisRunConfig config) throws Exception {
        System.out.println("=== WALA Movement Field Analysis ===");
        System.out.println("Minecraft JAR: " + config.minecraftJar());
        System.out.println("Output dir: " + config.outputDir());

        File exclusionsFile = writeExclusions();
        try {
            AnalysisScope scope = buildScope(config.minecraftJar(), exclusionsFile);
            IClassHierarchy cha = buildClassHierarchy(scope);
            Set<Entrypoint> entrypoints = createEntrypoints(cha);
            if (entrypoints.isEmpty()) {
                throw new IllegalStateException("No entry points found.");
            }
            System.out.println("Entry points: " + entrypoints.size());

            CallGraphState callGraphState = buildCallGraph(scope, cha, entrypoints);
            CallGraph cg = callGraphState.callGraph();
            PointerAnalysis<InstanceKey> pa = callGraphState.pointerAnalysis();

            System.out.println("\nRunning field analysis...");
            SliceAnalysis sliceAnalysis = new SliceAnalysis(cg, pa, cha);
            Set<SliceAnalysis.FieldInfo> slicedFields = sliceAnalysis.analyze();
            System.out.println("Discovered fields: " + slicedFields.size());
            for (SliceAnalysis.FieldInfo field : slicedFields) {
                System.out.println("  " + (field.isWrite() ? "W" : "R")
                    + " " + field.declaringClass() + "." + field.fieldName());
            }

            System.out.println("\nClassifying fields...");
            ModRefClassifier classifier = new ModRefClassifier(cg, pa);
            List<FieldResult> classified = classifier.classify(slicedFields);
            for (FieldResult result : classified) {
                System.out.println("  " + result.category()
                    + " " + result.declaringClass() + "." + result.fieldName());
            }

            System.out.println("\nGenerating code...");
            SyncCodeGenerator generator = new SyncCodeGenerator(cha, config.outputDir());
            generator.generate(classified);

            SliceExportResult sliceExportResult = exportSliceLinesIfAvailable(
                config.outputDir(), cg, pa, cha, classified);
            return new WalaPipelineResult(
                List.copyOf(classified),
                sliceExportResult.sliceLines(),
                sliceExportResult.mirrorClosure(),
                sliceExportResult.prerequisiteStatus());
        } finally {
            exclusionsFile.delete();
        }
    }

    private AnalysisScope buildScope(String minecraftJar, File exclusionsFile) throws Exception {
        System.out.println("\nBuilding analysis scope...");
        AnalysisScope scope = AnalysisScopeReader.instance.makeJavaBinaryAnalysisScope(
            minecraftJar, exclusionsFile);
        System.out.println("Application loader modules: "
            + scope.getModules(ClassLoaderReference.Application));
        System.out.println("Primordial loader modules: "
            + scope.getModules(ClassLoaderReference.Primordial).size() + " modules");
        return scope;
    }

    private IClassHierarchy buildClassHierarchy(AnalysisScope scope) throws Exception {
        System.out.println("Building class hierarchy...");
        IClassHierarchy cha = ClassHierarchyFactory.make(scope);
        System.out.println("Class hierarchy: " + cha.getNumberOfClasses() + " classes");
        for (String targetClass : AnalysisConfig.TARGET_CLASSES) {
            TypeReference tr = TypeReference.findOrCreate(ClassLoaderReference.Application, targetClass);
            IClass c = cha.lookupClass(tr);
            System.out.println("  " + targetClass + " -> "
                + (c != null ? c.getAllMethods().size() + " methods" : "NOT FOUND"));
        }
        return cha;
    }

    private CallGraphState buildCallGraph(AnalysisScope scope,
                                          IClassHierarchy cha,
                                          Set<Entrypoint> entrypoints) throws Exception {
        System.out.println("Attempting 0-1-Container-CFA call graph...");
        try {
            AnalysisOptions options = new AnalysisOptions(scope, entrypoints);
            options.setReflectionOptions(AnalysisOptions.ReflectionOptions.NONE);
            AnalysisCache cache = new AnalysisCacheImpl();
            CallGraphBuilder<InstanceKey> builder = Util.makeZeroOneContainerCFABuilder(
                options, cache, cha, scope);

            long cgStart = System.currentTimeMillis();
            CallGraph cg = builder.makeCallGraph(options, new PrintingProgressMonitor());
            PointerAnalysis<InstanceKey> pa = builder.getPointerAnalysis();
            long cgTime = System.currentTimeMillis() - cgStart;
            System.out.println("0-1-Container-CFA call graph: "
                + cg.getNumberOfNodes() + " nodes in " + (cgTime / 1000) + "s");
            if (cg.getNumberOfNodes() <= 5) {
                System.out.println("0-1-Container-CFA produced trivial graph, falling back to CHA...");
                throw new RuntimeException("trivial graph");
            }
            return new CallGraphState(cg, pa);
        } catch (Exception ex) {
            System.out.println("0-1-Container-CFA failed (" + ex.getMessage() + "), using CHA call graph...");
            CHACallGraph chaCg = new CHACallGraph(cha);
            chaCg.init(entrypoints);
            System.out.println("CHA call graph: " + chaCg.getNumberOfNodes() + " nodes");
            return new CallGraphState(chaCg, null);
        }
    }

    private SliceExportResult exportSliceLinesIfAvailable(Path outputDir,
                                                          CallGraph cg,
                                                          PointerAnalysis<InstanceKey> pa,
                                                          IClassHierarchy cha,
                                                          List<FieldResult> classifiedFields) throws Exception {
        Path sliceJsonPath = AnalysisArtifacts.sliceJsonPath(outputDir);
        Path mirrorClosurePath = AnalysisArtifacts.mirrorClosurePath(outputDir);
        if (pa == null) {
            removeStaleArtifacts(sliceJsonPath, mirrorClosurePath);
            System.out.println(
                "\nBackward slice unavailable: pointer analysis is unavailable after CHA fallback.");
            System.out.println("Spoon prerequisites are not satisfied for this run.");
            return new SliceExportResult(
                null,
                null,
                WalaPipelineResult.SpoonPrerequisiteStatus.POINTER_ANALYSIS_UNAVAILABLE);
        }
        System.out.println("\n=== Phase 2: Backward Slice + Spoon Pruning ===");
        BackwardSliceExporter exporter = new BackwardSliceExporter(cg, pa, cha);
        Map<String, Map<String, Set<Integer>>> sliceLines = exporter.computeSliceLines();
        if (sliceLines == null || sliceLines.isEmpty()) {
            removeStaleArtifacts(sliceJsonPath, mirrorClosurePath);
            System.out.println("Backward slice produced no source line mappings.");
            System.out.println("Spoon prerequisites are not satisfied for this run.");
            return new SliceExportResult(
                null,
                null,
                WalaPipelineResult.SpoonPrerequisiteStatus.SLICE_LINES_NOT_PRODUCED);
        }
        exporter.exportToJson(sliceLines, sliceJsonPath);
        MirrorClosure mirrorClosure = buildMirrorClosure(sliceLines, classifiedFields);
        AnalysisArtifacts.writeMirrorClosure(mirrorClosure, mirrorClosurePath);
        return new SliceExportResult(
            Map.copyOf(sliceLines),
            mirrorClosure,
            WalaPipelineResult.SpoonPrerequisiteStatus.READY);
    }

    private MirrorClosure buildMirrorClosure(Map<String, Map<String, Set<Integer>>> sliceLines,
                                             List<FieldResult> classifiedFields) {
        TreeSet<String> classes = new TreeSet<>();
        TreeMap<String, Set<String>> methodsByClass = new TreeMap<>();

        for (Map.Entry<String, Map<String, Set<Integer>>> classEntry : sliceLines.entrySet()) {
            String className = classEntry.getKey();
            if (className == null || className.isBlank()) {
                continue;
            }
            if (!className.startsWith("net.minecraft.")) {
                continue;
            }
            classes.add(className);
            TreeSet<String> selectors = new TreeSet<>();
            for (String selector : classEntry.getValue().keySet()) {
                if (selector != null && !selector.isBlank()) {
                    selectors.add(selector.trim());
                }
            }
            methodsByClass.put(className, Set.copyOf(selectors));
        }

        for (FieldResult field : classifiedFields) {
            String className = toDotClassName(field.declaringClass());
            if (className != null) {
                classes.add(className);
                methodsByClass.putIfAbsent(className, Set.of());
            }
        }

        return new MirrorClosure(Set.copyOf(classes), Map.copyOf(new LinkedHashMap<>(methodsByClass)));
    }

    private String toDotClassName(String internalName) {
        if (internalName == null || internalName.isBlank()) {
            return null;
        }
        String normalized = internalName.startsWith("L")
            ? internalName.substring(1)
            : internalName;
        if (normalized.endsWith(";")) {
            normalized = normalized.substring(0, normalized.length() - 1);
        }
        String dotName = normalized.replace('/', '.');
        return dotName.startsWith("net.minecraft.") ? dotName : null;
    }

    private void removeStaleArtifacts(Path... artifactPaths) throws Exception {
        for (Path artifactPath : artifactPaths) {
            if (artifactPath != null && Files.deleteIfExists(artifactPath)) {
                System.out.println("Removed stale artifact: " + artifactPath);
            }
        }
    }

    private File writeExclusions() throws Exception {
        File file = File.createTempFile("wala-exclusions", ".txt");
        file.deleteOnExit();
        Files.writeString(file.toPath(), String.join("\n", AnalysisConfig.EXCLUSIONS) + "\n");
        return file;
    }

    private Set<Entrypoint> createEntrypoints(IClassHierarchy cha) {
        Set<Entrypoint> entrypoints = new HashSet<>();
        TypeReference concreteType = TypeReference.findOrCreate(
            ClassLoaderReference.Application, AnalysisConfig.CLIENT_PLAYER_CLASS);
        IClass concreteClass = cha.lookupClass(concreteType);
        if (concreteClass == null) {
            System.err.println("ERROR: ClientPlayerEntity not found in class hierarchy");
            return entrypoints;
        }
        for (AnalysisConfig.EntryMethod em : AnalysisConfig.ENTRY_METHODS) {
            MethodReference concreteMethodRef = MethodReference.findOrCreate(
                concreteType, Selector.make(em.methodName() + em.descriptor()));
            IMethod resolved = cha.resolveMethod(concreteMethodRef);
            if (resolved == null) {
                System.err.println("WARNING: Method not found: "
                    + em.className() + "." + em.methodName());
                continue;
            }
            entrypoints.add(new DefaultEntrypoint(concreteMethodRef, cha));
            System.out.println("  Entry: ClientPlayerEntity." + em.methodName() + em.descriptor()
                + " (resolves to " + resolved.getDeclaringClass().getName() + ")");
        }
        return entrypoints;
    }

    private record CallGraphState(CallGraph callGraph, PointerAnalysis<InstanceKey> pointerAnalysis) {
    }

    private record SliceExportResult(Map<String, Map<String, Set<Integer>>> sliceLines,
                                     MirrorClosure mirrorClosure,
                                     WalaPipelineResult.SpoonPrerequisiteStatus prerequisiteStatus) {
    }
}
