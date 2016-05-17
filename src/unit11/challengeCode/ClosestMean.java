package unit11.challengeCode;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class ClosestMean {

	static Scanner input;
	//	static PrintStream output;
	static PrintWriter output;    // for file writing

	public static void main(String[] args) throws IOException {

		//		input = new Scanner(System.in); // for stdin
		input = new Scanner(new FileReader(args[0]));       // for file reading
		//		output = System.out;                                // for stdout
		output = new PrintWriter(new FileWriter(args[1]));  // for file writing

		ArrayList<Integer> list = new ArrayList<Integer>();
		Scanner scline = null;

		while(input.hasNextLine())
		{
			scline = new Scanner(input.nextLine());
			while(scline.hasNext()) {
				list.add(scline.nextInt());
			}
			double avg = 0;
			int check1;
			int check2;
			for (int i: list)
				avg += i;
			avg /= list.size();
			if(Math.floor(avg) != Math.round(avg)) {
				check1 = (int)avg;
				check2 = (int)(avg + .5);
				ArrayList<Integer> low = new ArrayList<Integer>();
				int lowest = 0;
				for(int i: list) {
					if(Math.abs(check1 - i) < lowest) {
						lowest = Math.abs(check1 - i);
						low.add(i);
						low.clear();
					}
					if(Math.abs(check1 - i) == lowest) {
						low.add(i);
					}
				}
				output.println(low);
			}
			else {
				check1 = (int)Math.round(avg);
				int lowest = 0;
				for(int i: list) {
					if(Math.abs(check1 - i) < lowest) {
						lowest = Math.abs(check1 - i);
						check1 = i;
					}
				}
				output.println(check1);
			}





			list.clear();
		}

		scline.close();
		output.flush();
		input.close();
		output.close();
	}
}