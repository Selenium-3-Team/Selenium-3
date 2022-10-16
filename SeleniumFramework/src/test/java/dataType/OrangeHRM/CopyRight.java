package dataType.OrangeHRM;

public enum CopyRight {
	VERSION("OrangeHRM OS 5.1"), 
	COMPANY("OrangeHRM, Inc");

	private final String value;

	CopyRight(final String value) {
		this.value = value;
	}

	public String getData() {
		return value;
	}

}
