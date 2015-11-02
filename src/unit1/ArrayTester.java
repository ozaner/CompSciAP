package unit1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

/**
 * This a "Self-Test" on using Arrays.<br><br>
 * 
 * AP Computer Science<br>
 * Dr. Jones<br>
 * October 27, 2015
 * @author Ozaner Hansha
 */
public class ArrayTester
{
	/**
	 * An array of Strings of the 7 days of the week. Starting at Monday.
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
	 * @param s - An Array of Strings to be shuffled.
	 */
	private static void shuffleStringArray(String[] s)
	{
		Collections.shuffle(Arrays.asList(s));
	}

	/**
	 * This method randomizes a given array of Strings using a simple algorithm.
	 * @param s - An Array of Strings to be shuffled.
	 */
	private static void shuffleStringArray2(String[] s)
	{
		ArrayList<String> temp = new ArrayList<String>(Arrays.asList(s)); //the rest of the non randomized array aka 'temp'.
		for(int x = 0; x < s.length; x++) //For every index of the given array (s).
		{
			int rand = (int)(Math.random()*temp.size()); //Creates a random int between 0 and the size of temp.
			s[x] = temp.get(rand); //Sets the index of the given array to a random variable in the rest temp.
			temp.remove(rand); //Removes the string at that same index to prevent duplicates.
		}
	}
	
	/**
	 * Calls the {@link Arrays#sort(Object[])} method on an array of Strings.
	 * @param s - The array of Strings to sort.
	 */
	private static void sortStringArray(String[] s)
	{
		Arrays.sort(s);
	}
	
	/**
	 * Calls the {@link Collections#reverse(java.util.List)} method on an array of Strings.
	 * @param s - The array of Strings to reverse.
	 */
	private static void reverseStringArray(String[] s)
	{
		Collections.reverse(Arrays.asList(s));	
	}
	
	public static void main(String[] args)
	{
		String[] copy;
		Scanner in = new Scanner(System.in);
	
		while(true)
		{
			copy = Arrays.copyOf(DAYS_OF_THE_WEEK, DAYS_OF_THE_WEEK.length); //Resets the copy array
			System.out.printf("Please enter either:\n•day\t•shuffle\n•sort\t•reverse\n•exit\t•shuffle2\n> ");
			switch(in.nextLine())
			{
			case("day"):
				System.out.printf("getRandomDay() returns %s\n", getRandomDay());
				break;
			case("sort"):
				sortStringArray(copy);
				System.out.printf("sortStringArray(%s) yields\n\t%s\n", 
					Arrays.toString(DAYS_OF_THE_WEEK), Arrays.toString(copy));
				break;
			case("shuffle"):
				shuffleStringArray(copy);
				System.out.printf("shuffleStringArray(%s) yields\n\t%s\n",
					Arrays.toString(DAYS_OF_THE_WEEK), Arrays.toString(copy));
				break;
			case("reverse"):
				reverseStringArray(copy);
				System.out.printf("reverseStringArray(%s) yields\n\t%s\n",
						Arrays.toString(DAYS_OF_THE_WEEK), Arrays.toString(copy));
				break;
			case("shuffle2"): 
				shuffleStringArray2(copy);
				System.out.printf("shuffleStringArray2(%s) yields \n\t%s\n", 
						Arrays.toString(DAYS_OF_THE_WEEK), Arrays.toString(copy));
				break;
			case("exit"):
				System.out.println("Terminating Program... Goodbye!");
				in.close();
				System.exit(0);
			default:
				System.out.println("[ArrayTester] Invalid input, please try again.");
			}
		}
	}
}