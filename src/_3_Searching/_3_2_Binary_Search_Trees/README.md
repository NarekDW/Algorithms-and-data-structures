# BINARY SEARCH TREES

**Definition**. A binary search tree (BST) is a binary tree where
each node has a Comparable key (and an associated value) and
satisfies the restriction that the key in any node is larger than
the keys in all nodes in that node’s left subtree and smaller
than the keys in all nodes in that node’s right subtree.

![img.png](../../resources/BST_trace.png)

## Analysis

![img.png](../../resources/BST_posibilities.png)

**Proposition C**. Search hits in a BST built from N random keys
require ~ 2 ln N (about 1.39 lg N) compares, on the average.

**Proposition D**. Insertions and search misses in a BST built from N
random keys require ~ 2 ln N (about 1.39 lg N) compares,
on the average.

![img.png](../../resources/deletion_in_BST.png)

___
**Proposition E**. In a BST,all operations take time proportional to
the height of the tree, in the worst case.

**Proof**: All of these methods go down one or two paths in the tree.
The length of any path is no more than the height, by definition.
***

![img.png](../../resources/BST_summary.png)

# Exercise:

## Done:

3.2.6 Add to BST a method height() that computes the height of the
tree. Develop two implementations: a recursive method (which takes
linear time and space proportional to the height), and a method
like size() that adds a field to each node in the tree (and takes
linear space and constant time per query).  
[Implementation: BST.java](./BST.java)

3.2.7 Add to BST a recursive method avgCompares() that computes the average number of
compares required by a random search hit in a given BST (the internal path length of the
tree divided by its size, plus one). Develop two implementations: a recursive method
(which takes linear time and space proportional to the height), and a method like size()
that adds a field to each node in the tree (and takes linear space and constant time per query).  
[Implementation: BST.java](./BST.java)

3.2.10 Write a test client TestBST.java for use in testing the implementations of
min() , max() , floor() , ceiling() , select() , rank() , delete() , deleteMin() ,
deleteMax() , and keys() that are given in the text. Start with the standard indexing
client given on page 370. Add code to take additional command-line arguments, as appropriate.  
[Implementation: TestBST.java](./exercises/TestBST.java)

3.2.13 Give nonrecursive implementations of get() and put() for BST.  
[Implementation: BSTNonRecursive.java](./BSTNonRecursive.java)

3.2.14 Give nonrecursive implementations of min(), max(), floor(), ceiling(), rank(), and select().
[Implementation: BSTNonRecursive.java](./BSTNonRecursive.java)

3.2.16 Define the external path length of a tree to be the sum of the number of nodes on
the paths from the root to all null links. Prove that the difference between the external
and internal path lengths in any binary tree with N nodes is 2N (see Proposition C).  
[Implementation: BST.java](./BST.java)

3.2.25 Perfect balance. Write a program that inserts a set of keys into an initially empty
BST such that the tree produced is equivalent to binary search, in the sense that the
sequence of compares done in the search for any key in the BST is the same as the sequence
of compares used by binary search for the same set of keys.  
[Implementation: PerfectBalance.java](./creative/PerfectBalance.java)

3.2.28 Sofware caching. Modify BST to keep the most recently accessed Node in an instance
variable so that it can be accessed in constant time if the next put() or get() uses the
same key (see Exercise 3.1.25).  
[Implementation: BST.java](./BST.java)

3.2.29 Binary tree check. Write a recursive method isBinaryTree() that takes a Node as
argument and returns true if the subtree count field N is consistent in the data structure
rooted at that node, false otherwise.
Note : This check also ensures that the data structure has no cycles and is therefore a
binary tree(!).  
[Implementation: BST.java](./BST.java)

3.2.30 Order check. Write a recursive method isOrdered() that takes a Node and two
keys min and max as arguments and returns true if all the keys in the tree are between
min and max ; min and max are indeed the smallest and largest keys in the tree,
respectively; and the BST ordering property holds for all keys in the tree; false otherwise.  
[Implementation: BST.java](./BST.java)

3.2.31 Equal key check. Write a method hasNoDuplicates() that takes a Node as argument and
returns true if there are no equal keys in the binary tree rooted at the argument node, false
otherwise. Assume that the test of the previous exercise has passed.  
[Implementation: BST.java](./BST.java)

3.2.32 Certification. Write a method isBST() that takes a Node as argument and returns true
if the argument node is the root of a binary search tree, false otherwise.
Hint : This task is also more difficult than it might seem, because the order in which you
call the methods in the previous three exercises is important.  
[Implementation: BST.java](./BST.java)

3.2.37 Level-order traversal. Write a method printLevel() that takes a Node as argument and
prints the keys in the subtree rooted at that node in level order (in order of their distance
from the root, with nodes on each level in order from left to right).
Hint : Use a Queue.  
[Implementation: BST.java](./BST.java)

3.2.40 Height. Run empirical studies to estimate average BST height by running 100
trials of the experiment of inserting N random keys into an initially empty tree, for
N = 10^4 , 10^5 , and 10^6 . Compare your results against the 2.99 lg N estimate that
is described in the text.  
[Implementation: AvgHeight.java](./experiments/AvgHeight.java)

3.2.41 Array representation. Develop a BST implementation that represents the BST
with three arrays (preallocated to the maximum size given in the constructor): one with
the keys, one with array indices corresponding to left links, and one with array indices
corresponding to right links. Compare the performance of your program with that of
the standard implementation.  
[Implementation: ArrayRepresentationBST.java](./experiments/ArrayRepresentationBST.java)

## Not covered/ TODO (numbers)

- 3.2.8
- 3.2.12
- 3.2.17-24
- 3.2.26
- 3.2.27
- 3.2.33 Select/rank check.
- 3.2.34 Threading.
- 3.2.35 Refined analysis.
- 3.2.36 Iterator.
- 3.2.39 Average case.
- 3.2.38 Tree drawing.
- 3.2.42-47
