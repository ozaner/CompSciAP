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
	 * A set of the lowercase Pig Latin Alphabet in order.
	 */
	public static final String[] ALPHABETYAY = 
		{"ay","bee","cee","dee","ee","ef","gee","aitch","eye","jay","kay","el","em","en",
				"oh","pee","que","ar","ess","tee","you","vee","double-you","ex","wy","zee"};
	/**
	 * This is an array of the lowercase vowels of the English alphabet in char form.
	 */
	public static final Character[] VOWELS = {'a','e','i','o','u'};
	
	/**
	 * A list of words that begin with a vowel sound yet are not spelled with one.
	 */
	public static final String[] VOWEL_SOUND_WORDS = {"Honest"};
	
	/**
	 * A set of strings of Pig onomatopoeias in several languages.
	 */
	public static final String[] PIG_NOISES = 
		{"Oink!", "Buu!", "Hunk!", "Grunz..", "Nöff..", "Sweek!", "Quiek!", "¡Oinc!", "-9J F!", "×Ò×Ò", "Kwi!", "Röf!"};
	
	/**
	 * The {@link Scanner} used to gather input from the console to be evaluated.
	 */
	static final Scanner IN = new Scanner(System.in);
	
	/**
	 * The phrase to end the program. It is "done" in Pig Latin.
	 */
	static final String SENTINEL = "oneday";
	
	/**
	 * The string that is currently being evaluated.
	 */
	static String currentWord = new String();
	
	/**
	 * This method returns a given word's Pig Latin translation.
	 * 
	 * @param word - A string to be translated to Pig Latin. 
	 * @return The Pig Latin translation of the word given.
	 */
	public static String translate(String word)
	{
		if(word == null) //If word is null.
			return null;
		else if(isSingleLetter(word)) //If word is just a single letter
			return translateLetter(word.charAt(0));
		else if(startsWithVowelSound(word)) //If word starts with a vowel sound.
			return translateVowelSound(word);
		else //if word passes all other tests (A normal word).
			return translateNormal(word);
	}
	
	/**
	 * This method translates a given word to Pig Latin.
	 * 
	 * @param word - A word to be translated to Pig Latin.
	 * @return The Pig Latin translation of the given word.
	 */
	public static String translateNormal(String word)
	{
		return word.substring(1) + word.substring(0, 1) + "ay"; //ABCD --> BCDAay
	}
	
	public static boolean isSingleLetter(String word)
	{
		if(word.length() == 1 && Arrays.asList(ALPHABET).contains(word.charAt(0)))
		{
			return true;
		}
		return false;
	}
	
	public static String translateLetter(char word)
	{
		return ALPHABETYAY[Arrays.asList(ALPHABET).indexOf(word)];
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
		else if(Arrays.asList(VOWEL_SOUND_WORDS).contains(word)) //If the word is a member of VOWEL_SOUND_WORD
		{
			return true;
		}
		return false; //If fails both tests
	}
	
	/**
	 * This method translates a word to Pig Latin with the Vowel Sound rules.<br>
	 * Ex. elephant --> elephantfay.<br>
	 * 
	 * @param word - A word to be translated to Pig Latin with the Vowel Sound rules.
	 * @return the given word translated to Pig Latin.
	 */
	public static String translateVowelSound(String word)
	{
		return word + "yay"; //ABCD --> ABCDyay
	}
	
	/**
	 * @return A random string from the {@link #PIG_NOISES} array.
	 */
	public static String speak()
	{
		return PIG_NOISES[(int)(Math.random()*PIG_NOISES.length)]; //random int from 0 to Length of array (12)
	}
	
	/**
	 * This Program takes a word(Input), translates it to Pig Latin(Evaluate), then prints it to the console(Print).
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
}
