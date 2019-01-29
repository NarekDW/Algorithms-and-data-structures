package _1_Fundamentals._1_1_Programming_Model;

public class Fibonacci {

    public static void main(String[] args) {
        System.out.println(fibonacci(5));
        System.out.println(fibonacci(10));

        for (int N = 0; N < 100; N++) {
            System.out.println(N + " = " + F(N));
        }
    }

    public static long F(int N) {
        if (N == 0) return 0;
        if (N == 1) return 1;
        return F(N - 1) + F(N - 2);
    }

    public static long fibonacci(int n) {
        long f = 0;
        long g = 1;
        long[] results = new long[n];
        for (int i = 0; i < n; i++) {
            f = f + g;
            g = f - g;
            results[i] = f;
        }

        return f;
    }

}
