package unit7;
public enum Month {
	JANUARY(31), FEBRUARY(28), MARCH(31), APRIL(30), MAY(31), JUNE(30),
	JULY(31), AUGUST(31), SEPTEMBER(30), OCTOBER(31), NOVEMBER(30), DECEMBER(31);
	
	private int numDays;
	
	private Month(int numDays) {
		this.numDays = numDays;
	}
	
	public int getDays() {
		return numDays;
	}
	
	public String toString() {
		String name = name();
		return name.substring(0,1).toUpperCase() + name.substring(1).toLowerCase();
	}
}
