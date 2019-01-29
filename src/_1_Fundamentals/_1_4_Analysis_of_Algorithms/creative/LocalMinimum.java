package _1_Fundamentals._1_4_Analysis_of_Algorithms.creative;

/*****************************************************************************************************
 *
 * 1.4.18 Local minimum of an array. Write a program that, given an array a[] of N dis-
 * tinct integers, finds a local minimum: an index i such that a[i-1] < a[i] < a[i+1] .
 * Your program should use ~2lg N compares in the worst case..
 * Answer : Examine the middle value a[N/2] and its two neighbors a[N/2 - 1] and
 * a[N/2 + 1] . If a[N/2] is a local minimum, stop; otherwise search in the half with the
 * smaller neighbor.
 *
 * 1.4.19 Local minimum of a matrix. Given an N-by-N array a[] of N 2 distinct inte-
 * gers, design an algorithm that runs in time proportional to N to find a local minimum:
 * a pair of indices i and j such that a[i][j] < a[i+1][j] , a [i][j] < a[i][j+1] ,
 * a[i][j] < a[i-1][j] , and a[i][j] < a[i][j-1] . The running time of your pro-
 * gram should be proportional to N in the worst case.
 *
 ****************************************************************************************************/
@SuppressWarnings("WeakerAccess")
public class LocalMinimum {

    // array elements are guaranteed to be distinct
    public static int localMinOfArray(int[] a) {
        return localMinOfArray(a, 0, a.length - 1);
    }

    private static int localMinOfArray(int[] a, int lo, int hi) {
        int mid = lo + (hi - lo) / 2;
        if (mid == 0 || mid == a.length - 1 || a[mid] < a[mid + 1] && a[mid] < a[mid - 1])
            return mid;
        else if (a[mid - 1] < a[mid + 1])
            return localMinOfArray(a, lo, mid - 1);
        else
            return localMinOfArray(a, mid + 1, hi);
    }

    private static int localMinOfMatrix(int[][] a) {
        return localMinOfMatrix(a, 0, a.length - 1, a.length / 2);
    }

    private static int localMinOfMatrix(int[][] a, int lo, int hi, int row) {
        int mid = lo + (hi - lo) / 2;
        if (row == 0 || row == a.length - 1)
            return a[row][localMinOfArray(a[row], lo, hi)];
        else if (a[row][mid] < a[row][mid - 1] && a[row][mid] < a[row][mid + 1] &&
                a[row][mid] < a[row - 1][mid] && a[row][mid] < a[row + 1][mid])
            return a[row][mid];
        else if (a[row - 1][mid] < a[row + 1][mid])
            return localMinOfMatrix(a, lo, hi, row - 1);
        else
            return localMinOfMatrix(a, lo, hi, row + 1);
    }

    public static void main(String[] args) {
        int[] x = {6, 5, 9, 18, 2, 70, 3, 15};
//        int[] x = {7, 6, 5};

        int index = localMinOfArray(x);
        System.out.println("x[index] = " + x[index]);

        int[][] xx = {
                {1,  7,  33,  10, 11},
                {4,  77,  6,   9, 12},
                {13, 14, 15, 16, 17},
                {20, 21, 5, 23, 24},
                {31, 32, 3, 34, 35}
        };
        int min = localMinOfMatrix(xx);
        System.out.println("mix2x2 = " + min);
    }

}
