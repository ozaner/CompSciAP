package unit1.quadraticExamples;

import java.util.Scanner;

/**
 *  To facilitate error checking, it often pays to have an input scanner get
 *  a line at a time (as a string), and then to have a line scanner operate on
 *  the line to process the values on each line appropriately.
 *  
 *  The line scanner can use "lookahead" to see if the kind of token (value)
 *  that it wants is available.  A "sentinel" value is sometimes used to signal
 *  that there is no more input.
 *  
 *  The code below also illustrates the "break" and "continue" statements that
 *  are useful inside loops.
 * 
 * @author mark.jones
 * 
 */
public class QuadSolver3_Lookahead {
	
	static final Scanner IN = new Scanner(System.in); // an input scanner for System.in
	static final String SENTINEL = "done";            // stopping sentinel
	
	/**
	 * Solves a quadratic equation of the form ax^2 + bx + c = 0 using the quadratic formula.
	 * Demonstrates lookahead.
	 * 
	 * @param args   no command line arguments expected
	 */
	public static void main(String[] args) {

		while (true) {
			int a, b, c;    // coefficients of ax^2 + bx + c = 0
						
			// READ
			System.out.printf("Enter integer values for a b and c (or '%s' when finished) > ", SENTINEL);
			String line = IN.nextLine();  // get the next line of input as a string

			if (line.equals(SENTINEL)) { // this is our sentinel for stopping
				System.out.printf("Goodbye...\n");
				break;  // exit the loop
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
		
		IN.close();  // close the input scanner
	}
}
