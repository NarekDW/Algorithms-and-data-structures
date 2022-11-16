# Quick Sort

Quicksort is a divide-and-conquer method for sorting.

The quicksort algorithm’s desirable features are that it is in-place
(uses only a small auxiliary stack) and that it requires time proportional
to N*log(N) on the average to sort an array of length N.

The crux of the method is the partitioning process, which rearranges
the array to make the following three conditions hold:

- The entry a[j] is in its final place in the array, for some j.
- No entry in a[lo] through a[j-1] is greater than a[j].
- No entry in a[j+1] through a[hi] is less than a[j].

We achieve a complete sort by partitioning, then recursively
applying the method.

It is a **randomized algorithm**, because it randomly shuffles
the array before sorting it.

Partition function:  
v = a[lo];  
i = lo + 1  
j = hi  
if a[i] > v && a[j] < v exch(a, i, j)  
return j


---
__Proposition K.__ Quicksort uses ~ 2N ln N compares (and one-sixth that
many exchanges (1/3 * N ln N)) on the average to sort an array of
length N with distinct keys.

_–Proof:__ see page 294
***

---
__Proposition L.__ Quicksort uses ~ N^2/2 compares in the worst case,
but random shuffling protects against this case.

__Proof:__ see page 295
Number of compares:
N + (N - 1) + (N - 2) + . . . + 2 + 1 = (N - 1) N / 2
***

Summary: the running time of algorithm will be within a constant factor
~ 1.39 N lg(N)

**Quicksort** is typically faster than **Mergesort** because
(even though it does 39 percent more compares) it does much **less data movement**.
This mathematical assurance is probabilistic, but you can certainly rely upon it.

## Algorithmic improvements

- Cutoff to insertion sort
- Median-of-three partitioning
- Entropy-optimal sorting

---
__Proposition N__. Quicksort with 3-way partitioning uses ~ (2ln 2) NH compares
to sort N items, where H is the Shannon entropy, defined from the frequencies
of key values.

__Proof sketch__: This result follows from a (relatively difficult) generalization
of the average-case analysis of quicksort in Proposition K. As with distinct keys,
this costs about 39 percent more than the optimum (but within a constant factor).
***

Note that H = lg N when the keys are all distinct (all the probabilities are 1/N)

**A carefully tuned version of quicksort is likely to run significantly faster on
most computers for most applications than will any other compare-based sorting method.**

# Exercise:

## Done:

2.3.4 Suppose that the initial random shuffle is omitted.
Give six arrays of ten elements for which Quick.sort() uses the worst-case number of compares.  
[Implementation: QuickSortCompareCounter.java](./exercises/QuickSortCompareCounter.java)

2.3.5 Give a code fragment that sorts an array that is known to consist of items having
just two distinct keys.  
[Implementation: TwoDistinctKeys.java](./exercises/TwoDistinctKeys.java)

2.3.6 Write a program to compute the exact value of C N , and compare the exact value
with the approximation 2N ln N, for N = 100, 1,000, and 10,000.  
[Implementation: ComparesCount.java](./exercises/ComparesCount.java)

2.3.8 About how many compares will Quick.sort() make when sorting an array of N
items that are all equal?  
Answer: ~ log(N) * (N + 2) / 2

2.3.15 Nuts and bolts. (G. J. E. Rawlins) You have a mixed pile of N nuts and N bolts
and need to quickly find the corresponding pairs of nuts and bolts. Each nut matches
exactly one bolt, and each bolt matches exactly one nut. By fitting a nut and bolt together,
you can see which is bigger, but it is not possible to directly compare two nuts or
two bolts. Give an efficient method for solving the problem.  
[Implementation: NutsAndBolts.java](./creative/NutsAndBolts.java)

2.3.17 Sentinels. Modify the code in Algorithm 2.5 to remove both bounds checks in the inner
while loops. The test against the left end of the subarray is redundant since the partitioning
item acts as a sentinel (v is never less than a[lo]). To enable removal of the other test,
put an item whose key is the largest in the whole array into a[length-1] just after the shuffle.
This item will never move (except possibly to be swapped with an item having the same key) and
will serve as a sentinel in all subarrays involving the end of the array.
Note : When sorting interior subarrays, the leftmost entry in the subarray to the right
serves as a sentinel for the right end of the subarray.  
[Implementation: Sentinels.java](./creative/Sentinels.java)

2.3.18 Median-of-3 partitioning. Add median-of-3 partitioning to quicksort, as described
in the text (see page 296). Run doubling tests to determine the effectiveness of the change.  
[Implementation: MedianOf3Partitioning.java](./creative/MedianOf3Partitioning.java)

2.3.20 Nonrecursive quicksort. Implement a nonrecursive version of quicksort based
on a main loop where a subarray is popped from a stack to be partitioned, and the resulting
subarrays are pushed onto the stack. Note : Push the larger of the subarrays onto
the stack first, which guarantees that the stack will have at most lg N entries.  
[Implementation: NonrecursiveQuicksort.java](./creative/NonrecursiveQuicksort.java)

2.3.25 Cutoff to insertion sort. Implement quicksort with a cutoff to insertion sort
for subarrays with less than M elements, and empirically determine the value of M for
which quicksort runs fastest in your computing environment to sort random arrays
of N doubles, for N = 10^3 , 10^4 , 10^5 , and 10^6 . Plot average running times for M from 0
to 30 for each value of M. Note : You need to add a three-argument sort() method to
Algorithm 2.2 for sorting subarrays such that the call Insertion.sort(a, lo, hi)
sorts the subarray a[lo..hi] .  
[Implementation: QuickVsQuickX.java](./experiments/QuickVsQuickX.java)

2.3.27 Ignore small subarrays. Run experiments to compare the following strategy for
dealing with small subarrays with the approach described in Exercise 2.3.25: Simply
ignore the small subarrays in quicksort, then run a single insertion sort after the quick-
sort completes. Note : You may be able to estimate the size of your computer’s cache
memory with these experiments, as the performance of this method is likely to degrade
when the array does not fit in the cache.  
[Implementation: IgnoreSmallSubarrays.java](./experiments/IgnoreSmallSubarrays.java)

## Not covered/ TODO (numbers)

- 2.3.7
- 2.3.10
- 2.3.11
- 2.3.14
- 2.3.16
- 2.3.19 Median-of-5 partitioning.
- 2.3.22 Fast 3-way partitioning.
- 2.3.23 Java system sort.
- 2.3.24 Samplesort
- 2.3.26 Subarray sizes.
- 2.3.28 Recursion depth.
- 2.3.29 Randomization.
- 2.3.30 Corner cases.
- 2.3.31 Histogram of running times.
