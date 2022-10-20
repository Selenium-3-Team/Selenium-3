package dataType.OrangeHRM;

public enum SystemUsers {

	USERNAME_TEXTBOX("Username"), USER_ROLE_DROPDOWN("User Role"), EMPLOYEE_NAME_TEXTBOX("Employee Name"), STATUS_DROPDOWN("Status");

	private final String value;

	SystemUsers(final String value) {
		this.value = value;
	}

	public String getName() {
		return value;
	}
}
