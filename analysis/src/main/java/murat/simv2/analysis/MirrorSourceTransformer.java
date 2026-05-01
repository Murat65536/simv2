package murat.simv2.analysis;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import spoon.Launcher;
import spoon.processing.Processor;
import spoon.reflect.code.CtBlock;
import spoon.reflect.code.CtComment;
import spoon.reflect.code.CtExpression;
import spoon.reflect.code.CtIf;
import spoon.reflect.code.CtLocalVariable;
import spoon.reflect.code.CtLoop;
import spoon.reflect.code.CtReturn;
import spoon.reflect.code.CtStatement;
import spoon.reflect.code.CtSwitch;
import spoon.reflect.cu.CompilationUnit;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtConstructor;
import spoon.reflect.declaration.CtElement;
import spoon.reflect.declaration.CtField;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.declaration.CtPackage;
import spoon.reflect.declaration.CtType;
import spoon.reflect.declaration.ModifierKind;
import spoon.reflect.factory.Factory;
import spoon.reflect.reference.CtTypeReference;
import spoon.reflect.visitor.DefaultJavaPrettyPrinter;
import spoon.reflect.visitor.filter.TypeFilter;
import spoon.support.compiler.VirtualFile;

/**
 * Spoon-driven half of {@link MirrorBuilder}. For each top-level source
 * provided, parses it, applies the slice + package rewrites, and writes
 * the transformed source out to {@code mirror.<package>}.
 *
 * <p>The transformations are deliberately simple:
 *
 * <ul>
 *   <li>Move every emitted type into the {@code mirror.net.minecraft.*}
 *       package.</li>
 *   <li>Rewrite every {@code net.minecraft.X} type reference in the
 *       class body to {@code mirror.net.minecraft.X}.</li>
 *   <li>For methods on the five primary entity-hierarchy classes:
 *       drop method bodies entirely (replace with no-op + default
 *       return) for methods the slice does not reach. For methods the
 *       slice does reach, keep statements whose source line is in the
 *       slice line set; drop the rest.</li>
 *   <li>Make every instance field public so {@code GeneratedSync} can
 *       write to it directly.</li>
 *   <li>Inject a public no-arg constructor on every primary class so
 *       the runtime side can {@code new} a simulator subclass.</li>
 * </ul>
 *
 * <p>For non-primary classes we keep the source body as-is — only the
 * package and type references change.
 */
final class MirrorSourceTransformer {
    private static final String MIRROR_PACKAGE_PREFIX = "murat.simv2.simulation.mirror.";

    private final Map<String, Map<String, Set<Integer>>> slice;
    private final Set<String> primaryClasses;

    MirrorSourceTransformer(Map<String, Map<String, Set<Integer>>> slice,
                            Set<String> primaryClasses) {
        this.slice = slice;
        this.primaryClasses = primaryClasses;
    }

    void transformAll(Map<String, byte[]> sourcesByClass,
                      Path mirrorRoot,
                      Set<String> emitted) throws IOException {
        if (sourcesByClass.isEmpty()) {
            return;
        }
        Launcher launcher = new Launcher();
        launcher.getEnvironment().setNoClasspath(true);
        launcher.getEnvironment().setIgnoreSyntaxErrors(true);
        launcher.getEnvironment().setComplianceLevel(21);
        launcher.getEnvironment().setAutoImports(false);

        Set<String> uniqueEntries = new LinkedHashSet<>();
        for (var e : sourcesByClass.entrySet()) {
            String fileName = e.getKey().replace('.', '/') + ".java";
            if (!uniqueEntries.add(fileName)) continue;
            launcher.addInputResource(new VirtualFile(
                new String(e.getValue(), StandardCharsets.UTF_8), fileName));
        }
        try {
            launcher.buildModel();
        } catch (RuntimeException ex) {
            // Spoon can fail hard on unresolved nested generics; we still
            // continue — failures degrade to a bytecode stub.
            System.err.println("Spoon model build failed: " + ex.getMessage());
            return;
        }

        Factory factory = launcher.getFactory();
        for (CtType<?> topLevel : new ArrayList<>(launcher.getModel().getAllTypes())) {
            if (topLevel.getPackage() == null) continue;
            String fqcn = topLevel.getQualifiedName();
            if (fqcn == null || !fqcn.startsWith("net.minecraft.")) continue;
            try {
                String mirrorSource = transformTopLevel(topLevel, factory);
                if (mirrorSource == null) continue;
                writeMirrorSource(mirrorRoot, fqcn, mirrorSource);
                emitted.add(fqcn);
                for (CtType<?> nested : topLevel.getElements(new TypeFilter<>(CtType.class))) {
                    if (nested != topLevel) {
                        emitted.add(nested.getQualifiedName());
                    }
                }
            } catch (RuntimeException ex) {
                System.err.println("Failed to transform " + fqcn + ": " + ex.getMessage());
            }
        }
    }

