package _1_Fundamentals._1_3_Bags_Queues_and_Stacks;

public class Evaluate {

    public static void main(String[] args) {
        Double res = evaluate("( 1 + ( ( 2 + 3 ) * ( 4 * 5 ) ) )");
        System.out.println(res);

        Stack<Integer> ints = new Stack<>();
        ints.push(1);
        ints.push(2);
        ints.push(3);
        ints.push(4);
        ints.push(5);
        ints.push(6);

        for (Integer integer : ints) {
            System.out.println(integer);
        }
    }

    /**
     * Evaluates (fully parenthesized) arithmetic expressions using
     * Dijkstra's two-stack algorithm.
     */
    public static Double evaluate(String expression) {
//        FixedCapacityStack<String> ops = new FixedCapacityStack<>(2);
//        FixedCapacityStack<Double> vals = new FixedCapacityStack<>(2);
        Stack<String> ops = new Stack<>();
        Stack<Double> vals = new Stack<>();

        String[] items = expression.split("");
        for (String s : items) {
            if (s.equals("(") || s.equals(" ")) continue;
            if (s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/")) ops.push(s);
            else if (s.equals(")")) {
                String op = ops.pop();
                Double x = vals.pop();
                Double y = vals.pop();
                if (op.equals("+")) vals.push(x + y);
                if (op.equals("-")) vals.push(x - y);
                if (op.equals("*")) vals.push(x * y);
                if (op.equals("/")) vals.push(x / y);
            } else
                vals.push(Double.parseDouble(s));
        }

        return vals.pop();
    }

}
