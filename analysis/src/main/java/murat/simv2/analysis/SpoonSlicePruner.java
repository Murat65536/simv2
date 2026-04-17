package murat.simv2.analysis;

import spoon.Launcher;
import spoon.reflect.CtModel;
import spoon.reflect.code.*;
import spoon.reflect.cu.CompilationUnit;
import spoon.reflect.declaration.*;
import spoon.reflect.factory.Factory;
import spoon.reflect.reference.*;
import spoon.processing.Processor;
import spoon.reflect.visitor.DefaultImportComparator;
import spoon.reflect.visitor.DefaultJavaPrettyPrinter;
import spoon.reflect.visitor.ForceImportProcessor;
import spoon.reflect.visitor.ImportCleaner;
import spoon.reflect.visitor.ImportConflictDetector;
import spoon.reflect.visitor.PrettyPrinter;
import spoon.reflect.visitor.filter.TypeFilter;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Uses Spoon to parse decompiled Minecraft sources, clone movement-relevant
 * methods into new detached Sliced* classes, and prune statements not present
 * in the WALA backward slice.
 *
 * The approach:
 * 1. Build a Spoon AST model from MC decompiled sources
 * 2. For each target class, create a new Sliced* class
 * 3. Clone methods that have WALA slice data into the new class
 * 4. Walk each cloned method's AST — delete statements whose source line
 *    is NOT in the WALA line set
 * 5. Rewrite type references (Entity → SlicedEntity, etc.)
 * 6. Generate abstract stubs for unresolved this.xxx() calls
 * 7. Use Spoon's prettyprint to write the result
 */
public class SpoonSlicePruner {

    /** MC entity type → Sliced type simple name mapping */
    private static final Map<String, String> TYPE_REMAP = Map.of(
        "Entity", "SlicedEntity",
        "LivingEntity", "SlicedLivingEntity",
        "PlayerEntity", "SlicedPlayerEntity",
        "AbstractClientPlayerEntity", "SlicedAbstractClientPlayerEntity",
        "ClientPlayerEntity", "SlicedClientPlayerEntity"
    );

    /**
     * Methods that should expose sliced peer parameters inside generated sliced code.
     * Vanilla-facing APIs, overrides, and bridge helpers intentionally keep vanilla types.
     */
    private static final Set<String> SLICED_PARAM_METHODS = Set.of();

    private static final Set<String> SUBCLASS_VISIBLE_HELPERS = Set.of(
        "adjustMovementForCollisions",
        "applyMoveEffect"
    );
    private static final boolean STRICT_WALA_LINE_FIDELITY = true;

    private final Path sourcesJar;
    private final Path minecraftJar;
    private final List<Path> extraClasspathOverride;
    private final Map<String, Map<String, Set<Integer>>> sliceLines;
    private final SlicedFieldPlanner fieldPlanner = new SlicedFieldPlanner();
    private final SpoonRepairPasses repairPasses = new SpoonRepairPasses();

    public SpoonSlicePruner(Path sourcesJar,
                            Path minecraftJar,
                            String extraClasspathOverride,
                            Map<String, Map<String, Set<Integer>>> sliceLines) {
        this.sourcesJar = Objects.requireNonNull(sourcesJar, "sourcesJar");
        this.minecraftJar = Objects.requireNonNull(minecraftJar, "minecraftJar");
        this.extraClasspathOverride = parseClasspath(extraClasspathOverride);
        this.sliceLines = Objects.requireNonNull(sliceLines, "sliceLines");
    }

    public void pruneAndWrite(Path outputDir) throws IOException {
        Path tempDir = Files.createTempDirectory("mc-sources-");
        try {
            extractTargetSources(tempDir);

            System.out.println("Building Spoon model...");
            Launcher launcher = new Launcher();
            launcher.addInputResource(tempDir.toString());
            String[] sourceClasspath = buildSourceClasspath();
            System.out.println("Spoon source classpath entries: " + sourceClasspath.length);
            launcher.getEnvironment().setNoClasspath(true);
            launcher.getEnvironment().setIgnoreSyntaxErrors(true);
            launcher.getEnvironment().setComplianceLevel(21);
            launcher.getEnvironment().setAutoImports(true);
            launcher.getModelBuilder().setSourceClasspath(sourceClasspath);
            launcher.buildModel();
            CtModel model = launcher.getModel();
            Factory factory = launcher.getFactory();
            System.out.println("Spoon model: " + model.getAllTypes().size() + " types loaded");

            Map<String, CtType<?>> typeIndex = new LinkedHashMap<>();
            for (CtType<?> t : model.getAllTypes()) {
                typeIndex.put(t.getQualifiedName(), t);
            }

            Set<String> emittedSimpleNames = new LinkedHashSet<>();
            Set<String> hierarchyDefinedMethods = new LinkedHashSet<>();
            Set<String> hierarchyDefinedFields = new LinkedHashSet<>();
            // Collect access widener entries needed for private members
            Set<String> awEntries = new TreeSet<>();
            // Methods found during a child's transitive closure that belong to a parent class
            Map<String, List<CtMethod<?>>> deferredMethods = new LinkedHashMap<>();
            // Track which sigs each class contributed to hierarchyDefinedMethods
            // so we can remove them before re-processing
            Map<String, Set<String>> classContributedSigs = new LinkedHashMap<>();
            // Track which fields each class contributed to hierarchyDefinedFields
            // so we can remove them before re-processing
            Map<String, Set<String>> classContributedFields = new LinkedHashMap<>();

            for (String className : AnalysisConfig.TARGET_CLASSES_DOT) {
                Map<String, Set<Integer>> methodLines = sliceLines.get(className);
                if (methodLines == null || methodLines.isEmpty()) {
                    System.out.println("  No slice data for " + className + " — skipping");
                    continue;
                }

                CtType<?> type = typeIndex.get(className);
                if (type == null) {
                    System.err.println("  Type not found in Spoon model: " + className);
                    continue;
                }

                // Include any methods deferred from child classes (disabled in strict mode).
                List<CtMethod<?>> extraMethods = STRICT_WALA_LINE_FIDELITY
                    ? List.of()
                    : deferredMethods.remove(type.getSimpleName());

                Set<String> beforeMethods = new LinkedHashSet<>(hierarchyDefinedMethods);
                Set<String> beforeFields = new LinkedHashSet<>(hierarchyDefinedFields);
                buildSlicedClass(type, methodLines, outputDir, factory,
                    emittedSimpleNames, typeIndex, hierarchyDefinedMethods, hierarchyDefinedFields, awEntries,
                    deferredMethods, extraMethods);
                Set<String> contributedMethods = new LinkedHashSet<>(hierarchyDefinedMethods);
                contributedMethods.removeAll(beforeMethods);
                classContributedSigs.put(type.getSimpleName(), contributedMethods);
                Set<String> contributedFields = new LinkedHashSet<>(hierarchyDefinedFields);
                contributedFields.removeAll(beforeFields);
                classContributedFields.put(type.getSimpleName(), contributedFields);
                emittedSimpleNames.add(type.getSimpleName());
            }

            // Re-process parent classes that gained deferred methods from later children.
            if (!STRICT_WALA_LINE_FIDELITY && !deferredMethods.isEmpty()) {
                for (String className : AnalysisConfig.TARGET_CLASSES_DOT) {
                    CtType<?> type = typeIndex.get(className);
                    if (type == null) continue;
                    List<CtMethod<?>> extra = deferredMethods.remove(type.getSimpleName());
                    if (extra == null || extra.isEmpty()) continue;

                    // Remove this class's previous contributions so the transitive
                    // closure can re-discover them (the file will be overwritten)
                    Set<String> previousSigs = classContributedSigs.getOrDefault(
                        type.getSimpleName(), Set.of());
                    hierarchyDefinedMethods.removeAll(previousSigs);
                    Set<String> previousFields = classContributedFields.getOrDefault(
                        type.getSimpleName(), Set.of());
                    hierarchyDefinedFields.removeAll(previousFields);

                    Map<String, Set<Integer>> methodLines = sliceLines.getOrDefault(className, Map.of());
                    System.out.println("Re-processing " + type.getSimpleName()
                        + " with " + extra.size() + " deferred methods from children");
                    Set<String> beforeMethods = new LinkedHashSet<>(hierarchyDefinedMethods);
                    Set<String> beforeFields = new LinkedHashSet<>(hierarchyDefinedFields);
                    buildSlicedClass(type, methodLines, outputDir, factory,
                        emittedSimpleNames, typeIndex, hierarchyDefinedMethods, hierarchyDefinedFields, awEntries,
                        deferredMethods, extra);
                    Set<String> contributedMethods = new LinkedHashSet<>(hierarchyDefinedMethods);
                    contributedMethods.removeAll(beforeMethods);
                    classContributedSigs.put(type.getSimpleName(), contributedMethods);
                    Set<String> contributedFields = new LinkedHashSet<>(hierarchyDefinedFields);
                    contributedFields.removeAll(beforeFields);
                    classContributedFields.put(type.getSimpleName(), contributedFields);
                }
            }

            // Append sliced-class AW entries to the existing access widener
            if (!awEntries.isEmpty()) {
                Path awPath = resolveAccessWidenerPath(outputDir);
                if (Files.exists(awPath)) {
                    String existing = Files.readString(awPath);
                    String normalizedExisting = normalizeSlicedAwHeader(existing);
                    List<String> missingEntries = awEntries.stream()
                        .filter(entry -> !normalizedExisting.contains(entry))
                        .toList();
                    if (!missingEntries.isEmpty()) {
                        StringBuilder sb = new StringBuilder(normalizedExisting);
                        if (!normalizedExisting.contains("# Access widener entries for sliced classes")) {
                            if (!normalizedExisting.endsWith("\n")) {
                                sb.append("\n");
                            }
                            sb.append("\n# Access widener entries for sliced classes\n");
                        } else if (!normalizedExisting.endsWith("\n")) {
                            sb.append("\n");
                        }
                        for (String entry : missingEntries) {
                            sb.append(entry).append("\n");
                        }
                        Files.writeString(awPath, sb.toString());
                        System.out.println("Added " + missingEntries.size() + " access widener entries for sliced classes");
                    } else if (!normalizedExisting.equals(existing)) {
                        Files.writeString(awPath, normalizedExisting);
                    }
                }
            }
        } finally {
            try (var walk = Files.walk(tempDir)) {
                walk.sorted(Comparator.reverseOrder()).map(Path::toFile).forEach(java.io.File::delete);
            }
        }
    }

