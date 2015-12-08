package unit4.graphicsProgram;

import java.awt.Color;
import acm.graphics.GCompound;
import acm.graphics.GRect;

/**
 * This class creates the street and is based on {@link GCompound}.
 * @author Ozaner Hansha
 */
@SuppressWarnings("serial")
public class Street extends GCompound implements Nighttimeable
{
	private GRect street;
	
	/**
	 * Creates a black rectangle at the bottom of the screen.
	 */
	public Street()
	{
		street = new GRect(0,HouseApp.APP_SIZE.getHeight()*.8,
				HouseApp.APP_SIZE.getWidth(),HouseApp.APP_SIZE.getHeight()*.1);
		street.setFilled(true);
		street.setColor(Color.BLACK);
		add(street);
	}
	
	/* Doesn't do anything.
	 * @see unit4.graphicsProgram.Nighttimeable#nighttime()
	 */
	@Override
	public void nighttime(){}

	/* Doesn't do anything.
	 * @see unit4.graphicsProgram.Nighttimeable#daytime()
	 */
	@Override
	public void daytime(){}

}
