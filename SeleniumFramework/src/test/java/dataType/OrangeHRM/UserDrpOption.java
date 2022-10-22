package dataType.OrangeHRM;

public enum UserDrpOption {
	
	ABOUT("About"), SUPPORT("Support"), CHANGE_PASSWORD("Change Password"), LOGOUT("Logout");

	private final String itemName;

	UserDrpOption(final String itemName) {
		this.itemName = itemName;
	}

	public String getValue() {
		return this.itemName;
	}
}
