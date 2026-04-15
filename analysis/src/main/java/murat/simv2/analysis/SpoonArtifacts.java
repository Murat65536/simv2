package murat.simv2.analysis;

import java.util.List;
import java.util.Map;
import java.util.Set;

record SpoonArtifacts(
    List<FieldResult> fields,
    Map<String, Map<String, Set<Integer>>> sliceLines,
    MirrorClosure mirrorClosure
) {
}
