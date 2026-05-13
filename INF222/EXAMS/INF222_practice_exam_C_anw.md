
## 1.1 parameter passing modes

addVertex(obs, upd)

addEdge(obs, obs, upd)

hasEdge(obs, obs, out, obs)

neighbours(obs, out, obs)

removeVertex(obs, upd) removeVertex(out, opd)

degree(obs, out, obs)

mergeInto(obs, upd)


## 1.2 Variables / scoping / shadowing 

A = 110
B = 1001
C = 110
D = 110
E = 1001

## 1.3 Pointers (Pascal)

1. 9
2. 9
3. 10
4. 9
## 1.4 Ownership / borrowing

a = &v
b = &v
c = &mut v
d = s.clone()
m = n
f =  &mut w

1. true 
2. true
3. false
4. true 
> check this :)

## 1.5 Typing disciplines

> practice more

| description                                                                                                                            | discipline           |
| -------------------------------------------------------------------------------------------------------------------------------------- | -------------------- |
| Two record types are interchangeable if they have the same fields.                                                                     | `structurally typed` |
| Whether an object can be used somewhere depends only on which methods it answers to, not on which class it was declared in.            | `duck typed`         |
| The same source file may have parts that are checked at compile time and parts where types are deferred to runtime.                    | `gradually typed`    |
| Numeric values and strings can be combined with `+` and the language silently converts one to the other.                               | `weakly typed`       |
| The language has no concept of "type" — everything is just bits to be operated on.                                                     | Untyped              |
| The length of a vector is part of its type, so the compiler can reject a 5-element vector passed where a 3-element vector is expected. | `dependently typed`  |
| (leftover row — anything else from the list)                                                                                           |                      |
## 2.1 Set *First* and recursive descent parsing (8 pts)

> practice more

- `First(A)` =  a, ε
- `First(A b)` = a, b
- `First(A c)` = a , c
- `First(S)` = a, b, c

subtask 2 
	ulike terminalbokstaver i alle reglene

## 2.2 Interpreters / evaluators

a = VI
b = lookup x env
c = VU
d = VI (-n) / VI (Negate n)
e = VB (Not b)
f  = VI (SUB a b)
g = VI (LT_ a b)
h = VB (AND a b)
i = (x e ) : env
j = 
k = execute f env
l = execute body env 
> practice more

## 3.1
a. int -> int -> int
b. 7
## 5.2 Type erasure

| type that uses a generic parameter          | erased to    |
| ------------------------------------------- | ------------ |
| `Set<E>` (E unbounded)                      | Set          |
| `List<? super String>`                      | List         |
| `T` where `T extends Comparable<T>`         | Comparable<> |
| `Map<K, V>[]` where K, V are both unbounded | Map<>[]      |
| `T` where `T extends Object & Serializable` | Objects      |
| `ArrayList<List<? extends Number>>`         | Arraylist    |



