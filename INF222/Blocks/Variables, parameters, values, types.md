# Variables and Scoping

## Variable

A named container in computer memory used to store and update data (e.g., player score, user name).

## Variable Declaration

- **Concrete syntax:** 
## Variable Declaration Examples

### JavaScript
- [[Variable Declaration Examples]]
- [[Abstract Syntax Examples]]

### Legal Identifiers

What a variable name can be — usually defined by a regex.

### Identifier Naming Conventions

- `flatcase`
- `UPPERCASE`
- `camelCase`
- `PascalCase`
- `snake_case`
- `CONSTANT_CASE`
- `camel_Snake_Case`
- `Pascal_Snake_Case`
- `dash-case`
- `COBOL-CASE`
- `Train-Case`
### Semantics
- [[Semantics (context conditions)]]

### Variables vs. values
- Store ≈ memory
- Variabels are bound to a value: Var -> Val
```haskell
type Enviorment = [(String, Val)]
```

### Scoping
- [[Scoping in Programming]] 

 ### API of an environment
	 - lookup(x, env) -> looks up a variable x in an enviorment env, and returns it binding v. undef if not bound to a env
	 - isDefined(x, env) -> True if variabel is bound in Env, False if not.
	 - declare(x, v, env) -> binds a new variabel x to a value v and enviorment env.
	 - newEnv() -> creates a new env
## Parameters
	
#### Terminology
- Subrutines
- Procedures
- Functions
- Methodes
- Routines
- Subprograms
- ...

```python
def greet(name):        # <- "name" is the PARAMETER
    print("Hello, " + name)

greet("Alice")          # <- "Alice" is the ARGUMENT (the actual value)
greet("Bob")            # <- "Bob" is another argument
```

```python
def make_toast():       # <- defining the procedure
    print("Put bread in toaster")
    print("Press the button")
    print("Wait...")
    print("Toast is ready!")

make_toast()            # <- calling/running the procedure
make_toast()            # <- run it again, anytime!
```

A procedure has: Name, Parameter list: (each parameter has: Name, Type, Passing mode), Body (where the code lives)

All parameters in a procedure body is treated as variabels declared in an outer scope to its statment. 

```haskell
data ProcedureDeclaration
	= Procedure
		String            --- Name
		[Parameter]       --- Parameter_list
		[VarDeclaration]  --- Local Variabels
		Stmt              --- Procedure_body
		
type Parameter = (VarDeclaration, Mode)
type VarDeclaration = (String, Type)

data Mode = In | Out | InOut --- Parameter passing modes
```
[[Parameter passing Modes]] 

## Values
 [[Values]]

## Types
[[Types]]
