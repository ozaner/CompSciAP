package unit4.graphicsProgram;

import java.awt.Color;
import acm.graphics.GCompound;
import acm.graphics.GPolygon;
import acm.graphics.GRect;

@SuppressWarnings("serial")
public class House extends GCompound implements Nighttimeable
{
	private GPolygon roof;
	private GRect body;
	private GRect door;
	private GRect windows[] = new GRect[3];
	
	public House(double x, double y)
	{
		//Roof
		roof = new GPolygon(x,y);
		roof.addVertex(0, 0);
		roof.addEdge(HouseApp.APP_SIZE.getWidth()*.1, -.2*HouseApp.APP_SIZE.getHeight());
		roof.addVertex(HouseApp.APP_SIZE.getWidth()*.5, 0);
		roof.setFilled(true);
		roof.setFillColor(Color.RED);
		add(roof);
		
		//Body
		body = new GRect(x,y,HouseApp.APP_SIZE.getWidth()*.5,HouseApp.APP_SIZE.getHeight()*.3);
		body.setFilled(true);
		body.setFillColor(Color.MAGENTA);
		add(body);
	}

	@Override
	public void nighttime()
	{
		roof.setFillColor(Color.DARK_GRAY);
		body.setFillColor(Color.LIGHT_GRAY);
	}

	@Override
	public void daytime()
	{
		roof.setFillColor(Color.RED);
		body.setFillColor(Color.MAGENTA);
	}
	
}
