package unit8.algorithms;
import java.util.Arrays;
import java.util.Scanner;

/**
   This program tests the binary search algorithm.
 */
public class BinarySearchTester {  
	public static void main(String[] args) {  
		int[] a = ArrayUtil.randomIntArray(20, 100);
		Arrays.sort(a);
		ArrayUtil.print(a);
		BinarySearcher searcher = new BinarySearcher(a);
		Scanner in = new Scanner(System.in);

		while (true) {
			System.out.print("Enter number to search for, -1 to quit: ");
			int n = in.nextInt();
			if (n == -1) break;

			int pos = searcher.search(n);
			System.out.println("Found in position " + pos);
		}
		in.close();
	}
}
