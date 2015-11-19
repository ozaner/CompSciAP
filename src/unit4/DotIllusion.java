package unit4;

import java.awt.Font;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import acm.graphics.GLabel;
import acm.graphics.GObject;
import acm.graphics.GScalable;
import acm.program.GraphicsProgram;

/**
 * Optical illusion from cognitive psychology.
 * The idea and original code by Caroline Viscovich.<br>
 * 
 * AP Computer Science - Pd. 7<br>
 * November 23rd, 2015<br>
 * @author markjones
 * @author Ozaner Hansha
 */
@SuppressWarnings("serial")
public class DotIllusion extends GraphicsProgram 
{ 
	/**
	 * The Width of the window.
	 */
	private static final int DEFAULT_WIDTH = 900;
	
	/**
	 * The Height of the window. (Same as width)
	 */
	private static final int DEFAULT_HEIGHT = DEFAULT_WIDTH;
	
	/**
	 * The character displayed in the center of the screen.
	 */
	private static final char CENTER = 'X';
	
	private static final Font CENTER_FONT = new Font("Ariel", 1, 25);

	/**
	 * Temporary variables for window resizing.
	 */
	private double wid, ht;

	/** 
	 * Main method to run the dot illusion as an application.
	 * @param args  no args expected
	 */
	public static void main(String[] args) { 
		(new DotIllusion()).start(args); 
	} 

	/**
	 * Initializes the canvas with an X in the center and a ring of surrounding dots.
	 */
	public void init() {
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		layoutX();
		layoutDots();
		catchResizeEvents();
 	} 
	
	/**
	 * Lays out the 'X' in the center of the canvas.
	 */
	private void layoutX() {
		GLabel middle = new GLabel(Character.toString(CENTER));
		middle.setFont(CENTER_FONT);
		add(middle, DEFAULT_WIDTH/2, (DEFAULT_HEIGHT + middle.getAscent())/2);
	}
	
	/**
	 * Lays out the dots (circles) in a ring starting at the top, centered on the canvas.
	 */
	private void layoutDots() {
		
	}
	
	/**
	 * Sets up the handler for resize events.  This handler catches resize
	 * events, rescales the (GScalable) objects, and adjusts the locations
	 * of all GObjects. It does not adjust the font size for GLabels and such.
	 */
	private void catchResizeEvents() {
		wid = getWidth();
		ht = getHeight();
		
		addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent e) {
				double scaleX = getWidth() / wid,  scaleY = getHeight() / ht;
				for (int i = 0; i < getElementCount(); i++) {
					Object obj = getElement(i);
					if (obj instanceof GObject) {
						if (obj instanceof GScalable) {
							((GScalable) obj).scale(scaleX, scaleY);
						}
						((GObject) obj).setLocation(((GObject) obj).getX()*scaleX, ((GObject) obj).getY()*scaleY);
					}
				}
				wid = getWidth(); ht = getHeight();
			}
		}); 		
	}

	/**
	 * Animation technique to make each dot in turn disappear briefly.
	 */
	public void run() { 
		// complete this method
	}
}
