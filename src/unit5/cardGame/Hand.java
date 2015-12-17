package unit5.cardGame;

import java.util.ArrayList;

@SuppressWarnings("serial")
public class Hand extends ArrayList<Card>
{	
	public Hand(Card... cards)
	{
		for(Card c: cards)
			add(c);
	}
}
