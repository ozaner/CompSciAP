package unit4.graphicsProgram;

import java.awt.Color;
import acm.graphics.GCompound;
import acm.graphics.GPolygon;
import acm.graphics.GRect;

@SuppressWarnings("serial")
public class House extends GCompound implements Nighttimeable
{
	private GPolygon roof;
	private GRect body, door, garage, driveway;
	private GRect[] windows = new GRect[3];
	
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
