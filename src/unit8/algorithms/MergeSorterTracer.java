package unit8.algorithms;
/**
   This class sorts an array, using the merge sort algorithm.
*/
public class MergeSorterTracer
{
   /**
      Constructs a merge sorter.
      @param anArray the array to sort
   */
   public MergeSorterTracer(int[] anArray)
   {
      a = anArray;
   }
   
   private void space(int n) {
	   for (int i = 0; i < n; i++)
		   System.out.print(" ");
   }
   
   private void traceEnter() {	
	   space(INDENT); System.out.print("==>"); ArrayUtil.print(a);
	   INDENT++;
   }
   
   private void traceExit() {	
	   INDENT--;
	   space(INDENT); System.out.print("<=="); ArrayUtil.print(a);
   }
   
   /**
      Sorts the array managed by this merge sorter.
   */
   public void sort()
   {  
	  traceEnter();
      if (a.length <= 1) {traceExit(); return;}
      int[] first = new int[a.length / 2];
      int[] second = new int[a.length - first.length];
      System.arraycopy(a, 0, first, 0, first.length);
      System.arraycopy(a, first.length, second, 0, second.length);
      MergeSorterTracer firstSorter = new MergeSorterTracer(first);
      MergeSorterTracer secondSorter = new MergeSorterTracer(second);
	  firstSorter.sort();
      secondSorter.sort();
      merge(first, second);
	  traceExit();
   }

   /**
      Merges two sorted arrays into the array managed by this
      merge sorter. 
      @param first the first sorted array
      @param second the second sorted array
   */
   private void merge(int[] first, int[] second)
   {  
      // Merge both halves into the temporary array

      int iFirst = 0;
         // Next element to consider in the first array
      int iSecond = 0;
         // Next element to consider in the second array
      int j = 0; 
         // Next open position in a

      // As long as neither iFirst nor iSecond past the end, move
      // the smaller element into a
      while (iFirst < first.length && iSecond < second.length)
      {  
         if (first[iFirst] < second[iSecond])
         {  
            a[j] = first[iFirst];
            iFirst++;
         }
         else
         {  
            a[j] = second[iSecond];
            iSecond++;
         }
         j++;
      }

      // Note that only one of the two calls to arraycopy below
      // copies entries

      // Copy any remaining entries of the first array
      System.arraycopy(first, iFirst, a, j, first.length - iFirst);
      
      // Copy any remaining entries of the second half
      System.arraycopy(second, iSecond, a, j, second.length - iSecond);
   }

   private int[] a;
   private static int INDENT = 0;
}
