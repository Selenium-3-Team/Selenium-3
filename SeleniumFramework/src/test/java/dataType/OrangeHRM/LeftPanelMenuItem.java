package dataType.OrangeHRM;

public enum LeftPanelMenuItem {
	ADMIN("Admin"), 
	PIM("PIM"), 
	LEAVE("Leave"), 
	TIME("Time"), 
	RECRUITMENT("Recruitment"), 
	MY_INFO("My Info"),
	PERFORMANCE("Performance"), 
	DASHBOARD("Dashboard"), 
	DIRECTORY("Directory"), 
	MANTAINANCE("Mantainamce"),
	BUZZ("Buzz");

	private final String value;

	LeftPanelMenuItem(final String value) {
		this.value = value;
	}

	public String getName() {
		return value;
	}

}
