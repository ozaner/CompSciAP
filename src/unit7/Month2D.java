package unit7;
import java.util.HashMap;                     
 
/**
 * Uses a 2D array to help print out a calendar for the year of the form:
 * 
 January
  Sun Mon Tue Wed Thu Fri Sat
                      	1   2
  	3   4   5   6   7   8   9
   10  11  12  13  14  15  16
   17  18  19  20  21  22  23
   24  25  26  27  28  29  30
   31                        

 February
  Sun Mon Tue Wed Thu Fri Sat
        1   2   3   4   5   6
    7   8   9  10  11  12  13
   14  15  16  17  18  19  20
   21  22  23  24  25  26  27
   28
   
   etc.
   
 * @author markjones
 *
 */
public class Month2D {

	/**
	 * Formats and prints each month.  We make extensive use of enumerations for
	 * the months and days of the week.  A 6 x 7 2D-array (monthTable) represents the
	 * layout of days for the month.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		for (Month m : Month.values()) {
			System.out.printf("%s\n", m);
			for (DayOfWeek d : DayOfWeek.values()) {
				System.out.printf("%4s", d.toString().substring(0, 3));
			}
			System.out.printf("\n");
			String[][] monthTable = makeMonthClean(2016, m);  // layout of days for month m in year 2016
			for (int r = 0; r < monthTable.length; r++) {
				for (int c = 0; c < monthTable[r].length; c++) {
					System.out.printf("%4s", monthTable[r][c]);
				}
				System.out.printf("\n");
			}
			System.out.printf("\n");
		}
	}
	
	/**
	 * Computes a 6x7 table with the days of the month. 
	 * @param year
	 * @param month
	 * @return
	 */
	public static String[][] makeMonthOLD(int year, Month month) {
		String[][] monthTable = new String[6][7];
		DayOfWeek startDayOfWeek = dow2016.get(month); // computes the starting day of week
		
		for(int i = startDayOfWeek.ordinal(); i < month.getDays() + startDayOfWeek.ordinal(); i++)
			monthTable[i/monthTable[0].length][i%monthTable[0].length] = "" + (i -startDayOfWeek.ordinal() + 1);
		
		for(int i = 0; i < monthTable[0].length * monthTable.length; i++)
			if(monthTable[i/monthTable[0].length][i%monthTable[0].length] == null)
				monthTable[i/monthTable[0].length][i%monthTable[0].length] = "";

		// Write code here to correctly fill up the table.
		// Hint: You may find the built-in ordinal() method for DayOfWeek to be useful.
		
		return monthTable;
	}
	
	/**
	 * Computes a 6x7 table with the days of the month. 
	 * @param year
	 * @param month
	 * @return
	 */
	public static String[][] makeMonthCleanOLD(int year, Month month) {
		String[][] monthTable = new String[6][7];
		DayOfWeek startDayOfWeek = dow2016.get(month); // computes the starting day of week
		
		int ROWS = monthTable.length;
		int COLS = monthTable[0].length;
		int dayShift = startDayOfWeek.ordinal();
		
		for(int i = dayShift; i < month.getDays() + dayShift; i++)
			monthTable[i/COLS][i%COLS] = "" + (i -dayShift + 1);
		
		for(int i = 0; i < ROWS*COLS; i++)
			if(monthTable[i/COLS][i%COLS] == null)
				monthTable[i/COLS][i%COLS] = "";

		// Write code here to correctly fill up the table.
		// Hint: You may find the built-in ordinal() method for DayOfWeek to be useful.
		
		return monthTable;
	}
	
	/**
	 * Computes a 6x7 table with the days of the month. 
	 * @param year
	 * @param month
	 * @return
	 */
	public static String[][] makeMonth(int year, Month month) {
		String[][] monthTable = new String[6][7];
		DayOfWeek startDayOfWeek = dow2016.get(month); // computes the starting day of week
		
		for(int i = 0; i < monthTable.length*monthTable[0].length; i++)
			monthTable[i/monthTable[0].length][i%monthTable[0].length] = i < startDayOfWeek.ordinal()
				|| (i -startDayOfWeek.ordinal() + 1) > month.getDays()?  "" : "" + (i -startDayOfWeek.ordinal() + 1);

		// Write code here to correctly fill up the table.
		// Hint: You may find the built-in ordinal() method for DayOfWeek to be useful.
		
		return monthTable;
	}
	
	/**
	 * Computes a 6x7 table with the days of the month. 
	 * @param year
	 * @param month
	 * @return
	 */
	public static String[][] makeMonthClean(int year, Month month) {
		String[][] monthTable = new String[6][7];
		DayOfWeek startDayOfWeek = dow2016.get(month); // computes the starting day of week
		
		int ROWS = monthTable.length;
		int COLS = monthTable[0].length;
		int dayShift = startDayOfWeek.ordinal();
		
		for(int i = 0; i < ROWS*COLS; i++)
				monthTable[i/COLS][i%COLS] = i < dayShift || (i -dayShift + 1) > month.getDays()?  "" : "" + (i -dayShift + 1);

		// Write code here to correctly fill up the table.
		// Hint: You may find the built-in ordinal() method for DayOfWeek to be useful.
		
		return monthTable;
	}

	// Usage: dow2016.get(month) ==> starting day of week for each month in 2016
	// This is a declarative approach to encoding the mapping from month to starting day of the week.

	// Note the use of a static block below:  static { . . . }
	// This block is executed at run time once, when the class is initialized, before program
	// execution starts in main();
	
	// Could you write a method instead which would take the day of the week that January starts on
	// and fill up the mapping?  Hint: Use the numDays() method in the Month enumeration.
	
	private static final HashMap<Month, DayOfWeek> dow2016 = new HashMap<Month, DayOfWeek>();
	static {
		dow2016.put(Month.JANUARY,   DayOfWeek.FRIDAY);
		dow2016.put(Month.FEBRUARY,  DayOfWeek.MONDAY);
		dow2016.put(Month.MARCH,     DayOfWeek.TUESDAY);
		dow2016.put(Month.APRIL,     DayOfWeek.FRIDAY);
		dow2016.put(Month.MAY,       DayOfWeek.SUNDAY);
		dow2016.put(Month.JUNE,      DayOfWeek.WEDNESDAY);
		dow2016.put(Month.JULY,      DayOfWeek.FRIDAY);
		dow2016.put(Month.AUGUST,    DayOfWeek.MONDAY);
		dow2016.put(Month.SEPTEMBER, DayOfWeek.THURSDAY);
		dow2016.put(Month.OCTOBER,   DayOfWeek.SATURDAY);
		dow2016.put(Month.NOVEMBER,  DayOfWeek.TUESDAY);
		dow2016.put(Month.DECEMBER,  DayOfWeek.THURSDAY);
	}
}
