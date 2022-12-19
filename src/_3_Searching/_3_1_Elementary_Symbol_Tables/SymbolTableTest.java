package _3_Searching._3_1_Elementary_Symbol_Tables;


public class SymbolTableTest {

    public static void testST(SymbolTable<Integer, String> st) {
        for (int i = 1; i <= 10; i++)
            st.put(i, "text " + i);

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
            throw new RuntimeException();

        st.keys().forEach(System.out::println);

        st.delete(1);
        st.delete(9);
        st.delete(10);

        if (!st.isEmpty())
            throw new RuntimeException();
    }

}
