package unit1.OtherExamples;

import java.util.ArrayList;
import java.util.Arrays;

public class ArrayExamples {

	/**
	 * Demonstrate arrays and ArrayLists.
	 * @param args
	 */
	public static void main(String[] args) {	
		arrayExample();
		arrayListExample();
	}
	
	/**
	 * Demonstrate arrays.
	 */
	public static void arrayExample() {
		// an array type is declared with special notation ([])
		// this just declares an array reference whose value is null
		int[] myArray;
		// this allocates the storage for the array
		// the array reference is no longer null, but each array value
		// is not initialized yet.
		myArray = new int[3];
		// this initializes the individual array values
		myArray[0] = myArray[1] = myArray[2] = -1;

		// you can do the first two steps together
		int[] myArray1 = new int[3];
		myArray1[0] = myArray1[1] = myArray1[2] = -1;

		// or you can do all three steps together (notice that this
		//   special initialization syntax omits the ‘new’ operator)
		// you must have as many values as the array size
		int[] myArray2 = {1, 2, 4, 8, 16};

		System.out.println("(initially)\nmyArray1" + Arrays.toString(myArray1));
		System.out.println("myArray2" + Arrays.toString(myArray2));

		// you can reassign an array reference to an existing array
		// the old array will be garbage collected
		myArray1 = myArray2;
		System.out.println("\nafter myArray1 = myArray2\nmyArray1" + Arrays.toString(myArray1));
		System.out.println("myArray2" + Arrays.toString(myArray2));

		myArray1[0] = 0;      // this implicitly changes myArray2[0] as well
		System.out.println("\nafter myArray1[0] = 0\nmyArray1" + Arrays.toString(myArray1));
		System.out.println("myArray2" + Arrays.toString(myArray2));
		
		// if you really want to copy the array, you have to use
		// System.arraycopy(fromArray,fromPos,toArray,toPos,length);
		myArray2 = new int[5];
		System.arraycopy(myArray1,0,myArray2,0,5);
		myArray1[0] = 100;  // this no longer changes myArray2
		System.out.println("\nafter copying myArray1 to myArray2 and setting myArray1[0] = 100\nmyArray1" + Arrays.toString(myArray1));
		System.out.println("myArray2" + Arrays.toString(myArray2));
		
		// another possibility is to use the Arrays.copyOf method which creates the
		// target array and then (internally) calls System.arraycopy to do the copying.
		myArray2 = Arrays.copyOf(myArray1, 5);
		System.out.println("\nafter copying myArray1 to myArray2 using Arrays.copyOf\nmyArray1" + Arrays.toString(myArray1));
		System.out.println("myArray2" + Arrays.toString(myArray2));
	}
	
	/**
	 * Demonstrate ArrayLists.
	 */
	public static void arrayListExample() {
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
