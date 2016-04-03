package unit9;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.graphics.GLine;
import acm.graphics.GPoint;
import acm.program.GraphicsProgram;

public class Hangman extends GraphicsProgram implements HangmanView {

	/**
	 * Size of the window containing the application.
	 * @see #init()
	 */
	public static final Dimension WINDOW_SIZE = new Dimension(1000,800);
	
	/**
	 * The coordinates of the {@link #graphics}
	 */
	public static final GPoint GRAPHICS_COORDS = new GPoint(50,70);
	
	/**
	 * Font letters will be displayed in.
	 */
	public static final Font LETTER_FONT = new Font("Times New Roman",Font.PLAIN,50);
	
	/**
	 * Font notifications will be displayed in.
	 */
	public static final Font NOTIFICATION_FONT = new Font("Papyrus", Font.BOLD, 20);
	
	/**
	 * Constants relating to the lines used in displaying the letters.
	 * @see #gameStartNotification(String)
	 */
	public static final int LINE_LENGTH = 60,LINE_Y = 600,LINE_GAP = 30;
	
	/**
	 * The graphics object for this HangmanView.
	 */
	private GHangman graphics = new GHangman();
	
	private JButton newGameButton = new JButton("New Round");
	
	/**
	 * Displays notifications about the game in the top.
	 */
	private JLabel notifications = new JLabel();
	
	/**
	 * The corresponding {@link HangmanModel} for method calls.
	 */
	private HangmanModel model = new HangmanModel(this,graphics.getMaxParts()-1);
	
	private ArrayList<GLine> lineBuffer = new ArrayList<GLine>();
	private ArrayList<GLabel> letterBuffer = new ArrayList<GLabel>();
	
	@Override
	public void init() {
		setSize(WINDOW_SIZE);
		setTitle("Hangman Simulator 2016");
		GImage bg = new GImage("src/unit9/bg.jpg");
		bg.scale(.6);
		add(bg,-100,0);
		graphics.drawNextPart(); //Adds Gallows
		add(graphics,GRAPHICS_COORDS);
		getGCanvas().requestFocus();
		
		add(notifications,NORTH);
		notifications.setFont(NOTIFICATION_FONT);
		notifications.setFocusable(false);
		add(newGameButton,SOUTH);
		newGameButton.setFocusable(false);
		
		addActionListeners();
		addKeyListeners();
		model.newRound();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("New Round"))
			model.newRound();
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER)
			model.newRound();
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		model.guess(Character.toUpperCase(e.getKeyChar()));
	}
	
	@Override
	public void gameStartNotification(String word) {
		notifications.setText("Welcome to Hangman! Type to guess a letter.");
		reset();
		for(int x = 0; x < word.length(); x++) {
			if(word.charAt(x) != ' ') { //does not add a line for space characters
				//Adds Line dependent on word size.
				int length = word.length()*(LINE_LENGTH+LINE_GAP)-LINE_GAP;
				int offset = (int)((getSize().getWidth()-length)/2);
				lineBuffer.add(new GLine(0,0,LINE_LENGTH,0));
				add(lineBuffer.get(lineBuffer.size()-1),x*(LINE_LENGTH+LINE_GAP)+offset,LINE_Y);
				
				//Adds letter above line and makes invisible.
				GLabel label = new GLabel(""+word.charAt(x));
				label.setFont(LETTER_FONT);
				label.setVisible(false);
				letterBuffer.add(label);
				add(label,x*(LINE_LENGTH+LINE_GAP)+(LINE_LENGTH-label.getWidth())/2+offset,LINE_Y);
			}
		}
	}
	
	@Override
	public void gameWonNotification() {
		notifications.setText("You won.");
	}
	
	@Override
	public void gameLostNotification() {
		notifications.setText("You lost.");
		graphics.drawNextPart();
		for(GLabel g: letterBuffer) {
			if(!g.isVisible()) {
				g.setVisible(true);
				g.setColor(Color.RED);
			}
		}
	}
	
	public void reset() {
		for(GLine g: lineBuffer)
			remove(g);
		for(GLabel l: letterBuffer)
			remove(l);
		lineBuffer.clear();
		letterBuffer.clear();
		graphics.reset();
	}
	
	@Override
	public void correctNotification(char c) {
		notifications.setText("Nice Job!");
		for(GLabel g: letterBuffer)
			if(g.getLabel().equals(""+c))
				g.setVisible(true);
	}
	
	@Override
	public void incorrectNotification(char c) {
		notifications.setText("That letter was incorrect. Try again.");
		graphics.drawNextPart();
	}
	
	@Override
	public void alreadyGuessedNotification(char c) {
		notifications.setText("That letter has already been guessed. Try again.");
	}
	
	public static void main(String[] args) {
		new Hangman().start();
	}
}
