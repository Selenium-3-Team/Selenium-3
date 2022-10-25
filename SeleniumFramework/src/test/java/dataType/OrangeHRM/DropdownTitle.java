package dataType.OrangeHRM;

public enum DropdownTitle {

	USER_ROLE("User Role"), STATUS("Status"), EMPLOYEE_STATUS("Employment Status"), SCREEN("Screen"), TYPE("Type"),
	LEAVETYPE("Leave Type"), JOB_TITLE("Job Title"), LOCATION("Location");

	private final String value;

	DropdownTitle(final String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
