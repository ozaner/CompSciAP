package unit6.blackjackProject;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import acm.program.GraphicsProgram;

/**
 * A Blackjack game using the ACM Library.
 * <br><br>
 * AP Computer Science<br>
 * 1/??/16<br>
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
	 * 
	 */
	private JButton[] buttons = {new JButton("New Game"),
								new JButton("Hit"),
								new JButton("Stay")};

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
		
		notifications.setText("TEST");
		notifications.setIcon(new ImageIcon(Blackjack.class.getResource("/unit6/blackjackProject/bjLogo.png")));
		
		//GUI
		add(notifications, NORTH);
		
		for(JButton b: buttons)
		{
			add(b, SOUTH);
		}
		
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
	public void newGame() {
		notifications.setText("Starting a New Round. (Hit or Stay?)");
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
		System.out.println(e.getActionCommand());
	}

	/*************  Notifications  ***************/

	/**
	 * Place the card dealt to the player on the canvas.
	 * The card size is rescaled to be consistent with the current deck image size.
	 * @param card  the card to be added
	 */
	@Override
	public void cardDealtToPlayerNotification(BlackjackGCard card) {
		notifications.setText(String.format("Dealer dealt a %s of %s.", card.getRank(), card.getSuit()));
	}

	/**
	 * Place the card dealt to the dealer on the canvas.
	 * The card size is rescaled to be consistent with the current deck image size.
	 * @param card  the card to be added
	 */
	@Override
	public void cardDealtToDealerNotification(BlackjackGCard card) {
		notifications.setText("Dealer dealt a card to himself");
	}

	/**
	 * End of game result notification. Dealer and player both bust.
	 */
	@Override
	public void bothBustNotification(int wins, int losses, int ties) {
		notifications.setText("You and the Dealer go bust. It's a Tie!");
		updateScoreboard(wins, losses, ties);
	}

	/**
	 * End of game result notification. Dealer wins, player busts.
	 */
	@Override
	public void youBustDealerWinsNotification(int wins, int losses, int ties) {
		notifications.setText("You went bust. You lose!");
		updateScoreboard(wins, losses, ties);
	}

	/**
	 * End of game result notification. Dealer busts, player wins.
	 */
	@Override
	public void dealerBustYouWinNotification(int wins, int losses, int ties) {
		notifications.setText("The Dealer went bust. You win!");
		updateScoreboard(wins, losses, ties);
	}

	/**
	 * End of game result notification. Player beats dealer.
	 */
	@Override
	public void youBeatDealerNotification(int wins, int losses, int ties) {
		notifications.setText("You got 21, You win!");
		updateScoreboard(wins, losses, ties);
	}

	/**
	 * End of game result notification. Dealer beats player.
	 */
	@Override
	public void dealerBeatsYouNotification(int wins, int losses, int ties) {
		notifications.setText("Dealer got to 21, You win!");
		updateScoreboard(wins, losses, ties);
	}

	/**
	 * End of game result notification. Dealer and player tie.
	 */
	@Override
	public void bothTieNotification(int wins, int losses, int ties) {
		notifications.setText("You and dealer tie!");
		updateScoreboard(wins, losses, ties);
	}

	/**
	 * End of game result notification. Player quits game and loses.
	 */
	@Override
	public void quitGameNotification(int wins, int losses, int ties) {
		notifications.setText("You quit, You lose!");
		updateScoreboard(wins, losses, ties);
	}
}

