package dataType.OrangeHRM;

public enum EmployeeInformationTypeTab {

	PERSONAL_DETAILS("Personal Details"), CONTACT_DETAILS("Contact Details"), EMERGENCY_CONTACTS("Emergency Contacts"),
	DEPENDENTS("Dependents"), IMMIGRATION("Immigration"),
	JOB("Job"), SALARY("Salary"), TAX_EXEMPTIONS("Tax Exemptions"),
	REPORT_TO("Report-to"), QUALIFICATIONS("Qualifications"),
	MEMBERSHIPS("Memberships");

	private final String itemName;

	EmployeeInformationTypeTab(final String itemName) {
		this.itemName = itemName;
	}

	public String getValue() {
		return itemName;
	}
	
}
