package _4_Graphs._4_1_Undirected_Graphs;

// Cycle detection. Support this query: Is a given graph acylic?
public class Cycle {
    private final boolean[] marked;
    private boolean hasCycle;

    public Cycle(Graph G) {
        marked = new boolean[G.V()];
        for (int s = 0; s < G.V(); s++)
            if (!marked[s])
                dfs(G, s, s);
    }

    private void dfs(Graph g, int curr, int prev) {
        marked[curr] = true;
        for (int w : g.adj(curr))
            if (!marked[w])
                dfs(g, w, curr);
            else if (w != prev)
                hasCycle = true;
    }

    public boolean hasCycle() {
        return hasCycle;
    }
}
