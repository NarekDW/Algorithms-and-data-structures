package _1_Fundamentals._1_4_Analysis_of_Algorithms.creative;

import common.StdRandom;
import common.Tuple;

/*****************************************************************************************************
 * <p>
 * 1.4.18 Local minimum of an array. Write a program that, given an array a[] of N distinct
 * integers, finds a local minimum: an index i such that a[i-1] < a[i] < a[i+1].
 * Your program should use ~2lg N compares in the worst case.
 * Answer : Examine the middle value a[N/2] and its two neighbors a[N/2 - 1] and
 * a[N/2 + 1] . If a[N/2] is a local minimum, stop; otherwise search in the half with the
 * smaller neighbor.
 * <p>
 * 1.4.19 Local minimum of a matrix.
 * Given an N-by-N array a[] of N 2 distinct integers, design an algorithm that runs in time
 * proportional to N to find a local minimum: a pair of indices i and j such that
 * a[i][j] < a[i+1][j], a[i][j] < a[i][j+1], a[i][j] < a[i-1][j], and a[i][j] < a[i][j-1].
 * The running time of your program should be proportional to N in the worst case.
 *
 ****************************************************************************************************/
public class LocalMinimum {

    // array elements are guaranteed to be distinct
    public static int localMinOfArray(int[] a) {
        return localMinOfArray(a, 0, a.length - 1);
    }

    private static int localMinOfArray(int[] a, int lo, int hi) {
        int mid = (hi + lo) / 2;
        if (isLocalMinimum(mid, a))
            return mid;
        else if (a[mid - 1] < a[mid + 1])
            return localMinOfArray(a, lo, mid - 1);
        else
            return localMinOfArray(a, mid + 1, hi);
    }

    public static boolean isLocalMinimum(int index, int[] a) {
        int n = a.length;
        if (n == 0)
            return false;
        if (n == 1)
            return true;

        boolean isLocalMinimum;
        int value = a[index];

        if (index == 0)
            isLocalMinimum = value < a[index + 1];
        else if (index == n - 1)
            isLocalMinimum = value < a[index - 1];
        else
            isLocalMinimum = value < a[index - 1] && value < a[index + 1];

        return isLocalMinimum;
    }

    public static Tuple<Integer> localMinimumOfMatrix(int[][] a) {
        int n = a.length;
        return localMinimumOfMatrixHelper(a, 0, n, 0, n);
    }

    private static Tuple<Integer> localMinimumOfMatrixHelper(int[][] a, int colLo, int colHi, int rowLo, int rowHi) {
        int colMid = (colHi + colLo) / 2;
        int rowMid = (rowHi + rowLo) / 2;
        if (colMid >= a.length || rowMid >= a.length)
            return null;

        // find minimal value on the middle ROW and minimal value on the middle COLUMN
        int minColIndex = colLo;
        int minRowIndex = rowLo;
        for (int i = minColIndex + 1; i < colHi; i++)
            if (a[rowMid][i] < a[rowMid][minColIndex])
                minColIndex = i;
        for (int i = minRowIndex + 1; i < rowHi; i++)
            if (a[i][colMid] < a[minRowIndex][colMid])
                minRowIndex = i;

        // find the minimal value between - minimal row value and minimal column value
        // if it's local minimum, then return, else decrease matrix size by selecting on of the
        // 4 square matrices and call the function recursively
        if (a[rowMid][minColIndex] < a[minRowIndex][colMid]) {
            if (isLocalMinimum(rowMid, minColIndex, a))
                return new Tuple<>(rowMid, minColIndex);
            boolean isColIndexGreaterThanMidIndex = minColIndex > colMid;
            colLo = isColIndexGreaterThanMidIndex ? colMid : colLo;
            colHi = isColIndexGreaterThanMidIndex ? colHi : colMid;
            if (a[rowMid - 1][minColIndex] < a[rowMid][minColIndex])
                rowHi = rowMid;
            else
                rowLo = rowMid;
        } else {
            if (isLocalMinimum(minRowIndex, colMid, a))
                return new Tuple<>(minRowIndex, colMid);
            boolean isRowIndexGreaterThenMidIndex = minRowIndex > rowMid;
            rowLo = isRowIndexGreaterThenMidIndex ? rowMid : rowLo;
            rowHi = isRowIndexGreaterThenMidIndex ? rowHi : rowMid;
            if (a[minRowIndex][colMid + 1] < a[minRowIndex][colMid])
                colHi = colMid;
            else
                colLo = colMid;
        }

        return localMinimumOfMatrixHelper(a, colLo, colHi, rowLo, rowHi);
    }

