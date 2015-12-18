package unit5.examples.abstracts;
/**
 * Demonstrates a class that extends an abstract class.
 *  @author Dr. Mark Jones
 *  @version 2014-12-11
 */

public class Rectangle extends Shape {

	private double length;
	private double width;
	
    /** 
     * Creates a rectangle of a given length and width.
     *  (Precondition: parameters are positive.)
     *  @param length   length of rectangle
     *  @param width    width of rectangle
     **/
	public Rectangle(double length, double width) {
		setLength(length);
		setWidth(width);
	}
	
	/** 
	 * Get the rectangle's length.
	 *  @return  length of this rectangle
	 **/
	public double getLength () {
		return length;
	}

	/** 
	 * Set the rectangle's length.
	 *  @param length   new length to be set
	 *  @return         new length of this rectangle
     **/
	public double setLength(double length) {
		this.length = length;	
		return length;
	}

	/** 
	 * Get the rectangle's width.
	 *  @return  width of this rectangle
     **/
	public double getWidth () {
		return width;
	}
	
	/** 
	 * Set the rectangle's width.
	 *  @param width   new width to be set
	 *  @return        new width of this rectangle
     **/
	public double setWidth(double width) {
		this.width = width;	
		return width;
	}
	
	/** 
	 * Describes a rectangle.
	 *  @return     the description
     **/
	public String toString() {
		return "Rectangle[length = " + getLength() +
		               ", width = " + getWidth() + "]";
	}
	
	/** 
	 * Computes the area of a rectangle.
	 *  @return     the area
     **/	
	public double getArea() {
		return getLength()*getWidth();
	}
}
