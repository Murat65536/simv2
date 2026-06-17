package murat.simv2.analysis;

import com.google.gson.GsonBuilder;
import com.ibm.wala.analysis.typeInference.TypeAbstraction;
import com.ibm.wala.analysis.typeInference.TypeInference;
import com.ibm.wala.classLoader.IBytecodeMethod;
import com.ibm.wala.classLoader.IMethod;
import com.ibm.wala.ipa.callgraph.CGNode;
import com.ibm.wala.ipa.callgraph.CallGraph;
import com.ibm.wala.ssa.ISSABasicBlock;
import com.ibm.wala.ssa.IR;
import com.ibm.wala.ssa.SSAArrayLengthInstruction;
import com.ibm.wala.ssa.SSAArrayLoadInstruction;
import com.ibm.wala.ssa.SSAArrayStoreInstruction;
import com.ibm.wala.ssa.SSABinaryOpInstruction;
import com.ibm.wala.ssa.SSACFG;
import com.ibm.wala.ssa.SSACheckCastInstruction;
import com.ibm.wala.ssa.SSAComparisonInstruction;
import com.ibm.wala.ssa.SSAConditionalBranchInstruction;
import com.ibm.wala.ssa.SSAConversionInstruction;
import com.ibm.wala.ssa.SSAFieldAccessInstruction;
import com.ibm.wala.ssa.SSAGetInstruction;
import com.ibm.wala.ssa.SSAGotoInstruction;
import com.ibm.wala.ssa.SSAInstanceofInstruction;
import com.ibm.wala.ssa.SSAInstruction;
import com.ibm.wala.ssa.SSAInvokeInstruction;
import com.ibm.wala.ssa.SSALoadMetadataInstruction;
import com.ibm.wala.ssa.SSANewInstruction;
import com.ibm.wala.ssa.SSAPhiInstruction;
import com.ibm.wala.ssa.SSAPutInstruction;
import com.ibm.wala.ssa.SSAReturnInstruction;
import com.ibm.wala.ssa.SSASwitchInstruction;
import com.ibm.wala.ssa.SSAThrowInstruction;
import com.ibm.wala.ssa.SSAUnaryOpInstruction;
import com.ibm.wala.ssa.SymbolTable;
import com.ibm.wala.types.TypeReference;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * Track-2 (Option C) IR capture: serializes the WALA SSA IR of the sliced movement methods to
 * {@code movement-ir.json}, so the standalone-sim transpiler can run without rebuilding the
 * (expensive) call graph. Captured during the analysis run while the IR is live.
 *
 * <p>The serialized form is decompiler-INDEPENDENT (it is the same SSA the slice was computed on)
 * and carries the information the transpiler needs to emit bit-exact Java: per-instruction
 * operands + op-specific fields, the SSA CFG (blocks + successors + phis), the symbol table
 * (typed constants), and per-value TypeInference (the authoritative float/double widths). Any
 * instruction shape not explicitly modelled is recorded as {@code op="unsupported"} with its raw
 * text — never silently dropped — so the transpiler fails loudly rather than mis-emitting.
 */
final class IrCapture {

    private IrCapture() {
    }

    static void capture(CallGraph cg, Map<String, Map<String, Set<Integer>>> slicedMethods, Path out)
        throws IOException {
        // Match CG nodes to the sliced (dotClass -> selector) set; dedupe by signature.
        Map<String, Map<String, Object>> byKey = new TreeMap<>();
        for (CGNode node : cg) {
            IMethod m = node.getMethod();
            String dotClass = toDotClass(m.getDeclaringClass().getName().toString());
            Map<String, Set<Integer>> methods = slicedMethods.get(dotClass);
            if (methods == null) continue;
            String selector = m.getName().toString() + m.getDescriptor().toString();
            if (!methods.containsKey(selector)) continue;
            String key = dotClass + "#" + selector;
            if (byKey.containsKey(key)) continue;
            IR ir = node.getIR();
            if (ir == null) continue;
            byKey.put(key, captureMethod(ir, dotClass, m));
        }

        Map<String, Object> root = new LinkedHashMap<>();
        root.put("contract", "movement-ir/v1");
        root.put("methodCount", byKey.size());
        root.put("methods", new ArrayList<>(byKey.values()));

        String json = new GsonBuilder().setPrettyPrinting().create().toJson(root);
        Files.writeString(out, json);
        System.out.println("IrCapture: serialized SSA IR for " + byKey.size()
            + " sliced movement methods -> " + out);
    }

