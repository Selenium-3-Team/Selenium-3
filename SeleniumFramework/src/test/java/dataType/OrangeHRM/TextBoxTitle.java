package dataType.OrangeHRM;

public enum TextBoxTitle {

	EMPLOYEE_ID("Employee Id"), CURRENT_PASSWORD("Current Password"), NEW_PASSWORD("Password"),
	CONFIRM_PASSWORD("Confirm Password"), FIELD_NAME("Field Name"), SELECT_OPTIONS("Select Options"),
	FROMDATE("From Date"), TODATE("To Date"), EMPLOYEE_NAME("Employee Name"), USERNAME("Username"),
	PASSWORD("Password");

	private final String value;

	TextBoxTitle(final String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
