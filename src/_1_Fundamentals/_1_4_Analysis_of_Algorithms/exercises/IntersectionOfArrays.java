package _1_Fundamentals._1_4_Analysis_of_Algorithms.exercises;

/*****************************************************************************************************
 *
 * 1.4.12 Write a program that, given two sorted arrays of N int values, prints all ele-
 * ments that appear in both arrays, in sorted order. The running time of your program
 * should be proportional to N in the worst case.
 *
 ****************************************************************************************************/
@Deprecated
// take a look at ../exercises/DuplicateElementsInArrays
public class IntersectionOfArrays {

    // Running time ~ N
    public static void printIntersections(int[] a, int[] b) {
        int i = 0;
        int j = 0;
        while (i < a.length && j < b.length) {
            if (a[i] < b[j]) i++;
            else if (a[i] > b[j]) j++;
            else {
                System.out.println(a[i++]);
                j++;
            }
        }
    }

    // Recursive solution
    public static void intersections(int[] a, int[] b, int i, int j) {
        if (i >= a.length || j >= b.length) {}
        else if (a[i] < b[j]) intersections(a, b, ++i, j);
        else if (a[i] > b[j]) intersections(a, b, i, ++j);
        else {
            System.out.println(a[i]);
            intersections(a, b, ++i, ++j);
        }
    }

    public static void main(String[] args) {
        int a[] = {1, 2, 4, 5, 6};
        int b[] = {2, 3, 5, 7};
        printIntersections(a, b);
        intersections(a, b, 0, 0);
    }

}
