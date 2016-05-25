package unit11.minesweeper;

import java.awt.Dimension;
import java.awt.event.ActionEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import acm.program.GraphicsProgram;

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
	
	private static final int EASY_MINES = 10;
	private static final int NORMAL_MINES = 40;
	private static final int HARD_MINES = 99;
	
	//Difficulty Variables
	private int currentRows = (int)EASY_DIM.getWidth();
	private int currentCols = (int)EASY_DIM.getHeight();
	private int currentMines = (int)EASY_MINES;
	
	/**
	 * A Board of cells.
	 */
	private Board board;
	
	/**
	 * Amount to offset the width on account of the GUI
	 */
	private static final int WIDTH_OFFSET = 122;
	
	/**
	 * Amount to offset the height on account of the GUI
	 */
	private static final int HEIGHT_OFFSET = 62;
	
	private int wins, losses;
	
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
	
	private JLabel winsTag = new JLabel("Wins: 0");
	private JLabel lossesTag = new JLabel("Losses: 0");
	
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
		if(currentRows < 2)
			currentRows = 2;
		if(currentRows > 20)
			currentRows = 20;
		if(currentCols < 2)
			currentCols = 2;
		if(currentCols > 31)
			currentCols = 31;
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
					JOptionPane.showMessageDialog(this, "You Lose!");
					losses++;
					lossesTag.setText("Losses: "+ losses);
					board.revealAll();
				}
				else if(cell instanceof BlankCell) {
					revealBlanks(cell);
					if(board.allBlanksRevealed()) {
						JOptionPane.showMessageDialog(this, "You Win!");
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
