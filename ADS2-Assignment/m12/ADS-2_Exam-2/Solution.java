import java.util.Scanner;
public class Solution {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int vertices = Integer.parseInt(scan.nextLine());
		int edges = Integer.parseInt(scan.nextLine());
		EdgeWeightedGraph graph = new EdgeWeightedGraph(vertices);
		for(int i = 0;i<edges;i++){
			String[] edgeValues = scan.nextLine().split(" ");
			graph.addEdge(new Edge(Integer.parseInt(edgeValues[0]), Integer.parseInt(edgeValues[1]),
				Double.parseDouble(edgeValues[2])));
		}
		String caseToGo = scan.nextLine();
		switch (caseToGo) {
		case "Graph":
			System.out.println(graph);
			break;

		case "DirectedPaths":
			String[] path = scan.nextLine().split(" ");
			DijkstraSP obj = new DijkstraSP(graph, Integer.parseInt(path[0]));
			if(obj.hasPathTo(Integer.parseInt(path[1]))){
				System.out.println(obj.distTo(Integer.parseInt(path[1])));
			} else{
				System.out.println("No Path Found.");
			}
			break;

		case "ViaPaths":
			String[] viaPath = scan.nextLine().split(" ");
			DijkstraSP obj1 = new DijkstraSP(graph, Integer.parseInt(viaPath[0]));
			DijkstraSP obj2 = new DijkstraSP(graph, Integer.parseInt(viaPath[1]));
			String str = "";
			if(obj1.hasPathTo(Integer.parseInt(viaPath[1]))&&obj2.hasPathTo(Integer.parseInt(viaPath[2]))){
				System.out.println(obj1.distTo(Integer.parseInt(viaPath[1]))+obj2.distTo(Integer.parseInt(viaPath[2])));
				for( Integer i : obj1.pathTo(Integer.parseInt(viaPath[1]))){
					str += i+" ";
				}
				for( Integer j : obj2.pathTo(Integer.parseInt(viaPath[2]))){
					str += j+" ";
				}
				str += viaPath[2];
				System.out.println(str);
			} else{
				System.out.println("No Path Found.");
			}

			break;

		default:
			break;
		}

	}
}