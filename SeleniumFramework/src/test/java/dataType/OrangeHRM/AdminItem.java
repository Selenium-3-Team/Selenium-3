package dataType.OrangeHRM;

public enum AdminItem {
	USERMANAGEMENT("User Management"), 
	JOB("Job"), 
	ORGANIZATION("Organization"), 
	QUALIFICATIONS("Qualifications"),
	NATIONALTITIES("Nationalities"),
	CORPORATEBRANDING("Corporate Branding");

	private final String value;

	AdminItem(final String value) {
		this.value = value;
	}

	public String getName() {
		return value;
	}

}
