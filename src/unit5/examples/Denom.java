package unit5.examples;

/**
 * Commonly used American currency denominations.
 * This version uses a private constructor to set up the values.
 * @author Mark Jones
 *
 */
public enum Denom {
	FIFTY(5000), TEN(1000), FIVE(500), ONE(100), QUARTER(25), DIME(10), NICKEL(5), PENNY(1);
	
	private int value;
	
	// An enum constructor must be private and is only called internally
	// when the above constants are created.
	private Denom(int value) {
		this.value = value;
	}
	
	public int value() {  // returns the value
		return value;
	}

}

/**
 * Commonly used American currency denominations.
 * This version dynamically computes the values.
 * @author Mark Jones
 *
 */
//public enum Denom {
//	FIFTY, TEN, FIVE, ONE, QUARTER, DIME, NICKEL, PENNY;
//	
//	public int value() {  // returns the value
//		switch (this) {
//		case FIFTY: return 5000;
//		case TEN: return 1000;
//		case FIVE: return 500;
//		case ONE: return 100;
//		case QUARTER: return 25;
//		case DIME: return 10;
//		case NICKEL: return 5;
//		case PENNY: return 1;
//		default: return 0;
//		}
//	}
//}

