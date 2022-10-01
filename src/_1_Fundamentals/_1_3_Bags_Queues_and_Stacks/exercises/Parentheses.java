package _1_Fundamentals._1_3_Bags_Queues_and_Stacks.exercises;

import _1_Fundamentals._1_3_Bags_Queues_and_Stacks.Stack;

/*****************************************************************************************************
 * <p>
 * 1.3.4 Write a stack client Parentheses that reads in a text stream from standard input
 * and uses a stack to determine whether its parentheses are properly balanced.
 * For example, your program should print true for [()]{}{[()()]()} and false for [(]) .
 *
 ****************************************************************************************************/
public class Parentheses {

    public static void main(String[] args) {
        System.out.println(isBalanced("[()]{}{[()()]()}"));
        System.out.println(isBalanced("[(])"));
    }

    public static boolean isBalanced(String expr) {
        char[] parts = expr.toCharArray();
        Stack<Character> b = new Stack<>();

        for (char c : parts) {
            if (c == ')' && b.pop() != '(') return false;
            else if (c == ']' && b.pop() != '[') return false;
            else if (c == '}' && b.pop() != '{') return false;
            else if (c == '(' || c == '{' || c == '[') b.push(c);
        }

        return b.isEmpty();
    }
}
