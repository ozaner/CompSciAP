package unit4.graphicsProgram;

import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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
	public static final Dimension APP_SIZE = new Dimension(700,400);
	
	/**
	 * Whether or not the program is displaying daytime. If false then it is nighttime.
	 */
	public static final boolean IS_DAY = true; 
	
	/**
	 * The house that is part of the scene
	 */
	public static House house = new House();
	
	/**
	 * The grass that is part of the scene
	 */
	public static Grass grass = new Grass();
	
	/**
	 * The sky that is part of the scene
	 */
	public static Sky sky = new Sky();

	/**
	 * The sky that is part of the scene
	 */
	public static Airplane plane = new Airplane();
	
	/**
	 * The street that is part of the scene
	 */
	public static Street street = new Street();
	
	public HouseApp(int x, int y)
	{
		setSize(x,y);
		add(house);
		add(grass);
		add(sky);
		add(street);
		add(plane);
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
		setSize(APP_SIZE.width,APP_SIZE.height);
	}
	
	public void mouseClicked(MouseEvent e)
	{
		if(IS_DAY)
			setNightTime();
		else
			setDayTime();
	}
	
	public static void setDayTime()
	{
		
	}
	
	public static void setNightTime()
	{
		
	}
	
	public static void main(String[] args)
	{
		new HouseApp(700,350).start();
		
		
//		while(true)
//		{
//			if(getWidth() >= getHeight())
//				setSize((int)(getWidth()*ASPECT_RATIO_WIDTH), (int)(getWidth()*ASPECT_RATIO_HEIGHT));
//			else
//				setSize((int)(getHeight()*ASPECT_RATIO_WIDTH), (int)(getHeight()*ASPECT_RATIO_HEIGHT));
//		}
	}
}
