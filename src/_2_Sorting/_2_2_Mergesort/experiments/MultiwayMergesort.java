package _2_Sorting._2_2_Mergesort.experiments;

import _2_Sorting._2_1_Elementary_Sorts.Insertion;
import common.SortUtils;
import common.StdRandom;

import java.util.Arrays;
import java.util.Objects;

import static common.SortUtils.less;

// TODO: 02.11.2022 Partially completed
public class MultiwayMergesort {
    public static <T extends Comparable<T>> T[] sort(T[] arr, int branches) {
        if (arr == null)
            return arr;

        int length = arr.length;
        if (length < branches)
            branches = length;

        if (length <= 1)
            return arr;

        T[] copy = arr.clone();
        sort(copy, 0, length, arr, branches);
        return arr;
    }

    public static <T extends Comparable<T>> void sort(T[] array, int low, int high, T[] destArray, int branches) {
        if (high - low - 1 <= branches) {
            if (array.length == branches)
                Insertion.sort(destArray, low, high - 1);
            else
                Insertion.sort(array, low, high - 1);
            return;
        }

        int[] mids = new int[branches + 1];
        for (int i = 0; i <= branches; i++) {
            mids[i] = low + i * ((high - low) / branches);
        }

        for (int i = 0; i < branches + 1; i++) {
            int lo = mids[i];
            int hi = i == branches ? high : mids[i + 1];
            sort(array, lo, hi, destArray, branches);
        }

        merge(array, low, high, mids, destArray);
    }

    private static <T extends Comparable<T>> void merge(T[] array, int low, int high, int[] mids, T[] destArray) {
        int[] midsClone = mids.clone();
        int l = low;
        while (l < high) {
            int minIndex = -1;
            for (int i = 0; i < mids.length; i++) {
                if (i != mids.length - 1)
                    if (mids[i] == midsClone[i + 1])
                        continue;
                if (mids[i] >= high) continue;

                if (minIndex == -1) {
                    minIndex = i;
                    continue;
                }

                if (less(array[mids[i]], array[mids[minIndex]]))
                    minIndex = i;
            }

            destArray[l++] = array[mids[minIndex]];
            mids[minIndex] = mids[minIndex] + 1;
        }
    }


    public static void main(String[] args) {
        for (int i = 0; i < 10_000; i++) {
            int size = StdRandom.uniform(0, 100);
            Integer[] integers = SortUtils.generateArrayInteger(size);
            Integer[] result = sort(integers.clone(), 5);

            checkContent(integers, result);
            if (!SortUtils.isSorted(result)) {
                System.out.println(Arrays.toString(result));
                throw new RuntimeException();
            }
        }
    }

    private static void checkContent(Integer[] a, Integer[] b) {
        for (Integer i : a)
            if (!contains(b, i))
                throw new RuntimeException(String.valueOf(i));
    }

    private static boolean contains(Integer[] b, Integer elem) {
        for (Integer i : b)
            if (Objects.equals(i, elem))
                return true;
        return false;
    }
}
