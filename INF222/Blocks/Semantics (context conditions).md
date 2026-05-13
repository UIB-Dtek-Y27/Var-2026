## The Three Layers of "Correctness"

A program can be wrong in three different ways:

| Layer | What it checks | When | Example of violation |
| ----- | -------------- | ---- | -------------------- |
| **Syntax** | Does it match the grammar? | Parsing | `int x = ;` |
| **Static semantics** | Does it make sense in context? | Compile time | `x + "hello"` (type error) |
| **Dynamic semantics** | What does it *do* when run? | Runtime | Division by zero |

Static semantics sits **between** syntax and runtime behavior.

## Definition

**Static semantics** = the rules a program must satisfy to be *well-formed*, beyond what the grammar alone can express — checkable **without running the program**.

These rules are called **context conditions** because they depend on the surrounding *context* (what's in scope, what types things have, etc.) rather than just the local shape of the code.

**Dynamic semantics** = the rules that determine a well-formed program's runtime **behavior** — how expressions evaluate, how statements transform program state, and what observable effects occur. Only meaningful when the program is run (or formally modeled as if run).

## Why "Context"?

A context-free grammar (CFG) can describe shape but not meaning-in-context. The grammar happily accepts:

```js
int x = y + 1;
```

…even if `y` was never declared. The grammar has no memory of what came before. Checking "is `y` declared?" requires looking at the **context** — the symbol table, the enclosing scope.

## Typical Context Conditions

### Declaration & scoping
- Every used variable must be declared
- No variable is declared twice in the same scope
- A variable must be declared *before* use (in some languages)

```java
int y = x + 1;  // ❌ x not declared
int x;
int x;          // ❌ x declared twice
```

### Type correctness
- Operands of `+` must be numeric (or both strings, depending on language)
- Condition of `if` must be Boolean
- Function arguments must match parameter types

```java
if (5) { ... }              // ❌ not a Boolean (in Java)
String s = 3 + true;        // ❌ type mismatch
```

### Control-flow rules
- `break` / `continue` only inside loops
- `return` type must match the function's declared return type
- All paths in a non-void function must return

```java
int f() {
    if (cond) return 1;
    // ❌ missing return on else path
}
```

### Definite assignment
- A local variable must be assigned before being read (Java, C#, Rust)

```java
int x;
System.out.println(x);  // ❌ x might not have been initialized
```

### Visibility / access
- Can't access a `private` field from outside its class
- Can't call a method that doesn't exist on the receiver

## Where Static Semantics Lives in a Compiler

```
Source code
    │
    ▼
[Lexer]      ── produces tokens
    │
    ▼
[Parser]     ── checks SYNTAX, builds AST
    │
    ▼
[Name resolution + Type checker]   ◄── STATIC SEMANTICS
    │                               (context conditions)
    ▼
[Code generator / Interpreter]     ◄── DYNAMIC SEMANTICS
```

## Key Takeaway

> **Static semantics = the rules a parser can't enforce alone, but the compiler still rejects before you run anything.**

If syntax is "is it spelled right?", static semantics is "does it actually make sense given everything else around it?"

> **Dynamic semantics tells you _what a program means when it runs_ — i.e. how it computes, step by step, from inputs to outputs (and what effects it has along the way).** 

If you only remember one thing: 
- **static semantics = legality**
- **dynamic semantics = behavior**

