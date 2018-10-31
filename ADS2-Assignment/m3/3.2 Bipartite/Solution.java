import java.util.Scanner;
/**
 * Class for bipartite.
 */
class Bipartite {
    /**
     * boolean array for marking the visit of node.
     */
    private boolean[] marked;
    /**
     * boolean array for color representation.
     * of each node.
     */
    private boolean[] color;
    /**
     * flag to check graph is bipartite or not.
     */
    private boolean flag;
    /**
     * array for the edge of that node.
     */
    private int[] edgeTo;
    /**
     * Constructs the object.
     * Time complexity is O(V)
     * V is number of vertices.
     *
     * @param      g     graph object
     */
    Bipartite(final Graph g) {
        int vertices = g.vertices();
        marked = new boolean[vertices];
        color = new boolean[vertices];
        edgeTo = new int[vertices];
        flag = true;
        for (int i = 0; i < vertices; i++) {
            if (!marked[i]) {
                dfs(g, i);
            }
        }
    }
    /**
     * this method performs dfs for the given graph.
     * Time complexity is O(E)
     * E is the number of edges.
     *
     * @param      g         graph object
     * @param      vertices  The vertices
     */
    public void dfs(final Graph g, final int vertices) {
        marked[vertices] = true;
        for (int w : g.adj(vertices)) {
            if (!flag) {
                return;
            } else if (!marked[w]) {
                edgeTo[w] = vertices;
                color[w] = !color[vertices];
                dfs(g, w);
            } else if (color[w] == color[vertices]) {
                flag = false;
            }
        }
    }
    /**
     * Determines if bipartite.
     *
     * @return     True if bipartite, False otherwise.
     */
    public boolean isBipartite() {
        return flag;
    }
}
/**
 * Class for solution.
 */
final class Solution {
    /**
     * Constructs the object.
     */
    private Solution() {

    }
    /**
     * the main method performs operations.
     *
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        Scanner scan = new Scanner(System.in);
        int vertices = Integer.parseInt(scan.nextLine());
        int edges = Integer.parseInt(scan.nextLine());
        Graph g = new Graph(vertices);
        for (int i = 0; i < edges; i++) {
            String[] t = scan.nextLine().split(" ");
            g.addEdge(Integer.parseInt(
                          t[0]), Integer.parseInt(t[1]));
        }
        Bipartite obj = new Bipartite(g);
        if (obj.isBipartite()) {
            System.out.println("Graph is bipartite");
        } else {
            System.out.println("Graph is not a bipartite");
        }
    }
}
