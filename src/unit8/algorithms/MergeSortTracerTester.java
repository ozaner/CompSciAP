package unit8.algorithms;
/**
   This program tests the merge sort algorithm by
   sorting an array that is filled with random numbers.
*/
public class MergeSortTracerTester
{  
   public static void main(String[] args)
   {  
      int[] a = ArrayUtil.randomIntArray(20, 100);
      ArrayUtil.print(a);
      MergeSorterTracer sorter = new MergeSorterTracer(a);
      sorter.sort();
      ArrayUtil.print(a);
   }
}

