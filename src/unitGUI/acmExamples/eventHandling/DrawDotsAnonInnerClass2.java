package unitGUI.acmExamples.eventHandling;
import acm.graphics.*;
import acm.program.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class DrawDotsAnonInnerClass2 extends GraphicsProgram {
	
	public void init() {
		addMouseListeners(new MouseListener() {
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseClicked(MouseEvent e) {
				GOval oval = new GOval(10.0, 10.0);
				oval.setFilled(true);
				add(oval, e.getX(), e.getY());
			}
		});
	}
}
