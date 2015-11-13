package unit4;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyListener;

import acm.graphics.G3DRect;
import acm.graphics.GCompound;
import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.graphics.GLine;
import acm.graphics.GMath;
import acm.graphics.GOval;
import acm.graphics.GPolygon;
import acm.graphics.GRect;
import acm.graphics.GRoundRect;
import acm.graphics.GTurtle;
import acm.program.GraphicsProgram;

/** 
 * This class displays a number of simple GObjects.<br><br>
 * 
 * AP Computer Science<br>
 * Dr. Jones<br>
 * November 13th, 2015<br>
 * @author Ozaner Hansha
 **/
@SuppressWarnings("serial")
public class DrawObjects extends GraphicsProgram implements KeyListener
{
	/**
	 * Displays a number of simple GObjects.
	 * @param args  none expected
	 */
	public static void main(String[] args)
	{
		new DrawObjects().start(args);
	}

	/**
	 * The init method is a good place to do initializations, to create
	 * graphical interfaces, and to set up event handlers.  
	 */
	@Override	
	public void init() {
		setSize(1000, 600);   // window width, window height
		setBackground(Color.LIGHT_GRAY);

		//------------------------------------------------
		//          GRect, GRoundRect, and G3DRect
		//------------------------------------------------

		// The following rectangle examples show 3 different placement methods
		// for GRect, GRoundRect, and G3DRect objects.

		GRect rect = new GRect(100, 100, 150, 100);  // x, y, wid, len (x, y specified when created)
		rect.setColor(Color.RED);       // the color of the boundary
		rect.setFillColor(Color.CYAN);  // the color of the interior
		rect.setFilled(true);           // whether to fill it
		add(rect);                      // add the object to the canvas

		GRoundRect rect2 = new GRoundRect(150, 100); // wid, len
		rect2.setFilled(true);
		rect2.setColor(Color.BLUE);
		add(rect2, 100, 250);  // x, y specified when adding to the canvas

		G3DRect rect3 = new G3DRect(100, 100);  // wid, len
		rect3.setFilled(true);
		rect3.setColor(Color.PINK);
		rect3.setRaised(true);        // whether to make it look raised (3D)
		rect3.setLocation(100, 400);  // x, y specified explicitly
		add(rect3);

		//------------------------------------------------
		//                   GOval
		//------------------------------------------------

		GOval oval = new GOval(300, 100, 150, 100);
		oval.setFilled(true);
		oval.setColor(Color.GREEN);
		add(oval);

		// Make two more ovals below the green one, to the right of the rectangles,
		// in two different fill colors, using the other two placement methods.
		// Make the boundary color a different shade than the fill color.
		// Make one of the ovals a circle.

		GOval projOval = new GOval(200, 100);
		projOval.setFilled(true);
		projOval.setFillColor(Color.MAGENTA);
		projOval.setColor(Color.BLACK);
		add(projOval, 300, 250); //add Parameter Placement Method
		
		//Orange-Outlined, White-Filled, Circle
		GOval projOval2 = new GOval(120, 120);
		projOval2.setFilled(true);
		projOval2.setFillColor(Color.WHITE);
		projOval2.setColor(Color.ORANGE);
		projOval2.setLocation(300, 400); //Explicit Placement Method
		add(projOval2);
		
		//------------------------------------------------
		//                   GLine
		//------------------------------------------------
		
		// Create two GLine objects and place them as diagonals inside the 
		// upper-left, cyan rectangle
		
		add(new GLine(100, 100, 250, 200));
		add(new GLine(250, 100, 100, 200));

		//------------------------------------------------
		//                   GPolygon
		//------------------------------------------------

		GPolygon triangle = new GPolygon();
		triangle.addVertex(0, 0);  // lower left vertex
		triangle.addEdge(150,0);   // edge to lower right vertex
		triangle.addEdge(150*GMath.cosDegrees(120), -150*GMath.sinDegrees(120)); // edge to top vertex
		triangle.setFilled(true);
		triangle.setColor(Color.ORANGE);		
		add(triangle, 500, 200);

		// Make a trapezoid below the triangle.  Use a combination of
		// the addVertex and addEdge methods.
		
		GPolygon pinkTrapazoid = new GPolygon();
		pinkTrapazoid.addVertex(0, 0);
		pinkTrapazoid.addEdge(50,-100);
		pinkTrapazoid.addEdge(100,0);
		pinkTrapazoid.addEdge(50,100);
		pinkTrapazoid.setFilled(true);
		pinkTrapazoid.setColor(Color.PINK);
		add(pinkTrapazoid, 500, 350);

		// Make a stop sign (red octagon with white border) below the trapezoid.
		// If you have know about polar coordinate systems, look up and use the 
		// addPolarEdge method.
		
		GPolygon redOctagon = new GPolygon();
		redOctagon.addVertex(0, 0);
		redOctagon.addEdge(50,0);
		redOctagon.addEdge(36,36);
		redOctagon.addEdge(0,50);
		redOctagon.addEdge(-36,36);
		redOctagon.addEdge(-50,0);
		redOctagon.addEdge(-36,-36);
		redOctagon.addEdge(0,-50);
		redOctagon.setFilled(true);
		redOctagon.setColor(Color.WHITE);	
		redOctagon.setFillColor(Color.RED);
		add(redOctagon, 550, 400);

		//------------------------------------------------
		//                   GLabel
		//------------------------------------------------

		// Create a GLabel containing the string "STOP" and add it to
		// the canvas so that it is centered inside the red hexagon.
		
		GLabel stopLabel = new GLabel("STOP");
		stopLabel.setColor(Color.WHITE);
		stopLabel.setFont(new Font("Times New Roman", 35, 25));
		add(stopLabel,545,470);
		
		//------------------------------------------------
		//                   GCompound
		//------------------------------------------------		
		
		// Create a GCompound containing the stop sign octagon,
		// the STOP label, and a sign post.  Place it to the right of
		// the polygons that you created above.	
		
		GRect post = new GRect(565,522,20,75);
		post.setFilled(true);
		post.setFillColor(Color.GRAY);
		GCompound stopSign = new GCompound();
		stopSign.add(redOctagon);
		stopSign.add(stopLabel);
		stopSign.add(post);
		add(stopSign, 200, -350);
		
		//------------------------------------------------
		//                   GImage
		//------------------------------------------------		
	
		// Find a jpg image of a stop sign.  Drag it into the project.
		// Create a GImage with it and place it below your GCompound.
		
		GImage stopPic = new GImage("stopsign.jpg");
		stopPic.scale(0.4);
		add(stopPic, 620, 375);
		
		//------------------------------------------------
		//                   GTurtle
		//------------------------------------------------
		addKeyListener(new DrawObjects());
		
		GTurtle[] gregArmy = new GTurtle[8];
		
		for(int x = 0; x <= gregArmy.length; x++)
		{
			add(gregArmy[x], 100, 200);
		}
		
		int x = 0, y = 0;
		while(x<1200)
		{
			
			x += 20;
		}
	}
} 
