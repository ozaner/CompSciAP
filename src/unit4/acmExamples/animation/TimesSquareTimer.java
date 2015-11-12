package unit4.acmExamples.animation;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import acm.program.*;
import acm.graphics.*;

/**
 * This program displays the text of the string HEADLINE on the
 * screen in an animated way that moves it across the display
 * from right to left.
 * 
 * Uses a local, final variable for the label.
 */
@SuppressWarnings("serial")
public class TimesSquareTimer extends GraphicsProgram {
	
	/* Set the program dimensions. */
	private static final int APPLICATION_WIDTH = 700;
	private static final int APPLICATION_HEIGHT = 400;

	/* The number of pixels to shift the label on each cycle */
	private static final int DELTA_X = -2;

	/* The number of milliseconds to pause on each cycle */
	private static final int PAUSE_TIME = 20;

	/* The string to use as the value of the label */
	private static final String HEADLINE =
			"The Giants win the pennant!  ";

	/**
	 * Initialize window and the banner label just off the right hand side of the window.
	 * Use a Timer to update the label.
	 */
	public void init() {
		setSize(APPLICATION_WIDTH, APPLICATION_HEIGHT);
		final GLabel label = new GLabel(HEADLINE);
		label.setFont("Serif-72");
		add(label, getWidth(), (getHeight() + label.getAscent()) / 2);
		
		/** Move the banner steadily to the left until it vanishes off the left side. */
		ActionListener mover = new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (label.getX() + label.getWidth() > 0)
					label.move(DELTA_X, 0);
			}
		};
		
		Timer t = new Timer(PAUSE_TIME, mover);
		t.start();
	}
} 
