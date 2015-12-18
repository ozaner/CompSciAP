package unit5.cardGame;

/**
 * The constants corresponding the {@link Card} ranks.
 * @author Ozaner Hansha
 */
public enum Rank
{
	DEUCE("2"),THREE("3"),FOUR("4"),FIVE("5"),SIX("6"),SEVEN("7"),EIGHT("8"),
	NINE("9"),TEN("10"),JACK("j"),QUEEN("q"),KING("k"),ACE("a");
	
	/**
	 * The rank of this card.
	 */
	private String rank;
	
	/**
	 * Constructs a new card with a given rank.
	 * @param r - The rank.
	 */
	private Rank(String r)
	{
		rank = r;
	}
	
	/**
	 * Returns the {@link #rank} of this card.
	 */
	public String toString()
	{
		return rank;
	}
}
