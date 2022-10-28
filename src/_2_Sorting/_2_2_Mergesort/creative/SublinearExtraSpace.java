package _2_Sorting._2_2_Mergesort.creative;

import _2_Sorting._2_1_Elementary_Sorts.Insertion;
import common.StdRandom;

import java.util.Arrays;

import static common.SortUtils.less;
import static java.lang.Math.min;

/*****************************************************************************************************
 * <p>
 * 2.2.12 Sublinear extra space.
 * Develop a merge implementation that reduces the extra space requirement to max(M, N/M),
 * based on the following idea: Divide the array into N/M blocks of size M
 * (for simplicity in this description, assume that N is a multiple of M).
 * Then, (i) considering the blocks as items with their first key as the sort key,
 * sort them using selection sort;
 * and (ii) run through the array merging the first block with the second, then the
 * second block with the third, and so forth.
 *
 ****************************************************************************************************/
// TODO: 28.10.2022 The approach doesn't work!
@SuppressWarnings("rawtypes")
public class SublinearExtraSpace {
    private static final int BLOCK_SIZE = 10;
    private static Comparable[] aux;

    private static void sort(Comparable[] a) {
        aux = new Comparable[BLOCK_SIZE];
        blockSelectionSort(a);
        for (int i = 0; i < a.length; i += BLOCK_SIZE) {
            Insertion.sort(a, i, Math.min(i + BLOCK_SIZE - 1, a.length - 1));
        }

        System.out.println("BLOCKS:");
        displayArrayByBlocks((Integer[]) a);
        System.out.println();

        for (int i = 0; i < a.length; i += BLOCK_SIZE) {
            int mid = min(i + BLOCK_SIZE, a.length - 1);
            int hi = min(i + 2 * BLOCK_SIZE - 1, a.length);
            int lo = i;
            if (lo >= a.length - 1)
                return;
            merge(a, lo, mid, hi);
        }
    }

    public static void merge(Comparable[] a, int lo, int mid, int hi) {
        // Merge a[lo..mid] with a[mid+1..hi].
        int i = mid, j = 0;
        int z = 0;
        for (int k = lo; k <= mid - 1; k++)
            aux[z++] = a[k];

        for (int k = lo; k < hi; k++)
            if (j > BLOCK_SIZE - 1)
                a[k] = a[i++];
            else if (i > min(hi, a.length - 1))
                a[k] = aux[j++];
            else if (less(aux[j], a[i]))
                a[k] = aux[j++];
            else
                a[k] = a[i++];
    }

    private static void blocksInsertionSort(Comparable[] a) {
        for (int i = BLOCK_SIZE; i < a.length; i += BLOCK_SIZE) {
            for (int j = i; j >= BLOCK_SIZE && less(a[j], a[j - BLOCK_SIZE]); j -= BLOCK_SIZE) {
                for (int k = 0; k < BLOCK_SIZE; k++) {
                    Comparable tmp = a[j + k];
                    a[j + k] = a[j - BLOCK_SIZE + k];
                    a[j - BLOCK_SIZE + k] = tmp;
                }
            }
        }
    }

    private static void blockSelectionSort(Comparable[] a) {
        for (int i = 0; i < a.length; i += BLOCK_SIZE) {
            int min = i;
            for (int j = i + BLOCK_SIZE; j < a.length; j += BLOCK_SIZE) {
                if (less(a[j], a[min]))
                    min = j;
            }

            if (min != i) {
                for (int k = 0; k < BLOCK_SIZE && min + k < a.length; k++) {
                    Comparable tmp = a[i + k];
                    a[i + k] = a[min + k];
                    a[min + k] = tmp;
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

        System.out.println(Arrays.toString(x));
        Integer[] clone = x.clone();
        sort(clone);
        System.out.println("MERGED");
        System.out.println(Arrays.toString(clone));
        checkArraysContent(x, clone);
    }

    private static void displayArrayByBlocks(Integer[] x) {
        for (int i = 0; i < x.length; i++) {
            if (i % BLOCK_SIZE == 0)
                System.out.println();
            System.out.print(x[i] + " ");
        }
    }

    private static void checkArraysContent(Integer[] a, Integer[] b) {
        for (Integer aItem : a) {
            if (!contains(b, aItem))
                throw new RuntimeException(String.valueOf(aItem));
        }
    }

    private static boolean contains(Integer[] arr, Integer item) {
        for (Integer arrItem : arr)
            if (item.equals(arrItem))
                return true;
        return false;
    }
}
