package unit7.concentration;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ListIterator;

/**
 * The generic model of a game of Concentration with,<br>
 * a table of {@link Cell}s, multiple {@link #players},<br>
 * {@link #scores}, and {@link #MEMORY_TIME}.<br>
 * @author Ozaner Hansha
 */
public abstract class ConcentrationModel {
	
	private Concentration app;        // for callbacks to the view
	private int rows, cols, players;  // basic configuration for the current game
	private int currentPlayer;
	private Cell[][] board;           // 2D layout of the cells in the game
	private int[] scores;

	
	/**
	 * A buffer for the 2 currently chosen cells.
	 */
	private Cell[] currentCells = new Cell[2];

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
		scores = new int[players+1]; //Init Scores
		
		//Creates and fills board
		board = new Cell[rows][cols];
		fillBoard();
		
		app.gameStartedNotification(board, players); //Starts game
	}
	
	/**
	 * Changes {@link #currentPlayer} to the next player.
	 * @return The currentPlayer
	 */
	public int nextPlayer() {
		if(currentPlayer < players)
			currentPlayer++;
		else
			currentPlayer = 1;
		return currentPlayer;
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
			board[i/cols][i%cols] = cells.get(i);
	}

	/** 
	 * Handles the action of a user choosing a cell.
	 * @param cell  the chosen cell
	 */
	public void choose(Cell cell) {
		if(currentCells[0] == null) {
			currentCells[0] = cell;
			app.selectCellNotification(cell);
		}
		else if(currentCells[1] == null && cell != currentCells[0]){
			currentCells[1] = cell;
			app.selectCellNotification(cell);
			if(cell.equals(currentCells[0]))
				match();
			else {
				app.deselectCellsNotification(currentCells[0], currentCells[1], nextPlayer());
				currentCells = new Cell[2];
			}
		}	
	}
	
	public void match() {
		app.removeCellsNotification(currentCells[0],currentCells[1],currentPlayer,scores[currentPlayer]);
		currentCells = new Cell[2]; //Resets cell buffer
	}
}
