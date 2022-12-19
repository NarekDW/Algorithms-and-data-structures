# SYMBOL TABLES

___
**Definition**. A symbol table is a data structure for key-value pairs
that supports two operations: insert (put) a new pair into the table and
search for (get) the value associated with a given key.
***

**Searching cost model.**  
When studying symbol-table implementations, we count compares (equality
tests or key comparisons). In (rare) cases where compares are not in the
inner loop, we count array accesses.

### Sequential search in an unordered linked list

**Proposition A**. Search misses and insertions in an (unordered) linked-list
symbol table having N key-value pairs both require N compares, and search
hits N compares in the worst case.   
In particular, inserting N distinct keys into an initially empty linked-list
symbol table uses ~N^2/2 compares.

**Proof**: When searching for a key that is not in the list, we test every
key in the table against the search key. Because of our policy of disallowing
duplicate keys, we need to do such a search before each insertion.

**Corollary**. Inserting N distinct keys in to an initially empty linked-list
symbol table uses ~N^2/2 compares.

![img.png](../../resources/SSST_average_compares.png)

### Binary search in an ordered array

We keep sorted arrays: 1 - for keys, 2 - for values. And use binary search for
put and get operations.

***
**Proposition B**. Binary search in an ordered array with N keys uses no more
than lg N + 1 compares for a search (successful or unsuccessful).
___
**Proposition B (continued)**. Inserting a new key into an ordered array of
size N uses ~ 2N array accesses in the worst case, so inserting N keys into
an initially empty table uses ~ N 2 array accesses in the worst case.

**Proof**: Same as for Proposition A.
***

![img.png](../../resources/BinarySearchSTCost.png)

![img.png](../../resources/BSST_average_compares.png)

### Preview

![img.png](../../resources/summary_for_elementary_ST.png)

# Exercise:

## Done:

3.1.2 Develop a symbol-table implementation ArrayST that uses an (unordered) array as the
underlying data structure to implement our basic symbol-table API.  
[Implementation: ArrayST.java](./exercises/ArrayST.java)
