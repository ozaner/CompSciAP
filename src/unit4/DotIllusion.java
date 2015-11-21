package unit4;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import acm.graphics.GLabel;
import acm.graphics.GObject;
import acm.graphics.GOval;
import acm.graphics.GPoint;
import acm.graphics.GScalable;
import acm.program.GraphicsProgram;

/**
 * Optical illusion from cognitive psychology.
 * The idea and original code by Caroline Viscovich.<br>
 * 
 * AP Computer Science - Pd. 7<br>
 * November 23rd, 2015<br>
 * Dr. Jones<br>
 * @author Ozaner Hansha
 */
@SuppressWarnings("serial")
public class DotIllusion extends GraphicsProgram 
{ 
	/**
	 * The Width of the window.
	 */
	private static final int DEFAULT_WIDTH = 800;
	
	/**
	 * The Height of the window. (Same as width)
	 */
	private static final int DEFAULT_HEIGHT = DEFAULT_WIDTH;
	
	/**
	 * The Center point of the illusion. Set by the {@link #layoutX()} method.
	 * @see #layoutX()
	 */
	private static final GPoint CENTER_POINT = new GPoint(DEFAULT_WIDTH/2, DEFAULT_HEIGHT/2);
	
	/**
	 * The String displayed in the center of the screen.
	 */
	private static final String CENTER_STRING = "X";
	
	/**
	 * The font of the String displayed in the center.
	 */
	private static final Font CENTER_FONT = new Font("Ariel", 1, 36);
	
	/**
	 * The amount of dots to be displayed equidistant to the center of the window.
	 */
	private static final int AMOUNT_OF_DOTS = 12;
	
	/**
	 * The array of {@link GOval}s used in the application.
	 * Defined by the {@link #AMOUNT_OF_DOTS}.
	 * @see #AMOUNT_OF_DOTS
	 */
	private static final GOval[] DOTS = new GOval[AMOUNT_OF_DOTS];
	
	/**
	 * The radius of all the {@link #DOTS}.
	 */
	private static final int DOT_RADIUS = 30;
	
	/**
	 * The color of the dots.
	 */
	private static final Color DOT_COLOR = Color.MAGENTA;
	
	/**
	 * The distance between the center of the window and a given dot.
	 */
	private static final int ILLUSION_RADIUS = 150;
	
	/**
	 * The amount of of time, in milliseconds, that the program pauses between frames.
	 * @see #pause(double)
	 */
	private static final long PAUSE_TIME = 50;

	/**
	 * Temporary variables for window resizing event handling.
	 * @see #catchResizeEvents()
	 */
	private double wid, ht;

	/** 
	 * Main method to run the dot illusion as an application.
	 * @param args  no args expected
	 */
	public static void main(String[] args)
	{ 
		new DotIllusion().start(args); 
	} 

	/**
	 * Initializes the canvas with an X in the center and a ring of surrounding dots.
	 */
	public void init()
	{
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		layoutX();
		createDots();
		layoutDots();
		catchResizeEvents();
 	} 
	
	/**
	 * Lays out the 'X' in the center of the canvas.
	 */
	private void layoutX()
	{
		GLabel label = new GLabel(CENTER_STRING);
		label.setFont(CENTER_FONT);
		add(label, CENTER_POINT.getX() - label.getWidth()/2,
				CENTER_POINT.getY() + label.getHeight()/2 - label.getAscent()/2);
	}
	
	/**
	 * Creates the {@link GOval} objects and places them in {@link #DOTS}.
	 */
	private void createDots()
	{
		for(int x = 0; x < AMOUNT_OF_DOTS; x++)
		{
			GOval dot = new GOval(DOT_RADIUS*2,DOT_RADIUS*2);
			dot.setFilled(true);
			dot.setColor(DOT_COLOR);
			DOTS[x] = dot;
		}
	}
	
	/**
	 * Lays out the {@link #DOTS} in a ring starting at the top, centered on the canvas.
	 */
	private void layoutDots()
	{
		for(int x = 0; x < AMOUNT_OF_DOTS; x++)
		{
			double angle = 2*Math.PI/AMOUNT_OF_DOTS;
			add(DOTS[x], (CENTER_POINT.getX() + ILLUSION_RADIUS * Math.cos(angle*x)) - DOT_RADIUS,
						(CENTER_POINT.getY() + ILLUSION_RADIUS * Math.sin(angle*x))  - DOT_RADIUS);
		}
	}
	
	/**
	 * Sets up the handler for resize events.  This handler catches resize
	 * events, rescales the (GScalable) objects, and adjusts the locations
	 * of all GObjects. It does not adjust the font size for GLabels and such.
	 * @author Mark Jones
	 */
	private void catchResizeEvents()
	{
		wid = getWidth();
		ht = getHeight();
		
		addComponentListener(new ComponentAdapter()
		{
			public void componentResized(ComponentEvent e)
			{
				double scaleX = getWidth() / wid,  scaleY = getHeight() / ht;
				for (int i = 0; i < getElementCount(); i++)
				{
					Object obj = getElement(i);
					if (obj instanceof GObject)
					{
						if (obj instanceof GScalable)
							((GScalable) obj).scale(scaleX, scaleY);
						((GObject) obj).setLocation(((GObject) obj).getX()*scaleX, ((GObject) obj).getY()*scaleY);
					}
				}
				wid = getWidth(); ht = getHeight();
			}
		}); 		
	}

	/**
	 * Animation technique to make each dot in turn disappear briefly.<br>
	 * This method is the game loop.
	 */
	public void run()
	{ 
		while(true)
			for(GOval c: DOTS)
			{
				c.setVisible(false);
				pause(PAUSE_TIME);
				c.setVisible(true);
			}
	}
}
