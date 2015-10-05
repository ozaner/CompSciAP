package unit1.quadraticExamples;

import java.util.Scanner;

/**
 *  To make code more understandable, it often helps to increase modularity.
 *  In other words, break the code into chunks which are more intellectually and visibly manageable.
 *  
 * @author mark.jones
 * 
 */
public class QuadSolver4_Modularity {

	static final Scanner IN = new Scanner(System.in); // an input scanner for System.in
	static final String SENTINEL = "done";            // stopping sentinel

	static int a, b, c;    // coefficients of ax^2 + bx + c = 0

	/**
	 * Solves a quadratic equation of the form ax^2 + bx + c = 0 using the quadratic formula.
	 * Demonstrates modularity.
	 * 
	 * @param args   no command line arguments expected
	 */
	public static void main(String[] args) {
		while (true) {
			// READ
			readCoefficients();  // obtain values for a, b, and c

			// EVAL
			String result = solve(a, b, c);  // solve the quadratic
			
			// PRINT
			System.out.printf("For a = %d, b = %d, c = %d, %s\n\n", a, b, c, result);
		}
	}
	
	/**
	 * Solves for the roots of a quadratic equations using the quadratic formula.
	 * @param a   the coefficients of x^2
	 * @param b   the coefficients of x
	 * @param c   the constant term
	 */
	private static String solve(int a, int b, int c) {
		int disc = b * b - 4 * a * c;       // the discriminant
		
		if (a == 0) {
			return String.format("the leading coefficient (a) " + 
					  "must not be zero");
			
		} else if (disc < 0) {
			return String.format("there are no real solutions");
			
		} else if (disc == 0) {
			return String.format("there is one real solution: %.1f",
				(-b / (2. * a)));
			
		} else { // disc > 0
			return String.format("there are two real solutions: %.1f, %.1f\n",
				(-b + Math.sqrt(disc)) / (2. * a), 
				(-b - Math.sqrt(disc)) / (2. * a));
		}
	}

	/**
	 * Inputs the quadratic equation coefficients a, b, and c.
	 */
	private static void readCoefficients() {
		while (true) {
			System.out.printf("Enter integer values for a b and c (or '%s' when finished) > ", SENTINEL);
			String line = IN.nextLine();  // get the next line of input as a string

			if (line.equals(SENTINEL)) { // this is our sentinel for stopping
				System.out.printf("Goodbye...\n");
				IN.close();  // close the input scanner
				System.exit(0);  // exit the program
			}

			Scanner sc = new Scanner(line);      // create a token scanner for the line
			if (!sc.hasNextInt()) {      // lookahead -- is the next token an int for 'a'?
				System.out.printf("Expecting 3 ints, got %s instead.\n", line);
				continue;  // skip the remainder and continue at the top of the loop
			}
			a = sc.nextInt();   // read an int for 'a' from the input stream

			if (!sc.hasNextInt()) {      // lookahead -- is the next token an int for 'b'?
				System.out.printf("Expecting 3 ints, got %s instead.\n", line);
				continue;  // skip the remainder and continue at the top of the loop
			}
			b = sc.nextInt();   // read 'b' from the input stream

			if (!sc.hasNextInt()) {      // lookahead -- is the next token an int for 'c'?
				System.out.printf("Expecting 3 ints, got %s instead.\n", line);
				continue;  // skip the remainder and continue at the top of the loop
			}			
			c = sc.nextInt();   // read 'c' from the input stream
			sc.close();  // close the line scanner
			break;
		}
	}
}
