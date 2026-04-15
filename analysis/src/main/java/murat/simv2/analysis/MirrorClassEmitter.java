package murat.simv2.analysis;

import java.io.IOException;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

final class MirrorClassEmitter {
    private static final String SLICED_PACKAGE = "murat.simv2.simulation.sliced";
    private static final String MIRROR_PACKAGE_PREFIX = "murat.simv2.simulation.mirror";
    private static final String MIRROR_PACKAGE_PREFIX_DOT = MIRROR_PACKAGE_PREFIX + ".";
    private static final Pattern IMPORT_PATTERN = Pattern.compile("import\\s+(static\\s+)?([\\w.$*]+);");

    private final Path outputDir;
    private final Map<String, String> primaryClassesBySimpleName;
    private Map<String, String> closureRewriteMap = Map.of();
    private List<TypeRewriteRule> closureRewriteRules = List.of();

    MirrorClassEmitter(Path outputDir) {
        this.outputDir = outputDir;
        this.primaryClassesBySimpleName = buildPrimaryClassMap();
    }

    void emit(MirrorClosure mirrorClosure) throws IOException {
        Path slicedDir = outputDir.resolve("java/murat/simv2/simulation/sliced");
        Path mirrorRootDir = outputDir.resolve("java/murat/simv2/simulation/mirror");
        Files.createDirectories(mirrorRootDir);

        // Rewrite closure imports to mirrored namespace, while preserving explicit vanilla
        // type references in primary mirrors so generated closure stubs stay compile-safe.
        closureRewriteMap = buildClosureRewriteMap(mirrorClosure);
        closureRewriteRules = List.of();
        emitPrimaryMirrorClasses(slicedDir, mirrorRootDir);
        emitClosureStubs(mirrorClosure, mirrorRootDir);
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

    private void emitPrimaryMirrorClasses(Path slicedDir, Path mirrorRootDir) throws IOException {
        for (Map.Entry<String, String> entry : primaryClassesBySimpleName.entrySet()) {
            String simpleName = entry.getKey();
            String fqcn = entry.getValue();
            Path slicedFile = slicedDir.resolve("Sliced" + simpleName + ".java");
            if (!Files.exists(slicedFile)) {
                continue;
            }

            String source = Files.readString(slicedFile);
            String transformed = transformPrimaryClassSource(source, fqcn);

            Path outputFile = mirrorRootDir.resolve(fqcn.replace('.', '/') + ".java");
            Files.createDirectories(outputFile.getParent());
            Files.writeString(outputFile, transformed);
            System.out.println("Generated mirrored class: " + fqcn);
        }
    }

    private String transformPrimaryClassSource(String source, String fqcn) {
        String transformed = source;

        for (Map.Entry<String, String> classEntry : primaryClassesBySimpleName.entrySet()) {
            String targetSimple = classEntry.getKey();
            String targetFqcn = classEntry.getValue();
            transformed = replaceSimpleTypeReference(transformed, targetSimple, targetFqcn);
        }

        String packageName = packageNameOf(fqcn);
        String mirroredPackageName = packageName.isBlank()
            ? MIRROR_PACKAGE_PREFIX
            : MIRROR_PACKAGE_PREFIX + "." + packageName;
        transformed = transformed.replace(
            "package " + SLICED_PACKAGE + ";",
            "package " + mirroredPackageName + ";"
        );

        for (Map.Entry<String, String> classEntry : primaryClassesBySimpleName.entrySet()) {
            String simpleName = classEntry.getKey();
            String originalFqcn = classEntry.getValue();
            String oldSimple = "Sliced" + simpleName;
            String oldFqcn = SLICED_PACKAGE + "." + oldSimple;
            String newFqcn = mirroredFqcn(originalFqcn);

            transformed = transformed.replace(oldFqcn, newFqcn);
            transformed = replaceTypeIdentifier(transformed, oldSimple, simpleName);
        }

        transformed = qualifyClosureSimpleReferences(transformed, fqcn);
        transformed = rewriteClosureTypeReferences(transformed);
        transformed = transformed.replace("Sliced from ", "Mirrored from ");
        transformed = transformed.replace("sliced construction", "mirror construction");
        transformed = transformed.replace(
            "// Mirrored from " + mirroredFqcn(fqcn),
            "// Mirrored from " + fqcn
        );
        transformed = addRequiredPrimaryImports(transformed, fqcn);
        return normalizeImports(transformed, fqcn);
    }

    private String addRequiredPrimaryImports(String source, String fqcn) {
        String currentPackage = packageNameOf(fqcn);
        String currentSimpleName = simpleNameOf(fqcn);
        TreeSet<String> importsToAdd = new TreeSet<>();

        for (Map.Entry<String, String> entry : primaryClassesBySimpleName.entrySet()) {
            String targetSimple = entry.getKey();
            String targetClass = entry.getValue();
            if (targetSimple.equals(currentSimpleName)) {
                continue;
            }
            if (packageNameOf(targetClass).equals(currentPackage)) {
                continue;
            }
            String targetImport = mirroredFqcn(targetClass);
            if (source.contains("import " + targetImport + ";")) {
                continue;
            }
            Pattern usage = Pattern.compile("\\b" + Pattern.quote(targetSimple) + "\\b");
            if (usage.matcher(source).find()) {
                importsToAdd.add("import " + targetImport + ";");
            }
        }

        if (importsToAdd.isEmpty()) {
            return source;
        }

        String lineSeparator = source.contains("\r\n") ? "\r\n" : "\n";
        String[] lines = source.split("\\R", -1);
        StringBuilder sb = new StringBuilder();
        int insertAfter = 0;
        int packageLineIndex = -1;
        for (int i = 0; i < lines.length; i++) {
            if (lines[i].startsWith("package ")) {
                packageLineIndex = i;
            }
            if (lines[i].startsWith("import ")) {
                insertAfter = i + 1;
            } else if (!lines[i].isBlank() && !lines[i].startsWith("package ")) {
                break;
            }
        }
        if (insertAfter == 0 && packageLineIndex >= 0) {
            insertAfter = packageLineIndex + 1;
        }

        for (int i = 0; i < lines.length; i++) {
            sb.append(lines[i]).append(lineSeparator);
            if (i + 1 == insertAfter) {
                for (String importLine : importsToAdd) {
                    sb.append(importLine).append(lineSeparator);
                }
            }
        }
        return sb.toString();
    }

    private String normalizeImports(String source, String fqcn) {
        String lineSeparator = source.contains("\r\n") ? "\r\n" : "\n";
        String[] lines = source.split("\\R", -1);
        String packageLine = null;
        TreeSet<String> imports = new TreeSet<>();
        List<String> bodyLines = new ArrayList<>();

        for (String line : lines) {
            String trimmed = line.trim();
            if (trimmed.startsWith("package ")) {
                packageLine = trimmed;
                continue;
            }

            Matcher importMatcher = IMPORT_PATTERN.matcher(trimmed);
            if (importMatcher.matches()) {
                boolean isStatic = importMatcher.group(1) != null;
                String importTarget = importMatcher.group(2);
                String rewrittenTarget = rewriteImportTarget(importTarget, isStatic);
                if (shouldKeepImport(rewrittenTarget, isStatic, fqcn)) {
                    imports.add("import " + (isStatic ? "static " : "") + rewrittenTarget + ";");
                }
                continue;
            }

            bodyLines.add(line);
        }

        while (!bodyLines.isEmpty() && bodyLines.get(0).isBlank()) {
            bodyLines.remove(0);
        }

        StringBuilder normalized = new StringBuilder();
        if (packageLine != null) {
            normalized.append(packageLine).append(lineSeparator).append(lineSeparator);
        }
        if (!imports.isEmpty()) {
            for (String importLine : imports) {
                normalized.append(importLine).append(lineSeparator);
            }
            normalized.append(lineSeparator);
        }

        for (int i = 0; i < bodyLines.size(); i++) {
            normalized.append(bodyLines.get(i));
            if (i < bodyLines.size() - 1) {
                normalized.append(lineSeparator);
            }
        }
        if (normalized.length() > 0 && !normalized.toString().endsWith(lineSeparator)) {
            normalized.append(lineSeparator);
        }
        return normalized.toString();
    }

    private boolean shouldKeepImport(String importTarget, boolean isStatic, String fqcn) {
        String currentMirroredFqcn = mirroredFqcn(fqcn);
        String ownerType = importTarget;
        if (isStatic) {
            int memberSeparator = importTarget.lastIndexOf('.');
            if (memberSeparator <= 0) {
                return false;
            }
            ownerType = importTarget.substring(0, memberSeparator);
        }

        if (primaryClassesBySimpleName.containsValue(ownerType)) {
            return false;
        }
        if (ownerType.equals(currentMirroredFqcn) || ownerType.startsWith(currentMirroredFqcn + ".")) {
            return false;
        }
        return isStatic || !packageNameOf(ownerType).equals(packageNameOf(currentMirroredFqcn));
    }

    private String rewriteImportTarget(String importTarget, boolean isStatic) {
        if (!isStatic) {
            return rewriteImportType(importTarget);
        }

        int memberSeparator = importTarget.lastIndexOf('.');
        if (memberSeparator <= 0) {
            return rewriteImportType(importTarget);
        }

        String ownerType = importTarget.substring(0, memberSeparator);
        String memberName = importTarget.substring(memberSeparator + 1);
        return rewriteImportType(ownerType) + "." + memberName;
    }

    private String rewriteImportType(String importTarget) {
        if (importTarget.startsWith(MIRROR_PACKAGE_PREFIX_DOT)) {
            return importTarget;
        }
        return closureRewriteMap.getOrDefault(importTarget, importTarget);
    }

    private Map<String, String> buildClosureRewriteMap(MirrorClosure mirrorClosure) {
        TreeSet<String> closureClasses = new TreeSet<>(primaryClassesBySimpleName.values());
        if (mirrorClosure != null && mirrorClosure.classes() != null) {
            for (String className : mirrorClosure.classes()) {
                if (className != null && className.startsWith("net.minecraft.")) {
                    closureClasses.add(className);
                }
            }
        }

        Map<String, String> rewrites = new TreeMap<>();
        for (String className : closureClasses) {
            String mirroredBinary = mirroredFqcn(className);
            rewrites.putIfAbsent(className, mirroredBinary);
            rewrites.putIfAbsent(className.replace('$', '.'), mirroredBinary);
        }
        return Map.copyOf(rewrites);
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
                        + "(?<![\\w$.])" + Pattern.quote(entry.getKey()) + "(?![\\w$])"
                ),
                entry.getValue()
            ))
            .toList();
    }

    private String rewriteClosureTypeReferences(String source) {
        String rewritten = source;
        for (TypeRewriteRule rule : closureRewriteRules) {
            rewritten = rule.pattern()
                .matcher(rewritten)
                .replaceAll(Matcher.quoteReplacement(rule.replacement()));
        }
        return rewritten;
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

    private void emitClosureStubs(MirrorClosure mirrorClosure, Path mirrorRootDir) throws IOException {
        if (mirrorClosure == null || mirrorClosure.classes() == null || mirrorClosure.classes().isEmpty()) {
            return;
        }
        Set<String> sorted = new TreeSet<>(mirrorClosure.classes());
        for (String fqcn : sorted) {
            if (fqcn == null || !fqcn.startsWith("net.minecraft.")) {
                continue;
            }
            if (primaryClassesBySimpleName.containsValue(fqcn)) {
                continue;
            }
            Path outputFile = mirrorRootDir.resolve(fqcn.replace('.', '/') + ".java");
            if (Files.exists(outputFile)) {
                continue;
            }
            Files.createDirectories(outputFile.getParent());
            Files.writeString(outputFile, generateStubSource(fqcn));
        }
    }

    private String generateStubSource(String fqcn) {
        String packageName = packageNameOf(fqcn);
        String simpleName = simpleNameOf(fqcn);
        return """
            package %s.%s;

            // Generated mirror stub for simulation closure.
            public abstract class %s {
            }
            """.formatted(MIRROR_PACKAGE_PREFIX, packageName, simpleName);
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
}
