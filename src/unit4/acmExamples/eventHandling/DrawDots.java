package unit4.acmExamples.eventHandling;
import acm.graphics.*;
import acm.program.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class DrawDots extends GraphicsProgram {
	
	public void init() {
		addMouseListeners();
	}
	
	public void mouseClicked(MouseEvent e) {
		GOval oval = new GOval(10.0, 10.0);
		oval.setFilled(true);
		add(oval, e.getX(), e.getY());
	}
}
