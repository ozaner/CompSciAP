package unit4.acmExamples.windowSize;
import acm.graphics.GLine;
import acm.graphics.GRect;
import acm.program.GraphicsProgram;

@SuppressWarnings("serial")
public class ExampleApp extends GraphicsProgram {
	
	private static final int INITIAL_HEIGHT = 600;
	private static final int INITIAL_WIDTH = 2 * INITIAL_HEIGHT;
	
	private int width, height;

	public static void main(String[] args) {
		(new ExampleApp()).start(args);
	}
	
	public void init() {
		height = INITIAL_HEIGHT;
		width = INITIAL_WIDTH;
		
		super.setSize(width, height);

		add(new GLine(0, 0, width, height));
		add(new GRect(width, 0.2*height), 0, 0.8*height);
	}
	
	public void run() {
		setSize(width, height);
	}
	
	@Override
	public void setSize(int width, int height) {
		super.setSize(2 * width - getWidth(), 2 * height - getHeight());
	}
}
