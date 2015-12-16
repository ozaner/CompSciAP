package unit5.examples.abstracts.comparable;
/**
 * Demonstrates a class that extends a parent class.
 *  @author Dr. Mark Jones
 *  @version 2014-12-11
 */

public class Square extends Rectangle {
	
	public Square (double side) {
		super(side, side);
	}
	
	/** Returns a string description of a square.
     **/
	public String toString() {
		return "Square[side = " + getLength() + "]";
	}
	
	/**
	 * Gets the side length of the square.
	 * @return  the side length of this square
	 */
	public double getSide() {
		return getLength();
	}
	
	/**
	 * Sets the side length of the square.
	 * @param side  the side length
	 * @return      the side length of this square
	 */
	public double setSide(double side) {
		super.setLength(side);
		return super.setWidth(side);
	}
	
	/**
	 * Sets the length of the square, while ensuring that the length and width are the same.
	 * @param side  the side length
	 * @return      the side length of this square
	 */
	@Override
	public double setLength(double side) {
		return setSide(side);
	}
	
	/**
	 * Sets the width of the square, while ensuring that the length and width are the same.
	 * @param side  the side length
	 * @return      the side length of this square
	 */
	@Override
	public double setWidth(double side) {
		return setSide(side);
	}
}
