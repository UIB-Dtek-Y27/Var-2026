When you call a procedure, you pass in parameters. But **how** those parameters are passed matters — that's what **passing modes** are about.

---

## The Three Modes

### 1. `In` — Input Only (Read Only)

The procedure **receives** a value but cannot send anything back. The original variable is **never changed**.

```python
def greet(name):      # "name" is IN — only read inside
    print("Hello, " + name)

word = "Alice"
greet(word)
print(word)           # still "Alice" — unchanged
```

> _"You can look, but don't touch."_

---

### 2. `Out` — Output Only (Write Only)

The procedure **produces** a value and sends it back. The original value is ignored going in.

```pascal
procedure GetResult(out result: Integer);
begin
    result := 42;     -- only writes, never reads the original
end;
```

> _"I don't care what's in the box — I'll put something new in it."_

---

### 3. `In/Out` — Both Input and Output (Read + Write)

The procedure **reads** the value coming in **and** can **modify** it. The change affects the original variable.

```python
def double(x: list):   # list is passed by reference in Python
    x[0] = x[0] * 2   # reads and modifies

nums = [5]
double(nums)
print(nums[0])         # 10 — it was changed!
```

> _"Take what's in the box, do something with it, and put the result back."_

---

## Side by Side Comparison

|Mode|Reads value?|Writes value back?|Original variable affected?|
|---|:-:|:-:|:-:|
|`In`|✅ Yes|❌ No|❌ No|
|`Out`|❌ No|✅ Yes|✅ Yes|
|`In/Out`|✅ Yes|✅ Yes|✅ Yes|

---

## How This Fits into a Procedure Definition

From the abstract syntax (Haskell):

```haskell
data ProcedureDeclaration
    = Procedure
        String            -- name
        [Parameter]       -- parameter list
        [VarDeclaration]  -- local variables
        Stmt              -- procedure body

type Parameter     = (VarDeclaration, Mode)
type VarDeclaration = (String, Type)

data Mode = In | Out | InOut
```

Each **parameter** is a combination of:

- A **variable declaration** — the name and type (e.g. `x: Integer`)
- A **mode** — how it is passed (`In`, `Out`, or `InOut`)

---

## Why Does Mode Matter?

Without modes, a procedure would have no clear contract about what it's allowed to do with your variables. Modes help:

- **Protect data** — `In` ensures a procedure can't accidentally overwrite your variable
- **Signal intent** — `Out` makes it clear a procedure is meant to produce a result
- **Enable two-way communication** — `In/Out` lets a procedure transform a value in place

---

## Real-World Language Examples

|Language|`In`|`Out`|`In/Out`|
|---|---|---|---|
|Pascal/Ada|`in`|`out`|`in out`|
|C#|(default)|`out`|`ref`|
|C++|(default)|(pointer/ref)|`&` reference|
|Python|immutable|return value|mutable objects|

---

## Summary

> A **parameter passing mode** defines the **direction of data flow** between the caller and the procedure.
> 
> - **In** → data flows _into_ the procedure
> - **Out** → data flows _out of_ the procedure
> - **In/Out** → data flows _both ways_