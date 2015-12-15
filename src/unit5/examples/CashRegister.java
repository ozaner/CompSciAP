package unit5.examples;

import java.util.ArrayList;

/** 
 * Models a cash register.
 * @author markjones
 *
 */
public class CashRegister {
	ArrayList<Denom> money;

	/**
	 * Creates a CashRegister with no initial cash in it.
	 */
	public CashRegister() {
		money = new ArrayList<Denom>();
	}
	
	/**
	 * Add a single bill or coin to cash register.
	 * @param d
	 */
	public void addMoney(Denom d) {
		money.add(d);
	}	
	
	/**
	 * Add multiple bills or coins of the same denomiation.
	 * @param d    the denomination to add
	 * @param num  the number of that denomination
	 */
	public void addMoney(Denom d, int num) {
		for (int i = 1; i <= num; i++)
			money.add(d);
	}
	
	/**
	 * Makes a given amount of change from the cash register.
	 * @param amount   is in standard currency form (whole dollars and 0 to 99 cents)
	 * @return         a list of the change removed from the cash register or null if change cannot be made
	 */
	public ArrayList<Denom> makeChange(double amount) {
		int cents = (int) (amount * 100);
		ArrayList<Denom> change = new ArrayList<Denom>();
		
		// makes the best change when the Denom values are in decreasing order
		for (Denom d : Denom.values()) {
			int value = d.value();
			int numCoins = cents / value;
			while (numCoins > 0 && money.remove(d)) {
				change.add(d);
				cents -= value;
				numCoins--;
			}
			if (cents == 0)
				return change;
		}
		
		// if you can't make the change, put the partial change back in the register and return null
		if (cents > 0) {
			for (Denom d : change)
				money.add(d);
			return null;
		}
		return change;
	}
	
	/**
	 * Creates a readable listing of the cash register contents.
	 */
	public String toString() {
		String s = "<";
		int numDenom = 0;
		for (Denom d : Denom.values()) {
			int numCoins = 0;
			for (Denom m : money)
				if (d == m) numCoins++;
			if (numCoins > 0) {
				if (numDenom > 0) s += ", ";
				s += d + "=" + numCoins;
				numDenom++;
			}
		}
		s += ">";
		return s;
	}
	
}
