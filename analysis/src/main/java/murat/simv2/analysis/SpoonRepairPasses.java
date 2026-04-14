package murat.simv2.analysis;

import spoon.reflect.code.CtAssignment;
import spoon.reflect.code.CtBlock;
import spoon.reflect.code.CtExpression;
import spoon.reflect.code.CtForEach;
import spoon.reflect.code.CtIf;
import spoon.reflect.code.CtLocalVariable;
import spoon.reflect.code.CtStatement;
import spoon.reflect.code.CtVariableAccess;
import spoon.reflect.code.CtVariableWrite;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.factory.Factory;
import spoon.reflect.visitor.filter.TypeFilter;

final class SpoonRepairPasses {
    void repairMethodAfterPrune(CtMethod<?> method, String sourceSimpleName, Factory factory) {
        repairEntityMovePositionAdvance(method, sourceSimpleName, factory);
    }

    boolean shouldSkipUnresolvedThisCall(String methodName) {
        return "<init>".equals(methodName);
    }

    /**
     * WALA can prune the accumulator assignment inside Entity#move's axis loop,
     * leaving a no-op loop that never advances position.
     * When that pattern is detected, reinsert vec3d2 = vec3d3.
     */
    private void repairEntityMovePositionAdvance(CtMethod<?> method,
                                                 String sourceSimpleName,
                                                 Factory factory) {
        if (!"Entity".equals(sourceSimpleName)
            || !"move".equals(method.getSimpleName())
            || method.getBody() == null) {
            return;
        }
        for (CtForEach forEach : method.getElements(new TypeFilter<>(CtForEach.class))) {
            CtStatement bodyStatement = forEach.getBody();
            if (bodyStatement == null) {
                continue;
            }
            CtBlock<?> bodyBlock;
            if (bodyStatement instanceof CtBlock<?> block) {
                bodyBlock = block;
            } else {
                CtBlock<?> wrapped = factory.createBlock();
                wrapped.addStatement(bodyStatement.clone());
                forEach.setBody(wrapped);
                bodyBlock = wrapped;
            }

            for (CtIf conditional : bodyBlock.getElements(new TypeFilter<>(CtIf.class))) {
                CtStatement thenStatement = conditional.getThenStatement();
                if (thenStatement == null) {
                    continue;
                }
                CtBlock<?> thenBlock;
                if (thenStatement instanceof CtBlock<?> block) {
                    thenBlock = block;
                } else {
                    CtBlock<?> wrappedThen = factory.createBlock();
                    wrappedThen.addStatement(thenStatement.clone());
                    conditional.setThenStatement(wrappedThen);
                    thenBlock = wrappedThen;
                }

                boolean declaresStepVec = thenBlock.getStatements().stream().anyMatch(
                    statement -> statement instanceof CtLocalVariable<?> localVariable
                        && "vec3d3".equals(localVariable.getSimpleName()));
                boolean updatesAccumulatedPos = thenBlock.getElements(new TypeFilter<>(CtAssignment.class)).stream()
                    .map(CtAssignment.class::cast)
                    .anyMatch(assignment -> assignmentWritesVariable(assignment.getAssigned(), "vec3d2"));
                if (declaresStepVec && !updatesAccumulatedPos) {
                    thenBlock.addStatement(factory.Code().createCodeSnippetStatement("vec3d2 = vec3d3"));
                }
            }
        }
    }

    private boolean assignmentWritesVariable(CtExpression<?> assigned, String variableName) {
        if (assigned instanceof CtVariableWrite<?> variableWrite) {
            return variableName.equals(variableWrite.getVariable().getSimpleName());
        }
        return assigned instanceof CtVariableAccess<?> variableAccess
            && variableName.equals(variableAccess.getVariable().getSimpleName());
    }
}
