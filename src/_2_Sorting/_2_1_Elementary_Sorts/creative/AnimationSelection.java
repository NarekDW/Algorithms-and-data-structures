package _2_Sorting._2_1_Elementary_Sorts.creative;

import common.SortUtils;

import static common.SortUtils.exch;
import static common.SortUtils.less;
import static _2_Sorting._2_1_Elementary_Sorts.creative.Animation.draw;
import static _2_Sorting._2_1_Elementary_Sorts.creative.Animation.drawInitial;

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
public class AnimationSelection {

    public static void sort(Integer[] a) throws InterruptedException {
        int n = a.length;
        drawInitial(a, n, 0);
        int min;
        for (int i = 0; i < n; i++) {
            min = i;
            for (int j = i + 1; j < n; j++)
                if (less(a[j], a[min]))
                    min = j;
            draw(a, min);
            exch(a, i, min);
            Thread.sleep(500);
        }
    }

    public static void main(String[] args) throws InterruptedException {
//        Integer[] x = {2, 4, 6, 8, 10, 12, 14, 15, 13, 11, 9, 7, 5, 3, 1};
        Integer[] x = {15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        sort(x);
        SortUtils.show(x);
    }

}
