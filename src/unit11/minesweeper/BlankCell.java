package unit11.minesweeper;

import java.awt.Color;
import java.awt.Font;

/**
 * Class extending {@link Cell} but without a mine.
 * @author Ozaner Hansha
 */
@SuppressWarnings("serial")
public class BlankCell extends Cell {

	/**
	 * @param r - row
	 * @param c - column
	 */
	public BlankCell(int r, int c) {
		super(r, c);
	}
	
	/**
	 * Reveals the cell by turning it gray and displaying 
	 * the amount of mines present, unless it is 0.
	 * @see unit11.minesweeper.Cell#reveal()
	 */
	@Override
	public void reveal(Board board, boolean firstMine) {
		setBackground(Color.GRAY);
		this.setFont(new Font("Times New Roman", 0, 13));
		if(getMineCount(board) > 0)
			setText(""+getMineCount(board));
		setIcon(null);
		revealed = true;
	}
}