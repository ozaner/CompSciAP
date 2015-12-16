package unit5.cardGame;

import java.util.ArrayList;

/** 
 * Models a standard deck of playing cards.
 * @author Dr. Mark A. Jones
 */

@SuppressWarnings("serial")
public class Deck extends ArrayList<Card> {
	
	private ArrayList<Card> deck = new ArrayList<Card>();
	
	/** Make a new deck of cards. */
	public Deck()
	{
		for(Suit s: Suit.values())
		{
			for(Rank r: Rank.values())
			{
				add(new GCard(r,s));
			}
		}
	}
	
	/**
	 * Deal a card.
	 * @return  the top card from the deck
	 */
	public Card deal()
	{
		
	}
	
	/** Shuffle the remaining cards in the deck. */
	public void shuffle() {
		// complete
	}
}
