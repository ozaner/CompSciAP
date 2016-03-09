package unit8.algorithms;
/**
   This class sorts an array, using the heap sort 
   algorithm
*/
public class HeapSorter
{
   /**
      Constructs a heap sorter.
      @param anArray the array to sort
   */   
   public HeapSorter(int[] anArray)
   {
      a = anArray;
   }
   
   /**
      Sorts the array managed by this heap sorter
    */  
   public void sort() {
	   int n = a.length;
	   for (int i = n/2; i >= 0; i--)
		   downheap(i,n);
	   for (int i = n-1; i > 0; i--) {
		   int temp = a[0];
		   a[0] = a[i];
		   a[i] = temp;
		   downheap(0,i);
	   }
   }

   private void downheap(int k, int maxPos) {
   	int temp = a[k];
   	while (k < maxPos/2) {
   		int maxChildIndex = 2*k + 1;
   		if (maxChildIndex<maxPos-1) {
   			if (a[maxChildIndex] < a[maxChildIndex+1]) {
   				maxChildIndex++;
   			}
   		}
   		if (temp >= a[maxChildIndex])
   			break;
   		a[k] = a[maxChildIndex];
   		k = maxChildIndex;
   	}
   	a[k] = temp;
   }
   
   private int[] a;
}
