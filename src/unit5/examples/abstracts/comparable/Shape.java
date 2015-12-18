package unit5.examples.abstracts.comparable;
/**
 * Example of an abstract class.
 *  @author Dr. Mark Jones
 *  @version 2014-12-11
 */
public abstract class Shape implements Comparable<Shape> {

	public abstract double getArea();
	
	/**
	 * Compare two doubles for equality by checking if |x - y| <= epsilon * max(|x|, |y|).
	 * This technique takes into account the relative difference between x and y and works 
	 * better for large numbers than the simple test on pg. 197 of Horstmann:  |x - y| <= epsilon
	 * @param x  the first double to test
	 * @param y  the second double to test
	 * @return    true if the numbers are "equal", false otherwise
	 */
	private static boolean floatingPointEquals(double x, double y) {
		final double EPSILON = 1E-15;  // double precision is about 1E-16
		return (Math.abs(x - y) <= EPSILON * Math.max(Math.abs(x), Math.abs(y)));
	}
	
	/** 
	 * Determine which Shape object is larger (by area).
	 *  (Required method for the Comparable interface.)
	 * @param s    the shape to compare against this object
	 */
	public int compareTo(Shape s) {
		if (s == null)
			throw new NullPointerException("Expecting a Shape");
		if (Shape.floatingPointEquals(getArea(), s.getArea()))
			return 0;
		else if (getArea() < s.getArea())
			return -1;
		else
			return 1;
	}
	
	/** 
	 * Determine whether a Shape object is equal in area to this.
	 *  (Required method for the Comparable interface.)
	 * @param s    the shape to compare against this object
	 */
	public boolean equals(Shape s) {
		if (s == null)
			throw new NullPointerException("Expecting a Shape");
		return compareTo(s) == 0;
	}
}

