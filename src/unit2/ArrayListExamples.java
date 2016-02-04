package unit2;
import java.util.ArrayList;

public class ArrayListExamples {

	/**
	 * Demonstrate ArrayLists.
	 * @param args   none expected
	 */
	public static void main(String[] args) {	
		ArrayList<Integer> myList = new ArrayList<Integer>();  // create an empty list
		int sz = myList.size();     // get the current size of the list
		System.out.println("\n\nThe initial size of myList is " + sz);

		// adding elements to the list
		// myList.add(new Integer(14));    // add object to the end of the list
		myList.add(14);                    // add object to the end of the list
		System.out.println("after adding 14, myList = " + myList);
		myList.add(123);  // note that primitives must be wrapped
		System.out.println("after adding 123, myList = " + myList);
		myList.add(1, -24); // insert object at position 1
		System.out.println("after inserting -24 at position 1, myList = " + myList);

		// getting and (re)setting values
		Integer val = myList.get(0);       // get the value at position 1
		System.out.println("the value at position 0 is " + val);
		myList.set(1, 5);                  // set value at position 1
		System.out.println("after setting 5 at position 1, myList = " + myList);

		// removing elements from the list
		int remval = myList.remove(2); // remove a value
		System.out.println("after removing the value " + remval + " from position 2, myList = " + myList);	
	}
}
