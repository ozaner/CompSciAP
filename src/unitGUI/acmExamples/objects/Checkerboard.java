package unitGUI.acmExamples.objects;

import acm.graphics.*;
import acm.program.*;

/**
 * This program draws a checkerboard.  The dimensions of the
 * checkerboard is specified by the constants NROWS and
 * NCOLUMNS, and the size of the squares is chosen so
 * that the checkerboard fills the available vertical space.
 */
@SuppressWarnings("serial")
public class Checkerboard extends GraphicsProgram {

	private static final int NROWS = 8;     /* Number of rows    */
	private static final int NCOLUMNS = 8;  /* Number of columns */

	/**
	 * Draws a checkerboard.
	 * @param args  none expected
	 */
	public static void main(String[] args) {
		new Checkerboard().start(args);
	}

	/** Creates the graphics for a checkerboard. */
	public void init() {
		double sqSize = (double) getHeight() / NROWS;
		for (int i = 0; i < NROWS; i++) {
			for (int j = 0; j < NCOLUMNS; j++) {
				double x = j * sqSize;
				double y = i * sqSize;
				GRect sq = new GRect(x, y, sqSize, sqSize);
				sq.setFilled((i + j) % 2 != 0);
				add(sq);
			}
		}
	}
}
