package _4_Graphs._4_1_Undirected_Graphs;

import _3_Searching._3_4_Hash_Tables.SeparateChainingHashST;
import common.In;

public class SymbolGraph {
    private final SeparateChainingHashST<String, Integer> st; // String -> index
    private final String[] keys; // index -> String
    private final Graph G; // the graph

    public SymbolGraph(String stream, String sp) {
        st = new SeparateChainingHashST<>();
        In in = new In(stream); // First pass
        while (in.hasNextLine()) {
            String[] a = in.readLine().split(sp);
            for (int i = 0; i < a.length; i++)
                if (!st.contains(a[i]))
                    st.put(a[i], st.size());
        }
        keys = new String[st.size()];
        for (String name : st.keys())
            keys[st.get(name)] = name;

        G = new Graph(st.size());
        in = new In(stream);
        while (in.hasNextLine()) {
            String[] a = in.readLine().split(sp);
            int v = st.get(a[0]);
            for (int i = 1; i < a.length; i++)
                G.addEdge(v, st.get(a[i]));
        }
    }

    public boolean contains(String s) {
        return st.contains(s);
    }

    public int index(String s) {
        return st.get(s);
    }

    public String name(int v) {
        return keys[v];
    }

    public Graph G() {
        return G;
    }
}
