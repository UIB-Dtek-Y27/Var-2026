
---

## What is WebAssembly?

- A **portable binary code format** — and a corresponding **text format**
- Goal: facilitate **high-performance applications** on the web and beyond
- Languages like C++, Rust, and others can be **compiled to Wasm** and executed in a browser or another environment

---

## Hosts

A **host** is an execution environment that:

- Instantiates a Wasm module
- Manages memory
- Calls exported functions

Examples of hosts:

- Web browsers
- JavaScript runtimes (Node.js, Deno, …)
- Standalone runtimes (Wasmtime, Wasmer, WasmEdge, …)
- Cloud/edge platforms

---

## Functions

- Each function explicitly specifies its **parameter types** and **result types**
- Functions can return **multiple values** (i.e., tuples)
- **Exported functions** form a module's public API — hosts can call these directly

---

## Built-in Types

|Type|Description|
|---|---|
|`i32`|32-bit integer|
|`i64`|64-bit integer|
|`f32`|32-bit float|
|`f64`|64-bit float|
|`v128`|128-bit vector (SIMD)|

---

## Text Format vs. Binary Format

The same program exists in two forms:

**Text format (WAT — WebAssembly Text):**

```wat
(module
  (type $t0 (func (param i32 i32) (result i32)))
  (func $f0 (type $t0)
    local.get 0
    local.get 1
    i32.add
  )
  (export "add" (func $f0))
)
```

**Binary format:**

```
0x00 0x61 0x73 0x6d  → magic bytes: \0asm
0x01 0x00 0x00 0x00  → version: 1
...
```

The tool `wat2wasm` converts text format to binary.

---

## Module Structure

A Wasm module is a **self-contained execution unit** made up of sections:

|Section|ID|Description|
|---|---|---|
|Type|0x01|Function signatures|
|Import|0x02|Functions provided by the host|
|Function|0x03|Indexes for functions|
|Table|0x04|Indexed refs for indirect calls|
|Memory|0x05|Memory attributes (size, limits)|
|Global|0x06|Global variables (mutable or immutable)|
|Export|0x07|Functions accessible to the host|
|Element|0x09|Table initialization|
|Code|0x0A|Function bodies as bytecode|
|Data|0x0B|Segments of linear memory|
|Custom|—|Optional metadata|

---

## Stack Machine & Reverse Łukasiewicz (Postfix) Notation

Wasm uses a **stack-based** execution model with **postfix notation** (no explicit parentheses needed).

**Example:** `(1 + 2) * 3 - (4 * 5) + 6`

In Wasm text format:

```wat
i32.const 1
i32.const 2
i32.add       ; stack: [3]
i32.const 3
i32.mul       ; stack: [9]
i32.const 4
i32.const 5
i32.mul       ; stack: [9, 20]
i32.sub       ; stack: [-11]
i32.const 6
i32.add       ; stack: [-5]
```

---

## C++ / Rust → Wasm Example

**C++:**

```cpp
extern "C"
int add(int a, int b) {
    return a + b;
}
```

**Rust:**

```rust
pub extern "C"
fn add(a: i32, b: i32) -> i32 {
    a + b
}
```

**Compiled Wasm (WAT):**

```wat
(module
  (type $t0 (func (param i32 i32) (result i32)))
  (func $add (export "add") (type $t0)
    local.get 0
    local.get 1
    i32.add)
)
```

---

## Running Wasm

Wasm can be executed from multiple host environments:

- **From JavaScript** — load and instantiate a `.wasm` file via the `WebAssembly` JS API
- **From C#** — use a Wasm runtime library
- **From browsers** — compile C/C++/Rust to Wasm and run natively at near-native speed

---

## Binary Format Deep Dive

The module preamble starts with 8 bytes:

```
0x00 0x61 0x73 0x6d  → magic: \0asm
0x01 0x00 0x00 0x00  → version: 1.0.0.0
```

Each section then follows the pattern: `[section_id] [size] [content]`

**Example — type section for `add(i32, i32) -> i32`:**

```
0x01  → section id: type
0x07  → section size: 7 bytes
0x01  → 1 type defined
0x60  → function type
0x02  → 2 parameters
0x7f  → i32
0x7f  → i32
0x01  → 1 result
0x7f  → i32
```

---

## Useful Tools

|Tool|Purpose|
|---|---|
|`wat2wasm`|Convert WAT text format → binary|
|`wasm2wat`|Convert binary → WAT text format|
|[WasmCodeExplorer](https://wasdk.github.io/wasmcodeexplorer/)|Visual exploration of Wasm binaries|
|Wasmtime / Wasmer / WasmEdge|Standalone Wasm runtimes|

---

## Key Takeaways

1. Wasm is **not a programming language** — it's a compilation target
2. It has both a **human-readable text format** (WAT) and a compact **binary format**
3. Wasm modules are **sandboxed** and interact with the outside world only through the host
4. It uses a **stack machine** with postfix instruction ordering
5. Types are **static and explicit** — every function declares its signature
6. Wasm is designed to be **fast, safe, and portable** across environments