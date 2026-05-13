# INF222 — Practice Exam B (generated from previous exam + slides)

> Suggested time: **180 minutes** · Total: **100 points**
> Format mirrors INF222 23.09.2025. Solutions are listed in a separate section at the end so you can attempt the exam first.

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

Below is a code snippet written in an imaginary language that supports specifying parameter passing modes. Select a passing mode (`obs`, `upd`, `out`) for each parameter.

```
interface Stack {
  method push(    (obs, upd, out)Element e,
                  (obs, upd, out)Stack   s);

  method pop(     (obs, upd, out)Element e,
                  (obs, upd, out)Stack   s);

  method top(     (obs, upd, out)Element e,
                  (obs, upd, out)Stack   s);

  method isEmpty( (obs, upd, out)bool    b,
                  (obs, upd, out)Stack   s);

  method size(    (obs, upd, out)int     n,
                  (obs, upd, out)Stack   s);

  method clear(   (obs, upd, out)Stack   s);

  method swapTop( (obs, upd, out)Stack   s1,
                  (obs, upd, out)Stack   s2);
}
```

> Recall: `obs` = inflowing only (cannot be modified), `upd` = both inflowing and outflowing, `out` = outflowing only (must not be read before initialised).

---

## 1.2 Variables / scoping / shadowing (6 pts)

Consider the JavaScript code below. For each `console.log`, write what is printed (or write `ReferenceError` / `undefined` where appropriate).

```javascript
function f() {
  let x = 1;
  var y = 2;
  {
    let x = 10;
    var y = 20;
    let z = 30;
    console.log(x);          // line A
    console.log(y);          // line B
  }
  console.log(x);            // line C
  console.log(y);            // line D
  try { console.log(z); }    // line E
  catch (e) { console.log("E_REF"); }
  console.log(typeof a);     // line F
  var a = 99;
}
f();
```

| line | output |
|------|--------|
| A    |        |
| B    |        |
| C    |        |
| D    |        |
| E    |        |
| F    |        |

---

## 1.3 Pointers (Pascal) (4.5 pts)

Consider the Pascal program below. Fill in the table with what each labelled `writeln` will print.

```pascal
program PointersDemo;
var a, b: integer;
    p, q: ^integer;
begin
  a := 7;
  b := 50;
  p := @a;
  q := @b;
  p^ := p^ + 3;
  writeln(a);     { line 1 }
  q^ := p^;
  writeln(b);     { line 2 }
  p := q;
  p^ := 0;
  writeln(a);     { line 3 }
  writeln(b);     { line 4 }
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

Fill in the blanks in the Rust code below. Each blank should be one of: `x`, `&x`, `&mut x`, `mut x`, `*x` — choose what makes the program type-check and run.

```rust
fn main() {
    let mut n: i32 = 5;

    let r1 = _____;                  // (a) read-only borrow of n
    println!("r1 = {}", r1);

    let r2 = _____;                  // (b) mutable borrow of n
    *r2 += 10;
    println!("n = {}", n);

    let s = String::from("rust");
    let t = _____;                   // (c) borrow of s without moving
    println!("s = {}, t = {}", s, t);

    let u = String::from("hello");
    take(_____);                     // (d) hand ownership to take()
    // println!("{}", u);            // would not compile after this point

    let mut v: Vec<i32> = vec![1, 2, 3];
    let p = _____;                   // (e) mutable borrow of v
    p.push(4);
    println!("v = {:?}", v);
}

fn take(_v: String) { /* drops _v at end of scope */ }
```

In addition, mark each statement TRUE or FALSE:

1. After `let s2 = s1;` where `s1: String`, the variable `s1` is still usable. ☐
2. For `i32` (a stack-only type), `let b = a;` is a copy, not a move. ☐
3. You can have several immutable references and one mutable reference to the same value at the same time. ☐
4. Once a mutable reference goes out of scope, the original owner can be borrowed again. ☐

---

## 1.5 Typing disciplines (7.5 pts)

Match each description (rows) with the typing discipline it best fits (columns). Each row maps to exactly one column.

Columns: `gradually typed` / `structurally typed` / `duck typed` / `weakly typed` / `untyped` / `dynamically typed` / `(some other discipline)`

| description | discipline |
|---|---|
| The same program may contain both statically and dynamically typed parts. | |
| Two types are compatible if they have the same fields and methods, regardless of their declared names. | |
| What matters is whether an object responds to the methods called on it; types are not explicitly checked. | |
| Values of different types may be implicitly converted into each other without an explicit cast. | |
| The language has no notion of "type" at all. | |
| Type errors are reported only when an offending operation is actually executed. | |
| (something else from the list) | |

---

## 2.1 Set *First* and recursive descent parsing (8 pts)

Consider the following grammar (recall: ε denotes the empty string):

```
S → A B c
A → a A | ε
B → b B | ε
```

**Subtask 1.** Determine the value of `First` for the following:
- `First(A)` =
- `First(B)` =
- `First(AB)` =
- `First(BC)`, where `c` here is the terminal `c` =
- `First(S)` =

> Use `epsilon` to denote ε; separate multiple symbols with commas (e.g., `a,b`).

**Subtask 2.** Is this grammar LL(1)? In one short sentence, justify your answer.

**Subtask 3.** Sketch (in pseudocode) the recursive-descent function for nonterminal `A`.

---

## 2.2 Interpreters / evaluators (Haskell) (11 pts)

Below is an interpreter in Haskell for a small imperative language. Fill in the blanks so the interpreter is consistent with the operational semantics described in the slides (assignment adds a new binding, `Seq` runs s1 then s2, `If` chooses by the boolean condition, `While` repeats while the condition is true).

```haskell
data Stmt
  = Skip
  | Assignment String _____      -- (a)
  | Seq Stmt Stmt
  | If _____ Stmt Stmt           -- (b)
  | While Expr _____             -- (c)

