package unit9;

/**
 * Interface for all Hangman games.
 * @author Ozaner Hansha
 */
public interface HangmanView {
	
	/**
	 * Starts the game with the given word.
	 * @param word - current word of the round.
	 */
	void gameStartNotification(String word);
	
	/**
	 * Tells view game has been lost.
	 */
	void gameLostNotification();
	
	/**
	 * Tells view game was won.
	 */
	void gameWonNotification();
	
	/**
	 * Notifies the view of a correct guess.
	 * @param c - the guessed letter.
	 */
	void correctNotification(char c);
	
	/**
	 * Notifies the view of an incorrect guess.
	 * @param c - the guessed letter.
	 */
	void incorrectNotification(char c);
	
	/**
	 * Notifies the view of a letter that has already been guessed.
	 * @param c - the guessed letter.
	 */
	void alreadyGuessedNotification(char c);
}