    private String transformTopLevel(CtType<?> topLevel, Factory factory) {
        String originalFqcn = topLevel.getQualifiedName();
        boolean primary = primaryClasses.contains(originalFqcn);
        Map<String, Set<Integer>> methodLines = slice.getOrDefault(originalFqcn, Map.of());

        for (CtType<?> type : topLevel.getElements(new TypeFilter<>(CtType.class))) {
            if (primaryClasses.contains(type.getQualifiedName())) {
                injectNoArgConstructorIfMissing(type, factory);
            }
            for (CtField<?> field : type.getFields()) {
                makeFieldMirrorVisible(field);
            }
            for (CtMethod<?> method : new ArrayList<>(type.getMethods())) {
                rewriteMethodBody(method, primary, methodLines, factory);
            }
            stripJavadoc(type);
        }

        rewriteTypeReferences(topLevel);

        // Move into the mirror package.
        String originalPackage = topLevel.getPackage().getQualifiedName();
        String mirrorPackage = MIRROR_PACKAGE_PREFIX + originalPackage;
        CtPackage targetPackage = factory.Package().getOrCreate(mirrorPackage);
        topLevel.getPackage().removeType(topLevel);
        targetPackage.addType(topLevel);

        CompilationUnit cu = factory.Core().createCompilationUnit();
        cu.setFile(Path.of(topLevel.getSimpleName() + ".java").toFile());
        cu.setDeclaredPackage(targetPackage);
        cu.addDeclaredType(topLevel);
        topLevel.setPosition(factory.createPartialSourcePosition(cu));

        DefaultJavaPrettyPrinter printer = new DefaultJavaPrettyPrinter(factory.getEnvironment());
        printer.setPreprocessors(java.util.Collections.<Processor<CtElement>>emptyList());
        return printer.printCompilationUnit(cu);
    }

    /**
     * Replaces a method body with the appropriate stub:
     * <ul>
     *   <li>If {@code primary} and the method is in {@code methodLines}: prune
     *       its statements to those whose source line is in the slice; if the
     *       result is missing a return, insert a default one.</li>
     *   <li>If {@code primary} and the method is <em>not</em> in the slice:
     *       replace the body with a default-return stub.</li>
     *   <li>Otherwise leave the method alone.</li>
     * </ul>
     */
    private void rewriteMethodBody(CtMethod<?> method,
                                   boolean primary,
                                   Map<String, Set<Integer>> methodLines,
                                   Factory factory) {
        if (!primary) return;
        if (method.isAbstract()) return;
        if (method.getBody() == null) return;

        String selector = methodSelector(method);
        Set<Integer> sliceLines = methodLines.get(selector);
        if (sliceLines == null || sliceLines.isEmpty()) {
            replaceWithDefaultReturn(method, factory);
            return;
        }
        pruneToSliceLines(method.getBody(), sliceLines);
        ensureReturnsValue(method, factory);
    }

    /** Recursively drops statements whose own line and all descendant lines lie outside the slice. */
    private void pruneToSliceLines(CtBlock<?> block, Set<Integer> sliceLines) {
        if (block == null) return;
        for (CtStatement stmt : new ArrayList<>(block.getStatements())) {
            if (!isOrContainsSliceLine(stmt, sliceLines)) {
                try {
                    stmt.delete();
                } catch (RuntimeException ignored) {
                    // Some statements (super-call) cannot be deleted; leave them.
                }
                continue;
            }
            descendIntoChildren(stmt, sliceLines);
        }
    }

