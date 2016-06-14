package unit11.minesweeper;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.Timer;
import acm.program.GraphicsProgram;
import acm.util.SoundClip;

/**
 * Minesweeper made using Java components.
 * @author Ozaner Hansha
 */
@SuppressWarnings("serial")
public class Minesweeper extends GraphicsProgram {
	/**
	 * Runs Minesweeper as an application.
	 * @param args	none are expected
	 */
	public static void main(String[] args) {
		(new Minesweeper()).start(args);
	}
	
	//Difficulty Constants
	private static final Dimension EASY_DIM = new Dimension(8,8);
	private static final Dimension NORMAL_DIM = new Dimension(16,16);
	private static final Dimension HARD_DIM = new Dimension(16,31);
	
	private static final int MIN_DIM = 2;
	private static final int MAX_ROWS = 20;
	private static final int MAX_COLS = 31;
	
	private static final int EASY_MINES = 10;
	private static final int NORMAL_MINES = 40;
	private static final int HARD_MINES = 99;
	
	//Difficulty Variables
	private int currentRows = (int)EASY_DIM.getWidth();
	private int currentCols = (int)EASY_DIM.getHeight();
	private int currentMines = (int)EASY_MINES;
	
	/**
	 * Sound clip of a mine exploding.
	 */
	public static final SoundClip bombSound = new SoundClip(new File("minesweeperRes/bomb.wav"));
	
	/**
	 * Location to load/save scores.
	 */
	public static final String SCORE_LOCATION = "minesweeperRes/scores.xml";
	
	/**
	 * Amount to offset the width on account of the GUI
	 */
	private static final int WIDTH_OFFSET = 121;
	
	/**
	 * Amount to offset the height on account of the GUI
	 */
	private static final int HEIGHT_OFFSET = 62;
	
	/**
	 * A Board of cells.
	 */
	private Board board;
	
	/**
	 * Thread that keep track of the timer.
	 */
	private Timer timer;
	
	/**
	 * current time taken on the board in seconds.
	 */
	private int currentTime;
	
	/**
	 * Amount of wins and losses.
	 */
	private int wins, losses;
	
	/**
	 * Record of highscores.
	 */
	private ScoreSheet scoreSheet = new ScoreSheet();
	
	/**
	 * Whether the game is over or not.
	 */
	private boolean gameOver;
	
	//GUI Fields
	private JRadioButton[] difficulties = {new JRadioButton("Easy"),
			new JRadioButton("Normal"),new JRadioButton("Hard"), new JRadioButton("Custom")
	};
	private ButtonGroup difficultyGroup = new ButtonGroup();
	private JLabel winsTag = new JLabel("Wins: 0"),
			lossesTag = new JLabel("Losses: 0"),
			timerLabel = new JLabel("Time Taken: 0");
	private JButton newGame = new JButton("New Game");
	private JTextField customRows = new JTextField("Rows"),
			customCols = new JTextField("Columns"),
			customMines = new JTextField("Mines");
	
	/**
	 * Create a Minesweeper game.
	 */
	public Minesweeper(){}
	