    private static Map<String, Object> captureMethod(IR ir, String dotClass, IMethod m) {
        Map<String, Object> out = new LinkedHashMap<>();
        out.put("declaringClass", dotClass);
        out.put("name", m.getName().toString());
        out.put("descriptor", m.getDescriptor().toString());
        out.put("static", m.isStatic());

        SymbolTable st = ir.getSymbolTable();
        TypeInference ti = safeTypeInference(ir);

        // Parameters (value numbers + inferred types).
        List<Map<String, Object>> params = new ArrayList<>();
        for (int i = 0; i < ir.getNumberOfParameters(); i++) {
            int vn = ir.getParameter(i);
            Map<String, Object> p = new LinkedHashMap<>();
            p.put("vn", vn);
            p.put("type", typeOf(ti, vn));
            params.add(p);
        }
        out.put("params", params);

        // Constants (typed) from the symbol table.
        List<Map<String, Object>> constants = new ArrayList<>();
        for (int vn = 1; vn <= st.getMaxValueNumber(); vn++) {
            if (!st.isConstant(vn)) continue;
            Map<String, Object> c = new LinkedHashMap<>();
            c.put("vn", vn);
            c.put("value", constantValue(st, vn));
            c.put("kind", constantKind(st, vn));
            c.put("type", typeOf(ti, vn));
            constants.add(c);
        }
        out.put("constants", constants);

        // Per-value inferred types (authoritative float/double/int/ref widths).
        List<Map<String, Object>> types = new ArrayList<>();
        if (ti != null) {
            for (int vn = 1; vn <= st.getMaxValueNumber(); vn++) {
                String t = typeOf(ti, vn);
                if (t != null) {
                    Map<String, Object> e = new LinkedHashMap<>();
                    e.put("vn", vn);
                    e.put("type", t);
                    types.add(e);
                }
            }
        }
        out.put("types", types);

        // Basic blocks: successors, phis, instructions.
        SSACFG cfg = ir.getControlFlowGraph();
        SSAInstruction[] insns = ir.getInstructions();
        List<Map<String, Object>> blocks = new ArrayList<>();
        for (ISSABasicBlock bb : cfg) {
            Map<String, Object> block = new LinkedHashMap<>();
            block.put("id", bb.getNumber());
            List<Integer> succ = new ArrayList<>();
            cfg.getSuccNodes(bb).forEachRemaining(s -> succ.add(s.getNumber()));
            block.put("succ", succ);
            // Predecessors in WALA order: phi operand k corresponds to the k-th predecessor, so the
            // transpiler needs this to eliminate phis (out-of-SSA) onto the right incoming edge.
            List<Integer> pred = new ArrayList<>();
            cfg.getPredNodes(bb).forEachRemaining(p -> pred.add(p.getNumber()));
            block.put("pred", pred);
            // Normal vs exceptional successors: the structurer reconstructs control flow from the
            // normal edges and prunes exceptional ones (movement physics ignores exception flow).
            List<Integer> excSucc = new ArrayList<>();
            for (ISSABasicBlock s : cfg.getExceptionalSuccessors(bb)) excSucc.add(s.getNumber());
            block.put("excSucc", excSucc);
            block.put("entry", bb.isEntryBlock());
            block.put("exit", bb.isExitBlock());

            List<Map<String, Object>> phis = new ArrayList<>();
            bb.iteratePhis().forEachRemaining(phi -> phis.add(encode(phi, ir, -1)));
            block.put("phis", phis);

            List<Map<String, Object>> blockInsns = new ArrayList<>();
            for (int i = bb.getFirstInstructionIndex(); i <= bb.getLastInstructionIndex(); i++) {
                if (i < 0 || i >= insns.length || insns[i] == null) continue;
                blockInsns.add(encode(insns[i], ir, i));
            }
            block.put("insns", blockInsns);
            blocks.add(block);
        }
        out.put("blocks", blocks);
        return out;
    }

