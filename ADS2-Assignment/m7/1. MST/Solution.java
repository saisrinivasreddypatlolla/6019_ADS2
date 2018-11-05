import java.util.Scanner;
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
     * { function_description }.
     *
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        Scanner scan = new Scanner(System.in);
        int vertices = Integer.parseInt(scan.nextLine());
        int edges = Integer.parseInt(scan.nextLine());
        Edge edgeObj;
        EdgeWeightedGraph graph
            = new EdgeWeightedGraph(vertices);
        for (int i = 0; i < edges; i++) {
            String[] tokens = scan.nextLine().split(" ");
            edgeObj = new Edge(Integer.parseInt(tokens[0]),
                               Integer.parseInt(tokens[1]),
                               Double.parseDouble(tokens[2]));
            graph.addEdge(edgeObj);
        }
        MSTree mstObj = new MSTree(graph);
        double result = mstObj.total();
        System.out.format("%.5f", result);
    }
}
/**
 * Class for edge.
 */
class Edge implements Comparable<Edge> {
    /**
     * { var_description }.
     */
    private int vertexOne;
    /**
     * { var_description }.
     */
    private int vertexTwo;
    /**
     * { var_description }.
     */
    private double weight;
    /**
     * Constructs the object.
     *
     * @param      v     { parameter_description }
     * @param      w     { parameter_description }
     * @param      wei   The wei
     */
    Edge(final int v, final int w, final double wei) {
        this.vertexOne = v;
        this.vertexTwo = w;
        this.weight = wei;
    }
    /**
     * { function_description }.
     *
     * @return     { description_of_the_return_value }
     */
    public int either() {
        return vertexOne;
    }
    /**
     * { function_description }.
     *
     * @param      v     { parameter_description }
     *
     * @return     { description_of_the_return_value }
     */
    public int other(final int v) {
        if (vertexOne == v) {
            return vertexTwo;
        }
        return vertexOne;
    }
    /**
     * { function_description }.
     *
     * @param      that  The that
     *
     * @return     { description_of_the_return_value }
     */
    public int compareTo(final Edge that) {
        if (this.weight < that.weight) {
            return -1;
        } else if (this.weight > that.weight) {
            return 1;
        }
        return 0;
    }
    /**
     * Gets the weight.
     *
     * @return     The weight.
     */
    public double getWeight() {
        return this.weight;
    }
}
/**
 * Class for edge weighted graph.
 */
class EdgeWeightedGraph {
    /**
     * .
     */
    private int vertices;
    /**
     * .
     */
    private Bag<Edge>[] adj;
    /**
     * Constructs the object.
     *
     * @param      v     { parameter_description }
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
     * @param      e     { parameter_description }
     */
    public void addEdge(final Edge e) {
        int first = e.either();
        int second = e.other(first);
        adj[first].add(e);
        adj[second].add(e);
    }
    /**
     * { function_description }.
     *
     * @return     { description_of_the_return_value }
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
     * { function_description }.
     *
     * @param      vertex  The vertex
     *
     * @return     { description_of_the_return_value }
     */
    public Iterable<Edge> adj(final int vertex) {
        return adj[vertex];
    }
    /**
     * { function_description }.
     *
     * @return     { description_of_the_return_value }
     */
    public int vertices() {
        return this.vertices;
    }

}
/**
 * Class for ms tree.
 */
class MSTree {
    /**
     *the graph object.
     */
    private EdgeWeightedGraph graph;
    /**
     *queue to give the mst.
     */
    private Queue<Edge> mst;
    /**
     *intializes  the values.
     *time complexity is O(ElogE).
     *where e is the edges.
     * @param      g  graph object.
     */
    MSTree(final EdgeWeightedGraph g) {
        graph = g;
        mst = new Queue<Edge>();
        MinPQ<Edge> pq = new MinPQ<Edge>();
        for (Edge edge : graph.edges()) {
            pq.insert(edge);
        }
        UF ufObj = new UF(graph.vertices());
        while (!pq.isEmpty()
                && mst.size() < graph.vertices() - 1) {
            Edge edge = pq.delMin();
            int vertexOne = edge.either();
            int vertexTwo = edge.other(vertexOne);
            if (!ufObj.connected(vertexOne, vertexTwo)) {
                ufObj.union(vertexOne, vertexTwo);
                mst.enqueue(edge);
            }
        }
    }
    /**
     *it returns all the edges on mst.
     *
     * @return  queue which contains all
     * vertices.
     */
    public Iterable<Edge> edges() {
        return mst;
    }
    /**
     *this method returns the total weight.
     *of mst.
     *time complexity is O(E)
     * @return weight of mst.
     */
    public double total() {
        double sum = 0.0;
        for (Edge e : edges()) {
            sum += e.getWeight();
        }
        return sum;
    }
}
