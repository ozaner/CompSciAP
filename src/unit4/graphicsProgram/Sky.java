package unit4.graphicsProgram;

import java.awt.Color;

import acm.graphics.GRect;

@SuppressWarnings("serial")
public class Sky extends Nighttimeable
{
	private GRect sky;
	
	public Sky()
	{
		sky = new GRect(0,0,HouseApp.APP_SIZE.getWidth(),HouseApp.APP_SIZE.getHeight());
		sky.setFilled(true);
		sky.setColor(Color.CYAN);
		add(sky);
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
