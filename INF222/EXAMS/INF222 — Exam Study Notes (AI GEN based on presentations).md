Organized by exam task. Read top-to-bottom; each section gives you what you need to score the points listed.

---

## TASK 1.1 — Parameter passing modes (11 pts, 20 min)

### Two views: "modes" vs. "semantics"

**Modes = information flow** (what does the parameter convey?):

- `obs` 👀 — _observed_ / inflowing. Should not be modified inside. May be an expression. Local copy holds the value at call.
- `out` 📤 — _output_ / outflowing. Should not be read before init. Actual argument must be a variable.
- `upd` 🔁 — _updated_ / both inflowing and outflowing. Actual argument must be a variable.

**Semantics = how data is passed** (what most languages actually expose):

- **Reference semantics** ("by reference") — formal parameter is an _alias_ for the actual argument's memory location.
- **Copy semantics** — formal parameter is a fresh local variable in the callee's activation record.
    - **Copy-in** ("by value") — local var initialized from argument's value.
    - **Copy-out** ("by result") — local var uninitialized; final value copied back to actual argument at return.
    - **Copy-in/out** ("by value-result") — initialized from argument; copied back at return.

### Modes → semantics mapping

|Information flow|Natural semantics|Alternative ("ref")|
|---|---|---|
|obs 👀|copy-in ("by value")|by reference (only if callee guaranteed not to modify the location)|
|upd 🔁|copy-in/out ("by value-result")|by reference|
|out 📤|copy-out ("by result")|by reference|

### What actually happens in each mode (memorize this — it's exam gold)

- **By value (copy-in):** new local var added to callee state, initialized with actual argument's value. At return: store from body is used.
- **By result (copy-out):** new local var added to callee state, _uninitialized_ (or default). At return: local var's final value is _copied to the location of the actual argument variable_.
- **By value-result (copy-in/out):** new local var added to callee state, initialized with actual argument's value. At return: local var's final value is _copied to the location of the actual argument variable_.
- **By reference:** a _new variable with the parameter's name_ is added to the callee environment, _but its location is the location of the actual argument variable_. Procedure can directly modify caller's variable. At return: caller's state uses the store from executing the body.

### Other modes you should recognize

