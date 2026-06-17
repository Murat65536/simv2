package murat.simv2.analysis;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.function.Predicate;

/**
 * Track-2 (Option C) transpiler. Reads the captured WALA SSA IR ({@code movement-ir.json}) and
 * emits standalone, Minecraft-free Java for the movement methods, driven by the {@link Routing}
 * table (M2). Starting from entry methods it transpiles the PHYSICS closure, delegating MATH to
 * {@code Vec3}/{@code MathHelperPort}, mapping player getters/setters to {@code SimPlayerState},
 * and failing on any unclassified call (coverage invariant — no silent drops).
 *
 * <p>Emission is a block DISPATCH LOOP ({@code while(true) switch(block)}) — correct for any CFG
 * (incl. loops, as back-edges become {@code $b} reassignments), with out-of-SSA done by assigning
 * phi targets on predecessor edges and exact float/double widths taken from the IR. Instance
 * methods take {@code SimPlayerState s} as their first parameter (the {@code this} receiver).
 */
public final class SimGenerator {

    /** Entry methods to transpile (target form). Their PHYSICS closure is pulled in transitively. */
    private static final List<String> ENTRIES = List.of(
        // The top of the closure — one full tick of midair/on-land physics. Everything else below
        // is pulled in transitively as its PHYSICS closure expands.
        "Lnet/minecraft/entity/LivingEntity#travelMidAir(Lnet/minecraft/util/math/Vec3d;)V",
        "Lnet/minecraft/entity/Entity#movementInputToVelocity(Lnet/minecraft/util/math/Vec3d;FF)Lnet/minecraft/util/math/Vec3d;",
        "Lnet/minecraft/entity/Entity#updateVelocity(FLnet/minecraft/util/math/Vec3d;)V",
        "Lnet/minecraft/entity/player/PlayerEntity#getOffGroundSpeed()F",
        "Lnet/minecraft/entity/LivingEntity#getMovementSpeed(F)F",
        "Lnet/minecraft/entity/LivingEntity#getEffectiveGravity()D",
        "Lnet/minecraft/entity/Entity#getFinalGravity()D");

