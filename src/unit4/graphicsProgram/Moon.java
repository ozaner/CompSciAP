package unit4.graphicsProgram;

import java.awt.Color;

import acm.graphics.GCompound;
import acm.graphics.GOval;

/**
 * This class creates a moon that has multiple phases that change each night.
 * @author Ozaner Hansha
 */
@SuppressWarnings("serial")
public class Moon extends GCompound implements Nighttimeable
{
	private GOval moon, illumination;
	private int phase = -10;
	
	/**
	 * Creates a white circle and a black one on top of it.
	 * @param x - The x coordinate of the top left corner of the moon.
	 * @param y - The x coordinate of the top left corner of the moon.
	 */
	public Moon(double x, double y)
	{
		moon = new GOval(HouseApp.APP_SIZE.getWidth()*.1,HouseApp.APP_SIZE.getWidth()*.1);
		moon.setFilled(true);
		moon.setFillColor(Color.WHITE);
		moon.setVisible(false);
		add(moon,x,y);
		
		illumination = new GOval(moon.getWidth(),moon.getHeight());
		illumination.setFilled(true);
		illumination.setFillColor(Color.BLACK);
		illumination.setVisible(false);
		add(illumination,moon.getX()+moon.getWidth(),y);
	}
	
	/* Sets the moon and illumination visible and increases the phase of the moon.
	 * @see unit4.graphicsProgram.Nighttimeable#nighttime()
	 */
	@Override
	public void nighttime()
	{
		illumination.setLocation(moon.getX()+illumination.getWidth()*.1*phase, illumination.getY());
		if(phase > -10)
			phase--;
		else
			phase = 10;
		moon.setVisible(true);
		illumination.setVisible(true);
		
	}

	/* Sets the moon and illumination invisible.
	 * @see unit4.graphicsProgram.Nighttimeable#daytime()
	 */
	@Override
	public void daytime()
	{
		moon.setVisible(false);
		illumination.setVisible(false);
	}
}