    private void descendIntoChildren(CtStatement stmt, Set<Integer> sliceLines) {
        if (stmt instanceof CtIf ctIf) {
            if (ctIf.getThenStatement() instanceof CtBlock<?> thenBlock) {
                pruneToSliceLines(thenBlock, sliceLines);
            }
            if (ctIf.getElseStatement() instanceof CtBlock<?> elseBlock) {
                pruneToSliceLines(elseBlock, sliceLines);
            }
        } else if (stmt instanceof CtLoop loop && loop.getBody() instanceof CtBlock<?> body) {
            pruneToSliceLines(body, sliceLines);
        } else if (stmt instanceof CtBlock<?> nested) {
            pruneToSliceLines(nested, sliceLines);
        } else if (stmt instanceof CtSwitch<?> ctSwitch) {
            for (CtStatement inner : ctSwitch.getCases()) {
                if (inner instanceof CtBlock<?> innerBlock) {
                    pruneToSliceLines(innerBlock, sliceLines);
                }
            }
        }
    }

    private boolean isOrContainsSliceLine(CtStatement stmt, Set<Integer> sliceLines) {
        if (stmt.getPosition() != null && stmt.getPosition().isValidPosition()
            && sliceLines.contains(stmt.getPosition().getLine())) {
            return true;
        }
        for (CtStatement desc : stmt.getElements(new TypeFilter<>(CtStatement.class))) {
            if (desc == stmt) continue;
            if (desc.getPosition() != null && desc.getPosition().isValidPosition()
                && sliceLines.contains(desc.getPosition().getLine())) {
                return true;
            }
        }
        return false;
    }

