package unit1;

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
				"oh","pee","cue","ar","ess","tee","you","vee","double-you","ex","wy","zee"};
	/**
	 * This is an array of the lowercase vowels of the English alphabet in char form.
	 */
	public static final Character[] VOWELS = {'a','e','i','o','u'};
	
	/**
	 * A list of words that begin with a silent H.
	 */
	public static final String[] SILENT_H_WORDS = {"Honor", "Honest", "Honestly", "Honesty", "Heir", "Hour", "Hourly"};
	
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
	private static final String SENTINEL = "oneday";
	
	/**
	 * The string that is currently being evaluated.
	 */
	private static String currentWord = new String();
	
	/**
	 * Checks if a given String is a single character in the English {@link #ALPHABET}.<br>
	 * Ex. j --> jay
	 * 
	 * @param word - The String to check.
	 * @return true if the given String is a single character in the {@link #ALPHABET}.
	 */
	public static boolean isSingleLetter(String word)
	{
		if(word.length() == 1 && Arrays.asList(ALPHABET).contains(word.charAt(0))) //If word is 1 char long and contains a letter of the ALPHABET.
		{
			return true;
		}
		return false;
	}
	
	/**
	 * This method returns whether or not this word is pronounced with a vowel at the start
	 * 
	 * @param word - The word to check for a vowel sound.
	 * @return True if this is a vowel sound word, false if not.
	 */
	public static boolean startsWithVowelSound(String word)
	{
		if(Arrays.asList(VOWELS).contains(word.charAt(0))) //If the word starts with a vowel
		{
			return true;
		}
		else if(Arrays.asList(SILENT_H_WORDS).contains(word)) //If the word begins with a silent H
		{
			return true;
		}
		return false; //If fails both tests
	}
	
	/**
	 * This method returns the index of the first vowel in a given String.
	 * 
	 * @param word - The String to evaluate.
	 * @return The index of the first vowel of the given String.
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
		return 0;
	}
	
	/**
	 * Translated individual letters to Pig Latin.
	 * 
	 * @param word - A character in the {@link #ALPHABET} to convert to Pig Latin.
	 * @return a translation of the given char to Pig Latin
	 */
	public static String translateLetter(String word)
	{
		String wordPronunciation = ALPHABET_PRONUNCIATION[Arrays.asList(ALPHABET).indexOf(word.charAt(0))];
		if(indexOfFirstVowel(wordPronunciation) > 0)
			return wordPronunciation + "ay";
		return wordPronunciation + "yay";
	}
	
	/**
	 * This method translates a word to Pig Latin with the Vowel Sound rules.<br>
	 * Ex. elephant --> elephantyay.
	 * 
	 * @param word - A word to be translated to Pig Latin with the Vowel Sound rules.
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
	 * @return The Pig Latin translation of the given word.
	 */
	public static String translateNormal(String word)
	{
		if(word.startsWith("qu")) //If the word starts with "qu"
		{
			return word.substring(2) + "quay"; //Quack --> ackquay
		}
		
		//Non "qu" words
		String prefix = word.substring(0, indexOfFirstVowel(word));
		String suffix = word.substring(indexOfFirstVowel(word));
		return suffix + prefix + "ay"; //Dirty --> irtyday
	}
	
	/**
	 * This method returns a given word's Pig Latin translation.
	 * 
	 * @param word - A string to be translated to Pig Latin.
	 * @return The Pig Latin translation of the word given, returns null if String given is null.
	 */
	public static String translate(String word)
	{
		if(word == null) //If word is null.
			return null;
		else if(isSingleLetter(word)) //If word is just a single letter
			return translateLetter(word);
		else if(startsWithVowelSound(word)) //If word starts with a vowel sound.
			return translateVowelSound(word);
		else //if word passes all other tests (A normal word).
			return translateNormal(word);
	}
	
	/**
	 * @return A random string from the {@link #PIG_NOISES} array.
	 */
	public static String speak()
	{
		return PIG_NOISES[(int)(Math.random()*PIG_NOISES.length)]; //random int from 0 to Length of array (12)
	}
	
	/**
	 * This Program takes a word(Input), translates it to Pig Latin(Evaluate), then prints it to the console(Print).<br>
	 * Program also prints out random items from {@link #PIG_NOISES} every translation.
	 * 
	 * @param args - no command line arguments expected
	 */
	public static void main(String[] args) 
	{
		System.out.printf("This program translates words into Pig Latin.\nEnter '%s' to quit.\n\n", SENTINEL); //Initialization Message.
		
		//Print Loop
		while(true)
		{
			System.out.print("Next Input > ");
			currentWord = IN.next().toLowerCase();
			
			System.out.print("Porky Says > ");
			if(currentWord.equals(SENTINEL)) //If the input was the SENTINEL(oneday)
			{
				System.out.print(translate("goodbye"));
				System.exit(0); //Terminate Program
			}
			else //If the input was NOT the SENTINEL(oneday)
				System.out.println(translate(currentWord) + " // " + speak() + "\n"); //Translate it to Pig Latin
		}
	}
	
//	public static void main(String[] args) 
//	{
//		System.out.printf("This program translates words into Pig Latin.\nEnter '%s' to quit.\n\n", SENTINEL); //Initialization Message.
//		
//		//Print Loop
//		while(true)
//		{
//			System.out.print("Next Input > ");
//			Scanner lineIn = new Scanner(IN.next());
//			while(lineIn.hasNext())
//			{
//				currentWords.add(lineIn.next());
//			}
//			
//			System.out.print("Porky Says >");
//			while(!currentWords.isEmpty())
//			{
//				for(String word: currentWords)
//				{
//					if(word.equals(SENTINEL)) //If the input was the SENTINEL(oneday)
//					{
//						System.out.print(translate("goodbye"));
//						System.exit(0); //Terminate Program
//					}
//					else //If the input was NOT the SENTINEL(oneday)
//					{
//						System.out.println(" " + translate(word) + " // " + speak() + "\n"); //Translate it to Pig Latin
//						currentWords.remove(word); //removes the word just translated from the currentWords list.
//					}
//				}
//			}
//		}
//	}
}
