package murat.simv2.analysis;

import spoon.Launcher;
import spoon.processing.Processor;
import spoon.reflect.code.CtComment;
import spoon.reflect.code.CtLiteral;
import spoon.reflect.cu.CompilationUnit;
import spoon.reflect.code.CtTypeAccess;
import spoon.reflect.declaration.CtElement;
import spoon.reflect.declaration.CtPackage;
import spoon.reflect.declaration.CtType;
import spoon.reflect.factory.Factory;
import spoon.reflect.reference.CtTypeReference;
import spoon.reflect.visitor.DefaultImportComparator;
import spoon.reflect.visitor.DefaultJavaPrettyPrinter;
import spoon.reflect.visitor.ForceImportProcessor;
import spoon.reflect.visitor.ImportCleaner;
import spoon.reflect.visitor.ImportConflictDetector;
import spoon.reflect.visitor.PrettyPrinter;
import spoon.reflect.visitor.filter.TypeFilter;
import spoon.support.compiler.VirtualFile;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

final class MirrorClassEmitter {
    private static final String SLICED_PACKAGE = "murat.simv2.simulation.sliced";
    private static final String MIRROR_PACKAGE_PREFIX = "murat.simv2.simulation.mirror";
    private static final String MIRROR_PACKAGE_PREFIX_DOT = MIRROR_PACKAGE_PREFIX + ".";
    private static final Pattern IMPORT_PATTERN = Pattern.compile("import\\s+(static\\s+)?([\\w.$*]+);");
    private static final Pattern QUALIFIED_REFERENCE_PATTERN = Pattern.compile(
        "(?<![\\w$.])(net\\.minecraft(?:\\.[A-Za-z_$][A-Za-z\\d_$]*)+)"
    );
    private static final Pattern DESCRIPTOR_OBJECT_PATTERN = Pattern.compile("L([^;]+);");
    private static final String NESTED_STUBS_BEGIN = "// BEGIN GENERATED MIRROR NESTED STUBS";
    private static final String NESTED_STUBS_END = "// END GENERATED MIRROR NESTED STUBS";
    private static final String ENTITY_COMPAT_BEGIN = "// BEGIN GENERATED ENTITY COMPAT STUBS";
    private static final String ENTITY_COMPAT_END = "// END GENERATED ENTITY COMPAT STUBS";
    private static final String PRIMARY_CONTRACT_BEGIN = "// BEGIN GENERATED PRIMARY CONTRACT STUBS";
    private static final String PRIMARY_CONTRACT_END = "// END GENERATED PRIMARY CONTRACT STUBS";
    private static final int ACC_PUBLIC = 0x0001;
    private static final int ACC_PRIVATE = 0x0002;
    private static final int ACC_INTERFACE = 0x0200;
    private static final int ACC_STATIC = 0x0008;
    private static final int ACC_BRIDGE = 0x0040;
    private static final int ACC_SYNTHETIC = 0x1000;
    private static final int ACC_ENUM = 0x4000;
    private static final Comparator<FieldStub> FIELD_STUB_COMPARATOR = Comparator
        .comparing(FieldStub::fieldName)
        .thenComparing(FieldStub::fieldType)
        .thenComparing(FieldStub::isStatic);

    private final Path outputDir;
    private final Path minecraftJar;
    private final Map<String, String> primaryClassesBySimpleName;
    private Map<String, String> closureRewriteMap = Map.of();
    private List<TypeRewriteRule> closureRewriteRules = List.of();
    private Map<String, BytecodeClass> minecraftClassIndex = Map.of();

    MirrorClassEmitter(Path outputDir, Path minecraftJar) {
        this.outputDir = outputDir;
        this.minecraftJar = minecraftJar;
        this.primaryClassesBySimpleName = buildPrimaryClassMap();
    }

    void emit(MirrorClosure mirrorClosure, Map<String, String> slicedPrimarySources) throws IOException {
        Map<String, String> primarySources = slicedPrimarySources == null ? Map.of() : slicedPrimarySources;
        Path mirrorRootDir = outputDir.resolve("java/murat/simv2/simulation/mirror");
        deleteDirectoryIfExists(mirrorRootDir);
        Files.createDirectories(mirrorRootDir);

        minecraftClassIndex = loadMinecraftClassIndex();
        Set<String> closureClasses = buildClosureClasses(mirrorClosure, primarySources);
        System.out.println("Mirror closure classes from artifacts: " + closureClasses.size());
        closureRewriteMap = buildClosureRewriteMap(closureClasses);
        closureRewriteRules = buildClosureRewriteRules(closureRewriteMap);
        Map<String, Set<String>> closureMethodSelectors = buildClosureMethodSelectors(closureClasses, mirrorClosure);
        Map<String, Set<FieldStub>> closureFields = buildClosureFields(closureClasses);
        populateBytecodeMethodSelectors(closureClasses, closureMethodSelectors);
        populateBytecodeFieldStubs(closureClasses, closureFields);
        emitPrimaryMirrorClasses(primarySources, mirrorRootDir);
        emitClosureStubs(closureClasses, closureMethodSelectors, closureFields, mirrorRootDir);
    }

    private void deleteDirectoryIfExists(Path dir) throws IOException {
        if (dir == null || !Files.exists(dir)) {
            return;
        }
        try (var walk = Files.walk(dir)) {
            walk.sorted(Comparator.reverseOrder()).forEach(path -> {
                try {
                    Files.deleteIfExists(path);
                } catch (IOException ex) {
                    throw new RuntimeException("Failed to delete path: " + path, ex);
                }
            });
        } catch (RuntimeException ex) {
            if (ex.getCause() instanceof IOException ioEx) {
                throw ioEx;
            }
            throw ex;
        }
    }

    private Map<String, String> buildPrimaryClassMap() {
        Map<String, String> map = new LinkedHashMap<>();
        for (String className : AnalysisConfig.TARGET_CLASSES_DOT) {
            int lastDot = className.lastIndexOf('.');
            if (lastDot <= 0 || lastDot == className.length() - 1) {
                continue;
            }
            map.put(className.substring(lastDot + 1), className);
        }
        return Map.copyOf(map);
    }

    private void emitPrimaryMirrorClasses(Map<String, String> slicedPrimarySources, Path mirrorRootDir) throws IOException {
        for (Map.Entry<String, String> entry : primaryClassesBySimpleName.entrySet()) {
            String fqcn = entry.getValue();
            String source = slicedPrimarySources.get(fqcn);
            if (source == null || source.isBlank()) {
                continue;
            }
            String transformed = transformPrimaryClassSource(source, fqcn);

            Path outputFile = mirrorRootDir.resolve(fqcn.replace('.', '/') + ".java");
            Files.createDirectories(outputFile.getParent());
            Files.writeString(outputFile, transformed);
            System.out.println("Generated mirrored class: " + fqcn);
        }
    }

    private String transformPrimaryClassSource(String source, String fqcn) {
        String transformed = transformPrimaryClassSourceWithAst(source, fqcn);
        if ("net.minecraft.entity.Entity".equals(fqcn)) {
            transformed = normalizeEntityPrimarySignatures(transformed);
            transformed = upsertEntityCompatBlock(transformed);
        }
        if ("net.minecraft.entity.LivingEntity".equals(fqcn)) {
            transformed = normalizeLivingEntityPrimarySignatures(transformed);
        }
        if ("net.minecraft.entity.Entity".equals(fqcn)
            || "net.minecraft.entity.LivingEntity".equals(fqcn)) {
            transformed = upsertPrimaryContractBlock(transformed, fqcn);
        }
        transformed = injectPrimaryNoArgConstructor(transformed, fqcn);
        return transformed;
    }

    private String transformPrimaryClassSourceWithAst(String source, String fqcn) {
        Launcher launcher = new Launcher();
        launcher.getEnvironment().setNoClasspath(true);
        launcher.getEnvironment().setIgnoreSyntaxErrors(true);
        launcher.getEnvironment().setComplianceLevel(21);
        launcher.getEnvironment().setAutoImports(true);
        launcher.addInputResource(new VirtualFile(source, simpleNameOf(fqcn) + ".java"));
        launcher.buildModel();

        CtType<?> type = launcher.getModel().getAllTypes().stream()
            .findFirst()
            .orElseThrow(() -> new IllegalStateException("Unable to parse primary sliced source for " + fqcn));
        Factory factory = launcher.getFactory();
        String mirroredPackageName = mirroredPackageNameOf(fqcn);
        CtPackage mirroredPackage = factory.Package().getOrCreate(mirroredPackageName);
        CtPackage existingPackage = type.getPackage();
        if (existingPackage != null && !mirroredPackageName.equals(existingPackage.getQualifiedName())) {
            existingPackage.removeType(type);
        }
        if (!mirroredPackage.getTypes().contains(type)) {
            mirroredPackage.addType(type);
        }

        String expectedSlicedSimpleName = "Sliced" + simpleNameOf(fqcn);
        if (expectedSlicedSimpleName.equals(type.getSimpleName())) {
            type.setSimpleName(simpleNameOf(fqcn));
        }

        Map<String, String> uniqueImportsBySimpleName = uniqueImportsBySimpleName(source);
        @SuppressWarnings("unchecked")
        List<CtTypeReference<?>> typeReferences = (List<CtTypeReference<?>>) (List<?>)
            type.getElements(new TypeFilter<>(CtTypeReference.class));
        for (CtTypeReference<?> ref : new ArrayList<>(typeReferences)) {
            String rewrite = rewritePrimaryReference(ref, fqcn, uniqueImportsBySimpleName);
            if (rewrite == null || rewrite.equals(ref.getQualifiedName())) {
                continue;
            }
            ref.replace(factory.Type().createReference(rewrite));
        }
        @SuppressWarnings("unchecked")
        List<CtTypeAccess<?>> typeAccesses = (List<CtTypeAccess<?>>) (List<?>)
            type.getElements(new TypeFilter<>(CtTypeAccess.class));
        for (CtTypeAccess<?> typeAccess : new ArrayList<>(typeAccesses)) {
            CtTypeReference<?> accessedType = typeAccess.getAccessedType();
            if (accessedType == null) {
                continue;
            }
            String rewrite = rewritePrimaryTypeName(
                accessedType.getQualifiedName(),
                fqcn,
                uniqueImportsBySimpleName
            );
            if (rewrite == null || rewrite.equals(accessedType.getQualifiedName())) {
                continue;
            }
            typeAccess.setAccessedType(factory.Type().createReference(rewrite));
        }
        rewritePrimaryCommentsAndLiterals(type, fqcn);

        CompilationUnit compilationUnit = factory.Core().createCompilationUnit();
        compilationUnit.setFile(Path.of(type.getSimpleName() + ".java").toFile());
        compilationUnit.setDeclaredPackage(mirroredPackage);
        compilationUnit.addDeclaredType(type);
        type.setPosition(factory.createPartialSourcePosition(compilationUnit));

        PrettyPrinter printer = createPrimaryPrettyPrinter(factory);
        return printer.printCompilationUnit(compilationUnit);
    }

