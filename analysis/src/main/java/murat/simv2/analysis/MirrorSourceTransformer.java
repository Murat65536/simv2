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
import spoon.reflect.code.CtExpression;
import spoon.reflect.code.CtIf;
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
 *   <li>Make every instance field public so simulator/runtime code can
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
        // Mirror sources are machine-read; suppress comments at the printer
        // level instead of walking each AST to delete them.
        launcher.getEnvironment().setCommentEnabled(false);

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
                String mirrorSource = transformTopLevel(topLevel, factory, emitted);
                if (mirrorSource == null) continue;
                writeMirrorSource(mirrorRoot, fqcn, mirrorSource);
                emitted.add(fqcn);
            } catch (RuntimeException ex) {
                System.err.println("Failed to transform " + fqcn + ": " + ex.getMessage());
            }
        }
    }

    private String transformTopLevel(CtType<?> topLevel, Factory factory, Set<String> emitted) {
        String originalFqcn = topLevel.getQualifiedName();
        boolean primary = primaryClasses.contains(originalFqcn);
        Map<String, Set<Integer>> methodLines = slice.getOrDefault(originalFqcn, Map.of());

        for (CtType<?> type : topLevel.getElements(new TypeFilter<>(CtType.class))) {
            // Capture nested-type FQCNs while everything is still in its
            // original net.minecraft.* package — after the package move below,
            // qualified names get the mirror prefix.
            if (type != topLevel) {
                emitted.add(type.getQualifiedName());
            }
            boolean primaryType = primaryClasses.contains(type.getQualifiedName());
            if (primaryType) {
                injectNoArgConstructorIfMissing(type, factory);
            }
            for (CtField<?> field : type.getFields()) {
                makeFieldMirrorVisible(field, primaryType);
            }
            for (CtMethod<?> method : new ArrayList<>(type.getMethods())) {
                rewriteMethodBody(method, primary, methodLines, factory);
            }
        }

        // Move into the mirror package first, then rewrite all internal type
        // references so the new self-package and external references both
        // get the mirror prefix in a single sweep.
        String originalPackage = topLevel.getPackage().getQualifiedName();
        String mirrorPackage = MIRROR_PACKAGE_PREFIX + originalPackage;
        CtPackage targetPackage = factory.Package().getOrCreate(mirrorPackage);
        topLevel.getPackage().removeType(topLevel);
        targetPackage.addType(topLevel);

        rewriteTypeReferences(topLevel);

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
        Set<CtStatement> kept = computeKeptStatements(method.getBody(), sliceLines);
        pruneToSliceLines(method.getBody(), kept);
        ensureReturnsValue(method, factory);
    }

    /**
     * One subtree walk over {@code body} collects every statement whose own
     * source line is in {@code sliceLines}, then we mark each such statement's
     * ancestor chain. The result is the set of every statement that should be
     * preserved by {@link #pruneToSliceLines}. Pruning is then O(N) by
     * identity-membership rather than the previous O(N²) per-statement
     * subtree walk.
     */
    private Set<CtStatement> computeKeptStatements(CtBlock<?> body, Set<Integer> sliceLines) {
        Set<CtStatement> kept = java.util.Collections.newSetFromMap(new java.util.IdentityHashMap<>());
        // CtStatement#equals is structural — multiple distinct nodes can compare
        // equal — so we key the kept-set on object identity.
        for (CtStatement stmt : body.getElements(new TypeFilter<>(CtStatement.class))) {
            if (stmt.getPosition() == null || !stmt.getPosition().isValidPosition()) continue;
            if (!sliceLines.contains(stmt.getPosition().getLine())) continue;
            CtElement cursor = stmt;
            while (cursor != null && cursor != body) {
                if (cursor instanceof CtStatement ancestor) {
                    if (!kept.add(ancestor)) break; // already marked, stop walk
                }
                cursor = cursor.getParent();
            }
        }
        return kept;
    }

    /** Drops statements not in {@code kept}; recurses into kept compound statements. */
    private void pruneToSliceLines(CtBlock<?> block, Set<CtStatement> kept) {
        if (block == null) return;
        for (CtStatement stmt : new ArrayList<>(block.getStatements())) {
            if (!kept.contains(stmt)) {
                try {
                    stmt.delete();
                } catch (RuntimeException ignored) {
                    // Some statements (super-call) cannot be deleted; leave them.
                }
                continue;
            }
            descendIntoChildren(stmt, kept);
        }
    }

    private void descendIntoChildren(CtStatement stmt, Set<CtStatement> kept) {
        if (stmt instanceof CtIf ctIf) {
            if (ctIf.getThenStatement() instanceof CtBlock<?> thenBlock) {
                pruneToSliceLines(thenBlock, kept);
            }
            if (ctIf.getElseStatement() instanceof CtBlock<?> elseBlock) {
                pruneToSliceLines(elseBlock, kept);
            }
        } else if (stmt instanceof CtLoop loop && loop.getBody() instanceof CtBlock<?> body) {
            pruneToSliceLines(body, kept);
        } else if (stmt instanceof CtBlock<?> nested) {
            pruneToSliceLines(nested, kept);
        } else if (stmt instanceof CtSwitch<?> ctSwitch) {
            for (CtStatement inner : ctSwitch.getCases()) {
                if (inner instanceof CtBlock<?> innerBlock) {
                    pruneToSliceLines(innerBlock, kept);
                }
            }
        }
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
        Factory factory = topLevel.getFactory();
        for (CtTypeReference<?> ref : topLevel.getElements(new TypeFilter<>(CtTypeReference.class))) {
            String qname = ref.getQualifiedName();
            if (qname == null || !qname.startsWith("net.minecraft.")) continue;
            int lastDot = qname.lastIndexOf('.');
            if (lastDot <= 0) continue;
            String oldPackage = qname.substring(0, lastDot);
            String mirrorPackage = MIRROR_PACKAGE_PREFIX + oldPackage;
            ref.setPackage(factory.Package().getOrCreate(mirrorPackage).getReference());
        }
    }

    private void makeFieldMirrorVisible(CtField<?> field, boolean primaryType) {
        if (field == null) return;
        boolean isStatic = field.hasModifier(ModifierKind.STATIC);
        // Static fields are made public only on primary classes (where the
        // runtime reaches in to read tracked-data keys / flag-bit indices).
        // Elsewhere we leave them at original visibility so package-private
        // initializers behave the same.
        if (isStatic && !primaryType) {
            return;
        }
        field.removeModifier(ModifierKind.PRIVATE);
        field.removeModifier(ModifierKind.PROTECTED);
        if (!field.hasModifier(ModifierKind.PUBLIC)) {
            field.addModifier(ModifierKind.PUBLIC);
        }
        if (!isStatic && field.getDefaultExpression() == null) {
            // Drop final on writable instance fields so sync code can assign.
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
}
