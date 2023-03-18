package _4_Graphs._4_1_Undirected_Graphs.exercises;

import _1_Fundamentals._1_3_Bags_Queues_and_Stacks.Queue;
import _4_Graphs._4_1_Undirected_Graphs.Graph;
import common.In;

import java.util.Arrays;

/*****************************************************************************************************
 * <p>
 * 4.1.16 The eccentricity of a vertex v is the length of the shortest path from that vertex
 * to the furthest vertex from v. The diameter of a graph is the maximum eccentricity of any
 * vertex. The radius of a graph is the smallest eccentricity of any vertex.
 * A center is a vertex whose eccentricity is the radius. Implement the following API:
 * <p>
 * 4.1.18 The girth of a graph is the length of its shortest cycle. If a graph is a cyclic,
 * then its girth is infinite. Add a method girth() to GraphProperties that returns the girth
 * of the graph. Hint : Run BFS from each vertex.
 * The shortest cycle containing s is a shortest path from s to some vertex v, plus the edge
 * from v back to s.
 *
 ****************************************************************************************************/
public class GraphProperties {
    private final Graph g;

    public GraphProperties(Graph g) {
        this.g = g;
    }

    /**
     * The eccentricity of a vertex v is the length of the shortest path from that vertex
     * to the furthest vertex from v.
     */
    public int eccentricity(int v) {
        int[] pathTo = new int[g.V()];
        boolean[] marked = new boolean[g.V()];
        pathTo[v] = 0;
        Queue<Integer> queue = new Queue<>();
        queue.enqueue(v);
        while (!queue.isEmpty()) {
            Integer x = queue.dequeue();
            marked[x] = true;
            for (Integer w : g.adj(x)) {
                if (!marked[w]) {
                    pathTo[w] = 1 + pathTo[x];
                    marked[w] = true;
                    queue.enqueue(w);
                }
            }
        }

        int max = 0;
        for (int j : pathTo) {
            if (j > max) max = j;
        }
        return max;
    }

    /**
     * The diameter of a graph is the maximum eccentricity of any vertex.
     */
    public int diameter() {
        int diameter = 0;
        for (int i = 0; i < g.V(); i++) {
            int eccentricity = eccentricity(i);
            if (eccentricity > diameter)
                diameter = eccentricity;
        }

        return diameter;
    }

    /**
     * The radius of a graph is the smallest eccentricity of any vertex.
     */
    int radius() {
        int radius = Integer.MAX_VALUE;
        for (int i = 0; i < g.V(); i++) {
            int eccentricity = eccentricity(i);
            if (eccentricity < radius)
                radius = eccentricity;
        }

        return radius;

    }

    /**
     * A center is a vertex whose eccentricity is the radius.
     */
    int center() {
        int radius = radius();
        for (int i = 0; i < g.V(); i++) {
            int eccentricity = eccentricity(i);
            if (eccentricity == radius)
                return i;
        }
        return -1;
    }

    public int girth() {
        int girth = Integer.MAX_VALUE;
        for (int i = 0; i < g.V(); i++) {
            int minCycle = getMinCycle(i);
            if (minCycle < girth)
                girth = minCycle;
        }

        return girth;
    }

    private int getMinCycle(int v) {
        int n = g.V();

        int[] parent = new int[n];
        Arrays.fill(parent, -1);

        int[] pathTo = new int[n];
        Arrays.fill(pathTo, Integer.MAX_VALUE);
        pathTo[v] = 0;

        boolean[] marked = new boolean[n];
        marked[v] = true;

        Queue<Integer> queue = new Queue<>();
        queue.enqueue(v);

        while (!queue.isEmpty()) {
            Integer root = queue.dequeue();
            for (Integer child : g.adj(root)) {
                if (!marked[child]) {
                    marked[child] = true;
                    pathTo[child] = pathTo[root] + 1;
                    parent[child] = root;
                    queue.enqueue(child);
                } else if (parent[root] != child && parent[child] != root) {
                    return pathTo[root] + pathTo[child] + 1;
                }
            }
        }

        return Integer.MAX_VALUE;
    }


    public static void main(String[] args) {
//        In in = new In("https://algs4.cs.princeton.edu/41graph/tinyG.txt");
//        Graph G = new Graph(in);
//        in.close();

        Graph graph = new Graph(15);
        graph.addEdge(0, 5);
        graph.addEdge(4, 3);
        graph.addEdge(0, 1);
        graph.addEdge(9, 12);
        graph.addEdge(6, 13);
        graph.addEdge(13, 14);
        graph.addEdge(14, 4);
        graph.addEdge(5, 4);
        graph.addEdge(0, 2);
        graph.addEdge(11, 12);
        graph.addEdge(9, 10);
        graph.addEdge(0, 6);
        graph.addEdge(7, 8);
        graph.addEdge(9, 11);
        graph.addEdge(5, 3);

        GraphProperties properties = new GraphProperties(graph);
        System.out.println("eccentricity: " + properties.eccentricity(0));
        System.out.println("diameter: " + properties.diameter());
        System.out.println("radius: " + properties.radius());
        System.out.println("center: " + properties.center());
        System.out.println("girth: " + properties.girth());
    }
}