    private PrettyPrinter createPrimaryPrettyPrinter(Factory factory) {
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

    private void rewritePrimaryCommentsAndLiterals(CtType<?> type, String fqcn) {
        for (CtComment comment : new ArrayList<>(type.getComments())) {
            String content = comment.getContent();
            if (content == null || content.isBlank()) {
                continue;
            }
            String rewritten = content
                .replace("Sliced from ", "Mirrored from ")
                .replace("sliced construction", "mirror construction")
                .replace("Mirrored from " + mirroredFqcn(fqcn), "Mirrored from " + fqcn);
            comment.setContent(rewritten);
        }
        @SuppressWarnings("unchecked")
        List<CtLiteral<?>> literals = (List<CtLiteral<?>>) (List<?>) type.getElements(new TypeFilter<>(CtLiteral.class));
        for (CtLiteral<?> literal : literals) {
            Object value = literal.getValue();
            if (!(value instanceof String s) || !s.contains("sliced construction")) {
                continue;
            }
            @SuppressWarnings("unchecked")
            CtLiteral<String> stringLiteral = (CtLiteral<String>) literal;
            stringLiteral.setValue(s.replace("sliced construction", "mirror construction"));
        }
    }

    private Map<String, String> uniqueImportsBySimpleName(String source) {
        Map<String, Set<String>> bySimpleName = new LinkedHashMap<>();
        for (String line : source.split("\\R")) {
            Matcher importMatcher = IMPORT_PATTERN.matcher(line.trim());
            if (!importMatcher.matches() || importMatcher.group(1) != null) {
                continue;
            }
            String importTarget = importMatcher.group(2);
            if (importTarget.endsWith(".*")) {
                continue;
            }
            bySimpleName.computeIfAbsent(simpleNameOf(importTarget), key -> new TreeSet<>()).add(importTarget);
            int memberSeparator = importTarget.lastIndexOf('.');
            if (memberSeparator > 0) {
                String ownerType = importTarget.substring(0, memberSeparator);
                bySimpleName.computeIfAbsent(simpleNameOf(ownerType), key -> new TreeSet<>()).add(ownerType);
            }
        }
        Map<String, String> unique = new LinkedHashMap<>();
        for (Map.Entry<String, Set<String>> entry : bySimpleName.entrySet()) {
            if (entry.getValue().size() == 1) {
                unique.put(entry.getKey(), entry.getValue().iterator().next());
            }
        }
        return unique;
    }

    private String rewritePrimaryReference(CtTypeReference<?> reference,
                                           String currentFqcn,
                                           Map<String, String> uniqueImportsBySimpleName) {
        return rewritePrimaryTypeName(reference.getQualifiedName(), currentFqcn, uniqueImportsBySimpleName);
    }

    private String mirroredPackageNameOf(String fqcn) {
        String packageName = packageNameOf(fqcn);
        return packageName.isBlank() ? MIRROR_PACKAGE_PREFIX : MIRROR_PACKAGE_PREFIX + "." + packageName;
    }

    private String rewritePrimaryTypeName(String qualifiedName,
                                          String currentFqcn,
                                          Map<String, String> uniqueImportsBySimpleName) {
        if (qualifiedName == null || qualifiedName.isBlank()) {
            return null;
        }
        String importedQualifiedName;
        if (!qualifiedName.contains(".")) {
            importedQualifiedName = uniqueImportsBySimpleName.getOrDefault(qualifiedName, qualifiedName);
        } else {
            importedQualifiedName = qualifiedName;
            if (!qualifiedName.startsWith("net.minecraft.") && !qualifiedName.startsWith(MIRROR_PACKAGE_PREFIX_DOT)) {
                int firstDot = qualifiedName.indexOf('.');
                String prefix = qualifiedName.substring(0, firstDot);
                String mappedPrefix = uniqueImportsBySimpleName.get(prefix);
                if (mappedPrefix != null) {
                    importedQualifiedName = mappedPrefix + qualifiedName.substring(firstDot);
                }
            }
        }
        for (Map.Entry<String, String> entry : primaryClassesBySimpleName.entrySet()) {
            String targetSimpleName = entry.getKey();
            String targetFqcn = entry.getValue();
            if (targetFqcn.equals(currentFqcn)
                && ("Sliced" + targetSimpleName).equals(qualifiedName)) {
                return targetSimpleName;
            }
            if (targetFqcn.equals(currentFqcn)
                && targetSimpleName.equals(qualifiedName)) {
                return null;
            }
            if (qualifiedName.equals("Sliced" + targetSimpleName)
                || qualifiedName.equals(SLICED_PACKAGE + ".Sliced" + targetSimpleName)
                || qualifiedName.equals(targetSimpleName)
                || importedQualifiedName.equals(targetFqcn)
                || qualifiedName.equals(targetFqcn)) {
                return mirroredFqcn(targetFqcn);
            }
        }
        String rewritten = resolveMirroredTypeName(importedQualifiedName);
        if (rewritten != null && !rewritten.equals(importedQualifiedName)) {
            return rewritten;
        }
        return null;
    }

    private String rewriteTypeReference(String typeName) {
        if (typeName.startsWith(MIRROR_PACKAGE_PREFIX_DOT)) {
            return typeName;
        }
        String rewritten = resolveMirroredTypeName(typeName);
        if (rewritten != null) {
            return rewritten;
        }
        return typeName;
    }

    private Set<String> buildClosureClasses(MirrorClosure mirrorClosure, Map<String, String> slicedPrimarySources) {
        TreeSet<String> closureClasses = new TreeSet<>(primaryClassesBySimpleName.values());
        if (mirrorClosure != null && mirrorClosure.classes() != null) {
            for (String className : mirrorClosure.classes()) {
                if (className != null && className.startsWith("net.minecraft.")) {
                    addClosureClass(closureClasses, className.trim());
                }
            }
        }
        if (mirrorClosure != null && mirrorClosure.methodsByClass() != null) {
            for (Map.Entry<String, Set<String>> entry : mirrorClosure.methodsByClass().entrySet()) {
                String ownerClass = entry.getKey();
                if (ownerClass != null && ownerClass.startsWith("net.minecraft.")) {
                    addClosureClass(closureClasses, ownerClass.trim());
                }
                if (entry.getValue() == null) {
                    continue;
                }
            }
        }
        for (String fqcn : primaryClassesBySimpleName.values()) {
            String source = slicedPrimarySources.get(fqcn);
            if (source == null || source.isBlank()) {
                continue;
            }
            for (String className : extractVanillaClassesFromSource(source)) {
                addClosureClass(closureClasses, className);
            }
        }

        closureClasses = new TreeSet<>(augmentClosureClassesWithHierarchy(closureClasses));
        closureClasses = new TreeSet<>(augmentClosureClassesWithMemberDescriptorTypes(closureClasses));
        closureClasses = new TreeSet<>(augmentClosureClassesWithNamedNestedTypes(closureClasses));

        return Set.copyOf(closureClasses);
    }

    private Set<String> augmentClosureClassesWithHierarchy(Set<String> closureClasses) {
        if (closureClasses == null || closureClasses.isEmpty() || minecraftClassIndex.isEmpty()) {
            return closureClasses == null ? Set.of() : Set.copyOf(closureClasses);
        }
        TreeSet<String> augmented = new TreeSet<>(closureClasses);
        for (String className : new TreeSet<>(closureClasses)) {
            addHierarchyClasses(augmented, className, new TreeSet<>());
        }
        return Set.copyOf(augmented);
    }

    private Set<String> augmentClosureClassesWithNamedNestedTypes(Set<String> closureClasses) {
        if (closureClasses == null || closureClasses.isEmpty() || minecraftClassIndex.isEmpty()) {
            return closureClasses == null ? Set.of() : Set.copyOf(closureClasses);
        }
        TreeSet<String> augmented = new TreeSet<>(closureClasses);
        boolean changed;
        do {
            changed = false;
            List<String> owners = new ArrayList<>(augmented);
            for (String owner : owners) {
                String nestedPrefix = owner + "$";
                for (String candidate : minecraftClassIndex.keySet()) {
                    if (!candidate.startsWith(nestedPrefix)) {
                        continue;
                    }
                    String nestedSuffix = candidate.substring(nestedPrefix.length());
                    if (nestedSuffix.isBlank()) {
                        continue;
                    }
                    String[] segments = nestedSuffix.split("\\$");
                    boolean namedNested = true;
                    for (String segment : segments) {
                        if (!isJavaIdentifier(segment)) {
                            namedNested = false;
                            break;
                        }
                    }
                    if (!namedNested) {
                        continue;
                    }
                    if (augmented.add(candidate)) {
                        changed = true;
                    }
                }
            }
        } while (changed);
        return Set.copyOf(augmented);
    }

    private Set<String> augmentClosureClassesWithMemberDescriptorTypes(Set<String> closureClasses) {
        if (closureClasses == null || closureClasses.isEmpty() || minecraftClassIndex.isEmpty()) {
            return closureClasses == null ? Set.of() : Set.copyOf(closureClasses);
        }
        TreeSet<String> augmented = new TreeSet<>(closureClasses);
        for (String className : new ArrayList<>(augmented)) {
            BytecodeClass classInfo = minecraftClassIndex.get(className);
            if (classInfo == null) {
                continue;
            }
            for (BytecodeMember field : classInfo.fields()) {
                collectDescriptorClasses(field.descriptor(), augmented);
            }
            for (BytecodeMember method : classInfo.methods()) {
                collectDescriptorClasses(method.descriptor(), augmented);
            }
        }
        return Set.copyOf(augmented);
    }

    private void addHierarchyClasses(Set<String> closureClasses, String className, Set<String> visited) {
        if (className == null || !visited.add(className)) {
            return;
        }
        BytecodeClass classInfo = minecraftClassIndex.get(className);
        if (classInfo == null) {
            return;
        }
        String superClass = classInfo.superClassName();
        if (superClass != null && superClass.startsWith("net.minecraft.")) {
            addClosureClass(closureClasses, superClass);
            addHierarchyClasses(closureClasses, superClass, visited);
        }
        for (String interfaceName : classInfo.interfaceNames()) {
            if (interfaceName != null && interfaceName.startsWith("net.minecraft.")) {
                addClosureClass(closureClasses, interfaceName);
                addHierarchyClasses(closureClasses, interfaceName, visited);
            }
        }
    }

    private void addClosureClass(Set<String> closureClasses, String className) {
        if (className == null || className.isBlank()) {
            return;
        }
        closureClasses.add(className);
        addOwningNestedClasses(closureClasses, className);
    }

    private void addOwningNestedClasses(Set<String> closureClasses, String className) {
        int nestedIndex = className.lastIndexOf('$');
        while (nestedIndex > 0) {
            String ownerClass = className.substring(0, nestedIndex);
            String ownerSimpleName = simpleNameOf(ownerClass);
            if (isJavaIdentifierOrDollar(ownerSimpleName)) {
                closureClasses.add(ownerClass);
            }
            className = ownerClass;
            nestedIndex = className.lastIndexOf('$');
        }
    }

    private void collectDescriptorClasses(String descriptor, Set<String> closureClasses) {
        if (descriptor == null || descriptor.isBlank()) {
            return;
        }
        Matcher descriptorMatcher = DESCRIPTOR_OBJECT_PATTERN.matcher(descriptor);
        while (descriptorMatcher.find()) {
            String fqcn = descriptorMatcher.group(1).replace('/', '.');
            if (fqcn.startsWith("net.minecraft.")) {
                addClosureClass(closureClasses, fqcn);
            }
        }
    }

    private Set<String> extractVanillaClassesFromSource(String source) {
        TreeSet<String> classes = new TreeSet<>();
        String[] lines = source.split("\\R", -1);
        for (String line : lines) {
            Matcher importMatcher = IMPORT_PATTERN.matcher(line.trim());
            if (!importMatcher.matches()) {
                continue;
            }
            String importTarget = importMatcher.group(2);
            boolean isStatic = importMatcher.group(1) != null;
            if (importTarget.endsWith(".*")) {
                if (!isStatic) {
                    continue;
                }
                importTarget = importTarget.substring(0, importTarget.length() - 2);
            } else if (isStatic) {
                int memberSeparator = importTarget.lastIndexOf('.');
                if (memberSeparator <= 0) {
                    continue;
                }
                importTarget = importTarget.substring(0, memberSeparator);
            }
            String className = toBinaryVanillaClassName(importTarget, true);
            if (className != null) {
                addClosureClass(classes, className);
            }
        }

        Matcher qualifierMatcher = QUALIFIED_REFERENCE_PATTERN.matcher(source);
        while (qualifierMatcher.find()) {
            String className = toBinaryVanillaClassName(qualifierMatcher.group(1), false);
            if (className != null) {
                addClosureClass(classes, className);
            }
        }
        return classes;
    }

    private String toBinaryVanillaClassName(String reference, boolean assumeTypeReference) {
        if (reference == null || reference.isBlank()) {
            return null;
        }
        String normalized = reference.trim();
        if (!normalized.startsWith("net.minecraft.")) {
            return null;
        }

        String[] segments = normalized.split("\\.");
        if (segments.length < 3) {
            return null;
        }

        List<String> packageSegments = new ArrayList<>();
        List<String> typeSegments = new ArrayList<>();
        boolean typeStarted = false;
        for (int i = 2; i < segments.length; i++) {
            String segment = segments[i];
            if (segment.isBlank()) {
                break;
            }
            if (!typeStarted) {
                if (!isPotentialTypeSegment(segment)) {
                    packageSegments.add(segment);
                    continue;
                }
                typeStarted = true;
                typeSegments.add(segment);
                continue;
            }

            if (assumeTypeReference) {
                if (!isJavaIdentifierOrDollar(segment)) {
                    break;
                }
                typeSegments.add(segment);
                continue;
            }

            if (!isPotentialTypeSegment(segment)) {
                break;
            }
            if (i == segments.length - 1 && typeSegments.size() >= 1 && isLikelyConstantSegment(segment)) {
                break;
            }
            typeSegments.add(segment);
        }

        if (typeSegments.isEmpty()) {
            return null;
        }

        StringBuilder className = new StringBuilder("net.minecraft");
        for (String packageSegment : packageSegments) {
            className.append('.').append(packageSegment);
        }
        className.append('.').append(typeSegments.get(0));
        for (int i = 1; i < typeSegments.size(); i++) {
            className.append('$').append(typeSegments.get(i));
        }
        return className.toString();
    }

    private boolean isPotentialTypeSegment(String segment) {
        return isJavaIdentifierOrDollar(segment) && Character.isUpperCase(segment.charAt(0));
    }

    private boolean isLikelyConstantSegment(String segment) {
        if (segment == null || segment.isBlank()) {
            return false;
        }
        boolean hasLetter = false;
        for (int i = 0; i < segment.length(); i++) {
            char ch = segment.charAt(i);
            if (Character.isLetter(ch)) {
                hasLetter = true;
                if (!Character.isUpperCase(ch)) {
                    return false;
                }
            } else if (ch != '_' && !Character.isDigit(ch)) {
                return false;
            }
        }
        return hasLetter;
    }

    private boolean isJavaIdentifierOrDollar(String value) {
        if (value == null || value.isBlank()) {
            return false;
        }
        char first = value.charAt(0);
        if (first != '$' && !Character.isJavaIdentifierStart(first)) {
            return false;
        }
        for (int i = 1; i < value.length(); i++) {
            char ch = value.charAt(i);
            if (ch != '$' && !Character.isJavaIdentifierPart(ch)) {
                return false;
            }
        }
        return true;
    }

    private Map<String, String> buildClosureRewriteMap(Set<String> closureClasses) {
        Map<String, String> rewrites = new TreeMap<>();
        for (String className : closureClasses) {
            String sourceTypeName = toSourceTypeName(className);
            String mirroredSourceTypeName = mirroredFqcn(sourceTypeName);
            rewrites.putIfAbsent(className, mirroredSourceTypeName);
            rewrites.putIfAbsent(sourceTypeName, mirroredSourceTypeName);
        }
        return Map.copyOf(rewrites);
    }

    private Map<String, Set<String>> buildClosureMethodSelectors(Set<String> closureClasses, MirrorClosure mirrorClosure) {
        Map<String, Set<String>> methodsByClass = new TreeMap<>();
        for (String className : closureClasses) {
            methodsByClass.put(className, new TreeSet<>());
        }
        if (mirrorClosure != null && mirrorClosure.methodsByClass() != null) {
            for (Map.Entry<String, Set<String>> entry : mirrorClosure.methodsByClass().entrySet()) {
                String className = entry.getKey();
                if (className == null || !className.startsWith("net.minecraft.")) {
                    continue;
                }
                Set<String> selectors = methodsByClass.computeIfAbsent(className, ignored -> new TreeSet<>());
                if (entry.getValue() != null) {
                    selectors.addAll(entry.getValue());
                }
            }
        }
        applyManualMethodSelectors(methodsByClass);
        return methodsByClass;
    }

    private void populateBytecodeMethodSelectors(Set<String> closureClasses, Map<String, Set<String>> methodsByClass) {
        if (minecraftClassIndex.isEmpty()) {
            return;
        }
        for (String className : closureClasses) {
            if (className == null || !className.startsWith("net.minecraft.")) {
                continue;
            }
            Set<String> selectors = methodsByClass.computeIfAbsent(className, ignored -> new TreeSet<>());
            addMethodSelectorsForHierarchy(className, selectors, new TreeSet<>(), true);
            BytecodeClass classInfo = minecraftClassIndex.get(className);
            if (classInfo != null && classInfo.isEnum()) {
                selectors.add("ordinal()I");
            }
        }
    }

    private void addMethodSelectorsForHierarchy(String className,
                                                Set<String> selectors,
                                                Set<String> visited,
                                                boolean includeConstructors) {
        if (!visited.add(className)) {
            return;
        }
        BytecodeClass classInfo = minecraftClassIndex.get(className);
        if (classInfo == null) {
            return;
        }
        for (BytecodeMember method : classInfo.methods()) {
            if (!isEligibleMethod(method, includeConstructors)) {
                continue;
            }
            if (isObjectFinalMethod(method.name(), method.descriptor())) {
                continue;
            }
            String prefix = (method.access() & ACC_STATIC) != 0 ? "static " : "";
            selectors.add(prefix + method.name() + method.descriptor());
        }
        String superClass = classInfo.superClassName();
        if (superClass != null && superClass.startsWith("net.minecraft.")) {
            addMethodSelectorsForHierarchy(superClass, selectors, visited, false);
        }
        for (String interfaceName : classInfo.interfaceNames()) {
            if (interfaceName != null && interfaceName.startsWith("net.minecraft.")) {
                addMethodSelectorsForHierarchy(interfaceName, selectors, visited, false);
            }
        }
    }

    private boolean isEligibleMethod(BytecodeMember method, boolean includeConstructors) {
        if (method == null) {
            return false;
        }
        if ((method.access() & ACC_SYNTHETIC) != 0 || (method.access() & ACC_BRIDGE) != 0) {
            return false;
        }
        if ("<clinit>".equals(method.name())) {
            return false;
        }
        if ("<init>".equals(method.name())) {
            return includeConstructors;
        }
        return includeConstructors || (method.access() & ACC_PRIVATE) == 0;
    }

    private boolean isObjectFinalMethod(String methodName, String descriptor) {
        return ("getClass".equals(methodName) && "()Ljava/lang/Class;".equals(descriptor))
            || ("notify".equals(methodName) && "()V".equals(descriptor))
            || ("notifyAll".equals(methodName) && "()V".equals(descriptor))
            || ("wait".equals(methodName) && "()V".equals(descriptor))
            || ("wait".equals(methodName) && "(J)V".equals(descriptor))
            || ("wait".equals(methodName) && "(JI)V".equals(descriptor));
    }

    private Map<String, Set<FieldStub>> buildClosureFields(Set<String> closureClasses) {
        Map<String, Set<FieldStub>> fieldsByClass = new TreeMap<>();
        for (String className : closureClasses) {
            fieldsByClass.put(className, new TreeSet<>(FIELD_STUB_COMPARATOR));
        }
        applyManualFieldStubs(fieldsByClass);
        return fieldsByClass;
    }

    private void populateBytecodeFieldStubs(Set<String> closureClasses, Map<String, Set<FieldStub>> fieldsByClass) {
        if (minecraftClassIndex.isEmpty()) {
            return;
        }
        for (String className : closureClasses) {
            if (className == null || !className.startsWith("net.minecraft.")) {
                continue;
            }
            Set<FieldStub> fieldStubs = fieldsByClass.computeIfAbsent(
                className,
                ignored -> new TreeSet<>(FIELD_STUB_COMPARATOR)
            );
            addFieldStubsForHierarchy(className, fieldStubs, new TreeSet<>(), true);
        }
    }

    private void addFieldStubsForHierarchy(String className,
                                           Set<FieldStub> fieldStubs,
                                           Set<String> visited,
                                           boolean includePrivate) {
        if (!visited.add(className)) {
            return;
        }
        BytecodeClass classInfo = minecraftClassIndex.get(className);
        if (classInfo == null) {
            return;
        }
        for (BytecodeMember field : classInfo.fields()) {
            if (field == null || !isJavaIdentifierOrDollar(field.name())) {
                continue;
            }
            if (!includePrivate && (field.access() & ACC_PRIVATE) != 0) {
                continue;
            }
            String fieldType = normalizeStubType(javaTypeFromFieldDescriptor(field.descriptor(), field.signature()));
            boolean isStatic = (field.access() & ACC_STATIC) != 0;
            boolean alreadyDeclared = fieldStubs.stream().anyMatch(existing -> existing.fieldName().equals(field.name()));
            if (!alreadyDeclared) {
                fieldStubs.add(new FieldStub(field.name(), fieldType, isStatic));
            }
        }
        String superClass = classInfo.superClassName();
        if (superClass != null && superClass.startsWith("net.minecraft.")) {
            addFieldStubsForHierarchy(superClass, fieldStubs, visited, false);
        }
    }

    private String javaTypeFromFieldDescriptor(String descriptor, String signature) {
        if (signature != null && !signature.isBlank()) {
            String fromSignature = javaTypeFromFieldSignature(signature);
            if (fromSignature != null && !fromSignature.isBlank()) {
                return fromSignature;
            }
        }
        TypeDescriptor typeDescriptor = parseTypeDescriptor(descriptor, 0);
        if (typeDescriptor == null || typeDescriptor.nextIndex() != descriptor.length()) {
            return "java.lang.Object";
        }
        return typeDescriptor.javaType();
    }

    private String javaTypeFromFieldSignature(String signature) {
        int[] index = new int[]{0};
        String parsed = parseGenericSignatureType(signature, index);
        if (parsed == null || index[0] != signature.length()) {
            return null;
        }
        return parsed;
    }

    private String parseGenericSignatureType(String signature, int[] index) {
        if (signature == null || index[0] >= signature.length()) {
            return null;
        }
        char code = signature.charAt(index[0]++);
        return switch (code) {
            case 'B' -> "byte";
            case 'C' -> "char";
            case 'D' -> "double";
            case 'F' -> "float";
            case 'I' -> "int";
            case 'J' -> "long";
            case 'S' -> "short";
            case 'Z' -> "boolean";
            case 'V' -> "void";
            case '[' -> {
                String component = parseGenericSignatureType(signature, index);
                yield component == null ? null : component + "[]";
            }
            case 'T' -> {
                int end = signature.indexOf(';', index[0]);
                if (end < 0) {
                    yield null;
                }
                index[0] = end + 1;
                yield "java.lang.Object";
            }
            case 'L' -> parseClassTypeSignature(signature, index);
            default -> null;
        };
    }

    private String parseClassTypeSignature(String signature, int[] index) {
        StringBuilder out = new StringBuilder();
        StringBuilder name = new StringBuilder();
        while (index[0] < signature.length()) {
            char ch = signature.charAt(index[0]);
            if (ch == '<' || ch == ';' || ch == '.') {
                break;
            }
            name.append(ch);
            index[0]++;
        }
        String base = toSourceTypeName(name.toString());
        String rewrittenBase = rewriteTypeReference(base);
        if (rewrittenBase.startsWith("net.minecraft.")) {
            rewrittenBase = "java.lang.Object";
        }
        out.append(rewrittenBase);
        boolean collapsedToObject = "java.lang.Object".equals(rewrittenBase);
        if (index[0] < signature.length() && signature.charAt(index[0]) == '<') {
            String args = parseGenericSignatureArgs(signature, index);
            if (args == null) {
                return null;
            }
            if (!collapsedToObject) {
                out.append(args);
            }
        }
        while (index[0] < signature.length() && signature.charAt(index[0]) == '.') {
            index[0]++;
            StringBuilder inner = new StringBuilder();
            while (index[0] < signature.length()) {
                char ch = signature.charAt(index[0]);
                if (ch == '<' || ch == ';' || ch == '.') {
                    break;
                }
                inner.append(ch);
                index[0]++;
            }
            if (!collapsedToObject) {
                out.append(".").append(inner);
            }
            if (index[0] < signature.length() && signature.charAt(index[0]) == '<') {
                String args = parseGenericSignatureArgs(signature, index);
                if (args == null) {
                    return null;
                }
                if (!collapsedToObject) {
                    out.append(args);
                }
            }
        }
        if (index[0] >= signature.length() || signature.charAt(index[0]) != ';') {
            return null;
        }
        index[0]++;
        return out.toString();
    }

    private String parseGenericSignatureArgs(String signature, int[] index) {
        if (index[0] >= signature.length() || signature.charAt(index[0]) != '<') {
            return null;
        }
        index[0]++;
        List<String> args = new ArrayList<>();
        while (index[0] < signature.length() && signature.charAt(index[0]) != '>') {
            char wildcard = signature.charAt(index[0]);
            if (wildcard == '*') {
                index[0]++;
                args.add("?");
                continue;
            }
            if (wildcard == '+' || wildcard == '-') {
                index[0]++;
                String bound = parseGenericSignatureType(signature, index);
                if (bound == null) {
                    return null;
                }
                args.add((wildcard == '+') ? "? extends " + bound : "? super " + bound);
                continue;
            }
            String arg = parseGenericSignatureType(signature, index);
            if (arg == null) {
                return null;
            }
            args.add(arg);
        }
        if (index[0] >= signature.length() || signature.charAt(index[0]) != '>') {
            return null;
        }
        index[0]++;
        return "<" + String.join(", ", args) + ">";
    }

    private Map<String, BytecodeClass> loadMinecraftClassIndex() {
        if (!minecraftClassIndex.isEmpty()) {
            return minecraftClassIndex;
        }
        if (minecraftJar == null || !Files.exists(minecraftJar)) {
            return Map.of();
        }

        Map<String, BytecodeClass> classes = new TreeMap<>();
        try (JarFile jarFile = new JarFile(minecraftJar.toFile())) {
            var entries = jarFile.entries();
            while (entries.hasMoreElements()) {
                JarEntry entry = entries.nextElement();
                if (entry.isDirectory()) {
                    continue;
                }
                String entryName = entry.getName();
                if (!entryName.startsWith("net/minecraft/") || !entryName.endsWith(".class")) {
                    continue;
                }
                try (InputStream raw = jarFile.getInputStream(entry);
                     DataInputStream input = new DataInputStream(new BufferedInputStream(raw))) {
                    BytecodeClass classInfo = parseClassInfo(input);
                    if (classInfo != null && classInfo.className().startsWith("net.minecraft.")) {
                        classes.put(classInfo.className(), classInfo);
                    }
                } catch (IOException ignored) {
                    // Skip malformed entries and continue.
                }
            }
        } catch (IOException ignored) {
            return Map.of();
        }
        minecraftClassIndex = Map.copyOf(classes);
        return minecraftClassIndex;
    }

    private BytecodeClass parseClassInfo(DataInputStream input) throws IOException {
        if (input.readInt() != 0xCAFEBABE) {
            return null;
        }
        input.readUnsignedShort();
        input.readUnsignedShort();

        int cpCount = input.readUnsignedShort();
        Object[] cp = new Object[cpCount];
        for (int i = 1; i < cpCount; i++) {
            int tag = input.readUnsignedByte();
            switch (tag) {
                case 1 -> cp[i] = input.readUTF();
                case 3, 4 -> input.readInt();
                case 5, 6 -> {
                    input.readLong();
                    i++;
                }
                case 7 -> cp[i] = new CpClassRef(input.readUnsignedShort());
                case 8, 16, 19, 20 -> input.readUnsignedShort();
                case 9, 10, 11, 12, 17, 18 -> {
                    input.readUnsignedShort();
                    input.readUnsignedShort();
                }
                case 15 -> {
                    input.readUnsignedByte();
                    input.readUnsignedShort();
                }
                default -> {
                    return null;
                }
            }
        }

        int access = input.readUnsignedShort();
        int thisClassIndex = input.readUnsignedShort();
        int superClassIndex = input.readUnsignedShort();
        String thisClassName = resolveClassName(cp, thisClassIndex);
        String superClassName = superClassIndex == 0 ? null : resolveClassName(cp, superClassIndex);
        if (thisClassName == null) {
            return null;
        }

        int interfacesCount = input.readUnsignedShort();
        List<String> interfaces = new ArrayList<>(interfacesCount);
        for (int i = 0; i < interfacesCount; i++) {
            String interfaceName = resolveClassName(cp, input.readUnsignedShort());
            if (interfaceName != null) {
                interfaces.add(interfaceName.replace('/', '.'));
            }
        }

        List<BytecodeMember> fields = readMembers(input, cp);
        List<BytecodeMember> methods = readMembers(input, cp);
        String signature = readClassSignature(input, cp);
        return new BytecodeClass(
            thisClassName.replace('/', '.'),
            superClassName == null ? null : superClassName.replace('/', '.'),
            access,
            List.copyOf(interfaces),
            List.copyOf(fields),
            List.copyOf(methods),
            signature
        );
    }

    private List<BytecodeMember> readMembers(DataInputStream input, Object[] cp) throws IOException {
        int memberCount = input.readUnsignedShort();
        List<BytecodeMember> members = new ArrayList<>(memberCount);
        for (int i = 0; i < memberCount; i++) {
            int access = input.readUnsignedShort();
            String name = resolveUtf8(cp, input.readUnsignedShort());
            String descriptor = resolveUtf8(cp, input.readUnsignedShort());
            String signature = readMemberSignature(input, cp);
            if (name == null || descriptor == null) {
                continue;
            }
            members.add(new BytecodeMember(name, descriptor, access, signature));
        }
        return members;
    }

    private String readMemberSignature(DataInputStream input, Object[] cp) throws IOException {
        int attrs = input.readUnsignedShort();
        String signature = null;
        for (int i = 0; i < attrs; i++) {
            String attrName = resolveUtf8(cp, input.readUnsignedShort());
            int len = input.readInt();
            if ("Signature".equals(attrName) && len == 2) {
                signature = resolveUtf8(cp, input.readUnsignedShort());
            } else {
                skipFully(input, len);
            }
        }
        return signature;
    }

    private void skipAttributes(DataInputStream input) throws IOException {
        int attrs = input.readUnsignedShort();
        for (int i = 0; i < attrs; i++) {
            input.readUnsignedShort();
            int len = input.readInt();
            skipFully(input, len);
        }
    }

    private String readClassSignature(DataInputStream input, Object[] cp) throws IOException {
        int attrs = input.readUnsignedShort();
        String signature = null;
        for (int i = 0; i < attrs; i++) {
            String attrName = resolveUtf8(cp, input.readUnsignedShort());
            int len = input.readInt();
            if ("Signature".equals(attrName) && len == 2) {
                signature = resolveUtf8(cp, input.readUnsignedShort());
            } else {
                skipFully(input, len);
            }
        }
        return signature;
    }

    private void skipFully(DataInputStream input, int len) throws IOException {
        int remaining = len;
        while (remaining > 0) {
            int skipped = input.skipBytes(remaining);
            if (skipped <= 0) {
                if (input.read() == -1) {
                    break;
                }
                skipped = 1;
            }
            remaining -= skipped;
        }
    }

    private String resolveClassName(Object[] cp, int classIndex) {
        if (classIndex <= 0 || classIndex >= cp.length) {
            return null;
        }
        Object entry = cp[classIndex];
        if (!(entry instanceof CpClassRef classRef)) {
            return null;
        }
        return resolveUtf8(cp, classRef.nameIndex());
    }

    private String resolveUtf8(Object[] cp, int index) {
        if (index <= 0 || index >= cp.length) {
            return null;
        }
        Object value = cp[index];
        return value instanceof String str ? str : null;
    }

    private void applyManualMethodSelectors(Map<String, Set<String>> methodsByClass) {
        addManualMethod(methodsByClass, "net.minecraft.world.World",
            "isSpaceEmpty(Lnet/minecraft/entity/Entity;Lnet/minecraft/util/math/Box;)Z");
        addManualMethod(methodsByClass, "net.minecraft.world.World",
            "containsFluid(Lnet/minecraft/util/math/Box;)Z");
        addManualMethod(methodsByClass, "net.minecraft.world.World",
            "getBlockState(Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/block/BlockState;");
        addManualMethod(methodsByClass, "net.minecraft.world.World", "isClient()Z");
        addManualMethod(methodsByClass, "net.minecraft.block.BlockState", "isAir()Z");
        addManualMethod(methodsByClass, "net.minecraft.block.BlockState",
            "isIn(Lnet/minecraft/registry/tag/TagKey;)Z");
        addManualMethod(methodsByClass, "net.minecraft.block.BlockState",
            "isOf(Lnet/minecraft/block/Block;)Z");
        addManualMethod(methodsByClass, "net.minecraft.block.BlockState",
            "getBlock()Lnet/minecraft/block/Block;");
        addManualMethod(methodsByClass, "net.minecraft.util.math.Box",
            "offset(DDD)Lnet/minecraft/util/math/Box;");
    }

    private void addManualMethod(Map<String, Set<String>> methodsByClass, String owner, String selector) {
        methodsByClass.computeIfAbsent(owner, ignored -> new TreeSet<>()).add(selector);
    }

    private void applyManualFieldStubs(Map<String, Set<FieldStub>> fieldsByClass) {
        addManualField(fieldsByClass, "net.minecraft.util.math.Vec3d", "x", "double", false);
        addManualField(fieldsByClass, "net.minecraft.util.math.Vec3d", "y", "double", false);
        addManualField(fieldsByClass, "net.minecraft.util.math.Vec3d", "z", "double", false);
    }

    private void addManualField(Map<String, Set<FieldStub>> fieldsByClass,
                                String owner,
                                String name,
                                String type,
                                boolean isStatic) {
        fieldsByClass.computeIfAbsent(owner, ignored -> new TreeSet<>(FIELD_STUB_COMPARATOR))
            .add(new FieldStub(name, type, isStatic));
    }

    private List<TypeRewriteRule> buildClosureRewriteRules(Map<String, String> rewrites) {
        return rewrites.entrySet()
            .stream()
            .sorted(
                Comparator.comparingInt((Map.Entry<String, String> entry) -> entry.getKey().length())
                    .reversed()
                    .thenComparing(Map.Entry::getKey)
            )
            .map(entry -> new TypeRewriteRule(
                Pattern.compile(
                    "(?<!" + Pattern.quote(MIRROR_PACKAGE_PREFIX_DOT) + ")"
                        + "(?<![\\w$.])" + Pattern.quote(entry.getKey()) + "(?![\\w$.])"
                ),
                entry.getValue()
            ))
            .toList();
    }

    private String rewriteClosureTypeReferences(String source) {
        if (closureRewriteRules.isEmpty() || !source.contains("net.minecraft.")) {
            return source;
        }
        String rewritten = source;
        for (TypeRewriteRule rule : closureRewriteRules) {
            rewritten = rule.pattern()
                .matcher(rewritten)
                .replaceAll(Matcher.quoteReplacement(rule.replacement()));
        }
        return rewritten;
    }

    private String rewriteQualifiedVanillaReferences(String source) {
        if (!source.contains("net.minecraft.")) {
            return source;
        }
        Matcher matcher = QUALIFIED_REFERENCE_PATTERN.matcher(source);
        StringBuffer rewritten = new StringBuffer();
        while (matcher.find()) {
            String reference = matcher.group(1);
            String replacement = rewriteQualifiedVanillaReference(reference);
            matcher.appendReplacement(
                rewritten,
                Matcher.quoteReplacement(replacement == null ? reference : replacement)
            );
        }
        matcher.appendTail(rewritten);
        return rewritten.toString();
    }

    private String rewriteQualifiedVanillaReference(String reference) {
        String sourceTypeName = toSourceTypeReference(reference);
        if (sourceTypeName == null) {
            return null;
        }
        String mirroredTypeName = resolveMirroredTypeName(sourceTypeName);
        if (mirroredTypeName == null) {
            return null;
        }
        return mirroredTypeName + reference.substring(sourceTypeName.length());
    }

    private String resolveMirroredTypeName(String sourceTypeName) {
        if (sourceTypeName == null || sourceTypeName.isBlank()) {
            return null;
        }
        if (sourceTypeName.startsWith(MIRROR_PACKAGE_PREFIX_DOT)) {
            return sourceTypeName;
        }
        String direct = closureRewriteMap.get(sourceTypeName);
        if (direct != null) {
            return direct;
        }
        if (!sourceTypeName.startsWith("net.minecraft.")) {
            return null;
        }
        int search = sourceTypeName.length();
        while (search > 0) {
            int separator = sourceTypeName.lastIndexOf('.', search - 1);
            if (separator < 0) {
                break;
            }
            String prefix = sourceTypeName.substring(0, separator);
            String mappedPrefix = closureRewriteMap.get(prefix);
            if (mappedPrefix != null) {
                return mappedPrefix + sourceTypeName.substring(separator);
            }
            search = separator;
        }
        return null;
    }

    private String qualifyClosureSimpleReferences(String source, String fqcn) {
        String currentSimpleName = simpleNameOf(fqcn);
        Map<String, Set<String>> importsBySimpleName = new LinkedHashMap<>();
        List<String> closureImports = new ArrayList<>();
        String[] lines = source.split("\\R", -1);
        for (String line : lines) {
            Matcher importMatcher = IMPORT_PATTERN.matcher(line.trim());
            if (!importMatcher.matches()) {
                continue;
            }
            if (importMatcher.group(1) != null) {
                continue;
            }
            String importTarget = importMatcher.group(2);
            if (importTarget.endsWith(".*")) {
                continue;
            }
            String simpleName = simpleNameOf(importTarget);
            importsBySimpleName.computeIfAbsent(simpleName, ignored -> new TreeSet<>()).add(importTarget);
            if (!importTarget.startsWith("net.minecraft.")) {
                continue;
            }
            if (primaryClassesBySimpleName.containsValue(importTarget)) {
                continue;
            }
            if (!closureRewriteMap.containsKey(importTarget)) {
                continue;
            }
            closureImports.add(importTarget);
        }

        String rewritten = source;
        for (String closureImport : closureImports) {
            String simpleName = simpleNameOf(closureImport);
            if (simpleName.equals(currentSimpleName)) {
                continue;
            }
            Set<String> owners = importsBySimpleName.get(simpleName);
            if (owners == null || owners.size() != 1 || !owners.contains(closureImport)) {
                continue;
            }
            rewritten = replaceSimpleTypeReference(rewritten, simpleName, closureImport);
        }
        return rewritten;
    }

    private String replaceSimpleTypeReference(String source, String simpleName, String replacementFqcn) {
        Pattern pattern = Pattern.compile("(?<![\\w$.])" + Pattern.quote(simpleName) + "(?![\\w$])");
        return pattern.matcher(source).replaceAll(Matcher.quoteReplacement(replacementFqcn));
    }

    private String replaceTypeIdentifier(String source, String from, String to) {
        Pattern pattern = Pattern.compile("(?<![\\w$])" + Pattern.quote(from) + "(?![\\w$])");
        return pattern.matcher(source).replaceAll(Matcher.quoteReplacement(to));
    }

    private String mirroredFqcn(String fqcn) {
        return MIRROR_PACKAGE_PREFIX + "." + fqcn;
    }

    private boolean isInterfaceType(String fqcn) {
        BytecodeClass classInfo = minecraftClassIndex.get(fqcn);
        return classInfo != null && (classInfo.access() & ACC_INTERFACE) != 0;
    }

    private String resolveStubSuperclass(String fqcn) {
        BytecodeClass classInfo = minecraftClassIndex.get(fqcn);
        if (classInfo == null || (classInfo.access() & ACC_INTERFACE) != 0) {
            return null;
        }
        String superClass = classInfo.superClassName();
        if (superClass == null) {
            return null;
        }
        String sourceName = toSourceTypeName(superClass);
        if ("java.lang.Record".equals(sourceName) || "java.lang.Enum".equals(sourceName)) {
            return null;
        }
        if (superClass.startsWith("net.minecraft.")) {
            String rewritten = rewriteTypeReference(sourceName);
            if (rewritten.startsWith("net.minecraft.")) {
                return null;
            }
            return rewritten;
        }
        return sourceName;
    }

    private String resolveImplementedInterfaces(String fqcn) {
        BytecodeClass classInfo = minecraftClassIndex.get(fqcn);
        if (classInfo == null) {
            return "";
        }
        TreeSet<String> interfaces = new TreeSet<>();
        for (String interfaceName : classInfo.interfaceNames()) {
            if (interfaceName == null) {
                continue;
            }
            String sourceName = toSourceTypeName(interfaceName);
            if (interfaceName.startsWith("net.minecraft.")) {
                String rewritten = rewriteTypeReference(sourceName);
                if (!rewritten.startsWith("net.minecraft.")) {
                    interfaces.add(rewritten);
                }
            }
        }
        if (interfaces.isEmpty()) {
            return "";
        }
        return String.join(", ", interfaces);
    }

    private String resolveTypeParameters(String fqcn) {
        BytecodeClass classInfo = minecraftClassIndex.get(fqcn);
        if (classInfo == null || classInfo.signature() == null || classInfo.signature().isBlank()) {
            return "";
        }
        String signature = classInfo.signature();
        if (!signature.startsWith("<")) {
            return "";
        }
        int end = signature.indexOf('>');
        if (end <= 1) {
            return "";
        }
        List<String> params = new ArrayList<>();
        int index = 1;
        while (index < end) {
            int sep = signature.indexOf(':', index);
            if (sep <= index || sep > end) {
                break;
            }
            String name = signature.substring(index, sep);
            if (isJavaIdentifier(name)) {
                params.add(name);
            }
            index = sep + 1;
            while (index < end && signature.charAt(index) != ':') {
                char ch = signature.charAt(index);
                if (ch == ';') {
                    index++;
                    break;
                }
                if (ch == '<') {
                    int depth = 1;
                    index++;
                    while (index < end && depth > 0) {
                        char nested = signature.charAt(index++);
                        if (nested == '<') {
                            depth++;
                        } else if (nested == '>') {
                            depth--;
                        }
                    }
                } else {
                    index++;
                }
            }
            while (index < end && signature.charAt(index) == ':') {
                index++;
            }
        }
        return params.isEmpty() ? "" : "<" + String.join(", ", params) + ">";
    }

    private boolean addToTopLevelPlan(Map<String, TopLevelStubPlan> plans,
                                      String fqcn,
                                      Set<String> methodSelectors,
                                      Set<FieldStub> fieldStubs) {
        String packageName = packageNameOf(fqcn);
        String binarySimpleName = simpleNameOf(fqcn);
        if (binarySimpleName.isBlank()) {
            return false;
        }
        String[] segments = binarySimpleName.split("\\$");
        if (segments.length == 0 || !isJavaIdentifier(segments[0])) {
            return false;
        }
        for (int i = 1; i < segments.length; i++) {
            if (!isJavaIdentifier(segments[i])) {
                return false;
            }
        }

        String topLevelSimpleName = segments[0];
        String topLevelFqcn = packageName.isBlank()
            ? topLevelSimpleName
            : packageName + "." + topLevelSimpleName;
        boolean isInterface = isInterfaceType(topLevelFqcn);
        String superType = resolveStubSuperclass(topLevelFqcn);
        String interfaces = resolveImplementedInterfaces(topLevelFqcn);
        String typeParams = resolveTypeParameters(topLevelFqcn);
        TopLevelStubPlan plan = plans.computeIfAbsent(topLevelFqcn, ignored ->
            new TopLevelStubPlan(packageName, topLevelSimpleName, isInterface, superType, interfaces, typeParams));
        if (segments.length == 1) {
            plan.topLevelRequested = true;
            plan.methodSelectors.addAll(methodSelectors);
            plan.fieldStubs.addAll(fieldStubs);
            return true;
        }

        StubNode parent = plan.root;
        for (int i = 1; i < segments.length; i++) {
            String segment = segments[i];
            parent = parent.children.computeIfAbsent(segment, StubNode::new);
        }
        parent.methodSelectors.addAll(methodSelectors);
        parent.fieldStubs.addAll(fieldStubs);
        return true;
    }

    private void emitTopLevelStubPlan(TopLevelStubPlan plan, Path mirrorRootDir) throws IOException {
        String topLevelFqcn = plan.packageName.isBlank()
            ? plan.topLevelSimpleName
            : plan.packageName + "." + plan.topLevelSimpleName;
        Path outputFile = mirrorRootDir.resolve(topLevelFqcn.replace('.', '/') + ".java");
        boolean isPrimaryTopLevel = primaryClassesBySimpleName.containsValue(topLevelFqcn);
        if (isPrimaryTopLevel && Files.exists(outputFile)) {
            populateReferencedNestedStubs(plan, topLevelFqcn, Files.readString(outputFile));
        }

        String topLevelFieldStubs = renderFieldStubs(plan.fieldStubs, 1, plan.isInterface);
        String topLevelMethodStubs = renderMethodStubs(
            plan.topLevelSimpleName,
            topLevelFqcn,
            plan.methodSelectors,
            1,
            plan.isInterface
        );
        String nestedDeclarations = renderNestedDeclarations(plan.root, 1, topLevelFqcn);
        String classBody = topLevelFieldStubs + topLevelMethodStubs + nestedDeclarations;

        if (Files.exists(outputFile) && isPrimaryTopLevel) {
            String existing = Files.readString(outputFile);
            String updated = nestedDeclarations.isBlank()
                ? removeNestedStubBlock(existing)
                : upsertNestedStubBlock(existing, nestedDeclarations);
            if (!updated.equals(existing)) {
                Files.writeString(outputFile, updated);
            }
            return;
        }

        Files.createDirectories(outputFile.getParent());
        String generated = rewriteClosureTypeReferences(generateTopLevelStubSource(
            plan.packageName,
            plan.topLevelSimpleName,
            plan.isInterface,
            plan.superType,
            plan.interfaceTypes,
            plan.typeParameters,
            classBody
        ));
        if (!Files.exists(outputFile) || !Files.readString(outputFile).equals(generated)) {
            Files.writeString(outputFile, generated);
        }
    }

    private void emitFallbackStub(String fqcn,
                                  Set<String> methodSelectors,
                                  Set<FieldStub> fieldStubs,
                                  Path mirrorRootDir) throws IOException {
        Path outputFile = mirrorRootDir.resolve(fqcn.replace('.', '/') + ".java");
        Files.createDirectories(outputFile.getParent());
        String generated = rewriteClosureTypeReferences(generateStubSource(
            fqcn,
            methodSelectors,
            fieldStubs,
            isInterfaceType(fqcn),
            resolveStubSuperclass(fqcn),
            resolveImplementedInterfaces(fqcn),
            resolveTypeParameters(fqcn)
        ));
        if (!Files.exists(outputFile) || !Files.readString(outputFile).equals(generated)) {
            Files.writeString(outputFile, generated);
        }
    }

    private void emitFallbackDollarStubs(Set<String> fallbackDollarStubs,
                                         Map<String, Set<String>> methodsByClass,
                                         Map<String, Set<FieldStub>> fieldsByClass,
                                         Path mirrorRootDir) throws IOException {
        for (String fqcn : fallbackDollarStubs) {
            emitFallbackStub(
                fqcn,
                methodsByClass.getOrDefault(fqcn, Set.of()),
                fieldsByClass.getOrDefault(fqcn, Set.of()),
                mirrorRootDir
            );
        }
    }

    private void deleteStaleNestedClassFiles(Set<String> nestedClassesToCleanup, Path mirrorRootDir) throws IOException {
        for (String fqcn : nestedClassesToCleanup) {
            Path staleNestedFile = mirrorRootDir.resolve(fqcn.replace('.', '/') + ".java");
            Files.deleteIfExists(staleNestedFile);
        }
    }

    private void emitTopLevelPlans(Map<String, TopLevelStubPlan> topLevelPlans, Path mirrorRootDir) throws IOException {
        for (TopLevelStubPlan plan : topLevelPlans.values()) {
            emitTopLevelStubPlan(plan, mirrorRootDir);
        }
    }

    private void emitClosureStubs(Set<String> closureClasses,
                                  Map<String, Set<String>> methodsByClass,
                                  Map<String, Set<FieldStub>> fieldsByClass,
                                  Path mirrorRootDir) throws IOException {
        if (closureClasses == null || closureClasses.isEmpty()) {
            return;
        }
        Set<String> emitClasses = new TreeSet<>();
        for (String fqcn : closureClasses) {
            if (fqcn == null || !fqcn.startsWith("net.minecraft.")) {
                continue;
            }
            if (primaryClassesBySimpleName.containsValue(fqcn)) {
                continue;
            }
            addClosureClass(emitClasses, fqcn);
        }

        Set<String> sorted = new TreeSet<>(emitClasses);
        System.out.println("emitClosureStubs: start (" + sorted.size() + " classes)");
        Map<String, TopLevelStubPlan> topLevelPlans = new TreeMap<>();
        Set<String> fallbackDollarStubs = new TreeSet<>();
        Set<String> nestedClassesToCleanup = new TreeSet<>();
        int processed = 0;
        int skipped = 0;
        int planned = 0;
        int fallback = 0;

        for (String fqcn : sorted) {
            if (fqcn == null || !fqcn.startsWith("net.minecraft.")) {
                skipped++;
                continue;
            }
            if (primaryClassesBySimpleName.containsValue(fqcn)) {
                skipped++;
                continue;
            }
            Set<String> methodSelectors = methodsByClass.getOrDefault(fqcn, Set.of());
            Set<FieldStub> fieldStubs = fieldsByClass.getOrDefault(fqcn, Set.of());
            if (!addToTopLevelPlan(topLevelPlans, fqcn, methodSelectors, fieldStubs)) {
                fallbackDollarStubs.add(fqcn);
                fallback++;
            } else if (simpleNameOf(fqcn).contains("$")) {
                nestedClassesToCleanup.add(fqcn);
                planned++;
            } else {
                planned++;
            }
            processed++;
            if (processed % 250 == 0) {
                System.out.println("emitClosureStubs: scanned " + processed
                    + " (planned=" + planned + ", fallback=" + fallback + ", skipped=" + skipped + ")");
            }
        }

        System.out.println("emitClosureStubs: writing top-level plans (" + topLevelPlans.size() + ")");
        emitTopLevelPlans(topLevelPlans, mirrorRootDir);
        System.out.println("emitClosureStubs: cleaning stale nested files (" + nestedClassesToCleanup.size() + ")");
        deleteStaleNestedClassFiles(nestedClassesToCleanup, mirrorRootDir);
        System.out.println("emitClosureStubs: writing fallback dollar stubs (" + fallbackDollarStubs.size() + ")");
        emitFallbackDollarStubs(fallbackDollarStubs, methodsByClass, fieldsByClass, mirrorRootDir);
        System.out.println("emitClosureStubs: done (processed=" + processed
            + ", planned=" + planned + ", fallback=" + fallback + ", skipped=" + skipped + ")");
    }

    private boolean hasRenderableMethods(Set<String> methodSelectors) {
        if (methodSelectors == null || methodSelectors.isEmpty()) {
            return false;
        }
        for (String selector : methodSelectors) {
            MethodStub methodStub = parseMethodSelector(selector, null);
            if (methodStub != null && !methodStub.isClassInitializer()) {
                return true;
            }
        }
        return false;
    }

    private String renderNestedDeclarations(StubNode node, int depth, String ownerBinaryName) {
        if (node.children.isEmpty()) {
            return "";
        }
        String indent = "    ".repeat(depth);
        StringBuilder sb = new StringBuilder();
        for (StubNode child : node.children.values()) {
            String childBinaryName = ownerBinaryName + "$" + child.simpleName;
            boolean childIsInterface = isInterfaceType(childBinaryName);
            String childDeclaration = typeDeclaration(
                child.simpleName,
                resolveTypeParameters(childBinaryName),
                childIsInterface,
                resolveStubSuperclass(childBinaryName),
                resolveImplementedInterfaces(childBinaryName)
            ).replace("public ", "public static ");
            sb.append(indent).append(childDeclaration).append(" {\n");
            sb.append(renderFieldStubs(child.fieldStubs, depth + 1, childIsInterface));
            sb.append(renderMethodStubs(child.simpleName, childBinaryName, child.methodSelectors, depth + 1, childIsInterface));
            sb.append(renderNestedDeclarations(child, depth + 1, childBinaryName));
            sb.append(indent).append("}\n\n");
        }
        return sb.toString();
    }

    private void populateReferencedNestedStubs(TopLevelStubPlan plan, String topLevelFqcn, String source) {
        if (plan == null || source == null || source.isBlank()) {
            return;
        }
        String mirroredTopLevel = mirroredFqcn(topLevelFqcn);
        Pattern nestedReferencePattern = Pattern.compile(
            "(?<![\\w$])" + Pattern.quote(mirroredTopLevel)
                + "\\.([A-Z][A-Za-z\\d_$]*(?:\\.[A-Z][A-Za-z\\d_$]*)*)"
        );
        Matcher matcher = nestedReferencePattern.matcher(source);
        while (matcher.find()) {
            String nestedPath = matcher.group(1);
            if (nestedPath == null || nestedPath.isBlank()) {
                continue;
            }
            String[] segments = nestedPath.split("\\.");
            if (segments.length == 0 || isLikelyConstantSegment(segments[segments.length - 1])) {
                continue;
            }
            StubNode parent = plan.root;
            boolean valid = true;
            for (String segment : segments) {
                if (!isJavaIdentifier(segment)) {
                    valid = false;
                    break;
                }
                parent = parent.children.computeIfAbsent(segment, StubNode::new);
            }
            if (!valid) {
                continue;
            }
        }
    }

    private String renderFieldStubs(Set<FieldStub> fieldStubs, int depth, boolean ownerIsInterface) {
        if (fieldStubs == null || fieldStubs.isEmpty()) {
            return "";
        }
        String indent = "    ".repeat(depth);
        StringBuilder sb = new StringBuilder();
        Set<FieldStub> sorted = new TreeSet<>(FIELD_STUB_COMPARATOR);
        sorted.addAll(fieldStubs);
        for (FieldStub fieldStub : sorted) {
            if (ownerIsInterface) {
                if (!fieldStub.isStatic()) {
                    continue;
                }
                sb.append(indent)
                    .append("public static final ")
                    .append(fieldStub.fieldType())
                    .append(" ")
                    .append(fieldStub.fieldName())
                    .append(" = ")
                    .append(defaultValueExpression(fieldStub.fieldType()))
                    .append(";\n");
                continue;
            }
            sb.append(indent).append("public ");
            if (fieldStub.isStatic()) {
                sb.append("static ");
            }
            sb.append(fieldStub.fieldType()).append(" ").append(fieldStub.fieldName()).append(";\n");
        }
        if (sb.isEmpty()) {
            return "";
        }
        sb.append("\n");
        return sb.toString();
    }

    private String renderMethodStubs(String ownerSimpleName,
                                     String ownerBinaryName,
                                     Set<String> methodSelectors,
                                     int depth,
                                     boolean ownerIsInterface) {
        return renderMethodStubs(ownerSimpleName, ownerBinaryName, methodSelectors, depth, ownerIsInterface, false, true);
    }

    private String renderMethodStubs(String ownerSimpleName,
                                     String ownerBinaryName,
                                     Set<String> methodSelectors,
                                     int depth,
                                     boolean ownerIsInterface,
                                     boolean preserveMirrorTypes,
                                     boolean includeImplicitNoArgConstructor) {
        if (methodSelectors == null || methodSelectors.isEmpty()) {
            return "";
        }
        String indent = "    ".repeat(depth);
        StringBuilder sb = new StringBuilder();
        Map<String, MethodStub> methodsBySignature = new TreeMap<>();
        for (String selector : new TreeSet<>(methodSelectors)) {
            MethodStub methodStub = parseMethodSelector(selector, ownerBinaryName, preserveMirrorTypes);
            if (methodStub == null || methodStub.isClassInitializer()) {
                continue;
            }
            String signatureKey = methodStub.signatureKey();
            MethodStub existing = methodsBySignature.get(signatureKey);
            if (existing == null || (!existing.isStatic() && methodStub.isStatic())) {
                methodsBySignature.put(signatureKey, methodStub);
            }
        }
        boolean hasNoArgConstructor = false;
        for (MethodStub methodStub : methodsBySignature.values()) {
            if (methodStub.isConstructor() && methodStub.parameterTypes().isEmpty()) {
                hasNoArgConstructor = true;
            }
            String specialStub = renderSpecialMethodStub(ownerBinaryName, ownerSimpleName, methodStub, indent, ownerIsInterface);
            if (specialStub != null) {
                sb.append(specialStub);
                continue;
            }
            if (methodStub.isConstructor()) {
                if (ownerIsInterface) {
                    continue;
                }
                sb.append(indent).append("public ").append(ownerSimpleName)
                    .append("(").append(renderParams(methodStub.parameterTypes())).append(") {\n");
                sb.append(indent).append("}\n\n");
                continue;
            }

            sb.append(indent).append("public ");
            if (methodStub.isStatic()) {
                sb.append("static ");
            } else if (ownerIsInterface) {
                sb.append(methodStub.returnType())
                    .append(" ").append(methodStub.methodName())
                    .append("(").append(renderParams(methodStub.parameterTypes())).append(");\n\n");
                continue;
            }
            sb.append(methodStub.returnType())
                .append(" ").append(methodStub.methodName())
                .append("(").append(renderParams(methodStub.parameterTypes())).append(") {\n");
            String defaultReturn = defaultReturnStatement(methodStub.returnType());
            if (!defaultReturn.isBlank()) {
                sb.append(indent).append("    ").append(defaultReturn).append("\n");
            }
            sb.append(indent).append("}\n\n");
        }
        if (includeImplicitNoArgConstructor && !ownerIsInterface && !hasNoArgConstructor) {
            sb.append(indent).append("public ").append(ownerSimpleName).append("() {\n");
            sb.append(indent).append("}\n\n");
        }
        return sb.toString();
    }

    private boolean isObjectMethodSignature(MethodStub methodStub) {
        if (methodStub == null || methodStub.isStatic()) {
            return false;
        }
        String methodName = methodStub.methodName();
        int paramCount = methodStub.parameterTypes().size();
        return ("toString".equals(methodName) && paramCount == 0)
            || ("hashCode".equals(methodName) && paramCount == 0)
            || ("equals".equals(methodName) && paramCount == 1);
    }

    private String renderSpecialMethodStub(String ownerBinaryName,
                                           String ownerSimpleName,
                                           MethodStub methodStub,
                                           String indent,
                                           boolean ownerIsInterface) {
        if ("net.minecraft.entity.data.DataTracker".equals(ownerBinaryName)) {
            if ("get".equals(methodStub.methodName()) && methodStub.parameterTypes().size() == 1) {
                return """
                    %spublic <T> murat.simv2.simulation.mirror.net.minecraft.entity.data.TrackedData<T> getData(murat.simv2.simulation.mirror.net.minecraft.entity.data.TrackedData<T> p0) {
                    %s    return null;
                    %s}
                    
                    %spublic <T> T get(murat.simv2.simulation.mirror.net.minecraft.entity.data.TrackedData<T> p0) {
                    %s    return null;
                    %s}
                    
                    """.formatted(indent, indent, indent, indent, indent, indent);
            }
            if ("set".equals(methodStub.methodName()) && methodStub.parameterTypes().size() == 2) {
                return """
                    %spublic <T> void set(murat.simv2.simulation.mirror.net.minecraft.entity.data.TrackedData<T> p0, T p1) {
                    %s}
                    
                    """.formatted(indent, indent);
            }
            if ("set".equals(methodStub.methodName()) && methodStub.parameterTypes().size() == 3) {
                return """
                    %spublic <T> void set(murat.simv2.simulation.mirror.net.minecraft.entity.data.TrackedData<T> p0, T p1, boolean p2) {
                    %s}
                    
                    """.formatted(indent, indent);
            }
            if ("registerData".equals(methodStub.methodName()) && methodStub.isStatic() && methodStub.parameterTypes().size() == 2) {
                return """
                    %spublic static <T> murat.simv2.simulation.mirror.net.minecraft.entity.data.TrackedData<T> registerData(java.lang.Class p0, murat.simv2.simulation.mirror.net.minecraft.entity.data.TrackedDataHandler<T> p1) {
                    %s    return null;
                    %s}
                    
                    """.formatted(indent, indent, indent);
            }
        }
        if ("net.minecraft.entity.data.DataTracker$Builder".equals(ownerBinaryName)) {
            if (methodStub.isConstructor() && methodStub.parameterTypes().size() == 1) {
                if (ownerIsInterface) {
                    return "";
                }
                return """
                    %spublic %s(java.lang.Object p0) {
                    %s}
                    
                    """.formatted(indent, ownerSimpleName, indent);
            }
            if ("add".equals(methodStub.methodName()) && methodStub.parameterTypes().size() == 2) {
                return """
                    %spublic <T> murat.simv2.simulation.mirror.net.minecraft.entity.data.DataTracker.Builder add(murat.simv2.simulation.mirror.net.minecraft.entity.data.TrackedData<T> p0, T p1) {
                    %s    return null;
                    %s}
                    
                    """.formatted(indent, indent, indent);
            }
        }
        if ("net.minecraft.entity.LazyEntityReference".equals(ownerBinaryName)) {
            if (methodStub.isConstructor()
                && methodStub.parameterTypes().size() == 1
                && methodStub.parameterTypes().get(0).contains("UniquelyIdentifiable")) {
                if (ownerIsInterface) {
                    return "";
                }
                return """
                    %spublic %s(java.lang.Object p0) {
                    %s}
                    
                    """.formatted(indent, ownerSimpleName, indent);
            }
            if ("resolve".equals(methodStub.methodName()) && methodStub.parameterTypes().size() == 2) {
                return """
                    %spublic java.lang.Object resolve(java.lang.Object p0, java.lang.Class p1) {
                    %s    return null;
                    %s}
                     
                    """.formatted(indent, indent, indent);
            }
            if ("resolve".equals(methodStub.methodName()) && methodStub.parameterTypes().size() == 3 && methodStub.isStatic()) {
                return """
                    %spublic static java.lang.Object resolve(murat.simv2.simulation.mirror.net.minecraft.entity.LazyEntityReference p0, java.lang.Object p1, java.lang.Class p2) {
                    %s    return null;
                    %s}
                     
                    """.formatted(indent, indent, indent);
            }
        }
        if ("net.minecraft.fluid.FluidState".equals(ownerBinaryName)) {
            if ("getHeight".equals(methodStub.methodName()) && methodStub.parameterTypes().size() == 2) {
                return """
                    %spublic double getHeight(java.lang.Object p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1) {
                    %s    return 0.0D;
                    %s}
                    
                    """.formatted(indent, indent, indent);
            }
            if ("getVelocity".equals(methodStub.methodName()) && methodStub.parameterTypes().size() == 2) {
                return """
                    %spublic murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d getVelocity(java.lang.Object p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1) {
                    %s    return null;
                    %s}
                    
                    """.formatted(indent, indent, indent);
            }
        }
        if ("net.minecraft.world.entity.EntityQueriable".equals(ownerBinaryName)
            && "getEntity".equals(methodStub.methodName())
            && methodStub.parameterTypes().size() == 1) {
            String interfacePrefix = ownerIsInterface ? "default " : "";
            return """
                %spublic %smurat.simv2.simulation.mirror.net.minecraft.entity.Entity getEntity(java.util.UUID p0) {
                %s    return null;
                %s}
                
                """.formatted(indent, interfacePrefix, indent, indent);
        }
        if ("net.minecraft.server.world.ServerChunkManager".equals(ownerBinaryName)
            && "getLightingProvider".equals(methodStub.methodName())
            && methodStub.parameterTypes().isEmpty()) {
            return """
                %spublic murat.simv2.simulation.mirror.net.minecraft.world.chunk.light.LightingProvider getLightingProvider() {
                %s    return null;
                %s}
                
                """.formatted(indent, indent, indent);
        }
        return null;
    }

    private String generateTopLevelStubSource(String packageName,
                                              String simpleName,
                                              boolean isInterface,
                                              String superType,
                                              String interfaceTypes,
                                              String typeParameters,
                                              String classBody) {
        String nestedBlock = classBody.isBlank() ? "" : "\n" + buildNestedStubBlock(classBody, "\n");
        String declaration = typeDeclaration(simpleName, typeParameters, isInterface, superType, interfaceTypes);
        return """
            package %s.%s;

            // Generated mirror stub for simulation closure.
            %s {
            %s}
            """.formatted(MIRROR_PACKAGE_PREFIX, packageName, declaration, nestedBlock);
    }

    private String generateStubSource(String fqcn,
                                      Set<String> methodSelectors,
                                      Set<FieldStub> fieldStubs,
                                      boolean isInterface,
                                      String superType,
                                      String interfaceTypes,
                                      String typeParameters) {
        String packageName = packageNameOf(fqcn);
        String simpleName = simpleNameOf(fqcn);
        String renderedFieldStubs = renderFieldStubs(fieldStubs, 1, isInterface);
        String methodStubs = renderMethodStubs(simpleName, fqcn, methodSelectors, 1, isInterface);
        String classBody = renderedFieldStubs + methodStubs;
        String declaration = typeDeclaration(simpleName, typeParameters, isInterface, superType, interfaceTypes);
        return """
            package %s.%s;

            // Generated mirror stub for simulation closure.
            %s {
            %s}
            """.formatted(MIRROR_PACKAGE_PREFIX, packageName, declaration, classBody.isBlank() ? "" : "\n" + classBody);
    }

    private String typeDeclaration(String simpleName,
                                   String typeParameters,
                                   boolean isInterface,
                                   String superType,
                                   String interfaceTypes) {
        String typeParams = typeParameters == null ? "" : typeParameters;
        if (isInterface) {
            if (interfaceTypes != null && !interfaceTypes.isBlank()) {
                return "public interface " + simpleName + typeParams + " extends " + interfaceTypes;
            }
            return "public interface " + simpleName + typeParams;
        }
        String interfaces = interfaceTypes == null ? "" : interfaceTypes;
        if (superType != null && !superType.isBlank()) {
            if (!interfaces.isBlank()) {
                return "public class " + simpleName + typeParams + " extends " + superType + " implements " + interfaces;
            }
            return "public class " + simpleName + typeParams + " extends " + superType;
        }
        if (!interfaces.isBlank()) {
            return "public class " + simpleName + typeParams + " implements " + interfaces;
        }
        return "public class " + simpleName + typeParams;
    }

    private String renderParams(List<String> parameterTypes) {
        if (parameterTypes == null || parameterTypes.isEmpty()) {
            return "";
        }
        StringBuilder params = new StringBuilder();
        for (int i = 0; i < parameterTypes.size(); i++) {
            if (i > 0) {
                params.append(", ");
            }
            params.append(parameterTypes.get(i)).append(" p").append(i);
        }
        return params.toString();
    }

    private String defaultReturnStatement(String returnType) {
        return switch (returnType) {
            case "void" -> "";
            case "boolean" -> "return false;";
            case "byte" -> "return (byte) 0;";
            case "char" -> "return '\\0';";
            case "short" -> "return (short) 0;";
            case "int" -> "return 0;";
            case "long" -> "return 0L;";
            case "float" -> "return 0.0F;";
            case "double" -> "return 0.0D;";
            default -> "return null;";
        };
    }

    private String defaultValueExpression(String typeName) {
        return switch (typeName) {
            case "boolean" -> "false";
            case "byte" -> "(byte) 0";
            case "char" -> "'\\0'";
            case "short" -> "(short) 0";
            case "int" -> "0";
            case "long" -> "0L";
            case "float" -> "0.0F";
            case "double" -> "0.0D";
            default -> "null";
        };
    }

    private MethodStub parseMethodSelector(String selector, String ownerBinaryName) {
        return parseMethodSelector(selector, ownerBinaryName, false);
    }

    private MethodStub parseMethodSelector(String selector, String ownerBinaryName, boolean preserveMirrorTypes) {
        if (selector == null || selector.isBlank()) {
            return null;
        }
        String normalized = selector.trim();
        boolean explicitStatic = false;
        if (normalized.startsWith("static ")) {
            explicitStatic = true;
            normalized = normalized.substring("static ".length()).trim();
        }
        int openParen = normalized.indexOf('(');
        int closeParen = normalized.indexOf(')', openParen + 1);
        if (openParen <= 0 || closeParen <= openParen) {
            return null;
        }
        String methodName = normalized.substring(0, openParen).trim();
        String paramsDescriptor = normalized.substring(openParen + 1, closeParen);
        String returnDescriptor = normalized.substring(closeParen + 1).trim();
        String fullDescriptor = "(" + paramsDescriptor + ")" + returnDescriptor;
        if (methodName.isBlank()) {
            return null;
        }

        List<String> parameterTypes = parseParameterTypes(paramsDescriptor, preserveMirrorTypes);
        if (parameterTypes == null) {
            return null;
        }

        String returnType = "void";
        if (!"<init>".equals(methodName) && !"<clinit>".equals(methodName)) {
            TypeDescriptor parsedReturn = parseTypeDescriptor(returnDescriptor, 0, preserveMirrorTypes);
            if (parsedReturn == null || parsedReturn.nextIndex() != returnDescriptor.length()) {
                return null;
            }
            returnType = parsedReturn.javaType();
        }

        if ("<init>".equals(methodName)) {
            return new MethodStub(methodName, returnType, parameterTypes, false);
        }
        boolean isStatic = explicitStatic || isStaticMethodByBytecode(ownerBinaryName, methodName, fullDescriptor);
        return new MethodStub(methodName, returnType, parameterTypes, isStatic);
    }

    private boolean isStaticMethodByBytecode(String ownerBinaryName, String methodName, String descriptor) {
        if (ownerBinaryName == null || ownerBinaryName.isBlank() || methodName == null || descriptor == null) {
            return false;
        }
        BytecodeClass classInfo = minecraftClassIndex.get(ownerBinaryName);
        if (classInfo == null) {
            return false;
        }
        for (BytecodeMember method : classInfo.methods()) {
            if (method == null) {
                continue;
            }
            if (methodName.equals(method.name()) && descriptor.equals(method.descriptor())) {
                return (method.access() & ACC_STATIC) != 0;
            }
        }
        return false;
    }

    private List<String> parseParameterTypes(String descriptor) {
        return parseParameterTypes(descriptor, false);
    }

    private List<String> parseParameterTypes(String descriptor, boolean preserveMirrorTypes) {
        List<String> params = new ArrayList<>();
        int index = 0;
        while (index < descriptor.length()) {
            TypeDescriptor parsed = parseTypeDescriptor(descriptor, index, preserveMirrorTypes);
            if (parsed == null) {
                return null;
            }
            params.add(parsed.javaType());
            index = parsed.nextIndex();
        }
        return params;
    }

    private TypeDescriptor parseTypeDescriptor(String descriptor, int startIndex) {
        return parseTypeDescriptor(descriptor, startIndex, false);
    }

    private TypeDescriptor parseTypeDescriptor(String descriptor, int startIndex, boolean preserveMirrorTypes) {
        if (descriptor == null || startIndex >= descriptor.length()) {
            return null;
        }
        int index = startIndex;
        int dimensions = 0;
        while (index < descriptor.length() && descriptor.charAt(index) == '[') {
            dimensions++;
            index++;
        }
        if (index >= descriptor.length()) {
            return null;
        }

        char typeCode = descriptor.charAt(index);
        String baseType;
        int consumedIndex;
        switch (typeCode) {
            case 'V' -> {
                baseType = "void";
                consumedIndex = index + 1;
            }
            case 'Z' -> {
                baseType = "boolean";
                consumedIndex = index + 1;
            }
            case 'B' -> {
                baseType = "byte";
                consumedIndex = index + 1;
            }
            case 'C' -> {
                baseType = "char";
                consumedIndex = index + 1;
            }
            case 'S' -> {
                baseType = "short";
                consumedIndex = index + 1;
            }
            case 'I' -> {
                baseType = "int";
                consumedIndex = index + 1;
            }
            case 'J' -> {
                baseType = "long";
                consumedIndex = index + 1;
            }
            case 'F' -> {
                baseType = "float";
                consumedIndex = index + 1;
            }
            case 'D' -> {
                baseType = "double";
                consumedIndex = index + 1;
            }
            case 'L' -> {
                int end = descriptor.indexOf(';', index + 1);
                if (end < 0) {
                    return null;
                }
                String rewritten = rewriteTypeReference(toSourceTypeName(descriptor.substring(index + 1, end)));
                baseType = preserveMirrorTypes ? normalizePrimaryStubType(rewritten) : normalizeStubType(rewritten);
                consumedIndex = end + 1;
            }
            default -> {
                return null;
            }
        }

        String javaType = baseType;
        for (int i = 0; i < dimensions; i++) {
            javaType = javaType + "[]";
        }
        return new TypeDescriptor(javaType, consumedIndex);
    }

    private String toSourceTypeName(String internalName) {
        String dotted = internalName.replace('/', '.');
        int lastDot = dotted.lastIndexOf('.');
        if (lastDot < 0) {
            return toSourceSimpleTypeName(dotted);
        }
        String packagePrefix = dotted.substring(0, lastDot + 1);
        String simpleName = dotted.substring(lastDot + 1);
        return packagePrefix + toSourceSimpleTypeName(simpleName);
    }

    private String normalizeStubType(String typeName) {
        if (typeName == null || typeName.isBlank()) {
            return "java.lang.Object";
        }
        String normalized = typeName;
        int dims = 0;
        while (normalized.endsWith("[]")) {
            normalized = normalized.substring(0, normalized.length() - 2);
            dims++;
        }
        if (normalized.startsWith("net.minecraft.")) {
            normalized = rewriteTypeReference(normalized);
            if (normalized.startsWith("net.minecraft.")) {
                normalized = "java.lang.Object";
            }
        }
        String result = normalized;
        for (int i = 0; i < dims; i++) {
            result += "[]";
        }
        return result;
    }

    private String normalizePrimaryStubType(String typeName) {
        if (typeName == null || typeName.isBlank()) {
            return "java.lang.Object";
        }
        String normalized = typeName;
        int dims = 0;
        while (normalized.endsWith("[]")) {
            normalized = normalized.substring(0, normalized.length() - 2);
            dims++;
        }
        if (normalized.startsWith("net.minecraft.")) {
            normalized = rewriteTypeReference(normalized);
            if (normalized.startsWith("net.minecraft.")) {
                normalized = "java.lang.Object";
            }
        }
        String result = normalized;
        for (int i = 0; i < dims; i++) {
            result += "[]";
        }
        return result;
    }

    private String upsertPrimaryContractBlock(String source, String fqcn) {
        String normalized = removePrimaryContractBlock(source);
        if (fqcn == null || fqcn.isBlank() || minecraftClassIndex.isEmpty() || !minecraftClassIndex.containsKey(fqcn)) {
            return normalized;
        }

        String simpleName = simpleNameOf(fqcn);
        Set<String> existingMethodKeys = extractExistingMethodKeys(normalized, simpleName);
        Set<String> existingMethodNameArities = extractExistingMethodNameArities(normalized, simpleName);
        Set<String> existingFieldNames = extractExistingFieldNames(normalized);

        Set<String> methodSelectors = new TreeSet<>();
        addMethodSelectorsForHierarchy(fqcn, methodSelectors, new TreeSet<>(), false);
        BytecodeClass classInfo = minecraftClassIndex.get(fqcn);
        if (classInfo != null) {
            for (BytecodeMember method : classInfo.methods()) {
                if (method == null || "<clinit>".equals(method.name())) {
                    continue;
                }
                if ("<init>".equals(method.name()) || !isJavaIdentifierOrDollar(method.name())) {
                    continue;
                }
                String prefix = (method.access() & ACC_STATIC) != 0 ? "static " : "";
                methodSelectors.add(prefix + method.name() + method.descriptor());
            }
        }
        Set<String> missingMethodSelectors = new TreeSet<>();
        for (String selector : methodSelectors) {
            MethodStub methodStub = parseMethodSelector(selector, fqcn, true);
            if (methodStub == null || methodStub.isClassInitializer() || methodStub.isConstructor()) {
                continue;
            }
            if (shouldSkipPrimaryContractMethodStub(methodStub)) {
                continue;
            }
            if (existingMethodKeys.contains(methodStub.signatureKey())) {
                continue;
            }
            if (existingMethodNameArities.contains(methodStub.nameArityKey())) {
                continue;
            }
            missingMethodSelectors.add(selector);
        }

        Set<FieldStub> hierarchyFields = new TreeSet<>(FIELD_STUB_COMPARATOR);
        addFieldStubsForHierarchy(fqcn, hierarchyFields, new TreeSet<>(), true);
        Set<FieldStub> missingFields = new TreeSet<>(FIELD_STUB_COMPARATOR);
        for (FieldStub fieldStub : hierarchyFields) {
            if (existingFieldNames.contains(fieldStub.fieldName())) {
                continue;
            }
            missingFields.add(fieldStub);
        }

        if (missingMethodSelectors.isEmpty() && missingFields.isEmpty()) {
            return normalized;
        }

        String fieldStubs = renderFieldStubs(missingFields, 1, false);
        String methodStubs = renderPrimaryContractMethodStubs(
            simpleName,
            fqcn,
            missingMethodSelectors,
            1
        );
        String declarations = fieldStubs + methodStubs;
        if (declarations.isBlank()) {
            return normalized;
        }

        String lineSeparator = normalized.contains("\r\n") ? "\r\n" : "\n";
        String block = buildPrimaryContractBlock(declarations, lineSeparator);
        int lastBrace = normalized.lastIndexOf('}');
        if (lastBrace < 0) {
            return normalized;
        }
        String prefix = normalized.substring(0, lastBrace);
        if (!prefix.endsWith(lineSeparator)) {
            prefix = prefix + lineSeparator;
        }
        if (!prefix.endsWith(lineSeparator + lineSeparator)) {
            prefix = prefix + lineSeparator;
        }
        return prefix + block + normalized.substring(lastBrace);
    }

    private String buildPrimaryContractBlock(String declarations, String lineSeparator) {
        StringBuilder sb = new StringBuilder();
        sb.append("    ").append(PRIMARY_CONTRACT_BEGIN).append(lineSeparator);
        sb.append(declarations.replace("\n", lineSeparator));
        if (!declarations.endsWith("\n")) {
            sb.append(lineSeparator);
        }
        sb.append("    ").append(PRIMARY_CONTRACT_END).append(lineSeparator);
        return sb.toString();
    }

    private String removePrimaryContractBlock(String source) {
        int begin = source.indexOf(PRIMARY_CONTRACT_BEGIN);
        if (begin < 0) {
            return source;
        }
        int endMarker = source.indexOf(PRIMARY_CONTRACT_END, begin);
        if (endMarker < 0) {
            return source;
        }
        int endLine = source.indexOf('\n', endMarker);
        if (endLine < 0) {
            endLine = source.length();
        } else {
            endLine += 1;
        }
        return source.substring(0, begin) + source.substring(endLine);
    }

    private Set<String> extractExistingMethodKeys(String source, String ownerSimpleName) {
        Set<String> keys = new TreeSet<>();
        Pattern methodPattern = Pattern.compile(
            "(?m)^\\s*(?:(?:public|protected|private)\\s+)?(?:static\\s+)?(?:final\\s+)?(?:synchronized\\s+)?(?:native\\s+)?(?:strictfp\\s+)?(?:[\\w.$<>\\[\\], ?]+\\s+)([A-Za-z_$][\\w$]*)\\s*\\(([^)]*)\\)\\s*(?:\\{|;)"
        );
        Matcher matcher = methodPattern.matcher(source);
        while (matcher.find()) {
            String methodName = matcher.group(1);
            String params = matcher.group(2);
            List<String> parameterTypes = parseSourceParameterTypes(params);
            if (parameterTypes == null) {
                continue;
            }
            String normalizedName = ownerSimpleName.equals(methodName) ? "<init>" : methodName;
            keys.add(normalizedName + "|" + String.join(",", parameterTypes));
        }
        return keys;
    }

    private Set<String> extractExistingMethodNameArities(String source, String ownerSimpleName) {
        Set<String> keys = new TreeSet<>();
        Pattern methodPattern = Pattern.compile(
            "(?m)^\\s*(?:(?:public|protected|private)\\s+)?(?:static\\s+)?(?:final\\s+)?(?:synchronized\\s+)?(?:native\\s+)?(?:strictfp\\s+)?(?:[\\w.$<>\\[\\], ?]+\\s+)([A-Za-z_$][\\w$]*)\\s*\\(([^)]*)\\)\\s*(?:\\{|;)"
        );
        Matcher matcher = methodPattern.matcher(source);
        while (matcher.find()) {
            String methodName = matcher.group(1);
            String params = matcher.group(2);
            List<String> parameterTypes = parseSourceParameterTypes(params);
            if (parameterTypes == null) {
                continue;
            }
            String normalizedName = ownerSimpleName.equals(methodName) ? "<init>" : methodName;
            keys.add(normalizedName + "#" + parameterTypes.size());
        }
        return keys;
    }

    private Set<String> extractExistingFieldNames(String source) {
        Set<String> names = new TreeSet<>();
        Pattern fieldPattern = Pattern.compile(
            "\\b(?:public|protected|private)\\s+(?:static\\s+)?(?:final\\s+)?[\\w.$<>\\[\\], ?]+\\s+([A-Za-z_$][\\w$]*)\\s*(?:=|;)"
        );
        Matcher matcher = fieldPattern.matcher(source);
        while (matcher.find()) {
            names.add(matcher.group(1));
        }
        return names;
    }

    private List<String> parseSourceParameterTypes(String params) {
        if (params == null || params.isBlank()) {
            return List.of();
        }
        List<String> result = new ArrayList<>();
        for (String rawParam : params.split(",")) {
            String param = rawParam.trim();
            if (param.isBlank()) {
                continue;
            }
            int lastSpace = param.lastIndexOf(' ');
            String type = lastSpace > 0 ? param.substring(0, lastSpace).trim() : param;
            while (type.startsWith("@")) {
                int idx = type.indexOf(' ');
                if (idx < 0) {
                    break;
                }
                type = type.substring(idx + 1).trim();
            }
            type = type.replaceAll("<[^>]*>", "");
            type = type.replace("...", "[]");
            type = type.replaceAll("\\s+", " ");
            result.add(type);
        }
        return result;
    }

    private String renderPrimaryContractMethodStubs(String ownerSimpleName,
                                                    String ownerBinaryName,
                                                    Set<String> methodSelectors,
                                                    int depth) {
        if (methodSelectors == null || methodSelectors.isEmpty()) {
            return "";
        }
        String indent = "    ".repeat(depth);
        StringBuilder sb = new StringBuilder();
        Map<String, MethodStub> methodsBySignature = new TreeMap<>();
        for (String selector : new TreeSet<>(methodSelectors)) {
            MethodStub methodStub = parseMethodSelector(selector, ownerBinaryName, true);
            if (methodStub == null || methodStub.isClassInitializer() || methodStub.isConstructor()) {
                continue;
            }
            if (shouldSkipPrimaryContractMethodStub(methodStub)) {
                continue;
            }
            String signatureKey = methodStub.signatureKey();
            MethodStub existing = methodsBySignature.get(signatureKey);
            if (existing == null || (!existing.isStatic() && methodStub.isStatic())) {
                methodsBySignature.put(signatureKey, methodStub);
            }
        }
        for (MethodStub methodStub : methodsBySignature.values()) {
            sb.append(indent);
            if (methodStub.isStatic()) {
                sb.append("public static ");
            } else {
                sb.append("public ");
            }
            sb.append(methodStub.returnType())
                .append(" ").append(methodStub.methodName())
                .append("(").append(renderParams(methodStub.parameterTypes())).append(") {\n");
            String defaultReturn = defaultReturnStatement(methodStub.returnType());
            if (!defaultReturn.isBlank()) {
                sb.append(indent).append("    ").append(defaultReturn).append("\n");
            }
            sb.append(indent).append("}\n\n");
        }
        return sb.toString();
    }

    private String toSourceSimpleTypeName(String simpleName) {
        if (!simpleName.contains("$")) {
            return simpleName;
        }
        String[] segments = simpleName.split("\\$");
        if (segments.length <= 1) {
            return simpleName;
        }
        for (int i = 1; i < segments.length; i++) {
            if (!isJavaIdentifier(segments[i])) {
                return simpleName;
            }
        }
        StringBuilder sb = new StringBuilder(segments[0]);
        for (int i = 1; i < segments.length; i++) {
            sb.append('.').append(segments[i]);
        }
        return sb.toString();
    }

    private String toSourceTypeReference(String reference) {
        if (reference == null || reference.isBlank()) {
            return null;
        }
        String sourceTypeName = reference.trim();
        String binaryTypeName = toBinaryVanillaClassName(sourceTypeName, false);
        if (binaryTypeName == null) {
            return null;
        }
        return toSourceTypeName(binaryTypeName);
    }

    private String upsertNestedStubBlock(String source, String nestedDeclarations) {
        String normalized = removeNestedStubBlock(source);
        String lineSeparator = normalized.contains("\r\n") ? "\r\n" : "\n";
        String declarationBlock = buildNestedStubBlock(nestedDeclarations, lineSeparator);
        int lastBrace = normalized.lastIndexOf('}');
        if (lastBrace < 0) {
            return normalized;
        }
        String prefix = normalized.substring(0, lastBrace);
        if (!prefix.endsWith(lineSeparator)) {
            prefix = prefix + lineSeparator;
        }
        if (!prefix.endsWith(lineSeparator + lineSeparator)) {
            prefix = prefix + lineSeparator;
        }
        return prefix + declarationBlock + normalized.substring(lastBrace);
    }

    private String removeNestedStubBlock(String source) {
        int begin = source.indexOf(NESTED_STUBS_BEGIN);
        if (begin < 0) {
            return source;
        }
        int endMarker = source.indexOf(NESTED_STUBS_END, begin);
        if (endMarker < 0) {
            return source;
        }
        int endLine = source.indexOf('\n', endMarker);
        if (endLine < 0) {
            endLine = source.length();
        } else {
            endLine += 1;
        }
        return source.substring(0, begin) + source.substring(endLine);
    }

    private String upsertEntityCompatBlock(String source) {
        String normalized = removeEntityCompatBlock(source);
        String lineSeparator = normalized.contains("\r\n") ? "\r\n" : "\n";
        String block = buildEntityCompatBlock(lineSeparator);
        int lastBrace = normalized.lastIndexOf('}');
        if (lastBrace < 0) {
            return normalized;
        }
        String prefix = normalized.substring(0, lastBrace);
        if (!prefix.endsWith(lineSeparator)) {
            prefix = prefix + lineSeparator;
        }
        if (!prefix.endsWith(lineSeparator + lineSeparator)) {
            prefix = prefix + lineSeparator;
        }
        return prefix + block + normalized.substring(lastBrace);
    }

    private String removeEntityCompatBlock(String source) {
        int begin = source.indexOf(ENTITY_COMPAT_BEGIN);
        if (begin < 0) {
            return source;
        }
        int endMarker = source.indexOf(ENTITY_COMPAT_END, begin);
        if (endMarker < 0) {
            return source;
        }
        int endLine = source.indexOf('\n', endMarker);
        if (endLine < 0) {
            endLine = source.length();
        } else {
            endLine += 1;
        }
        return source.substring(0, begin) + source.substring(endLine);
    }

    private String buildEntityCompatBlock(String lineSeparator) {
        return String.join(lineSeparator,
            "    " + ENTITY_COMPAT_BEGIN,
            "    public boolean velocityModified;",
            "",
            "    public Entity() {",
            "    }",
            "",
            "    public boolean isAttackable() {",
            "        return false;",
            "    }",
            "",
            "    public boolean handleAttack(murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity p0) {",
            "        return false;",
            "    }",
            "",
            "    public boolean sidedDamage(murat.simv2.simulation.mirror.net.minecraft.entity.damage.DamageSource p0, float p1) {",
            "        return false;",
            "    }",
            "",
            "    public boolean onKilledOther(",
            "        murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0,",
            "        murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity p1",
            "    ) {",
            "        return false;",
            "    }",
            "    " + ENTITY_COMPAT_END,
            ""
        ) + lineSeparator;
    }

    private String injectPrimaryNoArgConstructor(String source, String fqcn) {
        String simpleName = simpleNameOf(fqcn);
        if (simpleName == null || simpleName.isBlank()) {
            return source;
        }
        Pattern existingCtor = Pattern.compile("\\b(public|protected|private)\\s+" + Pattern.quote(simpleName) + "\\s*\\(\\s*\\)");
        if (existingCtor.matcher(source).find()) {
            return source;
        }
        Pattern classDecl = Pattern.compile("\\bclass\\s+" + Pattern.quote(simpleName) + "\\b");
        if (!classDecl.matcher(source).find()) {
            return source;
        }
        int lastBrace = source.lastIndexOf('}');
        if (lastBrace < 0) {
            return source;
        }
        String lineSeparator = source.contains("\r\n") ? "\r\n" : "\n";
        String prefix = source.substring(0, lastBrace);
        if (!prefix.endsWith(lineSeparator)) {
            prefix += lineSeparator;
        }
        if (!prefix.endsWith(lineSeparator + lineSeparator)) {
            prefix += lineSeparator;
        }
        String constructor = "    public " + simpleName + "() {" + lineSeparator
            + "    }" + lineSeparator + lineSeparator;
        return prefix + constructor + source.substring(lastBrace);
    }

    private String normalizeEntityPrimarySignatures(String source) {
        String normalized = source.replace(
            "public void recalculateDimensions(",
            "public boolean recalculateDimensions("
        );
        int recalcStart = normalized.indexOf("public boolean recalculateDimensions(");
        String nextMethodMarker = "public murat.simv2.simulation.mirror.net.minecraft.util.math.Box getBoundingBox(";
        int nextMethod = recalcStart >= 0 ? normalized.indexOf(nextMethodMarker, recalcStart) : -1;
        if (recalcStart >= 0 && nextMethod > recalcStart) {
            String methodBlock = normalized.substring(recalcStart, nextMethod);
            if (!methodBlock.contains("return false;")) {
                int methodClose = normalized.lastIndexOf("}", nextMethod);
                if (methodClose > recalcStart) {
                    normalized = normalized.substring(0, methodClose)
                        + "\n        return false;" + normalized.substring(methodClose);
                }
            }
        }
        return normalized;
    }

    private String normalizeLivingEntityPrimarySignatures(String source) {
        String normalized = source.replace(
            "protected void setAttackingPlayer(",
            "protected murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity setAttackingPlayer("
        );
        int methodStart = normalized.indexOf("setAttackingPlayer(murat.simv2.simulation.mirror.net.minecraft.entity.damage.DamageSource");
        String nextMethodMarker = "private boolean tryUseDeathProtector(";
        int nextMethod = methodStart >= 0 ? normalized.indexOf(nextMethodMarker, methodStart) : -1;
        if (methodStart >= 0 && nextMethod > methodStart) {
            String methodBlock = normalized.substring(methodStart, nextMethod);
            if (!methodBlock.contains("return null;")) {
                int closeOffset = methodBlock.lastIndexOf("}");
                if (closeOffset > 0) {
                    int methodClose = methodStart + closeOffset;
                    normalized = normalized.substring(0, methodClose)
                        + "\n        return null;" + normalized.substring(methodClose);
                }
            }
        }
        normalized = normalized.replace(
            "return EnchantmentHelper.getMobExperience(world, attacker, this, this.getExperienceToDrop(world));",
            "return EnchantmentHelper.getMobExperience(world, attacker, this, 0);"
        );
        normalized = normalized.replace(
            "murat.simv2.simulation.mirror.net.minecraft.component.type.EquippableComponent equippableComponent = newStack.get(",
            "murat.simv2.simulation.mirror.net.minecraft.component.type.EquippableComponent equippableComponent = (murat.simv2.simulation.mirror.net.minecraft.component.type.EquippableComponent) newStack.get("
        );
        normalized = normalized.replace(
            "murat.simv2.simulation.mirror.net.minecraft.component.type.BlocksAttacksComponent blocksAttacksComponent = this.getActiveItem().get(",
            "murat.simv2.simulation.mirror.net.minecraft.component.type.BlocksAttacksComponent blocksAttacksComponent = (murat.simv2.simulation.mirror.net.minecraft.component.type.BlocksAttacksComponent) this.getActiveItem().get("
        );
        normalized = normalized.replace(
            "murat.simv2.simulation.mirror.net.minecraft.component.type.BlocksAttacksComponent blocksAttacksComponent = itemStack.get(",
            "murat.simv2.simulation.mirror.net.minecraft.component.type.BlocksAttacksComponent blocksAttacksComponent = (murat.simv2.simulation.mirror.net.minecraft.component.type.BlocksAttacksComponent) itemStack.get("
        );
        normalized = normalized.replace(
            "murat.simv2.simulation.mirror.net.minecraft.component.type.BlocksAttacksComponent blocksAttacksComponent = this.activeItemStack.get(",
            "murat.simv2.simulation.mirror.net.minecraft.component.type.BlocksAttacksComponent blocksAttacksComponent = (murat.simv2.simulation.mirror.net.minecraft.component.type.BlocksAttacksComponent) this.activeItemStack.get("
        );
        normalized = normalized.replace(
            "deathProtectionComponent = itemStack2.get(",
            "deathProtectionComponent = (murat.simv2.simulation.mirror.net.minecraft.component.type.DeathProtectionComponent) itemStack2.get("
        );
        normalized = normalized.replace(
            ".map(source::isIn)",
            ".map(tag -> source.isIn((murat.simv2.simulation.mirror.net.minecraft.registry.tag.TagKey) tag))"
        );
        normalized = normalized.replace(
            "for (murat.simv2.simulation.mirror.net.minecraft.entity.EquipmentSlot equipmentSlot : murat.simv2.simulation.mirror.net.minecraft.component.type.AttributeModifierSlot.ARMOR) {",
            "for (murat.simv2.simulation.mirror.net.minecraft.entity.EquipmentSlot equipmentSlot : java.util.List.<murat.simv2.simulation.mirror.net.minecraft.entity.EquipmentSlot>of()) {"
        );
        return normalized;
    }

    private String buildNestedStubBlock(String nestedDeclarations, String lineSeparator) {
        StringBuilder sb = new StringBuilder();
        sb.append("    ").append(NESTED_STUBS_BEGIN).append(lineSeparator);
        sb.append(nestedDeclarations.replace("\n", lineSeparator));
        if (!nestedDeclarations.endsWith("\n")) {
            sb.append(lineSeparator);
        }
        sb.append("    ").append(NESTED_STUBS_END).append(lineSeparator);
        return sb.toString();
    }

    private boolean isJavaIdentifier(String value) {
        if (value == null || value.isBlank()) {
            return false;
        }
        if (!Character.isJavaIdentifierStart(value.charAt(0))) {
            return false;
        }
        for (int i = 1; i < value.length(); i++) {
            if (!Character.isJavaIdentifierPart(value.charAt(i))) {
                return false;
            }
        }
        return true;
    }


    private String packageNameOf(String fqcn) {
        int lastDot = fqcn.lastIndexOf('.');
        if (lastDot < 0) {
            return "";
        }
        return fqcn.substring(0, lastDot);
    }

    private String simpleNameOf(String fqcn) {
        int lastDot = fqcn.lastIndexOf('.');
        if (lastDot < 0 || lastDot == fqcn.length() - 1) {
            return fqcn;
        }
        return fqcn.substring(lastDot + 1);
    }

    private record TypeRewriteRule(Pattern pattern, String replacement) {
    }

    private record TypeDescriptor(String javaType, int nextIndex) {
    }

    private record MethodStub(String methodName, String returnType, List<String> parameterTypes, boolean isStatic) {
        private boolean isConstructor() {
            return "<init>".equals(methodName);
        }

        private boolean isClassInitializer() {
            return "<clinit>".equals(methodName);
        }

        private String signatureKey() {
            return methodName + "|" + String.join(",", parameterTypes);
        }

        private String nameArityKey() {
            return methodName + "#" + parameterTypes.size();
        }
    }

    private boolean shouldSkipPrimaryContractMethodStub(MethodStub methodStub) {
        if (methodStub == null) {
            return true;
        }
        return switch (methodStub.methodName()) {
            case "equals", "hashCode", "toString", "clone", "finalize",
                 "wait", "notify", "notifyAll", "getClass" -> true;
            default -> false;
        };
    }

    private record FieldStub(String fieldName, String fieldType, boolean isStatic) {
    }

    private record CpClassRef(int nameIndex) {
    }

    private record BytecodeMember(String name, String descriptor, int access, String signature) {
    }

    private record BytecodeClass(String className,
                                 String superClassName,
                                 int access,
                                 List<String> interfaceNames,
                                 List<BytecodeMember> fields,
                                 List<BytecodeMember> methods,
                                 String signature) {
        private boolean isEnum() {
            return (access & ACC_ENUM) != 0;
        }
    }

    private static final class StubNode {
        private final String simpleName;
        private final Map<String, StubNode> children = new TreeMap<>();
        private final Set<String> methodSelectors = new TreeSet<>();
        private final Set<FieldStub> fieldStubs = new TreeSet<>(FIELD_STUB_COMPARATOR);

        private StubNode(String simpleName) {
            this.simpleName = simpleName;
        }
    }

    private static final class TopLevelStubPlan {
        private final String packageName;
        private final String topLevelSimpleName;
        private final boolean isInterface;
        private final String superType;
        private final String interfaceTypes;
        private final String typeParameters;
        private final StubNode root = new StubNode("");
        private final Set<String> methodSelectors = new TreeSet<>();
        private final Set<FieldStub> fieldStubs = new TreeSet<>(FIELD_STUB_COMPARATOR);
        private boolean topLevelRequested;

        private TopLevelStubPlan(String packageName,
                                 String topLevelSimpleName,
                                 boolean isInterface,
                                 String superType,
                                 String interfaceTypes,
                                 String typeParameters) {
            this.packageName = packageName;
            this.topLevelSimpleName = topLevelSimpleName;
            this.isInterface = isInterface;
            this.superType = superType;
            this.interfaceTypes = interfaceTypes;
            this.typeParameters = typeParameters;
        }
    }
}
