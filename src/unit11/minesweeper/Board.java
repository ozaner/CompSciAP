package unit11.minesweeper;

import acm.graphics.GCompound;

public class Board extends GCompound{
	
	private Cell[][] board;
	
	private int amountOfMines;
	
	public Board(int r, int c, int amountOfMines) {
		this.amountOfMines = amountOfMines;
		board = new Cell[r][c];
		populateBoard(r,c); //Populate board with cells
		
		//Add Cells to GComound
		for(int x = 0; x < r; x++)
			for(int y = 0; y < c; y++)
				add(board[x][y],x*Cell.CELL_WIDTH,y*Cell.CELL_HEIGHT);
	}
	
	private Cell populateBoard(int r, int c) {
		int minesLeft = amountOfMines;
		while(amountOfMines > 0) {
			for(int x = 0; x < r; x++)
				for(int y = 0; y < c; y++)
					if(Math.random() > .5 && amountOfMines > 0) {
						board[x][y] = new MineCell(x,y);
						amountOfMines--;
					}
					else
		}
	}
	
	/**
	 * @param cell
	 */
	public void reveal(Cell cell) {
		
	}
}
