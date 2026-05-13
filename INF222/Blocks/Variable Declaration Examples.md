### JavaScript
```js
var x;           // function-scoped, can be redeclared
let x = 10;      // block-scoped, mutable
const x = 10;    // block-scoped, immutable binding
```

### Java
```java
int x;              // primitive, default value 0
int x = 10;         // with initialization
Boolean x = true;   // wrapper class (nullable)
final int x = 10;   // immutable
String name = "Bob";
```

### Python
```python
x = 10              # dynamic typing, no declaration keyword
x: int = 10         # optional type hint (PEP 484)
NAME: str = "Bob"   # convention: uppercase = constant
```

### C
```c
int x;              // uninitialized (garbage value!)
int x = 10;         // with initialization
const int x = 10;   // immutable
static int x = 10;  // retains value between calls
extern int x;       // declared elsewhere
```

### C++
```cpp
int x = 10;
auto x = 10;        // type inferred
const int x = 10;
constexpr int x = 10; // compile-time constant
```

### Kotlin
```kotlin
var x: Int = 10     // mutable
val x: Int = 10     // immutable (read-only)
var x = 10          // type inferred
const val PI = 3.14 // compile-time constant (top-level)
lateinit var name: String  // initialized later
```

### Rust
```rust
let x = 10;             // immutable by default
let mut x = 10;         // mutable
let x: i32 = 10;        // explicit type
const MAX: u32 = 100;   // compile-time constant
static GREETING: &str = "hi"; // 'static lifetime
```

### Go
```go
var x int           // zero value (0)
var x int = 10      // explicit type
var x = 10          // type inferred
x := 10             // short declaration (inside functions)
const Pi = 3.14
```

### Haskell
```haskell
x = 10              -- immutable binding
x :: Int            -- type signature
x = 10
let x = 10 in x + 1 -- local binding
```

### TypeScript
```ts
let x: number = 10;
const x: number = 10;
var x: number = 10;
let name: string = "Bob";
```

### Swift
```swift
var x = 10          // mutable
let x = 10          // immutable
var x: Int = 10     // explicit type
```

### C#
```csharp
int x;              // default value 0
int x = 10;
var x = 10;         // type inferred
const int X = 10;   // compile-time constant
readonly int x;     // assignable only in constructor
```