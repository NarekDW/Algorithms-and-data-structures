# Analysis of Algorithms

## Power-Law:

T(N) = a * N^b  
where T - time, N - problem size (amount of items).

Knuth’s basic insight is simple: the total running time of a program is determined by two primary factors:

- The cost of executing each statement
- The frequency of execution of each statement

_The number of combinations of n objects taken r at a time is determined by the following formula:
C(n,r) = n! / ((n−r)! r!)_

Example: C(n, 3) = n * (n - 1) * (n - 2) / 6

## Tilde approximations.

Tilde approximation:  
n * (n - 1) * (n - 2) ~ __n^3 / 6__

Order of growth:  
__n^3__

g(N) ~ a * f(N) where f(N) = N^b(log N)^c  
**g(N) ~ a * N^b * (log N)^c**

f(N) is O(g(N)) (Big Oh)

**"big-Omega"** notation is typically used to describe a lower bound on the worst case.  
**"big-Theta"** notation is typically used to describe the performance of algorithms that are optimal in the sense that
no algorithm can have better asymptotic worst-case order of growth.

***

- _PropertyA._ The order of growth of the running time of ThreeSum(to compute the number of triples that sum to 0 among
  N numbers) is N^3.
- _Evidence:_ Let T(N) be the running time of ThreeSum for N numbers.
  The mathematical model just described suggests that T(N) ~ a * N^3 for some machine dependent constant a;
  experiments on many computers (including yours and ours) validate that approximation.

---

## Cost Model

- PropositionB. The brute-force 3-sum algorithm uses ~ N^3/2 array accesses to compute the number of
  triples that sum to 0 among N numbers.
- Proof: The algorithm accesses each of the 3 numbers for each of the ~N^3/6 triples.

___

## Summary.

For many programs, developing a mathematical model of running time reduces to the following steps:

- Develop an input model, including a definition of the problem size.
- Identify the inner loop.
- Define a cost model that includes operations in the inner loop.
- Determine the frequency of execution of those operations for the given input.

Example:  
__Binary search__. The **input model** is the array a[] of size N; the **inner loop** is the statements in the single
while loop;
the **cost model** is the compare operation (compare the values of two array entries); and the analysis, discussed in
Section 1.1 and given in full detail in Proposition B in Section 3.1, shows that the number of compares is at
most lg(N) + 1.

![img.png](../../resources/functions.png)
![img.png](../../resources/approximations.png)
![img.png](../../resources/order_of_growth.png)

## Doubling Ratio

***

- _Proposition C_. (Doubling ratio)   
  If T(N) ~ a*N^b*lg(N) then T(2N)/T(N) ~ 2b
- _Proof_: Immediate from the following calculation:
  T(2N)/T(N) = a*(2N)^b*lg(2N)/a*N^b*lgN =2^b*(1+lg2/lgN)
  ~ 2b

---

## Worst case performance guarantees

***

- _PropositionD_. In the linked-list implementations of Bag(Algorithm1.4), Stack (Algorithm 1.2),
  and Queue (Algorithm 1.3), all operations take constant time in the worst case.
- _Proof_: Immediate from the code. The number of instructions executed for each operation is bounded by a small
  constant.
  Caveat : This argument depends upon the (reasonable) assumption that the Java system creates a new Node in constant
  time.

___

## Amortized analysis

Accordingly, another way to provide a performance guarantee is to amortize the cost, by keeping track of the total cost
of all operations, divided by the number of operations.

## Memory

![img.png](../../resources/memory_allocation.png)

For example, if you have **1GB** of memory on your computer (1 billion bytes),
you cannot fit more than about **32 million int values** or
**16 million double values** in memory at any one time.

Objects:
For example, an Integer object uses 24 bytes (
16 bytes of overhead (reference to the object’s class, garbage collection information, and synchronization information),
4 bytes for its int instance variable,
and 4 bytes of padding (Moreover, the memory usage is typically padded to be a multiple of 8 bytes (machine words, on a
64-bit machine)))

### Strings and substrings

![img.png](../../resources/string_substring_memory.png)

When you use the substring() method, you create a new String object (40 bytes) but reuse the same value[] array, so a
substring of an existing string takes just 40 bytes. The character array containing the original string is aliased in
the object for the substring; the offset and length fields identify the substring. In other words, a **substring takes
constant extra memory** and forming a **substring takes constant time**, even when the lengths of the string and the
substring are huge. 
