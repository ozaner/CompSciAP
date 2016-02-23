package unit7.concentration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.ListIterator;

public abstract class ConcentrationModel {

	private Concentration app;        // for callbacks to the view
	private int rows, cols, players;  // basic configuration for the current game
	private Cell[][] board;           // 2D layout of the cells in the game
	private int[] scores;

	public ConcentrationModel(Concentration app) {
		this.app = app;
	}
	
	public abstract double getCellWidth();
	public abstract double getCellHeight();
	public abstract ListIterator<Cell> getCellIterator();
	
	public void startGame(int rows, int cols, int players) {
		this.rows = rows;
		this.cols = cols;
		this.players = players;
		
		//Creates and fills board
		board = new Cell[rows][cols];
		fillBoard();
		
		scores = new int[players]; //Init Scores
		app.gameStartedNotification(board, players); //Starts game
	}
	
	/**
	 * Fills the 2D board with Cells.  At least one of rows and cols must be even.
	 */
	private void fillBoard() {
		int numCellsNeeded = rows * cols;
		ArrayList<Cell> cells = new ArrayList<Cell>(numCellsNeeded);
		ListIterator<Cell> cellIterator = null;

		// create a list of the cells to use for our board
		// we use the abstract method getCellIterator() to generate the cells
		while (cells.size() < numCellsNeeded) {
			cellIterator = getCellIterator();  // may get called again if we run out of cells
			// get the next cell, as long as we still need one and it is available
			for (Cell cell = cellIterator.next();                         
					cells.size() < numCellsNeeded && cellIterator.hasNext();
					cell = cellIterator.next()) {
				// add the cell and a copy of it to the list of cells to use for our board
				cells.add(cell);
				cells.add(cell.copy());
			}
		}
		Collections.shuffle(cells); // randomize the list
		
		for(int i = 0; i < numCellsNeeded; i++)
			board[i/cols][i%cols] = cellIterator.next();
	}

	/** 
	 * Handles the action of a user choosing a cell.
	 * @param cell  the chosen cell
	 */
	public void choose(Cell cell) {
		
	}
}
