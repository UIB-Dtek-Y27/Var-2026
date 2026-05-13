## What is a Value?

A **value** is the result of evaluating an expression — it is what a variable is bound to, what a function returns, and what the interpreter ultimately computes. Values are the inhabitants of **types**.

> Variables are bound to values: `Var → Val`

---
## Semantic Domain

When building an interpreter, we need to choose a **semantic domain** — the set of possible values that expressions can evaluate to.

Different syntactic forms (all being expressions) can produce results of different kinds:

- Some terms evaluate to an **integer**
- Some terms evaluate to a **boolean**
- Some terms are **undefined** (e.g. using an unbound variable)

To represent all of these cases uniformly, we define a single data type `Val`:

```haskell
data Val
  = VB Bool    -- a boolean value
  | VI Int     -- an integer value
  | VU         -- undefined
```

---

## Values vs. Variables

|Concept|Description|
|---|---|
|**Value**|The actual data (e.g. `42`, `True`)|
|**Variable**|A named container that holds a value|
|**Binding**|A pairing of a variable name with a value|
|**Environment**|A collection of all current bindings|

```haskell
type Environment = [(String, Val)]
-- e.g. [("x", VI 42), ("b", VB True)]
```

- Assigning a variable means adding a new binding to the environment (prepending a pair)
- Looking up a variable retrieves its bound value

---

## Values and Types

Types **classify** values. A value belongs to a type, meaning it is one of the valid inhabitants of that type.

### Notation

All of the following mean the same thing:

- `t : T`
- `t` is of type `T`
- `t` has type `T`
- `t` belongs to type `T`

---
## Values in an Interpreter

An **evaluator** defines a mapping from AST nodes to values in the semantic domain:

```
evaluate : Expr → Val
```

The evaluator pattern-matches on the AST and returns a `Val`:

```haskell
evaluate :: Expr -> Environment -> Val
evaluate (IntConst n)  env = VI n
evaluate (BoolConst b) env = VB b
evaluate (VarUse name) env =
    case lookup name env of
        Just v  -> v
        Nothing -> VU    -- variable not bound → undefined
```

### Undefined Values

`VU` (undefined) arises when:

- A variable is referenced before it has been assigned
- An operation receives a value of the wrong type (e.g. `Succ` applied to a boolean)

Undefined behaviour is undesirable — two strategies exist to deal with it:

1. **Specify** what happens (e.g. throw an exception)
2. **Reject** ill-formed programs before running them → this is called **semantic analysis** (e.g. type checking)

---

## Summary

- A **value** is what expressions evaluate to — the concrete data a program computes with
- Values live in a **semantic domain** chosen when designing an interpreter
- Values are the things **variables hold** and **types classify**
- In Haskell, the type `Val = VB Bool | VI Int | VU` captures the full range of possible runtime values in a simple imperative language