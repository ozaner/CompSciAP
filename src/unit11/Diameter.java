package unit11;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Diameter {
	private static Scanner input;
	private static PrintWriter output;

	private static final boolean STDIN = false;
	private static final boolean STDOUT = true;

	private void start(String[] args) throws IOException {		
		input = new Scanner(new FileReader(args[0]));       // for file reading
		output = new PrintWriter(new FileWriter(args[1]));  // for file writing

		ArrayList<Integer> list = new ArrayList<Integer>();
		Scanner scline = null;

		while(input.hasNextLine())
		{
			scline = new Scanner(input.nextLine());
			while(scline.hasNext()) {
				list.add(scline.nextInt());
			}
		}
		processArgs(args);

		output.flush();
		input.close();
		output.close();

	}


	private static void processArgs(String[] args) {
		if (STDIN) {
			input = new Scanner(System.in); // for stdin
		} else {
			try {
				// for file reading (input filename from command line)
				input = new Scanner(new FileReader(args[0])); 
			} catch (IOException exception) {
				System.err.println("Cannot open input file");
				System.exit(1);
			} catch (ArrayIndexOutOfBoundsException exception) {
				System.err.println("Missing command line input file arg");
				System.exit(1);
			}
		}

		if (STDOUT) {
			output = new PrintWriter(System.out, true); // for stdout & flushing
		} else {
			try {
				// for file writing (output filename from command line)
				output = new PrintWriter(new FileWriter(args[1]));
			} catch (IOException exception) {
				System.err.println("Cannot open output file");
				System.exit(1);
			} catch (ArrayIndexOutOfBoundsException exception) {
				System.err.println("Missing command line output file arg");
				System.exit(1);
			}
		}
	}
}
