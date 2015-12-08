package unit4.graphicsProgram;

import java.util.Timer;
import java.util.TimerTask;

import acm.graphics.GCompound;
import acm.graphics.GImage;

@SuppressWarnings("serial")
public class FlyingObject extends GCompound implements Nighttimeable
{
	private GImage airplane = new GImage("airplaneFlying.png"), santa = new GImage("santaFlying.gif");
	
	/**
	 * Creates 2 images, and sets them to move across the screen.
	 */
	public FlyingObject()
	{
		//Construct GCompound
		add(airplane);
		add(santa);
		createAnimationThread();
		
		daytime(); //init compound
	}
	
	/**
	 * Creates a new {@link Timer} with a {@link TimerTask} that plays the object's animation.
	 */
	private void createAnimationThread()
	{
		GCompound temp = this;
		
		new Timer().scheduleAtFixedRate(new TimerTask()
		{
			@Override
			public void run()
			{
				while(temp.getX() + temp.getWidth() > 0)
				{
					temp.move(-10, 0);
					pause(75);
				}
				setLocation(HouseApp.APP_SIZE.getWidth(),0);

			}
			
		}, 3000, 2000);
		setLocation(HouseApp.APP_SIZE.getWidth(),0);
	}
	
	/* Sets the airplane invisible and santa visible.
	 * @see unit4.graphicsProgram.Nighttimeable#nighttime()
	 */
	@Override
	public void nighttime()
	{
		airplane.setVisible(false);
		santa.setVisible(true);
	}

	/* Sets the airplane visible and santa invisible.
	 * @see unit4.graphicsProgram.Nighttimeable#daytime()
	 */
	@Override
	public void daytime()
	{
		airplane.setVisible(true);
		santa.setVisible(false);
	}

}
