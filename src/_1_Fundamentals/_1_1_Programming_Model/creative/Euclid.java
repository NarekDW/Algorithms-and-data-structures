package _1_Fundamentals._1_1_Programming_Model.creative;

/*****************************************************************************************************
 *
 * 1.1.24 Give the sequence of values of p and q that are computed when Euclid’s algo-
 * rithm is used to compute the greatest common divisor of 105 and 24. Extend the code
 * given on page 4 to develop a program Euclid that takes two integers from the command
 * line and computes their greatest common divisor, printing out the two arguments for
 * each call on the recursive method. Use your program to compute the greatest common
 * divisor or 1111111 and 1234567.
 *
 ****************************************************************************************************/
public class Euclid {

    public static void main(String[] args) {
        int gcd = gcd(1111111, 1234567);
        System.out.println("gcd = " + gcd);
    }

    public static int gcd(int p, int q) {
        System.out.printf("%s : %s\n", p, q);
        if (q == 0) return p;
        return gcd(q, p % q);
    }
}
