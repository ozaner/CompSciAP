package unit2;
import java.util.Arrays;
import java.util.List;

public class Array2DExamples {

	/**
	 * Demonstrate 2D arrays.
	 * @param args   none expected
	 */
	public static void main(String[] args) {	
		System.out.println("\n2D Array Examples\n");
		final int ROWS = 3, COLS = 3;
		int[][] table = new int[ROWS][COLS];
		
		// a useful idiom for turning a sequence of numbers
		// into 2D array indexes
		for (int i = 0; i < ROWS * COLS; i++)
			table[i/COLS][i%COLS] = i + 1;
		
		// printing out the 2D int array
		print2DArray(table);
		System.out.println();
		
		
		// a useful idiom for creating a string array by splitting
		// a string based on some delimiter
		String[] alphabetArray = "abcdefghijklmnopqrstuvwxyz".split("");
		String[][] alphabetTable = new String[2][13];
		
		// filling up a 2D array from a 1D array
		for (int i = 0; i < alphabetArray.length; i++)
			alphabetTable[i/13][i%13] = alphabetArray[i];
		
		// printing out the 2D String array
		print2DArray(alphabetTable);
		System.out.println();
		
		
		// useful idiom for converting an array to a list
		List<String> alphabetList = Arrays.asList(alphabetArray);
		
		// filling up a 2D array from a list
		for (int i = 0; i < alphabetArray.length; i++)
			alphabetTable[i/13][i%13] = alphabetList.get(i);
		
		// printing out the 2D String array
		print2DArray(alphabetTable);
		System.out.println();
	}
	
	/**
	 * 	These printing methods demonstrate the standard approach
	 *  to iterating through 2D rows and columns
	 * @param arr
	 */
	public static void print2DArray(Object[][] arr) {
		for (int r = 0; r < arr.length; r++) {
			for (int c = 0; c < arr[r].length; c++) {
				System.out.print(arr[r][c] + " ");
			}
			System.out.println();
		}		
	}
	
	public static void print2DArray(int[][] arr) {
		for (int r = 0; r < arr.length; r++) {
			for (int c = 0; c < arr[r].length; c++) {
				System.out.print(arr[r][c] + " ");
			}
			System.out.println();
		}		
	}
}
