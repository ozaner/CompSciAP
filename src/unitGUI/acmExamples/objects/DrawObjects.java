package unitGUI.acmExamples.objects;

/*
 * File: DrawObjects.java
 * ----------------------
 * This program displays two objects -- a red rectangle and
 * a green oval.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

/** This class displays a mouse-draggable rectangle and oval */
@SuppressWarnings("serial")
public class DrawObjects extends GraphicsProgram {
	
	public static void main(String[] args) {
		(new DrawObjects()).start(args);
	}

/* Initializes the program */
	public void init() {
		GRect rect = new GRect(100, 100, 150, 100);
		rect.setFilled(true);
		rect.setColor(Color.RED);
		add(rect);
		GOval oval = new GOval(300, 115, 100, 70);
		oval.setFilled(true);
		oval.setColor(Color.GREEN);
		add(oval);
	}
}