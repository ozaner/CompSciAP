package unit4.graphicsProgram;

import java.awt.Color;
import acm.graphics.GArc;
import acm.graphics.GCompound;
import acm.graphics.GLine;
import acm.graphics.GOval;

@SuppressWarnings("serial")
public class Snowman extends GCompound implements Nighttimeable
{
	private GOval[] body = new GOval[3];
	private GOval eyeLeft, eyeRight;
	private GArc mouth, nose;
	private GLine armLeft = new GLine(0,0,0,0), armRight = new GLine(0,0,0,0);
	
	/**
	 * Constructs a snowman with 3 white ovals, and black facial features.
	 */
	public Snowman(double x, double y)
	{
		//Body
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
		
		//Eyes
		double eyeY = body[2].getY()+body[2].getHeight()*.2;
		double eyeWidth = body[2].getWidth()*.2, eyeHeight = body[2].getHeight()*.2;
		
		eyeLeft = new GOval(body[2].getX()+body[2].getWidth()*.2,eyeY,eyeWidth,eyeHeight);
		eyeLeft.setFilled(true);
		add(eyeLeft);
		
		eyeRight = new GOval(body[2].getX()+body[2].getWidth()*.6,eyeY,eyeWidth,eyeHeight);
		eyeRight.setFilled(true);
		add(eyeRight);
		
		//Mouth
		mouth = new GArc(body[2].getWidth()*.8,body[2].getHeight()*.8,0,0);
		mouth.setFilled(true);
		add(mouth);
		
		//Nose
		nose = new GArc(body[2].getWidth()*.4,body[2].getHeight()*.6,0,60);
		nose.setFilled(true);
		add(nose, body[2].getX()+body[2].getWidth()*.2,body[2].getY()+body[2].getHeight()*.2);
		
		//Arms
		add(armLeft);
		add(armRight);
		
		daytime(); //init compound
	}
	
	/* Turns eyes red, makes mouth frown,
	 * @see unit4.graphicsProgram.Nighttimeable#nighttime()
	 */
	@Override
	public void nighttime()
	{
		for(GOval g: body)
		{
			g.setFillColor(Color.LIGHT_GRAY);
		}
		eyeLeft.setFillColor(Color.RED);
		eyeRight.setFillColor(Color.RED);
		mouth.setLocation(body[2].getX()+body[2].getWidth()*.1,body[2].getY()+body[2].getHeight()*.43);
		mouth.setSweepAngle(180);
		nose.setFillColor(Color.DARK_GRAY);
		
		//Arms
		double armStartY = body[1].getY()+body[1].getHeight()*.5;
		double armEndY = body[1].getY()+body[1].getHeight();
		armLeft.setColor(Color.BLACK);
		armLeft.setStartPoint(body[1].getX()+body[1].getWidth()*.8,armStartY);
		armLeft.setEndPoint(body[1].getX()+body[1].getWidth()*1.8,armEndY);
		armRight.setColor(Color.BLACK);
		armRight.setStartPoint(body[1].getX()+body[1].getWidth()*.2,armStartY);
		armRight.setEndPoint(body[1].getX()+body[1].getWidth()*-.8,armEndY);
	}

	/* Turns eyes black, makes mouth smile,
	 * @see unit4.graphicsProgram.Nighttimeable#nighttime()
	 */
	@Override
	public void daytime()
	{
		for(GOval g: body)
		{
			g.setFillColor(Color.WHITE);
		}
		eyeLeft.setFillColor(Color.BLACK);
		eyeRight.setFillColor(Color.BLACK);
		mouth.setLocation(body[2].getX()+body[2].getWidth()*.1,body[2].getY()+body[2].getHeight()*.1);
		mouth.setSweepAngle(-180);
		nose.setFillColor(Color.ORANGE);
		
		Color brown = new Color(156, 65, 82);
		double armStartY = body[1].getY()+body[1].getHeight()*.5;
		double armEndY = body[1].getY()+body[1].getHeight()*.1;
		armLeft.setColor(brown);
		armLeft.setStartPoint(body[1].getX()+body[1].getWidth()*.8,armStartY);
		armLeft.setEndPoint(body[1].getX()+body[1].getWidth()*1.8,armEndY);
		armRight.setColor(brown);
		armRight.setStartPoint(body[1].getX()+body[1].getWidth()*.2,armStartY);
		armRight.setEndPoint(body[1].getX()+body[1].getWidth()*-.8,armEndY);
	}

}
