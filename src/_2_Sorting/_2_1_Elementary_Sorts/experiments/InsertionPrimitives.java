package _2_Sorting._2_1_Elementary_Sorts.experiments;

import _2_Sorting._2_1_Elementary_Sorts.Insertion;
import common.SortUtils;
import common.StopwatchCPU;

import java.util.Arrays;

/*****************************************************************************************************
 * <p>
 * 2.1.26 Primitive types.
 * Develop a version of insertion sort that sorts arrays of int values and compare its performance
 * with the implementation given in the text (which sorts Integer values and implicitly uses
 * autoboxing and auto-unboxing to convert).
 *
 ****************************************************************************************************/
public class InsertionPrimitives {

    public static void sort(int[] a) {
        for (int i = 1; i < a.length; i++)
            for (int j = i; j > 0 && a[j] < a[j - 1]; j--) {
                int tmp = a[j];
                a[j] = a[j - 1];
                a[j - 1] = tmp;
            }
    }

    public static void main(String[] args) {
        int[] x = {3, 1, 2, 7, 6, 4, 2, 1};
        sort(x);
        System.out.println(Arrays.toString(x));
        compareOriginalVsCurrent();
    }

    /**
     * Original time: 25.115615
     * Primitives time: 1.456339
     * Primitives are 17.2 faster then Original
     */
    private static void compareOriginalVsCurrent() {
        int size = 100_000;
        int[] array = SortUtils.generateArrayInt(size);
        Integer[] arrayCopy = new Integer[size];
        for (int i = 0; i < size; i++)
            arrayCopy[i] = array[i];

        StopwatchCPU stopwatch = new StopwatchCPU();
        Insertion.sort(arrayCopy);
        double originalTime = stopwatch.elapsedTime();
        System.out.println("Original time: " + originalTime);

        StopwatchCPU stopwatch2 = new StopwatchCPU();
        InsertionPrimitives.sort(array);
        double sentinelTime = stopwatch2.elapsedTime();
        System.out.println("Primitives time: " + sentinelTime);

        System.out.printf("Primitives are %3.1f faster then Original", originalTime / sentinelTime);
    }

}
