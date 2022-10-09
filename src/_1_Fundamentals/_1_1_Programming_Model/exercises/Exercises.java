package _1_Fundamentals._1_1_Programming_Model.exercises;

import common.StdRandom;

import java.util.function.Function;

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
    public static int[][] transposition(int[][] x) {
        int m = x.length;
        int n = x[0].length;
        int[][] result = new int[n][m];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++) {
                result[j][i] = x[i][j];
            }
        return result;
    }

    // 1.1.14
    static int lg(int n) {
        int res = 0;
        for (int i = n; i > 1; i /= 2)
            res++;
        return res;
    }

    // 1.1.15
    @SuppressWarnings("SameParameterValue")
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
        return rankHelper(key, a, 0, a.length - 1);
    }

    static int euclid(int p, int q) {
        if (q == 0) return p;
        int r = p % q;
        return euclid(q, r);
    }

    public static void main(String[] args) {
        testIsInBetweenOfZeroAndOne();
        fibonacci(6);
        testBinaryRepresentation();
        testTransposition();
        testLg();
        testHistogram();
        System.out.println(exR1(6));
        testMystery();
        testF();
        testBetterF();
        testBetterF2();
        testRank();
        testEuclid();

        System.out.println("seqSum: " + seqSum(5));
        System.out.println("harmonicSeqSum: " + harmonicSeqSum(10));
    }

    private static void testIsInBetweenOfZeroAndOne() {
        for (int i = 0; i < 1000; i++) {
            double x = StdRandom.uniform(0.0, 1.0);
            double y = StdRandom.uniform(0.0, 1.0);
            if (!isInBetweenOfZeroAndOne(x, y))
                throw new RuntimeException(x + " : " + y);
        }

        for (int i = 0; i < 1000; i++) {
            double x = StdRandom.uniform(1.0, 10.0);
            double y = StdRandom.uniform(1.0, 10.0);
            if (isInBetweenOfZeroAndOne(x, y))
                throw new RuntimeException(x + " : " + y);
        }
    }

    private static void testBinaryRepresentation() {
        if (!binaryRepresentation(7).equals("111"))
            throw new RuntimeException();
        if (!binaryRepresentation(6).equals("110"))
            throw new RuntimeException();
        if (!binaryRepresentation(5).equals("101"))
            throw new RuntimeException();
    }

    private static void testTransposition() {
        int[][] x = {{11, 12}, {21, 22}, {31, 32}, {41, 42}, {51, 52}};
        int[][] result = transposition(x);
        int[][] expected = {{11, 21, 31, 41, 51}, {12, 22, 32, 42, 52}};
        for (int i = 0; i < result.length; i++)
            for (int j = 0; j < result[i].length; j++)
                if (result[i][j] != expected[i][j])
                    throw new RuntimeException(result[i][j] + " != " + expected[i][j]);
    }

    private static void testLg() {
        if (lg(1) != 0)
            throw new RuntimeException();
        if (lg(2) != 1)
            throw new RuntimeException();
        if (lg(4) != 2)
            throw new RuntimeException();
        if (lg(8) != 3)
            throw new RuntimeException();
        if (lg(10) != 3)
            throw new RuntimeException();
        if (lg(33) != 5)
            throw new RuntimeException();
    }

    private static void testHistogram() {
        int[] a = {1, 1, 2, 1, 3};
        int[] result = histogram(a, 4);
        int[] expected = {0, 3, 1, 1};
        for (int i = 0; i < expected.length; i++)
            if (result[i] != expected[i])
                throw new RuntimeException();
    }

    private static void testMystery() {
        if (mystery(2, 3) != 8)
            throw new RuntimeException();
        if (mystery(4, 3) != 64)
            throw new RuntimeException();
        if (mystery(5, 4) != 625)
            throw new RuntimeException();
    }

    private static void testF() {
        testFibonacci(Exercises::F);
    }

    private static void testBetterF() {
        testFibonacci(Exercises::betterF);
    }

    private static void testBetterF2() {
        testFibonacci(Exercises::betterF2);
    }

    private static void testFibonacci(Function<Integer, Long> fibonacci) {
        if (fibonacci.apply(0) != 0)
            throw new RuntimeException();
        if (fibonacci.apply(1) != 1)
            throw new RuntimeException();
        if (fibonacci.apply(2) != 1)
            throw new RuntimeException();
        if (fibonacci.apply(5) != 5)
            throw new RuntimeException();
        if (fibonacci.apply(7) != 13)
            throw new RuntimeException();
    }

    private static void testRank() {
        int[] a = new int[100];
        for (int i = 0; i < 100; i++)
            a[i] = i;

        if (rank(55, a) != 55)
            throw new RuntimeException();
        if (rank(555, a) != -1)
            throw new RuntimeException();
    }

    private static void testEuclid() {
        // Greatest common divisor
        if (euclid(15, 25) != 5)
            throw new RuntimeException();
        if (euclid(21, 30) != 3)
            throw new RuntimeException();
    }
}
