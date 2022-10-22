package dataType.OrangeHRM;

public enum UpdatePasswordForm {

	CURRENT_PASSWORD("Current Password"), NEW_PASSWORD("Password"), CONFIRM_PASSWORD("Confirm Password");

	private final String itemName;

	UpdatePasswordForm(final String itemName) {
		this.itemName = itemName;
	}

	public String getValue() {
		return this.itemName;
	}
}
