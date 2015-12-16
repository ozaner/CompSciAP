package unit5.cardGame;

/**
 * The constants corresponding to {@link Card} suits.
 * @author Ozaner Hansha
 */
public enum Suit
{
	CLUBS,DIAMONDS,HEARTS,SPADES;
	
	/**
	 * Returns the name of the suit in lowercase.
	 */
	@Override
	public String toString()
	{
		return name().toLowerCase();
	}
}
