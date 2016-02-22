package unit7.concentration;
/**
 * Interface for what an implementation of Cell in the game Concentration must be able to do.
 * @author markjones
 *
 */
public interface Cell {
	
	/**
	 * Creates a copy of this Cell.
	 * @return   the new Cell copy
	 */
	Cell copy();
	
	/**
	 * Gets the width of a Cell (for layout purposes).
	 * @return the width
	 */
	double getWidth();
	
	/**
	 * Gets the height of a Cell (for layout purposes).
	 * @return the height
	 */
	double getHeight();

	/** 
	 * Turn a Cell face up. 
	 */
	Cell turnFaceUp();

	/** 
	 * Turn a Cell face down. 
	 */
	Cell turnFaceDown();
	
	/**
	 * Are the cells the same?
	 */
	boolean equals(Cell cell);
}
