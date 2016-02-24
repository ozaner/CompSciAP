package unit7.examples;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class LinkedListExamples {
	
	static String[] colors = {  // roygbiv rainbow spectrum
			"red", "orange", "yellow", "green",
			"blue", "indigo", "violet"};

	/**
	 * Demonstrate LinkedLists.
	 * @param args   none expected
	 */
	public static void main(String[] args) {	
		// Initializing an LinkedList from a list of values.
		LinkedList<String> list6 =
			new LinkedList<String>(Arrays.asList("red", "orange",
					"yellow", "green", "blue", "indigo", "violet"));
		System.out.println("\nLinkedList<String> list6 = " + list6);
		
		// Initializing an LinkedList from an array.
		LinkedList<String> list7 =
			new LinkedList<String>(Arrays.asList(colors));
		System.out.println("LinkedList<String> list7 = " + list7);

		// Initializing an LinkedList from a Collection.
		LinkedList<String> list8 =
			new LinkedList<String>(new ArrayList<String>(Arrays.asList(colors)));
		System.out.println("LinkedList<String> list8 = " + list8);

		// Some examples of building a LinkedList from each end.
		LinkedList<String> list9 = new LinkedList<String>();
		list9.addFirst("b");
		list9.addLast("c");
		list9.addFirst("a");
		list9.addFirst("a");
		String first = list9.removeFirst();
		System.out.println("LinkedList<String> list9 after modifications = " + list9);
		System.out.println("LinkedList<String> first element removed = " + first);
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