    /** Normalize one SSA instruction to a transpiler-friendly record. */
    private static Map<String, Object> encode(SSAInstruction insn, IR ir, int iindex) {
        Map<String, Object> e = new LinkedHashMap<>();
        if (iindex >= 0) {
            e.put("i", iindex);
            int line = sourceLine(ir.getMethod(), iindex);
            if (line > 0) e.put("line", line);
        }
        if (insn.hasDef()) e.put("def", insn.getDef());

        if (insn instanceof SSAGetInstruction g) {
            e.put("op", g.isStatic() ? "getstatic" : "getfield");
            if (!g.isStatic()) e.put("ref", g.getRef());
            putField(e, g);
        } else if (insn instanceof SSAPutInstruction p) {
            e.put("op", p.isStatic() ? "putstatic" : "putfield");
            if (!p.isStatic()) e.put("ref", p.getRef());
            e.put("val", p.getVal());
            putField(e, p);
        } else if (insn instanceof SSAInvokeInstruction inv) {
            e.put("op", "invoke");
            e.put("kind", inv.getCallSite().getInvocationCode().toString());
            e.put("target", inv.getDeclaredTarget().getDeclaringClass().getName().toString()
                + "#" + inv.getDeclaredTarget().getSelector().toString());
            e.put("uses", uses(inv));
            if (inv.getException() >= 0) e.put("exc", inv.getException());
        } else if (insn instanceof SSABinaryOpInstruction b) {
            e.put("op", "binop");
            e.put("operator", b.getOperator().toString());
            e.put("uses", List.of(b.getUse(0), b.getUse(1)));
        } else if (insn instanceof SSAUnaryOpInstruction u) {
            e.put("op", "unop");
            e.put("operator", u.getOpcode().toString());
            e.put("uses", List.of(u.getUse(0)));
        } else if (insn instanceof SSAConversionInstruction c) {
            e.put("op", "conversion");
            e.put("from", c.getFromType().getName().toString());
            e.put("to", c.getToType().getName().toString());
            e.put("uses", List.of(c.getUse(0)));
        } else if (insn instanceof SSAComparisonInstruction cmp) {
            e.put("op", "compare");
            e.put("operator", cmp.getOperator().toString());
            e.put("uses", List.of(cmp.getUse(0), cmp.getUse(1)));
        } else if (insn instanceof SSAConditionalBranchInstruction cb) {
            e.put("op", "cbranch");
            e.put("operator", cb.getOperator().toString());
            e.put("uses", List.of(cb.getUse(0), cb.getUse(1)));
            e.put("target", cb.getTarget());
        } else if (insn instanceof SSAGotoInstruction go) {
            e.put("op", "goto");
            e.put("target", go.getTarget());
        } else if (insn instanceof SSAInstanceofInstruction io) {
            e.put("op", "instanceof");
            e.put("checkType", io.getCheckedType().getName().toString());
            e.put("uses", List.of(io.getUse(0)));
        } else if (insn instanceof SSAReturnInstruction r) {
            e.put("op", "return");
            if (r.getNumberOfUses() > 0) e.put("uses", List.of(r.getUse(0)));
        } else if (insn instanceof SSANewInstruction n) {
            e.put("op", "new");
            e.put("type", n.getConcreteType().getName().toString());
            e.put("uses", uses(n));
        } else if (insn instanceof SSAArrayLoadInstruction al) {
            e.put("op", "arrayload");
            e.put("uses", List.of(al.getArrayRef(), al.getIndex()));
        } else if (insn instanceof SSAArrayStoreInstruction as) {
            e.put("op", "arraystore");
            e.put("uses", List.of(as.getArrayRef(), as.getIndex(), as.getValue()));
        } else if (insn instanceof SSAArrayLengthInstruction alen) {
            e.put("op", "arraylength");
            e.put("uses", List.of(alen.getUse(0)));
        } else if (insn instanceof SSAPhiInstruction phi) {
            e.put("op", "phi");
            e.put("uses", uses(phi));
        } else if (insn instanceof SSASwitchInstruction sw) {
            e.put("op", "switch");
            e.put("uses", List.of(sw.getUse(0)));
            e.put("default", sw.getDefault());
            e.put("casesAndTargets", intList(sw.getCasesAndLabels()));
        } else if (insn instanceof SSACheckCastInstruction cc) {
            e.put("op", "checkcast");
            e.put("uses", List.of(cc.getVal()));
            List<String> ts = new ArrayList<>();
            for (TypeReference t : cc.getDeclaredResultTypes()) ts.add(t.getName().toString());
            e.put("castTypes", ts);
        } else if (insn instanceof SSAThrowInstruction th) {
            e.put("op", "throw");
            e.put("uses", List.of(th.getUse(0)));
        } else if (insn instanceof SSALoadMetadataInstruction lm) {
            e.put("op", "loadmetadata");
            e.put("token", String.valueOf(lm.getToken()));
            e.put("metaType", lm.getType().getName().toString());
        } else {
            // Never silently drop: record so the transpiler can fail loudly.
            e.put("op", "unsupported");
            e.put("class", insn.getClass().getSimpleName());
            e.put("raw", insn.toString());
        }
        return e;
    }

