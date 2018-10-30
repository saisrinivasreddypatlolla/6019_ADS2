import java.util.Scanner;
/**
 * Class for solution.
 */
class Perculation {
    /**
     * number of vertices.
     */
    private int vertices;
    /**
     * this method checks input is perculated or not
     * and returns the boolean value.
     * Time complexity is O(N^2).
     * addEdge method of graph method is called untill all
     * edges are connected.
     *
     * @param      grid  The grid
     * @param      graphObj    The graphics
     * @param      vertices1    The vertices
     *
     * @return     returns boolean value.
     */
    public boolean perculation(final boolean[][] grid,
                               final Graph graphObj,
                               final int vertices1) {
        vertices = vertices1;
        for (int i = 0; i < vertices; i++) {
            for (int j = 0; j < vertices; j++) {
                if (grid[i][j]) {
                    int tmp = simplify(i, j);
                    if (i == 0) {

                        graphObj.addEdge(
                            tmp, vertices * vertices);
                    }
                    if (i == vertices - 1) {
                        graphObj.addEdge(tmp,
                                         vertices * vertices + 1);
                    }
                    if (i - 1 >= 0
                        && grid[i - 1][j]) {
                        graphObj.addEdge(tmp,
                                         simplify(i - 1, j));
                    }
                    if (i + 1 < vertices
                        && grid[i + 1][j]) {
                        graphObj.addEdge(
                            tmp, simplify(i + 1, j));
                    }
                    if (j - 1 >= 0
                        && grid[i][j - 1]) {
                        graphObj.addEdge(tmp,
                                         simplify(i, j - 1));
                    }
                    if (j + 1 < vertices
                        && grid[i][j + 1]) {
                        graphObj.addEdge(
                            tmp, simplify(i, j + 1));
                    }

                }
            }
        }
        DepthFirstPaths dp = new DepthFirstPaths(
            graphObj, vertices * vertices);
        return dp.hasPathTo(vertices * vertices + 1);
    }
    /**
     * this method gives the edge of graph.
     * Time complexity is O(1).
     *
     * @param      row     row
     * @param      column     column
     *
     * @return     returns the edge of the graph.
     */
    public int simplify(final int row, final int column) {
        return row * vertices + column;
    }
}
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
     * this main method performs operations
     * Time complexity is O(N^2).
     *
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        Scanner scan = new Scanner(System.in);
        int vertices = Integer.parseInt(scan.nextLine());
        Perculation obj = new Perculation();
        boolean[][] grid = new boolean[vertices][vertices];
        Graph gObj = new Graph(vertices * vertices + 2);
        try {
            while (scan.hasNext()) {
                int row = scan.nextInt(), col = scan.nextInt();
                grid[row - 1][col - 1] = true;

            }
        } catch (Exception e) {

        } finally {
            System.out.println(obj.perculation(
                                   grid, gObj, vertices));
        }
    }
}
