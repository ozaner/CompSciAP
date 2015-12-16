package unit5.cardGame;

import java.awt.event.MouseEvent;
import acm.program.GraphicsProgram;

@SuppressWarnings("serial")
public class CardApp extends GraphicsProgram {
	
	private static final int INITIAL_WIDTH = 700;
	private static final int INITIAL_HEIGHT = 400;
	
	// complete
	
	public static void main(String[] args) {
		new CardApp().start(args);
	}

	/**
	 * Make a deck of cards and place them face down on the canvas (in a pile) slightly left of center.
	 */
	@Override
	public void init() {
		setSize(INITIAL_WIDTH, INITIAL_HEIGHT);
		
		// complete
	}
	
	/**
	 * On a mouse click over the deck, remove the top card (if one exists), flip it over face up and place 
	 *    it in a discard pile slightly right of center.
	 * On a mouse click over the discard pile (if one exists), return the top card, flip it over face down 
	 *    and return in to the deck pile.
	 * Ignore any other mouse clicks that are not over the deck or the discard pile.
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		// complete
	}
}
