package unit4.graphicsProgram;

import acm.graphics.GCompound;

@SuppressWarnings("serial")
public abstract class Nighttimeable extends GCompound
{
	/**
	 * Sets the object to its daytime status.
	 */
	public abstract void nighttime();
	
	/**
	 * Sets the object to its nighttime status.
	 */
	public abstract void daytime();
}
