package _2_Sorting._2_1_Elementary_Sorts.creative;

import common.SortUtils;

import static _2_Sorting._2_1_Elementary_Sorts.creative.Animation.draw;
import static _2_Sorting._2_1_Elementary_Sorts.creative.Animation.drawInitial;
import static common.SortUtils.exch;
import static common.SortUtils.less;


/*****************************************************************************************************
 * <p>
 * 2.1.17 Animation. Add code to Insertion and Selection to make them draw the
 * array contents as vertical bars like the visual traces in this section, redrawing the bars
 * after each pass, to produce an animated effect, ending in a “sorted” picture where the
 * bars appear in order of their height. Hint : Use a client like the one in the text that generates
 * random Double values, insert calls to show() as appropriate in the sort code, and
 * implement a show() method that clears the canvas and draws the bars.
 *
 ****************************************************************************************************/
public class AnimationInsertion {

    public static void sort(Integer[] a) throws InterruptedException {
        int n = a.length;
        drawInitial(a, n, 1);
        for (int i = 1; i < n; i++) {
            for (int j = i; j > 0 && less(a[j], a[j - 1]); j--) {
                exch(a, j, j - 1);
                //noinspection BusyWait
                Thread.sleep(200);
                draw(a, j - 1);
            }

        }
    }

    public static void main(String[] args) throws InterruptedException {
//        Integer[] x = {2, 4, 6, 8, 10, 12, 14, 15, 13, 11, 9, 7, 5, 3, 1};
        Integer[] x = {15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        sort(x);
        SortUtils.show(x);
    }

}
