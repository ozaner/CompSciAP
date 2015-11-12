package unit4.acmExamples.swingComponents;

/*
 * File: DrawStarMap.java
 * ----------------------
 * This program creates a five-pointed star every time the
 * user clicks the mouse on the canvas.
 */

import acm.program.*;

import java.awt.event.*;

@SuppressWarnings("serial")
public class DrawStarMap extends GraphicsProgram {

/* Initialize the mouse listeners */
	public void init() {
		addMouseListeners();
	}

/* Called whenever the user clicks the mouse */
	public void mouseClicked(MouseEvent e) {
		GStar star = new GStar(STAR_SIZE);
		star.setFilled(true);
		add(star, e.getX(), e.getY());
	}

/* Private constants */
	private static final double STAR_SIZE = 20;

}
