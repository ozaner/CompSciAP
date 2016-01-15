package unit6.blackjackProject;
/**
 * Abstractly models a Blackjack game.
 * @author Mark Jones
 *
 */
public class BlackjackModel {

	/**
	 * The BlackjackView corresponding to this model.
	 */
	private BlackjackView bv;
	
	/**
	 * The player object.
	 */
	private Player player = new Player();
	
	/**
	 * The dealer object.
	 */
	private Dealer dealer = new Dealer();
	
	/**
	 * The wins, losses, or ties of this game.
	 */
	private int wins, losses, ties;

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
		bv.cardDealtToPlayerNotification(dealer.dealFaceDown(player));
	}
	
	/**
	 * Ends the player's turn.
	 */
	public void stay()
	{
		
	}
	
	/**
	 * WHat the dealer does on his turn.
	 */
	public void dealerTurn()
	{
		if(dealer.handValue() <= 17) return;
		for(Card c: dealer.hand)
		{
			
		}
	}		
	
	/**
	 * 
	 */
	public void quitGame()
	{
		
	}
}
