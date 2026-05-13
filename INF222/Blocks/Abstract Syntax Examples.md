Abstract syntax describes the *structure* of language constructs, independent of concrete textual form (keywords, punctuation, whitespace).

### Signature Notation

```
VarDecl  : Type × String → Statement
VarDecl  : Type × String × Expression → Statement   // with init
VarUse   : String → Expression
Assign   : String × Expression → Statement
```

### BNF / Grammar Form

```
Statement  ::= VarDecl(Type, Identifier)
            |  VarDecl(Type, Identifier, Expression)
            |  Assign(Identifier, Expression)
            |  ...

Expression ::= VarUse(Identifier)
            |  IntLit(Integer)
            |  BinOp(Op, Expression, Expression)
            |  ...

Type       ::= IntType | BoolType | StringType | ...
```

### Concrete → Abstract Mapping

| Concrete syntax        | Abstract syntax                                  |
| ---------------------- | ------------------------------------------------ |
| `int x;`               | `VarDecl(IntType, "x")`                          |
| `int x = 10;`          | `VarDecl(IntType, "x", IntLit(10))`              |
| `boolean flag = true;` | `VarDecl(BoolType, "flag", BoolLit(true))`       |
| `x = 5;`               | `Assign("x", IntLit(5))`                         |
| `x + 1`                | `BinOp(Plus, VarUse("x"), IntLit(1))`            |
| `let y = x + 1;`       | `VarDecl(_, "y", BinOp(Plus, VarUse("x"), IntLit(1)))` |

### AST (Tree Form)

For the statement `int y = x + 1;`:

```
        VarDecl
       /   |    \
   IntType "y"   BinOp
                /  |  \
             Plus  |   IntLit(1)
                   |
                VarUse("x")
```

### Algebraic Data Type (Haskell-style)

```haskell
data Type
  = IntType
  | BoolType
  | StringType

data Expr
  = IntLit Int
  | BoolLit Bool
  | VarUse String
  | BinOp Op Expr Expr

data Stmt
  = VarDecl Type String (Maybe Expr)
  | Assign String Expr
  | Block [Stmt]
```

### Kotlin/Java-style Class Hierarchy

```kotlin
sealed class Stmt
data class VarDecl(val type: Type, val name: String, val init: Expr?) : Stmt()
data class Assign(val name: String, val value: Expr) : Stmt()

sealed class Expr
data class IntLit(val value: Int) : Expr()
data class VarUse(val name: String) : Expr()
data class BinOp(val op: Op, val lhs: Expr, val rhs: Expr) : Expr()
```

### Worked Example

**Concrete program:**
```js
var x = 10;
var y = x + 1;
```

**Abstract syntax:**
```
Block([
  VarDecl(_, "x", IntLit(10)),
  VarDecl(_, "y", BinOp(Plus, VarUse("x"), IntLit(1)))
])
```