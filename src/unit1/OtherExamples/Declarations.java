package unit1.OtherExamples;
/**
 * This class illustrates how to declare and to print four important data types in Java:
 *    + String
 *    + int
 *    + double
 *    + boolean
 *    
 * Many simple programs can be written using just these data types.
 *    
 * @author mark.jones
 *
 */
public class Declarations {

	/**
	 * Demonstrates the data types String, int, double and boolean.
	 * @param args		no command line arguments expected
	 */
	public static void main(String[] args) {
		
		// Examples of declarations for reference type String (for character strings)
		String alphabet = "abcdefghijklmnopqrstuvwxyz";
		String vowels = "aeiou";
		String studentID = "035482";
		String address = "255 Lafayette Ave, Chatham, NJ 07928";
		String pilcrowSign = "\u00B6";
		
		System.out.printf("The English alphabet is %s.\n", alphabet);
		// System.out.println("The English alphabet is " + alphabet + ".");
		System.out.printf("The letters %s are vowels.\n", vowels);
		System.out.printf("My student ID is %s.\n", studentID);
		System.out.printf("I go to school at %s.\n", address);
		System.out.printf("%s is the pilcrow sign.\n", pilcrowSign);
		System.out.printf("\n");
		
		System.out.printf("Last\tFirst\n----\t-----\n");
		System.out.printf("%s\t%s\n", "Doe", "Jane");
		System.out.printf("%s\t%s\n", "Smith", "John");
		System.out.printf("\n");
		
		// *****************************************************************	
		// Examples of declarations for primitive type int (for integers)
		int july = 7;
		int day = 4;
		int year = 2013;
		int p1 = 2, p2 = 3, p3 = 5;
		
		// Examples of printing decimal integers (format code %d)
		System.out.printf("July is month %d.\n", july);
		System.out.printf("This year is %d.\n", year);
		System.out.printf("Independence Day is %d/%d.\n", july, day);
		System.out.printf("%d, %d and %d are the first three prime numbers.\n", p1, p2, p3);
		System.out.printf("\n");
		
		// *****************************************************************	
		// Examples of declarations for primitive type double
		//    (for real numbers, AKA floating point numbers)
		double taxRate = 0.062;
		double pi = Math.PI;     // Some math constants are available in the Math class.
		double e = Math.E;
		
		// Examples of printing floating point numbers (format code %f)
		// Use %5.1f to get a field of width 5 with 1 decimal place.
		System.out.printf("The social security tax rate is %5.3f currently.\n", taxRate);
		System.out.printf("The value of pi is %f.\n", pi);
		System.out.printf("The value of e is %7.5f to 5 decimal places.\n", e);
		System.out.printf("\n");
		
		// *****************************************************************	
		// Examples of declarations for primitive type boolean
		boolean debug = true;
		boolean isDone = false;
		
		// Examples of printing boolean values (format code %b)
		System.out.printf("The current debugging state is %b.\n", debug);
		System.out.printf("The isDone flag is set to %b.\n", isDone);
	}
	
}