    private List<Path> parseClasspath(String rawClasspath) {
        if (rawClasspath == null || rawClasspath.isBlank()) {
            return List.of();
        }
        List<Path> entries = new ArrayList<>();
        StringTokenizer tokenizer = new StringTokenizer(rawClasspath, File.pathSeparator);
        while (tokenizer.hasMoreTokens()) {
            String entry = tokenizer.nextToken().trim();
            if (!entry.isEmpty()) {
                entries.add(Path.of(entry));
            }
        }
        return List.copyOf(entries);
    }

    private Path resolveAccessWidenerPath(Path outputDir) {
        // outputDir = .../src/main/generated/java/murat/simv2/simulation/sliced
        Path generatedDir = outputDir.getParent().getParent().getParent().getParent().getParent();
        Path mainDir = generatedDir.getParent();
        Path resourcesAw = mainDir.resolve("resources").resolve("sim-v2.accesswidener");
        if (Files.exists(resourcesAw)) {
            return resourcesAw;
        }
        return generatedDir.resolve("sim-v2.accesswidener");
    }

    private String normalizeSlicedAwHeader(String awSource) {
        if (awSource == null || awSource.isEmpty()) {
            return awSource;
        }
        String marker = "# Access widener entries for sliced classes";
        String[] lines = awSource.split("\\R", -1);
        List<String> keptLines = new ArrayList<>(lines.length);
        boolean seenMarker = false;
        for (String line : lines) {
            if (marker.equals(line)) {
                if (seenMarker) {
                    continue;
                }
                seenMarker = true;
            }
            keptLines.add(line);
        }
        while (!keptLines.isEmpty() && keptLines.get(keptLines.size() - 1).isEmpty()) {
            keptLines.remove(keptLines.size() - 1);
        }
        String normalized = String.join("\n", keptLines);
        return awSource.endsWith("\n") ? normalized + "\n" : normalized;
    }

    private String[] buildSourceClasspath() throws IOException {
        LinkedHashSet<String> validated = new LinkedHashSet<>();

        Path normalizedMinecraftJar = minecraftJar.toAbsolutePath().normalize();
        if (!Files.exists(normalizedMinecraftJar)) {
            throw new IOException("Minecraft JAR not found for Spoon classpath: " + normalizedMinecraftJar);
        }
        validated.add(normalizedMinecraftJar.toString());

        String runtimeClasspath = System.getProperty("java.class.path", "");
        if (!runtimeClasspath.isBlank()) {
            List<String> skippedRuntimeEntries = new ArrayList<>();
            StringTokenizer runtimeEntries = new StringTokenizer(runtimeClasspath, File.pathSeparator);
            while (runtimeEntries.hasMoreTokens()) {
                String entry = runtimeEntries.nextToken().trim();
                if (entry.isEmpty()) {
                    continue;
                }
                Path normalized = Path.of(entry).toAbsolutePath().normalize();
                if (Files.exists(normalized)) {
                    validated.add(normalized.toString());
                } else {
                    skippedRuntimeEntries.add(normalized.toString());
                }
            }
            if (!skippedRuntimeEntries.isEmpty()) {
                System.out.println("Skipping " + skippedRuntimeEntries.size()
                    + " missing runtime classpath entries for Spoon.");
            }
        }

        int discoveredCacheJars = 0;
        for (Path entry : discoverGradleModuleCacheJars()) {
            Path normalized = entry.toAbsolutePath().normalize();
            if (validated.add(normalized.toString())) {
                discoveredCacheJars++;
            }
        }
        if (discoveredCacheJars > 0) {
            System.out.println("Added " + discoveredCacheJars
                + " Gradle module cache jars to Spoon classpath.");
        }

        List<String> missingOverrides = new ArrayList<>();
        for (Path entry : extraClasspathOverride) {
            Path normalized = entry.toAbsolutePath().normalize();
            if (!Files.exists(normalized)) {
                missingOverrides.add(normalized.toString());
            } else {
                validated.add(normalized.toString());
            }
        }
        if (!missingOverrides.isEmpty()) {
            throw new IOException("Spoon extra classpath override contains missing entries: "
                + String.join(", ", missingOverrides));
        }
        if (validated.isEmpty()) {
            throw new IOException("Spoon source classpath is empty after validation.");
        }

        return validated.toArray(String[]::new);
    }

    private List<Path> discoverGradleModuleCacheJars() throws IOException {
        String gradleHomeEnv = System.getenv("GRADLE_USER_HOME");
        Path gradleHome = (gradleHomeEnv == null || gradleHomeEnv.isBlank())
            ? Path.of(System.getProperty("user.home"), ".gradle")
            : Path.of(gradleHomeEnv);
        Path moduleCache = gradleHome.resolve("caches").resolve("modules-2").resolve("files-2.1");
        if (!Files.isDirectory(moduleCache)) {
            return List.of();
        }

        List<Path> jars = new ArrayList<>();
        try (var walk = Files.walk(moduleCache)) {
            walk.filter(Files::isRegularFile)
                .filter(this::isBinaryJar)
                .forEach(jars::add);
        }
        return jars;
    }

    private boolean isBinaryJar(Path path) {
        String fileName = path.getFileName().toString();
        return fileName.endsWith(".jar")
            && !fileName.endsWith("-sources.jar")
            && !fileName.endsWith("-javadoc.jar");
    }

    private void extractTargetSources(Path tempDir) throws IOException {
        System.out.println("Extracting target sources from " + sourcesJar.getFileName() + "...");

        Set<String> targetPaths = new HashSet<>();
        for (String className : AnalysisConfig.TARGET_CLASSES_DOT) {
            targetPaths.add(className.replace('.', '/') + ".java");
        }

        try (JarFile jar = new JarFile(sourcesJar.toFile())) {
            Enumeration<JarEntry> entries = jar.entries();
            while (entries.hasMoreElements()) {
                JarEntry entry = entries.nextElement();
                if (entry.isDirectory()) continue;

                if (targetPaths.contains(entry.getName())) {
                    Path outFile = tempDir.resolve(entry.getName());
                    Files.createDirectories(outFile.getParent());
                    try (var in = jar.getInputStream(entry)) {
                        Files.copy(in, outFile);
                    }
                    System.out.println("  Extracted: " + entry.getName());
                }
            }
        }
    }

