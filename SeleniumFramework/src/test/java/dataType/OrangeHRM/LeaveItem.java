package dataType.OrangeHRM;

public enum LeaveItem {

	LEAVETYPE("Leave Type"), FROMDATE("From Date"), TODATE("To Date");

	private final String value;

	LeaveItem(final String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
