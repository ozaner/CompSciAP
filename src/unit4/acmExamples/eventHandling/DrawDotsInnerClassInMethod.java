package unit4.acmExamples.eventHandling;
import acm.graphics.*;
import acm.program.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class DrawDotsInnerClassInMethod extends GraphicsProgram {
	
	public void init() {
		class MyMouseListener implements MouseListener {
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseClicked(MouseEvent e) {
				GOval oval = new GOval(10.0, 10.0);
				oval.setFilled(true);
				add(oval, e.getX(), e.getY());
			}
		}
		MouseListener ms = new MyMouseListener();
		addMouseListeners(ms);
	}
}
