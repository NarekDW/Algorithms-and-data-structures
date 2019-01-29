package _2_Sorting._2_1_Elementary_Sorts.experiments;

public class InsertionPrimitives {

    public static void sort(int[] a) {
        for (int i = 1; i < a.length; i++)
            for (int j = i; j > 0 && a[j] < a[j - 1]; j--) {
                int tmp = a[j];
                a[j] = a[j - 1];
                a[j - 1] = tmp;
            }
    }

    public static void main(String[] args) {
        int[] x = {3, 1, 2, 7, 6, 4, 2, 1};
        sort(x);
        for (int i : x) {
            System.out.print(i + " ");
        }
    }

}
