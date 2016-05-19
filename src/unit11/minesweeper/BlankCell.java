package unit11.minesweeper;

import java.awt.Color;
import java.awt.Font;

/**
 * @author Ozaner Hansha
 */
@SuppressWarnings("serial")
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
		this.setFont(new Font("Times New Roman", 0, 13));
		if(getMineCount(board) > 0)
			setText(""+getMineCount(board));
		setIcon(null);
		revealed = true;
	}
}
