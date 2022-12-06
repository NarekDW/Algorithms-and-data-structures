package _2_Sorting._2_5_Sorting_Applications.creative;

import static common.SortUtils.less;

/*****************************************************************************************************
 * <p>
 * 2.5.27 Sorting parallel arrays. When sorting parallel arrays, it is useful to have a version
 * of a sorting routine that returns a permutation, say index[] , of the indices in sorted
 * order. Add a method indirectSort() to Insertion that takes an array of Comparable
 * objects a[] as argument, but instead of rearranging the entries of a[] returns an integer
 * array index[] so that a[index[0]] through a[index[N-1]] are the items in ascending
 * order.
 *
 ****************************************************************************************************/
public class Insertion {

    public static <T extends Comparable<T>> int[] indirectSort(T[] a) {
        int length = a.length;
        int[] index = new int[length];
        for (int i = 0; i < length; i++)
            index[i] = i;
        for (int i = 1; i < length; i++)
            for (int j = i; j > 0 && less(a[index[j]], a[index[j - 1]]); j--)
                exch(index, j, j - 1);

        return index;
    }

    private static void exch(int[] index, int i, int j) {
        int tmp = index[i];
        index[i] = index[j];
        index[j] = tmp;
    }


    public static void main(String[] args) {
        Integer[] x = {3, 2, 5, 4, 1};
        int[] ints = indirectSort(x);
        for (int i : ints)
            System.out.print(i + " ");
    }
}
