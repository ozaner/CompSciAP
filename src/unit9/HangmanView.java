package unit9;

public interface HangmanView {
	
	void gameStartNotification(String word);
	
	void gameLostNotification();
	
	void gameWonNotification();
	
	void correctNotification(char c);
	
	void incorrectNotification(char c);
	
	void alreadyGuessedNotification(char c);
}
