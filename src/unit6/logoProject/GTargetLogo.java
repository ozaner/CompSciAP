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
	/**
	 * Makes a target logo object of a given size.
	 *  @param size		size of the logo
	 */
	public GTargetLogo(double size) {
		this.size = size;
		
		double outerRedCircleSize = size;
		GOval outerRedCircle = new GOval(outerRedCircleSize, outerRedCircleSize);
		outerRedCircle.setFilled(true);
		outerRedCircle.setFillColor(Color.RED);
		outerRedCircle.setLocation(-outerRedCircleSize / 2, -outerRedCircleSize / 2);  
		add(outerRedCircle);
		
		// make inner white circle
		double whiteCircleSize = 2./3.*size;	
		GOval whiteCircle = new GOval(whiteCircleSize, whiteCircleSize);
		whiteCircle.setFilled(true);
		whiteCircle.setFillColor(Color.WHITE);
		whiteCircle.setLocation(-whiteCircleSize / 2, -whiteCircleSize / 2);
		add(whiteCircle);
		
		// make small inner red circle
		double innerRedCircleSize = 1./3.*size;		
		GOval innerRedCircle = new GOval(innerRedCircleSize, innerRedCircleSize);
		innerRedCircle.setFilled(true);
		innerRedCircle.setFillColor(Color.RED);
		innerRedCircle.setLocation(-innerRedCircleSize / 2, -innerRedCircleSize / 2);
		add(innerRedCircle);
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
	
	public String toString() {
		String s = super.toString();
		return s.substring(0, s.length()-1) + ", size=" + size +"]";
	}
} 
