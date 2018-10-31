import java.util.Scanner;
/**
 * Class for directed cycle.
 */
class DirectedCycle {
    /**
     * boolean array for marking the visit of node.
     */
    private boolean[] marked;
    /**
     * boolean array if the node is in the stack.
     */
    private boolean[] onStack;
    /**
     * array for the edge of that node.
     */
    private int[] edgeTo;
    /**
     * stack object to store nodes.
     */
    private Stack<Integer> cycle;
    /**
     * Constructs the object.
     * Time complexity is O(V).
     * V is the number of vertices.
     *
     * @param      g     diGraph object
     */
    DirectedCycle(final DiGraph g) {
        marked  = new boolean[g.vertices()];
        onStack = new boolean[g.vertices()];
        edgeTo  = new int[g.vertices()];
        for (int vertices = 0; vertices < g.vertices(); vertices++) {
            if (!marked[vertices] && cycle == null) {
                dfs(g, vertices);
            }
        }
    }
    /**
     * this method performs dfs for the given graph.
     * Time complexity is O(E)
     * E is the number of edges.
     *
     * @param      g     object of digraph
     * @param      vertices     number of vertice.
     */
    private void dfs(final DiGraph g, final int vertices) {
        onStack[vertices] = true;
        marked[vertices] = true;
        for (int w : g.adj(vertices)) {
            if (cycle != null) {
                return;
            } else if (!marked[w]) {
                edgeTo[w] = vertices;
                dfs(g, w);
            } else if (onStack[w]) {
                cycle = new Stack<Integer>();
                for (int x = vertices; x != w; x = edgeTo[x]) {
                    cycle.push(x);
                }
                cycle.push(w);
                cycle.push(vertices);
            }
        }
        onStack[vertices] = false;

    }
    /**
     * Determines if it has cycle.
     * Time complexity of this method is O(1)
     *
     * @return     True if has cycle,
     *             False otherwise.
     */
    public boolean hasCycle() {
        return cycle != null;
    }
}
/**
 * Class for di graph.
 */
class DiGraph {
    /**
     * number of vertices.
     */
    private int vertices;
    /**
     * number of edges.
     */
    private int edges;
    /**
     * edge count.
     */
    private int edge = 0;
    /**
     * Indegree of that node.
     */
    private int indegree = 0;
    /**
     * array to store that adjacent nodes.
     */
    private Bag<Integer>[] adj;
    /**
     * Constructs the object.
     * Time complexity is O(V+E);
     * V is number of vertices
     * E is number of edges.
     *
     * @param      scan  The scan
     */
    DiGraph(final Scanner scan) {
        vertices = Integer.parseInt(scan.nextLine());
        adj = (Bag<Integer>[]) new Bag[vertices];
        for (int i = 0; i < vertices; i++) {
            adj[i] = new Bag<Integer>();
        }
        edges = Integer.parseInt(scan.nextLine());
        for (int i = 0; i < edges; i++) {
            addEdge(scan.nextInt(), scan.nextInt());
        }
    }
    /**
     * Adds an edge.
     * Time complexity is O(1).
     *
     * @param      vertexOne    The vertexOne
     * @param      vertexTwo    The vertexTwo
     */
    public void addEdge(final int vertexOne,
                        final int vertexTwo) {
        if (!hasEdge(vertexOne, vertexTwo)) {
            edge++;
        }
        adj[vertexTwo].add(vertexOne);
    }
    /**
     * Determines if it has edge.
     * Time complexity of this method is O(E)
     * E is the number of edges.
     *
     * @param      vertexOne  The vertex one
     * @param      vertexTwo  The vertex two
     *
     * @return     True if has edge, False otherwise.
     */
    public boolean hasEdge(final int vertexOne,
                           final int vertexTwo) {
        for (int each : adj(vertexOne))  {
            if (each == vertexTwo) {
                return true;
            }
        }
        return false;
    }
    /**
     * returns the number of vetices.
     *
     * @return     returns the number of vetices.
     */
    public int vertices() {
        return vertices;
    }
    /**
     * returns the number of edges.
     *
     * @return     returns the number of edges.
     */
    public int edges() {
        return edges;
    }
    /**
     * returns the adjacent vetices of that vertex.
     *
     * @param      vertex  The vertex
     *
     * @return     returns the adjacent vetices of that vertex.
     */
    public Iterable<Integer> adj(final int vertex) {
        return adj[vertex];
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
     * main method performs the operation.
     * Time complexity of this method is O(N).
     * N is number of edges
     *
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        Scanner scan = new Scanner(System.in);
        DiGraph g = new DiGraph(scan);
        DirectedCycle obj = new DirectedCycle(g);
        if (obj.hasCycle()) {
            System.out.println("Cycle exists.");
        } else {
            System.out.println("Cycle doesn't exists.");
        }
    }
}
