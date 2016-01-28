package unit6.blackjackProject;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import acm.graphics.GImage;
import acm.graphics.GObject;
import acm.program.GraphicsProgram;

/**
 * A Blackjack game using the ACM Library.
 * <br><br>
 * AP Computer Science<br>
 * 1/28/16<br>
 * @author Ozaner Hansha
 * @author Mark Jones
 * 
 * @version 1.0
 */
@SuppressWarnings("serial")
public class Blackjack extends GraphicsProgram implements BlackjackView {
	
	/**
	 * Initial window size.
	 */
	private static final Dimension INITIAL_SIZE = new Dimension(700,300);
	
	/**
	 * Coordinates of where the card stacks start.
	 */
	private static final double DECK_X = INITIAL_SIZE.getWidth() * .04,
								PLAYER_X = INITIAL_SIZE.getWidth() * .55,
								DEALER_X = INITIAL_SIZE.getWidth() * .2,
								CARD_Y = INITIAL_SIZE.getHeight() * .1;
	
	/**
	 * How much to offset new cards added to dealer/player's hand.
	 */
	private static final double CARD_OFFSET = BlackjackGCard.cardWidth() * .25;
	
	/**
	 * The Y Coordinate of the name tags.
	 */
	private static final double NAME_Y = INITIAL_SIZE.getHeight() * .01;
	
	/**
	 * The font of the {@link #nameTags}.
	 */
	private static final Font NAME_FONT = new Font("Papyrus", Font.BOLD, 19);
		
	/**
	 * A pointer to the {@link BlackjackModel} corresponding with
	 * this instance of {@link Backjack} for callbacks.
	 */
	private BlackjackModel bm;
	
	/**
	 * This {@link JLabel} displays any messages sent by {@link #bm}.
	 */
	private JLabel notifications = new JLabel();
	
	/**
	 * This {@link JLabel} displays the scores of the games.
	 * @see #updateScoreboard()
	 */
	private JLabel scoreboard = new JLabel();
	
	/**
	 * Name tags for the deck, dealer, and the player.
	 */
	private JLabel[] nameTags = {new JLabel("Deck"),
								new JLabel("Dealer"),
								new JLabel("Player")};
	
	/**
	 * 
	 */
	private JButton[] buttons = {new JButton("New Round"),
								new JButton("Hit"),
								new JButton("Stay")};

	/**
	 * An image of a facedown card, representing the deck.
	 */
	private GImage deck = BlackjackGCard.getBackImage();
	
	/**
	 * Entry point when running Blackjack as an application.
	 * @param args
	 */
	public static void main(String[] args) {
		(new Blackjack()).start();
	}

	/**
	 * Constructor for Blackjack when running as an application.
	 */
	public Blackjack() {
		
	}

	/** 
	 * Create the Model (with this for callbacks).
	 * Set up the GUI.
	 */
	@Override
	public void init() {

		bm = new BlackjackModel(this);

		setSize(INITIAL_SIZE);
		setBackground(Color.LIGHT_GRAY);
		
		notifications.setText("Welcome to BlackJack. Press New Game to start a new round.");
		notifications.setIcon(new ImageIcon(Blackjack.class.getResource("/unit6/blackjackProject/bjLogo.png")));
		
		//GUI
		add(notifications, NORTH);
		
		for(JButton b: buttons)
		{
			add(b, SOUTH);
		}
		buttons[1].setEnabled(false);
		buttons[2].setEnabled(false);
		
		add(deck, DECK_X, CARD_Y);
		
		for(JLabel j: nameTags)
			j.setFont(NAME_FONT);
		add(nameTags[0], DECK_X, NAME_Y);
		add(nameTags[1], DEALER_X, NAME_Y);
		add(nameTags[2], PLAYER_X, NAME_Y);
		
		add(scoreboard, SOUTH);
		updateScoreboard(0,0,0); //Starts scoreboard at 0
		
		addActionListeners();
	}

	/**
	 * Run a new round of blackjack.
	 */
	@Override
	public void run() {
		
	}

	/**
	 * Starts a new round.
	 */
	public void newRound() {
		if(!bm.isGameInProgress())
		{
			//Removes all cards from canvas.
			ArrayList<GObject> toRemove = new ArrayList<GObject>();
			for(int x = 0; x < getGCanvas().getElementCount(); x++)
			{
				if(getGCanvas().getElement(x) instanceof BlackjackGCard)
				{ 		
					toRemove.add(getGCanvas().getElement(x));
				}												
			}
			//Can't do it directly as it would modify the list currently being iterated through
			for(GObject g: toRemove)
			{
				getGCanvas().remove(g);
			}
			
			bm.newRound();
			notifications.setText("Starting a New Round. (Hit or Stay?)");
			buttons[0].setText("Give Up");
			buttons[1].setEnabled(true);
			buttons[2].setEnabled(true);
		}
		else
		{
			bm.quitGame();
		}
	}
	
