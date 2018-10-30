import java.util.*;
class Solution {
	static int v;
	static boolean perculation(boolean[][] grid, Graph gr) {
		for (int i = 0; i < v; i++) {
			for (int j = 0; j < v; j++) {
				if (grid[i][j]) {
					int tmp = trans(i, j);
					if (i == 0) {

						gr.addEdge(tmp, v * v);
					}
					if (i == v - 1) {
						gr.addEdge(tmp, v * v + 1);
					}
					if (i - 1 >= 0 && grid[i - 1][j]) {
						gr.addEdge(tmp, trans(i - 1, j));
					}
					if (i + 1 < v && grid[i + 1][j]) {
						gr.addEdge(tmp, trans(i + 1, j));
					}
					if (j - 1 >= 0 && grid[i][j - 1]) {
						gr.addEdge(tmp, trans(i, j - 1));
					}
					if (j + 1 < v && grid[i][j + 1]) {
						gr.addEdge(tmp, trans(i, j + 1));
					}

				}
			}
		}
		DepthFirstPaths dp = new DepthFirstPaths(gr, v * v);
		return dp.hasPathTo(v * v + 1);
	}

	static int trans(int i, int j) {
		return i * v + j;
	}
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		v = Integer.parseInt(scan.nextLine());
		// v *= v;
		boolean[][] grid = new boolean[v][v];
		Graph g = new Graph(v * v + 2);
		try {
			while (scan.hasNext()) {
				int row = scan.nextInt(), col = scan.nextInt();
				grid[row - 1][col - 1] = true;

			}
		} catch (Exception e) {

		} finally {
			System.out.println(perculation(grid, g));
		}
	}
}