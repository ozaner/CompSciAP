package unit1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class ArrayTester
{
	/**
	 * An array of Strings of the 7 days of the week.
	 */
	public static final String[] DAYS_OF_THE_WEEK =
		{"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
	
	/**
	 * This method returns a random string from the {@link #DAYS_OF_THE_WEEK} array using {@link Math#random()}.
	 * @return A random day of the week as per {@link #DAYS_OF_THE_WEEK}.
	 */
	private static String getRandomDay()
	{
		return DAYS_OF_THE_WEEK[(int)(Math.random()*DAYS_OF_THE_WEEK.length)];
	}
	
	/**
	 * This method calls the {@link Collections#shuffle(java.util.List)}
	 * @param copy - An Array of Strings to be shuffled.
	 */
	private static void shuffleStringArray(String[] s)
	{
		Collections.shuffle(Arrays.asList(s));
	}

	private static void shuffleStringArray2(String[] s)
	{
		ArrayList<String> temp = new ArrayList<String>(Arrays.asList(s));
		for(int x = 0; x > s.length; x++)
		{
			int rand = (int)(Math.random()*temp.size());
			s[x] = temp.get(rand);
			temp.remove(rand);
		}
	}
	
	/**
	 * Calls the {@link Arrays#sort(Object[])} method on an array of Strings.
	 * @param copy - The array of Strings to sort.
	 */
	private static void sortStringArray(String[] s)
	{
		Arrays.sort(s);
	}
	
	private static void reverseStringArray(String[] s)
	{
		String[] temp = new String[s.length]; //Makes a temporary array to store the values.
		for(int c = 0; c < s.length; c++)
			temp[c] = s[s.length-1-c];
		System.arraycopy(temp, 0, s, 0, s.length); //Copies the contents of temp top
	}
	
	public static void main(String[] args)
	{
		String[] copy; 
		System.out.printf("getRandomDay() returns %s\n", getRandomDay());
		
		copy = Arrays.copyOf(DAYS_OF_THE_WEEK, DAYS_OF_THE_WEEK.length); 
		sortStringArray(copy);
		System.out.printf("sortStringArray(%s) yields\n\t%s\n", 
				Arrays.toString(DAYS_OF_THE_WEEK), Arrays.toString(copy));
		
		copy = Arrays.copyOf(DAYS_OF_THE_WEEK, DAYS_OF_THE_WEEK.length);
		shuffleStringArray(copy);
		System.out.printf("shuffleStringArray(%s) yields\n\t%s\n",
				Arrays.toString(DAYS_OF_THE_WEEK), Arrays.toString(copy));
		
		copy = Arrays.copyOf(DAYS_OF_THE_WEEK, DAYS_OF_THE_WEEK.length);
		reverseStringArray(copy);
		System.out.printf("reverseStringArray(%s) yields\n\t%s\n",
				Arrays.toString(DAYS_OF_THE_WEEK), Arrays.toString(copy));
		
		copy = Arrays.copyOf(DAYS_OF_THE_WEEK, DAYS_OF_THE_WEEK.length); 
		shuffleStringArray2(copy);
		System.out.printf("shuffleStringArray2(%s) yields \n\t%s\n", 
				Arrays.toString(DAYS_OF_THE_WEEK), Arrays.toString(copy));
	}
}