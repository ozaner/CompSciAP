package unit4.graphicsProgram;

import java.awt.Color;
import acm.graphics.GCompound;
import acm.graphics.GRect;

@SuppressWarnings("serial")
public class Sky extends GCompound implements Nighttimeable
{
	private GRect sky;
	private FlyingObject flyingObj;
	private Moon moon;
	
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

	@Override
	public void nighttime()
	{
		sky.setColor(Color.BLACK);
		flyingObj.setLight(false);
		moon.setLight(false);
	}

	@Override
	public void daytime()
	{
		sky.setColor(Color.CYAN);
		flyingObj.setLight(true);
		moon.setLight(true);
	}
}
