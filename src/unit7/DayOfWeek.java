package unit7;
public enum DayOfWeek {
	SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY;
	
	public String toString() {
		String name = name();
		return name.substring(0,1).toUpperCase() + name.substring(1).toLowerCase();
	}
}
