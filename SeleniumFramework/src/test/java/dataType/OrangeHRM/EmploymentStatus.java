package dataType.OrangeHRM;

public enum EmploymentStatus {

	FREELANCE("Freelance"), FULL_TIME_CONTRACT("Full-Time Contract"), FULL_TIME_PERMANENT("Full-Time Permanent"),
	FULL_TIME_PROBATION("Full-Time Probation"), PART_TIME_CONTRACT("Part-Time Contract"),
	PART_TIME_INTERNSHIP("Part-Time Internship");

	private final String itemName;

	EmploymentStatus(final String itemName) {
		this.itemName = itemName;
	}

	public String getValue() {
		return itemName;
	}

}
