package murat.simv2.analysis;

import java.util.Map;
import java.util.Set;

record SpoonArtifacts(
    Map<String, Map<String, Set<Integer>>> sliceLines,
    MirrorClosure mirrorClosure
) {
}
