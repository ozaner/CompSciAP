package unit11.minesweeper;

import java.awt.Color;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 * Class extending {@link Cell} but with a mine.
 * @author Ozaner Hansha
 */
@SuppressWarnings("serial")
public class MineCell extends Cell {

	private static ImageIcon mineRed;
	private static ImageIcon mineGray;
	static {
		loadImage();
	}
	
	/**
	 * @param r
	 * @param c
	 */
	public MineCell(int r, int c) {
		super(r, c);
	}

	public static void loadImage() {
		Image temp = new ImageIcon("minesweeperRes/Mine_Red.png").getImage();
		mineRed = new ImageIcon(temp.getScaledInstance(Cell.CELL_WIDTH, Cell.CELL_HEIGHT, 0));
		temp = new ImageIcon("minesweeperRes/Mine_Gray.png").getImage();
		mineGray = new ImageIcon(temp.getScaledInstance(Cell.CELL_WIDTH, Cell.CELL_HEIGHT, 0));
	}
	
	/**
	 * @see unit11.minesweeper.Cell#reveal()
	 */
	@Override
	public void reveal(Board board, boolean firstMine) {
		setBackground(Color.RED);
		if(!isRevealed())
			if(firstMine)
				setIcon(mineRed);
			else
				setIcon(mineGray);
		revealed = true;
	}
}
