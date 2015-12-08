package unit4.graphicsProgram;

/**
 * This interface allows all objects to share common functionality,
 * in regards to their day/night cycle.
 * @author Ozaner Hansha
 */
public interface Nighttimeable
{
	/**
	 * Calls the {@link #daytime()} if day is true and {@link #nighttime()} if not.
	 * @param day - Whether or not is is day.
	 */
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
