package unit5.cardGame;

import java.awt.Dimension;
import java.awt.event.MouseEvent;

import acm.program.GraphicsProgram;

@SuppressWarnings("serial")
public class CardApp extends GraphicsProgram
{
	private static final Dimension INITIAL_DIM = new Dimension(700,400);
	private static Deck deck;
	
	private static final double APP_WIDTH_3X = INITIAL_DIM.getWidth()*.3;
	private static final double APP_WIDTH_6X = INITIAL_DIM.getWidth()*.6;
	private static final double APP_HEIGHT_5X = INITIAL_DIM.getHeight()*.5;
	private static final double CARD_HEIGHT = GCard.cardHeight();
	
	public static void main(String[] args)
	{
		new CardApp().start(args);
	}

	/**
	 * Make a deck of cards and place them face down on the canvas (in a pile) slightly left of center.
	 */
	@Override
	public void init()
	{
		setSize(INITIAL_DIM);
		addMouseListeners();
		
		deck = GCard.makeDeck();
		System.out.println(deck);
		
		for(int i = deck.size(); i > 0; i--)
			add((GCard)deck.deal(), APP_WIDTH_3X, APP_HEIGHT_5X - CARD_HEIGHT);
	}
	
	/**
	 * On a mouse click over the deck, remove the top card (if one exists), flip it over face up and place 
	 *    it in a discard pile slightly right of center.
	 * On a mouse click over the discard pile (if one exists), return the top card, flip it over face down 
	 *    and return in to the deck pile.
	 * Ignore any other mouse clicks that are not over the deck or the discard pile.
	 */
	@Override
	public void mouseClicked(MouseEvent e)
	{
		GCard obj = (GCard)getElementAt(e.getX(),e.getY());
		if(obj != null)
			if(obj.isFaceUp())
			{
				obj.turnFaceDown();
				obj.setLocation(APP_WIDTH_3X, APP_HEIGHT_5X - CARD_HEIGHT);
				obj.sendToFront();
			}
			else
			{
				obj.turnFaceUp();
				obj.setLocation(APP_WIDTH_6X, APP_HEIGHT_5X - CARD_HEIGHT);
				obj.sendToFront();
			}
	}
}
