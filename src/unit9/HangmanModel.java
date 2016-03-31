package unit9;

public class HangmanModel {
	public static char[] ALPHABET = {
		'a','b','c','d','e','f','g','h','i','j','k','l','m',
		'n','o','p','q','r','s','t','u','v','w','x','y','z'
	};
	
	private HangmanView view;
	private int score;
	private String currentWord;
	private char[] guessedLetters;
	
	public HangmanModel(HangmanView view) {
		this.view = view;
	}
}
