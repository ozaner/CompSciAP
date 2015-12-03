package unit4.acmExamples.windowSize;
import acm.graphics.GLine;
import acm.graphics.GRect;
import acm.program.GraphicsProgram;

@SuppressWarnings("serial")
public class WindowSizeApp extends GraphicsProgram {
	
	private static final int INITIAL_HEIGHT = 600;
	private static final int INITIAL_WIDTH = 2 * INITIAL_HEIGHT;
	
	private int width, height;

	public static void main(String[] args) {
		(new WindowSizeApp()).start(args);
	}
	
	public void init() {
		height = INITIAL_HEIGHT;
		width = INITIAL_WIDTH;
		
		super.setSize(width, height);
		System.out.printf("init() super.setSize: getWidth() = %d, getHeight() = %d\n", 
				getWidth(), getHeight());
		pause(2000);
		
		add(new GLine(0, 0, width, height));
		add(new GRect(width, 0.2*height), 0, 0.8*height);
		pause(2000);
	}
	
	public void run() {
		System.out.printf("run() before setSize: getWidth() = %d, getHeight() = %d\n", 
				getWidth(), getHeight());
		setSize(width, height);
		System.out.printf("run() after setSize: getWidth() = %d, getHeight() = %d\n", 
				getWidth(), getHeight());
		System.out.printf("run(): getMenuBar().getWidth() = %d, getMenuBar().getHeight() = %d\n", 
				getMenuBar().getWidth(), getMenuBar().getHeight());	
	}
	
	@Override
	public void setSize(int width, int height) {
		super.setSize(2 * width - getWidth(), 2 * height - getHeight());
	}
}
