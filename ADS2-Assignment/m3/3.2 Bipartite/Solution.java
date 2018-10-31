import java.util.Scanner;
/**
 * Class for bipartite.
 */
class Bipartite {
	/**
	 * { var_description }
	 */
	boolean[] marked;
	boolean[] color;
	boolean flag;
	int[] edgeTo;
	Bipartite(Graph g) {
		int vertices = g.vertices();
		marked = new boolean[vertices];
		color = new boolean[vertices];
		edgeTo = new int[vertices];
		flag = true;
		for (int i = 0; i < vertices; i = edgeTo[i]) {
			if (!marked[i]) {
				dfs(g, i);
			}
		}
	}
	public void dfs(Graph g, int vertices) {
		marked[vertices] = true;
		color[vertices] = true;
		for (int w : g.adj(vertices)) {
			if (!flag) {
				return;
			} else if (!marked[w]) {
				edgeTo[w] = vertices;
				dfs(g, w);
			} else if (color[w] == color[vertices]) {
				flag = false;
			}
		}
	}
	public boolean isBipartite() {
		return flag;
	}
}
class Solution {
	private Solution() {

	}
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int vertices = Integer.parseInt(scan.nextLine());
		int edges = Integer.parseInt(scan.nextLine());
		Graph g = new Graph(vertices);
		for (int i = 0; i < edges; i++) {
			g.addEdge(scan.nextInt(), scan.nextInt());
		}
		Bipartite obj = new Bipartite(g);
		if (obj.isBipartite()) {
			System.out.println("Graph is bipartite");
		} else {
			System.out.println("Graph is not a bipartite");
		}
	}
}