package unit6.blackjackProject;
/**
 * Abstractly models a Blackjack game.
 * @author Mark Jones
 *
 */
public class BlackjackModel {

	// instance variables
	private BlackjackView bv;
	
	private Player player;
	
	private Dealer dealer;
	
	private boolean dealerTurn;

	/**
	 * Create a BlackjackModel (given a BlackjackView for notifications).
	 * @param blackjackView
	 */
	public BlackjackModel(BlackjackView blackjackView) {
		bv = blackjackView;
		// . . .
	}
	
	/**
	 * Starts a game by dealing 2 cards, one face down,
	 * one face up, to both the player and the dealer.
	 */
	public void startGame()
	{
		dealer.dealFaceUp(player);
		dealer.dealFaceUp(dealer);
		
		dealer.dealFaceDown(player);
		dealer.dealFaceDown(dealer);
	}
	
	/**
	 * Hits the player with one face up card.
	 */
	public void hit()
	{
		dealer.dealFaceDown(player);
		bv.cardDealtToPlayerNotification(card);
	}
	
	/**
	 * 
	 */
	public void stay()
	{
		dealerTurn = true;
	}
	
	/**
	 * 
	 */
	public void dealerTurn()
	{
		
	}
	
	/**
	 * 
	 */
	public void quitGame()
	{
		
	}
}
