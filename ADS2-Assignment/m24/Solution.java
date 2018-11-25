import java.util.Scanner;
import java.lang.Runtime;
import java.io.File;
import java.io.FileNotFoundException;

public final class Solution {
	
	public static void main(String[] args) {
		try {
		File fr = new File("largeT.txt");
		Scanner sc = new Scanner(fr);
		String[] s = new String[10000000];
		int j = 0;
		while (sc.hasNext()) {
			s[j] = sc.nextLine().trim();
			j++;
		}
		BSTree<Integer, Integer> bstar = new BSTree<Integer, Integer>(10000000);
		StopwatchCPU stp = new StopwatchCPU();
		for (int i = 0; i < 10000000; i++) {
			bstar.put(Integer.parseInt(s[i]), i);
			// System.out.println("Hello");
		}
		System.out.println("Time Elapsed: " + stp.elapsedTime());
		Runtime runtime = Runtime.getRuntime();

		runtime.gc();

		long memory = runtime.totalMemory() - runtime.freeMemory();

		System.out.println("Memory used: " + memory);

		StopwatchCPU stp1 = new StopwatchCPU();

		for (int i = 0; i < 10000000; i++) {
			bstar.get(Integer.parseInt(s[i]));
		}
		System.out.println("Time Elapsed: " + stp1.elapsedTime());

		Runtime run = Runtime.getRuntime();

		long memory1 = run.totalMemory() - run.freeMemory();

		System.out.println("Memory used: " + memory1);

		StopwatchCPU stp2 = new StopwatchCPU();

		for (int i = 0; i < 10000000; i++) {
			bstar.delete(Integer.parseInt(s[i]));
		}
		System.out.println("Time Elapsed: " + stp2.elapsedTime());

		Runtime run1 = Runtime.getRuntime();

		long memory2 = run1.totalMemory() - run1.freeMemory();

		System.out.println("Memory used: " + memory2);

		BST<Integer, Integer> bst = new BST<Integer, Integer>();

		StopwatchCPU stp3 = new StopwatchCPU();

		for (int i = 0; i < 10000000; i++) {
			bst.put(Integer.parseInt(s[i]), i);
		}
		System.out.println("Time Elapsed: " + stp3.elapsedTime());

		Runtime run2 = Runtime.getRuntime();

		long memory3 = run2.totalMemory() - run2.freeMemory();

		System.out.println("Memory used: " + memory3);

		StopwatchCPU stp4 = new StopwatchCPU();

		for (int i = 0; i < 10000000; i++) {
			bst.get(Integer.parseInt(s[i]));
		}
		System.out.println("Time Elapsed: " + stp4.elapsedTime());

		Runtime run3 = Runtime.getRuntime();

		long memory4 = run3.totalMemory() - run3.freeMemory();

		System.out.println("Memory used: " + memory4);

		StopwatchCPU stp5 = new StopwatchCPU();

		for (int i = 0; i < 10000000; i++) {
			bst.delete(Integer.parseInt(s[i]));
		}
		System.out.println("Time Elapsed: " + stp5.elapsedTime());

		Runtime run4 = Runtime.getRuntime();

		long memory5 = run4.totalMemory() - run4.freeMemory();

		System.out.println("Memory used: " + memory5);
	} catch(Exception e) {
		e.printStackTrace();
		System.out.println(e);
	} 
	
	}
		
}