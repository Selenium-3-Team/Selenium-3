package dataType.OrangeHRM;

public enum DirectoryForm {

	EMPLOYEE_NAME_TEXTBOX("Employee Name"), JOB_TITLE_DROPDOWN("Job Title"), LOCATION_DROPDOWN("Location");

	private final String itemName;

	DirectoryForm(final String itemName) {
		this.itemName = itemName;
	}

	public String getValue() {
		return itemName;
	}

}
