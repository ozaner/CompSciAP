package unit3.examples;
/**
 * An application for demonstrating object-oriented programming using banking classes.
 * @author Mark Jones
 *
 */
public class BankingApp {

	/**
	 * Main method for testing the banking classes.
	 * @param args
	 */
	public static void main(String[] args) {
		// 1.  Make an account with owner Jack, acct # 17295, and initial balance of $1000
		System.out.println("Create account for Jack");
		Account jack = new Account("Jack", 17295, 1000);
		System.out.println(jack);
		
		// 2.  Make an account with owner Jill, acct # 26397, and initial balance of $800
		System.out.println("\nCreate account for Jill");
		Account jill = new Account("Jill", 26397, 800);
		System.out.println(jill);
		
		// 3.  Withdraw $200 from Jack's account and deposit it in Jill's account.
		System.out.println("\nTransfer $200 from Jack to Jill");
		if (jack.withdraw(200) == 200)
			jill.deposit(200);
		System.out.println(jack);
		System.out.println(jill);
		
		// 4.  Try to withdraw $2000 from Jack's account and deposit it in Jill's account.
		System.out.println("\nTransfer $2000 from Jack to Jill");
		if (jack.withdraw(2000) == 2000)
			jill.deposit(200);
		System.out.println(jack);
		System.out.println(jill);
		
		// 5.  Try to withdraw -$100 from Jack's account and deposit it in Jill's account.
		System.out.println("\nTransfer -$100 from Jack to Jill");
		if (jack.withdraw(-100) == -100)
			jill.deposit(-100);
		System.out.println(jack);
		System.out.println(jill);
		
		// 6.  Obtain an account balance for Jack and for Jill.
		System.out.printf("%nJack's balance is %.2f%n", jack.getBalance());
		System.out.printf("Jill's balance is %.2f%n", jill.getBalance());
		
		// 7.  Transfer $300 from Jill's account to Jack's account.
		System.out.println("\nTransfer $300 from Jill to Jack (using a class method)");
		Account.transfer(jill, jack, 300);
		System.out.println(jack);
		System.out.println(jill);
		
		// 8.  Make a savings account with owner Old Mother Hubbard, acct # 23961, and initial balance of $100.
		System.out.println("\nCreate a savings account for Old Mother Hubbard with periodic interest rate of 0.12 (12.0%)");
		SavingsAccount hubbard = new SavingsAccount("Old Mother Hubbard", 23961, 100, 0.12);
		System.out.println(hubbard);	
		
		// 9.  Make a savings account with owner Little Boy Blue, acct # 31115, and initial balance of $10000.
		System.out.println("\nCreate a savings account for Little Boy Blue with default periodic interest rate");
		SavingsAccount blue = new SavingsAccount("Little Boy Blue", 31115, 100);
		System.out.println(blue);	
		
		// 10. Get the default interest rate (class variable) and the rate for Old Mother Hubbard.
		System.out.printf("\nThe default periodic interest rate for savings accounts (from public class variable) is %4.2f (%3.1f%%)",
				SavingsAccount.DEFAULT_RATE, SavingsAccount.DEFAULT_RATE*100);
		System.out.printf("\nThe default periodic interest rate for savings accounts (from public class method) is %4.2f (%3.1f%%)",
				SavingsAccount.getDefaultRate(), SavingsAccount.getDefaultRate()*100);
		System.out.printf("\nThe periodic interest rate for Old Mother Hubbard is %4.2f (%3.1f%%)",
				hubbard.getRate(), hubbard.getRate()*100);
	}
}
