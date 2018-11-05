import java.util.Scanner;
class Solution {
	private Solution() {

	}
	public static void main(String[] args) {
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
class Edge implements Comparable<Edge> {
	int vertexOne;
	int vertexTwo;
	double weight;
	Edge(int v, int w, double wei) {
		this.vertexOne = v;
		this.vertexTwo = w;
		this.weight = wei;
	}
	public int either() {
		return vertexOne;
	}
	public int other(int v) {
		if (vertexOne == v) {
			return vertexTwo;
		} return vertexOne;
	}
	public int compareTo(Edge that) {
		if (this.weight < that.weight) {
			return -1;
		} else if (this.weight > that.weight) {
			return 1;
		} return 0;
	}
	public double getWeight(){
		return this.weight;
	}
}
class EdgeWeightedGraph {
	int vertices;
	Bag<Edge>[] adj;
	EdgeWeightedGraph(int v) {
		this.vertices = v;
		adj = new Bag[vertices];
		for (int i = 0; i < vertices; i++) {
			adj[i] = new Bag<Edge>();
		}
	}
	public void addEdge(Edge e) {
		int first = e.either();
		int second = e.other(first);
		adj[first].add(e);
		adj[second].add(e);
	}
	public Iterable<Edge> edges() {
		Bag<Edge> list = new Bag<Edge>();
		for (int i = 0; i < vertices; i++) {
			for (Edge e : adj(i)) {
				list.add(e);
			}
		}
		return list;
	}
	public Iterable<Edge> adj(final int vertex) {
        return adj[vertex];
    }
    public int vertices() {
        return this.vertices;
    }

}
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