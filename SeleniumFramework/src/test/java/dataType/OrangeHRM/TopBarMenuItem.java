package dataType.OrangeHRM;

public enum TopBarMenuItem {

	USER_MANAGEMENT("User Management"), JOB("Job"), ORGANIZATION("Organization"), QUALIFICATIONS("Qualifications"),
	NATIONALTITIES("Nationalities"), CORPORATE_BRANDING("Corporate Branding"), CONFIGURATION("Configuration"),
	EMPLOYEE_LIST("Employee List"), ADD_EMPLOYEE("Add Employee"), REPORTS("Reports"), APPLY("Apply"),
	MYLEAVE("My Leave"), ENTITLEMENTS("Entitlements"), CONFIGURE("Configure"), LEAVELIST("Leave List"),
	ASSIGNLEAVE("Assign Leave"), CUSTOM_FIELDS("Custom Fields");

	private final String value;

	TopBarMenuItem(final String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
