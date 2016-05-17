package unit11;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
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
 * An application to demonstrate regular expressions applied to a dictionary.
 * 2016/05/12
 * @version 1.0
 * @author markjones
 *
 */
@SuppressWarnings("serial")
public class RegexDictionary extends GraphicsProgram {

	private static String readFile(String path, Charset encoding) throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded, encoding);
	}

	private static final int INITIAL_WIDTH = 800;
	private static final int INITIAL_HEIGHT = 800;
	private static final String DICTIONARY = "yawl.txt";
	private static final Font MSG_FONT = new Font("Helvetica", Font.BOLD, 16);
	private static final Font TEXT_FONT = UIManager.getFont("TextField.font");
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
		new RegexDictionary().start(args);
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

		try { inputTextArea = new JTextArea(readFile(DICTIONARY, Charset.defaultCharset())); }
		catch (IOException ex) { System.exit(1); }
		inputTextArea.setFont(TEXT_FONT);
		inputTextArea.setLineWrap(true);
		inputTextArea.setWrapStyleWord(true);

		inputPane = new JScrollPane (
				inputTextArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		inputPane.setSize((int)(getWidth()*0.4), (int)(getHeight()*0.80));
		add(inputPane, getWidth()*0.05, getHeight()*0.10);
		GLabel inputLabel = new GLabel("input"); inputLabel.setFont(MSG_FONT);
		add(inputLabel, inputPane.getX(), inputPane.getY() - 1.0 * inputLabel.getHeight());

		outputTextArea = new JTextPane(); 
		outputPane = new JScrollPane(outputTextArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		outputPane.setSize((int)(getWidth()*0.4), (int)(getHeight()*0.80));
		add(outputPane, getWidth()*0.55, getHeight()*0.10);
		GLabel outputLabel = new GLabel("output"); outputLabel.setFont(MSG_FONT);
		add(outputLabel, outputPane.getX(), outputPane.getY() - 1.0 * outputLabel.getHeight());
	}

	/**
	 * Runs a regular expression search whenever a new regex is entered.
	 * This algorithm surrounds each regex with "^" and "$" to constrain matches to entire lines.
	 * Only those lines which match are listed in the Output pane.
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == regexField) {

			String regex = "^" + regexField.getText() + "$";
			Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);

			String input = inputTextArea.getText();
			Matcher matcher = pattern.matcher(input);

			int numMatches = 0;
			outputTextArea.setText("");
			while (matcher.find()) {
				++numMatches;
				appendToPane(outputTextArea, matcher.group() + "\n", Color.RED);
			}
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
