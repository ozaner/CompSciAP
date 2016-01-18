package unit6.blackjackProject;
/**
 * A BlackjackHand is a Hand with BlackjackCards in it.
 * This is simpler than parameterizing Hand<BlackjackCard>.
 * A BlackjackHand knows how to value itself, determine if
 * a hand is a blackjack, etc.
 * 
 * @author Dr. Mark A. Jones
 */
@SuppressWarnings("serial")
public class BlackjackHand extends Hand {

	public static int BLACKJACK_VALUE = 21;
	
	/**
	 * Creates a BlackjackHand.
	 */
	public BlackjackHand() {
		super();
	}
	
	/**
	 * Returns the highest legal value for a blackjack hand.
	 * @return
	 */
	public int handValue()
	{
		int value = 0;
		int highAces = 0;
		
		//Adds values of the entire hand.
		for(Card g: this)
		{
			//If card is an ace add its high value, else use normal value.
			if(g.getRank().toString() == "a")
			{
				value += BlackjackGCard.HIGH_ACE_VALUE;
				highAces++;
			}
			else
				value += ((BlackjackGCard)g).value();
		}
		
		//If the value is over 21 and there are still high aces.
		//Set those high aces to low ones.
		while(value > 21 && highAces > 0)
		{
			value += BlackjackGCard.LOW_ACE_VALUE - BlackjackGCard.HIGH_ACE_VALUE;
			highAces--;
		}
		
		return value;
	}

	/**
	 * Determines whether a hand is a "blackjack" (a two-card hand worth 21).
	 * @return  true if a blackjack hand, false otherwise
	 */
	public boolean isBlackjack() {
		return size() == 2 && handValue() == BLACKJACK_VALUE;
	}
}
