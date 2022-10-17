package dataType.OrangeHRM;

public enum PIMItem {
	CONFIGURATION("Configuration"), 
	EMPLOYEELIST("Employee List"), 
	ADDEMPLOYEELIST("Add Employee"), 
	REPORTS("Reports");

	private final String value;

	PIMItem(final String value) {
		this.value = value;
	}

	public String getName() {
		return value;
	}

}
