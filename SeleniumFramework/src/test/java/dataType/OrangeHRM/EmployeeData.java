package dataType.OrangeHRM;

public enum EmployeeData {
	/**
	 * Declare constants for employee for test case
	 */
	EMPLOYEETC06("employeetc06"), EMPLOYEETC07("employeetc07");

	private final String employeeData;

	EmployeeData(final String employeeData) {
		this.employeeData = employeeData;
	}

	/**
	 * 
	 * Gets the user role.
	 * 
	 * @return String, the user role
	 */
	public String getValue() {
		return this.employeeData;
	}

}