data Expr
  = IntConst Int
  | BoolConst Bool
  | VarUse String
  | Binary BOp Expr Expr

data BOp = ADD | SUB | MUL | LT_ | EQ_
data Val = VI Int | VB Bool | VU
type Environment = [(String, Val)]

evaluate :: Expr -> Environment -> Val
evaluate (IntConst n)  _   = _____                          -- (d)
evaluate (BoolConst b) _   = VB b
evaluate (VarUse x)    env =
    case lookup x env of
      Just v  -> v
      Nothing -> _____                                       -- (e)
evaluate (Binary ADD e1 e2) env =
    case (evaluate e1 env, evaluate e2 env) of
      (VI a, VI b) -> _____                                  -- (f)
      _            -> VU

execute :: Stmt -> Environment -> Environment
execute  Skip                  env = _____                   -- (g)
execute (Assignment x e)       env = _____ : env             -- (h)
execute (Seq s1 s2)            env = _____                   -- (i)
execute (If c t f)             env =
    case evaluate c env of
      VB True  -> execute t env
      VB False -> _____                                      -- (j)
      _        -> env
execute (While c body)         env =
    case evaluate c env of
      VB True  -> _____                                      -- (k)
      _        -> env
```

---

## 2.3 Interpreters / evaluators (Haskell) (4 pts)

Using the interpreter from 2.2, what is the final environment (i.e., the values of `x` and `y`) after executing this BIPL program from the empty initial environment?

```
{
  x = 1;
  y = 0;
  while (x < 4) {
    y = y + x;
    x = x + 1;
  }
}
```

- final `x` =
- final `y` =

---

## 3.1 WebAssembly (9 pts)

Consider the following WebAssembly text-format module:

```
(module
  (func $f (param i32 i32) (result i32)
    local.get 0
    local.get 1
    i32.mul
    i32.const 2
    i32.add)
  (export "compute" (func $f))
)
```

**a)** What is the signature of the exported function (parameter types → result type)?

**b)** If we call `compute(3, 4)`, what does it return? Trace the contents of the operand stack after **each** instruction (from top of stack downwards).

**c)** Rewrite the function body so it uses **one local variable** `r` to hold the multiplication result before adding 2. Show the full `(func ...)` block (you may use `local.set` / `local.get`). Recall that locals are declared after the parameters with `(local i32)` and indexed after the parameters.

---

## 4.1 PL standardization (2 pts)

Match each programming language with the body that owns/maintains its standard.

| Language               | Maintained by |
|------------------------|---------------|
| C++                    |               |
| C#                     |               |
| JavaScript / ECMAScript|               |
| Python                 |               |

Pick from: `ISO/IEC JTC1 SC22 WG21` · `Ecma International TC49` · `Ecma International TC39` · `Python Software Foundation (no ISO standard)`

---

## 4.2 Formal semantics (3 pts)

Below are inference rules for a tiny expression language with integer constants, variables, addition, and equality.

```
                                  x : T  ∈  Γ
   ──────────────       (1)      ─────────────       (2)
    Γ ⊢ n : Int                    Γ ⊢ x : T
```

**Subtask 1.** Fill in the missing premises in rule (3) below, which types an addition expression. The result must be of type `Int`.

```
        ____________________________
   ────────────────────────────────────       (3)
            Γ ⊢ e1 + e2 : Int
