package unit5.examples;

public enum DaysOfWeek
{
	MONDAY, TUESDAY, WENSDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY;
	
	@Override
	public String toString()
	{
		return name().toUpperCase().substring(0,1) + name().toLowerCase().substring(1);
	}
}
