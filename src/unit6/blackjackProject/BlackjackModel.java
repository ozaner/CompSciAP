package unit6.blackjackProject;

/**
 * Abstractly models a Blackjack game.
 * @author Ozaner Hansha
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
	 * Does dealer's turn and decides who wins.
	 */
	public void stay()
	{
		while(dealer.handValue() < 17) //if hand value is 17 or more, stay.
			bv.cardDealtToDealerNotification(dealer.dealFaceDown(dealer));
		checkWinConditions();
	}

	/**
	 * Checks if player or dealer has gone bust and
	 * sends appropriate callback to the {@link #bv}.
	 * @return Whether or not this check has resulted in a winner/tie.
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
	 * Checks to see if any player has a blackjack.
	 * Whoever does wins, if both have one its a tie.
	 * @return Whether or not this check has resulted in a winner/tie.
	 */
	public boolean checkForBlackjack()
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
		else if(dealer.hasBlackjack()) //If dealer has a blackjack.
		{
			losses++;
			bv.dealerBeatsYouNotification(wins, losses, ties);
			return true;
		}
		return false;
	}
	
	/**
	 * Checks who has a bigger hand. Always returns a winner.
	 * Sends appropriate callback to the {@link #bv}.
	 */
	public void checkForWin()
	{
		if(player.handValue() > dealer.handValue()) //If player is over dealer (checkForBust insures not a bust)
		{
			wins++;
			bv.youBeatDealerNotification(wins, losses, ties);
		}
		else if(dealer.handValue() > player.handValue()) //If dealer is over player
		{
			losses++;
			bv.dealerBeatsYouNotification(wins, losses, ties);
		}
		else //Must be a tie
		{
			ties++;
			bv.bothTieNotification(wins, losses, ties);
		}
	}
	
	/**
	 * Ends the round and finds a winner.
	 */
	public void checkWinConditions()
	{
		if(!checkForBust()) //If nobody bust.
			if(!checkForBlackjack()) //If nobody has a blackjack.
				checkForWin(); //Always returns a winner.
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