## What is a Type?

A **type** classifies values. Every value belongs to a type, and a type defines the set of values that are valid inhabitants of it.

All of the following mean the same thing:

- `t : T`
- `t` is of type `T`
- `t` has type `T`
- `t` belongs to type `T`

---
## The Typing Relation

The typing relation is usually written as a **ternary relation**:

```
Γ ⊢ t : T
```

- `Γ` is the **context** (also called the **typing environment**) — it tracks what types variables currently have
- `t` is the term/expression being typed
- `T` is its type

The typing relation is defined using **inference rules**.

### Type Checker for BTL (formally)

```
typeOf(TRUE)    = BOOLEAN
typeOf(FALSE)   = BOOLEAN
typeOf(ZERO)    = INTEGER
typeOf(SUCC e)  = INTEGER   if typeOf(e) = INTEGER
                  ERROR     otherwise
typeOf(PRED e)  = INTEGER   if typeOf(e) = INTEGER
                  ERROR     otherwise
typeOf(ISZERO e)= BOOLEAN   if typeOf(e) = INTEGER
                  ERROR     otherwise
typeOf(IF e0 e1 e2)
                = typeOf(e1) if typeOf(e0) = BOOLEAN
                              AND typeOf(e1) = typeOf(e2)
                  ERROR     otherwise
```

### Well-Typed (Typable) Terms

A term `t` is **typable** (or **well-typed**) if there exists some type `T` such that `t : T`.

### Why Type Checking Matters

Interpreters exhibit **undefined behaviour** for ill-formed programs (wrong types, unbound variables). Two strategies exist:

1. **Specify** behaviour for the ill-formed case (e.g. throw an exception)
2. **Semantic analysis** — analyze the program before running it and reject ill-formed programs

A **type checker** is a semantic analyzer that:

- checks that each expression is well-formed
- determines a type for each expression
- does **not** evaluate anything

> If a BTL program passes the type checker, the interpreter is guaranteed not to crash — it will never try to increment a boolean or use a number as a condition.

---

## Subtyping

**Notation:** `S <: T` means "`S` is a subtype of `T`"

### Substitutability (Liskov Substitution Principle)

If `S <: T`, then any expression of type `S` can be used in any context that expects a value of type `T`, without causing a type error.

More formally: if for each object `obj1` of type `S` there is an object `obj2` of type `T` such that for all programs `P` defined in terms of `T`, the behaviour of `P` is unchanged when `obj1` is substituted for `obj2` — then `S` is a subtype of `T`.

### Subsumption Rule

```
Γ ⊢ t : S    S <: T
────────────────────
     Γ ⊢ t : T
```

### General Rules

- **Reflexivity:** `T <: T` (every type is a subtype of itself)
- **Transitivity:** if `S <: T` and `T <: U`, then `S <: U`

### Top and Bottom Types

- **Top type** (`⊤`): supertype of all types. In Java: `Object`. In Kotlin: `Any?`
- **Bottom type** (`⊥`): subtype of every type. In Kotlin: `Nothing`

### [[Subclassing ≠ Subtyping]]

Extending a class does **not** automatically make it a subtype in the LSP sense. Example:

```java
class Bag {
    Bag add(int n);
    int count(int n); // return value >= 0
}
class Set extends Bag {
    Set add(int n);
    int count(int n); // return value is 0 or 1
}
```

For a `Bag b`, we expect `b.count(42) + 2 == b.add(42).add(42).count(42)`. This does **not** hold if `b` is actually a `Set`. So `Set` is a subclass of `Bag`, but **not** a subtype in the LSP sense.

---
## Polymorphism

### Subtype Polymorphism (Inclusion Polymorphism)

A single method works for a type and all its subtypes:

```java
class Animal { void saySomething() {…} }
class Cat extends Animal { @Override void saySomething() {…} }
class Dog extends Animal { @Override void saySomething() {…} }
```

### Parametric Polymorphism

A single definition works for any type `T` (generic programming):

```java
MyClass<T> { T max(T x, T y) { … }; }
```

### Ad Hoc Polymorphism

Function overloading — same name, different type signatures:

```java
void plus(int a, int b) { … }
void plus(String a, String b) { … }
```

---
## Generics

Generics allow writing code that is parameterized over types, avoiding both code duplication and unsafe casts.

### Motivation

```java
// Without generics: unsafe cast at runtime
List words = new ArrayList();
String s = (String) words.get(0); // ClassCastException possible

// With generics: compile-time safety
List<String> words = new ArrayList<String>();
String s = words.get(0); // no cast needed
```

### Generic Methods

```java
public static <T> List<T> toList(T... array) {
    List<T> list = new ArrayList<T>();
    for (T element : array) list.add(element);
    return list;
}
```

