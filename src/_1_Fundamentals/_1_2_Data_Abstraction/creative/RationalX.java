package _1_Fundamentals._1_2_Data_Abstraction.creative;

import common.StdOut;

public class RationalX {
    private final long numerator;
    private final long denominator;

    public RationalX(long numerator, long denominator) {
        long gcd = gcd(numerator, denominator);
        this.numerator = numerator / gcd;
        this.denominator = denominator / gcd;
    }

    RationalX plus(RationalX b) {
        return new RationalX(
                this.numerator * b.denominator + b.numerator * this.denominator,
                this.denominator * b.denominator
        );
    }

    RationalX minus(RationalX b) {
        return new RationalX(
                this.numerator * b.denominator - b.numerator * this.denominator,
                this.denominator * b.denominator
        );
    }

    RationalX times(RationalX b) {
        return new RationalX(
                this.numerator * b.numerator,
                this.denominator * b.denominator
        );
    }

    RationalX divides(RationalX b) {
        return new RationalX(
                this.numerator * b.denominator,
                this.denominator * b.numerator
        );
    }

    long gcd(long a, long b) {
        if (b == 0) return a;
        long r = a % b;
        return gcd(b, r);
    }

    public boolean equals(RationalX that) {
        if (this.numerator != that.numerator) return false;
        return this.denominator == that.denominator;
    }

    @Override
    public String toString() {
        return numerator + "/" + denominator;
    }


    public static void main(String[] args) {
//        RationalX a = new RationalX(2, 4);
//        RationalX b = new RationalX(8, 10);
//        System.out.println(a.plus(b));
//        System.out.println(a.minus(b));
//        System.out.println(a.times(b));
//        System.out.println(a.divides(b));

        RationalX x, y, z;

        // 1/2 + 1/3 = 5/6
        x = new RationalX(1, 2);
        y = new RationalX(1, 3);
        z = x.plus(y);
        StdOut.println(z);

        // 8/9 + 1/9 = 1
        x = new RationalX(8, 9);
        y = new RationalX(1, 9);
        z = x.plus(y);
        StdOut.println(z);

        // 1/200000000 + 1/300000000 = 1/120000000
        x = new RationalX(1, 200000000);
        y = new RationalX(1, 300000000);
        z = x.plus(y);
        StdOut.println(z);

        // 1073741789/20 + 1073741789/30 = 1073741789/12
        x = new RationalX(1073741789, 20);
        y = new RationalX(1073741789, 30);
        z = x.plus(y);
        StdOut.println(z);

        //  4/17 * 17/4 = 1
        x = new RationalX(4, 17);
        y = new RationalX(17, 4);
        z = x.times(y);
        StdOut.println(z);

        // 3037141/3247033 * 3037547/3246599 = 841/961
        x = new RationalX(3037141, 3247033);
        y = new RationalX(3037547, 3246599);
        z = x.times(y);
        StdOut.println(z);

        // 1/6 - -4/-8 = -1/3
        x = new RationalX(1, 6);
        y = new RationalX(-4, -8);
        z = x.minus(y);
        StdOut.println(z);
    }
}
