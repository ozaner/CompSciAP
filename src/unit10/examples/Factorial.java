package unit10.examples;
/**
   This program computes factorial.
 */
public class Factorial {

	public static void main(String[] args) {
		int n = 5;
		System.out.println("(recursive) " + n + "! = " + factorial(n));
		System.out.println("(iterative) " + n + "! = " + factorial2(n));
	}

	/**
	 * Recursively computes n!.
	 * @param n     the number
	 * @return      n!
	 */
	private static int factorial(int n) {
		int result;

		if (n == 1) {  // Base case
			result = 1; 
		} else {       // Recursive case
			result = n * factorial(n - 1);
		}
		
		return result;
	}

	/**
	 * Iteratively computes n!.
	 * @param n     the number
	 * @return      n!
	 */
	private static int factorial2(int n) {
		int result = 1;

		for (int i = 1; i <= n; i++)
			result *= i;
		
		return result;
	}

}

