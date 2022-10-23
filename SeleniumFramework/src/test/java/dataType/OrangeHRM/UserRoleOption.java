package dataType.OrangeHRM;

public enum UserRoleOption {
	/**
	 * Declare constants for user role
	 */
	ADMIN("Admin"), ESS("ESS");

	private final String userRole;

	UserRoleOption(final String userRole) {
		this.userRole = userRole;
	}

	/**
	 * 
	 * Gets the user role.
	 * 
	 * @return String, the user role
	 */
	public String getValue() {
		return this.userRole;
	}

}
