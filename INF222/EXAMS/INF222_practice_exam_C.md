# INF222 — Practice Exam C (generated from previous exam + slides)

> Suggested time: **180 minutes** · Total: **100 points**
> Same topic mix as Exam B; every task is materially different. Answer key at the end.

---

## Topic / points / time

| Task | Topic                                          | Points | Time   |
|------|------------------------------------------------|--------|--------|
| 1.1  | Parameter passing modes                        | 11     | 20 min |
| 1.2  | Variables / scoping / shadowing                | 6      | 10 min |
| 1.3  | Pointers (Pascal)                              | 4.5    | 10 min |
| 1.4  | Ownership / borrowing (Rust)                   | 10     | 10 min |
| 1.5  | Typing disciplines                             | 7.5    | 10 min |
| 2.1  | Set *First* and recursive descent parsing      | 8      | 20 min |
| 2.2  | Interpreters / evaluators (Haskell)            | 11     | 25 min |
| 2.3  | Interpreters / evaluators (Haskell)            | 4      | 5 min  |
| 3.1  | WebAssembly                                    | 9      | 15 min |
| 4.1  | PL standardization                             | 2      | 5 min  |
| 4.2  | Formal semantics                               | 3      | 10 min |
| 5.1  | Subtyping (Java / Kotlin)                      | 5      | 10 min |
| 5.2  | Type erasure (Java)                            | 6      | 5 min  |
| 5.3  | Generics-related (Kotlin)                      | 3      | 5 min  |
| 5.4  | Variance (Kotlin)                              | 10     | 20 min |
|      | **TOTAL**                                      | **100**| **180 min** |

---

# Tasks

## 1.1 Parameter passing modes (11 pts)

Consider the code snippet below in an imaginary language that supports specifying parameter passing modes. Select a mode (`obs`, `upd`, `out`) for **each** parameter.

```
interface Graph {
  method addVertex(   (obs, upd, out) Vertex v,
                      (obs, upd, out) Graph  g);

  method addEdge(     (obs, upd, out) Vertex from,
                      (obs, upd, out) Vertex to,
                      (obs, upd, out) Graph  g);

  method hasEdge(     (obs, upd, out) Vertex from,
                      (obs, upd, out) Vertex to,
                      (obs, upd, out) bool   answer,
                      (obs, upd, out) Graph  g);

  method neighbours(  (obs, upd, out) Vertex v,
                      (obs, upd, out) List<Vertex> result,
                      (obs, upd, out) Graph  g);

  method removeVertex((obs, upd, out) Vertex v,
                      (obs, upd, out) Graph  g);

  method degree(      (obs, upd, out) Vertex v,
                      (obs, upd, out) int    d,
                      (obs, upd, out) Graph  g);

  method mergeInto(   (obs, upd, out) Graph  source,
                      (obs, upd, out) Graph  target);
}
```

> Recall: `obs` = inflowing only, must not be modified · `upd` = inflowing and outflowing · `out` = outflowing only, must not be read before assigned.

---

## 1.2 Variables / scoping / shadowing (6 pts)

Consider the Python code below. For each labelled `print`, write what is output.

```python
x = 1

def outer():
    x = 10

    def inner_a():
        nonlocal x
        x = x + 100
        print(x)             # line A

    def inner_b():
        global x
        x = x + 1000
        print(x)             # line B

    inner_a()
    print(x)                 # line C
    inner_b()
    print(x)                 # line D

outer()
print(x)                     # line E
```

| line | output |
|------|--------|
| A    |        |
| B    |        |
| C    |        |
| D    |        |
| E    |        |

---

## 1.3 Pointers (Pascal) (4.5 pts)

Consider the Pascal program below. Fill in the table with what each labelled `writeln` will print.

```pascal
program PointerDemo;
var a, b : integer;
    p, q : ^integer;
begin
  a := 4;
  b := 9;
  p := @a;
  q := @b;

  p^ := q^;          { line 1: writeln(a) }
  writeln(a);
  writeln(b);        { line 2 }

  q := p;            { q now points to a }
  q^ := q^ + 1;
  writeln(p^);       { line 3 }
  writeln(b);        { line 4 }
end.
```

