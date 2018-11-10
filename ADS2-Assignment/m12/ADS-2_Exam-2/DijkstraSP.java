class DijkstraSP {
    /**
     * distance of the edge from vetrex1 to vertex 2.
     */
    private Double[] distTo;
    /**
     *  to store edge object.
     */
    private Edge[] edgeTo;
    /**
     * Index min pQ to store the weights of edges.
     */
    private IndexMinPQ<Double> pq;
    /**
     * edgeweightedgraph object.
     */
    private EdgeWeightedGraph graph;
    /**
     * The constructor to initialize the objects.
     * The time complexity is O(E + V).
     * @param      g  graph object.
     * @param      source  The source
     */
    DijkstraSP(final EdgeWeightedGraph g,
               final int source) {
        distTo = new Double[g.vertices()];
        edgeTo = new Edge[g.vertices()];
        for (int i = 0; i < g.vertices(); i++) {
            distTo[i] = Double.POSITIVE_INFINITY;
        }
        distTo[source] = 0.0;
        pq = new IndexMinPQ<Double>(g.vertices());
        pq.insert(source, distTo[source]);
        while (!pq.isEmpty()) {
            int vertex = pq.delMin();
            for (Edge edge : g.adj(vertex)) {
                relax(edge, vertex);
            }
        }
    }
    /**
     * This method is to relax the edges.
     * Time complexity is O(logE)
     * @param      edge    The edge
     * @param      vertex  The vertex
     */
    private void relax(final Edge edge,
                       final int vertex) {
        int vertexTwo = edge.other(vertex);
        if (distTo[vertexTwo] > distTo[vertex] + edge.getWeight()) {
            distTo[vertexTwo] = distTo[vertex] + edge.getWeight();
            edgeTo[vertexTwo] = edge;
            if (pq.contains(vertexTwo)) {
                pq.decreaseKey(vertexTwo, distTo[vertexTwo]);
            } else {
                pq.insert(vertexTwo, distTo[vertexTwo]);
            }
        }
    }
    /**
     * returns the distance of two vertices.
     *
     * @param      v  vertex
     *
     * @return distance between two vertices.
     */
    public double distTo(final int v) {
        return distTo[v];
    }
    /**
     * returns the boolean value if the path is present
     * or not.
     *
     * @param      v another vertex.
     *
     * @return     True if has path to, False otherwise.
     */
    public boolean hasPathTo(final int v) {
        return distTo[v] < Double.POSITIVE_INFINITY;
    }
    /**
     * returns shortest path to given vertex.
     * Time complexity is O(ElogV)
     * @param      v  vertex.
     *
     * @return shortest path is returned from the source.
     */
    public Iterable<Edge> pathTo(final int v) {
        if (!hasPathTo(v)) {
            return null;
        }
        Stack<Edge> path = new Stack<Edge>();
        int x = v;
        for (Edge e = edgeTo[v]; e != null; e = edgeTo[x]) {
            path.push(e);
            x = e.other(x);
        }
        return path;
    }
    /**
     * returns the shortest distance between.
     * two vertices.
     * Time complexity O(E)
     * @param      vertex  The vertex
     *
     * @return shortest distance between two vertices.
     */
    public double getDistance(final int vertex) {
        double sum = 0;
        for (Edge each : pathTo(vertex)) {
            sum += each.getWeight();
        }
        return sum;
    }
}