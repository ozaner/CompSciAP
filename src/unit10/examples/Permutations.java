package unit10.examples;
import java.util.ArrayList;

/**
   This program computes permutations.
 */
public class Permutations {
	
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
				for (String s : permutations(shorterWord)) {
					result.add(word.charAt(i) + s);
				}
			}
		}
		// Return all permutations
		return result;
	}
}

