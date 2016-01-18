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
	private Player player;
	
	/**
	 * The dealer object.
	 */
	private Dealer dealer;
	
	/**
	 * The wins, losses, or ties of this game.
	 */
	private int wins, losses, ties;
	
	/**
	 * Whether or not the current round has ended.
	 */
	private boolean gameInProgress;

	/**
	 * Create a BlackjackModel (given a BlackjackView for notifications).
	 * @param blackjackView
	 */
	public BlackjackModel(BlackjackView blackjackView)
	{
		bv = blackjackView;
		// . . .
	}
	
	/**
	 * @return Amount of cards in {@link #player}'s hand.
	 */
	public int getPlayerCardNumber()
	{
		return player.getHand().size();
	}
	
	/**
	 * @return Amount of cards in {@link #dealer}'s hand.
	 */
	public int getDealerCardNumber()
	{
		return dealer.getHand().size();
	}
	
	/**
	 * @return Whether or not a game is in progress.
	 */
	public boolean isGameInProgress()
	{
		return gameInProgress;
	}
	
	/**
	 * Starts a new Round by dealing 2 cards, one face down,
	 * one face up, to both the player and the dealer.
	 * @return Whether the round was started or not(quit).
	 */
	public void newRound()
	{
		gameInProgress = true;
		dealer = new Dealer();
		player = new Player();
		
		bv.cardDealtToPlayerNotification(dealer.dealFaceUp(player));
		bv.cardDealtToDealerNotification(dealer.dealFaceUp(dealer));
		bv.cardDealtToPlayerNotification(dealer.dealFaceUp(player));
		bv.cardDealtToDealerNotification(dealer.dealFaceDown(dealer));
		checkWinConditions(); //Check to see if anyone won right away.
	}
	
	/**
	 * Hits the player with one face up card.
	 */
	public void hit()
	{
		if(player.handValue() < 21)
			bv.cardDealtToPlayerNotification(dealer.dealFaceUp(player));
	}
	
	/**
	 * Ends the player's turn.
	 */
	public void stay()
	{
		if(dealer.handValue() < 17) //if hand value is 17 or more, stay.
			bv.cardDealtToDealerNotification(dealer.dealFaceDown(dealer));
		checkWinConditions();
	}	

	/**
	 * Checks if player or dealer has gotten a 21 or blackjack
	 * and sends appropriate callback to the {@link #bv}.
	 * @return Whether or not someone has won.
	 */
	public boolean checkForWin()
	{
		if(player.handValue() == 21 && dealer.handValue() == 21) //If both got 21.
		{
			if(player.hasBlackjack() && dealer.hasBlackjack()) //If both got blackjack as well.
			{
				ties++;
				bv.bothTieNotification(wins, losses, ties);
				return true;
			}
			else if(player.hasBlackjack()) //If player has a blackjack.
			{
				wins++;
				bv.youBeatDealerNotification(wins, losses, ties);
				return true;
			}
			else //Dealer must have blackjack.
			{
				losses++;
				bv.dealerBeatsYouNotification(wins, losses, ties);
				return true;
			}
		}
		else if(player.handValue() == 21) //If player got 21.
		{
			wins++;
			bv.youBeatDealerNotification(wins, losses, ties);
			return true;
		}
		else if(dealer.handValue() == 21) //If dealer got 21.
		{
			losses++;
			bv.dealerBeatsYouNotification(wins, losses, ties);
			return true;
		}
		return false;
	}
	
	/**
	 * Checks if player or dealer has gone bust and
	 * sends appropriate callback to the {@link #bv}.
	 * 	 * @return Whether or not someone has bust.
	 */
	public boolean checkForBust()
	{
		if(player.handValue() > 21 && dealer.handValue() > 21) //If both went bust.
		{
			ties++;
			bv.bothBustNotification(wins, losses, ties);
			return true;
		}
		else if(player.handValue() > 21) //If player went bust.
		{
			losses++;
			bv.youBustDealerWinsNotification(wins, losses, ties);
			return true;
		}
		else if(dealer.handValue() > 21) //If dealer went bust.
		{
			wins++;
			bv.dealerBustYouWinNotification(wins, losses, ties);
			return true;
		}
		return false;
	}
	
	/**
	 * Checks for any game ending conditions.
	 */
	public void checkWinConditions()
	{
		//If any win conditions have been met.
		if(checkForWin() || checkForBust())
			gameInProgress = false;
	}
	
	/**
	 * This round is a loss and starts a new round.
	 */
	public void quitGame()
	{
		losses++;
		gameInProgress = false;
		bv.quitGameNotification(wins, losses, ties);
	}
}