| line | will print |
|------|-----------|
| 1    |           |
| 2    |           |
| 3    |           |
| 4    |           |

---

## 1.4 Ownership / borrowing (Rust) (10 pts)

Fill in the blanks in the Rust code below. Each blank takes one of: `v`, `&v`, `&mut v`, `*v`, `v.clone()`, etc. — choose the smallest expression that lets the program compile and behave as the comments indicate.

```rust
fn main() {
    let mut v = vec![1, 2, 3];

    let r1 = _____;                 // (a) immutable borrow of v
    let r2 = _____;                 // (b) ANOTHER immutable borrow of v (must coexist with r1)
    println!("{:?} {:?}", r1, r2);
    // r1, r2 are no longer used after this line

    let r3 = _____;                 // (c) mutable borrow of v (now allowed)
    r3.push(4);

    let s = String::from("hello");
    let t = _____;                  // (d) make t an independent copy of s (deep copy)
    println!("s = {}, t = {}", s, t);

    let n: i32 = 42;
    let m = _____;                  // (e) duplicate n; both n and m must remain usable
    println!("n = {}, m = {}", n, m);

    let mut w = String::from("hi");
    let r = _____;                  // (f) mutable reference to w
    r.push_str("!");
    println!("{}", r);
    println!("{}", w);              // OK — r is no longer used after the line above
}
```

Mark each statement **T** (true) or **F** (false):

1. After `let s2 = s1.clone();`, both `s1` and `s2` independently own their own `String`. ☐
2. Immutable references `&T` are `Copy`, so you can pass them around freely. ☐
3. You may hold a `&mut T` and an `&T` to the same value at the same time. ☐
4. The lifetime of a reference cannot exceed the lifetime of the value it points to. ☐

---

## 1.5 Typing disciplines (7.5 pts)

Match each description (row) with the typing discipline it best fits (column). Each row maps to exactly one column.

Columns: `gradually typed` · `structurally typed` · `duck typed` · `weakly typed` · `untyped` · `dependently typed` · `(some other discipline)`

| description                                                                                                                            | discipline |
| -------------------------------------------------------------------------------------------------------------------------------------- | ---------- |
| Two record types are interchangeable if they have the same fields.                                                                     |            |
| Whether an object can be used somewhere depends only on which methods it answers to, not on which class it was declared in.            |            |
| The same source file may have parts that are checked at compile time and parts where types are deferred to runtime.                    |            |
| Numeric values and strings can be combined with `+` and the language silently converts one to the other.                               |            |
| The language has no concept of "type" — everything is just bits to be operated on.                                                     |            |
| The length of a vector is part of its type, so the compiler can reject a 5-element vector passed where a 3-element vector is expected. |            |
| (leftover row — anything else from the list)                                                                                           |            |

---

## 2.1 Set *First* and recursive descent parsing (8 pts)

Consider the following grammar:

```
S → A b | A c
A → a A | ε
```

> Recall: ε denotes the empty string.

**Subtask 1.** Compute the following:
- `First(A)` =
- `First(A b)` =
- `First(A c)` =
- `First(S)` =

**Subtask 2.** Is this grammar LL(1)? In one short sentence, explain why or why not.

**Subtask 3.** If your answer to Subtask 2 was **no**, rewrite the grammar using left factoring so that it becomes LL(1). If your answer was **yes**, skip this subtask (and explain in one sentence why no rewriting is needed).

---

## 2.2 Interpreters / evaluators (Haskell) (11 pts)

Below is an interpreter in Haskell for a small imperative language. The language now also has **unary operators**. Fill in the blanks.

