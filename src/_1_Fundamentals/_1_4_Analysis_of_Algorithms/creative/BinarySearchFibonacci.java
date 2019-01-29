package _1_Fundamentals._1_4_Analysis_of_Algorithms.creative;

/*****************************************************************************************************
 *
 * 1.4.22 Binary search with only addition and subtraction. [Mihai Patrascu] Write a
 * program that, given an array of N distinct int values in ascending order, determines
 * whether a given integer is in the array. You may use only additions and subtractions
 * and a constant amount of extra memory. The running time of your program should be
 * proportional to log N in the worst case.
 *
 * Answer : Instead of searching based on powers of two (binary search), use Fibonacci
 * numbers (which also grow exponentially). Maintain the current search range to be the
 * interval [i, i + F k ] and keep F k and F k–1 in two variables. At each step compute F k–2 via
 * subtraction, check element i + F k–2 , and update the current range to either [i, i + F k–2 ]
 * or [i + F k–2 , i + F k–2 + F k–1 ].
 *
 ****************************************************************************************************/
@SuppressWarnings("ALL")
public class BinarySearchFibonacci {

    public static int rank(int[] a, int key) {
        return rank(a, key, 0, 1, 0);
    }

    public static int rank(int[] a, int key, int fi2, int fi1, int offset) {
        int index = offset + fi2;
        if (index >= a.length && key > a[a.length - 1] || index < 0 && key < a[0])
            return -1;
        else if (index >= a.length)
            index = a.length - 1;
        if (key < a[index]) return rank(a, key, fi1 - fi2, fi2, offset);
        else if (key > a[index]) return rank(a, key, fi1, fi1 + fi2, index);
        else return index;
    }

    public static void main(String[] args) {
        int[] x = {1, 2, 3, 4, 5, 6, 7, 8, 9};

        for (int j = -5; j < 15; j++) {
            int i = rank(x, j);
            if (i != -1)
                System.out.println(j + ": " + i + " : " + x[i]);
            else
                System.out.println(j + ": Absent!");
        }

    }

}
