package unit11.minesweeper;

import acm.graphics.GCompound;

public class Board extends GCompound{
	
	private Cell[][] board;
	
	private double percentOfMines = .5;
	
	public Board(int r, int c) {
		board = new Cell[r][c];
		
		//Populate board with cells
		for(int x = 0; x < r; x++)
			for(int y = 0; y < c; y++)
				board[x][y] = getCell(x,y);
		
		//Add Cells to GComound
		for(int x = 0; x < r; x++)
			for(int y = 0; y < c; y++)
				add(board[x][y],x*Cell.CELL_WIDTH,y*Cell.CELL_HEIGHT);
	}
	
	private Cell getCell(int x, int y) {
		Cell c;
		if(Math.random() < percentOfMines)
			c = new BlankCell(x,y);
		else
			c = new MineCell(x,y);
		return c;
	}
	
	/**
	 * @param cell
	 */
	public void reveal(Cell cell) {
		
	}
}
