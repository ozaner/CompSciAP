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

/**
 * Project 11 - Hangman<br>
 * Ozaner Hansha, Armen<br>
 * Dr. Jones<br>
 * AP Computer Science<br>
 * April 4th, 2016<br>
 */
@SuppressWarnings("serial")
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
	
	/**
	 * The new round button.
	 */
	private JButton newGameButton = new JButton("New Round");
	
	/**
	 * Displays notifications about the game in the top.
	 */
	private JLabel notifications = new JLabel();
	
	/**
	 * The corresponding {@link HangmanModel} for method calls.
	 */
	private HangmanModel model = new HangmanModel(this,graphics.getMaxParts()-1);
	
	/**
	 * Stores letter spaces for later reference (removal).
	 */
	private ArrayList<GLine> lineBuffer = new ArrayList<GLine>();
	
	/**
	 * Stores letter objects for letter reference (removal/visibility).
	 */
	private ArrayList<GLabel> letterBuffer = new ArrayList<GLabel>();
	
	/**
	 * Initializes the GUI.
	 * @see acm.program.GraphicsProgram#init()
	 */
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
	
	/**
	 * Catches button press events.
	 * @see acm.program.Program#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("New Round"))
			model.newRound();
	}
	
	/**
	 * Catches enter key events.
	 * @see acm.program.Program#keyPressed(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER)
			model.newRound();
	}
	
	/**
	 * Catches keyboard key events.
	 * @see acm.program.Program#keyTyped(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyTyped(KeyEvent e) {
		model.guess(Character.toUpperCase(e.getKeyChar()));
	}
	
	/**
	 * Sets up the given word for this round.
	 * @see unit9.HangmanView#gameStartNotification(java.lang.String)
	 */
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
		
//		for(int x = 0; x < 26; x++) {
//			GLabel temp = new GLabel(""+((char)(x + 65))); //Uppercase Letter starting at A.
//			temp.setFont(LETTER_FONT);
//			temp.setColor(Color.GREEN);
//			add(temp,430+(x%9)*(50+10),200+((x/9)*50));
//		}
	}
	
	/**
	 * Displays winning message.
	 * @see unit9.HangmanView#gameWonNotification()
	 */
	@Override
	public void gameWonNotification() {
		notifications.setText("You won.");
	}
	
	/**
	 * Displays losing message.
	 * @see unit9.HangmanView#gameLostNotification()
	 */
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
	
	/**
	 * Resets the GUI.
	 */
	public void reset() {
		for(GLine g: lineBuffer)
			remove(g);
		for(GLabel l: letterBuffer)
			remove(l);
		lineBuffer.clear();
		letterBuffer.clear();
		graphics.reset();
	}
	
	/**
	 * Displays correct notification and shows the correct letter.
	 * @see unit9.HangmanView#correctNotification(char)
	 */
	@Override
	public void correctNotification(char c) {
		notifications.setText("Nice Job!");
		for(GLabel g: letterBuffer)
			if(g.getLabel().equals(""+c))
				g.setVisible(true);
	}
	
	/**
	 * Displays incorrect notification.
	 * @see unit9.HangmanView#incorrectNotification(char)
	 */
	@Override
	public void incorrectNotification(char c) {
		notifications.setText("That letter was incorrect. Try again.");
		graphics.drawNextPart();
	}
	
	/**
	 * Displays already guessed notification.
	 * @see unit9.HangmanView#alreadyGuessedNotification(char)
	 */
	@Override
	public void alreadyGuessedNotification(char c) {
		notifications.setText("That letter has already been guessed. Try again.");
	}
	
	/**
	 * Starts the program.
	 * @param args - No arguments expected.
	 */
	public static void main(String[] args) {
		new Hangman().start();
	}
}
