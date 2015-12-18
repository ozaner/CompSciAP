package unit5.examples.interfaces;
/**
 * Demonstrates a class that implements an interface.
 *  @author Dr. Mark Jones
 *  @version 2014-12-11
 */
public class Circle implements Shape {
	
	private double radius;
	
	/**
	 * Creates a circle with a given radius.
	 * @param radius    the radius
	 */
	public Circle (double radius) {
		setRadius(radius);
	}

	/** 
	 * Returns a string description of a circle.
     **/
	public String toString() {
		return "Circle[radius = " + radius + "]";
	}
	
	/** 
	 * Get the circles's radius.
	 *  @return  radius of this circle
     **/
	public double getRadius () {
		return radius;
	}
	
	/** 
	 * Set the circle's radius.
	 *  @param radius   new radius to be set
	 *  @return         new radius of this circle
     **/
	public double setRadius(double radius) {
		this.radius = radius;	
		return radius;
	}

	/** 
	 * Computes the area of a circle.
     */	
	public double getArea() {
		return Math.PI * radius * radius;
	}
}