    private void buildSlicedClass(CtType<?> type, Map<String, Set<Integer>> methodLines,
                                   Path outputDir, Factory factory,
                                   Set<String> emittedSimpleNames,
                                   Map<String, CtType<?>> typeIndex,
                                   Set<String> hierarchyDefinedMethods,
                                   Set<String> hierarchyDefinedFields,
                                   Set<String> awEntries,
                                   Map<String, List<CtMethod<?>>> deferredMethods,
                                   List<CtMethod<?>> extraMethods) throws IOException {
        String simpleName = type.getSimpleName();
        String slicedName = "Sliced" + simpleName;
        System.out.println("Pruning " + type.getQualifiedName() + "...");

        CtTypeReference<?> extendsRef = resolveExtendsType(type, emittedSimpleNames, typeIndex, factory);

        // ── Phase 1: Clone WALA-sliced methods with pruning ──
        List<CtMethod<?>> clonedMethods = new ArrayList<>();
        Set<String> definedSigs = new LinkedHashSet<>();
        Set<String> definedNames = new LinkedHashSet<>();
        Set<String> prunedReturnMethods = new HashSet<>();
        Set<String> walaSelectedMethodKeys = new LinkedHashSet<>();

        for (var entry : methodLines.entrySet()) {
            String methodSelector = entry.getKey();
            Set<Integer> walaLines = entry.getValue();

            String methodName = methodSelector.substring(0, methodSelector.indexOf('('));
            String descriptor = methodSelector.substring(methodSelector.indexOf('('));
            CtMethod<?> original = findMethod(type, methodName, descriptor);
            if (original == null) {
                System.out.println("  Method not found: " + methodName + " in " + simpleName);
                continue;
            }

            CtMethod<?> cloned = original.clone();
            rewriteMethodSignature(cloned, original);
            relaxHelperVisibility(cloned);
            String sig = methodKey(original);
            String rewrittenSig = methodKey(cloned);
            if (definedSigs.contains(sig) || definedSigs.contains(rewrittenSig)) {
                continue;
            }
            definedSigs.add(sig);
            definedSigs.add(rewrittenSig);
            walaSelectedMethodKeys.add(sig);
            walaSelectedMethodKeys.add(rewrittenSig);

            int returnsBefore = cloned.getBody() == null ? 0
                : cloned.getBody().getElements(new TypeFilter<>(CtReturn.class)).size();
            int before = countStatements(cloned);
            pruneByWalaLines(cloned, walaLines);
            preserveConstructorDelegation(cloned, original, extendsRef != null, factory);
            repairPasses.repairMethodAfterPrune(cloned, simpleName, factory);
            int after = countStatements(cloned);
            int returnsAfter = cloned.getBody() == null ? 0
                : cloned.getBody().getElements(new TypeFilter<>(CtReturn.class)).size();

            if (returnsAfter < returnsBefore) {
                prunedReturnMethods.add(methodKey(cloned));
            }

            System.out.println("  " + simpleName + "." + methodName + "(): "
                + before + " → " + after + " statements (" + walaLines.size() + " WALA lines)");

            clonedMethods.add(cloned);
            definedNames.add(methodName);
        }

        // Include methods deferred from child classes (strict mode forbids this).
        if (!STRICT_WALA_LINE_FIDELITY && extraMethods != null) {
            for (CtMethod<?> extra : extraMethods) {
                CtMethod<?> cloned = extra.clone();
                rewriteMethodSignature(cloned, extra);
                relaxHelperVisibility(cloned);
                String sig = methodKey(extra);
                String rewrittenSig = methodKey(cloned);
                if (!definedSigs.contains(sig) && !definedSigs.contains(rewrittenSig)) {
                    clonedMethods.add(cloned);
                    definedSigs.add(sig);
                    definedSigs.add(rewrittenSig);
                    definedNames.add(extra.getSimpleName());
                }
            }
        }

        // Constructors are structural for non-strict mode only. In strict mode they must be WALA-selected.
        if (!STRICT_WALA_LINE_FIDELITY && type instanceof CtClass<?> ctClass) {
            for (CtConstructor<?> ctor : ctClass.getConstructors()) {
                CtMethod<?> wrappedCtor = wrapConstructorAsMethod(ctor, false);
                if (wrappedCtor == null) {
                    continue;
                }
                String sig = methodKey(wrappedCtor);
                if (!definedSigs.contains(sig)) {
                    clonedMethods.add(wrappedCtor);
                    definedSigs.add(sig);
                    definedNames.add(wrappedCtor.getSimpleName());
                }
            }
        }

        if (clonedMethods.isEmpty()) {
            System.out.println("  No methods resolved for " + simpleName + " — skipping");
            return;
        }

        // ── Phase 2: Transitive method closure (fixpoint) ──
        // Find this.xxx() calls not defined in this class or parent sliced classes
        Set<String> allKnownSigs = new LinkedHashSet<>(definedSigs);
        allKnownSigs.addAll(hierarchyDefinedMethods);

        int transitiveClosed = 0;
        int deferred = 0;
        if (!STRICT_WALA_LINE_FIDELITY) {
            boolean changed = true;
            while (changed) {
                changed = false;
                Set<MethodCall> unresolvedCalls = findUnresolvedThisCalls(type, clonedMethods, allKnownSigs);
                for (MethodCall call : unresolvedCalls) {
                    CtMethod<?> found = findMethodInHierarchy(call, type, typeIndex);
                    if (found != null) {
                        // Check if method belongs to a parent class that has a sliced version
                        CtType<?> declaringType = found.getDeclaringType();
                        String declaringName = declaringType != null ? declaringType.getSimpleName() : simpleName;
                        boolean belongsToParent = !declaringName.equals(simpleName)
                            && emittedSimpleNames.contains(declaringName);

                        String originalSig = methodKey(found);
                        CtMethod<?> cloned = found.clone();
                        rewriteMethodSignature(cloned, found);
                        relaxHelperVisibility(cloned);
                        String rewrittenSig = methodKey(cloned);
                        if (!definedSigs.contains(originalSig) && !definedSigs.contains(rewrittenSig)) {
                            if (belongsToParent) {
                                // Defer to parent — don't include body here
                                deferredMethods.computeIfAbsent(declaringName, k -> new ArrayList<>()).add(found);
                                definedSigs.add(originalSig);
                                definedSigs.add(rewrittenSig);
                                allKnownSigs.add(originalSig);
                                allKnownSigs.add(rewrittenSig);
                                deferred++;
                                changed = true;
                            } else {
                                clonedMethods.add(cloned);
                                definedSigs.add(originalSig);
                                definedSigs.add(rewrittenSig);
                                definedNames.add(call.name);
                                allKnownSigs.add(originalSig);
                                allKnownSigs.add(rewrittenSig);
                                transitiveClosed++;
                                changed = true;
                            }
                        }
                    }
                }
            }
        }
        if (transitiveClosed > 0 || deferred > 0) {
            System.out.println("  Transitively included " + transitiveClosed + " methods"
                + (deferred > 0 ? ", deferred " + deferred + " to parent classes" : ""));
        }

        // ── Phase 2b: Voidify non-void methods whose return statements were pruned ──
        voidifyUnusedReturns(clonedMethods, hierarchyDefinedMethods, prunedReturnMethods);

        // ── Phase 3: Remove @Override, deduplicate, apply AST rewrites ──
        List<CtMethod<?>> emittedMethods = new ArrayList<>();
        Set<String> emittedSigs = new LinkedHashSet<>();
        for (CtMethod<?> m : clonedMethods) {
            String sig = methodKey(m);
            if (emittedSigs.contains(sig)) continue;
            emittedSigs.add(sig);

            for (var ann : new ArrayList<>(m.getAnnotations())) {
                if (ann.getAnnotationType().getSimpleName().equals("Override")) {
                    ann.delete();
                }
            }

            // Remove javadoc comments (parameters may have been pruned, making them inaccurate)
            for (var comment : new ArrayList<>(m.getComments())) {
                comment.delete();
            }
            m.setDocComment(null);

            // Apply AST-based type rewrites (replaces text-based applyTextTypeRewrites)
            applyAstTypeRewrites(m, definedNames, simpleName, factory);
            emittedMethods.add(m);
        }
        if (STRICT_WALA_LINE_FIDELITY) {
            List<String> nonWalaMethods = new ArrayList<>();
            for (CtMethod<?> emitted : emittedMethods) {
                String key = methodKey(emitted);
                if (!walaSelectedMethodKeys.contains(key)) {
                    nonWalaMethods.add(key);
                }
            }
            if (!nonWalaMethods.isEmpty()) {
                throw new IllegalStateException("Strict WALA mode: non-WALA methods emitted for "
                    + simpleName + ": " + String.join(", ", nonWalaMethods.stream().limit(10).toList()));
            }
        }

        // ── Phase 5: Generate abstract stubs for remaining unresolved calls ──
        // Re-scan after transitive closure for still-unresolved methods
        Set<String> finalKnownSigs = new LinkedHashSet<>(allKnownSigs);
        Set<MethodCall> stillUnresolved = findUnresolvedThisCalls(type, clonedMethods, finalKnownSigs);
        List<CtMethod<?>> abstractStubMethods = new ArrayList<>();
        Set<String> stubNames = new LinkedHashSet<>();
        if (STRICT_WALA_LINE_FIDELITY) {
            if (!stillUnresolved.isEmpty()) {
                MethodCall first = stillUnresolved.iterator().next();
                throw new IllegalStateException("Strict WALA mode: unresolved self call in "
                    + simpleName + ": this." + first.name + "(...)");
            }
        } else {
            for (MethodCall call : stillUnresolved) {
                String callKey = methodKey(call.name, call.argTypes);
                if (stubNames.contains(callKey)) continue;
                // Find the method in the original MC hierarchy to get its signature
                CtMethod<?> originalMethod = findMethodInHierarchy(call, type, typeIndex);
                if (originalMethod != null) {
                    CtMethod<?> stub = generateAbstractStub(originalMethod, factory);
                    if (stub != null) {
                        String rewrittenSig = rewrittenMethodKey(originalMethod);
                        if (emittedSigs.contains(rewrittenSig)) {
                            continue;
                        }
                        abstractStubMethods.add(stub);
                        stubNames.add(callKey);
                        stubNames.add(methodKey(originalMethod));
                        stubNames.add(rewrittenSig);
                        definedNames.add(call.name);
                    }
                } else {
                    System.out.println("  WARNING: Cannot resolve this." + call.name
                        + "(" + call.argCount + " args) — no stub generated");
                }
            }
            if (!abstractStubMethods.isEmpty()) {
                System.out.println("  Generated " + abstractStubMethods.size() + " abstract stubs");
            }
        }

        // ── Phase 6: Build output ──
        List<CtMethod<?>> allMethods = new ArrayList<>(emittedMethods);
        allMethods.addAll(abstractStubMethods);
        List<CtField<?>> fieldDecls = extractFieldDeclarations(type, allMethods, typeIndex, hierarchyDefinedFields);
        if (STRICT_WALA_LINE_FIDELITY) {
            Set<String> referencedFieldNames = collectReferencedFieldNames(allMethods);
            List<String> extraFields = fieldDecls.stream()
                .map(CtField::getSimpleName)
                .filter(name -> !referencedFieldNames.contains(name))
                .toList();
            if (!extraFields.isEmpty()) {
                throw new IllegalStateException("Strict WALA mode: non-referenced fields emitted for "
                    + simpleName + ": " + String.join(", ", extraFields.stream().limit(10).toList()));
            }
        }
        rewriteTrackedDataFieldInitializers(fieldDecls, simpleName, factory);
        retargetStaticFieldAccessesToLocalSlicedType(allMethods, fieldDecls, simpleName);

        // Collect access widener entries for non-public static fields/methods
        collectAccessWidenerEntries(type, allMethods, fieldDecls, typeIndex, awEntries);

        Path outFile = outputDir.resolve(slicedName + ".java");
        Files.createDirectories(outFile.getParent());
        writeSlicedCompilationUnit(outFile, type, slicedName, extendsRef, fieldDecls,
            abstractStubMethods, emittedMethods, emittedSimpleNames, factory);
        System.out.println("  Wrote " + outFile.getFileName());

        // Update hierarchy tracking
        hierarchyDefinedMethods.addAll(definedSigs);
        hierarchyDefinedMethods.addAll(stubNames);
        for (CtField<?> fieldDecl : fieldDecls) {
            hierarchyDefinedFields.add(fieldDecl.getSimpleName());
        }
    }

    // ── Method resolution helpers ──

    private record MethodCall(String name, int argCount, List<String> argTypes) {}

    /**
     * Finds all this.xxx() invocations in the given methods that are not defined
     * in the known method signature set.
     */
    private Set<MethodCall> findUnresolvedThisCalls(CtType<?> ownerType,
                                                    List<CtMethod<?>> methods,
                                                    Set<String> knownSigs) {
        Set<MethodCall> unresolved = new LinkedHashSet<>();
        for (CtMethod<?> m : methods) {
            for (var inv : m.getElements(new TypeFilter<>(CtInvocation.class))) {
                var target = inv.getTarget();
                // In some model edge cases Spoon can still represent implicit-this
                // calls as CtTypeAccess. Treat target-hierarchy type accesses as
                // self-calls to keep method resolution stable.
                boolean isSelf = (target == null)
                    || (target instanceof CtThisAccess)
                    || (target instanceof CtSuperAccess)
                    || (target instanceof CtTypeAccess<?> typeAccess
                        && isDeclaringTypeInOwnerHierarchy(ownerType, typeAccess.getAccessedType()));
                if (!isSelf) continue;

                String rawName = inv.getExecutable().getSimpleName();
                if (rawName == null || rawName.contains(".")) {
                    continue;
                }
                String name = normalizeMethodName(rawName);
                if (repairPasses.shouldSkipUnresolvedThisCall(name)) {
                    continue;
                }
                CtTypeReference<?> declaringTypeRef = inv.getExecutable().getDeclaringType();
                if (declaringTypeRef != null && !isDeclaringTypeInOwnerHierarchy(ownerType, declaringTypeRef)) {
                    continue;
                }
                int argCount = inv.getArguments().size();
                List<String> argTypes = invocationArgTypes(inv);
                String sig = methodKey(name, argTypes);
                boolean declaredInHierarchy = hasMethodByNameAndArity(ownerType, name, argCount);
                if (!knownSigs.contains(sig)
                    && !hasCompatibleKnownSignature(name, argCount, knownSigs)
                    && !declaredInHierarchy) {
                    unresolved.add(new MethodCall(name, argCount, argTypes));
                }
            }
        }
        return unresolved;
    }

    private boolean hasMethodByNameAndArity(CtType<?> declaringType, String methodName, int argCount) {
        if (declaringType == null) {
            return false;
        }
        for (CtMethod<?> method : declaringType.getAllMethods()) {
            if (normalizeMethodName(method.getSimpleName()).equals(normalizeMethodName(methodName))
                && method.getParameters().size() == argCount) {
                return true;
            }
        }
        return false;
    }

