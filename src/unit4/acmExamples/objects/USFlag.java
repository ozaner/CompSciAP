package unit4.acmExamples.objects;

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

/**
 * ACM GraphicsProgram to draw a US flag.  It uses a subsidiary
 * GStar class to simplify the drawing.
 */
@SuppressWarnings("serial")
public class USFlag extends GraphicsProgram {
	
	private static final double FIELD_FRACTION = 0.40;
	private static final double STAR_FRACTION = 0.40;

	/**
	 * Makes a US flag.
	 * @param args   none expected
	 */
	public static void main(String[] args) {
		(new USFlag()).start(args);
	}

	/** Creates the flag graphics. */
	public void init() {
		addStripes(getWidth(), getHeight());
		addField(FIELD_FRACTION * getWidth(), getHeight() * 7.0 / 13);
	}

	/** Adds the stripes. */
	private void addStripes(double width, double height) {
		double sHeight = height / 13;
		for (int i = 12; i >= 0; i--) {
			GRect stripe = new GRect(0, i * sHeight, width, sHeight);
			stripe.setFilled(true);
			stripe.setColor((i % 2 == 0) ? Color.RED : Color.WHITE);
			add(stripe);
		}
	}

	/** Adds the star field. */
	private void addField(double width, double height) {
		GRect field = new GRect(0, 0, width, height);
		field.setColor(Color.BLUE);
		field.setFilled(true);
		add(field);
		double dx = width / 6;
		double dy = height / 5;
		double starSize = STAR_FRACTION * Math.min(dx, dy);
		for (int row = 0; row < 5; row++) {
			double y = (row + 0.5) * dy;
			for (int col = 0; col < 6; col++) {
				double x = (col + 0.5) * dx;
				GStar star = new GStar(starSize);
				star.setColor(Color.WHITE);
				star.setFilled(true);
				star.setLocation(x, y);
				add(star);
			}
		}
		for (int row = 0; row < 4; row++) {
			double y = (row + 1) * dy;
			for (int col = 0; col < 5; col++) {
				double x = (col + 1) * dx;
				GStar star = new GStar(starSize);
				star.setColor(Color.WHITE);
				star.setFilled(true);
				star.setLocation(x, y);
				add(star);
			}
		}
	}
}
