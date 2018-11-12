import java.util.Scanner;
import java.util.Arrays;
/**
 * Class for lsd.
 */
class LSD {
	/**
	 * this method performs sorting of the array of strings.
	 * Time complexity is O(w*N).
	 *
	 * @param      a     array of strings.
	 * @param      w     fixed lenth.
	 */
	public void sort(final String[] a, final int w) {
		int n = a.length;
		final int extendedASCII = 256;
		String[] aux = new String[n];
		for (int d = w - 1; d >= 0; d--) {
			int[] count = new int[extendedASCII + 1];
			for (int i = 0; i < n; i++) {
				count[a[i].charAt(d) + 1]++;
			}
			for (int r = 0; r < extendedASCII; r++) {
				count[r + 1] += count[r];
			}
			for (int i = 0; i < n; i++) {
				aux[count[a[i].charAt(d)]++] = a[i];
			}
			for (int i = 0; i < n; i++) {
				a[i] = aux[i];
			}
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
	 * main method to perform operations.
	 * Time complexity is O(N+w*N).
	 * 
	 *
	 * @param      args  The arguments
	 */
	public static void main(final String[] args) {
		Scanner scan = new Scanner(System.in);
		int count = Integer.parseInt(scan.nextLine());
		String[] array = new String[count];
		for (int i = 0; i < count; i++) {
			array[i] = scan.nextLine();
		}
		LSD obj = new LSD();
		obj.sort(array, array[0].length());
		System.out.println(Arrays.toString(array));
	}
}