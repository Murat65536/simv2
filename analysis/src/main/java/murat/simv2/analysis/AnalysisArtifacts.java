package murat.simv2.analysis;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.regex.Pattern;

final class AnalysisArtifacts {
    static final String FIELD_MANIFEST_SCHEMA = "movement-fields";
    static final int FIELD_MANIFEST_VERSION = 1;
    static final Comparator<FieldResult> FIELD_MANIFEST_ORDER = Comparator
        .comparing(FieldResult::declaringClass)
        .thenComparing(FieldResult::fieldName)
        .thenComparing(FieldResult::typeDescriptor)
        .thenComparing(field -> field.category().name());

    private static final String FIELD_MANIFEST_CONTRACT_PREFIX = "manifest ";
    private static final Pattern FIELD_MANIFEST_CONTRACT_PATTERN = Pattern.compile("^([a-z0-9-]+)/v(\\d+)$");
    private static final String FIELD_MANIFEST_COLUMNS =
        "CATEGORY declaring_class field_name type_descriptor";

    private AnalysisArtifacts() {
    }

    static Path fieldManifestPath(Path outputDir) {
        return outputDir.resolve("movement-fields.txt");
    }

    static Path sliceJsonPath(Path outputDir) {
        return outputDir.resolve("movement-slice.json");
    }

    static SpoonArtifacts loadForSpoon(Path outputDir) throws IOException {
        List<FieldResult> fields = loadFieldManifest(fieldManifestPath(outputDir));
        Map<String, Map<String, Set<Integer>>> sliceLines = loadSliceLines(sliceJsonPath(outputDir));
        return new SpoonArtifacts(fields, sliceLines);
    }

    static String expectedFieldManifestContractLine() {
        return FIELD_MANIFEST_CONTRACT_PREFIX + FIELD_MANIFEST_SCHEMA + "/v" + FIELD_MANIFEST_VERSION;
    }

    static List<FieldResult> loadFieldManifest(Path manifestPath) throws IOException {
        if (!Files.exists(manifestPath)) {
            throw new IllegalStateException("Missing movement field manifest: " + manifestPath
                + ". Run WALA phase first.");
        }

        List<FieldResult> fields = new ArrayList<>();
        Set<String> uniqueKeys = new LinkedHashSet<>();
        boolean contractRead = false;
        int lineNumber = 0;
        for (String line : Files.readAllLines(manifestPath)) {
            lineNumber++;
            String trimmed = line.trim();
            if (trimmed.isEmpty() || trimmed.startsWith("#")) {
                continue;
            }

            if (!contractRead) {
                validateManifestContract(trimmed, manifestPath, lineNumber, line);
                contractRead = true;
                continue;
            }

            String[] parts = trimmed.split("\\s+");
            if (parts.length != 4) {
                throw malformedManifest(
                    manifestPath,
                    lineNumber,
                    "Expected exactly 4 columns (" + FIELD_MANIFEST_COLUMNS + "), found " + parts.length + ".",
                    line
                );
            }

            FieldResult.FieldCategory category = parseFieldCategory(parts[0], manifestPath, lineNumber, line);
            if (!parts[1].startsWith("L") || parts[1].length() < 2) {
                throw malformedManifest(
                    manifestPath,
                    lineNumber,
                    "Invalid declaring_class '" + parts[1]
                        + "'. Expected an internal class name like Lnet/minecraft/...",
                    line
                );
            }

            FieldResult field = new FieldResult(
                parts[1],
                parts[2],
                parts[3],
                category
            );
            if (!uniqueKeys.add(field.key())) {
                throw malformedManifest(
                    manifestPath,
                    lineNumber,
                    "Duplicate field entry: " + field.key() + ".",
                    line
                );
            }
            fields.add(field);
        }

        if (!contractRead) {
            throw new IllegalStateException("Movement field manifest is missing contract line in " + manifestPath
                + ". Expected '" + expectedFieldManifestContractLine()
                + "' near the top of the file. Run WALA phase (:analysis:runWala) to regenerate artifacts.");
        }
        if (fields.isEmpty()) {
            throw new IllegalStateException("Movement field manifest has no field entries: " + manifestPath
                + ". Run WALA phase (:analysis:runWala) to regenerate artifacts.");
        }
        return List.copyOf(fields);
    }

