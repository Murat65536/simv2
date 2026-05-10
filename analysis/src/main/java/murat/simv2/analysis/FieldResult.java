package murat.simv2.analysis;

/**
 * One field discovered by the WALA backward slice.
 *
 * @param declaringClass dot-form class name, e.g. {@code net.minecraft.entity.Entity}.
 * @param fieldName simple field name.
 * @param typeDescriptor JVM type descriptor ({@code I}, {@code Lnet/minecraft/util/math/Vec3d;}, etc.).
 * @param category whether the field is mutated, only read, or both, in the slice.
 */
public record FieldResult(
    String declaringClass,
    String fieldName,
    String typeDescriptor,
    Category category
) {
    public enum Category { MOD, REF, MOD_REF }

    public static Category mergeWith(Category a, Category b) {
        if (a == b) return a;
        return Category.MOD_REF;
    }
}
