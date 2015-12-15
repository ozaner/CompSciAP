package unit5.examples;

import java.util.ArrayList;

/**
 * Exercise the CashRegister class.
 * @author Mark Jones
 *
 */
public class CashRegisterRunner {

	static CashRegister reg;

	/**
	 * Exercise the CashRegister class.
	 * @param args  no command line args are expected
	 */
	public static void main(String[] args) {
		reg = new CashRegister();
		newDrawer();
		System.out.println(reg);
		makeChange(3.25);
		System.out.println(reg);
		makeChange(1.55);
		System.out.println(reg);
		makeChange(1.25);
		System.out.println(reg);
		makeChange(1.25);
		System.out.println(reg);
	}

	/**
	 * Makes change for a given amount. Prints change to the console.
	 * @param amount  the amount of change required
	 * @return        true if the change was successfully made, false otherwise
	 */
	private static boolean makeChange(double amount) {
		ArrayList<Denom> change = reg.makeChange(amount);
		if (change != null) {
			System.out.printf("Making change for %.2f\n", amount);
			for (Denom d : change)
				System.out.printf("Dispense a %s\n", d);
		} else {
			System.out.printf("Cannot make change for %.2f\n", amount);
		}
		return change != null;
	}

	/** 
	 * Sets up a new cash drawer for the cash register.
	 */
	public static void newDrawer() {
		reg.addMoney(Denom.FIFTY,    1);
		reg.addMoney(Denom.TEN,      3);
		reg.addMoney(Denom.FIVE,     2);
		reg.addMoney(Denom.ONE,     10);
		reg.addMoney(Denom.QUARTER,  3);
		reg.addMoney(Denom.DIME,     2);
		reg.addMoney(Denom.NICKEL,   2);
		reg.addMoney(Denom.PENNY,   10);
	}
}
