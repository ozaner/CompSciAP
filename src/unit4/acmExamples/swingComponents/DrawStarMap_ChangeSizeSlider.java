package unit4.acmExamples.swingComponents;

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
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

@SuppressWarnings("serial")
public class DrawStarMap_ChangeSizeSlider extends GraphicsProgram {

/* Initialize the graphical user interface */
	public void init() {
		add(new JButton("Clear"), SOUTH);
		sizeSlider = new JSlider(MIN_SIZE, MAX_SIZE, DEFAULT_SIZE);
		add(new JLabel("  Small"), SOUTH);
		add(sizeSlider, SOUTH);
		add(new JLabel("Large"), SOUTH);
		
		ChangeListener changeListener = new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
		        JSlider source = (JSlider)e.getSource();
		        if (!source.getValueIsAdjusting()) {
		            double newSize = source.getValue();
		            double scale = newSize / currentSize;
		            for (int i = 0; i < getElementCount(); i++) {
		            	GStar star = (GStar)getElement(i);
		            	star.scale(scale);
		            }
		            currentSize = newSize;
		        }
			}
		};

		sizeSlider.addChangeListener(changeListener);
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
	private double currentSize = DEFAULT_SIZE;

}
