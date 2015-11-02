package unit2.examples;
/**
 * Models a savings account which pays periodic interest.
 * @author mark.jones
 *
 */
public class SavingsAccount extends Account {
	
	//*********************** Instance Variables ********************
	
	private double periodicInterestRate;       // periodic interest rate paid on this account
	
	//************************ Class Variables **********************
	
	public static final double DEFAULT_RATE= 0.03;  // the default periodic interest rate
	
	
	//************************* Constructors ************************
	
	/**
	 * Constructor to make a savings account given a name, account number, an initial balance, and a periodic rate.
	 * @param name		owner name
	 * @param account	account number
	 * @param initial	initial amount deposited
	 * @param rate		periodic interest rate
	 */
	public SavingsAccount (String name, int account, double initial, double rate) {
		super(name, account, initial);
		periodicInterestRate = rate;
	}
	
	/**
	 * Constructor to make a savings account given a name, account number, an initial balance, and a default periodic rate.
	 * @param name
	 * @param account
	 * @param initial
	 */
	public SavingsAccount (String name, int account, double initial) {
		this(name, account, initial, DEFAULT_RATE);
	}

	//************************* Instance Methods ************************
	
	/**
	 * Getter for the periodic interest rate.
	 * @return			periodic interest rate
	 */
	public double getRate() {
		return periodicInterestRate;
	}
	
	/**
	 * Setter for the periodic interest rate.
	 * @param rate		the new periodic interest rate
	 * @return			the periodic interest rate
	 */
	public double setRate(double rate) {
		return periodicInterestRate = rate;
	}
	
	/**
	 * Create a readable representation of a bank account. 
	 * @return			string representation of a savings account
	 */
	public String toString() {
		return String.format("%s : rate=%4.2f (%3.1f%%)", super.toString(), periodicInterestRate, periodicInterestRate*100);
	}
	
	//************************** Class Methods **************************
	
	public static double getDefaultRate () {
		return DEFAULT_RATE;
	}
	
}
