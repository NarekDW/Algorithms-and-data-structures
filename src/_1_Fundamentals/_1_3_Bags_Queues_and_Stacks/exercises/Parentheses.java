package _1_Fundamentals._1_3_Bags_Queues_and_Stacks.exercises;

import _1_Fundamentals._1_3_Bags_Queues_and_Stacks.Stack;

/*****************************************************************************************************
 *
 * 1.3.4 Write a stack client Parentheses that reads in a text stream from standard input
 * and uses a stack to determine whether its parentheses are properly balanced. For ex-
 * ample, your program should print true for [()]{}{[()()]()} and false for [(]) .
 *
 ****************************************************************************************************/
public class Parentheses {

    public static void main(String[] args) {
        System.out.println(isBalanced("[()]{}{[()()]()}"));
        System.out.println(isBalanced("[(])"));
    }

    public static boolean isBalanced(String expr) {
        String[] parts = expr.split("");
        Stack<String> b = new Stack<>();

        for (String s : parts) {
            if (s.equals(")") && !b.pop().equals("(")) return false;
            else if (s.equals("]") && !b.pop().equals("[")) return false;
            else if (s.equals("}") && !b.pop().equals("{")) return false;
            else if (s.equals("(") || s.equals("{") || s.equals("[")) b.push(s);
        }

        return b.isEmpty();
    }
}
