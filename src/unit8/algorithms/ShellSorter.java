package unit8.algorithms;
/**
   This class sorts an array, using the shell sort 
   algorithm
*/
public class ShellSorter
{
   /**
      Constructs an insertion sorter.
      @param anArray the array to sort
   */   
   public ShellSorter(int[] anArray)
   {
      a = anArray;
   }
   
   /**
      Sorts the array managed by this shell sorter
   */  
   public void sort() {
		int h = 1;
		int temp;
		int k;
		do {
			h = 3 * h + 1;
		} while (h <= a.length);
		do {
			h /= 3;
			for (int j = h; j < a.length; j++) {
				temp = a[j];
				k = j;
				while (a[k - h] > temp) {
					a[k] = a[k - h];
					k -= h;
					if (k < h)
						break;
				}
				a[k] = temp;
			}
		} while (h != 1);
	}
   
   private int[] a;
}
