----
## Global Scope

A variable declared at the top level of a program, outside any function or block. It is visible **everywhere** in the program from that point onward.

```py
x = 10 # global def 

foo(): 
	print(x) # accessible here 

foo() # prints 10
```

**Risk:** global variables create hidden dependencies between distant parts of a codebase, making code harder to reason about and test 

----

### Local (Function) Scope

A variable declared inside a function. It is only visible **within that function** and ceases to exist when the function returns.

```py
def foo():
    y = 42  # local to foo
    print(y)

foo()
print(y)  # NameError — y doesn't exist here
```
Each call to a function gets its own **fresh local scope** (its own stack frame), which is why recursion works.

----
### Block Scope

A variable declared inside a specific **block** — an `if`, `for`, `while`, or `{}` body — and visible **only within that block**. Not all languages have this.

```javascript
// JavaScript with let/const (block-scoped)
if (true) {
    let z = "blockScoped";
    console.log(z); // 99
}
// console.log(z); // ReferenceError - wont run

// var is NOT block-scoped (function-scoped instead)
if (true) {
    var w = "NOT block scoped";
    console.log(w); // 66
}
console.log(w); // 66 — leaks out of the block!
```

Languages with block scope: C, C++, Java, Rust, Go, JavaScript (`let`/`const`). Languages without (traditionally): Python, older JavaScript (`var`).

----

# How a Language Resolves Names

This is about **when and how** the language looks up which variable a name refers to

----
### Static / Lexical Scoping _(most modern languages)_

A name is resolved by looking at **where it is written in the source code** — specifically, by walking outward through the **enclosing scopes at the point of definition**.

```javascript
const a = "outer";

function outer() {

    const a = "middle";
    
    function inner() {
        console.log(a); // "middle" — determined by where inner() is DEFINED
    }
    inner();
}
outer(); // "outer"
```

The key insight: **you can figure out what every name refers to just by reading the source code** — no need to run the program. This is why it's called _static_. Closures are a direct consequence of lexical scoping.

Used by: JavaScript, Python, C, C++, Java, Rust, Haskell, Go — virtually all modern languages.

----

### Dynamic Scoping

A name is resolved by looking at the **[[Call Stack (Activation limit)]] at runtime** — specifically, which function called the current one, and what variables are live in that chain.

```
# Pseudocode (dynamic scoping)
a = "global"

procedure foo():
    print(a)          # looks up the call stack for 'a'

procedure bar():
    a = "bar's a"
    foo()             # foo sees bar's 'a', not the global one!

bar()  # prints "bar's a"
foo()  # prints "global"
```

The **same function** can produce **different results** depending on who called it. This makes code very hard to reason about — a subtle bug can appear just by calling a function from a different context.

> Used by: early Lisp, Emacs Lisp, bash (in some cases), Perl (with `local`).

--- 

### Hoisting

```javascript
console.log(x); // undefined (not an error!)
var x = 5;
console.log(x); // 5
```

```javascript
// What JS actually does internally:
var x;           // declaration hoisted to top
console.log(x);  // undefined
x = 5;           // assignment stays in place
```


