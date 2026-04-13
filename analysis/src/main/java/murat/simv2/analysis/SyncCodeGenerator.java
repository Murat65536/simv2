package murat.simv2.analysis;

import com.ibm.wala.classLoader.IClass;
import com.ibm.wala.classLoader.IField;
import com.ibm.wala.classLoader.IMethod;
import com.ibm.wala.ipa.cha.IClassHierarchy;
import com.ibm.wala.types.TypeReference;
import spoon.Launcher;
import spoon.reflect.code.*;
import spoon.reflect.declaration.*;
import spoon.reflect.factory.Factory;
import spoon.reflect.reference.CtTypeReference;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

public class SyncCodeGenerator {

    private final IClassHierarchy cha;
    private final Path outputDir;
    private final Factory factory;

    public SyncCodeGenerator(IClassHierarchy cha, Path outputDir) {
        this.cha = cha;
        this.outputDir = outputDir;
        this.factory = new Launcher().getFactory();
    }

    public void generate(List<FieldResult> fields) throws IOException {
        generateSyncClass(fields);
        generateAccessWidener(fields);
        generateFieldManifest(fields);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private void generateSyncClass(List<FieldResult> fields) throws IOException {
        Path javaDir = outputDir.resolve("java/murat/simv2/simulation");
        Files.createDirectories(javaDir);

        // Build class via Spoon AST
        CtClass syncClass = factory.Core().createClass();
        syncClass.setSimpleName("GeneratedSync");
        syncClass.addModifier(ModifierKind.PUBLIC);
        syncClass.addModifier(ModifierKind.FINAL);

        // Private no-arg constructor
        CtConstructor ctor = factory.Core().createConstructor();
        ctor.addModifier(ModifierKind.PRIVATE);
        ctor.setBody(factory.createBlock());
        syncClass.addConstructor(ctor);

        // public static void sync(SimulatedPlayer sim, ClientPlayerEntity real)
        CtMethod syncMethod = factory.createMethod();
        syncMethod.setSimpleName("sync");
        syncMethod.addModifier(ModifierKind.PUBLIC);
        syncMethod.addModifier(ModifierKind.STATIC);
        syncMethod.setType(factory.Type().voidPrimitiveType());

        CtParameter simParam = factory.Core().createParameter();
        simParam.setSimpleName("sim");
        CtTypeReference simType = factory.Core().createTypeReference();
        simType.setSimpleName("SimulatedPlayer");
        simParam.setType(simType);
        syncMethod.addParameter(simParam);

        CtParameter realParam = factory.Core().createParameter();
        realParam.setSimpleName("real");
        CtTypeReference realType = factory.Core().createTypeReference();
        realType.setSimpleName("ClientPlayerEntity");
        realParam.setType(realType);
        syncMethod.addParameter(realParam);

        CtBlock body = factory.createBlock();
        syncMethod.setBody(body);

        // Dynamic field assignments grouped by declaring class
        Map<String, List<FieldResult>> byClass = fields.stream()
            .collect(Collectors.groupingBy(FieldResult::declaringClass, LinkedHashMap::new, Collectors.toList()));

        for (var entry : byClass.entrySet()) {
            String className = entry.getKey();
            String shortName = className.substring(className.lastIndexOf('/') + 1);
            addComment(body, "── " + shortName + " ──");

            for (FieldResult field : entry.getValue()) {
                String assignment = generateFieldAssignment(field);
                if (assignment != null) {
                    addSnippet(body, assignment);
                }
            }
        }

        // Hardcoded composite sync items that WALA can't resolve
        addComment(body, "── Composite state (not resolvable by static analysis) ──");
        addSnippet(body, "sim.getAttributes().setFrom(real.getAttributes());");
        addSnippet(body, "sim.clearStatusEffects();");
        addSnippet(body, "for (StatusEffectInstance effect : real.getActiveStatusEffects().values()) {\n            sim.addStatusEffect(new StatusEffectInstance(effect));\n        }");
        addSnippet(body, "PlayerAbilities realAbilities = real.getAbilities();");
        addSnippet(body, "PlayerAbilities simAbilities = sim.getAbilities();");
        addSnippet(body, "simAbilities.flying = realAbilities.flying;");
        addSnippet(body, "simAbilities.allowFlying = realAbilities.allowFlying;");
        addSnippet(body, "simAbilities.creativeMode = realAbilities.creativeMode;");
        addSnippet(body, "simAbilities.invulnerable = realAbilities.invulnerable;");
        addSnippet(body, "simAbilities.allowModifyWorld = realAbilities.allowModifyWorld;");
        addSnippet(body, "simAbilities.setFlySpeed(realAbilities.getFlySpeed());");
        addSnippet(body, "simAbilities.setWalkSpeed(realAbilities.getWalkSpeed());");
        addSnippet(body, "sim.getHungerManager().setFoodLevel(real.getHungerManager().getFoodLevel());");
        addSnippet(body, "sim.getHungerManager().setSaturationLevel(real.getHungerManager().getSaturationLevel());");
        addSnippet(body, "for (var slot : net.minecraft.entity.EquipmentSlot.values()) {\n            sim.equipStack(slot, real.getEquippedStack(slot).copy());\n        }");
        addSnippet(body, "sim.setPose(real.getPose());");
        addComment(body, "DataTracker flags (bit flags in a tracked byte, not individual fields)");
        addSnippet(body, "sim.setSprinting(real.isSprinting());");
        addSnippet(body, "sim.setSneaking(real.isSneaking());");
        addSnippet(body, "sim.setSwimming(real.isSwimming());");
        addSnippet(body, "if (real.isGliding()) {\n            sim.startGliding();\n        } else if (sim.isGliding()) {\n            sim.stopGliding();\n        }");

        syncClass.addMethod(syncMethod);

        // Output: package + imports header, then Spoon-rendered class body
        StringBuilder sb = new StringBuilder();
        sb.append("package murat.simv2.simulation;\n\n");
        sb.append("import net.minecraft.client.network.ClientPlayerEntity;\n");
        sb.append("import net.minecraft.entity.effect.StatusEffectInstance;\n");
        sb.append("import net.minecraft.entity.player.PlayerAbilities;\n\n");
        sb.append("// Generated by WALA movement field analysis — do not edit\n");
        sb.append(syncClass.toString()).append("\n");

        Files.writeString(javaDir.resolve("GeneratedSync.java"), sb.toString());
        System.out.println("Generated GeneratedSync.java");
    }

    private void addComment(CtBlock<?> block, String text) {
        CtComment comment = factory.Core().createComment();
        comment.setCommentType(CtComment.CommentType.INLINE);
        comment.setContent(text);
        block.addStatement(comment);
    }

    private void addSnippet(CtBlock<?> block, String code) {
        CtCodeSnippetStatement stmt = factory.Core().createCodeSnippetStatement();
        // Strip trailing semicolon — CtCodeSnippetStatement adds one during rendering
        String value = code.endsWith(";") ? code.substring(0, code.length() - 1) : code;
        stmt.setValue(value);
        block.addStatement(stmt);
    }

    private String generateFieldAssignment(FieldResult field) {
        String name = field.fieldName();

        // Skip excluded and composite fields
        if (AnalysisConfig.EXCLUDED_FIELDS.contains(name)) return null;
        if (isCompositeField(name)) return null;

        // Check if the field is final — skip it (can't reassign final fields)
        if (isFieldFinal(field)) {
            System.out.println("  Skipping final field: " + field.declaringClass() + "." + name);
            return null;
        }

        // Check for public getter/setter
        GetterSetter gs = findGetterSetter(field);

        if (gs.getter != null && gs.setter != null) {
            return "sim." + gs.setter + "(real." + gs.getter + "());";
        } else if (gs.getter != null) {
            // Has getter for reading but no setter — direct field write
            return "sim." + name + " = real." + gs.getter + "();";
        } else {
            // Direct field access both sides
            return "sim." + name + " = real." + name + ";";
        }
    }

    private boolean isFieldFinal(FieldResult field) {
        String className = field.declaringClass();
        TypeReference typeRef = TypeReference.findOrCreate(cha.getScope().getApplicationLoader(), className);
        IClass clazz = cha.lookupClass(typeRef);
        if (clazz == null) return false;

        for (IField f : clazz.getDeclaredInstanceFields()) {
            if (f.getName().toString().equals(field.fieldName())) {
                return f.isFinal();
            }
        }
        return false;
    }

    private boolean isCompositeField(String name) {
        return Set.of(
            "attributes", "activeStatusEffects", "abilities",
            "hungerManager", "equipment", "pose"
        ).contains(name);
    }

    private GetterSetter findGetterSetter(FieldResult field) {
        TypeReference typeRef = TypeReference.findOrCreate(
            cha.getScope().getApplicationLoader(), field.declaringClass());
        IClass clazz = cha.lookupClass(typeRef);
        if (clazz == null) return new GetterSetter(null, null);

        String fieldName = field.fieldName();
        String getterName = null;
        String setterName = null;

        // Common getter patterns
        for (String prefix : List.of("get", "is", "has")) {
            String candidate = prefix + capitalize(fieldName);
            IMethod method = findPublicNoArgMethod(clazz, candidate);
            if (method != null) {
                getterName = candidate;
                break;
            }
        }

        // Common setter patterns
        String setCandidate = "set" + capitalize(fieldName);
        for (IMethod m : clazz.getAllMethods()) {
            if (m.getName().toString().equals(setCandidate)
                && m.isPublic()
                && m.getNumberOfParameters() == 2) { // 'this' + 1 arg
                setterName = setCandidate;
                break;
            }
        }

        return new GetterSetter(getterName, setterName);
    }

    private IMethod findPublicNoArgMethod(IClass clazz, String name) {
        for (IMethod m : clazz.getAllMethods()) {
            if (m.getName().toString().equals(name)
                && m.isPublic()
                && m.getNumberOfParameters() == 1) { // only 'this'
                return m;
            }
        }
        return null;
    }

    private static String capitalize(String s) {
        if (s.isEmpty()) return s;
        return Character.toUpperCase(s.charAt(0)) + s.substring(1);
    }

    private record GetterSetter(String getter, String setter) {}

    private void generateAccessWidener(List<FieldResult> fields) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("accessWidener v2 named\n\n");
        sb.append("# Generated by WALA movement field analysis\n");

        for (FieldResult field : fields) {
            if (isCompositeField(field.fieldName())) continue;
            if (AnalysisConfig.EXCLUDED_FIELDS.contains(field.fieldName())) continue;
            if (isFieldFinal(field)) continue;

            // Emit AW entry for every field that uses direct access in GeneratedSync.
            // We cannot rely on CHA visibility checks because linemapped JARs have
            // access wideners pre-applied (all fields appear public). Instead, mirror
            // the logic in generateFieldAssignment(): if there's no public getter+setter
            // pair, the generated code uses direct field access and needs widening.
            GetterSetter gs = findGetterSetter(field);
            boolean usesDirectAccess = (gs.getter == null || gs.setter == null);
            if (usesDirectAccess) {
                String classPath = field.declaringClass().substring(1); // strip leading 'L'
                String typeDesc = toJvmDescriptor(field.typeDescriptor());
                sb.append("accessible field ")
                    .append(classPath).append(" ")
                    .append(field.fieldName()).append(" ")
                    .append(typeDesc).append("\n");
            }
        }

        Files.writeString(outputDir.resolve("sim-v2.accesswidener"), sb.toString());
        System.out.println("Generated sim-v2.accesswidener");
    }

    private String toJvmDescriptor(String walaType) {
        // Primitives: single char (Z, I, F, D, etc.)
        if (walaType.length() == 1) {
            return walaType;
        }
        // Already has L prefix — ensure ; suffix (WALA omits it)
        if (walaType.startsWith("L")) {
            return walaType.endsWith(";") ? walaType : walaType + ";";
        }
        // Array types
        if (walaType.startsWith("[")) {
            return walaType;
        }
        return "L" + walaType + ";";
    }

    private void generateFieldManifest(List<FieldResult> fields) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("# Movement field manifest — generated by WALA analysis\n");
        sb.append("# Format: CATEGORY declaring_class field_name type_descriptor\n\n");

        for (FieldResult field : fields) {
            sb.append(String.format("%-8s %-60s %-30s %s%n",
                field.category(),
                field.declaringClass(),
                field.fieldName(),
                field.typeDescriptor()));
        }

        Files.writeString(outputDir.resolve("movement-fields.txt"), sb.toString());
        System.out.println("Generated movement-fields.txt (" + fields.size() + " fields)");
    }
}
