package unit11.challengeCode;

import java.io.*;
import java.util.Scanner;

/** 
 * 2009 NJIT Programming Contest:   Practice Problem 0.  Average a set of numbers.  
 *     - Expects the first input line to be number of remaining lines to process.
 * @author majones
 */

public class Average {
	static Scanner input;
	
//	static PrintStream output;
	static PrintWriter output;    // for file writing

	public static void main(String[] args) throws IOException {
//		input = new Scanner(System.in);                     // for stdin
		input = new Scanner(new FileReader(args[0]));       // for file reading
		
//		output = System.out;                                // for stdout
		output = new PrintWriter(new FileWriter(args[1]));  // for file writing
    	
    	// get n = number of following input lines
    	String line = input.nextLine();
    	Scanner scline = new Scanner(line);
        int n = scline.nextInt();

        double sum = 0.0;
        for (int j=1; j<=n; j++) {
        	line = input.nextLine();
        	scline = new Scanner(line);
        	double num = scline.nextDouble();
        	sum += num;
        }
        
        double ave = sum / n;
        output.println(ave);
        
    	scline.close();
        output.flush();
        input.close();
        output.close();
	}
}
