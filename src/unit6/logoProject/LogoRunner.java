package unit6.logoProject;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;

import acm.program.*; 

/**
 * Demonstrates animation and Swing interactors such as JButtons, JSliders 
 * and JRadioButtons.
 * 
 * @author Dr. Mark Jones
 */

@SuppressWarnings("serial")
public class LogoRunner extends GraphicsProgram {
	
	// application constants
	private static final int INITIAL_WIDTH = 700;
	private static final int INITIAL_HEIGHT = 400;
	private static final long PAUSE_TIME = 10;   /* animation pause time */
	
	// logo sizes as percentages of the screen size
	private static final int MIN_PERCENTAGE = 10;
	private static final int MAX_PERCENTAGE = 90;
	private static final int DEFAULT_PERCENTAGE = 50;
	
	// animation speed factors
	private static final double SLOW_SPEED = 1;
	private static final double MEDIUM_SPEED = 2;
	private static final double FAST_SPEED = 5;
	private static int currentSpeed = 1; //0 = slow, 1 = normal, 2 & up = fast.
	
	// instance variables
	private GTargetLogo logo;  /* the logo object */
	private double dx;         /* velocity delta in the x direction */
	private double dy;         /* velocity delta in the y direction */
	private boolean suspend;   /* whether to suspend the animation */
	
	//Swing objects.
	private static JButton reset = new JButton("Reset");
	private static JSlider size = new JSlider();
	private static JRadioButton[] speeds = {new JRadioButton("Slow"),
									new JRadioButton("Medium"),
									new JRadioButton("Fast")};
	private ButtonGroup speedGroup = new ButtonGroup();
	
	/**
	 * Animates a Target logo.
	 * @param args   none expected
	 */
	public static void main(String[] args) {
		new LogoRunner().start(args);
	}
	
	/** Makes a bouncing Target logo and the GUI to control it. */
	public void init() {
		setSize(INITIAL_WIDTH, INITIAL_HEIGHT);
		
		// add a JButton to reset the animation starting at the center
		// at the current size and speed
		add(reset, SOUTH);
		
		// add a JSlider for the size of the logo
		add(size, SOUTH);
		
		// add radio buttons for the speed of the animation
		// (you control the speed by varying the pause interval in the animation)
		for(JRadioButton b: speeds)
		{
			speedGroup.add(b);
			add(b, SOUTH);
		}
		
		addActionListeners();
		addMouseListeners();

		reset();
	}
	
	/**
	 * A mouse click suspends/resumes the animation.
	 * @param e  a mouse event
	 */
	public void mouseClicked(MouseEvent e) {
		suspend = !suspend;
	}
	
	/**
	 * ActionEvent handler for button presses, etc.
	 */
	public void actionPerformed(ActionEvent e)
	{
		switch(e.getActionCommand())
		{
		case "Reset": reset();
		case "Slow": currentSpeed = 0;
		case "Medium": currentSpeed = 1;
		case "Fast": currentSpeed = 2;
		}
	}
	
	/**
	 * Resets the animation to start at the center at the current size and speed.
	 */
	public void reset() {
		removeAll();
		double size = getCurrentPercentage() * Math.min(getWidth(), getHeight());
		logo = new GTargetLogo(getWidth() / 2., getHeight() / 2., size);
		System.out.println(logo);
		add(logo);
		dx = 2; dy = 1;
		suspend = false;
	}
	
	/** Simple animation method that shifts an object. */
	public void run() {
		while (true) {
			if (!suspend) advanceOneTimeStep();
			pause(PAUSE_TIME / getCurrentSpeed());
		}
	}
	
	/** Check for bounces and advance the ball */
	private void advanceOneTimeStep() {
		double x = logo.getBounds().getX();
		double y = logo.getBounds().getY();
		if (x + dx < 0 || x + logo.getWidth() + dx > getWidth())
			dx = -dx;
		if (y + dy < 0 || y + logo.getHeight() + dy > getHeight())
			dy = -dy;
		logo.move(dx, dy);
	}
	
	/**
	 * Computes the current percentage of the window size to use for the logo size.
	 * @return  the percentage
	 */
	public double getCurrentPercentage() {
		// replace the line below with correct code
		return DEFAULT_PERCENTAGE / 100.;
	}
	
	/**
	 * Computes the current speed factor for the animation.
	 * @return
	 */
	private double getCurrentSpeed() {
		switch(currentSpeed)
		{
		case 0: return SLOW_SPEED;
		case 1: return MEDIUM_SPEED;
		}
		return FAST_SPEED;
	}
} 
