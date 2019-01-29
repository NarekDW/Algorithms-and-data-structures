package _1_Fundamentals._1_4_Analysis_of_Algorithms.creative;

import common.StdRandom;

/*****************************************************************************************************
 *
 * 1.4.34 Hot or cold. Your goal is to guess a secret integer between 1 and N. You repeat-
 * edly guess integers between 1 and N. After each guess you learn if your guess equals the
 * secret integer (and the game stops). Otherwise, you learn if the guess is hotter (closer to)
 * or colder (farther from) the secret number than your previous guess. Design an algo-
 * rithm that finds the secret number in at most ~2 lg N guesse
 *
 ****************************************************************************************************/
public class HotOrCold {

    private final int[] x;
    private final int secret;

    public HotOrCold(int[] x) {
        this.x = x;
        this.secret = StdRandom.uniform(x.length);
    }

    public int findSecret() {
        return findSecret(0, x.length - 1);
    }

    // ~ 2*log(N)
    public int findSecret(int lo, int hi) {
        if (lo > hi) return -1;
        int mid = lo + (hi - lo) / 2;
        int midDiff = diff(x[mid]);

        if (midDiff == 0) return mid;
        else if (mid == 0) return -1;
        else if (diff(x[mid - 1]) - midDiff < 0) return findSecret(lo, mid - 1);
        else return findSecret(mid + 1, hi);
    }

    // ??? ~ log(N)
//    public int findSecretFast(int lo, int hi, int prevDiff) {}

    private int diff(int x) {
        return Math.abs(secret - x);
    }


    public static void main(String[] args) {
        int[] x = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        HotOrCold hotOrCold = new HotOrCold(x);
        int secret = hotOrCold.findSecret();
        System.out.println(secret);
        System.out.println("answ: " + hotOrCold.secret);
    }
}
