package unit8.algorithms;
/**
   This class sorts an array, using the bubble sort 
   algorithm
*/
public class BubbleSorter
{
   /**
      Constructs an insertion sorter.
      @param anArray the array to sort
   */   
   public BubbleSorter(int[] anArray)
   {
      a = anArray;
   }

   /**
      Sorts the array managed by this bubble sorter
   */  
   public void sort() {
	  int temp;
	  do {
		  temp = a[0];
		  for (int i = 1; i < a.length; i++) {
			  if (a[i-1] > a[i]) {
				  temp = a[i];
				  a[i] = a[i-1];
				  a[i-1] = temp;
			  }
		  }
	  } while (temp != a[0]);
   }
   
   private int[] a;
}
