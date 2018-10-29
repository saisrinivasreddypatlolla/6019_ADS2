import java.util.Scanner;
/**
 * Class for list graph.
 */
class ListGraph {
    /**
     * number f vertices.
     */
    private int vertices;
    /**
     * number of edges.
     */
    private int edges;
    /**
     * bag of adjacent values.
     */
    private Bag<Integer>[] adjacent;
    /**
     * intput strings.
     */
    private String[] tokens;
    /**
     * Constructs the object.
     */
    ListGraph() {

    }
    /**
     * Constructs the object.
     * Time complexity of this method is O(N)
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
     * Time complexity of this method is O(N)
     *
     *
     * @param      vertexOne  The vertex one
     * @param      vertexTwo  The vertex two
     */
    public void addEdge(final int vertexOne, final int vertexTwo) {
        if (vertexOne == vertexTwo) {
            return;
        }
        if (!hasEdge(vertexOne, vertexTwo)) {
            edges++;
        }
        adjacent[vertexOne].add(vertexTwo);
        adjacent[vertexTwo].add(vertexOne);
    }
    /**
     * this method returns the iterable.
     *
     * @param      vertex  The vertex
     *
     * @return     the iterable
     */
    public Iterable<Integer> adj(final int vertex) {
        return adjacent[vertex];
    }
    /**
     * Determines if it has edge.
     * Time complexity of this method is O(N)
     *
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
     * Time complexity of this method is O(N)
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
     * Time complexity of this method is O(N)
     *
     * @param      vertexOne     vertexOne
     * @param      vertexTwo     vertexTwo
     */
    public void addEdge(final int vertexOne, final int vertexTwo) {
        if (vertexOne != vertexTwo) {
            if (!hasEdge(vertexOne, vertexTwo)) {
                graphMatrix[vertexOne][vertexTwo] = 1;
                graphMatrix[vertexTwo][vertexOne] = 1;
                edges++;
            }
        }

    }
    /**
     * Determines if it has edge.
     * Time complexity of this method is O(N)
     *
     * @param      vertexOne     vertexOne
     * @param      vertexTwo     vertexTwo
     *
     * @return     True if has edge, False otherwise.
     */
    public boolean hasEdge(final int vertexOne, final int vertexTwo) {
        return graphMatrix[vertexOne][vertexTwo] == 1;
    }
    /**
     * this method prints all values.
     * Time complexity of this method is O(N)
     *
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
     * this main method performs the operations.
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



