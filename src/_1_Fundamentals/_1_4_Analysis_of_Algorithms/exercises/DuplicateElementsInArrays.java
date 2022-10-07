package _1_Fundamentals._1_4_Analysis_of_Algorithms.exercises;

import java.util.function.BiFunction;

/*****************************************************************************************************
 * <p>
 * 1.4.12 Write a program that, given two sorted arrays of N int values, prints all
 * elements that appear in both arrays, in sorted order. The running time of your program
 * should be proportional to N in the worst case.
 *
 ****************************************************************************************************/
public class DuplicateElementsInArrays {

    public static int[] findDuplicates(int[] firstArray, int[] secondArray) {
        int length = Math.min(firstArray.length, secondArray.length);
        int[] duplicates = new int[length];
        int resultIndex = 0;
        for (int j = 0, i = 0; i < length && j < length; ) {
            int firstArrayItem = firstArray[i];
            int secondArrayItem = secondArray[j];
            if (firstArrayItem == secondArrayItem) {
                duplicates[resultIndex++] = firstArrayItem;
                i++;
                j++;
            } else if (firstArrayItem > secondArrayItem) {
                int[] tmp = firstArray;
                firstArray = secondArray;
                secondArray = tmp;
                j++;
            } else {
                i++;
            }
        }

        int[] result = new int[resultIndex];
        //noinspection ManualArrayCopy
        for (int i = 0; i < resultIndex; i++)
            result[i] = duplicates[i];

        return result;
    }

    private static int[] findDuplicatesRecursiveHelper(int[] firstArray, int firstArrayIndex,
                                                       int[] secondArray, int secondArrayIndex,
                                                       int[] result, int resultIndex, int length) {
        if (firstArrayIndex >= length || secondArrayIndex >= length) {
            int[] fixedResult = new int[resultIndex];
            System.arraycopy(result, 0, fixedResult, 0, resultIndex);
            return fixedResult;
        }

        int firstArrayItem = firstArray[firstArrayIndex];
        int secondArrayItem = secondArray[secondArrayIndex];
        if (firstArrayItem == secondArrayItem) {
            result[resultIndex] = firstArrayItem;
            return findDuplicatesRecursiveHelper(firstArray, firstArrayIndex + 1,
                    secondArray, secondArrayIndex + 1, result, resultIndex + 1, length);
        } else if (firstArrayItem > secondArrayItem) {
            return findDuplicatesRecursiveHelper(secondArray, secondArrayIndex + 1,
                    firstArray, firstArrayIndex, result, resultIndex, length);
        } else {
            return findDuplicatesRecursiveHelper(firstArray, firstArrayIndex + 1,
                    secondArray, secondArrayIndex, result, resultIndex, length);
        }
    }


    public static int[] findDuplicatesRecursive(int[] firstArray, int[] secondArray) {
        int length = Math.min(firstArray.length, secondArray.length);
        int[] result = new int[length];
        return findDuplicatesRecursiveHelper(firstArray, 0,
                secondArray, 0, result, 0, length);
    }


    public static void main(String[] args) {
        System.out.println("Test DuplicateElementsInArrays::findDuplicates");
        testFunction(DuplicateElementsInArrays::findDuplicates);
        System.out.println("Test DuplicateElementsInArrays::findDuplicatesRecursive");
        testFunction(DuplicateElementsInArrays::findDuplicatesRecursive);
    }

    private static void testFunction(BiFunction<int[], int[], int[]> function) {
        int[] a = new int[10];
        int[] b = new int[10];
        for (int i = 0; i < 10; i++)
            a[i] = i + 1;
        for (int i = 0; i < 10; i++)
            b[i] = i + 5;
        int[] duplicates = function.apply(a, b);

        for (int i = 0; i < duplicates.length; i++)
            if (duplicates[i] != i + 5)
                throw new RuntimeException();

        int[] c = new int[30];
        int[] d = new int[20];
        for (int i = 0; i < 30; i++)
            c[i] = i + 100;
        for (int i = 0; i < 10; i++)
            d[i] = i;
        duplicates = function.apply(c, d);
        if (duplicates.length != 0)
            throw new RuntimeException();

        int[] e = new int[10];
        int[] f = new int[10];
        for (int i = 0; i < 10; i++) {
            e[i] = i;
            f[i] = i;
        }
        duplicates = function.apply(e, f);
        if (duplicates.length != 10)
            throw new RuntimeException();
        for (int i = 0; i < 10; i++)
            if (duplicates[i] != i)
                throw new RuntimeException();
    }
}
