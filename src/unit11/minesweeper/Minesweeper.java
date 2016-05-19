package unit11.minesweeper;

import java.awt.event.ActionEvent;
import javax.swing.ButtonGroup;
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
	
	/**
	 * A Board of cells.
	 */
	private Board board;
	
	/**
	 * 0 for easy, 1 for normal, 2 for hard.
	 */
	private int difficulty;
	
	//GUI
	private static final int WIDTH_OFFSET = 92;
	private static final int HEIGHT_OFFSET = 62;
	
	private JRadioButton[] difficulties = {new JRadioButton("Easy"),
			new JRadioButton("Normal"),new JRadioButton("Hard")
	};
	
	private ButtonGroup difficultyGroup = new ButtonGroup();
	
	/**
	 * Create a Minesweeper game.
	 */
	public Minesweeper(){}
	
	/**
	 * Initializes the board, which is also the GUI.
	 */
	public void init() {
		board = new Board(11,10,10);
		
		for(JRadioButton b: difficulties) {
			add(b,WEST);
			difficultyGroup.add(b);
		}
		//Add Cells to Canvas
		for(int r = 0; r < board.getRows(); r++)
			for(int c = 0; c < board.getCols(); c++)
				add(board.getCellAt(r, c),c*Cell.CELL_HEIGHT,r*Cell.CELL_WIDTH);
		
		setSize(board.getCols()*Cell.CELL_WIDTH+WIDTH_OFFSET, board.getRows()*Cell.CELL_HEIGHT+HEIGHT_OFFSET);
		addActionListeners();
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
		Cell cell = (Cell) e.getSource();
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
