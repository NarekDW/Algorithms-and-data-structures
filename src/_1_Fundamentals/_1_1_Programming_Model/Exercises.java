package _1_Fundamentals._1_1_Programming_Model;

import common.StdOut;

public class Exercises {
    // 1.1.5
    static boolean isInBetweenOfZeroAndOne(double x, double y) {
        return x > 0 && x < 1 && y > 0 && y < 1;
    }

    // 1.1.6
    static void fibonacci(int n) {
        int f = 0;
        int g = 1;
        for (int i = 0; i < n; i++) {
            System.out.println(f);
            f = f + g;
            g = f - g;
        }
    }

    // 1.1.7
    static double sqrt(double t) {
        double tt = t;
        while (Math.abs(t - tt / t) > 0.001)
            t = (tt / t + t) / 2.0;

        return t;
    }

    static int seqSum(int n) {
        int sum = 0;
        for (int i = 1; i < n; i++)
            for (int j = 0; j < i; j++)
                sum++;
        return sum;
    }

    static int harmonicSeqSum(int n) {
        int sum = 0;
        for (int i = 1; i < n; i *= 2)
            for (int j = 0; j < i; j++)
                sum++;
        return sum;
    }

    // 1.1.9
    static String binaryRepresentation(int n) {
        StringBuilder s = new StringBuilder();
        for (int i = n; i > 0; i /= 2)
            s.insert(0, (i % 2));
        return s.toString();
    }

    // 1.1.13
    static int[][] transposition(int[][] x) {
        int m = x.length;
        int n = x[0].length;
        int[][] result = new int[n][m];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++) {
                result[j][i] = x[i][j];
            }
        return result;
    }

    static void printMatrix(int[][] x) {
        for (int i = 0; i < x.length; i++) {
            for (int j = 0; j < x[0].length; j++) {
                System.out.print(x[i][j] + "  ");
            }
            System.out.println();
        }
    }


    // 1.1.14
    static int lg(int n) {
        int res = 0;
        for (int i = n; i > 1; i /= 2)
            res++;
        return res;
    }

    // 1.1.15
    static int[] histogram(int[] a, int m) {
        int[] result = new int[m];
        for (int i = 0; i < m; i++) {
            int value = 0;
            for (int k : a) if (k == i) value++;
            result[i] = value;
        }

        return result;
    }

    public static String exR1(int n) {
        if (n <= 0) return "";
        return exR1(n - 3) + n + exR1(n - 2) + n;
    }

    public static int mystery(int a, int b) {
        if (b == 0) return 1;
        if (b % 2 == 0) return mystery(a * a, b / 2);
        return mystery(a * a, b / 2) * a;
    }

    public static long F(int N) {
        if (N == 0) return 0;
        if (N == 1) return 1;
        return F(N - 1) + F(N - 2);
    }

    public static long betterF(int n) {
        long f = 0;
        long g = 1;
        for (int i = 0; i < n; i++) {
            f = f + g;
            g = f - g;
        }

        return f;
    }

    public static long betterF2Helper(int n, long first, long second) {
        if (n == 0) return first;
        return betterF2Helper(n - 1, second, first + second);
    }

    public static long betterF2(int n) {
        return betterF2Helper(n, 0, 1);
    }

    // 1.1.20 Write a recursive static method that computes the value of ln(N!)
    static double lnHelper(double n, int step, double result) {
        double part = 1;
        for (int i = 0; i < 2 * step + 1; i++)
            part *= (n - 1.0) / (n + 1.0);
        double localResult = 1.0 / (2 * step + 1) * part;
        if (localResult < 0.000000000000001) return 2 * result;
        else return lnHelper(n, step + 1, result + localResult);
    }

    static double ln(int n) {
        int newN = 1;
        for (int i = 1; i <= n; i++)
            newN *= i;
        return lnHelper(newN, 0, 0);
    }

    // 1.1.22
    static int rankHelper(int key, int[] a, int lo, int hi) {
        if (lo > hi) return -1;
        int mid = (lo + hi) / 2;
        if (key < a[mid]) return rankHelper(key, a, lo, mid - 1);
        else if (key > a[mid]) return rankHelper(key, a, mid + 1, hi);
        else return mid;
    }

    static int rank(int key, int[] a) {
        return rankHelper(key, a, 0, a.length);
    }

    static int euclid(int p, int q) {
        System.out.println("p: " + p + ",  q: " + q);
        if (q == 0) return p;
        int r = p % q;
        return euclid(q, r);
    }

    public static void main(String[] args) {
        int gcd = euclid(15, 25);
        System.out.println(2 % 5);

        int n = 1000000;
        int[] a = new int[n];
        for (int i = 0; i < n; i++)
            a[i] = i;
        int rank = rank(0, a);
//        System.out.println(rank + " : " + a[rank]);

//        System.out.println(ln(4));

//        for (int N = 0; N < 100; N++)
//            StdOut.println(N + " " + F(N));
//            StdOut.println(N + " " + betterF(N));
//            StdOut.println(N + " " + betterF2(N));
    }

//    public static void main(String[] args) {
////        System.out.println(isInBetweenOfZeroAndOne(1, 1));
////        System.out.println(isInBetweenOfZeroAndOne(0, 0));
////        System.out.println(isInBetweenOfZeroAndOne(1, 0.5));
////        System.out.println(isInBetweenOfZeroAndOne(0.5, 1));
////        System.out.println(isInBetweenOfZeroAndOne(0.5, 0.1));
//
////        fibonacci(15);
////        StdOut.printf("%.5f\n", sqrt(36.0));
////        System.out.println(seqSum(5));
////        System.out.println(harmonicSeqSum(10));
////
////        System.out.println((char) ('a' + 4));
//
////        System.out.println(binaryRepresentation(11111));
//
////        int[] a = new int[10];
////        for (int i = 0; i < 10; i++)
////            a[i] = 9 - i;
////        for (int i = 0; i < 10; i++)
////            a[i] = a[a[i]];
////        for (int i = 0; i < 10; i++)
////            System.out.println(a[i]);
//
////        int[][] x = {{11, 11}, {22, 22}, {33, 33}, {44, 44}, {55, 55}};
////        printMatrix(transposition(x));
//
////        System.out.println(lg(10));
////        System.out.println(lg(12));
////        System.out.println(lg(16));
////        System.out.println(lg(20));
////        System.out.println(lg(35));
////        System.out.println(lg(0));
////        System.out.println(lg(1));
////        System.out.println(lg(2));
////        System.out.println(lg(100));
////        System.out.println(lg(512));
//
//
////        int[] a = {1, 1, 2, 1, 3};
////        int[] res = histogram(a, 4);
////        for (int r : res)
////            System.out.print(r + "  ");
//
////        System.out.println(exR1(6));
//
////        System.out.println(mystery(2, 3));;
////        System.out.println(mystery(4, 3));;
////        System.out.println(mystery(5, 4));;
////        System.out.println(mystery(3, 11));;
////        System.out.println(mystery(11, 7));;
//    }
}
