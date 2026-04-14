package murat.simv2.analysis;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import spoon.reflect.code.CtFieldAccess;
import spoon.reflect.code.CtInvocation;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtField;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.declaration.CtType;
import spoon.reflect.declaration.ModifierKind;
import spoon.reflect.factory.Factory;
import spoon.reflect.reference.CtExecutableReference;
import spoon.reflect.reference.CtTypeReference;
import spoon.reflect.visitor.filter.TypeFilter;

final class SlicedFieldPlanner {
    List<CtField<?>> extractFieldDeclarations(CtType<?> type,
                                              List<CtMethod<?>> methods,
                                              Map<String, CtType<?>> typeIndex,
                                              Set<String> inheritedFieldNames) {
        Set<String> referencedFieldNames = new HashSet<>();
        for (CtMethod<?> method : methods) {
            for (CtFieldAccess<?> fieldAccess : method.getElements(new TypeFilter<>(CtFieldAccess.class))) {
                referencedFieldNames.add(fieldAccess.getVariable().getSimpleName());
            }
        }

        List<CtField<?>> declarations = new ArrayList<>();
        Set<String> added = new HashSet<>();
        addReferencedFields(type, typeIndex, referencedFieldNames, declarations, added, inheritedFieldNames);

        boolean changed = true;
        while (changed) {
            changed = false;
            Set<String> newReferences = new HashSet<>();
            for (CtField<?> field : declarations) {
                if (field.getDefaultExpression() == null) {
                    continue;
                }
                for (CtFieldAccess<?> fieldAccess : field.getDefaultExpression().getElements(
                    new TypeFilter<>(CtFieldAccess.class))) {
                    String refName = fieldAccess.getVariable().getSimpleName();
                    if (!added.contains(refName)) {
                        newReferences.add(refName);
                    }
                }
            }
            if (!newReferences.isEmpty()) {
                int before = declarations.size();
                addReferencedFields(type, typeIndex, newReferences, declarations, added, inheritedFieldNames);
                changed = declarations.size() > before;
            }
        }
        return declarations;
    }

    void rewriteTrackedDataFieldInitializers(List<CtField<?>> fieldDecls,
                                             String sourceSimpleName,
                                             Factory factory) {
        if (fieldDecls == null || fieldDecls.isEmpty()) {
            return;
        }
        Set<String> trackedFieldNames = vanillaTrackedFieldNames(sourceSimpleName);
        if (trackedFieldNames.isEmpty()) {
            return;
        }
        for (CtField<?> field : fieldDecls) {
            if (field == null || field.getDefaultExpression() == null) {
                continue;
            }
            if (!trackedFieldNames.contains(field.getSimpleName())) {
                continue;
            }
            if (!(field.getDefaultExpression() instanceof CtInvocation<?> invocation)) {
                continue;
            }
            CtExecutableReference<?> executable = invocation.getExecutable();
            if (executable == null || !"registerData".equals(executable.getSimpleName())) {
                continue;
            }
            CtTypeReference<?> declaringType = executable.getDeclaringType();
            if (declaringType == null || !"DataTracker".equals(declaringType.getSimpleName())) {
                continue;
            }
            field.setDefaultExpression(factory.Code().createCodeSnippetExpression(
                sourceSimpleName + "." + field.getSimpleName()));
        }
    }

    private Set<String> vanillaTrackedFieldNames(String sourceSimpleName) {
        return switch (sourceSimpleName) {
            case "Entity" -> Set.of("FLAGS", "AIR", "CUSTOM_NAME", "NAME_VISIBLE",
                "SILENT", "NO_GRAVITY", "POSE", "FROZEN_TICKS");
            case "LivingEntity" -> Set.of("LIVING_FLAGS", "HEALTH", "SLEEPING_POSITION");
            case "PlayerEntity" -> Set.of("ABSORPTION_AMOUNT");
            default -> Set.of();
        };
    }

    private void addReferencedFields(CtType<?> type,
                                     Map<String, CtType<?>> typeIndex,
                                     Set<String> referencedFieldNames,
                                     List<CtField<?>> declarations,
                                     Set<String> added,
                                     Set<String> inheritedFieldNames) {
        CtType<?> current = type;
        while (current != null) {
            for (CtField<?> field : current.getFields()) {
                String name = field.getSimpleName();
                if (current != type && inheritedFieldNames.contains(name)) {
                    continue;
                }
                if (!added.contains(name) && referencedFieldNames.contains(name)) {
                    CtField<?> cloned = field.clone();
                    if (cloned.getDefaultExpression() == null) {
                        cloned.removeModifier(ModifierKind.FINAL);
                    }
                    makeSlicedFieldPublic(cloned);
                    declarations.add(cloned);
                    added.add(name);
                }
            }
            if (current instanceof CtClass<?> ctClass && ctClass.getSuperclass() != null) {
                current = typeIndex.get(ctClass.getSuperclass().getQualifiedName());
            } else {
                break;
            }
        }
    }

    private void makeSlicedFieldPublic(CtField<?> field) {
        if (field == null) {
            return;
        }
        field.removeModifier(ModifierKind.PRIVATE);
        field.removeModifier(ModifierKind.PROTECTED);
        field.addModifier(ModifierKind.PUBLIC);
    }
}