    private void ensureReturnsValue(CtMethod<?> method, Factory factory) {
        CtTypeReference<?> ret = method.getType();
        if (ret == null || "void".equals(ret.getSimpleName())) return;
        if (method.getBody() == null) return;
        boolean hasReturn = !method.getBody().getElements(new TypeFilter<>(CtReturn.class)).isEmpty();
        if (hasReturn) return;
        method.getBody().addStatement(defaultReturnStatement(ret, factory));
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    private void replaceWithDefaultReturn(CtMethod<?> method, Factory factory) {
        CtBlock body = factory.createBlock();
        method.setBody(body);
        CtTypeReference<?> ret = method.getType();
        if (ret == null || "void".equals(ret.getSimpleName())) return;
        body.addStatement(defaultReturnStatement(ret, factory));
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    private CtReturn defaultReturnStatement(CtTypeReference<?> type, Factory factory) {
        CtReturn ret = factory.createReturn();
        CtExpression<?> expr = defaultExpressionFor(type, factory);
        ret.setReturnedExpression((CtExpression) expr);
        return ret;
    }

    private CtExpression<?> defaultExpressionFor(CtTypeReference<?> type, Factory factory) {
        if (type == null) return factory.createLiteral(null);
        return switch (type.getSimpleName()) {
            case "boolean" -> factory.createLiteral(false);
            case "byte"    -> factory.createLiteral((byte) 0);
            case "char"    -> factory.createLiteral('\0');
            case "short"   -> factory.createLiteral((short) 0);
            case "int"     -> factory.createLiteral(0);
            case "long"    -> factory.createLiteral(0L);
            case "float"   -> factory.createLiteral(0.0f);
            case "double"  -> factory.createLiteral(0.0);
            default        -> factory.createLiteral(null);
        };
    }

    private void rewriteTypeReferences(CtType<?> topLevel) {
        for (CtTypeReference<?> ref : topLevel.getElements(new TypeFilter<>(CtTypeReference.class))) {
            String qname = ref.getQualifiedName();
            if (qname == null || !qname.startsWith("net.minecraft.")) continue;
            // Don't touch the primary type's self-reference — Spoon updates it
            // automatically when we move the package, which would otherwise
            // collide with our manual rewrite.
            if (ref == topLevel.getReference()) continue;
            String mirrorQname = MIRROR_PACKAGE_PREFIX + qname;
            ref.setPackage(ref.getFactory().Package().getOrCreate(mirrorQname.substring(0, mirrorQname.lastIndexOf('.'))).getReference());
        }
    }

    private void makeFieldMirrorVisible(CtField<?> field) {
        if (field == null) return;
        if (field.hasModifier(ModifierKind.STATIC)) return;
        field.removeModifier(ModifierKind.PRIVATE);
        field.removeModifier(ModifierKind.PROTECTED);
        if (!field.hasModifier(ModifierKind.PUBLIC)) {
            field.addModifier(ModifierKind.PUBLIC);
        }
        // Drop final on writable mirror fields so sync code can assign.
        if (field.getDefaultExpression() == null) {
            field.removeModifier(ModifierKind.FINAL);
        }
    }

    private void injectNoArgConstructorIfMissing(CtType<?> type, Factory factory) {
        if (!(type instanceof CtClass<?> ctClass)) return;
        for (CtConstructor<?> ctor : ctClass.getConstructors()) {
            if (ctor.getParameters().isEmpty()) return;
        }
        CtConstructor<?> ctor = factory.Core().createConstructor();
        ctor.addModifier(ModifierKind.PUBLIC);
        ctor.setBody(factory.createBlock());
        @SuppressWarnings({ "unchecked", "rawtypes" })
        CtClass raw = ctClass;
        raw.addConstructor(ctor);
    }

    private void stripJavadoc(CtType<?> type) {
        java.util.List<CtComment> comments = type.getElements(new TypeFilter<CtComment>(CtComment.class));
        for (CtComment comment : new ArrayList<>(comments)) {
            if (comment.getCommentType() == CtComment.CommentType.JAVADOC) {
                comment.delete();
            }
        }
    }

    private String methodSelector(CtMethod<?> method) {
        StringBuilder sb = new StringBuilder(method.getSimpleName()).append('(');
        for (var param : method.getParameters()) {
            sb.append(toJvmDescriptor(param.getType()));
        }
        sb.append(')').append(toJvmDescriptor(method.getType()));
        return sb.toString();
    }

    private String toJvmDescriptor(CtTypeReference<?> ref) {
        if (ref == null) return "V";
        if (ref.isArray()) {
            return "[" + toJvmDescriptor(((spoon.reflect.reference.CtArrayTypeReference<?>) ref).getComponentType());
        }
        return switch (ref.getQualifiedName()) {
            case "void"    -> "V";
            case "boolean" -> "Z";
            case "byte"    -> "B";
            case "char"    -> "C";
            case "short"   -> "S";
            case "int"     -> "I";
            case "long"    -> "J";
            case "float"   -> "F";
            case "double"  -> "D";
            default        -> "L" + ref.getQualifiedName().replace('.', '/') + ";";
        };
    }

    private void writeMirrorSource(Path mirrorRoot, String originalFqcn, String source) throws IOException {
        Path target = mirrorRoot.resolve(originalFqcn.replace('.', '/') + ".java");
        Files.createDirectories(target.getParent());
        Files.writeString(target, source);
    }

    /**
     * Used by tests / other transformers to short-circuit when a {@link CtLocalVariable}
     * declaration was deleted but the variable is still referenced.
     */
    @SuppressWarnings("unused")
    private boolean referencesUndeclaredLocals(CtBlock<?> block) {
        Set<String> declared = new LinkedHashSet<>();
        for (CtLocalVariable<?> local : block.getElements(new TypeFilter<>(CtLocalVariable.class))) {
            declared.add(local.getSimpleName());
        }
        for (var ref : block.getElements(new TypeFilter<>(spoon.reflect.code.CtVariableAccess.class))) {
            if (ref.getVariable() != null
                && ref.getVariable().getDeclaration() == null
                && !declared.contains(ref.getVariable().getSimpleName())) {
                return true;
            }
        }
        return false;
    }
}
