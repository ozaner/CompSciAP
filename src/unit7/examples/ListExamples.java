package unit7.examples;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ListExamples {

	static String[] colors = {  // roygbiv rainbow spectrum
			"red", "orange", "yellow", "green",
			"blue", "indigo", "violet"};

	/**
	 * Demonstrate ArrayLists.
	 * @param args   none expected
	 */
	public static void main(String[] args) {	
		System.out.println("colors = " + Arrays.toString(colors));

		// Arrays.asList returns a fixed size list backed by the array.
		// (Changes to the returned list "write through" to the array.) 
		// This method acts as bridge between array-based and collection-based 
		// APIs, in combination with Collection.toArray(). The returned list 
		// is serializable and implements RandomAccess. 
		// The returned list is an Arrays$ArrayList, not a java.util.ArrayList.
		// If you really want a java.util.ArrayList, then use
		//    new ArrayList(Arrays.asList(...))
		
		List<String> list1 = Arrays.asList("red", "orange",
				"yellow", "green", "blue", "indigo", "violet");
		System.out.println("List<String> list1 = " + list1);

		List<String> list2 = Arrays.asList(colors);
		System.out.println("List<String> list2 = " + list2);

		for (int i = 0; i < list1.size(); i++) {
			if (list1.get(i).equals("violet"))
				System.out.println("foreach found violet");
		}

		for (String str : list1) {
			if (str.equals("violet"))
				System.out.println("foreach found violet");
		}

		for (Iterator<String> itr = list1.iterator(); itr.hasNext();) {
			String str = itr.next();
			if (str.equals("violet"))
				System.out.println("Iterator found violet");
		}

		for (ListIterator<String> itr = list1.listIterator(); itr.hasNext();) {
			String str = itr.next();
			if (str.equals("violet"))
				System.out.println("ListIterator found violet");
		}
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

