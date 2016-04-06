package unit9;

import java.util.HashSet;
import java.util.Set;

/**
 * Model for {@link Hangman} game. Stores state of game
 * & notifies {@link HangmanView} of events.
 * @author Ozaner Hansha
 */
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
	 * Amount of guesses until loss.
	 */
	private int maxGuesses, guesses;
	
	/**
	 * Used to obtain a random word.
	 */
//	private Dictionary dictionary = new Dictionary("src/unit9/yawl.txt");
	private Dictionary dictionary = new Dictionary();
	
	/**
	 * The current word in play.
	 */
	private String currentWord;
	
	/**
	 * A set of guessed letters.
	 */
	private Set<Character> guessedLetters = new HashSet<Character>();
	
	/**
	 * Whether the game is over or not.
	 */
	private boolean gameOver = false;
	
	/**
	 * Constructor for {@link HangmanModel}.
	 * @param view - A corresponding {@link HangmanView}.
	 */
	public HangmanModel(HangmanView view, int maxGuesses) {
		this.view = view;
		this.maxGuesses = maxGuesses;
	}
	
	/**
	 * Resets variables and starts new round.
	 */
	public void newRound() {
		guesses = 0;
		guessedLetters.clear();
		gameOver = false;
		view.gameLostNotification();
		do {
			currentWord = dictionary.getRandomWord().toUpperCase();
		} while(currentWord.length() > 11);
		view.gameStartNotification(currentWord);
	}
	
	/**
	 * Returns whether or not the game has been won.
	 * @return - Win status of the game.
	 */
	public boolean gameWon() {
		//If the set of characters in the currentWord is a subset of the guessed letters.
		//Then game has been won.
		Set<Character> s = new HashSet<Character>();
		for(char c: currentWord.toCharArray()) {
				s.add(c);
		}
		s.remove(' ');
		return guessedLetters.containsAll(s);
	}
	
	/**
	 * Checks if guess is valid and guesses if so.
	 * Ends game if guesses exceed {@link #maxGuesses} or {@link #gameWon()} is true.
	 * @param c - Letter being guessed.
	 */
	public void guess(char c) {
		if(!gameOver) {
			if(!guessedLetters.contains(c)) {
				if(isValidGuess(c)) {
					guessedLetters.add(c);
					if(currentWord.indexOf(c) != -1) {
						view.correctNotification(c);
						if(gameWon()) {
							view.gameWonNotification();
							gameOver = true;
						}
					}
					else {
						guesses++;
						if(guesses >= maxGuesses) {
							view.gameLostNotification();
							gameOver = true;
						}
						else
							view.incorrectNotification(c);
						
					}
				}
			}
			else
				view.alreadyGuessedNotification(c);
		}
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