- **By name** — Algol-60-style; argument is re-evaluated every time the parameter is mentioned.
- **By need** — call-by-name + memoization (Haskell's lazy evaluation).
- **By macro expansion** — textual substitution.
- **Move** (Rust) — transfers ownership.
- **Borrow** (Rust) — temporary reference without ownership transfer.

### Sample code patterns to recognize

```
// by value
void add(int a, int b) { a += b; }   // changes to a, b invisible to caller

// by result (C# uses "out")
void add(int a, int b, byresult int c) { c = a + b; }

// by value-result
void add(int a, byvalueresult int b) { b += a; }

// by reference  (alias for caller's variable)
void add(int a, byreference int b) { b += a; }
```

### Activation records (used in tracing exam questions)

Each procedure call pushes a **frame** containing: formal parameters, local variables, return value, return address, temporaries, saved registers, **dynamic link** (pointer to caller's frame, a.k.a. control link), **static link** (pointer to lexical-enclosing frame, only if first-class functions are supported).

Calling convention = the rules for frame layout and parameter passing (where args go, who cleans the stack, where return value is, etc.).

---

## TASK 1.2 + 1.3 — Variables / pointers / scoping / shadowing (10.5 pts, 20 min)

### Core vocabulary

- **Variable**: a name bound to a value; the binding can change.
- **Constant**: a name permanently bound to a value.
- **Environment** (a.k.a. **symbol table**): map from variable names to _locations in the store_.
- **Store** (≈ memory): array of values indexed by location. Used like a stack.
- **Binding**: pair (variable name, value) or (variable name, location).

### Environment API (used to define semantics formally)

```
lookup(x, env)          -- returns binding of x in env (undefined if absent)
isDefined(x, env)       -- true iff x is bound in env
declare(x, v, env)      -- returns new env where x is bound to v (overrides)
newEnv()                -- empty environment
```

Key axioms:

- `lookup(x, declare(x, v, e)) ⟺ v`
- `lookup(x₁, declare(x₂, v, e)) ⟺ lookup(x₁, e)` when `x₁ ≠ x₂`
- `isDefined(x, newEnv()) ⟺ false`

### Scoping kinds

- **Static / lexical** — scope determined by program text (the _norm_). The variable binding is determined by the lexical structure of the program.
- **Dynamic** — variable refers to its most recent declaration in the _execution path_ (most recent activation record). Examples: LISP, Perl. Brittle; rarely used today.
- **Runtime** — bindings determined entirely at runtime.
- **Block / local / global** — refers to the _region_ of source code where the binding is visible.

### Lexical scope rule (Java/JS/most)

The scope of a declaration extends from immediately after the variable's introduction to the end of the enclosing block (or to the nearest scoping container).

### Shadowing

Declaring a new variable with the same name as an in-scope variable **shadows** the outer one. The outer variable still exists and is active — it is only inaccessible by simple name. Sometimes accessible via qualified names (e.g. `Main.x`, `Example.this.f(0)`).

- Java _forbids_ shadowing of local variables in nested blocks (compile error).
- Java _allows_ shadowing of fields by parameters/locals (`this.x` accesses the field).
- JavaScript with `let` shadows freely per block.

### Hoisting (JavaScript-specific)

`var` declarations are _hoisted_ to the top of the enclosing **function** (no block scope). The variable is "declared" everywhere in the function but its value is `undefined` until the line where it's assigned. `let` is block-scoped and accessing it before the declaration throws `ReferenceError` ("temporal dead zone").

### `global` and `nonlocal` in Python

```python
def f():
    x = 1
    def g():
        nonlocal x   # refers to enclosing function's x (NOT global)
        x = 2
    g()
    return x         # returns 2
```

- `global x` — refers to module-level `x`.
- `nonlocal x` — refers to _immediately enclosing_ function's `x`. Doesn't reach globals.

### Pointers (Pascal terminology)

```pascal
var n: integer;
var p: ^integer;        { pointer to integer }
n := 42;
p := @n;                { p now holds address of n }
writeln(p^);            { dereference: get value at address }
p^ := 100;              { write through pointer; n is now 100 }
new(p);                 { allocate new memory }
dispose(p);             { deallocate }
```

C terminology:

- **l-value**: the memory location identifying an object (can appear on either side of `=`).
- **r-value**: the data value stored at an address (right-hand side only).

### Aliasing

Two or more variables refer to the same memory location. Sources: pointers, references, by-reference parameter passing, Fortran's `equivalence` statement.

### Variable lifetime / storage duration

- **Automatic** — allocated when block is entered, deallocated when block exits. _Local variables._
- **Static** — lives for the entire program. _Global variables, `static` locals in C._
- **Dynamic** — allocated/deallocated on request (`new` / `dispose`, `malloc` / `free`). _Heap._

### Memory regions

- **Code segment** — code + constants; read-only, fixed size.
- **Static area** — globals + statics.
- **Stack** — procedure activations.
- **Heap** — dynamic allocations (size unknown at compile time, lifetime not aligned with a procedure).

### Namespaces / qualified names

A namespace is a container for related identifiers (Java `package`, C# `namespace`, Python `module`). Qualified names disambiguate (`A.x` vs `B.x`).

### Closures (mentioned briefly in the slides)

A function value packaged with the lexical environment in which it was defined. Requires static link in the activation record.

---

## TASK 1.4 — Ownership / borrowing in Rust (10 pts, 10 min)

### The three rules of ownership

1. Each value has **exactly one owner**.
2. There can be **only one owner at a time**.
3. When the owner goes out of scope, the value is **dropped** (destructed).

### Move semantics (the default for heap types)

```rust
let s1 = String::from("hello");
let s2 = s1;                  // ownership MOVED from s1 to s2
println!("{}", s1);           // COMPILE ERROR — s1 no longer valid
```

- Assignment is a move.
- A moved variable cannot be used again.
- Passing to a function = move (unless it's a reference).

### "Stack-only" types (Copy)

```rust
let a = 1;
let b = a;             // COPY (because i32 is Copy)
println!("{}", a);     // OK
```

- Primitive numerics, `bool`, `char`, tuples of Copy types — these are `Copy`.
- For these, "moves" are actually copies.

### Borrowing (references)

```rust
let s1 = String::from("hello");
let s2 = &s1;                          // immutable borrow
println!("{}", s1);                    // OK — s1 still owns
```

- `&x` — immutable reference.
- `&mut x` — mutable reference.
- References indicate _borrowing_, not owning.
- When the reference goes out of scope, the value is **not dropped**; ownership returns to the caller.

### The borrowing rules (memorize)

At any given time, you can have **either**:

- any number of **immutable** references `&T`, **OR**
- exactly **one mutable** reference `&mut T`.

You **cannot** mix mutable and immutable references to the same value at the same time.

```rust
let mut s = String::from("hi");
let r1 = &s;          // OK
let r2 = &s;          // OK — multiple immutable refs allowed
let r3 = &mut s;      // ERROR — can't have mutable while immutables exist
```

```rust
let mut s = String::from("hi");
let r1 = &mut s;
let r2 = &mut s;      // ERROR — only one mutable ref at a time
```

### Why these rules? — Data race prevention at compile time

A data race needs:

1. ≥ 2 pointers to same data,
2. simultaneous,
3. at least one writes.

The borrow rules eliminate (3) when (1) and (2) hold. Rust **prevents data races at compile time**.

### Scopes and borrows

```rust
let mut s = String::from("hi");
{
    let r1 = &mut s;
}                       // r1 dropped here
let r2 = &mut s;        // OK — not simultaneous with r1
```

Dangling reference detection:

```rust
let y: &i32;
{
    let x = 5;
    y = &x;
}
println!("{}", y);     // ERROR — x doesn't live long enough
```

### Mutability of references

- References are **immutable by default** (`&T`).
- Must explicitly write `&mut T` for mutable.
- `let r = &x` borrows immutably; `let r = &mut x` requires `let mut x = ...`.

### Summary cheat-sheet

|Operation|Effect|
|---|---|
|`let y = x`|move (or copy if Copy)|
|`let y = &x`|immutable borrow|
|`let y = &mut x`|mutable borrow (requires `let mut x`)|
|Passing `x` to a function|move (unless Copy or you pass `&x`)|
|Passing `&x` to a function|immutable borrow|
|Passing `&mut x`|mutable borrow (function gets to mutate)|

---

## TASK 1.5 — Typing disciplines (7.5 pts, 10 min)

### The three orthogonal axes

1. **When are types checked?** — static / dynamic / gradual
2. **How strictly are types enforced?** — strong / weak
3. **How is type compatibility determined?** — nominal / structural / duck

### When types are checked

- **Statically typed**: types known at compile time; checking happens during compilation. Examples: **Java, C, C++, Haskell, Rust, Kotlin, Swift**.
- **Dynamically typed**: types determined at runtime; checking happens during execution. Examples: **Python, JavaScript, Ruby**.
- **Gradually typed**: mixes static and dynamic. Examples: **TypeScript, Raku (Perl 6)**.
- **Untyped**: no type system at all. Example: **assembly language**.

### How strictly types are enforced

- **Strongly typed**: strict rules; operations only on compatible types; little/no implicit coercion. Examples: **Haskell, Rust, Swift, Python**.
- **Weakly typed**: implicit coercion across types is permitted; can lead to surprising behavior. Examples: **JavaScript, PHP, Perl, C**.

JavaScript example showing weak typing:

```js
"Hello" + 123        // "Hello123" — number coerced to string
"Hello" + [1, 2, 3]  // "Hello1,2,3"
```

### How type compatibility is determined

- **Nominally typed**: two types are compatible only if explicitly declared/named the same. Examples: **Java, C++, C#, Kotlin**.
- **Structurally typed**: two types are compatible if they have the same _structure_ (fields, methods), regardless of name. Example: **TypeScript**.
- **Duck typed**: an object's suitability is judged by whether it supports the required methods/behaviour, not by its declared type. Examples: **Python, JavaScript** ("if it walks like a duck and quacks like a duck…").

### Special: dependent typing

A type can depend on a _value_. Example: "an array of exactly 10 elements" is its own type. Languages: **Agda, Idris, Coq**.

```idris
-- Vect is parameterized by a Nat (its length)
data Vect : Nat -> Type -> Type where
    Nil : Vect Z a
    (::) : a -> Vect n a -> Vect (S n) a
```

### Pinning common languages to the disciplines

|Language|When|Strictness|Compatibility|
|---|---|---|---|
|Java|Static|Strong|Nominal|
|C|Static|Weak|Nominal|
|C++|Static|Strong*|Nominal|
|Python|Dynamic|Strong|Duck|
|JavaScript|Dynamic|Weak|Duck|
|TypeScript|Gradual|Strong|Structural|
|Haskell|Static|Strong|Nominal|
|Rust|Static|Strong|Nominal|
|Kotlin|Static|Strong|Nominal|
|WebAssembly|Static|Strong|(low-level)|

(*C++ has explicit casts that can defeat the type system; called "strong" relative to C.)

### Subtyping (foundation; full treatment in 5.1)

`S <: T` means S is a subtype of T. Liskov substitution principle: anywhere T is expected, an S works.

Subsumption rule:  
`Γ ⊢ t : U U <: T`  
`──────────────────────`  
`Γ ⊢ t : T`

Reflexivity: `T <: T`. Transitivity: `T <: U, U <: V ⇒ T <: V`.

Top type: supertype of all types (Java `Object`, Kotlin `Any?`).  
Bottom type: subtype of all types (Kotlin `Nothing`).

---

## TASK 2.1 — First sets and recursive descent parsing (8 pts, 20 min)

### What is `First(ξ)`?

Given `ξ ∈ (Σ ∪ N)*`, **`First(ξ) = { a ∈ Σ : ξ ⇒* a γ }`** — i.e., the set of terminals that any string derivable from ξ can _start_ with.

### Computing First — the recipe

Let `ξ ∈ (Σ ∪ N)*`.

1. **If `ξ = a` (a terminal)**: `First(a) = {a}`.
2. **If `ξ = A` (a nonterminal)** with rules `A → α₁ | α₂ | … | αₙ`:  
    `First(A) = First(α₁) ∪ First(α₂) ∪ … ∪ First(αₙ)`.
3. **If `ξ = v₁ v₂ … vₖ`** (a sequence of symbols):
    - If `v₁` cannot derive `ε`: `First(ξ) = First(v₁)`.
    - If `v₁ ⇒* ε`: `First(ξ) = First(v₁) ∪ First(v₂ … vₖ)`.

### Worked example (the slide example)

Grammar:

- `S → AB`
- `A → aC | bE`
- `B → cD | HF`
- `H → a`
- `F → b`

Computation:

- `First(H) = {a}`
- `First(F) = {b}`
- `First(A) = First(aC) ∪ First(bE) = {a, b}`
- `First(B) = First(cD) ∪ First(HF) = {c} ∪ First(H) = {c, a}`
- `First(S) = First(AB) = First(A) = {a, b}`

### Worked example with ε

Grammar:

- `S → aSb | BC`
- `B → bB | ε`
- `C → a`

Then:

- `First(B) = {b, ε}` (because B can derive ε)
- `First(C) = {a}`
- `First(BC)` — since `B ⇒* ε`, `First(BC) = First(B) ∪ First(C) = {b} ∪ {a} = {a, b}`
- `First(S → aSb) = {a}`
- `First(S → BC) = {a, b}`
- `First(S) = {a, b}`

### Recursive descent parsing — the algorithm

A recursive descent parser is **LL(1)**: top-down, lookahead = 1.

**Idea:** for every nonterminal `A` of the grammar, define a function `void A()`. The function looks at the lookahead token and decides which production of `A` to apply.

**Skeleton template** (for a nonterminal `A` with rules `A → ξ₁ | ξ₂ | …`, where `First(ξᵢ) = {σᵢ}`):

```
void A() {
    if (lookahead == σ₁) {
        // parse ξ₁ symbol-by-symbol
    } else if (lookahead == σ₂) {
        // parse ξ₂ symbol-by-symbol
    } else error;
}
```

For each symbol on the right-hand side:

- Terminal `a` → `match("a")` (consume token; advance position).
- Nonterminal `B` → call `B()`.

### Concrete example

Grammar: `S → aSb | ε`

```
void S() {
    if (lookahead == "a") {
        match("a");
        S();
        match("b");
    }
    // else: ε — do nothing
}
```

To parse: call `S()`; if at end, accept; else reject.

### Why First is needed

When a nonterminal has multiple productions, recursive descent picks one based on the _lookahead_ token. To know which production to pick, you compute `First` of each right-hand side. If the productions' First sets overlap, the parser cannot decide → **LL(1) conflict**.

> **Definition (informal):** A grammar is **LL(1)** if for every nonterminal, all alternative productions start with _different_ terminal symbols (their First sets are disjoint).

### LL(1) conflicts and what to do about them

**Conflict example:**

- `A → a B | a C`

Both alternatives start with `a` — conflict. Fix with **left factoring**:

- `A → a T`
- `T → B | C`

Indirect conflict:

- `H → h U | K`, `K → h V` — both can start with `h`. Same fix: left-factor.

### Left-recursive grammars

A grammar with `A → A ξ | β` cannot be parsed by recursive descent (infinite recursion). **Left-recursion elimination:**

`A → A ξ | β` becomes `A → β A'` ; `A' → ξ A' | ε`

Practical example:

- `E → E + N | N` becomes `E → N E'` ; `E' → + N E' | ε`

Left recursion can also be **indirect**: `A → B`, `B → A ξ`. Same idea, more steps.

### Where Follow comes in (mentioned briefly)

If the right-hand side of a production can derive ε, the parser also needs to know what terminals can _follow_ the nonterminal to decide if it should pick the ε-production. `Follow(A)` is the set of terminals that can appear immediately after `A` in some derivation. (Full LL(1) parsing tables use both First and Follow — INF225 territory.)

### Tools used in industry

- Parser generators / compiler compilers: **yacc, bison, ANTLR, CoCo/R, Happy (Haskell)** — given a grammar, generate a parser.
- Language workbenches: **Eclipse Xtext, Langium, Spoofax, Rascal** — generate full IDEs.
- Hand-written parsers — actually works very well for industrial languages.

---

## TASK 2.2 + 2.3 — Interpreters / evaluators in Haskell (15 pts, 30 min)

### Key terminology

- **Metaprogram**: a program whose input is a program.
- **Metalanguage**: the language the metaprogram is written in. (e.g. Haskell)
- **Object language**: the language the metaprogram processes. (e.g. BIPL)
- **Interpreter**: a metaprogram that _executes_ programs in an object language.
- **Evaluator**: an interpreter that produces a final _value_ (common term when the program has a result; e.g., for arithmetic expression languages).

### What an evaluator is, formally

> An evaluator defines a mapping from **AST → value in some semantic domain**.

### The recipe for writing an interpreter (this is the slide's general method)

1. Identify the syntactic categories of the object language (e.g., `Expr`, `Stmt`).
2. Define abstract syntax in the metalanguage (e.g., Haskell `data` types).
3. Choose a **semantic domain** (the metalanguage types your AST will evaluate to).
4. Decide what state/context each interpreter function needs (e.g., environment).
5. Define one interpreter function per syntactic category.
6. Each function pattern-matches on AST constructors and recurses on subexpressions.

### BIPL abstract syntax in Haskell (memorize the shape)

```haskell
data Stmt
    = Skip
    | Assignment String Expr
    | Seq Stmt Stmt
    | If Expr Stmt Stmt
    | While Expr Stmt
    deriving Show

data Expr
    = IntConst Int
    | BoolConst Bool
    | VarUse String
    | Unary UOp Expr
    | Binary BOp Expr Expr
    deriving Show

data UOp = Negate | Not deriving Show
data BOp = ADD | SUB | MUL | LESSTHAN | LESSTHANOREQUAL
         | EQUAL | GREATERTHANOREQUAL | GREATERTHAN | AND | OR
         deriving Show
```

### Why `Val` and not `Int` / `Bool` directly?

Different `Expr` constructors evaluate to different types (some integer, some boolean), so you need a sum type:

```haskell
data Val
    = VB Bool
    | VI Int
    | VU         -- undefined
    deriving (Eq, Show)
```

### Environment

```haskell
type Environment = [(String, Val)]    -- association list
```

Lookup is `Prelude.lookup :: Eq a => a -> [(a,b)] -> Maybe b`. Assigning a variable means **prepending** a new pair (so newer bindings shadow older ones in the list).

### The two interpreter functions

```haskell
evaluate :: Expr -> Environment -> Val      -- expressions PRODUCE a value
execute  :: Stmt -> Environment -> Environment  -- statements TRANSFORM the env
```

### Statement semantics (informal table — exam-friendly)

|Statement|What execute does|
|---|---|
|`Skip`|Returns the env unchanged.|
|`Assign x e`|Evaluate `e` in current env to value `v`; prepend `(x, v)` to env.|
|`Seq s1 s2`|Execute `s1` to get env'; execute `s2` in env'.|
|`If e s1 s2`|Evaluate `e`; if `VB True` execute `s1`, if `VB False` execute `s2`.|
|`While e s`|Equivalent to `If e (Seq s (While e s)) Skip`.|

### Implementing it

```haskell
execute :: Stmt -> Environment -> Environment

execute Skip env = env

execute (Assignment name expr) env =
    (name, evaluate expr env) : env

execute (Seq s1 s2) env =
    (execute s2 . execute s1) env

execute (If cond t f) env =
    case evaluate cond env of
        VB True  -> execute t env
        VB False -> execute f env

execute (While cond body) env =
    execute (If cond
                (Seq body (While cond body))
                Skip)
            env
```

`evaluate` recurses through `Expr`, looking up `VarUse x` in the environment and applying `BOp` / `UOp` to the recursive results.

### Crucial subtleties

- **While via If**: this is an elegant trick; `While` is _defined in terms of_ `If` and `Seq`. The "new environment" propagates because `Seq`'s implementation feeds the env from `body` into the next `While` call.
- **Undefined behaviour**: an `If` whose condition is `VI 5` (not a boolean) crashes. Real interpreters either (a) define what happens (throw an exception) or (b) use **semantic analysis / type checking** to reject ill-formed programs ahead of time.

### Type checking BTL (the simpler example used as a checkpoint)

For BTL with constructors `TRUE, FALSE, ZERO, SUCC, PRED, ISZERO, IF`:

```
τ(TRUE)        = BOOLEAN
τ(FALSE)       = BOOLEAN
τ(ZERO)        = INTEGER
τ(SUCC e)      = INTEGER if τ(e) = INTEGER else ERROR
τ(PRED e)      = INTEGER if τ(e) = INTEGER else ERROR
τ(ISZERO e)    = BOOLEAN if τ(e) = INTEGER else ERROR
τ(IF e0 e1 e2) = ERROR    if τ(e0) ≠ BOOLEAN
                 ERROR    if τ(e1) ≠ τ(e2)
                 τ(e1)    otherwise
```

If a BTL program _passes_ `typeOf`, the interpreter will not crash. (But `typeOf` may also reject _some_ valid programs — e.g. `IF TRUE TRUE (SUCC TRUE)` — because static type checking is conservative.)

### Stepwise interpreter (small-step style)

Instead of one `evaluate` that goes from term to value, write:

```haskell
isValue  :: Expr -> Bool
step     :: Expr -> Expr
evaluate :: Expr -> Expr
evaluate e | isValue e = e
evaluate e             = evaluate (step e)
```

This corresponds to **small-step / structural operational semantics**.

### Visitor pattern (Java-side equivalent — for context)

In Java, the natural OOP approach (one `evaluate()` method per AST class) spreads logic across classes. The **Visitor pattern** centralizes one piece of functionality (evaluation, pretty-printing, …) into one class:

- Every AST node class implements a generic `accept(Visitor v)` method calling `v.visit(this)`.
- A `Visitor` interface has a `visit(IntegerLiteral)`, `visit(BinaryExpr)`, … overload per AST class.
- Each concrete visitor (e.g., `EvaluatorVisitor`, `PrinterVisitor`) implements all visit methods.

In Haskell, pattern matching on `data` types makes this pattern unnecessary.

---

## TASK 3.1 — WebAssembly (9 pts, 15 min)

### What WebAssembly is

- A **portable binary code-format** with a corresponding **text format**.
- Goal: high-performance applications in the browser **and beyond** (Node.js, Deno, Wasmtime, Wasmer, WasmEdge, edge platforms, …).
- Languages compile _to_ Wasm: C, C++, Rust, Zig, Go, AssemblyScript, …
- **Strongly typed**, statically typed, low-level.

### The hosting model

- A **module** is a self-contained execution unit.
- A **host** is the execution environment that _instantiates_ a module, manages its memory, and calls its exported functions.
- Hosts: web browsers, JS runtimes (Node, Deno), standalone runtimes (Wasmtime, Wasmer, WasmEdge), cloud/edge platforms.

### Module structure (the sections, in numeric ID order)

|ID|Section|Purpose|
|---|---|---|
|0x01|Type|Function signatures|
|0x02|Import|Functions provided by the host|
|0x03|Function|Indexes assigning types to functions|
|0x04|Table|Indexed references for indirect calls|
|0x05|Memory|Memory attributes (size, limits)|
|0x06|Global|Global variables (mutable or immutable)|
|0x07|Export|Functions accessible to the host|
|0x09|Element|Table initialization|
|0x0A|Code|Function bodies as bytecode|
|0x0B|Data|Segments of linear memory|
|—|Custom|Tool-specific metadata (debug info, names…)|

The binary file also has a fixed **preamble**: magic bytes `0x00 0x61 0x73 0x6d` (= `\0asm`) followed by version `0x01 0x00 0x00 0x00`.

### Built-in types (just five)

- `i32` — 32-bit integer
- `i64` — 64-bit integer
- `f32` — 32-bit float
- `f64` — 64-bit float
- `v128` — 128-bit vector (SIMD)

### Functions

- Each function explicitly declares its parameter types and result types.
- **Functions can return multiple values** (tuples).
- **Exported functions** form the module's public API; the host can call them.
- Local variables are accessed by index: `local.get N` puts the Nth local on the stack.

### Stack-based execution + reverse Polish (postfix)

Wasm uses a **stack machine**. Instructions push and pop. The text format is essentially **reverse Łukasiewicz (postfix) notation**:

`(1 + 2) * 3 - (4 * 5) + 6` written infix becomes `1 2 + 3 * 4 5 * - 6 +` in postfix, which maps to:

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

### Worked example: `add(a, b)`

C / Rust:

```c
extern "C" int add(int a, int b) { return a + b; }
```

```rust
pub extern "C" fn add(a: i32, b: i32) -> i32 { a + b }
```

Wasm text format:

```
(module
    (type $t0 (func (param i32 i32) (result i32)))
    (func $f0 (type $t0)
        local.get 0
        local.get 1
        i32.add)
    (export "add" (func $f0))
)
```

### Walking the binary form (showpiece exam material)

```
0x00 0x61 0x73 0x6d   magic bytes "\0asm"
0x01 0x00 0x00 0x00   version 1.0.0.0

0x01 0x07             section id 1 (Type), size 7
0x01                  number of types: 1
0x60                  function-type marker
0x02 0x7f 0x7f        2 params, both i32 (0x7f = i32)
0x01 0x7f             1 result, i32

0x03 0x02             section id 3 (Function), size 2
0x01 0x00             one function, type index 0

0x07 0x07             section id 7 (Export), size 7
0x01                  one export
0x03 0x61 0x64 0x64   name length 3, "add"
0x00 0x00             kind = function, function index 0

0x0a 0x09             section id 10 (Code), size 9
0x01                  one function body
0x07 0x00             body size 7, no local declarations
0x20 0x00             local.get 0
0x20 0x01             local.get 1
0x6a                  i32.add
0x0b                  end
```

### Useful opcodes to recognize

|Opcode|Name|Meaning|
|---|---|---|
|0x20|local.get|push local variable on stack|
|0x41|i32.const|push i32 constant|
|0x6a|i32.add|pop two i32, push sum|
|0x6b|i32.sub|pop two i32, push difference|
|0x6c|i32.mul|pop two i32, push product|
|0x0b|end|end of expression / block|

### Why Wasm is "strongly typed"

Each instruction has expected stack types and produces types; the validator checks the entire module before execution. You cannot, e.g., add an `i32` and an `f32`.

---

## TASK 4.1 — PL standardization (2 pts, 5 min)

### The main bodies and what they standardize

- **ISO** (International Organization for Standardization, 1947) — non-governmental, 164 national bodies.
- **IEC** (International Electrotechnical Commission, 1906) — electrical/electronic.
- **ISO/IEC JTC1** (1987) — joint technical committee for IT standards.
- **ISO/IEC JTC1 SC22** — Programming languages, environments, system software interfaces.
    - WG4 — COBOL, WG5 — Fortran, WG9 — Ada, **WG14 — C**, WG17 — Prolog, **WG21 — C++**, WG23 — language vulnerabilities.
- **ANSI** (American National Standards Institute) — coordinates US standards with international.
- **INCITS** — coordinates ANSI ↔ ISO/IEC.

### Ecma International (1961)

- **TC39 — ECMAScript**: ECMA-262 = ECMAScript Language Specification (= ISO/IEC 22275:2018). Also ECMA-404 / JSON.
- **TC49 — Programming Languages**: ECMA-334 = C# (= ISO/IEC 23270), ECMA-367 = Eiffel.

UiB is a TC39 member; the course mentions ongoing collaborations with Mozilla / Bloomberg / Google on JavaScript engine implementations.

### Other relevant bodies

- **ITU** (International Telecommunication Union, 1865)
- **IEEE** (Institute of Electrical and Electronics Engineers)
- **W3C** (World Wide Web Consortium)
- **IETF** (Internet Engineering Task Force)
- **OMG** (Object Management Group)
- **The Open Group** — Unix.

### Who "owns" a language

- **The inventor** — most ad hoc languages; Python (Guido van Rossum / Python Software Foundation since 2001).
- **A community** — Haskell.
- **A company** — Java (Sun → Oracle), Kotlin (JetBrains).
- **A designated international organization** — ECMAScript (Ecma TC39), C++ (ISO/IEC), C# (Ecma).

### Stakeholders

- Vendors (compiler vendors, tool developers).
- Users (software developers, software maintainers).

### Java's evolution timeline (key dates)

- 1996: Java 1.0/1.1 (Sun).
- 1998: Java 1.2 (Sun + Java Community Process).
- 2004: Java 5 (generics, enumerations).
- 2014: Java 8 LTS (lambdas).
- 2018+: Oracle / JCP, OpenJDK reference implementation.

---

## TASK 4.2 — Formal semantics (3 pts, 10 min)

### The three styles

For program `y = 1; while (x != 1) { y = x * y; x = x - 1; }`:

**Operational semantics:** describes what the program _does_ step-by-step on an abstract machine.

> "First assign 1 to y, then test whether x is 1. If it is, stop, otherwise update y to be x*y and decrement x by one. Now go back to testing…"

**Denotational semantics:** describes the program as a _(partial) mathematical function_ from inputs to outputs.

> "The program computes a partial function from states to states: the final state will equal the initial state, except that x will be 1 and y will be the factorial of the initial value of x."

**Axiomatic semantics:** describes the meaning as a _relation_ between _preconditions_ and _postconditions_.

> "If `x = n` holds before the program is executed, then `y = n!` will hold when execution terminates (if it terminates)."

### Operational semantics — the machinery

Program meaning = result of execution on an abstract machine.  
**Configurations** = `⟨control, data⟩`:

- _control_: program counter / current term.
- _data_: values of program variables.

Two main flavours:

- **Small-step / structural operational semantics**: takes configurations step-by-step.  
    `conf₁ → conf₂ → conf₃ → …`
- **Big-step / natural semantics**: relates a program directly to its meaning in one judgement.  
    `⟨control, data⟩ ⇓ data'` or `t ⇓ v`

### Inference rules — the notation

```
   A₁  …  Aₙ
  ─────────── [LABEL]
       B
```

- `A₁, …, Aₙ` are **premises** (logical statements).
- `B` is the **conclusion**.
- Rules are often labelled.
- An **axiom** is an inference rule with **no premises**.

### Sample BTL big-step rules (the typical exam thing)

For BTL with `if t1 t2 t3`, the big-step rules look like:

```
              [E-True]
true ⇓ true

              [E-False]
false ⇓ false

t1 ⇓ true    t2 ⇓ v
─────────────────────  [E-IfTrue]
if t1 t2 t3  ⇓  v

t1 ⇓ false   t3 ⇓ v
─────────────────────  [E-IfFalse]
if t1 t2 t3  ⇓  v
```

### Typing relation (also defined with inference rules)

Notation: `Γ ⊢ t : T` ("in context Γ, term t has type T").

`Γ` is the **context** / **typing environment** (maps free variables to types).

A term is **typable** / **well-typed** if there exists some T with `Γ ⊢ t : T`.

---

## TASK 5.1 — Subtyping in Java and Kotlin (5 pts, 10 min)

### The rules

- Notation: `S <: T` means S is a subtype of T.
- **Reflexivity**: `T <: T`.
- **Transitivity**: `T <: U, U <: V ⇒ T <: V`.
- **Substitution principle (Liskov)**: anywhere a value of type T is expected, a value of any subtype of T may be supplied.

### Subsumption rule

```
Γ ⊢ t : U     U <: T
─────────────────────
     Γ ⊢ t : T
```

### Top and bottom types

- **Top type**: supertype of everything.
    - Java: `Object`.
    - Kotlin: `Any?` (`Any` if you want non-nullable).
- **Bottom type**: subtype of everything.
    - Kotlin: `Nothing` (used as the type of `throw`, infinite loops, etc.). No values exist of type `Nothing`.

### Java subtyping examples

- `Integer <: Number`
- `Double <: Number`
- `ArrayList<E> <: List<E>` (one type parameter, same)
- `List<E> <: Collection<E>`
- `Collection<E> <: Iterable<E>`
- By transitivity: `List<E> <: Iterable<E>`
- Every type is a subtype of itself.
- `Object` is the supertype of everything.

### The classic generics gotcha (Java)

```java
List<Integer> ints = ...;
List<Number>  nums = ints;   // ERROR!
```

`List<Integer>` is **NOT** a subtype of `List<Number>`. Why? Because if it were, you could add a `Double` (a `Number`) to a `List<Integer>` through the `nums` reference.  
But: `Integer[]` **is** a subtype of `Number[]` in Java (this is a known design wart, "covariant arrays" — leads to `ArrayStoreException` at runtime).

### Subclassing ≠ subtyping (the Bag/Set example)

```java
class Bag {
    Bag add(int n);
    int count(int n);    // value >= 0
}
class Set extends Bag {
    Set add(int n);
    int count(int n);    // value is 0 or 1
}
```

Code that holds for any Bag:

```java
int c1 = b.count(42);
int c2 = b.add(42).add(42).count(42);
assert(c1 + 2 == c2);    // breaks if b is a Set!
```

Set extends Bag at the _class_ level, but Set is **not** a behavioural subtype of Bag (Liskov is violated).

### Polymorphism (related)

- **Subtype polymorphism** (a.k.a. inclusion polymorphism): `Animal a = new Cat();`
- **Parametric polymorphism**: `MyClass<T>` — generics.
- **Ad hoc polymorphism**: function/method overloading.

### Kotlin specifics

- All Kotlin types are _non-nullable_ by default. `Int? <: Any?` but `Int?` is **NOT** a subtype of `Int`.
- `Any?` is the absolute top; `Any` is the top of non-nullable types.
- A type parameter without a bound implicitly has bound `Any?` (i.e. nullable). Use `T : Any` to forbid nulls.

---

## TASK 5.2 — Type erasure in Java (6 pts, 5 min)

### What erasure does (the rules)

After type checking, the compiler **erases** all generic type information so the resulting bytecode is plain pre-generics Java. The rules:

1. Remove all type parameters from parameterized types (`List<Integer>` → `List`).
2. Replace any type variable with the **erasure of its bound**:
    - No explicit bound → `Object`.
    - Multiple bounds → erasure of the **leftmost** bound.
3. Type casts are inserted by the compiler where necessary so the program still type-checks at the bytecode level.
4. **Bridge methods** are generated to preserve the inheritance contract.

### Erasure examples (memorize)

- `List<Integer>` → `List`
- `List<String>` → `List`
- `List<List<String>>` → `List`
- `List<Integer>[]` → `List[]`
- `List` (raw) → `List`
- `Integer` → `Integer`
- `int` → `int`

### Bridge method example

```java
class A<T> { abstract T id(T x); }
class B extends A<Integer> {
    Integer id(Integer x) { return x; }
}
```

After erasure:

```java
class A {
    abstract Object id(Object x);
}
class B extends A {
    Integer id(Integer x) { return x; }
    // bridge — generated by the compiler
    Object id(Object x) { return id((Integer)(x)); }
}
```

### "Why did I have to write `extends Object & Comparable<? super T>`?"

```java
public static <T extends Object & Comparable<? super T>>
              T max(Collection<? extends T> coll) { … }
```

Multiple bounds are needed because `T` is replaced with the **leftmost bound's erasure**. By making the leftmost bound `Object`, the erased signature is:

```java
public static Object max(Collection coll) { … }
```

which is what we want for binary compatibility with old code.

### Implications of erasure (exam favourites)

1. **All instances of a generic class share one runtime representation:**
    
    ```java
    List<String>  a = new ArrayList<>();List<Integer> b = new ArrayList<>();a.getClass() == b.getClass()    // true!
    ```
    
2. **`instanceof T` is forbidden** when `T` is a type parameter (T was erased; it doesn't exist at runtime).
3. **`new T()` is forbidden** — you can't instantiate an erased type.
4. **Generic arrays (`new T[n]`) are forbidden** — array creation needs a reifiable type.
5. **Two methods can collide via erasure:**
    
    ```java
    class C<T> { T id(T x) {…} }class D extends C<String> {    Object id(Object x) {…}      // ERROR — same erasure as C.id}
    ```
    
6. **A class cannot directly or indirectly implement two interfaces with the same erasure:**
    
    ```java
    class B implements I<Integer> {…}class C extends B implements I<String> {…}    // ERROR
    ```
    
7. **Generic classes cannot extend `Throwable`.** You couldn't tell `catch (E<Integer>)` from `catch (E<String>)` at runtime.
8. **Two different bounds for the same type parameter cannot have the same erasure.**

### Reifiable types (the opposite of erased)

A type is **reifiable** if it is _fully_ represented at runtime — erasure removes nothing useful. Reifiable types in Java:

- **Primitive types** (`int`, `boolean`, …).
- **Non-parameterized class/interface types** (`String`, `Number`, `Runnable`).
- **Parameterized types where all type arguments are unbounded wildcards** (`List<?>`, `Map<?, ?>`).
- **Raw types** (`List`, `ArrayList`, `Map`).
- **Arrays whose component type is reifiable** (`int[]`, `Number[]`, `List<?>[]`, `int[][]`).

NOT reifiable:

- **Type variables** (`T`).
- **Parameterized types with concrete arguments** (`List<Number>`, `Map<String, Integer>`).
- **Parameterized types with bounded wildcards** (`List<? extends Number>`).

> Tricky: `List<? extends Object>` is _not_ reifiable, but `List<?>` (which is _equivalent_ in meaning) **is** reifiable. Syntax matters here.

### Reification rules of thumb

- An `instanceof` test must be against a reifiable type.
- A cast should usually be to a reifiable type (uncheckeds may be allowed).
- A class extending `Throwable` must not be parameterized.
- Variadic args (`T... xs`) should be of a reifiable type.

### "Java does not really have generics"

This is the slide's explicit framing — Java's generics are a **compile-time** feature implemented via type erasure plus inserted casts. The bytecode and runtime see only raw types. (Compare with C# generics, which are reified.)

### Cast-iron guarantee

Java promises: the implicit casts inserted by the compilation of generics will _never_ fail at runtime. (Almost always true — fails only with unchecked operations, raw types, or reflection.)

---

## TASK 5.3 — Generics-related (Kotlin) (3 pts, 5 min)

This task could touch any of: reified type parameters, declaration vs. use-site variance, projections (out / in / star), or non-null type bounds. Here's the key Kotlin generics distinct from Java:

### Reified type parameters (Kotlin's escape from erasure)

In Kotlin, an **inline function** can have **`reified`** type parameters, which are _known at the call site_:

```kotlin
inline fun <reified T> isOfType(value: Any) = value is T

println(isOfType<String>("hi"))   // OK
```

- Without `inline`/`reified`, `value is T` is a compile error (T is erased).
- Why does it work? The compiler **inlines the body at every call site**, substituting the concrete type. Each invocation sees a real type, not an erased `Object`.
- Caveat: inline-reified Kotlin functions **cannot be called from Java code**.

Useful real-world example:

```kotlin
inline fun <reified T> Iterable<*>.filterIsInstance(): List<T> {
    val destination = mutableListOf<T>()
    for (element in this) {
        if (element is T) destination.add(element)
    }
    return destination
}
```

### Bounds: Kotlin vs. Java

|Bound|Kotlin|Java|
|---|---|---|
|Upper bound|`T : U`|`T extends U`|
|Lower bound|n/a (only on wildcards)|n/a (only on wildcards: `? super T`)|

```kotlin
fun <T : Number> List<T>.sum(): T
fun <T : Comparable<T>> max(first: T, second: T): T
```

Multiple bounds use a `where` clause:

```kotlin
fun <T> ensure(seq: T) where T : CharSequence, T : Appendable { ... }
```

### Non-null type parameters

Without an explicit bound, type parameters are nullable (bound `Any?`). To require non-null, use `T : Any`:

```kotlin
class Processor<T> {                  // T can be null
    fun process(value: T) { value?.hashCode() }   // safe call needed
}
class Processor<T : Any> { ... }      // T cannot be null
```

### Star projection `<*>`

`List<*>` ≈ Java's `List<?>` — list of an unknown element type.

`MutableList<*>` vs Java's `List<?>`:

|Operation|`MutableList<*>`|`List<?>`|
|---|---|---|
|retrieve elements safely|Yes|Yes|
|modify existing entries|No|No|
|add elements safely|No|No|
|remove elements safely|Yes|Yes|

### No raw types in Kotlin

```java
List words = new ArrayList();   // OK in Java (raw type)
```

Not allowed in Kotlin — you must always specify or have inferred type arguments.

### Diamond / inference

```kotlin
val words = listOf("hello", "world")        // List<String> inferred
val nums  = mutableListOf<Int>(1, 2)        // explicit
val nums2 : MutableList<Int> = mutableListOf(1, 2)  // explicit on left
```

---

## TASK 5.4 — Variance in Kotlin (10 pts, 20 min)

### Definitions

For a generic type `C<T>` and types `A`, `B` with `A <: B`:

|Variance|What it says|
|---|---|
|**Covariance**|`C<A> <: C<B>` — direction of subtyping is **preserved**|
|**Contravariance**|`C<B> <: C<A>` — direction of subtyping is **reversed**|
|**Invariance**|Neither `C<A> <: C<B>` nor `C<B> <: C<A>`|
|**Bivariance**|Both directions hold (rare; usually unsafe)|

### Without generics — the function-override intuition

When overriding a function in a subtype:

- The **return type** can become **more specific** (covariance — produced output).
- The **parameter type** can become **more general** (contravariance — consumed input).

```kotlin
interface VendingMachine {
    fun purchase(money: Coin): Snack
}
class SimpleVendingMachine : VendingMachine {
    override fun purchase(money: Coin): CandyBar = CandyBar()  // covariant return
}
```

A vending machine that accepts not just Coins but also general Money is also OK — the parameter became more general (contravariance). (Kotlin only supports this directly via properties of function type, due to JVM signature constraints.)

### Declaration-site variance (Kotlin's headline feature)

You declare variance **on the type parameter at class declaration**:

```kotlin
interface Producer<out T> { fun produce(): T }       // covariant in T
interface Consumer<in T>  { fun consume(item: T) }   // contravariant in T
class Box<T>(var value: T)                            // invariant
```

Then automatically:

- `Producer<Cat> <: Producer<Animal>` (covariance)
- `Consumer<Animal> <: Consumer<Cat>` (contravariance — flipped!)
- `Box<Cat>` is unrelated to `Box<Animal>` (invariance)

### "in" / "out" — what positions mean

- A function's **return type** is an **out-position** (T is _produced_).
- A function's **parameter type** is an **in-position** (T is _consumed_).

Rules for the compiler:

- Class declared `<out T>`: T may appear **only in out-positions**.
- Class declared `<in T>`: T may appear **only in in-positions**.
- Class declared `<T>` (invariant): T may appear in either.

Combinations:

- `in + in + ... + in` = in
- `out + ... + out` = out
- `in + out` = both → can't be variant
- `something + neutral` = something

### Why these rules guarantee soundness

- An `out` parameter only **flows out** of the object → consumers see a more specific type, but the _promise_ you made (returning at least a `T`) is still kept. Safe to widen.
- An `in` parameter only **flows in** → if you accept any `Animal`, you also accept any `Cat`. Safe to narrow.

### Standard library examples

```kotlin
interface List<out T> : Collection<T> {
    operator fun get(index: Int): T                            // out-position
    fun subList(fromIndex: Int, toIndex: Int): List<T>         // out-position
}                                                              // → covariant ✓

interface MutableList<T> : List<T>, MutableCollection<T> {
    override fun add(element: T): Boolean                      // in-position
}                                                              // → invariant (cannot be `out`)

interface Comparator<in T> {
    fun compare(e1: T, e2: T): Int                             // in-positions only
}                                                              // → contravariant ✓
```

### Constructor parameters

Constructor params are **neither in- nor out-positions** by themselves, but:

- `val foo: T` (immutable) — out-position only → preserves covariance.
- `var foo: T` (mutable) — both positions → breaks covariance.

```kotlin
class Herd<out T : Animal>(vararg animals: T) { ... }                   // OK
class Herd<out T : Animal>(private val leadAnimal: T, ...) { ... }      // OK
class Herd<out T : Animal>(private var leadAnimal: T, ...) { ... }      // ERROR (var → in-pos)
```

### Visibility and variance

The in/out-position rules **only apply to public/protected/internal** methods (i.e., visible from outside). **Private** members' parameters are in _neutral_ positions — variance constraints don't apply.

### Function type variance

`(P) -> R` is `Function1<P, R>`, which is internally:

```kotlin
interface Function1<in P, out R> {
    operator fun invoke(p: P): R
}
```

- Contravariant in P (parameter — consumed).
- Covariant in R (result — produced).

So `(Animal) -> Int` is a subtype of `(Cat) -> Number`.

### Use-site variance / projections (when declaration-site doesn't apply)

If you have an invariant class (e.g. `MutableList<T>`) but want to use it variantly at _one specific spot_, use a projection:

```kotlin
fun <T> copyData(src: MutableList<out T>, dst: MutableList<T>) {
    for (item in src) dst.add(item)
}
// or:
fun <T> copyData(src: MutableList<T>, dst: MutableList<in T>) { ... }
```

- `MutableList<out T>` — within this scope, you can only call methods that _return_ T (read elements). `add()` and `set()` become unusable (T is replaced by `Nothing` in in-positions, and `Nothing` has no values).
- `MutableList<in T>` — methods that _return_ T have their return type widened to `Any?`.

#### How projections rewrite signatures

For `VendingMachine<out Snack>`:

- Methods returning `T` keep returning `Snack`.
- Methods taking `T` as parameter get their parameter type rewritten to `Nothing` → impossible to call (no values of type `Nothing` exist).

For `VendingMachine<in CandyBar>`:

- Methods taking `T` accept anything down to `CandyBar`.
- Methods returning `T` get their return type widened to `Any?`.

### Star projections `<*>`

`VendingMachine<*>` ≡ behaviour-equivalent to `VendingMachine<out UpperBound>`, where `UpperBound` is the type parameter's declared upper bound.

In a star projection:

- Every occurrence of T in an **in-position** is replaced with `Nothing`.
- Every occurrence of T in an **out-position** is replaced with the **upper bound** of T.

Use star projections to convey "type argument is irrelevant for what I'm doing here".

### Summary table

|Kotlin syntax|Meaning|Java equivalent|
|---|---|---|
|`Producer<out T>`|declaration-site covariance|(impossible in Java)|
|`Consumer<in T>`|declaration-site contravariance|(impossible in Java)|
|`C<T>`|declaration-site invariance|`C<T>` (Java's only choice)|
|`f(x: C<out T>)`|use-site covariance|`f(C<? extends T> x)`|
|`f(x: C<in T>)`|use-site contravariance|`f(C<? super T> x)`|
|`f(x: C<T>)`|invariant at use-site|`f(C<T> x)`|
|`C<*>`|star projection|`C<?>`|

### Java's situation

Java has **no declaration-site variance** — every generic class is invariant at the declaration. Wildcards (`? extends T`, `? super T`) provide _use-site_ variance only.

The Get-and-Put principle (Java wildcards):

- `? extends T` (covariant) — you can **get** values of type T out, but cannot **put** values in.
- `? super T` (contravariant) — you can **put** values of type T in, but **getting** them out yields `Object`.
- No wildcard — when you both **get and put**.

### Wildcard subtyping rules (Java)

Given `S <: T`:

- `C<S>` is a subtype of `C<?>`
- `C<S>` is a subtype of `C<? extends T>` if `S <: T`
- `C<S>` is a subtype of `C<? super T>` if `T <: S`
- `C<? extends S>` is a subtype of `C<? extends T>` if `S <: T`
- `C<? super S>` is a subtype of `C<? super T>` if `T <: S`

Wildcard restrictions (Java):

- Not allowed at the top of class instance creation: `new ArrayList<?>()` — error.
- Not allowed in supertypes: `class MyList implements List<?>` — error.
- Not allowed in explicit type arguments: `Lists.<?>factory()` — error.
- _Nested_ wildcards are permitted: `List<List<?>>` — OK.

---

## Closing tips for the exam

- **Time budget**: 180 min total. The biggest blocks are 1.1 (20 min, 11 pts), 2.1 (20 min, 8 pts), 2.2 (25 min, 11 pts), and 5.4 (20 min, 10 pts). These four alone are 40 points and 85 minutes — get them right.
- **Quick wins**: 4.1 (5 min, 2 pts), 5.2 (5 min, 6 pts), 5.3 (5 min, 3 pts), 2.3 (5 min, 4 pts) — 19 mins for 15 points. Don't skip these.
- **Memorize the key tables**: parameter passing modes ↔ semantics; First-set computation rules; Wasm section IDs; variance-position rules; erasure rules.
- **Practice writing inference rules** with the given notation — they show up in 4.2 and possibly 1.5.
- **For Haskell interpreter questions**: be ready to (a) write a `data` declaration for the AST, (b) write the type signature of `evaluate` / `execute`, (c) implement one or two cases by pattern matching.
- **For Rust questions**: trace ownership step-by-step on each line; ask "is this a move? a copy? a borrow?". Always check the rule "1 mutable XOR many immutable".

Good luck! 🎓