import java.util.Scanner;
import java.util.HashMap;
/**
 * Class for edge.
 */
class Edge implements Comparable<Edge> {
    /**
     * first vertex to connect.
     */
    private int vertexOne;
    /**
     * second vertex connect from first one.
     */
    private int vertexTwo;
    /**
     * weight of the edge.
     */
    private double weight;
    /**
     * Constructs the object.
     *
     * @param      v     first vertex
     * @param      w     second vertex
     * @param      wei   The weight
     */
    Edge(final int v, final int w, final double wei) {
        this.vertexOne = v;
        this.vertexTwo = w;
        this.weight = wei;
    }
    /**
     * returns first end of edge.
     *
     * @return     returns first end of edge.
     */
    public int either() {
        return vertexOne;
    }
    /**
     * returns other end of edge.
     *
     * @param      v     connected vertex.
     *
     * @return      returns other end of edge.
     */
    public int other(final int v) {
        if (vertexOne == v) {
            return vertexTwo;
        }
        return vertexOne;
    }
    /**
     * compares both edges weight.
     *
     * @param      that  The that
     *
     * @return     returns integer value.
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
     * vertices of the graph.
     */
    private int vertices;
    /**
     * adjacent list of one vertex.
     */
    private Bag<Edge>[] adj;
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

}
/**
 *the class for dijkstra's algorithm.
 *to find the shortest path.
 */
class DijkstraSP {
    /**
     *the distTo array to store.
     *distance from one vertex to another.
     */
    private Double[] distTo;
    /**
     *edge to is to store the edge connected.
     */
    private Edge[] edgeTo;
    /**
     *indexed minpq to store the key value.
     *pair.
     */
    private IndexMinPQ<Double> pq;
    /**
     *the graph object.
     */
    private EdgeWeightedGraph graph;
    /**
     *the constructor to initialize the objects.
     *the time complexity is O(E + V).
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
        pq = new IndexMinPQ<Double>(graph.vertices());
        pq.insert(source, distTo[source]);
        while (!pq.isEmpty()) {
            int vertex = pq.delMin();
            for (Edge edge : g.adj(vertex)) {
                relax(edge, vertex);
            }
        }
    }
    /**
     *this method is to relax the edges.
     *time complexity is O(logE)
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
     *the method returns the distance.
     *from the source to given vertex.
     *
     * @param      v  vertex
     *
     * @return distance between two vertices.
     */
    public double distTo(final int v) {
        return distTo[v];
    }
    /**
     *whether the path is there or not.
     *
     * @param      v another vertex.
     *
     * @return     True if has path to, False otherwise.
     */
    public boolean hasPathTo(final int v) {
        return distTo[v] < Double.POSITIVE_INFINITY;
    }
    /**
     *shortest path to given vertex.
     *
     * @param      v  vertex.
     *time complexity is O(ElogV)
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
     *returns the shortest distance between.
     *two vertices.
     *time complexity O(E)
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
class Solution{
	private Solution(){

	}
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		String[] inputs = scan.nextLine().split(" ");
		int vertices = Integer.parseInt(inputs[0]);
		int edges = Integer.parseInt(inputs[1]);
		String[] cities = scan.nextLine().split(" ");
		for(int i=0;i<vertices;i++){
			map.put(cities[i], i);
		}
		Edge obj;
		EdgeWeightedGraph graph = new EdgeWeightedGraph(vertices);
		for(int i =0;i<edges;i++){
			String[] routes = scan.nextLine().split(" ");
			obj = new Edge(map.get(routes[0]),map.get(routes[1]),
				Double.parseDouble(routes[2]));
			graph.addEdge(obj);
		}
		int numQueries = Integer.parseInt(scan.nextLine());
		DijkstraSP dObj;
		for(int i = 0;i<numQueries;i++){
			String[] queries = scan.nextLine().split(" ");
			int source = map.get(queries[0]);
			dObj = new DijkstraSP(graph, source);
			System.out.println((int) dObj.getDistance(map.get(queries[1])));
		}

	}
}