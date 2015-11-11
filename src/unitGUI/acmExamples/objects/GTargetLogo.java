package unitGUI.acmExamples.objects;

import java.awt.Color;
import acm.graphics.*;

/**
 * The GTargetLogo class demonstrates a GCompound subclass.
 * The origin of the GCompound is in the center of the logo.
 * 
 * @author Dr. Mark Jones
 */

@SuppressWarnings("serial")
public class GTargetLogo extends GCompound {

	/**
	 * Makes a target logo object that behaves similar to a GObject.
	 *  @param size		size of the logo
	 */
	public GTargetLogo(double size) {
		double outerRedCircleSize = size;
		GOval outerRedCircle = new GOval(outerRedCircleSize, outerRedCircleSize);
		outerRedCircle.setFilled(true);
		outerRedCircle.setFillColor(Color.RED);
		// GCompound coordinates, not GCanvas coordinates
		outerRedCircle.setLocation(-outerRedCircleSize / 2, -outerRedCircleSize / 2);  
		add(outerRedCircle);  // add to the GCompound, not the GCanvas
		
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
	
	public GTargetLogo(double x, double y, double size) {
		this(size);
		setLocation(x, y);  // GCanvas coordinates for the entire GCompound
	}
} 
