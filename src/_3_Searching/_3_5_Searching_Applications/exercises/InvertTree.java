package _3_Searching._3_5_Searching_Applications.exercises;

import _1_Fundamentals._1_3_Bags_Queues_and_Stacks.Bag;
import _3_Searching._3_2_Binary_Search_Trees.BST;

/*****************************************************************************************************
 *
 * 3.5.14 Develop and test a static method invert() that takes as argument an
 * ST<String, Bag<String>> and produces as return value the inverse of the given sym-
 * bol table (a symbol table of the same type).
 *
 ****************************************************************************************************/
public class InvertTree {

    public static BST<String, Bag<String>> invert(BST<String, Bag<String>> that) {
        BST<String, Bag<String>> inv = new BST<>();
        for (String key : that.keys()) {
            Bag<String> bag = that.get(key);
            for (String val : bag) {
                if (!inv.contains(val))
                    inv.put(val, new Bag<>());
                inv.get(val).add(key);
            }
        }

        return inv;
    }

    public static void main(String[] args) {
        BST<String, Bag<String>> st = new BST<>();
        Bag<String> bag = new Bag<>();
        bag.add("xxx");
        bag.add("yyy");
        bag.add("zzz");

        Bag<String> bag2 = new Bag<>();
        bag2.add("xxx");
        bag2.add("qqq");
        bag2.add("fff");



        st.put("a", bag);
        st.put("b", bag);
        st.put("c", bag2);

        BST<String, Bag<String>> invert = invert(st);
        for (String s : invert.keys()) {
            System.out.print(s + " : ");
            invert.get(s).forEach(x -> System.out.print(x + ", "));
            System.out.println();
        }
    }

}
