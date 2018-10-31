import java.util.Scanner;
class DirectedCycle {
	boolean[] marked;
	boolean[] onStack;
	int[] edgeTo;
	Stack<Integer> cycle = new Stack<Integer>();
	public DirectedCycle(DiGraph g) {
		System.out.println(g.V());
		marked  = new boolean[g.V()];
		onStack = new boolean[g.V()];
		edgeTo  = new int[g.V()];
		for (int v = 0; v < g.V(); v++)
			if (!marked[v] && cycle == null) {
				dfs(g, v);
			}
	}
	private void dfs(DiGraph G, int v) {
		onStack[v] = true;
		marked[v] = true;
		for (int w : G.adj(v)) {
			if (cycle != null) return;
			else if (!marked[w]) {
				edgeTo[w] = v;
				dfs(G, w);
			} else if (onStack[w]) {
				cycle = new Stack<Integer>();
				for (int x = v; x != w; x = edgeTo[x]) {
					cycle.push(x);
				}
				cycle.push(w);
				cycle.push(v);
			}
		}
		onStack[v] = false;
	}
	public boolean hasCycle() {
        return cycle != null;
    }
}
class DiGraph {
	int v, e, edge = 0;
	int indegree = 0;
	Bag<Integer>[] adj;
	DiGraph(Scanner scan) {
		v = Integer.parseInt(scan.nextLine());
		adj = (Bag<Integer>[]) new Bag[v];
		for (int i = 0; i < v; i++) {
			adj[i] = new Bag<Integer>();
		}
		e = Integer.parseInt(scan.nextLine());
		for (int i = 0; i < e; i++) {
			addEdge(scan.nextInt(), scan.nextInt());
		}
	}
	public void addEdge(int v1, int v2) {
		if (v1 == v2) {
			return;
		}
		if (!hasEdge(v1, v2)) {
			edge++;
		}
		adj[v2].add(v1);
	}
	public boolean hasEdge(final int vertexOne,
	                       final int vertexTwo) {
		for (int each : adj(vertexOne))  {
			if (each == vertexTwo) {
				return true;
			}
		}
		return false;
	}
	public int V() {
		return v;
	}
	public int E() {
		return e;
	}
	public Iterable<Integer> adj(final int vertex) {
		return adj[vertex];
	}
}
final class Solution{
	private Solution(){

	}
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		DiGraph g = new DiGraph(scan);
		DirectedCycle obj = new DirectedCycle(g);
		System.out.println(obj.hasCycle());
	}
}