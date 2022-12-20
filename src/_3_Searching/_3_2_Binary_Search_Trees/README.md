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
