/**
 * Class for edge weighted graph.
 */
class EdgeWeightedGraph {
    /**
     * vertices of the graph.
     */
    private int vertices;
    /**
     * adjacent list of one vertex.
     */
    private Bag<Edge>[] adj;
    private int edges = 0;
    /**
     * Constructs the object.
     *
     * @param      v     vertices input.
     */
    EdgeWeightedGraph(final int v) {
        this.vertices = v;
        adj = new Bag[vertices];
        for (int i = 0; i < vertices; i++) {
            adj[i] = new Bag<Edge>();
        }
    }
    /**
     * Adds an edge.
     *
     * @param      e     edge.
     */
    public void addEdge(final Edge e) {
        int first = e.either();
        int second = e.other(first);
        adj[first].add(e);
        adj[second].add(e);
        edges++;
    }
    /**
     * returns iterable of edge.
     * Time complexity is O(E).
     * E is the number of edges.
     *
     * @return     returns iterable of edge.
     */
    public Iterable<Edge> edges() {
        Bag<Edge> list = new Bag<Edge>();
        for (int i = 0; i < vertices; i++) {
            for (Edge e : adj(i)) {
                list.add(e);
            }
        }
        return list;
    }
    /**
     * returns iterable of vertices.
     * Time complexity is O(V).
     * V is number of vertices.
     *
     * @param      vertex  The vertex
     *
     * @return     returns iterable of vertices.
     */
    public Iterable<Edge> adj(final int vertex) {
        return adj[vertex];
    }
    /**
     * returns the vertices of the graph.
     *
     * @return     returns the vertices of the graph.
     */
    public int vertices() {
        return this.vertices;
    }
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(vertices + " vertices " + edges + " edges"+"\n");
        for (int v = 0; v < vertices; v++) {
            s.append(v + ": ");
            for (Edge e : adj[v]) {
                s.append(e + "  ");
            }
            s.append("\n");
        }
        return s.toString();
    }

}