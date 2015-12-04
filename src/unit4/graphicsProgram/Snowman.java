package unit4.graphicsProgram;

import java.awt.Color;
import acm.graphics.GCompound;
import acm.graphics.GOval;

@SuppressWarnings("serial")
public class Snowman extends GCompound implements Nighttimeable
{
	private GOval[] body = new GOval[3];
	
	/**
	 * Constructs a snowman with 3 white ovals, and black facial features.
	 */
	public Snowman(double x, double y)
	{
		body[2] = new GOval(x,y,HouseApp.APP_SIZE.getHeight()*.06,HouseApp.APP_SIZE.getHeight()*.06); //Head
		body[1] = new GOval(x-body[2].getWidth()/3,body[2].getY()+body[2].getHeight()*.9,
				HouseApp.APP_SIZE.getHeight()*.1,HouseApp.APP_SIZE.getHeight()*.1); //Middle
		body[0] = new GOval(x-body[1].getWidth()/3,body[1].getY()+body[1].getHeight()*.9,
				HouseApp.APP_SIZE.getHeight()*.12,HouseApp.APP_SIZE.getHeight()*.12); //End
		for(GOval g: body)
		{
			g.setFilled(true);
			g.setFillColor(Color.WHITE);
			add(g);
		}
	}
	
	@Override
	public void nighttime()
	{
		for(GOval g: body)
		{
			g.setFillColor(Color.LIGHT_GRAY);
		}
	}

	@Override
	public void daytime()
	{
		for(GOval g: body)
		{
			g.setFillColor(Color.WHITE);
		}
	}

}
