package murat.simv2.analysis;

public record FieldResult(
    String declaringClass,
    String fieldName,
    String typeDescriptor,
    FieldCategory category
) {

    public enum FieldCategory {
        MOD,
        REF,
        MOD_REF
    }

    public String key() {
        return declaringClass + "." + fieldName;
    }
}
