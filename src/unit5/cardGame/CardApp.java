package unit5.cardGame;

import java.awt.Dimension;
import java.awt.event.MouseEvent;
import acm.graphics.GPoint;
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
	private static final double CARD_Y = INITIAL_DIM.getHeight()*.5 - GCard.cardHeight();
	private static final double CARD_SPACE_FACTOR = 1;
	
	private static Deck deck;
	private static int DECK_HEIGHT; //Keeps track of height of deck on canvas.
	
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
		
		deck = GCard.makeDeck(); //Makes the 52 card deck
		deck.shuffle(); //Shuffles the deck

		//Adds all cards in deck to canvas.
		for(Card c: deck)
		{
			add((GCard)c, APP_WIDTH_3X + DECK_HEIGHT*CARD_SPACE_FACTOR, CARD_Y - DECK_HEIGHT*CARD_SPACE_FACTOR);
			DECK_HEIGHT++;
		}
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
		double cardSpace = DECK_HEIGHT*CARD_SPACE_FACTOR;
		GPoint pilePoint = new GPoint(APP_WIDTH_6X - cardSpace, CARD_Y + cardSpace);
		GPoint deckPoint = new GPoint(APP_WIDTH_3X + cardSpace, CARD_Y - cardSpace);
	
		//Sets obj equal to the top card in the deck/pile
		GCard obj = (GCard)getElementAt(e.getX(),e.getY());
		if(obj != null) //Checks to see if a card was clicked.
		{
			if(obj.getX() < INITIAL_DIM.getWidth()*.5) //If this card is on the deck.
			{
				obj = (GCard)deck.get(DECK_HEIGHT-1); //The top card of the deck
				obj.setLocation(pilePoint);
				DECK_HEIGHT--;
			}
			else //Card must be on pile
			{
				obj = (GCard)deck.get(DECK_HEIGHT); //The top card of the pile
				obj.setLocation(deckPoint);
				DECK_HEIGHT++;
			}
			obj.flipOver();
			obj.sendToFront();
		}
	}
}