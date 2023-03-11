package _3_Searching._3_5_Searching_Applications.exercises;

import _1_Fundamentals._1_3_Bags_Queues_and_Stacks.Bag;
import _3_Searching._3_2_Binary_Search_Trees.BST;

/*****************************************************************************************************
 * <p>
 * 3.5.14 Develop and test a static method invert() that takes as argument an
 * ST<String, Bag<String>> and produces as return value the inverse of the given
 * symbol table (a symbol table of the same type).
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

    public static BST<Integer, String> invertTree(BST<Integer, String> that) {
        that.invertTree();
        return that;
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

        // Invert tree
        BST<Integer, String> bst = new BST<>();
        bst.put(10, "10");
        bst.put(4, "4");
        bst.put(6, "6");
        bst.put(3, "3");

        bst.put(12, "12");
        bst.put(11, "11");
        bst.put(13, "13");

        System.out.println(bst);

        bst = invertTree(bst);
        System.out.println(bst);
    }

}
