
---

## 1. Why study real-world PLs?

### Design criteria (be ready to define + give examples)

- **Writability** — how naturally a problem maps to code. Contributing factors: _simplicity, expressiveness, orthogonality, definiteness_.
- **Readability** — major factor for maintainability; closely tied to writability.
- **Reliability** — easier to reason about correctness when both writability and readability are good. Static checks help; rigorous semantics help.
- Plus two practical goals: _efficient translation_ and _efficient execution_.

**Orthogonality** has three flavors worth memorizing:

- _Combination orthogonality_ — any feature combines with any other (e.g., any kind of declaration with any kind of type).
- _Sort orthogonality_ — wherever one member of a sort is allowed, any member should be (e.g., structs containing arrays, structs containing structs).
- _Number orthogonality_ — wherever one is allowed, zero or more should be allowed (`int a, b, c;`).

Counterexamples to keep in mind:

- Java `Box<Integer>[]` is not allowed (breaks sort orthogonality — generic + array).
- COBOL has multiple syntaxes for the same operation (breaks simplicity).
- C++ unspecified order of evaluation in older versions (breaks definiteness).

---

## 2. Typing disciplines (Task 1.5)

these are _not_ mutually exclusive:

|Axis|Question|Options|
|---|---|---|
|When?|When are types checked?|static / dynamic / gradual|
|How strict?|Are coercions allowed?|weak / strong|
|What determines compatibility?||nominal / structural / duck / dependent|

**Examples to remember:**

- **Statically typed:** Java, C, C++ (checked at compile time)
- **Dynamically typed:** Python, JavaScript, Ruby
- **Gradually typed:** TypeScript, Raku (Perl 6) — mix static + dynamic
- **Weakly typed:** JavaScript, PHP, Perl (implicit coercions)
- **Strongly typed:** Haskell, Rust, Swift
- **Nominally typed:** Java, C++, C# (named types must match)
- **Structurally typed:** TypeScript (same shape = compatible)
- **Duck typed:** Python, JavaScript ("if it walks like a duck…")
- **Dependently typed:** Agda, Idris, Coq (types depend on values)
- **Untyped:** assembly

---

## 3. Variables, pointers, scoping, shadowing (Tasks 1.2, 1.3)

### Scoping

- **Lexical / static** (the norm): scope determined by where the declaration appears in source code.
- **Dynamic**: scope determined by _call stack at runtime_ (Perl with `local`, classic Lisp). Brittle, but easy to implement.
- **Runtime** scoping also exists.

The classic Perl-vs-JavaScript test:

```perl
our $a = 2;
sub foo { return $a * 10; }
sub bar { local $a = 3; return foo(); }
print bar();   # 30 — dynamic
```

Same structure in JavaScript prints **20** because of lexical scoping.

### Shadowing

A new declaration hides an outer one of the same name. The outer variable still exists; only the _name_ is out of scope. Java forbids shadowing local variables (compile error); JavaScript and many others allow it. Qualified names (`Main.x`) can still reach a shadowed binding.

### JavaScript hoisting

- `var` is hoisted to the top of the enclosing **function** (not block); using before init gives `undefined`, not an error.
- `let` is block-scoped; using before declaration gives a `ReferenceError`.
- This is a typical "pitfall of real-world languages" example.

### Pointers and aliasing

- **Pointer** = an object that stores a memory address. Dereferencing (`*p`, `p^`) reads the value.
- **Aliasing** = two or more variables refer to the same memory location. Pascal pointers, Fortran 95 `EQUIVALENCE`.
- **Lifetime** (storage duration): automatic (block-local), static (whole program), dynamic (heap, manual `new`/`dispose` or GC).
- **l-value vs r-value** (C terminology): l-value identifies a memory location; r-value is the value stored there.

### Other "real-world" oddities

