package _2_Sorting._2_5_Sorting_Applications.creative;

/*****************************************************************************************************
 *
 * 2.5.21 Multidimensional sort. Write a Vector data type for use in having the sort-
 * ing methods sort multidimensional vectors of d integers, putting the vectors in order
 * by first component, those with equal first component in order by second component,
 * those with equal first and second components in order by third component, and so
 * forth.
 *
 * Insertion Sort was used.
 *
 ****************************************************************************************************/
public class Vector {

    public static <T extends Comparable<T>> void sort(T[][] vector) {
        for (int i = 1; i < vector.length; i++)
            for (int j = i; j > 0 && less(vector, j - 1, j); j--)
                exch(vector, j, j - 1);
    }

    private static <T extends Comparable<T>> boolean less(T[][] vector, int l, int r) {
        T[] left = vector[l];
        T[] right = vector[r];
        for (int k = 0; k < left.length; k++) {
            int i = right[k].compareTo(left[k]);
            if (i > 0)
                return false;
            else if (i < 0)
                return true;
            else
                continue;
        }

        return false;
    }

    private static <T extends Comparable<T>> void exch(T[][] vector, int j, int i) {
        T[] left = vector[j];
        T[] right = vector[i];

        int length = left.length;
        T[] aux = (T[]) new Comparable[length];
        for (int k = 0; k < length; k++)
            aux[k] = left[k];

        for (int k = 0; k < length; k++) {
            left[k] = right[k];
            right[k] = aux[k];
        }
    }


    public static void main(String[] args) {
        Integer[][] x = {
                {1, 5, 4, 3, 2},
                {1, 3, 2, 4, 5},
                {1, 2, 5, 4, 3},
                {1, 4, 3, 2, 5}
        };

        print(x);
        sort(x);
        System.out.println("\nSorted:");
        print(x);
    }

    private static <T> void print(T[][] t) {
        for (int i = 0; i < t.length; i++) {
            for (int j = 0; j < t[i].length; j++)
                System.out.print(t[i][j] + " ");
            System.out.println();
        }
    }
}
