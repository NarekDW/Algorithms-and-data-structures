package _2_Sorting._2_5_Sorting_Applications.exercises;

import _2_Sorting._2_3_Quicksort.Quick;

/*****************************************************************************************************
 *
 * 2.5.4 Implement a method String[] dedup(String[] a) that returns the objects in
 * a[] in sorted order, with duplicates removed.
 *
 * Complexity of this Algorithm is ~ N * log(N) + 2 * N ~~ N * log(N)
 * And it uses ~ N extra space
 *
 ****************************************************************************************************/
public class RemoveDuplicates {

    String[] dedup(String[] a) {
        int n = a.length;
        int duplicates = 0;
        Quick.sort(a); // ~ N * log(N)
        // ~ N
        for (int i = 1; i < n; i++)
            if (a[i].compareTo(a[i - 1]) == 0)
                duplicates++;

        if (duplicates == 0)
            return a.clone(); // ~ N
        else
            return create(a, n - duplicates); // ~ N
    }

    private String[] create(String[] initial, int size) {
        String[] newArr = new String[size];
        newArr[0] = initial[0];
        for (int i = 1, j = 1; i < initial.length; i++)
            if (initial[i].compareTo(initial[i - 1]) != 0)
                newArr[j++] = initial[i];
        return newArr;
    }


    public static void main(String[] args) {
        String[] x = {"1", "5", "2", "2", "1", "3", "4", "3", "3", "1"};
        RemoveDuplicates removeDuplicates = new RemoveDuplicates();
        String[] dedup = removeDuplicates.dedup(x);
        for (String s : dedup)
            System.out.println(s);
    }
}
