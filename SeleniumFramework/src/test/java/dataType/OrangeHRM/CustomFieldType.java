package dataType.OrangeHRM;

public enum CustomFieldType {

	TEXT_OR_NUMBER("Text or Number"), DROP_DOWN("Drop Down");

	private final String itemName;

	CustomFieldType(final String itemName) {
		this.itemName = itemName;
	}

	public String getValue() {
		return itemName;
	}
	
}
