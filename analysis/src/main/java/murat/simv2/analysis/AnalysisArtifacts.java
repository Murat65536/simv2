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
 * On-disk artifact layout for WALA output.
 *
 * <p>Each artifact starts with a contract string {@code "<schema>/v<version>"}.
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

    // ── Closure ──

    public static void writeClosure(Path path, MirrorClosure closure) throws IOException {
        ClosurePayload payload = new ClosurePayload();
        payload.contract = SCHEMA_VERSION;
        payload.classes = new ArrayList<>(new TreeSet<>(closure.classes()));
        Files.writeString(path, GSON.toJson(payload));
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
