package dataType.OrangeHRM;

public enum TextBoxTitle {

	EMPLOYEE_ID("Employee Id"), CURRENT_PASSWORD("Current Password"), NEW_PASSWORD("Password"),
	CONFIRM_PASSWORD("Confirm Password"), FIELD_NAME("Field Name"), SELECT_OPTIONS("Select Options"),
	FROMDATE("From Date"), TODATE("To Date"), EMPLOYEE_NAME("Employee Name"), PROJECT_NAME("Project Name"),
	EMAIL("Email"), USERNAME("Username"), PASSWORD("Password"), STREET_1("Street 1"), STREET_2("Street 2"),
	CITY("City"), PROVINCE("State/Province"), POSTAL_CODE("Zip/Postal Code"), HOME("Home"), MOBILE("Mobile"),
	WORK("Work"), WORK_EMAIL("Work Email"), OTHER_EMAIL("Other Email");

	private final String value;

	TextBoxTitle(final String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
