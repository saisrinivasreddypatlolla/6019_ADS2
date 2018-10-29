import java.util.Scanner;
/**
 * Class for list graph.
 */
class ListGraph {
    /**
     * { var_description }.
     */
    private int vertices;
    /**
     * { var_description }.
     */
    private int edges;
    /**
     * { var_description }.
     */
    private Bag<Integer>[] adjacent;
    /**
     * { var_description }.
     */
    private String[] tokens;
    /**
     * Constructs the object.
     */
    ListGraph() {

    }
    /**
     * Constructs the object.
     *
     * @param      scan  The scan
     */
    ListGraph(final Scanner scan) {
        this.vertices = Integer.parseInt(scan.nextLine());
        adjacent = (Bag<Integer>[]) new Bag[vertices];
        for (int i = 0; i < vertices; i++) {
            adjacent[i] = new Bag<Integer>();
        }
        int edge = Integer.parseInt(scan.nextLine());
        tokens = scan.nextLine().split(",");
        for (int i = 0; i < edge; i++) {
            String[] inputs = scan.nextLine().split(" ");
            addEdge(Integer.parseInt(inputs[0]), Integer.parseInt(inputs[1]));
        }
    }
    /**
     * Adds an edge.
     *
     * @param      v     { parameter_description }
     * @param      w     { parameter_description }
     */
    public void addEdge(final int v, final int w) {
        if (v == w) {
            return;
        }
        if (!hasEdge(v, w)) {
            edges++;
        }
        adjacent[v].add(w);
        adjacent[w].add(v);
    }
    /**
     * { function_description }.
     *
     * @param      vertex  The vertex
     *
     * @return     { description_of_the_return_value }
     */
    public Iterable<Integer> adj(final int vertex) {
        return adjacent[vertex];
    }
    /**
     * Determines if it has edge.
     *
     * @param      vertexOne  The vertex one
     * @param      vertexTwo  The vertex two
     *
     * @return     True if has edge, False otherwise.
     */
    public boolean hasEdge(final int vertexOne,
                           final int vertexTwo) {
        for (int each : adj(vertexOne))  {
            if (each == vertexTwo) {
                return true;
            }
        }
        return false;
    }
    /**
     * Returns a string representation of the object.
     *
     * @return     String representation of the object.
     */
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append(vertices + " vertices, " + edges + " edges" + "\n");
        if (edges > 0) {
            for (int i = 0; i < vertices; i++) {
                str.append(tokens[i] + ": ");
                for (int j : adjacent[i]) {
                    str.append(tokens[j] + " ");
                }
                str.append("\n");
            }
            return str.toString();
        } else {
            str.append("No edges");
            return str.toString();
        }
    }
}
/**
 * Class for matrix graph.
 */
class MatrixGraph {
    /**
     * .
     */
    private String[] tokens;
    /**
     * .
     */
    private int[][] graphMatrix;
    /**
     * .
     */
    private int vertices;
    /**
     * .
     */
    private int edges;
    /**
     * Constructs the object.
     */
    MatrixGraph() {

    }
    /**
     * Constructs the object.
     *
     * @param      scan  The scan
     */
    MatrixGraph(final Scanner scan) {
        this.vertices = Integer.parseInt(scan.nextLine());
        graphMatrix = new int[vertices][vertices];
        int edge = Integer.parseInt(scan.nextLine());
        tokens = scan.nextLine().split(",");
        for (int i = 0; i < edge; i++) {
            String[] inputs = scan.nextLine().split(" ");
            addEdge(Integer.parseInt(
                        inputs[0]), Integer.parseInt(inputs[1]));
        }

    }
    /**
     * Adds an edge.
     *
     * @param      v     { parameter_description }
     * @param      w     { parameter_description }
     */
    public void addEdge(final int v, final int w) {
        if (v != w) {
            if (!hasEdge(v, w)) {
                graphMatrix[v][w] = 1;
                graphMatrix[w][v] = 1;
                edges++;
            }
        }

    }
    /**
     * Determines if it has edge.
     *
     * @param      v     { parameter_description }
     * @param      w     { parameter_description }
     *
     * @return     True if has edge, False otherwise.
     */
    public boolean hasEdge(final int v, final int w) {
        return graphMatrix[v][w] == 1;
    }
    /**
     * { function_description }.
     */
    public void print() {
        String str = "";
        str += vertices + " vertices, " + edges + " edges" + "\n";
        if (edges > 0) {
            for (int i = 0; i < graphMatrix.length; i++) {
                for (
                    int j = 0; j < graphMatrix[0].length; j++) {
                    str += graphMatrix[i][j] + " ";
                }
                str += "\n";
            }
            System.out.println(str);
        } else {
            System.out.println(str + "No edges");
        }
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
     * { function_description }.
     *
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        Scanner scan = new Scanner(System.in);
        String type = scan.nextLine();
        switch (type) {
        case "List":
            ListGraph obj = new ListGraph(scan);
            System.out.println(obj.toString());
            break;
        case "Matrix":
            MatrixGraph obj1 = new MatrixGraph(scan);
            obj1.print();
            break;
        default:
            break;
        }
    }
}


