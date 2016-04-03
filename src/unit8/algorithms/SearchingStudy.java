package unit8.algorithms;
import java.util.Arrays;
import java.util.Random;

/**
   This program measures how long it takes to search an
   array with the linear and binary search algorithms.
*/
public class SearchingStudy
{  
   public static void main(String[] args)
   {  
      final int smallestN = (int).1E7;
      final int largestN = (int)1E7;
      final int incrementN = (int).1E7;
      final int numberTrials = 100;
      final Random rand = new Random();
      
      System.out.printf("%8s\t%15s\t%15s\n", "n",
    		  "LinearSearcher",
    		  "BinarySearcher");
      for (int n=smallestN; n<=largestN; n+=incrementN)
      {
    	  StopWatch timer = new StopWatch();
    	  long linElapsedTime = 0;
    	  long binElapsedTime = 0;
    	  
		  int[] a = ArrayUtil.randomIntArray(n, n);
		  LinearSearcher linSearcher = new LinearSearcher(a);
		  
		  int[] acopy = new int[a.length];
		  System.arraycopy(a,0,acopy,0,a.length);
	      Arrays.sort(acopy);
   		  BinarySearcher binSearcher = new BinarySearcher(acopy);
	      
    	  for (int i=0; i<numberTrials; i++)
    	  {
    		  int v = a[rand.nextInt(n)];  // chooses only values already in the array
//    		  int v = rand.nextInt(n);     // may also choose values not in the array
    		  
    		  // LinearSearcher
    	      timer.reset();
    	      timer.start();
    	      linSearcher.search(v);
    	      timer.stop();
    	      linElapsedTime += timer.getElapsedTime();
    	      
    		  // BinarySearcher
    	      timer.reset();
    	      timer.start();
    	      binSearcher.search(v);
    	      timer.stop();
    	      binElapsedTime += timer.getElapsedTime();
    	  }
    	  System.out.printf("%8d\t%15d\t%15d\n", n,
    			  linElapsedTime/numberTrials,
    			  binElapsedTime/numberTrials);
      }
   }
}

   
