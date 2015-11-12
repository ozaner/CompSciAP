package unit4.acmExamples.objects;

import java.awt.Color;
import acm.graphics.*;
import acm.program.*; 

/**
 * The TargetLogoApp1 class demonstrates a simple ACM Library GraphicsProgram
 * application/applet.
 * 
 * @author Dr. Mark Jones
 */
@SuppressWarnings("serial")
public class TargetLogoApp1 extends GraphicsProgram {
	
	private static final double LOGO_PERCENTAGE = 0.8;
	
	/**
	 * Makes a Target logo.
	 * @param args  none expected
	 */
	public static void main(String[] args) {
		(new TargetLogoApp1()).start(args);
	}

	/** Creates the graphics for a Target logo. */
	public void init() {
		double size = LOGO_PERCENTAGE * Math.min(getWidth(), getHeight());
		double xc = getWidth() / 2;  // x-coordinate of the center of the canvas
		double yc = getHeight() / 2; // y-coordinate of the center of the canvas
		
		// make large outer red circle
		double outerRedCircleSize = size;
		GOval outerRedCircle = new GOval(outerRedCircleSize, outerRedCircleSize);
		outerRedCircle.setFilled(true);
		outerRedCircle.setFillColor(Color.RED);
		outerRedCircle.setLocation(xc - size / 2, yc - size / 2);
		add(outerRedCircle);
		
		// make inner white circle
		double whiteCircleSize = 2./3.*size;	
		GOval whiteCircle = new GOval(whiteCircleSize, whiteCircleSize);
		whiteCircle.setFilled(true);
		whiteCircle.setFillColor(Color.WHITE);
		whiteCircle.setLocation(xc - whiteCircleSize / 2, yc - whiteCircleSize / 2);
		add(whiteCircle);
		
		// make small inner red circle
		double innerRedCircleSize = 1./3.*size;		
		GOval innerRedCircle = new GOval(innerRedCircleSize, innerRedCircleSize);
		innerRedCircle.setFilled(true);
		innerRedCircle.setFillColor(Color.RED);
		innerRedCircle.setLocation(xc - innerRedCircleSize / 2, yc - innerRedCircleSize / 2);
		add(innerRedCircle);
	}
} 
