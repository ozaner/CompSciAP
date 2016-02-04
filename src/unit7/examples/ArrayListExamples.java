package unit7.examples;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.ListIterator;

public class ArrayListExamples {
	
	static String[] colors = {  // roygbiv rainbow spectrum
			"red", "orange", "yellow", "green",
			"blue", "indigo", "violet"};

	/**
	 * Demonstrate ArrayLists.
	 * @param args   none expected
	 */
	public static void main(String[] args) {	
		// Initializing an ArrayList from a list of values.
		ArrayList<String> list3 = 
			new ArrayList<String>(Arrays.asList("red", "orange",
					"yellow", "green", "blue", "indigo", "violet"));
		System.out.println("\nArrayList<String> list3 = " + list3);
		
		// Initializing an ArrayList from an array.
		ArrayList<String> list4 =
			new ArrayList<String>(Arrays.asList(colors));
		System.out.println("ArrayList<String> list4 = " + list4);

		// Initializing an ArrayList from a Collection.
		ArrayList<String> list5 = 
				new ArrayList<String>(new LinkedList<String>(Arrays.asList(colors)));
		System.out.println("ArrayList<String> list5 = " + list5);
		
		// A typical iteration demonstrating remove, set and add.
		// This would be identical and more efficient for a LinkedList.
		for (ListIterator<String> itr = list3.listIterator(); itr.hasNext();) {
			String str = itr.next();
			if (str.equals("green")) {
				System.out.println("removing green from list3");
				itr.remove();
			} else if (str.equals("violet")) {
				System.out.println("turning violet into purple from list3");
				itr.set("purple");				
				System.out.println("adding infrared to list3");
				itr.add("infrared");				
			}
		}
		System.out.println("list3 after modifications = " + list3);
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
