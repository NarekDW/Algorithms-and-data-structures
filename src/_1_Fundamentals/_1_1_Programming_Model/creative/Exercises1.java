package _1_Fundamentals._1_1_Programming_Model.creative;

public class Exercises1 {
    public static int x = 11;

    public static void main(String[] args) {
        //1
        double t = 9.0;
        while (Math.abs(t - 9.0 / t) > .001)
            t = (9.0 / t + t) / 2.0;
        System.out.printf("%.5f\n", t);

        //2
        int sum = 0;
        for (int i = 1; i < 1000; i *= 2) {
            for (int j = 0; j < i; j++) {
                sum++;
            }
        }
        System.out.println("sum = " + sum);
        System.out.println((char) ('a' + 4));

        System.out.println();
        System.out.println("decimalToBinaryString: " + decimalToBinaryString(75));

        /*****************************************************************************************************
         *
         * 1.1.11 Write a code fragment that prints the contents of a two-dimensional boolean
         * array, using * to represent true and a space to represent false . Include row and column
         * numbers.
         *
         ****************************************************************************************************/
        System.out.println();
        boolean[][] b = {
                {true, true, true},
                {true, false, true},
                {true, false, false}
        };
        show2xBooleanMatrix(b);


        /*****************************************************************************************************
         *
         * 1.1.12 What does the following code fragment print?
         *
         * int[] a = new int[10];
         * for (int i = 0; i < 10; i++)
         * a[i] = 9 - i;
         * for (int i = 0; i < 10; i++)
         * a[i] = a[a[i]];
         * for (int i = 0; i < 10; i++)
         * System.out.println(i);
         *
         ****************************************************************************************************/
        mutable2xMatrix();

        System.out.println();
        System.out.println("transponir(matrix):");
        Integer[][] matrix = {
                {1, 2, 3},
                {4, 5, 6}
        };
        transponir(matrix);

        System.out.println("exR1: ");
        System.out.println("exR1(6): " + exR1(6));

        System.out.println("mystery:");
        /*
        * = a^b
        + = a * b
         */
//        System.out.println(mystery(2, 25));
//        System.out.println(mystery(3, 11));
//        System.out.println(mystery(1, 2));
//        System.out.println(mystery(3, 2));
        System.out.println(mystery(4, 5));
        System.out.println(mystery(6, 3));
//        System.out.println(mystery(3, 3));
//        System.out.println(mystery(5, 5));
//        System.out.println(mystery(10, 4));
    }

    // How does it work????????????
    public static int mystery(int a, int b) {
        if (b == 0) return 1;
        if (b % 2 == 0) return mystery(a * a, b/2);
        return mystery(a * a, b/2) * a;
    }

    private static String exR1(int n) {
        if (n <= 0) return "";
        return exR1(n - 3) + n + exR1(n - 2) + n;
    }

    private static String decimalToBinaryString(int x) {
        String s = "";
        for (int n = x; n > 0; n /= 2)
            s = (n % 2) + s;

        return s;
    }

    private static void show2xBooleanMatrix(boolean[][] b) {
        for (int i = 0; i < b.length; i++) {
            if (i == 0) {
                for (int j = 0; j < b[0].length; j++) {
                    System.out.printf("%3d", j);
                }
                System.out.println();
            }

            System.out.printf("%d ", i);
            for (int j = 0; j < b[i].length; j++) {
                if (j == 0) {
                    if (b[i][j] == true) System.out.printf("%1s", "*");
                    else System.out.printf("%1s", " ");
                } else {
                    if (b[i][j] == true) System.out.printf("%3s", "*");
                    else System.out.printf("%3s", " ");
                }
            }

            System.out.println();
        }
    }

    private static void mutable2xMatrix() {
        int[] a = new int[10];
        for (int i = 0; i < 10; i++)
            a[i] = 9 - i;
        System.out.println("1: a[i]:");
        for (int i = 0; i < 10; i++)
            System.out.print(a[i]);

        System.out.println();

        for (int i = 0; i < 10; i++)
            a[i] = a[a[i]];
        System.out.println("a[i]:");
        for (int i = 0; i < 10; i++)
            System.out.print(a[i]);
    }

    static void transponir(Object[][] matrix) {
        final int h = matrix.length;
        final int x = matrix[0].length;

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < h; j++) {
                System.out.print(matrix[j][i]);
            }
            System.out.println();
        }

        System.out.println();
        System.out.println("lg: " + lg(16));
    }

    static int lg(int n) {
        int res = 0;
        while (n/2 > 0) {
            n = n/2;
            res++;
        }
        return res;
    }
}
