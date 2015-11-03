package unit3.examples;
/**
 * A simple bank account example.
 * @author Mark Jones
 *
 */
public class Account {
	
	//*********************** Instance Variables ********************
	
	private String ownerName;  // owner name of the account
	private int acctNumber;    // account number for this account
	private double balance;    // running balance in the account
	
	//************************ Class Variables **********************
	
	
	//************************* Constructors ************************
	
	/**
	 * Constructor to make a bank account given a name, account number and an initial balance.
	 * @param name		owner name
	 * @param account	account number
	 * @param initial	initial amount deposited
	 */
	public Account (String name, int account, double initial) {
		ownerName = name;
		acctNumber = account;
		balance = initial;
	}
	
	//************************* Instance Methods ************************
	
	/**
	 * Deposit money into a bank account.
	 * @param amount	amount to be deposited
	 * @return			amount successfully deposited
	 */
	public double deposit(double amount) {
		if (amount < 0) {
			System.out.printf("Deposit Error (%s): Invalid amount %.2f%n", ownerName, amount);
			return 0;
		}
		balance += amount;
		return amount;
	}
	
	/**
	 * Withdraw money from a bank account.
	 * @param amount		amount to be withdrawn
	 * @return				amount successfully withdrawn
	 */
	public double withdraw(double amount) {
		if (amount < 0) {
			System.out.printf("Withdrawal Error (%s): Invalid amount %.2f%n", ownerName, amount);
			return 0;
		} else if (amount > balance) {
			System.out.printf("Withdrawal Error (%s): Insufficient funds  balance=%.2f  amount=%.2f%n", ownerName, balance, amount);
			return 0;
		}
		balance -= amount;
		return amount;
	}
	
	/**
	 * Getter for the balance.
	 * @return			the balance in the account
	 */
	public double getBalance() {
		return balance;
	}
	
	/**
	 * Create a readable representation of a bank account. 
	 * @return			string representation of a savings account
	 */
	public String toString() {
		return String.format("acct=%d : name=%s : bal=%.2f", acctNumber, ownerName, balance);
	}
	
	//************************** Class Methods **************************
	
	/**
	 * Transfer an amount from one account to another.
	 * @param fromAcct		account to transfer from
	 * @param toAcct		account to transfer to
	 * @param amount		amount to be transferred
	 * @return				amount successfully transferred
	 */
	public static double transfer(Account fromAcct, Account toAcct, double amount) {
		if (fromAcct.withdraw(amount) != amount) {
			// transfer withdraw failed
			return 0;
		} else if (toAcct.deposit(amount) != amount) {
			// transfer withdraw succeeded but deposit failed
			fromAcct.deposit(amount);  // restore the funds to the fromAcct
			return 0;
		}
		// the withdraw and deposit both succeeded
		return amount;
	}
	

}
