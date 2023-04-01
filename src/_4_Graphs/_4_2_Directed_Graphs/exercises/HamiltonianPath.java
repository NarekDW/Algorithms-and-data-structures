package _4_Graphs._4_2_Directed_Graphs.exercises;

import _4_Graphs._4_2_Directed_Graphs.DepthFirstOrder;
import _4_Graphs._4_2_Directed_Graphs.Digraph;

/*****************************************************************************************************
 * <p>
 * 4.2.24 Hamiltonian path in DAGs. Given a DAG, design a linear-time algorithm to determine whether
 * there is a directed path that visits each vertex exactly once.
 *
 ****************************************************************************************************/
public class HamiltonianPath {

    public static boolean isHamiltonianPath(Digraph digraph) {
        DepthFirstOrder order = new DepthFirstOrder(digraph);
        Iterable<Integer> reversePost = order.reversePost();
        int previousVertex = -1;
        for (Integer vertex : reversePost) {
            if (previousVertex != -1) {
                if (!digraph.hasEdge(previousVertex, vertex)) {
                    return false;
                }
            }
            previousVertex = vertex;
        }

        return true;
    }

    public static void main(String[] args) {
        Digraph digraph = new Digraph(6);
        digraph.addEdge(0, 1);
        digraph.addEdge(1, 2);
        digraph.addEdge(2, 3);
        digraph.addEdge(2, 4);
        digraph.addEdge(3, 4);
        digraph.addEdge(3, 5);
        digraph.addEdge(4, 5);

        System.out.println(isHamiltonianPath(digraph));
    }
}
