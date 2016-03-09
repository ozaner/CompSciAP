package unit8.algorithms;
/**
   This program tests the bubble sort algorithm.
*/
public class BubbleSortTester
{  
   public static void main(String[] args)
   {  
      int[] a = ArrayUtil.randomIntArray(20, 100);
      ArrayUtil.print(a);
      BubbleSorter sorter = new BubbleSorter(a);
      sorter.sort();
      ArrayUtil.print(a);
   }
}

