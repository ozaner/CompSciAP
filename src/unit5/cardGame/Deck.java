package unit5.cardGame;

import java.util.ArrayList;
import java.util.Collections;

/** 
 * Models a standard deck of playing cards.
 * @author Dr. Mark A. Jones
 */
@SuppressWarnings("serial")
public class Deck extends ArrayList<Card>
{	
	/**
	 * Make a new deck of 52 cards.
	 */
	public Deck(Card... cards)
	{
		for(Card c: cards)
			add(c);
	}
	
	/**
	 * Deal a card. 
	 * @return the top card from the deck, or null if empty.
	 */
	public Card deal()
	{		
		return size() != 0 ? remove(size()-1) : null;
	}
	
	/**
	 * Shuffle the remaining cards in the deck.
	 */
	public void shuffle()
	{
		Collections.shuffle(this);
	}
}
