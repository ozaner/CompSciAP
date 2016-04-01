package unit9;

public class HangmanModel {
	
	/**
	 * Regular expression for Alphabetic characters.
	 */
	public static String VALID_CHARACTERS = "[a-zA-Z]";
	
	/**
	 * Reference to corresponding {@link HangmanView}.
	 */
	private HangmanView view;
	
	/**
	 * Player's score this round.
	 */
	private int score;
	
	/**
	 * The current word in play.
	 */
	private String currentWord;
	
	/**
	 * An array of guessed letters.
	 */
	private char[] guessedLetters;
	
	/**
	 * Constructor for {@link HangmanModel}.
	 * @param view - A corresponding {@link HangmanView}.
	 */
	public HangmanModel(HangmanView view) {
		this.view = view;
	}
	
	/**
	 * Checks whether a guess is valid (i.e alphabetic).
	 * @param c -  a character.
	 * @return The validity of a guess.
	 */
	public boolean isValidGuess(char c) {
		return (""+c).matches(VALID_CHARACTERS);
	}
}