package unit8.algorithms;
/**
   This program tests the heap sort algorithm by
   sorting an array that is filled with random numbers.
*/
public class HeapSortTester
{  
   public static void main(String[] args)
   {  
      int[] a = ArrayUtil.randomIntArray(20, 100);
      ArrayUtil.print(a);

      HeapSorter sorter = new HeapSorter(a);
      sorter.sort();

      ArrayUtil.print(a);
   }
}

   
