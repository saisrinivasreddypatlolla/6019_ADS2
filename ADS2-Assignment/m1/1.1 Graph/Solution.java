import java.util.Scanner;
class ListGraph {
	int vertices;
	int edges;
	Bag<Integer>[] adjacent;
	private String[] tokens;
	ListGraph() {

	}
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
	public void addEdge(int v, int w) {
		if (v == w) {
            return;
        }
        if (!hasEdge(v, w)) {
            edges++;
        }
        adjacent[v].add(w);
        adjacent[w].add(v);
	}
	 public Iterable<Integer> adj(final int vertex) {
        return adjacent[vertex];
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
class MatrixGraph {
	private String[] tokens;
	private int[][] graphMatrix;
	private int vertices;
	private int edges;
	MatrixGraph() {

	}
	MatrixGraph(Scanner scan) {
		this.vertices = Integer.parseInt(scan.nextLine());
		graphMatrix = new int[vertices][vertices];
		int edge = Integer.parseInt(scan.nextLine());
		tokens = scan.nextLine().split(",");
		for (int i = 0; i < edge; i++) {
			String[] inputs = scan.nextLine().split(" ");
			addEdge(Integer.parseInt(inputs[0]), Integer.parseInt(inputs[1]));
		}

	}
	public void addEdge(int v, int w) {
		if (v != w) {
			if (!hasEdge(v, w)) {
				graphMatrix[v][w] = 1;
				graphMatrix[w][v] = 1;
				edges++;
			}
		}

	}
	public boolean hasEdge(int v, int w) {
		return graphMatrix[v][w] == 1;
	}
	public void print() {
		String str = "";
		str += vertices + " vertices," + edges + " edges" + "\n";
		if (edges > 0) {
			for (int i = 0; i < graphMatrix.length; i++) {
				for (int j = 0; j < graphMatrix[0].length; j++) {
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
class Solution {
	private Solution() {

	}
	public static void main(String[] args) {
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

