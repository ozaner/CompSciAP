package unit7.concentration;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import javax.swing.Timer;
import acm.graphics.GPoint;
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
 * @author Ozaner Hansha
 * Dr. Jones<br>
 * AP Computer Science<br>
 * February 25th, 2016<br>
 */

@SuppressWarnings("serial")
public class Concentration extends GraphicsProgram {
	
	/**
	 * The amount of time to memorize the cells (in milliseconds).
	 * @see #cellTimer
	 */
	private static final int CELL_TIME = 1000;
	
	/**
	 * A reference to the {@link ConcentrationCardModel} for callbacks.
	 */
	private ConcentrationModel model;

	/**
	 * The main method, starts graphics program
	 * @param args - Not expecting any args.
	 */
	public static void main(String[] args) {
		new Concentration().start(args);
	}

	/**
	 * Initializes GUI and adds event listeners.
	 * @see acm.program.GraphicsProgram#init()
	 */
	public void init() {
		model = new ConcentrationCardModel(this);
		setBackground(Color.LIGHT_GRAY);
		addActionListeners();
		addMouseListeners();
	}

	public void run() {
		model.startGame(8, 4, 4);
	}

	/**
	 * Handles GUI input.
	 * @see acm.program.Program#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e) {
		
	}

	/**
	 * Adds the board and adds player scores to the GUI.
	 * @param board - 2D Array of cells.
	 * @param player - number of players.
	 */
	public void gameStartedNotification(Cell[][] board, int player) {
		int rows = board.length;
		int cols = board[0].length;
		for(int i = 0; i < rows*cols; i++)
			add((GCard)board[i/cols][i%cols], i/cols * model.getCellWidth(), i%cols * model.getCellHeight());
	}

	/**
	 * Checks to see if a cell was chosen and informs {@link #model}.
	 */
	public void mouseClicked(MouseEvent e) {
		Object obj = getElementAt(new GPoint(e.getPoint()));
		if(obj instanceof GCard)
			model.choose((Cell)obj);
	}

	/**
	 * Turn the cell face up.
	 * @param cell  the chosen cell
	 */
	public void selectCellNotification(Cell cell) {
		cell.turnFaceUp();
	}

	/**
	 * Update the player's score and prepare to remove the cells.
	 * @param cell1       cell to be removed (after a brief pause)
	 * @param cell2       cell to be removed (after a brief pause)
	 * @param player      the player who scored
	 * @param score       the player's new score     
	 */
	public void removeCellsNotification(Cell cell1, Cell cell2, int player, int score) {
		new Timer(CELL_TIME, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				remove((GCard)cell1);
				remove((GCard)cell2);
				((Timer)e.getSource()).stop();
			}	
		}).start();
	}

	/**
	 * Prepare to turn the selected cells face down again, and pass play to the next player.
	 * @param cell1       cell to be turned over (after a brief pause)
	 * @param cell2       cell to be turned over (after a brief pause)
	 * @param nextPlayer  the next player to play
	 */
	public void deselectCellsNotification(Cell cell1, Cell cell2, int nextPlayer) {
		
		new Timer(CELL_TIME, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cell1.turnFaceDown();
				cell2.turnFaceDown();
				((Timer)e.getSource()).stop();
			}	
		}).start();
	}
	
	/**
	 * Notification that the game is over, and which players had the highest score.
	 * @param winners    the winning players
	 */
	public void gameOverNotification(int[] winners) {
		// . . .
	}
}