    public static void main(String[] args) throws IOException {
        if (args.length < 2) {
            throw new IllegalArgumentException("Usage: SimGenerator <movement-ir.json> <generated-java-root>");
        }
        Path irPath = Path.of(args[0]);
        Path javaRoot = Path.of(args[1]);

        Map<String, Object> root;
        try (Reader r = Files.newBufferedReader(irPath)) {
            root = new Gson().fromJson(r, Map.class);
        }
        Map<String, Map<String, Object>> byKey = new TreeMap<>();
        for (Map<String, Object> m : listOfMaps(root.get("methods"))) {
            byKey.put(methodKey(m), m);
        }

        // Phase 1 — discover the PHYSICS closure and each method's DIRECT world dependency, without
        // emitting yet. (Emission needs the world-threading verdict, which is a fixpoint over the
        // whole closure, so it cannot be decided one method at a time.)
        Deque<String> worklist = new ArrayDeque<>(ENTRIES);
        Set<String> seenTargets = new LinkedHashSet<>();
        Map<String, Map<String, Object>> recordByCanon = new LinkedHashMap<>();
        Map<String, MethodTranspiler> transpilerByCanon = new LinkedHashMap<>();
        Map<String, Set<String>> physicsCalleeCanons = new LinkedHashMap<>();
        Set<String> worldNeeded = new LinkedHashSet<>(); // canon keys that (transitively) read world
        while (!worklist.isEmpty()) {
            String target = worklist.poll();
            if (!seenTargets.add(target)) continue;
            Map<String, Object> record = byKey.get(target);
            if (record == null) {
                // Virtual dispatch: the call's declared owner may be a supertype while the IR holds
                // the actually-reached override. Resolve by selector (name+descriptor).
                record = resolveBySelector(byKey, target);
            }
            if (record == null) {
                throw new IllegalStateException("PHYSICS target not found in IR: " + target);
            }
            String canon = methodKey(record);
            if (recordByCanon.containsKey(canon)) continue; // already analyzed (under another alias)
            recordByCanon.put(canon, record);
            MethodTranspiler t = new MethodTranspiler(record);
            t.analyze();
            transpilerByCanon.put(canon, t);
            if (t.worldDirect()) worldNeeded.add(canon);
            Set<String> calleeCanons = new LinkedHashSet<>();
            for (String pt : t.physicsCalls()) {
                Map<String, Object> crec = byKey.get(pt);
                if (crec == null) crec = resolveBySelector(byKey, pt);
                if (crec == null) throw new IllegalStateException("PHYSICS callee not found in IR: " + pt);
                calleeCanons.add(methodKey(crec));
                worklist.add(pt); // expand the closure
            }
            physicsCalleeCanons.put(canon, calleeCanons);
        }

        // Phase 2 — fixpoint: a method needs `world` if it reads the world directly OR transitively
        // calls (through the PHYSICS closure) a method that does.
        boolean changed = true;
        while (changed) {
            changed = false;
            for (Map.Entry<String, Set<String>> e : physicsCalleeCanons.entrySet()) {
                if (worldNeeded.contains(e.getKey())) continue;
                for (String callee : e.getValue()) {
                    if (worldNeeded.contains(callee)) {
                        worldNeeded.add(e.getKey());
                        changed = true;
                        break;
                    }
                }
            }
        }

        // Phase 3 — emit. `world` is threaded as the trailing parameter into world-needing methods
        // and as the trailing argument into calls to world-needing PHYSICS callees.
        Predicate<String> calleeNeedsWorld = rawTarget -> {
            Map<String, Object> crec = byKey.get(rawTarget);
            if (crec == null) crec = resolveBySelector(byKey, rawTarget);
            return crec != null && worldNeeded.contains(methodKey(crec));
        };
        Map<String, String> emitted = new LinkedHashMap<>(); // name -> source
        for (Map.Entry<String, Map<String, Object>> e : recordByCanon.entrySet()) {
            emitted.put((String) e.getValue().get("name"),
                transpilerByCanon.get(e.getKey()).emit(worldNeeded.contains(e.getKey()), calleeNeedsWorld));
        }

        StringBuilder body = new StringBuilder();
        for (String src : emitted.values()) {
            body.append(src).append('\n');
        }
        String cls = """
            package murat.simv2.sim.gen;

            // GENERATED by murat.simv2.analysis.SimGenerator from movement-ir.json — DO NOT EDIT.

            import murat.simv2.sim.Vec3;
            import murat.simv2.sim.MathHelperPort;
            import murat.simv2.sim.SimRuntime;
            import murat.simv2.sim.SimPlayerState;
            import murat.simv2.sim.WorldSnapshot;

            /** Transpiled standalone movement methods (Option C, Track 2). */
            public final class GeneratedMovement {
                private GeneratedMovement() {
                }

            %s
            }
            """.formatted(body.toString());

        Path outDir = javaRoot.resolve("murat/simv2/sim/gen");
        Files.createDirectories(outDir);
        Files.writeString(outDir.resolve("GeneratedMovement.java"), cls);
        System.out.println("SimGenerator: emitted GeneratedMovement with " + emitted.size()
            + " method(s): " + emitted.keySet() + " -> " + outDir);
    }

    /** Transpiles one method record to a dispatch-loop Java method. */
    private static final class MethodTranspiler {
        private final Map<String, Object> method;
        private final Map<Integer, Map<String, Object>> constByVn = new TreeMap<>();
        private final Map<Integer, String> typeByVn = new TreeMap<>();
        private final Map<Integer, Map<String, Object>> blockById = new TreeMap<>();
        private final boolean isStatic;
        private final int thisVn;
        private final Set<String> physicsCalls = new LinkedHashSet<>();
        private boolean worldDirect;
        private boolean selfNeedsWorld;
        private Predicate<String> calleeNeedsWorld;