```haskell
data Stmt
  = Skip
  | Assignment String Expr
  | Seq Stmt Stmt
  | If Expr Stmt Stmt
  | While Expr Stmt

data Expr
  = IntConst Int
  | BoolConst Bool
  | VarUse String
  | Unary UOp Expr
  | Binary BOp Expr Expr

data UOp = Negate | Not
data BOp = ADD | SUB | MUL | LT_ | EQ_ | AND | OR
data Val = VI Int | VB Bool | VU
type Environment = [(String, Val)]

evaluate :: Expr -> Environment -> Val
evaluate (IntConst n)  _   = _____                            -- (a)
evaluate (BoolConst b) _   = VB b
evaluate (VarUse x)    env = case _____ of                    -- (b)
                               Just v  -> v
                               Nothing -> _____               -- (c)

evaluate (Unary Negate e) env =
    case evaluate e env of
      VI n -> _____                                            -- (d)
      _    -> VU

evaluate (Unary Not e) env =
    case evaluate e env of
      VB b -> _____                                            -- (e)
      _    -> VU

evaluate (Binary SUB e1 e2) env =
    case (evaluate e1 env, evaluate e2 env) of
      (VI a, VI b) -> _____                                    -- (f)
      _            -> VU

evaluate (Binary LT_ e1 e2) env =
    case (evaluate e1 env, evaluate e2 env) of
      (VI a, VI b) -> _____                                    -- (g)
      _            -> VU

evaluate (Binary AND e1 e2) env =
    case (evaluate e1 env, evaluate e2 env) of
      (VB a, VB b) -> _____                                    -- (h)
      _            -> VU

execute :: Stmt -> Environment -> Environment
execute  Skip                env = env
execute (Assignment x e)     env = _____                       -- (i)
execute (Seq s1 s2)          env = _____                       -- (j)
execute (If c t f)           env =
    case evaluate c env of
      VB True  -> execute t env
      VB False -> _____                                        -- (k)
      _        -> env
execute (While c body)       env =
    case evaluate c env of
      VB True  -> _____                                        -- (l)
      _        -> env
```

---

## 2.3 Interpreters / evaluators (Haskell) (4 pts)

Using the interpreter from 2.2, what is the final value of every variable after executing this BIPL program from the empty initial environment?

```
{
  n = 5;
  fact = 1;
  while (!(n == 0)) {
    fact = fact * n;
    n = n - 1;
  }
}
```

- final `n`    =
- final `fact` =

---

## 3.1 WebAssembly (9 pts)

Consider the following WebAssembly module:

```
(module
  (func $max (param i32 i32) (result i32)
    local.get 0
    local.get 1
    i32.gt_s
    if (result i32)
      local.get 0
    else
      local.get 1
    end)
  (export "max" (func $max))
)
```

**a)** What is the signature of the exported function (parameter types → result type)?

**b)** If we call `max(7, 4)`, what does it return? Trace the contents of the operand stack after each instruction (write the stack top-first). Also indicate which branch (`if` or `else`) is taken.

**c)** Modify the function so it returns the **minimum** of its two arguments instead. Show the full `(func ...)` block (only the body needs to change, and only minimally).

---

## 4.1 PL standardization (2 pts)

Match each programming language with the body that owns/maintains its standard.

| Language    | Maintained by |
|-------------|---------------|
| Fortran     |               |
| Ada         |               |
| TypeScript  |               |
| Haskell     |               |

Pick from:
- `ISO/IEC JTC1 SC22 WG5`
- `ISO/IEC JTC1 SC22 WG9`
- `Microsoft (proprietary; no ISO/Ecma standard)`
- `Haskell Language Committee (Haskell 2010 report; no ISO standard)`

---

## 4.2 Formal semantics (3 pts)

Below are typing rules for a small expression language with integer constants, variables, less-than comparison, and if-then-else. Rules (1) and (2) are given. Fill in the missing premises in rules (3) and (4).

```
                                          x : T  ∈  Γ
   ──────────────       (1)              ─────────────       (2)
    Γ ⊢ n : Int                            Γ ⊢ x : T


            ___________________________
        ──────────────────────────────────       (3)
                Γ ⊢ e1 < e2 : Bool


       _______________________________________
   ─────────────────────────────────────────────       (4)
       Γ ⊢ if e1 then e2 else e3 : T
```

**Subtask.** Write a typing rule for the let-binding `let x = e1 in e2`. Use the same notation as above; don't invent your own.

---

## 5.1 Subtyping (Java / Kotlin) (5 pts)

Assume `Integer` is a subtype of `Number`. For each row, determine the relationship between type A and type B. **Pay attention to the language column.**