	/**
	 * Done after a round is over.
	 */
	public void endRound(int wins, int losses, int ties)
	{
		//Turn all cards face up.
		for(int x = 0; x < getGCanvas().getElementCount(); x++)
		{
			if(getGCanvas().getElement(x) instanceof BlackjackGCard)
			{
				((BlackjackGCard)getGCanvas().getElement(x)).turnFaceUp();
			}
		}
		updateScoreboard(wins, losses, ties);
		buttons[0].setText("New Round");
		buttons[1].setEnabled(false);
		buttons[2].setEnabled(false);
	}
	
	/**
	 * Updates the scoreboard with the current wins, losses, and ties.
	 */
	public void updateScoreboard(int wins, int losses, int ties) {
		scoreboard.setText(String.format("%d Wins - %d Losses - %d Ties", wins, losses, ties));
	}
	
	/** 
	 * Handler for button actions.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand() == "Hit")
			bm.hit();
		else if(e.getActionCommand() == "Stay")
			bm.stay();
		else //Must be reset, only 3 buttons.
			newRound();
	}

	/**
	 * This is a helper method for capitalizing strings.
	 * @param string - String to capitalize.
	 */
	public static String capitalize(String string)
	{
		return string.charAt(0) + string.toLowerCase().substring(1);
	}
	
	/*************  Notifications  ***************/

	/**
	 * Place the card dealt to the player on the canvas.
	 * The card size is rescaled to be consistent with the current deck image size.
	 * @param card  the card to be added
	 */
	@Override
	public void cardDealtToPlayerNotification(BlackjackGCard card) {
		notifications.setText(String.format("Dealer dealt a %s of %s.",
				capitalize(card.getRank().name()), capitalize(card.getSuit().name())));
		add(card, PLAYER_X + (bm.getPlayerCardNumber() -1) * CARD_OFFSET, CARD_Y);
	}

	/**
	 * Place the card dealt to the dealer on the canvas.
	 * The card size is rescaled to be consistent with the current deck image size.
	 * @param card  the card to be added
	 */
	@Override
	public void cardDealtToDealerNotification(BlackjackGCard card) {
		notifications.setText("Dealer dealt a card to himself");
		add(card, DEALER_X + (bm.getDealerCardNumber() -1) * CARD_OFFSET, CARD_Y);
	}

	/**
	 * End of game result notification. Dealer and player both bust.
	 */
	@Override
	public void bothBustNotification(int wins, int losses, int ties) {
		notifications.setText("You and the Dealer go bust. It's a Tie!");
		endRound(wins, losses, ties);
	}

	/**
	 * End of game result notification. Dealer wins, player busts.
	 */
	@Override
	public void youBustDealerWinsNotification(int wins, int losses, int ties) {
		notifications.setText("You went bust. You lose!");
		endRound(wins, losses, ties);
	}

	/**
	 * End of game result notification. Dealer busts, player wins.
	 */
	@Override
	public void dealerBustYouWinNotification(int wins, int losses, int ties) {
		notifications.setText("The Dealer went bust. You win!");
		endRound(wins, losses, ties);
	}

	/**
	 * End of game result notification. Player beats dealer.
	 */
	@Override
	public void youBeatDealerNotification(int wins, int losses, int ties) {
		notifications.setText("You beat the Dealer, You win!");
		endRound(wins, losses, ties);
	}

	/**
	 * End of game result notification. Dealer beats player.
	 */
	@Override
	public void dealerBeatsYouNotification(int wins, int losses, int ties) {
		notifications.setText("Dealer beat you, You Lose!");
		endRound(wins, losses, ties);
	}

	/**
	 * End of game result notification. Dealer and player tie.
	 */
	@Override
	public void bothTieNotification(int wins, int losses, int ties) {
		notifications.setText("You and the Dealer tie!");
		endRound(wins, losses, ties);
	}

	/**
	 * End of game result notification. Player quits game and loses.
	 */
	@Override
	public void quitGameNotification(int wins, int losses, int ties) {
		notifications.setText("You quit, You lose!");
		endRound(wins, losses, ties);
	}
}