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

    private final Path sourcesJar;
    private final Path minecraftJar;
    private final List<Path> extraClasspathOverride;
    private final Map<String, Map<String, Set<Integer>>> sliceLines;

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
            launcher.getEnvironment().setNoClasspath(false);
            launcher.getEnvironment().setIgnoreSyntaxErrors(true);
            launcher.getEnvironment().setComplianceLevel(21);
            launcher.getEnvironment().setAutoImports(true);
            launcher.getModelBuilder().setSourceClasspath(sourceClasspath);
            launcher.prettyprint();
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
            // Collect access widener entries needed for private members
            Set<String> awEntries = new TreeSet<>();
            // Methods found during a child's transitive closure that belong to a parent class
            Map<String, List<CtMethod<?>>> deferredMethods = new LinkedHashMap<>();
            // Track which sigs each class contributed to hierarchyDefinedMethods
            // so we can remove them before re-processing
            Map<String, Set<String>> classContributedSigs = new LinkedHashMap<>();

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

                // Include any methods deferred from child classes
                List<CtMethod<?>> extraMethods = deferredMethods.remove(type.getSimpleName());

                Set<String> before = new LinkedHashSet<>(hierarchyDefinedMethods);
                buildSlicedClass(type, methodLines, outputDir, factory,
                    emittedSimpleNames, typeIndex, hierarchyDefinedMethods, awEntries,
                    deferredMethods, extraMethods);
                Set<String> contributed = new LinkedHashSet<>(hierarchyDefinedMethods);
                contributed.removeAll(before);
                classContributedSigs.put(type.getSimpleName(), contributed);
                emittedSimpleNames.add(type.getSimpleName());
            }

            // Re-process parent classes that gained deferred methods from later children
            if (!deferredMethods.isEmpty()) {
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

                    Map<String, Set<Integer>> methodLines = sliceLines.getOrDefault(className, Map.of());
                    System.out.println("Re-processing " + type.getSimpleName()
                        + " with " + extra.size() + " deferred methods from children");
                    buildSlicedClass(type, methodLines, outputDir, factory,
                        emittedSimpleNames, typeIndex, hierarchyDefinedMethods, awEntries,
                        deferredMethods, extra);
                }
            }

            // Append sliced-class AW entries to the existing access widener
            if (!awEntries.isEmpty()) {
                Path awPath = resolveAccessWidenerPath(outputDir);
                if (Files.exists(awPath)) {
                    String existing = Files.readString(awPath);
                    StringBuilder sb = new StringBuilder(existing);
                    sb.append("\n# Access widener entries for sliced classes\n");
                    for (String entry : awEntries) {
                        if (!existing.contains(entry)) {
                            sb.append(entry).append("\n");
                        }
                    }
                    Files.writeString(awPath, sb.toString());
                    System.out.println("Added " + awEntries.size() + " access widener entries for sliced classes");
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
                    sanitizeExtractedSource(entry.getName(), outFile);
                    System.out.println("  Extracted: " + entry.getName());
                }
            }
        }
    }

    private void sanitizeExtractedSource(String entryName, Path outFile) throws IOException {
        String code = Files.readString(outFile);
        String fixed = code;

        if ("net/minecraft/entity/player/PlayerEntity.java".equals(entryName)) {
            fixed = fixed
                .replace("@Override\r\nprotected ", "@Override\r\npublic ")
                .replace("@Override\nprotected ", "@Override\npublic ");
            fixed = fixed.replace(
                "protected void damageArmor(DamageSource source, float amount) {",
                "public void damageArmor(DamageSource source, float amount) {"
            );
            fixed = fixed.replace(
                "protected void damageHelmet(DamageSource source, float amount) {",
                "public void damageHelmet(DamageSource source, float amount) {"
            );
        } else if ("net/minecraft/entity/Entity.java".equals(entryName)) {
            // Decompiled source uses an overload-by-return-type pair on TeleportTarget#asPassenger,
            // which is valid bytecode but not valid Java invocation resolution.
            fixed = fixed.replace("!teleportTarget.asPassenger()", "true");
        }

        if (!fixed.equals(code)) {
            Files.writeString(outFile, fixed);
        }
    }

    private void buildSlicedClass(CtType<?> type, Map<String, Set<Integer>> methodLines,
                                   Path outputDir, Factory factory,
                                   Set<String> emittedSimpleNames,
                                   Map<String, CtType<?>> typeIndex,
                                   Set<String> hierarchyDefinedMethods,
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

            int returnsBefore = cloned.getBody() == null ? 0
                : cloned.getBody().getElements(new TypeFilter<>(CtReturn.class)).size();
            int before = countStatements(cloned);
            pruneByWalaLines(cloned, walaLines);
            preserveConstructorDelegation(cloned, original, extendsRef != null, factory);
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

        // Include methods deferred from child classes
        if (extraMethods != null) {
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

        // Constructors are structural and must always exist on the sliced type,
        // even when WALA does not include <init> in the sliced method set.
        if (type instanceof CtClass<?> ctClass) {
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
        boolean changed = true;
        while (changed) {
            changed = false;
            Set<MethodCall> unresolvedCalls = findUnresolvedThisCalls(clonedMethods, allKnownSigs);
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

        // ── Phase 5: Generate abstract stubs for remaining unresolved calls ──
        // Re-scan after transitive closure for still-unresolved methods
        Set<String> finalKnownSigs = new LinkedHashSet<>(allKnownSigs);
        Set<MethodCall> stillUnresolved = findUnresolvedThisCalls(clonedMethods, finalKnownSigs);
        List<CtMethod<?>> abstractStubMethods = new ArrayList<>();
        Set<String> stubNames = new LinkedHashSet<>();
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

        // ── Phase 6: Build output ──
        List<CtMethod<?>> allMethods = new ArrayList<>(emittedMethods);
        allMethods.addAll(abstractStubMethods);
        List<CtField<?>> fieldDecls = extractFieldDeclarations(type, allMethods, typeIndex);

        // Apply entity bridge rewrite to field initializers (e.g., new DamageTracker(this))
        for (CtField<?> field : fieldDecls) {
            if (field.getDefaultExpression() != null) {
                replaceThisInArgPositions(field.getDefaultExpression(), factory, simpleName);
            }
        }

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
    }

    // ── Method resolution helpers ──

    private record MethodCall(String name, int argCount, List<String> argTypes) {}

    /**
     * Finds all this.xxx() invocations in the given methods that are not defined
     * in the known method signature set.
     */
    private Set<MethodCall> findUnresolvedThisCalls(List<CtMethod<?>> methods, Set<String> knownSigs) {
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
                    || (target instanceof CtTypeAccess<?> ta
                        && TYPE_REMAP.containsKey(ta.getAccessedType().getSimpleName()));
                if (!isSelf) continue;

                String name = inv.getExecutable().getSimpleName();
                int argCount = inv.getArguments().size();
                List<String> argTypes = invocationArgTypes(inv);
                String sig = methodKey(name, argTypes);
                if (!knownSigs.contains(sig)) {
                    unresolved.add(new MethodCall(name, argCount, argTypes));
                }
            }
        }
        return unresolved;
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
            if (!method.getSimpleName().equals(call.name) || method.getParameters().size() != call.argCount) {
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
        return name + "(" + String.join(",", argTypes) + ")";
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

        // 2. Bare 'this' in argument positions -> (MCType) this.entityBridge
        replaceThisInArgPositions(method, factory, mcTypeName);

        // 3. == this / != this -> == this.entityBridge / != this.entityBridge
        for (CtBinaryOperator<?> binOp : new ArrayList<>(method.getElements(new TypeFilter<CtBinaryOperator<?>>(CtBinaryOperator.class)))) {
            if (binOp.getKind() != BinaryOperatorKind.EQ && binOp.getKind() != BinaryOperatorKind.NE) continue;
            if (binOp.getRightHandOperand() instanceof CtThisAccess<?> rhs && rhs.getTypeCasts().isEmpty()) {
                ((CtBinaryOperator) binOp).setRightHandOperand(createEntityBridgeRead(factory));
            }
            if (binOp.getLeftHandOperand() instanceof CtThisAccess<?> lhs && lhs.getTypeCasts().isEmpty()) {
                ((CtBinaryOperator) binOp).setLeftHandOperand(createEntityBridgeRead(factory));
            }
        }

        // 4-5. instanceof rewrites
        for (CtBinaryOperator<?> binOp : new ArrayList<>(method.getElements(new TypeFilter<CtBinaryOperator<?>>(CtBinaryOperator.class)))) {
            if (binOp.getKind() != BinaryOperatorKind.INSTANCEOF) continue;
            if (!(binOp.getLeftHandOperand() instanceof CtThisAccess<?> lhs) || !lhs.getTypeCasts().isEmpty()) continue;

            CtExpression<?> rhs = binOp.getRightHandOperand();
            String typeName = null;
            if (rhs instanceof CtTypeAccess<?> ta) {
                typeName = ta.getAccessedType().getSimpleName();
            }
            if (typeName == null) continue;

            if ("ServerPlayerEntity".equals(typeName)) {
                // this.entityBridge instanceof ServerPlayerEntity
                ((CtBinaryOperator) binOp).setLeftHandOperand(createEntityBridgeRead(factory));
            } else if (TYPE_REMAP.containsKey(typeName)) {
                // this instanceof Entity -> this instanceof SlicedEntity
                remapTypeReferenceToSliced(((CtTypeAccess<?>) rhs).getAccessedType());
            }
        }

        // 6. Cast rewrites: (Entity) this -> (SlicedEntity) this
        for (CtThisAccess<?> ta : new ArrayList<CtThisAccess<?>>(method.getElements(new TypeFilter<CtThisAccess<?>>(CtThisAccess.class)))) {
            for (CtTypeReference<?> cast : ta.getTypeCasts()) {
                remapTypeReferenceToSliced(cast);
            }
        }

        // 7. Static/self type accesses used as invocation/field targets:
        // ClientPlayerEntity.foo() -> SlicedClientPlayerEntity.foo()
        for (CtTypeAccess<?> typeAccess : new ArrayList<>(method.getElements(new TypeFilter<CtTypeAccess<?>>(CtTypeAccess.class)))) {
            CtElement parent = typeAccess.getParent();
            if (parent instanceof CtInvocation<?> invocation && invocation.getTarget() == typeAccess) {
                remapTypeReferenceToSliced(typeAccess.getAccessedType());
                continue;
            }
            if (parent instanceof CtFieldAccess<?> fieldAccess && fieldAccess.getTarget() == typeAccess) {
                remapTypeReferenceToSliced(typeAccess.getAccessedType());
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
        CtReturn<?> ret = factory.createReturn();
        ((CtReturn<Object>) ret).setReturnedExpression((CtExpression<Object>) defaultValue);
        method.getBody().addStatement(ret);
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
                                                       Map<String, CtType<?>> typeIndex) {
        // Collect all field names referenced in methods via AST walk
        Set<String> referencedFieldNames = new HashSet<>();
        for (CtMethod<?> m : methods) {
            for (CtFieldAccess<?> fa : m.getElements(new TypeFilter<>(CtFieldAccess.class))) {
                referencedFieldNames.add(fa.getVariable().getSimpleName());
            }
        }

        List<CtField<?>> decls = new ArrayList<>();
        Set<String> added = new HashSet<>();

        // Walk up the hierarchy to find fields referenced in methods
        addReferencedFields(type, typeIndex, referencedFieldNames, decls, added);

        // Transitive closure: field initializers may reference other fields (e.g., uuid → random)
        boolean changed = true;
        while (changed) {
            changed = false;
            Set<String> newRefs = new HashSet<>();
            for (CtField<?> field : decls) {
                if (field.getDefaultExpression() == null) continue;
                for (CtFieldAccess<?> fa : field.getDefaultExpression().getElements(
                        new TypeFilter<>(CtFieldAccess.class))) {
                    String refName = fa.getVariable().getSimpleName();
                    if (!added.contains(refName)) {
                        newRefs.add(refName);
                    }
                }
            }
            if (!newRefs.isEmpty()) {
                int before = decls.size();
                addReferencedFields(type, typeIndex, newRefs, decls, added);
                changed = decls.size() > before;
            }
        }
        return decls;
    }

    private void addReferencedFields(CtType<?> type, Map<String, CtType<?>> typeIndex,
                                      Set<String> referencedFieldNames,
                                      List<CtField<?>> decls, Set<String> added) {
        CtType<?> current = type;
        while (current != null) {
            for (CtField<?> field : current.getFields()) {
                String name = field.getSimpleName();
                if (!added.contains(name) && referencedFieldNames.contains(name)) {
                    CtField<?> cloned = field.clone();
                    // Remove 'final' from fields without initializers — there is no
                    // constructor in the sliced class to assign them.
                    if (cloned.getDefaultExpression() == null) {
                        cloned.removeModifier(ModifierKind.FINAL);
                    }
                    decls.add(cloned);
                    added.add(name);
                }
            }
            if (current instanceof CtClass<?> ctClass && ctClass.getSuperclass() != null) {
                current = typeIndex.get(ctClass.getSuperclass().getQualifiedName());
            } else {
                break;
            }
        }
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
            if (extendsRef == null) {
                CtField<?> entityBridgeField = factory.createField();
                entityBridgeField.setSimpleName("entityBridge");
                entityBridgeField.setType(factory.Type().createReference("net.minecraft.entity.Entity"));
                entityBridgeField.addModifier(ModifierKind.PROTECTED);
                entityBridgeField.setDocComment(
                    "Real MC entity used as bridge for World and external API calls.");
                slicedClass.addField(entityBridgeField);
            }

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
