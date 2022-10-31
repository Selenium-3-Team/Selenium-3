package dataType.OrangeHRM;

public enum FrameTitle {

	SYSTEM_USERS("System Users"), CHANGE_PROFILE_PICTURE("Change Profile Picture"), CUSTOM_FIELDS("Custom Fields"),
	ATTENDANCE_SUMMARY("Attendance Total Summary Report");

	private final String itemName;

	FrameTitle(final String itemName) {
		this.itemName = itemName;
	}

	public String getValue() {
		return itemName;
	}

}
