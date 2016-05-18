package unit11.minesweeper;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import acm.program.GraphicsProgram;

/**
 * The classic Minesweeper game, (c) Microsoft.
 * @author Mark Jones
 *
 */
@SuppressWarnings("serial")
public class Minesweeper extends GraphicsProgram {
	/**
	 * Runs Minesweeper as an application.
	 * @param args	none are expected
	 */
	public static void main(String[] args) {
		(new Minesweeper()).start(args);
	}
	
	/**
	 * A Board of cells.
	 */
	private Board board;
	
	/**
	 * 0 for easy, 1 for normal, 2 for hard.
	 */
	private int difficulty;
	
	/**
	 * Create a Minesweeper game.
	 */
	public Minesweeper(){}
	
	/**
	 * Initializes the board, which is also the GUI.
	 */
	public void init() {
		board = new Board(5,5);
		JRadioButton easy = new JRadioButton("Easy");
		JRadioButton normal = new JRadioButton("Normal");
		JRadioButton hard = new JRadioButton("Hard");
		ButtonGroup difficulties = new ButtonGroup();
		difficulties.add(easy);
		difficulties.add(normal);
		difficulties.add(hard);
		add(easy,WEST);
		add(normal,WEST);
		add(hard,WEST);
		add(board);
	}
	
	/**
	 * Reveal a cell on a mouseclick.
	 */
	public void mouseClicked(MouseEvent e) {
		Cell cell = (Cell) e.getSource();
		board.reveal(cell);
	}
	
	/** 
	 * Handler for button actions.
	 */
	public void actionPerformed(ActionEvent e) {
		// complete the code
	}
}
