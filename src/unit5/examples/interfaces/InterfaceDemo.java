package unit5.examples.interfaces;

import java.util.Random;

/**
 * Demonstrates Java polymorphism with interfaces.
 *  @author Dr. Mark Jones
 *  @version 2014-12-11
 */
public class InterfaceDemo {
	/**
	 * Demonstrates Java polymorphism with interfaces.
	 *   @param args	No command-line arguments are expected.
	 */
	public static void main(String[] args) {
		Circle cir = new Circle(5.1);
		Square sqr = new Square(2.9);
		Rectangle rectsqr = new Square(3.2);  // a Square is also a Rectangle
		Rectangle rect = new Rectangle(3, 4);
		System.out.printf("%s, %s, %s, %s\n\n", cir, sqr, rectsqr, rect);
		
		// we have to downcast rectsqr to do a "square-only" operation 
		// System.out.printf("%s%.1f\n\n", "The " + rectsqr + " has side length ", rectsqr.getSide());
		System.out.printf("%s%.1f\n\n", "The " + rectsqr + " has side length ", ((Square)rectsqr).getSide());
		
		// polymorphism -- the correct getArea() method is called at runtime depending on the type of the object
		System.out.printf("The %s has area %.2f\n", cir, cir.getArea());
		System.out.printf("The %s has area %.2f\n\n", rectsqr, rectsqr.getArea());
		
		Random rand = new Random();     // random number generator used to pick a shape
		Shape s;   // s is a Shape which can either be a Circle or a Rectangle
		
		// the proper getArea() is determined at run-time ("dynamic binding" or "late binding")
		// the compiler can't possibly know what kind of Shape s will be
		System.out.printf("%s%.2f\n", "We randomly chose the " + (s = (rand.nextBoolean() ? cir : rectsqr)) 
				+ " with area ", s.getArea());
		System.out.printf("%s%.2f\n", "We randomly chose the " + (s = (rand.nextBoolean() ? cir : rectsqr)) 
				+ " with area ", s.getArea());
		System.out.printf("%s%.2f\n", "We randomly chose the " + (s = (rand.nextBoolean() ? cir : rectsqr)) 
				+ " with area ", s.getArea());
	}
}