        MethodTranspiler(Map<String, Object> method) {
            this.method = method;
            this.isStatic = Boolean.TRUE.equals(method.get("static"));
            List<Map<String, Object>> params = listOfMaps(method.get("params"));
            this.thisVn = isStatic ? -1 : i(params.get(0).get("vn"));
            for (Map<String, Object> c : listOfMaps(method.get("constants"))) constByVn.put(i(c.get("vn")), c);
            for (Map<String, Object> t : listOfMaps(method.get("types"))) typeByVn.put(i(t.get("vn")), (String) t.get("type"));
            for (Map<String, Object> b : listOfMaps(method.get("blocks"))) blockById.put(i(b.get("id")), b);
        }

        Set<String> physicsCalls() {
            return physicsCalls;
        }

        boolean worldDirect() {
            return worldDirect;
        }

        /**
         * Pre-emission scan: classify every call to discover the PHYSICS closure (which callees to
         * transpile) and whether this method reads the world DIRECTLY (a WORLD route whose template
         * binds the {@code world} token). Fails on any unclassified call (coverage invariant).
         */
        void analyze() {
            for (Map<String, Object> b : blockById.values()) {
                for (Map<String, Object> in : listOfMaps(b.get("insns"))) {
                    if (!"invoke".equals(in.get("op"))) continue;
                    String target = (String) in.get("target");
                    String selector = target.substring(target.indexOf('#') + 1);
                    if (selector.startsWith("<init>")) continue; // constructor, handled inline (MATH)
                    Routing.Route route = Routing.lookup(target);
                    if (route == null) {
                        throw new IllegalStateException("M2 coverage gap — unclassified call: " + target
                            + " (add a Routing entry)");
                    }
                    if (route.cat() == Routing.Cat.PHYSICS) {
                        physicsCalls.add(target);
                    } else if (route.cat() == Routing.Cat.WORLD && route.template().contains("world")) {
                        worldDirect = true;
                    }
                }
            }
        }

        String emit(boolean selfNeedsWorld, Predicate<String> calleeNeedsWorld) {
            this.selfNeedsWorld = selfNeedsWorld;
            this.calleeNeedsWorld = calleeNeedsWorld;
            String name = (String) method.get("name");
            String descriptor = (String) method.get("descriptor");
            List<Map<String, Object>> params = listOfMaps(method.get("params"));

            List<String> paramDecls = new ArrayList<>();
            Set<Integer> paramVns = new java.util.HashSet<>();
            for (Map<String, Object> p : params) {
                int vn = i(p.get("vn"));
                paramVns.add(vn);
                if (vn == thisVn) {
                    paramDecls.add("SimPlayerState s");
                } else {
                    paramDecls.add(javaType((String) p.get("type")) + " v" + vn);
                }
            }
            // World-threading: methods that (transitively) touch the world take the frozen snapshot
            // as a trailing parameter — mirrors the hand-port signature (..., WorldSnapshot world).
            if (selfNeedsWorld) {
                paramDecls.add("WorldSnapshot world");
            }
            String ret = javaType(returnTypeOf(descriptor));

            StringBuilder sb = new StringBuilder();
            sb.append("    public static ").append(ret).append(' ').append(name)
                .append('(').append(String.join(", ", paramDecls)).append(") {\n");

            Set<Integer> defs = new java.util.TreeSet<>();
            for (Map<String, Object> b : blockById.values()) {
                for (Map<String, Object> phi : listOfMaps(b.get("phis"))) defs.add(i(phi.get("def")));
                for (Map<String, Object> in : listOfMaps(b.get("insns"))) {
                    if (in.containsKey("def")) defs.add(i(in.get("def")));
                }
            }
            for (int vn : defs) {
                if (paramVns.contains(vn) || constByVn.containsKey(vn)) continue;
                String jt = javaType(typeByVn.getOrDefault(vn, "I"));
                sb.append("        ").append(jt).append(" v").append(vn).append(" = ")
                    .append(defaultValue(jt)).append(";\n");
            }

            sb.append("        int $b = ").append(entryBlockId()).append(";\n");
            sb.append("        while (true) {\n            switch ($b) {\n");
            for (Map<String, Object> b : blockById.values()) {
                if (listOfMaps(b.get("insns")).isEmpty() && listOfMaps(b.get("phis")).isEmpty()
                    && normalSucc(b).isEmpty()) {
                    continue;
                }
                sb.append("                case ").append(i(b.get("id"))).append(": {\n");
                emitBlock(b, sb);
                sb.append("                }\n");
            }
            sb.append("                default: throw new IllegalStateException(\"bad block \" + $b);\n");
            sb.append("            }\n        }\n    }\n");
            return sb.toString();
        }

