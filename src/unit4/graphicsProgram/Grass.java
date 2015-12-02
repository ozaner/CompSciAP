package unit4.graphicsProgram;

import java.awt.Color;

import acm.graphics.GCompound;
import acm.graphics.GRect;

@SuppressWarnings("serial")
public class Grass extends GCompound
{	
	public Grass()
	{
		super(0,(int)(HouseApp.APP_SIZE.getHeight()*(6/15)),(int)(HouseApp.APP_SIZE.getWidth()),(int)(HouseApp.APP_SIZE.getHeight()*(8/15)));// 8/15 is ratio of road to height
		grass.setFilled(true);
		grass.setColor(Color.GREEN);
		add(grass);
	}
	
	public void nightTime()
	{
		grass.setColor(Color.GRAY);
	}
}
