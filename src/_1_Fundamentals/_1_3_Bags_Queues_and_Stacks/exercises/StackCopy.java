package _1_Fundamentals._1_3_Bags_Queues_and_Stacks.exercises;

import _1_Fundamentals._1_3_Bags_Queues_and_Stacks.Stack;

/*****************************************************************************************************
 * <p>
 * 1.3.12 Write an iterable Stack client that has a static method copy() that takes a stack of strings
 * as argument and returns a copy of the stack.
 * Note: This ability is a prime example of the value of having an iterator,
 * because it allows development of such functionality without changing the basic API.
 ****************************************************************************************************/
public class StackCopy {

    public static <T> Stack<T> copy(Stack<T> items) {
        Stack<T> reverse = new Stack<>();
        for (T item : items)
            reverse.push(item);

        Stack<T> result = new Stack<>();
        for (T t : reverse)
            result.push(t);

        return result;
    }

    public static void main(String[] args) {
        Stack<String> numbers = new Stack<>();
        for (int i = 0; i < 10; i++)
            numbers.push(String.valueOf(i));

        Stack<String> copy = copy(numbers);
        while (!numbers.isEmpty())
            System.out.print(numbers.pop() + " ");
        System.out.println();
        while (!copy.isEmpty())
            System.out.print(copy.pop() + " ");

    }
}
