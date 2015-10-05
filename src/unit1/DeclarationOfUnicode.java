package unit1;

/**
 * This program prints the prelude to the Declaration of Independence 
 * to the console in English, French, Spanish, Russian.<br><br>
 * 
 * AP Computer Science<br>
 * Dr. Jones<br>
 * Class 7
 * @author Ozaner Hansha
 */
public class DeclarationOfUnicode
{
	//Unicode Constants
	
	/**
	 * Unicode Character:<br>
	 * LATIN SMALL LETTER A WITH DIAERESIS (ä)
	 */
	public static final char UMLAUT_a = '\u00E4';
	
	/**
	 * Unicode Character:<br>
	 * LATIN SMALL LETTER O WITH DIAERESIS (ö)
	 */
	public static final char UMLAUT_o = '\u00F6';
	
	/**
	 * Unicode Character:<br>
	 * LATIN SMALL LETTER U WITH DIAERESIS (ü)
	 */
	public static final char UMLAUT_u = '\u00FC';
	
	/**
	 * Unicode Character:<br>
	 * LATIN SMALL LETTER A WITH GRAVE (à)
	 */
	public static final char GRAVE_a = '\u00E0';
	
	/**
	 * Unicode Character:<br>
	 * LATIN SMALL LETTER E WITH ACUTE (é)
	 */
	public static final char ACCENT_e = '\u00E9';
	
	/**
	 * Unicode Character:<br>
	 * LATIN SMALL LETTER O WITH ACUTE (ó)
	 */
	public static final char ACCENT_o = '\u00F3';
	
	/**
	 * Unicode Character:<br>
	 * LATIN SMALL LETTER I WITH ACUTE (í)
	 */
	public static final char ACCENT_i = '\u00ED';
	
	/**
	 * Unicode Character:<br>
	 * LATIN SMALL LETTER U WITH CIRCUMFLEX (û)
	 */
	public static final char CIRCUMFLEX_u = '\u00FC';
	
	
	/**
	 * This method prints the prelude in English.
	 */
	public static void printEnglish()
	{
		System.out.println("(ENGLISH)");
		System.out.print("When in the Course of human events, it becomes necessary for one people to dissolve the \n"
				+ "political bands which have connected them with another, and to assume among the \n"
				+ "powers of the earth, the separate and equal station to which the Laws of Nature and of \n"
				+ "Nature's God entitle them, a decent respect to the opinions of mankind requires that they \n"
				+ "should declare the causes which impel them to the separation.");
	}
	
	/**
	 * This method prints the prelude in German. (Google Translated)
	 */
	public static void printGerman()
	{
		System.out.println("(GERMAN)");
		System.out.format("Wenn im Verlauf der menschlichen Ereignisse, wird es notwendig f%cr ein Volk, die \n"
				+ "politischen B%cnder, die sie mit anderen verbunden sind aufzul%csen und unter den M%cchten \n"
				+ "der Erde %cbernehmen, die getrennte und gleiche Station , an die die Gesetze der Natur \n"
				+ "und der Natur Gott berechtigen, eine anst%cndige gegen%cber den Meinungen der \n"
				+ "Menschheit , dass sie die Ursachen, die sie auf die Trennung veran.",
				UMLAUT_u, UMLAUT_a, UMLAUT_o, UMLAUT_u, UMLAUT_u, UMLAUT_a, UMLAUT_u);
	}
	
	/**
	 * This method prints the prelude in French. (Google Translated)
	 */
	public static void printFrench()
	{
		System.out.println("(FRENCH)");
		System.out.format("Lorsque dans le cours des %cv%cnements humains, il devient n%ccessaire pour un peuple de \n"
				+ "dissoudre les liens politiques qui l' ont attach%c %c un autre et de prendre, parmi les \n"
				+ "puissances de la terre , la place s%cpar%ce et %cgale %c laquelle les lois de la nature et de la \n"
				+ "nature de Dieu lui donnent droit, le respect d%c %c l'opinion de l'humanit%c oblige %c d%cclarer \n"
				+ "les causes qui le d%cterminent %c la s%cparation.",
				ACCENT_e, ACCENT_e, ACCENT_e, ACCENT_e, GRAVE_a, ACCENT_e, ACCENT_e, ACCENT_e,
				GRAVE_a, CIRCUMFLEX_u, GRAVE_a, ACCENT_e, GRAVE_a, ACCENT_e, ACCENT_e, GRAVE_a, ACCENT_e);
	}
	
	/**
	 * This method prints the prelude in Spanish. (Google Translated)
	 */
	public static void printSpanish()
	{
		System.out.println("(SPANISH)");
		System.out.format("Cuando en el curso de los acontecimientos humanos se hace necesario para un pueblo \n"
				+ "disolver los v%cnculos pol%cticos que lo han ligado a otro, y asumir entre los poderes de la \n"
				+ "tierra, el puesto separado e igual a que las leyes de la naturaleza y de la naturaleza de \n"
				+ "Dios le dan derecho, un justo respeto al juicio de la humanidad exige que declare las \n"
				+ "causas que lo impulsan a la separaci%cn.", ACCENT_i, ACCENT_i, ACCENT_o);
	}
	
	/**
	 * This method prints the prelude in Russian. (Google Translated)<br>
	 * NOTE: This method uses Unicode characters directly and does not use any escape sequences.
	 */
	public static void printRussian()
	{
		System.out.println("(RUSSIAN)");
		System.out.print("Когда в ходе человеческих событий становится необходимым для одного народа \n"
				+ "распускать политические полосы , которые связывали их с другими, и занять среди \n"
				+ "держав мира , отдельно и равноправно станция , к которой законы природы и \n"
				+ "Природы Бог дает им , достойный уважения к мнению человечества требует, чтобы \n"
				+ "они объявили причины, которые побуждают их к отделению.");
	}
	
	/**
	 * Main Method.
	 */
	public static void main(String[] args)
	{
		printEnglish();
		System.out.println("\n"); //2 new lines
		printGerman();
		System.out.println("\n"); //2 new lines
		printFrench();
		System.out.println("\n"); //2 new lines
		printSpanish();
		System.out.println("\n"); //2 new lines
		printRussian();
	}
}