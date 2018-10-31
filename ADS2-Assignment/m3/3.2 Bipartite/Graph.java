/**
 * Class for graph.
 */
public class Graph {
    /**
     * Vertex.
     */
    private int vertices;
    /**
     * Edges.
     */
    private int edges;
    /**
     * Declaring a bag array of type integer.
     */
    private Bag<Integer>[] adj;

    /**
     * Create an empty graph with V vertices.
     * Time complexity is O(V)
     * V is number of vertices.
     * @param     vertex     Vertex
     */
    public Graph(final int vertex) {
        if (vertex < 0) {
            throw new RuntimeException(
                "Number of vertices must be nonnegative");
        }
        this.vertices = vertex;
        this.edges = 0;
        adj = (Bag<Integer>[]) new Bag[vertices];
        for (int i = 0; i < vertices; i++) {
            adj[i] = new Bag<Integer>();
        }
    }

    /**
     * Return the number of vertices in the graph.
     * @return      Integer
     */
    public int vertices() {
        return vertices;
    }

    /**
     * Return the number of edges in the graph.
     * @return      Integer
     */
    public int edges() {
        return edges;
    }

    /**
     * Add the edge vertex-w to graph.
     * Time complexity is O(1).
     * @param      vertexOne  Vertex one
     * @param      vertexTwo  Vertex two
     */
    public void addEdge(final int vertexOne,
                        final int vertexTwo) {
        edges++;
        adj[vertexOne].add(vertexTwo);
        adj[vertexTwo].add(vertexOne);
    }

    /**
     * Return the list of neighbors of vertex vertex as in Iterable.
     * Time complexity is O(1).
     * @param      vertex    Vertex
     * @return     Iterable
     */
    public Iterable<Integer> adj(final int vertex) {
        return adj[vertex];
    }
}

