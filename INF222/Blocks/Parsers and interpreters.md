### Parsers

#### What is a Parser?

A **parser** is a metaprogram that takes a string and, if it belongs to a language (defined by a context-free grammar), produces the **Concrete Syntax Tree (CST)** — the "witness" or justification for why the string is accepted.

#### Context-Free Grammars (CFG)

A CFG is a tuple **G = ⟨Σ, N, P, S⟩**:

- **Σ** — terminal symbols (alphabet)
- **N** — non-terminal symbols (language constructs)
- **P** — grammar rules of the form `A → α`
- **S** — start symbol

#### Concrete Syntax Tree (CST)

- Internal nodes = rule nodes (non-terminals)
- Leaf nodes = terminal symbols
- The yield (concatenation of leaves) = the accepted string

#### Parsing Algorithms

**Top-down** (start at root, grow to leaves):

- **Recursive Descent** — one function per grammar symbol; uses 1-token lookahead; cannot handle left-recursive grammars
- **LL(1), LL(k)** — uses a parsing table built from **First** and **Follow** sets

**Bottom-up** (start at leaves, work up to root; also called shift-reduce):

- **LR(1), SLR, LALR, CYK**

#### Key Issues

- **Left recursion** → must be eliminated for top-down parsers
- **LL conflicts** → resolved with **left factoring** (factor out common prefixes)
- **Lookahead** — how many tokens ahead the parser must peek

#### In Practice

- **Parser generators**: ANTLR, yacc/bison, Happy (Haskell), CoCo/R
- **Language Workbenches**: Eclipse Xtext, Spoofax, Rascal

---

### Tool Pipeline for a Language

|Tool|Input → Output|
|---|---|
|Acceptor|String → yes/no|
|**Parser**|Concrete syntax → CST|
|CST → AST Translator|CST → AST|
|AST → ASR Translator|AST → Abstract Semantic Repr.|
|Type Checker|AST → well-formedness check|
|**Evaluator/Interpreter**|AST → value (semantic domain)|

---

### Interpreters

#### What is an Interpreter?

A **metaprogram** implemented in a **metalanguage** that interprets programs written in an **object language**.

- Example: BIPL interpreter written in Haskell → metalanguage = Haskell, object language = BIPL

#### Evaluators

An **evaluator** is an interpreter where the program has a final value. It defines a mapping: **AST → semantic domain**.

---

### Recipe for Building an Interpreter (7 Steps)

1. Define the **abstract syntax** (syntactic categories)
2. Decide on an **environment/store model**
3. Determine **types** and their **semantic domains**
4. Determine **operations** on the semantic domains
5. Establish a **(semi-)formal semantics** for each syntactic construct
6. Pick a **metalanguage** and encode the object language's AST in it
7. Implement an **interpreter function per syntactic category** (each maps syntax → semantic domain)

---

### BIPL Example (Basic Imperative Programming Language)

**Abstract Syntax in Haskell:**

haskell

```haskell
data Stmt = Skip | Assignment String Expr | Seq Stmt Stmt | If Expr Stmt Stmt | While Expr Stmt
data Expr = IntConst Int | BoolConst Bool | VarUse String | Unary UOp Expr | Binary BOp Expr Expr
data Val  = VB Bool | VI Int | VU  -- semantic domain
type Environment = [(String, Val)]
```

**Two interpreter functions:**

- `evaluate :: Expr -> Environment -> Val`
- `execute :: Stmt -> Environment -> Environment`

**While** is implemented as a recursive `If`:

haskell

```haskell
execute (While cond body) env =
  execute (If cond (Seq body (While cond body)) Skip) env
```

---

### Semantic Analysis & Type Checking

- Interpreters without semantic analysis exhibit **undefined behaviour** for ill-formed programs (e.g., wrong types, undeclared variables)
- **Type checker**: analyzes the AST _before_ running, rejects ill-formed programs — does **not** evaluate
- **Static type checking** catches errors early, simplifies the interpreter, and can improve performance

---

### Stepwise Interpreters

An alternative to big-step evaluation: reduce the program **one step at a time** toward a value:

haskell

```haskell
evaluate e | isValue e = e
evaluate e = evaluate (step e)
```