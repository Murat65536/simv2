package murat.simv2.analysis;

import spoon.Launcher;
import spoon.reflect.CtModel;
import spoon.reflect.code.*;
import spoon.reflect.declaration.*;
import spoon.reflect.factory.Factory;
import spoon.reflect.reference.CtTypeReference;
import spoon.reflect.visitor.filter.TypeFilter;

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
    private final Map<String, Map<String, Set<Integer>>> sliceLines;

    public SpoonSlicePruner(Path sourcesJar, Map<String, Map<String, Set<Integer>>> sliceLines) {
        this.sourcesJar = sourcesJar;
        this.sliceLines = sliceLines;
    }

    public void pruneAndWrite(Path outputDir) throws IOException {
        Path tempDir = Files.createTempDirectory("mc-sources-");
        try {
            extractTargetSources(tempDir);

            System.out.println("Building Spoon model...");
            Launcher launcher = new Launcher();
            launcher.addInputResource(tempDir.toString());
            launcher.getEnvironment().setNoClasspath(true);
            launcher.getEnvironment().setComplianceLevel(21);
            launcher.getEnvironment().setAutoImports(true);
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

                buildSlicedClass(type, methodLines, outputDir, factory,
                    emittedSimpleNames, typeIndex, hierarchyDefinedMethods, awEntries);
                emittedSimpleNames.add(type.getSimpleName());
            }

            // Hardcoded AW entries for private/protected static fields and methods
            // that sliced code references via import static
            addKnownPrivateMembers(awEntries);

            // Append sliced-class AW entries to the existing access widener
            if (!awEntries.isEmpty()) {
                // outputDir = .../src/main/generated/java/murat/simv2/simulation/sliced
                // AW file = .../src/main/generated/sim-v2.accesswidener
                Path awPath = outputDir.getParent().getParent().getParent().getParent().getParent().resolve("sim-v2.accesswidener");
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
                                   Set<String> awEntries) throws IOException {
        String simpleName = type.getSimpleName();
        String slicedName = "Sliced" + simpleName;
        System.out.println("Pruning " + type.getQualifiedName() + "...");

        // Collect imports from original source
        Set<String> imports = new TreeSet<>();
        if (type.getPosition() != null && type.getPosition().getCompilationUnit() != null) {
            for (var imp : type.getPosition().getCompilationUnit().getImports()) {
                String s = imp.toString().trim();
                if (!s.endsWith(";")) s += ";";
                imports.add(s);
            }
        }

        String extendsClause = resolveExtendsClause(type, emittedSimpleNames, typeIndex);

        // ── Phase 1: Clone WALA-sliced methods with pruning ──
        List<CtMethod<?>> clonedMethods = new ArrayList<>();
        Set<String> definedSigs = new LinkedHashSet<>();
        Set<String> definedNames = new LinkedHashSet<>();

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
            if (definedSigs.contains(sig) || definedSigs.contains(rewrittenSig)
                || hierarchyDefinedMethods.contains(sig) || hierarchyDefinedMethods.contains(rewrittenSig)) {
                continue;
            }
            definedSigs.add(sig);
            definedSigs.add(rewrittenSig);

            int before = countStatements(cloned);
            pruneByWalaLines(cloned, walaLines);
            int after = countStatements(cloned);

            System.out.println("  " + simpleName + "." + methodName + "(): "
                + before + " → " + after + " statements (" + walaLines.size() + " WALA lines)");

            clonedMethods.add(cloned);
            definedNames.add(methodName);
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
        boolean changed = true;
        while (changed) {
            changed = false;
            Set<MethodCall> unresolvedCalls = findUnresolvedThisCalls(clonedMethods, allKnownSigs);
            for (MethodCall call : unresolvedCalls) {
                CtMethod<?> found = findMethodInHierarchy(call, type, typeIndex);
                if (found != null) {
                    String originalSig = methodKey(found);
                    CtMethod<?> cloned = found.clone();
                    rewriteMethodSignature(cloned, found);
                    relaxHelperVisibility(cloned);
                    String rewrittenSig = methodKey(cloned);
                    if (!definedSigs.contains(originalSig) && !definedSigs.contains(rewrittenSig)) {
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
        if (transitiveClosed > 0) {
            System.out.println("  Transitively included " + transitiveClosed + " methods");
        }

        // ── Phase 3: Remove @Override, deduplicate, convert to text + text rewrites ──
        List<String> methodTexts = new ArrayList<>();
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

            // Convert to text and apply instanceof/cast rewrites
            String text = applyTextTypeRewrites(m.toString(), definedNames, simpleName);
            text = text.replaceAll("(?s)/\\*\\*\\s*\\*/\\s*", "");
            methodTexts.add(text);
        }

        // ── Phase 5: Generate abstract stubs for remaining unresolved calls ──
        // Re-scan after transitive closure for still-unresolved methods
        Set<String> finalKnownSigs = new LinkedHashSet<>(allKnownSigs);
        Set<MethodCall> stillUnresolved = findUnresolvedThisCalls(clonedMethods, finalKnownSigs);
        List<String> abstractStubs = new ArrayList<>();
        Set<String> stubNames = new LinkedHashSet<>();
        for (MethodCall call : stillUnresolved) {
            String callKey = methodKey(call.name, call.argTypes);
            if (stubNames.contains(callKey)) continue;
            // Find the method in the original MC hierarchy to get its signature
            CtMethod<?> originalMethod = findMethodInHierarchy(call, type, typeIndex);
            if (originalMethod != null) {
                String stub = generateAbstractStub(originalMethod);
                if (stub != null) {
                    String rewrittenSig = rewrittenMethodKey(originalMethod);
                    if (emittedSigs.contains(rewrittenSig)) {
                        continue;
                    }
                    abstractStubs.add(stub);
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
        if (!abstractStubs.isEmpty()) {
            System.out.println("  Generated " + abstractStubs.size() + " abstract stubs");
        }

        // ── Phase 6: Build output ──
        String allMethodText = String.join("\n", methodTexts);
        String allStubText = String.join("\n", abstractStubs);
        String allCodeText = allMethodText + "\n" + allStubText;
        List<String> fieldDecls = extractFieldDeclarations(type, allCodeText, typeIndex);

        // Apply entity bridge rewrite to field initializers (e.g., new DamageTracker(this))
        for (int i = 0; i < fieldDecls.size(); i++) {
            fieldDecls.set(i, fieldDecls.get(i).replaceAll(
                "(?<=[(,])\\s*this(?=\\s*[,)])",
                " (" + simpleName + ") this.entityBridge"));
        }

        // Collect access widener entries for non-public static fields/methods
        collectAccessWidenerEntries(type, allCodeText, typeIndex, awEntries);

        // Filter imports
        String fullCode = allCodeText + "\n" + String.join("\n", fieldDecls);
        Set<String> filteredImports = filterImports(imports, fullCode);
        addMissingTypeImports(filteredImports, fullCode);

        StringBuilder sb = new StringBuilder();
        sb.append("package murat.simv2.simulation.sliced;\n\n");
        for (String imp : filteredImports) {
            sb.append(imp).append("\n");
        }
        if (!filteredImports.isEmpty()) sb.append("\n");

        sb.append("// Sliced from ").append(type.getQualifiedName()).append("\n");
        sb.append("// Movement-relevant statements only (WALA backward slice + Spoon AST pruning)\n");
        sb.append("// Generated — do not edit\n\n");

        sb.append("public abstract class ").append(slicedName).append(extendsClause).append(" {\n");

        // Entity bridge field — only in root class (inherited by all sliced subclasses)
        // Set to the real player entity before simulation; used at World/external API boundaries
        if (extendsClause.isEmpty()) {
            sb.append("\n    /** Real MC entity used as bridge for World and external API calls. */\n");
            sb.append("    protected Entity entityBridge;\n");
        }

        // Field declarations
        if (!fieldDecls.isEmpty()) {
            sb.append("\n");
            for (String fieldDecl : fieldDecls) {
                sb.append("    ").append(fieldDecl).append("\n");
            }
        }

        // Abstract stubs
        if (!abstractStubs.isEmpty()) {
            sb.append("\n    // ── Abstract stubs for methods not in slice ──\n");
            for (String stub : abstractStubs) {
                sb.append("    ").append(stub).append("\n");
            }
        }

        // Methods
        for (String methodText : methodTexts) {
            sb.append("\n");
            for (String line : methodText.split("\n")) {
                sb.append("    ").append(line).append("\n");
            }
        }

        sb.append("}\n");

        Path outFile = outputDir.resolve(slicedName + ".java");
        Files.createDirectories(outFile.getParent());
        Files.writeString(outFile, sb.toString());
        System.out.println("  Wrote " + outFile.getFileName());

        // Update hierarchy tracking
        for (String sig : definedSigs) {
            hierarchyDefinedMethods.add(sig);
        }
        for (String stubSig : stubNames) {
            hierarchyDefinedMethods.add(stubSig);
        }
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
                boolean isSelf = (target == null)
                    || (target instanceof CtThisAccess)
                    || (target instanceof CtSuperAccess);
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
            String simpleName = ref.getSimpleName();
            String replacement = TYPE_REMAP.get(simpleName);
            if (replacement != null) {
                ref.setSimpleName(replacement);
                try { ref.setPackage(null); } catch (Exception ignored) {}
            }
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

    /**
     * Applies text-level type rewrites for instanceof and casts of 'this'.
     * These are safe to rewrite because 'this' is always a Sliced type.
     */
    private String applyTextTypeRewrites(String methodText, Set<String> definedNames, String mcTypeName) {
        // Replace super.xxx() with this.xxx() for methods that are defined in the current class
        // (transitively-included from parent). super.xxx() won't resolve since the parent
        // sliced class may not have the method.
        for (String name : definedNames) {
            methodText = methodText.replace("super." + name + "(", "this." + name + "(");
        }

        // ── Entity bridge pattern ──
        // Bare `this` in method/constructor argument positions gets replaced with
        // `(MCType) this.entityBridge`. The bridge is a real MC Entity (set before
        // simulation) that passes instanceof checks and satisfies external MC APIs.
        methodText = methodText.replaceAll(
            "(?<=[(,])\\s*this(?=\\s*[,)])",
            " (" + mcTypeName + ") this.entityBridge");

        // Identity comparisons: `entity == this` → `entity == this.entityBridge`
        methodText = methodText.replaceAll("==\\s*this(?![.A-Za-z0-9_])", "== this.entityBridge");
        methodText = methodText.replaceAll("!=\\s*this(?![.A-Za-z0-9_])", "!= this.entityBridge");
        methodText = methodText.replace("this instanceof ServerPlayerEntity", "this.entityBridge instanceof ServerPlayerEntity");

        // instanceof rewrites: `this instanceof Entity` → `this instanceof SlicedEntity`
        for (var entry : TYPE_REMAP.entrySet()) {
            String mc = entry.getKey();
            String sliced = entry.getValue();
            methodText = methodText.replaceAll(
                "this instanceof " + mc + "\\b(?![A-Za-z])",
                "this instanceof " + sliced);
            // Cast of bare `this`: "(Entity) this" → "(SlicedEntity) this"
            methodText = methodText.replaceAll(
                "\\(" + mc + "\\)\\s*this(?![.A-Za-z0-9_])",
                "(" + sliced + ") this");
            // Parenthesized cast: "(Entity) (this)" pattern
            methodText = methodText.replaceAll(
                "\\(" + mc + "\\)\\s*\\(this\\)(?![.A-Za-z0-9_])",
                "(" + sliced + ") (this)");
        }
        return methodText;
    }

    // ── Abstract stub generation ──

    /**
     * Generates an abstract method declaration string from an original MC method.
     * Applies type rewriting to the stub signature.
     */
    private String generateAbstractStub(CtMethod<?> original) {
        StringBuilder sb = new StringBuilder();
        sb.append("public abstract ");

        // Keep full type text so generic signatures survive erasure-sensitive overloads.
        String returnType = original.getType().toString();
        sb.append(returnType).append(" ");

        sb.append(original.getSimpleName()).append("(");

        // Parameters (with type rewriting)
        List<CtParameter<?>> params = original.getParameters();
        for (int i = 0; i < params.size(); i++) {
            if (i > 0) sb.append(", ");
            CtParameter<?> p = params.get(i);
            String paramType = shouldRewriteParameterTypes(original)
                ? rewriteTypeText(p.getType().toString())
                : p.getType().toString();
            sb.append(paramType).append(" ").append(p.getSimpleName());
        }

        sb.append(");");
        return sb.toString();
    }

    /** Applies TYPE_REMAP to a simple type name if it matches. */
    private String rewriteTypeName(String simpleName) {
        return TYPE_REMAP.getOrDefault(simpleName, simpleName);
    }

    private String rewriteTypeText(String typeText) {
        String rewritten = typeText;
        for (var entry : TYPE_REMAP.entrySet()) {
            rewritten = rewritten.replaceAll("\\b" + entry.getKey() + "\\b", entry.getValue());
        }
        return rewritten;
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

    // ── Hierarchy and method resolution ──

    private String resolveExtendsClause(CtType<?> type, Set<String> emittedSimpleNames,
                                         Map<String, CtType<?>> typeIndex) {
        if (!(type instanceof CtClass<?> ctClass)) return "";

        CtTypeReference<?> superRef = ctClass.getSuperclass();
        while (superRef != null) {
            String superSimpleName = superRef.getSimpleName();
            if (emittedSimpleNames.contains(superSimpleName)) {
                return " extends Sliced" + superSimpleName;
            }
            CtType<?> superType = typeIndex.get(superRef.getQualifiedName());
            if (superType instanceof CtClass<?> superClass) {
                superRef = superClass.getSuperclass();
            } else {
                break;
            }
        }
        return "";
    }

    private CtMethod<?> findMethod(CtType<?> type, String methodName, String descriptor) {
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

    private List<String> extractFieldDeclarations(CtType<?> type, String methodCode,
                                                    Map<String, CtType<?>> typeIndex) {
        List<String> decls = new ArrayList<>();
        Set<String> added = new HashSet<>();

        // Walk up the hierarchy to find fields referenced in method code
        CtType<?> current = type;
        while (current != null) {
            for (CtField<?> field : current.getFields()) {
                String name = field.getSimpleName();
                if (!added.contains(name) && methodCode.contains("this." + name)) {
                    String fieldStr = field.toString();
                    if (!fieldStr.endsWith(";")) fieldStr += ";";
                    decls.add(fieldStr);
                    added.add(name);
                }
            }
            // Walk to parent
            if (current instanceof CtClass<?> ctClass && ctClass.getSuperclass() != null) {
                current = typeIndex.get(ctClass.getSuperclass().getQualifiedName());
            } else {
                break;
            }
        }
        return decls;
    }

    private void addMissingTypeImports(Set<String> imports, String code) {
        Map<String, String> knownTypes = new LinkedHashMap<>();
        knownTypes.put("Entity", "import net.minecraft.entity.Entity;");
        knownTypes.put("LivingEntity", "import net.minecraft.entity.LivingEntity;");
        knownTypes.put("PlayerEntity", "import net.minecraft.entity.player.PlayerEntity;");
        knownTypes.put("EntityType", "import net.minecraft.entity.EntityType;");
        knownTypes.put("EntityDimensions", "import net.minecraft.entity.EntityDimensions;");
        knownTypes.put("EntityPose", "import net.minecraft.entity.EntityPose;");
        knownTypes.put("EntityAttachments", "import net.minecraft.entity.EntityAttachments;");
        knownTypes.put("EntityAttachmentType", "import net.minecraft.entity.EntityAttachmentType;");
        knownTypes.put("MovementType", "import net.minecraft.entity.MovementType;");
        knownTypes.put("EquipmentSlot", "import net.minecraft.entity.EquipmentSlot;");
        knownTypes.put("EntityEquipment", "import net.minecraft.entity.EntityEquipment;");
        knownTypes.put("EntityStatuses", "import net.minecraft.entity.EntityStatuses;");
        knownTypes.put("Flutterer", "import net.minecraft.entity.Flutterer;");
        knownTypes.put("PositionInterpolator", "import net.minecraft.entity.PositionInterpolator;");
        knownTypes.put("LazyEntityReference", "import net.minecraft.entity.LazyEntityReference;");
        knownTypes.put("PlayerListEntry", "import net.minecraft.client.network.PlayerListEntry;");
        knownTypes.put("ClientPlayerEntity", "import net.minecraft.client.network.ClientPlayerEntity;");
        knownTypes.put("AbstractClientPlayerEntity", "import net.minecraft.client.network.AbstractClientPlayerEntity;");
        knownTypes.put("PlayerAbilities", "import net.minecraft.entity.player.PlayerAbilities;");
        knownTypes.put("DamageSources", "import net.minecraft.entity.damage.DamageSources;");
        knownTypes.put("DamageUtil", "import net.minecraft.entity.DamageUtil;");
        knownTypes.put("EntityAttribute", "import net.minecraft.entity.attribute.EntityAttribute;");
        knownTypes.put("ItemEntity", "import net.minecraft.entity.ItemEntity;");
        knownTypes.put("ExperienceOrbEntity", "import net.minecraft.entity.ExperienceOrbEntity;");
        knownTypes.put("ProjectileDeflection", "import net.minecraft.entity.ProjectileDeflection;");
        knownTypes.put("Fluid", "import net.minecraft.fluid.Fluid;");
        knownTypes.put("RegistryEntry", "import net.minecraft.registry.entry.RegistryEntry;");
        knownTypes.put("StatusEffect", "import net.minecraft.entity.effect.StatusEffect;");
        knownTypes.put("TagKey", "import net.minecraft.registry.tag.TagKey;");
        knownTypes.put("RaycastContext", "import net.minecraft.world.RaycastContext;");
        knownTypes.put("BlockHitResult", "import net.minecraft.util.hit.BlockHitResult;");
        knownTypes.put("UUID", "import java.util.UUID;");
        knownTypes.put("Map", "import java.util.Map;");
        knownTypes.put("Set", "import java.util.Set;");
        knownTypes.put("HashSet", "import java.util.HashSet;");
        knownTypes.put("Maps", "import com.google.common.collect.Maps;");
        knownTypes.put("Random", "import net.minecraft.util.math.random.Random;");
        knownTypes.put("DataTracker", "import net.minecraft.entity.data.DataTracker;");
        knownTypes.put("TrackedData", "import net.minecraft.entity.data.TrackedData;");
        knownTypes.put("Object2DoubleMap", "import it.unimi.dsi.fastutil.objects.Object2DoubleMap;");
        knownTypes.put("Object2DoubleArrayMap", "import it.unimi.dsi.fastutil.objects.Object2DoubleArrayMap;");
        knownTypes.put("ObjectArrayList", "import it.unimi.dsi.fastutil.objects.ObjectArrayList;");

        // Inner types that Spoon resolves to simple names in no-classpath mode
        Map<String, String> innerTypes = new LinkedHashMap<>();
        innerTypes.put("Axis", "import net.minecraft.util.math.Direction.Axis;");
        innerTypes.put("AxisDirection", "import net.minecraft.util.math.Direction.AxisDirection;");
        innerTypes.put("ShapeType", "import net.minecraft.world.RaycastContext.ShapeType;");
        innerTypes.put("FluidHandling", "import net.minecraft.world.RaycastContext.FluidHandling;");
        innerTypes.put("MoveEffect", "import net.minecraft.entity.Entity.MoveEffect;");
        innerTypes.put("RemovalReason", "import net.minecraft.entity.Entity.RemovalReason;");
        innerTypes.put("QueuedCollisionCheck", "import net.minecraft.entity.Entity.QueuedCollisionCheck;");
        innerTypes.put("Type", "import net.minecraft.util.hit.HitResult.Type;");

        for (var entry : innerTypes.entrySet()) {
            if (code.contains(entry.getKey())) {
                knownTypes.put(entry.getKey(), entry.getValue());
            }
        }

        for (var entry : knownTypes.entrySet()) {
            if (code.contains(entry.getKey())) {
                imports.add(entry.getValue());
            }
        }

        // Static wildcard imports for Entity hierarchy — makes static fields and methods accessible
        // (FLAGS, POSE, NULL_BOX, collectStepHeights, etc.)
        for (String targetClass : AnalysisConfig.TARGET_CLASSES_DOT) {
            imports.add("import static " + targetClass + ".*;");
        }
    }

    private Set<String> filterImports(Set<String> allImports, String code) {
        Set<String> kept = new TreeSet<>();
        for (String imp : allImports) {
            String imported = imp.replaceFirst("import\\s+(static\\s+)?", "")
                                 .replace(";", "").trim();
            if (imported.endsWith(".*")) {
                kept.add(imp);
            } else {
                String simpleName = imported.substring(imported.lastIndexOf('.') + 1);
                if (code.contains(simpleName)) {
                    kept.add(imp);
                }
            }
        }
        return kept;
    }

    private int countStatements(CtMethod<?> method) {
        if (method.getBody() == null) return 0;
        return method.getBody().getElements(new TypeFilter<>(CtStatement.class)).size();
    }

    /**
     * Adds access widener entries for known private/protected static members
     * that sliced code references via import static.
     * Spoon in no-classpath mode can't determine visibility, so these are hardcoded.
     */
    private void addKnownPrivateMembers(Set<String> awEntries) {
        // Entity — private static TrackedData fields
        awEntries.add("accessible field net/minecraft/entity/Entity FLAGS Lnet/minecraft/entity/data/TrackedData;");
        awEntries.add("accessible field net/minecraft/entity/Entity POSE Lnet/minecraft/entity/data/TrackedData;");
        awEntries.add("accessible field net/minecraft/entity/Entity NO_GRAVITY Lnet/minecraft/entity/data/TrackedData;");
        awEntries.add("accessible field net/minecraft/entity/Entity FROZEN_TICKS Lnet/minecraft/entity/data/TrackedData;");
        awEntries.add("accessible field net/minecraft/entity/Entity SILENT Lnet/minecraft/entity/data/TrackedData;");
        awEntries.add("accessible field net/minecraft/entity/Entity NULL_BOX Lnet/minecraft/util/math/Box;");
        // Entity — private/protected static int constants
        awEntries.add("accessible field net/minecraft/entity/Entity SPRINTING_FLAG_INDEX I");
        awEntries.add("accessible field net/minecraft/entity/Entity SWIMMING_FLAG_INDEX I");
        awEntries.add("accessible field net/minecraft/entity/Entity SNEAKING_FLAG_INDEX I");
        awEntries.add("accessible field net/minecraft/entity/Entity GLIDING_FLAG_INDEX I");
        // Entity — private/protected methods
        awEntries.add("accessible method net/minecraft/entity/Entity collectStepHeights (Lnet/minecraft/util/math/Box;Ljava/util/List;FF)[F");
        // Note: do NOT add the [Lfloat; variant — only [F is valid
        awEntries.add("accessible method net/minecraft/entity/Entity getAxisCheckOrder (Lnet/minecraft/util/math/Vec3d;)Ljava/lang/Iterable;");
        awEntries.add("accessible method net/minecraft/entity/Entity isControlledByMainPlayer ()Z");
        // LivingEntity — protected methods
        awEntries.add("accessible method net/minecraft/entity/LivingEntity canGlide ()Z");
        // Entity — inner classes
        awEntries.add("accessible class net/minecraft/entity/Entity$QueuedCollisionCheck");
        // LivingEntity — private static TrackedData fields
        awEntries.add("accessible field net/minecraft/entity/LivingEntity LIVING_FLAGS Lnet/minecraft/entity/data/TrackedData;");
        awEntries.add("accessible field net/minecraft/entity/LivingEntity HEALTH Lnet/minecraft/entity/data/TrackedData;");
        awEntries.add("accessible field net/minecraft/entity/LivingEntity SLEEPING_POSITION Lnet/minecraft/entity/data/TrackedData;");
        // PlayerEntity — private static TrackedData fields
        awEntries.add("accessible field net/minecraft/entity/player/PlayerEntity ABSORPTION_AMOUNT Lnet/minecraft/entity/data/TrackedData;");
    }

    /**
     * Scans generated code for references to non-public static fields and methods,
     * and generates access widener entries for them.
     */
    private void collectAccessWidenerEntries(CtType<?> type, String code,
                                              Map<String, CtType<?>> typeIndex,
                                              Set<String> awEntries) {
        // Walk up the hierarchy looking for static fields referenced in code
        CtType<?> current = type;
        while (current != null) {
            String classPath = current.getQualifiedName().replace('.', '/');
            for (CtField<?> field : current.getFields()) {
                if (!field.isStatic()) continue;
                String name = field.getSimpleName();
                // Check if field name appears in code (as bare identifier)
                if (!code.contains(name)) continue;
                // Check visibility
                if (field.isPrivate() || field.isProtected()) {
                    String typeDesc = getFieldTypeDescriptor(field);
                    awEntries.add("accessible field " + classPath + " " + name + " " + typeDesc);
                }
            }
            // Also check for static methods
            for (CtMethod<?> method : current.getMethods()) {
                if (!method.isStatic()) continue;
                String name = method.getSimpleName();
                if (!code.contains(name + "(")) continue;
                if (method.isPrivate() || method.isProtected()) {
                    String methodDesc = getMethodDescriptor(method);
                    awEntries.add("accessible method " + classPath + " " + name + " " + methodDesc);
                }
            }

            if (current instanceof CtClass<?> ctClass && ctClass.getSuperclass() != null) {
                current = typeIndex.get(ctClass.getSuperclass().getQualifiedName());
            } else {
                break;
            }
        }
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
                    yield "[L" + name.substring(0, name.length() - 2).replace('.', '/') + ";";
                }
                yield "L" + name.replace('.', '/') + ";";
            }
        };
    }
}
