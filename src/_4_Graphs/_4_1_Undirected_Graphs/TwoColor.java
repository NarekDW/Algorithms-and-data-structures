package _4_Graphs._4_1_Undirected_Graphs;

/***
 * Two-colorability. Support this query: Can the vertices of a given graph be assigned
 * one of two colors in such a way that no edge connects vertices of the same color?
 * which is equivalent to this question: Is the graph bipartite ?
 */
public class TwoColor {
    private final boolean[] marked;
    private final boolean[] color;
    private boolean isTwoColorable = true;

    public TwoColor(Graph g) {
        marked = new boolean[g.V()];
        color = new boolean[g.V()];
        for (int s = 0; s < g.V(); s++)
            if (!marked[s])
                dfs(g, s);
    }

    private void dfs(Graph g, int v) {
        marked[v] = true;
        for (int w : g.adj(v))
            if (!marked[w]) {
                color[w] = !color[v];
                dfs(g, w);
            } else if (color[w] == color[v])
                isTwoColorable = false;
    }

    public boolean isBipartite() {
        return isTwoColorable;
    }
}
