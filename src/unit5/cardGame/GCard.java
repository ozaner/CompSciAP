package unit5.cardGame;

import java.util.ArrayList;

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
	/**
	 * Rank of the card. Initialized in constructor.
	 */
	private Rank rank;
	
	/**
	 * Suit of the card. Initialized in constructor.
	 */
	private Suit suit;
	
	/**
	 * Flip status. Default to face down.
	 */
	private boolean faceUp;
	
	/**
	 * Image on front of card. Initialized in constructor.
	 */
	private GImage faceUpImage;
	
	/**
	 * Face down image of all cards.
	 */
	private static final GImage FACE_DOWN_IMAGE = new GImage("back-blue-75-1.png");
	
	/**
	 * Create a playing card with a given rank and suit.
	 * @param rank - the rank
	 * @param suit - the suit
	 */
	public GCard(Rank rank, Suit suit)
	{
		this.rank = rank;
		this.suit = suit;
		faceUpImage = new GImage(String.format("%s-%s-75.png", suit.toString(), rank.toString()));
		
		add(FACE_DOWN_IMAGE); //This is below
		add(faceUpImage); //This is on top.
		turnFaceDown(); //Starts face down.
	}
	
	/**
	 * Get the rank of the card.
	 * @return the rank
	 */
	@Override
	public Rank getRank()
	{
		return rank;
	}
	
	/**
	 * Get the suit of the card.
	 * @return the suit
	 */
	@Override
	public Suit getSuit()
	{
		return suit;
	}
	
	/**
	 * A card as a string.
	 * @return the card as a string
	 */
	@Override
	public String toString()
	{
		return String.format("Rank: %s, Suit: %s", getRank(), getSuit());
	}
	
	/**
	 * Is the card face up?
	 * @return true if the card is face up
	 */
	@Override
	public boolean isFaceUp()
	{
		return faceUp;
	}
	
	/**
	 * Turn the card face up.
	 * @return the card
	 */
	@Override
	public GCard turnFaceUp()
	{
		faceUp = true;
		faceUpImage.setVisible(true);
		return this;
	}
	
	/**
	 * Turn the card face down.
	 * @return the card
	 */
	@Override
	public GCard turnFaceDown()
	{
		faceUp = false;
		faceUpImage.setVisible(false);
		return this;
	}
	
	/**
	 * Flip the card over.
	 * @return the card
	 */
	@Override
	public GCard flipOver()
	{
		faceUp = !faceUp;
		faceUpImage.setVisible(faceUp);
		return this;
	}
	
	/**
	 * Convenience method for making a deck of GCards.
	 * @return the new deck
	 */
	public static Deck makeDeck()
	{
		ArrayList<Card> cards = new ArrayList<Card>();
		for(Suit s: Suit.values())
		{
			for(Rank r: Rank.values())
			{
				cards.add(new GCard(r,s));
			}
		}
		Card[] temp = new Card[52];
		return new Deck(cards.toArray(temp));
	}
	
	/**
	 * Get the width for any playing card.
	 * @return the width
	 */
	public static double cardWidth()
	{
		return FACE_DOWN_IMAGE.getWidth();
	}
	
	/**
	 * Get the height for any playing card.
	 * @return the height
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