    private boolean isDeclaringTypeInOwnerHierarchy(CtType<?> ownerType, CtTypeReference<?> declaringTypeRef) {
        if (ownerType == null || declaringTypeRef == null) {
            return false;
        }
        String declaringQualifiedName = declaringTypeRef.getQualifiedName();
        if (declaringQualifiedName == null || declaringQualifiedName.isBlank()) {
            return false;
        }
        CtType<?> current = ownerType;
        while (current instanceof CtClass<?> currentClass) {
            if (declaringQualifiedName.equals(current.getQualifiedName())) {
                return true;
            }
            CtTypeReference<?> superRef = currentClass.getSuperclass();
            if (superRef == null) {
                break;
            }
            CtType<?> superType = superRef.getTypeDeclaration();
            if (superType == null || superType == current) {
                break;
            }
            current = superType;
        }
        return false;
    }

    private boolean hasCompatibleKnownSignature(String methodName, int argCount, Set<String> knownSigs) {
        String prefix = normalizeMethodName(methodName) + "(";
        for (String known : knownSigs) {
            if (!known.startsWith(prefix) || !known.endsWith(")")) {
                continue;
            }
            int open = known.indexOf('(');
            String args = known.substring(open + 1, known.length() - 1);
            int knownArgCount = args.isBlank() ? 0 : args.split(",", -1).length;
            if (knownArgCount == argCount) {
                return true;
            }
        }
        return false;
    }

    /**
     * Searches the type hierarchy (within target classes) for the best matching overload.
     */
    private CtMethod<?> findMethodInHierarchy(MethodCall call,
                                              CtType<?> startType,
                                              Map<String, CtType<?>> typeIndex) {
        List<CtType<?>> hierarchy = new ArrayList<>();
        hierarchy.add(startType);

        if (startType instanceof CtClass<?> ctClass) {
            CtTypeReference<?> superRef = ctClass.getSuperclass();
            while (superRef != null) {
                CtType<?> superType = typeIndex.get(superRef.getQualifiedName());
                if (superType != null) {
                    hierarchy.add(superType);
                    if (superType instanceof CtClass<?> sc) {
                        superRef = sc.getSuperclass();
                    } else break;
                } else break;
            }
        }

        for (CtType<?> current : hierarchy) {
            CtMethod<?> found = findBestOverload(current, call);
            if (found != null) {
                return found;
            }
        }

        return null;
    }

    private CtMethod<?> findBestOverload(CtType<?> type, MethodCall call) {
        CtMethod<?> fallback = null;
        int bestScore = Integer.MIN_VALUE;

        for (CtMethod<?> method : type.getMethods()) {
            if (!normalizeMethodName(method.getSimpleName()).equals(call.name)
                || method.getParameters().size() != call.argCount) {
                continue;
            }

            if (fallback == null) {
                fallback = method;
            }

            int score = scoreOverload(method, call.argTypes);
            if (score > bestScore) {
                bestScore = score;
                fallback = method;
            }
        }

        // Note: intentionally NOT using getAllMethods() here.
        // getMethods() returns only declared methods, so findMethodInHierarchy
        // walks up and finds each method in its declaring class, ensuring
        // correct placement in the sliced hierarchy.

        return fallback;
    }

    private int scoreOverload(CtMethod<?> method, List<String> argTypes) {
        int score = 0;
        List<CtParameter<?>> params = method.getParameters();
        for (int i = 0; i < params.size(); i++) {
            String paramType = typeKey(params.get(i).getType());
            String argType = (i < argTypes.size()) ? argTypes.get(i) : "?";

            if (argType.equals(paramType)) {
                score += 4;
            } else if ("null".equals(argType) && !isPrimitiveType(paramType)) {
                score += 2;
            } else if ("?".equals(argType)) {
                score += 1;
            } else if (rewriteTypeName(argType).equals(rewriteTypeName(paramType))) {
                score += 3;
            } else {
                score -= 2;
            }
        }
        return score;
    }

    private boolean isPrimitiveType(String typeName) {
        return switch (typeName) {
            case "boolean", "byte", "char", "short", "int", "long", "float", "double" -> true;
            default -> false;
        };
    }

    private List<String> invocationArgTypes(CtInvocation<?> invocation) {
        List<String> argTypes = new ArrayList<>();
        for (CtExpression<?> arg : invocation.getArguments()) {
            CtTypeReference<?> type = arg.getType();
            argTypes.add(type != null ? typeKey(type) : inferArgType(arg));
        }
        return argTypes;
    }

    private String inferArgType(CtExpression<?> arg) {
        if (arg instanceof CtLiteral<?> literal && literal.getValue() == null) {
            return "null";
        }
        if (arg instanceof CtConstructorCall<?> ctor) {
            return typeKey(ctor.getType());
        }
        if (arg instanceof CtVariableAccess<?> access && access.getType() != null) {
            return typeKey(access.getType());
        }
        return "?";
    }

    private String methodKey(CtMethod<?> method) {
        List<String> paramTypes = new ArrayList<>();
        for (CtParameter<?> parameter : method.getParameters()) {
            paramTypes.add(typeKey(parameter.getType()));
        }
        return methodKey(method.getSimpleName(), paramTypes);
    }

    private String methodKey(String name, List<String> argTypes) {
        return normalizeMethodName(name) + "(" + String.join(",", argTypes) + ")";
    }

    private String normalizeMethodName(String name) {
        if (name == null) {
            return "";
        }
        String noWhitespace = name.replaceAll("\\s+", "");
        StringBuilder normalized = new StringBuilder(noWhitespace.length());
        for (int i = 0; i < noWhitespace.length(); i++) {
            char ch = noWhitespace.charAt(i);
            if (Character.isIdentifierIgnorable(ch)) {
                continue;
            }
            if (Character.isJavaIdentifierPart(ch) || ch == '<' || ch == '>') {
                normalized.append(ch);
            }
        }
        return normalized.toString();
    }

    private String typeKey(CtTypeReference<?> ref) {
        if (ref == null) return "?";
        return ref.getSimpleName();
    }

    // ── Type reference rewriting ──

    /**
     * Rewrites MC entity type references to Sliced types in method PARAMETERS only.
     * This is intentionally targeted — we don't rewrite return types, local variables,
     * inner class references (Entity.MoveEffect), static field access, etc.
     */
    private void rewriteParameterTypes(CtMethod<?> method, CtMethod<?> originalMethod) {
        if (!shouldRewriteParameterTypes(originalMethod)) {
            return;
        }
        for (CtParameter<?> param : method.getParameters()) {
            CtTypeReference<?> ref = param.getType();
            remapTypeReferenceToSliced(ref);
        }
    }

    private void rewriteMethodSignature(CtMethod<?> method, CtMethod<?> originalMethod) {
        rewriteParameterTypes(method, originalMethod);
    }

    private void relaxHelperVisibility(CtMethod<?> method) {
        if (method.hasModifier(ModifierKind.FINAL)) {
            method.removeModifier(ModifierKind.FINAL);
        }
        if (!SUBCLASS_VISIBLE_HELPERS.contains(method.getSimpleName())) {
            return;
        }
        if (method.hasModifier(ModifierKind.PRIVATE)) {
            method.removeModifier(ModifierKind.PRIVATE);
            method.addModifier(ModifierKind.PROTECTED);
        }
    }

    // ── AST-based type rewrites (replaces former text-based applyTextTypeRewrites) ──

    /**
     * Creates a CtFieldRead for {@code this.entityBridge}.
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    private CtExpression<?> createEntityBridgeRead(Factory factory) {
        CtFieldRead read = factory.Core().createFieldRead();
        read.setTarget(factory.Code().createThisAccess(factory.Type().objectType()));
        CtFieldReference ref = factory.Core().createFieldReference();
        ref.setSimpleName("entityBridge");
        read.setVariable(ref);
        return read;
    }

    /**
     * Creates {@code (mcTypeName) this.entityBridge} — a cast bridge read.
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    private CtExpression<?> createCastEntityBridgeRead(Factory factory, String mcTypeName) {
        CtFieldRead read = factory.Core().createFieldRead();
        read.setTarget(factory.Code().createThisAccess(factory.Type().objectType()));
        CtFieldReference ref = factory.Core().createFieldReference();
        ref.setSimpleName("entityBridge");
        read.setVariable(ref);
        CtTypeReference castType = factory.Core().createTypeReference();
        castType.setSimpleName(mcTypeName);
        read.addTypeCast(castType);
        return read;
    }

    /**
     * True if the CtThisAccess is a bare {@code this} used as an argument
     * (not as a method/field qualifier).
     */
    private boolean isThisInArgumentPosition(CtThisAccess<?> thisAccess) {
        if (!thisAccess.getTypeCasts().isEmpty()) return false;
        CtElement parent = thisAccess.getParent();
        if (parent instanceof CtInvocation<?> inv) {
            return inv.getArguments().contains(thisAccess);
        }
        if (parent instanceof CtConstructorCall<?> call) {
            return call.getArguments().contains(thisAccess);
        }
        return false;
    }

    /**
     * Replaces bare {@code this} in argument positions with
     * {@code (mcTypeName) this.entityBridge} within the subtree.
     */
    private void replaceThisInArgPositions(CtElement root, Factory factory, String mcTypeName) {
        for (CtThisAccess<?> ta : new ArrayList<CtThisAccess<?>>(root.getElements(new TypeFilter<CtThisAccess<?>>(CtThisAccess.class)))) {
            if (isThisInArgumentPosition(ta)) {
                ta.replace(createCastEntityBridgeRead(factory, mcTypeName));
            }
        }
    }