```

**Subtask 2.** Write a similar inference rule for the equality expression `e1 == e2`, whose result is `Bool`. The two sides of `==` must have the **same** type, but that type may be either `Int` or `Bool`.

> Use the same notation as in the rules above. Don't invent your own.

---

## 5.1 Subtyping (Java / Kotlin) (5 pts)

Assume `Dog` is a subtype of `Animal`. For each row, determine the relation between the two types. Pay attention to the language column.

| Lang   | Type A                  | relation | Type B                    |
|--------|-------------------------|----------|---------------------------|
| Java   | `List<Dog>`             |          | `List<Animal>`            |
| Java   | `List<? extends Dog>`   |          | `List<? extends Animal>`  |
| Java   | `List<? super Animal>`  |          | `List<? super Dog>`       |
| Kotlin | `List<Dog>`             |          | `List<Animal>`            |
| Kotlin | `MutableList<Dog>`      |          | `MutableList<Animal>`     |

Options: `is-SUBtype-of` / `is-SUPERtype-of` / `is-not-related-to` / `is-the-same-as`

---

## 5.2 Type erasure (Java) (6 pts)

Fill in the table — to what type does the type on the left erase?

| type that uses a generic parameter | erased to |
|------------------------------------|-----------|
| `List<String>`                     |           |
| `Map<K, V>`  (with `K extends Comparable<K>`, `V` unbounded) |  |
| `T[]`  (where `T extends Number`)  |           |
| `List<? extends Number>`           |           |
| `Class<T>`  (where `T` is unbounded) |         |
| `Pair<List<String>, Integer>`      |           |

---

## 5.3 Generics-related (Kotlin) (3 pts)

Consider the following Kotlin signature:

```kotlin
inline fun <reified T> filterByType(list: List<Any>): List<T> =
    list.filterIsInstance<T>()
```

**a)** Why does the keyword `inline` appear here?

**b)** What does `reified` enable that an ordinary Java generic parameter cannot do at runtime?

**c)** If you remove both `inline` and `reified`, the body `list.filterIsInstance<T>()` no longer compiles. Briefly explain why, in terms of type erasure.

---

## 5.4 Variance (Kotlin) (10 pts)

Consider the following Kotlin interface:

```kotlin
interface Pipeline<A, B, C> {
    fun produce(): A
    fun consume(b: B)
    fun transform(c: C): C
}
```

**a)** Determine the variance of each generic parameter (`covariant` / `contravariant` / `invariant`):

| param | variance |
|-------|----------|
| A     |          |
| B     |          |
| C     |          |

**b)** Rewrite the declaration using `in` / `out` where applicable (leave plain if invariant):

```kotlin
interface Pipeline< _____ A , _____ B , _____ C > { ... }
```

**c)** Assume `Kitten` is a subtype of `Cat`, `Cat` is a subtype of `Animal`, `Apple` is a subtype of `Fruit`.

What is the relation between
`Pipeline<Cat, Cat, Fruit>` and `Pipeline<Kitten, Animal, Fruit>`?

Pick one: `is-equivalent-to` / `is-not-related-to` / `is-a-SUPERtype-of` / `is-a-SUBtype-of`.

**d)** Explain in 1–2 sentences why `C` (used in `transform`) cannot be marked `out` *or* `in` here.

---

# ANSWER KEY (try the exam first!)

<details>

### 1.1
- `push`: `e=obs, s=upd`
- `pop`: `e=out, s=upd`
- `top`: `e=out, s=obs`
- `isEmpty`: `b=out, s=obs`
- `size`: `n=out, s=obs`
- `clear`: `s=upd`
- `swapTop`: `s1=upd, s2=upd`

### 1.2
- A: `10` · B: `20` · C: `1` · D: `20` (`var y` is hoisted to function scope and the inner `y=20` overwrote it) · E: `E_REF` (block-scoped `let z` is gone) · F: `undefined` (`var a` is hoisted, value `undefined` until the assignment is reached).

### 1.3
1: `10` · 2: `10` · 3: `10` (a was never reassigned after `p^ := p^ + 3` made it 10) · 4: `0` (after `p := q` and `p^ := 0`, both `p` and `q` point at `b`).

### 1.4
(a) `&n` · (b) `&mut n` · (c) `&s` · (d) `u` · (e) `&mut v`.
TRUE/FALSE: 1=F, 2=T, 3=F, 4=T.

### 1.5
- both static + dynamic → `gradually typed`
- structure-based compatibility → `structurally typed`
- behaviour-based ("if it quacks…") → `duck typed`
- implicit coercions → `weakly typed`
- no type system at all → `untyped`
- type errors at runtime only → `dynamically typed`
- the leftover row goes to `(some other discipline)`

### 2.1
- `First(A) = a, epsilon`
- `First(B) = b, epsilon`
- `First(AB) = a, b, epsilon`
- `First(Bc) = b, c`
- `First(S) = a, b, c`

LL(1)? **Yes**, because for every nonterminal the alternatives start with disjoint terminals (after taking ε into account).

```
void A() {
  if (lookahead == 'a') { match('a'); A(); }
  // else: epsilon — do nothing
}
```

### 2.2
(a) `Expr` · (b) `Expr` · (c) `Stmt` · (d) `VI n` · (e) `VU` · (f) `VI (a + b)` · (g) `env` · (h) `(x, evaluate e env)` · (i) `execute s2 (execute s1 env)` · (j) `execute f env` · (k) `execute (While c body) (execute body env)`.

### 2.3
After the loop iterates with x=1,2,3 (stops when x=4): final `x = 4`, final `y = 1+2+3 = 6`.

### 3.1
**a)** `(i32, i32) -> i32`.

**b)** `compute(3,4) = 3*4 + 2 = 14`. Stack trace (top first):

| after instruction | stack (top → bottom) |
|---|---|
| `local.get 0` | `3` |
| `local.get 1` | `4, 3` |
| `i32.mul`     | `12` |
| `i32.const 2` | `2, 12` |
| `i32.add`     | `14` |

**c)**
```
(func $f (param i32 i32) (result i32)
  (local i32)        ;; r at index 2
  local.get 0
  local.get 1
  i32.mul
  local.set 2        ;; r = a*b
  local.get 2
  i32.const 2
  i32.add)
