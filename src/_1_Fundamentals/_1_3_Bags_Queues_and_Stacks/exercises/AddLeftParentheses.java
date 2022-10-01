package _1_Fundamentals._1_3_Bags_Queues_and_Stacks.exercises;

import _1_Fundamentals._1_3_Bags_Queues_and_Stacks.Stack;

/*****************************************************************************************************
 * <p>
 * 1.3.9 Write a program that takes from standard input an expression without left parentheses
 * and prints the equivalent infix expression with the parentheses inserted.
 * For example, given the input:
 *  1 + 2 ) * 3 - 4 ) * 5 - 6 ) ) )
 * your program should print
 *  ( ( 1 + 2 ) * ( ( 3 - 4 ) * ( 5 - 6 ) )
 *
 ****************************************************************************************************/
public class AddLeftParentheses {

    private static String constructPart(String operation, Stack<String> operands) {
        String second = operands.pop();
        String first = operands.pop();
        return '(' + first + operation + second + ')';
    }

    public static String addLeftParentheses(String expression) {
        char[] chars = expression.replaceAll("\\s+","").toCharArray();
        Stack<Character> operations = new Stack<>();
        Stack<String> operands = new Stack<>();
        for (char c : chars) {
            if (c == ')') {
                String part = constructPart(operations.pop().toString(), operands);
                operands.push(part);
            } else if (c == '+' || c == '-' || c == '*' || c == '\\') {
                operations.push(c);
            } else operands.push(String.valueOf(c));
        }

        for (Character operation : operations)
            operands.push(constructPart(operation.toString(), operands));

        StringBuilder result = new StringBuilder();
        for (String operand : operands) {
            result.append(operand);
        }

        return result.toString();
    }

    public static void main(String[] args) {
        String example = addLeftParentheses("1 + 2 ) * 3 - 4 ) * 5 - 6 ) ) )");
        System.out.println(example);
    }

}
