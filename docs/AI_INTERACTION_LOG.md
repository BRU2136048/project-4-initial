The prompt I used was the assignment parameters that I formatted according to prompt engineering principles as follows:

<!-- ROLE
You are an expert Java developer and an expert in AI‑assisted software engineering.  
Your task is to generate **correct, idiomatic, fully‑compilable Java code** and **comprehensive JUnit tests** that follow all constraints exactly.

---

## **Project Goal**
Generate a complete implementation of a generic Java class named **Bag**, plus a full JUnit test suite.  
The Bag must implement the provided `Container<E>` interface and use an `ArrayList` internally.

---

## **Interface (Do Not Modify)**
Use this interface exactly as written:

java
public interface Container<E> extends Iterable<E> {
    void add(E item);
    boolean remove(E item);
    boolean contains(E item);
    int size();
    boolean isEmpty();
}
---

## **Bag Class Requirements**
### **General**
- Class name: `Bag`
- Declaration: `public class Bag<E> implements Container<E>`
- Must use **java.util.ArrayList** as the *only* internal data structure
- Must allow duplicates (multiset behavior)
- Must not add new public methods
- Must not modify the interface

### **Required Methods**
Implement all interface methods:

- `add(E item)`
- `remove(E item)` — removes *one* occurrence
- `contains(E item)`
- `size()`
- `isEmpty()`
- `iterator()` — must return a correct iterator over the ArrayList

### **Iterator Requirements**
The iterator must:

- Traverse in ArrayList order
- Correctly implement `hasNext()` and `next()`
- Throw appropriate exceptions when needed

### 
Also implement:
- `forEach`
- `spliterator`

---


Generate a complete JUnit test class that:
- Compiles without modification
- Tests normal behavior
- Tests edge cases
- Tests iterator behavior
- Tests empty‑bag behavior
- Tests adding, removing, and contains logic
- Tests removing nonexistent items
- Tests handling of `null` values (if allowed by your implementation)

Tests must be thorough, not minimal.

---
## Output Format

### 1. Bag.java
Provide the full implementation.

### 2. BagTest.java
Provide a complete JUnit test suite with high coverage.

---

## CONSTRAINTS
- Code must compile as‑is
- No additional helper classes unless absolutely necessary
- No modification of the interface
- No use of data structures other than ArrayList
- Iterator must be correct and safe

---

## TASK
Generate:
1. A complete and correct `Bag<E>` implementation  
2. A complete and correct JUnit test suite  
3. A short self‑review  

Follow all constraints exactly.
