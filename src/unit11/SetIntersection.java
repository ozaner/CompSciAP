package unit11;

import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class SetIntersection {
	static Scanner input;
	
	static PrintStream output;
//	static PrintWriter output;    // for file writing

    public static Set<Integer> getSet (Scanner scline) {
    	Set<Integer> set = new HashSet<Integer>();
    	while(scline.hasNextInt())
    		set.add(scline.nextInt());
    	return set;
    }
    
	public static void main(String[] args) throws IOException {
//		input = new Scanner(System.in);                     // for stdin
		input = new Scanner(new FileReader(args[0]));       // for file reading
		
		output = System.out;                                // for stdout
//		output = new PrintWriter(new FileWriter(args[1]));  // for file writing
    	
	Set<Integer> set1;
	Set<Integer> set2;
	Set<Integer> set3;
		
    	String line = input.nextLine();
    	Scanner scline = new Scanner(line);
        int n = decline.nextInt();
    	
        for(int x = n; x >= 3; x-=3)
        line = input.nextLine();
    	set1 = getSet(scline);
    	line = input.nextLine();
    	set2 = getSet(scline);
    	line = input.nextLine();
    	set3 = getSet(scline);
        set1.retianAll(set2);
        set1.retainAll(set3);
        output.println(set1);
        }
    	scline.close();
        output.flush();
        input.close();
        output.close();
	}
}
