package unit11.minesweeper;

import java.awt.Dimension;
import java.awt.event.ActionEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

import acm.program.GraphicsProgram;

/**
 * The classic Minesweeper game, (c) Microsoft.
 * @author Mark Jones
 *
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
	
	public static final Dimension EASY_DIM = new Dimension(8,8);
	public static final Dimension NORMAL_DIM = new Dimension(16,16);
	public static final Dimension HARD_DIM = new Dimension(31,16);
	
	public static final int EASY_MINES = 10;
	public static final int NORMAL_MINES = 40;
	public static final int HARD_MINES = 99;
	
	private int currentRows = (int)EASY_DIM.getWidth();
	private int currentCols = (int)EASY_DIM.getHeight();
	private int currentMines = (int)EASY_MINES;
	
	/**
	 * A Board of cells.
	 */
	private Board board;
	
	//GUI
	private static final int WIDTH_OFFSET = 122;
	private static final int HEIGHT_OFFSET = 62;
	
	private JRadioButton[] difficulties = {new JRadioButton("Easy"),
			new JRadioButton("Normal"),new JRadioButton("Hard"), new JRadioButton("Custom")
	};
	
	private JButton newGame = new JButton("New Game");
	
	private ButtonGroup difficultyGroup = new ButtonGroup();
	
	/**
	 * Create a Minesweeper game.
	 */
	public Minesweeper(){}
	
	/**
	 * Initializes the board, which is also the GUI.
	 */
	public void init() {
		
		for(JRadioButton b: difficulties) {
			difficultyGroup.add(b);
			add(b,WEST);
			b.addActionListener(this);
		}
		add(newGame,WEST);
		newGame.addActionListener(this);
		reset();
		addActionListeners();
	}
	
	public void reset() {
		//Remove old board from canvas
		if(board != null)
			for(int r = 0; r < board.getRows(); r++)
				for(int c = 0; c < board.getCols(); c++)
					remove(board.getCellAt(r, c));
		
		board = new Board(currentRows,currentCols,currentMines); //create new board
		
		//Add Cells to Canvas
		for(int r = 0; r < board.getRows(); r++)
			for(int c = 0; c < board.getCols(); c++)
				add(board.getCellAt(r, c),c*Cell.CELL_HEIGHT,r*Cell.CELL_WIDTH);
		
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
	 * Handler for button actions.
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
		}
		else if(e.getActionCommand() == "Normal") {
			currentRows = (int)NORMAL_DIM.getWidth();
			currentCols = (int)NORMAL_DIM.getHeight();
			currentMines = (int)NORMAL_MINES;
		}
		else if(e.getActionCommand() == "Hard") {
			currentRows = (int)HARD_DIM.getWidth();
			currentCols = (int)HARD_DIM.getHeight();
			currentMines = (int)HARD_MINES;
		}
		else if(e.getSource() instanceof Cell) {
			Cell cell = (Cell) e.getSource();
			if(!cell.isMarked()) {
				board.reveal(cell,true);
				if(cell instanceof MineCell) {
					JOptionPane.showMessageDialog(this, "You Lose!");
					board.revealAll();
				}
				else if(cell instanceof BlankCell) {
					revealBlanks(cell);
					if(board.allBlanksRevealed()) {
						JOptionPane.showMessageDialog(this, "You Win!");
						board.revealAll();
					}
				}
			}
		}
	}
}
