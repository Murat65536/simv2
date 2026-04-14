package murat.simv2.analysis;

import java.util.Locale;

enum AnalysisMode {
    ALL,
    WALA_ONLY,
    SPOON_ONLY;

    static AnalysisMode from(String raw) {
        if (raw == null || raw.isBlank()) {
            return ALL;
        }
        return switch (raw.trim().toLowerCase(Locale.ROOT)) {
            case "all" -> ALL;
            case "wala" -> WALA_ONLY;
            case "spoon" -> SPOON_ONLY;
            default -> throw new IllegalArgumentException(
                "Unknown mode '" + raw + "'. Use one of: all, wala, spoon.");
        };
    }
}
