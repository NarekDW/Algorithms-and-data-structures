package _1_Fundamentals._1_4_Analysis_of_Algorithms.creative;

import _1_Fundamentals._1_4_Analysis_of_Algorithms.StopwatchCPU;
import common.StdRandom;

/*****************************************************************************************************
 * <p>
 * 1.4.34 Hot or cold. Your goal is to guess a secret integer between 1 and N. You repeatedly
 * guess integers between 1 and N. After each guess you learn if your guess equals the
 * secret integer (and the game stops). Otherwise, you learn if the guess is hotter (closer to)
 * or colder (farther from) the secret number than your previous guess.
 * Design an algorithm that finds the secret number in at most ~2 lg N guesse
 *
 ****************************************************************************************************/
public class HotOrCold {

    private enum Direction {
        LEFT, RIGHT, UNDEFINED;

        Direction opposite() {
            if (this.equals(LEFT))
                return RIGHT;
            return LEFT;
        }

        boolean isLeft() {
            return this.equals(LEFT);
        }
    }

    private final int[] x;
    private final int secret;

    public HotOrCold(int[] x) {
        this.x = x;
        this.secret = StdRandom.uniform(x.length);
    }

    public int findSecret() {
        return findSecret(0, x.length);
    }

    // ~ 2*log(N)
    public int findSecret(int lo, int hi) {
        if (lo >= hi) return -1;
        int mid = lo + (hi - lo) / 2;
        int midDiff = diff(x[mid]);
        if (midDiff == 0) return mid;
        else if (diff(x[mid - 1]) < midDiff) return findSecret(lo, mid);
        else return findSecret(mid + 1, hi);
    }

    // ~ log(N)
    public int findSecretFast() {
        return findSecretFast(0, x.length - 1, 0, Direction.UNDEFINED);
    }

    public int findSecretFast(int lo, int hi, int prevDiff, Direction direction) {
        if (lo > hi) return -1;
        int mid = lo + (hi - lo) / 2;
        int midDiff = diff(x[mid]);
        if (midDiff == 0) return mid;

        if (direction.equals(Direction.UNDEFINED)) {
            if (diff(x[mid - 1]) < midDiff)
                return findSecretFast(lo, mid - 1, midDiff, Direction.LEFT);
            else
                return findSecretFast(mid + 1, hi, midDiff, Direction.RIGHT);
        }

        if (midDiff < prevDiff) {
            if (direction.isLeft())
                return findSecretFast(lo, mid + 1, midDiff, direction);
            else
                return findSecretFast(mid + 1, hi, midDiff, direction);
        } else {
            int d = hi - mid;
            if (direction.isLeft())
                return findSecretFast(mid + 1, mid + 2 * d, prevDiff, direction.opposite());
            else
                return findSecretFast(mid - 2 * d, mid - 1, prevDiff, direction.opposite());
        }
    }

    private int diff(int x) {
        return Math.abs(secret - x);
    }


    public static void main(String[] args) {
        testFindSecret();
        testFindSecretFast();
        testPerformance();
    }

    private static void testFindSecret() {
        int[] x = new int[1000];
        for (int i = 0; i < 1000; i++)
            x[i] = i;
        for (int i = 0; i < 1000; i++) {
            HotOrCold hotOrCold = new HotOrCold(x);
            int secretIndex = hotOrCold.findSecret();
            if (x[secretIndex] != hotOrCold.secret)
                throw new RuntimeException();
        }
    }

    private static void testFindSecretFast() {
        int arraySize = 1001;
        int[] x = new int[arraySize];
        for (int i = 0; i < arraySize; i++)
            x[i] = i;
        for (int i = 0; i < 100_000; i++) {
            HotOrCold hotOrCold = new HotOrCold(x);
            int secretIndex = hotOrCold.findSecretFast();
            if (secretIndex == -1)
                System.out.println(hotOrCold.secret);
            if (x[secretIndex] != hotOrCold.secret)
                throw new RuntimeException();
        }
    }

    private static void testPerformance() {
        int[] x = new int[10_001];
        for (int i = 0; i < 10_001; i++)
            x[i] = i;
        System.out.println("Start findSecret function performance test:");
        StopwatchCPU stopwatchCPU = new StopwatchCPU();
        for (int i = 0; i < 1_000_000; i++) {
            HotOrCold hotOrCold = new HotOrCold(x);
            int secretIndex = hotOrCold.findSecret();
            if (x[secretIndex] != hotOrCold.secret)
                throw new RuntimeException();
        }
        System.out.println(stopwatchCPU.elapsedTime() + " sec.");

        System.out.println("Start findSecretFast function performance test:");
        StopwatchCPU stopwatchCPU2 = new StopwatchCPU();
        for (int i = 0; i < 1_000_000; i++) {
            HotOrCold hotOrCold = new HotOrCold(x);
            int secretIndex = hotOrCold.findSecretFast();
            if (x[secretIndex] != hotOrCold.secret)
                throw new RuntimeException();
        }
        System.out.println(stopwatchCPU2.elapsedTime() + " sec.");
    }
}
