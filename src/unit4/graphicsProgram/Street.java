package unit4.graphicsProgram;

import java.awt.Color;
import acm.graphics.GCompound;
import acm.graphics.GRect;

@SuppressWarnings("serial")
public class Street extends GCompound implements Nighttimeable
{

	private GRect street;
	
	public Street()
	{
		street = new GRect(0,HouseApp.APP_SIZE.getHeight()*.8,
				HouseApp.APP_SIZE.getWidth(),HouseApp.APP_SIZE.getHeight()*.1);
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
