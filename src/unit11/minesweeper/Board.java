package unit11.minesweeper;

public class Board {
	
	private Cell[][] board;
	
	private int amountOfMines;
	
	private static final double CHANCE_OF_MINE = .9;
	
	public Board(int r, int c, int amountOfMines) {
		this.amountOfMines = amountOfMines;
		board = new Cell[r][c];
		populateBoard(r,c); //Populate board with cells
	}
	
	/**
	 * Randomly* Fills the board with the {@link #amountOfMines}
	 * specified in the constructor.
	 * @param r
	 * @param c
	 */
	private void populateBoard(int r, int c) {
		int minesLeft = amountOfMines;
		while(minesLeft > 0) {
			for(int x = 0; x < r; x++)
				for(int y = 0; y < c; y++) {
					if(!(getCellAt(x,y) instanceof MineCell))
						if(Math.random() > CHANCE_OF_MINE && minesLeft > 0) {
							board[x][y] = new MineCell(x,y);
							minesLeft--;
						}
						else
							board[x][y] = new BlankCell(x,y);
			}				
		}
	}
	
	/**
	 * @return rows of the board.
	 */
	public int getRows() {
		return board.length;
	}
	
	/**
	 * @return columns of the board.
	 */
	public int getCols() {
		return board[0].length;
	}
	
	/**
	 * @param row
	 * @param col
	 * @return The cell at the row & column, null if invalid row and column.
	 */
	public Cell getCellAt(int row, int col) {
		if(row < getRows() && row >= 0 && col < getCols() && col >= 0)
			return board[row][col];
		return null;
	}
	
	/**
	 * Reveals the given cell if it is in this board.
	 * @param cell
	 */
	public void reveal(Cell cell, boolean firstMine) {
		board[cell.getRow()][cell.getCol()].reveal(this, firstMine);
	}
	
	/**
	 * @return Whether or not all blanks on the board have been revealed.
	 */
	public boolean allBlanksRevealed() {
		for(int x = 0; x < getRows(); x++)
			for(int y = 0; y < getCols(); y++)
				if(getCellAt(x,y) instanceof BlankCell && !getCellAt(x,y).isRevealed())
					return false;
		return true;
	}
	
	/**
	 * Reveals all cells in the board.
	 */
	public void revealAll() {
		for(int x = 0; x < getRows(); x++)
			for(int y = 0; y < getCols(); y++)
				reveal(getCellAt(x,y),false);
	}
}
