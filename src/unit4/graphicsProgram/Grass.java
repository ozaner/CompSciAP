package unit4.graphicsProgram;

import java.awt.Color;

import acm.graphics.GRect;

@SuppressWarnings("serial")
public class Grass extends Nighttimeable
{	
	private GRect grass;
	
	public Grass()
	{
		grass = new GRect(0,HouseApp.APP_SIZE.getHeight()*(2.0/5.0),
				HouseApp.APP_SIZE.getWidth(),HouseApp.APP_SIZE.getHeight()*(2.0/5.0));
		grass.setFilled(true);
		grass.setColor(Color.GREEN);
		add(grass);
	}

	@Override
	public void nighttime()
	{
		
	}

	@Override
	public void daytime()
	{
		
	}
}
