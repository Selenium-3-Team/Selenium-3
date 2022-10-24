package dataType.OrangeHRM;

public enum AddCustomField {

	FIELD_NAME("Field Name"), SCREEN("Screen"), TYPE("Type"), SELECT_OPTIONS("Select Options");

	private final String itemName;

	AddCustomField(final String itemName) {
		this.itemName = itemName;
	}

	public String getValue() {
		return itemName;
	}
	
}