| Lang   | Type A                       | relation | Type B                       |
|--------|------------------------------|----------|------------------------------|
| Java   | `Integer[]`                  |          | `Number[]`                   |
| Java   | `List<Integer>`              |          | `List<Number>`               |
| Java   | `List<? extends Integer>`    |          | `List<? extends Number>`     |
| Java   | `List<? super Number>`       |          | `List<? super Integer>`      |
| Kotlin | `MutableList<Integer>`       |          | `MutableList<Number>`        |

Options: `is-SUBtype-of` · `is-SUPERtype-of` · `is-not-related-to` · `is-the-same-as`

---

## 5.2 Type erasure (Java) (6 pts)

Fill in the table — to what type does each type on the left erase?

| type that uses a generic parameter                                    | erased to |
|-----------------------------------------------------------------------|-----------|
| `Set<E>` (E unbounded)                                                |           |
| `List<? super String>`                                                |           |
| `T` where `T extends Comparable<T>`                                   |           |
| `Map<K, V>[]` where K, V are both unbounded                           |           |
| `T` where `T extends Object & Serializable`                           |           |
| `ArrayList<List<? extends Number>>`                                   |           |
OI
---

## 5.3 Generics-related (Kotlin) (3 pts)

Consider this Kotlin function:

```kotlin
fun describe(list: List<*>): String {
    val first = list.firstOrNull()
    return "size=${list.size}, first=$first"
}
```

**a)** Inside `describe`, what is the **static type** of `first`?

**b)** Are the types `List<*>` and `List<Any?>` interchangeable? Justify in one sentence.

**c)** Suppose we declared a similar function using `MutableList<*>` instead. Could we call `list.add("hi")` inside its body? Explain in one sentence in terms of in- and out-positions.

---

## 5.4 Variance (Kotlin) (10 pts)

Consider the following Kotlin interface:

```kotlin
interface Translator<L, M, N> {
    val defaultOutput: M
    fun translate(input: L): M
    fun setSource(value: N)
    fun getSource(): N
}
```

**a)** Determine the variance of each generic parameter (`covariant` / `contravariant` / `invariant`):

| param | variance |
|-------|----------|
| L     |          |
| M     |          |
| N     |          |

**b)** Rewrite the declaration using `in` / `out` where applicable (leave plain if invariant):

```kotlin
interface Translator< _____ L , _____ M , _____ N > { ... }
```

**c)** Assume `Dog` is a subtype of `Mammal`, `Mammal` is a subtype of `Animal`, and `Apple` is a subtype of `Fruit`.

What is the relation between
`Translator<Mammal, Mammal, Apple>` and `Translator<Dog, Animal, Apple>`?

Pick one: `is-equivalent-to` · `is-not-related-to` · `is-a-SUPER-type-of` · `is-a-SUB-type-of`.

**d)** What is the relation between
`Translator<Mammal, Animal, Apple>` and `Translator<Dog, Mammal, Apple>`?

Pick one of the same four options.

**e)** Briefly explain (1 sentence) why N must be invariant.

---

# ANSWER KEY (try the exam first!)

<details>

### 1.1
- `addVertex`: `v=obs, g=upd`
- `addEdge`: `from=obs, to=obs, g=upd`
- `hasEdge`: `from=obs, to=obs, answer=out, g=obs`
- `neighbours`: `v=obs, result=out, g=obs`
- `removeVertex`: `v=obs, g=upd`
- `degree`: `v=obs, d=out, g=obs`
- `mergeInto`: `source=obs, target=upd`

### 1.2
Trace:
- `outer()` is entered. A *local* `x = 10` is created (this shadows the global x).
- `inner_a()` runs. `nonlocal x` rebinds to outer's `x`. `x = 10 + 100 = 110`.
  - **A:** `110`
- After inner_a returns, outer's `x` is now `110`.
  - **C:** `110`
- `inner_b()` runs. `global x` rebinds to the module-level `x`, which is still `1`. `x = 1 + 1000 = 1001`.
  - **B:** `1001`
- inner_b only touched the *global* x; outer's local `x` is untouched (still `110`).
  - **D:** `110`
- After outer returns, the module-level x has been bumped to `1001` by inner_b.
  - **E:** `1001`

