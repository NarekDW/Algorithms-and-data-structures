package _2_Sorting._2_1_Elementary_Sorts.experiments;

import common.StopwatchCPU;
import _2_Sorting._2_1_Elementary_Sorts.Insertion;
import common.SortUtils;

import static common.SortUtils.exch;
import static common.SortUtils.less;

/*****************************************************************************************************
 * <p>
 * 2.1.24 Insertion sort with sentinel.
 * Develop an implementation of insertion sort that eliminates the j>0 test in the inner loop
 * by first putting the smallest item into position.
 * Use SortCompare to evaluate the effectiveness of doing so.
 * Note : It is often possible to avoid an index-out-of-bounds test in this way—the element that
 * enables the test to be eliminated is known as a sentinel.
 *
 ****************************************************************************************************/
public class InsertionX {

    public static void sort(Comparable<?>[] a) {
        int min = 0;
        int n = a.length;
        for (int i = 1; i < n; i++)
            if (less(a[i], a[min]))
                min = i;
        exch(a, 0, min);

        for (int i = 2; i < n; i++) {
            int j = i;
            Comparable<?> v = a[i];
            while (less(v, a[j - 1])){
                a[j] = a[j - 1];
                j--;
            }
            a[j] = v;
        }
    }


    public static void main(String[] args) {
        Integer[] x = {3, 1, 2, 7, 6, 4, 2, 1};
        sort(x);
        SortUtils.show(x);

        // Sentinel 2x time faster then Original
        compareOriginalVsSentinel();
    }

    /**
     * Original time: 26.663713
     * Sentinel time: 13.237746
     * Sentinel 2.0 faster then Original
     */
    private static void compareOriginalVsSentinel() {
        int size = 100_000;
        Double[] array = SortUtils.generateArrayDouble(size, false);
        Double[] arrayCopy = new Double[size];
        System.arraycopy(array, 0, arrayCopy, 0, size);

        StopwatchCPU stopwatch = new StopwatchCPU();
        Insertion.sort(array);
        double originalTime = stopwatch.elapsedTime();
        System.out.println("Original time: " + originalTime);

        StopwatchCPU stopwatch2 = new StopwatchCPU();
        InsertionX.sort(arrayCopy);
        double sentinelTime = stopwatch2.elapsedTime();
        System.out.println("Sentinel time: " + sentinelTime);

        System.out.printf("Sentinel %3.1f faster then Original", originalTime / sentinelTime);
    }

}
