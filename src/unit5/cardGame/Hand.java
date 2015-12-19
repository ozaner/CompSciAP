package unit5.cardGame;

import java.util.ArrayList;

/**
 * Models a hand of an arbitrary amount of {@link GCards}.
 * @author Ozaner Hansha
 */
@SuppressWarnings("serial")
public class Hand extends ArrayList<Card>
{	
	/**
	 * Make a new hand of an arbitrary amount cards.
	 */
	public Hand(Card... cards)
	{
		for(Card c: cards)
			add(c);
	}
}