    /**
     * Applies all type rewrites on a method's AST in-place.
     * Replaces the former text-based applyTextTypeRewrites method.
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    private void applyAstTypeRewrites(CtMethod<?> method, Set<String> definedNames,
                                       String mcTypeName, Factory factory) {
        if (method.getBody() == null) return;

        // 2-3. instanceof rewrites
        for (CtBinaryOperator<?> binOp : new ArrayList<>(method.getElements(new TypeFilter<CtBinaryOperator<?>>(CtBinaryOperator.class)))) {
            if (binOp.getKind() != BinaryOperatorKind.INSTANCEOF) continue;
            if (!(binOp.getLeftHandOperand() instanceof CtThisAccess<?> lhs) || !lhs.getTypeCasts().isEmpty()) continue;

            CtExpression<?> rhs = binOp.getRightHandOperand();
            String typeName = null;
            if (rhs instanceof CtTypeAccess<?> ta) {
                typeName = ta.getAccessedType().getSimpleName();
            }
            if (typeName == null) continue;

            if (TYPE_REMAP.containsKey(typeName)) {
                // this instanceof Entity -> this instanceof SlicedEntity
                remapTypeReferenceToSliced(((CtTypeAccess<?>) rhs).getAccessedType());
            }
        }

        // 4. Cast rewrites: (Entity) this -> (SlicedEntity) this
        for (CtThisAccess<?> ta : new ArrayList<CtThisAccess<?>>(method.getElements(new TypeFilter<CtThisAccess<?>>(CtThisAccess.class)))) {
            for (CtTypeReference<?> cast : ta.getTypeCasts()) {
                remapTypeReferenceToSliced(cast);
            }
        }

        // 5. Static/self type accesses used as invocation/field targets:
        // ClientPlayerEntity.foo() -> SlicedClientPlayerEntity.foo()
        // ClientPlayerEntity.SOME_FIELD -> SlicedClientPlayerEntity.SOME_FIELD
        // (but keep class literals like PlayerEntity.class unchanged)
        for (CtTypeAccess<?> typeAccess : new ArrayList<>(method.getElements(new TypeFilter<CtTypeAccess<?>>(CtTypeAccess.class)))) {
            CtElement parent = typeAccess.getParent();
            if (parent instanceof CtInvocation<?> invocation && invocation.getTarget() == typeAccess) {
                remapTypeReferenceToSliced(typeAccess.getAccessedType());
                continue;
            }
            if (parent instanceof CtFieldAccess<?> fieldAccess
                && fieldAccess.getTarget() == typeAccess
                && !isClassLiteralFieldAccess(fieldAccess)) {
                remapTypeReferenceToSliced(typeAccess.getAccessedType());
            }
        }
    }

    private boolean isClassLiteralFieldAccess(CtFieldAccess<?> fieldAccess) {
        CtFieldReference<?> variable = fieldAccess != null ? fieldAccess.getVariable() : null;
        return variable != null && "class".equals(variable.getSimpleName());
    }

    private void retargetStaticFieldAccessesToLocalSlicedType(List<CtMethod<?>> methods,
                                                              List<CtField<?>> fieldDecls,
                                                              String mcTypeName) {
        if (methods == null || methods.isEmpty() || fieldDecls == null || fieldDecls.isEmpty()) {
            return;
        }
        Set<String> localStaticFieldNames = new HashSet<>();
        for (CtField<?> field : fieldDecls) {
            if (field != null && field.hasModifier(ModifierKind.STATIC)) {
                localStaticFieldNames.add(field.getSimpleName());
            }
        }
        if (localStaticFieldNames.isEmpty()) {
            return;
        }

        String localSlicedType = rewriteTypeName(mcTypeName);
        for (CtMethod<?> method : methods) {
            for (CtFieldAccess<?> fieldAccess : method.getElements(new TypeFilter<>(CtFieldAccess.class))) {
                if (isClassLiteralFieldAccess(fieldAccess)) {
                    continue;
                }
                if (!(fieldAccess.getTarget() instanceof CtTypeAccess<?> targetTypeAccess)) {
                    continue;
                }
                CtTypeReference<?> targetType = targetTypeAccess.getAccessedType();
                if (targetType == null || !targetType.getSimpleName().startsWith("Sliced")) {
                    continue;
                }
                CtFieldReference<?> variable = fieldAccess.getVariable();
                if (variable == null || !localStaticFieldNames.contains(variable.getSimpleName())) {
                    continue;
                }
                if (!localSlicedType.equals(targetType.getSimpleName())) {
                    targetType.setSimpleName(localSlicedType);
                    targetType.setPackage(targetType.getFactory().Package()
                        .getOrCreate("murat.simv2.simulation.sliced").getReference());
                    targetType.setDeclaringType(null);
                }
            }
        }
    }

    // ── Abstract stub generation ──

    /**
     * Generates an abstract method stub as a CtMethod from an original MC method.
     * Applies type rewriting to the stub parameters.
     */
    private CtMethod<?> generateAbstractStub(CtMethod<?> original, Factory factory) {
        CtMethod<?> stub = factory.createMethod();
        stub.addModifier(ModifierKind.PUBLIC);
        stub.addModifier(ModifierKind.ABSTRACT);
        stub.setType(original.getType().clone());
        stub.setSimpleName(original.getSimpleName());

        for (CtParameter<?> p : original.getParameters()) {
            CtParameter<?> clonedParam = p.clone();
            if (shouldRewriteParameterTypes(original)) {
                CtTypeReference<?> ref = clonedParam.getType();
                remapTypeReferenceToSliced(ref);
            }
            stub.addParameter(clonedParam);
        }

        return stub;
    }

    /** Applies TYPE_REMAP to a simple type name if it matches. */
    private String rewriteTypeName(String simpleName) {
        return TYPE_REMAP.getOrDefault(simpleName, simpleName);
    }

    private void remapTypeReferenceToSliced(CtTypeReference<?> ref) {
        if (ref == null) {
            return;
        }
        String replacement = TYPE_REMAP.get(ref.getSimpleName());
        if (replacement == null) {
            return;
        }
        CtTypeReference<?> slicedRef = ref.getFactory().Type()
            .createSimplyQualifiedReference("murat.simv2.simulation.sliced." + replacement);
        ref.setSimpleName(slicedRef.getSimpleName());
        ref.setPackage(slicedRef.getPackage());
        ref.setDeclaringType(null);
    }

    private boolean shouldRewriteParameterTypes(CtMethod<?> method) {
        if (method == null) return false;
        CtType<?> declaringType = method.getDeclaringType();
        if (declaringType == null) return false;
        return SLICED_PARAM_METHODS.contains(declaringType.getSimpleName() + "#" + method.getSimpleName());
    }

    private String rewrittenMethodKey(CtMethod<?> method) {
        List<String> paramTypes = new ArrayList<>();
        boolean rewriteParams = shouldRewriteParameterTypes(method);
        for (CtParameter<?> parameter : method.getParameters()) {
            String typeName = typeKey(parameter.getType());
            paramTypes.add(rewriteParams ? rewriteTypeName(typeName) : typeName);
        }
        return methodKey(method.getSimpleName(), paramTypes);
    }

    // ── Statement pruning ──

    private void pruneByWalaLines(CtMethod<?> method, Set<Integer> walaLines) {
        if (method.getBody() == null) return;

        Set<String> neededVars = new HashSet<>();
        for (CtStatement stmt : method.getBody().getElements(new TypeFilter<>(CtStatement.class))) {
            if (stmt.getPosition() == null || !stmt.getPosition().isValidPosition()) continue;
            if (!walaLines.contains(stmt.getPosition().getLine())
                    && !isSlicedOrHasSlicedDescendant(stmt, walaLines)) continue;
            for (var varAccess : stmt.getElements(new TypeFilter<>(CtVariableAccess.class))) {
                neededVars.add(((CtVariableAccess<?>) varAccess).getVariable().getSimpleName());
            }
        }

        pruneBlock(method.getBody(), walaLines, neededVars);
        removeEmptyBlocks(method.getBody());
        initializeUninitializedLocals(method);
    }

    private void pruneBlock(CtBlock<?> block, Set<Integer> walaLines, Set<String> neededVars) {
        if (block == null) return;

        List<CtStatement> toDelete = new ArrayList<>();
        for (CtStatement stmt : new ArrayList<>(block.getStatements())) {
            recurseIntoChildren(stmt, walaLines, neededVars);

            if (isSlicedOrHasSlicedDescendant(stmt, walaLines)) continue;

            if (stmt instanceof CtLocalVariable<?> localVar) {
                if (neededVars.contains(localVar.getSimpleName())) continue;
            }

            toDelete.add(stmt);
        }

        for (CtStatement stmt : toDelete) {
            try {
                stmt.delete();
            } catch (Exception e) {
                // Already removed
            }
        }
    }

    private void recurseIntoChildren(CtStatement stmt, Set<Integer> walaLines, Set<String> neededVars) {
        if (stmt instanceof CtIf ctIf) {
            if (ctIf.getThenStatement() instanceof CtBlock<?> thenBlock) {
                pruneBlock(thenBlock, walaLines, neededVars);
            }
            if (ctIf.getElseStatement() instanceof CtBlock<?> elseBlock) {
                pruneBlock(elseBlock, walaLines, neededVars);
            }
        } else if (stmt instanceof CtLoop loop) {
            if (loop.getBody() instanceof CtBlock<?> body) {
                pruneBlock(body, walaLines, neededVars);
            }
        } else if (stmt instanceof CtBlock<?> nested) {
            pruneBlock(nested, walaLines, neededVars);
        }
    }

    private boolean isSlicedOrHasSlicedDescendant(CtStatement stmt, Set<Integer> walaLines) {
        if (stmt.getPosition() != null && stmt.getPosition().isValidPosition()) {
            if (walaLines.contains(stmt.getPosition().getLine())) {
                return true;
            }
        }
        List<CtStatement> descendants = stmt.getElements(new TypeFilter<>(CtStatement.class));
        for (CtStatement desc : descendants) {
            if (desc == stmt) continue;
            if (desc.getPosition() != null && desc.getPosition().isValidPosition()) {
                if (walaLines.contains(desc.getPosition().getLine())) {
                    return true;
                }
            }
        }
        return false;
    }

    private void removeEmptyBlocks(CtBlock<?> block) {
        if (block == null) return;
        boolean changed = true;
        while (changed) {
            changed = false;
            for (CtStatement stmt : new ArrayList<>(block.getElements(new TypeFilter<CtStatement>(CtStatement.class)))) {
                if (stmt instanceof CtIf ctIf) {
                    boolean thenEmpty = isEmpty(ctIf.getThenStatement());
                    boolean elseEmpty = ctIf.getElseStatement() == null || isEmpty(ctIf.getElseStatement());

                    if (thenEmpty && elseEmpty) {
                        try { ctIf.delete(); changed = true; } catch (Exception ignored) {}
                    } else if (elseEmpty && ctIf.getElseStatement() != null) {
                        ctIf.setElseStatement(null);
                        changed = true;
                    }
                }
            }
        }
    }

    private boolean isEmpty(CtStatement stmt) {
        if (stmt == null) return true;
        if (stmt instanceof CtBlock<?> b) return b.getStatements().isEmpty();
        return false;
    }

    @SuppressWarnings("unchecked")
    private void initializeUninitializedLocals(CtMethod<?> method) {
        if (method.getBody() == null) return;
        Factory factory = method.getFactory();
        for (CtLocalVariable<?> localVar : method.getBody().getElements(new TypeFilter<>(CtLocalVariable.class))) {
            if (localVar.getDefaultExpression() != null) continue;
            if (!(localVar.getParent() instanceof CtBlock)) continue;
            CtTypeReference<?> type = localVar.getType();
            if (type == null) continue;
            CtExpression<?> defaultValue = createDefaultValue(factory, type);
            if (defaultValue != null) {
                ((CtLocalVariable<Object>) localVar).setDefaultExpression((CtExpression<Object>) defaultValue);
            }
        }
    }

