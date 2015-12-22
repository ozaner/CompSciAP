package unit5.cardGame;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import acm.graphics.GLabel;
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
	
	//Deck variables
	private static Deck deck = GCard.makeDeck();
	private static int DECK_HEIGHT; //Keeps track of height of deck on canvas.
	
	//Deck Counter Variables
	private static final String DECK_COUNT_LABEL_STRING = "#Cards in Deck: ";
	private static GLabel DECK_COUNT_LABEL = new GLabel(DECK_COUNT_LABEL_STRING + deck.size());
	private static final double DECK_COUNTER_X = INITIAL_DIM.getWidth()*.3;
	private static final double DECK_COUNTER_Y = INITIAL_DIM.getHeight()*.6;
	
	//Pile Counter Variables
	private static final String PILE_COUNT_LABEL_STRING = "#Cards in Pile: ";
	private static GLabel PILE_COUNT_LABEL = new GLabel(PILE_COUNT_LABEL_STRING + 0);
	private static final double PILE_COUNTER_X = INITIAL_DIM.getWidth()*.55;
	private static final double PILE_COUNTER_Y = INITIAL_DIM.getHeight()*.15;
	
	private static final Font COUNTER_FONT = new Font("Ariel", 0, 15);
	
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
		deck.shuffle(); //Shuffles the deck
		
		//Adds all cards in deck to canvas.
		for(Card c: deck)
		{
			add((GCard)c, APP_WIDTH_3X + DECK_HEIGHT, CARD_Y - DECK_HEIGHT);
			DECK_HEIGHT++;
		}
		DECK_COUNT_LABEL.setFont(COUNTER_FONT);
		PILE_COUNT_LABEL.setFont(COUNTER_FONT);
		add(DECK_COUNT_LABEL,DECK_COUNTER_X,DECK_COUNTER_Y); //Adds deck counter to canvas
		add(PILE_COUNT_LABEL,PILE_COUNTER_X,PILE_COUNTER_Y); //Adds pile counter to canvas
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
		//Sets obj equal to the top card in the deck/pile
		GCard obj = (GCard)getElementAt(e.getX(),e.getY());
		if(obj != null) //Checks to see if a card was clicked.
		{
			if(DECK_HEIGHT > deck.indexOf(obj)) //If this card is on the deck.
			{
				obj = (GCard)deck.get(DECK_HEIGHT-1); //The top card of the deck
				obj.setLocation(APP_WIDTH_6X - DECK_HEIGHT, CARD_Y + DECK_HEIGHT); //Right side of screen.
				DECK_HEIGHT--;
			}
			else //Card must be on pile
			{
				obj = (GCard)deck.get(DECK_HEIGHT); //The top card of the pile
				obj.setLocation(APP_WIDTH_3X + DECK_HEIGHT, CARD_Y - DECK_HEIGHT); //Left side of screen.
				DECK_HEIGHT++;
			}
			obj.flipOver();
			obj.sendToFront();
			DECK_COUNT_LABEL.setLabel(DECK_COUNT_LABEL_STRING + DECK_HEIGHT);
			PILE_COUNT_LABEL.setLabel(PILE_COUNT_LABEL_STRING + (deck.size() - DECK_HEIGHT));
		}
	}
}