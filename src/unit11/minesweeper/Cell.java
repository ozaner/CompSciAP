package unit11.minesweeper;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * Abstract class representing a Minesweeper Cell.
 * @author Ozaner Hansha
 */

@SuppressWarnings("serial")
public abstract class Cell extends JButton {

	public static final int CELL_WIDTH = 40;
	public static final int CELL_HEIGHT = 40;
	
	protected int row, col;
	protected boolean revealed;
	protected boolean marked;
	
	private static ImageIcon FLAG;
	
	/**
	 * Creates a Minesweeper Cell given a row and a column.
	 * The default unrevealed cell is currently a simple blue rectangle. 
	 * @param r
	 * @param c
	 */
	public Cell(int r, int c) {
		super("");
		setSize(CELL_WIDTH,CELL_HEIGHT);
		setBackground(Color.BLUE);
		Image flag = new ImageIcon("minesweeperRes/flag.png").getImage();
		FLAG = new ImageIcon(flag.getScaledInstance(Cell.CELL_WIDTH, Cell.CELL_HEIGHT, 0));
		row = r;
		col = c;
		revealed = false;
		Cell cell = this;
		addMouseListener(new MouseAdapter(){
		    public void mouseClicked(MouseEvent e){
		    	if(e.isMetaDown() && !cell.isRevealed())
		    		cell.mark();
		    }
		});
	}
	
	/**
	 * @param board - Board this cell is contained in.
	 * @return Amount of mines within 1 square of this cell.
	 */
	public int getMineCount(Board board) {
		int count = 0;
		for(int x = -1; x < 2; x++)
			for(int y = -1; y < 2; y++)
				if(board.getCellAt(row+x, col+y) instanceof MineCell)
					count++;
		return count;
	}
	
	/**
	 * Any subclass of Cell must implement this method to change the 
	 * appearance and state of the cell to reflect its being revealed.
	 */
	public abstract void reveal(Board board, boolean firstMine);
	
	/**
	 * Test if the cell has been revealed.
	 * @return   true if the cell has been revealed, false otherwise
	 */
	public boolean isRevealed() {
		return revealed;
	}
	
	/**
	 * @param cell1
	 * @param cell2
	 * @return Whether the 2 cells are adjacent.
	 */
	public boolean isAdjacent(Cell cell) {
		return (getRow() == cell.getRow() && Math.abs(getCol() - cell.getCol()) == 1) ||
				(getCol() == cell.getCol() && Math.abs(getRow() - cell.getRow()) == 1);
	}
	
	public boolean isMarked() {
		return marked;
	}
	
	/**
	 * Getter for the cell's row.
	 * @return   the row
	 */
	public int getRow() {
		return row;
	}
	
	/**
	 * Getter for the cell's column
	 * @return   the column
	 */
	public int getCol() {
		return col;
	}
	
	public void mark() {
		if(marked) {
			setIcon(null);
			marked = false;
		}
		else {
			setIcon(FLAG);
			marked = true;
		}
	}
	
	/**
	 * A printable representation of a Cell.
	 */
	public String toString() {
		return String.format("[%d,%d]", row, col);
	}
}