	/**
	 * Initializes the board, which is also the GUI.
	 */
	public void init() {
		bombSound.setVolume(.5);
		scoreSheet.loadXML(SCORE_LOCATION);
		
		timer = new Timer(1000,new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				currentTime++;
				timerLabel.setText("Time Taken: " + currentTime);
			}
		});
		timer.start();
		
		//Difficulty options
		for(JRadioButton b: difficulties) {
			difficultyGroup.add(b);
			add(b,WEST);
			b.addActionListener(this);
		}
		difficulties[0].setSelected(true);
		
		//New Game button
		add(newGame,WEST);
		newGame.addActionListener(this);
		
		//Custom difficulty boxes
		add(customRows,WEST);
		customRows.addActionListener(this);
		customRows.setEnabled(false);
		
		add(customCols,WEST);
		customCols.addActionListener(this);
		customCols.setEnabled(false);
		
		add(customMines,WEST);
		customMines.addActionListener(this);
		customMines.setEnabled(false);
		updateGUI();
		
		add(winsTag,WEST);
		add(lossesTag,WEST);
		add(timerLabel,WEST);
		
		reset();
		addActionListeners();
	}
	
	/**
	 * Resets the board with the current difficulty variables.
	 */
	public void reset() {
		gameOver = false;
		if(difficulties[3].isSelected()) {
			currentRows = Integer.parseInt(customRows.getText());
			currentCols = Integer.parseInt(customCols.getText());
			currentMines = Integer.parseInt(customMines.getText());
		}
		
		//Check if board size is within bounds.
		if(currentRows < MIN_DIM)
			currentRows = MIN_DIM;
		if(currentRows > MAX_ROWS)
			currentRows = MAX_ROWS;
		if(currentCols < MIN_DIM)
			currentCols = MIN_DIM;
		if(currentCols > MAX_COLS)
			currentCols = MAX_COLS;
		if(currentMines > currentRows*currentCols)
			currentRows = currentRows*currentCols-1;
		updateGUI();
		
		removeAll(); //removes old board
		board = new Board(currentRows,currentCols,currentMines); //create new board
		
		//Add Cells to Canvas
		for(int r = 0; r < board.getRows(); r++)
			for(int c = 0; c < board.getCols(); c++) {
				add(board.getCellAt(r, c),c*Cell.CELL_HEIGHT,r*Cell.CELL_WIDTH);
				board.getCellAt(r, c).addActionListener(this);
			}
		
		//Set Size
		setSize(board.getCols()*Cell.CELL_WIDTH+WIDTH_OFFSET, board.getRows()*Cell.CELL_HEIGHT+HEIGHT_OFFSET);
		
		currentTime = 0;
		timerLabel.setText("Time Taken: 0");
		timer.start();
	}
	
	/** 
	 * Handler for button and cell actions.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand() == "New Game") {
			reset();
		}
		else if(e.getActionCommand() == "Easy") {
			currentRows = (int)EASY_DIM.getWidth();
			currentCols = (int)EASY_DIM.getHeight();
			currentMines = (int)EASY_MINES;
			enableTextBoxes(false);
			updateGUI();
		}
		else if(e.getActionCommand() == "Normal") {
			currentRows = (int)NORMAL_DIM.getWidth();
			currentCols = (int)NORMAL_DIM.getHeight();
			currentMines = (int)NORMAL_MINES;
			enableTextBoxes(false);
			updateGUI();
		}
		else if(e.getActionCommand() == "Hard") {
			currentRows = (int)HARD_DIM.getWidth();
			currentCols = (int)HARD_DIM.getHeight();
			currentMines = (int)HARD_MINES;
			enableTextBoxes(false);
			updateGUI();
		}
		else if(e.getActionCommand() == "Custom") {
			enableTextBoxes(true);
			updateGUI();
		}
		else if(e.getSource() instanceof Cell && !gameOver) {
			Cell cell = (Cell) e.getSource();
			int result = board.clickCell(cell);
			
			String title = "",message = "";
			Object[] options = {"Continue", "Exit"};
			int choice = 0;
			
			switch(result) {
			case 1: //Game won
				timer.stop();
				wins++;
				updateGUI();
				gameOver = true;
				title = "You Win!";
				
				int previousTime = scoreSheet.getScore(board.getRows(), board.getCols());
				scoreSheet.addScore(board.getRows(), board.getCols(), board.getMines(), currentTime);
				
				if(previousTime == -1)
					message = "You Win! Your time was " + currentTime + "s";
				else if(previousTime > currentTime)
					message = "You beat the previous high time of " + previousTime 
					+ "s with a score of " + currentTime + "s!";
				else
					message = "You Win! Your time was " + currentTime +
					"s. The record for this config is " + previousTime;
				break;
			case 2: //Game lost
				bombSound.play();
				timer.stop();
				losses++;
				updateGUI();
				gameOver = true;
				title = "You Lose!";
				message = "You Lost! Your time was " + currentTime + "s.";
				break;
			default: //Continue game if not won or lost.
				return;
			}
			choice = JOptionPane.showOptionDialog(this, message, title,
					JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
					null, options, options[0]);
			if(choice == 1) {
				scoreSheet.saveXML(SCORE_LOCATION); //Saves scores before exiting.
				System.exit(0);
			}
		}
	}
	
	/**
	 * Updates the text boxes with the current row/col/mines and the scores.
	 */
	public void updateGUI() {
		customRows.setText(""+currentRows);
		customCols.setText(""+currentCols);
		customMines.setText(""+currentMines);
		lossesTag.setText("Losses: "+ losses);
		winsTag.setText("Wins: "+ wins);
	}
	
	/**
	 * Enables or disables all textboxes
	 * @param lock
	 */
	public void enableTextBoxes(boolean lock) {
		customRows.setEnabled(lock);
		customCols.setEnabled(lock);
		customMines.setEnabled(lock);
	}
}
