package unit4.graphicsProgram;

import java.awt.Color;
import java.util.Timer;
import java.util.TimerTask;

import acm.graphics.GCompound;
import acm.graphics.GImage;
import acm.graphics.GRect;

@SuppressWarnings("serial")
public class Grass extends GCompound implements Nighttimeable
{	
	private GRect grass;
	private Snowman[] snowmen = new Snowman[3];
	private House house;
	private GImage planeTakeOff;
	
	public Grass()
	{
		//Grass Background
		grass = new GRect(0,HouseApp.APP_SIZE.getHeight()*.35,
				HouseApp.APP_SIZE.getWidth(),HouseApp.APP_SIZE.getHeight()*.5);
		grass.setFilled(true);
		grass.setFillColor(Color.GREEN);
		add(grass);
		
		//House
		house = new House(HouseApp.APP_SIZE.getWidth()*.2,HouseApp.APP_SIZE.getHeight()*.3);
		add(house);
		
		//Plane take off
		planeTakeOff = new GImage("planeTakeOff.gif",HouseApp.APP_SIZE.getWidth()*.7,0);
		add(planeTakeOff);
		
		//3 Snowmen
		snowmen[0] = new Snowman(HouseApp.APP_SIZE.getWidth()*.1,HouseApp.APP_SIZE.getHeight()*.4); //Left Snowman
		snowmen[1] = new Snowman(HouseApp.APP_SIZE.getWidth()*.5,HouseApp.APP_SIZE.getHeight()*.5); //Middle Snowman
		snowmen[2] = new Snowman(HouseApp.APP_SIZE.getWidth()*.8,HouseApp.APP_SIZE.getHeight()*.4); //Right Snowman
		for(Snowman s: snowmen)
			add(s);
	}

	/**
	 * Sets the {@link planeTakeOff} object invisible after 2.5 seconds.
	 */
	public void takeOffPlane()
	{
		new Timer().schedule(new TimerTask()
		{
			@Override
			public void run()
			{
				planeTakeOff.setVisible(false);
			}
			
		}, 2500);
	}
	
	@Override
	public void nighttime()
	{
		grass.setFillColor(Color.GRAY);
		house.nighttime();
		for(Snowman s: snowmen)
			s.nighttime();
	}

	@Override
	public void daytime()
	{
		grass.setFillColor(Color.GREEN);
		house.daytime();
		for(Snowman s: snowmen)
			s.daytime();
	}
}
