# APPLICATIONS

## Which symbol-table implementation should I use?

![img.png](../../resources/st_summary.png)

_Java’s java.util.TreeMap and java.util.HashMap libraries are symbol-table
implementations based on red-black BSTs and hashing with separate chaining respectively._

# Exercise:

## Done:

3.5.1 Implement SET and HashSET as “wrapper class” clients of ST and HashST,
respectively (provide dummy values and ignore them).      
[Implementation: SET.java](./exercises/SET.java)  
[Implementation: HashSET.java](./exercises/HashSET.java)

3.5.2 Develop a SET implementation SequentialSearchSET by starting with the code
for SequentialSearchST and eliminating all of the code involving values.  
[Implementation: SequentialSearchSET.java](./exercises/SequentialSearchSET.java)

3.5.3 Develop a SET implementation BinarySearchSET by starting with the code for
BinarySearchST and eliminating all of the code involving values.  
[Implementation: BinarySearchSET.java](./exercises/BinarySearchSET.java)

3.5.8 Modify LinearProbingHashST to keep duplicate keys in the table. Return any
value associated with the given key for get() , and remove all items in the table that have
keys equal to the given key for delete() .  
[Implementation: LinearProbingHashSTWithDuplicates.java](./exercises/LinearProbingHashSTWithDuplicates.java)

3.5.9 Modify BST to keep duplicate keys in the tree. Return any value associated with
the given key for get() , and remove all nodes in the tree that h ave keys equal to the
given key for delete() .  
[Implementation: BSTWithDuplicates.java](./exercises/BSTWithDuplicates.java)

3.5.11 Develop a MultiSET class that is like SET,but allows equal keys and thus implements
a mathematical multiset.  
[Implementation: MultiSet.java](./exercises/MultiSet.java)

3.5.14 Develop and test a static method invert() that takes as argument an
ST<String, Bag<String>> and produces as return value the inverse of the given
symbol table (a symbol table of the same type).  
[Implementation: InvertTree.java](./exercises/InvertTree.java)

## Not covered/ TODO (numbers)

- 3.5.4 - 7
- 3.5.10
- 3.5.12 - 13
- 3.5.15 - 16
