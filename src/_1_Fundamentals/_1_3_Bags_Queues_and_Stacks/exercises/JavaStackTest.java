package _1_Fundamentals._1_3_Bags_Queues_and_Stacks.exercises;

import common.StdOut;

import java.util.Stack;


/*****************************************************************************************************
 * <p>
 * 1.3.2 Give the output printed by java Stack for the input
 *     it was - the best - of times - - - it  was - the - -
 * <p>
 * Answer: was best times of the was the it (1 left on queue)
 ****************************************************************************************************/
public class JavaStackTest {

    public static void main(String[] args) {
        Stack<String> s = new Stack<>();
        String[] words = "it was - the best - of times - - - it  was - the - -".split("\\s+");
        for (String item : words) {
            if (!item.equals("-"))
                s.push(item);
            else if (!s.isEmpty()) StdOut.print(s.pop() + " ");
        }

        StdOut.println("(" + s.size() + " left on queue)");
    }

}