    public static boolean isLocalMinimum(int rowIndex, int colIndex, int[][] a) {
        int n = a.length;
        if (n == 0)
            return false;
        if (n == 1)
            return true;

        boolean isLocalMinimum;
        int value = a[rowIndex][colIndex];

        if (rowIndex == 0)
            isLocalMinimum = value < a[rowIndex + 1][colIndex];
        else if (rowIndex == n - 1)
            isLocalMinimum = value < a[rowIndex - 1][colIndex];
        else
            isLocalMinimum = value < a[rowIndex - 1][colIndex] && value < a[rowIndex + 1][colIndex];

        if (!isLocalMinimum)
            return false;

        if (colIndex == 0)
            isLocalMinimum = value < a[rowIndex][colIndex + 1];
        else if (colIndex == n - 1)
            isLocalMinimum = value < a[rowIndex][colIndex - 1];
        else
            isLocalMinimum = value < a[rowIndex][colIndex - 1] && value < a[rowIndex][colIndex + 1];

        return isLocalMinimum;
    }


    public static void main(String[] args) {
        int[] x = {1, 2, 5, 4, 7, 11, 22};
        testIsLocalMinimum(x);
        testLocalMinOfArray(x, 4);
        int[] x2 = {1, 2, 5, 6, 7, 11, 22};
        testLocalMinOfArray(x2, 1);
        int[] x3 = {11, 22, 5, 6, 3, 11, 2};
        testLocalMinOfArray(x3, 2);

        int[][] a = new int[][]{
                {30, 100, 20, 19, 18},
                {29, 101, 21, 104, 17},
                {28, 102, 22, 105, 16},
                {27, 103, 23, 106, 15},
                {26, 25, 24, 107, 14}
        };

        int[][] b = new int[][]{
                {1, 3, 4, 5, 18},
                {7, 6, 2, 16},
                {15, 14, 13, 12},
                {11, 10, 8, 9}
        };

        testIsLocalMinimum(b);
        testLocalMinimumOfMatrix(a, 14);
        testLocalMinimumOfMatrix(b, 2);
        for (int i = 0; i < 1001; i++)
            testLocalMinimumOfMatrixRandom(i);
    }

    private static void testIsLocalMinimum(int[] array) {
        if (!isLocalMinimum(0, array))
            throw new RuntimeException();
        if (isLocalMinimum(1, array))
            throw new RuntimeException();
        if (isLocalMinimum(2, array))
            throw new RuntimeException();
        if (!isLocalMinimum(3, array))
            throw new RuntimeException();
        if (isLocalMinimum(6, array))
            throw new RuntimeException();
    }

    private static void testLocalMinOfArray(int[] array, int expected) {
        int index = localMinOfArray(array);
        if (array[index] != expected)
            throw new RuntimeException();
    }

    private static void testIsLocalMinimum(int[][] array) {
        if (!isLocalMinimum(0, 0, array))
            throw new RuntimeException();
        if (isLocalMinimum(0, 1, array))
            throw new RuntimeException();
        if (isLocalMinimum(1, 0, array))
            throw new RuntimeException();
        if (!isLocalMinimum(1, 2, array))
            throw new RuntimeException();
        if (!isLocalMinimum(3, 2, array))
            throw new RuntimeException();
        if (isLocalMinimum(3, 1, array))
            throw new RuntimeException();
    }

    private static void testLocalMinimumOfMatrix(int[][] matrix, int expected) {
        Tuple<Integer> localMinimum = localMinimumOfMatrix(matrix);
        if (matrix[localMinimum.getX()][localMinimum.getY()] != expected)
            throw new RuntimeException();
    }

    private static void testLocalMinimumOfMatrixRandom(int matrixSize) {
        int[][] matrix = new int[matrixSize][matrixSize];
        for (int i = 0; i < matrixSize; i++)
            for (int j = 0; j < matrixSize; j++)
                matrix[i][j] = StdRandom.uniform(100_000);

        Tuple<Integer> localMinimum = localMinimumOfMatrix(matrix);
        if (matrixSize == 0) {
            if (localMinimum != null)
                throw new RuntimeException();
        } else if (!isLocalMinimum(localMinimum.getX(), localMinimum.getY(), matrix))
            throw new RuntimeException(String.valueOf(matrixSize));
    }
}