        private void emitBlock(Map<String, Object> b, StringBuilder sb) {
            Map<String, Object> terminator = null;
            for (Map<String, Object> in : listOfMaps(b.get("insns"))) {
                String op = (String) in.get("op");
                if (op.equals("cbranch") || op.equals("goto") || op.equals("return")) {
                    terminator = in;
                    continue;
                }
                String stmt = emitInsn(in);
                if (stmt != null) sb.append("                    ").append(stmt).append('\n');
            }
            int from = i(b.get("id"));
            if (terminator == null) {
                List<Integer> ns = normalSucc(b);
                if (ns.size() == 1) {
                    sb.append(edgeAssign(from, ns.get(0)));
                    sb.append("                    $b = ").append(ns.get(0)).append("; break;\n");
                } else {
                    sb.append("                    break;\n");
                }
                return;
            }
            switch ((String) terminator.get("op")) {
                case "return" -> {
                    List<Integer> uses = intList(terminator.get("uses"));
                    sb.append("                    return ")
                        .append(uses.isEmpty() ? "" : operand(uses.get(0))).append(";\n");
                }
                case "goto" -> {
                    int to = targetBlock(i(terminator.get("target")));
                    sb.append(edgeAssign(from, to));
                    sb.append("                    $b = ").append(to).append("; break;\n");
                }
                case "cbranch" -> {
                    List<Integer> uses = intList(terminator.get("uses"));
                    int taken = targetBlock(i(terminator.get("target")));
                    int fall = -1;
                    for (int sc : normalSucc(b)) if (sc != taken) { fall = sc; break; }
                    String cond = booleanCond(uses.get(0), uses.get(1), (String) terminator.get("operator"));
                    if (cond == null) {
                        cond = operand(uses.get(0)) + " " + relop((String) terminator.get("operator"))
                            + " " + operand(uses.get(1));
                    }
                    sb.append("                    if (").append(cond).append(") {\n");
                    sb.append(indent(edgeAssign(from, taken)));
                    sb.append("                        $b = ").append(taken).append(";\n");
                    sb.append("                    } else {\n");
                    sb.append(indent(edgeAssign(from, fall)));
                    sb.append("                        $b = ").append(fall).append(";\n");
                    sb.append("                    }\n                    break;\n");
                }
                default -> throw new IllegalStateException("unexpected terminator " + terminator.get("op"));
            }
        }

