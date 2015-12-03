package unit4.acmExamples.windowSize;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import acm.graphics.GLine;
import acm.graphics.GObject;
import acm.graphics.GRect;
import acm.graphics.GScalable;
import acm.program.GraphicsProgram;

@SuppressWarnings("serial")
public class ExampleAppWithResize extends GraphicsProgram {
	
	private static final int INITIAL_HEIGHT = 600;
	private static final int INITIAL_WIDTH = 2 * INITIAL_HEIGHT;
	
	private int width, height;

	public static void main(String[] args) {
		(new ExampleAppWithResize()).start(args);
	}
	
	public void init() {
		height = INITIAL_HEIGHT;
		width = INITIAL_WIDTH;
		
		super.setSize(width, height);

		add(new GLine(0, 0, width, height));
		add(new GRect(width, 0.2*height), 0, 0.8*height);
		
		addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent e) {
				double scaleX = getWidth() / (double)width,  scaleY = getHeight() / (double)height;
				for (int i = 0; i < getElementCount(); i++) {
					Object obj = getElement(i);
					if (obj instanceof GObject) {
						if (obj instanceof GScalable) {
							((GScalable) obj).scale(scaleX, scaleY);
						}
						((GObject) obj).setLocation(((GObject) obj).getX()*scaleX, ((GObject) obj).getY()*scaleY);
					}
				}
				width = getWidth(); height = getHeight();
			}
		}); 
	}
	
	public void run() {
		setSize(width, height);
	}
	
	@Override
	public void setSize(int width, int height) {
		super.setSize(2 * width - getWidth(), 2 * height - getHeight());
	}
}
