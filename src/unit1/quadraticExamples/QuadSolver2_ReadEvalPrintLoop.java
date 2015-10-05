package unit1.quadraticExamples;

import java.util.Scanner;

/**
 * This class illustrates a "Read, Eval, Print Loop" strategy.
 * It embeds the "Read, Eval, Print" pattern in a loop.
 * In this version, it is an infinite loop, and the user must terminate by quitting the program.
 * 
 * @author mark.jones
 * 
 */
public class QuadSolver2_ReadEvalPrintLoop {
	
	static final Scanner IN = new Scanner(System.in); // an input scanner for System.in
	
	/**
	 * Solves a quadratic equation of the form ax^2 + bx + c = 0 using the quadratic formula.
	 * Demonstrates "Read, Eval, Print Loop" patttern.
	 * 
	 * @param args   no command line arguments expected
	 */
	public static void main(String[] args) {		
		while (true) {
			int a, b, c;    // coefficients of ax^2 + bx + c = 0
			
			// READ
			System.out.printf("Enter integer values for a b and c > ");
			a = IN.nextInt();   // read a from the input stream
			b = IN.nextInt();   // read b from the input stream
			c = IN.nextInt();   // read c from the input stream

			// EVAL
			int disc = b * b - 4 * a * c;       // the discriminant
			
			// PRINT		
			String result;
			if (a == 0) {
				result = "the leading coefficient (a) must not be zero";

			} else if (disc < 0) {
				result = "there are no real solutions";
				
			} else if (disc == 0) {
				result = String.format("there is one real solution: %.1f",
					(-b / (2. * a)));
				
			} else { // disc > 0
				result = String.format("There are two real solutions: %.1f, %.1f",
						(-b + Math.sqrt(disc)) / (2. * a), 
						(-b - Math.sqrt(disc)) / (2. * a));
			}

			System.out.printf("For a = %d, b = %d, c = %d, %s\n\n", a, b, c, result);
		}
		
		// IN.close();  // unreachable with the infinite loop above
	}
}
