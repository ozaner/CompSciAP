package unit1.OtherExamples;

public class ArithmeticTester {

	public static void main(String[] args) {
		
		// Declare two variables 'a' and 'b' of type int and assign them the values 10 and -1.
		int a = 10, b = -1;
		
		// Declare a variable 'aveInt' of type int which is the average of 'a' and 'b'
		// and print a statement that demonstrates the computation.  Is it the value you expected?
		int aveInt = (int)((a + b) / 2.0 + .5);
		System.out.printf("Average of %d and %d is %d \n", a, b, aveInt);
		
		// Declare a variable 'aveDouble' of type double which is the average of 'a' and 'b'
		// and print a statement that demonstrates the computation.  Is it the value you expected?
		double aveDouble = (a + b) / 2.0;
		System.out.printf("Average of %d and %d is %3.1f \n", a, b, aveDouble);

		// Uncomment the following line and run it again.  What runtime error did you get?
//		int bad = 4 / 0; <--- Divide by Zero (ArethmeticException)
		
		//Re-comment the offending line above.  Explain in a comment what the following code does.
		try {
			@SuppressWarnings("unused")
			int bad = 4 / 0;
		} catch (ArithmeticException e) {
			System.out.println("You can't divide by zero.");
		}

		// Java's primitive integer types store their values in finite numbers of bits 
		// (such as 32 bits for an int). When an integer arithmetic operation (such as 
		// addition with integer operands) generates a result that cannot fit into these bits, 
		// the operation is said to overflow (exceed the largest positive value that can be 
		// stored) or underflow (exceed the smallest negative value that can be stored).
		//
		// Oddly, Java's integer arithmetic operators don't detect overflows or underflows. Unless an 
		// application has been designed to detect these conditions, problems could occur. 
		// [In the new Java 8, methods were added (addExact, multipyExact, etc.) to the 
		// java.lang.Math class to detect overflow/underflow and generate an ArithmeticException.] 
		//
		// The following code intentionally creates an integer overflow.  How would you detect
		// when overflow occurs?
		
		int product = 10;
		while (product > 0) {
			System.out.println(product);
			product *= 1000000;
		}
		System.out.println(product);
		
		//Using addExact, subtractExact.... to generate ArthmeticExceptions.
		int product2 = 10;
		while (product2 > 0) {
			System.out.println(product2);
			try {
				product2 = Math.multiplyExact(product2, 1000000);
			}
			catch (ArithmeticException e){
				System.out.println("Integer Overflow");
				System.exit(0);
			}
		}
		System.out.println(product2);
		
		
		
	}
}
