# Merge Sort

Divide it into two halves, sort the two halves (recursively),
and then merge the results.

- It guarantees ~ **N * log(N)**
- Its prime disadvantage is that it uses extra space proportional to N.

___
__Proposition F__. Top-down merge sort uses between 1⁄2 * N * lg (N) and
N * lg(N) compares to sort any array of length N.

__Proof__: Let C(N) be the number of compares needed to sort an array of length N.
We have C(0)=C(1)=0 and for N > 0 we can write a recurrence relationship that
directly mirrors the recursive sort() method to establish an
_upper bound:_

**C(N) <= C(⎣N/2⎦) + C(⎡N/2⎤) + N**

_The lower bound_:  
**C(N) >= C(⎣N/2⎦) + C(⎡N/2⎤) + N / 2**

Let's say N = 2^n, and N / 2 = 2^(n-1), then **for upper bound:**

_C(2^n) = C(2^(n-1)) + C(2^(n-1)) + 2^(n)_   
_C(2^n) = 2*C(2^(n-1)) + 2^(n)_  
_C(2^n)/(2^n) = C(2^(n-1))/2^(n-1) + 1_  
As _C(2^(n-1))/(2^(n-1)) = C(2^(n-2))/2^(n-2) + 1_  
Then  
_C(2^n)/(2^n) = C(2^(n-2))/2^(n-2) + 2_  
_C(2^n)/(2^n) = C(2^0))/2^0 + n_  
_C(2^n) = (2^n) * n_  
n = log (N)  
_C(N) = N * log (N)_

**For lower bound:**  
_C(2^n) = 2*C(2^(n-1)) + 2^(n-1)_  
_C(2^n) / 2^(n) = C(2^(n-1)) / 2^(n-1) + 1/2_
_C(2^n) = (2^n) * n / 2_
_C(N) = N / 2 * log (N)_
***

<br/>

---
_PropositionG._ Top-down merge sort uses at most 6N * lg(N )
array accesses to sort an array of length N.

_Proof:_ Each merge uses at most 6N array accesses
(2N for the copy, 2N for the move back, and at most 2N for compares).
The result follows from the same argument as for Proposition F.
***

### Improvements

- Use insertion sort for small subarrays.
- Test whether the array is already in order. (a[mid] <= a[mid + 1])
- Eliminate the copy to the auxiliary array.

# Exercise:

## Done:

2.2.6 Write a program to compute the exact value of the number of array accesses used by
top-down mergesort and by bottom-up mergesort.
Use your program to plot the values for N from 1 to 512, and to compare the exact values
with the upper bound 6N*lg(N).
[Implementation: CountMerge.java](./exercises/CountMerge.java)

2.2.7 Show that the number of compares used by merge sort is monotonically increasing
(C(N+1) > C(N) for all N > 0).  
[Implementation: CountMerge.java](./exercises/CountMerge.java)

2.2.8 Suppose that Algorithm 2.4 is modified to skip the call on merge() whenever a[mid] <= a[mid+1].
Prove that the number of compares used to merge sort a sorted array is linear.
[Implementation: LinearCompares.java](./exercises/LinearCompares.java)

2.2.10 Faster merge. Implement a version of merge() that copies the second half of
a[] to aux[] in decreasing order and then does the merge back to a[] .
This change allows you to remove the code to test that each of the halves
has been exhausted from the inner loop.
Note: The resulting sort is not stable (see page 341).      
[Implementation: FasterMerge.java](./creative/FasterMerge.java)

2.2.11 Improvements.
Implement the three improvements to mergesort that are described in the text on page 275:
Add a cutoff for small subarrays, test whether the array is already in order, and avoid
the copy by switching arguments in the recursive code.  
[Implementation: MergeX.java](./creative/MergeX.java)

2.2.14 Merging sorted queues. Develop a static method that takes two queues of sorted
items as arguments and returns a queue that results from merging the queues into
sorted order.  
[Implementation: MergingSortedQueues.java](./creative/MergingSortedQueues.java)

2.2.15 Bottom-up queue mergesort.
Develop a bottom-up mergesort implementation based on the following approach:
Given N items, create N queues, each containing one of the items. Create a queue of the N queues.
Then repeatedly apply the merging operation of Exercise 2.2.14 to the first two queues and
reinsert the merged queue at the end. Repeat until the queue of queues contains only one queue.  
[Implementation: BottomUpQueueMergeSort.java](./creative/BottomUpQueueMergeSort.java)

2.2.16 Natural mergesort. Write a version of bottom-up mergesort that takes advantage of order
in the array by proceeding as follows each time it needs to find two arrays to merge:
find a sorted subarray (by incrementing a pointer until finding an entry that is smaller than
its predecessor in the array), then find the next, then merge them.
Analyze the running time of this algorithm in terms of the array size and the number of
maximal increasing sequences in the array.  
[Implementation: NaturalMergesort.java](./creative/NaturalMergesort.java)

2.2.17 Linked-list sort. Implement a natural mergesort for linked lists. (This is the
method of choice for sorting linked lists because it uses no extra space and is guaranteed
to be linearithmic.)  
[Implementation: LinkedListSort.java](./creative/LinkedListSort.java)

2.2.18 Shuffling a linked list. Develop and implement a divide-and-conquer
algorithm that randomly shuffles a linked list in linearithmic time and
logarithmic extra space.  
[Implementation: ShufflingLinkedList.java](./creative/ShufflingLinkedList.java)

2.2.19 Inversions. Develop and implement a linearithmic algorithm for computing
the number of inversions in a given array (the number of exchanges that would be
performed by insertion sort for that array—see Section 2.1). This quantity is related
to the Kendall tau distance; see Section 2.5.  
[Implementation: Inversions.java](./creative/Inversions.java)

2.2.20 Indirect sort. Develop and implement a version of mergesort that does not rearrange
the array, but returns an int[] array perm such that perm[i] is the index of the
i th smallest entry in the array.
[Implementation: IndirectSort.java](./creative/IndirectSort.java)

## Not covered/ TODO (numbers)

- 2.2.9
- 