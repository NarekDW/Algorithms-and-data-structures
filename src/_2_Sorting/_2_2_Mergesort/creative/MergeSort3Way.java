package _2_Sorting._2_2_Mergesort.creative;

import common.SortUtils;
import common.StdRandom;

import static common.SortUtils.less;

/**
 * 2.2.22 3-way mergesort. Suppose instead of dividing in half at each step, you divide
 * into thirds, sort each third, and combine using a 3-way merge. What is the order of
 * growth of the overall running time of this algorithm?
 * <p>
 * <p>
 * First approach to solve equation:
 * C(N) = C(N/3) + C(N/3) + C(N/3) + 3*N
 * Lets be N = 3^n
 * C(3^n) =  C(3^(n-1)) + C(3^(n-1)) + C(3^(n-1)) + 3^(n + 1)
 * C(3^n) = 3 * C(3^(n-1)) + 3^(n + 1)
 * C(3^n) / 3^(n) = C(3^(n-1)) / 3^(n-1) + 2
 * C(3^n) / 3^(n) = C(3^(n-2)) / 3^(n-2) + 4
 * ..
 * C(3^n) / 3^(n) = C(3^0) / 3^0 + (2 * n)
 * C(3^n) = 3^(n) * 2 * n
 * n = log(3, N)
 * <p>
 * ======================
 * C(N) = 2 * N * Log(3, N)
 * ======================
 * <p>
 * Second approach is to use Master Method:
 * Master Method is a direct way to get the solution.
 * The master method works only for following type of recurrences or for recurrences that can be
 * transformed to following type.
 * -----------------------------------------------
 * T(n) = aT(n/b) + f(n) where a >= 1 and b > 1
 * -----------------------------------------------
 * <p>
 * There are following three cases:
 * 1. If f(n) = Θ(nc) where c < Log(b, a) then T(n) = Θ(n Log(b, a) )
 * 2. If f(n) = Θ(nc) where c = Log(b, a) then T(n) = Θ(nc Log n)
 * 3. If f(n) = Θ(nc) where c > Log(b, a) then T(n) = Θ(f(n))
 */
public class MergeSort3Way {
    public static <T extends Comparable<T>> void sort(T[] arr) {
        if (arr == null)
            return;

        T[] copy = arr.clone();
        sort(copy, 0, arr.length, arr);
        for (int i = 0; i < copy.length; i++) {
            arr[i] = copy[i];
        }
    }

    public static <T extends Comparable<T>> void sort(T[] array, int low, int high, T[] destArray) {
        if (high - low < 2)
            return;

        int mid1 = low + ((high - low) / 3);
        int mid2 = low + 2 * ((high - low) / 3) + 1;

        sort(destArray, low, mid1, array);
        sort(destArray, mid1, mid2, array);
        sort(destArray, mid2, high, array);

        merge(destArray, low, mid1, mid2, high, array);
    }

    public static <T extends Comparable<T>> void merge(T[] arr, int low, int mid1, int mid2, int high, T[] destArr) {
        int i = low, j = mid1, k = mid2, l = low;

        while ((i < mid1) && (j < mid2) && (k < high)) {
            if (less(arr[i], arr[j])) {
                if (less(arr[i], arr[k]))
                    destArr[l++] = arr[i++];
                else
                    destArr[l++] = arr[k++];
            } else {
                if (less(arr[j], arr[k]))
                    destArr[l++] = arr[j++];
                else
                    destArr[l++] = arr[k++];
            }
        }

        while ((i < mid1) && (j < mid2)) {
            if (less(arr[i], arr[j]))
                destArr[l++] = arr[i++];
            else
                destArr[l++] = arr[j++];
        }

        while ((j < mid2) && (k < high)) {
            if (less(arr[j], arr[k]))
                destArr[l++] = arr[j++];
            else
                destArr[l++] = arr[k++];
        }

        while ((i < mid1) && (k < high)) {
            if (less(arr[i], arr[k]))
                destArr[l++] = arr[i++];
            else
                destArr[l++] = arr[k++];
        }

        while (i < mid1)
            destArr[l++] = arr[i++];
        while (j < mid2)
            destArr[l++] = arr[j++];
        while (k < high)
            destArr[l++] = arr[k++];
    }


    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            int arraySize = StdRandom.uniform(10_000, 100_000);
            SortUtils.testRandomArray(MergeSort3Way::sort, 2, arraySize);
        }
    }
}
