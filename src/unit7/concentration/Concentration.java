package unit7.concentration;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

import acm.program.GraphicsProgram;

/**
 * An app for the Concentration game.  This version uses playing cards via the
 * ConcentrationCardModel class.  The game may use images other than cards by
 * extending the ConcentrationModel abstract class with a different implementation.
 * 
 * The GCard class only required a couple of methods to implement the Cell interface.
 * Thus, for the most part, the classes used in earlier playing cards apps were able
 * to be reused without modification.
 * 
 * @author markjones
 *
 */

@SuppressWarnings("serial")
public class Concentration extends GraphicsProgram {
	
	private ConcentrationModel model;

	public static void main(String[] args) {
		new Concentration().start(args);
	}

	public void init() {
		model = new ConcentrationCardModel(this);
		setBackground(Color.LIGHT_GRAY);
		addActionListeners();
		addMouseListeners();
	}

	public void run() {
		model.startGame(4, 4, 4);
	}

	public void actionPerformed(ActionEvent e) {
		
	}

	public void gameStartedNotification(Cell[][] board, int player) {
		int rows = board.length;
		int cols = board[0].length;
		for(int i = 0; i < rows*cols; i++)
			add((GCard)board[i/cols][i%cols], i/cols * model.getCellWidth(), i%cols * model.getCellHeight());
	}

	/**
	 * Tell the model that a cell was chosen.
	 */
	public void mouseClicked(MouseEvent e) {
		
	}

	/**
	 * Turn the cell face up.
	 * @param cell  the chosen cell
	 */
	public void selectCellNotification(Cell cell) {
		
	}

	/**
	 * Update the player's score and prepare to remove the cells.
	 * @param cell1       cell to be removed (after a brief pause)
	 * @param cell2       cell to be removed (after a brief pause)
	 * @param player      the player who scored
	 * @param score       the player's new score     
	 */
	public void removeCellsNotification(Cell cell1, Cell cell2, int player, int score) {
		// set up a timer to generates an event so that after a brief pause, 
		// the cells are removed
		// . . .
	}

	/**
	 * Prepare to turn the selected cells face down again, and pass play to the next player.
	 * @param cell1       cell to be turned over (after a brief pause)
	 * @param cell2       cell to be turned over (after a brief pause)
	 * @param nextPlayer  the next player to play
	 */
	public void deselectCellsNotification(Cell cell1, Cell cell2, int nextPlayer) {
		// set up a timer to generates an event so that after a brief pause, 
		// the cells are turned face down again
		// . . .
	}

	/**
	 * Notification that the game is over, and which players had the highest score.
	 * @param winners    the winning players
	 */
	public void gameOverNotification(int[] winners) {
		// . . .
	}
}