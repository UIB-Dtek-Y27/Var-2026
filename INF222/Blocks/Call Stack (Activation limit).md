The call stack is a data structure the runtime uses to **keep track of which functions are currently executing** and where to return when each one finishes.

It works exactly like a stack of plates — you can only add or remove from the top.

--- 
Every time you call a function, the runtime pushes a **stack frame** onto the top of the stack. That frame holds:

- The function's **local variables**
- The **return address** (where to go back to when the function finishes)
- Any **parameters** passed in

When the function returns, its frame is **popped off**, and execution resumes in the frame below it.

---
```python
def add(a, b):
	print(f"step 3: {a + b}")
	return a + b                         # step 3

def square_sum(x, y):              
	result = add(x, y)                   # step 2
	print(f"step 2: {result}")
	return result * result

def main():
    print(f"step 1: {square_sum(3, 4)}")  # step 1
    
main()    
```

```
Step 1 — main() calls square_sum()

┌─────────────────┐                     
│   square_sum    │  ← top              
│   x=3, y=4      │                     
├─────────────────┤                     
│   main()        │                     
└─────────────────┘                     

Step 2 — square_sum() calls add()

┌─────────────────┐  ← top
│   add(3, 4)     │
├─────────────────┤
│   square_sum    │
│   x=3, y=4      │
├─────────────────┤
│   main()        │
└─────────────────┘

Step 3 — add() returns 7, frame popped

┌─────────────────┐  ← top
│   square_sum    │  (now computes 7 * 7)
│   result = 7    │
├─────────────────┤
│   main()        │
└─────────────────┘
```
Eventually `square_sum` returns 49, its frame is popped, and `main` finishes.

---
### Stack Overflow

The stack has a **fixed maximum size**. If you call functions too deeply — most commonly through infinite or very deep recursion — you run out of space and get a **stack overflow**:
```python
def infinite():
    return infinite()  # keeps pushing frames forever

infinite()  # RecursionError: maximum recursion depth exceeded
```

### Why It Matters for Scoping

When the runtime uses **dynamic scoping**, it walks _up the call stack_ to find variables. With **lexical scoping**, it ignores the call stack entirely and uses the source code structure instead — which is why lexical scoping is more predictable.



