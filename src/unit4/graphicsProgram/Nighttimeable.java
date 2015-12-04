package unit4.graphicsProgram;

@SuppressWarnings("serial")
public interface Nighttimeable
{
	public default void setLight(boolean day)
	{
		if(day)
			daytime();
		else
			nighttime();
	}
	
	/**
	 * Sets the object to its daytime status.
	 */
	public abstract void nighttime();
	
	/**
	 * Sets the object to its nighttime status.
	 */
	public abstract void daytime();
}
