### Subclassing vs. Subtyping

The core distinction is about two different relationships that people often conflate:

- **Subclassing** is a _syntactic/implementation_ relationship — "I reuse or extend this code"
- **Subtyping** is a _semantic/behavioral_ relationship — "I can be used wherever the parent is expected, without breaking anything"

Java (and most OOP languages) give you subclassing for free when you write `extends`, but **subtyping has to be earned** by preserving behavior.

---

#### What LSP Actually Says

The Liskov Substitution Principle states:

> If `S` is a subtype of `T`, then any program using a `T` can be replaced with an `S` without changing the program's correctness.

"Correctness" here is defined by the **contracts** (preconditions, postconditions, invariants) of the supertype. The question isn't whether the code compiles — it's whether the _guarantees_ still hold.

---

#### Breaking Down the Bag/Set Example

`Bag` has an implicit **postcondition** on its behavior:

java

```java
// For any Bag b:
b.add(42).add(42).count(42) == b.count(42) + 2
```

This says: _adding the same element twice always increments the count by exactly 2_. That's what a bag (multiset) means — duplicates are allowed and tracked.

Now substitute a `Set`:

java

```java
Set s = new Set();
s.add(42).add(42).count(42)  // → 1
s.count(42) + 2              // → 0 + 2 = 2
// 1 ≠ 2 — contract broken!
```

The second `add(42)` is silently ignored because sets reject duplicates. The postcondition of `Bag` is **violated**, so `Set` is not a behavioral subtype — even though it compiles perfectly as a subclass.

---

#### Why This Is Dangerous

The whole point of subtyping is that callers shouldn't need to know what concrete type they're dealing with:

java

```java
void fillWithDuplicates(Bag b) {
    b.add(7); b.add(7); b.add(7);
    assert b.count(7) == 3; // Reasonable expectation from Bag's contract
}
```

If you pass a `Set` here, the assert fails. The method was written correctly against `Bag`'s contract — the problem is that `Set extends Bag` **lied** about the relationship.

---

#### The Root Cause: Inheritance Doesn't Check Contracts

The compiler only checks:

- Does `Set` have the required methods? ✓
- Are the return types compatible? ✓ (`Set` is a `Bag`)

It does **not** check:

- Does `Set` preserve the postconditions of `Bag`? ✗ (this is your problem)

This is a fundamental limitation of type systems in mainstream OOP languages — contracts are informal (comments, docs) rather than enforced.

---

#### The Real Relationship: Sets and Bags

Mathematically, a Set is **not** a special case of a Bag. They have incompatible contracts:

|Property|Bag|Set|
|---|---|---|
|Allows duplicates|Yes|No|
|`count(n)` range|`[0, ∞)`|`{0, 1}`|
|`add` postcondition|count increases by 1|count becomes 1|

A Set _restricts_ what Bag allows, which is a **stronger invariant** — and that's exactly what breaks substitutability. Subtypes must honor the _same or weaker_ preconditions and _same or stronger_ postconditions, but here `Set.add` breaks the postcondition of `Bag.add` entirely.