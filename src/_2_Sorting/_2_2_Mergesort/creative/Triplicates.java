package _2_Sorting._2_2_Mergesort.creative;

import _2_Sorting._2_2_Mergesort.Merge;

/*****************************************************************************************************
 * <p>
 * 2.2.21 Triplicates. Given three lists of N names each, devise a linearithmic algorithm
 * to determine if there is any name common to all three lists, and if so, return the first
 * such name.
 *
 ****************************************************************************************************/
public class Triplicates {

    // ~ 4*N*log(N)
    public static String triplicates(String[] n1, String[] n2, String[] n3) {
        // N*log(N)
        Merge.sort(n2);
        // N*log(N)
        Merge.sort(n3);

        // N*2*log(N)
        for (String s : n1)
            if (BinarySearch.rank(n2, s) != -1)
                if (BinarySearch.rank(n3, s) != -1)
                    return s;

        return null;
    }

}
