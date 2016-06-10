package unit11.minesweeper;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.Date;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import acm.program.GraphicsProgram;
import acm.util.SoundClip;

/**
 * The classic Minesweeper game, (c) Microsoft.
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
	 * Amount to offset the width on account of the GUI
	 */
	private static final int WIDTH_OFFSET = 122;
	
	/**
	 * Amount to offset the height on account of the GUI
	 */
	private static final int HEIGHT_OFFSET = 62;
	
	/**
	 * Amount of wins and losses.
	 */
	private int wins, losses;
	
	/**
	 * A Board of cells.
	 */
	private Board board;
	
	/**
	 * Array of difficulty buttons.
	 */
	private JRadioButton[] difficulties = {new JRadioButton("Easy"),
			new JRadioButton("Normal"),new JRadioButton("Hard"), new JRadioButton("Custom")
	};
	
	/**
	 * Groups Difficulty buttons.
	 */
	private ButtonGroup difficultyGroup = new ButtonGroup();
	
	/**
	 * JLabel for wins and losses.
	 */
	private JLabel winsTag = new JLabel("Wins: 0"), lossesTag = new JLabel("Losses: 0");
	
	/**
	 * JLabel for time.
	 */
	private JLabel timer = new JLabel("Time Taken: 0");
	
	/**
	 * When the round started.
	 */
	private Date start = new Date();
	
	/**
	 * Thread that keep track of the timer.
	 */
	private Thread timeThread;
	
	/**
	 * New Game Button
	 */
	private JButton newGame = new JButton("New Game");
	
	/**
	 * Custom rows text box
	 */
	private JTextField customRows = new JTextField("Rows");
	
	/**
	 * Custom columns text box
	 */
	private JTextField customCols = new JTextField("Columns");
	
	/**
	 * Custom mines text box
	 */
	private JTextField customMines = new JTextField("Mines");
	
	/**
	 * Create a Minesweeper game.
	 */
	public Minesweeper(){}
	
	/**
	 * Initializes the board, which is also the GUI.
	 */
	public void init() {
		bombSound.setVolume(.5);
		
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
		updateTextBoxes();
		
		add(winsTag,WEST);
		add(lossesTag,WEST);
		add(timer,WEST);
		
		reset();
		addActionListeners();
	}
	
	/**
	 * Resets the board with the current difficulty variables.
	 */
	public void reset() {
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
		updateTextBoxes();
		
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
		
		start.setTime(new Date().getTime());
		//Aliases for the thread below
		JLabel t = timer;
		Date s = start;
		
		timeThread = new Thread() {
			public void run() {
				while(true) {
					Date now = new Date();
					Date difference = new Date(now.getTime() - s.getTime());
					if(difference.getSeconds() > 9)
						t.setText("Time Taken: "+ difference.getMinutes() + ":" + difference.getSeconds());
					else
						t.setText("Time Taken: "+ difference.getMinutes() + ":0" + difference.getSeconds());
				}
			}
		};
		timeThread.start();
	}
	
	/**
	 * Reveals all blanks up to a mine.
	 * @param cell
	 */
	public void revealBlanks(Cell cell) {
		if(cell.getMineCount(board) == 0) {
			revealBlanksHelper(board.getCellAt(cell.getRow()-1, cell.getCol()-1));
			revealBlanksHelper(board.getCellAt(cell.getRow()-1, cell.getCol()));
			revealBlanksHelper(board.getCellAt(cell.getRow()-1, cell.getCol()+1));
			
			revealBlanksHelper(board.getCellAt(cell.getRow(), cell.getCol()-1));
			revealBlanksHelper(board.getCellAt(cell.getRow(), cell.getCol()+1));
			
			revealBlanksHelper(board.getCellAt(cell.getRow()+1, cell.getCol()-1));
			revealBlanksHelper(board.getCellAt(cell.getRow()+1, cell.getCol()));
			revealBlanksHelper(board.getCellAt(cell.getRow()+1, cell.getCol()+1));
		}
	}
	
	/**
	 * Helps the {@link #revealBlanks(Cell)} method with recursion.
	 * @param cell
	 */
	public void revealBlanksHelper(Cell cell) {
		if(cell == null || cell.isRevealed())
			return;
		else if(cell instanceof BlankCell) {
			if(cell.getMineCount(board) == 0) {
				cell.reveal(board, false);
				revealBlanks(cell);
			}
			else
				cell.reveal(board, false);
		}
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
			updateTextBoxes();
		}
		else if(e.getActionCommand() == "Normal") {
			currentRows = (int)NORMAL_DIM.getWidth();
			currentCols = (int)NORMAL_DIM.getHeight();
			currentMines = (int)NORMAL_MINES;
			enableTextBoxes(false);
			updateTextBoxes();
		}
		else if(e.getActionCommand() == "Hard") {
			currentRows = (int)HARD_DIM.getWidth();
			currentCols = (int)HARD_DIM.getHeight();
			currentMines = (int)HARD_MINES;
			enableTextBoxes(false);
			updateTextBoxes();
		}
		else if(e.getActionCommand() == "Custom") {
			enableTextBoxes(true);
			updateTextBoxes();
		}
		else if(e.getSource() instanceof Cell && !board.allBlanksRevealed()) {
			Cell cell = (Cell) e.getSource();
			if(!cell.isMarked()) {
				board.reveal(cell,true);
				if(cell instanceof MineCell) {
					bombSound.play();
					timeThread.stop();
					Object[] options = {"Continue", "Exit"};
					int choice = JOptionPane.showOptionDialog(this, "You Lose!", "You Lose!",
							JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
							null, options, options[0]);
					if(choice == 1)
						System.exit(0);
					losses++;
					lossesTag.setText("Losses: "+ losses);
					board.revealAll();
				}
				else if(cell instanceof BlankCell) {
					revealBlanks(cell);
					if(board.allBlanksRevealed()) {
						timeThread.stop();
						Object[] options = {"Continue", "Exit"};
						int choice = JOptionPane.showOptionDialog(this, "You Win!", "You Win!",
								JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
								null, options, options[0]);
						if(choice == 1)
							System.exit(0);
						wins++;
						winsTag.setText("Wins: "+ wins);
						board.revealAll();
					}
				}
			}
		}
	}
	
	/**
	 * Updates the text boxes with the current row/col/mines
	 */
	public void updateTextBoxes() {
		customRows.setText(""+currentRows);
		customCols.setText(""+currentCols);
		customMines.setText(""+currentMines);
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
