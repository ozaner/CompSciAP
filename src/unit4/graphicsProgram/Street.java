package unit4.graphicsProgram;

import java.awt.Color;

import acm.graphics.GRect;

@SuppressWarnings("serial")
public class Street extends Nighttimeable
{

	private GRect street;
	
	public Street()
	{
		street = new GRect(0,HouseApp.APP_SIZE.getHeight()*(12.0/15.0),
				HouseApp.APP_SIZE.getWidth(),HouseApp.APP_SIZE.getHeight()*(1.0/15.0));
		street.setFilled(true);
		street.setColor(Color.BLACK);
		add(street);
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
