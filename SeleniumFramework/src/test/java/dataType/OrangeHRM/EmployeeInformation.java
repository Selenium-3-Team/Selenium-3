package dataType.OrangeHRM;

public enum EmployeeInformation {

	EMPLOYEE_NAME_TEXTBOX("Employee Name"), EMPLOYEE_ID_TEXTBOX("Employee Id"),
	EMPLOYEE_STATUS_DROPDOWN("Employment Status"), INCLUDE_DROPDOWN("Include"),
	SUPERVISOR_NAME_TEXTBOX("Supervisor Name"), JOB_TITLE_DROPDOWN("Job Title"), SUB_UNIT_DROPDOWN("Sub Unit");

	private final String itemName;

	EmployeeInformation(final String itemName) {
		this.itemName = itemName;
	}

	public String getValue() {
		return itemName;
	}
}
