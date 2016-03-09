package unit8.algorithms;
/**
   This program tests the shell sort algorithm by
   sorting an array that is filled with random numbers.
*/
public class ShellSortTester
{  
   public static void main(String[] args)
   {  
      int[] a = ArrayUtil.randomIntArray(20, 100);
      ArrayUtil.print(a);

      ShellSorter sorter = new ShellSorter(a);
      sorter.sort();

      ArrayUtil.print(a);
   }
}

   
