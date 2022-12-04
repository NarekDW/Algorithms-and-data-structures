package _2_Sorting;

import static _2_Sorting.SortCompare.run;

public class SystemVsQuick {

    public static void main(String[] args) {
        // System : 37.934999999999995
        // Quick : 40.672000000000025
        // For 1000000 random Doubles System is 1.072 times faster than Quick
        run("System", "Quick", 1_000_000, 100, false);

        // For 1000000 random Doubles System is 117.779 times faster than Quick
        run("System", "Quick", 1_000_000, 100, true);
    }

}