- `<T>` declares `T` as a **type variable** local to the method
- Type arguments are usually **inferred**: `toList(1, 2, 3)` → `T = Integer`

### Bounds for Type Variables

```java
// T must implement Comparable<T>
public static <T extends Comparable<T>> T min(T a, T b) {
    return (a.compareTo(b) < 0) ? a : b;
}
```

- In Java: bounds use `extends` (even for interfaces)
- In Kotlin: bounds use `:` (e.g. `<T: Comparable<T>>`)
- Multiple bounds:
    
    ```java
    // Java<T extends Readable & Closeable>// Kotlinwhere T : CharSequence, T : Appendable
    ```
    

---

## Subtyping with Generics

Generics and subtyping interact in non-obvious ways.

### Java: Generic Types are Invariant

Even though `Integer` is a subtype of `Number`:

```java
List<Integer> ints = ...;
List<Number> nums = ints; // COMPILE ERROR
```

`List<Integer>` is **not** a subtype of `List<Number>`. This prevents unsafe operations like adding a `Double` to a `List<Integer>` through a `List<Number>` reference.

### Java Wildcards

Wildcards let you express bounded unknown types:

|Syntax|Meaning|Use|
|---|---|---|
|`? extends T`|some subtype of T|**get** values out (covariant position)|
|`? super T`|some supertype of T|**put** values in (contravariant position)|
|`?`|any type (`? extends Object`)|read-only, unknown element type|

#### The Get and Put Principle

- Use `? extends T` when you **only get** values out of a structure
- Use `? super T` when you **only put** values into a structure
- Don't use a wildcard when you both get and put

```java
public static <T> void copy(
    List<? super T> destination,   // put into → super
    List<? extends T> source       // get from → extends
)
```

#### Subtyping Rules for Wildcards

```
C<S>             <: C<? extends T>   if S <: T
C<S>             <: C<? super T>     if T <: S
C<? extends S>   <: C<? extends T>   if S <: T
C<? super S>     <: C<? super T>     if T <: S
```

---

## Variance

**Variance** describes how the subtyping relation of a generic type `C<T>` relates to the subtyping relation of its type argument `T`.

### Covariance

`C<T>` is **covariant** in `T` if: `A <: B` implies `C<A> <: C<B>` (subtyping direction is **preserved**)

- Declaration site in Kotlin: `out T`
- Use site in Java: `? extends T`
- Example: `List<Cat>` is a subtype of `List<Animal>`
- A type may only be `out T` if `T` only appears in **out-positions** (return types)

```kotlin
interface Producer<out T> {
    fun produce(): T   // T is in out-position ✓
}
```

### Contravariance

`C<T>` is **contravariant** in `T` if: `A <: B` implies `C<B> <: C<A>` (subtyping direction is **reversed**)

- Declaration site in Kotlin: `in T`
- Use site in Java: `? super T`
- Example: `Comparator<Animal>` is a subtype of `Comparator<Cat>`
- A type may only be `in T` if `T` only appears in **in-positions** (parameter types)

```kotlin
interface Comparator<in T> {
    fun compare(e1: T, e2: T): Int   // T is in in-positions ✓
}
```

### Invariance

`C<T>` is **invariant** in `T` if neither `C<A> <: C<B>` nor `C<B> <: C<A>` (for distinct `A`, `B`)

- Java default: **all classes are invariant** (workaround: use wildcards at use-site)
- Kotlin: `MutableList<T>` is invariant because `T` appears in both in- and out-positions

### Summary Table

||Covariance|Contravariance|Invariance|
|---|---|---|---|
|Kotlin (declaration)|`out T`|`in T`|`T`|
|Java (use-site)|`? extends T`|`? super T`|`T`|
|Subtyping direction|preserved|reversed|none|
|T appears in|out-positions only|in-positions only|any position|
|Example|`List<out T>`|`Comparator<in T>`|`MutableList<T>`|

### Mixed Variance

A class can be covariant in one parameter and contravariant in another:

```kotlin
interface Function1<in P, out R> {
    operator fun invoke(p: P): R
}
```

---

## Type Erasure (Java)

Java generics are implemented via **type erasure**: generic type parameters exist only at compile time and are erased before the bytecode is generated.

```java
// Generic code written:
class A<T> { abstract T id(T x); }

// Compiled to (after erasure):
class A { abstract Object id(Object x); }
```

This is why:

- You cannot use `instanceof` with generic types at runtime (`list instanceof List<String>` is illegal)
- Static members cannot refer to class-level type parameters (there is no `T` at runtime for static context)
- The trick `<T extends Object & Comparable<? super T>>` forces the erasure to `Object` rather than `Comparable` for compatibility reasons

Kotlin also uses type erasure on the JVM, but provides **reified type parameters** (in inline functions) as a workaround.