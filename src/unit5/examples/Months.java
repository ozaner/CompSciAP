package unit5.examples;

public enum Months
{
	JANUARY(31), FEBRUARY(28), MARCH(31), APRIL(30), MAY(31), JUNE(30),
	JULY(31), AUGUST(31), SEPTEMBER(30), OCTOBER(30), NOVEMBER(30), DECEMBER(31);
	
	private int daysInMonth;
	
	private Months(int days)
	{
		daysInMonth = days;
	}
	
	@Override
	public String toString()
	{
		return name().toUpperCase().substring(0,1) + name().toLowerCase().substring(1);
	}
	
	public static void main(String[] args)
	{
		for(Months d: Months.values())
		{
			System.out.printf("Day of the Week is %s\n", d);
		}
	}
}