        private String emitInsn(Map<String, Object> in) {
            String op = (String) in.get("op");
            switch (op) {
                case "new":
                    return null;
                case "binop": {
                    List<Integer> u = intList(in.get("uses"));
                    return assign(in, operand(u.get(0)) + " " + arith((String) in.get("operator")) + " " + operand(u.get(1)));
                }
                case "unop":
                    return assign(in, "-" + operand(intList(in.get("uses")).get(0)));
                case "conversion": {
                    List<Integer> u = intList(in.get("uses"));
                    return assign(in, "(" + javaType((String) in.get("to")) + ") " + operand(u.get(0)));
                }
                case "compare": {
                    List<Integer> u = intList(in.get("uses"));
                    String fn = "cmpg".equals(in.get("operator")) ? "cmpg" : "cmpl";
                    return assign(in, "SimRuntime." + fn + "(" + operand(u.get(0)) + ", " + operand(u.get(1)) + ")");
                }
                case "getfield": {
                    Map<String, Object> f = field(in);
                    Routing.Route rt = Routing.fieldLookup((String) f.get("class"), (String) f.get("name"));
                    if (rt == null) {
                        throw new IllegalStateException("M2 coverage gap — unclassified field: " + f);
                    }
                    return assign(in, applyTemplate(rt.template(), List.of(i(in.get("ref")))));
                }
                case "getstatic": {
                    Map<String, Object> f = field(in);
                    Routing.Route rt = Routing.fieldLookup((String) f.get("class"), (String) f.get("name"));
                    if (rt == null) {
                        throw new IllegalStateException("M2 coverage gap — unclassified static field: " + f);
                    }
                    return assign(in, applyTemplate(rt.template(), List.of()));
                }
                case "instanceof": {
                    // Type test on the receiver. The simulated entity is the local player, so these
                    // fold to compile-time constants (coverage-checked — an unknown type fails).
                    String checkType = (String) in.get("checkType");
                    String lit = Routing.typeTest(checkType);
                    if (lit == null) {
                        throw new IllegalStateException("M2 coverage gap — unclassified instanceof: "
                            + checkType + " (add a Routing type test)");
                    }
                    return assign(in, lit);
                }
                case "invoke":
                    return emitInvoke(in);
                default:
                    throw new IllegalStateException("unsupported op in transpile: " + op + " " + in);
            }
        }

        private String emitInvoke(Map<String, Object> in) {
            String target = (String) in.get("target");
            List<Integer> u = intList(in.get("uses"));
            String selector = target.substring(target.indexOf('#') + 1);
            if (selector.startsWith("<init>")) {
                String type = javaType(target.substring(0, target.indexOf('#')));
                List<String> argv = new ArrayList<>();
                for (int k = 1; k < u.size(); k++) argv.add(operand(u.get(k)));
                return "v" + u.get(0) + " = new " + type + "(" + String.join(", ", argv) + ");";
            }
            Routing.Route route = Routing.lookup(target);
            if (route == null) {
                throw new IllegalStateException("M2 coverage gap — unclassified call: " + target
                    + " (add a Routing entry)");
            }
            return switch (route.cat()) {
                case PHYSICS -> {
                    physicsCalls.add(target);
                    String calleeName = selector.substring(0, selector.indexOf('('));
                    List<String> argv = new ArrayList<>();
                    for (int useVn : u) argv.add(operand(useVn));
                    if (calleeNeedsWorld != null && calleeNeedsWorld.test(target)) {
                        argv.add("world"); // callee threads the snapshot too
                    }
                    String call = calleeName + "(" + String.join(", ", argv) + ")";
                    yield in.containsKey("def") ? "v" + i(in.get("def")) + " = " + call + ";" : call + ";";
                }
                case MATH, STATE_READ, WORLD -> {
                    String expr = applyTemplate(route.template(), u);
                    yield in.containsKey("def") ? "v" + i(in.get("def")) + " = " + expr + ";" : expr + ";";
                }
                case STATE_WRITE -> applyTemplate(route.template(), u) + ";";
                case PRUNE -> {
                    // value-returning prune -> substitute the routed constant; void prune -> drop.
                    if (in.containsKey("def")) {
                        yield "v" + i(in.get("def")) + " = " + route.template() + ";";
                    }
                    yield null;
                }
            };
        }

        private String applyTemplate(String template, List<Integer> uses) {
            String out = template;
            for (int k = uses.size() - 1; k >= 0; k--) {
                out = out.replace("$" + k, operand(uses.get(k)));
            }
            return out;
        }