- **Autovivification** (PHP, JavaScript, C# via `dynamic`): nested structures spring into existence on assignment.
- **Computed property names** (JavaScript: `{['field' + n]: ...}`).
- **Symbols** (JavaScript): non-string property keys, guaranteed unique.
- **Implicit variables** (Scala `implicit val`): context-dependent argument injection.
- **Closures**, **hoisting**, **namespaces and qualified names** — recurring vocabulary.

---

## 4. Rust: ownership and borrowing (Task 1.4)

This is one of the highest-value exam topics — memorize the rules.

### The core rules

1. Each value has **exactly one owner**.
2. When the owner goes out of scope, the value is **dropped**.
3. **Assignment is a move** — the source is invalidated.
4. Stack-only types (`i32`, `bool`, …) are _copied_, not moved.
5. References are **immutable by default**.
6. **At most one mutable reference** to a value at a time.
7. Immutable and mutable references **cannot coexist**.
8. References must not outlive what they point to.

### Worked examples (the kind that show up on exams)

```rust
let s1 = String::from("hello");
let s2 = s1;             // move — s1 is invalid now
println!("{}", s1);      // COMPILE ERROR
```

```rust
let s1 = String::from("hello");
let s2 = &s1;            // borrow — s1 still valid
println!("{}", s1);      // OK
```

```rust
let mut s = String::from("hi");
let r1 = &mut s;
let r2 = &mut s;         // ERROR: two mutable borrows
```

```rust
let mut s = String::from("hi");
{ let r1 = &mut s; }     // r1 dies at }
let r2 = &mut s;         // OK — not simultaneous
```

### Why it matters (the punchline)

The rules eliminate **data races at compile time** — two pointers, at least one writing, simultaneously. The borrow checker statically rejects this pattern.

---

## 5. Java: subtyping, generics, type erasure (Tasks 5.1, 5.2)

### Subtyping

- Substitution principle: a value of type B can be used wherever A is expected, if `B <: A`.
- Reflexive (`T <: T`) and transitive (`A <: B`, `B <: C` ⇒ `A <: C`).
- Top type: Java `Object`, Kotlin `Any?`. Bottom: Kotlin `Nothing`.
- **Subclassing ≠ subtyping** — the Bag/Set example shows a subclass that violates the contract of its superclass (counts cap at 1), so it isn't a true subtype even though it `extends`.

### Generics & type erasure

The exam-critical idea: Java generics exist only at compile time. After compilation:

- All type parameters are removed.
- Type variables are replaced with their erasure: `Object` (no bound), the bound itself (single bound), or the **leftmost** bound (multiple bounds).
- Casts are inserted where needed.
- **Bridge methods** are synthesized to keep overriding consistent.

Example:

```java
class A<T> { abstract T id(T x); }
class B extends A<Integer> { Integer id(Integer x) { return x; }}
```

becomes

```java
class A { abstract Object id(Object x); }
class B extends A {
   Integer id(Integer x) { return x; }
   Object  id(Object x)  { return id((Integer)x); }   // bridge
}
```

### Consequences of erasure (very testable)

- All instances of a generic class share **one runtime class**: `new ArrayList<String>().getClass() == new ArrayList<Integer>().getClass()`.
- `new T()`, `T[]`, `x instanceof T` are **forbidden** — `T` doesn't exist at runtime.
- A generic class **cannot extend `Throwable`** (catch blocks couldn't distinguish).
- Two methods can collide via erasure: `class D extends C<String> { Object id(Object x){…} }` clashes with the inherited `T id(T)` from `C<T>`.
- Why `<T extends Object & Comparable<? super T>>`: forces leftmost erasure to be `Object`, not `Comparable`.

### Reifiable vs non-reifiable types

- **Reifiable** (fully known at runtime): primitives, raw types, `List<?>`, `Map<?,?>`, arrays of reifiable component types.
- **Non-reifiable**: `T`, `List<Integer>`, `List<? extends Number>`.
- `instanceof` against a non-reifiable type is always an error.

### Kotlin's escape: `inline fun <reified T>`

Inline functions inline their bytecode at every call site, so the type _is_ known there — Kotlin can write `value is T` for reified parameters. Cannot work for ordinary functions because we're still on the JVM with erasure.

---

## 6. Kotlin generics (Task 5.3)

Differences from Java to remember:

- **No wildcards** (`?`). Uses `out`/`in` instead.
- **No raw types** (`List words = ArrayList()` invalid in Kotlin).
- Type params can be inferred: `val a = mutableListOf(1, 2)`.
- Default upper bound is `Any?` (allows null). Use `<T : Any>` for non-null.
- Upper bound: `T : U`. Multiple bounds: `where T : CharSequence, T : Appendable`.
- Generic extension properties: `val <T> List<T>.penultimate: T get() = this[size - 2]`.
- The implicit lambda parameter `it`.

---

## 7. Variance (Task 5.4) — high-yield topic

Setup: let `A <: B` (A subtype of B), and `C<T>` a generic type.

|Variance|Direction|Example|Position rule|
|---|---|---|---|
|**Covariant** (`out T`)|preserved: `C<A> <: C<B>`|`List<out T>`, `Producer<out T>`|T only in **out-positions** (returns)|
|**Contravariant** (`in T`)|reversed: `C<B> <: C<A>`|`Comparator<in T>`, `Consumer<in T>`|T only in **in-positions** (parameters)|
|**Invariant**|neither|`MutableList<T>`|T anywhere|

Intuition: producers are covariant ("a list of cats can be read as a list of animals"), consumers are contravariant ("a comparator of animals can compare cats"). Mutable collections are invariant because they both produce and consume.

### Declaration-site vs use-site variance

- **Kotlin**: both. Declare variance once on the class (`interface List<out T>`), or specify at the call site (`MutableList<out T>`).
- **Java**: only use-site, via wildcards.

|Use-site|Kotlin|Java|
|---|---|---|
|covariance|`C<out T>`|`C<? extends T>`|
|contravariance|`C<in T>`|`C<? super T>`|
|invariance|`C<T>`|`C<T>`|

### Mixing — `Function1<in P, out R>`

A one-arg function is contravariant in its parameter and covariant in its return type. So `(Animal) -> Int` is a subtype of `(Cat) -> Number`.

### Star projections

Kotlin's `MutableList<*>` and Java's `List<?>` differ slightly: with `*` you can still safely _remove_ elements (the element type is still a fixed unknown), and you can still call methods that take/return `Any?`. With `?` in Java you can't add (except null) — same logic.

### Out-/in-projections (use-site)

If a class is invariant but you want covariant access at one call site, use a **projection**: `val machine: VendingMachine<out Snack> = candyBarMachine`. Methods that put `T` in an _in_-position become unusable (parameter type becomes `Nothing`); methods returning `T` still work.

---

## 8. Real-world languages: who owns them? (Task 4.1)

PL standardization is a recurring exam topic.

### Standardization bodies

- **ISO** + **IEC** + **ISO/IEC JTC1 SC22** = the formal international track for PLs.
- **Working groups** under SC22: WG4 COBOL, WG5 Fortran, WG9 Ada, WG14 C, WG17 Prolog, WG21 C++, WG23 Vulnerabilities.
- **ANSI** (US national), **INCITS** (US ↔ ISO).
- **Ecma International** — TC39 for ECMAScript (JavaScript), TC49 for C# and Eiffel.
- **W3C, IEEE, IETF, OMG** — adjacent organizations.

### Who owns what — typical patterns

- **Inventor**: most ad-hoc languages, Python (Guido van Rossum until 2018).
- **Community**: Haskell.
- **Company**: Java (Sun → Oracle), Kotlin (JetBrains).
- **International org**: JavaScript/ECMAScript (Ecma TC39), C++ (ISO/IEC), C# (Ecma).

UiB participates in TC39 — relevant trivia if the exam asks about JavaScript governance.

---

## 9. Formal semantics (Task 4.2)

Three styles to be able to name and contrast:

|Style|What it defines|
|---|---|
|**Operational**|Meaning = how an abstract machine's state changes when running the program|
|**Denotational**|Meaning = a (partial) function from inputs to outputs|
|**Axiomatic**|Meaning = a relation between preconditions and postconditions|

For the factorial loop `y = 1; while (x != 1) { y = x * y; x = x - 1; }`:

- _Operational_: "First assign 1 to y, then test…" (procedural narrative).
- _Denotational_: "computes a partial function from states to states; final y = factorial of initial x".
- _Axiomatic_: "if `x = n` holds before, then `y = n!` holds after (if it terminates)".

### Operational substyles

- **Small-step / structural**: `⟨c₁, σ₁⟩ → ⟨c₂, σ₂⟩ → …` — one step at a time.
- **Big-step / natural**: `⟨c, σ⟩ ⇓ σ'` — entire program to its final state.

### Inference rules notation

```
   premise₁ … premiseₙ
  ───────────────────── [rule-name]
        conclusion
```

An axiom is a rule with no premises.

### Hoare logic (axiomatic)

Triples `{P} S {Q}`: precondition P, command S, postcondition Q. Key inference rules cover sequencing, if/while, and consequence.

---

## 10. WebAssembly (Task 3.1)

### Goal

A **portable binary code format** (and a matching text format) for high-performance execution on the web _and beyond_. Languages like C++, Rust compile to Wasm; the Wasm module then runs in a browser, Node.js, Deno, Wasmtime, Wasmer, WasmEdge, edge platforms, etc.

### Built-in types

`i32, i64, f32, f64, v128`. That's it — everything else is built on top.

### Module structure (sections)

A Wasm module is a self-contained execution unit consisting of these sections:

- **Type** — function signatures
- **Import** — functions provided by host
- **Function** — function indices (linking signatures to bodies)
- **Table** — indexed refs for indirect calls
- **Memory** — linear memory size/limits
- **Global** — global variables
- **Export** — public API to the host
- **Element** — table initialization
- **Code** — function bodies as bytecode
- **Data** — initial linear-memory contents

The binary always begins with magic bytes `0x00 0x61 0x73 0x6d` ("\0asm") and a 4-byte version.

### Execution model

**Stack-based** with reverse-Polish (postfix) notation. Example:

```
(1 + 2) * 3 - (4 * 5) + 6
```

becomes

```
i32.const 1
i32.const 2
i32.add
i32.const 3
i32.mul
i32.const 4
i32.const 5
i32.mul
i32.sub
i32.const 6
i32.add
```

### Functions

- Each function explicitly declares parameter and result types.
- Can return multiple values (tuples).
- **Exported** functions form the module's public API; **imported** functions come from the host.
- The **host** (browser, Node, Wasmtime, …) instantiates modules, manages memory, calls exports.

### Why this matters as a "real-world PL" topic

Wasm is the assembly of the modern web. It's how Rust and C++ run in browsers. It's the compilation target for the Oblig2 project.

---

## 11. Parameter passing (Task 1.1) — high-yield

### Modes (about _information flow_)

|Mode|Meaning|Constraints|
|---|---|---|
|`obs`|observed (inflowing)|not modified in body; argument can be any expression|
|`out`|output (outflowing)|not read before assigned; argument must be a variable|
|`upd`|updated (in + out)|argument must be a variable|

### Semantics (about _how data is passed_)

|Semantics|Aka|Mechanism|
|---|---|---|
|Reference|"by reference"|callee gets the location of the actual argument|
|Copy-in|"by value"|local var initialized with argument's value|
|Copy-out|"by result"|local var; final value copied back to argument|
|Copy-in/out|"by value-result"|initialized + copied back|

### How they map

- `obs` ⇒ copy-in semantics (or reference, _if_ the body is guaranteed not to modify).
- `upd` ⇒ copy-in/out semantics (or reference).
- `out` ⇒ copy-out semantics (or reference).

### What happens at return

- _Reference_: caller uses the store produced by the body (the change is visible through the alias).
- _Copy-in_: caller uses the body's resulting store, but the local copy is gone.
- _Copy-out_: the local var's final value is written back to the argument's location.
- _Copy-in/out_: same as copy-out, but the local was initialized first.

### Activation records

Each procedure call pushes an **activation record** (frame) on the stack containing: formal parameters, locals, return value, return address, saved registers, **dynamic link** (caller's frame), **static link** (lexically enclosing function's frame, in languages with nested first-class functions). Pop on return.

A **calling convention** specifies who pushes/pops, where the return value lives, who saves which registers — standardizing this is what allows C++ to call Pascal and vice versa.

---

## 12. Interpreters & evaluators (Tasks 2.2, 2.3) — recipe-style answers

If asked to "build an interpreter for language X in Haskell", follow the seven-step recipe from the slides:

1. Define **abstract syntax** with explicit syntactic categories.
2. Decide on an **environment / store / other model**.
3. Determine **types and the semantic domain** for each.
4. Determine **operations on the semantic domains** (lookup/update on environments, primitives on values).
5. Establish a **(semi-)formal semantics** by giving an interpretation per syntactic construct.
6. Pick the **metalanguage** and define the abstract syntax of the object language in it.
7. Implement an **interpreter function per syntactic category** — each is a mapping from a syntactic category to a semantic domain.

### Evaluator vs interpreter

An **evaluator** is an interpreter that produces a _final value_. The distinction matters for languages like the BTL expression language vs imperative BIPL.

### BIPL example skeleton (Haskell)

```haskell
data Stmt = Skip | Assignment String Expr | Seq Stmt Stmt
          | If Expr Stmt Stmt | While Expr Stmt
data Expr = IntConst Int | BoolConst Bool | VarUse String
          | Unary UOp Expr | Binary BOp Expr Expr
data Val  = VB Bool | VI Int | VU
type Environment = [(String, Val)]

evaluate :: Expr -> Environment -> Val
execute  :: Stmt -> Environment -> Environment
```

Key trick: implement `While` recursively in terms of `If`:

```haskell
execute (While c body) env =
  execute (If c (Seq body (While c body)) Skip) env
```

### Stepwise (small-step) evaluator

```haskell
isValue :: Expr -> Bool
step    :: Expr -> Expr
evaluate e | isValue e = e
           | otherwise = evaluate (step e)
```

### Type checker for BTL

The classic case: type checker rejects `IF TRUE TRUE (SUCC TRUE)` (branches differ in type) — even though some such programs can never reach the bad branch at runtime. Static checkers can both:

- **catch** real errors before execution, and
- **reject** valid programs (necessary cost of decidability).

---

## 13. Polymorphism cheat-sheet

- **Subtype polymorphism** (inclusion): `class Cat extends Animal { override … }`.
- **Parametric polymorphism**: generics — `<T> T max(T x, T y)`.
- **Ad hoc polymorphism**: function overloading — `void plus(int,int)` and `void plus(string,string)`.

---

## Quick last-day review checklist

- [ ] Three semantics styles + the factorial example for each.
- [ ] Big-step vs small-step; inference-rule notation.
- [ ] Every typing-discipline label with one example language.
- [ ] Rust: 8 ownership/borrowing rules + the data-race punchline.
- [ ] Lexical vs dynamic scoping + the JS/Perl test program.
- [ ] `var` hoisting vs `let` block scope.
- [ ] Java erasure: erasure rules, bridge methods, why `<T extends Object & Comparable<…>>`.
- [ ] Reifiable vs non-reifiable types; Kotlin `reified` only with `inline`.
- [ ] Variance table (covariant/contravariant/invariant) with positions and example types.
- [ ] Declaration-site (Kotlin) vs use-site (Java wildcards) variance.
- [ ] Parameter passing **modes** (obs/out/upd) vs **semantics** (ref/copy-in/out/in-out).
- [ ] Wasm: text vs binary, stack-based postfix, four numeric types, module sections.
- [ ] PL standardization: ISO/IEC JTC1 SC22 working groups, Ecma TC39/TC49.
- [ ] Seven-step interpreter recipe.