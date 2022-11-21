# Priority Queues

## Implementations characteristics:

![img.png](../../resources/pq_characteristics.png)

## Binary Heap

**Definition**. A binary tree is heap-ordered if the key in each node is larger than or
equal to the keys in that node’s two children (if any).

___
**Proposition O**. The largest key in a heap-ordered binary tree is found at the root.

**Proof**: By induction on the size of the tree.
***

![img.png](../../resources/heap_ordered_binary_tree.png)

**Definition**. A binary heap is a collection of keys arranged in a complete heap-ordered
binary tree, represented in level order in an array (not using the first entry).

![img.png](../../resources/heap_representation.png)

___
**Proposition P**. The height of a complete binary tree of size N is ⎣lg(N)⎦ .

**Proof**: The stated result is easy to prove by induction or by noting that the height
increases by 1 when N is a power of 2.
***

**Proposition Q**. In an N-key priority queue, the heap algorithms require no more than
1 + lg N compares for insert and no more than 2lg N compares for remove the maximum.

**Proof**: By Proposition P, both operations involve moving along a path between the root
and the bottom of the heap whose number of links is no more than lg N.
The _remove the maximum operation requires two compares for each node on the path_ (except
at the bottom): one to find the child with the larger key, the other to decide whether
that child needs to be promoted.
___

## Heapsort

Heapsort breaks into two phases: **heap construction**, where we reorganize the original array
into a heap, and the **sortdown**, where we pull the items out of the heap in decreasing order
to build the sorted result.

___
**Proposition S**. Heapsort uses fewer than 2N lg N + 2N compares (and half that many exchanges)
to sort N items.

**Proof**: The 2N term covers the cost of heap construction (see Proposition R).
The 2N lg N term follows from bounding the cost of each sink operation during
the sortdown by 2lg N (see Proposition PQ).
***

However, it is rarely used in typical applications on modern systems because
**it has poor cache performance**: array entries are rarely compared with nearby
array entries, so the number of cache misses is far higher than for quicksort,
mergesort, and even shellsort, where most compares are with nearby entries.

On the other hand, the use of heaps to implement priority queues plays an
increasingly important role in modern applications, because it provides an
easy way to guarantee logarithmic running time for **dynamic situations** where
large numbers of insert and remove the maximum operations are intermixed.

# Exercise:

## Done:

2.4.13 Describe a way to avoid the j < N test in sink() .  
[Implementation: Heap.java](./Heap.java)

2.4.19 Implement the constructor for MaxPQ that takes an array of items as argument,
using the bottom-up heap construction method described on page 323 in the text.  
[Implementation: MaxPQ.java](./MaxPQ.java)

