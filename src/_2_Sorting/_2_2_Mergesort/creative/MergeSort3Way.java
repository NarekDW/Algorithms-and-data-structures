package _2_Sorting._2_2_Mergesort.creative;

/**
 * 2.2.22 3-way mergesort. Suppose instead of dividing in half at each step, you divide
 * into thirds, sort each third, and combine using a 3-way merge. What is the order of
 * growth of the overall running time of this algorithm?
 *
 *
 * First approach to solve equation:
 * On each level amount of comparisons = (N/3) * 3 + (N/3 - 1) * 3 + (N/3 - 1) * 3 + 1 = 3N - 6 + 1 = 3N -5
 * C(N) = C(N/3) + C(N/3) + C(N/3) + (3N - 5)
 * Lets be N = 3^n
 * then n = log(3, N)
 * C(3^n) =  C(3^(n-1)) + C(3^(n-1)) + C(3^(n-1)) + (3^(n+1) - 5)
 * C(3^n) = 3 * C(3^(n-1)) + (3^(n+1) - 5)
 * C(3^n) / 3^n = C(3^(n-1)) / 3^(n-1) + 3 - (5 / 3^n)
 * (5 / 3^n) --> 0 while n is big ==>
 * C(3^n) / 3^n = C(3^(n-1)) / 3^(n-1) + 3
 * C(3^n) / 3^n = C(3^(n-2)) / 3^(n-2) + 3 + 3
 * ....
 * C(3^n) / 3^n = C(3^0) / 3^0 + 3n
 * C(3^n) = 3n * 3^n
 * ======================
 * C(N) = 3NLogN ~ NLogN
 * ======================
 *
 * Second approach is to use Master Method:
 * Master Method is a direct way to get the solution.
 * The master method works only for following type of recurrences or for recurrences that can be
 * transformed to following type.
 * -----------------------------------------------
 * T(n) = aT(n/b) + f(n) where a >= 1 and b > 1
 * -----------------------------------------------
 *
 * There are following three cases:
 * 1. If f(n) = Θ(nc) where c < Log(b, a) then T(n) = Θ(n Log(b, a) )
 * 2. If f(n) = Θ(nc) where c = Log(b, a) then T(n) = Θ(nc Log n)
 * 3. If f(n) = Θ(nc) where c > Log(b, a) then T(n) = Θ(f(n))
 */
public class MergeSort3Way {}
