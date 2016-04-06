package unit10.examples;
/**
   This program computes factorial.
 */
public class FactorialDebug {

	private static int indent = 0;

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
		printlnIndented("factorial(\"" + n + "\")");
		int result;

		if (n == 1) {  // Base case
			result = 1; 
		} else {       // Recursive case
			indent += 2;
			result = n * factorial(n - 1);
			indent -= 2;
		}
		
		printlnIndented("<==" + result);		
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
	
	/**
	 * Prints an indented line. 
	 * @param str  the string to be printed
	 */
	private static void printlnIndented(String str) {
		for (int i = 0; i < indent; i++) System.out.print(" ");
		System.out.println(str);
	}
}

