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
		
	}

	/**
	 * Determines whether a hand is a "blackjack" (a two-card hand worth 21).
	 * @return  true if a blackjack hand, false otherwise
	 */
	public boolean isBlackjack() {

	}
}