    private static void validateManifestContract(String trimmed, Path manifestPath, int lineNumber, String line) {
        if (!trimmed.startsWith(FIELD_MANIFEST_CONTRACT_PREFIX)) {
            throw malformedManifest(
                manifestPath,
                lineNumber,
                "Missing manifest contract line. Expected '" + expectedFieldManifestContractLine()
                    + "' before field entries.",
                line
            );
        }
        String contractValue = trimmed.substring(FIELD_MANIFEST_CONTRACT_PREFIX.length()).trim();
        var matcher = FIELD_MANIFEST_CONTRACT_PATTERN.matcher(contractValue);
        if (!matcher.matches()) {
            throw malformedManifest(
                manifestPath,
                lineNumber,
                "Malformed contract value '" + contractValue
                    + "'. Expected '<schema>/v<version>' (for example: "
                    + FIELD_MANIFEST_SCHEMA + "/v" + FIELD_MANIFEST_VERSION + ").",
                line
            );
        }

        String schema = matcher.group(1);
        int version = Integer.parseInt(matcher.group(2));
        if (!FIELD_MANIFEST_SCHEMA.equals(schema)) {
            throw malformedManifest(
                manifestPath,
                lineNumber,
                "Unsupported manifest schema '" + schema + "'. Expected '" + FIELD_MANIFEST_SCHEMA + "'.",
                line
            );
        }
        if (version != FIELD_MANIFEST_VERSION) {
            throw malformedManifest(
                manifestPath,
                lineNumber,
                "Unsupported manifest version " + version + ". Supported version is "
                    + FIELD_MANIFEST_VERSION + ".",
                line
            );
        }
    }

    private static FieldResult.FieldCategory parseFieldCategory(
        String rawCategory,
        Path manifestPath,
        int lineNumber,
        String line
    ) {
        try {
            return FieldResult.FieldCategory.valueOf(rawCategory);
        } catch (IllegalArgumentException ex) {
            throw malformedManifest(
                manifestPath,
                lineNumber,
                "Unsupported category '" + rawCategory + "'. Expected one of: MOD, REF, MOD_REF.",
                line
            );
        }
    }

    private static IllegalStateException malformedManifest(Path manifestPath, int lineNumber, String detail, String line) {
        return new IllegalStateException(
            "Invalid movement field manifest at " + manifestPath + ":" + lineNumber + ". "
                + detail + " Line: " + line
                + ". Run WALA phase (:analysis:runWala) to regenerate artifacts."
        );
    }

    static Map<String, Map<String, Set<Integer>>> loadSliceLines(Path slicePath) throws IOException {
        if (!Files.exists(slicePath)) {
            throw new IllegalStateException("Missing movement slice JSON: " + slicePath
                + ". Run WALA phase first. If WALA fell back to CHA or produced no slice lines, "
                + "rerun WALA with pointer analysis available.");
        }

        Type mapType = new TypeToken<Map<String, Map<String, Set<Integer>>>>() {
        }.getType();
        String json = Files.readString(slicePath);
        Map<String, Map<String, Set<Integer>>> rawSliceLines = new Gson().fromJson(json, mapType);
        if (rawSliceLines == null || rawSliceLines.isEmpty()) {
            throw new IllegalStateException("Movement slice JSON is empty: " + slicePath
                + ". WALA did not produce usable slice lines.");
        }
        return canonicalizeSliceLines(rawSliceLines);
    }

    private static Map<String, Map<String, Set<Integer>>> canonicalizeSliceLines(
        Map<String, Map<String, Set<Integer>>> rawSliceLines
    ) {
        Map<String, Map<String, Set<Integer>>> canonical = new TreeMap<>();
        for (Map.Entry<String, Map<String, Set<Integer>>> classEntry : rawSliceLines.entrySet()) {
            String className = classEntry.getKey();
            if (className == null || className.isBlank()) {
                throw new IllegalStateException("Slice JSON contains empty class name.");
            }
            Map<String, Set<Integer>> methodMap = classEntry.getValue();
            if (methodMap == null || methodMap.isEmpty()) {
                continue;
            }
            Map<String, Set<Integer>> methods = new TreeMap<>();
            for (Map.Entry<String, Set<Integer>> methodEntry : methodMap.entrySet()) {
                String methodSelector = methodEntry.getKey();
                if (methodSelector == null || methodSelector.isBlank()) {
                    throw new IllegalStateException("Slice JSON contains empty method selector in " + className);
                }
                Set<Integer> lines = methodEntry.getValue();
                if (lines == null || lines.isEmpty()) {
                    continue;
                }
                TreeSet<Integer> sortedLines = new TreeSet<>(Comparator.naturalOrder());
                for (Integer line : lines) {
                    if (line != null && line > 0) {
                        sortedLines.add(line);
                    }
                }
                if (!sortedLines.isEmpty()) {
                    methods.put(methodSelector, Set.copyOf(sortedLines));
                }
            }
            if (!methods.isEmpty()) {
                canonical.put(className, new LinkedHashMap<>(methods));
            }
        }
        if (canonical.isEmpty()) {
            throw new IllegalStateException("Slice JSON had no usable slice lines after validation.");
        }
        return Map.copyOf(canonical);
    }
}
