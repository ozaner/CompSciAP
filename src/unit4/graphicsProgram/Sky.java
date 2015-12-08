package unit4.graphicsProgram;

import java.awt.Color;
import acm.graphics.GCompound;
import acm.graphics.GRect;

/**
 * This class creates the sky and the objects placed in it.
 * It is based on {@link GCompound}.
 * @author Ozaner Hansha
 */
@SuppressWarnings("serial")
public class Sky extends GCompound implements Nighttimeable
{
	private GRect sky;
	private FlyingObject flyingObj;
	private Moon moon;
	
	/**
	 * Creates the a blue rectangle, a {@link Moon}, and a {@link FlyingObject}.
	 */
	public Sky()
	{
		//Sky
		sky = new GRect(0,0,HouseApp.APP_SIZE.getWidth(),HouseApp.APP_SIZE.getHeight()*.5);
		sky.setFilled(true);
		add(sky);
		
		//Moon
		moon = new Moon(HouseApp.APP_SIZE.getWidth()*.8,HouseApp.APP_SIZE.getHeight()*.1);
		add(moon);
		
		//Airplane-Santa
		flyingObj = new FlyingObject();
		add(flyingObj);
		
		daytime(); //init compound
	}

	/* Sets the sky black, and the flying object and moon to nighttime.
	 * @see unit4.graphicsProgram.Nighttimeable#nighttime()
	 */
	@Override
	public void nighttime()
	{
		sky.setColor(Color.BLACK);
		flyingObj.setLight(false);
		moon.setLight(false);
	}

	/* Sets the sky cyan, and the flying object and moon to daytime.
	 * @see unit4.graphicsProgram.Nighttimeable#daytime()
	 */
	@Override
	public void daytime()
	{
		sky.setColor(Color.CYAN);
		flyingObj.setLight(true);
		moon.setLight(true);
	}
}
