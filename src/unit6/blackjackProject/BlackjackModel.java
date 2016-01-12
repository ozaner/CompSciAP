package unit6.blackjackProject;
/**
 * Abstractly models a Blackjack game.
 * @author Mark Jones
 *
 */
public class BlackjackModel {

	// instance variables
	private BlackjackView bv;
	// . . .

	/**
	 * Create a BlackjackModel (given a BlackjackView for notifications).
	 * @param blackjackView
	 */
	public BlackjackModel(BlackjackView blackjackView) {
		bv = blackjackView;
		// . . .
	}
	
	// Define additional methods for the actions that arise in the playing of Blackjack,
	// such as startGame, hit, stay, and quitGame.
}
