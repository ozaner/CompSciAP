package unit5.cardGame;

import acm.graphics.GCompound;
import acm.graphics.GImage;

/** 
 * Models a (graphical) playing card.  This model includes not only the 
 * rank and suit, but also the graphical representation of the card.
 * 
 * @author Dr. Mark A. Jones
 */
@SuppressWarnings("serial")
public class GCard extends GCompound implements Card
{	
	private Rank rank;
	private Suit suit;
	private boolean faceUp;
	private GImage faceUpImage;
	private static final GImage FACE_DOWN_IMAGE = new GImage("back-blue-75-1.png");
	
	/**
	 * Create a playing card with a given rank and suit.
	 * @param rank    the rank
	 * @param suit    the suit
	 */
	public GCard(Rank rank, Suit suit)
	{
		this.rank = rank;
		this.suit = suit;
	}
	
	/**
	 * Get the rank of the card.
	 * @return   the rank
	 */
	public Rank getRank()
	{
		return rank;
	}
	
	/**
	 * Get the suit of the card.
	 * @return   the suit
	 */
	public Suit getSuit()
	{
		return suit;
	}
	
	/**
	 * A card as a string.
	 * @return  the card as a string
	 */
	public String toString()
	{
		return String.format("Rank: %s, Suit: %s", getRank(),getSuit());
	}
	
	/**
	 * Is the card face up?
	 * @return  true if the card is face up
	 */
	public boolean isFaceUp()
	{
		return faceUp;
	}
	
	/**
	 * Turn the card face up.
	 * @return the card
	 */
	public GCard turnFaceUp()
	{
		faceUp = true;
		return this;
	}
	
	/**
	 * Turn the card face down.
	 * @return the card
	 */
	public GCard turnFaceDown()
	{
		faceUp = true;
		return this;
	}
	
	/**
	 * Flip the card over.
	 * @return the card
	 */
	public GCard flipOver()
	{
		faceUp = !faceUp;
		return this;
	}
	
	/**
	 * Convenience method for making a deck of GCards.
	 * @return        the new deck
	 */
	public static Deck makeDeck()
	{
		return new Deck();
	}
	
	/**
	 * Get the width for any playing card.
	 * @return  the width
	 */
	public static double cardWidth()
	{
		return FACE_DOWN_IMAGE.getWidth();
	}
	
	/**
	 * Get the height for any playing card.
	 * @return  the height
	 */
	public static double cardHeight()
	{
		return FACE_DOWN_IMAGE.getHeight();
	}
	
	/**
	 * Get the back image for any playing card.
	 * @return  the back image
	 */
	public static GImage getBackImage()
	{
		return FACE_DOWN_IMAGE;
	}
}
