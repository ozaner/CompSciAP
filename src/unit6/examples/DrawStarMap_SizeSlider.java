package unit6.examples;

/*
 * File: DrawStarMap_SizeSlider.java
 * -----------------------------------
 * This program creates a five-pointed star every time the
 * user clicks the mouse on the canvas.  This version adds
 * a JSlider to specify the size of the star.
 */

import acm.program.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class DrawStarMap_SizeSlider extends GraphicsProgram {

/* Initialize the graphical user interface */
	public void init() {
		add(new JButton("Clear"), SOUTH);
		sizeSlider = new JSlider(MIN_SIZE, MAX_SIZE, DEFAULT_SIZE);
		add(new JLabel("  Small"), SOUTH);
		add(sizeSlider, SOUTH);
		add(new JLabel("Large"), SOUTH);
		addMouseListeners();
		addActionListeners();
	}

/* Called whenever the user clicks the mouse */
	public void mouseClicked(MouseEvent e) {
		GStar star = new GStar(getCurrentSize());
		star.setFilled(true);
		add(star, e.getX(), e.getY());
	}

/* Called whenever an action event occurs */
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Clear")) {
			removeAll();
		}
	}

/* Returns the current size */
	public double getCurrentSize() {
		return sizeSlider.getValue();
	}

/* Private constants */
	private static final int MIN_SIZE = 1;
	private static final int MAX_SIZE = 50;
	private static final int DEFAULT_SIZE = 16;

/* Private instance variables */
	private JSlider sizeSlider;

}
