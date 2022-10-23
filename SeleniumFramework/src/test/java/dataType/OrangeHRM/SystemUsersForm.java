package dataType.OrangeHRM;

public enum SystemUsersForm {

	USERNAME_TEXTBOX("Username"), USER_ROLE_DROPDOWN("User Role"), EMPLOYEE_NAME_TEXTBOX("Employee Name"), STATUS_DROPDOWN("Status");

	private final String value;

	SystemUsersForm(final String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
