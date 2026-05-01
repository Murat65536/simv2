package murat.simv2.analysis;

import java.util.Set;

/**
 * The transitive closure of MC types that the mirror must reproduce.
 *
 * @param classes every dot-form class name that must exist as a mirror class.
 */
public record MirrorClosure(Set<String> classes) {
}
