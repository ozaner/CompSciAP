package unit7.concentration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ListIterator;

/**
 * The generic model of a game of Concentration with,<br>
 * a table of {@link Cell}s, multiple {@link #players},<br>
 * {@link #scores}.<br>
 * @author Ozaner Hansha
 */
public abstract class ConcentrationModel {
	
	/**
	 * A reference to this {@link ConcentrationModel}'s
	 * Corresponding View for callbacks.
	 */
	private Concentration app;
	
	/**
	 * 1 of 3 basic settings for a game.
	 */
	private int rows, cols, players;
	
	/**
	 * Keeps track of which player's turn it is.
	 */
	private int currentPlayer;
	
	/**
	 * A 2D array of cells representing
	 * the concentration board.
	 */
	private Cell[][] board;
	
	/**
	 * An array of the scores of the players.
	 */
	private int[] scores;
	
	/**
	 * How many cards have been removed (matched) so far.
	 */
	private int matchedCards;

	
	/**
	 * A buffer for the 2 currently chosen cells.
	 */
	private Cell[] currentCells = new Cell[2];

	/**
	 * Constructs a new model with a view reference.
	 * @param app - View for callbacks
	 */
	public ConcentrationModel(Concentration app) {
		this.app = app;
	}
	
	/**
	 * @return The width in pixels of any given cell.
	 */
	public abstract double getCellWidth();
	
	/**
	 * @return The height in pixels of any given cell.
	 */
	public abstract double getCellHeight();
	
	/**
	 * @return A listIterator of the Cell objects to be placed on the board.
	 */
	public abstract ListIterator<Cell> getCellIterator();
	
	/**
	 * Starts a new round by resetting all old values,<br>
	 * creating a new board and sending it to the {@link #app}.
	 * @param rows - Rows of board.
	 * @param cols - Columns of board.
	 * @param players - How many players.
	 */
	public void startGame(int rows, int cols, int players) {
		this.rows = rows;
		this.cols = cols;
		this.players = players;
		currentPlayer = 1;
		matchedCards = 0;
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
	
	/**
	 * Increases the {@link #matchedCards} and {@link #scores}
	 * of a player by 2,<br> informs {@link #app} and calls 
	 * {@link #gameOver()} if all matches found.
	 */
	public void match() {
		scores[currentPlayer]+=2;
		matchedCards+=2;
		app.removeCellsNotification(currentCells[0],currentCells[1],currentPlayer,scores[currentPlayer]);
		currentCells = new Cell[2]; //Resets cell buffer
		if(matchedCards >= rows*cols)
			gameOver();
	}
	
	/**
	 * Called when all matches are done.<br>
	 * Finds the list of all winners and sends it back to the {@link #app}.
	 * @see #match()
	 */
	public void gameOver() {
		ArrayList<Integer> winners = new ArrayList<Integer>();
		int max = scores[0];
		for(int i = 0; i < scores.length; i++) {
			if(scores[i] > max) {
				max = scores[i];
				winners.clear();
				winners.add(i);
			}
			else if(scores[i] == max) winners.add(i);
		}
		app.gameOverNotification(winners);
	}
}
