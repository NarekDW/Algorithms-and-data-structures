package _3_Searching;


import _3_Searching._3_1_Elementary_Symbol_Tables.exercises.ArrayST;
import _3_Searching._3_1_Elementary_Symbol_Tables.exercises.ItemBinarySearchST;
import _3_Searching._3_1_Elementary_Symbol_Tables.exercises.OrderedSequentialSearchST;
import _3_Searching._3_2_Binary_Search_Trees.experiments.ArrayRepresentationBST;

public class SymbolTableTest {

    public static void testST(SymbolTable<Integer, String> st) {
        for (int i = 1; i <= 10; i++)
            st.put(i, "text " + i);

        testSymbolTable(st);

        for (int i = 10; i > 0; i--)
            st.put(i, "text " + i);

        testSymbolTable(st);

        st.put(6, "X");
        st.put(4, "X");
        st.put(8, "X");
        st.put(2, "X");
        st.put(5, "X");
        st.put(7, "X");
        st.put(9, "X");
        st.put(10, "X");
        st.put(1, "X");
        st.put(3, "X");

        testSymbolTable(st);

        System.out.println("Test completed successfully!");
    }

    private static void testSymbolTable(SymbolTable<Integer, String> st) {
        for (int i = 1; i <= 10; i++)
            if (!st.contains(i))
                throw new RuntimeException();

        st.delete(5);
        String absent = st.get(5);
        if (absent != null)
            throw new RuntimeException();

        st.delete(6);
        st.delete(7);
        st.delete(8);
        st.delete(4);
        st.delete(3);
        st.delete(2);

        if (st.size() != 3)
            throw new RuntimeException(String.valueOf(st.size()));

        st.keys().forEach(System.out::println);

        st.delete(1);
        st.delete(9);
        st.delete(10);

        if (!st.isEmpty())
            throw new RuntimeException();
    }

    public static void main(String[] args) {
        testST(new ArrayRepresentationBST<>(10));
        testST(new ArrayST<>(10));
        testST(new ItemBinarySearchST<>(10));
        testST(new OrderedSequentialSearchST<>());

        // TODO: 06.01.2023 Fix size issue
//        testST(new BST<>());
//        testST(new BSTNonRecursive<>());
    }
}
