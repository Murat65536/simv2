package murat.simv2.analysis;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Matches a callee {@code (ownerInternal, methodName)} against the
 * {@link AnalysisConfig#MIRROR_SINKS} denylist.
 *
 * <p>In the Mixin architecture the denylist is no longer used to rewrite
 * bytecode; it defines the set of side-effecting methods that must be
 * <em>gated</em> by a Mixin while a movement prediction is running (see
 * {@code murat.simv2.mixin}). The WALA pipeline uses these rules to enumerate
 * every reachable sink call-site into {@code sink-callsites.txt}, which is the
 * completeness oracle for the gating Mixin set.
 */
final class SinkRules {

    private SinkRules() {
    }

    private record Rule(Pattern owner, Pattern name) {
        boolean matches(String ownerInternal, String methodName) {
            return (owner == null || owner.matcher(ownerInternal).matches())
                && name.matcher(methodName).matches();
        }
    }

    private static final List<Rule> RULES = buildRules();

    private static List<Rule> buildRules() {
        List<Rule> rules = new ArrayList<>(AnalysisConfig.MIRROR_SINKS.length);
        for (String[] r : AnalysisConfig.MIRROR_SINKS) {
            rules.add(new Rule(
                r[0] == null ? null : Pattern.compile(r[0]),
                Pattern.compile(r[1])));
        }
        return rules;
    }

    /**
     * @param ownerInternal callee owner internal name, slash-form, no leading
     *                      {@code L} or trailing {@code ;}
     *                      (e.g. {@code net/minecraft/world/World}).
     * @param methodName    callee simple method name.
     */
    static boolean isSink(String ownerInternal, String methodName) {
        for (Rule rule : RULES) {
            if (rule.matches(ownerInternal, methodName)) {
                return true;
            }
        }
        return false;
    }
}
