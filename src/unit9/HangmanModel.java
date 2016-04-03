package unit9;

import java.util.HashSet;
import java.util.Set;

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
	
	public boolean gameWon() {
		Set<Character> s = new HashSet<Character>();
		for(char c: currentWord.toCharArray()) {
			s.add(c);
		}
		return guessedLetters.containsAll(s);
	}
	
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