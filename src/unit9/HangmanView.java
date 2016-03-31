package unit9;

public interface HangmanView {
	
	void gameStartNotification(String word);
	
	void gameOverNotification();
	
	void correctNotification(char c);
	
	void incorrectNotification(char c);
}
