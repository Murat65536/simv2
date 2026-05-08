package murat.simv2.analysis;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * On-disk artifact layout shared between the WALA and Spoon phases.
 *
 * <p>Each artifact starts with a contract string {@code "<schema>/v<version>"}
 * so the Spoon phase can detect a stale or incompatible WALA run.
 */
public final class AnalysisArtifacts {
    public static final String SCHEMA_VERSION = "movement-analysis/v2";

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    private AnalysisArtifacts() {
    }

    public static Path slicePath(Path outputDir) {
        return outputDir.resolve("movement-slice.json");
    }

    public static Path closurePath(Path outputDir) {
        return outputDir.resolve("mirror-closure.json");
    }

    public static Path fieldManifestPath(Path outputDir) {
        return outputDir.resolve("movement-fields.txt");
    }

    // ── Slice ──

    public static void writeSlice(Path path, Map<String, Map<String, Set<Integer>>> slice) throws IOException {
        SlicePayload payload = new SlicePayload();
        payload.contract = SCHEMA_VERSION;
        payload.lines = new TreeMap<>();
        for (var e : slice.entrySet()) {
            Map<String, List<Integer>> methodMap = new TreeMap<>();
            for (var m : e.getValue().entrySet()) {
                methodMap.put(m.getKey(), new ArrayList<>(new TreeSet<>(m.getValue())));
            }
            payload.lines.put(e.getKey(), methodMap);
        }
        Files.writeString(path, GSON.toJson(payload));
    }

    public static Map<String, Map<String, Set<Integer>>> readSlice(Path path) throws IOException {
        SlicePayload payload = GSON.fromJson(Files.readString(path), SlicePayload.class);
        if (payload == null || !SCHEMA_VERSION.equals(payload.contract)) {
            throw new IllegalStateException("Slice JSON " + path + " has wrong contract; expected "
                + SCHEMA_VERSION + ", got " + (payload == null ? "null" : payload.contract));
        }
        Map<String, Map<String, Set<Integer>>> result = new TreeMap<>();
        if (payload.lines != null) {
            for (var e : payload.lines.entrySet()) {
                Map<String, Set<Integer>> methods = new TreeMap<>();
                for (var m : e.getValue().entrySet()) {
                    methods.put(m.getKey(), new TreeSet<>(m.getValue()));
                }
                result.put(e.getKey(), methods);
            }
        }
        return result;
    }

    // ── Closure ──

    public static void writeClosure(Path path, MirrorClosure closure) throws IOException {
        ClosurePayload payload = new ClosurePayload();
        payload.contract = SCHEMA_VERSION;
        payload.classes = new ArrayList<>(new TreeSet<>(closure.classes()));
        Files.writeString(path, GSON.toJson(payload));
    }

    public static MirrorClosure readClosure(Path path) throws IOException {
        ClosurePayload payload = GSON.fromJson(Files.readString(path), ClosurePayload.class);
        if (payload == null || !SCHEMA_VERSION.equals(payload.contract)) {
            throw new IllegalStateException("Closure JSON " + path + " has wrong contract; expected "
                + SCHEMA_VERSION + ", got " + (payload == null ? "null" : payload.contract));
        }
        Set<String> classes = payload.classes == null ? Set.of() : Set.copyOf(new TreeSet<>(payload.classes));
        return new MirrorClosure(classes);
    }

    // ── Field manifest (human-readable) ──

    public static void writeFieldManifest(Path path, List<FieldResult> fields) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("# Movement field manifest\n");
        sb.append("# contract: ").append(SCHEMA_VERSION).append("\n");
        sb.append("# format: <category> <declaringClass> <fieldName> <typeDescriptor>\n");
        for (FieldResult f : fields) {
            sb.append(String.format(Locale.ROOT, "%-7s %s %s %s%n",
                f.category(), f.declaringClass(), f.fieldName(), f.typeDescriptor()));
        }
        Files.writeString(path, sb.toString());
    }

    public static List<FieldResult> readFieldManifest(Path path) throws IOException {
        List<FieldResult> result = new ArrayList<>();
        for (String line : Files.readAllLines(path)) {
            String trimmed = line.trim();
            if (trimmed.isEmpty() || trimmed.startsWith("#")) {
                continue;
            }
            String[] parts = trimmed.split("\\s+");
            if (parts.length != 4) {
                throw new IllegalStateException("Malformed manifest line in " + path + ": " + line);
            }
            result.add(new FieldResult(parts[1], parts[2], parts[3], FieldResult.Category.valueOf(parts[0])));
        }
        return result;
    }

    public static void requireWalaArtifacts(Path outputDir) {
        Path[] required = { slicePath(outputDir), closurePath(outputDir), fieldManifestPath(outputDir) };
        for (Path p : required) {
            if (!Files.exists(p)) {
                throw new IllegalStateException(
                    "Missing WALA artifact: " + p + ". Run :analysis:runWala first.");
            }
        }
    }

    // ── Gson payloads ──

    private static final class SlicePayload {
        String contract;
        Map<String, Map<String, List<Integer>>> lines;
    }

    private static final class ClosurePayload {
        String contract;
        List<String> classes;
    }
}
