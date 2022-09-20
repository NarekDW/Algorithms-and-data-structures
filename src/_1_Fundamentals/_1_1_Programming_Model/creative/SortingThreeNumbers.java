package _1_Fundamentals._1_1_Programming_Model.creative;

import common.StdRandom;

public class SortingThreeNumbers {

    static class Container {
        int a, b, c;

        public Container(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }

        @Override
        public String toString() {
            return "{a=" + a + ", b=" + b + ", c=" + c + '}';
        }

        public boolean isSortedAsc() {
            return a <= b && b <= c;
        }
    }

    static Container sorting(Container container) {
        int a = container.a;
        int b = container.b;
        int c = container.c;
        int t;

        if (a > b) {
            t = a;
            a = b;
            b = t;
        }
        if (a > c) {
            t = a;
            a = c;
            c = t;
        }
        if (b > c) {
            t = b;
            b = c;
            c = t;
        }

        return new Container(a, b, c);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 1_000_000; i++) {
            int a = StdRandom.uniform(1000);
            int b = StdRandom.uniform(1000);
            int c = StdRandom.uniform(1000);
            Container container = sorting(new Container(a, b, c));
            if (!container.isSortedAsc())
                throw new RuntimeException("Not Sorted: " + container);
        }
    }
}
