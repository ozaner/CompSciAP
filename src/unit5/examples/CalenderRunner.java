package unit5.examples;

public class CalenderRunner
{
	public static void main(String[] args)
	{
		//Days of week
		for(DaysOfWeek d: DaysOfWeek.values())
		{
			System.out.printf("Day of the Week is %s\n", d);
		}
		
		//Months of year
		for(Months d: Months.values())
		{
			System.out.printf("%s has %d days\n", d, d.getDays());
		}
	}
}
