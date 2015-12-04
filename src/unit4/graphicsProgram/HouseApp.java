package unit4.graphicsProgram;

import java.awt.Dimension;
import java.awt.event.MouseEvent;
import acm.graphics.GCompound;
import acm.program.GraphicsProgram;

/**
 * This is the Main Class of the Graphics Program Project.
 * 
 * AP Computer Science - Pd. 7<br>
 * December 3rd, 2015<br>
 * Dr. Jones<br>
 * @author Ozaner Hansha
 */
@SuppressWarnings("serial")
public class HouseApp extends GraphicsProgram
{	
	/**
	 * The width and height of the application.
	 */
	public static final Dimension APP_SIZE = new Dimension(700,450);
	
	/**
	 * Whether or not the program is displaying daytime. If false then it is nighttime.
	 */
	public boolean isDay = true;
	
	/**
	 * An array of all objects in the scene.
	 */
	public Object[] objects = 
	{
		new Sky(),new Grass(),new Street()
	};
	
	/**
	 * Places all objects in the Graphics Scene.
	 * @param x - Width of the program window.
	 * @param y - Height of the program window.
	 */
	public HouseApp(int x, int y)
	{
		setSize(x,y);
		for(Object g: objects)
		{
			add((GCompound)g);
		}
	}
	
	/* 
	 * Creates Scene using {LINK CLASSES HERE}
	 * @see acm.program.GraphicsProgram#init()
	 */
	@Override
	public void init()
	{
		addMouseListeners();
	}
	
	@Override
	public void run()
	{
		setSize((int)APP_SIZE.getWidth(),(int)APP_SIZE.getHeight());
	}
	
	@Override
	public void mouseClicked(MouseEvent e)
	{
		isDay = !isDay;
		
		for(Object g: objects)
		{
			((Nighttimeable)g).setLight(isDay);
		}
	}
	
	/**
	 * The main method of the {@link HouseApp} program.<br>
	 * Creates a new Graphics Scene.
	 * @param args - Not expecting any command line input.
	 */
	public static void main(String[] args)
	{
		new HouseApp((int)APP_SIZE.getWidth(),(int)APP_SIZE.getHeight()).start();
	}
}
