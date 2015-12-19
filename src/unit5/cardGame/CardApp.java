package unit5.cardGame;

import java.awt.Dimension;
import java.awt.event.MouseEvent;

import acm.program.GraphicsProgram;

/**
 * This program places a deck of cards on the screen and when
 * clicked moves the top card to a discard pile. This process can be
 * reversed by clicking on the discard pile.
 * <br><br>
 * Project 6 - CardApp<br>
 * AP Computer Science - Pd. 7<br>
 * Dr. Jones
 * @author Ozaner Hansha
 */
@SuppressWarnings("serial")
public class CardApp extends GraphicsProgram
{
	//Window and size constants.
	private static final Dimension INITIAL_DIM = new Dimension(700,400);
	private static final double APP_WIDTH_3X = INITIAL_DIM.getWidth()*.3;
	private static final double APP_WIDTH_6X = INITIAL_DIM.getWidth()*.6;
	private static final double APP_HEIGHT_5X = INITIAL_DIM.getHeight()*.5;
	private static final double CARD_HEIGHT = GCard.cardHeight();
	
	/**
	 * Starts the CardApp.
	 * @param args - Not expecting any command arguments.
	 */
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
		Deck deck = GCard.makeDeck();
		
		//Deals every card onto a spot (the deck) on the canvas.
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
		GCard obj = (GCard)getElementAt(e.getX(),e.getY()); //Can cast to GCard immediately because all objects on canvas are GCards.
		if(obj != null) //Checks to see if an object was clicked on.
			if(obj.isFaceUp()) //Checks to see if card was part of the discard pile
			{
				obj.turnFaceDown();
				obj.setLocation(APP_WIDTH_3X, APP_HEIGHT_5X - CARD_HEIGHT);
				obj.sendToFront();
			}
			else //GCard must be part of deck
			{
				obj.turnFaceUp();
				obj.setLocation(APP_WIDTH_6X, APP_HEIGHT_5X - CARD_HEIGHT);
				obj.sendToFront();
			}
	}
}
