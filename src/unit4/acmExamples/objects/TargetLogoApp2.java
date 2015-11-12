package unit4.acmExamples.objects;

import acm.program.*; 

/**
 * The TargetLogoApp2 class demonstrates a GCompound object.
 * 
 * @author Dr. Mark Jones
 */
@SuppressWarnings("serial")
public class TargetLogoApp2 extends GraphicsProgram {
	
	private static final double LOGO_PERCENTAGE = 0.8;
	
	/**
	 * Makes a Target logo.
	 * @param args  none expected
	 */
	public static void main(String[] args) {
		(new TargetLogoApp2()).start(args);
	}

	/** Makes a Target logo. */
	public void init() {
		double size = LOGO_PERCENTAGE * Math.min(getWidth(), getHeight());
		double xc = getWidth() / 2;  // x-coordinate of the center of the canvas
		double yc = getHeight() / 2; // y-coordinate of the center of the canvas

		GTargetLogo logo = new GTargetLogo(size);
		add(logo, xc, yc);
	}
} 
