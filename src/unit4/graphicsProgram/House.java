package unit4.graphicsProgram;

import java.awt.Color;
import acm.graphics.GCompound;
import acm.graphics.GPolygon;
import acm.graphics.GRect;

/**
 * This class creates house objects based on {@link GCompound}.
 * @author Ozaner Hansha
 */
@SuppressWarnings("serial")
public class House extends GCompound implements Nighttimeable
{
	private GPolygon roof;
	private GRect body, door, garage, driveway;
	private GRect[] windows = new GRect[3];
	
	/**
	 * Creates a house with a red roof, magenta body, white windows, gray garage, and black driveway.
	 * @param x - The x coordinate of the top left of the house's base.
	 * @param y - The y coordinate of the top left of the house's base.
	 */
	public House(double x, double y)
	{
		//Roof
		roof = new GPolygon(x,y);
		roof.addVertex(0, 0);
		roof.addEdge(HouseApp.APP_SIZE.getWidth()*.1, -.2*HouseApp.APP_SIZE.getHeight());
		roof.addVertex(HouseApp.APP_SIZE.getWidth()*.5, 0);
		roof.setFilled(true);
		add(roof);
		
		//Body
		body = new GRect(x,y,HouseApp.APP_SIZE.getWidth()*.5,HouseApp.APP_SIZE.getHeight()*.3);
		body.setFilled(true);
		add(body);
		
		//Door
		door = new GRect(body.getWidth()*.12,body.getHeight()*.55);
		door.setFilled(true);
		add(door,body.getX()+body.getWidth()*.2,body.getY()+body.getHeight()-door.getHeight());
		
		//Windows
		double windowY = body.getY()+body.getHeight()*.2;
		double windowWidth = body.getWidth()*.1, windowHeight = body.getHeight()*.5;
		
		windows[0] = new GRect(body.getX()+body.getWidth()*.05,windowY,windowWidth,windowHeight);
		windows[1] = new GRect(body.getX()+body.getWidth()*.4,windowY,windowWidth,windowHeight);
		windows[2] = new GRect(body.getX()+body.getWidth()*.55,windowY,windowWidth,windowHeight);
		for(GRect g: windows)
		{
			g.setFilled(true);
			add(g);
		}
		
		//Garage
		garage = new GRect(body.getWidth()*.2,body.getHeight()*.55);
		garage.setFilled(true);
		add(garage,body.getX()+body.getWidth()*.73,body.getY()+body.getHeight()-garage.getHeight());
		
		//Driveway
		driveway = new GRect(body.getWidth()*.2,body.getHeight()*.7);
		driveway.setFilled(true);
		add(driveway,garage.getX(),garage.getY()+garage.getHeight());
		
		daytime(); //init Compound
	}

	/* Sets the roof, door, and garage dark gray. Sets the body light gray.
	 * Sets all the windows yellow.
	 * @see unit4.graphicsProgram.Nighttimeable#nighttime()
	 */
	@Override
	public void nighttime()
	{
		roof.setFillColor(Color.DARK_GRAY);
		body.setFillColor(Color.LIGHT_GRAY);
		door.setFillColor(Color.DARK_GRAY);
		garage.setFillColor(Color.DARK_GRAY);
		for(GRect g: windows)
		{
			g.setFillColor(Color.YELLOW);
		}
	}

	/* Sets the roof red, the body magenta, the door orange,
	 * the garage light gray, and all the windows white.
	 * @see unit4.graphicsProgram.Nighttimeable#daytime()
	 */
	@Override
	public void daytime()
	{
		roof.setFillColor(Color.RED);
		body.setFillColor(Color.MAGENTA);
		door.setFillColor(Color.ORANGE);
		garage.setFillColor(Color.LIGHT_GRAY);
		for(GRect g: windows)
		{
			g.setFillColor(Color.WHITE);
		}
	}
	
}
