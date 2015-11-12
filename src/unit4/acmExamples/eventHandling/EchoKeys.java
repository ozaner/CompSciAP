package unit4.acmExamples.eventHandling;
import acm.graphics.*;
import acm.program.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class EchoKeys extends GraphicsProgram {
 
	public void init() {
		getGCanvas().setFocusable(true);
		label = new GLabel("", getWidth() / 2, getHeight() / 2);
		label.setFont("Helvetica-24");
		add(label);
		addKeyListeners();
	}
	
	public void keyTyped(KeyEvent e) {
		char c = e.getKeyChar();
		label.setLabel(label.getLabel()+c);
		label.setLocation(getWidth()/2 - label.getWidth()/2, getHeight()/2);
	}
	
	private GLabel label;
}