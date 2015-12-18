package unit5.examples.abstracts.comparable;
/**
 * Demonstrates Java polymorphism with an abstract classes that implement the Comparable interface.
 *  @author Dr. Mark Jones
 *  @version 2014-12-11
 */
public class AbstractClassComparableDemo {
	/**
	 * Demonstrates Java polymorphism with abstract classes.
	 *   @param args	No command-line arguments are expected.
	 */
	public static void main(String[] args) {
		Circle c = new Circle(5.1);
		Rectangle r = new Square(3.2);  // a Square is also a Rectangle
		System.out.printf("%s, %s\n\n", c, r);

		if (c.equals(r)) {
			System.out.printf("%s\n", "The shapes " + c + " and " + r +
					" have (approximately) the same area.");				
		} else {
			Shape largest = r;
			if (c.compareTo(r) > 0) 
				largest = c;
			System.out.printf("%s\n", "The largest shape (by area) is the " + largest);
		}
	}
}
