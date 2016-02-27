package unit7.concentration;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import acm.graphics.GPoint;
import acm.program.GraphicsProgram;

/**
 * An app for the Concentration game.  This version uses playing cards via the
 * ConcentrationCardModel class.  The game may use images other than cards by
 * extending the ConcentrationModel abstract class with a different implementation.
 * 
 * The GCard class only required a couple of methods to implement the Cell interface.
 * Thus, for the most part, the classes used in earlier playing cards apps were able
 * to be reused without modification.
 * 
 * @author Ozaner Hansha
 * Dr. Jones<br>
 * AP Computer Science<br>
 * February 25th, 2016<br>
 */

@SuppressWarnings("serial")
public class Concentration extends GraphicsProgram implements ChangeListener {
	
	/**
	 * The maximum amount of players.
	 */
	public static final int MAX_PLAYERS = 4;
	
	/**
	 * The maximum size of the rows/columns.
	 */
	public static final int MAX_SIZE = 10;
	
	/**
	 * The minimum amount of players.
	 */
	public static final int MIN_PLAYERS = 1;
	
	/**
	 * The minimum size of the rows/columns.
	 */
	public static final int MIN_SIZE = 1;
	
	/**
	 * How many spaces (" ") between each {@link #scores}.
	 */
	public static final int SCORE_SPACING = 8;
	
	/**
	 * Space (in pixels) between each card, both x and y.
	 */
	public static final int CARD_SPACING = 5;
	
	/**
	 * Labels above {@link #options} boxes.
	 */
	public static final JLabel[] OPTION_LABELS = 
		{new JLabel("Columns (1-10)"), new JLabel("Rows (1-10)"),
		new JLabel("Players (1-4)"), new JLabel("Memory Time (0-5 Sec)")};
	
	/**
	 * The font of the labels.
	 */
	public static final Font NAME_FONT = new Font("Ariel", Font.BOLD, 13);
	
	/**
	 * Path of the tie sound effect.
	 * @see #playSound(String)
	 */
	public static final String TIE_SOUND = "/concentrationTie.wav";
	
	/**
	 * Path of the win sound effect.
	 * @see #playSound(String)
	 */
	public static final String WIN_SOUND = "/concentrationWin.wav";
			
	/**
	 * A reference to the {@link ConcentrationCardModel} for callbacks.
	 */
	private ConcentrationModel model;
	
	/**
	 * Displays who's turn it is and who won.
	 */
	private JLabel notifications = new JLabel();
	
	/**
	 * This displays the players' scores.
	 * @see #updateScoreboard()
	 */
	private JLabel scoreText = new JLabel("Scores for Player");
	
	/**
	 * Displays the scores of the players.
	 */
	private JLabel[] scores = new JLabel[4];

	/**
	 * This button starts a new game when pressed.
	 * @see #newGame()
	 */
	private JButton newGame = new JButton("New Game");
	
	/**
	 * The rows, columns and player options.
	 */
	private JTextField[] options = new JTextField[3];
	
	/**
	 * A slider that changes {@link #memoryTime}.
	 */
	private JSlider timerSlider = new JSlider(0,5000,1000);
	
	/**
	 * The amount of time to memorize the cells (in milliseconds).
	 * @see #cellTimer
	 */
	private int memoryTime = 1000;
	
	/**
	 * Whether or not the game is currently paused due to a timer.
	 */
	private boolean paused;
	
	/**
	 * The main method, starts graphics program
	 * @param args - Not expecting any args.
	 */
	public static void main(String[] args) {
		new Concentration().start(args);
	}

