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

## Not covered/ TODO (numbers)

- 2.3.7
- 2.3.10
- 2.3.11
- 2.3.14
