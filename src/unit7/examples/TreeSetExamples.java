package unit7.examples;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.TreeSet;

public class TreeSetExamples {
	
	static String[] colors = {  // roygbiv rainbow spectrum
			"red", "orange", "yellow", "green",
			"blue", "indigo", "violet"};

	/**
	 * Demonstrate ArrayLists.
	 * @param args   none expected
	 */
	public static void main(String[] args) {	
		// Initializing a TreeSet from a list of values.
		TreeSet<String> set5 = new TreeSet<String>(Arrays.asList("red",
				"orange", "yellow", "green", "blue", "indigo", "violet"));
		System.out.println("\nTreeSet<String> set5 = " + set5);

		// Initializing a TreeSet from an array.
		TreeSet<String> set6 = new TreeSet<String>(Arrays.asList(colors));
		System.out.println("TreeSet<String> set6 = " + set6);

		// Initializing an TreeSet from a Collection.
		TreeSet<String> set7 = new TreeSet<String>(new LinkedList<String>(
				Arrays.asList(colors)));
		System.out.println("TreeSet<String> set7 = " + set7);

		System.out.print("Iterator prints (ordered) ");
		for (Iterator<String> itr = set5.iterator(); itr.hasNext();)
			System.out.print((String) itr.next() + " ");
		System.out.println();
	}
	
	/*
	Iterator vs ListIterator
    ------------------------
	1) Iterator is for traversing both List and Set.
       ListIterator is for traversing List only.

	2) Iterator traverses in the forward direction only.
       ListIterator traverses in either a List in both the forward and backward directions.

	3) We cannot obtain indexes while using Iterator
       We can obtain indexes at any point of time while traversing a list using ListIterator.
       The methods nextIndex() and previousIndex() are used for this purpose.

	4) We cannot add an element to collection while traversing it using Iterator.
	   It throws ConcurrentModificationException when you try to do it.

	   We can add element at any point of time while traversing a list using ListIterator.

	5) We cannot replace the existing element value when using Iterator.
       By using set(E e) method of ListIterator we can replace the last element returned by next() or previous() methods.

	6) Methods of Iterator:
	     hasNext()
	     next()
	     remove()
	     
	   Methods of ListIterator:
    	 add(E e)
	     hasNext()
	     hasPrevious()
	     next()
	     nextIndex()
	     previous()
	     previousIndex()
	     remove()
	     set(E e)
	*/
}
