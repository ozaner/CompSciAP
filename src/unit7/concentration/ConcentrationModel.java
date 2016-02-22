package unit7.concentration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ListIterator;

public abstract class ConcentrationModel {

	// instance variables
	private Concentration app;        // for callbacks to the view
	private int rows, cols, players;  // basic configuration for the current game
	private Cell[][] board;           // 2D layout of the cells in the game
	
	private int[] scores = new int[4];
	// . . . more instance variables . . .


	public ConcentrationModel(Concentration app) {
		this.app = app;
	}
	
	// abstract methods
	public abstract double getCellWidth();
	public abstract double getCellHeight();
	public abstract ListIterator<Cell> getCellIterator();
	
	public void startGame(int rows, int cols, int players) {
		this.rows = rows;
		this.cols = cols;
		this.players = players;
		
		// create the board and fill it up

		
		// initialize the player scores

		
		// initialize other state variables and notify the GUI that the game has started

	}

	private ArrayList<Cell> getPairs() {
		
	}
	
	/**
	 * Fills the 2D board with Cells.  At least one of rows and cols must be even.
	 */
	private void fillBoard() {
		int numCellsNeeded = rows * cols;
		ArrayList<Cell> cells = new ArrayList<Cell>(numCellsNeeded);
		ListIterator<Cell> cellIterator;

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
		
		// supply code below to assign the cells to the 2D board array
		
	}

	/** 
	 * Handles the action of a user choosing a cell.
	 * @param cell  the chosen cell
	 */
	public void choose(Cell cell) {
	}
}
