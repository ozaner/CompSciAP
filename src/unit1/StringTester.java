package unit1;

import java.util.Random;
import java.util.Scanner;

/**
 * This a "Self-Test" on using Strings.<br><br>
 * 
 * AP Computer Science<br>
 * Dr. Jones<br>
 * October 16, 2015
 * @author Ozaner Hansha
 */
public class StringTester
{
	public static final String[] DAYS_OF_WEEK = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
	
	/**
	 * This method returns a random string from the {@link #DAYS_OF_WEEK} array using {@link Math#random()}.
	 * @return A random day of the week as per {@link #DAYS_OF_WEEK}.
	 */
	private static String getRandomDay1()
	{
		return DAYS_OF_WEEK[(int)(Math.random()*DAYS_OF_WEEK.length)];
	}
	
	/**
	 * This method returns a random string from the {@link #DAYS_OF_WEEK} array using {@link Random}.
	 * @return A random day of the week as per {@link #DAYS_OF_WEEK}.
	 */
	private static String getRandomDay2()
	{
		Random rand = new Random();
		return DAYS_OF_WEEK[rand.nextInt(DAYS_OF_WEEK.length)];
	}
	
	/**
	 * This method compares 2 Strings an returns the one that appears 
	 * first alphabetically as per the {@link String#compareTo(String)} method.
	 * 
	 * @param str1 - 1st String to compare.
	 * @param str2 - 2nd String to compare.
	 * @return The String that appears first.
	 */
	private static String firstString(String str1, String str2)
	{
		if(str1.compareTo(str2) < 0)
		{
			return str1;
		}
		return str2;
	}
	
	/**
	 * This method returns a boolean based on whether or not a char appears in a String.
	 * 
	 * @param c - The character to check the String for.
	 * @param str - The string to check the char in.
	 * @return True if the given char is appears in the given String.
	 */
	private static boolean charIsInString(char c, String str)
	{
		return str.indexOf(c) > -1;
	}
	
	/**
	 * This method returns the amount of times a certain character appears in a string.
	 * 
	 * @param c - The char to count in the given String.
	 * @param str - The string to check for the given Character.
	 * @return The amount of times the given character appears in the given string.
	 */
	private static int charCount(char c, String str)
	{
		int count = 0;
		for(int i = 0; i < str.length(); i++)
		{
			if(str.charAt(i) == c)
				count++;
		}
		return count;
	}
	
	/**
	 * This method formats a 10-digit NANP (U.S & Canada) phone number from a string.<br>
	 * Ex. "1234567890" returns "(123) 456-7890.
	 * 
	 * @param str - The phone number to be translated.
	 * @return A formated NANP phone number.
	 */
	private static String convertPhoneFormat(Long number)
	{
		String str = Long.toString(number);
		//Checks to see if String is a valid number.
		if(str.length() != 10)
		{
			return "Not a valid 10-Digit Phone Number";
		}
		return String.format("(%s) %s-%s", str.substring(0, 3), str.substring(3,6), str.substring(6));
	}
	
	/**
	 * This method takes a given String and returns its revered form.
	 * 
	 * @param str - The string to be reversed.
	 * @return The revered form of the given String.
	 */
	private static String reverse(String str)
	{
		String reversed = "";
		for(int c = str.length()-1; c >= 0; c--)
		{
			reversed += str.charAt(c);
		}
		return reversed;
	}
	
	/**
	 * This method tests the string related methods in {@link StringTester}.
	 * 
	 * @param args - N/A
	 */
	public static void main(String[] args)
	{
		Scanner in = new Scanner(System.in);
		System.out.println("[StringTester]WARNING THIS PROGRAM CANNOT HANDLE INCORRECT INPUT!");
		while(true)
		{
			System.out.println("Enter a number to test a method.");
			System.out.println("1. getRandomDay1\t2. getRandomDay2\n3. firstString\t\t4. charIsInString\n"
					+ "5. charCount\t\t6. convertPhoneFormat\n7. reverse\t\t8. Terminate");

			switch(in.nextInt())
			{
			case 1:
				System.out.println(getRandomDay1());
				break;
			case 2:
				System.out.println(getRandomDay2());
				break;
			case 3:
				System.out.println("Enter 2 Strings...");
				System.out.println(firstString(in.next(),in.next()));
				break;
			case 4:
				System.out.println("Enter a char and a String...");
				System.out.println(charIsInString(in.next().charAt(0), in.next()));
				break;
			case 5:
				System.out.println("Enter a char and a String...");
				System.out.println(charCount(in.next().charAt(0), in.next()));
				break;
			case 6:
				System.out.println("Enter a 10 digit long...");
				System.out.println(convertPhoneFormat(in.nextLong()));
				break;
			case 7:
				System.out.println("Enter a String...");
				System.out.println(reverse(in.next()));
				break;
			case 8:
				in.close();
				System.exit(0);
			default: System.out.println("[StringTester] ERROR: Invalid Integer - Try Again");
			}
		}
	}
}
