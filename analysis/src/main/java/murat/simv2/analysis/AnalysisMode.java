package murat.simv2.analysis;

import java.util.Locale;

public enum AnalysisMode {
    ALL,
    WALA,
    SPOON;

    public static AnalysisMode parse(String raw) {
        if (raw == null || raw.isBlank()) {
            return ALL;
        }
        return switch (raw.trim().toLowerCase(Locale.ROOT)) {
            case "all" -> ALL;
            case "wala" -> WALA;
            case "spoon" -> SPOON;
            default -> throw new IllegalArgumentException(
                "Unknown analysis mode '" + raw + "'. Expected one of: all, wala, spoon.");
        };
    }
}
