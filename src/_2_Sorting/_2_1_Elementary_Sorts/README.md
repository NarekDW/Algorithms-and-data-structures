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