        private String edgeAssign(int from, int to) {
            Map<String, Object> toBlock = blockById.get(to);
            if (toBlock == null) return "";
            List<Map<String, Object>> phis = listOfMaps(toBlock.get("phis"));
            if (phis.isEmpty()) return "";
            int idx = intList(toBlock.get("pred")).indexOf(from);
            StringBuilder sb = new StringBuilder();
            for (Map<String, Object> phi : phis) {
                sb.append("                    v").append(i(phi.get("def"))).append(" = ")
                    .append(operand(intList(phi.get("uses")).get(idx))).append(";\n");
            }
            return sb.toString();
        }

        private String assign(Map<String, Object> in, String expr) {
            return "v" + i(in.get("def")) + " = " + expr + ";";
        }

        private String operand(int vn) {
            if (vn == thisVn) return "s";
            Map<String, Object> c = constByVn.get(vn);
            if (c == null) return "v" + vn;
            return literal((String) c.get("kind"), c.get("value"));
        }

        /**
         * Boolean-vs-int-0/1 comparison: WALA represents Z as int, so {@code if (flying == 0)} must
         * become boolean logic ({@code !flying}). Returns null when this isn't that case.
         */
        private String booleanCond(int a, int b, String op) {
            Map<String, Object> cb = constByVn.get(b);
            if (!"Z".equals(typeByVn.get(a)) || cb == null || !"int".equals(cb.get("kind"))) {
                return null;
            }
            int bv = (int) Math.rint(((Number) cb.get("value")).doubleValue());
            String aExpr = operand(a);
            return switch (op) {
                case "eq" -> bv == 0 ? "!" + aExpr : aExpr;
                case "ne" -> bv == 0 ? aExpr : "!" + aExpr;
                default -> null;
            };
        }

        private String literal(String kind, Object value) {
            return switch (kind) {
                case "int" -> Integer.toString((int) Math.rint(((Number) value).doubleValue()));
                case "long" -> ((Number) value).longValue() + "L";
                case "boolean" -> Boolean.toString(Boolean.TRUE.equals(value));
                case "double" -> token(value, "Double", () -> Double.toString(((Number) value).doubleValue()));
                case "float" -> token(value, "Float", () -> ((Number) value).doubleValue() + "f");
                case "null" -> "null";
                default -> throw new IllegalStateException("unhandled const kind " + kind);
            };
        }

        private String token(Object value, String box, java.util.function.Supplier<String> normal) {
            if (value instanceof String s) {
                return switch (s) {
                    case "NaN" -> box + ".NaN";
                    case "Infinity" -> box + ".POSITIVE_INFINITY";
                    case "-Infinity" -> box + ".NEGATIVE_INFINITY";
                    default -> throw new IllegalStateException("bad float/double token " + s);
                };
            }
            return normal.get();
        }

        private int entryBlockId() {
            for (Map<String, Object> b : blockById.values()) {
                if (Boolean.TRUE.equals(b.get("entry"))) return i(b.get("id"));
            }
            return blockById.keySet().iterator().next();
        }

        private List<Integer> normalSucc(Map<String, Object> b) {
            List<Integer> exc = intList(b.get("excSucc"));
            List<Integer> out = new ArrayList<>();
            for (int sc : intList(b.get("succ"))) if (!exc.contains(sc)) out.add(sc);
            return out;
        }

        private int targetBlock(int targetIindex) {
            for (Map<String, Object> b : blockById.values()) {
                if (i(b.get("firstInsn")) == targetIindex) return i(b.get("id"));
            }
            throw new IllegalStateException("no block with firstInsn " + targetIindex);
        }

        @SuppressWarnings("unchecked")
        private Map<String, Object> field(Map<String, Object> in) {
            return (Map<String, Object>) in.get("field");
        }
    }

    // --- shared helpers ---

