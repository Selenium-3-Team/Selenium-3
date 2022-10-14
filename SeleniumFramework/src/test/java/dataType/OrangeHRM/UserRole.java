package dataType.OrangeHRM;

public enum UserRole {
	/**
	 * Declare constants for user role
	 */
	ADMIN("admin"), ESS("ess");

	private final String userRole;

	UserRole(final String userRole) {
		this.userRole = userRole;
	}

	/**
	 * 
	 * Gets the user role.
	 * 
	 * @return String, the user role
	 */
	public String getUserRole() {
		return this.userRole;
	}

}
