import java.util.Scanner;
import java.util.Arrays;
/**
 * Class for page rank.
 */
class PageRank {
	/**
	 * array for the page ranks.
	 */
	double[] pageRank;
	/**
	 * digraph onject.
	 */
	Digraph graph;
	/**
	 * previous ranks array.
	 */
	double[] prevRank;
	Digraph revGraph;
	/**
	 * Constructs the object.
	 * Time complexities is O(V)
	 * V is number of vertices.
	 *
	 * @param      g     digraph object.
	 */
	PageRank(final Digraph g) {
		graph = g;
		revGraph = g.reverse();
		int vertices = graph.V();
		pageRank = new double[vertices];
		prevRank = new double[vertices];
		for (int i = 0; i < vertices; i++) {
			pageRank[i] = (double)1 / vertices;
		}
		for (int i = 0; i < graph.V(); i++) {
            if (graph.outdegree(i) == 0) {
                for (int j = 0; j < graph.V(); j++) {
                    if (j != i) {
                        graph.addEdge(i, j);
                    }
                }
            }
        }
        // values = new Double[graph.V()];
        for (int i = 0; i < 1000; i++) {
            for (int j = 0; j < graph.V(); j++) {
                getPR(j);
            }
            pageRank = Arrays.copyOf(prevRank, prevRank.length);
        }

	}
	/**
	 * Gets the pr.
	 * Time complexities is O(E).
	 * E is number of adjacent values to that vertex.
	 *
	 * @param      vertex  The vertex
	 *
	 * @return     The pr.
	 */
	public double getPR(final int vertex) {
		double sum = 0;
		int[] values = new int[graph.V()];
		for (int v : revGraph.adj(vertex)) {
			if (graph.indegree(v) == 0) {
				return 0;
			}
			sum += (prevRank[v] / graph.outdegree(v));
		}
		return sum;
	}


	/**
	 * this method to perform to add the pagerank to that vertex.
	 * time complexity is O(V*1000)
	 * V is number of vertices.
	 */
	public void add() {
		for (int i = 0; i < 1000; i++) {
			for (int j = 0; j < graph.V(); j++) {
				prevRank[j] = pageRank[j];
				pageRank[j] = getPR(j);
				// if(prevRank[j] == pageRank[j]){
				// 	break;
				// }
			}
		}
	}
	/**
	 * Returns a string representation of the object.
	 * Time complexity is O(V).
	 * V is number of vertices
	 *
	 * @return     String representation of the object.
	 */
	public String toString() {
		String str = "";
		for (int i = 0; i < graph.V(); i++) {
			str += i + " - " + pageRank[i] + "\n";
		}
		return str;
	}

}

class WebSearch {

}

/**
 * Class for solution.
 */
public class Solution {
	/**
	 * main method to perform operations.
	 *
	 * @param      args  The arguments
	 */
	public static void main(final String[] args) {
		Scanner scan = new Scanner(System.in);
		int vertices = Integer.parseInt(scan.nextLine());
		Digraph graph = new Digraph(vertices);
		for (int i = 0; i < vertices; i++) {
			String[] tokens = scan.nextLine().split(" ");
			for (int j = 1; j < tokens.length; j++) {
				graph.addEdge(Integer.parseInt(tokens[0]),
				              Integer.parseInt(tokens[j]));
			}
		}
		System.out.println(graph);
		PageRank pageObj = new PageRank(graph);
		// pageObj.add();
		System.out.println(pageObj.toString());
		// while (scan.hasNext()) {

		// }
		// read the first line of the input to get the number of vertices

		// iterate count of vertices times
		// to read the adjacency list from std input
		// and build the graph


		// Create page rank object and pass the graph object to the constructor

		// print the page rank object

		// This part is only for the final test case

		// File path to the web content
		String file = "WebContent.txt";

		// instantiate web search object
		// and pass the page rank object and the file path to the constructor

		// read the search queries from std in
		// remove the q= prefix and extract the search word
		// pass the word to iAmFeelingLucky method of web search
		// print the return value of iAmFeelingLucky

	}
}
