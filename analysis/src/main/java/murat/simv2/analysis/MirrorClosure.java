package murat.simv2.analysis;

import java.util.Map;
import java.util.Set;

record MirrorClosure(
    Set<String> classes,
    Map<String, Set<String>> methodsByClass
) {
}
