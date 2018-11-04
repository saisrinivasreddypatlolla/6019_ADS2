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
     * @param      g     digraph object
     */
    DirectedCycle(final Digraph g) {
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
    private void dfs(final Digraph g, final int vertices) {
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

