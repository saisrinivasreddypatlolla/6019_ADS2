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
	 * main method to perform operations.
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
	 * read file method reads the data in file and stores.
	 * it in string array.
	 *
	 * @param      file  The file
	 *
	 * @return     returns string array with words.
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
	 * @return     returns BST object.
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
/**
 * Class for t 9.
 */
class T9 {
	/**
	 * TST object to store the words and frequency.
	 */
	private TST<Integer> trie;
	/**
	 * Constructs the object.
	 * Time complexity is O(K).
	 * K is number of keys.
	 *
	 * @param      stObj  The st object
	 */
	public T9(final BinarySearchST<String, Integer> stObj) {
		trie = new TST<>();
		for (String key : stObj.keys()) {
			trie.put(key, stObj.get(key));
		}
	}

	/**
	 * Gets all words.
	 *
	 * @param      prefix  The prefix
	 *
	 * @return     All words.
	 */
	public Iterable<String> getAllWords(String prefix) {
		return trie.keysWithPrefix(prefix);
	}
	/**
	 * { function_description }.
	 *
	 * @param      t9Signature  The t 9 signature
	 *
	 * @return     { description_of_the_return_value }
	 */
	public Iterable<String> potentialWords(final String t9Signature) {
		ArrayList<String> ar = new ArrayList<>();
		for (String each : trie.keys()) {
			String[] str = each.split("");
			String pattern = "";
			for (String charac : str) {
				if (charac.equals("a") || charac.equals("b") || charac.equals("c")) {
					pattern += "2";
				}
				if (charac.equals("d") || charac.equals("e") || charac.equals("f")) {
					pattern += "3";
				}
				if (charac.equals("g") || charac.equals("h") || charac.equals("i")) {
					pattern += "4";
				}
				if (charac.equals("j") || charac.equals("k") || charac.equals("l")) {
					pattern += "5";
				}
				if (charac.equals("m") || charac.equals("n") || charac.equals("o")) {
					pattern += "6";
				}
				if (charac.equals("p") || charac.equals("q") || charac.equals("r") || charac.equals("s")) {
					pattern += "7";
				}
				if (charac.equals("t") || charac.equals("u") || charac.equals("v")) {
					pattern += "8";
				}
				if (charac.equals("w") || charac.equals("x") || charac.equals("y") || charac.equals("z")) {
					pattern += "9";
				}
			}
			if (pattern.equals(t9Signature)) {
				ar.add(each);
			}
		}
		return ar;
	}

	/**
	 * Gets the suggestions.
	 *
	 * @param      words  The words
	 * @param      k      number of
	 *
	 * @return     The suggestions.
	 */
	public Iterable<String> getSuggestions(
	    final Iterable<String> words, final int k) {
		BinarySearchST<Integer, String>
		stObj = new BinarySearchST<Integer, String>();
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
			if (!stObj.isEmpty()) {
				Integer i = stObj.max();
				ascendingOrder[j] = stObj.get(i);
				stObj.deleteMax();
			}
		}
		Arrays.sort(ascendingOrder);
		for (int n = k - 1; n >= 0; n--) {
			bag.add(ascendingOrder[n]);
		}
		return bag;
	}

	/**
	 * this method returns the t9 formate.
	 *
	 * @param      t9Signature  The t 9 signature
	 * @param      k            number of inputs
	 *
	 * @return     returns iterable with t9 format.
	 */
	public Iterable<String> t9(final String t9Signature,
	                           final int k) {
		return getSuggestions(potentialWords(t9Signature), k);
	}
}
