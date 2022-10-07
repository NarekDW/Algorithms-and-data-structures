package _1_Fundamentals._1_4_Analysis_of_Algorithms.exercises;

/*****************************************************************************************************
 * <p>
 * 1.4.2 Modify ThreeSum to work properly even when the int values are so large
 * that adding two of them might cause overflow.
 *
 ****************************************************************************************************/
public class ThreeSumWithoutOverflow {

    public static int count(int[] a) {
        int n = a.length;
        int count = 0;
        for (int i = 0; i < n; i++)
            for (int j = i + 1; j < n; j++)
                for (int k = j + 1; k < n; k++)
                    if (isSumZero(a[i], a[j], a[k]))
                        count++;
        return count;
    }

    public static boolean isSumZero(int a, int b, int c) {
        int[] sortedValues = sortTriple(a, b, c);
        int max = sortedValues[0];
        int mid = sortedValues[1];
        int min = sortedValues[2];
        if (min > 0 || max < 0)
            return false;
        return mid == -1 * (min + max);
    }

    private static int[] sortTriple(int a, int b, int c) {
        if (a < b)
            return sortTriple(b, a, c);
        else if (a < c)
            return sortTriple(c, b, a);
        else if (b < c)
            return sortTriple(a, c, b);
        else
            return new int[]{a, b, c};
    }


    public static void main(String[] args) {
        checkFalse(new int[]{1, Integer.MAX_VALUE, -5}, 1);
        checkFalse(new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE, -5}, 2);
        checkFalse(new int[]{Integer.MIN_VALUE, Integer.MIN_VALUE, 10}, 3);

        int[] a = {Integer.MIN_VALUE, 1, Integer.MAX_VALUE};
        if (count(a) != 1)
            throw new RuntimeException();
    }

    private static void checkFalse(int[] a, int attempt) {
        if (count(a) != 0)
            throw new RuntimeException(String.valueOf(attempt));
    }
}