| line | output |
|------|--------|
| A    | 110    |
| B    | 1001   |
| C    | 110    |
| D    | 110    |
| E    | 1001   |

### 1.3
- Line 1: `p^ := q^` makes `a = 9`, so `writeln(a)` prints **9**.
- Line 2: `b` is unchanged, so **9** as well.
- Line 3: `q := p` makes `q` point at `a`. `q^ := q^ + 1` sets `a := 10`. `writeln(p^)` prints **10** (p still points at a).
- Line 4: `b` was never written to since line 1's snapshot, so still **9**.

### 1.4
- (a) `&v`
- (b) `&v`
- (c) `&mut v`
- (d) `s.clone()`
- (e) `n` (i32 is `Copy`, so this just makes a copy)
- (f) `&mut w`

T/F: **1 = T**, **2 = T** (immutable references are `Copy`), **3 = F** (mutable + any other reference is forbidden), **4 = T**.

### 1.5
| description | discipline |
|---|---|
| Same fields → interchangeable | structurally typed |
| Only the methods matter | duck typed |
| Mix of compile-time and runtime checking | gradually typed |
| Silent conversion between strings and numbers | weakly typed |
| No concept of "type" | untyped |
| Length is part of the type | dependently typed |
| (leftover) | (some other discipline) |

### 2.1
- `First(A) = a, epsilon`
- `First(A b) = a, b`
- `First(A c) = a, c`
- `First(S) = a, b, c`

LL(1)? **No.** S has two productions (`S → A b` and `S → A c`) whose First sets both contain `a` — they conflict on the lookahead `a`.

Left-factored grammar:
```
S → A T
T → b | c
A → a A | ε
```
Now `First(T) = {b, c}`, no conflict, and the grammar is LL(1).

### 2.2
- (a) `VI n`
- (b) `lookup x env`
- (c) `VU`
- (d) `VI (-n)` (or equivalently `VI (negate n)`)
- (e) `VB (not b)`
- (f) `VI (a - b)`
- (g) `VB (a < b)`
- (h) `VB (a && b)`
- (i) `(x, evaluate e env) : env`
- (j) `execute s2 (execute s1 env)`
- (k) `execute f env`
- (l) `execute (While c body) (execute body env)`

### 2.3
The loop iterates while `!(n == 0)`, i.e., while `n /= 0`:
- start: n=5, fact=1
- iter 1: fact = 1*5 = 5,  n = 4
- iter 2: fact = 5*4 = 20, n = 3
- iter 3: fact = 20*3 = 60, n = 2
- iter 4: fact = 60*2 = 120, n = 1
- iter 5: fact = 120*1 = 120, n = 0 → loop exits

Final **n = 0**, **fact = 120**.

### 3.1
**a)** `(i32, i32) -> i32`.

**b)** `max(7, 4)` returns `7`. Stack trace (top first):

| after instruction | stack |
|---|---|
| `local.get 0` | `7` |
| `local.get 1` | `4, 7` |
| `i32.gt_s`    | `1` (true, since 7 > 4) |
| (`if` consumes 1) | (empty) |
| Take the `then` branch: `local.get 0` | `7` |
| (`end`) | `7` |

Branch taken: **`if` (the then-branch)**.

**c)** Swap `gt_s` for `lt_s`, or swap the two branches. Cleanest:

```
(func $min (param i32 i32) (result i32)
  local.get 0
  local.get 1
  i32.lt_s
  if (result i32)
    local.get 0
  else
    local.get 1
  end)
```

### 4.1
- Fortran → `ISO/IEC JTC1 SC22 WG5`
- Ada → `ISO/IEC JTC1 SC22 WG9`
- TypeScript → `Microsoft (proprietary; no ISO/Ecma standard)`
- Haskell → `Haskell Language Committee (Haskell 2010 report; no ISO standard)`

### 4.2
**Rule (3):**
```
   Γ ⊢ e1 : Int      Γ ⊢ e2 : Int
   ─────────────────────────────────
            Γ ⊢ e1 < e2 : Bool
```

