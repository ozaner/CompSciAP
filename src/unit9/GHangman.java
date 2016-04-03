package unit9;

import acm.graphics.GCompound;
import acm.graphics.GImage;

/**
 * @author Ozaner Hansha
 *
 */
public class GHangman extends GCompound implements ProgressivelyDrawable {

	/**
	 * Number of parts this object has.
	 */
	public static final int MAX_PARTS = 7;
	
	/**
	 * Array of Hangman images used.
	 */
	public static final GImage[] HANGMAN_IMAGES = new GImage[7];
	static { //Initializes HANGMAN_IMAGES
		for(int x = 0; x < MAX_PARTS; x++) {
			HANGMAN_IMAGES[x] = new GImage("src/unit9/hangman" + x + ".png");
		}
	}
	
	/**
	 * The current index of the compound.
	 */
	private int currentPart = 1;
	
	/**
	 * Assembles this object.
	 */
	public GHangman() {
		for(GImage g: HANGMAN_IMAGES) {
			add(g);
		}
		scale(.5);
	}
	
	/**
	 * Draws the next part in this object.
	 */
	@Override
	public void drawNextPart() {
		if(currentPart < MAX_PARTS) {
			HANGMAN_IMAGES[currentPart].setVisible(true);
			currentPart++;
		}
	}

	/**
	 * Resets this object.
	 */
	@Override
	public void reset() {
		currentPart = 1;
		for(GImage g: HANGMAN_IMAGES) {
			g.setVisible(false);
		}
		HANGMAN_IMAGES[0].setVisible(true);
	}

	/**
	 * Returns the max amount of parts this object contains.
	 */
	@Override
	public int getMaxParts() {
		return MAX_PARTS;
	}
	
}
