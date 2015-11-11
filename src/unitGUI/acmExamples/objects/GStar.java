package unitGUI.acmExamples.objects;

import acm.graphics.*;

/**
 * Illustrates subclassing of GPolygon by making a five-pointed star.
 * 
 * @author Dr. Mark Jones (and Eric Roberts)
 */
@SuppressWarnings("serial")
public class GStar extends GPolygon {
	
	/**
	 * Makes a GStar.
	 * 
	 *       edge      / delta
	 *  --------------/ . . . . 
	 *   \ .18     dx  .       |  
	 *    \     .        .     |
	 *     \         .     . 36| dy
	 *      \             .  . |
	 *       \                 +
	 * @param width
	 */
	public GStar(double width) {
		// dx = horizontal distance from center of star to left point
		double dx = width / 2;
		// dy = vertical distance from center of star to left point
		double dy = dx * GMath.tanDegrees(18);
		// delta = horizontal distance from center to nearest vertex
		double delta = dy * GMath.tanDegrees(36);
		// edge = length of a star edge
		double edge = dx - delta;
		
		addVertex(-dx, -dy);
		int angle = 0;
		for (int i = 0; i < 5; i++) {
			addPolarEdge(edge, angle);
			addPolarEdge(edge, angle + 72);
			angle -= 72;
		}
		markAsComplete();
	}

	public GStar(double x, double y, double size) {
		this(size);
		setLocation(x, y);
	}

}