**Rule (4):**
```
   Γ ⊢ e1 : Bool      Γ ⊢ e2 : T      Γ ⊢ e3 : T
   ─────────────────────────────────────────────────
            Γ ⊢ if e1 then e2 else e3 : T
```

**Let-binding rule:**
```
   Γ ⊢ e1 : T1      Γ, x : T1 ⊢ e2 : T2
   ──────────────────────────────────────
        Γ ⊢ let x = e1 in e2 : T2
```

### 5.1
| Java   | `Integer[]`                  | **is-SUBtype-of**      | `Number[]`                   |
| Java   | `List<Integer>`              | **is-not-related-to**  | `List<Number>`               |
| Java   | `List<? extends Integer>`    | **is-SUBtype-of**      | `List<? extends Number>`     |
| Java   | `List<? super Number>`       | **is-SUBtype-of**      | `List<? super Integer>`      |
| Kotlin | `MutableList<Integer>`       | **is-not-related-to**  | `MutableList<Number>`        |

> Note row 1: Java arrays are **covariant**, which is a well-known footgun. Java generics, in contrast, are invariant (row 2). Kotlin's `MutableList` is invariant just like Java's `List` (row 5), while Kotlin's `List` is `out T` (covariant).

### 5.2
| type | erased to |
|------|-----------|
| `Set<E>` | `Set` |
| `List<? super String>` | `List` |
| `T` (T extends Comparable<T>) | `Comparable` |
| `Map<K, V>[]` (K, V unbounded) | `Map[]` |
| `T` (T extends Object & Serializable) | `Object` (leftmost bound) |
| `ArrayList<List<? extends Number>>` | `ArrayList` |

### 5.3
**a)** `Any?` — when reading from a `List<*>`, every element type collapses to `Any?` (everything is a subtype of `Any?`, so that's the most we can say).

**b)** Not interchangeable. `List<*>` is the **star projection**: read-only access at the most general element type. `List<Any?>` is a concrete type argument; one can pass a `List<String>` for a `List<*>` parameter, but not for a `List<Any?>` parameter (well — for the *immutable* `List` they happen to be equivalent in effect, but they are not the *same type*; the slides note this for `List<? extends Object>` vs `List<?>` in Java). Either of these answers is acceptable.

**c)** No. With `MutableList<*>`, the element type is an unknown subtype of `Any?`. `add` puts a value **in** (in-position), and the compiler has no way to know it matches the unknown element type — so the call is rejected.

### 5.4
Position analysis:
- `L` appears once, as a parameter of `translate` → **in-position only** → **contravariant**.
- `M` appears as the return type of `translate` and as the type of `val defaultOutput` → **out-positions only** → **covariant**.
  - (`val` is read-only, so it's out-position; if it were `var`, it would also be in-position and N's situation would apply.)
- `N` appears as a parameter of `setSource` (in-position) and as the return type of `getSource` (out-position) → **both positions** → **invariant**.

**a)** L = contravariant, M = covariant, N = invariant.

**b)** `interface Translator<in L, out M, N> { ... }`.

**c)** `Translator<Mammal, Mammal, Apple>` vs `Translator<Dog, Animal, Apple>`:
- L (contravariant): Mammal → Dog makes the L argument **more specific**. Contravariance flips direction, so going to a more specific L makes the whole type a **supertype**.
- M (covariant): Mammal → Animal makes the M argument **more general**, and covariance preserves direction, so the whole type becomes a **supertype**.
- Both axes pull the same way → **`Translator<Mammal, Mammal, Apple>` is-a-SUB-type-of `Translator<Dog, Animal, Apple>`**.

**d)** `Translator<Mammal, Animal, Apple>` vs `Translator<Dog, Mammal, Apple>`:
- L: Mammal → Dog (more specific L). Contravariant ⇒ moves the whole type to **supertype**.
- M: Animal → Mammal (more specific M). Covariant ⇒ moves the whole type to **subtype**.
- The two axes pull in **opposite** directions, so neither type can be substituted for the other. → **is-not-related-to**.

**e)** N is used both as a parameter (in-position, by `setSource`) and as a return type (out-position, by `getSource`). The "in + out" combination forces invariance: marking it `out` would break `setSource`, and marking it `in` would break `getSource`.

</details>
