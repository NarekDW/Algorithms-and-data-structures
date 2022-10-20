# Elementary Sorts

## Selection sort

First, find the smallest item in the array and exchange it with the first entry (itself if the first entry is already
the smallest). Then, find the next smallest item and exchange it with the second entry. Continue in this way until the
entire array is sorted.

---
__Proposition A__. Selection sort uses **~N^2/2** compares and **N** exchanges to sort
an array of length N.

__Proof__: You can prove this fact by examining the trace, which is
an N-by-N table in which unshaded letters correspond to compares.
About one-half of the entries in the table are unshaded—those on
and above the diagonal. The entries on the diagonal each correspond
to an exchange. More precisely, examination of the code reveals that,
for each i from 0 to N - 1, there is one exchange and N - 1 - i
compares, so the totals are N exchanges
and   
(N - 1) + (N - 2) + ... + 2 + 1 + 0 = N * (N - 1)/2 ~ **N^2/2** compares.
***

#### Properties:

- Running time is insensitive to input.  
  It doesn't take any advantage (of running time) if original array is already sorted.
- Data movement is minimal. It's always ~ N (linear).

## Insertion sort

Move smaller element to the left. Running time depends on the initial order.
<br/><br/>
___
__Proposition B__. Insertion sort uses ~N^2/4 compares and ~N^2/4 exchanges to
sort a randomly ordered array of length N with distinct keys, on the average.
The worst case is ~ N^2/2 compares and ~N^2/2 exchanges and the best case is
N - 1 compares and 0 exchanges.

__Proof__: Just as for Proposition A, the number of compares and exchanges is
easy to visualize in the N-by-N diagram that we use to illustrate the sort.
We count entries below the diagonal—all of them, in the worst case, and none
of them, in the best case. For randomly ordered arrays, we expect each item
to go about halfway back, on the average, so we count one-half of the entries
below the diagonal.  
The number of compares is the number of exchanges plus an additional term
equal to N minus the number of times the item inserted is the smallest so far.
In the worst case (array in reverse order), this term is negligible in relation
to the total; in the best case (array in order) it is equal to N - 1.
***
<br/><br/>
**If initial array is already sorted, then the running time of Insertion sort
algorithm is ~ N (linear)**

_If the number of inversions (inversion is a pair of entries that are out of
order in the array) in an array is less than a constant multiple of the array
size, we say that the array is **partially sorted**._

When the number of inversions is low - then Insertion sort most probably becomes
the fastest sorting algorithm.

<br/><br/>
___
__Proposition C__. The number of exchanges used by insertion sort is equal
to the number of inversions in the array, and the number of compares is
at least equal to the number of inversions and at most equal to the number
of inversions plus the array size minus 1.

__Proof__: Every exchange involves two inverted adjacent entries and thus
reduces the number of inversions by one, and the array is sorted when the
number of inversions reaches zero. Every exchange corresponds to a compare,
and an additional compare might happen for each value of i from 1 to N-1
(when a[i] does not reach the left end of the array).
***

## Shell sort

Shell sort is a simple extension of insertion sort that gains speed by allowing 
exchanges of array entries that are far apart, to produce partially sorted arrays 
that can be efficiently sorted, eventually by insertion sort.

**Shell Sort = Insertion Sort [for h = 1]**

Performance characteristics:
Worst-case ~ **N^(3/2)**


# Exercise:

## Done:

2.1.3 Give an example of an array of N items that maximizes the number of times the test
a[j] < a[min] fails (and, therefore, min gets updated) during the operation of selection
sort (Algorithm 2.1).
[2, 3, 4, 5, 6, 7, 8, 9, 10, 1]  
[Implementation: ShellSortWithArray.java](./exercises/ShellSortWithArray.java)

2.1.6 Which method runs faster for an array with all keys identical, 
selection sort or insertion sort?  
[Implementation: SortCompareWithDiffArrays.java](./exercises/SortCompareWithDiffArrays.java)

2.1.7 Which method runs faster for an array in reverse order, selection sort or insertion sort?  
[Implementation: SortCompareWithDiffArrays.java](./exercises/SortCompareWithDiffArrays.java)

2.1.11 Implement a version of shellsort that keeps the increment sequence in an array,
rather than computing it.  
[Implementation: ShellSortWithArray.java](./exercises/ShellSortWithArray.java)

2.1.14 Dequeue sort. Explain how you would sort a deck of cards, with the restriction
that the only allowed operations are to look at the values of the top two cards, to
exchange the top two cards, and to move the top card to the bottom of the deck.  
[Implementation: DequeueSort.java](./creative/DequeueSort.java)

2.1.15 Expensive exchange. A clerk at a shipping company is charged with the task of 
rearranging a number of large crates in order of the time they are to be shipped out. 
Thus, the cost of compares is very low (just look at the labels) relative to the cost 
of exchanges (move the crates). The warehouse is nearly full—there is extra space 
sufficient to hold any one of the crates, but not two. 
What sorting method should the clerk use?  
**Answer**: Selection sort, as it requires _N - 1_ exchanges only.

2.1.17 Animation. Add code to Insertion and Selection to make them draw the
array contents as vertical bars like the visual traces in this section, redrawing the bars
after each pass, to produce an animated effect, ending in a “sorted” picture where the
bars appear in order of their height. Hint : Use a client like the one in the text that generates
random Double values, insert calls to show() as appropriate in the sort code, and
implement a show() method that clears the canvas and draws the bars.
[Implementation: Animation.java](./creative/Animation.java)
[Implementation: AnimationInsertion.java](./creative/AnimationInsertion.java)
[Implementation: AnimationSelection.java](./creative/AnimationSelection.java)

2.1.19 Shellsort worst case.
Construct an array of 100 elements containing the numbers 1 through 100 for which shellsort,
with the increments 1 4 13 40 , uses as large a number of compares as you can find.  
[Implementation: ShellSortWorstAndBestCases.java](./creative/ShellSortWorstAndBestCases.java)

2.1.20 Shellsort best case. What is the best case for shellsort? Justify your answer.  
[Implementation: ShellSortWorstAndBestCases.java](./creative/ShellSortWorstAndBestCases.java)


## Not covered/ TODO (numbers)
- 2.1.12
