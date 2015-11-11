package unitGUI.acmExamples.swingComponents;

/*
 * File: DrawStarMap_FillCheckBox.java
 * -----------------------------------
 * This program creates a five-pointed star every time the
 * user clicks the mouse on the canvas.  This version includes
 * a checkbox to indicate whether the stars should be filled.
 */

import acm.program.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class DrawStarMap_FillCheckBox extends GraphicsProgram {

/* Initialize the graphical user interface */
	public void init() {
		add(new JButton("Clear"), SOUTH);
		fillCheckBox = new JCheckBox("Filled");
		fillCheckBox.setSelected(true);
		add(fillCheckBox, SOUTH);
		addActionListeners();
		addMouseListeners();
	}

/* Called whenever the user clicks the mouse */
	public void mouseClicked(MouseEvent e) {
		GStar star = new GStar(STAR_SIZE);
		star.setFilled(fillCheckBox.isSelected());
		add(star, e.getX(), e.getY());
	}

/* Called whenever an action event occurs */
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Clear"))
			removeAll();

	}

/* Private constants */
	private static final double STAR_SIZE = 20;

/* Private instance variables */
	private JCheckBox fillCheckBox;

}
