package unit9;

import java.awt.Color;
import java.awt.Dimension;

import acm.graphics.GLine;
import acm.graphics.GPoint;
import acm.program.GraphicsProgram;

public class Hangman extends GraphicsProgram implements HangmanView {

	public static final Dimension WINDOW_SIZE = new Dimension(1000,800);
	
	public static final Dimension GRAPHICS_SIZE = new Dimension(200,300);
	
	public static final GPoint GRAPHICS_COORDS = new GPoint(250,250);
	
	public static final GPoint WORD_COORDS = new GPoint(350,0);
	
	public static final int LINE_Y = 600;

	public static final int LINE_GAP = 40;
	
	public static final int LINE_OFFSET = 100;
	
	public static final int LINE_SIZE_FACTOR = 90;
	
	private HangmanModel model = new HangmanModel(this);
	
	private HangmanGraphics graphics = 
			new HangmanGraphics(GRAPHICS_SIZE.getWidth(),GRAPHICS_SIZE.getHeight());
	
	@Override
	public void init() {
		setSize(WINDOW_SIZE);
		setBackground(Color.DARK_GRAY);
		graphics.drawNextPart();
		add(graphics,GRAPHICS_COORDS);
		gameStartNotification("algorithm");
	}

	@Override
	public void gameStartNotification(String word) {
		for(int x = 0; x < word.length(); x++) {
			System.out.println(x);
			add(new GLine(x*LINE_SIZE_FACTOR + LINE_OFFSET,LINE_Y,x*LINE_SIZE_FACTOR + LINE_OFFSET + LINE_GAP,LINE_Y));
		}
	}

	@Override
	public void gameOverNotification() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void correctNotification(char c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void incorrectNotification(char c) {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) {
		Hangman e = new Hangman();
		e.start();
	}
}
