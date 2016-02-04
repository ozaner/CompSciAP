package unit7.examples;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class HashSetExamples {
	
	static String[] colors = {  // roygbiv rainbow spectrum
			"red", "orange", "yellow", "green",
			"blue", "indigo", "violet"};

	/**
	 * Demonstrate ArrayLists.
	 * @param args   none expected
	 */
	public static void main(String[] args) {	
		// Initializing a HashSet from a list of values.
		HashSet<String> set1 = new HashSet<String>(Arrays.asList("red",
				"orange", "yellow", "green", "blue", "indigo", "violet"));
		System.out.println("\nHashSet<String> set1 = " + set1);

		// Initializing a HashSet from an array.
		HashSet<String> set2 = new HashSet<String>(Arrays.asList(colors));
		System.out.println("HashSet<String> set2 = " + set2);

		// Initializing an HashSet from a Collection.
		HashSet<String> set3 = new HashSet<String>(new LinkedList<String>(
				Arrays.asList(colors)));
		System.out.println("HashSet<String> set3 = " + set3);

		System.out.print("Iterator prints ");
		for (Iterator<String> itr = set1.iterator(); itr.hasNext();)
			System.out.print((String) itr.next() + " ");
		System.out.println();
		
		// A tricky way to sort a HashSet
		System.out.println("sorting HashSet set3 = " +
				new TreeSet<String>(set3));
		
		// A tricky way to eliminate duplicates
		List<String> list = new ArrayList<String>(Arrays.asList("red", "orange",
				"yellow", "red", "red", "orange", "red"));
		System.out.println("eliminating dups from list " +
				list + " = " + new HashSet<String>(list));
		
		System.out.println("sorting HashSet set3 = " +
				new TreeSet<String>(set3));
		
		// Some examples of operations on a HashSet.
		// By declaring as the interface type Set,
		//   we maintain flexibility to change implementations.
		Set<String> set4 = new HashSet<String>();
		set4.add("b");
		set4.add("c");
		set4.add("a");
		set4.add("a");  // should have no effect
		set4.remove("b");
		System.out.println("set4 after add and remove operations = " + set4);
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
