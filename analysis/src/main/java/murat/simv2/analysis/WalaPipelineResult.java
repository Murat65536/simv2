package murat.simv2.analysis;

import java.util.List;
import java.util.Map;
import java.util.Set;

record WalaPipelineResult(
    List<FieldResult> classifiedFields,
    Map<String, Map<String, Set<Integer>>> sliceLines,
    SpoonPrerequisiteStatus spoonPrerequisiteStatus
) {
    enum SpoonPrerequisiteStatus {
        READY,
        POINTER_ANALYSIS_UNAVAILABLE,
        SLICE_LINES_NOT_PRODUCED
    }

    boolean isSpoonReady() {
        return spoonPrerequisiteStatus == SpoonPrerequisiteStatus.READY
            && sliceLines != null
            && !sliceLines.isEmpty();
    }

    String spoonReadinessDetail() {
        return switch (spoonPrerequisiteStatus) {
            case READY -> "Backward slice lines are available for Spoon.";
            case POINTER_ANALYSIS_UNAVAILABLE ->
                "WALA fell back to a CHA call graph, so pointer analysis was unavailable.";
            case SLICE_LINES_NOT_PRODUCED ->
                "Backward slicing completed without producing usable source line mappings.";
        };
    }
}