    private CtExpression<?> createDefaultValue(Factory factory, CtTypeReference<?> type) {
        return switch (type.getSimpleName()) {
            case "boolean" -> factory.createLiteral(false);
            case "byte"    -> factory.createLiteral((byte) 0);
            case "char"    -> factory.createLiteral((char) 0);
            case "short"   -> factory.createLiteral((short) 0);
            case "int"     -> factory.createLiteral(0);
            case "long"    -> factory.createLiteral(0L);
            case "float"   -> factory.createLiteral(0.0F);
            case "double"  -> factory.createLiteral(0.0);
            default        -> factory.createLiteral(null);
        };
    }

    @SuppressWarnings("unchecked")
    private void addDefaultReturn(CtMethod<?> method) {
        Factory factory = method.getFactory();
        CtTypeReference<?> returnType = method.getType();
        CtExpression<?> defaultValue = createDefaultValue(factory, returnType);
        if (defaultValue instanceof CtLiteral<?> literal && literal.getValue() == null) {
            CtExpression<?> localFallback = findLocalReturnFallback(method, returnType, factory);
            if (localFallback != null) {
                defaultValue = localFallback;
            }
        }
        CtReturn<?> ret = factory.createReturn();
        ((CtReturn<Object>) ret).setReturnedExpression((CtExpression<Object>) defaultValue);
        method.getBody().addStatement(ret);
    }

    private CtExpression<?> findLocalReturnFallback(CtMethod<?> method,
                                                    CtTypeReference<?> returnType,
                                                    Factory factory) {
        if (method.getBody() == null || returnType == null || returnType.isPrimitive()) {
            return null;
        }
        List<CtStatement> statements = method.getBody().getStatements();
        for (int i = statements.size() - 1; i >= 0; i--) {
            CtStatement statement = statements.get(i);
            if (!(statement instanceof CtLocalVariable<?> localVar)) {
                continue;
            }
            CtTypeReference<?> localType = localVar.getType();
            if (localType == null) {
                continue;
            }
            if (returnType.getSimpleName().equals(localType.getSimpleName())) {
                return factory.Code().createCodeSnippetExpression(localVar.getSimpleName());
            }
        }
        return null;
    }

    private void voidifyUnusedReturns(List<CtMethod<?>> methods,
                                      Set<String> hierarchyDefinedMethods,
                                      Set<String> prunedReturnMethods) {
        // Collect names of methods that lost ALL return statements after pruning
        Set<String> noReturnMethods = new HashSet<>();
        for (CtMethod<?> m : methods) {
            if (m.getBody() == null) continue;
            if (m.getType() == null || m.getType().getSimpleName().equals("void")) continue;
            if (m.getBody().getElements(new TypeFilter<>(CtReturn.class)).isEmpty()) {
                noReturnMethods.add(m.getSimpleName());
            }
        }

        if (!noReturnMethods.isEmpty()) {
            // Check if any method in the class uses the return value of a no-return method
            Set<String> returnValueUsed = new HashSet<>();
            for (CtMethod<?> m : methods) {
                if (m.getBody() == null) continue;
                for (CtInvocation<?> inv : m.getBody().getElements(new TypeFilter<>(CtInvocation.class))) {
                    String calledName = inv.getExecutable().getSimpleName();
                    if (noReturnMethods.contains(calledName) && !(inv.getParent() instanceof CtBlock)) {
                        returnValueUsed.add(calledName);
                    }
                }
            }

            // Voidify methods whose return value is never used;
            // add default return for methods whose return value IS used or that override a parent
            for (CtMethod<?> m : methods) {
                if (!noReturnMethods.contains(m.getSimpleName())) continue;
                boolean overridesParent = hierarchyDefinedMethods.contains(methodKey(m));
                if (returnValueUsed.contains(m.getSimpleName()) || overridesParent) {
                    addDefaultReturn(m);
                } else {
                    m.setType(m.getFactory().Type().voidPrimitiveType());
                }
            }
        }

        // Add default return for methods that lost SOME (not all) returns during
        // WALA pruning and can now fall through without returning.
        for (CtMethod<?> m : methods) {
            if (m.getBody() == null) continue;
            if (m.getType() == null || m.getType().getSimpleName().equals("void")) continue;
            if (!prunedReturnMethods.contains(methodKey(m))) continue;
            if (m.getBody().getElements(new TypeFilter<>(CtReturn.class)).isEmpty()) continue;
            List<CtStatement> stmts = m.getBody().getStatements();
            if (stmts.isEmpty()) continue;
            CtStatement last = stmts.get(stmts.size() - 1);
            if (!(last instanceof CtReturn)) {
                addDefaultReturn(m);
            }
        }
    }

    // ── Hierarchy and method resolution ──

    private CtTypeReference<?> resolveExtendsType(CtType<?> type, Set<String> emittedSimpleNames,
                                                  Map<String, CtType<?>> typeIndex,
                                                  Factory factory) {
        if (!(type instanceof CtClass<?> ctClass)) return null;

        CtTypeReference<?> superRef = ctClass.getSuperclass();
        while (superRef != null) {
            String superSimpleName = superRef.getSimpleName();
            if (emittedSimpleNames.contains(superSimpleName)) {
                return factory.Type().createSimplyQualifiedReference(
                    "murat.simv2.simulation.sliced.Sliced" + superSimpleName);
            }
            CtType<?> superType = typeIndex.get(superRef.getQualifiedName());
            if (superType instanceof CtClass<?> superClass) {
                superRef = superClass.getSuperclass();
            } else {
                break;
            }
        }
        return null;
    }

    private CtMethod<?> findMethod(CtType<?> type, String methodName, String descriptor) {
        if (methodName.equals("<init>")) {
            return findConstructorAsMethod(type, descriptor);
        }
        int paramCount = countDescriptorParams(descriptor);
        for (CtMethod<?> m : type.getMethods()) {
            if (m.getSimpleName().equals(methodName)) {
                if (m.getParameters().size() == paramCount && getMethodDescriptor(m).equals(descriptor)) {
                    return m;
                }
            }
        }

        for (CtMethod<?> m : type.getMethods()) {
            if (m.getSimpleName().equals(methodName) && m.getParameters().size() == paramCount) {
                return m;
            }
        }

        return null;
    }

    /**
     * Find a constructor matching the given descriptor and wrap it as a {@code CtMethod}
     * named {@code constructorInit} so the rest of the slicing pipeline can process it.
     */
    private CtMethod<?> findConstructorAsMethod(CtType<?> type, String descriptor) {
        if (!(type instanceof CtClass<?> ctClass)) return null;
        int paramCount = countDescriptorParams(descriptor);
        CtConstructor<?> match = null;

        // Try exact descriptor match first
        for (CtConstructor<?> ctor : ctClass.getConstructors()) {
            if (ctor.getParameters().size() == paramCount && getConstructorDescriptor(ctor).equals(descriptor)) {
                match = ctor;
                break;
            }
        }
        // Fall back to param-count match
        if (match == null) {
            for (CtConstructor<?> ctor : ctClass.getConstructors()) {
                if (ctor.getParameters().size() == paramCount) {
                    match = ctor;
                    break;
                }
            }
        }
        if (match == null) return null;

        return wrapConstructorAsMethod(match, true);
    }

    private CtMethod<?> wrapConstructorAsMethod(CtConstructor<?> match, boolean includeFullBody) {
        if (match == null) {
            return null;
        }
        Factory f = match.getFactory();
        CtMethod<?> method = f.createMethod();
        method.setSimpleName("constructorInit");
        method.setType(f.Type().voidPrimitiveType());
        method.setModifiers(match.getModifiers());
        for (CtParameter<?> p : match.getParameters()) {
            method.addParameter(p.clone());
        }
        if (includeFullBody && match.getBody() != null) {
            method.setBody(match.getBody().clone());
        } else {
            method.setBody(f.createBlock());
            CtStatement delegation = findConstructorDelegationStatement(match);
            if (delegation != null) {
                method.getBody().addStatement(delegation.clone());
            }
        }
        method.setPosition(match.getPosition());
        return method;
    }

    private String getConstructorDescriptor(CtConstructor<?> ctor) {
        StringBuilder sb = new StringBuilder("(");
        for (CtParameter<?> p : ctor.getParameters()) {
            sb.append(toDescriptor(p.getType()));
        }
        sb.append(")V");
        return sb.toString();
    }

    private int countDescriptorParams(String descriptor) {
        String params = descriptor.substring(1, descriptor.indexOf(')'));
        if (params.isEmpty()) return 0;
        int count = 0;
        int i = 0;
        while (i < params.length()) {
            char c = params.charAt(i);
            if (c == 'L') {
                i = params.indexOf(';', i) + 1;
                count++;
            } else if (c == '[') {
                i++;
            } else {
                i++;
                count++;
            }
        }
        return count;
    }

    // ── Field and import handling ──

    private List<CtField<?>> extractFieldDeclarations(CtType<?> type, List<CtMethod<?>> methods,
                                                       Map<String, CtType<?>> typeIndex,
                                                       Set<String> inheritedFieldNames) {
        return fieldPlanner.extractFieldDeclarations(
            type,
            methods,
            typeIndex,
            inheritedFieldNames,
            !STRICT_WALA_LINE_FIDELITY
        );
    }

    private Set<String> collectReferencedFieldNames(List<CtMethod<?>> methods) {
        Set<String> referenced = new HashSet<>();
        if (methods == null) {
            return referenced;
        }
        for (CtMethod<?> method : methods) {
            for (CtFieldAccess<?> fieldAccess : method.getElements(new TypeFilter<>(CtFieldAccess.class))) {
                if (fieldAccess.getVariable() != null) {
                    referenced.add(fieldAccess.getVariable().getSimpleName());
                }
            }
        }
        return referenced;
    }

    private void rewriteTrackedDataFieldInitializers(List<CtField<?>> fieldDecls,
                                                     String sourceSimpleName,
                                                     Factory factory) {
        fieldPlanner.rewriteTrackedDataFieldInitializers(fieldDecls, sourceSimpleName, factory);
    }

