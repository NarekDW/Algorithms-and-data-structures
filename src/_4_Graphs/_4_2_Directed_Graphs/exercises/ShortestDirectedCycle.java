package _4_Graphs._4_2_Directed_Graphs.exercises;

import _1_Fundamentals._1_3_Bags_Queues_and_Stacks.Stack;
import _4_Graphs._4_2_Directed_Graphs.BreadthFirstDirectedPaths;
import _4_Graphs._4_2_Directed_Graphs.Digraph;
import common.In;
import common.StdOut;

public class ShortestDirectedCycle {
    private Stack<Integer> cycle;    // directed cycle (or null if no such cycle)
    private int length;

    public ShortestDirectedCycle(Digraph G) {
        Digraph R = G.reverse();
        length = G.V() + 1;
        for (int v = 0; v < G.V(); v++) {
            BreadthFirstDirectedPaths bfs = new BreadthFirstDirectedPaths(R, v);
            for (int w : G.adj(v)) {
                if (bfs.hasPathTo(w) && (bfs.distTo(w) + 1) < length) {
                    length = bfs.distTo(w) + 1;
                    cycle = new Stack<>();
                    for (int x : bfs.pathTo(w))
                        cycle.push(x);
                    cycle.push(v);
                }
            }
        }
    }


    public boolean hasCycle() {
        return cycle != null;
    }

    public Iterable<Integer> cycle() {
        return cycle;
    }

    public int length() {
        return length;
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        Digraph G = new Digraph(in);

        ShortestDirectedCycle finder = new ShortestDirectedCycle(G);
        if (finder.hasCycle()) {
            StdOut.print("Shortest directed cycle: ");
            for (int v : finder.cycle()) {
                StdOut.print(v + " ");
            }
            StdOut.println();
        } else {
            StdOut.println("No directed cycle");
        }
    }

}
