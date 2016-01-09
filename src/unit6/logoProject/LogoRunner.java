package unit6.logoProject;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import acm.program.GraphicsProgram; 

/**
 * Demonstrates animation and Swing interactors such as JButtons, JSliders 
 * and JRadioButtons.<br><br>
 * 
 * Ozaner Hansha<br>
 * AP Computer Science<br>
 * @author Dr. Mark Jones
 * January 11th, 2016
 */

@SuppressWarnings({"serial", "unchecked", "rawtypes"})
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
	
	// instance variables
	private GTargetLogo logo;  /* the logo object */
	private double dx;         /* velocity delta in the x direction */
	private double dy;         /* velocity delta in the y direction */
	private boolean suspend;   /* whether to suspend the animation */
	
	//A map of the name of colors and the actual colors.
	private static HashMap<String, Color> colors = new HashMap<String, Color>();
	static
	{
		colors.put("Red", Color.RED);
		colors.put("Blue", Color.BLUE);
		colors.put("Cyan", Color.CYAN);
		colors.put("Gray", Color.GRAY);
		colors.put("Green", Color.GREEN);
		colors.put("Magenta", Color.MAGENTA);
		colors.put("Yellow", Color.YELLOW);
	}
	
	//Swing objects.
	private JButton reset = new JButton("Reset");
	private JSlider size = new JSlider(MIN_PERCENTAGE,MAX_PERCENTAGE,DEFAULT_PERCENTAGE);
	private JComboBox colorBox = new JComboBox(colors.keySet().toArray());
	private JRadioButton[] speeds = {new JRadioButton("Slow"),
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
			b.addActionListener(this);
		}
		speeds[1].setSelected(true);
		
		//JComboBox for color changing.
		add(colorBox, SOUTH);
		colorBox.addActionListener(this);
		
		addActionListeners();
		addMouseListeners();

		reset();
	}
	
	/**
	 * A mouse click suspends/resumes the animation.
	 * @param e  a mouse event
	 */
	public void mouseClicked(MouseEvent e){
		suspend = !suspend;
	}
	
	/**
	 * ActionEvent handler for button presses, etc.
	 */
	public void actionPerformed(ActionEvent e){
		switch(e.getActionCommand()){
		case "comboBoxChanged":
			logo.setColor(colors.get(((JComboBox)e.getSource()).getSelectedItem()));
			break;
		case "Reset": reset();
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
		colorBox.setSelectedIndex(0);
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
	public double getCurrentPercentage(){
		return size.getValue() / 100.;
	}
	
	/**
	 * Computes the current speed factor for the animation.
	 * @return
	 */
	private double getCurrentSpeed(){
		if(speeds[0].isSelected()) return SLOW_SPEED;
		if(speeds[2].isSelected()) return FAST_SPEED;
		return MEDIUM_SPEED;
	}
} 
