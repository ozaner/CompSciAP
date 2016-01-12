package unit6.blackjackProject;
/**
 * Models a blackjack card.  The value of a blackjack card is computed.
 * @author Mark Jones
 */
@SuppressWarnings("serial")
public class BlackjackGCard extends GCard {

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
		if(rank.matches("10|j|q|k")) return 10;
		else if(rank.equals("a")) return 1;
		else return Integer.parseInt(rank);
	}
}
