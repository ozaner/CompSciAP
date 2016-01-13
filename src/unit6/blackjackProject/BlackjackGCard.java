package unit6.blackjackProject;
/**
 * Models a blackjack card.  The value of a blackjack card is computed.
 * @author Mark Jones
 */
@SuppressWarnings("serial")
public class BlackjackGCard extends GCard {

	/**
	 * The highest possible value of an ace.
	 */
	public static int HIGH_ACE_VALUE = 11;
	
	/**
	 * The lowest possible value for an ace.
	 */
	public static int LOW_ACE_VALUE = 1;
	
	/**
	 * The value of all face cards (jack, queen, king)
	 */
	public static int FACE_VALUE = 10;
	
	/**
	 * Creates a new blackjack card.
	 * @param r - The rank of the card.
	 * @param s - The suit of the card.
	 */
	public BlackjackGCard(Rank r, Suit s) {
		super(r, s);
	}
	
	/**
	 * Convenience method for making a deck of BlackjackGCards.
	 * @return        the new deck
	 */
	public static Deck makeDeck() {
		Deck deck = new Deck();
		for (Suit s : Suit.values()) {
			for (Rank r : Rank.values()) {
				deck.add((Card) new BlackjackGCard(r, s));
			}
		}
		return deck;
	}
	
	/**
	 * The value of a Blackjack card, counting an ACE as 1.
	 */
	public int value() {
		String rank = getRank().toString();
		if(rank.matches("j|q|k")) return FACE_VALUE;
		else if(rank.equals("a")) return LOW_ACE_VALUE;
		else return Integer.parseInt(rank);
	}
}