    private void writeSlicedCompilationUnit(Path outFile,
                                            CtType<?> sourceType,
                                            String slicedName,
                                            CtTypeReference<?> extendsRef,
                                            List<CtField<?>> fieldDecls,
                                            List<CtMethod<?>> abstractStubMethods,
                                            List<CtMethod<?>> emittedMethods,
                                            Set<String> emittedSimpleNames,
                                            Factory factory) throws IOException {
        CtPackage outputPackage = factory.Package().getOrCreate("murat.simv2.simulation.sliced");
        CtType<?> existingType = outputPackage.getType(slicedName);
        if (existingType != null) {
            outputPackage.removeType(existingType);
        }
        List<CtType<?>> placeholderTypes = ensureSlicedTypePlaceholders(outputPackage, emittedSimpleNames, factory);

        CtClass<?> slicedClass = factory.Core().createClass();
        slicedClass.setSimpleName(slicedName);
        slicedClass.addModifier(ModifierKind.PUBLIC);
        slicedClass.addModifier(ModifierKind.ABSTRACT);
        slicedClass.addComment(factory.Code().createComment(
            "Sliced from " + sourceType.getQualifiedName(), CtComment.CommentType.INLINE));
        slicedClass.addComment(factory.Code().createComment(
            "Movement-relevant statements only (WALA backward slice + Spoon AST pruning)",
            CtComment.CommentType.INLINE));
        slicedClass.addComment(factory.Code().createComment(
            "Generated - do not edit", CtComment.CommentType.INLINE));
        if (extendsRef != null) {
            slicedClass.setSuperclass(extendsRef);
        }

        outputPackage.addType(slicedClass);
        try {

            for (CtField<?> field : fieldDecls) {
                slicedClass.addField(field);
            }
            for (CtMethod<?> stub : abstractStubMethods) {
                if (isSyntheticConstructorMethod(stub)) {
                    ((CtClass) slicedClass).addConstructor(convertSyntheticConstructorMethod(stub, extendsRef != null, factory));
                    continue;
                }
                slicedClass.addMethod(stub);
            }
            for (CtMethod<?> method : emittedMethods) {
                if (isSyntheticConstructorMethod(method)) {
                    ((CtClass) slicedClass).addConstructor(convertSyntheticConstructorMethod(method, extendsRef != null, factory));
                    continue;
                }
                slicedClass.addMethod(method);
            }

            CompilationUnit compilationUnit = factory.Core().createCompilationUnit();
            compilationUnit.setFile(outFile.toFile());
            compilationUnit.setDeclaredPackage(outputPackage);
            compilationUnit.addDeclaredType(slicedClass);
            compilationUnit.setImports(createSlicedCompilationUnitImports(factory, fieldDecls,
                abstractStubMethods, emittedMethods));
            slicedClass.setPosition(factory.createPartialSourcePosition(compilationUnit));
            removeJavadocsFromGeneratedClass(slicedClass);

            PrettyPrinter printer = createImportCleanerPrettyPrinter(factory);
            String source = printer.printCompilationUnit(compilationUnit);
            Files.writeString(outFile, source);
        } finally {
            outputPackage.removeType(slicedClass);
            for (CtType<?> placeholderType : placeholderTypes) {
                outputPackage.removeType(placeholderType);
            }
        }
    }

    private void addEntityBridgeBootstrapSupport(CtClass<?> slicedClass, Factory factory) {
        CtField<?> entityBridgeField = factory.createField();
        entityBridgeField.setSimpleName("entityBridge");
        entityBridgeField.setType(factory.Type().createReference("net.minecraft.entity.Entity"));
        entityBridgeField.addModifier(ModifierKind.PROTECTED);
        entityBridgeField.setDocComment(
            "Real MC entity used as bridge for World and external API calls.");
        slicedClass.addField(entityBridgeField);

        CtField<?> bootstrapField = factory.createField();
        bootstrapField.setSimpleName("ENTITY_BRIDGE_BOOTSTRAP");
        bootstrapField.setType(factory.Type().createReference("java.lang.ThreadLocal"));
        bootstrapField.addModifier(ModifierKind.PRIVATE);
        bootstrapField.addModifier(ModifierKind.STATIC);
        bootstrapField.addModifier(ModifierKind.FINAL);
        bootstrapField.setDefaultExpression(factory.Code().createCodeSnippetExpression("new ThreadLocal<>()"));
        slicedClass.addField(bootstrapField);

        CtMethod<Void> pushBootstrap = factory.createMethod();
        pushBootstrap.setSimpleName("pushEntityBridgeBootstrap");
        pushBootstrap.addModifier(ModifierKind.PUBLIC);
        pushBootstrap.addModifier(ModifierKind.STATIC);
        pushBootstrap.setType(factory.Type().createReference(void.class));

        CtParameter<?> entityParameter = factory.Core().createParameter();
        entityParameter.setType(factory.Type().createReference("net.minecraft.entity.Entity"));
        entityParameter.setSimpleName("entity");
        pushBootstrap.addParameter(entityParameter);

        CtBlock<Void> pushBody = factory.createBlock();
        pushBody.addStatement(factory.Code().createCodeSnippetStatement("ENTITY_BRIDGE_BOOTSTRAP.set(entity)"));
        pushBootstrap.setBody(pushBody);
        slicedClass.addMethod(pushBootstrap);

        CtMethod<?> claimBootstrap = factory.createMethod();
        claimBootstrap.setSimpleName("claimEntityBridgeBootstrap");
        claimBootstrap.addModifier(ModifierKind.PRIVATE);
        claimBootstrap.addModifier(ModifierKind.STATIC);
        claimBootstrap.setType(factory.Type().createReference("net.minecraft.entity.Entity"));

        CtBlock<?> claimBody = factory.createBlock();
        claimBody.addStatement(factory.Code().createCodeSnippetStatement(
            "net.minecraft.entity.Entity entity = (net.minecraft.entity.Entity) ENTITY_BRIDGE_BOOTSTRAP.get()"));
        claimBody.addStatement(factory.Code().createCodeSnippetStatement("ENTITY_BRIDGE_BOOTSTRAP.remove()"));
        claimBody.addStatement(factory.Code().createCodeSnippetStatement(
            "if (entity == null) throw new IllegalStateException(\"Missing entity bridge bootstrap for sliced construction\")"));
        claimBody.addStatement(factory.Code().createCodeSnippetStatement("return entity"));
        claimBootstrap.setBody((CtBlock) claimBody);
        slicedClass.addMethod((CtMethod<?>) claimBootstrap);
    }

    private void injectEntityBridgeBootstrapIntoRootConstructors(CtClass<?> slicedClass, Factory factory) {
        for (CtConstructor<?> constructor : slicedClass.getConstructors()) {
            CtBlock<?> body = constructor.getBody();
            if (body == null) {
                continue;
            }
            if (!body.getStatements().isEmpty()) {
                CtStatement first = body.getStatements().get(0);
                if (first.toString().contains("claimEntityBridgeBootstrap()")) {
                    continue;
                }
            }
            body.insertBegin(factory.Code().createCodeSnippetStatement("this.entityBridge = claimEntityBridgeBootstrap()"));
        }
    }

    private List<CtType<?>> ensureSlicedTypePlaceholders(CtPackage outputPackage,
                                                         Set<String> emittedSimpleNames,
                                                         Factory factory) {
        List<CtType<?>> placeholders = new ArrayList<>();
        for (String emittedSimpleName : emittedSimpleNames) {
            String slicedSimpleName = "Sliced" + emittedSimpleName;
            if (outputPackage.getType(slicedSimpleName) != null) {
                continue;
            }
            CtClass<?> placeholder = factory.Core().createClass();
            placeholder.setSimpleName(slicedSimpleName);
            placeholder.addModifier(ModifierKind.PUBLIC);
            placeholder.addModifier(ModifierKind.ABSTRACT);
            outputPackage.addType(placeholder);
            placeholders.add(placeholder);
        }
        return placeholders;
    }

    private boolean isSyntheticConstructorMethod(CtMethod<?> method) {
        return method != null && "constructorInit".equals(method.getSimpleName());
    }

    private void preserveConstructorDelegation(CtMethod<?> constructorMethod,
                                               CtMethod<?> originalMethod,
                                               boolean hasSuperclass,
                                               Factory factory) {
        if (!isSyntheticConstructorMethod(constructorMethod)) {
            return;
        }
        if (constructorMethod.getBody() == null) {
            constructorMethod.setBody(factory.createBlock());
        }
        CtStatement existingDelegation = findConstructorDelegationStatement(constructorMethod);
        if (existingDelegation != null) {
            return;
        }

        CtStatement originalDelegation = findConstructorDelegationStatement(originalMethod);
        if (originalDelegation != null) {
            constructorMethod.getBody().insertBegin(originalDelegation.clone());
            return;
        }

        if (hasSuperclass) {
            CtCodeSnippetStatement superCall = factory.Code().createCodeSnippetStatement("super()");
            constructorMethod.getBody().insertBegin(superCall);
        }
    }

    private CtStatement findConstructorDelegationStatement(CtMethod<?> method) {
        if (method == null || method.getBody() == null || method.getBody().getStatements().isEmpty()) {
            return null;
        }
        CtStatement first = method.getBody().getStatements().get(0);
        if (first instanceof CtInvocation<?> invocation && invocation.getExecutable().isConstructor()) {
            return first;
        }
        if (first instanceof CtCodeSnippetStatement snippet) {
            String value = snippet.getValue();
            if (value != null) {
                String normalized = value.stripLeading();
                if (normalized.startsWith("super(") || normalized.startsWith("this(")) {
                    return first;
                }
            }
        }
        return null;
    }

    private CtStatement findConstructorDelegationStatement(CtConstructor<?> constructor) {
        if (constructor == null || constructor.getBody() == null || constructor.getBody().getStatements().isEmpty()) {
            return null;
        }
        CtStatement first = constructor.getBody().getStatements().get(0);
        if (first instanceof CtInvocation<?> invocation && invocation.getExecutable().isConstructor()) {
            return first;
        }
        if (first instanceof CtCodeSnippetStatement snippet) {
            String value = snippet.getValue();
            if (value != null) {
                String normalized = value.stripLeading();
                if (normalized.startsWith("super(") || normalized.startsWith("this(")) {
                    return first;
                }
            }
        }
        return null;
    }

    private CtConstructor<?> convertSyntheticConstructorMethod(CtMethod<?> method,
                                                               boolean hasSuperclass,
                                                               Factory factory) {
        CtConstructor<?> constructor = factory.Core().createConstructor();
        constructor.setModifiers(new LinkedHashSet<>(method.getModifiers()));
        for (CtParameter<?> parameter : method.getParameters()) {
            constructor.addParameter(parameter.clone());
        }
        if (method.getBody() != null) {
            constructor.setBody(method.getBody().clone());
        } else {
            constructor.setBody(factory.createBlock());
        }
        if (hasSuperclass && !constructorStartsWithDelegation(constructor)) {
            constructor.getBody().insertBegin(factory.Code().createCodeSnippetStatement("super()"));
        }
        constructor.setPosition(method.getPosition());
        constructor.setComments(new ArrayList<>(method.getComments()));
        constructor.setDocComment(method.getDocComment());
        return constructor;
    }

