package unit10.examples;
/**
   This program checks for palindromes.
 */
public class Palindrome {
	
	private static String[] inputs = {"otto", "A man, a plan, a canal: Panama", "racer"};
	
	public static void main(String[] args) {
		for (String s : inputs)      
			System.out.println("\"" + s + "\"" +
					(isPalindrome(s) ? " is " : " isn\'t ") + "a palindrome");
	}

	/**
	 * Return whether a given string is a palindrome.  
	 * It ignores case and non-alphabetic characters.
	 * @param s   the given string
	 * @return    true if the string is a palindrome, false otherwise
	 */
	private static boolean isPalindrome(String s) {
		s = normalizeString(s);
		boolean bool;
		
		if(s.equals("") || s.length() == 1)
			return true;
		else if(s.charAt(0) == s.charAt(s.length()-1))
			bool = isPalindrome(s.substring(1,s.length()-1));
		else return false;
		
		return bool;
		
	}
	
	private static String normalizeString(String s) {
		String temp = "";
		for(int x = 0; x < s.length(); x++)
			if((""+s.charAt(x)).matches("[a-zA-Z]"))
				temp += s.charAt(x);
		return temp.toLowerCase();
	}
}

