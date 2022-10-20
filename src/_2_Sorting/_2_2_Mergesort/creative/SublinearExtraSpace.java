package _2_Sorting._2_2_Mergesort.creative;

import common.StdRandom;

import static common.SortUtils.exch;
import static common.SortUtils.less;
import static java.lang.Math.min;

// ???
@SuppressWarnings("Duplicates")
public class SublinearExtraSpace {

    private static final int CUTT_OFF = 10;
    private static Comparable[] aux;

    public static void sort(Comparable[] a) {
        int n = a.length;
        aux = new Comparable[CUTT_OFF];
        sort(a, 0, n - 1);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        int n = a.length;
        for (int i = 0; i < n; i+=CUTT_OFF)
            insertionSort(a, i, min(i + CUTT_OFF, n));

        blocksInsertionSort(a);

        System.out.println();
        arr((Integer[]) a);
        System.out.println();

        for (int i = 0; i < n - CUTT_OFF; i+=CUTT_OFF)
            merge(a, 0, i + CUTT_OFF, 2 * CUTT_OFF);

//        merge(a, lo, mid, hi);
    }

    public static void merge(Comparable[] a, int lo, int mid, int hi) {
        // Merge a[lo..mid] with a[mid+1..hi].
        int i = lo, j = 0;
        int z = 0;
        for (int k = mid + 1; k < hi; k++)
            aux[z++] = a[k];

        for (int k = lo; k < hi; k++)
            if (j >= hi)                    a[k] = a[i++];
            else if (i >= CUTT_OFF)         a[k] = aux[j++];
            else if (less(aux[j], a[i]))    a[k] = aux[j++];
            else                            a[k] = a[i++];
    }

    private static void insertionSort(Comparable[] a, int l, int h) {
        for (int i = l + 1; i < h; i++)
            for (int j = i; j > l && less(a[j], a[j - 1]); j--)
                exch(a, j, j - 1);
    }

    private static void blocksInsertionSort(Comparable[] a) {
        for (int i = CUTT_OFF; i < a.length; i+=CUTT_OFF) {
            for (int j = i; j >= CUTT_OFF && less(a[j], a[j - CUTT_OFF]); j-=CUTT_OFF){
                for (int k = 0; k < CUTT_OFF; k++) {
                    Comparable tmp = a[j + k];
                    a[j + k] = a[j - CUTT_OFF + k];
                    a[j - CUTT_OFF + k] = tmp;
                }
            }
        }

    }


    public static void main(String[] args) {
        int s = 100;
        Integer[] x = new Integer[s];
        for (int i = 0; i < s; i++) {
            x[i] = StdRandom.uniform(100);
        }

        arr(x);
        System.out.println();

        sort(x);
        arr(x);
    }

    private static void arr(Integer[] x) {
        for (int i = 0; i < x.length; i++) {
            if (i % CUTT_OFF == 0) System.out.println();
            System.out.print(x[i] + " ");

        }
    }
}
