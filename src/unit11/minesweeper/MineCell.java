package unit11.minesweeper;

import java.awt.Color;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 * @author Ozaner Hansha
 */
@SuppressWarnings("serial")
public class MineCell extends Cell {

	private static ImageIcon mineRed;
	private static ImageIcon mineGray;
	
	/**
	 * @param r
	 * @param c
	 */
	public MineCell(int r, int c) {
		super(r, c);
		loadImage();
	}

	public static void loadImage() {
		Image red = new ImageIcon("minesweeperRes/Mine_Red.png").getImage();
		Image gray = new ImageIcon("minesweeperRes/Mine_Gray.png").getImage();
		mineRed = new ImageIcon(red.getScaledInstance(Cell.CELL_WIDTH, Cell.CELL_HEIGHT, 0));
		mineGray = new ImageIcon(gray.getScaledInstance(Cell.CELL_WIDTH, Cell.CELL_HEIGHT, 0));
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
