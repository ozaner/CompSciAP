package unit10.examples;
import java.util.ArrayList;

/**
   This program computes permutations and shows tracing info.
 */
public class PermutationsDebug {

	private static int indent = 0;
	
	public static void main(String[] args) {
		for (String s : permutations("eat"))      
			System.out.println(s);
	}

	/**
	 * Recursively generate all permutations of the characters in a string.
	 * @param word  the string
	 * @return      a list of the permutations
	 */
	private static ArrayList<String> permutations(String word) {
		printlnIndented("permutations(\"" + word + "\")");
		ArrayList<String> result = new ArrayList<String>();

		if (word.length() < 2) {  // Base case
			result.add(word); 
		} else {                   // Recursive case
			// Loop through all character positions
			for (int i = 0; i < word.length(); i++) {
				// Form a simpler word by removing the ith character
				String shorterWord = word.substring(0, i)
						+ word.substring(i + 1);

				// Add the removed character to the front of
				// each permutation of the simpler word
				indent += 2;
				for (String s : permutations(shorterWord)) {
					result.add(word.charAt(i) + s);
				}
				indent -= 2;
			}
		}
		// Return all permutations
		printlnIndented("<==" + result);
		return result;
	}

	/**
	 * Prints an indented line. 
	 * @param str  the string to be printed
	 */
	private static void printlnIndented(String str) {
		for (int i = 0; i < indent; i++) System.out.print(" ");
		System.out.println(str);
	}
}