```

### 4.1
- C++ → `ISO/IEC JTC1 SC22 WG21`
- C# → `Ecma International TC49`
- JavaScript / ECMAScript → `Ecma International TC39`
- Python → `Python Software Foundation (no ISO standard)`

### 4.2
**Rule (3):**
```
   Γ ⊢ e1 : Int      Γ ⊢ e2 : Int
   ─────────────────────────────────
            Γ ⊢ e1 + e2 : Int
```

**Equality rule:**
```
   Γ ⊢ e1 : T      Γ ⊢ e2 : T
   ─────────────────────────────
        Γ ⊢ e1 == e2 : Bool
```
(where `T ∈ {Int, Bool}`; you may also write two separate rules, one per type.)

### 5.1
- Java `List<Dog>` vs `List<Animal>` → `is-not-related-to` (Java generics are invariant)
- Java `List<? extends Dog>` vs `List<? extends Animal>` → `is-SUBtype-of`
- Java `List<? super Animal>` vs `List<? super Dog>` → `is-SUBtype-of` (super flips direction)
- Kotlin `List<Dog>` vs `List<Animal>` → `is-SUBtype-of` (Kotlin's `List` is `out T`)
- Kotlin `MutableList<Dog>` vs `MutableList<Animal>` → `is-not-related-to` (mutable list is invariant)

### 5.2
| type | erased to |
|------|-----------|
| `List<String>` | `List` |
| `Map<K, V>` | `Map` |
| `T[]` (T extends Number) | `Number[]` |
| `List<? extends Number>` | `List` |
| `Class<T>` (T unbounded) | `Class` |
| `Pair<List<String>, Integer>` | `Pair` |

### 5.3
**a)** `inline` substitutes the function body at every call site, which is how Kotlin can keep the compile-time type information for `T` instead of erasing it.
**b)** With `reified`, you can use `T` at runtime — e.g., do `is T`, `T::class`, or `filterIsInstance<T>()` — none of which works in plain Java because the type parameter has been erased to its bound.
**c)** Without inlining + reification, `T` is erased at runtime, so `filterIsInstance<T>()` has no concrete class to test against and the compiler rejects it ("Cannot use T as reified type parameter").

### 5.4
**a)** A is `covariant` (only in out-position), B is `contravariant` (only in in-position), C is `invariant` (used both in an in-position *and* in an out-position in `transform`).

**b)** `interface Pipeline<out A, in B, C>`.

**c)** `Pipeline<Cat, Cat, Fruit>` `is-a-SUBtype-of` `Pipeline<Kitten, Animal, Fruit>` — covariance preserves direction (`Cat <: Kitten`? No — Kitten <: Cat — so for an `out` parameter, `Pipeline<Cat,…>` is *super* of `Pipeline<Kitten,…>`. For `in B`, `Cat <: Animal` flips, so `Pipeline<…, Cat, …>` is *sub* of `Pipeline<…, Animal, …>`. Combined: NOT related — they pull in opposite directions). **The correct answer is `is-not-related-to`.**

**d)** Because `C` is used both as a parameter (in-position) of `transform` and as its return type (out-position). Variance modifiers are only sound when the parameter appears exclusively in one kind of position, so `C` must remain invariant.

</details>
