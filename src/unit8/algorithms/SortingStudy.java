package unit8.algorithms;
/**
   This program measures how long it takes to sort an
   array of a user-specified size with various sorting algorithms.
*/
public class SortingStudy
{  
   public static void main(String[] args)
   {  
      final int smallestN = 500;  // the smallest value of n
      final int largestN = 20000; // the largest value of n
      final int incrementN = 500; // the increment valueof n
      final int numberTrials = 5; // the number of trials to average for a given n 
      
      System.out.printf("%8s\t%15s\t%15s\t%15s\t%15s\n", "n",
    		  "InsertionSort",
    		  "SelectionSort",
    		  "MergeSort",
    		  "QuickSort");
      for (int n=smallestN; n<=largestN; n+=incrementN)
      {
    	  StopWatch timer = new StopWatch();
    	  long insElapsedTime = 0;
    	  long selElapsedTime = 0;
    	  long mergeElapsedTime = 0;
    	  long quickElapsedTime = 0;
    	  for (int i=0; i<numberTrials; i++)
    	  {
    		  // fill an array of size n with random numbers between 0 and n-1
    		  int[] a = ArrayUtil.randomIntArray(n, n);
    		  int[] acopy = new int[a.length];
 
    		  // InsertionSort
    		  System.arraycopy(a,0,acopy,0,a.length);
    	      InsertionSorter insSorter = new InsertionSorter(acopy);
    	      timer.reset();
    	      timer.start();
    	      insSorter.sort();
    	      timer.stop();
    	      insElapsedTime += timer.getElapsedTime();
    	      
    		  // SelectionSort
       		  System.arraycopy(a,0,acopy,0,a.length);
    	      SelectionSorter selsorter = new SelectionSorter(acopy);
    	      timer.reset();
    	      timer.start();
    	      selsorter.sort();
    	      timer.stop();
    	      selElapsedTime += timer.getElapsedTime();

    	      // MergeSort
       		  System.arraycopy(a,0,acopy,0,a.length);
    	      MergeSorter mergesorter = new MergeSorter(acopy);
    	      timer.reset();
    	      timer.start();
    	      mergesorter.sort();
    	      timer.stop();
    	      mergeElapsedTime += timer.getElapsedTime();

    	      // QuickSort
       		  System.arraycopy(a,0,acopy,0,a.length);
    	      QuickSorter quicksorter = new QuickSorter(acopy);
    	      timer.reset();
    	      timer.start();
    	      quicksorter.sort();
    	      timer.stop();
    	      quickElapsedTime += timer.getElapsedTime();
    	  }
    	  System.out.printf("%8d\t%15d\t%15d\t%15d\t%15d\n", n,
    			  insElapsedTime/numberTrials,
    			  selElapsedTime/numberTrials,
    			  mergeElapsedTime/numberTrials,
    			  quickElapsedTime/numberTrials);
      }
   }
}

   