    private static String methodKey(Map<String, Object> record) {
        String internal = ((String) record.get("declaringClass")).replace('.', '/');
        return "L" + internal + "#" + record.get("name") + record.get("descriptor");
    }

    /** Resolve a call target to the unique IR method with the same selector (name+descriptor). */
    private static Map<String, Object> resolveBySelector(Map<String, Map<String, Object>> byKey, String target) {
        String selector = target.substring(target.indexOf('#') + 1);
        Map<String, Object> found = null;
        for (Map<String, Object> m : byKey.values()) {
            if (selector.equals(m.get("name") + "" + m.get("descriptor"))) {
                if (found != null) {
                    throw new IllegalStateException("ambiguous virtual dispatch for selector " + selector
                        + " (multiple overrides reached) — receiver-type resolution needed");
                }
                found = m;
            }
        }
        return found;
    }

    private static String returnTypeOf(String descriptor) {
        return descriptor.substring(descriptor.indexOf(')') + 1);
    }

    private static String javaType(String wala) {
        return switch (wala) {
            case "D" -> "double";
            case "F" -> "float";
            case "I" -> "int";
            case "Z" -> "boolean";
            case "J" -> "long";
            case "B" -> "byte";
            case "S" -> "short";
            case "C" -> "char";
            case "V" -> "void";
            case "Lnet/minecraft/util/math/Vec3d", "Lnet/minecraft/util/math/Vec3d;" -> "Vec3";
            // Field-chain intermediate (this.abilities.*): the player state stands in for it; the
            // local is assigned `s` and only ever used as a ref that STATE field routes ignore.
            case "Lnet/minecraft/entity/player/PlayerAbilities", "Lnet/minecraft/entity/player/PlayerAbilities;" -> "SimPlayerState";
            default -> {
                // Unmapped REFERENCE types are only valid for PRUNE-substituted / unused locals
                // (e.g. a RegistryEntry arg to a pruned hasStatusEffect). Degrade to Object — any
                // real use of such a value would then fail to compile, so this isn't a silent error.
                if (wala.startsWith("L") || wala.startsWith("[")) {
                    yield "Object";
                }
                throw new IllegalStateException("unmapped primitive type " + wala);
            }
        };
    }

    private static String defaultValue(String javaType) {
        return switch (javaType) {
            case "double" -> "0.0";
            case "float" -> "0.0f";
            case "int", "byte", "short", "char" -> "0";
            case "long" -> "0L";
            case "boolean" -> "false";
            default -> "null";
        };
    }

    private static String arith(String op) {
        return switch (op) {
            case "add" -> "+";
            case "sub" -> "-";
            case "mul" -> "*";
            case "div" -> "/";
            case "rem" -> "%";
            default -> throw new IllegalStateException("unhandled binop " + op);
        };
    }

    private static String relop(String op) {
        return switch (op) {
            case "eq" -> "==";
            case "ne" -> "!=";
            case "lt" -> "<";
            case "ge" -> ">=";
            case "gt" -> ">";
            case "le" -> "<=";
            default -> throw new IllegalStateException("unhandled relop " + op);
        };
    }

    private static String indent(String block) {
        if (block.isEmpty()) return "";
        StringBuilder sb = new StringBuilder();
        for (String line : block.split("\n", -1)) {
            if (!line.isEmpty()) sb.append("    ").append(line).append('\n');
        }
        return sb.toString();
    }

    @SuppressWarnings("unchecked")
    private static List<Map<String, Object>> listOfMaps(Object o) {
        return o == null ? List.of() : (List<Map<String, Object>>) o;
    }

    private static List<Integer> intList(Object o) {
        List<Integer> out = new ArrayList<>();
        if (o instanceof List<?> l) for (Object e : l) out.add(i(e));
        return out;
    }

    private static int i(Object o) {
        return (int) Math.rint(((Number) o).doubleValue());
    }

    private SimGenerator() {
    }
}
