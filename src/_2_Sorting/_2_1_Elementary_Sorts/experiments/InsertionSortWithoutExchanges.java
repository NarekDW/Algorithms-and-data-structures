package _2_Sorting._2_1_Elementary_Sorts.experiments;

import _2_Sorting._2_1_Elementary_Sorts.Insertion;
import common.SortUtils;
import common.StopwatchCPU;

import static common.SortUtils.less;

/*****************************************************************************************************
 * <p>
 * 2.1.25 Insertion sort without exchanges.
 * Develop an implementation of insertion sort that moves larger elements to the right one position
 * with one array access per entry, rather than using exch().
 * Use SortCompare to evaluate the effectiveness of doing so.
 *
 ****************************************************************************************************/
public class InsertionSortWithoutExchanges {

    public static void sort(Comparable<?>[] a) {
        for (int i = 1; i < a.length; i++) {
            Comparable<?> currentValue = a[i];
            int indexToPut = i;
            for (int j = i; j > 0 && less(currentValue, a[j - 1]); j--) {
                indexToPut = j - 1;
                a[j] = a[j - 1];
            }

            a[indexToPut] = currentValue;
        }
    }

    public static void main(String[] args) {
        Integer[] x = {3, 1, 2, 7, 6, 4, 2, 1};
        InsertionSortWithoutExchanges.sort(x);
        SortUtils.show(x);

        compareOriginalVsCurrent();
    }

    /**
     * Original time: 27.994219
     * Without exchange time: 16.507504
     * Without exchange 1.7 faster then Original
     */
    private static void compareOriginalVsCurrent() {
        int size = 100_000;
        Double[] array = SortUtils.generateArrayDouble(size, false);
        Double[] arrayCopy = new Double[size];
        System.arraycopy(array, 0, arrayCopy, 0, size);

        StopwatchCPU stopwatch = new StopwatchCPU();
        Insertion.sort(array);
        double originalTime = stopwatch.elapsedTime();
        System.out.println("Original time: " + originalTime);

        StopwatchCPU stopwatch2 = new StopwatchCPU();
        InsertionSortWithoutExchanges.sort(arrayCopy);
        double sentinelTime = stopwatch2.elapsedTime();
        System.out.println("Without exchange time: " + sentinelTime);

        System.out.printf("Without exchange %3.1f faster then Original", originalTime / sentinelTime);
    }
}
