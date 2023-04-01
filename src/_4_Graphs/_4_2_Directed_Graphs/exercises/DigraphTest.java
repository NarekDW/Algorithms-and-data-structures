package _4_Graphs._4_2_Directed_Graphs.exercises;

import _4_Graphs._4_2_Directed_Graphs.Digraph;
import common.In;

public class DigraphTest {
    public static void main(String[] args) {
        In in = new In("https://algs4.cs.princeton.edu/41graph/tinyG.txt");
        Digraph g = new Digraph(in);
        in.close();

        System.out.println(g.hasEdge(0, 1));
        System.out.println(g.hasEdge(4, 3));
        System.out.println(g.hasEdge(3, 4));

        System.out.println(g.indegree(3));
        System.out.println(g.outdegree(4));
    }
}
