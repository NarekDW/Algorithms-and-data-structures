package _4_Graphs._4_1_Undirected_Graphs.exercises;

import _1_Fundamentals._1_3_Bags_Queues_and_Stacks.Stack;
import _4_Graphs._4_1_Undirected_Graphs.Graph;
import _4_Graphs._4_1_Undirected_Graphs.Search;
import common.In;

/*****************************************************************************************************
 * <p>
 * 4.1.8 Develop an implementation for the Search API on page 528 that uses UF,as described in the text.
 *
 ****************************************************************************************************/
public class UnionFindSearch implements Search {
    private final int[] id;
    private int count;
    private final int p;

    public UnionFindSearch(Graph G, int s) {
        this.count = G.V();
        this.p = s;
        id = new int[G.V()];
        for (int i = 0; i < G.V(); i++)
            id[i] = i;
        union(G);
    }

    private int find(int p) {
        return id[p];
    }

    // from N + 2 to 2N
    public void union(Graph g) {
        Stack<Integer> stack = new Stack<>();
        stack.push(p);
        int idP = find(p);
        while (!stack.isEmpty()) {
            Integer v = stack.pop();
            for (Integer adj : g.adj(v)) {
                int idAdj = find(adj);
//                System.out.println(idP +" : "+ idAdj);
                if (idP != idAdj) {
                    id[adj] = idP;
                    count--;
                    stack.push(adj);
                }
            }
        }
    }

    @Override
    public boolean marked(int v) {
        return id[v] == id[p];
    }

    @Override
    public int count() {
        return count;
    }

    public static void main(String[] args) {
        In in = new In("https://algs4.cs.princeton.edu/41graph/tinyG.txt");
        Graph G = new Graph(in);

        UnionFindSearch uf = new UnionFindSearch(G, 0);
        System.out.println(uf.count);
        System.out.println(uf.marked(3));
        System.out.println(uf.marked(4));
        System.out.println(uf.marked(9));
        System.out.println(uf.marked(11));
    }
}
