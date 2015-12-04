package unit4.graphicsProgram;

import java.awt.Color;
import acm.graphics.GCompound;
import acm.graphics.GRect;

@SuppressWarnings("serial")
public class Sky extends GCompound implements Nighttimeable
{
	private GRect sky;
	
	public Sky()
	{
		sky = new GRect(0,0,HouseApp.APP_SIZE.getWidth(),HouseApp.APP_SIZE.getHeight()*.5);
		sky.setFilled(true);
		sky.setColor(Color.CYAN);
		add(sky);
	}

	@Override
	public void nighttime()
	{
		sky.setColor(Color.BLACK);
	}

	@Override
	public void daytime()
	{
		sky.setColor(Color.CYAN);
	}
}