    private boolean constructorStartsWithDelegation(CtConstructor<?> constructor) {
        if (constructor == null || constructor.getBody() == null || constructor.getBody().getStatements().isEmpty()) {
            return false;
        }
        CtStatement first = constructor.getBody().getStatements().get(0);
        if (first instanceof CtInvocation<?> invocation && invocation.getExecutable().isConstructor()) {
            return true;
        }
        if (first instanceof CtCodeSnippetStatement snippet) {
            String value = snippet.getValue();
            if (value != null) {
                String normalized = value.stripLeading();
                return normalized.startsWith("super(") || normalized.startsWith("this(");
            }
        }
        return false;
    }

    private void removeJavadocsFromGeneratedClass(CtClass<?> slicedClass) {
        List<CtComment> comments = new ArrayList<CtComment>(slicedClass.getElements(new TypeFilter<CtComment>(CtComment.class)));
        for (CtComment comment : comments) {
            if (comment.getCommentType() == CtComment.CommentType.JAVADOC) {
                comment.delete();
            }
        }
    }

    private List<CtImport> createSlicedCompilationUnitImports(Factory factory,
                                                              List<CtField<?>> fieldDecls,
                                                              List<CtMethod<?>> abstractStubMethods,
                                                              List<CtMethod<?>> emittedMethods) {
        LinkedHashMap<String, CtImport> imports = new LinkedHashMap<>();
        for (String targetClass : AnalysisConfig.TARGET_CLASSES_DOT) {
            CtTypeReference<?> targetRef = factory.Type().createReference(targetClass);
            CtTypeMemberWildcardImportReference wildcardImport =
                factory.Type().createTypeMemberWildcardImportReference(targetRef);
            CtImport ctImport = factory.createImport(wildcardImport);
            imports.put(ctImport.toString(), ctImport);
        }

        List<CtElement> allElements = new ArrayList<>();
        allElements.addAll(fieldDecls);
        allElements.addAll(abstractStubMethods);
        allElements.addAll(emittedMethods);
        for (CtElement element : allElements) {
            for (CtTypeReference<?> ref : element.getElements(new TypeFilter<>(CtTypeReference.class))) {
                CtTypeReference<?> normalized = normalizeNestedImportedType(ref);
                if (normalized == null) {
                    continue;
                }
                CtImport ctImport = factory.createImport(normalized);
                imports.put(ctImport.toString(), ctImport);
            }
        }
        return new ArrayList<>(imports.values());
    }

    private CtTypeReference<?> normalizeNestedImportedType(CtTypeReference<?> ref) {
        CtTypeReference<?> normalized = normalizeTypeReference(ref);
        if (normalized == null || normalized.getDeclaringType() == null) {
            return null;
        }
        String qname = normalized.getQualifiedName();
        if (qname == null || qname.startsWith("murat.simv2.simulation.sliced.")) {
            return null;
        }
        return normalized;
    }

    private PrettyPrinter createImportCleanerPrettyPrinter(Factory factory) {
        DefaultJavaPrettyPrinter printer = new DefaultJavaPrettyPrinter(factory.getEnvironment());
        List<Processor<CtElement>> preprocessors = List.of(
            new ForceImportProcessor(),
            new ImportCleaner().setCanAddImports(false),
            new ImportConflictDetector(),
            new ImportCleaner().setImportComparator(new DefaultImportComparator())
        );
        printer.setIgnoreImplicit(false);
        printer.setPreprocessors(preprocessors);
        return printer;
    }

    private int countStatements(CtMethod<?> method) {
        if (method.getBody() == null) return 0;
        return method.getBody().getElements(new TypeFilter<>(CtStatement.class)).size();
    }

    /**
     * Scans AST elements for references to non-public members and types that the
     * generated sliced class still relies on, and generates access widener entries.
     * Members satisfied by the generated sliced class itself are skipped.
     *
     * and generates access widener entries for them.
     */
    private void collectAccessWidenerEntries(CtType<?> type,
                                              List<CtMethod<?>> methods,
                                              List<CtField<?>> fields,
                                              Map<String, CtType<?>> typeIndex,
                                              Set<String> awEntries) {
        Set<String> sourceHierarchy = sourceHierarchyQualifiedNames(type, typeIndex);
        List<CtElement> allElements = new ArrayList<>();
        allElements.addAll(methods);
        allElements.addAll(fields);

        for (CtElement elem : allElements) {
            for (CtFieldAccess<?> fa : elem.getElements(new TypeFilter<>(CtFieldAccess.class))) {
                CtFieldReference<?> ref = fa.getVariable();
                if (ref == null || ref.getDeclaringType() == null) {
                    continue;
                }
                CtField<?> field = ref.getDeclaration();
                if (field == null) {
                    continue;
                }
                if (isSatisfiedByGeneratedSlicedHierarchy(field, ref.getDeclaringType(), sourceHierarchy)) {
                    continue;
                }
                addClassAccessWidenerEntries(field.getDeclaringType().getReference(), awEntries);
                if (requiresAccessWidener(field)) {
                    awEntries.add("accessible field " + internalName(field.getDeclaringType())
                        + " " + field.getSimpleName() + " " + getFieldTypeDescriptor(field));
                }
            }

            for (CtInvocation<?> inv : elem.getElements(new TypeFilter<>(CtInvocation.class))) {
                CtExecutableReference<?> ref = inv.getExecutable();
                if (ref == null || ref.getDeclaringType() == null) {
                    continue;
                }
                CtExecutable<?> executable = ref.getExecutableDeclaration();
                if (!(executable instanceof CtMethod<?> method)) {
                    continue;
                }
                if (isSatisfiedByGeneratedSlicedHierarchy(method, ref.getDeclaringType(), sourceHierarchy)) {
                    continue;
                }
                addClassAccessWidenerEntries(method.getDeclaringType().getReference(), awEntries);
                if (requiresAccessWidener(method)) {
                    awEntries.add("accessible method " + internalName(method.getDeclaringType())
                        + " " + method.getSimpleName() + " " + getMethodDescriptor(method));
                }
            }

            for (CtTypeReference<?> ref : elem.getElements(new TypeFilter<>(CtTypeReference.class))) {
                addClassAccessWidenerEntries(ref, awEntries);
            }
        }
    }

    private Set<String> sourceHierarchyQualifiedNames(CtType<?> type, Map<String, CtType<?>> typeIndex) {
        Set<String> hierarchy = new LinkedHashSet<>();
        CtType<?> current = type;
        while (current != null) {
            hierarchy.add(current.getQualifiedName());
            if (current instanceof CtClass<?> ctClass && ctClass.getSuperclass() != null) {
                current = typeIndex.get(ctClass.getSuperclass().getQualifiedName());
            } else {
                break;
            }
        }
        return hierarchy;
    }

    private boolean isSatisfiedByGeneratedSlicedHierarchy(CtTypeMember member,
                                                          CtTypeReference<?> declaringType,
                                                          Set<String> sourceHierarchy) {
        String declaringQName = declaringType.getQualifiedName();
        if (declaringQName == null) {
            return false;
        }
        if (!sourceHierarchy.contains(declaringQName)) {
            return false;
        }
        return !member.isStatic();
    }

    private boolean requiresAccessWidener(CtModifiable modifiable) {
        return !modifiable.hasModifier(ModifierKind.PUBLIC);
    }

    private void addClassAccessWidenerEntries(CtTypeReference<?> typeRef, Set<String> awEntries) {
        CtTypeReference<?> current = normalizeTypeReference(typeRef);
        while (current != null) {
            CtType<?> type = current.getTypeDeclaration();
            if (type == null) {
                current = current.getDeclaringType();
                continue;
            }
            String qname = type.getQualifiedName();
            if (qname == null || qname.startsWith("murat.simv2.simulation.sliced.")) {
                current = current.getDeclaringType();
                continue;
            }
            if (requiresAccessWidener(type)) {
                awEntries.add("accessible class " + internalName(type));
            }
            current = current.getDeclaringType();
        }
    }

    private CtTypeReference<?> normalizeTypeReference(CtTypeReference<?> typeRef) {
        if (typeRef == null) {
            return null;
        }
        CtTypeReference<?> current = typeRef;
        while (current instanceof CtArrayTypeReference<?> arrayRef) {
            current = arrayRef.getArrayType();
        }
        if (current.isPrimitive() || current instanceof CtTypeParameterReference || current.isGenerics()) {
            return null;
        }
        String qname = current.getQualifiedName();
        if (qname == null || qname.isEmpty() || !qname.contains(".")) {
            return null;
        }
        return current;
    }

    private String internalName(CtType<?> type) {
        return internalName(type.getReference());
    }

    private String internalName(CtTypeReference<?> typeRef) {
        CtTypeReference<?> normalized = normalizeTypeReference(typeRef);
        if (normalized == null) {
            return "";
        }
        String qname = normalized.getQualifiedName();
        CtTypeReference<?> declaringType = normalized.getDeclaringType();
        if (declaringType == null) {
            return qname.replace('.', '/');
        }
        return internalName(declaringType) + "$" + normalized.getSimpleName();
    }

    /** Approximates a JVM field type descriptor from Spoon type info. */
    private String getFieldTypeDescriptor(CtField<?> field) {
        CtTypeReference<?> ref = field.getType();
        return toDescriptor(ref);
    }

    /** Approximates a JVM method descriptor from Spoon method info. */
    private String getMethodDescriptor(CtMethod<?> method) {
        StringBuilder sb = new StringBuilder("(");
        for (CtParameter<?> p : method.getParameters()) {
            sb.append(toDescriptor(p.getType()));
        }
        sb.append(")").append(toDescriptor(method.getType()));
        return sb.toString();
    }

    private String toDescriptor(CtTypeReference<?> ref) {
        String name = ref.getQualifiedName();
        return switch (name) {
            case "void" -> "V";
            case "boolean" -> "Z";
            case "byte" -> "B";
            case "char" -> "C";
            case "short" -> "S";
            case "int" -> "I";
            case "long" -> "J";
            case "float" -> "F";
            case "double" -> "D";
            default -> {
                if (name.endsWith("[]")) {
                    String component = name.substring(0, name.length() - 2);
                    // Create a temporary reference for the component type to handle primitives
                    CtTypeReference<?> componentRef = ref.getFactory().Type().createReference(component);
                    yield "[" + toDescriptor(componentRef);
                }
                yield "L" + name.replace('.', '/') + ";";
            }
        };
    }
}
