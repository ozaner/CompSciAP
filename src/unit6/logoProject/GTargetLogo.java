package unit6.logoProject;
import java.awt.Color;
import acm.graphics.*;

/**
 * Makes the Target logo.
 * The origin of the GCompound is in the center of the logo.
 * 
 * @author Dr. Mark Jones
 */

@SuppressWarnings("serial")
public class GTargetLogo extends GCompound {

	private double size;
	
	private GOval outerCircle, middleCircle, innerCircle;
	
	/**
	 * Makes a target logo object of a given size.
	 *  @param size		size of the logo
	 */
	public GTargetLogo(double size) {
		this.size = size;
		
		double outerRedCircleSize = size;
		outerCircle = new GOval(outerRedCircleSize, outerRedCircleSize);
		outerCircle.setFilled(true);
		outerCircle.setFillColor(Color.RED);
		outerCircle.setLocation(-outerRedCircleSize / 2, -outerRedCircleSize / 2);  
		add(outerCircle);
		
		// make inner white circle
		double whiteCircleSize = 2./3.*size;	
		middleCircle = new GOval(whiteCircleSize, whiteCircleSize);
		middleCircle.setFilled(true);
		middleCircle.setFillColor(Color.WHITE);
		middleCircle.setLocation(-whiteCircleSize / 2, -whiteCircleSize / 2);
		add(middleCircle);
		
		// make small inner red circle
		double innerRedCircleSize = 1./3.*size;		
		innerCircle = new GOval(innerRedCircleSize, innerRedCircleSize);
		innerCircle.setFilled(true);
		innerCircle.setFillColor(Color.RED);
		innerCircle.setLocation(-innerRedCircleSize / 2, -innerRedCircleSize / 2);
		add(innerCircle);
	}
	
	/**
	 * Makes a target logo object at a given canvas location of a given size.
	 * @param x			x-coordinate of the logo
	 * @param y			y-coordinate of the logo
	 * @param size		size of the logo
	 */
	public GTargetLogo(double x, double y, double size) {
		this(size);
		setLocation(x, y);
	}
	
	/**
	 * Sets the color of the logo's inner and outer circles.
	 */
	public void setColor(Color c)
	{
		outerCircle.setFillColor(c);
		innerCircle.setFillColor(c);
	}
	
	public String toString() {
		String s = super.toString();
		return s.substring(0, s.length()-1) + ", size=" + size +"]";
	}
} 
