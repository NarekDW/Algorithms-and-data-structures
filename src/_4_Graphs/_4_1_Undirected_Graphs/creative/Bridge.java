package _4_Graphs._4_1_Undirected_Graphs.creative;

import _4_Graphs._4_1_Undirected_Graphs.Graph;
import common.StdOut;


/*****************************************************************************************************
 * <p>
 * 4.1.36 Edge connectivity. A bridge in a graph is an edge that, if removed, would separate
 * a connected graph into two disjoint subgraphs. A graph that has no bridges is said to be
 * edge connected. Develop a DFS-based data type for determing whether a given graph is edge connected.
¬ *
 ****************************************************************************************************/
public class Bridge {
    private int bridges;                 // number of bridges
    private int cnt;                     // counter
    private final int[] traversalOrder;  // traversalOrder[v] = order in which dfs examines v
    private final int[] subgroups;       // subgroups of connected in a cycle vertexes

    public Bridge(Graph g) {
        this.traversalOrder = new int[g.V()];
        this.subgroups = new int[g.V()];
        this.bridges = 0;
        this.cnt = 0;

        for (int i = 0; i < g.V(); i++) {
            traversalOrder[i] = -1;
            subgroups[i] = -1;
        }

        for (int i = 0; i < g.V(); i++) {
            dfs(g, i, i);
        }
    }

    public int components() {
        return bridges + 1;
    }

    private void dfs(Graph g, int prev, int curr) {
        traversalOrder[curr] = cnt++;
        subgroups[curr] = traversalOrder[curr];
        for (Integer next : g.adj(curr)) {
            if (traversalOrder[next] == -1) {
                dfs(g, curr, next);
                subgroups[curr] = Math.min(subgroups[curr], subgroups[next]);
                if (subgroups[curr] != subgroups[next]) {
                    StdOut.println(curr + "-" + next + " is a bridge");
                    bridges++;
                }
            } else if (prev != next) {
                subgroups[curr] = Math.min(subgroups[curr], traversalOrder[next]);
            }
        }
    }


    public static void main(String[] args) {
//        Graph graph = getGraph1();
        Graph graph = getGraph2();
        Bridge bridge = new Bridge(graph);
        StdOut.println("Edge connected components = " + bridge.components());
    }

    private static Graph getGraph1() {
        Graph graph = new Graph(5);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(0, 3);
        graph.addEdge(0, 4);
        graph.addEdge(4, 3);
        return graph;
    }

    private static Graph getGraph2() {
        Graph graph = new Graph(8);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);
        graph.addEdge(4, 1);
        graph.addEdge(4, 5);
        graph.addEdge(2, 6);
        graph.addEdge(6, 7);
        graph.addEdge(7, 3);
        return graph;
    }
}
