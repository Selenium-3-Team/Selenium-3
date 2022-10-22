package dataType.OrangeHRM;

public enum EmployeeInfoRecordColumnTitle {

	NULL(""), FIRST_AND_MIDDILE_NAME("First (& Middle) Name"),
	LAST_NAME("Last Name"), JOB_TITLE("Job Title"),
	EMPLOYMENT_STATUS("Employment Status"), SUB_UNIT("Sub Unit"), SUPERVISOR("Supervisor"), ACTIONS("Actions");

	private final String itemName;

	EmployeeInfoRecordColumnTitle(final String itemName) {
		this.itemName = itemName;
	}

	public String getValue() {
		return itemName;
	}
	
}
