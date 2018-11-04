import java.io.File;
import java.io.FileReader;
import java.util.Scanner;
import java.util.HashMap;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;
import java.util.HashMap;
import java.util.ArrayList;
import java.io.FileNotFoundException;
// import java.util.Arrays;
class Example {
	public static void main(String[] args) {
		int count = 0;
		Scanner scan = new Scanner(System.in);
		String file1 = scan.nextLine();
		String file2 = scan.nextLine();
		String type = scan.nextLine();
		Synset obj = new Synset();
		obj.addSynset(file1);
		Digraph dig = obj.addHypenym(file2);
		switch (type) {
		case "Graph":
			DirectedCycle obj1 = new DirectedCycle(dig);
			for (int i = 0; i < dig.vertices(); i++) {
				int outdegree = dig.outdegree(i);
				if (outdegree == 0) {
					count++;
				}
			}
			if (count > 1) {
				System.out.println("Multiple Roots");
			}
			if (obj1.hasCycle()) {
				System.out.println("Cycle detected");
			} else {
				System.out.println(dig);
			}
			break;
		case "Queries":
			while (scan.hasNext()) {
				String[] tokens = scan.nextLine().split(" ");
				if (tokens[0].equals("null")) {
					System.out.println("IllegalArgumentException");
					return;
				}
				SAP obj2 = new SAP(dig);
				ArrayList<Integer> vertexOne = obj.getRevmap().get(tokens[0]);
				ArrayList<Integer> vertexTwo = obj.getRevmap().get(tokens[1]);
				int[] array = obj2.length(vertexOne, vertexTwo);
				ArrayList<String> result = obj.getMap().get(array[1]);
				String tmp = result.get(0);
				System.out.print("distance = " + array[0] + ", ancestor = ");
				for (int k = 0 ; k < result.size(); k++) {
					if (k != result.size() - 1) {
						System.out.print(result.get(k) + " ");
					} else {
						System.out.print(result.get(k));
					}
				}
				System.out.println();
			}
			break;
		}
	}

}

class Synset {
	Digraph graph;
	HashMap<Integer, ArrayList<String>> map;
	HashMap<String, ArrayList<Integer>> revmap;
	Synset() {
		map = new HashMap<Integer, ArrayList<String>>();
		revmap = new HashMap<String, ArrayList<Integer>>();


	}
	public void addSynset(String file1) {
		try {
			File file = new File("Files");
			File[] fileList = file.listFiles();

			ArrayList<String> list;
			for (File f : fileList) {
				Scanner sc = new Scanner(f);
				if (f.getName().equals(file1)) {
					while (sc.hasNext()) {
						String[] tokens = sc.nextLine().split(",");
						// System.out.println(Arrays.toString(tokens));
						String[] synonyms = tokens[1].split(" ");
						list = new ArrayList<String>();
						for (int i = 0; i < synonyms.length; i++) {
							list.add(synonyms[i]);
						}
						map.put(Integer.parseInt(tokens[0]), list);
					}
				}
			}
			for (Integer key : map.keySet()) {
				ArrayList<String> values = map.get(key);
				for (String value : values) {
					if (revmap.containsKey(value)) {
						ArrayList<Integer> revVal = revmap.get(value);
						revVal.add(key);
						revmap.put(value, revVal);
					} else {
						ArrayList<Integer> it = new ArrayList<Integer>();
						it.add(key);
						revmap.put(value, it);
					}
				}
			}
			int vertices = map.size();
			graph = new Digraph(vertices);
		} catch (FileNotFoundException e) {
			System.out.println(e);
		}
	}
	public Digraph addHypenym(String file2) {
		try {
			File files = new File("Files");
			File[] filelist2 = files.listFiles();
			for (File f : filelist2) {
				Scanner sc = new Scanner(f);
				if (f.getName().equals(file2)) {
					while (sc.hasNext()) {
						String[] hypers = sc.nextLine().split(",");
						for (int i = 1; i < hypers.length; i++) {
							graph.addEdge(Integer.parseInt(hypers[0]), Integer.parseInt(hypers[i]));
						}
					}

				}
			}
		} catch (FileNotFoundException e) {
			System.out.println(e);
		}
		return graph;
	}
	public HashMap<Integer, ArrayList<String>> getMap(){
		return map;
	}
	public HashMap<String, ArrayList<Integer>> getRevmap(){
		return revmap;
	}
}