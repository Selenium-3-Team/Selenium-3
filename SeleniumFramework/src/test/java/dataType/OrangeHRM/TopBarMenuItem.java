package dataType.OrangeHRM;

public enum TopBarMenuItem {

	USERMANAGEMENT("User Management"), JOB("Job"), ORGANIZATION("Organization"), QUALIFICATIONS("Qualifications"),
	NATIONALTITIES("Nationalities"), CORPORATEBRANDING("Corporate Branding"), CONFIGURATION("Configuration"),
	EMPLOYEELIST("Employee List"), ADDEMPLOYEELIST("Add Employee"), REPORTS("Reports");

	private final String value;

	TopBarMenuItem(final String value) {
		this.value = value;
	}

	public String getName() {
		return value;
	}
}
