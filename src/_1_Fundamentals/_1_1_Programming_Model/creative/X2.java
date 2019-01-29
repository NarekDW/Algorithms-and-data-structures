package _1_Fundamentals._1_1_Programming_Model.creative;

import static java.lang.Math.E;

public class X2 {

    public static void main(String[] args) {
        System.out.println("lnFactorial(5): " + lnFactorial(5));
        // Implementation ???
        Math.log(120);

        prettyPrint("Narek", 12.12235, 3.2332423);
    }

    public static double lnFactorial(int N) {
        if (N == 1) return 0;
        if (N == E) return 1;
        return Math.log(N) + lnFactorial(N - 1);
    }

    public static void prettyPrint(String name, double a, double b) {
        System.out.printf("%s %f %f %.3f", name, a, b, a/b);
    }
}
