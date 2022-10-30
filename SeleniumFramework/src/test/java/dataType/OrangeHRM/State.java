package dataType.OrangeHRM;

public enum State {

	ACTIVE("active"), FOCUS("focus"), ENABLED("Enabled"), DISABLED("Disabled");

	private final String value;

	State(final String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
