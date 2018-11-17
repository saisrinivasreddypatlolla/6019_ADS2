import java.util.Scanner;
import java.util.*;

/**
 * Class for solution.
 */
public class Solution {
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
		String cases = scan.nextLine();

		switch (cases) {
		case "loadDictionary":
			// input000.txt and output000.txt
			BinarySearchST<String, Integer> hash = loadDictionary("/Files/t9.csv");
			while (scan.hasNextLine()) {
				String key = scan.nextLine();
				System.out.println(hash.get(key));
			}
			break;

		case "getAllPrefixes":
			// input001.txt and output001.txt
			T9 t9 = new T9(loadDictionary("/Files/t9.csv"));
			while (scan.hasNextLine()) {
				String prefix = scan.nextLine();
				for (String each : t9.getAllWords(prefix)) {
					System.out.println(each);
				}
			}
			break;

		case "potentialWords":
			// input002.txt and output002.txt
			t9 = new T9(loadDictionary("/Files/t9.csv"));
			int count = 0;
			while (scan.hasNextLine()) {
				String t9Signature = scan.nextLine();
				for (String each : t9.potentialWords(t9Signature)) {
					count++;
					System.out.println(each);
				}
			}
			if (count == 0) {
				System.out.println("No valid words found.");
			}
			break;

		case "topK":
			// input003.txt and output003.txt
			t9 = new T9(loadDictionary("/Files/t9.csv"));
			Bag<String> bag = new Bag<String>();
			int k = Integer.parseInt(scan.nextLine());
			while (scan.hasNextLine()) {
				String line = scan.nextLine();
				bag.add(line);
			}
			for (String each : t9.getSuggestions(bag, k)) {
				System.out.println(each);
			}

			break;

		case "t9Signature":
			// input004.txt and output004.txt
			t9 = new T9(loadDictionary("/Files/t9.csv"));
			bag = new Bag<String>();
			k = Integer.parseInt(scan.nextLine());
			while (scan.hasNextLine()) {
				String line = scan.nextLine();
				for (String each : t9.t9(line, k)) {
					System.out.println(each);
				}
			}
			break;

		default:
			break;

		}
	}

	/**
	 * { function_description }.
	 *
	 * @param      file  The file
	 *
	 * @return     { description_of_the_return_value }
	 */
	public static String[] toReadFile(final String file) {
		In in = new In(file);
		return in.readAllStrings();
	}
	/**
	 * Loads a dictionary.
	 *
	 * @param      file  The file
	 *
	 * @return     { description_of_the_return_value }
	 */
	public static BinarySearchST <
	String, Integer > loadDictionary(final String file) {
		BinarySearchST <
		String, Integer >  stObj = new BinarySearchST <
		String, Integer > ();
		String[] words = toReadFile(file);
		for (int i = 0; i < words.length; i++) {
			String word = words[i].toLowerCase();
			if (!stObj.contains(word)) {
				stObj.put(word, 1);
			} else {
				stObj.put(word, stObj.get(word) + 1);
			}
		}
		return stObj;
	}

}

class T9 {
	TST<Integer> trie;
	public T9(BinarySearchST<String, Integer> stObj) {
		trie = new TST<>();
		for (String key : stObj.keys()) {
			trie.put(key, stObj.get(key));
		}
	}

	// get all the prefixes that match with given prefix.
	public Iterable<String> getAllWords(String prefix) {
		return trie.keysWithPrefix(prefix);
	}

	public Iterable<String> potentialWords(String t9Signature) {
		ArrayList<String> ar = new ArrayList<>();
		for (String each : trie.keys()) {
			String[] str = each.split("");
			String pattern = "";
			for (String a : str) {
				if (a.equals("a") || a.equals("b") || a.equals("c")) {
					pattern += "2";
				}
				if (a.equals("d") || a.equals("e") || a.equals("f")) {
					pattern += "3";
				}
				if (a.equals("g") || a.equals("h") || a.equals("i")) {
					pattern += "4";
				}
				if (a.equals("j") || a.equals("k") || a.equals("l")) {
					pattern += "5";
				}
				if (a.equals("m") || a.equals("n") || a.equals("o")) {
					pattern += "6";
				}
				if (a.equals("p") || a.equals("q") || a.equals("r") || a.equals("s")) {
					pattern += "7";
				}
				if (a.equals("t") || a.equals("u") || a.equals("v")) {
					pattern += "8";
				}
				if (a.equals("w") || a.equals("x") || a.equals("y") || a.equals("z")) {
					pattern += "9";
				}
			}
		}
		return null;
	}

	// return all possibilities(words), find top k with highest frequency.
	public Iterable<String> getSuggestions(Iterable<String> words, int k) {
		BinarySearchST<Integer, String> stObj = new BinarySearchST<Integer, String>();
		int m = 0;
		for (String key : words) {
			Integer value = trie.get(key);
			if (stObj.contains(value)) {
				String str = stObj.get(value);
				if (str.length() > key.length()) {
					key = str;
				}
			}
			stObj.put(value, key);
		}
		Bag<String> bag = new Bag<String>();
		String[] ascendingOrder = new String[k];
		for (int j = 0; j < k; j++) {
			Integer i = stObj.max();
			ascendingOrder[j] = stObj.get(i);
			stObj.deleteMax();
		}
		Arrays.sort(ascendingOrder);
		for (int n = k - 1; n >= 0; n--) {
			bag.add(ascendingOrder[n]);
		}
		return bag;
	}

	// final output
	// Don't modify this method.
	public Iterable<String> t9(String t9Signature, int k) {
		return getSuggestions(potentialWords(t9Signature), k);
	}
}
