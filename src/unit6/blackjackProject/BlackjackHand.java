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
	public int handValue() {
		int value = 0;
		int aces = 0;
		
		//Adds values of non-aces.
		for(Card g: this)
		{
			//If card is not an ace add its value to the count.
			if(g.getRank().toString() == "a")
				aces++;
			else
				value += ((BlackjackGCard)g).value();
		}
		
		//Adds values of aces.
		for(int x = 0; x < aces; x++)
		{
			if(value <= BLACKJACK_VALUE - BlackjackGCard.HIGH_ACE_VALUE)
				value += BlackjackGCard.HIGH_ACE_VALUE;
			else
				value += BlackjackGCard.LOW_ACE_VALUE;
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