	/**
	 * Initializes GUI and adds event listeners.
	 * @see acm.program.GraphicsProgram#init()
	 */
	public void init() {
		model = new ConcentrationCardModel(this);
		setBackground(Color.LIGHT_GRAY);
		
		notifications.setFont(NAME_FONT);
		add(notifications, NORTH);
		notifications.setText("Player 1's Turn");
		
		//Scoreboard
		scoreText.setFont(NAME_FONT);
		add(scoreText, SOUTH);
		for(int i = 0; i < scores.length; i++){
			add(new JLabel(new String(new char[SCORE_SPACING]).replace("\0", " ")),SOUTH);
			scores[i] = new JLabel("Player " + (i+1) + ": 0");
			scores[i].setFont(NAME_FONT);
			add(scores[i], SOUTH);
		}
		
		newGame.setFont(NAME_FONT);
		add(newGame, WEST); //Adds new game button
		
		//Rows, Columns & Player options
		for(int i = 0; i < options.length; i++ ) {
			options[i] = new JTextField("2");
			options[i].addActionListener(this);
			add(new JLabel(" "),WEST);
			OPTION_LABELS[i].setFont(NAME_FONT);
			add(OPTION_LABELS[i], WEST);
			options[i].setFont(NAME_FONT);
			add(options[i], WEST);
		}
		
		//Adds Timer slider
		add(new JLabel(" "),WEST);
		add(OPTION_LABELS[3],WEST);
		timerSlider.addChangeListener(this);
		add(timerSlider,WEST);
		
		newGame();
		addActionListeners();
		addMouseListeners();
	}

	/**
	 * Checks to see if a cell was chosen and informs {@link #model}.
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		if(!paused) {
		Object obj = getElementAt(new GPoint(e.getPoint()));
		if(obj instanceof GCard)
			model.choose((Cell)obj);
		}
	}
	
	/**
	 * Handles New Game Button input.
	 * @see acm.program.Program#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("New Game"))
			newGame();
	}
	
	/**
	 * Handles Slider Input
	 * @see javax.swing.event.ChangeListener#stateChanged(javax.swing.event.ChangeEvent)
	 */
	@Override
	public void stateChanged(ChangeEvent e) {
		if(e.getSource().equals(timerSlider))
			memoryTime = timerSlider.getValue();
	}
	
	/**
	 * Adds the board and adds player scores to the GUI.
	 * @param board - 2D Array of cells.
	 * @param player - number of players.
	 */
	public void gameStartedNotification(Cell[][] board, int players) {
		//Grays out names of non-Players
		for(int i = 0; i < MAX_PLAYERS; i++)
			scores[i].setForeground(Color.LIGHT_GRAY);
		for(int i = 0; i < players; i++)
			scores[i].setForeground(Color.BLACK);
		
		//Adds board to screen
		int rows = board.length;
		int cols = board[0].length;
		for(int i = 0; i < rows*cols; i++) {
			int x = i/cols, y = i%cols;
			add((GCard)board[x][y], 
				x * (model.getCellWidth() + CARD_SPACING),y * (model.getCellHeight()+CARD_SPACING));
		}
	}

	/**
	 * Returns whether or not a string can be parsed to an int.
	 * @param string - The string to check
	 * @return True if string can be parsed to an int, else false.
	 */
	public boolean isInteger(String string) {
	    try {
	        Integer.valueOf(string);
	        return true;
	    } catch (NumberFormatException e) {
	        return false;
	    }
	}
	
	/**
	 * Starts a new game with the given parameters ({@link #options}).
	 */
	public void newGame() {
		//Removes all cards from canvas.
		for(int x = getGCanvas().getElementCount()-1; x >= 0; x--) {
			if(getGCanvas().getElement(x) instanceof GCard) {
				getGCanvas().remove(getGCanvas().getElement(x));
			}
		}
		for(int i = 0; i < scores.length; i++)
			scores[i].setText("Player " + (i+1) + ": 0");
		
		int rows,cols,players;
		
		//Checks if options are invalid
		rows = isInteger(options[0].getText()) ? 
				Integer.parseInt(options[0].getText()): MIN_SIZE;
		cols = isInteger(options[1].getText()) ? 
				Integer.parseInt(options[1].getText()): MIN_SIZE;
		players = isInteger(options[2].getText()) ? 
				Integer.parseInt(options[2].getText()): MIN_PLAYERS;
		
		//Checks if options are over/under the limit.
		if(rows > MAX_SIZE) rows = MAX_SIZE;
		if(rows < MIN_SIZE) rows = MIN_SIZE;
		if(cols > MAX_SIZE) cols = MAX_SIZE;
		if(cols < MIN_SIZE) cols = MIN_SIZE;
		if(players > MAX_PLAYERS) players = MAX_PLAYERS;
		if(players < MIN_PLAYERS) players = MIN_PLAYERS;
		
		//Checks if there is an even # of cards.
		if(rows*cols % 2 == 1)
			rows = rows == MAX_SIZE ? rows-1:rows+1;
		
		//Updates text fields to reflect changes (if any)
		options[0].setText(""+rows);
		options[1].setText(""+cols);
		options[2].setText(""+players);
		
		setSizeCells(rows, cols);
		model.startGame(rows,cols,players);
		notifications.setText("Player 1's Turn");
	}
	
