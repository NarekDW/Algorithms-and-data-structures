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
