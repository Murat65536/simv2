package murat.simv2.analysis;

public record FieldResult(
    String declaringClass,
    String fieldName,
    String typeDescriptor,
    Category category
) {
    public enum Category { MOD, REF, MOD_REF }

    public String key() {
        return declaringClass + "." + fieldName;
    }

    public static Category mergeWith(Category a, Category b) {
        if (a == b) return a;
        return Category.MOD_REF;
    }
}
