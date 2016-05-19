package unit11.minesweeper;

import java.awt.Color;

/**
 * @author Ozaner Hansha
 *
 */
public class BlankCell extends Cell {

	/**
	 * @param r
	 * @param c
	 */
	public BlankCell(int r, int c) {
		super(r, c);
	}
	
	/**
	 * @see unit11.minesweeper.Cell#reveal()
	 */
	@Override
	public void reveal(Board board, boolean firstMine) {
		setBackground(Color.GRAY);
		if(getMineCount(board) > 0)
			setText(""+getMineCount(board));
		setIcon(null);
		revealed = true;
	}
}
