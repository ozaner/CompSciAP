package unit1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * This program:<br>
 * -takes a word from the user,<br>
 * -translates it to Pig Latin, <br>
 * -then prints that translation to the console.<br><br>
 * The program also outputs occasional pig onomatopoeias to the console as well<br><br>
 * 
 * AP Computer Science<br>
 * Dr. Jones<br>
 * Class 7<br>
 * @author Ozaner Hansha
 */
public class EnglishToPigLatin
{
	/**
	 * A set of the lowercase English Alphabet in order.
	 */
	public static final Character[] ALPHABET = 
		{'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
	
	/**
	 * A set of strings representing the pronunciation of the English {@link #ALPHABET}.
	 */
	public static final String[] ALPHABET_PRONUNCIATION = 
		{"ay","bee","cee","dee","ee","ef","gee","aitch","eye","jay","kay","el","em","en",
				"oh","pee","kyoo","ar","ess","tee","you","vee","double-you","ex","wy","zee"};
	/**
	 * This is an array of the lowercase vowels of the English alphabet in char form.
	 */
	public static final Character[] VOWELS = {'a','e','i','o','u'};
	
	/**
	 * A list of words that begin with a silent H.
	 */
	public static final String[] SILENT_H_WORDS = {"honor", "honest", "honestly", "honesty", "heir", "hour", "hourly"};
	
	/**
	 * A set of strings of Pig onomatopoeias in several languages.
	 */
	public static final String[] PIG_NOISES = 
		{"Oink!", "Buu!", "Hunk!", "Grunz..", "Nöff..", "Sweek!", "Quiek!", "¡Oinc!", "Ghrutu!", "Knor!", "Kwi!", "Röf!"};
	
	/**
	 * The {@link Scanner} used to gather input from the console to be evaluated.
	 */
	private static final Scanner IN = new Scanner(System.in);
	
	/**
	 * The phrase to end the program. It is "done" in Pig Latin.
	 */
	private static final String SENTINEL = "";
	
	/**
	 * An array of Strings that are currently being evaluated.
	 */
	private static ArrayList<String> currentWords = new ArrayList<String>();
	
	/**
	 * An array of boolean values that correspond the the capitalization of words in the {@link #currentWords} list.
	 */
	private static ArrayList<Boolean> capitalization = new ArrayList<Boolean>();
	
	/**
	 * Checks if a given String is a single character in the English {@link #ALPHABET}.<br>
	 * Ex. j --> jay
	 * 
	 * @param word - The String to check.
	 * @return true if the given String is a single character in the {@link #ALPHABET}.
	 */
	public static boolean isSingleLetter(String word)
	{
		return word.length() == 1 && Character.isLetter(word.charAt(0)); //If word is 1 char long and contains a letter of the ALPHABET.
	}
	
	/**
	 * This method returns whether or not this word is pronounced with a vowel at the start
	 * 
	 * @param word - The word to check for a vowel sound.
	 * @return True if this is a vowel sound word, false if not.
	 */
	public static boolean startsWithVowelSound(String word)
	{
		return Arrays.asList(VOWELS).contains(word.charAt(0)) || Arrays.asList(SILENT_H_WORDS).contains(word);
	}
	
	/**
	 * This method returns the index of the first vowel in a given String.
	 * 
	 * @param word - The String to evaluate.
	 * @return The index of the first vowel of the given String, -1 if no vowels.
	 */
	public static int indexOfFirstVowel(String word)
	{
		for(int i = 0; i < word.length(); i++)
		{
			for(int v = 0; v < VOWELS.length; v++)
			{
				if(word.charAt(i) == VOWELS[v])
					return i;
			}
		}
		return -1;
	}
	
	/**
	 * Translated individual letters to Pig Latin.
	 * 
	 * @param word - A character in the {@link #ALPHABET} to convert to Pig Latin.
	 * @return a translation of the given char to Pig Latin
	 */
	public static String translateLetter(String word, boolean capitalization)
	{
		return translate(ALPHABET_PRONUNCIATION[Arrays.asList(ALPHABET).indexOf(word.charAt(0))], capitalization);
	}
	
	/**
	 * This method translates a word to Pig Latin with the Vowel Sound rules.<br>
	 * Ex. elephant --> elephantyay.
	 * 
	 * @param word - A word to be translated to Pig Latin with the Vowel Sound rules.
	 * @param capitilized - Whether or not this word should be capitalized.
	 * @return the given word translated to Pig Latin.
	 */
	public static String translateVowelSound(String word)
	{
		return word + "yay"; //ABCD --> ABCDyay
	}
	
	/**
	 * This method translates a given word to Pig Latin.<br>
	 * Ex. Hello --> ellohay
	 * 
	 * @param word - A word to be translated to Pig Latin.
	 * @param capitilized - Wheather or not this word should be capitalized.
	 * @return The Pig Latin translation of the given word.
	 */
	public static String translateNormal(String word)
	{
		//for "qu" words.
		if(indexOfFirstVowel(word) > word.indexOf("qu"))
		{
			return word.substring(word.indexOf("qu") + 2) + word.substring(0, word.indexOf("qu") + 2) + "ay";
		}
		
		//For words with no vowels.
		if(indexOfFirstVowel(word) <= -1)
			return word + "ay";
		
		//Non "qu" words.
		String prefix = word.substring(0, indexOfFirstVowel(word));
		String suffix = word.substring(indexOfFirstVowel(word));
		return suffix + prefix + "ay"; //Dirty --> irtyday
	}
	
	/**
	 * This method returns a given word's Pig Latin translation.
	 * 
	 * @param word - A string to be translated to Pig Latin.
	 * @param capitilized - Wheather or not this word should be capitalized.
	 * @return The Pig Latin translation of the word given, returns null if String given is null.
	 */
	public static String translate(String word, boolean capitalized)
	{
		String tempWord = word;
		if(word == null) //If word is null.
			return null;
		else if(isSingleLetter(word)) //If word is just a single letter
			tempWord = translateLetter(word, capitalized);
		else if(startsWithVowelSound(word)) //If word starts with a vowel sound.
			tempWord = translateVowelSound(word);
		else //if word passes all other tests (A normal word).
			tempWord = translateNormal(word);
		
		//Checks for Capitalization
		if(capitalized)
			tempWord = tempWord.substring(0, 1).toUpperCase() + tempWord.substring(1);
		return tempWord;
	}
	
	/**
	 * @return A random string from the {@link #PIG_NOISES} array.
	 */
	public static String speak()
	{
		return PIG_NOISES[(int)(Math.random()*PIG_NOISES.length)]; //random int from 0 to Length of array (12)
	}
	
	/**
	 * This Program takes a word or sentence(Input),<br>
	 * translates it to Pig Latin(Evaluate),<br>
	 * then prints it to the console(Print).<br><br>
	 * 
	 * Program also prints out random items from {@link #PIG_NOISES} every translation.
	 * 
	 * @param args - no command line arguments expected
	 */
	public static void main(String[] args) 
	{
		System.out.println("This program translates words into Pig Latin.\n"); //Initialization Message.
		
		//Print Loop
		while(true)
		{
			System.out.print("Next Input > ");
			Scanner lineIn = new Scanner(IN.nextLine());
			
			//Gathers all words delimited by a space into currentWords
			while(lineIn.hasNext())
			{
				String tempWord = lineIn.next(); //Stores this word for analysis.
				capitalization.add(Character.isUpperCase(tempWord.charAt(0))); //Adds its capitalization status to list.
				currentWords.add(tempWord.toLowerCase()); //Adds this word to list (in lower case)
			}
			System.out.print("Porkey Says >");
			
			//Terminates Program via Sentinel
			if(currentWords.isEmpty())
			{
				lineIn.close();
				IN.close();
				System.out.println(" " + translate("goodbye", true) + "...");
				System.exit(0);
			}
			
			//Outputs all Strings in currentWords translated
			for(int w = 0; w < currentWords.size(); w++)
			{
				System.out.print(" " + translate(currentWords.get(w), capitalization.get(w)));
			}
			currentWords.clear();
			capitalization.clear();
			System.out.print(" // " + speak() + "\n\n");
		}
	}
}