    private static void putField(Map<String, Object> e, SSAFieldAccessInstruction f) {
        Map<String, Object> field = new LinkedHashMap<>();
        field.put("class", f.getDeclaredField().getDeclaringClass().getName().toString());
        field.put("name", f.getDeclaredField().getName().toString());
        field.put("type", f.getDeclaredField().getFieldType().getName().toString());
        e.put("field", field);
    }

    private static List<Integer> uses(SSAInstruction insn) {
        List<Integer> u = new ArrayList<>();
        for (int i = 0; i < insn.getNumberOfUses(); i++) u.add(insn.getUse(i));
        return u;
    }

    private static List<Integer> intList(int[] a) {
        List<Integer> l = new ArrayList<>(a.length);
        for (int v : a) l.add(v);
        return l;
    }

    private static TypeInference safeTypeInference(IR ir) {
        try {
            return TypeInference.make(ir, true);
        } catch (Throwable t) {
            return null;
        }
    }

    private static String typeOf(TypeInference ti, int vn) {
        if (ti == null) return null;
        try {
            TypeAbstraction ta = ti.getType(vn);
            if (ta == null) return null;
            TypeReference tr = ta.getTypeReference();
            return tr == null ? null : tr.getName().toString();
        } catch (Throwable t) {
            return null;
        }
    }

    private static Object constantValue(SymbolTable st, int vn) {
        if (st.isIntegerConstant(vn)) return st.getIntValue(vn);
        if (st.isLongConstant(vn)) return st.getLongValue(vn);
        if (st.isFloatConstant(vn)) return finiteOrToken(st.getFloatValue(vn));
        if (st.isDoubleConstant(vn)) return finiteOrToken(st.getDoubleValue(vn));
        if (st.isBooleanConstant(vn)) return st.getConstantValue(vn);
        if (st.isStringConstant(vn)) return st.getStringValue(vn);
        if (st.isNullConstant(vn)) return null;
        return String.valueOf(st.getConstantValue(vn));
    }

    // JSON has no NaN/Infinity; emit non-finite float/double constants as string tokens the
    // transpiler decodes back (keeps the JSON standard and Python/Gson-parseable).
    private static Object finiteOrToken(double d) {
        if (Double.isNaN(d)) return "NaN";
        if (d == Double.POSITIVE_INFINITY) return "Infinity";
        if (d == Double.NEGATIVE_INFINITY) return "-Infinity";
        return d;
    }

    private static String constantKind(SymbolTable st, int vn) {
        if (st.isIntegerConstant(vn)) return "int";
        if (st.isLongConstant(vn)) return "long";
        if (st.isFloatConstant(vn)) return "float";
        if (st.isDoubleConstant(vn)) return "double";
        if (st.isBooleanConstant(vn)) return "boolean";
        if (st.isStringConstant(vn)) return "string";
        if (st.isNullConstant(vn)) return "null";
        return "other";
    }

    private static int sourceLine(IMethod method, int instructionIndex) {
        if (!(method instanceof IBytecodeMethod<?> bc)) return -1;
        try {
            return bc.getLineNumber(bc.getBytecodeIndex(instructionIndex));
        } catch (Exception ignored) {
            return -1;
        }
    }

    private static String toDotClass(String internal) {
        String s = internal.startsWith("L") ? internal.substring(1) : internal;
        if (s.endsWith(";")) s = s.substring(0, s.length() - 1);
        return s.replace('/', '.');
    }
}
