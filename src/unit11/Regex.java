package unit11;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

import acm.graphics.GLabel;
import acm.program.GraphicsProgram;
import acm.program.Program;

/**
 * An application to demonstrate regular expressions.
 * 2016/05/12
 * @version 1.0
 * @author markjones
 *
 */
@SuppressWarnings("serial")
public class Regex extends GraphicsProgram {
	
    private static final String GETTYSBURG_ADDRESS =
			"Four score and seven years ago our fathers brought forth on this continent, "
			+ "a new nation, conceived in Liberty, and dedicated to the proposition that all men are created equal.\n\n"
			+ "Now we are engaged in a great civil war, testing whether that nation, or any nation so conceived and dedicated, "
			+ "can long endure. We are met on a great battle-field of that war. We have come to dedicate a portion of that field, "
			+ "as a final resting place for those who here gave their lives that that nation might live. "
			+ "It is altogether fitting and proper that we should do this.\n\n"
			+ "But, in a larger sense, we can not dedicate -- we can not consecrate -- we can not hallow -- this ground. "
			+ "The brave men, living and dead, who struggled here, have consecrated it, "
			+ "far above our poor power to add or detract. The world will little note, "
			+ "nor long remember what we say here, but it can never forget what they did here. "
			+ "It is for us the living, rather, to be dedicated here to the unfinished work "
			+ "which they who fought here have thus far so nobly advanced. "
			+ "It is rather for us to be here dedicated to the great task remaining before us -- "
			+ "that from these honored dead we take increased devotion to that cause "
			+ "for which they gave the last full measure of devotion -- "
			+ "that we here highly resolve that these dead shall not have died in vain -- "
			+ "that this nation, under God, shall have a new birth of freedom -- "
			+ "and that government of the people, by the people, for the people, shall not perish from the earth.";

	private static final int INITIAL_WIDTH = 800;
	private static final int INITIAL_HEIGHT = 800;
	private static final Font MSG_FONT = new Font("Helvetica", Font.BOLD, 16);
	private static final Font TEXT_FONT = UIManager.getFont("TextField.font");
	private static final String DEFAULT_INPUT = GETTYSBURG_ADDRESS;
	private static final int MAX_REGEX_LENGTH = 30;
	
	private JLabel statusMsg;
	private JTextField regexField;
	private JTextArea inputTextArea;
	private JScrollPane inputPane;
	private JTextPane outputTextArea;
	private JScrollPane outputPane;

	/**
	 * Runs the program as an application. 
	 * @param args  none expected
	 */
	public static void main(String[] args) {
		new Regex().start(args);
	}
	
	/**
	 * Creates the GUI.
	 */
	public void init() {
		
		setSize(INITIAL_WIDTH, INITIAL_HEIGHT);

		// label for status messages
		statusMsg = new JLabel("");
		statusMsg.setFont(MSG_FONT);
		add(statusMsg, Program.NORTH);
		
		regexField = new JTextField(MAX_REGEX_LENGTH);
		regexField.setText("");
		JLabel regexLabel = new JLabel("regex"); regexLabel.setFont(MSG_FONT);
		add(regexLabel, SOUTH);
		add(regexField, SOUTH);
		regexField.addActionListener(this);
		
		inputTextArea = new JTextArea(DEFAULT_INPUT); 
		inputTextArea.setFont(TEXT_FONT);
		inputTextArea.setLineWrap(true);
		inputTextArea.setWrapStyleWord(true);
		
		inputPane = new JScrollPane (
				inputTextArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		inputPane.setSize((int)(getWidth()*0.4), (int)(getHeight()*0.80));
		add(inputPane, getWidth()*0.05, getHeight()*0.10);
		GLabel inputLabel = new GLabel("input"); inputLabel.setFont(MSG_FONT);
		add(inputLabel, inputPane.getX(), inputPane.getY() - 1.5 * inputLabel.getHeight());
		
		outputTextArea = new JTextPane(); 
		outputPane = new JScrollPane(outputTextArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		outputPane.setSize((int)(getWidth()*0.4), (int)(getHeight()*0.80));
		add(outputPane, getWidth()*0.55, getHeight()*0.10);
		GLabel outputLabel = new GLabel("output"); outputLabel.setFont(MSG_FONT);
		add(outputLabel, outputPane.getX(), outputPane.getY() - 1.5 * outputLabel.getHeight());
	}
	
	/**
	 * Runs a regular expression search whenever a new regex is entered.
	 * This algorithm colors each matching expression and copies the entire
	 * input to the Output pane.
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == regexField) {

			String regex = regexField.getText();
	        Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
	        
			String input = inputTextArea.getText();
	        Matcher matcher = pattern.matcher(input);
	        
	        int numMatches = 0;
	        int start = 0, end = 0;
	        outputTextArea.setText("");
	        while (matcher.find()) {
	        	++numMatches;
	        	String match = matcher.group();
	        	start = matcher.start();

	        	appendToPane(outputTextArea, input.substring(end, start), Color.BLACK);
	        	appendToPane(outputTextArea, match, Color.RED);
	        	end = matcher.end();
	        }
	    	appendToPane(outputTextArea, input.substring(end), Color.BLACK);
	        statusMsg.setText("Number of matches = " + numMatches);
		}
	}
	
	/**
	 * Appends a string in the given color to a JTextPane.
	 * @param tp   the JTextPane
	 * @param msg  the string to be appended
	 * @param c    the color
	 */
    private void appendToPane(JTextPane tp, String msg, Color c) {
        StyleContext sc = StyleContext.getDefaultStyleContext();
        AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, c);

        aset = sc.addAttribute(aset, StyleConstants.FontFamily, TEXT_FONT.getFontName());
        aset = sc.addAttribute(aset, StyleConstants.Alignment, StyleConstants.ALIGN_JUSTIFIED);

        int len = tp.getDocument().getLength();
        tp.setCaretPosition(len);
        tp.setCharacterAttributes(aset, false);
        tp.replaceSelection(msg);
    }
}
