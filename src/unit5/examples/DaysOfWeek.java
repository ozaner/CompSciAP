package unit5.examples;

public enum DaysOfWeek
{
	MONDAY, TUESDAY, WENSDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY;
	
	@Override
	public String toString()
	{
		return name().toUpperCase().substring(0,1) + name().toLowerCase().substring(1);
	}
	
	public static void main(String[] args)
	{
		for(DaysOfWeek d: DaysOfWeek.values())
		{
			System.out.printf("Day of the Week is %s\n", d);
		}
	}
}
