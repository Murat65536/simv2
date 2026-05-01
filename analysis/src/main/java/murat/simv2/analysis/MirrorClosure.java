package murat.simv2.analysis;

import java.util.Map;
import java.util.Set;

/**
 * The transitive closure of MC types that the mirror must reproduce.
 *
 * @param classes every dot-form class name that must exist as a mirror class.
 * @param slicedMethodsByClass per-class set of method selectors that the slice
 *                             reaches with non-empty line sets. Methods listed
 *                             here keep their pruned bodies in the mirror;
 *                             everything else is emitted as an empty stub.
 */
public record MirrorClosure(
    Set<String> classes,
    Map<String, Set<String>> slicedMethodsByClass
) {
}
