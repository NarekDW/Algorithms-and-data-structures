package _1_Fundamentals._1_4_Analysis_of_Algorithms.creative;

/*****************************************************************************************************
 * <p>
 * 1.4.22 Binary search with only addition and subtraction. [Mihai Patrascu] Write a
 * program that, given an array of N distinct int values in ascending order, determines
 * whether a given integer is in the array. You may use only additions and subtractions
 * and a constant amount of extra memory. The running time of your program should be
 * proportional to log N in the worst case.
 * <p>
 * Answer : Instead of searching based on powers of two (binary search), use Fibonacci
 * numbers (which also grow exponentially). Maintain the current search range to be the
 * interval [i, i + F k ] and keep F k and F k–1 in two variables. At each step compute F k–2 via
 * subtraction, check element i + F k–2 , and update the current range to either [i, i + F k–2 ]
 * or [i + F k–2 , i + F k–2 + F k–1 ].
 *
 ****************************************************************************************************/
public class BinarySearchFibonacci {

    public static int rank(int[] a, int key) {
        return rank(a, key, 0, 1, 0);
    }

    public static int rank(int[] a, int key, int first, int second, int offset) {
        int index = offset + first;
        if (index >= a.length && key > a[a.length - 1] || index < 0 && key < a[0])
            return -1;
        index = Math.min(a.length - 1, index);
        if (key < a[index]) return rank(a, key, second - first, first, offset);
        else if (key > a[index]) return rank(a, key, second, first + second, index);
        else return index;
    }

    public static void main(String[] args) {
        int[] array = new int[10];
        for (int i = 0; i < 10; i++)
            array[i] = i;

        if (rank(array, 0) != 0)
            throw new RuntimeException();
        if (rank(array, 7) != 7)
            throw new RuntimeException();
        if (rank(array, 55) != -1)
            throw new RuntimeException();
        if (rank(array, -7) != -1)
            throw new RuntimeException();
    }

}