	/**
	 * Sets the size of the window based of of the table size.
	 * @param rows - Rows of board.
	 * @param cols - Columns of board.
	 */
	public void setSizeCells(int rows, int cols) {
		int xOffset = 152, yOffset = 117;
		int minWidth = 580, minHeight = 440;
		int windowWidth = (int)(model.getCellWidth()+CARD_SPACING)*rows + xOffset;
		int windowHeight = (int)(model.getCellHeight()+CARD_SPACING)*cols + yOffset;
		setSize(windowWidth < minWidth ? minWidth:windowWidth,
			windowHeight < minHeight ? minHeight:windowHeight);
	}
	
	/**
	 * Turn the cell face up.
	 * @param cell  the chosen cell
	 */
	public void selectCellNotification(Cell cell) {
		cell.turnFaceUp();
	}

	/**
	 * Update the player's score and prepare to remove the cells.
	 * @param cell1       cell to be removed (after a brief pause)
	 * @param cell2       cell to be removed (after a brief pause)
	 * @param player      the player who scored
	 * @param score       the player's new score     
	 */
	public void removeCellsNotification(Cell cell1, Cell cell2, int player, int score) {
		paused = true;
		new Timer(memoryTime, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				remove((GCard)cell1);
				remove((GCard)cell2);
				scores[player-1].setText("Player " + (player) + ": " + score);
				paused = false;
				((Timer)e.getSource()).stop();
			}	
		}).start();
	}

	/**
	 * Prepare to turn the selected cells face down again, and pass play to the next player.
	 * @param cell1       cell to be turned over (after a brief pause)
	 * @param cell2       cell to be turned over (after a brief pause)
	 * @param nextPlayer  the next player to play
	 */
	public void deselectCellsNotification(Cell cell1, Cell cell2, int nextPlayer) {
		paused = true;
		new Timer(memoryTime, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cell1.turnFaceDown();
				cell2.turnFaceDown();
				notifications.setText("Player " + nextPlayer + "'s Turn");
				paused = false;
				((Timer)e.getSource()).stop();
			}	
		}).start();
	}
	
	/**
	 * Notification that the game is over, and which players had the highest score.
	 * @param winners    the winning players
	 */
	public void gameOverNotification(ArrayList<Integer> winners) {
		String str;
		if(winners.size() == 1) {
			str = "Player " + winners.get(0) + " wins!";
			playSound(WIN_SOUND);
		}
		else {
			str = "Players " + winners.get(0);
			for(int i = 1; i < winners.size(); i++)
				str += " and " + winners.get(i);
			str += " win!";
			playSound(TIE_SOUND);
		}
		notifications.setText(str);
	}
	
	/**
	 * Plays the given .wav file.
	 * @param URL - Path of the sound in project.
	 */
	public void playSound(String URL) {
		AudioInputStream audioIn;
		try {
			audioIn = AudioSystem.getAudioInputStream(Concentration.class.getResource(URL));
			Clip clip = AudioSystem.getClip();
			clip.open(audioIn);
			clip.start();
		}
		catch(UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			e.printStackTrace();
		}

	}
}