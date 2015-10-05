package unit1.acmLibraryExample;
/*
 * File: HelloGraphics.java
 * ------------------------ 
 * This program displays the message "hello, world" and is inspired
 * by the first program "The C Programming Language" by Brian
 * Kernighan and Dennis Ritchie.  This version displays the message
 * graphically.
 */
 
import acm.graphics.*;
import acm.program.*; 

public class HelloGraphics extends GraphicsProgram {
	private static final long serialVersionUID = 1L;

	public void run() {
		GLabel label = new GLabel("hello, world");
		label.setFont("SansSerif-18");
		double x = (getWidth() - label.getWidth()) / 2;
		double y = (getHeight() + label.getAscent()) / 2;
		add(label, x, y);
	}
	
	/* Standard Java entry point */
	/* This method can be eliminated in most Java environments */
	/*
	public static void main(String[] args) {
		new HelloGraphics().start(args);
	}
	*/
} 
