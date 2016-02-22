package unit7.concentration;
import java.util.ArrayList;
import java.util.ListIterator;

/**
 * The Concentration Model based off of {@link GCard}.
 * @author Ozaner Hansha
 */
public class ConcentrationCardModel extends ConcentrationModel {

	/**
	 * Creates a new Concentration Model based off of cards.
	 * @param app - The Concentration application
	 */
	public ConcentrationCardModel(Concentration app) {
		super(app);
	}

	/**
	 * Returns the width of a cell (Card).
	 * @see unit7.concentration.ConcentrationModel#getCellWidth()
	 */
	@Override
	public double getCellWidth() {
		return GCard.cardWidth();
	}

	/**
	 * Returns the height of a cell (Card).
	 * @see unit7.concentration.ConcentrationModel#getCellHeight()
	 */
	@Override
	public double getCellHeight() {
		return GCard.cardHeight();
	}

	/**
	 * Returns a listIterator of a GCard deck made from {@link GCard#makeDeck()}.
	 * @see unit7.concentration.ConcentrationModel#getCellIterator()
	 */
	@Override
	public ListIterator<Cell> getCellIterator() {
		Deck deck = GCard.makeDeck();
		ArrayList<Cell> cells = new ArrayList<Cell>();
		for(Card c: deck) {
			cells.add((GCard)c);
		}
		return cells.listIterator();
	}
}
