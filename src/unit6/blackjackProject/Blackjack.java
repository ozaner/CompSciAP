package unit6.blackjackProject;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JButton;
import javax.swing.JLabel;

import acm.program.*;
import acm.graphics.*;

/**
 * Blackjack game.
 * @author Mark Jones
 * @version 1.0
 */
@SuppressWarnings("serial")
public class Blackjack extends GraphicsProgram implements BlackjackView {
	
	// class constants
	private static final int INITIAL_WIDTH = 700;
	private static final int INITIAL_HEIGHT = 300;
	// . . .

	// class variables
	// . . .

	// instance variables
	private BlackjackModel bm;
	
	
	// . . .

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

		setSize(INITIAL_WIDTH, INITIAL_HEIGHT);
		setBackground(Color.LIGHT_GRAY);

	}

	/**
	 * Run a new round of blackjack.
	 */
	@Override
	public void run() {
		
	}

	/** 
	 * Handler for button actions.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}

	/*************  Notifications  ***************/

	/**
	 * Place the card dealt to the player on the canvas.
	 * The card size is rescaled to be consistent with the current deck image size.
	 * @param card  the card to be added
	 */
	@Override
	public void cardDealtToPlayerNotification(BlackjackGCard card) {
		
	}

	/**
	 * Place the card dealt to the dealer on the canvas.
	 * The card size is rescaled to be consistent with the current deck image size.
	 * @param card  the card to be added
	 */
	@Override
	public void cardDealtToDealerNotification(BlackjackGCard card) {

	}

	/**
	 * End of game result notification. Dealer and player both bust.
	 */
	@Override
	public void bothBustNotification(int wins, int losses, int ties) {

	}

	/**
	 * End of game result notification. Dealer wins, player busts.
	 */
	@Override
	public void youBustDealerWinsNotification(int wins, int losses, int ties) {

	}

	/**
	 * End of game result notification. Dealer busts, player wins.
	 */
	@Override
	public void dealerBustYouWinNotification(int wins, int losses, int ties) {

	}

	/**
	 * End of game result notification. Player beats dealer.
	 */
	@Override
	public void youBeatDealerNotification(int wins, int losses, int ties) {

	}

	/**
	 * End of game result notification. Dealer beats player.
	 */
	@Override
	public void dealerBeatsYouNotification(int wins, int losses, int ties) {

	}

	/**
	 * End of game result notification. Dealer and player tie.
	 */
	@Override
	public void bothTieNotification(int wins, int losses, int ties) {

	}

	/**
	 * End of game result notification. Player quits game and loses.
	 */
	@Override
	public void quitGameNotification(int wins, int losses, int ties) {

	}
}

