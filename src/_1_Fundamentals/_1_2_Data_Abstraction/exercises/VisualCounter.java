package _1_Fundamentals._1_2_Data_Abstraction.exercises;

import common.StdDraw;
import common.StdRandom;

/*****************************************************************************************************
 * <p>
 * 1.2.10 Develop a class VisualCounter that allows both increment and decrement operations.
 * Take two arguments N and max in the constructor, where N specifies the maximum number of operations
 * and max specifies the maximum absolute value for the counter.
 * As a side effect, create a plot showing the value of the counter each time its tally changes.
 *
 ****************************************************************************************************/
public class VisualCounter {

    private int count = 0;
    private int n;
    private final int max;

    public VisualCounter(int n, int max) {
        this.n = n;
        this.max = max;
        StdDraw.setXscale(0, n);
        StdDraw.setYscale(-max - 10, max + 10);
        StdDraw.setPenRadius(.005);
    }

    public void increment() {
        if (isAllowed()) {
            n--;
            count++;
        }

        draw();
    }

    public void decrement() {
        if (isAllowed()) {
            n--;
            count--;
        }

        draw();
    }

    private boolean isAllowed() {
        if (n <= 0) {
            System.out.println("Limit of operations is exceeded");
            return false;
        }

        if (Math.abs(count) >= max) {
            System.out.println("Maximum value is reached: " + max);
            return false;
        }

        return true;
    }

    private void draw() {
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.point(n, count);
    }

    public static void main(String[] args) {
        int n = 1000;
        VisualCounter visualCounter = new VisualCounter(n, 50);
        for (int i = 0; i < n; i++) {
            if (StdRandom.bernoulli())
                visualCounter.increment();
            else visualCounter.decrement();
        }
    }
}
