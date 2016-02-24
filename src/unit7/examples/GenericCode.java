package unit7.examples;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class GenericCode {

	static String[] colors = {  // roygbiv rainbow spectrum
			"red", "orange", "yellow", "green",
			"blue", "indigo", "violet"};

	/**
	 * Demonstrate ArrayLists.
	 * @param args   none expected
	 */
	public static void main(String[] args) {	
		testSwap();
		testFindMax();
		
	}
	
	/**
	 * Tests generic swap code.
	 *
	 */
	public static void testSwap() {
		List<Object> list10 = 
			new ArrayList<Object>(Arrays.asList(colors));
		System.out.println("\ntestSwap: \nArrayList<String> list10 = " + list10);
		System.out.println("swapping first and last elements");
		swap(list10, 0, list10.size()-1);
		System.out.println("ArrayList<String> list10 = " + list10);
	}
	
	/**
	 * Generic element-swapping code that works for any list.
	 * It will be somewhat more efficient for an ArrayList
	 * than a LinkedList.
	 */
	public static void swap(List<Object> list, int i, int j) {
		Object temp = list.get(i);
		list.set(i, list.get(j));
		list.set(j, temp);
	}
	
	/**
	 * Tests generic findMax code.
	 *
	 */	
	public static void testFindMax() {
		List<String> list11 = 
			new ArrayList<String> (Arrays.asList(colors));
		System.out.println("\ntestFindMax: \nArrayList<String> list11 = " + list11);
		System.out.println("finding the max element using iterators");
		String max = findMax(list11);
		System.out.println("ArrayList<String> max = " + max);
		System.out.println("finding the max element using foreach");
		String max2 = findMax2(list11);
		System.out.println("ArrayList<String> max2 = " + max2);
	}

	/**
	 * Generic maximum-finding code that works for any non-empty list of
	 * of comparable elements.  Uses iterators.
	 */	
	public static <T extends Comparable<T>> T findMax(List<T> list) {
		Iterator<T> itr = list.iterator();
		T max = itr.next();
		while (itr.hasNext()) {
			T element = itr.next();
			if (max.compareTo(element) < 0)
				max = element;
		}
		return max;		
	}

	
	/**
	 * Generic maximum-finding code that works for any non-empty list of
	 * of comparable elements.  Uses foreach.
	 */
	public static <T extends Comparable<T>> T findMax2(List<T> list) {
		T max = list.get(0);
		for (T element : list)
			if (max.compareTo(element) < 0)
				max = element;
		return max;		
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

