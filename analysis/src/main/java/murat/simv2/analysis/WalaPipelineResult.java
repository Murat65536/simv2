package murat.simv2.analysis;

import java.util.List;
import java.util.Map;
import java.util.Set;

record WalaPipelineResult(
    List<FieldResult> classifiedFields,
    Map<String, Map<String, Set<Integer>>> sliceLines,
    MirrorClosure mirrorClosure,
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
            && !sliceLines.isEmpty()
            && mirrorClosure != null
            && mirrorClosure.classes() != null
            && !mirrorClosure.classes().isEmpty();
    }

    String spoonReadinessDetail() {
        return switch (spoonPrerequisiteStatus) {
            case READY -> "Backward slice lines are available for Spoon.";
            case POINTER_ANALYSIS_UNAVAILABLE ->
                "WALA did not provide pointer analysis for this run.";
            case SLICE_LINES_NOT_PRODUCED ->
                "Backward slicing completed without producing usable source line mappings.";
        };
    }
}
